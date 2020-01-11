package main.java.pageobjects.tesla;

import main.java.pageobjects.PageObjectBase;
import main.java.utilities.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Page object to handle the tesla page functions. This contains the allowed methods this page offers
 *
 * @author Jason Campos on 1/11/2020
 */

public class TeslaPage extends PageObjectBase {

    /**
     * Web elements
     */
    private static final By SELECT_YOUR_REGION_CLOSE_BUTTON = By.id("locale-modal-close");
    private static final By TESLA_LOGO_DIV = By.id("tesla-logo");
    private static final By CAROUSEL_DIV = By.cssSelector(".tds-animate_large--revealed > div.titles");
    private static final By CAR_MODEL_TITLE = By.cssSelector("h1.animate--slide_to_right");
    private static final By CAR_MODEL_DESCRIPTION = By.cssSelector("div.product--is_active .tds--hide_on_desk");

    public void makeResponsive() {
        Log.info("Attempt to resize the browser");
        actionBot.resizeBrowser();
    }

    public boolean isTeslaPageDisplayed() {
        Log.info("Attempt to verify if the tesla page is displayed");
        return actionBot.isElementDisplayed(TESLA_LOGO_DIV);
    }

    public void swipeCarModels() {
        Log.info("Attempt to swipe the car models to right");
        actionBot.swipeRight(CAROUSEL_DIV);
        Log.info("Attempt to swipe the car models to left");
        actionBot.swipeLeft(CAROUSEL_DIV);
        actionBot.swipeLeft(CAROUSEL_DIV);
    }

    public String getModelTitle() {
        Log.info("Attempt to get the car model title");
        return actionBot.getText(CAR_MODEL_TITLE);
    }

    public String getModelDescription() {
        Log.info("Attempt to get the car model description");
        return actionBot.getText(CAR_MODEL_DESCRIPTION);
    }

    public void closeModal() {
        Log.info("Attempt to close the select your region modal");
        actionBot.click(SELECT_YOUR_REGION_CLOSE_BUTTON);
        actionBot.waitUntil(ExpectedConditions.invisibilityOfElementLocated(SELECT_YOUR_REGION_CLOSE_BUTTON),
                SELECT_YOUR_REGION_CLOSE_BUTTON);
    }
}
