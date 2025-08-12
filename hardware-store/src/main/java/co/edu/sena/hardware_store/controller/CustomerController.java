package co.edu.sena.hardware_store.controller;

import co.edu.sena.hardware_store.model.Customer;
import co.edu.sena.hardware_store.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CustomerController {
    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("/view/customer")
    public String list(Model model) {
        model.addAttribute("customer", customerRepository.findAll());
        return "customer";
    }

    @GetMapping("/view/customer/form")
    public String form(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer_form";
    }

    @PostMapping("/view/customer/save")
    public String save(@ModelAttribute Customer customer, RedirectAttributes ra) {
        try {
            customerRepository.save(customer);
            ra.addFlashAttribute("success", "Cliente guardado");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Ocurrió un error al guardar el cliente");
        }
        return "redirect:/view/customer";
    }

    @GetMapping("/view/customer/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Customer customer = customerRepository.findById(id).orElse(null);
        model.addAttribute("customer", customer);
        return "customer_form";
    }

    @PostMapping("/view/customer/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        try {
            customerRepository.deleteById(id);
            ra.addFlashAttribute("success", "Cliente eliminado");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Ocurrió un error al eliminar el cliente");
        }
        return "redirect:/view/customer";
    }
}
