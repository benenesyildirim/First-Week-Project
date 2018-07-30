package com.example.enesyildirim.firstweeksproject.model;

import java.io.Serializable;

public class Items implements Serializable {

    private static final long serialVersionUID = -5239382098728392847L;

    private long id;
    private int watchers;
    private String name, full_name, language;
    private boolean fork;
    private Owner owner;

    //Id
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    //Watchers
    public int getWatchers() {
        return watchers;
    }

    public void setWatchers(int watchers) {
        this.watchers = watchers;
    }

    //Name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //Full Name
    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    //Language
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    //Fork
    public boolean isFork() {
        return fork;
    }

    public void setFork(boolean fork) {
        this.fork = fork;
    }

    //Owner
    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}
