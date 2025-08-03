package co.edu.sena.hardware_store.controller;

import co.edu.sena.hardware_store.model.Employee;
import co.edu.sena.hardware_store.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/view/employee")
    public String list(Model model) {
        model.addAttribute("employee", employeeRepository.findAll());
        return "employee";
    }

    @GetMapping("view/employee/form")
    public String form(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee_form";
    }

    @PostMapping("view/employee/save")
    public String save(@ModelAttribute Employee employee, RedirectAttributes ra) {
        employeeRepository.save(employee);
        ra.addFlashAttribute("success", "Empleado guardado");
        return "redirect:view/employee";
    }

    @GetMapping("view/employee/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        model.addAttribute("employee", employee);
        return "employee/form";
    }

    @PostMapping("view/employee/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        employeeRepository.deleteById(id);
        ra.addFlashAttribute("success", "Empleado eliminado");
        return "redirect:/view/employee";
    }
}
