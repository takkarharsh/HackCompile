package com.guavus.raf.compile.utils;


public enum ErrorCodes {


    COMPILATION_FAILED("HACK_400","COMPILATION FAILED"),
    EXECUTION_FAILED("HACK_400","EXECUTION FAILED");



    private String code;
    private String description;

    ErrorCodes(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return code + " : " + description;
    }
}

