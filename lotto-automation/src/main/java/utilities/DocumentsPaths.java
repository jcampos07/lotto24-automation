package main.java.utilities;

import main.java.datastructures.SearchElement;

public enum DocumentsPaths {

    SEARCH("src/main/resources/datasources/SearchElements.json", SearchElement.class);

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
