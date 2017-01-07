package org.arenatest.bits.arena_test.sets;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EventsData {

    @SerializedName("success")
    @Expose
    private long success;
    @SerializedName("data")
    @Expose
    private List<EventsSet> data = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public EventsData() {
    }

    /**
     *
     * @param data
     * @param success
     */
    public EventsData(long success, List<EventsSet> data) {
        super();
        this.success = success;
        this.data = data;
    }

    public long getSuccess() {
        return success;
    }

    public void setSuccess(long success) {
        this.success = success;
    }

    public List<EventsSet> getData() {
        return data;
    }

    public void setData(List<EventsSet> data) {
        this.data = data;
    }

}
