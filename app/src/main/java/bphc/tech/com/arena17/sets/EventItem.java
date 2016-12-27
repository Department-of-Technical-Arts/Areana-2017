package bphc.tech.com.arena17.sets;

/**
 * Created by tejeshwar on 26/12/16.
 */

public class EventItem {

    String name;
    int image;

    public EventItem(String name, int image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
