package main.resources.dataproviders;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import tests.config.TestCase;

public class SearchDataProvider {

    /**
     *  Create a matrix in order to search on wikipedia for several information
     * @param context Context of the project
     * @return matrix with the data providers to test
     */
    @DataProvider(name = "Search on wikipedia")
    public static Object[][] dataProviderToSearchOnWikipedia(ITestContext context) {
        Object[][] dataProviderArraySearchInformation = new Object[2][1];
        dataProviderArraySearchInformation[0][0] = TestCase.dataSources.getSearchElementList().get(0);
        dataProviderArraySearchInformation[1][0] = TestCase.dataSources.getSearchElementList().get(1);
        return dataProviderArraySearchInformation;
    }
}
