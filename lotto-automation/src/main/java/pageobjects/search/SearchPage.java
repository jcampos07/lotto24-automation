package main.java.pageobjects.search;

import main.java.pageobjects.PageObjectBase;
import main.java.pageobjects.results.ResultsPage;
import main.java.utilities.Log;
import org.openqa.selenium.By;

/**
 * Search page that contains the functions this page object offers
 * @author Jason Campos on 1/11/2020
 */

public class SearchPage extends PageObjectBase {

    /**
     * Web Elements
     */
    private static final By SEARCH_FIELD = By.id("searchInput");
    private static final By SEARCH_BUTTON = By.cssSelector("button.pure-button");

    public String getPageTitle() {
        Log.info("Attempt to validate Site title is displayed");
       return driver.getTitle();
    }

    public ResultsPage searchInformation(String informationToSearch) {
        Log.info("Attempt to enter the search criteria in search field");
        actionBot.type(SEARCH_FIELD, informationToSearch);
        Log.info("Attempt to submit");
        actionBot.click(SEARCH_BUTTON);
        return new ResultsPage();
    }


}
