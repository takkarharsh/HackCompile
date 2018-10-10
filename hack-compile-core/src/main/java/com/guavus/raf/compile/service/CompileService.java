package com.guavus.raf.compile.service;

import com.guavus.raf.compile.model.CompileInput;
import com.guavus.raf.compile.utils.Compile;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import scala.Tuple2;
import spire.algebra.Bool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CompileService {


    public Tuple2<String, Boolean> compile(String code, String packagename) {

        return Compile.compile(packagename, code);
        /*Boolean success = true;
        String out = Compile.compile(compileInput.getPackagename(), compileInput.getCode());
        Pattern pattern = Pattern.compile("\\s\\d+.?(error)s*");
        Matcher matcher = pattern.matcher(out);
        if (matcher.find()) {
            success = false;
        }
        return new Tuple2<>(out,success);*/
    }
}
