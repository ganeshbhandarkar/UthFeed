package com.ganeshbhandarkar.uthfeed.MainThreadActivities;

public class UserProfile {

    String userName;
    String userEmail;
    String userPhoto;

    public UserProfile(String userName, String userEmail, String userPhoto) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPhoto = userPhoto;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
