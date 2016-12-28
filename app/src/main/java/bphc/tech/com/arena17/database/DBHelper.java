package bphc.tech.com.arena17.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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

    public long addEventsRow(int eventid, String name, String captain, String contact, String image, int gender) {
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
                        cursor.getInt(6)
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
        /*ContentValues cv = new ContentValues();
        cv.put(KEY_MAIN_TABLE_ID,1);
        cv.put(KEY_MAIN_EVENT_NAMES,"Cricket");
        sqLiteDatabase.insert(EVENTS_ID_TABLE,null,cv);
        cv.put(KEY_MAIN_TABLE_ID,2);
        cv.put(KEY_MAIN_EVENT_NAMES,"Football");
        sqLiteDatabase.insert(EVENTS_ID_TABLE,null,cv);
        cv.put(KEY_MAIN_TABLE_ID,3);
        cv.put(KEY_MAIN_EVENT_NAMES,"Volleyball");
        sqLiteDatabase.insert(EVENTS_ID_TABLE,null,cv);
        cv.put(KEY_MAIN_TABLE_ID,4);
        cv.put(KEY_MAIN_EVENT_NAMES,"Badminton");
        sqLiteDatabase.insert(EVENTS_ID_TABLE,null,cv);
        cv.put(KEY_MAIN_TABLE_ID,5);
        cv.put(KEY_MAIN_EVENT_NAMES,"Basketball");
        sqLiteDatabase.insert(EVENTS_ID_TABLE,null,cv);
        cv.put(KEY_MAIN_TABLE_ID,6);
        cv.put(KEY_MAIN_EVENT_NAMES,"Kabaddi");
        sqLiteDatabase.insert(EVENTS_ID_TABLE,null,cv);
        cv.put(KEY_MAIN_TABLE_ID,7);
        cv.put(KEY_MAIN_EVENT_NAMES,"Throwball");
        sqLiteDatabase.insert(EVENTS_ID_TABLE,null,cv);
        cv.put(KEY_MAIN_TABLE_ID,8);
        cv.put(KEY_MAIN_EVENT_NAMES,"Chess");
        sqLiteDatabase.insert(EVENTS_ID_TABLE,null,cv);
        cv.put(KEY_MAIN_TABLE_ID,9);
        cv.put(KEY_MAIN_EVENT_NAMES,"Tennis");
        sqLiteDatabase.insert(EVENTS_ID_TABLE,null,cv);
        cv.put(KEY_MAIN_TABLE_ID,10);
        cv.put(KEY_MAIN_EVENT_NAMES,"Table Tennis");
        sqLiteDatabase.insert(EVENTS_ID_TABLE,null,cv);
        cv.put(KEY_MAIN_TABLE_ID,11);
        cv.put(KEY_MAIN_EVENT_NAMES,"Squash");
        sqLiteDatabase.insert(EVENTS_ID_TABLE,null,cv);
        cv.put(KEY_MAIN_TABLE_ID,12);
        cv.put(KEY_MAIN_EVENT_NAMES,"Hockey");
        sqLiteDatabase.insert(EVENTS_ID_TABLE,null,cv);
        cv.put(KEY_MAIN_TABLE_ID,13);
        cv.put(KEY_MAIN_EVENT_NAMES,"Carrom");
        sqLiteDatabase.insert(EVENTS_ID_TABLE,null,cv);
        cv.put(KEY_MAIN_TABLE_ID,14);
        cv.put(KEY_MAIN_EVENT_NAMES,"Billiards\\/Pool");
        sqLiteDatabase.insert(EVENTS_ID_TABLE,null,cv);
        cv.put(KEY_MAIN_TABLE_ID,15);
        cv.put(KEY_MAIN_EVENT_NAMES,"Athletics");
        sqLiteDatabase.insert(EVENTS_ID_TABLE,null,cv);
        cv.put(KEY_MAIN_TABLE_ID,16);
        cv.put(KEY_MAIN_EVENT_NAMES,"Body Building");
        sqLiteDatabase.insert(EVENTS_ID_TABLE,null,cv);
        cv.put(KEY_MAIN_TABLE_ID,17);
        cv.put(KEY_MAIN_EVENT_NAMES,"Duathlon");
        sqLiteDatabase.insert(EVENTS_ID_TABLE,null,cv);
        cv.put(KEY_MAIN_TABLE_ID,18);
        cv.put(KEY_MAIN_EVENT_NAMES,"Power Lifting");
        sqLiteDatabase.insert(EVENTS_ID_TABLE,null,cv);*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + EVENTS_ID_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + EVENTS_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + SCHEDULE_TABLE);
        onCreate(sqLiteDatabase);
    }
}
