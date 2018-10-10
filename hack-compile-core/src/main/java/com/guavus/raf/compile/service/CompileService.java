package com.guavus.raf.compile.service;

import com.guavus.raf.compile.utils.Compile;
import org.springframework.stereotype.Service;
import scala.Tuple2;

@Service
public class CompileService {


    public Tuple2<String, Boolean> compile(String packagename,String code) {

        return Compile.compileOnly(packagename, code);
        /*Boolean success = true;
        String out = Compile.compileOnly(compileInput.getPackagename(), compileInput.getCode());
        Pattern pattern = Pattern.compileOnly("\\s\\d+.?(error)s*");
        Matcher matcher = pattern.matcher(out);
        if (matcher.find()) {
            success = false;
        }
        return new Tuple2<>(out,success);*/
    }
}
