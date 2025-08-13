package co.edu.sena.hardware_store.controller;

import co.edu.sena.hardware_store.model.Customer;
import co.edu.sena.hardware_store.model.Product;
import co.edu.sena.hardware_store.repository.CustomerRepository;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
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

    @GetMapping("/view/customer/pdf")
    public void exportarPDF(HttpServletResponse response) throws Exception
    {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=Customer.pdf");

        List<Customer> customerList = customerRepository.findAll();
        DecimalFormat df = new DecimalFormat("#,###");

        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        document.add(new Paragraph("Listado de clientes"));
        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100);
        table.setSpacingBefore(20);
        float[] columnWidths = {5f, 15f, 18f, 17f, 26f, 29f};
        table.setWidths(columnWidths);

        // Encabezados
        table.addCell("ID");
        table.addCell("Documento");
        table.addCell("Nombre");
        table.addCell("Teléfono");
        table.addCell("Correo eletrónico");
        table.addCell("Dirección");

        // Filas
        for (Customer f : customerList) {
            table.addCell(f.getId_customer().toString());
            table.addCell(f.getDocument().toString());
            table.addCell(f.getName());
            table.addCell(f.getPhone());
            table.addCell(f.getEmail());
            table.addCell(f.getAddress());
        }

        document.add(table);
        document.close();
    }

    @GetMapping("/view/customer/excel")
    public void exportarExcel(HttpServletResponse response) throws Exception
    {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=Customer.xlsx");

        List<Customer> customerList = customerRepository.findAll();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Customer");

        // Crear encabezado
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Documento");
        headerRow.createCell(2).setCellValue("Nombre");
        headerRow.createCell(3).setCellValue("Teléfono");
        headerRow.createCell(4).setCellValue("Correo eletrónico");
        headerRow.createCell(5).setCellValue("Dirección");

        // Agregar datos
        int rowNum = 1;
        for (Customer customer : customerList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(customer.getId_customer());
            row.createCell(1).setCellValue(customer.getDocument());
            row.createCell(2).setCellValue(customer.getName());
            row.createCell(3).setCellValue(customer.getPhone());
            row.createCell(4).setCellValue(customer.getEmail());
            row.createCell(5).setCellValue(customer.getAddress());
        }

        // Autoajustar columnas
        for (int i = 0; i < 6; i++) {
            sheet.autoSizeColumn(i);
        }

        workbook.write(response.getOutputStream());
        workbook.close();
    }
}
