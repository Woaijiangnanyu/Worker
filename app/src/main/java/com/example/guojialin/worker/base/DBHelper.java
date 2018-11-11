package com.example.guojialin.worker.base;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.guojialin.worker.table.UserTable;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "lin_worker";
    private static final int VERSION = 1;
    public static SQLiteDatabase dbInstance = null;
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        UserTable.createTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public static synchronized SQLiteDatabase getDbInstance() {
        if (dbInstance == null || !dbInstance.isOpen()) {
            DBHelper dbOpenHelper = new DBHelper(TApplication.getInstance());
            dbInstance = dbOpenHelper.getWritableDatabase();
        }
        return dbInstance;
    }

    public static synchronized boolean closeDB() {
        if (dbInstance == null) {
            return true;
        }
        if (dbInstance.isOpen() && !dbInstance.isDbLockedByOtherThreads()) {
            dbInstance.close();
        }
        return !dbInstance.isOpen();
    }
}
