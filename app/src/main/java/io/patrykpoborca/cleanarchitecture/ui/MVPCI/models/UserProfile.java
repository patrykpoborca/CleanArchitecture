package io.patrykpoborca.cleanarchitecture.ui.MVPCI.models;

/**
 * Created by Patryk on 7/29/2015.
 */
public class UserProfile {
    private final String password;
    private final String username;

    public UserProfile(String username, String password) {
        this.username = username;
        this.password =password;
    }

    public String getFormattedCredentials(){
        return "User Profile = " + username + " " + password;
    }

    public String getUserName() {
        return username;
    }
}
