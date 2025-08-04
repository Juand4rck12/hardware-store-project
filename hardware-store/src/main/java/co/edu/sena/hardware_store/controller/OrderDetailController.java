package co.edu.sena.hardware_store.controller;

import co.edu.sena.hardware_store.model.OrderDetail;
import co.edu.sena.hardware_store.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class OrderDetailController {
    @Autowired
    OrderDetailRepository orderDetailRepository;

    @GetMapping("/view/orderDetail")
    public String list(Model model) {
        model.addAttribute("orderDetail", orderDetailRepository.findAll());
        return "orderDetail";
    }

    @GetMapping("view/orderDetail/form")
    public String form(Model model) {
        model.addAttribute("orderDetail", new OrderDetail());
        return "orderDetail_form";
    }

    @PostMapping("view/orderDetail/save")
    public String save(@ModelAttribute OrderDetail orderDetail, RedirectAttributes ra) {
        orderDetailRepository.save(orderDetail);
        ra.addFlashAttribute("success", "Detalle de orden guardado");
        return "redirect:/view/orderDetail";
    }

    @GetMapping("view/orderDetail/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        OrderDetail orderDetail = orderDetailRepository.findById(id).orElse(null);
        model.addAttribute("orderDetail", orderDetail);
        return "orderDetail_form";
    }

    @PostMapping("view/orderDetail/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        orderDetailRepository.deleteById(id);
        ra.addFlashAttribute("success", "Detalle de orden eliminado");
        return "redirect:/view/orderDetail";
    }
}
