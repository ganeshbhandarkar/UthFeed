package com.ganeshbhandarkar.uthfeed.Models;

import com.google.firebase.database.ServerValue;

public class PostModel {

    String postKey;
    String imageUri;
    String authorUri;
    String author;
    String title;
    String descripton;
    Object timeStamp;

    public PostModel(){}

    public String getPostKey() {
        return postKey;
    }

    public void setPostKey(String postKey) {
        this.postKey = postKey;
    }

    public PostModel(String imageUri, String authorUri, String author, String title, String descripton) {
        this.imageUri = imageUri;
        this.authorUri = authorUri;
        this.author = author;
        this.title = title;
        this.descripton = descripton;
        this.timeStamp = ServerValue.TIMESTAMP;
    }

    public Object getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Object timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescripton() {
        return descripton;
    }

    public void setDescripton(String descripton) {
        this.descripton = descripton;
    }

    public String getAuthorUri() {
        return authorUri;
    }

    public void setAuthorUri(String authorUri) {
        this.authorUri = authorUri;
    }
}
