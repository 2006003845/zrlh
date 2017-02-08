package com.zrong.entity;

import android.database.Cursor;

public class Combo {

	public int score;
	public int combindex;

	public Combo() {

	}

	public static final String COMB_COLUMN = "comb_score";
	public static final String COMB_INDEX_COLUMN = "comb_index";
	public static final int COMB_COLUMN_INDEX = 1;
	public static final int COMB_INDEX_INDEX = 2;

	public Combo(int score, int combindex) {
		super();
		this.score = score;
		this.combindex = combindex;
	}

	public static final String TABLE_NAME = "combo";

	// public static Combo[] getComboList(Cursor cursor) {
	// cursor.moveToFirst();
	// int count = cursor.getCount();
	// if (count == 0) {
	// return null;
	// }
	// Combo[] combos = new Combo[count];
	// for (int i = 0; i < combos.length; i++) {
	// Combo combo = new Combo();
	// combo.score = cursor.getInt(Combo.COMB_COLUMN_INDEX);
	// combo.combindex = cursor.getInt(Combo.COMB_INDEX_INDEX);
	// combos[i] = combo;
	// cursor.moveToNext();
	// }
	// cursor.close();
	// return combos;
	// }

}
