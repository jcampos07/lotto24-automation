package main.java.steps.tesla;

import main.java.datastructures.ModelElement;
import main.java.pageobjects.tesla.TeslaPage;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

/**
 * This step class is a controller between the tests and page objects
 *
 * @author Jason Campos on 1/11/2020
 */

public class TeslaSteps {

    private final TeslaPage teslaPage = new TeslaPage();

    public void makeThePageResponsive() {
        Assert.assertTrue(teslaPage.isTeslaPageDisplayed(), "Tesla page was not displayed");
        teslaPage.makeResponsive();
        teslaPage.closeModal();
    }

    public void swipeCarModels() {
        teslaPage.swipeCarModels();
    }

    public void verifyCarModelDisplayed(ModelElement modelElement) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(teslaPage.getModelTitle(), modelElement.getModel(), "Model is not the expected.");
        softAssert.assertEquals(teslaPage.getModelDescription(), modelElement.getDescription(), "Model description is not the expected.");
        softAssert.assertAll();
    }
}
