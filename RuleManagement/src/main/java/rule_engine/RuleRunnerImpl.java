package rule_engine;

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
        //step 5 : creating Container
        KieContainer kieContainer=baseRuleAgents.kieServices.newKieContainer(baseRuleAgents.kieRepository.getDefaultReleaseId());

        //step 6 : creating session
        KieSession kiessession = kieContainer.newKieSession();
        kiessession.addEventListener( new DebugRuleRuntimeEventListener() );
        if(fact.getFactObjects()!=null)
            for(FactObject fo:fact.getFactObjects())
            {
                kiessession.insert(fo.getBObject());
            }
        int resultCode= kiessession.fireAllRules();
        ruleResult.setResultCode(resultCode);
        return ruleResult;
    }
}
