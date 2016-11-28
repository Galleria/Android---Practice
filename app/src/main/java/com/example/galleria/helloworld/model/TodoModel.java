package com.example.galleria.helloworld.model;

/**
 * Created by dt204842 on 11/24/2016.
 */

public class TodoModel {
    private String id;
    private String topic;
    private String content;
    private Boolean isDone;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getDone() {
        return isDone;
    }

    public void setDone(Boolean done) {
        isDone = done;
    }
}
