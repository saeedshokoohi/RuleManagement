package domain;

import rule_engine.RuleContentTypes;

import javax.persistence.*;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Created by saeed on 12/12/2015.
 */
@Entity
@Table(name = "rm_raw_rule", schema = "public")
public class RmRawRule extends BaseEntity {
    private String ruleName;
    private String ruleDescription;
    private String ruleDisplayName;
    private String versionNumber;
    private String resourceType;
    private byte[] ruleContent;
    private String ruleContentStr;
    private String imports;
    private String groupId;
    private String ruleContentType;
    private String fileType;


    @Basic
    @Column(name = "rule_name", nullable = false, length = 500)
    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    @Column(name = "group_id")
    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @Basic
    @Column(name = "imports", nullable = false, length = 500)
    public String getImports() {
        return imports;
    }

    public void setImports(String imports) {
        this.imports = imports;
    }

    @Basic
    @Column(name = "rule_description", nullable = true, length = 1000)
    public String getRuleDescription() {
        return ruleDescription;
    }

    public void setRuleDescription(String ruleDescription) {
        this.ruleDescription = ruleDescription;
    }

    @Basic
    @Column(name = "rule_display_name", nullable = true, length = 500)
    public String getRuleDisplayName() {
        return ruleDisplayName;
    }

    public void setRuleDisplayName(String ruleDisplayName) {
        this.ruleDisplayName = ruleDisplayName;
    }

    @Basic
    @Column(name = "version_number", nullable = true, length = 100)
    public String getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(String versionNumber) {
        this.versionNumber = versionNumber;
    }

    @Basic
    @Column(name = "resource_type", nullable = true, length = 10)
    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    @Basic
    @Column(name = "rule_content", nullable = true)
    public byte[] getRuleContent() {
        return ruleContent;
    }

    public void setRuleContent(byte[] ruleContent) {
        this.ruleContent = ruleContent;
    }

    @Basic
    @Column(name = "rule_content_type", nullable = true)
    public String getRuleContentType() {
        if(ruleContentType==null || ruleContentType.isEmpty())
            ruleContentType=RuleContentTypes.TEXT_AS_RULE.name();
        return ruleContentType.trim();
    }

    public void setRuleContentType(String ruleContentType) {
        this.ruleContentType = ruleContentType;
    }
    @Basic
    @Column(name = "file_type", nullable = true)
    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    @Transient
    public String getRuleContentStr() {
if(ruleContent==null)return  "";
        try {
            if(ruleContentType.trim().equalsIgnoreCase(RuleContentTypes.TEXT_AS_RULE.name())) {
                ruleContentStr = new String(ruleContent, "UTF-8");
            }
            return ruleContentStr;

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }

    }

    public void setRuleContentStr(String ruleContentStr) {
        if(ruleContentStr==null)ruleContentStr="";
        ruleContent=ruleContentStr.getBytes();
        this.ruleContentStr = ruleContentStr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RmRawRule rmRawRule = (RmRawRule) o;

        if (id != null ? !id.equals(rmRawRule.id) : rmRawRule.id != null) return false;
        if (ruleName != null ? !ruleName.equals(rmRawRule.ruleName) : rmRawRule.ruleName != null) return false;
        if (ruleDescription != null ? !ruleDescription.equals(rmRawRule.ruleDescription) : rmRawRule.ruleDescription != null)
            return false;
        if (ruleDisplayName != null ? !ruleDisplayName.equals(rmRawRule.ruleDisplayName) : rmRawRule.ruleDisplayName != null)
            return false;
        if (versionNumber != null ? !versionNumber.equals(rmRawRule.versionNumber) : rmRawRule.versionNumber != null)
            return false;
        if (resourceType != null ? !resourceType.equals(rmRawRule.resourceType) : rmRawRule.resourceType != null)
            return false;
        if (!Arrays.equals(ruleContent, rmRawRule.ruleContent)) return false;

        return true;
    }


    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (ruleName != null ? ruleName.hashCode() : 0);
        result = 31 * result + (ruleDescription != null ? ruleDescription.hashCode() : 0);
        result = 31 * result + (ruleDisplayName != null ? ruleDisplayName.hashCode() : 0);
        result = 31 * result + (versionNumber != null ? versionNumber.hashCode() : 0);
        result = 31 * result + (resourceType != null ? resourceType.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(ruleContent);
        return result;
    }

    private List<RmCompiledRule> oneToMany;

    @OneToMany
    public List<RmCompiledRule> getOneToMany() {
        return oneToMany;
    }

    public void setOneToMany(List<RmCompiledRule> oneToMany) {
        this.oneToMany = oneToMany;
    }

    @Transient
    public String getRuleFileName() {
        try {
            if(getRuleContentType().contains(RuleContentTypes.FILE_AS_RULE.name()))
            return ruleName + "." + getRuleFileExtension();
        }catch (Exception ex){}
        return "";
    }
    @Transient
    public String getRuleFileExtension() {
        try {
            if(getRuleContentType().contains(RuleContentTypes.FILE_AS_RULE.name()))
                return  RuleResourceType.valueOf(resourceType).getExtension();
        }catch (Exception ex){}
        return "";
    }
   @Transient
    public RuleContentTypes getRuleContentTypeObject() {
       try{
           return  RuleContentTypes.valueOf(getRuleContentType());
       }catch (Exception ex){}
        return RuleContentTypes.TEXT_AS_RULE;
    }

    public void setRuleContentTypeObject(RuleContentTypes ruleContentTypeObject) {
        setRuleContentType(ruleContentTypeObject.name());
    }
}
