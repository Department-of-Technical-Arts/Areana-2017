
package bphc.tech.com.arena17.sets;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventsSet {

    @SerializedName("uid")
    @Expose
    private String uid;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("captain_name")
    @Expose
    private String captainName;
    @SerializedName("contact")
    @Expose
    private String contact;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;

    /**
     * No args constructor for use in serialization
     * 
     */
    public EventsSet() {
    }

    /**
     * 
     * @param captainName
     * @param uid
     * @param imageUrl
     * @param name
     * @param contact
     */
    public EventsSet(String uid, String name, String captainName, String contact, String imageUrl) {
        super();
        this.uid = uid;
        this.name = name;
        this.captainName = captainName;
        this.contact = contact;
        this.imageUrl = imageUrl;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCaptainName() {
        return captainName;
    }

    public void setCaptainName(String captainName) {
        this.captainName = captainName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
