package co.edu.sena.hardware_store.controller;

import co.edu.sena.hardware_store.model.Sale;
import co.edu.sena.hardware_store.model.SaleDetail;
import co.edu.sena.hardware_store.repository.CustomerRepository;
import co.edu.sena.hardware_store.repository.EmployeeRepository;
import co.edu.sena.hardware_store.repository.SaleDetailRepository;
import co.edu.sena.hardware_store.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/view/sale")
    public String list(Model model) {
        model.addAttribute("sale", saleRepository.findAll());
        return "sale";
    }

    @GetMapping("/view/sale/form")
    public String form(Model model) {
        model.addAttribute("sale", new Sale());
        model.addAttribute("customer", customerRepository.findAll());
        model.addAttribute("employee", employeeRepository.findAll());
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
        // buscar la venta
        Sale sale = saleRepository.findById(id).orElse(null);

        if (sale == null) return "redirect:/view/sale";

        // Buscar detalles asociados a la venta
        List<SaleDetail> details = saleDetailRepository.findBySale_Id(id);

        // Pasar todo al modelo
        model.addAttribute("sale", sale);
        model.addAttribute("saleDetails", details);

        return "sale_detail_modal";
    }
}
