package tests.tesla;

import main.java.steps.tesla.TeslaSteps;
import org.testng.annotations.Test;
import tests.config.TestCase;

public class TeslaTest extends TestCase {

    @Test(groups = {"tesla"}, description = "Verify the tesla page is responsive")
    public void verifyPageIsResponsive() {
        TeslaSteps teslaSteps = new TeslaSteps();
        teslaSteps.makeThePageResponsive();
        teslaSteps.swipeCarModels();
        teslaSteps.verifyCarModelDisplayed(dataSources.getModelElementList().get(0));
    }
}
