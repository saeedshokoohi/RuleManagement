package domain;

import javax.persistence.*;
import java.util.Arrays;
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



    @Basic
    @Column(name = "rule_name", nullable = false, length = 500)
    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
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
}
