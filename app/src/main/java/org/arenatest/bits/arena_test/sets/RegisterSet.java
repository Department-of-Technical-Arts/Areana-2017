package org.arenatest.bits.arena_test.sets;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tejeshwar on 13/1/17.
 */

public class RegisterSet {

    @SerializedName("success")
    @Expose
    private int success;
    @SerializedName("uid")
    @Expose
    private String uid;
    @SerializedName("message")
    @Expose
    private String message;

    /**
     * No args constructor for use in serialization
     *
     */
    public RegisterSet() {
    }

    /**
     *
     * @param message
     * @param uid
     * @param success
     */
    public RegisterSet(int success, String uid, String message) {
        super();
        this.success = success;
        this.uid = uid;
        this.message = message;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
