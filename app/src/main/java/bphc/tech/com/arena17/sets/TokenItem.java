package bphc.tech.com.arena17.sets;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tejeshwar on 4/1/17.
 */

public class TokenItem {

    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("error_msg")
    @Expose
    private String errorMsg;

    /**
     * No args constructor for use in serialization
     *
     */
    public TokenItem() {
    }

    /**
     *
     * @param errorMsg
     * @param success
     */
    public TokenItem(String success, String errorMsg) {
        super();
        this.success = success;
        this.errorMsg = errorMsg;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
