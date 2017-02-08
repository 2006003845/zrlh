package cn.zrong.utils;

import android.content.Context;
import android.widget.Toast;

public class Tools {

	public static void showToast(Context context, String text) {
		Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
	}

	public static String getFileNameFromUrl(String url) {
		char[] chars = url.toCharArray();
		int start = 0;
		for (int i = chars.length - 1; i >= 0; i--) {
			if (chars[i] == '/') {
				start = i;
				break;
			}
		}
		int end = url.lastIndexOf(".");
		if (start < chars.length && start < end && end < chars.length) {
			return url.substring(start + 1, end);
		} else {
			return null;
		}

	}

}
