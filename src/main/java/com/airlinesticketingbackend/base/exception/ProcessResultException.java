package com.airlinesticketingbackend.base.exception;

import com.airlinesticketingbackend.dto.common.ProcessResult;

public class ProcessResultException extends RuntimeException {
    private ProcessResult processResult;
    private boolean involvedGeneric;

    public ProcessResultException(ProcessResult processResult, boolean isInvolvedGeneric) {
        this.processResult = processResult;
        this.involvedGeneric = isInvolvedGeneric;
    }

    public ProcessResult getProcessResult() {
        return processResult;
    }

    public void setProcessResult(ProcessResult processResult) {
        this.processResult = processResult;
    }

    public boolean isInvolvedGeneric() {
        return involvedGeneric;
    }

    public void setInvolvedGeneric(boolean involvedGeneric) {
        this.involvedGeneric = involvedGeneric;
    }
}
