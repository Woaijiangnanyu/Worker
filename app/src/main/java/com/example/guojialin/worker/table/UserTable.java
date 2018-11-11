package com.example.guojialin.worker.table;

import android.database.sqlite.SQLiteDatabase;

public class UserTable {
    public static final String TABLE_NAME = "lin_user";
    public static final String USER_NUMBER = "u_number";
    public static final String USER_NAME = "u_name";

    public static void createTable(SQLiteDatabase db){
        db.execSQL("create table if not exists " + TABLE_NAME + " (" +
        USER_NUMBER + " long," +
        USER_NAME + " varchar" + ")");
    }
}
