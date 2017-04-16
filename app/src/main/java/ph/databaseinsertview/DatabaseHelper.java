package ph.databaseinsertview;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 0_0 on 12/29/2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper
{
    public static String dbName = "USERS.db" ;
    public static  int dbVersion = 1;


    public DatabaseHelper(Context context)
    {
        super(context,dbName,null,dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {

        db.execSQL("CREATE TABLE USERS (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, email TEXT, password TEXT );");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }

    public Cursor getAllRows(SQLiteDatabase db)
    {
        Cursor cursor = db.query(true,"USERS",new String[]{"_id","name","email","password"},null,null,null,null,null,null);

        if (cursor != null)
        {
            cursor.moveToFirst();
        }

        return cursor;
    }
}
