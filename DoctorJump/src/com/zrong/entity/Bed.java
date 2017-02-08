package com.zrong.entity;

import android.graphics.Bitmap;

import com.zrong.physics.Rectangle;

public class Bed {
	
	public Rectangle bedR;

	public String bedName;
	public Bitmap currentBedBm;

	public int style = BED_NORMAL;// ������

	public int size_state;// ���С

	public boolean isCollideByThumbtack = false;
	public boolean isCollideByKnifeNPC = false;
	public boolean isCollideByFire = false;
	public boolean isCollideByNPC = false;
	public boolean isCollideByStone = false;

	public static final int BED_NORMAL = 0;// ��ͨ
	public static final int BED_SHORT = 1;// �̰�
	public static final int BED_LONG = 2;// ����
	/**
	 * ľ��
	 */
	public static final int BED_BOARD = 0;
	/**
	 * ����
	 */
	public static final int BED_ICE = 31;
	
	/**
	 * ����
	 */
	public static final int BED_IRON = 32;
	/**
	 * ����
	 */
	public static final int BED_ROPE = 33;

	/**
	 * �ʺ��
	 */
	public static final int BED_RAINBOW = 100;


}
