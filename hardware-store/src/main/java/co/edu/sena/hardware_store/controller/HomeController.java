package co.edu.sena.hardware_store.controller;

import co.edu.sena.hardware_store.repository.CustomerRepository;
import co.edu.sena.hardware_store.repository.ProductRepository;
import co.edu.sena.hardware_store.repository.SaleDetailRepository;
import co.edu.sena.hardware_store.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    SaleRepository saleRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    SaleDetailRepository saleDetailRepository;

    @GetMapping("/view/home")
    public String model(Model model) {
        model.addAttribute("product", productRepository.findAll());
        model.addAttribute("sale", saleRepository.findAll());
        model.addAttribute("customer", customerRepository.findAll());
        model.addAttribute("saleDetail", saleDetailRepository.findAll());
        return "home";
    }
}
