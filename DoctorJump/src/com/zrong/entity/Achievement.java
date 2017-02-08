package com.zrong.entity;

import java.util.ArrayList;

import android.database.Cursor;

public class Achievement {
	public int ach_id;
	public int ach_img;
	public int ach_condition;
	public int ach_lock;
	public int ach_award;

	/**
	 * �ɾ�
	 * 
	 * @param ach_id
	 *            ���
	 * @param ach_img
	 *            ͼƬ��Դ
	 * @param ach_condition
	 *            ����
	 * @param ach_lock
	 *            �Ƿ����
	 * @param ach_award
	 *            ����
	 */
	public Achievement(int ach_id, int ach_img, int ach_condition,
			int ach_lock, int ach_award) {
		super();
		this.ach_id = ach_id;
		this.ach_img = ach_img;
		this.ach_condition = ach_condition;
		this.ach_lock = ach_lock;
		this.ach_award = ach_award;
	}

	public Achievement() {

	}

	public static final int LOCKED = 0;
	public static final int UNLOCK = 1;

	public static final int CONDITION_1 = 0;// ����ؿ����Ǽ���
	public static final int CONDITION_2 = 1;// 36����
	public static final int CONDITION_3 = 2;// �ؿ�ģʽͨ��
	public static final int CONDITION_4 = 3;// ��Ҵﵽ1000
	public static final int CONDITION_5 = 4;// ��Ҵﵽ5000
	public static final int CONDITION_6 = 5;// ��ʱģʽ�ﵽ5000��
	public static final int CONDITION_7 = 6;// ��ʱģʽ�ﵽ10000��
	public static final int CONDITION_8 = 7;// �޾�ģʽ���5����
	public static final int CONDITION_9 = 8;// �޾�ģʽ���15����
	public static final int CONDITION_10 = 9;// ����ȫ���Ĵ�
	public static final int CONDITION_11 = 10;// ����ȫ������
	public static final int CONDITION_12 = 11;// ����ȫ������������
	public static final int CONDITION_13 = 12;// ����ȫ����Ʒ
	public static final int CONDITION_14 = 13;// ������гɾ�

	public static final int AWARD_NOTHING = 0;// �޽���
	public static final int AWARD_OPENRAINROST = 1;// �򿪲ʺ��ż���
	public static final int AWARD_500 = 2;// ����500���
	public static final int AWARD_ADDBED = 3;// ��ӱĴ�
	public static final int AWARD_ADDSCENE = 4;// �����ɽ����
	public static final int AWARD_ADDNPC = 5;// ��Ӻ�����
	public static final int AWARD_REDUCECOOLING = 6;// ���ټ��ܼ���ȴʱ��
	public static final int AWARD_ADDPROP10 = 7;// ����PROP����10%

	public static final String ACH_ID = "ach_id";
	public static final String ACH_IMG = "ach_img";
	public static final String ACH_COND = "ach_condition";
	public static final String ACH_LOCK = "ach_isLocking";
	public static final String ACH_AWARD = "ach_award";

	public static final int INDEX_ID = 1;
	public static final int INDEX_IMG = 2;
	public static final int INDEX_COND = 3;
	public static final int INDEX_LOCK = 4;
	public static final int INDEX_AWARD = 5;

	public static final String TAB_NAME = "tab_ach";

	public static ArrayList<Achievement> getachList(Cursor cursor) {
		cursor.moveToFirst();
		ArrayList<Achievement> list = new ArrayList<Achievement>();
		int count = cursor.getCount();
		do {
			if (count == 0) {
				break;
			}
			Achievement ach = new Achievement();
			ach.ach_id = cursor.getInt(INDEX_ID);
			ach.ach_img = cursor.getInt(INDEX_IMG);
			ach.ach_condition = cursor.getInt(INDEX_COND);
			ach.ach_lock = cursor.getInt(INDEX_LOCK);
			ach.ach_award = cursor.getInt(INDEX_AWARD);

			list.add(ach);
		} while (cursor.moveToNext());
		cursor.close();
		return list;
	}
	

}
