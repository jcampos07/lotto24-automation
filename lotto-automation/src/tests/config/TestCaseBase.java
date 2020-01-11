package tests.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;

/**
 * This Class set up the browser in order to execute the tests.
 * @author Jason Campos on 1/9/2020.
 */

public class TestCaseBase{
    private static WebDriver driver;

    public enum Browsers {
        CHROME {
            @Override
            void startDriver() {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                setChromeOptions();
            }
        },
        FIREFOX {
            @Override
            void startDriver() {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                setFirefoxOptions();
            }
        },
        IE {
            @Override
            void startDriver() {
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
            }
        };
        abstract void startDriver();
    }

    /**
     * This method returns the driver to be used.
     * @return driver to be used in the proofs
     */
    public WebDriver getDriver() {
        return driver;
    }

    /**
     * this method configure the browser for the tests
     * @param browserName the browser in which the tests will run
     */
    void initSession(String browserName, String url) {
        setLocalDriver(browserName);
        openURL(driver, url);
    }

    /**
     *this method is the in charge of quit the browser after the tests runs
     */
    void quitBrowser() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }

    private void setLocalDriver(String selectedBrowser) {
        for (Browsers browser: Browsers.values()) {
            if (selectedBrowser.equalsIgnoreCase(browser.name())) {
                browser.startDriver();
            }
        }
    }

    private void openURL(WebDriver driver, String url ) {
        driver.manage().window().maximize();
        driver.get(url);
    }

    private static void setChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--incognito");
        options.setCapability(ChromeOptions.CAPABILITY, options);
    }

    private static void setFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless");
    }
}
