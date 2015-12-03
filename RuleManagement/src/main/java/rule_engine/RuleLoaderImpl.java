package rule_engine;

import org.kie.api.KieServices;

import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import rule_dto.Rule;
import rule_dto.RuleCollection;
import rule_dto.RuleQuery;

import java.io.File;
import java.io.Reader;
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
        baseRuleAgents.kieResources=baseRuleAgents.kieServices.getResources();
        baseRuleAgents.fileSystem=baseRuleAgents.kieServices.newKieFileSystem();
        baseRuleAgents.kieRepository=baseRuleAgents.kieServices.getRepository();


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
        String myRule = getRuleAsString(query);
        Resource resource = baseRuleAgents.kieResources.newReaderResource((Reader) new StringReader(myRule));
        resource.setResourceType(ResourceType.DRL);
        retRuleDto.setResource(resource);





    return retRuleDto;
    }
    private String getRuleAsString(RuleQuery query) {
        return "package isirandrools.rayten.rayten_pkg2;\n" +
                "\n" +
                "rule \"r_rule2\"\n" +
                "dialect \"java\"\n" +
                "when \n" +
                "$student: entities.Student(grade>1 )\n" +
                "then\n" +
                "$student.alert();\n" +
                "end\n";
    }
    private File getRuleFile(RuleQuery query) {
        return null;
    }

    @Override
    public RuleCollection getRules(RuleQuery query) {
        return null;
    }
}
