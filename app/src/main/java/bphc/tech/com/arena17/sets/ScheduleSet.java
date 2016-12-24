
package bphc.tech.com.arena17.sets;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ScheduleSet {

    @SerializedName("event_id")
    @Expose
    private int eventId;
    @SerializedName("time")
    @Expose
    private long time;
    @SerializedName("updated_at")
    @Expose
    private long updatedAt;
    @SerializedName("sport_name")
    @Expose
    private String sportName;
    @SerializedName("event_date")
    @Expose
    private long eventDate;
    @SerializedName("gender")
    @Expose
    private int gender;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ScheduleSet() {
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public long getEventDate() {
        return eventDate;
    }

    public void setEventDate(long eventDate) {
        this.eventDate = eventDate;
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

    public ScheduleSet(int eventId, long time, long updatedAt, String sportName, long eventDate, int gender) {
        this.eventId = eventId;
        this.time = time;
        this.updatedAt = updatedAt;
        this.sportName = sportName;
        this.eventDate = eventDate;
        this.gender = gender;
    }
}
