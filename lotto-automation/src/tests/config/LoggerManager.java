package tests.config;

import main.java.utilities.Log;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * This class handles the log4j in order to get a log with custom messages when the test runs
 * @author Jason Campos on 1/9/2020
 */
class LoggerManager {

    private static final String CONFIG_LOCATION = "src/main/resources/log4j.xml";

    public void initLogs(String testName){
        DOMConfigurator.configure(CONFIG_LOCATION);
        Log.startTestCase(testName);
    }

    public void endLogs(String description) {
        Log.endTestCase(description);
    }
}
