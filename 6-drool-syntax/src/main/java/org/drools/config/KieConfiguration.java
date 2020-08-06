package org.drools.config;

import org.drools.core.event.DefaultAgendaEventListener;
import org.kie.api.KieServices;
import org.kie.api.event.rule.AfterMatchFiredEvent;
import org.kie.api.runtime.ClassObjectFilter;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.Collection;

public class KieConfiguration {

    public KieSession getKieSession() {
        System.out.println("Bootstrapping the Rule Engine ...");
        // Bootstrapping a Rule Engine Session
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession = kContainer.newKieSession();
        addEventListener(kSession);
        return kSession;
    }

    private void addEventListener(KieSession kSession) {
        kSession.addEventListener(new DefaultAgendaEventListener() {
            public void afterMatchFired(AfterMatchFiredEvent event) {
                super.afterMatchFired(event);
                System.out.println("Events Triggered : " + event.getMatch().getRule());
            }
        });
    }

    public <T> Collection<T> getFactsFromKieSession(KieSession ksession, Class<T> classType) {
        return (Collection<T>) ksession.getObjects(new ClassObjectFilter(classType));
    }
}
