package domain;

/**
 * Created by saeed on 22/12/2015.
 */
public enum RuleResourceType {
    DRL("DRL","drl","DroolsRule"),
    DTABLE("DTABLE","xls","DecisionTable"),
    PKG("PKG","jar","BinaryPackage");

    RuleResourceType(String key, String extension, String descrption) {
        this.key = key;
        this.extension = extension;
        this.description = descrption;
    }

    private String key;
    private String extension;
    private String description;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return key;
    }
}
