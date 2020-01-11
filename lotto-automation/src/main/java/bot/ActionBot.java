package main.java.bot;

import com.google.common.base.Function;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * This class contains all the methods that interact to the web driver, all the page objects call these methods
 * to perform actions like send keys, findElement, wait for an element, etc.
 * All the methods are encapsulated in order to be easier the maintenance.
 *
 * @author Jason Campos on 9/12/2017
 */

public class ActionBot {

    /**
     * Attributes
     */
    private final WebDriver driver;
    private final WebDriverWait webDriverWait;
    private final int TIME_TO_WAIT_IN_SECONDS = 20;

    /**
     * constructor
     *
     * @param driver instance of the web driver
     */
    public ActionBot(WebDriver driver) {
        this.driver = driver;
        this.webDriverWait = new WebDriverWait(this.driver, TIME_TO_WAIT_IN_SECONDS);
    }


    /**
     * this method types in any field with the data providers received in the method
     *
     * @param element web element to type the info
     * @param data    value to enter in the field
     */
    public void type(By element, String data) {
        find(element).clear();
        waitUntilBoolean(ExpectedConditions.textToBePresentInElementLocated(element, ""), element);
        find(element).sendKeys(data);
    }

    /**
     * Resize the page
     */
    public void resizeBrowser() {
        Dimension dimension = new Dimension(550, 650);
        //Resize current window to the set dimension
        driver.manage().window().setSize(dimension);
    }

    /**
     * This method drag and drop an slider (captcha)
     *
     * @param source from to drag and drop
     */
    public void swipeRight(final By source) {
        // Custom wait to see the how the image change from X to Y
        wait(2000);
        new Actions(driver).dragAndDropBy(find(source), -20, 0).build().perform();
    }

    public void swipeLeft(final By source) {
        // Custom wait to see the how the image change from X to Y
        wait(1000);
        new Actions(driver).dragAndDropBy(find(source), 20, 0).build().perform();
    }

    /**
     * this method find an element in the html
     *
     * @param element web element to type the info
     */
    private WebElement find(By element) {
        if (waitForElementVisible(element)) {
            return driver.findElement(element);
        }
        throw new RuntimeException(String.format("The web element with the following locator was not found: %s", element));
    }

    /**
     * this method find an element in the html
     *
     * @param element web element to type the info
     */
    public List<WebElement> findAll(By element) {
        if (waitForElementPresent(element)) {
            return driver.findElements(element);
        }
        throw new RuntimeException(String.format("The web elements with the following locator were not found: %s", element));
    }

    /**
     * This method clicks on any element in the html
     *
     * @param element webElement to click on
     */
    public void click(By element) {
        try {
            waitForPageToLoad();
            scrollDown(element);
            WebElement elementToClick = waitUntilElement(ExpectedConditions.elementToBeClickable(element), element);
            elementToClick.click();
            // Custom Wait to see the page is displayed, if we do not wait, the execution will not allow us to see what
            // it opened
            wait(1000);
        } catch (WebDriverException exception) {
            throw new RuntimeException(String.format("The element cannot be clickable: %s", element), exception);
        }
    }

    /**
     * This method clicks on any element in the html
     *
     * @param element webElement to click on
     */
    public void click(WebElement element) {
        try {
            waitForPageToLoad();
            WebElement elementToClick = waitUntilElement(ExpectedConditions.elementToBeClickable(element), element);
            elementToClick.click();
            // Custom Wait to see the page is displayed, if we do not wait, the execution will not allow us to see what
            // it opened
            wait(2000);
        } catch (WebDriverException exception) {
            throw new RuntimeException(String.format("The element cannot be clickable: %s", element), exception);
        }
    }

    /**
     * Verifies if a element is displayed
     *
     * @param element element to verify
     * @return boolean if the element is displayed or not
     */
    public boolean isElementDisplayed(By element) {
        return find(element).isDisplayed();
    }

    /**
     * This method calls the text from the element
     *
     * @param element to get the text
     * @return String info of the text field
     */
    public String getText(By element) {
        waitUntilElement(ExpectedConditions.visibilityOfElementLocated(element), element);
        return find(element).getText().trim();

    }


    /**
     * wait until the element is displayed if it is not found, an exception is throws.
     * @param expectedCondition expected condition to apply in the webdriver
     * @throws TimeoutException if the default time is reach, this exception is throws
     */
    public void waitUntil(ExpectedCondition<Boolean> expectedCondition, By element) {
        try {
            WebDriverWait webDriverWaitInstance = new WebDriverWait(this.driver, 20);
            webDriverWaitInstance.until(expectedCondition);
        } catch (TimeoutException exception) {
            throw new RuntimeException(String.format("The element is visible when it should be hidden %s ", element));
        }
    }

    /**
     * Wait until an element is present in the html
     *
     * @param element By element to wait until
     * @return boolean if the element is present
     */
    private boolean waitForElementPresent(final By element) {
        boolean isElementPresent = true;
        try {
            Wait<WebDriver> wait = new FluentWait<>(this.driver)
                    .withTimeout(Duration.ofSeconds(20))
                    .ignoring(NoSuchElementException.class);
            wait.until((Function<WebDriver, WebElement>) d -> {
                Objects.requireNonNull(d).manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
                return d.findElement(element);
            });
        } catch (TimeoutException te) {
            isElementPresent = false;
        }
        return isElementPresent;
    }

    /**
     * This method wait that the element is present in order to perform the following action
     *
     * @param element element to wait
     * @return boolean
     */
    private boolean waitForElementVisible(final By element) {
        boolean isElementPresent = true;
        try {
            WebDriverWait wait = new WebDriverWait(driver, 20);
            wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        } catch (TimeoutException te) {
            System.out.println("I entered in visible timeout");
            isElementPresent = false;
        }
        return isElementPresent;
    }

    /**
     * This method waits for X amount of milliseconds for continuing the next instruction
     *
     * @param timeToWaitInMilliseconds overall of milliseconds to wait.
     */
    private void wait(int timeToWaitInMilliseconds) {
        long startTime = System.currentTimeMillis();
        while ((System.currentTimeMillis() - startTime) < timeToWaitInMilliseconds) {
        }
    }

    /**
     * this method scroll down the element we specified
     *
     * @param element element to scroll down
     */
    private void scrollDown(By element) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView(false)", find(element));
    }

    /**
     * wait until the element is displayed.
     *
     * @param expectedCondition expected condition to apply in the web driver
     * @return boolean if the element was found
     */
    private WebElement waitUntilElement(ExpectedCondition<WebElement> expectedCondition, By element) {
        try {
            return getWaitDriver().until(expectedCondition);
        } catch (TimeoutException exception) {
            throw new RuntimeException(String.format("The element is not visible in the expected time it: %s", element));
        }
    }

    /**
     * wait until the element is displayed.
     *
     * @param expectedCondition expected condition to apply in the web driver
     * @return boolean if the element was found
     */
    private WebElement waitUntilElement(ExpectedCondition<WebElement> expectedCondition, WebElement element) {
        try {
            return getWaitDriver().until(expectedCondition);
        } catch (TimeoutException exception) {
            throw new RuntimeException(String.format("The element is not visible in the expected time before clicking " +
                    "it: %s", element));
        }
    }

    /**
     * wait until the element is displayed if it is not found, an exception is throws.
     *
     * @param expectedCondition expected condition to apply in the web driver
     * @throws TimeoutException if the default time is reach, this exception is throws
     */
    private void waitUntilBoolean(ExpectedCondition<Boolean> expectedCondition, final By element) {
        try {
            getWaitDriver().until(expectedCondition);
        } catch (TimeoutException exception) {
            throw new RuntimeException(String.format("The wait until returned false instead of true for the element:" +
                    " %s", element));
        }
    }

    /**
     * This method wait until the page is loaded
     */
    private void waitForPageToLoad() {
        driver.manage().timeouts().pageLoadTimeout(TIME_TO_WAIT_IN_SECONDS, TimeUnit.SECONDS);
    }

    /**
     * @return the web driver wait instance
     */
    private WebDriverWait getWaitDriver() {
        return this.webDriverWait;
    }
}
