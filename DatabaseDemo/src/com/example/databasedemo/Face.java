package com.example.databasedemo;

import java.util.HashMap;

import android.content.Context;
import android.graphics.Bitmap;

public class Face {
	public static String[] faceNames = new String[] { "酷", "困", "衰", "晕", "闪",
			"心", "门牙", "可爱", "猪", "大哭", "无事", "微笑", "无视", "龇牙", "心碎", "难过",
			"爱你", "ok", "媚眼", "握手", "2", "大指", "小指", "过来" };

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
				Bitmap bm = ImageUtils.getBitmap(context, faceName);
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
