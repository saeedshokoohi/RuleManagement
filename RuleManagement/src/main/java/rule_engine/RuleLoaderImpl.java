package rule_engine;

import domain.RmRawRule;
import org.kie.api.KieServices;

import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import rule_dto.Rule;
import rule_dto.RuleCollection;
import rule_dto.RuleQuery;

import java.io.File;
import java.io.StringReader;

/**
 * Created by saeed on 02/12/2015.
 */
public class RuleLoaderImpl implements RuleLoader  {

   BaseRuleAgents baseRuleAgents;


    public RuleLoaderImpl() {
        //step 1 : creating the kie service to use for creating Kie objects
        baseRuleAgents=new BaseRuleAgents();
        baseRuleAgents.kieServices=KieServices.Factory.get();
   //     baseRuleAgents.kieResources=baseRuleAgents.kieServices.getResources();
  //      baseRuleAgents.fileSystem=baseRuleAgents.kieServices.newKieFileSystem();
  //      baseRuleAgents.kieRepository=baseRuleAgents.kieServices.getRepository();


    }

    public BaseRuleAgents getBaseRuleAgents() {
        return baseRuleAgents;
    }

    public void setBaseRuleAgents(BaseRuleAgents baseRuleAgents) {
        this.baseRuleAgents = baseRuleAgents;
    }

    @Override
    public Rule getSingleRule(RuleQuery query) {
Rule retRuleDto=new Rule();
        //step 2 : creating resource
        //resource can be made from string , file , url ,reader ,....
        //that's great!

        //if single rule is type of DRL
        retRuleDto.setRuleName("tempSingleRule.drl");
        byte[] myRule = getRuleAsString(query);
        Resource resource = baseRuleAgents.kieServices.getResources().newByteArrayResource(myRule,"UTF-8");
        resource.setResourceType(ResourceType.DRL);
        retRuleDto.setResource(resource);
        retRuleDto.setRuleContent(myRule);
    return retRuleDto;
    }
    public Rule getRuleFromRawRule(RmRawRule rawRule)
    {
        Rule retRuleDto=new Rule();
        retRuleDto.setRuleName(rawRule.getRuleName());
        Resource resource = baseRuleAgents.kieServices.getResources().newByteArrayResource(rawRule.getRuleContent(),"UTF-8");
        resource.setResourceType(ResourceType.getResourceType(rawRule.getResourceType()));
        retRuleDto.setResource(resource);
        retRuleDto.setRuleContent(rawRule.getRuleContent());
        return retRuleDto;

    }
    private byte[] getRuleAsString(RuleQuery query) {
        String retContent= "import entities.Student;\n" +
                "dialect  \"java\"\n" +
                "\n" +
                "rule \"ruleGoodStudent\"\n" +
                "    when\n" +
                "    $s:Student(grade>0)\n" +
                "    then\n" +
                "    System.out.println($s.getName());\n" +
                "    $s.alert();\n" +
                "end";
        return retContent.getBytes();
    }
    private File getRuleFile(RuleQuery query) {
        return null;
    }

    @Override
    public RuleCollection getRules(RuleQuery query) {
        return null;
    }
}
