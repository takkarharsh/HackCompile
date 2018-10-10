package com.guavus.raf.functions.udf;

import org.apache.spark.sql.SparkSession;

import java.util.Set;

public abstract class UDFComputeTest {

    SparkSession sparkSession;

    public void initialise(){
        sparkSession = SparkSession.builder().master("local").appName("SwatKats").getOrCreate();
    }

    abstract public Set<String> testSet();

}
