package main.java.pageobjects.results;

import main.java.pageobjects.PageObjectBase;
import main.java.pageobjects.article.ArticlePage;
import main.java.utilities.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Page object for results page with the function this page offers
 * @author Jason Campos on 1/11/2020
 */

public class ResultsPage extends PageObjectBase {

    /**
     * Web elements
     */
    private static final By DID_YOU_MEAN_LINK = By.id("mw-search-DYM-suggestion");
    private static final By SEARCH_RESULTS_LIST = By.cssSelector(".mw-search-result-heading > a");

    public boolean isSuggestionDisplayed() {
        Log.info("Attempt to get if Did you mean div is displayed");
        return actionBot.isElementDisplayed(DID_YOU_MEAN_LINK);
    }

    public int getAmountResults() {
        Log.info("Attempt to click on Did you mean div");
        actionBot.click(DID_YOU_MEAN_LINK);
        return getSearchResultList().size();
    }

    public ArticlePage selectFirstResult() {
        Log.info("Attempt to click on the first result");
        actionBot.click(getSearchResultList().get(0));
        return new ArticlePage();
    }

    private List<WebElement> getSearchResultList() {
        return actionBot.findAll(SEARCH_RESULTS_LIST);
    }
}
