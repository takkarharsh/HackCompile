package com.guavus.raf.compile.service;

import com.guavus.raf.compile.model.CompileInput;
import com.guavus.raf.compile.model.CompileTestResponse;
import com.guavus.raf.compile.runtest.UDFComputeTest;
import com.guavus.raf.functions.udf.UDFCompute;
import org.joor.Reflect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompileTestService {

    @Autowired
    CustomTestRunner customTestRunner;

    public CompileTestResponse executeTest(CompileInput compileInput) {
        UDFCompute udfCompute = getUdfCompute(compileInput);
        UDFComputeTest udfComputeTest = getUdfComputeTest(compileInput);
        CompileTestResponse compileTestResponse = customTestRunner.invokeTest(udfCompute, udfComputeTest);
        return compileTestResponse;
    }

    private UDFCompute getUdfCompute(CompileInput compileInput) {
        String udfCode = compileInput.getCode();
        String codePackageName = compileInput.getPackagename();
        UDFCompute udfCompute = Reflect.compile(codePackageName, udfCode).create().get();
        return udfCompute;
    }

    private UDFComputeTest getUdfComputeTest(CompileInput compileInput) {
        String udfCode = compileInput.getUtCode();
        String codePackageName = compileInput.getUtPackagename();
        UDFComputeTest udfComputeTest = Reflect.compile(codePackageName, udfCode).create().get();
        return udfComputeTest;
    }
}
