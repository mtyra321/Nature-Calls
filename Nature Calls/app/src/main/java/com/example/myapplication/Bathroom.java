package com.example.myapplication;

import java.util.List;

//will store the data on the bathrooms
//testing testing
public class Bathroom {
    private String description;
    private List<String> tags;
    private List<Ratings> ratings;
    private String roomNumber;
    private String photo;
    private String building;
    private long rating;

    public long getRating() {
        return rating;
    }

    public void setRating(long rating) {
        this.rating = rating;
    }

    public Bathroom() {
    }

    public Bathroom(String description, List<String> tags, List<Ratings> ratings, String roomNumber, String building) {
        this.description = description;
        this.tags = tags;
        this.ratings = ratings;
        this.roomNumber = roomNumber;
        this.building = building;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getTags() {
        return tags;
    }

    public List<Ratings> getRatings() {
        return ratings;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getPhoto() {
        return photo;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public void setRatings(List<Ratings> ratings) {
        this.ratings = ratings;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return building+" "+ roomNumber+ "\n"+"Description: "+description+"\n"+"Rating: "+rating+"\n"+"Tags: "+tags+"\n"+"Ratings: "+ratings;

    }
}
