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
        String response = "package com.guavus.raf.functions.udf;\n\nimport org.apache.spark.sql.*;\nimport org.apache.spark.sql.types.*;\nimport com.guavus.raf.functions.udf.UDFCompute;\nimport java.util.HashMap;\nimport java.util.Map;\n\npublic class UdfComputeImpl implements UDFCompute {\n\n    @Override\n    public void initialise(HashMap<String, String> inputMap, HashMap<String, String> frameworkMap) {\n\n    }\n\n    @Override\n    public Dataset<Row> performCompute(Dataset<Row> dataset, HashMap<String, String> inputMap) {\n\n    }\n}";
        return new ServiceResponse<String>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/initUdfTest")
    public ServiceResponse<String> getStaticTestCodeTemplate() {
        String response = "package com.guavus.raf.compile.runtest;\\r\\n\\r\\nimport java.util.*;\\r\\n\\r\\npublic class UDFComputeTestImpl extends UDFComputeTest {\\r\\n    \\/* ==============================================================================================\\r\\n                        Use \\\"sparkSession\\\" variable for spark-related operations.\\r\\n       ==============================================================================================\\r\\n     *\\/\\r\\n    @Override\\r\\n    public LinkedHashSet<String> testSet() {\\r\\n         return null;\\r\\n    }\\r\\n}\\r\\n";
        return new ServiceResponse<String>(response, HttpStatus.OK);
    }
}
