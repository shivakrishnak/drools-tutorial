package org.drools;

import org.drools.config.KieConfiguration;
import org.drools.model.Customer;
import org.drools.model.Order;
import org.drools.model.Product;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.Arrays;

public class SilverCustomer {

    public static void main(String[] args) {

        customerWithThreeProducts();

    }

    private static void customerWithThreeProducts() {
        KieConfiguration kieConfiguration = new KieConfiguration();
        KieSession kieSession = kieConfiguration.getKieSession();

        Order order = getOrderWithThreeProducts();
        System.out.println("Item Category: " + order.getCustomer().getCategory());

        kieSession.insert(order.getCustomer());
        kieSession.insert(order.getProducts().get(0));
        kieSession.insert(order.getProducts().get(1));
        kieSession.insert(order.getProducts().get(2));
        kieSession.insert(order);

        int fired = kieSession.fireAllRules();
        System.out.println("Number of Rules executed = " + fired);
        System.out.println("Item Category: " + order.getCustomer().getCategory());
    }

    private static KieSession getKieSession() {
        System.out.println("Bootstrapping the Rule Engine ...");
        // Bootstrapping a Rule Engine Session
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession = kContainer.newKieSession();
        return kSession;
    }


    private static Order getOrderWithThreeProducts() {
        return new Order(new Customer("Shiva"), Arrays.asList(
                new Product("A", 500.00),
                new Product("B", 1500.00),
                new Product("C", 500.00)
        ));
    }
}
