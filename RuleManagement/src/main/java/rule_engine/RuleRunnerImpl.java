package rule_engine;

import org.drools.compiler.kie.builder.impl.FileKieModule;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.*;
import org.kie.api.event.rule.DebugRuleRuntimeEventListener;
import org.kie.api.io.Resource;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import rule_dto.CompiledRule;
import rule_dto.FactObject;
import rule_dto.RuleFact;
import rule_dto.RuleResult;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by saeed on 02/12/2015.
 */
@Named
public class RuleRunnerImpl implements RuleRunner {
    @Inject
    BaseRuleAgents baseRuleAgents;


    public RuleRunnerImpl() {
    }

    public RuleRunnerImpl(BaseRuleAgents baseRuleAgents) {
        this.baseRuleAgents = baseRuleAgents;
    }

    @Override
    public RuleResult runRule(CompiledRule compiledRule, RuleFact fact) {

        RuleResult ruleResult=new RuleResult();
 //       checkruleTemp(compiledRule,fact);


        //step 5 : creating Container
        KieFileSystem fs = baseRuleAgents.kieServices.newKieFileSystem();
        KieBuilder ks= baseRuleAgents.kieServices.newKieBuilder(fs);
        Resource jarKie = baseRuleAgents.kieServices.getResources().newByteArrayResource(compiledRule.getKjarFile());
        baseRuleAgents.getKieRepository().addKieModule(jarKie);
        ReleaseId releaseId=compiledRule.getReleaseId();
        KieContainer kieContainer=baseRuleAgents.kieServices.newKieContainer(releaseId);
        //step 6 : creating session
        //KieSession kiessession = kieContainer.newKieSession();
        KieBase kiebase = kieContainer.getKieBase();
        KieSession kiessession =kiebase.newKieSession();
        //addingg Event listeneners
        kiessession.addEventListener( new DebugRuleRuntimeEventListener() );
        //adding Facts (Business Object)
        if(fact.getFactObjects()!=null)
            for(FactObject fo:fact.getFactObjects())
            {
                kiessession.insert(fo.getBObject());
            }
        //firing the rules
        int resultCode= kiessession.fireAllRules();
        ruleResult.setResultCode(resultCode);
        return ruleResult;
    }

    @Override
    public void setBaseRuleAgent(BaseRuleAgents baseRuleAgents) {
        this.baseRuleAgents=baseRuleAgents;
    }


    public  RuleResult fireRules(List<CompiledRule> compiledRules, RuleFact fact)
    {
        addRulesToRepository(compiledRules);
        return null;
    }

    private void addRulesToRepository(List<CompiledRule> compiledRules) {
        KieContainer container = baseRuleAgents.kieServices.getKieClasspathContainer();

    }

    public void checkruleTemp(CompiledRule compiledRule, RuleFact fact)
    {
        KieServices kieServices = KieServices.Factory.get();
        KieFileSystem kfs = kieServices.newKieFileSystem();
        kfs.write( "src/main/resources/simple.drl",  "import entities.Student;\n" +
                "dialect  \"java\"\n" +
                "\n" +
                "rule \"ruleGoodStudent\"\n" +
                "    when\n" +
                "    $s:Student(grade1>0)\n" +
                "    then\n" +
                "    System.out.println($s.getName());\n" +
                "    $s.alert();\n" +
                "end" );
        KieBuilder kieBuilder = kieServices.newKieBuilder( kfs ).buildAll();
        Results results = kieBuilder.getResults();
        if( results.hasMessages( Message.Level.ERROR ) ){
            System.out.println( results.getMessages() );
            throw new IllegalStateException( "### errors ###" );
        }
        KieContainer kieContainer =
                kieServices.newKieContainer( kieServices.getRepository().getDefaultReleaseId() );
        KieBase kieBase = kieContainer.getKieBase();
        KieSession kiessession = kieContainer.newKieSession();
        if(fact.getFactObjects()!=null)
            for(FactObject fo:fact.getFactObjects())
            {
                kiessession.insert(fo.getBObject());
            }
        //firing the rules
        int resultCode= kiessession.fireAllRules();
    }
}
