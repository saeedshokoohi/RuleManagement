package controller;

import domain.RmRawRule;
import org.springframework.context.annotation.Scope;
import org.springframework.webflow.execution.RequestContext;
import org.springframework.webflow.execution.RequestContextHolder;
import repository.RawRuleRepository;
import util.ClassFinder;
import util.MessageUtil;
import util.ResourcesUtil;

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
    public void packageChanged()
    {
        getRuleVM().getRawRule();
    }
    public void importClass(RmRawRule rmRawRule)
    {
        System.out.printf("importClass called");
        String importstr=makeImportStr(getRuleVM().getSelectedclass());
        rmRawRule.setRuleContentStr(importstr+rmRawRule.getRuleContentStr());

    }

    private String makeImportStr(String selectedclass) {
        return "import "+selectedclass+";\n";
    }
}
