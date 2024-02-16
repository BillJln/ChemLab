package com.jit.funchemistry.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.jit.funchemistry.utils.Constants;

/**
 * @author XiaNingIf
 * @date 2021/3/24
 */
public class FunChemistryDBHelper extends SQLiteOpenHelper {
    public FunChemistryDBHelper(@Nullable Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION_CODE);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String historyVideoSql = "create table " + Constants.HISTORY_VIDEO_NAME + "(" +
                Constants.HISTORY_ID + " integer primary key autoincrement, " +
                Constants.HISTORY_TITLE + " varchar," +
                Constants.HISTORY_COVER + " integer," +
                Constants.HISTORY_MAKER + " varchar," +
                Constants.HISTORY_UPDATE_TIME + " integer" +
                ")";
        db.execSQL(historyVideoSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
