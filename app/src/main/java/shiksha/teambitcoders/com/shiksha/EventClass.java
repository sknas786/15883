package shiksha.teambitcoders.com.shiksha;

/**
 * Created by Subham Lenka on 20-01-2018.
 */

public class EventClass {
    public String eventUniqueId;
    private String eventDate;
    private String eventName;
    private String eventColor;

    public EventClass() {
        // This is default constructor.
    }
    public EventClass(String eventUniqueId,String eventDate,String eventName) {
        this.eventUniqueId=eventUniqueId;
        this.eventDate=eventDate;
        this.eventName=eventName;

        // This is default constructor.
    }


    public String getEventUniqueId() {

        return eventUniqueId;
    }

    public void setEventUniqueId(String eventUniqueId) {

        this.eventUniqueId = eventUniqueId;
    }
    public String getEventDate() {

        return eventDate;
    }

    public void setEventDate(String eventDate) {

        this.eventDate = eventDate;
    }

    public String getEventName() {

        return eventName;
    }

    public void setEventName(String eventName) {

        this.eventName = eventName;
    }
    public String getEventColor() {

        return eventColor;
    }

    public void setEventColor(String eventColor) {

        this.eventColor = eventColor;
    }
}
