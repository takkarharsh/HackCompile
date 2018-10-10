package com.guavus.raf.compile.controller;

import com.guavus.raf.compile.model.CompileTestInput;
import com.guavus.raf.compile.model.CompileTestResponse;
import com.guavus.raf.compile.model.ServiceResponse;
import com.guavus.raf.compile.service.CompileTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/hack/test")
public class TestUdfController {

    @Autowired
    CompileTestService compileTestService;

    @Autowired
    CompileController compileController;


    @RequestMapping(value = "/execute", method = RequestMethod.POST)
    public ServiceResponse<CompileTestResponse> compile(@RequestBody CompileTestInput compileTestInput) {
        try {
            String compileCodeOut = compileController.compile(compileTestInput.getCode()).;
            String compileCodeOut = compileController.compile(compileTestInput.getCode());
            List<CompileTestResponse> compileTestResponseList = compileTestService.executeTest(compileTestInput);
            return new ServiceResponse(compileTestResponseList, HttpStatus.OK);
        } catch (Exception e) {
            return new ServiceResponse<CompileTestResponse>(HttpStatus.INTERNAL_SERVER_ERROR, "Error running test cases : " + e.getCause());
        }

    }

}
