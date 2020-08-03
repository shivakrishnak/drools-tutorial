package org.drools.config;

import org.kie.api.KieServices;
import org.kie.api.builder.Results;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;

public class KieStatelessConfiguration {

    public KieServices getKieServices() {
        return KieServices.Factory.get();
    }

    public StatelessKieSession getKieSession() {

        System.out.println("### Running statelessSessionTest() Test ###");

        KieContainer kContainer = this.getKieServices().newKieClasspathContainer();

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
