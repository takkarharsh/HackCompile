package com.guavus.raf.runtest;

import com.google.common.collect.ImmutableList;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

abstract class CustomTest {

    SparkSession sparkSession;

    public void initialise(){
        sparkSession = SparkSession.builder().appName("SwatKats").getOrCreate();
    }

    abstract public Set<String> testSet();

}
