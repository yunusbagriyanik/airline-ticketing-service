package com.airlinesticketingbackend.dto.common;

public class GenericResult<T> {
    protected T result;
    protected ProcessResult processResult;

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public ProcessResult getProcessResult() {
        return processResult;
    }

    public void setProcessResult(ProcessResult processResult) {
        this.processResult = processResult;
    }
}
