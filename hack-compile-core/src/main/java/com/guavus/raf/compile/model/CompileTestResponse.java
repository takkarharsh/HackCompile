package com.guavus.raf.compile.model;

import java.io.Serializable;

public class CompileTestResponse implements Serializable {

    private String testName;
    private String status;

    public CompileTestResponse(String testName, String status) {
        this.testName = testName;
        this.status = status;
    }

    public CompileTestResponse() {
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
