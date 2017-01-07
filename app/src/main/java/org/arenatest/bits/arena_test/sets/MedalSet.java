
package org.arenatest.bits.arena_test.sets;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MedalSet {

    @SerializedName("college")
    @Expose
    private String college;
    @SerializedName("gold")
    @Expose
    private int gold;
    @SerializedName("silver")
    @Expose
    private int silver;
    @SerializedName("bronze")
    @Expose
    private int bronze;

    /**
     * No args constructor for use in serialization
     * 
     */
    public MedalSet() {
    }

    /**
     * 
     * @param bronze
     * @param silver
     * @param college
     * @param gold
     */

    public MedalSet(String college, int gold, int silver, int bronze) {
        this.college = college;
        this.gold = gold;
        this.silver = silver;
        this.bronze = bronze;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getSilver() {
        return silver;
    }

    public void setSilver(int silver) {
        this.silver = silver;
    }

    public int getBronze() {
        return bronze;
    }

    public void setBronze(int bronze) {
        this.bronze = bronze;
    }
}
