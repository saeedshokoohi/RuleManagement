package rule_engine;

import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieRepository;
import org.kie.api.io.KieResources;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.definition.KnowledgePackage;
import org.kie.internal.runtime.StatefulKnowledgeSession;

import java.util.Collection;

/**
 * Created by saeed on 03/12/2015.
 */
public class BaseRuleAgents {
    public KieServices kieServices;
    public KieFileSystem fileSystem;
    public KieResources kieResources;
    public KieRepository kieRepository;

    public KieRepository getKieRepository() {
        if(kieRepository==null)kieRepository=kieServices.getRepository();
        return kieRepository;
    }

    public BaseRuleAgents() {

    }
}
