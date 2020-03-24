package com.example.myapplication;

import java.util.List;

//will store the data on the bathrooms
//testing testing
public class Bathroom {
    private String name;
    private String description;
    private List<Tags> tags;
    private List<Ratings> ratings;
    private Integer roomNumber;
    private String photo;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Tags> getTags() {
        return tags;
    }

    public List<Ratings> getRatings() {
        return ratings;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public String getPhoto() {
        return photo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTags(List<Tags> tags) {
        this.tags = tags;
    }

    public void setRatings(List<Ratings> ratings) {
        this.ratings = ratings;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
