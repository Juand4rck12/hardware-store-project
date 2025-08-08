package co.edu.sena.hardware_store.controller;

import co.edu.sena.hardware_store.model.PurchaseOrder;
import co.edu.sena.hardware_store.repository.CustomerRepository;
import co.edu.sena.hardware_store.repository.EmployeeRepository;
import co.edu.sena.hardware_store.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PurchaseOrderController {
    @Autowired
    PurchaseOrderRepository purchaseOrderRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/view/purchaseOrder")
    public String list(Model model) {
        model.addAttribute("purchaseOrder", purchaseOrderRepository.findAll());
        return "purchaseOrder";
    }

    @GetMapping("/view/purchaseOrder/form")
    public String form(Model model) {
        model.addAttribute("purchaseOrder", new PurchaseOrder());
        model.addAttribute("customer", customerRepository.findAll());
        model.addAttribute("employee", employeeRepository.findAll());
        return "purchaseOrder_form";
    }

    @PostMapping("/view/purchaseOrder/save")
    public String save(@ModelAttribute PurchaseOrder purchaseOrder, RedirectAttributes ra) {
        purchaseOrderRepository.save(purchaseOrder);
        ra.addFlashAttribute("success", "Orden de compra guardada");
        return "redirect:/view/purchaseOrder";
    }

    @GetMapping("/view/purchaseOrder/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(id).orElse(null);
        model.addAttribute("purchaseOrder", purchaseOrder);
        model.addAttribute("customer", customerRepository.findAll());
        model.addAttribute("employee", employeeRepository.findAll());
        return "purchaseOrder_form";
    }

    @PostMapping("/view/purchaseOrder/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        purchaseOrderRepository.deleteById(id);
        ra.addFlashAttribute("success", "Orden de compra eliminada");
        return "redirect:/view/purchaseOrder";
    }
}
