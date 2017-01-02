package bphc.tech.com.arena17.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

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

    //COLUMN NAMES FOR SCHEDULE TABLE
    private final String KEY_SCHEDULE_EVENT_ID = "eventsid";
    private final String KEY_SCHEDULE_EVENT_NAME = "name";
    private final String KEY_SCHEDULE_TIME = "time";
    private final String KEY_SCHEDULE_UPDATED = "updated";
    private final String KEY_SCHEDULE_DATE = "date";
    private final String KEY_SCHEDULE_GENDER = "gender";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    private long success = 0;

    public long addEventsRow(int eventid,
                             String name,
                             String captain,
                             String contact,
                             String image,
                             String pdf,
                             String longitude,
                             String latitude,
                             long updated,
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

    public long addScheduleRow(int eventid, String name, long time, long updated, long date, int gender){
        db = this.getWritableDatabase();
        Log.e("add Schedue Row", eventid + "");
        ContentValues cv =new ContentValues();
        cv.put(KEY_SCHEDULE_EVENT_ID,eventid);
        cv.put(KEY_SCHEDULE_EVENT_NAME,name);
        cv.put(KEY_SCHEDULE_TIME,time);
        cv.put(KEY_SCHEDULE_UPDATED,updated);
        cv.put(KEY_SCHEDULE_DATE,date);
        cv.put(KEY_SCHEDULE_GENDER,gender);
        success = db.insert(SCHEDULE_TABLE, null , cv);
        db.close();
        return success;
    }

    public ArrayList<String> getAllEvents(){
        String columns[] = {KEY_MAIN_EVENT_NAMES};
        ArrayList<String> events = new ArrayList<>();
        Cursor cursor = db.query(EVENTS_ID_TABLE,columns,null,null,null,null,null,null);
        do {
            events.add(cursor.getString(0));
        }while (cursor.moveToNext());
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
                        cursor.getInt(11)
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
                        cursor.getLong(3),
                        cursor.getLong(4),
                        cursor.getString(2),
                        cursor.getLong(5),
                        cursor.getInt(6)
                ));
            }while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return scheduleSets;
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
            cv.put(KEY_EVENTS_FAVOURITE,1);
            db = this.getWritableDatabase();
            db.update(EVENTS_TABLE,cv,KEY_EVENTS_ID+ " = '" +eventid+"' ",null);
        }else {
            cv.put(KEY_EVENTS_FAVOURITE,0);
            db = this.getWritableDatabase();
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

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String CREATE_MAIN_TABLE = "CREATE TABLE IF NOT EXISTS " + EVENTS_ID_TABLE + " (" +
                KEY_MAIN_TABLE_ID + " INTEGER PRIMARY KEY, " +
                KEY_MAIN_EVENT_NAMES + " TEXT NOT NULL); ";

        final String CREATE_EVENTS_TABLE = "CREATE TABLE IF NOT EXISTS " + EVENTS_TABLE + " (" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_EVENTS_ID + " INTEGER NOT NULL REFERENCES "+EVENTS_ID_TABLE+"("+KEY_MAIN_TABLE_ID+"), " +
                KEY_EVENTS_NAME + " TEXT NOT NULL, " +
                KEY_EVENTS_CAPTAIN + " TEXT UNIQUE, " +
                KEY_EVENTS_CONTACT + " TEXT, " +
                KEY_EVENTS_IMAGE + " TEXT, " +
                KEY_EVENTS_PDF + " TEXT, " +
                KEY_EVENTS_LONGITUDE + " TEXT, " +
                KEY_EVENTS_LATITUDE + "TEXT, " +
                KEY_SCHEDULE_UPDATED + " INTEGER, " +
                KEY_EVENTS_GENDER + " INTEGER NOT NULL, " +
                KEY_EVENTS_FAVOURITE + " INTEGER NOT NULL); ";

        final String CREATE_SCHEDULE_TABLE = "CREATE TABLE IF NOT EXISTS " + SCHEDULE_TABLE + " (" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_SCHEDULE_EVENT_ID + " INTEGER NOT NULL REFERENCES "+EVENTS_ID_TABLE+"("+KEY_MAIN_TABLE_ID+"), " +
                KEY_SCHEDULE_EVENT_NAME + " TEXT NOT NULL, " +
                KEY_SCHEDULE_TIME + " INTEGER, " +
                KEY_SCHEDULE_UPDATED + " INTEGER, " +
                KEY_SCHEDULE_DATE + " INTEGER, " +
                KEY_SCHEDULE_GENDER + " INTEGER NOT NULL); ";

        sqLiteDatabase.execSQL(CREATE_MAIN_TABLE);
        sqLiteDatabase.execSQL(CREATE_EVENTS_TABLE);
        sqLiteDatabase.execSQL(CREATE_SCHEDULE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + EVENTS_ID_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + EVENTS_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + SCHEDULE_TABLE);
        onCreate(sqLiteDatabase);
    }
}
