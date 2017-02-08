package cn.zrong.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import cn.zrong.entity.User;
import cn.zrong.weibobinding.BindingAccount;

public class HallDB extends SQLiteOpenHelper {

	private SQLiteDatabase sqliteDB;

	private static HallDB gagaDB;

	public static HallDB getHallDBInstance(Context context) {
		if (gagaDB == null) {
			gagaDB = new HallDB(context);
		}
		return gagaDB;
	}

	private HallDB(Context context) {
		super(context, "hall02", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// 建表-type
		db.execSQL("create table  if not exists " + User.UserTable.TAB_NAME
				+ " (" + User.UserTable._ID
				+ " integer primary key autoincrement,"
				+ User.UserTable.USER_ID + " varchar(15) unique,"
				+ User.UserTable.USER_NAME + " varchar(15),"
				+ User.UserTable.USER_PSD + " varchar(20),"
				+ User.UserTable.USER_NICKNAME + " varchar(20),"
				+ User.UserTable.USER_GENDER + " varchar(2))");
		// 建表-type
		// 建表-type
		db.execSQL("create table  if not exists "
				+ BindingAccount.BindingAccountTable.TAB_NAME + " ("
				+ BindingAccount.BindingAccountTable._ID
				+ " integer primary key autoincrement,"
				+ BindingAccount.BindingAccountTable.BINDINGUSER_USERID
				+ " varchar(10),"
				+ BindingAccount.BindingAccountTable.BINDINGUSER_TYPE
				+ " varchar(15),"
				+ BindingAccount.BindingAccountTable.BINDINGUSER_TYPE_NAME
				+ " varchar(10),"
				+ BindingAccount.BindingAccountTable.BINDINGUSER_TYPE_IconURL
				+ " varchar(20),"
				+ BindingAccount.BindingAccountTable.BINDINGUSER_NEWKEY
				+ " varchar(20),"
				+ BindingAccount.BindingAccountTable.BINDINGUSER_NEWSECRET
				+ " varchar(20),"
				+ BindingAccount.BindingAccountTable.BINDINGUSER_USER_KEYID
				+ " varchar(20),"
				+ BindingAccount.BindingAccountTable.BINDINGUSER_AUTHO_STATE
				+ " integer,"
				+ BindingAccount.BindingAccountTable.BINDINGUSER_BINDING_STATE
				+ " integer,"
				+ BindingAccount.BindingAccountTable.BINDINGUSER_ConsumerKey
				+ " varchar(20),"
				+ BindingAccount.BindingAccountTable.BINDINGUSER_ConsumerSecret
				+ " varchar(20),"
				+ BindingAccount.BindingAccountTable.BINDINGUSER_OauthNonce
				+ " varchar(20),"
				+ BindingAccount.BindingAccountTable.BINDINGUSER_OauthTimestamp
				+ " varchar(20),"
				+ BindingAccount.BindingAccountTable.BINDINGUSER_OauthVerifier
				+ " varchar(20),"
				+ BindingAccount.BindingAccountTable.BINDINGUSER_OauthVersion
				+ " varchar(20))");
	}

	/**
	 * 查询表数据
	 * 
	 * @param tableName
	 *            表名
	 * @param columns
	 *            字段
	 * @param where
	 *            判断句
	 * @param whereArgs
	 *            判断字段对于的判断值
	 * @param groupBy
	 *            分组
	 * @param having
	 * @param orderBy
	 * @return
	 */
	public synchronized Cursor query(String tableName, String[] columns,
			String where, String[] whereArgs, String groupBy, String having,
			String orderBy) {
		// create and/or open a database (只读)
		sqliteDB = getReadableDatabase();
		Cursor cursor = sqliteDB.query(tableName, columns, where, whereArgs,
				groupBy, having, orderBy);
		return cursor;
	}

	public boolean insertUser(User user) {
		SQLiteDatabase sqldb = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(User.UserTable.USER_ID, user.getKeyID());
		values.put(User.UserTable.USER_NAME, user.getU_name());
		values.put(User.UserTable.USER_PSD, user.getU_psd());
		sqldb.insert(User.UserTable.TAB_NAME, User.UserTable._ID, values);
		sqldb.close();
		return true;
	}

	public boolean updateUser(User user) {
		SQLiteDatabase sqldb = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(User.UserTable.USER_ID, user.getKeyID());
		values.put(User.UserTable.USER_NAME, user.getU_name());
		values.put(User.UserTable.USER_PSD, user.getU_psd());
		sqldb.update(User.UserTable.TAB_NAME, values, User.UserTable.USER_ID
				+ "=?", new String[] { user.getKeyID() });
		sqldb.close();
		return true;
	}

	public boolean updateUserInfo(User user) {
		SQLiteDatabase sqldb = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(User.UserTable.USER_NICKNAME, user.getUserInfo().nickName);
		values.put(User.UserTable.USER_GENDER,
				user.getUserInfo().gender.toString());
		sqldb.update(User.UserTable.TAB_NAME, values, User.UserTable.USER_ID
				+ "=?", new String[] { user.getKeyID() });
		sqldb.close();
		return true;
	}

	public boolean insertBindingUser(BindingAccount user) {
		SQLiteDatabase sqldb = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(BindingAccount.BindingAccountTable.BINDINGUSER_USERID,
				user.getUserId());
		values.put(BindingAccount.BindingAccountTable.BINDINGUSER_TYPE,
				user.getType());
		values.put(BindingAccount.BindingAccountTable.BINDINGUSER_TYPE_NAME,
				user.getTypeName());
		values.put(BindingAccount.BindingAccountTable.BINDINGUSER_TYPE_IconURL,
				user.getTypeIconUrl());
		values.put(BindingAccount.BindingAccountTable.BINDINGUSER_NEWKEY,
				user.getNewKey());
		values.put(BindingAccount.BindingAccountTable.BINDINGUSER_NEWSECRET,
				user.getNewSecret());
		values.put(BindingAccount.BindingAccountTable.BINDINGUSER_USER_KEYID,
				user.getUserKeyId());
		values.put(BindingAccount.BindingAccountTable.BINDINGUSER_AUTHO_STATE,
				user.getAuthoState());
		values.put(
				BindingAccount.BindingAccountTable.BINDINGUSER_BINDING_STATE,
				user.getBindingState());
		values.put(BindingAccount.BindingAccountTable.BINDINGUSER_ConsumerKey,
				user.getConsumerKey());
		values.put(
				BindingAccount.BindingAccountTable.BINDINGUSER_ConsumerSecret,
				user.getConsumerSecret());
		values.put(BindingAccount.BindingAccountTable.BINDINGUSER_OauthNonce,
				user.getOauthNonce());
		values.put(
				BindingAccount.BindingAccountTable.BINDINGUSER_OauthTimestamp,
				user.getOauthTimestamp());
		values.put(
				BindingAccount.BindingAccountTable.BINDINGUSER_OauthVerifier,
				user.getOauthVerifier());
		values.put(BindingAccount.BindingAccountTable.BINDINGUSER_OauthVersion,
				user.getOauthVersion());
		sqldb.insert(BindingAccount.BindingAccountTable.TAB_NAME,
				BindingAccount.BindingAccountTable._ID, values);
		sqldb.close();
		return true;
	}

	public boolean updateBindingUser(BindingAccount user) {
		SQLiteDatabase sqldb = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(BindingAccount.BindingAccountTable.BINDINGUSER_NEWKEY,
				user.getNewKey());
		values.put(BindingAccount.BindingAccountTable.BINDINGUSER_NEWSECRET,
				user.getNewSecret());
		values.put(BindingAccount.BindingAccountTable.BINDINGUSER_AUTHO_STATE,
				user.getAuthoState());
		values.put(
				BindingAccount.BindingAccountTable.BINDINGUSER_BINDING_STATE,
				user.getBindingState());
		values.put(BindingAccount.BindingAccountTable.BINDINGUSER_ConsumerKey,
				user.getConsumerKey());
		values.put(
				BindingAccount.BindingAccountTable.BINDINGUSER_ConsumerSecret,
				user.getConsumerSecret());
		values.put(BindingAccount.BindingAccountTable.BINDINGUSER_OauthNonce,
				user.getOauthNonce());
		values.put(
				BindingAccount.BindingAccountTable.BINDINGUSER_OauthTimestamp,
				user.getOauthTimestamp());
		values.put(
				BindingAccount.BindingAccountTable.BINDINGUSER_OauthVerifier,
				user.getOauthVerifier());
		values.put(BindingAccount.BindingAccountTable.BINDINGUSER_OauthVersion,
				user.getOauthVersion());
		sqldb.update(BindingAccount.BindingAccountTable.TAB_NAME, values,
				BindingAccount.BindingAccountTable.BINDINGUSER_USERID + "=?",
				new String[] { user.getUserId() + "" });
		sqldb.close();
		return true;
	}

	/**
	 * 删除数据
	 * 
	 * @param id
	 * @return
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
