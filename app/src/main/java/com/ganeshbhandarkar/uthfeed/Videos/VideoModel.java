package com.ganeshbhandarkar.uthfeed.Videos;

import com.google.firebase.database.ServerValue;

public class VideoModel {


    public VideoModel() {
    }

    String videoLink;
    Object timeStamp;

    public VideoModel(String videoLink) {
        this.videoLink = videoLink;
        this.timeStamp = ServerValue.TIMESTAMP;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    public Object getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Object timeStamp) {
        this.timeStamp = timeStamp;
    }
}
