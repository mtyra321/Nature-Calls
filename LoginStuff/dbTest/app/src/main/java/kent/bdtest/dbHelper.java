package kent.bdtest;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class dbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "natureProfiles.db";
    public static final String TABLE_NAME = "natureTable";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "USERNAME";
    public static final String COL_4 = "PASSWORD";

    public static final String TABLE_CREATE = "CREATE TABLE natureTable (ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            "NAME TEXT NOT NULL, USERNAME TEXT NOT NULL, PASSWORD TEXT NOT NULL);";


    public dbHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    public void insertInfo(ProfileInformation pi){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_2, pi.getName());
        values.put(COL_3, pi.getUsername());
        values.put(COL_4, pi.getPassword());

        db.insert(TABLE_NAME, null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        this.onCreate(db);
    }
}
