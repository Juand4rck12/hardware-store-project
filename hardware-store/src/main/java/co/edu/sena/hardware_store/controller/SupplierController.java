package co.edu.sena.hardware_store.controller;

import co.edu.sena.hardware_store.model.Customer;
import co.edu.sena.hardware_store.model.Supplier;
import co.edu.sena.hardware_store.repository.SupplierRepository;
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

@Controller
public class SupplierController {
    @Autowired
    SupplierRepository supplierRepository;

    @GetMapping("/view/supplier")
    public String list(Model model) {
        model.addAttribute("supplier", supplierRepository.findAll());
        return "supplier";
    }

    @GetMapping("/view/supplier/form")
    public String form(Model model) {
        model.addAttribute("supplier", new Supplier());
        return "supplier_form";
    }

    @PostMapping("/view/supplier/save")
    public String save(@ModelAttribute Supplier supplier, RedirectAttributes ra) {
        supplierRepository.save(supplier);
        ra.addFlashAttribute("success", "Proveedor guardado");
        return "redirect:/view/supplier";
    }

    @GetMapping("/view/supplier/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Supplier supplier = supplierRepository.findById(id).orElse(null);
        model.addAttribute("supplier", supplier);
        return "supplier_form";
    }

    @PostMapping("/view/supplier/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        supplierRepository.deleteById(id);
        ra.addFlashAttribute("success", "Proveedor eliminado");
        return "redirect:/view/employee";
    }

    @GetMapping("/view/supplier/pdf")
    public void exportarPDF(HttpServletResponse response) throws Exception
    {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=Supplier.pdf");

        List<Supplier> supplierList = supplierRepository.findAll();
        DecimalFormat df = new DecimalFormat("#,###");

        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        document.add(new Paragraph("Listado de proveedores"));
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        table.setSpacingBefore(20);
        float[] columnWidths = {5f, 15f, 18f, 17f, 26f};
        table.setWidths(columnWidths);

        // Encabezados
        table.addCell("ID");
        table.addCell("Documento");
        table.addCell("Nombre");
        table.addCell("Teléfono");
        table.addCell("Correo eletrónico");

        // Filas
        for (Supplier f : supplierList) {
            table.addCell(f.getId_supplier().toString());
            table.addCell(f.getDocument().toString());
            table.addCell(f.getName());
            table.addCell(f.getPhone());
            table.addCell(f.getEmail());
        }

        document.add(table);
        document.close();
    }

    @GetMapping("/view/supplier/excel")
    public void exportarExcel(HttpServletResponse response) throws Exception
    {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=Supplier.xlsx");

        List<Supplier> supplierList = supplierRepository.findAll();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Supplier");

        // Crear encabezado
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Documento");
        headerRow.createCell(2).setCellValue("Nombre");
        headerRow.createCell(3).setCellValue("Teléfono");
        headerRow.createCell(4).setCellValue("Correo eletrónico");

        // Agregar datos
        int rowNum = 1;
        for (Supplier supplier : supplierList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(supplier.getId_supplier());
            row.createCell(1).setCellValue(supplier.getDocument());
            row.createCell(2).setCellValue(supplier.getName());
            row.createCell(3).setCellValue(supplier.getPhone());
            row.createCell(4).setCellValue(supplier.getEmail());
        }

        // Autoajustar columnas
        for (int i = 0; i < 5; i++) {
            sheet.autoSizeColumn(i);
        }

        workbook.write(response.getOutputStream());
        workbook.close();
    }
}
