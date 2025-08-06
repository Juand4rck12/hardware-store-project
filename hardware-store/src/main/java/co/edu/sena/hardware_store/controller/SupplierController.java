package co.edu.sena.hardware_store.controller;

import co.edu.sena.hardware_store.model.Supplier;
import co.edu.sena.hardware_store.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SupplierController {
    @Autowired
    SupplierRepository supplierRepository;

    @GetMapping("/view/supplier")
    public String list(Model model) {
        model.addAttribute("supplier", supplierRepository.findAll());
        return "supplier";
    }

    @GetMapping("/view/supplier/form")
    public String form(Model model) {
        model.addAttribute("supplier", new Supplier());
        return "supplier_form";
    }

    @PostMapping("/view/supplier/save")
    public String save(@ModelAttribute Supplier supplier, RedirectAttributes ra) {
        supplierRepository.save(supplier);
        ra.addFlashAttribute("success", "Proveedor guardado");
        return "redirect:/view/supplier";
    }

    @GetMapping("/view/supplier/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Supplier supplier = supplierRepository.findById(id).orElse(null);
        model.addAttribute("supplier", supplier);
        return "supplier_form";
    }

    @PostMapping("/view/supplier/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        supplierRepository.deleteById(id);
        ra.addFlashAttribute("success", "Proveedor eliminado");
        return "redirect:/view/employee";
    }
}
