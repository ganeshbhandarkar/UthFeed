package com.ganeshbhandarkar.uthfeed.Compete;

import com.google.firebase.database.ServerValue;

public class WinnerModel {

    String postKey;
    String imageUri;
    String winnerName;
    String competitionName;
    Object timeStamp;

    public WinnerModel() {
    }

    public void setPostKey(String postKey) {
        this.postKey = postKey;
    }

    public WinnerModel(String imageUri, String winnerName, String competitionName) {
        this.imageUri = imageUri;
        this.winnerName = winnerName;
        this.competitionName = competitionName;
        this.timeStamp = ServerValue.TIMESTAMP;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getWinnerName() {
        return winnerName;
    }

    public void setWinnerName(String winnerName) {
        this.winnerName = winnerName;
    }

    public String getCompetitionName() {
        return competitionName;
    }

    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }

    public Object getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Object timeStamp) {
        this.timeStamp = timeStamp;
    }
}
