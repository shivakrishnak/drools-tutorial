package org.drools;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long orderId;
    private Date date;

    private Customer customer;
    private List<Product> products = new ArrayList<>();
    private int discount;

    public Order() {
    }

    public Order(Customer customer, List<Product> products) {
        this.customer = customer;
        this.products = products;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public double getTotal() {
        return this.getProducts().stream()
                .mapToDouble(item -> item.getCost() * item.getQuantity())
                .sum();
    }

    public int getTotalItems() {
        return this.getProducts().stream()
                .mapToInt(item -> item.getQuantity())
                .sum();
    }

    public void increaseDiscount(int increase) {
        this.discount = discount + increase;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", date=" + date +
                ", customer=" + customer +
                ", products=" + products +
                ", discount=" + discount +
                '}';
    }
}
