package main.java.utilities;

import main.java.datastructures.ModelElement;
import main.java.datastructures.SearchElement;

/**
 * Contains the files paths and data structures classes each json file is going to be mapped.
 * @author Jason Campos on 1/11/2020
 */

public enum DocumentsPaths {

    SEARCH("src/main/resources/datasources/SearchElements.json", SearchElement.class),
    MODELS("src/main/resources/datasources/ModelElements.json", ModelElement.class);

    private String fileName;
    private Class domainType;

    DocumentsPaths(String fileName, Class domainType) {
        this.setFileName(fileName);
        this.setDomainType(domainType);
    }

    public String getFileName() {
        return fileName;
    }

    private void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Class getDomainType() {
        return domainType;
    }

    private void setDomainType(Class domainType) {
        this.domainType = domainType;
    }
}
