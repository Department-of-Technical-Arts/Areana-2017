package org.arenatest.bits.arena_test.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import org.arenatest.bits.arena_test.sets.ContactItem;
import org.arenatest.bits.arena_test.sets.EventsSet;
import org.arenatest.bits.arena_test.sets.FeedItem;
import org.arenatest.bits.arena_test.sets.MedalSet;
import org.arenatest.bits.arena_test.sets.PrizeItem;
import org.arenatest.bits.arena_test.sets.ScheduleSet;
import org.arenatest.bits.arena_test.sets.SponsorSet;

/**
 * Created by tejeshwar on 20/12/16.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "appdb";
    private static final int DB_VERSION = 1;
    private SQLiteDatabase db;

    //column common to all tables
    private final String KEY_ID = "_id";

    //Table Names
    private final String EVENTS_ID_TABLE = "maintable";
    private final String EVENTS_TABLE = "events";
    private final String SCHEDULE_TABLE = "schedule";
    private final String MEDAL_TABLE = "medals";
    private final String FEED_TABLE = "feed";
    private final String SPONSORS_TABLE = "sponsor";

    //Column Names for Main Table
    private final String KEY_MAIN_TABLE_ID = "eventid";
    private final String KEY_MAIN_EVENT_NAMES = "eventname";

    //Column Names FOR EVENTS TABLE
    private final String KEY_EVENTS_ID = "eventsid";
    private final String KEY_EVENTS_NAME = "name";
    private final String KEY_EVENTS_CAPTAIN = "captain";
    private final String KEY_EVENTS_CONTACT = "contact";
    private final String KEY_EVENTS_IMAGE = "image";
    private final String KEY_EVENTS_GENDER = "gender";
    private final String KEY_EVENTS_FAVOURITE = "favourite";
    private final String KEY_EVENTS_PDF = "pdf";
    private final String KEY_EVENTS_LONGITUDE = "longitude";
    private final String KEY_EVENTS_LATITUDE = "latitude";
    private final String KEY_EVENTS_UPDATED = "updated";
    private final String KEY_EVENTS_PRIZE = "prize";

    //COLUMN NAMES FOR SCHEDULE TABLE
    private final String KEY_SCHEDULE_EVENT_ID = "eventsid";
    private final String KEY_SCHEDULE_TIME = "time";
    private final String KEY_SCHEDULE_UPDATED = "updated";
    private final String KEY_SCHEDULE_EVENT_NAME = "name";
    private final String KEY_SCHEDULE_DATE = "date";
    private final String KEY_SCHEDULE_GENDER = "gender";
    private final String KEY_SCHEDULE_DESCRIPTION = "description";
    private final String KEY_SCHEDULE_VENUE = "venue";
    private final String KEY_SCHEDULE_ROUND = "round";
    private final String KEY_SCHEDULE_VS = "vs";

    //COLUMN NAMES FOR MEDAL TALLY TABLE
    private final String KEY_MEDAL_COLLEGE_NAME = "name";
    private final String KEY_MEDAL_GOLD = "gold";
    private final String KEY_MEDAL_SILVER = "silver";
    private final String KEY_MEDAL_BRONZE = "bronze";

    //COLUMN NAMES FOR FEED TABLE
    private final String KEY_FEED_ID = "feedid";
    private final String KEY_FEED_TITLE = "title";
    private final String KEY_FEED_MESSAGE = "message";
    private final String KEY_FEED_TIME = "time";

    //COLUMN NAMES FOR SPONSORS TABLE
    private final String KEY_SPONSORS_TITLE = "title";
    private final String KEY_SPONSORS_URL = "url";
    private final String KEY_SPONSORS_SPONS_URL = "sponsurl";
    private final String KEY_SPONSORS_UPDATED_AT = "updatedat";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    private long success = 0;

    public long addMedalsRow(String college, int gold,int silver,int bronze){
        db = this.getWritableDatabase();
        ContentValues cv =new ContentValues();
        cv.put(KEY_MEDAL_COLLEGE_NAME,college);
        cv.put(KEY_MEDAL_GOLD,gold);
        cv.put(KEY_MEDAL_SILVER,silver);
        cv.put(KEY_MEDAL_BRONZE,bronze);
        success = db.insert(MEDAL_TABLE,null,cv);
        db.close();
        return success;
    }

    public long addFeedRow(String feedid, String title, String message, long time){
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_FEED_ID,feedid);
        cv.put(KEY_FEED_TITLE,title);
        cv.put(KEY_FEED_MESSAGE,message);
        cv.put(KEY_FEED_TIME,time);
        success = db.insert(FEED_TABLE,null,cv);
        db.close();
        return success;
    }

    public long addEventsRow(int eventid,
                             String name,
                             String captain,
                             String contact,
                             String image,
                             String pdf,
                             String longitude,
                             String latitude,
                             long updated,
                             int prize,
                             int gender) {
        db = this.getWritableDatabase();
        Log.e("DB manager", eventid + "  " + name + "  " + captain + "  " + contact + "  "+ image + "  " + pdf + "  " + longitude + "  " + latitude + "  "+ updated + " " + prize + "  " + gender);
        ContentValues cv = new ContentValues();
        cv.put(KEY_EVENTS_NAME, name);
        cv.put(KEY_EVENTS_CAPTAIN, captain);
        cv.put(KEY_EVENTS_CONTACT, contact);
        cv.put(KEY_EVENTS_GENDER, gender);
        cv.put(KEY_EVENTS_ID, eventid);
        cv.put(KEY_EVENTS_IMAGE, image);
        cv.put(KEY_EVENTS_FAVOURITE,0);
        cv.put(KEY_EVENTS_PDF,pdf);
        cv.put(KEY_EVENTS_LONGITUDE,longitude);
        cv.put(KEY_EVENTS_LATITUDE,latitude);
        cv.put(KEY_EVENTS_UPDATED,updated);
        cv.put(KEY_EVENTS_PRIZE,prize);
        success = db.insert(EVENTS_TABLE, null, cv);
        db.close();
        return success;
    }

    public long addEventIdRow(int eventid, String name){
        db = this.getWritableDatabase();
        Log.e("add Events ID", eventid + "");
        ContentValues cv = new ContentValues();
        cv.put(KEY_MAIN_TABLE_ID,eventid);
        cv.put(KEY_MAIN_EVENT_NAMES,name);
        success = db.insert(EVENTS_ID_TABLE, null , cv);
        db.close();
        return success;
    }

    public long addScheduleRow(int eventid,
                               long time,
                               long updated,
                               String name,
                               long date,
                               int gender,
                               String description,
                               String venue,
                               String round,
                               String vs){
        db = this.getWritableDatabase();
        Log.e("add Schedue Row", eventid + "");
        ContentValues cv =new ContentValues();
        cv.put(KEY_SCHEDULE_EVENT_ID,eventid);
        cv.put(KEY_SCHEDULE_EVENT_NAME,name);
        cv.put(KEY_SCHEDULE_TIME,time);
        cv.put(KEY_SCHEDULE_UPDATED,updated);
        cv.put(KEY_SCHEDULE_DATE,date);
        cv.put(KEY_SCHEDULE_GENDER,gender);
        cv.put(KEY_SCHEDULE_DESCRIPTION,description);
        cv.put(KEY_SCHEDULE_VENUE,venue);
        cv.put(KEY_SCHEDULE_ROUND,round);
        cv.put(KEY_SCHEDULE_VS,vs);
        success = db.insert(SCHEDULE_TABLE, null , cv);
        db.close();
        return success;
    }

    public ArrayList<EventsSet> getEventData(int eventid){
        db = this.getWritableDatabase();
        Log.e("event data", eventid + "");
        ArrayList<EventsSet> eventsSets = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ EVENTS_TABLE +" WHERE " + KEY_EVENTS_ID + " = '"+ eventid +"'",null);
        if (cursor.moveToFirst()) {
            do {
                eventsSets.add(new EventsSet(
                        eventid,
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getString(8),
                        cursor.getLong(9),
                        cursor.getInt(10),
                        cursor.getInt(11),
                        cursor.getInt(12)
                ));
            }while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return eventsSets;
    }

    public ArrayList<ScheduleSet> getScheduleData(int eventid){
        db = this.getWritableDatabase();
        Log.e("schedule data", eventid + "");
        ArrayList<ScheduleSet> scheduleSets = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ SCHEDULE_TABLE +" WHERE " + KEY_SCHEDULE_EVENT_ID + " = '"+ eventid +"' ORDER BY " + KEY_SCHEDULE_DATE +" ASC ",null);
        if (cursor.moveToFirst()){
            do {
                scheduleSets.add(new ScheduleSet(
                        eventid,
                        cursor.getLong(2),
                        cursor.getLong(3),
                        cursor.getString(4),
                        cursor.getLong(5),
                        cursor.getInt(6),
                        cursor.getString(7),
                        cursor.getString(8),
                        cursor.getString(9),
                        cursor.getString(10)
                ));
            }while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return scheduleSets;
    }

    public long addSponsorData(String title,String url,String spons_url, long update_at){
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_SPONSORS_TITLE,title);
        cv.put(KEY_SPONSORS_URL,url);
        cv.put(KEY_SPONSORS_SPONS_URL,spons_url);
        cv.put(KEY_SPONSORS_UPDATED_AT,update_at);
        success = db.insert(SPONSORS_TABLE,null,cv);
        db.close();
        return success;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String CREATE_MAIN_TABLE = "CREATE TABLE IF NOT EXISTS " + EVENTS_ID_TABLE + " (" +
                KEY_MAIN_TABLE_ID + " INTEGER PRIMARY KEY, " +
                KEY_MAIN_EVENT_NAMES + " TEXT NOT NULL); ";

        final String CREATE_EVENTS_TABLE = "CREATE TABLE IF NOT EXISTS " + EVENTS_TABLE + " (" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_EVENTS_ID + " INTEGER NOT NULL REFERENCES "+EVENTS_ID_TABLE+"("+KEY_MAIN_TABLE_ID+"), " +
                KEY_EVENTS_NAME + " TEXT NOT NULL, " +
                KEY_EVENTS_CAPTAIN + " TEXT, " +
                KEY_EVENTS_CONTACT + " TEXT, " +
                KEY_EVENTS_IMAGE + " TEXT, " +
                KEY_EVENTS_PDF + " TEXT, " +
                KEY_EVENTS_LONGITUDE + " TEXT, " +
                KEY_EVENTS_LATITUDE + " TEXT, " +
                KEY_EVENTS_UPDATED+ " INTEGER UNIQUE, " +
                KEY_EVENTS_PRIZE + " INTEGER, " +
                KEY_EVENTS_GENDER + " INTEGER NOT NULL, " +
                KEY_EVENTS_FAVOURITE + " INTEGER NOT NULL); ";

        final String CREATE_SCHEDULE_TABLE = "CREATE TABLE IF NOT EXISTS " + SCHEDULE_TABLE + " (" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_SCHEDULE_EVENT_ID + " INTEGER NOT NULL REFERENCES "+EVENTS_ID_TABLE+"("+KEY_MAIN_TABLE_ID+"), " +
                KEY_SCHEDULE_TIME + " INTEGER, " +
                KEY_SCHEDULE_UPDATED + " INTEGER UNIQUE, " +
                KEY_SCHEDULE_EVENT_NAME + " TEXT NOT NULL, " +
                KEY_SCHEDULE_DATE + " INTEGER, " +
                KEY_SCHEDULE_GENDER + " INTEGER NOT NULL, " +
                KEY_SCHEDULE_DESCRIPTION + " TEXT, " +
                KEY_SCHEDULE_VENUE + " TEXT, " +
                KEY_SCHEDULE_ROUND + " TEXT, " +
                KEY_SCHEDULE_VS + " TEXT); ";

        final String CREATE_MEDAL_TABLE = "CREATE TABLE IF NOT EXISTS " + MEDAL_TABLE + " (" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_MEDAL_COLLEGE_NAME + " TEXT UNIQUE NOT NULL, " +
                KEY_MEDAL_GOLD + " INTEGER, " +
                KEY_MEDAL_SILVER + " INTEGER, " +
                KEY_MEDAL_BRONZE + " INTEGER); ";

        final String CREATE_FEEDS_TABLE = "CREATE TABLE IF NOT EXISTS " + FEED_TABLE + " (" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_FEED_ID + " TEXT UNIQUE, " +
                KEY_EVENTS_ID + " INTEGER, " +
                KEY_FEED_TITLE + " TEXT, " +
                KEY_FEED_MESSAGE + " TEXT, " +
                KEY_FEED_TIME + " INTEGER); ";

        final String CREATE_SPONSORS_TABLE = "CREATE TABLE IF NOT EXISTS " + SPONSORS_TABLE + " (" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_SPONSORS_TITLE + " TEXT, " +
                KEY_SPONSORS_URL + " TEXT UNIQUE, " +
                KEY_SPONSORS_SPONS_URL + " TEXT, " +
                KEY_SPONSORS_UPDATED_AT + " INTEGER); ";

        sqLiteDatabase.execSQL(CREATE_MAIN_TABLE);
        sqLiteDatabase.execSQL(CREATE_EVENTS_TABLE);
        sqLiteDatabase.execSQL(CREATE_SCHEDULE_TABLE);
        sqLiteDatabase.execSQL(CREATE_MEDAL_TABLE);
        sqLiteDatabase.execSQL(CREATE_FEEDS_TABLE);
        sqLiteDatabase.execSQL(CREATE_SPONSORS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + EVENTS_ID_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + EVENTS_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + SCHEDULE_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MEDAL_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + FEED_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + SPONSORS_TABLE);
        onCreate(sqLiteDatabase);
    }

    public LatLng getLocation(int eventid){
        LatLng location = null;
        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + KEY_EVENTS_LATITUDE + " , " + KEY_EVENTS_LONGITUDE +" FROM "+ EVENTS_TABLE + " WHERE " + KEY_EVENTS_ID +" = '"+eventid+"'", null);
        if (cursor.moveToFirst()){
            location = new LatLng(Double.parseDouble(cursor.getString(0)),Double.parseDouble(cursor.getString(1)));
        }
        cursor.close();
        db.close();
        return location;
    }

    public void deleteEventsTable(){
        db = this.getWritableDatabase();
        db.delete(EVENTS_TABLE,null,null);
        db.close();
    }

    public void deleteScheduleTable(){
        db = this.getWritableDatabase();
        db.delete(SCHEDULE_TABLE,null,null);
        db.close();
    }

    public void deleteMedalsTable(){
        db = this.getWritableDatabase();
        db.delete(MEDAL_TABLE,null,null);
        db.close();
    }

    public void deleteSponsorTable(){
        db = this.getWritableDatabase();
        db.delete(SPONSORS_TABLE,null,null);
        db.close();
    }

    public ArrayList<ContactItem> getEventContacts(int eventid){
        ArrayList<ContactItem> contacts = new ArrayList<>();
        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + KEY_EVENTS_CAPTAIN +" , "+KEY_EVENTS_CONTACT + " , " + KEY_EVENTS_GENDER + " FROM "+ EVENTS_TABLE+ " WHERE " + KEY_EVENTS_ID + " = '" + eventid + "'", null);
        if (cursor.moveToFirst()){
            do {
                contacts.add(new ContactItem(cursor.getString(0),cursor.getString(1),cursor.getInt(2)));
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        Log.e("get Events function",contacts.size()+"");
        return contacts;
    }


    public ArrayList<MedalSet> getMedalTally(){
        ArrayList<MedalSet> medalSets = new ArrayList<>();
        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+MEDAL_TABLE, null);
        if (cursor.moveToFirst()){
            do{
                medalSets.add(new MedalSet(cursor.getString(1),cursor.getInt(2),cursor.getInt(3),cursor.getInt(4)));
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return medalSets;
    }

    public ArrayList<FeedItem> getAllFeedData(){
        ArrayList<FeedItem> feedItems = new ArrayList<>();
        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ FEED_TABLE,null);
        if (cursor.moveToFirst()){
            do {
                feedItems.add(new FeedItem(cursor.getString(4),cursor.getString(3),cursor.getLong(5),1,cursor.getInt(2)));
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return feedItems;
    }

    public ArrayList<PrizeItem> getEventPrizes(int eventid){
        ArrayList<PrizeItem> prizeItems = new ArrayList<>();
        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + KEY_EVENTS_PRIZE + " , " + KEY_EVENTS_GENDER + " FROM " + EVENTS_TABLE + " WHERE " + KEY_EVENTS_ID + " = '"+eventid+"'",null);
        if (cursor.moveToFirst()){
            do {
                prizeItems.add(new PrizeItem(cursor.getInt(1),cursor.getInt(0)));
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return prizeItems;
    }

    public ArrayList<SponsorSet> getSponsorData(){
        ArrayList<SponsorSet> sponsorSets = new ArrayList<>();
        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + SPONSORS_TABLE ,null);
        if (cursor.moveToFirst()){
            do {
                sponsorSets.add(new SponsorSet(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getLong(4)));
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return sponsorSets;
    }
}
