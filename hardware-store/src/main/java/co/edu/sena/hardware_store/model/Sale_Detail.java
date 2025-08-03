package co.edu.sena.hardware_store.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "sale_detail")
public class Sale_Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_detail;
    @ManyToOne
    @JoinColumn(name = "id_sale")
    private Sale id_sale;
    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product id_product;
    private Integer quantity;
    private BigDecimal unit_price;
    private BigDecimal subtotal;

    public Long getId_detail() {
        return id_detail;
    }

    public void setId_detail(Long id_detail) {
        this.id_detail = id_detail;
    }

    public Sale getId_sale() {
        return id_sale;
    }

    public void setId_sale(Sale id_sale) {
        this.id_sale = id_sale;
    }

    public Product getId_product() {
        return id_product;
    }

    public void setId_product(Product id_product) {
        this.id_product = id_product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(BigDecimal unit_price) {
        this.unit_price = unit_price;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
}
