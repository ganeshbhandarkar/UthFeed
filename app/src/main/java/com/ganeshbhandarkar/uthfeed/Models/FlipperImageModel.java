package com.ganeshbhandarkar.uthfeed.Models;

import com.google.firebase.database.ServerValue;

public class FlipperImageModel {

    String imageUri;
    String imageUri1;
    String imageUri2;
    Object timeStamp;
    String postKey;

    public String getPostKey() {
        return postKey;
    }

    public void setPostKey(String postKey) {
        this.postKey = postKey;
    }

    public FlipperImageModel() {
    }

    public FlipperImageModel(String imageUri, String imageUri1, String imageUri2) {
        this.imageUri = imageUri;
        this.imageUri1 = imageUri1;
        this.imageUri2 = imageUri2;
        this.timeStamp = ServerValue.TIMESTAMP;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getImageUri1() {
        return imageUri1;
    }

    public void setImageUri1(String imageUri1) {
        this.imageUri1 = imageUri1;
    }

    public String getImageUri2() {
        return imageUri2;
    }

    public void setImageUri2(String imageUri2) {
        this.imageUri2 = imageUri2;
    }

    public Object getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Object timeStamp) {
        this.timeStamp = timeStamp;
    }
}
