package rule_engine;

import domain.RmRawRule;
import rule_dto.CompiledRule;
import rule_dto.RuleCollection;
import rule_dto.RuleQuery;

/**
 * Created by saeed on 02/12/2015.
 */
public interface RuleLoader {


    /**
     * get a query to find a rule and return Rule Object

      * @param query
     * @return  null if no rule matches the given query
     */
    public CompiledRule getSingleRule(RuleQuery query);

    /**
     * get a query to find  ruleS and return RuleCollection Object
     * @param query
     * @return EMPTY  collection if no rule matches the given query
     */
    public RuleCollection getRules(RuleQuery query);

    BaseRuleAgents getBaseRuleAgents();
    void setBaseRuleAgents(BaseRuleAgents baseRuleAgents);

    /**
     * get a raw rule object and make RuleDto Object
     * @param rawRule
     * @return
     */
    public CompiledRule getRuleFromRawRule(RmRawRule rawRule);

    CompiledRule getSingleCompiledRule(RuleQuery query);
}
