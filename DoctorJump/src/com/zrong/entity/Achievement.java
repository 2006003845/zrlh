package com.zrong.entity;

import java.util.ArrayList;

import android.database.Cursor;

public class Achievement {
	public int ach_id;
	public int ach_img;
	public int ach_condition;
	public int ach_lock;
	public int ach_award;

	/**
	 * 成就
	 * 
	 * @param ach_id
	 *            编号
	 * @param ach_img
	 *            图片资源
	 * @param ach_condition
	 *            条件
	 * @param ach_lock
	 *            是否解锁
	 * @param ach_award
	 *            奖励
	 */
	public Achievement(int ach_id, int ach_img, int ach_condition,
			int ach_lock, int ach_award) {
		super();
		this.ach_id = ach_id;
		this.ach_img = ach_img;
		this.ach_condition = ach_condition;
		this.ach_lock = ach_lock;
		this.ach_award = ach_award;
	}

	public Achievement() {

	}

	public static final int LOCKED = 0;
	public static final int UNLOCK = 1;

	public static final int CONDITION_1 = 0;// 任意关卡三星级别
	public static final int CONDITION_2 = 1;// 36颗星
	public static final int CONDITION_3 = 2;// 关卡模式通关
	public static final int CONDITION_4 = 3;// 金币达到1000
	public static final int CONDITION_5 = 4;// 金币达到5000
	public static final int CONDITION_6 = 5;// 限时模式达到5000分
	public static final int CONDITION_7 = 6;// 限时模式达到10000分
	public static final int CONDITION_8 = 7;// 无尽模式坚持5分钟
	public static final int CONDITION_9 = 8;// 无尽模式坚持15分钟
	public static final int CONDITION_10 = 9;// 购买全部蹦床
	public static final int CONDITION_11 = 10;// 购买全部场景
	public static final int CONDITION_12 = 11;// 购买全部的人物形象
	public static final int CONDITION_13 = 12;// 购买全部商品
	public static final int CONDITION_14 = 13;// 获得所有成就

	public static final int AWARD_NOTHING = 0;// 无奖励
	public static final int AWARD_OPENRAINROST = 1;// 打开彩虹桥技能
	public static final int AWARD_500 = 2;// 奖励500金币
	public static final int AWARD_ADDBED = 3;// 添加蹦床
	public static final int AWARD_ADDSCENE = 4;// 添加深山场景
	public static final int AWARD_ADDNPC = 5;// 添加黑衣人
	public static final int AWARD_REDUCECOOLING = 6;// 减少技能键冷却时间
	public static final int AWARD_ADDPROP10 = 7;// 增益PROP增加10%

	public static final String ACH_ID = "ach_id";
	public static final String ACH_IMG = "ach_img";
	public static final String ACH_COND = "ach_condition";
	public static final String ACH_LOCK = "ach_isLocking";
	public static final String ACH_AWARD = "ach_award";

	public static final int INDEX_ID = 1;
	public static final int INDEX_IMG = 2;
	public static final int INDEX_COND = 3;
	public static final int INDEX_LOCK = 4;
	public static final int INDEX_AWARD = 5;

	public static final String TAB_NAME = "tab_ach";

	public static ArrayList<Achievement> getachList(Cursor cursor) {
		cursor.moveToFirst();
		ArrayList<Achievement> list = new ArrayList<Achievement>();
		int count = cursor.getCount();
		do {
			if (count == 0) {
				break;
			}
			Achievement ach = new Achievement();
			ach.ach_id = cursor.getInt(INDEX_ID);
			ach.ach_img = cursor.getInt(INDEX_IMG);
			ach.ach_condition = cursor.getInt(INDEX_COND);
			ach.ach_lock = cursor.getInt(INDEX_LOCK);
			ach.ach_award = cursor.getInt(INDEX_AWARD);

			list.add(ach);
		} while (cursor.moveToNext());
		cursor.close();
		return list;
	}
	

}
