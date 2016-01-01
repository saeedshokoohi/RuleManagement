package controller;

import domain.RmRawRule;
import org.springframework.context.annotation.Scope;
import repository.RawRuleRepository;
import util.MessageUtil;
import util.ResourcesUtil;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by saeed on 15/12/2015.
 */
@Named
public class RawRuleController {


    @Inject
    RawRuleRepository rawRuleRepository;
    @Inject
    ResourcesUtil resourcesUtil;
    @Inject
    MessageUtil messageUtil;

    public void save(RmRawRule entity)
    {
       // RmRawRule entity=new RmRawRule();
        entity.setResourceType("DRL");
      //  entity.setRuleName("firstRule");
     //   entity.setVersionNumber("1.0");
      //  rawRuleRepository.findAll();
        try {
            rawRuleRepository.saveOrUpdate(entity);
            messageUtil.addInfoMessage("","saved_successfully");
        }catch (Exception ex)
        {
            ex.printStackTrace();
            messageUtil.addErrorMessage("","saved_failed",ex.getMessage());
        }
    }

    public void initRawRuleList(RuleViewModel ruleVM ) {
        List<RmRawRule> ruleList=rawRuleRepository.findAll();
        ruleVM.setRawRuleList(ruleList);
    }
    public  String gotoEdit(RmRawRule e,RuleViewModel ruleVM)
    {
        ruleVM.setRawRule(e);
        return "edit";
    }
    public RmRawRule initRawRule()
    {
        return new RmRawRule();
    }
}
