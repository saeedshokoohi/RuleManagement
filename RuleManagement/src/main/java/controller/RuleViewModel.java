package controller;

import domain.RmRawRule;
import domain.RmRawRuleList;

import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by saeed on 31/12/2015.
 */

public class RuleViewModel implements Serializable{


    private RmRawRule rawRule;
    private List<RmRawRule> rawRuleList;
    private String selectedPackage;
    private String selectedclass;


    public RmRawRule getRawRule() {
        if(rawRule==null)rawRule=new RmRawRule();
        return rawRule;
    }

    public void setRawRule(RmRawRule rawRule) {
        this.rawRule = rawRule;
    }

    public List<RmRawRule> getRawRuleList() {
        if(rawRuleList==null)rawRuleList=new ArrayList<RmRawRule>();
        return rawRuleList;
    }

    public void setRawRuleList(List<RmRawRule> rawRuleList) {
        this.rawRuleList = rawRuleList;
    }

    public void initRawRule() {
        this.rawRule=new RmRawRule();
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
}
