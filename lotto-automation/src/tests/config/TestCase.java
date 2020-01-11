package tests.config;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class TestCase {

    private static final TestCaseBase testCaseBase = new TestCaseBase();
    protected static final DataSources dataSources = new DataSources();
    /**
     * This method overrides the setUp method from TestCaseBase class
     * @param browserName String browser name
     * @param url String url to open
     */
    @Parameters({"browserName", "url"})
    @BeforeMethod(alwaysRun = true)
    public void setUp(String browserName, String url) {
        testCaseBase.initSession(browserName, url);
        dataSources.readDataSources();
    }

    /**
     * This method overrides the quitBrowser method inherited from TestCaseBase class
     */
    @AfterMethod(alwaysRun = true)
    public void quitBrowser() {
        testCaseBase.quitBrowser();
    }
}
