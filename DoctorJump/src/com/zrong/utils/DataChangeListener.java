package com.zrong.utils;

import android.os.Bundle;

public interface DataChangeListener {

	/**
	 * 
	 * @param score
	 *            ���÷���
	 * @param isCombo
	 *            �Ƿ�Ϊcombo��
	 */
	void scoreChangeListener(int score, boolean isCombo);

	void lifeChangeListener(Bundle b);
	/**
	 * Time Stop
	 * @param b
	 */
	void stopTimeListener(Bundle b);

}
