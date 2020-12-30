package com.example.javacoursesapp.model;

public class Module {
    private String moduleName;
    private String moduleUrl;

    public Module(String moduleName, String moduleUrl) {
        this.moduleName = moduleName;
        this.moduleUrl = moduleUrl;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModuleUrl() {
        return moduleUrl;
    }

    public void setModuleUrl(String moduleUrl) {
        this.moduleUrl = moduleUrl;
    }
}
