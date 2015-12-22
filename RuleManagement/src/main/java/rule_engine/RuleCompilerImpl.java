package rule_engine;

import org.drools.compiler.kie.builder.impl.InternalKieModule;
import org.drools.compiler.kproject.ReleaseIdImpl;
import org.kie.api.KieServices;
import org.kie.api.builder.*;
import org.kie.api.builder.model.KieModuleModel;
import org.kie.api.io.KieResources;
import rule_dto.CompiledRule;
import rule_dto.Rule;

import java.util.UUID;

/**
 * Created by Saeed on 02/12/2015.
 */
public class RuleCompilerImpl  implements RuleCompiler {

    BaseRuleAgents baseRuleAgents;

    public RuleCompilerImpl(BaseRuleAgents baseRuleAgents) {
        this.baseRuleAgents = baseRuleAgents;
    }

    @Override
    public CompiledRule compileRule(MethodResult result, Rule rule){


        CompiledRule compiledRule=new CompiledRule();

        //step 3 : write the resources to fileSystem
        KieFileSystem fileSystem = baseRuleAgents.kieServices.newKieFileSystem();
      //  fileSystem= fileSystem.write("/src/main/java/temp.drl",rule.getResource());
        ReleaseId releseId = new ReleaseIdImpl("rayten","testRule","1.0");
        if(rule!=null && rule.getResource()!=null && rule.getRuleName()!=null ) {
            fileSystem.generateAndWritePomXML(releseId);
            fileSystem= fileSystem.write("src/main/resources/"+rule.getRuleName()+".drl",rule.getResource());
        }
        //   KieModuleModel kmodel=baseRuleAgents.kieServices.newKieModuleModel();
        //step 4 : build the rule are temporary stored in filesystem
        KieBuilder kieBuilder=baseRuleAgents.kieServices.newKieBuilder(fileSystem);
        KieBuilder r = kieBuilder.buildAll();
        if(!r.getResults().hasMessages(Message.Level.ERROR)) {
            result.setDone(true);
            result.setMessages(r.getResults().getMessages());
            //    ReleaseId releseId = baseRuleAgents.getKieRepository().getDefaultReleaseId();

            InternalKieModule kmodule = (InternalKieModule) baseRuleAgents.getKieRepository().getKieModule(releseId);
            compiledRule.setReleaseId(kmodule.getReleaseId());
            compiledRule.setKjarFile(kmodule.getBytes());
        }
        else
        {
            result.setDone(false);
            compiledRule.setCompileResult(r.getResults());
            result.setErrorMessages(r.getResults().getMessages(Message.Level.ERROR));
        }
        result.setMessages(r.getResults().getMessages());
        result.setWarningMessages(r.getResults().getMessages(Message.Level.WARNING));
        result.setInfoMessages(r.getResults().getMessages(Message.Level.INFO));
        return  compiledRule;
    }

    public static byte[] createKJar(KieServices ks,
                                    ReleaseId releaseId,
                                    String pom,
                                    String... drls) {
        KieFileSystem kfs = ks.newKieFileSystem();
        if( pom != null ) {
            kfs.write("pom.xml", pom);
        } else {
            kfs.generateAndWritePomXML(releaseId);
        }
        KieResources kr = ks.getResources();
        for (int i = 0; i < drls.length; i++) {
            if (drls[i] != null) {
                kfs.write( kr.newByteArrayResource( drls[i].getBytes() ).setSourcePath("my/pkg/drl"+i+".drl") );
            }
        }
        KieBuilder kb = ks.newKieBuilder(kfs).buildAll();
        if( kb.getResults().hasMessages( org.kie.api.builder.Message.Level.ERROR ) ) {
            for( org.kie.api.builder.Message result : kb.getResults().getMessages() ) {
                System.out.println(result.getText());
            }
            return null;
        }
        InternalKieModule kieModule = (InternalKieModule) ks.getRepository()
                .getKieModule(releaseId);
        byte[] jar = kieModule.getBytes();
        return jar;
    }
}
