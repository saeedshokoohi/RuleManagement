package rule_dto;

import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.ReleaseId;
import org.kie.api.builder.Results;

/**
 * Created by saeed on 02/12/2015.
 */
public class CompiledRule extends BaseRule {
    private KieBuilder builder;
    private Results compileResult;
    private byte[] kjarFile;
    private String releaseNumber;
    private ReleaseId releaseId;

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
}
