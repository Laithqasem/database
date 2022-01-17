package com.database.database;

public class Meals {

    private String meal_id;
    private String name;
    private int Price;

    public Meals(String meal_id, String name, int price){
        this.meal_id=meal_id;
        this.name=name;
        Price=price;
    }

    public String getMeal_id(){
        return meal_id;
    }

    public void setMeal_id(String meal_id){
        this.meal_id=meal_id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public int getPrice(){
        return Price;
    }

    public void setPrice(int price){
        Price=price;
    }
}
