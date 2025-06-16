package dev.dojo.game;

import java.util.Date;

public class Event {

    private Date date;
    private String description;

    public Event(String description) {
        this.date = new Date();
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }
}
