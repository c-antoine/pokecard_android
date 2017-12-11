package com.burelliercervo.androidpokeapi;

/**
 * Created by iem on 14/11/2017.
 */

public class Pokemon {

    private int id;
    private String name;
    private String xp;
    private String type;
    private String height;
    private String width;

    public Pokemon(String name, String xp, String type){
        this.name = name;
        this.xp = xp;
        this.type = type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {

        return type;
    }

    public int getId(){
        return id;
    }

    public String getName() {
        return name;
    }


    public String getXp(){
        return xp;
    }

    public String getHeight(){
        return height;
    }

    public String getWidth(){
        return width;
    }



}
