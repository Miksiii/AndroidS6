package pracc06_3.metropolitan.fit.rs.pracc06_3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Milan on 8/11/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper{

    public static final String DB_NAME    = "exam.db";
    public static final String DB_TABLE   = "students";
    public static final int    DB_VERSION = 1;

    public static final String KEY_ID = "ID";
    public static final String KEY_NAME = "NAME";
    public static final String KEY_INDEX = "NUM_OF_INDEX";
    public static final String KEY_POINTS = "NUM_OF_POINTS";

    private static final String CREATE_TABLE_STUDENTS = "CREATE TABLE " + DB_TABLE
            + "("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_NAME + " TEXT,"
            + KEY_INDEX + " VARCHAR,"
            + KEY_POINTS + " VARCHAR)";

    SQLiteDatabase db;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        db = this.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_STUDENTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DB_TABLE+ ";");
        onCreate(sqLiteDatabase);
    }

    public boolean add(String name, String index, String points) {

        ContentValues content = new ContentValues();
        content.put(KEY_NAME, name);
        content.put(KEY_INDEX, index);
        content.put(KEY_POINTS, points);

        long query = db.insert(DB_TABLE, null, content);

        return (query == -1) ? false : true;
    }

    public Cursor view() {
        return db.rawQuery("select * from " + DB_TABLE, null);
    }

    public boolean update(String id, String name, String index, String points) {
        ContentValues content = new ContentValues();
        content.put(KEY_NAME, name);
        content.put(KEY_INDEX, index);
        content.put(KEY_POINTS, points);

        long query = db.update(DB_TABLE, content, "ID = ?", new String[] { id });
        return true;
    }

    public int delete(String id) {
        // returns the number of deleted rows
        return db.delete(DB_TABLE, "ID = ?",new String[] {id});
    }

}
