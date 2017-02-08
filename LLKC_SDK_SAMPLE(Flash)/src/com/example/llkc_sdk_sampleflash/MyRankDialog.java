package com.example.llkc_sdk_sampleflash;

import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.llkc_sdk_flash.beans.RankItem;
import com.example.llkc_sdk_flash.ui.ItemViewInterface;
import com.example.llkc_sdk_flash.ui.RankAdapter;
import com.example.llkc_sdk_flash.ui.RankDialog;

public class MyRankDialog extends RankDialog {

	public MyRankDialog(Context context, boolean cancelable,
			OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);
		// TODO Auto-generated constructor stub
	}

	public MyRankDialog(Context context, int theme) {
		super(context, theme);
		// TODO Auto-generated constructor stub
	}

	public MyRankDialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void showRankDialog(List<RankItem> items, String util, String title,
			Bitmap icon, ItemViewInterface viewInterface) {
		// TODO Auto-generated method stub

	}

	@Override
	public void showRankDialog(List<RankItem> items, String util, String title,
			int iconResId, ItemViewInterface viewInterface) {
		View layout = getLayoutInflater().inflate(R.layout.dialog_rank, null);
		final AlertDialog dialog = new AlertDialog.Builder(context).create();
		dialog.setCanceledOnTouchOutside(true);
		dialog.show();
		dialog.getWindow().setLayout(RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.MATCH_PARENT);
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

	@Override
	public void showRankDialog(List<RankItem> items, String util, String title,
			int iconResId, int closeImagResId, int titleBgResId, int bgResId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void showRankDialog(List<RankItem> items, String util, String title,
			Bitmap iconBM, Bitmap closeBM, Bitmap titleBM, Bitmap bgBM) {
		// TODO Auto-generated method stub

	}

}
