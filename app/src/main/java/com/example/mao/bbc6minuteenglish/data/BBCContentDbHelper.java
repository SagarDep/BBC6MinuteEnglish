package com.example.mao.bbc6minuteenglish.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by MAO on 7/17/2017.
 */

public class BBCContentDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "bbc6minute.db";

    private static final int DATABASE_VERSION = 1;

    public BBCContentDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String createTable = "CREATE TABLE "
                + BBCContentContract.BBC6MinuteEnglishEntry.TABLE_NAME + "("
                + BBCContentContract.BBC6MinuteEnglishEntry._ID + "PRIMARY AUTOINCREMENT NOT NULL,"
                + BBCContentContract.BBC6MinuteEnglishEntry.COLUMN_TITLE + "TEXT NOT NULL,"
                + BBCContentContract.BBC6MinuteEnglishEntry.COLUMN_TIME + "TEXT NOT NULL,"
                + BBCContentContract.BBC6MinuteEnglishEntry.COLUMN_DESCRIPTION + "TEXT NOT NULL,"
                + BBCContentContract.BBC6MinuteEnglishEntry.COLUMN_ARTICLE + "TEXT NOT NULL,"
                + BBCContentContract.BBC6MinuteEnglishEntry.COLUMN_HREF + "TEXT NOT NULL,"
                + BBCContentContract.BBC6MinuteEnglishEntry.COLUMN_MP3_HREF + "TEXT NOT NULL,"

                // For test, not null disable
                + BBCContentContract.BBC6MinuteEnglishEntry.COLUMN_THUMBNAIL + "BLOB,"
                + BBCContentContract.BBC6MinuteEnglishEntry.COLUMN_TIMESTAMP + "INTEGER)";

        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        final String dropTable = "DROP TABLE IF EXISTS "
                + BBCContentContract.BBC6MinuteEnglishEntry.TABLE_NAME;
        db.execSQL(dropTable);
    }
}
