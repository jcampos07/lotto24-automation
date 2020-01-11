package main.java.datastructures;

import com.google.gson.internal.LinkedTreeMap;

public class SearchElement {

    private String searchCriteria;
    private String title;

    public SearchElement(){
    }

    public SearchElement(LinkedTreeMap<String,String> map){

        setSearchCriteria(map.get("searchCriteria"));
        setTitle(map.get("title"));
    }

    public SearchElement(String searchCriteria, String title) {
        setSearchCriteria(searchCriteria);
        setTitle(title);
    }

    public String getTitle() {
        return title;
    }

    public String getSearchCriteria() {
        return searchCriteria;
    }

    private void setTitle(String title) {
        this.title = title;
    }

    private void setSearchCriteria(String searchCriteria) {
        this.searchCriteria = searchCriteria;
    }
}
