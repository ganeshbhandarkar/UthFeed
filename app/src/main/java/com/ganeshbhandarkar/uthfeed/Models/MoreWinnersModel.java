package com.ganeshbhandarkar.uthfeed.Models;

import com.google.firebase.database.ServerValue;

public class MoreWinnersModel {

    String postKey;
    String imageUri;
    String winnerName;
    String competitionName;
    Object timeStamp;

    public MoreWinnersModel() {
    }

    public String getPostKey() {
        return postKey;
    }

    public MoreWinnersModel(String imageUri, String winnerName, String competitionName) {
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
