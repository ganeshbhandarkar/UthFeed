package com.ganeshbhandarkar.uthfeed.Models;

import com.google.firebase.database.ServerValue;

public class MoreCompetitionModel {

    String postKey;
    String imageUri;
    String title;
    String description;
    String registerLink;
    Object timeStamp;

    public MoreCompetitionModel() {
    }

    public String getPostKey() {
        return postKey;
    }

    public MoreCompetitionModel(String imageUri, String title,String description,String registerLink) {
        this.imageUri = imageUri;
        this.title = title;
        this.description = description;
        this.registerLink = registerLink;
        this.timeStamp = ServerValue.TIMESTAMP;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRegisterLink() {
        return registerLink;
    }

    public void setRegisterLink(String registerLink) {
        this.registerLink = registerLink;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Object timeStamp) {
        this.timeStamp = timeStamp;
    }
}
