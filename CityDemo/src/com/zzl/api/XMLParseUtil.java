package com.zzl.api;

import java.util.ArrayList;
import java.util.Iterator;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import android.util.Log;

import com.zzl.city.City;
import com.zzl.city.ToPinYin;

public class XMLParseUtil {

	public static ArrayList<City> readStringXmlToCityList(String xmlStr) {
		ArrayList<City> list = new ArrayList<City>();
		Document doc = null;
		try {
			// 解析xml字符串
			doc = DocumentHelper.parseText(xmlStr); // 将字符串转为XML
			Element rootElement = doc.getRootElement(); // 获取根节点

			Log.i("Log", "根节点：" + rootElement.getName()); // 拿到根节点的名称
			Iterator iter = rootElement.elementIterator("city"); // 遍历根节点下的节点
			// 遍历list节点
			while (iter.hasNext()) {
				Element cityEle = (Element) iter.next();
				City city = new City();
				String id = cityEle.attributeValue("id");
				city.setId(id);
				String name = cityEle.attributeValue("name");
				city.setCity(name);
				String countyStr = cityEle.attributeValue("county");
				city.setCountyStr(countyStr);
				String[] countys = null;
				if (countyStr != null)
					countys = countyStr.split(",");
				city.setCountys(countys);
				city.setAllPY(ToPinYin.getPinYin(name + ""));
				city.setFirstPY(getNameFirstPY(name + ""));
				city.setAllFristPY(getNameAllFirstPY(name + ""));
				if (name != null && name.equals("长沙市")) {// 目前只发现长沙的拼音
					// 匹配成zhang sha
					city.setAllPY("CHANGSHASHI");
					city.setAllFristPY("CSS");
					city.setFirstPY("C");
				} else if (name != null && name.equals("琼海市")) {// 目前只发现长沙的拼音
					// 匹配成zhang sha
					city.setAllPY("QIONGHAISHI");
					city.setAllFristPY("QHS");
					city.setFirstPY("Q");
				}
				list.add(city);
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private static String getNameAllFirstPY(String name) {
		try {
			if (name != null && name.length() != 0) {
				int len = name.length();
				char[] nums = new char[len];
				for (int i = 0; i < len; i++) {
					String tmp = name.substring(i);
					nums[i] = ToPinYin.getPinYin(tmp).charAt(0);
				}
				return new String(nums);
			}
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			e.printStackTrace();
		}
		return "";
	}

	private static String getNameFirstPY(String name) {
		String pinyin = "#";
		try {
			if (name != null && name.length() != 0) {
				String tmp = name.substring(0);
				pinyin = ToPinYin.getPinYin(tmp).charAt(0) + "";
			}
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			e.printStackTrace();
		}
		return pinyin;
	}
}
