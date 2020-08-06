package org.drools;

import org.drools.config.KieStatelessConfiguration;
import org.drools.model.Customer;
import org.drools.model.Order;
import org.kie.api.KieServices;
import org.kie.api.command.Command;
import org.kie.api.runtime.ExecutionResults;
import org.kie.api.runtime.StatelessKieSession;

import java.util.ArrayList;
import java.util.List;

public class StatelessApp {

    public static void main(String[] args) {
        KieStatelessConfiguration kieConfig = new KieStatelessConfiguration();

        KieServices ks = kieConfig.getKieServices();
        StatelessKieSession statelessKieSession = kieConfig.getKieSession();

        discoutforSilverCustomer(ks, statelessKieSession);

    }

    private static void discoutforSilverCustomer(KieServices ks, StatelessKieSession statelessKieSession) {
        Customer customer = new Customer();
        customer.setCategory(Customer.Category.SILVER);
        customer.setName("Shiva");

        Order order = new Order();
        order.setOrderId(26L);
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
        System.out.println("customer : " + customer);
    }

    //Stateless = statefull + dispose();

}
