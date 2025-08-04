package co.edu.sena.hardware_store.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "order_detail")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id_detail;
    private Long id_order;
    private Long id_product;
    private Integer quantity;
    @Column(precision = 10, scale = 2)
    private BigDecimal unit_price;
    @Column(precision = 10, scale = 2)
    private BigDecimal subtotal;

    public Long getId_detail() {
        return id_detail;
    }

    public void setId_detail(Long id_detail) {
        this.id_detail = id_detail;
    }

    public Long getId_order() {
        return id_order;
    }

    public void setId_order(Long id_order) {
        this.id_order = id_order;
    }

    public Long getId_product() {
        return id_product;
    }

    public void setId_product(Long id_product) {
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

