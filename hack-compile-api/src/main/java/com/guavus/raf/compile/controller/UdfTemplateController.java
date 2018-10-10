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
        String response = "package com.guavus.raf.functions.udf;\n\nimport org.apache.spark.sql.*;\nimport org.apache.spark.sql.types.*;\nimport com.guavus.raf.functions.udf.UDFCompute;\nimport java.util.HashMap;\nimport java.util.Map;\n\npublic class UdfComputeImpl implements UDFCompute {\n\n    @Override\n    public void initialise(HashMap<String, String> inputMap, HashMap<String, String> frameworkMap) {\n\n    }\n\n    @Override\n    public Dataset<Row> performCompute(Dataset<Row> dataset, HashMap<String, String> inputMap) {\n\n      // Add code here\n      return dataset;\n    }\n}";
        return new ServiceResponse<String>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/initUdfTest")
    public ServiceResponse<String> getStaticTestCodeTemplate() {
        String response = "package com.guavus.raf.functions.udf;\n\nimport com.google.common.collect.ImmutableList;\nimport com.guavus.raf.functions.udf.UDFComputeTest;\nimport org.apache.spark.sql.Dataset;\nimport org.apache.spark.sql.Row;\nimport org.apache.spark.sql.*;\nimport org.apache.spark.sql.types.*;\nimport scala.Tuple2;\n\nimport java.util.*;\n\npublic class UDFComputeTestImpl extends UDFComputeTest {\n    \\/* ==============================================================================================\n                        Use \"sparkSession\" variable for spark-related operations.\n       ==============================================================================================\n     */\n    \n    \n    @Override\n    public LinkedHashSet<String> testSet() {\n        \n        LinkedHashSet<String> testNameSet = new LinkedHashSet<>();\n        testNameSet.add(\"test1\");\n        \n        return testNameSet;\n        \n    }\n\n\n    public Tuple2<Dataset<Row>, HashMap<String, String>> inittest1() {\n        \n        Dataset<Row> data = null;\n        HashMap<String, String> inputMap = new HashMap<>();\n        \n        /*\n            ==============================================================================================\n                                                    SAMPLE TEST CASE INITIALIZATION \n            ==============================================================================================\n            \n        StructType schemata = DataTypes.createStructType(\n                new StructField[]{\n                        new StructField(\"NAME\", DataTypes.StringType, false, null),\n                        new StructField(\"STRING_VALUE\", DataTypes.StringType, false, null),\n                        new StructField(\"NUM_VALUE\", DataTypes.IntegerType, false, null),\n                });\n        Row r1 = RowFactory.create(\"name1\", \"value1\", 1);\n        Row r2 = RowFactory.create(\"name2\", \"value2\", 2);\n        List<Row> rowList = ImmutableList.of(r1, r2);\n        data = sparkSession.createDataFrame(rowList, schemata);\n\n        inputmap.put(\"param1\",\"param1Value\");\n         */\n        \n        return new Tuple2<>(data, inputMap);\n        \n    }\n\n\n    public boolean verifytest1(Dataset<Row> dataset, HashMap<String,String> inputMap){\n        \n        // Validate your output dataset here\n        return true;\n    }\n    \n    \n    \n    \n}\n";
        return new ServiceResponse<String>(response, HttpStatus.OK);
    }
}
