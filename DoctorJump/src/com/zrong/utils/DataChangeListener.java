package com.zrong.utils;

import android.os.Bundle;

public interface DataChangeListener {

	/**
	 * 
	 * @param score
	 *            所得分数
	 * @param isCombo
	 *            是否为combo数
	 */
	void scoreChangeListener(int score, boolean isCombo);

	void lifeChangeListener(Bundle b);
	/**
	 * Time Stop
	 * @param b
	 */
	void stopTimeListener(Bundle b);

}
