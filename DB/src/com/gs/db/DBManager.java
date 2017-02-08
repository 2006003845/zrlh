package com.gs.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
	private static DBManager mDBManagerInstance = null;
	private DBHelper dbHelper = null;
	private SQLiteDatabase mSQLiteDB = null;

	public SQLiteDatabase getmSQLiteDB() {
		return mSQLiteDB;
	}

	public void setmSQLiteDB(SQLiteDatabase mSQLiteDB) {
		this.mSQLiteDB = mSQLiteDB;
	}

	private DBManager(Context ctx) {
		if (dbHelper == null) {
			dbHelper = DBHelper.getInstance(ctx);
			mSQLiteDB = dbHelper.getReadableDatabase();
		}
	}

	public static DBManager getDBManager(Context ctx) {
		if (mDBManagerInstance == null) {
			mDBManagerInstance = new DBManager(ctx);
		}
		return mDBManagerInstance;
	}

}
