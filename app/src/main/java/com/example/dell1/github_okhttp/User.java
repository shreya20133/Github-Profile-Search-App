package com.example.dell1.github_okhttp;

public class User {

    String login,id,avatar_url,html_url;

    public User(String login, String id, String image, String url) {
        this.login = login;
        this.id = id;
        this.avatar_url = image;
        this.html_url = url;

    }

    public String getLogin() {
        return login;
    }

    public String getId() {
        return id;
    }

    public String getImage() {
        return avatar_url;
    }

    public String getUrl() {
        return html_url;
    }


}
