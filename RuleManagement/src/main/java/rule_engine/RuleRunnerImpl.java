package rule_engine;

import org.drools.compiler.kie.builder.impl.FileKieModule;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.*;
import org.kie.api.event.rule.DebugRuleRuntimeEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import rule_dto.CompiledRule;
import rule_dto.FactObject;
import rule_dto.RuleFact;
import rule_dto.RuleResult;

/**
 * Created by saeed on 02/12/2015.
 */
public class RuleRunnerImpl implements RuleRunner {
    BaseRuleAgents baseRuleAgents;

    public RuleRunnerImpl(BaseRuleAgents baseRuleAgents) {
        this.baseRuleAgents = baseRuleAgents;
    }

    @Override
    public RuleResult runRule(CompiledRule compiledRule, RuleFact fact) {

        RuleResult ruleResult=new RuleResult();
 //       checkruleTemp(compiledRule,fact);


        //step 5 : creating Container
        KieContainer kieContainer=baseRuleAgents.kieServices.newKieContainer(baseRuleAgents.kieServices.getRepository().getDefaultReleaseId());


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
