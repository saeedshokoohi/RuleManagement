package domain;

import org.jsoup.Connection;

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
    private String releaseNumber;
    private byte[] kjarFile;


    @Basic
    @Column(name = "rule_name", nullable = false, length = 500)
    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    @Basic
    @Column(name = "release_number", nullable = true, length = 200)
    public String getReleaseNumber() {
        return releaseNumber;
    }

    public void setReleaseNumber(String releaseNumber) {
        this.releaseNumber = releaseNumber;
    }

    @Basic
    @Column(name = "kjar_file", nullable = true)
    public byte[] getKjarFile() {
        return kjarFile;
    }

    public void setKjarFile(byte[] kjarFile) {
        this.kjarFile = kjarFile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RmCompiledRule that = (RmCompiledRule) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (ruleName != null ? !ruleName.equals(that.ruleName) : that.ruleName != null) return false;
        if (releaseNumber != null ? !releaseNumber.equals(that.releaseNumber) : that.releaseNumber != null)
            return false;
        if (!Arrays.equals(kjarFile, that.kjarFile)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (ruleName != null ? ruleName.hashCode() : 0);
        result = 31 * result + (releaseNumber != null ? releaseNumber.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(kjarFile);
        return result;
    }
}
