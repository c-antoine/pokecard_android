package com.burelliercervo.androidpokeapi.model;

/**
 * Created by Cervo on 26/03/2018.
 */

public class User {
    private int id;
    private String[] pokelist;
    private static User userInstance;

    public static User getInstance() {
        if (userInstance == null) {
            userInstance = new User();
        }
        return userInstance;
    }

    public User() {}

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
