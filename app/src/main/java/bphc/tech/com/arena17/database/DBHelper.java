package bphc.tech.com.arena17.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import bphc.tech.com.arena17.sets.ContactItem;
import bphc.tech.com.arena17.sets.EventsSet;
import bphc.tech.com.arena17.sets.ScheduleSet;

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
        Log.d("DB manager", eventid + "");
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
                               String round){
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
        success = db.insert(SCHEDULE_TABLE, null , cv);
        db.close();
        return success;
    }

    public ArrayList<String> getAllEvents(){
        db = this.getWritableDatabase();
        ArrayList<String> events = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT "+ KEY_MAIN_EVENT_NAMES+ " FROM " + EVENTS_ID_TABLE , null);
        if (cursor.moveToFirst()) {
            do {
                events.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return events;
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
        return  eventsSets;
    }

    public ArrayList<ScheduleSet> getScheduleData(int eventid){
        db = this.getWritableDatabase();
        Log.e("schedule data", eventid + "");
        ArrayList<ScheduleSet> scheduleSets = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ SCHEDULE_TABLE +" WHERE " + KEY_SCHEDULE_EVENT_ID + " = '"+ eventid +"'",null);
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
                        cursor.getString(9)
                ));
            }while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return scheduleSets;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String CREATE_MAIN_TABLE = "CREATE TABLE IF NOT EXISTS " + EVENTS_ID_TABLE + " (" +
                KEY_MAIN_TABLE_ID + " INTEGER PRIMARY KEY, " +
                KEY_MAIN_EVENT_NAMES + " TEXT NOT NULL); ";

        final String CREATE_EVENTS_TABLE = "CREATE TABLE IF NOT EXISTS " + EVENTS_TABLE + " (" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_EVENTS_ID + " INTEGER NOT NULL REFERENCES "+EVENTS_ID_TABLE+"("+KEY_MAIN_TABLE_ID+"), " +
                KEY_EVENTS_NAME + " TEXT UNIQUE NOT NULL, " +
                KEY_EVENTS_CAPTAIN + " TEXT, " +
                KEY_EVENTS_CONTACT + " TEXT, " +
                KEY_EVENTS_IMAGE + " TEXT, " +
                KEY_EVENTS_PDF + " TEXT, " +
                KEY_EVENTS_LONGITUDE + " TEXT, " +
                KEY_EVENTS_LATITUDE + " TEXT, " +
                KEY_SCHEDULE_UPDATED + " INTEGER, " +
                KEY_EVENTS_PRIZE + " INTEGER, " +
                KEY_EVENTS_GENDER + " INTEGER NOT NULL, " +
                KEY_EVENTS_FAVOURITE + " INTEGER NOT NULL); ";

        final String CREATE_SCHEDULE_TABLE = "CREATE TABLE IF NOT EXISTS " + SCHEDULE_TABLE + " (" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_SCHEDULE_EVENT_ID + " INTEGER NOT NULL REFERENCES "+EVENTS_ID_TABLE+"("+KEY_MAIN_TABLE_ID+"), " +
                KEY_SCHEDULE_TIME + " INTEGER, " +
                KEY_SCHEDULE_UPDATED + " INTEGER, " +
                KEY_SCHEDULE_EVENT_NAME + " TEXT NOT NULL, " +
                KEY_SCHEDULE_DATE + " INTEGER, " +
                KEY_SCHEDULE_GENDER + " INTEGER NOT NULL, " +
                KEY_SCHEDULE_DESCRIPTION + " TEXT, " +
                KEY_SCHEDULE_VENUE + " TEXT, " +
                KEY_SCHEDULE_ROUND + " TEXT); ";

        final String CREATE_MEDAL_TABLE = "CREATE TABLE IF NOT EXISTS " + MEDAL_TABLE + " (" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_MEDAL_COLLEGE_NAME + " TEXT NOT NULL, " +
                KEY_MEDAL_GOLD + " INTEGER, " +
                KEY_MEDAL_SILVER + " INTEGER, " +
                KEY_MEDAL_BRONZE + " INTEGER); ";

        final String CREATE_FEEDS_TABLE = "CREATE TABLE IF NOT EXISTS " + FEED_TABLE + " (" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_FEED_ID + " TEXT UNIQUE, " +
                KEY_FEED_TITLE + " TEXT, " +
                KEY_FEED_MESSAGE + " TEXT, " +
                KEY_FEED_TIME + " INTEGER); ";

        sqLiteDatabase.execSQL(CREATE_MAIN_TABLE);
        sqLiteDatabase.execSQL(CREATE_EVENTS_TABLE);
        sqLiteDatabase.execSQL(CREATE_SCHEDULE_TABLE);
        sqLiteDatabase.execSQL(CREATE_MEDAL_TABLE);
        sqLiteDatabase.execSQL(CREATE_FEEDS_TABLE);
    }

    public int isFavourite(int eventid){
        int fav = 0;
        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + KEY_EVENTS_FAVOURITE + " FROM "+ EVENTS_TABLE + " WHERE " + KEY_EVENTS_ID +" = '"+eventid+"'",null);
        if (cursor.moveToNext()){
            do {
                fav = cursor.getInt(0);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return fav;
    }

    public void toggleFavourite(int eventid){

        ContentValues cv = new ContentValues();
        if (isFavourite(eventid) == 0){
            db = this.getWritableDatabase();
            cv.put(KEY_EVENTS_FAVOURITE,1);
            db.update(EVENTS_TABLE,cv,KEY_EVENTS_ID+ " = '" +eventid+"' ",null);
        }else {
            db = this.getWritableDatabase();
            cv.put(KEY_EVENTS_FAVOURITE,0);
            db.update(EVENTS_TABLE,cv,KEY_EVENTS_ID+ " = '" +eventid+"' ",null);
        }
        db.close();
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

    public String getPDF(int eventid){
        String pdf =null;
        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + KEY_EVENTS_PDF + " FROM "+ EVENTS_TABLE + " WHERE " + KEY_EVENTS_ID +" = '"+eventid+"'", null);
        if (cursor.moveToFirst()){
            pdf = cursor.getString(0);
        }
        cursor.close();
        db.close();
        return pdf;
    }

    public String getImage(int eventid){
        String image = null;
        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + KEY_EVENTS_IMAGE + " FROM "+ EVENTS_TABLE + " WHERE " + KEY_EVENTS_ID +" = '"+eventid+"'", null);
        if (cursor.moveToFirst()){
            image = cursor.getString(0);
        }
        cursor.close();
        db.close();
        return image;
    }

    public long getLastEventUpdatedAt(){
        long updatedat = 0;
        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + KEY_EVENTS_UPDATED + " FROM "+ EVENTS_TABLE+" ORDER BY "+ KEY_EVENTS_UPDATED +" DESC LIMIT 1", null);
        if (cursor.moveToFirst()){
            updatedat = cursor.getLong(0);
        }
        cursor.close();
        db.close();
        return updatedat;
    }

    public ArrayList<ContactItem> getEventContacts(int eventid){
        ArrayList<ContactItem> contacts = new ArrayList<>();
        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + KEY_EVENTS_CAPTAIN +" , "+KEY_EVENTS_CONTACT + " , " + KEY_EVENTS_GENDER + " FROM "+ EVENTS_TABLE+" ORDER BY "+ KEY_EVENTS_UPDATED +" DESC LIMIT 1", null);
        if (cursor.moveToFirst()){
            do {
                contacts.add(new ContactItem(cursor.getString(0),cursor.getString(1),cursor.getInt(2)));
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return contacts;
    }

    public int[] getFavouriteEvents(){
        int[] eventids = {0};
        int i =0;
        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + KEY_EVENTS_ID + " FROM " + EVENTS_TABLE + " WHERE " + KEY_EVENTS_FAVOURITE + " = '1'" ,null);
        if (cursor.moveToFirst()){
            do {
                eventids[i] = cursor.getInt(0);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return eventids;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + EVENTS_ID_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + EVENTS_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + SCHEDULE_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MEDAL_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + FEED_TABLE);
        onCreate(sqLiteDatabase);
    }
}
