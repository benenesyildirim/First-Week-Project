package com.example.enesyildirim.firstweeksproject.model;

import java.io.Serializable;

public class Owner implements Serializable {
    private static final long serialVersionUID = -4930304192184576196L;

    private String login, avatar_url, type;
    private long id;

    //Login
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    //Avatar URL
    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    //Type
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    //Id
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}