package rule_dto;

import org.kie.api.io.Resource;

/**
 * Created by saeed on 02/12/2015.
 */
public class Rule extends BaseRule {
    private Resource resource;
    private String ruleText;

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public Resource getResource() {
        return resource;
    }

    public void setRuleText(String ruleText) {
        this.ruleText = ruleText;
    }

    public String getRuleText() {
        return ruleText;
    }
}
