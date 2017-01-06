package bphc.tech.com.arena17.sets;

/**
 * Created by tejeshwar on 6/1/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SponsorSet {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("spons_url")
    @Expose
    private String sponsUrl;
    @SerializedName("updated_at")
    @Expose
    private long updatedAt;

    /**
     * No args constructor for use in serialization
     *
     */
    public SponsorSet() {
    }

    /**
     *
     * @param updatedAt
     * @param title
     * @param sponsUrl
     * @param url
     */

    public SponsorSet(String title, String url, String sponsUrl, long updatedAt) {
        this.title = title;
        this.url = url;
        this.sponsUrl = sponsUrl;
        this.updatedAt = updatedAt;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSponsUrl() {
        return sponsUrl;
    }

    public void setSponsUrl(String sponsUrl) {
        this.sponsUrl = sponsUrl;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }
}