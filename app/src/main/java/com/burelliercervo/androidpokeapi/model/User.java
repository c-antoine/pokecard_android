package com.burelliercervo.androidpokeapi.model;

/**
 * Created by Cervo on 26/03/2018.
 */

public class User {
    private int id;
    private String[] pokelist;
    private static final User ourInstance = new User();

    public static User getInstance() {
        return ourInstance;
    }

    private User() {
    }
}
