package main.java.bot;

import com.google.common.base.Function;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 *  This class contains all the methods that interact to the web driver, all the page objects call these methods
 *  to perform actions like send keys, findElement, wait for an element, etc.
 *  All the methods are encapsulated in order to be easier the maintenance.
 *  @author Jason Campos on 9/12/2017
 */

public class ActionBot {

    /**
     * Attributes
     */
    private final WebDriver driver;
    private final WebDriverWait webDriverWait;
    private final int TIME_TO_WAIT_IN_SECONDS = 60;
    private final int DEFAULT_TIME_TO_WAIT_IN_SECONDS = 45;

    /**
     * constructor
     * @param driver instance of the web driver
     */
    public ActionBot(WebDriver driver){
        this.driver = driver;
        this.webDriverWait = new WebDriverWait(this.driver, TIME_TO_WAIT_IN_SECONDS);
    }


    /**
     * this method types in any field with the data providers received in the method
     * @param element web element to type the info
     * @param data value to enter in the field
     */
    public void type(By element, String data) {
        find(element).clear();
        wait(1000);
        waitUntilBoolean(ExpectedConditions.textToBePresentInElementLocated(element, ""), element);
        find(element).sendKeys(data);
        wait(1000);
    }

    /**
     * this method find an element in the html
     * @param element web element to type the info
     */
    private WebElement find(By element) {
        if(waitForElementVisible(element)) {
            return driver.findElement(element);
        }
        throw new RuntimeException(String.format("The web element with the following locator was not found: %s", element));
    }

    /**
     * this method find an element in the html
     * @param element web element to type the info
     */
    public List<WebElement> findAll(By element) {
        if(waitForElementPresent(element,TIME_TO_WAIT_IN_SECONDS)) {
            return driver.findElements(element);
        }
        throw new RuntimeException(String.format("The web elements with the following locator were not found: %s" ,element));
    }

    /**
     * This method clicks on any element in the html
     * @param element webElement to click on
     */
    public void click(By element) {
        try {
            waitForPageToLoad();
            scrollDown(element);
            WebElement elementToClick = waitUntilElement(ExpectedConditions.visibilityOfElementLocated(element), element);
            elementToClick.click();
            wait(1000);
        }catch (WebDriverException exception) {
            throw new RuntimeException(String.format("The element cannot be clickable: %s", element), exception);
        }
    }

    /**
     * This method clicks on any element in the html
     * @param element webElement to click on
     */
    public void click(WebElement element) {
        try {
            waitForPageToLoad();
            WebElement elementToClick = waitUntilElement(ExpectedConditions.elementToBeClickable(element), element);
            elementToClick.click();
            wait(1000);
        }catch (WebDriverException exception) {
            throw new RuntimeException(String.format("The element cannot be clickable: %s", element), exception);
        }
    }

    /**
     * This method clicks on all the element in the html
     * @param element webElement to click on
     */
    public void clickAllElements(By element) {
        try {
            waitForPageToLoad();
            scrollDown(element);
            waitUntilElement(ExpectedConditions.visibilityOfElementLocated(element), element);
            List<WebElement> elements = findAll(element);
            for (WebElement elementToSelect: elements) {
                elementToSelect.click();
            }
            wait(1000);
        }catch (WebDriverException exception) {
            throw new RuntimeException(String.format("The element cannot be clickable: %s", element), exception);
        }
    }

    /**
     * This method clicks on any element in the html
     * @param element webElement to click on
     */
    public void clickJs(By element) {
        try {
            waitForPageToLoad();
            WebElement elementToClick = waitUntilElement(ExpectedConditions.visibilityOfElementLocated(element), element);
            JavascriptExecutor executor = (JavascriptExecutor)driver;
            executor.executeScript("arguments[0].click();", elementToClick);
        }catch (WebDriverException exception) {
            throw new RuntimeException(String.format("The element cannot be clickable: %s", element), exception);
        }
    }

    /**
     * This method clicks on any element in the html
     * @param element webElement to click on
     */
    public void clickJs(WebElement element) {
        try {
            waitForPageToLoad();
            WebElement elementToClick = waitUntilElement(ExpectedConditions.elementToBeClickable(element), element);
            JavascriptExecutor executor = (JavascriptExecutor)driver;
            executor.executeScript("arguments[0].click();", elementToClick);
        }catch (WebDriverException exception) {
            throw new RuntimeException(String.format("The element cannot be clickable: %s", element), exception);
        }
    }

    /**
     * This method clicks on any element in the html
     * @param element webElement to click on
     */
    public void clickRadioButton(WebElement element) {
        try {
            waitForPageToLoad();
            element.click();
            wait(1000);
        }catch (WebDriverException exception) {
            throw new RuntimeException(String.format("The element cannot be clickable: %s", element), exception);
        }
    }

    /**
     * Checks the option in a checkbox, it verifies whether the element is checked or not before click on it.
     * @param shouldOptionBeChecked String with a flag that indicated whether the checkbox should be checked
     * @param element WebElement to check
     */
    public void checkOptionCheckBox(String shouldOptionBeChecked, By element) {
        if (shouldOptionBeChecked.equalsIgnoreCase("Yes")) {
            if (!isOptionSelected(element)) {
                click(element);
            }
        } else {
            if (isOptionSelected(element)) {
                click(element);
            }
        }
    }

    /**
     * Verifies if a element is displayed
     * @param element element to verify
     * @return boolean if the element is displayed or not
     */
    public boolean isElementDisplayed(By element) {
        return find(element).isDisplayed();
    }

    /**
     * Verifies if a element is selected, it can be used by checkboxes
     * @param element element to verify
     * @return boolean if the element is selected or not
     */
    public boolean isOptionSelected(By element) {
        return find(element).isSelected();
    }

    /**
     * Verifies if a element is selected, it can be used by checkboxes
     * @param element element to verify
     * @return boolean if the element is selected or not
     */
    public boolean isOptionSelected(WebElement element) {
        return element.isSelected();
    }

    /**
     * This method wait for the element in order to perform the following action
     * @param element element to wait
     *  @return boolean
     */

    public boolean waitForElementPresent(final By element,int secondsToWait) {
        boolean isElementPresent = true;
        try {
            Wait<WebDriver> wait = new FluentWait<>(this.driver)
                    .withTimeout(Duration.ofSeconds(secondsToWait))
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
     * @param element element to wait
     *  @return boolean
     */
    public boolean waitForElementVisible(final By element) {
        boolean isElementPresent = true;
        try {
            WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIME_TO_WAIT_IN_SECONDS);
            wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        } catch (TimeoutException te) {
            System.out.println("I entered in visible timeout");
            isElementPresent = false;
        }
        return isElementPresent;
    }

    /**
     * This method wait that the element is present in order to perform the following action
     * @param element element to wait
     *  @return boolean
     */
    public boolean waitForElementVisible(final By element, int time) {
        boolean isElementPresent = true;
        try {
            WebDriverWait wait = new WebDriverWait(driver, time);
            wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        } catch (TimeoutException te) {
            System.out.println("I entered in visible timeout");
            isElementPresent = false;
        }
        return isElementPresent;
    }

    /**
     * This method wait that the element is present in order to perform the following action
     * @param element element to wait
     *  @return boolean
     */
    public boolean waitForElementVisible(WebElement element) {
        boolean isElementPresent = true;
        try {
            WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIME_TO_WAIT_IN_SECONDS);
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException te) {
            System.out.println("I entered in visible timeout");
            isElementPresent = false;
        }
        return isElementPresent;
    }

    /**
     * This method wait that the element is not visible in order to perform the following action
     * @param element element to wait
     *  @return boolean
     */
    public boolean waitForElementInVisible(final By element) {
        boolean isElementPresent = true;
        try {
            WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIME_TO_WAIT_IN_SECONDS);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
        } catch (TimeoutException te) {
            System.out.println("I enter in invisible timeout");
            isElementPresent = false;
        }
        return isElementPresent;
    }

    /**
     * This method waits for X amount of milliseconds for continuing the next instruction
     * @param timeToWaitInMilliseconds overall of milliseconds to wait.
     */
    public void wait(int timeToWaitInMilliseconds) {
        long startTime = System.currentTimeMillis();
        while((System.currentTimeMillis()-startTime)<timeToWaitInMilliseconds)
        {
        }
    }

    /**
     * This method drag and drop an slider (captcha)
     * @param source from to drag and drop
     *                 @param destination destination element
     */
    public void dragAndDropFromElementAtoB(final By source, final By destination) {
            System.out.println("Source : " + source);
            System.out.println("Destination: " + destination);
        scrollDownAlignToTopVisibleArea(By.className("tab-content"));
        scrollDownAlignToTopVisibleArea(source);
        wait(2000);
            new Actions(driver).dragAndDrop(find(source), find(destination)).build().perform();
    }

    /**
     * This method calls the text from the element
     * @param element to get the text
     * @return String info of the text field
     */
    public String getText(By element) {
        String infoInTextField = "";
        if(waitForElementVisible(element)) {
            infoInTextField = find(element).getText().replaceAll("\\u00A0", " ").trim();
        }
        return infoInTextField;
    }

    /**
     * This method calls the text from the element
     * @param element to get the text
     * @return String info of the text field
     */
    public String getText(WebElement element) {
        return element.getText().trim();
    }

    /**
     * get texts from several elements and concatenate them in one string
     * @param element locator to find
     * @return String with all the texts concatenated
     */
    public String getConcatenatedTextForSeveralElements(By element) {
        try {
            List<WebElement> elements = null;
            if (waitForElementVisible(element)) {
                elements = findAll(element);
            }
            StringBuilder texts = new StringBuilder();
            for (WebElement webElement : Objects.requireNonNull(elements)) {
                texts.append(webElement.getText().trim().replace("\n", " ").replaceAll("\\s+", " ").replaceAll("\\u00A0", ""));
                texts.append(" ");
            }
            return texts.toString().trim();
        }catch (NullPointerException exception) {
            throw new RuntimeException(String.format("There was not found any element in the " +
                    "getConcatenatedTextForSeveralElements method with the following locator: %s, %s", element, exception));
        }
    }

    /**
     * this method scroll down the browser in order to see the elements
     * @param pixelsToMoveInX pixels to move in X
     * @param pixelsToMoveInY pixels to move in Y
     */
    public void scrollDown(int pixelsToMoveInX, int pixelsToMoveInY, By element) {
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        WebElement el = find(element);
        jse.executeScript("window.scrollBy("+ pixelsToMoveInX +"," + pixelsToMoveInY +")", el);
    }

    /**
     * this method scroll down the element we specified
     * @param element element to scroll down
     */
    public void scrollDown(By element) {
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].scrollIntoView(false)", find(element));
    }

    /**
     * this method scroll down the element we specified
     * @param element element to scroll down
     */
    public void scrollDownAlignToTopVisibleArea(By element) {
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].scrollIntoView(true)", find(element));
    }

    /**
     * Get the tabs opened in order to interact with them
     * @return Array list with the opened tabs
     */
    public ArrayList<String> switchToWindow() {
        wait(5000);
        try {
            ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(tabs.size() - 1));
            return tabs;
        }catch (IndexOutOfBoundsException exception) {
            throw new RuntimeException(String.format("New tab was not opened. %s" , exception));
        }
    }

    /**
     * Switch to default content(main page of the site)
     */
    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    /**
     * Get the tabs opened in order to interact with them
     */
    public void switchToWindow(int tabToSwitch) {
        wait(5000);
        try {
            driver.switchTo().window(switchToWindow().get(tabToSwitch));
        }catch (IndexOutOfBoundsException exception) {
            throw new RuntimeException(String.format("New tab was not opened. %s" , exception));
        }
    }

    /**
     * Go back to main page
     */
    public void goBackToMainPage() {
        driver.switchTo().defaultContent();
    }

    /**
     * Gets text from a popup windows
     * @return String
     */
    public String getTextFromPopup() {
            waitUntil(ExpectedConditions.alertIsPresent());
            return driver.switchTo().alert().getText().trim();
    }

    /**
     * Accept the popup
     */
    public void acceptPopup() {
            waitUntil(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();
    }

    /**
     * wait until the element is displayed if it is not found, an exception is throws.
     * @param expectedCondition expected condition to apply in the web driver
     * @throws TimeoutException if the default time is reach, this exception is throws
     */
    public void waitUntil(ExpectedCondition<WebElement> expectedCondition, final By element) {
        try {
            getWaitDriver().until(expectedCondition);
        } catch (TimeoutException exception) {
            throw new RuntimeException(String.format("The wait until method has not found an element in the default time," +
                    " the expected element was: %s",element));
        }
    }

    /**
     * wait until the element is displayed if it is not found, an exception is throws.
     * @param expectedCondition expected condition to apply in the web driver
     * @throws TimeoutException if the default time is reach, this exception is throws
     */
    public void waitUntil(ExpectedCondition<Alert> expectedCondition) {
        try {
            getWaitDriver().until(expectedCondition);
        } catch (TimeoutException exception) {
            throw new RuntimeException("Popup was not displayed");
        }
    }

    /**
     * wait until the element is displayed.
     * @param expectedCondition expected condition to apply in the web driver
     * @return boolean if the element was found
     */
    public WebElement waitUntilElement(ExpectedCondition<WebElement> expectedCondition, By element) {
        try {
            return getWaitDriver().until(expectedCondition);
        } catch (TimeoutException exception) {
            throw new RuntimeException(String.format("The element is not visible in the expected time before clicking " +
                    "it: %s", element));
        }
    }

    /**
     * wait until the element is displayed.
     * @param expectedCondition expected condition to apply in the web driver
     * @return boolean if the element was found
     */
    public WebElement waitUntilElement(ExpectedCondition<WebElement> expectedCondition, WebElement element) {
        try {
            return getWaitDriver().until(expectedCondition);
        } catch (TimeoutException exception) {
            throw new RuntimeException(String.format("The element is not visible in the expected time before clicking " +
                    "it: %s", element));
        }
    }

    /**
     * wait until the element is displayed if it is not found, an exception is throws.
     * @param expectedCondition expected condition to apply in the web driver
     * @throws TimeoutException if the default time is reach, this exception is throws
     */
    public void waitUntilBoolean(ExpectedCondition<Boolean> expectedCondition, final By element) {
        try {
            getWaitDriver().until(expectedCondition);
        } catch (TimeoutException exception) {
            throw new RuntimeException(String.format("The wait until returned false instead of true for the element:" +
                    " %s",element));
        }
    }

    /**
     *  This method selects a dropdown option depending on the value.
     * @param webElement dropdown to select the option
     * @param value value to select in the dropdown
     */
    public void selectDropDownOptionByValue(final By webElement, String value) {
        Select dropdown= new Select(find(webElement));
        dropdown.selectByValue(value);
    }

    /**
     *  This method selects a dropdown option depending on the text.
     * @param webElement dropdown to select the option
     * @param value value to select in the dropdown
     */
    public void selectDropDownOptionByText(final By webElement, String value) {
        Select dropdown = new Select(find(webElement));
        dropdown.selectByVisibleText(value);
    }

    /** @param element to get the text
     *  @param attribute, is the attribute to find out in the html
     * @return String info of the text field
     */
    public String getAttribute(By element, String attribute){
        return  find(element).getAttribute(attribute);
    }

    /** @param element to get the text
     *  @param attribute, is the attribute to find out in the html
     * @return String info of the text field
     */
    public String getAttribute(WebElement element, String attribute){
        return  element.getAttribute(attribute);
    }

    /**
     * Opens the url we need to interact with
     * @param url string with the url to open
     */
    public void openUrl(String url) {
        if (!StringUtils.isEmpty(url)) {
            driver.get(url);
        } else {
            throw new RuntimeException("The url could not be an empty value");
        }
    }

    /**
     * Gets the selected dropdown from the element it gets as parameter
     * @param element By element to select dropdown
     * @return String with the text of the dropdown
     */
    public String getSelectedOptionDropdown(By element) {
        Select select = new Select(find(element));
        WebElement option = select.getFirstSelectedOption();
        return getText(option);
    }

    /**
     * Gets the current url
     * @return String with the current url
     */
    public String getPageSource() {
        return driver.getPageSource();
    }

    /**
     * Gets the current url
     * @return String with the current url
     */
    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }

    /**
     * Refreshes the page
     */
    public void refreshPage() {
        driver.navigate().refresh();
    }

    /**
     * This method wait until the page is loaded
     */
    private void waitForPageToLoad() {
        driver.manage().timeouts().pageLoadTimeout(TIME_TO_WAIT_IN_SECONDS, TimeUnit.SECONDS);
    }

    /**
     *
     * @return the web driver wait instance
     */
    private WebDriverWait getWaitDriver() {
        return this.webDriverWait;
    }
}
