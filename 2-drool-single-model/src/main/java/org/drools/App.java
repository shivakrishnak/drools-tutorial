package org.drools;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class App 
{
    public static void main( String[] args )
    {
        KieSession kSession = getKieSession();

        Product product = new Product("A", 523.0);
        System.out.println( "Item Category: " + product.getCategory());
        kSession.insert(product);
        int fired = kSession.fireAllRules();
        System.out.println( "Number of Rules executed = " + fired );
        System.out.println( "Item Category: " + product.getCategory());

    }

    private static KieSession getKieSession() {
        System.out.println( "Bootstrapping the Rule Engine ..." );
        // Bootstrapping a Rule Engine Session
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession =  kContainer.newKieSession();
        return kSession;
    }
}
