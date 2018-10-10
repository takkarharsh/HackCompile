package com.guavus.raf.functions.udf;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

import java.util.HashMap;

public class UDFComputeImpl implements UDFCompute {
    @Override
    public void initialise(HashMap<String, String> inputMap, HashMap<String, String> frameworkMap) {

    }

    @Override
    public Dataset<Row> performCompute(Dataset<Row> dataset, HashMap<String, String> inputMap) {
        return dataset;
    }
}
