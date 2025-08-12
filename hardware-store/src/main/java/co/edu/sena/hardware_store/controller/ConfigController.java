package co.edu.sena.hardware_store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ConfigController {
    @GetMapping("/view/config")
    public String list(Model model){
        return "config";
    }
}
