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
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("venue")
    @Expose
    private String venue;
    @SerializedName("round")
    @Expose
    private String round;

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
     * @param description
     * @param sportName
     * @param gender
     * @param round
     * @param venue
     */

    public ScheduleSet(int eventId,
                       long time,
                       long updatedAt,
                       String sportName,
                       long eventDate,
                       int gender,
                       String description,
                       String venue,
                       String round) {
        this.eventId = eventId;
        this.time = time;
        this.updatedAt = updatedAt;
        this.sportName = sportName;
        this.eventDate = eventDate;
        this.gender = gender;
        this.description = description;
        this.venue = venue;
        this.round = round;
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

    public long getEventDate() {
        return eventDate;
    }

    public void setEventDate(long eventDate) {
        this.eventDate = eventDate;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }
}
