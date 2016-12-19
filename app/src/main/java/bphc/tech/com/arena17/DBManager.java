package bphc.tech.com.arena17;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by tejeshwar on 20/12/16.
 */

public class DBManager extends SQLiteOpenHelper {

    private static final String DB_NAME = "";
    private static int DB_VERSION = 1;
    private static final String EVENTS_TABLE = "";
    private static final String SCHEDULE_TABLE = "";

    private static final String CRE = "";


    public DBManager(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
