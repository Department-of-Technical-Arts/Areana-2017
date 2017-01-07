package org.arenatest.bits.arena_test.sets;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ScheduleData {

    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("data")
    @Expose
    private List<ScheduleSet> schedule_data = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ScheduleData() {
    }

    /**
     * 
     * @param schedule_data
     * @param success
     */
    public ScheduleData(String success, List<ScheduleSet> schedule_data) {
        super();
        this.success = success;
        this.schedule_data = schedule_data;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<ScheduleSet> getData() {
        return schedule_data;
    }

    public void setData(List<ScheduleSet> data) {
        this.schedule_data = schedule_data;
    }

}
