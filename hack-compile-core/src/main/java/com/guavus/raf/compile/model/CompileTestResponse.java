package com.guavus.raf.compile.model;

import java.io.Serializable;
import java.util.List;

public class CompileTestResponse implements Serializable {

    private List<TestStatus> testStatusList;
    private boolean successful;

    public CompileTestResponse(List<TestStatus> testStatusList, boolean successful) {
        this.testStatusList = testStatusList;
        this.successful = successful;
    }

    public CompileTestResponse() {
    }

    public List<TestStatus> getTestStatusList() {
        return testStatusList;
    }

    public void setTestStatusList(List<TestStatus> testStatusList) {
        this.testStatusList = testStatusList;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }
}
