package rule_engine;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import rule_dto.CompiledRule;
import rule_dto.Rule;

/**
 * Created by Saeed on 02/12/2015.
 */
public class RuleCompilerImpl  implements RuleCompiler {

    BaseRuleAgents baseRuleAgents;

    public RuleCompilerImpl(BaseRuleAgents baseRuleAgents) {
        this.baseRuleAgents = baseRuleAgents;
    }

    @Override
    public CompiledRule compileRule(MethodResult result, Rule rule){

        CompiledRule compiledRule=new CompiledRule();

        //step 3 : write the resources to fileSystem
        KieFileSystem fileSystem = baseRuleAgents.kieServices.newKieFileSystem();
        fileSystem= fileSystem.write("/src/main/java/temp.drl",rule.getResource());

        //step 4 : build the rule are temporary stored in filesystem
        KieBuilder kieBuilder=baseRuleAgents.kieServices.newKieBuilder(fileSystem);
        KieBuilder r = kieBuilder.buildAll();
        if(!r.getResults().hasMessages()) {
            result.setDone(true);
            compiledRule.setBuilder(kieBuilder);
        }
        else
        {
            result.setDone(false);
            compiledRule.setCompileResult(r.getResults());
        }
        return  compiledRule;
    }
}
