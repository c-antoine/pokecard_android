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
    private String[] abilities;

    public String[] getAbilities() {
        return abilities;
    }

    public void setAbilities(String[] abilities) {
        this.abilities = abilities;
    }

    public Pokemon(){
        /*
        this.sprite = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+this.id+".png";
        */
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

    public void setName(String name) {
        this.name = name;
    }

    public void setXp(String xp) {
        this.xp = xp;
    }

    public void setType(String[] type) {
        this.type = type;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getSprite() {
        return sprite;
    }

    public String getSpriteWithID(int id) {
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+id+".png";
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }

    public Pokemon getPokemon(){
        return this;
    }
}
