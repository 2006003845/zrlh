package com.example.databasedemo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;

public class App implements Serializable {

	private static final long serialVersionUID = -6002589861348172331L;

	public View view;

	public String appIndex;// 游戏索引
	public String appClass;// 分类
	public String appName;// 游戏名称
	public String appPack;// 游戏包名
	public String appComp;// 游戏公司
	public String appPrice = "0";// 游戏价格
	public int appChargeForm;// 游戏收费形式
	public String appVer;// 游戏版本
	public String appExp;// 游戏简介
	public String appImgUrl;// 游戏图片地址
	public int appHit;// 游戏下载量
	public int appTop;// 游戏排行
	public int appInst;// 游戏安装数量
	public int apptar;// 游戏数量
	public int appize;// 游戏大小
	public String appDown;// 游戏下载地址
	public String appupp;// 支持
	public String appOn;// 反对率
	public String[] gImgUrls;// 截图

	public String appAppid;// 游戏包产品ID

	public int gStandard;

	public int fileSize = 1;
	public int progress = 0;
	public boolean isStartLoad = false;
	public boolean isLoading = false;
	public Drawable appDrawable;
	public Bitmap appIconBm;
	public boolean isDone = false;

	public int iconResId = -1;



	public App() {
	}

	public App(String name) {
		appName = name;
	}

	public App(JSONObject json) throws JSONException {
		
	}

	public static List<App> getappItemList(JSONArray array)
			throws JSONException {
		int size = array.length();
		List<App> appItemList = new ArrayList<App>(size);
		for (int i = 0; i < size; i++) {
			JSONObject jsonO = array.getJSONObject(i);
			App gi = new App(jsonO);
			appItemList.add(gi);
		}
		return appItemList;
	}

	public static final int app_OPERATION_LAUNCH = 3;
	public static final int app_OPERATION_INSTALL = 2;
	public static final int app_OPERATION_DOWNLOAD = 1;

	public static class appTable {
		public static final String TAB_NAME = "load_app";
		// 字段名
		public static final String _ID = "_id";
		public static final String app_ID = "app_index";
		public static final String app_NAME = "app_name";
		public static final String app_pack = "app_pack";
		public static final String app_comp = "app_comp";
		public static final String app_price = "app_price";
		public static final String app_chargef = "app_chargef";
		public static final String app_class = "app_class";
		public static final String app_ver = "app_ver";
		public static final String app_exp = "app_exp";
		public static final String app_icon = "app_icon";
		public static final String app_star = "app_star";
		public static final String app_size = "app_size";
		public static final String app_down = "app_down";
		public static final String app_supp = "app_supp";
		public static final String app_on = "app_on";
		public static final String app_urls = "app_urls";
		public static final String app_hit = "app_hit";
		public static final String app_top = "app_top";
		public static final String app_inst = "app_inst";
		public static final String app_chargeR = "app_chargeR";

		public static final int app_INDEX_ID = 1;
		public static final int app_INDEX_NAME = 2;
		public static final int app_INDEX_pack = 3;
		public static final int app_INDEX_comp = 4;
		public static final int app_INDEX_price = 5;
		public static final int app_INDEX_chargef = 6;
		public static final int app_INDEX_class = 7;
		public static final int app_INDEX_ver = 8;
		public static final int app_INDEX_exp = 9;
		public static final int app_INDEX_icon = 10;
		public static final int app_INDEX_star = 11;
		public static final int app_INDEX_size = 12;
		public static final int app_INDEX_down = 13;
		public static final int app_INDEX_supp = 14;
		public static final int app_INDEX_on = 15;
		public static final int app_INDEX_urls = 16;
		public static final int app_INDEX_hit = 17;
		public static final int app_INDEX_top = 18;
		public static final int app_INDEX_inst = 19;
		public static final int app_INDEX_chargeR = 20;

		public static App getapp(Cursor cursor) {
			// cursor.moveToFirst();
			if (cursor.getCount() == 0) {
				// cursor.close();
				return null;
			}
			App item = new App();
			item.appIndex = cursor.getString(app_INDEX_ID);
			item.appName = cursor.getString(app_INDEX_NAME);
			item.appPack = cursor.getString(app_INDEX_pack);
			item.appComp = cursor.getString(app_INDEX_comp);
			item.appPrice = cursor.getString(app_INDEX_price);
			item.appChargeForm = cursor.getInt(app_INDEX_chargef);
			item.appClass = cursor.getString(app_INDEX_class);
			item.appVer = cursor.getString(app_INDEX_ver);
			item.appExp = cursor.getString(app_INDEX_exp);
			item.appImgUrl = cursor.getString(app_INDEX_icon);
			item.apptar = cursor.getInt(app_INDEX_star);
			item.appize = cursor.getInt(app_INDEX_size);
			item.appDown = cursor.getString(app_INDEX_down);
			item.appupp = cursor.getString(app_INDEX_supp);
			item.appOn = cursor.getString(app_INDEX_on);
			String urls = cursor.getString(app_INDEX_urls);
			if (urls != null && !urls.equals("")) {
				item.gImgUrls = urls.split("\\|");
			}
			item.appHit = cursor.getInt(app_INDEX_hit);
			item.appTop = cursor.getInt(app_INDEX_top);
			item.appInst = cursor.getInt(app_INDEX_inst);
		
			// cursor.close();
			return item;
		}

		public static List<App> getappList(Cursor cursor) {
			cursor.moveToFirst();
			int count = cursor.getCount();
			ArrayList<App> list = new ArrayList<App>();
			do {
				if (count == 0) {
					break;
				}
				App item = App.appTable.getapp(cursor);
				if (item != null) {
					list.add(item);
				}
			} while (cursor.moveToNext());
			cursor.close();
			return list;
		}
	}
}
