package co.edu.sena.hardware_store.controller;

import co.edu.sena.hardware_store.model.Product;
import co.edu.sena.hardware_store.repository.ProductRepository;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.DecimalFormat;
import java.util.List;

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
        try {
            productRepository.save(product);
            ra.addFlashAttribute("success", "¡Producto guardado correctamente!");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "¡Ocurrio un error al guardar el producto" + e.getMessage());
        }
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
        try {
            productRepository.deleteById(id);
            ra.addFlashAttribute("success", "¡Producto eliminado correctamente!");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "¡Ocurrio un error al eliminar el producto!");
        }
        return "redirect:/view/product";
    }

    @GetMapping("/view/pdf")
    public void exportarPDF(HttpServletResponse response) throws Exception
    {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=Product.pdf");

        List<Product> productList = productRepository.findAll(); // Asegúrate de tener fichaRepository
        DecimalFormat df = new DecimalFormat("#,###");

        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        document.add(new Paragraph("Inventario de productos"));
        PdfPTable table = new PdfPTable(7); // ajusta las columnas necesarias
        table.setWidthPercentage(100);
        table.setSpacingBefore(20);
        float[] columnWidths = {5f, 32f, 30f, 16f, 10f, 10f, 17f};
        table.setWidths(columnWidths);

        // Encabezados
        table.addCell("ID");
        table.addCell("Nombre");
        table.addCell("Categoría");
        table.addCell("Precio");
        table.addCell("Stock");
        table.addCell("Stock Mínimo");
        table.addCell("Proveedor");

        // Filas
        for (Product f : productList) {
            table.addCell(f.getId_product().toString());
            table.addCell(f.getName());
            table.addCell(f.getCategory());
            table.addCell("$" + f.getPrice().toString());
            table.addCell(f.getStock_quantity().toString());
            table.addCell(f.getMin_stock_level().toString());
            table.addCell(f.getId_supplier() != null ? f.getId_supplier().getName() : "Sin proveedor");
        }

        document.add(table);
        document.close();
    }

    @GetMapping("/view/excel")
    public void exportarExcel(HttpServletResponse response) throws Exception
    {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=Product.xlsx");

        List<Product> productList = productRepository.findAll(); // Reemplaza con tu repositorio

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Product");

        // Crear encabezado
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Nombre");
        headerRow.createCell(2).setCellValue("Categoría");
        headerRow.createCell(3).setCellValue("Precio");
        headerRow.createCell(4).setCellValue("Stock");
        headerRow.createCell(5).setCellValue("Stock Mínimo");
        headerRow.createCell(6).setCellValue("Proveedor");

        // Agregar datos
        int rowNum = 1;
        for (Product product : productList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(product.getId_product());
            row.createCell(1).setCellValue(product.getName());
            row.createCell(2).setCellValue(product.getCategory());
            row.createCell(3).setCellValue("$" + product.getPrice().toString());
            row.createCell(4).setCellValue(product.getStock_quantity());
            row.createCell(5).setCellValue(product.getMin_stock_level());
            row.createCell(6).setCellValue(product.getId_supplier() != null ? product.getId_supplier().getName() : "Sin proveedor");
        }

        // Autoajustar columnas
        for (int i = 0; i < 7; i++) {
            sheet.autoSizeColumn(i);
        }

        workbook.write(response.getOutputStream());
        workbook.close();
    }
}
