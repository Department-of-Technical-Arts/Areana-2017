
package bphc.tech.com.arena17.sets;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventsSet {

    @SerializedName("event_id")
    @Expose
    private int eventId;
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
    @SerializedName("pdf_url")
    @Expose
    private String pdfUrl;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("updated_at")
    @Expose
    private long updatedAt;
    @SerializedName("gender")
    @Expose
    private int gender;
    private int favourite;

    public int getFavourite() {
        return favourite;
    }

    public void setFavourite(int favourite) {
        this.favourite = favourite;
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public EventsSet() {
    }

    public EventsSet(int eventId, String name, String captainName, String contact, String imageUrl, String pdfUrl, String longitude, String latitude, long updatedAt, int gender, int favourite) {
        this.eventId = eventId;
        this.name = name;
        this.captainName = captainName;
        this.contact = contact;
        this.imageUrl = imageUrl;
        this.pdfUrl = pdfUrl;
        this.longitude = longitude;
        this.latitude = latitude;
        this.updatedAt = updatedAt;
        this.gender = gender;
        this.favourite = favourite;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
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

    public String getPdfUrl() {
        return pdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }
}
