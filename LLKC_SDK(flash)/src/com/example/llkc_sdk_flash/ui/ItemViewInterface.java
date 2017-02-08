package com.example.llkc_sdk_flash.ui;

import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.llkc_sdk_flash.beans.RankItem;

public interface ItemViewInterface {
	/**
	 * 
	 * @param inflater
	 * @param position
	 * @param convertView
	 * @param parent
	 * @param itemList
	 *            排名列表
	 * @param util
	 *            单位(得分单位)
	 * @return
	 */
	View getItemView(LayoutInflater inflater, int position, View convertView,
			ViewGroup parent, List<RankItem> itemList, String util);
}
