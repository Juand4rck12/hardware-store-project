package co.edu.sena.hardware_store.controller;

import co.edu.sena.hardware_store.model.Product;
import co.edu.sena.hardware_store.repository.ProductRepository;
import co.edu.sena.hardware_store.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProductController {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    SupplierRepository supplierRepository;

    @GetMapping("/view/product")
    public String list(Model model) {
        model.addAttribute("product", productRepository.findAll());
        return "product";
    }

    @GetMapping("/view/product/form")
    public String form(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("supplier", supplierRepository.findAll());
        return "product_form";
    }

    @PostMapping("/view/product/save")
    public String save(@ModelAttribute Product product, RedirectAttributes ra) {
        productRepository.save(product);
        ra.addFlashAttribute("success", "Producto guardado");
        return "redirect:/view/product";
    }

    @GetMapping("/view/product/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Product product = productRepository.findById(id).orElse(null);
        model.addAttribute("product", product);
        model.addAttribute("supplier", supplierRepository.findAll());
        return "product_form";
    }

    @PostMapping("/view/product/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        productRepository.deleteById(id);
        ra.addFlashAttribute("success", "Producto eliminado");
        return "redirect:/view/product";
    }
}
