package rule_engine;

import rule_dto.CompiledRule;
import rule_dto.RuleFact;
import rule_dto.RuleResult;

/**
 * Created by saeed on 02/12/2015.
 */
public interface RuleRunner {

    /**
     * run the given compiled rule
     * @param compiledRule
     * @return
     */
    RuleResult runRule(CompiledRule compiledRule, RuleFact ruleFact);


    void setBaseRuleAgent(BaseRuleAgents baseRuleAgents);
}
