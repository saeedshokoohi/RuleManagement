package rule_engine;

import domain.RmCompiledRule;
import org.drools.compiler.kproject.ReleaseIdImpl;
import org.kie.api.builder.ReleaseId;
import rule_dto.CompiledRule;

/**
 * Created by saeed on 02/12/2015.
 */
public class RuleUtility {

    private static final String DEFAULT_GROUP = "com.isiran.rayten";
    private static final String DEFAULT_ARTIFACT = "rule";
    private static final String DEFAULT_VERSION = "1.0.SNAPSHOT";

  public static   ReleaseId makeReleaseId(String group, String artifact, String version)
    {
        if(group==null || group.trim().length()==0)group=DEFAULT_GROUP;
        if(artifact==null || artifact.trim().length()==0)artifact=DEFAULT_ARTIFACT;
        if(version==null || version.trim().length()==0)version=DEFAULT_VERSION;
        return new ReleaseIdImpl(group,artifact,version);
    }


    public static ReleaseId makeReleaseId(RmCompiledRule compiledRuleEntity) {
        return  makeReleaseId(compiledRuleEntity.getGroupId(),compiledRuleEntity.getArtifactId(),compiledRuleEntity.getVersionId());
    }
    public static CompiledRule makeCompileRuleFromEntity(RmCompiledRule compiledRuleEntity) {
        CompiledRule retRule=new CompiledRule();
        retRule.setKjarFile(compiledRuleEntity.getKjarFile());
        retRule.setReleaseId(RuleUtility.makeReleaseId(compiledRuleEntity));
        return retRule;
    }
}
