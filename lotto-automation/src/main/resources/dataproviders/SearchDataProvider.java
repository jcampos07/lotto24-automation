package main.resources.dataproviders;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import tests.config.TestCase;

public class SearchDataProvider {

    private static final int FIRST_INFORMATION_TO_SEARCH = 0;
    private static final int SECOND_INFORMATION_TO_SEARCH = 1;

    /**
     *  Create a matrix in order to search on wikipedia for several information
     * @param context Context of the project
     * @return matrix with the data providers to test
     */
    @DataProvider(name = "Search on wikipedia")
    public static Object[][] dataProviderToSearchOnWikipedia(ITestContext context) {
        Object[][] dataProviderArraySearchInformation = new Object[2][1];
        dataProviderArraySearchInformation[0][0] = TestCase.dataSources.getSearchElementList().get(FIRST_INFORMATION_TO_SEARCH);
        dataProviderArraySearchInformation[1][0] = TestCase.dataSources.getSearchElementList().get(SECOND_INFORMATION_TO_SEARCH);
        return dataProviderArraySearchInformation;
    }
}
