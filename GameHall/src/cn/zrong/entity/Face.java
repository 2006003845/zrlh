package cn.zrong.entity;

import java.util.HashMap;

import android.content.Context;
import cn.zrong.activity.R;

public class Face {
	public static String[] faceNames = new String[] { "酷", "困", "衰", "晕", "闪",
			"心", "门牙", "可爱", "猪", "大哭", "无事", "微笑", "无视", "龇牙", "心碎", "难过",
			"爱你", "ok", "媚眼", "握手", "2", "大拇指", "小拇指", "过来", "拳头", "奥特曼", "88",
			"帅", "高兴", "大骂", "惊讶", "馋", "敲打", "怒", "闭嘴", "鼓掌", "色", "害羞", "不屑",
			"挖鼻孔", "可怜", "白眼", "财迷", "偷笑", "窘", "大晕", "睡", "纳闷", "恶心", "小声",
			"右哼哼", "左哼哼", "乖", "nono", "大怒", "鄙视", "思考", "委屈", "亲亲" };

	private static HashMap<String, Integer> faces;

	public static HashMap<String, Integer> getfaces(Context context) {
		int len = faceNames.length;
		if (faces != null) {
			return faces;
		}
		faces = new HashMap<String, Integer>();
		String faceName = "";
		for (int i = 1; i < len; i++) {
			faceName = "face" + i;
			try {
				int id = R.drawable.class.getDeclaredField(faceName).getInt(
						context);
				faces.put(faceNames[i - 1], id);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			}
		}

		return faces;
	}
}
