package com.guavus.raf.compile.model;

import java.io.Serializable;

public class CompileInput implements Serializable {
    private String packagename;
    private String code;

    public CompileInput() {
    }

    public String getPackagename() {
        return packagename;
    }

    public void setPackagename(String packagename) {
        this.packagename = packagename;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
