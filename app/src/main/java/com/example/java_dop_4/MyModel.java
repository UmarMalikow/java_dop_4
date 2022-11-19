package com.example.java_dop_4;

import java.io.Serializable;

public class MyModel implements Serializable {

    private String imageUrl;
    private String name;
    private int age;
    private String color;

    public MyModel(String imageUrl, String name, int age, String color) {
        this.imageUrl = imageUrl;
        this.name = name;
        this.age = age;
        this.color = color;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
