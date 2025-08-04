package co.edu.sena.hardware_store.controller;

import co.edu.sena.hardware_store.model.Sale;
import co.edu.sena.hardware_store.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SaleController {
    @Autowired
    SaleRepository saleRepository;

    @GetMapping("/view/sale")
    public String list(Model model) {
        model.addAttribute("sale", saleRepository.findAll());
        return "sale";
    }

    @GetMapping("view/sale/form")
    public String form(Model model) {
        model.addAttribute("sale", new Sale());
        return "supplier_form";
    }

    @PostMapping("view/sale/save")
    public String save(@ModelAttribute Sale sale, RedirectAttributes ra) {
        saleRepository.save(sale);
        ra.addFlashAttribute("success", "Venta guardada");
        return "redirect:view/sale";
    }

    @GetMapping("view/sale/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Sale sale = saleRepository.findById(id).orElse(null);
        model.addAttribute("sale", sale);
        return "sale_form";
    }

    @PostMapping("view/sale/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        saleRepository.deleteById(id);
        ra.addFlashAttribute("success", "Venta eliminada");
        return "redirect:/view/sale";
    }
}
