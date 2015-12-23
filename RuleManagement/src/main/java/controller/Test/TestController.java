package controller.Test;

import controller.RuleViewModel;
import domain.RmRawRule;
import domain.Test.Personnel;
import org.springframework.webflow.execution.RequestContext;
import org.springframework.webflow.execution.RequestContextHolder;
import repository.RawRuleRepository;
import repository.Test.TestRepository;
import rule_dto.RuleFact;
import rule_dto.RuleResult;
import rule_engine.MethodResult;
import rule_engine.RaytenRuleEngine;
import util.ClassFinder;
import util.MessageUtil;
import util.ResourcesUtil;
import util.ViewUtil;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by saeed on 15/12/2015.
 */
@Named
public class TestController {


    @Inject
    TestRepository testRepository;
    @Inject
    ResourcesUtil resourcesUtil;
    @Inject
    RaytenRuleEngine raytenRuleEngine;
    @Inject
    MessageUtil messageUtil;
    public void save(Personnel entity)
    {

        try {
            RuleFact facts=new RuleFact();
            facts.insertBObject(entity);
            RuleResult result = raytenRuleEngine.RunRuleByGroupId("com.isiran.hr.personnel", facts);
            testRepository.saveOrUpdate(entity);
            messageUtil.addInfoMessage("","saved_successfully");
        }catch (Exception ex)
        {
            ex.printStackTrace();
            messageUtil.addErrorMessage("","saved_failed",ex.getMessage());
        }
    }

    public Personnel init(Personnel entity) {
        return new Personnel();
    }
    public static boolean isNationalCodeValid(String code)
    {
        if(code.length()<10)
        return true;
        else
            return false;
    }
}
