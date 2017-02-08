package com.zrong.entity;

import java.io.Serializable;

import android.database.Cursor;

public class Prop implements Serializable {

	private static final long serialVersionUID = -2558002106154358137L;

	public Prop() {

	}

	/**
	 * ��������
	 * 
	 * @param p_name
	 * @param p_efficacy
	 * @param p_imgName
	 */
	public Prop(String p_name, int p_efficacy, String p_imgName) {
		super();
		this.p_name = p_name;
		this.p_efficacy = p_efficacy;
		this.p_imgName = p_imgName;
	}

	public int p_id;
	public String p_name;
	/**
	 * 9:����-��������Ϸ�����޻��� 0:˫����Ծ���� 1:ֱ�����ӽ�� 2:����һ������ֵ--��ʱģʽ������ 3:ֹͣʱ��10��--����ʱģʽ����
	 * 4:����NPC�ܶ�&�����ٶȣ�����10�� 5:���ӱĴ����ȣ�����10�� 6:���̱Ĵ����ȣ�����10��
	 * 7:�������ڱĴ��ϣ���Ծ���������߸�Ծ�𣬼̶�������������Ҫ�����Ĵ�ʹ��ʧЧ 8:�Ĵ����ʯ�壬ͼ���������Զ��۶�
	 */
	public int p_efficacy;// ��Ч
	public String p_imgName;// ͼƬ����

	// �б��
	public final static int P_ID_INDEX = 0;
	public final static int P_NAME_INDEX = 1;
	public final static int P_EFFICACY_INDEX = 2;
	public final static int P_IMGNAME_INDEX = 3;

	// �ֶ���
	public final static String P_ID = "_id";
	public final static String P_NAME = "prop_name";
	public final static String P_EFFICACY = "prop_efficacy";
	public final static String P_IMGNAME = "prop_img";

	// table name
	public static final String TAB_NAME = "prop";

	@Override
	public String toString() {
		return "���" + p_id + "����" + p_name + "��Ч" + p_efficacy;
	}

	public static final String FIRE_USER_NAME = "prop_fire";
	public static final String DOUBLE_USER_NAME = "prop_double";
	public static final String GOLD_USER_NAME = "prop_gold";
	public static final String LIFE_USER_NAME = "prop_life";
	public static final String STOP_USER_NAME = "prop_stop";
	public static final String SLOW_USER_NAME = "prop_slow";
	public static final String EXTEND_USER_NAME = "prop_extend";
	public static final String SHORTEN_USER_NAME = "prop_shorten";
	public static final String THUMBTACK_USER_NAME = "prop_thumbtack";
	public static final String STONE_USER_NAME = "prop_stone";

	public static Prop[] getProps(Cursor cursor) {
		cursor.moveToFirst();

		int count = cursor.getCount();
		if (count == 0) {
			return null;
		}
		Prop[] props = new Prop[count];
		int i = 0;
		do {
			Prop p = new Prop();
			p.p_name = cursor.getString(Prop.P_NAME_INDEX);
			p.p_efficacy = cursor.getInt(Prop.P_EFFICACY_INDEX);
			p.p_imgName = cursor.getString(Prop.P_IMGNAME_INDEX);
			props[i++] = p;

		} while (cursor.moveToNext());
		cursor.close();
		return props;
	}

}
