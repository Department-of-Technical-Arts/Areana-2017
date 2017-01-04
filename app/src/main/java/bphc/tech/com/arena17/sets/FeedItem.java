package bphc.tech.com.arena17.sets;

/**
 * Created by tejeshwar on 3/1/17.
 */

public class FeedItem {

    String feedid,message,title;
    long time;

    public FeedItem(String feedid, String message, String title, long time) {
        this.feedid = feedid;
        this.message = message;
        this.title = title;
        this.time = time;
    }

    public String getFeedid() {
        return feedid;
    }

    public void setFeedid(String feedid) {
        this.feedid = feedid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
