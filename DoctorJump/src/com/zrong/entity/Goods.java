package com.zrong.entity;

import java.io.Serializable;
import java.util.ArrayList;

import android.database.Cursor;

public class Goods implements Serializable {

	private static final long serialVersionUID = -3817302083902011218L;

	public Goods() {
	}

	/**
	 * ������Ʒ
	 * 
	 * @param g_id
	 * @param g_type
	 * @param g_name
	 * @param g_imgName
	 * @param g_price
	 * @param g_describ
	 *            ����/ͼƬName
	 * @param infoid
	 * @param isOwning
	 * @param isEquipment
	 */
	public Goods(int g_id, int g_type, String g_name, String g_imgName,
			int g_price, String g_describ, int info, int isOwning,
			int isEquipment) {
		super();
		this.g_id = g_id;
		this.g_type = g_type;
		this.g_name = g_name;
		this.g_imgName = g_imgName;
		this.g_price = g_price;
		this.g_describ = g_describ;
		this.g_info = info;
		this.isOwning = isOwning;
		this.isEquipment = isEquipment;
	}

	public int g_id;// ID
	public int g_type;// 0-5��������
	public String g_name;// ��Ʒ��
	public String g_imgName;// ��Ʒ����
	public int g_price;// �۸�
	public String g_describ;// ����
	public int g_info;
	/**
	 * �Ƿ��Ѿ�����
	 */
	public int isOwning;// �Ƿ��Ѿ�����
	public int isEquipment;// �Ƿ��Ѿ�װ��

	// �б��
	public final static int G_ID_INDEX = 1;
	public final static int G_TYPE_INDEX = 2;
	public final static int G_NAME_INDEX = 3;
	public final static int G_IMGName_INDEX = 4;
	public final static int G_PRICE_INDEX = 5;
	public final static int G_DESCRIB_INDEX = 6;
	public final static int G_INFO_INDEX = 7;
	public final static int G_ISOWNING_INDEX = 8;
	public final static int G_ISEQUIP_INDEX = 9;

	// ���ֶ�
	public final static String _ID = "_id";
	public final static String G_ID = "goods_id";
	public final static String G_TYPE = "goods_type";
	public final static String G_NAME = "goods_name";
	public final static String G_IMGName = "goods_img";
	public final static String G_PRICE = "goods_price";
	public final static String G_DESCRIB = "goods_describ";
	public final static String G_INFO = "goods_info";
	public final static String G_ISOWNING = "goods_isowning";
	public final static String G_ISEQUIP = "goods_isequip";

	// table_name
	public static final String TAB_NAME = "goods";

	/**
	 * δ����
	 */
	public static final int UNOWNING = 0;
	/**
	 * �Ѿ�����
	 */
	public static final int OWNING = 1;

	/**
	 * δװ��
	 */
	public static final int UNEQUIP = 2;
	/**
	 * �Ѿ�װ��
	 */
	public static final int HAVEEQUIP = 4;

	/**
	 * ��Ծ������
	 */
	public static final int GOODS_TYPE_JUMPER = 0;
	/**
	 * �Ĵ�����
	 */
	public static final int GOODS_TYPE_BED = 1;
	/**
	 * ��������
	 */
	public static final int GOODS_TYPE_SENCE = 2;
	/**
	 * ��Ч����
	 */
	public static final int GOODS_TYPE_EFFECT = 3;

	/**
	 * ��������
	 */
	public static final int GOODS_TYPE_SKILL = 4;
	/**
	 * ����������
	 */
	public static final int GOODS_TYPE_GOLD = 9;

	@Override
	public String toString() {
		return "����" + g_type + "����" + g_name + "�۸�" + g_price + "����"
				+ isOwning;
	}

	public static ArrayList<Goods> getGoodsList(Cursor cursor) {
		cursor.moveToFirst();
		ArrayList<Goods> list = new ArrayList<Goods>();
		int count = cursor.getCount();
		do {
			if (count == 0) {
				break;
			}
			Goods goods = new Goods();
			goods.g_id = cursor.getInt(Goods.G_ID_INDEX);
			goods.g_type = cursor.getInt(Goods.G_TYPE_INDEX);
			goods.g_name = cursor.getString(Goods.G_NAME_INDEX);
			goods.g_imgName = cursor.getString(Goods.G_IMGName_INDEX);
			goods.g_price = cursor.getInt(Goods.G_PRICE_INDEX);
			goods.g_describ = cursor.getString(Goods.G_DESCRIB_INDEX);
			goods.g_info = cursor.getInt(Goods.G_INFO_INDEX);
			goods.isOwning = cursor.getInt(Goods.G_ISOWNING_INDEX);
			goods.isEquipment = cursor.getInt(Goods.G_ISEQUIP_INDEX);
			list.add(goods);
		} while (cursor.moveToNext());
		cursor.close();
		return list;
	}

}
