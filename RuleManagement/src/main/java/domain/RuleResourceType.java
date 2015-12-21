package domain;

/**
 * Created by saeed on 22/12/2015.
 */
public enum RuleResourceType {
    DRL("DRL","drl","DroolsRule");

    RuleResourceType(String key, String extension, String descrption) {
        this.key = key;
        this.extension = extension;
        this.descrption = descrption;
    }

    private String key;
    private String extension;
    private String descrption;

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

    public String getDescrption() {
        return descrption;
    }

    public void setDescrption(String descrption) {
        this.descrption = descrption;
    }

    @Override
    public String toString() {
        return key;
    }
}
