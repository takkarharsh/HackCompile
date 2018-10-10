package com.guavus.raf.functions.udf;



import com.google.common.collect.ImmutableList;
import com.guavus.raf.functions.udf.UDFComputeTest;
import org.apache.hadoop.util.hash.Hash;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
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
       /* System.out.println("in test");

        StructType schemata = DataTypes.createStructType(
                new StructField[]{
                        new StructField("NAME", DataTypes.StringType, false, null),
                        new StructField("STRING_VALUE", DataTypes.StringType, false, null),
                        new StructField("NUM_VALUE", DataTypes.IntegerType, false, null),
                });
        Row r1 = RowFactory.create("name1", "value1", 1);
        Row r2 = RowFactory.create("name2", "value2", 2);
        List<Row> rowList = ImmutableList.of(r1, r2);
        Dataset<Row> data = sparkSession.createDataFrame(rowList, schemata).toDF();

        HashMap<String, String> inputMap = new HashMap<>();

        System.out.println("returning from test");

        return new Tuple2<Dataset<Row>, HashMap<String, String>>(data, inputMap);*/
        /*Dataset<Row> dataset;
        List<String> values = new ArrayList<>();
        values.add("1");
        values.add("2");
        values.add("3");
        Row row = RowFactory.create(values.toArray());
        sparkSession.createDataFrame()*/

//        SparkSession spark = SparkSession.builder().appName("Build a DataFrame from Scratch").master("local[*]")
//                .getOrCreate();

        List<String> stringAsList = new ArrayList<>();
        stringAsList.add("bar");
        stringAsList.add("bar1");
        stringAsList.add("bar2");

        JavaSparkContext sparkContext = new JavaSparkContext(sparkSession.sparkContext());

        JavaRDD<Row> rowRDD = sparkContext.parallelize(stringAsList).map((String row) -> RowFactory.create(row));

        // Creates schema
        StructType schema = DataTypes.createStructType(
                new StructField[] { DataTypes.createStructField("foe", DataTypes.StringType, false) });

        Dataset<Row> df = sparkSession.sqlContext().createDataFrame(rowRDD, schema).toDF();
        System.out.println("here now==================");
        HashMap<String, String> inputMap = new HashMap<>();
        return new Tuple2<>(df, inputMap);

    }


    public boolean verifytest1(Dataset<Row> dataset, HashMap<String,String> inputMap){
        dataset.show();
        return true;
    }


    public static void main(String[] args) {
        SampleTest t = new SampleTest();
        UDFComputeImpl udf = new UDFComputeImpl();
        t.initialise();
//        t.inittest1();
        Tuple2<Dataset<Row>, HashMap<String,String>> pair = t.inittest1();
        Dataset<Row> out = udf.performCompute(pair._1, pair._2);
        t.verifytest1(out, null);

    }

}
