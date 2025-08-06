package co.edu.sena.hardware_store.controller;

import co.edu.sena.hardware_store.model.SaleDetail;
import co.edu.sena.hardware_store.repository.ProductRepository;
import co.edu.sena.hardware_store.repository.SaleDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SaleDetailDetailController {
    @Autowired
    SaleDetailRepository saleDetailRepository;
    @Autowired
    ProductRepository productRepository;

    @GetMapping("/view/saleDetail")
    public String list(Model model) {
        model.addAttribute("saleDetail", saleDetailRepository.findAll());
        return "saleDetail";
    }

    @GetMapping("/view/saleDetail/form")
    public String form(Model model) {
        model.addAttribute("saleDetail", new SaleDetail());
        model.addAttribute("product", productRepository.findAll());
        return "saleDetail_form";
    }

    @PostMapping("/view/saleDetail/save")
    public String save(@ModelAttribute SaleDetail saleDetail, RedirectAttributes ra) {
        saleDetailRepository.save(saleDetail);
        ra.addFlashAttribute("success", "Detalle de venta guardada");
        return "redirect:/view/saleDetail";
    }

    @GetMapping("/view/saleDetail/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        SaleDetail saleDetail = saleDetailRepository.findById(id).orElse(null);
        model.addAttribute("saleDetail", saleDetail);
        model.addAttribute("product", productRepository.findAll());
        return "saleDetail_form";
    }

    @PostMapping("view/saleDetail/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        saleDetailRepository.deleteById(id);
        ra.addFlashAttribute("success", "Detalle de venta eliminada");
        return "redirect:/view/saleDetail";
    }
}
