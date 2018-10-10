package com.guavus.raf.compile.service;

import com.guavus.raf.compile.model.CompileTestResponse;
import com.guavus.raf.compile.model.TestStatus;
import com.guavus.raf.compile.runtest.UDFComputeTest;
import com.guavus.raf.functions.udf.UDFCompute;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.springframework.stereotype.Service;
import scala.Tuple2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Service
public class CustomTestRunner {

    public CompileTestResponse invokeTest(UDFCompute udfCompute, UDFComputeTest testObj) {
        Set<String> methodTest = testObj.testSet();
        boolean overallStatus = true;
        List<TestStatus> testStatuses = new ArrayList<>();

        for (String methodName : methodTest) {
            try {

                Method initMethod = getInitMethod(testObj, methodName);
                Method verifyMethod = getVerifyMethod(testObj, methodName);
                Tuple2<Dataset<Row>, HashMap<String, String>> initPair = invokeInitMethod(testObj, initMethod);
                Dataset<Row> inputDataset = initPair._1;
                HashMap<String, String> inputProps = initPair._2;
                Dataset<Row> outputDataset = computeUdf(udfCompute, inputDataset, inputProps);
                Boolean result = invokeVerifyMethod(testObj, verifyMethod, outputDataset, inputProps);
                if (result) {
                    testStatuses.add(new TestStatus(methodName, "SUCCESS"));
                } else {
                    overallStatus = false;
                    testStatuses.add(new TestStatus(methodName, "FAILED"));
                }
            } catch (Exception e) {
                overallStatus = false;
                e.printStackTrace();
                testStatuses.add(new TestStatus(methodName, ExceptionUtils.getStackTrace(e)));
            }
        }

        return new CompileTestResponse(testStatuses, overallStatus);
    }

    private Method getInitMethod(UDFComputeTest testObj, String methodSuffix) throws NoSuchMethodException {
        return testObj.getClass().getMethod("init" + methodSuffix, new Class[]{});
    }

    private Tuple2<Dataset<Row>, HashMap<String, String>> invokeInitMethod(UDFComputeTest testObj, Method method) throws InvocationTargetException, IllegalAccessException {
        return (Tuple2<Dataset<Row>, HashMap<String, String>>) method.invoke(testObj, new Object[]{});
    }

    private Boolean invokeVerifyMethod(UDFComputeTest testObj, Method method, Dataset<Row> rowDataset, HashMap<String, String> props) throws InvocationTargetException, IllegalAccessException {
        return (Boolean) method.invoke(testObj, rowDataset, props);
    }

    private Method getVerifyMethod(UDFComputeTest testObj, String methodSuffix) throws NoSuchMethodException {
        return testObj.getClass().getMethod("verify" + methodSuffix, Dataset.class, HashMap.class);
    }


    private Dataset<Row> computeUdf(UDFCompute
                                            udfCompute, Dataset<Row> dataset, HashMap<String, String> props) {
        udfCompute.initialise(props, new HashMap<String, String>());
        Dataset<Row> outputDataset = udfCompute.performCompute(dataset, props);
        return outputDataset;
    }
}
