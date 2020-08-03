package org.drools;

import org.drools.config.KieStatefulConfiguration;
import org.drools.model.Customer;
import org.drools.model.Order;
import org.kie.api.runtime.KieSession;

public class StatefulApp {

    public static void main(String[] args) {
        KieStatefulConfiguration kieConfig = new KieStatefulConfiguration();
        KieSession kieSession = kieConfig.getKieSession();

        discountForBronzeCustomers(kieSession);
    }

    private static void discountForBronzeCustomers(KieSession kieSession) {
        Customer customer = new Customer();
        customer.setCategory(Customer.Category.BRONZE);
        customer.setName("Shiva");

        Order order = new Order();
        order.setOrderId(234L);
        order.setCustomer(customer);

        kieSession.insert(customer);
        kieSession.insert(order);

        int fired = kieSession.fireAllRules();

        System.out.println("Number of Rules executed = " + fired);
        System.out.println("Discount : " + order.getDiscount());
        System.out.println("customer : " + customer);

    }

}
