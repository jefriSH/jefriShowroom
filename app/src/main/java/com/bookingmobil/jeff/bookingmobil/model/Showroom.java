package com.bookingmobil.jeff.bookingmobil.model;

public class Showroom {

    private String id;
    private String name;
    private String location;
    private String Photo;

    public Showroom(){

    }

    public Showroom(String id, String name, String location, String photo) {
        this.id = id;
        this.name = name;
        this.location = location;
        Photo = photo;
    }

//    Getter


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getPhoto() {
        return Photo;
    }

    //    Setter
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPhoto(String photo) {
        Photo = photo;
    }
}
