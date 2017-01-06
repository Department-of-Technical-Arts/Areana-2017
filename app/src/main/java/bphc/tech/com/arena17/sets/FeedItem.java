package bphc.tech.com.arena17.sets;

/**
 * Created by tejeshwar on 3/1/17.
 */

public class FeedItem {

    String message,title;
    long updated_at;
    int type,event_id;

    public FeedItem(String message, String title, long updated_at, int type, int event_id) {
        this.message = message;
        this.title = title;
        this.updated_at = updated_at;
        this.type = type;
        this.event_id = event_id;
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

    public long getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(long updated_at) {
        this.updated_at = updated_at;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }
}
