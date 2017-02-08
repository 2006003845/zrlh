package com.zzl.zrlh.llkc_sdk.ui;

import java.util.List;

import com.zzl.zrlh.llkc_sdk.beans.RankItem;

import android.app.AlertDialog;

public interface DisplayInterface {
	/**
	 * 客户端调用此接口来自定义dialogView
	 * 
	 * @param dialog
	 * @param items
	 */
	void display(AlertDialog dialog, List<RankItem> items);
}
