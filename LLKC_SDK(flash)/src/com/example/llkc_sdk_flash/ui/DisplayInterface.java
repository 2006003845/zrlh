package com.example.llkc_sdk_flash.ui;

import java.util.List;

import android.app.AlertDialog;

import com.example.llkc_sdk_flash.beans.RankItem;

public interface DisplayInterface {
	/**
	 * 客户端调用此接口来自定义dialogView
	 * 
	 * @param dialog
	 * @param items
	 */
	void display(AlertDialog dialog, List<RankItem> items);
}
