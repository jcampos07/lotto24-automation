package main.java.datastructures;

import com.google.gson.internal.LinkedTreeMap;

public class SearchElement {

    private String searchCriteria;
    private String result;

    public SearchElement(){
    }

    public SearchElement(LinkedTreeMap<String,String> map){

        this.searchCriteria = map.get("searchCriteria");
        this.result = map.get("result");
    }

    public SearchElement(String searchCriteria, String result) {
        this.searchCriteria = searchCriteria;
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public String getSearchCriteria() {
        return searchCriteria;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setSearchCriteria(String searchCriteria) {
        this.searchCriteria = searchCriteria;
    }
}
