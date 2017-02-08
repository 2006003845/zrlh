package com.zrong.Android.api;

import java.util.ArrayList;

import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import android.util.Log;

import com.zrong.Android.entity.Area;
import com.zrong.Android.entity.Info;
import com.zrong.Android.entity.Province;

public class XMLParseUtil {

	public static ArrayList<Info> readStringXmlToInfoList(String xmlStr) {
		ArrayList<Info> list = new ArrayList<Info>();
		Document doc = null;
		try {
			// 解析xml字符串
			doc = DocumentHelper.parseText(xmlStr); // 将字符串转为XML
			Element rootElement = doc.getRootElement(); // 获取根节点
			if ("1".equals(rootElement.element("status").getTextTrim())) {// 失败
				return null;
			}
			if (!"showSchoolInfo".equals(rootElement.element("msgType").getTextTrim())) {// 类型不匹配
				return null;
			}

			Log.i("Log", "根节点：" + rootElement.getName()); // 拿到根节点的名称
			Iterator iter = rootElement.elementIterator("List"); // 遍历根节点下的节点
			// 遍历list节点
			while (iter.hasNext()) {
				Element recordEle = (Element) iter.next();
				Iterator iters = recordEle.elementIterator("Info");

				// 遍历info 节点
				while (iters.hasNext()) {
					Info info = new Info();
					Element e = (Element) iters.next();
					// TODO...
					if (e.element("index").hasContent()) {
						info.setId(e.element("index").getTextTrim());
					}
					if (e.element("nLevel").hasContent()) {
						info.setLevel(e.element("nLevel").getTextTrim());
						if (e.element("nLevel").getTextTrim().equals("-9")) {
							info.setContent(e.element("content").getTextTrim());
						}
					}
					if (e.element("title").hasContent()) {
						info.setTitle(e.element("title").getTextTrim());
					}
					if (e.element("icon").hasContent()) {
						info.setIconUrlStr(e.element("icon").getTextTrim());
					}
					list.add(info);
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<Area> readStringXmlToMapInfo(String xmlStr) {
		ArrayList<Area> areas = new ArrayList<Area>();

		Document doc = null;
		try {
			// 解析xml字符串
			doc = DocumentHelper.parseText(xmlStr); // 将字符串转为XML
			Element rootElement = doc.getRootElement(); // 获取根节点
			Log.i("Log", "根节点：" + rootElement.getName()); // 拿到根节点的名称
			Iterator iter = rootElement.elementIterator("List"); // 遍历根节点下的节点
			// 遍历list节点
			while (iter.hasNext()) {

				Element recordEle = (Element) iter.next();
				Iterator iters = recordEle.elementIterator("Info");
				// 遍历info 节点
				while (iters.hasNext()) {
					ArrayList<Province> list = new ArrayList<Province>();
					Element itemEle = (Element) iters.next();
					Iterator iterss = itemEle.elementIterator("map");
					// 遍历所有的map节点,封装为province
					while (iterss.hasNext()) {
						Province province = new Province();
						Element e = (Element) iterss.next();
						Element ee = null;
						if ((ee = e.element("index")) != null)
							province.setPid(ee.getTextTrim());
						if ((ee = e.element("name")) != null)
							province.setPname(ee.getTextTrim());
						if ((ee = e.element("mark")) != null) {
							String[] coords = ee.getTextTrim().split(",");
							province.setCoordX(Integer.parseInt(coords[0]));
							province.setCoordY(Integer.parseInt(coords[1]));
						}
						if ((ee = e.element("nLevel")) != null) {
							province.setLevel(ee.getTextTrim());
						}
						list.add(province);
					}
					// 区域
					if (itemEle.element("proName") != null) {
						Area area = new Area();
						area.setAreaName(itemEle.element("proName")
								.getTextTrim());
						area.setPrvinces(list);
						Log.i("Log", area.getAreaName());
						areas.add(area);
					}
				}

			}

		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return areas;
	}

}
