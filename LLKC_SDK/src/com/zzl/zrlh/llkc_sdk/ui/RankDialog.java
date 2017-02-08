package com.zzl.zrlh.llkc_sdk.ui;

import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zzl.zrlh.llkc_sdk.R;
import com.zzl.zrlh.llkc_sdk.beans.RankItem;

public class RankDialog extends AlertDialog {

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

	Context context;

	private void init(Context context) {
		this.context = context;
	}

	private RankAdapter adapter;

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
	 * 显示排行榜(使用默认的dialog)
	 * 
	 * @param items
	 *            游戏排名
	 * @param util
	 *            得分单位
	 */
	public void showRankDialog(List<RankItem> items, String util) {
		View layout = getLayoutInflater().inflate(R.layout.dialog_rank, null);
		final AlertDialog dialog = new AlertDialog.Builder(context).create();
		dialog.setCanceledOnTouchOutside(true);
		dialog.show();
		dialog.getWindow().setContentView(layout);
		dialog.setCanceledOnTouchOutside(true);
		dialog.findViewById(R.id.dialog_close).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						dialog.dismiss();
					}
				});
		ListView listV = (ListView) dialog.findViewById(R.id.dialog_rank_listv);
		if (adapter == null)
			adapter = new RankAdapter(context, items, util, null);
		listV.setAdapter(adapter);
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
	public void showRankDialog(List<RankItem> items, String util, String title,
			int iconResId, int closeImagResId, int titleBgResId, int bgResId) {
		View layout = getLayoutInflater().inflate(R.layout.dialog_rank, null);
		final AlertDialog dialog = new AlertDialog.Builder(context).create();
		dialog.setCanceledOnTouchOutside(true);
		dialog.show();
		dialog.getWindow().setContentView(layout);
		dialog.setCanceledOnTouchOutside(true);
		TextView titleTV = (TextView) dialog.findViewById(R.id.dialog_title_tv);
		if (title != null && !title.equals(""))
			titleTV.setText(title);
		ImageView iconImgV = (ImageView) dialog
				.findViewById(R.id.dialog_title_icon);

		RelativeLayout dialog_title = (RelativeLayout) dialog
				.findViewById(R.id.dialog_title);
		if (titleBgResId != 0) {
			dialog_title.setBackgroundResource(titleBgResId);
		}
		LinearLayout bg = (LinearLayout) dialog.findViewById(R.id.dialog_bg);
		if (bgResId != 0) {
			bg.setBackgroundResource(bgResId);
		}
		iconImgV.setImageResource(iconResId);
		ImageView close = (ImageView) dialog.findViewById(R.id.dialog_close);
		if (closeImagResId != 0)
			iconImgV.setImageResource(closeImagResId);
		close.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		ListView listV = (ListView) dialog.findViewById(R.id.dialog_rank_listv);
		if (adapter == null)
			adapter = new RankAdapter(context, items, util, null);
		listV.setAdapter(adapter);
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
	 * @param iconBM
	 *            (ImageView)图片
	 * @param closeBM
	 *            关闭按键(ImageView)图片
	 * @param titleBM
	 *            标题背景
	 * @param bgBM
	 *            背景图片
	 */
	public void showRankDialog(List<RankItem> items, String util, String title,
			Bitmap iconBM, Bitmap closeBM, Bitmap titleBM, Bitmap bgBM) {
		View layout = getLayoutInflater().inflate(R.layout.dialog_rank, null);
		final AlertDialog dialog = new AlertDialog.Builder(context).create();
		dialog.setCanceledOnTouchOutside(true);
		dialog.show();
		dialog.getWindow().setContentView(layout);
		dialog.setCanceledOnTouchOutside(true);
		TextView titleTV = (TextView) dialog.findViewById(R.id.dialog_title_tv);
		if (title != null && !title.equals(""))
			titleTV.setText(title);
		ImageView iconImgV = (ImageView) dialog
				.findViewById(R.id.dialog_title_icon);
		if (iconBM != null)
			iconImgV.setImageBitmap(iconBM);
		RelativeLayout dialog_title = (RelativeLayout) dialog
				.findViewById(R.id.dialog_title);
		if (titleBM != null) {
			dialog_title.setBackground(new BitmapDrawable(context
					.getResources(), titleBM));
		}
		LinearLayout bg = (LinearLayout) dialog.findViewById(R.id.dialog_bg);
		if (bgBM != null) {
			bg.setBackground(new BitmapDrawable(context.getResources(), bgBM));
		}

		ImageView close = (ImageView) dialog.findViewById(R.id.dialog_close);
		if (closeBM != null)
			iconImgV.setImageBitmap(closeBM);
		close.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		ListView listV = (ListView) dialog.findViewById(R.id.dialog_rank_listv);
		if (adapter == null)
			adapter = new RankAdapter(context, items, util, null);
		listV.setAdapter(adapter);
	}

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
	public void showRankDialog(List<RankItem> items, String util, String title,
			Bitmap icon, ItemViewInterface viewInterface) {
		View layout = getLayoutInflater().inflate(R.layout.dialog_rank, null);
		final AlertDialog dialog = new AlertDialog.Builder(context).create();
		dialog.setCanceledOnTouchOutside(true);
		dialog.show();
		dialog.getWindow().setContentView(layout);
		dialog.setCanceledOnTouchOutside(true);
		TextView titleTV = (TextView) dialog.findViewById(R.id.dialog_title_tv);
		if (title != null && !title.equals(""))
			titleTV.setText(title);
		ImageView iconImgV = (ImageView) dialog
				.findViewById(R.id.dialog_title_icon);
		if (icon != null)
			iconImgV.setImageBitmap(icon);
		dialog.findViewById(R.id.dialog_close).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						dialog.dismiss();
					}
				});
		ListView listV = (ListView) dialog.findViewById(R.id.dialog_rank_listv);
		if (adapter == null)
			adapter = new RankAdapter(context, items, util, viewInterface);
		listV.setAdapter(adapter);
	}

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
	public void showRankDialog(List<RankItem> items, String util, String title,
			int iconResId, ItemViewInterface viewInterface) {
		View layout = getLayoutInflater().inflate(R.layout.dialog_rank, null);
		final AlertDialog dialog = new AlertDialog.Builder(context).create();
		dialog.setCanceledOnTouchOutside(true);
		dialog.show();
		dialog.getWindow().setContentView(layout);
		dialog.setCanceledOnTouchOutside(true);
		TextView titleTV = (TextView) dialog.findViewById(R.id.dialog_title_tv);
		if (title != null && !title.equals(""))
			titleTV.setText(title);
		ImageView iconImgV = (ImageView) dialog
				.findViewById(R.id.dialog_title_icon);
		if (iconResId != 0)
			iconImgV.setImageResource(iconResId);
		dialog.findViewById(R.id.dialog_close).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						dialog.dismiss();
					}
				});
		ListView listV = (ListView) dialog.findViewById(R.id.dialog_rank_listv);
		if (adapter == null)
			adapter = new RankAdapter(context, items, util, viewInterface);
		else {
			adapter.setRankList(items);
			adapter.setViewInterface(viewInterface);
		}
		listV.setAdapter(adapter);
	}

}
