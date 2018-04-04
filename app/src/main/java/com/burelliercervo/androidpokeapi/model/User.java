package com.burelliercervo.androidpokeapi.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Cervo on 26/03/2018.
 */

public class User {
    private int id;
    private String name;
    private String picture;
    private String email;

    public void setUserData(JSONObject json_object){
        try{
            this.id = json_object.getInt("id");
            this.name = json_object.getString("name");
            this.email = json_object.getString("email");
            JSONObject jsonObject = json_object.getJSONObject("picture");
            Object temp = jsonObject.get("data");
            JSONObject temp2 = (JSONObject) temp;
            String finalURL = temp2.getString("url");
            this.picture = (finalURL);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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
