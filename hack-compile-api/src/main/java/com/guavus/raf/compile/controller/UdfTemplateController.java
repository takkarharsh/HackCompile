package com.guavus.raf.compile.controller;

import com.guavus.raf.compile.model.ServiceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/hack/template")
public class UdfTemplateController {


    @RequestMapping(value = "/initUdf")
    public ServiceResponse<String> getStaticCodeTemplate() {
        String response = "package com.guavus.raf.functions.udf;\n\nimport org.apache.spark.sql.*;\nimport org.apache.spark.sql.types.*;\nimport com.guavus.raf.functions.udf.UDFCompute;\nimport java.util.HashMap;\nimport java.util.Map;\n\npublic class UdfComputeImpl implements UDFCompute {\n\n    @Override\n    public void initialise(HashMap<String, String> inputMap, Map<String, String> frameworkMap) {\n\n    }\n\n    @Override\n    public Dataset<Row> performCompute(Dataset<Row> dataset, HashMap<String, String> inputMap) {\n\n    }\n}";
        return new ServiceResponse<String>(response, HttpStatus.OK);
    }
}
