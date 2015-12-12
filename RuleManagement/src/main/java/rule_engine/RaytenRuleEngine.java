package rule_engine;

import org.kie.api.builder.Message;
import rule_dto.*;
import rule_dto.CompiledRule;
import rule_dto.Rule;

/**
 * Created by saeed on 02/12/2015.
 */
public class RaytenRuleEngine {


    private RuleLoader loader;
    private RuleCompiler compiler;
    private RuleRunner runner;


    public void setCompiler(RuleCompiler compiler) {
        this.compiler = compiler;
    }

    public RuleLoader getLoader() {//**********
        return loader;
    }

    public void setLoader(RuleLoader loader) {
        this.loader = loader;
    }

    public RuleRunner getRunner() {//**********
        return runner;
    }

    public void setRunner(RuleRunner runner) {//**********
        this.runner = runner;
    }

    public RaytenRuleEngine() {
        this.loader = new RuleLoaderImpl();
        this.compiler =new RuleCompilerImpl(loader.getBaseRuleAgents());
        this.runner = new RuleRunnerImpl(loader.getBaseRuleAgents());
    }

    public RuleCompiler getCompiler() {
        return compiler;
    }

    public RaytenRuleEngine(RuleCompiler compiler, RuleLoader loader, RuleRunner runner) {
        this.compiler = compiler;
        this.loader = loader;
        this.runner = runner;
    }

    /**
     * run the rules base on given query
     * @param query
     * @return RuleResult as the result of running the rule
     */
    public RuleResult runRule(RuleQuery query,RuleFact ruleFact)
    {
        RuleResult runResult=new RuleResult();
        Rule rule = getLoader().getSingleRule(query);
        MethodResult result=new MethodResult();

        CompiledRule compiledRule = getCompiler().compileRule(result, rule);
        runResult.setCompileResult(result);
        if(result.isDone() && compiledRule!=null)
        {
             runResult = getRunner().runRule(compiledRule, ruleFact);
        }
        else
        {
            System.out.println(result.getDisplayMessages());
        }
        return runResult;
    }


}
