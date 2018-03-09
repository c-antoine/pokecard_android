package com.burelliercervo.androidpokeapi.model;

/**
 * Created by iem on 14/11/2017.
 */

public class Pokemon {

    private String id;
    private String name;
    private String xp;
    private String[] type;
    private String height;
    private String weight;
    private String sprite;
    private String level;

    public String getSprite() {
        return sprite;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }

    public Pokemon(String id, String name, String xp, String[] type){
        this.id = id;
        this.name = name;
        this.xp = xp;
        this.type = type;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLevel(String level) {
        this.level = level;
    }



    public String getType() {
        int i;
        String toReturn="";
        for(i=0;i<type.length;i++){
            toReturn=toReturn+ " / " + type[i];
        }
        return toReturn;
    }

    public String getId(){
        return id;
    }

    public String getName() {
        return name;
    }


    public String getXp(){
        return xp;
    }

    public String getLevel(){
        return level;
    }

    public String getHeight(){
        return height;
    }

    public String getWeight(){
        return weight;
    }



}
