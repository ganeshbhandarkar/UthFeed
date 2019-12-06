package com.ganeshbhandarkar.uthfeed.Models;

import com.google.firebase.database.ServerValue;

public class CommentModel {

    String comment,uid,uimg,uname;
    Object timeStamp;

    // This is git demo

//    public CommentModel(String comment, String uid, String uimg, String uname, Object timeStamp) {
//        this.comment = comment;
//        this.uid = uid;
//        this.uimg = uimg;
//        this.uname = uname;
//        this.timeStamp = timeStamp;
//    }

    public CommentModel() {
    }

    public CommentModel(String comment, String uid, String uimg, String uname) {
        this.comment = comment;
        this.uid = uid;
        this.uimg = uimg;
        this.uname = uname;
        this.timeStamp = ServerValue.TIMESTAMP;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUimg() {
        return uimg;
    }

    public void setUimg(String uimg) {
        this.uimg = uimg;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public Object getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Object timeStamp) {
        this.timeStamp = timeStamp;
    }
}
