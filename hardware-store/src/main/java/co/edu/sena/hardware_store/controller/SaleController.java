package co.edu.sena.hardware_store.controller;

import co.edu.sena.hardware_store.model.*;
import co.edu.sena.hardware_store.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SaleController {
    @Autowired
    SaleRepository saleRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    SaleDetailRepository saleDetailRepository;
    @Autowired
    ProductRepository productRepository;

    @GetMapping("/view/sale")
    public String list(Model model) {
        model.addAttribute("sale", saleRepository.findAll());
        return "sale";
    }

    @GetMapping("/view/sale/form")
    public String form(Model model) {
        model.addAttribute("sale", new Sale());
        model.addAttribute("customers", customerRepository.findAll());
        model.addAttribute("products", productRepository.findAll());
        return "sale_form";
    }

    @PostMapping("/view/sale/save")
    public String save(@ModelAttribute Sale sale, RedirectAttributes ra) {
        try {
            saleRepository.save(sale);
            ra.addFlashAttribute("success", "¡Venta guardada!");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "¡Ocurrio un error al guardar la venta!");
        }
        return "redirect:/view/sale";
    }

    @GetMapping("/view/sale/detail/{id}")
    public String viewSaleDetail(@PathVariable Long id, Model model) {
        Sale sale = saleRepository.findById(id).orElse(null);
        if (sale == null) return "redirect:/view/sale";
        List<SaleDetail> details = saleDetailRepository.findBySale_Id(id);
        model.addAttribute("sale", sale);
        model.addAttribute("saleDetails", details);
        return "sale_detail_modal";
    }

    @PostMapping("/api/customers")
    @ResponseBody
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        try {
            Customer newCustomer = customerRepository.save(customer);
            return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/api/sales")
    @Transactional
    @ResponseBody
    public ResponseEntity<String> createSale(@RequestBody SalePayload payload) {
        try {
            Sale sale = new Sale();
            Customer customer = customerRepository.findById(payload.getCustomerId()).orElseThrow(() -> new RuntimeException("Customer not found"));
            Employee employee = employeeRepository.findById(1).orElseThrow(() -> new RuntimeException("Employee not found")); // Hardcoded employee ID 1
            sale.setId_customer(customer);
            sale.setId_employee(employee);
            sale.setSale_date(LocalDateTime.now());

            double total = 0;

            for (SaleDetailPayload detailPayload : payload.getItems()) {
                Product product = productRepository.findById(detailPayload.getProductId()).orElseThrow(() -> new RuntimeException("Product not found"));

                if (product.getStock_quantity() < detailPayload.getQuantity()) {
                    return new ResponseEntity<>("Not enough stock for product: " + product.getName(), HttpStatus.BAD_REQUEST);
                }

                product.setStock_quantity(product.getStock_quantity() - detailPayload.getQuantity());
                productRepository.save(product);

                SaleDetail saleDetail = new SaleDetail();
                saleDetail.setSale(sale);
                saleDetail.setId_product(product);
                saleDetail.setQuantity(detailPayload.getQuantity());
                saleDetail.setUnit_price(product.getPrice());
                double subtotal = product.getPrice() * detailPayload.getQuantity();
                saleDetail.setSubtotal(subtotal);
                total += subtotal;
                sale.getSaleDetails().add(saleDetail);
            }

            sale.setTotal_amount((long) total);
            saleRepository.save(sale);

            return new ResponseEntity<>("Venta creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public static class SalePayload {
        private Long customerId;
        private List<SaleDetailPayload> items;
        public Long getCustomerId() { return customerId; }
        public void setCustomerId(Long customerId) { this.customerId = customerId; }
        public List<SaleDetailPayload> getItems() { return items; }
        public void setItems(List<SaleDetailPayload> items) { this.items = items; }
    }

    public static class SaleDetailPayload {
        private Long productId;
        private int quantity;
        public Long getProductId() { return productId; }
        public void setProductId(Long productId) { this.productId = productId; }
        public int getQuantity() { return quantity; }
        public void setQuantity(int quantity) { this.quantity = quantity; }
    }
}
