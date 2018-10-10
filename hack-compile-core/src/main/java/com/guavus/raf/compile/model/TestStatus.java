package com.guavus.raf.compile.model;

public class TestStatus {
    private String testName;
    private String status;

    public TestStatus(String testName, String status) {
        this.testName = testName;
        this.status = status;
    }

    public TestStatus() {
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
