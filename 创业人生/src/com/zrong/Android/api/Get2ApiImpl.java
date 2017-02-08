package com.zrong.Android.api;

import java.util.ArrayList;

import android.util.Log;

import com.zrong.Android.entity.Area;
import com.zrong.Android.entity.InfoList;
import com.zrong.Android.game.ConstructData;
import com.zrong.Android.game.GameDef;
import com.zrong.Android.online.network.GameProtocol;

public class Get2ApiImpl implements IGet2Api {

	private String doGet(String query) throws WSError {
		return ConnectionCaller.doGet(query);
	}

	public InfoList getInfoList(String infoMark, String index, String level)
			throws WSError {
		Log.i("Log", "Get2ApiImpl===");
		InfoList infoList = new InfoList();
		String infoStr = null;
		if (index == null || level == null) {
			return null;
		}

		ConnectionCaller connectionCaller = new ConnectionCaller();
		infoStr = connectionCaller.startURLPost("http://112.25.14.51/GameSrc/string/schoolXML.php", ConstructData
				.getXMLRequest(new String[] { "msgType", "appid", "channel",
						"index", "level", "page", "size", "order" },
						new String[] { "showSchoolInfo", "10001", "8000",
								index, level, "1", "-1", "1" }));
		
		 /* String infoStr2 = connectionCaller.startURLPost( "http://112.25.14.51/GameSrc/string/mapXML.php",
		  ConstructData.getXMLRequest(new String[] { "msgType", "appid",
		  "channel" }, new String[] { "mapInfo", "10001", "9500" }));
		  Log.i("Log", infoStr2==null?"null":infoStr2);*/
		 

		Log.i("Log", "getInfoList====");

		if (infoStr == null) {
			return null;
		}
		Log.i("Log", infoStr);
		infoList.setInfoList(XMLParseUtil.readStringXmlToInfoList(infoStr));
		if (infoList.getInfoList() == null || infoList.getInfoList().isEmpty()) {
			return null;
		}
		return infoList;

	}

	 
	public ArrayList<Area> getMapInfo() throws WSError {
		String infoStr = doGet(ventureSchoolForMapURL);
		if (infoStr == null) {
			return null;
		}
		 Log.i("Log", infoStr);
		return XMLParseUtil.readStringXmlToMapInfo(infoStr);
	}

	// 创业学堂获取地图信息URL地址-当乐
	private static String ventureSchoolForMapURL = "http://112.25.14.51/GameSrc/string/mapXML.php?msgType=mapInfo&appid=10001&channel=9500";
	// 创业学堂获取地图信息URL地址-移动
	private static String ventureSchoolForMapURL2 = "http://112.25.14.51/ GameSrc_cmcc/string/mapXML.php?msgType=mapInfo&appid=10001&channel=9500";

}
