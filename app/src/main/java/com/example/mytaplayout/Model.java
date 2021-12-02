package com.example.mytaplayout;

public class Model {

    private String name;
    private String avaterUrl;
    private String type;

    public Model(String name, String avaterUrl, String type) {
        this.name = name;
        this.avaterUrl = avaterUrl;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvaterUrl() {
        return avaterUrl;
    }

    public void setAvaterUrl(String avaterUrl) {
        this.avaterUrl = avaterUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



}
