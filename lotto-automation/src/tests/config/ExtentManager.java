package tests.config;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class contains the initialization of the Extend report used for the different threads to log the results
 * @author  Jason Campos on 1/9/2020
 */
class ExtentManager {

    private static ExtentReports extentReports;
    static String folderPath = "reports/report_" + new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").
            format(new Date()) + "/";

    /**
     * Singleton report to concatenate the results in one file
     * @return ExtendedReports url of the report
     */
    public static synchronized ExtentReports getInstance() {
        if (extentReports == null) {
            folderPath = folderPath.replace(" ","_");
            File folder = new File(folderPath.replace(" ","_"));
            if (!folder.exists()) {
                System.out.println("File created " + folder);
                folder.mkdir();
            }
            extentReports = new ExtentReports();
            ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(folderPath + "Lotto24_Automation_Report.html");
            htmlReporter.config().setDocumentTitle("Lotto24 Automation Report");
            htmlReporter.config().setReportName("Lotto24 Automation Report");
            htmlReporter.config().setTheme(Theme.DARK);
            extentReports.attachReporter(htmlReporter);
        }
        return extentReports;
    }
}
