package controller;

import domain.RmRawRule;
import org.springframework.context.annotation.Scope;
import org.springframework.webflow.execution.RequestContext;
import org.springframework.webflow.execution.RequestContextHolder;
import repository.RawRuleRepository;
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
public class RawRuleController {


    @Inject
    RawRuleRepository rawRuleRepository;
    @Inject
    ResourcesUtil resourcesUtil;
    @Inject
    MessageUtil messageUtil;


    public RuleViewModel getRuleVM()
    {
        RequestContext requestContext = RequestContextHolder.getRequestContext();
        return (RuleViewModel)requestContext.getFlowScope().get("ruleVM");
    }

    public void save(RmRawRule entity)
    {

        try {
            entity.setImports(ViewUtil.ObjectToJsonStr(getRuleVM().getImportedClasses()));
            rawRuleRepository.saveOrUpdate(entity);
            messageUtil.addInfoMessage("","saved_successfully");
        }catch (Exception ex)
        {
            ex.printStackTrace();
            messageUtil.addErrorMessage("","saved_failed",ex.getMessage());
        }
    }
    @Inject
    RaytenRuleEngine raytenRuleEngine;
    public void compileRule(RmRawRule entity)
    {
        MethodResult result = raytenRuleEngine.compileRawRule(entity);
        if(result.isDone())
        {
            getRuleVM().setCompileMessage("");
            messageUtil.addInfoMessage("","compiled_successfully");

        }else
        {
            messageUtil.addErrorMessage("","compiled_failed");
           getRuleVM().setCompileMessage(ViewUtil.convertListToHtmlList(result.getDisplayMessages()));

        }
    }
    public void publishRule(RmRawRule entity)
    {
        MethodResult result = raytenRuleEngine.publishRule(entity);
        if(result.isDone())
        {
            getRuleVM().setCompileMessage("");
            messageUtil.addInfoMessage("","publish_successfully");

        }else
        {
            messageUtil.addErrorMessage("","publish_failed");
            getRuleVM().setCompileMessage(ViewUtil.convertListToHtmlList(result.getDisplayMessages()));

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
    public void initRawRule(RuleViewModel ruleVM)
    {
        ruleVM.initRawRule();
    }
    public List<String> completePackageList(String val)
    {
        List<String> filteredPackages=new ArrayList<String>();
        Package[] packages = Package.getPackages();
        for(Package p:packages)
        {
            if(p.getName().contains(val))
            {
                filteredPackages.add(p.getName());
            }
            if(filteredPackages.size()>20)break;
        }
        return filteredPackages;

    }
    public List<String> completeClassList(String val)
    {
        List<String> classlist=new ArrayList<String>();
        RuleViewModel ruleVM = getRuleVM();

        List<Class<?>> classes = ClassFinder.find(ruleVM.getSelectedPackage());
        for(Class<?> c:classes)
        {
            if(c.getName().toLowerCase().contains(val) )
            {
                classlist.add(c.getName());
            }
            if(classlist.size()>20)
                break;
        }
        return classlist;
    }
    public List<String> completeGroupId(String val)
    {
        List<String> retList=new ArrayList<String>();
        retList=rawRuleRepository.findGroups(val);
        return retList;
    }
    public void packageChanged()
    {
        getRuleVM().getRawRule();
    }
    public void importClass(RmRawRule rmRawRule)
    {
        System.out.printf("importClass called");

        String importstr=makeImportStr(getRuleVM().getSelectedclass());
        if(!getRuleVM().getImportedClasses().contains(getRuleVM().getSelectedclass())) {
            getRuleVM().getImportedClasses().add(getRuleVM().getSelectedclass());
        }
        rmRawRule.setRuleContentStr(importstr + rmRawRule.getRuleContentStr());

    }

     public void  removeImport(String className)
     {
         getRuleVM().getImportedClasses().remove(className);
         getRuleVM().getRawRule().setRuleContentStr( getRuleVM().getRawRule().getRuleContentStr().replace(makeImportStr(className),""));
     }
    private String makeImportStr(String selectedclass) {
        return "import "+selectedclass+";\r\n";
    }
}
