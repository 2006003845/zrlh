package com.zzl.zl_app.entity;

import java.util.HashMap;

import android.content.Context;
import android.graphics.Bitmap;

import com.zzl.zl_app.util.ImageUtil;

public class Face {
	public static String[] faceNames = new String[] { "酷", "睡", "抓狂", "晕", "怒",
			"色", "惊讶", "可爱", "淘气", "大哭", "困", "微笑", "无视", "龇牙", "病毒", "难过",
			"嘘", "馋", "拜拜", "哭" };

	// private static HashMap<String, Integer> faces;
	private static HashMap<String, Bitmap> faces;

	public void clearFaces() {
		faces.clear();
	}

	public static HashMap<String, Bitmap> getfaces(Context context) {
		int len = faceNames.length;
		if (faces != null) {
			return faces;
		}
		// faces = new HashMap<String, Integer>();
		faces = new HashMap<String, Bitmap>();
		String faceName = "";
		for (int i = 1; i < len; i++) {
			faceName = "face" + i + ".png";
			try {
				// int id = R.drawable.class.getDeclaredField(faceName).getInt(
				// context);
				Bitmap bm = ImageUtil.getBitmap(context, faceName);
				faces.put(faceNames[i - 1], bm);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}
		}

		return faces;
	}
}
