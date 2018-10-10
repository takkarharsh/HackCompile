package com.guavus.raf.compile.service;

import com.guavus.raf.compile.model.CompileInput;
import com.guavus.raf.compile.utils.Compile;
import org.springframework.stereotype.Service;

@Service
public class CompileService {

    public String compile(CompileInput compileInput) {
        String out = Compile.compile(compileInput.getPackagename(), compileInput.getCode());
        return out;
    }
}
