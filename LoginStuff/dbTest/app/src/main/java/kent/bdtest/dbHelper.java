package kent.bdtest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class dbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "natureProfiles.db";
    public static final String TABLE_NAME = "natureTable";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "USERNAME";
    public static final String COL_4 = "PASSWORD";
    SQLiteDatabase db;
    public static final String TABLE_CREATE = "CREATE TABLE natureTable (ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            "NAME TEXT NOT NULL, USERNAME TEXT NOT NULL, PASSWORD TEXT NOT NULL);";


    public dbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    public void insertInfo(ProfileInformation pi){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "SELECT * FROM natureTable";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();

        values.put(COL_1, count);
        values.put(COL_2, pi.getName());
        values.put(COL_3, pi.getUsername());
        values.put(COL_4, pi.getPassword());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public String SearchPass(String username) {
        db = this.getReadableDatabase();
        String query = "SELECT username, password FROM natureTable";
        Cursor cursor = db.rawQuery(query, null);
        String uname, pass;
        pass = "Not Found";
        if(cursor.moveToFirst()) {
            do {
                uname = cursor.getString(0);

                if(uname.equals(username)) {
                    pass = cursor.getString(1);
                    break;
                }
            } while(cursor.moveToNext());
        }

        return pass;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS natureTable");

        this.onCreate(db);
    }
}
