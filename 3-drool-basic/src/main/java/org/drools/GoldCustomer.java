package org.drools;

import org.drools.config.KieConfiguration;
import org.drools.model.Coupon;
import org.drools.model.Customer;
import org.drools.model.Order;
import org.drools.model.Product;
import org.kie.api.runtime.KieSession;

import java.util.Arrays;
import java.util.Collection;

public class GoldCustomer {

    public static void main(String[] args) {

        customerWithFiveProducts();

    }

    private static void customerWithFiveProducts() {
        KieConfiguration kieConfiguration = new KieConfiguration();
        KieSession kieSession = kieConfiguration.getKieSession();

        Order order = getOrderWithFiveProducts();

        System.out.println("Item Category: " + order.getCustomer().getCategory());
        System.out.println("Item Category: " + order.getDiscount());

        kieSession.insert(order.getCustomer());
        kieSession.insert(order.getProducts().get(0));
        kieSession.insert(order.getProducts().get(1));
        kieSession.insert(order.getProducts().get(2));
        kieSession.insert(order.getProducts().get(3));
        kieSession.insert(order.getProducts().get(4));
        kieSession.insert(order);

        int fired = kieSession.fireAllRules();

        Collection<Coupon> coupons = kieConfiguration.getFactsFromKieSession(kieSession, Coupon.class);

        System.out.println("Number of Rules executed = " + fired);
        System.out.println("Item Category: " + order.getCustomer().getCategory());
        System.out.println("Order Discount: " + order.getDiscount());
        System.out.println("Order Discount: " + coupons.size());

    }

    private static Order getOrderWithFiveProducts() {
        return new Order(new Customer("Shiva"), Arrays.asList(
                new Product("A", 500.00),
                new Product("B", 1500.00),
                new Product("C", 500.00),
                new Product("D", 100.00),
                new Product("E", 600.00)
        ));
    }
}
