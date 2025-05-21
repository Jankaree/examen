package com.example.guessing_game.Model;

import java.util.Arrays;

public class Animal {
    private String name;
    private String habitat;
    private String diet;
    private String[] locations;
    private String name_of_young;
    private String skin_type;

    private int locationInt = 2;

    public Animal(String name, String habitat, String diet, String[] locations, String name_of_young, String skin_type) {
        this.name = name;
        this.habitat = habitat;
        this.diet = diet;
        this.locations = locations;
        this.name_of_young = name_of_young;
        this.skin_type = skin_type;
    }

    public int getLocationInt() {
        return locationInt;
    }

    public void setLocationInt(int locationInt) {
        this.locationInt = locationInt;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "locations=" + Arrays.toString(locations) +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public String[] getLocations() {
        return locations;
    }

    public void setLocations(String[] locations) {
        this.locations = locations;
    }

    public String getName_of_young() {
        return name_of_young;
    }

    public void setName_of_young(String name_of_young) {
        this.name_of_young = name_of_young;
    }

    public String getSkin_type() {
        return skin_type;
    }

    public void setSkin_type(String skin_type) {
        this.skin_type = skin_type;
    }
}
