package com.sv.swivl.app.model;

public class User {

    private String avatarURL;
    private String login;
    private String profileURL;

    public User(String avatarURL, String login, String profileURL) {
        this.avatarURL = avatarURL;
        this.login = login;
        this.profileURL = profileURL;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
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

        if (!avatarURL.equals(user.avatarURL)) return false;
        if (!login.equals(user.login)) return false;
        if (!profileURL.equals(user.profileURL)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = avatarURL.hashCode();
        result = 31 * result + login.hashCode();
        result = 31 * result + profileURL.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "avatarURL='" + avatarURL + '\'' +
                ", login='" + login + '\'' +
                ", profileURL='" + profileURL + '\'' +
                '}';
    }
}
