package main.java.steps.search;

import main.java.datastructures.SearchElement;
import main.java.pageobjects.article.ArticlePage;
import main.java.pageobjects.results.ResultsPage;
import main.java.pageobjects.search.SearchPage;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

/**
 * Search steps page that works as controller between the tests and page objects
 *
 * @author Jason Campos on 1/11/2020
 */

public class SearchSteps {

    private final SoftAssert softAssert = new SoftAssert();
    private ResultsPage resultsPage;

    public void executeSearch(SearchElement searchElement) {
        SearchPage searchPage = new SearchPage();
        Assert.assertEquals(searchPage.getPageTitle(), searchElement.getTitle(), "Site's title was not displayed");
        resultsPage = searchPage.searchInformation(searchElement.getSearchCriteria());
    }

    public void verifySearchResults() {
        softAssert.assertTrue(resultsPage.isSuggestionDisplayed(), "Did you mean suggestion was not displayed");
        softAssert.assertEquals(resultsPage.getAmountResults(), 20, "The amount of results are not the expected");
        softAssert.assertAll();
    }

    public void verifyFirstResultInformation() {
        ArticlePage articlePage = resultsPage.selectFirstResult();
        softAssert.assertFalse(articlePage.isArticleTitleDisplayed(), "Article title was not displayed");
        softAssert.assertTrue(articlePage.isTableOfContentsDisplayed(), "Table of contents was not displayed");
        softAssert.assertAll();
    }
}
