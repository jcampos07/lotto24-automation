package main.java.datastructures;

import com.google.gson.internal.LinkedTreeMap;

/**
 * Model element page that stores the information the json file maps to it in order to be used in the tests
 * @author Jason Campos on 1/11/2020
 */

public class ModelElement {

    private String model;
    private String description;

    public ModelElement(){
    }

    public ModelElement(LinkedTreeMap<String,String> map){
        setModel(map.get("model"));
        setDescription(map.get("description"));
    }

    public ModelElement(String model, String description) {
        setModel(model);
        setDescription(description);
    }

    public String getDescription() {
        return description;
    }

    public String getModel() {
        return model;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    private void setModel(String model) {
        this.model = model;
    }
}
