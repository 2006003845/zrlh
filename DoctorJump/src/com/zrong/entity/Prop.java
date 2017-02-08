package com.zrong.entity;

import java.io.Serializable;

import android.database.Cursor;

public class Prop implements Serializable {

	private static final long serialVersionUID = -2558002106154358137L;

	public Prop() {

	}

	/**
	 * 创建道具
	 * 
	 * @param p_name
	 * @param p_efficacy
	 * @param p_imgName
	 */
	public Prop(String p_name, int p_efficacy, String p_imgName) {
		super();
		this.p_name = p_name;
		this.p_efficacy = p_efficacy;
		this.p_imgName = p_imgName;
	}

	public int p_id;
	public String p_name;
	/**
	 * 9:火焰-碰触后游戏结束无积分 0:双倍跳跃人数 1:直接增加金币 2:增加一点生命值--限时模式不可用 3:停止时间10秒--仅限时模式可用
	 * 4:降低NPC跑动&跳动速度，持续10秒 5:增加蹦床长度，持续10秒 6:缩短蹦床长度，持续10秒
	 * 7:触碰后附在蹦床上，跳跃者碰到则会高高跃起，继而跳向深处，玩家需要触碰蹦床使其失效 8:蹦床变成石板，图钉碰到后自动折断
	 */
	public int p_efficacy;// 功效
	public String p_imgName;// 图片名称

	// 列编号
	public final static int P_ID_INDEX = 0;
	public final static int P_NAME_INDEX = 1;
	public final static int P_EFFICACY_INDEX = 2;
	public final static int P_IMGNAME_INDEX = 3;

	// 字段名
	public final static String P_ID = "_id";
	public final static String P_NAME = "prop_name";
	public final static String P_EFFICACY = "prop_efficacy";
	public final static String P_IMGNAME = "prop_img";

	// table name
	public static final String TAB_NAME = "prop";

	@Override
	public String toString() {
		return "编号" + p_id + "名字" + p_name + "功效" + p_efficacy;
	}

	public static final String FIRE_USER_NAME = "prop_fire";
	public static final String DOUBLE_USER_NAME = "prop_double";
	public static final String GOLD_USER_NAME = "prop_gold";
	public static final String LIFE_USER_NAME = "prop_life";
	public static final String STOP_USER_NAME = "prop_stop";
	public static final String SLOW_USER_NAME = "prop_slow";
	public static final String EXTEND_USER_NAME = "prop_extend";
	public static final String SHORTEN_USER_NAME = "prop_shorten";
	public static final String THUMBTACK_USER_NAME = "prop_thumbtack";
	public static final String STONE_USER_NAME = "prop_stone";

	public static Prop[] getProps(Cursor cursor) {
		cursor.moveToFirst();

		int count = cursor.getCount();
		if (count == 0) {
			return null;
		}
		Prop[] props = new Prop[count];
		int i = 0;
		do {
			Prop p = new Prop();
			p.p_name = cursor.getString(Prop.P_NAME_INDEX);
			p.p_efficacy = cursor.getInt(Prop.P_EFFICACY_INDEX);
			p.p_imgName = cursor.getString(Prop.P_IMGNAME_INDEX);
			props[i++] = p;

		} while (cursor.moveToNext());
		cursor.close();
		return props;
	}

}
