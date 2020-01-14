package tests.tesla;

import main.java.steps.tesla.TeslaSteps;
import org.testng.annotations.Test;
import tests.config.TestCase;

public class TeslaTest extends TestCase {

    private static final int FIRST_CAR_MODEL_INFORMATION = 0;

    @Test(groups = {"tesla"}, description = "TC 02: Verify Carousel works on responsive")
    public void verifyPageIsResponsive() {
        TeslaSteps teslaSteps = new TeslaSteps();
        teslaSteps.makeThePageResponsive();
        teslaSteps.swipeCarModels();
        teslaSteps.verifyCarModelDisplayed(dataSources.getModelElementList().get(FIRST_CAR_MODEL_INFORMATION));
    }
}
