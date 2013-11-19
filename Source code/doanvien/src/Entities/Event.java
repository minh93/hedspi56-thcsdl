package Entities;

import java.util.Date;

/**
 *
 * @author PhamDucMinh
 */
public class Event {
    private String evtID;
    private String evtName;
    private String location;
    private Date start;
    private Date end;
    private int numOfPeople;
    private int rating;

    public Event(String evtID, String evtName, String location, Date start, Date end, int numOfPeople, int rating) {
        this.evtID = evtID;
        this.evtName = evtName;
        this.location = location;
        this.start = start;
        this.end = end;
        this.numOfPeople = numOfPeople;
        this.rating = rating;
    }

    public String getEvtID() {
        return evtID;
    }

    public String getEvtName() {
        return evtName;
    }

    public String getLocation() {
        return location;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public int getNumOfPeople() {
        return numOfPeople;
    }

    public int getRating() {
        return rating;
    }

    public void setEvtID(String evtID) {
        this.evtID = evtID;
    }

    public void setEvtName(String evtName) {
        this.evtName = evtName;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public void setNumOfPeople(int numOfPeople) {
        this.numOfPeople = numOfPeople;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
    
    
}
