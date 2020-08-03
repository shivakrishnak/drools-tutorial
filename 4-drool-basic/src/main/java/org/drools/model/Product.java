package org.drools.model;

import java.io.Serializable;

public class Product implements Serializable {

    public enum Category {
        NA, LOW_RANGE, MID_RANGE, HIGH_RANGE,
        SPECIAL_MIDHIGH_RANGE //used in chapter 4
    };
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private int quantity;
    private Double cost;
    private Double salePrice;
    private Category category;

    public Product() {
    }

    public Product(String name, Double cost) {
        this(null, name, cost, 1, null);
    }

    public Product(String name, Double cost, Double salePrice) {
        this(null, name, cost,1, salePrice);
    }

    public Product(Long id, String name, Double cost, int quantity, Double salePrice) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.quantity = quantity;
        this.salePrice = salePrice;
        this.category = Category.NA;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Item{" + "id=" + id + ", name=" + name + ", cost=" + cost
                + ", salePrice=" + salePrice + ", category=" + category + '}';
    }

}
