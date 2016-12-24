
package bphc.tech.com.arena17.sets;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ScheduleSet {

    @SerializedName("event_id")
    @Expose
    private String eventId;
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
    @SerializedName("gender")
    @Expose
    private String gender;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ScheduleSet() {
    }

    /**
     * 
     * @param updatedAt
     * @param eventDate
     * @param time
     * @param eventId
     * @param sportName
     * @param gender
     */
    public ScheduleSet(String eventId, String time, String updatedAt, String sportName, String eventDate, String gender) {
        super();
        this.eventId = eventId;
        this.time = time;
        this.updatedAt = updatedAt;
        this.sportName = sportName;
        this.eventDate = eventDate;
        this.gender = gender;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}
