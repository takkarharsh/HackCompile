package com.guavus.raf.compile.controller;

import com.guavus.raf.compile.model.CompileInput;
import com.guavus.raf.compile.model.ServiceResponse;
import com.guavus.raf.compile.utils.Compile;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/hack")
public class CompileController {

    @RequestMapping(value = "/dummy", method = RequestMethod.GET)
    public String getDummyValue() {
        return new String("Dummy Response");
    }

    @RequestMapping(value = "/compile", method = RequestMethod.POST)
    public ServiceResponse<String> compile(@RequestBody CompileInput compileInput) {
        String out = null;
        try {
            out = Compile.compile(compileInput.getName(), compileInput.getCode());
            System.out.print(out);
            Pattern pattern = Pattern.compile("\\s\\d+.?(error)s*");
            Matcher matcher = pattern.matcher(out);
            if (matcher.find()) {
                return new ServiceResponse<String>(out, HttpStatus.SERVICE_UNAVAILABLE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (Objects.nonNull(out)) {
            return new ServiceResponse<String>(out + "\n COMPILATION SUCCESSFUL", HttpStatus.OK);
        } else {
            return new ServiceResponse<String>("COMPILATION SUCCESSFUL", HttpStatus.OK);
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
