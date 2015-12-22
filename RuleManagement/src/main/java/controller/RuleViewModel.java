package controller;

import com.sun.org.apache.xpath.internal.operations.Bool;
import domain.RmRawRule;

import domain.RuleResourceType;
import repository.RawRuleRepository;
import util.ViewUtil;

import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by saeed on 31/12/2015.
 */

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

    public String getCompileMessage() {
        return compileMessage;
    }

    public void setCompileMessage(String compileMessage) {
        this.compileMessage = compileMessage;
    }

    public RuleResourceType[] getResourceTypes() {
        return RuleResourceType.values();
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
        //new mode
        if (isNewMode == null || isNewMode) {
            this.rawRule = new RmRawRule();
            this.rawRule.setRuleName("ruleName");
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
}
