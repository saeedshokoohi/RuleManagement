package domain;

import javax.persistence.*;
import java.util.Arrays;
import java.util.UUID;

/**
 * Created by saeed on 12/12/2015.
 */
@Entity
@Table(name = "rm_compiled_rule", schema = "public", catalog = "rm_db")
public class RmCompiledRule extends BaseEntity {
    private String ruleName;
    private String versionId;
    private byte[] kjarFile;
    private String groupId;
    private String artifactId;
//    private UUID ruleRef;
    private RmRawRule ruleRefEntity;


    @Basic
    @Column(name = "rule_name", nullable = false, length = 500)
    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

//    @Column(name = "rule_ref")
//    public UUID getRuleRef() {
//        return ruleRef;
//    }
//
//    public void setRuleRef(UUID ruleRef) {
//        this.ruleRef = ruleRef;
//    }

    @ManyToOne
    @JoinColumn(name = "rule_ref",insertable = false ,updatable = false)
    public RmRawRule getRuleRefEntity() {
        return ruleRefEntity;
    }

    public void setRuleRefEntity(RmRawRule ruleRefEntity) {
        this.ruleRefEntity = ruleRefEntity;
    }

    @Basic
    @Column(name = "version_id", nullable = true, length = 200)
    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String releaseNumber) {
        this.versionId = releaseNumber;
    }

    @Basic
    @Column(name = "kjar_file", nullable = true)
    public byte[] getKjarFile() {
        return kjarFile;
    }

    public void setKjarFile(byte[] kjarFile) {
        this.kjarFile = kjarFile;
    }
    @Basic
    @Column(name = "group_id", nullable = true, length = 100)
    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
    @Basic
    @Column(name = "artifact_id", nullable = true, length = 100)
    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RmCompiledRule that = (RmCompiledRule) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (ruleName != null ? !ruleName.equals(that.ruleName) : that.ruleName != null) return false;
        if (versionId != null ? !versionId.equals(that.versionId) : that.versionId != null)
            return false;
        if (!Arrays.equals(kjarFile, that.kjarFile)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (ruleName != null ? ruleName.hashCode() : 0);
        result = 31 * result + (versionId != null ? versionId.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(kjarFile);
        return result;
    }
}
