package com.zrong.entity;

import android.database.Cursor;

public class Result {

	public Result() {
	}

	/**
	 * 
	 * @param floor1
	 *            1楼人数
	 * @param floor2
	 * @param floor3
	 * @param bloodvalue
	 *            剩余生命值
	 */
	public Result(int floor1, int floor2, int floor3, int bloodvalue,
			int lastplaytime) {
		super();
		this.floor1 = floor1;
		this.floor2 = floor2;
		this.floor3 = floor3;
		this.bloodvalue = bloodvalue;
		this.lastplaytime = lastplaytime;
	}

	public int floor1, floor2, floor3;// 三层楼分别的人数
	public int bloodvalue;// 生命值
	public int lastplaytime;// 持续时间

	public static final String FLOOR1 = "result_f1";
	public static final String FLOOR2 = "result_f2";
	public static final String FLOOR3 = "result_f3";
	public static final String BOOLDV = "result_blood";
	public static final String LAST_TIME = "result_lasttime";

	public static final int FLOOR1_INDEX = 1;
	public static final int FLOOR2_INDEX = 2;
	public static final int FLOOR3_INDEX = 3;
	public static final int BLOODV_INDEX = 4;
	public static final int LAST_TIME_INDEX = 5;

	public static final String TABLE_NAME = "result";

	// public static Result getResult(Cursor cursor) {
	// cursor.moveToFirst();
	// Result result = new Result();
	// result.floor1 = cursor.getInt(Result.FLOOR1_INDEX);
	// result.floor2 = cursor.getInt(Result.FLOOR2_INDEX);
	// result.floor3 = cursor.getInt(Result.FLOOR3_INDEX);
	// result.bloodvalue = cursor.getInt(Result.BLOODV_INDEX);
	// result.lastplaytime = cursor.getInt(Result.LAST_TIME_INDEX);
	// cursor.close();
	// return result;
	// }

}
