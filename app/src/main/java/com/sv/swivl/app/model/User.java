package com.sv.swivl.app.model;

public class User {

    private String login;
    private String profileURL;

    public User(String login, String profileURL) {
        this.login = login;
        this.profileURL = profileURL;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getProfileURL() {
        return profileURL;
    }

    public void setProfileURL(String profileURL) {
        this.profileURL = profileURL;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;

        return login.equals(user.login) && profileURL.equals(user.profileURL);
    }

    @Override
    public int hashCode() {
        int result = login.hashCode();
        result = 31 * result + profileURL.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", profileURL='" + profileURL + '\'' +
                '}';
    }
}
