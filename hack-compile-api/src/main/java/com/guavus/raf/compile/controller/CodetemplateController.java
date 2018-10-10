package com.guavus.raf.compile.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hack/template")
public class CodetemplateController {


    @RequestMapping(value = "/initUdf")
    public String getStaticCodeTemplate() {
        return "package com.guavus.raf.functions.udf;\n\nimport org.apache.spark.sql.*;\nimport org.apache.spark.sql.types.*;\nimport com.guavus.raf.functions.udf.UDFCompute;\nimport java.util.HashMap;\nimport java.util.Map;\n\npublic class UdfComputeImpl implements UDFCompute {\n\n    @Override\n    public void initialise(HashMap<String, String> inputMap, Map<String, String> frameworkMap) {\n\n    }\n\n    @Override\n    public Dataset<Row> performCompute(Dataset<Row> dataset, HashMap<String, String> inputMap) {\n        return dataset.selectExpr(\"*\", \"5 as compute\", \"10 as compute1\");\n    }\n}";
    }
}
