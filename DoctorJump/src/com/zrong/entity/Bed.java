package com.zrong.entity;

import android.graphics.Bitmap;

import com.zrong.physics.Rectangle;

public class Bed {
	
	public Rectangle bedR;

	public String bedName;
	public Bitmap currentBedBm;

	public int style = BED_NORMAL;// °åÀàÐÍ

	public int size_state;// °å´óÐ¡

	public boolean isCollideByThumbtack = false;
	public boolean isCollideByKnifeNPC = false;
	public boolean isCollideByFire = false;
	public boolean isCollideByNPC = false;
	public boolean isCollideByStone = false;

	public static final int BED_NORMAL = 0;// ÆÕÍ¨
	public static final int BED_SHORT = 1;// ¶Ì°å
	public static final int BED_LONG = 2;// ³¤°å
	/**
	 * Ä¾°å
	 */
	public static final int BED_BOARD = 0;
	/**
	 * ±ù°å
	 */
	public static final int BED_ICE = 31;
	
	/**
	 * Ìú°å
	 */
	public static final int BED_IRON = 32;
	/**
	 * Éþ°å
	 */
	public static final int BED_ROPE = 33;

	/**
	 * ²Êºç°å
	 */
	public static final int BED_RAINBOW = 100;


}
