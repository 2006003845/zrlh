import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	/**
	 * @param args
	 * @throws UnsupportedEncodingException
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		String ssss = String.valueOf(1.1222222222222);
		System.out.println(String.valueOf(ssss));
		System.out.println(String.valueOf(String.valueOf(0)));
		System.out.println(String.valueOf(String.valueOf("null")));
		System.out.println(String.valueOf("null"));

		System.out.println("结果:" + URLEncoder.encode("北京", "utf-8"));
		String photo = "RXVURRVRRQVTRTWPSOWMSS";
		// System.out.println(Secret.decriptXor(photo));

		// System.out.println(URLEncoder.encode(null));
		System.out.println("结果:" + URLEncoder.encode("  "));
		System.out.println("结果:" + URLEncoder.encode("北京", "utf-8"));
		System.out.println("结果:" + URLEncoder.encode("asd", "utf-8"));
		try {
			Secret.test();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String urls = "http://u5.mm-img.com:80/rs/res1/21/2013/12/07/a472/391/32391472/picture1320x2406376015717.jpg|http://u5.mm-img.com:80/rs/res1/21/2013/12/07/a476/391/32391476/picture1320x2406376024683.jpg|http://u5.mm-img.com:80/rs/res1/21/2013/12/07/a480/391/32391480/picture1320x2406376033967.jpg|http://u5.mm-img.com:80/rs/res1/21/2013/12/07/a484/391/32391484/picture1320x2406376040010.jpg";
		System.out.println(URLEncoder.encode(urls));

		System.out
				.println("urls: "
						+ isUrl("http://u5.mm-img.com:80/rs/res1/21/2013/12/07/a472/391/32391472/picture1320x2406376015717.jpg"));
		System.out.println("head: " + isUrl("head"));
		System.out.println("ooo: " + isUrl("www.baidu.com"));
		System.out.println("baidu: " + isUrl("http://www.baidu.com"));
		try {
			System.out
					.println(URLEncoder.encode("http://www.baidu.com", "gbk"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String[] utlss = urls.split("\\|");
		for (String string : utlss) {
			System.out.print("\n" + string);
		}
		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
		for (int i = 0; i < 5; i++) {
			Map<String, String> m = new HashMap<String, String>();
			m.put("obj", "sss" + i);
			mapList.add(m);
		}
		StringBuffer sb = new StringBuffer();
		for (Map<String, String> mat : mapList) {
			String s = mat.get("obj");
			sb.append(s);
			sb.append(",");
		}

		System.out.println(sb.toString().substring(0,
				sb.length() > 0 ? sb.length() - 1 : 0));

		String text = "%E5%8D%97%E6%9E%81";
		System.out.println(tozhCN(text));

		System.out.println(toUnicode("你好"));

		A a = new A();
		B b = new B();
		b.save(a);
		System.out.println(getTimeStr2(new Date(), "yyyy-MM-dd"));

		String now = getTimeStr2(new Date(), "yyyy-MM-dd");
		String last = "25-12-15";
		System.out.println(needUpdateTime(last, now));
	}

	public static boolean needUpdateTime(String lastDate, String nowDate) {
		String[] nows = nowDate.split("-");
		String[] lasts = lastDate.split("-");
		if (nows.length == 3 && lasts.length == 3) {
			int nowYear = Integer.parseInt(nows[0]);
			int nowMonth = Integer.parseInt(nows[1]);
			int nowDay = Integer.parseInt(nows[2]);
			int lastYear = Integer.parseInt(lasts[0]);
			int lastMonth = Integer.parseInt(lasts[1]);
			int lastDay = Integer.parseInt(lasts[2]);
			if (nowYear > lastYear)
				return true;
			else if (nowYear == lastYear) {
				if (nowMonth > lastMonth)
					return true;
				else if (nowMonth == lastMonth) {
					if (nowDay > lastDay)
						return true;
				}
			}
		} else {
			return false;
		}
		return false;
	}

	public static String getTimeStr2(Date date, String format) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.CHINA);
		return dateFormat.format(date);

	}

	public static String toUnicode(String zhStr) {
		StringBuffer unicode = new StringBuffer();
		for (int i = 0; i < zhStr.length(); i++) {
			char c = zhStr.charAt(i);
			unicode.append("\\u" + Integer.toHexString(c));
		}
		return unicode.toString().toString();
	}

	public static String tozhCN(String unicode) {
		StringBuffer gbk = new StringBuffer();
		String[] hex = unicode.split("\\\\u"); // 妈的，分割让我想了半天！！不是"\\u"，而是
												// "\\\\u"
		for (int i = 1; i < hex.length; i++) { // 注意要从 1 开始，而不是从0开始。第一个是空。
			int data = Integer.parseInt(hex[i], 16); // 将16进制数转换为 10进制的数据。
			gbk.append((char) data); // 强制转换为char类型就是我们的中文字符了。
		}

		return gbk.toString();
	}

	public static boolean isUrl(String url) {
		if (url == null) {
			return false;
		}
		String regEx = "^(http|https|ftp)\\://([a-zA-Z0-9\\.\\-]+(\\:[a-zA-"
				+ "Z0-9\\.&%\\$\\-]+)*@)?((25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{"
				+ "2}|[1-9]{1}[0-9]{1}|[1-9])\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}"
				+ "[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|"
				+ "[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-"
				+ "4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])|([a-zA-Z0"
				+ "-9\\-]+\\.)*[a-zA-Z0-9\\-]+\\.[a-zA-Z]{2,4})(\\:[0-9]+)?(/"
				+ "[^/][a-zA-Z0-9\\.\\,\\?\\'\\\\/\\+&%\\$\\=~_\\-@]*)*$";

		Pattern p = Pattern.compile(regEx);
		Matcher matcher = p.matcher(url);
		return matcher.matches();
	}

}
