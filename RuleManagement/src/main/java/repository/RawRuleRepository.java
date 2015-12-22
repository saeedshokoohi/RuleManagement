/*
 * (c) Copyright 2005-2013 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to purchase Celerio ? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Template pack-backend-jpa:src/main/java/repository/Repository.e.vm.java
 */
package repository;

import domain.RmRawRule;
import repository.support.GenericRepository;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.persistence.Query;
import java.util.List;

@Named
@Singleton
public class RawRuleRepository extends GenericRepository<RmRawRule> {

    public static String getInitRuleTemplate(String ruleName)
    {
        String rule="\n\ndialect  \"java\"\n\n\n" +
                "rule \""+ruleName+"\"\n\n" +
                "when\n" +
                "    /* the rule conditions\n" +
                "      are here */\n" +
                "then\n" +
                "    /* \n" +
                "        the right hand side of a rule */\n" +
                "end ";
        return  rule;

    }
    public RawRuleRepository() {
        super(RmRawRule.class);
    }


    public List<String> findGroups(String val) {
        Query q = getEm().createQuery("select r.groupId from RmRawRule r group by r.groupId having groupId like %:str%")
                .setParameter("str", val);
        return q.getResultList();
    }
}