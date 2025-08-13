package co.edu.sena.hardware_store.controller;

import co.edu.sena.hardware_store.model.Employee;
import co.edu.sena.hardware_store.repository.EmployeeRepository;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/view/employee")
    public String list(Model model) {
        model.addAttribute("employee", employeeRepository.findAll());
        List<Employee> employees = employeeRepository.findAll();
        Set<String> roles = employees.
                stream()
                .map(Employee::getRole)
                .sorted()
                .collect(Collectors.toSet());
        model.addAttribute("employees", employees);
        model.addAttribute("roles", roles);
        return "employee";
    }

    @GetMapping("/view/employee/form")
    public String form(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee_form";
    }

    @PostMapping("/view/employee/save")
    public String save(@ModelAttribute Employee employee, RedirectAttributes ra) {
        try {
            employeeRepository.save(employee);
            ra.addFlashAttribute("success", "¡Empleado guardado correctamente!");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "¡Error al guardar el empleado!" + e.getMessage());
        }
        return "redirect:/view/employee";
    }

    @GetMapping("/view/employee/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        model.addAttribute("employee", employee);
        return "employee_form";
    }

    @PostMapping("/view/employee/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        try {
            employeeRepository.deleteById(id);
            ra.addFlashAttribute("success", "¡Empleado eliminado correctamente!");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "¡Error al eliminar el empleado!" + e.getMessage());
        }
        return "redirect:/view/employee";
    }

    @GetMapping("/view/employee/pdf")
    public void exportarPDF(HttpServletResponse response) throws Exception
    {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=Employee.pdf");

        List<Employee> employeeList = employeeRepository.findAll();
        DecimalFormat df = new DecimalFormat("#,###");

        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        document.add(new Paragraph("Listado de empleados"));
        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100);
        table.setSpacingBefore(20);
        float[] columnWidths = {5f, 15f, 18f, 17f, 26f, 29f};
        table.setWidths(columnWidths);

        // Encabezados
        table.addCell("ID");
        table.addCell("Documento");
        table.addCell("Nombre");
        table.addCell("Cargo");
        table.addCell("Salario");
        table.addCell("Rol");

        // Filas
        for (Employee f : employeeList) {
            table.addCell(f.getId_employee().toString());
            table.addCell(f.getDocument().toString());
            table.addCell(f.getName());
            table.addCell(f.getPosition());
            table.addCell(df.format(f.getSalary()));
            table.addCell(f.getRole());
        }

        document.add(table);
        document.close();
    }

    @GetMapping("/view/employee/excel")
    public void exportarExcel(HttpServletResponse response) throws Exception
    {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=Employee.xlsx");

        List<Employee> employeeList = employeeRepository.findAll();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Employee");

        // Crear encabezado
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Documento");
        headerRow.createCell(2).setCellValue("Nombre");
        headerRow.createCell(3).setCellValue("Cargo");
        headerRow.createCell(4).setCellValue("Salario");
        headerRow.createCell(5).setCellValue("Rol");

        // Agregar datos
        int rowNum = 1;
        for (Employee employee : employeeList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(employee.getId_employee());
            row.createCell(1).setCellValue(employee.getDocument());
            row.createCell(2).setCellValue(employee.getName());
            row.createCell(3).setCellValue(employee.getPosition());
            row.createCell(4).setCellValue(employee.getSalary().doubleValue());
            row.createCell(5).setCellValue(employee.getRole());
        }

        // Autoajustar columnas
        for (int i = 0; i < 6; i++) {
            sheet.autoSizeColumn(i);
        }

        workbook.write(response.getOutputStream());
        workbook.close();
    }
}
