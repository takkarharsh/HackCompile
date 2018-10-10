package com.guavus.raf.compile.model;

import java.io.Serializable;

public class CompileInput implements Serializable {
    private String name;
    private String code;

    public CompileInput() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
