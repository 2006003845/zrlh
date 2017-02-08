package com.zrong.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.zrong.entity.Achievement;
import com.zrong.entity.Goods;
import com.zrong.entity.Prop;
import com.zrong.entity.Stage;

public class DoctorJumpDB extends SQLiteOpenHelper {

	private Cursor contactCur;

	private static DoctorJumpDB doctorDB;

	private SQLiteDatabase sqliteDB;

	public DoctorJumpDB(Context context) {
		super(context, "zr_5555", null, 1);
	}

	// public static DoctorJumpDB newInstanceOfDB(Context context) {
	// if (doctorDB == null) {
	// doctorDB = new DoctorJumpDB(context);
	// }
	// return doctorDB;
	// }

	@Override
	public void onCreate(SQLiteDatabase db) {

		// 建表--关卡
		db.execSQL("create table " + Stage.TAB_NAME
				+ "(_id integer primary key autoincrement," + Stage.NUMB
				+ " integer," + Stage.JUMPPOINT + " integer," + Stage.NPC
				+ " integer," + Stage.SCENE + " varchar(20),"
				+ Stage.SPACING_TIME + " integer," + Stage.GAME_TIME
				+ " integer," + Stage.SKILL + " integer," + Stage.ISPASSED
				+ " integer," + Stage.STAR_NUM + " integer," + Stage.IMGID
				+ " integer)");
		// 建表--商城物品
		db.execSQL("create table " + Goods.TAB_NAME
				+ "(_id integer primary key autoincrement," + Goods.G_ID
				+ " integer," + Goods.G_TYPE + " integer," + Goods.G_NAME
				+ " integer," + Goods.G_IMGName + " varchar(20),"
				+ Goods.G_PRICE + " integer," + Goods.G_DESCRIB
				+ " varchar(50)," + Goods.G_INFO + " integer,"
				+ Goods.G_ISOWNING + " integer," + Goods.G_ISEQUIP
				+ " integer)");
		// 建表--道具
		db.execSQL("create table " + Prop.TAB_NAME + "(" + Prop.P_ID
				+ " integer primary key autoincrement," + Prop.P_NAME
				+ " varchar(20)," + Prop.P_EFFICACY + " integer,"
				+ Prop.P_IMGNAME + " varchar(20))");

		// 建表--成就
		db.execSQL("create table " + Achievement.TAB_NAME
				+ "(_id integer primary key autoincrement,"
				+ Achievement.ACH_ID + " integer," + Achievement.ACH_IMG
				+ " integer," + Achievement.ACH_COND + " integer,"
				+ Achievement.ACH_LOCK + " integer," + Achievement.ACH_AWARD
				+ " integer)");
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
	public Cursor query(String tableName, String[] columns, String where,
			String[] whereArgs, String groupBy, String having, String orderBy) {
		// create and/or open a database (只读)
		sqliteDB = getReadableDatabase();

		Cursor cursor = sqliteDB.query(tableName, columns, where, whereArgs,
				groupBy, having, orderBy);
		return cursor;

	}

	/**
	 * 插入关卡数据
	 * 
	 * @param stage
	 * @return
	 */
	public boolean insertStage(Stage stage) {
		SQLiteDatabase sqldb = getWritableDatabase();
		// sqldb.execSQL("insert into " + Stage.TAB_NAME + "(" + Stage.NUMB +
		// ","
		// + Stage.JUMPPOINT + "," + Stage.NPC + "," + Stage.SCENE + ","
		// + Stage.SPACING_TIME + "," + Stage.GAME_TIME + ","
		// + Stage.SKILL + "," + Stage.ISPASSED + "," + Stage.STAR_NUM
		// + ") values('" + stage.numb + "','" + stage.jumppoint + "','"
		// + stage.npc + "','" + stage.sceneName + "','"
		// + stage.spacingTime + "','" + stage.gameTime + "','"
		// + stage.skill + "','" + stage.isPassed + "','" + stage.starNum
		// + "')");
		ContentValues values = new ContentValues();
		values.put(Stage.NUMB, stage.numb);
		values.put(Stage.JUMPPOINT, stage.jumppoint);
		values.put(Stage.NPC, stage.npc);
		values.put(Stage.SCENE, stage.sceneName);
		values.put(Stage.SPACING_TIME, stage.spacingTime);
		values.put(Stage.GAME_TIME, stage.gameTime);
		values.put(Stage.SKILL, stage.skill);
		values.put(Stage.ISPASSED, stage.isPassed);
		values.put(Stage.STAR_NUM, stage.starNum);
		values.put(Stage.IMGID, stage.imgId);
		sqldb.insert(Stage.TAB_NAME, "_id", values);
		sqldb.close();
		return true;
	}

	/**
	 * 插入商品数据
	 * 
	 * @param goods
	 * @return
	 */
	public boolean insertGoods(Goods goods) {
		SQLiteDatabase sqldb = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(Goods.G_ID, goods.g_id);
		values.put(Goods.G_TYPE, goods.g_type);
		values.put(Goods.G_NAME, goods.g_name);
		values.put(Goods.G_IMGName, goods.g_imgName);
		values.put(Goods.G_PRICE, goods.g_price);
		values.put(Goods.G_DESCRIB, goods.g_describ);
		values.put(Goods.G_INFO, goods.g_info);
		values.put(Goods.G_ISOWNING, goods.isOwning);
		values.put(Goods.G_ISEQUIP, goods.isEquipment);
		sqldb.insert(Goods.TAB_NAME, null, values);
		sqldb.close();
		return true;
	}

	/**
	 * 插入道具数据
	 * 
	 * @param prop
	 * @return
	 */
	public boolean insertProp(Prop prop) {
		SQLiteDatabase sqldb = getWritableDatabase();
		ContentValues values = new ContentValues();
		// values.put(Prop.P_ID, prop.p_id);
		values.put(Prop.P_NAME, prop.p_name);
		values.put(Prop.P_EFFICACY, prop.p_efficacy);
		values.put(Prop.P_IMGNAME, prop.p_imgName);
		sqldb.insert(Prop.TAB_NAME, null, values);
		sqldb.close();
		return true;
	}

	/**
	 * 插入成就数据
	 * 
	 * @param Achievement
	 *            ach
	 * @return
	 */
	public boolean insertAchievement(Achievement ach) {
		SQLiteDatabase sqldb = getWritableDatabase();
		ContentValues values = new ContentValues();
		// values.put(Prop.P_ID, prop.p_id);
		values.put(Achievement.ACH_ID, ach.ach_id);
		values.put(Achievement.ACH_IMG, ach.ach_img);
		values.put(Achievement.ACH_COND, ach.ach_condition);
		values.put(Achievement.ACH_LOCK, ach.ach_lock);
		values.put(Achievement.ACH_AWARD, ach.ach_award);
		sqldb.insert(Achievement.TAB_NAME, null, values);
		sqldb.close();
		return true;
	}

	// /**
	// * 插入result 数据
	// *
	// * @param result
	// * @return
	// */
	// public boolean insertResult(Result result) {
	// SQLiteDatabase sqldb = getWritableDatabase();
	// ContentValues values = new ContentValues();
	// values.put(Result.FLOOR1, result.floor1);
	// values.put(Result.FLOOR2, result.floor2);
	// values.put(Result.FLOOR3, result.floor3);
	// values.put(Result.BOOLDV, result.bloodvalue);
	// values.put(Result.LAST_TIME, result.lastplaytime);
	// sqldb.insert(Result.TABLE_NAME, null, values);
	// sqldb.close();
	// return true;
	//
	// }
	//
	// /**
	// * 插入combo数据
	// *
	// * @param combo
	// * @return
	// */
	// public boolean insertCombo(Combo combo) {
	// SQLiteDatabase sqldb = getWritableDatabase();
	// ContentValues values = new ContentValues();
	// values.put(Combo.COMB_COLUMN, combo.score);
	// values.put(Combo.COMB_INDEX_COLUMN, combo.combindex);
	// sqldb.insert(Combo.TABLE_NAME, null, values);
	// sqldb.close();
	// return true;
	// }

	/**
	 * 修改表stage updata
	 * 
	 * @param numb
	 * @param isPassed
	 * @param starNum
	 * @return
	 */
	public boolean updataStage(int numb, int isPassed, int starNum) {
		SQLiteDatabase sqldb = getWritableDatabase();
		// sqldb.execSQL("update " + Stage.TAB_NAME + " set " + Stage.ISPASSED
		// + "='" + isPassed + "'," + Stage.STAR_NUM + "='" + starNum
		// + "' where " + Stage.NUMB + "='" + numb + "'");
		ContentValues values = new ContentValues();
		values.put(Stage.ISPASSED, isPassed);
		values.put(Stage.STAR_NUM, starNum);
		sqldb.update(Stage.TAB_NAME, values, Stage.NUMB + "=" + numb, null);
		sqldb.close();
		return true;
	}

	/**
	 * 修改表goods updata
	 * 
	 * @param gId
	 * @param isOwning
	 * @param isEquipment
	 * @return
	 */
	public boolean updataGoods(int gId, int isOwning, int isEquipment) {
		SQLiteDatabase sqldb = getWritableDatabase();
		ContentValues values = new ContentValues();
		if (isOwning != -1) {
			values.put(Goods.G_ISOWNING, isOwning);
		}
		if (isEquipment != -1) {
			values.put(Goods.G_ISEQUIP, isEquipment);
		}
		sqldb.update(Goods.TAB_NAME, values, Goods.G_ID + "=" + gId, null);
		sqldb.close();
		return true;
	}

	/**
	 * 修改表achievement updata
	 * 
	 * @param gId
	 * @param isOwning
	 * @param isEquipment
	 * @return
	 */
	public boolean updataAchievement(int achId, int lock) {
		SQLiteDatabase sqldb = getWritableDatabase();
		ContentValues values = new ContentValues();
		if (lock != -1) {
			values.put(Achievement.ACH_LOCK, lock);
		}
		sqldb.update(Achievement.TAB_NAME, values, Achievement.ACH_ID + "="
				+ achId, null);
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
		System.out.println("onUpgrade......");
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