package cn.zrong.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.text.format.Time;
import android.util.Log;

public class TimeUtil {

	public static String getTimeStr(Date date) {
		Time today = new Time("GMT+8");
		today.setToNow();
		Date d = new Date();

		Log.i("Loh", "today" + d.getTime());
		Log.i("Loh2", "today" + d.toLocaleString());
		Log.i("Loh", "date" + date.getTime());
		Log.i("Loh", "date2" + date.toLocaleString());
		Log.i("Loh", "------------------------------");
		// long interval = today.toMillis(true) - date.getTime();
		long interval = d.getTime() - date.getTime();
		interval = interval < 0 ? -interval : interval;
		boolean isSameDay = (interval / 86400000) == 0 ? true : false;
		if (isSameDay) {
			if (interval < 60 * 1000) {
				return (interval / 1000) + "秒前";
			} else if (interval < 60 * 60 * 1000) {
				return (interval / (60 * 1000)) + "分前";
			} else {
				return (interval / (60 * 60 * 1000)) + "小时前";
			}
		} else {
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd HH:mm",
					Locale.CHINA);
			return dateFormat.format(date);
		}
	}

	public static String getTimeStr(long date) {
		return getTimeStr(new Date(date));
	}

	void log(String msg) {
		Log.i("weibo", "TimeCalculator--" + msg);
	}
}
