package tests.config;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import java.util.HashMap;
import java.util.Map;

/**
 * This class contains the methods synchronized used for the threads in order to start a tests and log a result
 * @author Jason Campos on 6/11/2018
 */

class ExtentTestManager {

    private static final Map extentTestMap = new HashMap();
    private static final ExtentReports extent = ExtentManager.getInstance();

    /**
     * Gets the tests instance in order to log info
     * @return ExtendedTest instance synchronized
     */
    public static synchronized ExtentTest getTest() {
        return (ExtentTest)extentTestMap.get((int) (long) (Thread.currentThread().getId()));
    }

    /**
     * Starts a tests in synchronized way
     * @param testName Test name to use.
     * @param desc String description to add to the tests
     */
    public static synchronized void startTest(String testName, String desc) {
        ExtentTest test = extent.createTest(testName, desc);
        extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
    }
}
