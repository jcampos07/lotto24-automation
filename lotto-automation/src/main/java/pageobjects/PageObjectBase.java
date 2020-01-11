package main.java.pageobjects;

import main.java.bot.ActionBot;
import org.openqa.selenium.WebDriver;
import tests.config.TestCaseBase;

/**
 * Parent page object for the other pages, it will contain the initialization of the web driver and action bot class
 * to interact with the selenium api methods
 * @author Jason Campos on 1/9/2020
 */
public class PageObjectBase {

    protected final ActionBot actionBot;

    protected PageObjectBase(){
        TestCaseBase testCaseBase = new TestCaseBase();
        WebDriver driver = testCaseBase.getDriver();
        actionBot = new ActionBot(driver);
    }
}
