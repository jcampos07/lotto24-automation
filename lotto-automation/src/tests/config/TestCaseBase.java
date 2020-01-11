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
 *
 * @author Jason Campos on 1/9/2020.
 */

public class TestCaseBase {

    protected static WebDriver driver;

    public enum Browsers {
        CHROME {
            @Override
            void startDriver(String mode) {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(setChromeOptions(mode));
            }
        },
        FIREFOX {
            @Override
            void startDriver(String mode) {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver(setFirefoxOptions(mode));
            }
        },
        IE {
            @Override
            void startDriver(String mode) {
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
            }
        };

        abstract void startDriver(String mode);
    }

    /**
     * This method returns the driver to be used.
     *
     * @return driver to be used in the proofs
     */
    public WebDriver getDriver() {
        return driver;
    }

    /**
     * this method configure the browser for the tests
     *
     * @param browserName the browser in which the tests will run
     * @param url         String with the url to open
     * @param mode        String mode to run the browser
     */
    void initSession(String browserName, String url, String mode) {
        setLocalDriver(browserName, mode);
        openURL(driver, url);
    }

    /**
     * this method is the in charge of quit the browser after the tests runs
     */
    void quitBrowser() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }

    private void setLocalDriver(String selectedBrowser, String mode) {
        for (Browsers browser : Browsers.values()) {
            if (selectedBrowser.equalsIgnoreCase(browser.name())) {
                browser.startDriver(mode);
            }
        }
    }

    private void openURL(WebDriver driver, String url) {
        driver.manage().window().maximize();
        driver.get(url);
    }

    private static ChromeOptions setChromeOptions(String mode) {
        ChromeOptions options = new ChromeOptions();
        if (mode.equalsIgnoreCase("headless")) {
            options.addArguments("headless");
        }
        options.addArguments("incognito");
        options.setCapability(ChromeOptions.CAPABILITY, options);
        return options;
    }

    private static FirefoxOptions setFirefoxOptions(String mode) {
        FirefoxOptions options = new FirefoxOptions();
        if (mode.equalsIgnoreCase("headless")) {
            options.addArguments("--headless");
        }
        return options;
    }
}
