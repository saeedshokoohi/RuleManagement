package rule_dto;

import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.ReleaseId;
import org.kie.api.builder.Results;
import org.kie.api.io.Resource;

/**
 * Created by saeed on 02/12/2015.
 */
public class CompiledRule extends BaseRule {
    private KieBuilder builder;
    private Results compileResult;
    private byte[] kjarFile;
    private String releaseNumber;
    private ReleaseId releaseId;
    private Resource resource;

    public void setBuilder(KieBuilder builder) {
        this.builder = builder;
    }

    public KieBuilder getBuilder() {
        return builder;
    }

    public void setCompileResult(Results compileResult) {
        this.compileResult = compileResult;
    }

    public Results getCompileResult() {
        return compileResult;
    }

    public void setKjarFile(byte[] kjarFile) {
        this.kjarFile = kjarFile;
    }

    public byte[] getKjarFile() {
        return kjarFile;
    }

    public void setReleaseId(ReleaseId releaseId) {
        this.releaseId = releaseId;
    }

    public ReleaseId getReleaseId() {
        return releaseId;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public Resource getResource() {
        return resource;
    }
}
