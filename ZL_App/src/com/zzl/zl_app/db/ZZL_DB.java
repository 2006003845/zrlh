package com.zzl.zl_app.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.zzl.zl_app.PlatformApi;

/**
 * 
 * @author zhouzhilong
 * 
 */
public class ZZL_DB extends SQLiteOpenHelper {

	private SQLiteDatabase sqliteDB;

	private static ZZL_DB epDB;
	private static Context mContext;

	public static ZZL_DB getHallDBInstance(Context context) {
		mContext = context;
		if (epDB == null) {
			epDB = new ZZL_DB(context);
		}
		return epDB;
	}

	private ZZL_DB(Context context) {
		super(context, PlatformApi.DB, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

	}
	
	public synchronized Cursor query(String tableName, String[] columns,
			String where, String[] whereArgs, String groupBy, String having,
			String orderBy) {
		// create and/or open a database (只读)
		sqliteDB = getReadableDatabase();
		Cursor cursor = sqliteDB.query(tableName, columns, where, whereArgs,
				groupBy, having, orderBy);
		return cursor;
	}

	public synchronized Cursor query(boolean distinct, String tableName,
			String[] columns, String where, String[] whereArgs, String groupBy,
			String having, String orderBy, String limite) {
		// create and/or open a database (只读)
		sqliteDB = getReadableDatabase();
		Cursor cursor = sqliteDB.query(distinct, tableName, columns, where,
				whereArgs, groupBy, having, orderBy, limite);
		return cursor;
	}
	
	

	/**
	 * 删除数据
	 * 
	 * @param id
	 * @return
	 * @author zhouzhilong
	 */
	public boolean deleteData(String tableName, String whereClause,
			String[] whereArgs) {
		SQLiteDatabase sqldb = getWritableDatabase();
		sqldb.delete(tableName, whereClause, whereArgs);
		sqldb.close();
		return true;
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

	/**
	 * 关闭数据库
	 */
	public void closeDB() {
		if (sqliteDB != null) {
			sqliteDB.close();
			sqliteDB = null;
		}
	}
}
