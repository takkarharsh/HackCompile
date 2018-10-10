package com.guavus.raf.compile.controller;

import com.guavus.raf.compile.model.CompileInput;
import com.guavus.raf.compile.model.ServiceResponse;
import com.guavus.raf.compile.service.CompileService;
import com.guavus.raf.compile.utils.Compile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import scala.Tuple2;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/hack")
@CrossOrigin
public class CompileController {

    @Autowired
    CompileService compileService;

    @RequestMapping(value = "/compile", method = RequestMethod.POST)
    public ServiceResponse<String> compile(@RequestBody CompileInput compileInput) {
        Tuple2<String,Boolean> response = null;
        try {
            response = compileService.compile(compileInput.getPackagename(), compileInput.getCode());
            if(!response._2){
                return new ServiceResponse<>(response._1, HttpStatus.BAD_REQUEST);
            }
            /*out = Compile.compile(compileInput.getPackagename(), compileInput.getCode());
            System.out.print(out);
            Pattern pattern = Pattern.compile("\\s\\d+.?(error)s*");
            Matcher matcher = pattern.matcher(out);
            if (matcher.find()) {
                return new ServiceResponse<String>(out, HttpStatus.BAD_REQUEST);
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (Objects.nonNull(response._2)) {
            return new ServiceResponse<>(response._1 + "\n COMPILATION SUCCESSFUL", HttpStatus.OK);
        } else {
            return new ServiceResponse<>("COMPILATION SUCCESSFUL", HttpStatus.OK);
        }
    }
//    @RequestMapping(value = "/test", method = RequestMethod.POST, consumes = TEXT_PLAIN_VALUE, produces = "application/json")
//    public CompileInput runTest(@RequestBody String val) {
//
//
//
//    }
//

//    @RequestMapping(value = "/escape", method = RequestMethod.POST, consumes = TEXT_PLAIN_VALUE, produces = "application/json")
//    public CompileInput getEscapedString(@RequestBody String val) {
//        CompileInput compileInput = new CompileInput();
//        compileInput.setCode(val);
//        compileInput.setName("com.guavus.hack.service.SparkComputeImpl");
//        Gson gson = new Gson();
////        return new ServiceResponse<String>(gson.toJson(compileInput),HttpStatus.OK);
//        return compileInput;
//    }

}
