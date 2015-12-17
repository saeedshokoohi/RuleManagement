package controller;

import domain.RmRawRule;
import org.springframework.context.annotation.Scope;
import repository.RawRuleRepository;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by saeed on 15/12/2015.
 */
@Named
@Scope("view")
public class RawRuleController {

    RmRawRule entity;

    public RmRawRule getEntity() {
        return entity;
    }

    public void setEntity(RmRawRule entity) {
        this.entity = entity;
    }

    @Inject
    RawRuleRepository rawRuleRepository;

    public void save()
    {
       // RmRawRule entity=new RmRawRule();
        entity.setResourceType("DRL");
      //  entity.setRuleName("firstRule");
        entity.setVersionNumber("1.0");
        rawRuleRepository.findAll();
        rawRuleRepository.save(entity);
    }

}
