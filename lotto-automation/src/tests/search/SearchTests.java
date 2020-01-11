package tests.search;

import main.java.datastructures.SearchElement;
import main.java.steps.search.SearchSteps;
import main.resources.dataproviders.SearchDataProvider;
import org.testng.annotations.Test;
import tests.config.TestCase;


public class SearchTests extends TestCase {

    @Test(groups = {"search"}, description = "Search on wikipedia some articles", dataProvider = "Search on wikipedia",
            dataProviderClass = SearchDataProvider.class)
    public void search(SearchElement searchElement) {
        SearchSteps searchSteps = new SearchSteps();
        searchSteps.executeSearch(searchElement);
        searchSteps.verifySearchResults();
        searchSteps.verifyFirstResultInformation();
    }
}
