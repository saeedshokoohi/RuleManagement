package rayten;

import entities.Student;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.builder.ReleaseId;
import org.kie.api.event.rule.DebugRuleRuntimeEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import rule_dto.RuleFact;
import rule_dto.RuleQuery;
import rule_engine.RaytenRuleEngine;

import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by saeed on 22/10/2015.
 */
@Named
public class GoodStudentRule implements Serializable {




    public String checkRule(Student student)
    {
        String result="";
        RaytenRuleEngine raytenRuleEngine=new RaytenRuleEngine();
        RuleQuery query=new RuleQuery();
        RuleFact facts=new RuleFact();
        facts.insertBObject(new Student("ali","shokoohi zad",0));
        facts.insertBObject(new Student("molla","rozbeh",2));
        facts.insertBObject(new Student("karim", "aghyani", 3));
        raytenRuleEngine.runRule(query,facts);
      /*  kieSession.addEventListener( new DebugRuleRuntimeEventListener() );
        Student s1=new Student("saeed","shokoohi",5);
        kieSession.insert(s1);
        kieSession.insert();
        kieSession.insert();
        kieSession.insert();
        int i= kieSession.fireAllRules();
        kieSession.dispose();
        */


        return result;
    }

   /* public String checkRule(Student student)
    {
        String url = "http://localhost:8080/kie-/maven2/com/isiran/ruleRepo/isiranDrools/rayten/1.0/rayten-1.0.jar";
        String result="";
        KieServices kieServices=KieServices.Factory.get();
        //getting rule from classPath

        KieContainer container = kieServices.getKieClasspathContainer();
     *//*   KieBase kieBase=container.getKieBase("defaultKieBase");
        KieSession kieSession= kieBase.newKieSession();*//*


        //
        kieServices.getResources().newUrlResource(url);
        ReleaseId releaseId = kieServices.newReleaseId("isiranDrools", "rayten", "LATEST");

        KieContainer kContainer = kieServices.newKieContainer(releaseId);
        KieScanner kScanner = kieServices.newKieScanner(kContainer);
        kScanner.scanNow();
        KieBase kieBase=kContainer.getKieBase("defaultKieBase");
        KieSession kieSession= kieBase.newKieSession();
      // Start the KieScanner polling the Maven repository every 10 seconds
      // kScanner.start( 10000L );
        kieSession.addEventListener( new DebugRuleRuntimeEventListener() );
        Student s1=new Student("saeed","shokoohi",5);
        kieSession.insert(s1);
        kieSession.insert(new Student("ali","shokoohi zad",0));
        kieSession.insert(new Student("marjan","rozbeh",2));
        kieSession.insert(new Student("klara", "aghyani", 3));
        int i= kieSession.fireAllRules();
        kieSession.dispose();
        return result;
    }
*/
}
