package com.katas.supermarket.app.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.math.BigDecimal;

@Entity
public class Item {

    @Id
    private String sku;
    private BigDecimal price;
    private String description;

    public Item() {
    }

    public Item(String sku, BigDecimal price, String description) {
        this.sku = sku;
        this.price = price;
        this.description = description;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
