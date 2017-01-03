
package bphc.tech.com.arena17.sets;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MedalData {

    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("data")
    @Expose
    private List<MedalSet> data = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public MedalData() {
    }

    /**
     * 
     * @param data
     * @param success
     */
    public MedalData(String success, List<MedalSet> data) {
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

    public List<MedalSet> getData() {
        return data;
    }

    public void setData(List<MedalSet> data) {
        this.data = data;
    }

}
