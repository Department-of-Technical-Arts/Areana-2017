
package bphc.tech.com.arena17.sets;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ScheduleSet {

    @SerializedName("uid")
    @Expose
    private String uid;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("sport_name")
    @Expose
    private String sportName;
    @SerializedName("event_date")
    @Expose
    private String eventDate;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ScheduleSet() {
    }

    /**
     * 
     * @param updatedAt
     * @param uid
     * @param eventDate
     * @param time
     * @param imageUrl
     * @param sportName
     */
    public ScheduleSet(String uid, String time, String updatedAt, String sportName, String eventDate, String imageUrl) {
        super();
        this.uid = uid;
        this.time = time;
        this.updatedAt = updatedAt;
        this.sportName = sportName;
        this.eventDate = eventDate;
        this.imageUrl = imageUrl;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
