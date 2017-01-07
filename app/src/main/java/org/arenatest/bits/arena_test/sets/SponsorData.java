package org.arenatest.bits.arena_test.sets;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tejeshwar on 6/1/17.
 */

public class SponsorData {
    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("data")
    @Expose
    private List<SponsorSet> data = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public SponsorData() {
    }

    /**
     *
     * @param data
     * @param success
     */
    public SponsorData(String success, List<SponsorSet> data) {
        super();
        this.success = success;
        this.data = data;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<SponsorSet> getData() {
        return data;
    }

    public void setData(List<SponsorSet> data) {
        this.data = data;
    }

}
