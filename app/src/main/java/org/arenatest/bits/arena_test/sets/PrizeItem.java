package org.arenatest.bits.arena_test.sets;

/**
 * Created by tejeshwar on 2/1/17.
 */

public class PrizeItem {

    int gender,prize;

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getPrize() {
        return prize;
    }

    public void setPrize(int prize) {
        this.prize = prize;
    }

    public PrizeItem(int gender, int prize) {

        this.gender = gender;
        this.prize = prize;
    }
}
