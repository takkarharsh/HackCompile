package com.guavus.raf.compile.model;

import java.io.Serializable;

public class CompileInput implements Serializable {
    private String packagename;
    private String code;
    private String utCode;
    private String utPackagename;


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

    public String getUtCode() {
        return utCode;
    }

    public void setUtCode(String utCode) {
        this.utCode = utCode;
    }

    public String getUtPackagename() {
        return utPackagename;
    }

    public void setUtPackagename(String utPackagename) {
        this.utPackagename = utPackagename;
    }
}
