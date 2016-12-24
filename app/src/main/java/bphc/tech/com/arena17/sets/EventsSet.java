package bphc.tech.com.arena17.sets;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventsSet {

    @SerializedName("event_id")
    @Expose
    private String eventId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("captain_name")
    @Expose
    private String captainName;
    @SerializedName("contact")
    @Expose
    private String contact;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("gender")
    @Expose
    private String gender;

    /**
     * No args constructor for use in serialization
     *
     */
    public EventsSet() {
    }

    /**
     *
     * @param captainName
     * @param eventId
     * @param imageUrl
     * @param name
     * @param gender
     * @param contact
     */
    public EventsSet(String eventId, String name, String captainName, String contact, String imageUrl, String gender) {
        super();
        this.eventId = eventId;
        this.name = name;
        this.captainName = captainName;
        this.contact = contact;
        this.imageUrl = imageUrl;
        this.gender = gender;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCaptainName() {
        return captainName;
    }

    public void setCaptainName(String captainName) {
        this.captainName = captainName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}

