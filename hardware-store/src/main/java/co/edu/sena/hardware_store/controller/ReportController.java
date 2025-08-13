package co.edu.sena.hardware_store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReportController {
    @GetMapping("/view/report")
    public String list(Model model) {
        return "report";
    }
}
