package cn.zrong.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import json.JSONArray;
import json.JSONException;
import json.JSONObject;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import cn.zrong.connection.Protocol;

public class GameItem implements Serializable {

	private static final long serialVersionUID = -6002589861348172331L;

	public String gameIndex;// 游戏索引
	public String gameClass;// 分类
	public String gameName;// 游戏名称
	public String gamePack;// 游戏包名
	public String gameComp;// 游戏公司
	public String gameVer;// 游戏版本
	public String gameExp;// 游戏简介
	public String gameImgUrl;// 游戏图片地址
	public int gameHit;// 游戏下载量
	public int gameTop;// 游戏排行
	public int gameInst;// 游戏安装数量
	public int gameStar;// 游戏数量
	public int gameSize;// 游戏大小
	public String gameDown;// 游戏下载地址
	public String gameSupp;// 支持
	public String gameOn;// 反对率
	
	public int fileSize=1 ;
	public int progress = 0;
	public boolean isLoading = false;
	public Drawable gameDrawable;
	public Bitmap gameIconBm;
	

	public GameItem() {
	}
	
	public GameItem(String name) {
		gameName = name;
	}

	public GameItem(JSONObject json) throws JSONException {
		if (!json.isNull(Protocol.ProtocolKey.KEY_gIndex)) {
			gameIndex = json.getString(Protocol.ProtocolKey.KEY_gIndex);
		}
		if (!json.isNull(Protocol.ProtocolKey.KEY_gClass)) {
			gameClass = json.getString(Protocol.ProtocolKey.KEY_gClass);
		}
		if (!json.isNull(Protocol.ProtocolKey.KEY_gName)) {
			gameName = json.getString(Protocol.ProtocolKey.KEY_gName);
		}
		if (!json.isNull(Protocol.ProtocolKey.KEY_gPack)) {
			gamePack = json.getString(Protocol.ProtocolKey.KEY_gPack);
		}
		if (!json.isNull(Protocol.ProtocolKey.KEY_gCom)) {
			gameComp = json.getString(Protocol.ProtocolKey.KEY_gCom);
		}
		if (!json.isNull(Protocol.ProtocolKey.KEY_gVer)) {
			gameVer = json.getString(Protocol.ProtocolKey.KEY_gVer);
		}
		if (!json.isNull(Protocol.ProtocolKey.KEY_gExp)) {
			gameExp = json.getString(Protocol.ProtocolKey.KEY_gExp);
		}
		if (!json.isNull(Protocol.ProtocolKey.KEY_gImg)) {
			gameImgUrl = json.getString(Protocol.ProtocolKey.KEY_gImg);
		}
		if (!json.isNull(Protocol.ProtocolKey.KEY_gHit)) {
			gameHit = Integer.parseInt(json
					.getString(Protocol.ProtocolKey.KEY_gHit));
		}
		if (!json.isNull(Protocol.ProtocolKey.KEY_gTop)) {
			gameTop = Integer.parseInt(json
					.getString(Protocol.ProtocolKey.KEY_gTop));
		}

		if (!json.isNull(Protocol.ProtocolKey.KEY_gInst)) {
			gameInst = Integer.parseInt(json
					.getString(Protocol.ProtocolKey.KEY_gInst));
		}
		if (!json.isNull(Protocol.ProtocolKey.KEY_gStar)) {
			gameStar = Integer.parseInt(json
					.getString(Protocol.ProtocolKey.KEY_gStar));
		}
		if (!json.isNull(Protocol.ProtocolKey.KEY_gSize)) {
			gameSize = Integer.parseInt(json
					.getString(Protocol.ProtocolKey.KEY_gSize));
		}
		if (!json.isNull(Protocol.ProtocolKey.KEY_gDown)) {
			gameDown = json.getString(Protocol.ProtocolKey.KEY_gDown);
		}
		if (!json.isNull(Protocol.ProtocolKey.KEY_gSupp)) {
			gameSupp = json.getString(Protocol.ProtocolKey.KEY_gSupp);
		}
		if (!json.isNull(Protocol.ProtocolKey.KEY_gOn)) {
			gameOn = json.getString(Protocol.ProtocolKey.KEY_gOn);
		}
	}

	public static List<GameItem> getGameItemList(JSONArray array)
			throws JSONException {
		int size = array.length();
		List<GameItem> gameItemList = new ArrayList<GameItem>(size);
		for (int i = 0; i < size; i++) {
			JSONObject jsonO = array.getJSONObject(i);
			GameItem gi = new GameItem(jsonO);
			gameItemList.add(gi);
		}
		return gameItemList;
	}
}
