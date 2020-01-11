package tests.config;

import main.java.datastructures.ModelElement;
import main.java.utilities.DocumentsPaths;
import main.java.datastructures.SearchElement;
import main.java.utilities.ReadJson;

import java.util.List;

/**
 * This class handles the data sources, the list this class contains are going to be used for the tests
 * @author Jason Campos on 1/9/2020
 */
public class DataSources {

    private final ReadJson read = new ReadJson();
    private List<SearchElement> searchElementList;
    private List<ModelElement> modelElementList;

    @SuppressWarnings("unchecked")
    public void readDataSources(){
        searchElementList = (List<SearchElement>)(Object)read.readFile(DocumentsPaths.SEARCH);
        modelElementList = (List<ModelElement>)(Object)read.readFile(DocumentsPaths.MODELS);
    }

    public List<SearchElement> getSearchElementList() {
        return searchElementList;
    }

    public List<ModelElement> getModelElementList() {
        return modelElementList;
    }
}
