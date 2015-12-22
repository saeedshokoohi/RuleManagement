package controller;

import domain.RmRawRule;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.context.annotation.Scope;
import org.springframework.webflow.execution.RequestContext;
import org.springframework.webflow.execution.RequestContextHolder;
import repository.RawRuleRepository;
import rule_engine.MethodResult;
import rule_engine.RaytenRuleEngine;
import rule_engine.RuleContentTypes;
import util.ClassFinder;
import util.MessageUtil;
import util.ResourcesUtil;
import util.ViewUtil;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
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
//            if(getRuleVM().isFromFile() && getRuleVM().getRuleFile()!=null) {
//                entity.setRuleContent(getRuleVM().getRuleFile().getContents());
//                entity.setFileType(getRuleVM().getRuleFile().getContentType());
//            }
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
    public void uploadRuleFile() {
        RuleViewModel ruleViewModel=getRuleVM();
        //checking if any file has uploaded
        if(ruleViewModel != null && ruleViewModel.getRuleFile()!=null) {
            //checking if uploaded file is valid based on selected type
           String ext=  ViewUtil.getExtensionFromFileName(ruleViewModel.getRuleFile().getFileName());
            if(ext.equalsIgnoreCase(ruleViewModel.getRawRule().getRuleFileExtension())) {
                messageUtil.addInfoMessage("","file_uploaded_successfully");
                FacesMessage message = new FacesMessage("");
                ruleViewModel.getRuleFile();
                ruleViewModel.getRawRule().setRuleContent(ruleViewModel.getRuleFile().getContents());
                ruleViewModel.getRawRule().setFileType(ruleViewModel.getRuleFile().getContentType());
            }
            else
            {
                messageUtil.addErrorMessage("","error_rule_file_type_not_valid");
            }

        }
    }
    public void fileTypeChanged(RuleViewModel ruleViewModel)
    {
        if(RuleContentTypes.valueOf(ruleViewModel.getRawRule().getRuleContentType())==RuleContentTypes.FILE_AS_RULE)ruleViewModel.setFromFile(true);
        else ruleViewModel.setFromFile(false);
    }
    public  void makeDownloadFile(RuleViewModel ruleVM)
    {
        if(ruleVM.getRawRule().getRuleContentType() !=null && ruleVM.getRawRule().getRuleContentType().contains(RuleContentTypes.FILE_AS_RULE.name())) {
            InputStream stream = new ByteArrayInputStream(ruleVM.getRawRule().getRuleContent());
            StreamedContent streanContent = new DefaultStreamedContent(stream,ruleVM.getRawRule().getFileType(),ruleVM.getRawRule().getRuleFileName());
            ruleVM.setRuleFileForDownload(streanContent);
        }
    }
    public boolean canDownloadFile(RuleViewModel ruleViewModel)
    {
        if(ruleViewModel.getRawRule().getRuleContentType().contains(RuleContentTypes.FILE_AS_RULE.name())
             &&  ruleViewModel.getRawRule().getFileType()!=null &&  !ruleViewModel.getRawRule().getFileType().isEmpty() && ruleViewModel.getRawRule().getRuleContent().length>0  )
            return true;
        else
            return false;

    }
}
