package org.drools;

import org.kie.api.KieServices;
import org.kie.api.builder.Results;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class StatefulApp {

    public static void main(String[] args) {
        KieSession kSession = getKieSession();

        discountForBronzeCustomers(kSession);

    }

    private static void discountForBronzeCustomers(KieSession kieSession) {
        Customer customer = new Customer();
        customer.setCategory(Customer.Category.BRONZE);

        Order order = new Order();
        order.setCustomer(customer);

        kieSession.insert(customer);
        kieSession.insert(order);

        int fired = kieSession.fireAllRules();

        System.out.println("Number of Rules executed = " + fired);
        System.out.println("Discount : " + order.getDiscount());
    }

    private static KieSession getKieSession() {
        System.out.println("### Running loadingRulesFromClassPath() Test ###");
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.newKieClasspathContainer();

        Results results = kContainer.verify();
        results.getMessages().stream().forEach((message) -> {
            System.out.println(">> Message ( " + message.getLevel() + " ): " + message.getText());
        });
        kContainer.getKieBaseNames().stream().map((kieBase) -> {
            System.out.println(">> Loading KieBase: " + kieBase);
            return kieBase;
        }).forEach((kieBase) -> {
            kContainer.getKieSessionNamesInKieBase(kieBase).stream().forEach((kieSession) -> {
                System.out.println("\t >> Containingx` KieSession: " + kieSession);
            });
        });

        KieSession kieSession = kContainer.newKieSession("rules.customer.discount");
        return kieSession;
    }

}
