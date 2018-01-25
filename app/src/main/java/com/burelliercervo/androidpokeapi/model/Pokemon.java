package com.burelliercervo.androidpokeapi.model;

/**
 * Created by iem on 14/11/2017.
 */

public class Pokemon {

    private int id;
    private String name;
    private String xp;
    private String[] type;
    private String height;
    private String weight;
    private String sprite;

    public String getSprite() {
        return sprite;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }

    public Pokemon(String name, String xp, String[] type){
        this.name = name;
        this.xp = xp;
        this.type = type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        int i;
        String toReturn="";
        for(i=0;i<type.length;i++){
            toReturn=toReturn+ " / " + type[i];
        }
        return toReturn;
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

    public String getWeight(){
        return weight;
    }



}
