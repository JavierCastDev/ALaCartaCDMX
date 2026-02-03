package com.javiercast.alacartacdmx;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ALaCartaCDMX.db";
    private static final int DATABASE_VERSION = 1;

    public AdminSQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tabla de Restaurantes
        db.execSQL("CREATE TABLE restaurant (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL)");

        // Tabla de Alimentos (Relacional)
        db.execSQL("CREATE TABLE food (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "restaurant_id INTEGER, " +
                "name TEXT NOT NULL, " +
                "price REAL NOT NULL, " +
                "description TEXT, " +
                "type TEXT NOT NULL CHECK(type IN ('food', 'drink', 'complement')), " +
                "FOREIGN KEY(restaurant_id) REFERENCES restaurant(_id) ON DELETE CASCADE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS food");
        db.execSQL("DROP TABLE IF EXISTS restaurant");
        onCreate(db);
    }
}