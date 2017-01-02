package bphc.tech.com.arena17.sets;

/**
 * Created by tejeshwar on 26/12/16.
 */

public class EventItem {

    String name;
    int image;
    int id;

    public EventItem(String name, int image, int id) {
        this.name = name;
        this.image = image;
        this.id = id;
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

    public int getId(){return id;}
}
