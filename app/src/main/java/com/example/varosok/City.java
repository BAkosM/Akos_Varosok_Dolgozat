package com.example.varosok;

import com.google.gson.annotations.Expose;
import android.annotation.SuppressLint;

public class City {
    private int id;
    @Expose(serialize = false)
    private String name;
    private String country;
    private int populis;

    public City(int id, String name, String country, int populis) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.populis = populis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getPopulis() {
        return populis;
    }

    public void setPopulis(int populis) {
        this.populis = populis;
    }

    @SuppressLint("DefaultLocale")
    @Override
    public String toString() {
        return String.format("%s (%s) Population: %d\n", this.name, this.country, this.populis);
    }
}
