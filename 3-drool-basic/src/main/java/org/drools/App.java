package org.drools;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.Arrays;

public class App {

    public static void main(String[] args) {
        KieSession kSession = getKieSession();

        customerWithThreeProducts(kSession);

        customerWithFiveProducts(kSession);

    }

    private static void customerWithThreeProducts(KieSession kSession) {
        Order order = getOrderWithThreeProducts();
        System.out.println("Item Category: " + order.getCustomer().getCategory());

        kSession.insert(order.getCustomer());
        kSession.insert(order.getProducts().get(0));
        kSession.insert(order.getProducts().get(1));
        kSession.insert(order.getProducts().get(2));
        kSession.insert(order);

        int fired = kSession.fireAllRules();
        System.out.println("Number of Rules executed = " + fired);
        System.out.println("Item Category: " + order.getCustomer().getCategory());
    }

    private static void customerWithFiveProducts(KieSession kSession) {
        Order order = getOrderWithFiveProducts();
        System.out.println("Item Category: " + order.getCustomer().getCategory());
        System.out.println("Item Category: " + order.getDiscount());

        kSession.insert(order.getCustomer());
        kSession.insert(order.getProducts().get(0));
        kSession.insert(order.getProducts().get(1));
        kSession.insert(order.getProducts().get(2));
        kSession.insert(order.getProducts().get(3));
        kSession.insert(order.getProducts().get(4));
        kSession.insert(order);

        int fired = kSession.fireAllRules();
        System.out.println("Number of Rules executed = " + fired);
        System.out.println("Item Category: " + order.getCustomer().getCategory());
        System.out.println("Item Category: " + order.getDiscount());
    }

    private static KieSession getKieSession() {
        System.out.println("Bootstrapping the Rule Engine ...");
        // Bootstrapping a Rule Engine Session
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession = kContainer.newKieSession();
        return kSession;
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

    private static Order getOrderWithThreeProducts() {
        return new Order(new Customer("Shiva"), Arrays.asList(
                new Product("A", 500.00),
                new Product("B", 1500.00),
                new Product("C", 500.00)
        ));
    }
}
