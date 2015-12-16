package controller;

import domain.RmRawRule;
import repository.RawRuleRepository;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by saeed on 15/12/2015.
 */
@Named
public class RawRuleController {
    @Inject
    RawRuleRepository rawRuleRepository;

    public void save()
    {
        RmRawRule entity=new RmRawRule();
        entity.setResourceType("DRL");
        entity.setRuleName("firstRule");
        entity.setVersionNumber("1.0");
        rawRuleRepository.findAll();
        rawRuleRepository.save(entity);
    }

}
