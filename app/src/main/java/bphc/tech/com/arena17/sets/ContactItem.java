package bphc.tech.com.arena17.sets;

/**
 * Created by tejeshwar on 2/1/17.
 */

public class ContactItem {

    String name;
    String phone;
    int gender;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public ContactItem(String name, String phone, int gender) {
        this.name = name;

        this.phone = phone;
        this.gender = gender;
    }
}
