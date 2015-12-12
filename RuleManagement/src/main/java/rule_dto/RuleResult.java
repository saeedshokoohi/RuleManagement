package rule_dto;

import rule_engine.MethodResult;

/**
 * Created by saeed on 02/12/2015.
 */
public class RuleResult extends BaseRule {
    private int resultCode;
    private MethodResult compileResult;

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setCompileResult(MethodResult compileResult) {
        this.compileResult = compileResult;
    }

    public MethodResult getCompileResult() {
        return compileResult;
    }
}
