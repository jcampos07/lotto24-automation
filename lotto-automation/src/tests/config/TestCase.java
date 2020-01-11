package tests.config;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

public class TestCase {

    private static final TestCaseBase testCaseBase = new TestCaseBase();
    public static final DataSources dataSources = new DataSources();

    @BeforeSuite(alwaysRun = true)
    public void loadData() {
        dataSources.readDataSources();
    }

    /**
     * This method overrides the setUp method from TestCaseBase class
     *
     * @param browserName String browser name
     * @param url         String url to open
     * @param mode        String mode could be headless
     */
    @Parameters({"browserName", "url", "mode"})
    @BeforeMethod(alwaysRun = true)
    public void setUp(String browserName, String url, String mode) {
        testCaseBase.initSession(browserName, url, mode);
    }

    /**
     * This method overrides the quitBrowser method inherited from TestCaseBase class
     */
    @AfterMethod(alwaysRun = true)
    public void quitBrowser() {
        testCaseBase.quitBrowser();
    }
}
