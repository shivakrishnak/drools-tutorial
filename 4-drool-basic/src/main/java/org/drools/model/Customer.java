package org.drools.model;

public class Customer {

    public enum Category {
        NA, GOLD, SILVER, BRONZE
    };
    private static final long serialVersionUID = 1L;

    private Long customerId;
    private Integer age;
    private String name;
    private String email;
    private Category category = Category.NA;

    public Customer() {
    }

    public Customer(String name) {
        this.name = name;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Customer [id = " + customerId + ", age=" + age + ", email=" + email + ", name=" + name + ", category = " + category + "]";
    }
}
