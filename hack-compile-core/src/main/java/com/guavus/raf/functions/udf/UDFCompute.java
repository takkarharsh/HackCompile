package com.guavus.raf.functions.udf;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public interface UDFCompute extends Serializable {

    void initialise(HashMap<String, String> inputMap,Map<String, String> frameworkMap);
    Dataset<Row> performCompute(Dataset<Row> dataset,HashMap<String, String> inputMap);
}