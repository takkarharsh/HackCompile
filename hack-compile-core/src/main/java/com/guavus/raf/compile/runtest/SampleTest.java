package com.guavus.raf.compile.runtest;



import com.google.common.collect.ImmutableList;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import scala.Tuple2;

import java.util.*;

public class SampleTest extends UDFComputeTest {


    /* ==============================================================================================
                        Use "sparkSession" variable for spark-related operations.
       ==============================================================================================
     */

    @Override
    public LinkedHashSet<String> testSet() {
        LinkedHashSet<String> testNameSet = new LinkedHashSet<>();
        testNameSet.add("test1");
        return testNameSet;
    }


    public Tuple2<Dataset<Row>, HashMap<String, String>> inittest1() {
        System.out.println("in test");

        StructType schemata = DataTypes.createStructType(
                new StructField[]{
                        new StructField("NAME", DataTypes.StringType, false, null),
                        new StructField("STRING_VALUE", DataTypes.StringType, false, null),
                        new StructField("NUM_VALUE", DataTypes.IntegerType, false, null),
                });
        Row r1 = RowFactory.create("name1", "value1", 1);
        Row r2 = RowFactory.create("name2", "value2", 2);
        List<Row> rowList = ImmutableList.of(r1, r2);
        Dataset<Row> data = sparkSession.createDataFrame(rowList, schemata);

        HashMap<String, String> inputMap = new HashMap<>();

        System.out.println("returning from test");

        return new Tuple2<Dataset<Row>, HashMap<String, String>>(data, inputMap);
        /*Dataset<Row> dataset;
        List<String> values = new ArrayList<>();
        values.add("1");
        values.add("2");
        values.add("3");
        Row row = RowFactory.create(values.toArray());
        sparkSession.createDataFrame()*/
    }


    public boolean verifytest1(Dataset<Row> dataset, HashMap<String,String> inputMap){
        return true;
    }


}
