package main.java.utilities;

import org.apache.log4j.Logger;

public class Log {

    private static final Logger Log = Logger.getLogger(Log.class.getName());//

    public static void startTestCase(String testCaseName){
        Log.info("****************************************************************************************");
        Log.info("****************************************************************************************");
        Log.info("                                "+testCaseName+ "                                       ");
        Log.info("                                                                                        ");
    }

    //This is to print log for the ending of the tests case

    public static void endTestCase(String description){
        Log.info("XXXXXXXXXXXXXXXXXXXXXXX             "+"-END- "+ description +"             XXXXXXXXXXXXXXXXXXXXXX");
        Log.info("                                                                                  ");
    }

    public static void info(String message) {
        Log.info(message);
    }

    public static void warn(String message) {
        Log.warn(message);
    }

    public static void error(String message) {
        Log.error(message);
    }

    public static void fatal(String message) {
        Log.fatal(message);
    }

    public static void debug(String message) {
        Log.debug(message);
    }
}
