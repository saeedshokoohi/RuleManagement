package controller.Test;

import controller.RuleViewModel;
import domain.RmRawRule;
import domain.Test.Personnel;
import domain.Test.PersonnelOverTime;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
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

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
            RuleResult result = raytenRuleEngine.RunRuleByGroupId("com.isiran.test", facts);
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
    private UploadedFile ruleFile;

    public UploadedFile getRuleFile() {
        return ruleFile;
    }

    public void setRuleFile(UploadedFile file) {
        this.ruleFile = file;
    }
    public void uploadRuleFile() {

        if(ruleFile!=null) {
            FacesMessage message = new FacesMessage("Succesful", ruleFile.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    public static boolean isNationalCodeValid(String code)
    {
        if(code.length()<10)
        return true;
        else
            return false;
    }

    //injecting Rayten Rule Engine
    @Inject
    RaytenRuleEngine raytenRuleEngine;

    public void caculateOverTime(Personnel personnel ,List<PersonnelOverTime> overTimeList)
    {
        //creating Fact to be added to working memmory
        RuleFact facts=new RuleFact();
        //inserting data as part of fact
        facts.insertBObject(overTimeList);
        facts.insertBObject(personnel);
        //fire the rule
        RuleResult result = raytenRuleEngine.RunRuleByAgendaGroup("sample_group", facts);

    }



}
