package rule_engine;

import org.kie.api.builder.ReleaseId;
import rule_dto.CompiledRule;
import rule_dto.Rule;

/**
 * Created by saeed on 02/12/2015.
 */
public interface RuleCompiler {

    /**
     * this method try to compile given rule
     * @param result , the compile result set to the MethodResult
     * @param rule , the rule which want to be compiled
     * @param releaseId
     * @return CompiledRule object , the compiled rule will be return if God wants!
     */
    CompiledRule compileRule(MethodResult result, Rule rule, ReleaseId releaseId);

}
