package com.example.llkc_sdk_flash.ui;

import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;

import com.example.llkc_sdk_flash.beans.RankItem;

public abstract class RankDialog extends AlertDialog {

	public RankDialog(Context context, boolean cancelable,
			OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);
		init(context);
	}

	public RankDialog(Context context, int theme) {
		super(context, theme);
		init(context);
	}

	public RankDialog(Context context) {
		super(context);
		init(context);
	}

	protected Context context;

	private void init(Context context) {
		this.context = context;
	}

	protected RankAdapter adapter;

	public void updateRankData(List<RankItem> items) {
		if (adapter != null) {
			adapter.setRankList(items);
		}
	}

	/**
	 * 该方法 只初始化AlertDialog对象 初始化Dialog 中控件 需要客户端自己来实现
	 * 
	 * @param items
	 * @param layoutID
	 * @DisplayInterface diaplayInterface 自定义dialogView
	 */
	public void showRankDialog(List<RankItem> items, int layoutID,
			DisplayInterface diaplayInterface) {
		View layout = getLayoutInflater().inflate(layoutID, null);
		final AlertDialog dialog = new AlertDialog.Builder(context).create();
		dialog.setCanceledOnTouchOutside(true);
		dialog.show();
		dialog.getWindow().setContentView(layout);
		dialog.setCanceledOnTouchOutside(true);
		diaplayInterface.display(dialog, items);
	}

	/**
	 * 显示排行榜dialog
	 * 
	 * @param items
	 *            排名列表
	 * @param util
	 *            单位
	 * @param title
	 *            标题
	 * @param iconResId
	 *            (ImageView)图片资源id
	 * @param closeImagResId
	 *            关闭按键(ImageView)图片资源id
	 * @param titleBgResId
	 *            标题背景
	 * @param bgResId
	 *            背景图片资源ID
	 */
	public abstract void showRankDialog(List<RankItem> items, String util,
			String title, int iconResId, int closeImagResId, int titleBgResId,
			int bgResId);

	/**
	 * 显示排行榜dialog
	 * 
	 * @param items
	 *            排名列表
	 * @param util
	 *            单位
	 * @param title
	 *            标题
	 * @param iconBM
	 *            (ImageView)图片
	 * @param closeBM
	 *            关闭按键(ImageView)图片
	 * @param titleBM
	 *            标题背景
	 * @param bgBM
	 *            背景图片
	 */
	public abstract void showRankDialog(List<RankItem> items, String util,
			String title, Bitmap iconBM, Bitmap closeBM, Bitmap titleBM,
			Bitmap bgBM);

	/**
	 * 
	 * @param items
	 * @param util
	 *            单位
	 * @param title
	 *            标题
	 * @param icon
	 *            icon bitmap
	 * 
	 * @ItemViewInterface viewInterface
	 */
	public abstract void showRankDialog(List<RankItem> items, String util,
			String title, Bitmap icon, ItemViewInterface viewInterface);

	/**
	 * 
	 * @param items
	 * @param util
	 *            单位
	 * @param title
	 *            标题
	 * @param icon
	 *            icon resId
	 * 
	 * @ItemViewInterface viewInterface
	 */
	public abstract void showRankDialog(List<RankItem> items, String util,
			String title, int iconResId, ItemViewInterface viewInterface);
}
