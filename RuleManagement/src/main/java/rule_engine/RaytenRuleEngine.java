package rule_engine;

import domain.RmCompiledRule;
import domain.RmRawRule;
import org.kie.api.builder.ReleaseId;
import org.springframework.context.ApplicationContext;
import repository.CompiledRuleRepository;
import rule_dto.*;
import rule_dto.CompiledRule;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.UUID;

/**
 * Created by saeed on 02/12/2015.
 */
@Named
public class RaytenRuleEngine {



    @Inject
    CompiledRuleRepository compiledRuleRepository;


   @Inject
    private RuleLoader loader;
    @Inject
    private RuleCompiler compiler;
    @Inject
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

//        this.compiler.setBaseRuleAgent(loader.getBaseRuleAgents());
//        this.runner.setBaseRuleAgent(loader.getBaseRuleAgents());
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
        CompiledRule rule = getLoader().getSingleRule(query);
        MethodResult result=new MethodResult();

        CompiledRule compiledRule = getCompiler().compileRule(result, rule, null);
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


    public MethodResult compileRawRule(RmRawRule rawRule) {

        MethodResult result=new MethodResult();
        CompiledRule rule = getLoader().getRuleFromRawRule(rawRule);
        try {
            CompiledRule compiledRule = getCompiler().compileRule(result, rule, null);
        }catch (Exception ex)
        {
            result.setDone(false);
        }
        return  result;
    }
    public MethodResult publishRule(RmRawRule rawRule) {

        MethodResult result=new MethodResult();
        CompiledRule rule = getLoader().getRuleFromRawRule(rawRule);
        try {
            ReleaseId releaseId=RuleUtility.makeReleaseId(rawRule.getGroupId(),rawRule.getRuleName(),rawRule.getVersionNumber());
            CompiledRule compiledRule = getCompiler().compileRule(result, rule,releaseId);
            if(result.isDone()) {
                RmCompiledRule rmCompiledRule = new RmCompiledRule();
                rmCompiledRule.setRuleName(rawRule.getRuleName());
                rmCompiledRule.setId(UUID.randomUUID());
                rmCompiledRule.setKjarFile(compiledRule.getKjarFile());
                rmCompiledRule.setArtifactId(compiledRule.getReleaseId().getArtifactId());
                rmCompiledRule.setGroupId(compiledRule.getReleaseId().getGroupId());
                rmCompiledRule.setVersionId(compiledRule.getReleaseId().getVersion());
                rmCompiledRule.setRuleRef(rawRule.getId());
                compiledRuleRepository.publishNewVersion(rmCompiledRule);
             //   compiledRuleRepository.saveOrUpdate(rmCompiledRule);
            }

        }catch (Exception ex)
        {
            ex.printStackTrace();
            result.setDone(false);
        }
        return  result;
    }
    public RuleResult RunRuleByGroupId(String groupId,RuleFact fact)
    {
        RuleQuery query=new RuleQuery();
        query.setGroupId(groupId);
        CompiledRule rule = loader.getSingleCompiledRule(query);
        RuleResult result = runRule(rule, fact);
        return result;

    }
    public RuleResult runRule(CompiledRule compiledRuleEntity,RuleFact fact)
    {
        RuleResult ruleResult = runner.runRule(compiledRuleEntity, fact);
        return  ruleResult;
    }



}
