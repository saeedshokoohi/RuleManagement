package rule_dto;

import org.kie.api.io.Resource;

/**
 * Created by saeed on 02/12/2015.
 */
public class Rule extends BaseRule {
    private Resource resource;
    private byte[] ruleContent;

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public Resource getResource() {
        return resource;
    }

    public void setRuleContent(byte[] ruleContent) {
        this.ruleContent = ruleContent;
    }

    public byte[] getRuleContent() {
        return ruleContent;
    }
}
