package rule_engine;

import rule_dto.CompiledRule;
import rule_dto.Rule;

/**
 * Created by saeed on 02/12/2015.
 */
public interface RuleCompiler {

    /**
     * this method try to compile given rule
     * @param rule
     * @return CompiledRule object
     */
    CompiledRule compileRule(MethodResult result, Rule rule);

}
