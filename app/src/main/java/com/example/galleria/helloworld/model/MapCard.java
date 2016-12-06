package com.example.galleria.helloworld.model;

import android.graphics.Bitmap;

/**
 * Created by Windows on 12/4/2016.
 */

public class MapCard {

    private String topic;
    private String time;
    private int rate;
    private Bitmap picture;
    private Bitmap owner_picture;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public Bitmap getPicture() {
        return picture;
    }

    public void setPicture(Bitmap picture) {
        this.picture = picture;
    }

    public Bitmap getOwner_picture() {
        return owner_picture;
    }

    public void setOwner_picture(Bitmap owner_picture) {
        this.owner_picture = owner_picture;
    }

}
