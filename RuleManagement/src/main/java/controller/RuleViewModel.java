package controller;

import domain.RmRawRule;

import domain.RuleResourceType;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import repository.RawRuleRepository;
import rule_engine.RuleContentTypes;
import util.ResourcesUtil;
import util.ViewUtil;

import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by saeed on 31/12/2015.
 */
@Named
public class RuleViewModel implements Serializable {


    private RmRawRule rawRule;
    private List<RmRawRule> rawRuleList;
    private String selectedPackage;
    private String selectedclass;
    private List<String> importedClasses;
    private String classList;
    private String methodList;
    private Boolean isNewMode;
    private String compileMessage;
    private Object availableGroups;
    private String ruleContentType;
    private boolean fromFile=false;
    private UploadedFile ruleFile;
    private StreamedContent ruleFileForDownload;


    public UploadedFile getRuleFile() {
        return ruleFile;
    }

    public void setRuleFile(UploadedFile file) {
        this.ruleFile = file;
    }

    public String getCompileMessage() {
        return compileMessage;
    }

    public void setCompileMessage(String compileMessage) {
        this.compileMessage = compileMessage;
    }

    public List<SelectItem> getResourceTypes() {
        List<SelectItem> retList=new ArrayList<SelectItem>();
        for(RuleResourceType rrt:RuleResourceType.values())
        {
            retList.add(new SelectItem(rrt.getKey(),rrt.getDescription()));
        }
        System.out.println();
        return retList;
    }

    public Boolean getNewMode() {
        return isNewMode;
    }

    public void setNewMode(Boolean newMode) {
        isNewMode = newMode;
    }

    public RmRawRule getRawRule() {
        if (rawRule == null) rawRule = new RmRawRule();
        return rawRule;
    }

    public void setRawRule(RmRawRule rawRule) {
        this.rawRule = rawRule;
    }

    public List<RmRawRule> getRawRuleList() {
        if (rawRuleList == null) rawRuleList = new ArrayList<RmRawRule>();
        return rawRuleList;
    }

    public void setRawRuleList(List<RmRawRule> rawRuleList) {
        this.rawRuleList = rawRuleList;
    }

    public void initRawRule() {
        compileMessage="";
        //new mode
        if (isNewMode == null || isNewMode) {
            this.rawRule = new RmRawRule();
            this.rawRule.setRuleName("ruleName");
            this.rawRule.setRuleContentType(RuleContentTypes.TEXT_AS_RULE.name());
            this.rawRule.setRuleContentStr(RawRuleRepository.getInitRuleTemplate("ruleName"));
        }else
        //edit mode
        {
            importedClasses=stringToList(this.rawRule.getImports());

        }
    }

    private List<String> stringToList(String imports) {
        List<String> retList=new ArrayList<String>();
        Object tempImports = ViewUtil.JsonToObject(imports, List.class);
        if(tempImports!=null)
            retList=(List<String>)tempImports;
        return retList;
    }

    public String getSelectedPackage() {
        return selectedPackage;
    }

    public void setSelectedPackage(String selectedPackage) {
        this.selectedPackage = selectedPackage;
    }

    public void setSelectedclass(String selectedclass) {
        this.selectedclass = selectedclass;
    }

    public String getSelectedclass() {
        return selectedclass;
    }

    public List<String> getImportedClasses() {
        if (importedClasses == null) importedClasses = new ArrayList<String>();
        return importedClasses;

    }

    public void setImportedClasses(List<String> importedClasses) {
        this.importedClasses = importedClasses;
    }

    public void setClassList(String classList) {
        this.classList = classList;
    }

    public String getClassList() {
        String retStr = "";
        List<String> classListTemp = new ArrayList<String>();
        for (String c : getImportedClasses()) {
            String[] parts = c.split("\\.");
            String classToadd = c;
            if (parts.length > 1) {
                classToadd = parts[parts.length - 1];
            }
            if (!classListTemp.contains(classToadd)) classListTemp.add(classToadd);
        }
        retStr = ViewUtil.ObjectToJsonStr(classListTemp);
        return retStr;
    }

    public void setMethodList(String methodList) {
        this.methodList = methodList;
    }

    public String getMethodList() {
        methodList = "";
        List<String> methodListTemp = new ArrayList<String>();
        for (String c : importedClasses) {
            for (String m : getMethodsOfClass(c))
                if (!methodListTemp.contains(m))
                    methodListTemp.add(m);
        }
        methodList = ViewUtil.ObjectToJsonStr(methodListTemp);
        return methodList;
    }

    private List<String> getMethodsOfClass(String c) {

        List<String> retStr = new ArrayList<String>();
        try {
            Class<?> myclass = Class.forName(c);
            Method[] methods = myclass.getDeclaredMethods();
            for (Method m : methods) {
                retStr.add(makeMethodInString(m));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return retStr;
    }

    private String makeMethodInString(Method m) {
        return m.getName() + "()";
    }

    public void setAvailableGroups(Object availableGroups) {
        this.availableGroups = availableGroups;
    }

    public Object getAvailableGroups() {
        return availableGroups;
    }

    public void setRuleContentType(String ruleContentType) {
        this.ruleContentType = ruleContentType;
    }

    public String getRuleContentType() {
        return ruleContentType;
    }

    public void setFromFile(boolean fromFile) {
        this.fromFile = fromFile;
    }

    public boolean isFromFile() {
        fromFile=getRawRule().getRuleContentTypeObject()==RuleContentTypes.FILE_AS_RULE;
        return fromFile;
    }

    public void setRuleFileForDownload(StreamedContent ruleFileForDownload) {
        this.ruleFileForDownload = ruleFileForDownload;
    }

    public StreamedContent getRuleFileForDownload() {
        return ruleFileForDownload;
    }
}
