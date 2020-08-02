package org.drools;

import org.kie.api.KieServices;
import org.kie.api.builder.Results;
import org.kie.api.command.Command;
import org.kie.api.runtime.ExecutionResults;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;

import java.util.ArrayList;
import java.util.List;

public class StatelessApp {

    public static void main(String[] args) {
        KieServices ks = KieServices.Factory.get();
        StatelessKieSession statelessKieSession = getKieSession(ks);

        discoutforSilverCustomer(ks, statelessKieSession);

    }

    private static void discoutforSilverCustomer(KieServices ks, StatelessKieSession statelessKieSession) {
        Customer customer = new Customer();
        customer.setCategory(Customer.Category.SILVER);

        Order order = new Order();
        order.setCustomer(customer);

        System.out.println("Item Category: " + order.getCustomer().getCategory());

        Command newInsertOrder = ks.getCommands().newInsert(order, "orderOut");
        Command newInsertCustomer = ks.getCommands().newInsert(customer);
        Command newFireAllRules = ks.getCommands().newFireAllRules("outFired");
        List<Command> cmds = new ArrayList<Command>();
        cmds.add(newInsertOrder);
        cmds.add(newInsertCustomer);
        cmds.add(newFireAllRules);
        ExecutionResults execResults = statelessKieSession.execute(ks.getCommands().newBatchExecution(cmds));

        order = (Order) execResults.getValue("orderOut");
        int fired = (Integer) execResults.getValue("outFired");

        System.out.println("Number of Rules executed = " + fired);
        System.out.println("Discount : " + order.getDiscount());
    }

    private static StatelessKieSession getKieSession(KieServices ks) {
        System.out.println("### Running statelessSessionTest() Test ###");

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
                System.out.println("\t >> Containing KieSession: " + kieSession);
            });
        });

        StatelessKieSession statelessKieSession = kContainer.newStatelessKieSession("rules.customer.sl.discount");

        return statelessKieSession;
    }

}
