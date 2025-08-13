package co.edu.sena.hardware_store.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product", nullable = false)
    private Long id_product;

    @Column(name = "name", nullable = false, unique = true, length = 100)
    private String name;

    @Column(name = "category", nullable = false, length = 50)
    private String category;

    @Column(name = "price", nullable = false)
    private Long price;

    @Column(name = "stock_quantity", nullable = false)
    private Integer stock_quantity;

    @Column(name = "min_stock_level", nullable = false)
    private Integer min_stock_level;

    @ManyToOne
    @JoinColumn(name = "id_supplier", referencedColumnName = "id_supplier")
    private Supplier id_supplier;

    public Long getId_product() {
        return id_product;
    }

    public void setId_product(Long id_product) {
        this.id_product = id_product;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getStock_quantity() {
        return stock_quantity;
    }

    public void setStock_quantity(Integer stock_quantity) {
        this.stock_quantity = stock_quantity;
    }

    public Integer getMin_stock_level() {
        return min_stock_level;
    }

    public void setMin_stock_level(Integer min_stock_level) {
        this.min_stock_level = min_stock_level;
    }

    public Supplier getId_supplier() {
        return id_supplier;
    }

    public void setId_supplier(Supplier id_supplier) {
        this.id_supplier = id_supplier;
    }
}
