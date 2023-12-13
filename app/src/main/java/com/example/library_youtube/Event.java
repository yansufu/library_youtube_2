package com.example.library_youtube;

import java.io.Serializable;

public class Event implements Serializable {
    public String uid;
    public String eventname;
    public String collaborator;
    private int imgEvent;

    public Event() {
    }

    public Event(String eventname, String collaborator, int imgEvent) {

        this.eventname = eventname;
        this.collaborator = collaborator;
        this.imgEvent = imgEvent;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public String getCollaborator() {
        return collaborator;
    }

    public void setCollaborator(String collaborator) {
        this.collaborator = collaborator;
    }

    public int getImgEvent() {
        return imgEvent;
    }

    public void setImgEvent(int imgEvent) {
        this.imgEvent = imgEvent;
    }
}
