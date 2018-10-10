package com.guavus.raf.compile.controller;

import com.guavus.raf.compile.model.CompileInput;
import com.guavus.raf.compile.model.CompileTestResponse;
import com.guavus.raf.compile.model.ServiceResponse;
import com.guavus.raf.compile.service.CompileService;
import com.guavus.raf.compile.service.CompileTestService;
import com.guavus.raf.compile.utils.ErrorCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import scala.Tuple2;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/hack/test")
public class TestUdfController {

    @Autowired
    CompileTestService compileTestService;

    @Autowired
    CompileService compileService;

    @RequestMapping(value = "/execute", method = RequestMethod.POST)
    public ServiceResponse<CompileTestResponse> compile(@RequestBody CompileInput compileInput) {
        try {

            Tuple2<String,Boolean> codeCompile = compileService.compile(compileInput.getPackagename(), compileInput.getCode());
            if(!codeCompile._2){
                return new ServiceResponse<>(HttpStatus.BAD_REQUEST, ErrorCodes.COMPILATION_FAILED.getCode(), "Code Compilation Failed\n"+codeCompile._1);
            }

            Tuple2<String,Boolean> testCompile = compileService.compile(compileInput.getUtPackagename(), compileInput.getUtCode());
            if(!testCompile._2){
                return new ServiceResponse<>(HttpStatus.BAD_REQUEST, ErrorCodes.COMPILATION_FAILED.getCode(), "Testing Code Compilation Failed\n"+testCompile._1 );
            }

            CompileTestResponse compileTestResponse = compileTestService.executeTest(compileInput);
            if(compileTestResponse.isSuccessful())
                return new ServiceResponse(compileTestResponse, HttpStatus.OK);

            return new ServiceResponse(compileTestResponse, HttpStatus.BAD_REQUEST);
           /* Tuple2<String,Boolean> codeCompile = compileService.compile(compileInput.getPackagename(), compileInput.getCode());
            Tuple2<String,Boolean> testCompile = compileService.compile(compileInput.getUtPackagename(), compileInput.getUtCode());
            String codeOut = codeCompile._1;
            String testOut = testCompile._1;

            if(!codeCompile._2){
                return new ServiceResponse<>("Code Compilation Failed\n"+codeCompile._1, HttpStatus.BAD_REQUEST);
            }
            if(!testCompile)
             = compileService.compile(compileTestInput.getCode())
            CompileTestResponse compileTestResponse = compileTestService.executeTest(compileTestInput);
            if(compileTestResponse.isSuccessful())
            return new ServiceResponse(compileTestResponse, HttpStatus.OK);*/
        } catch (Exception e) {
            e.printStackTrace();
            return new ServiceResponse<CompileTestResponse>(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCodes.EXECUTION_FAILED.getCode(), "Error running test cases : " + e.getCause());
        }

    }

}
