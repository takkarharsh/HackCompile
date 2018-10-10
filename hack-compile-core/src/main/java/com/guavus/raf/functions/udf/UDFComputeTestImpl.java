//package com.guavus.raf.functions.udf;
//
//import com.google.common.collect.ImmutableList;
//import com.guavus.raf.functions.udf.UDFComputeTest;
//import org.apache.spark.sql.Dataset;
//import org.apache.spark.sql.Row;
//import org.apache.spark.sql.*;
//import org.apache.spark.sql.types.*;
//import scala.Tuple2;
//
//import java.util.*;
//
//public class UDFComputeTestImpl extends UDFComputeTest {
//    /* ==============================================================================================
//                        Use "sparkSession" variable for spark-related operations.
//       ==============================================================================================
//     */
//
//
//    @Override
//    public LinkedHashSet<String> testSet() {
//
//        LinkedHashSet<String> testNameSet = new LinkedHashSet<>();
//        testNameSet.add("test1");
//
//        return testNameSet;
//
//    }
//
//
//    public Tuple2<Dataset<Row>, HashMap<String, String>> inittest1() {
//
//        Dataset<Row> data = null;
//        HashMap<String, String> inputMap = new HashMap<>();
//
//        /*
//            ==============================================================================================
//                                                    SAMPLE TEST CASE INITIALIZATION
//            ==============================================================================================
//
//        StructType schemata = DataTypes.createStructType(
//                new StructField[]{
//                        new StructField("NAME", DataTypes.StringType, false, null),
//                        new StructField("STRING_VALUE", DataTypes.StringType, false, null),
//                        new StructField("NUM_VALUE", DataTypes.IntegerType, false, null),
//                });
//        Row r1 = RowFactory.create("name1", "value1", 1);
//        Row r2 = RowFactory.create("name2", "value2", 2);
//        List<Row> rowList = ImmutableList.of(r1, r2);
//        data = sparkSession.createDataFrame(rowList, schemata);
//
//        inputmap.put("param1","param1Value");
//         */
//
//        return new Tuple2<>(data, inputMap);
//
//    }
//
//
//    public boolean verifytest1(Dataset<Row> dataset, HashMap<String,String> inputMap){
//
//        // Validate your output dataset here
//        return true;
//    }
//
//
//
//
//}
