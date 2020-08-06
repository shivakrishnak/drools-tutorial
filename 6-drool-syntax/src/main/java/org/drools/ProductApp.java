package org.drools;

import org.drools.config.KieConfiguration;
import org.drools.model.Product;
import org.kie.api.runtime.KieSession;

public class ProductApp {
    public static void main(String[] args) {
        KieConfiguration kieConfiguration = new KieConfiguration();
        KieSession kieSession = kieConfiguration.getKieSession();

        setProductCategory(kieSession);

    }

    private static void setProductCategory(KieSession kieSession) {
        Product product = new Product("A", 423.0);
        System.out.println("Item Category: " + product.getCategory());
        kieSession.insert(product);
        int fired = kieSession.fireAllRules();
        System.out.println("Number of Rules executed = " + fired);
        System.out.println("Product Category: " + product.getCategory());
        System.out.println("Product : " + product);
    }

}
