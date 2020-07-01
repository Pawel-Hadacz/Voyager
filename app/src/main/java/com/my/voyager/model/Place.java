package com.my.voyager.model;

import android.graphics.Bitmap;

public class Place {
    private String id;



    private String name;
    private int diameter;
    private Bitmap image;
    private User user;
    private String note;
    public Place() {
    }


    public Place(String name, int diameter, Bitmap image, User user, String note) {
        this.name = name;
        this.diameter = diameter;
        this.image = image;
        this.user = user;
        this.note = note;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDiameter() {
        return diameter;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
