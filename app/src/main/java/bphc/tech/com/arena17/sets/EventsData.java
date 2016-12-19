package bphc.tech.com.arena17.sets;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tejeshwar on 20/12/16.
 */

public class EventsData {

    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("data")
    @Expose
    private List<EventsSet> events_data = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public EventsData() {
    }

    /**
     *
     * @param events_data
     * @param success
     */
    public EventsData(String success, List<EventsSet> events_data) {
        super();
        this.success = success;
        this.events_data = events_data;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<EventsSet> getData() {
        return events_data;
    }

    public void setData(List<EventsSet> data) {
        this.events_data = events_data;
    }
}
