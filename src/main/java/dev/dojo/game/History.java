package dev.dojo.game;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class History {

    List<Event> events;

    public History() {
        this.events = new ArrayList<Event>();
        addEvent("Début du jeu");
    }

    public void addEvent(String eventDescription) {
        this.events.add(new Event(eventDescription));
    }

    public Event lastEvent() {
        if (events.isEmpty()) {
            return null;
        }
        return events.get(events.size() - 1);
    }

    public Event firstEvent() {
        if (events.isEmpty()) {
            return null;
        }
        return events.get(0);
    }
}
