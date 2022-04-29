package com.atlassian.raman.rest;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "message")
@XmlAccessorType(XmlAccessType.FIELD)
public class MyRestResourceModel {

    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "key")
    private String key;

    public MyRestResourceModel() {
    }

    public MyRestResourceModel(String name, String key) {
        this.name = name;
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}