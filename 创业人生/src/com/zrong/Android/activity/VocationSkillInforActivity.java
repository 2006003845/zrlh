package com.zrong.Android.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.zrong.Android.api.Get2ApiImpl;
import com.zrong.Android.api.IGet2Api;
import com.zrong.Android.api.WSError;
import com.zrong.Android.entity.Info;
import com.zrong.Android.entity.InfoList;
import com.zrong.Android.entity.VentureSchoolData;

public class VocationSkillInforActivity extends GameActivity {
	private static VocationSkillInforActivity mContext;

	private String index, level;
	private int img_index;

	private TextView contentTV;
	private ImageView img;

	 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		mContext = this;

		// 获取信息
		Intent intent = getIntent();
		Bundle bundle = intent.getBundleExtra("info");
		if (bundle != null) {
			index = bundle.getString("index");
			level = bundle.getString("level");
			img_index = bundle.getInt("img");
		}

		new VentureSkillContentTask().execute((Void) null);

		setContentView(R.layout.vocationskill_info);

		img = (ImageView) mContext.findViewById(R.id.vocationskill_info_img);
		if (img_index >= 0) {
			setImageSrc(img_index);
		}
		Button returnback = (Button) this
				.findViewById(R.id.vocationskill_info_button_returnback);
		Button cancel = (Button) this
				.findViewById(R.id.vocationskill_info_button_cancel);
		returnback.setOnClickListener(new OnClickListener() {

			 
			public void onClick(View v) {
				mContext.finish();
			}
		});
		cancel.setOnClickListener(new OnClickListener() {

			 
			public void onClick(View v) {
				mContext.finish();
			}
		});
	}

	private void setImageSrc(int img_index) {
		switch (img_index) {
		case 100:// 咖啡
			img.setImageResource(R.drawable.barista);
			break;
		case 101:// 中餐
			img.setImageResource(R.drawable.chinese_kitchen_man);
			break;
		case 102:// 西餐
			img.setImageResource(R.drawable.western_food_division);
			break;
		case 103:// 调酒
			img.setImageResource(R.drawable.bartender);
			break;
		case 104:// 裁缝
			img.setImageResource(R.drawable.tailor);
			break;
		case 105:// 图书管理
			img.setImageResource(R.drawable.librarian);
			break;
		case 106:// 美容
			img.setImageResource(R.drawable.beautician);
			break;
		case 107:// 兽医
			img.setImageResource(R.drawable.veterinarian);
			break;
		case 108:// 销售
			img.setImageResource(R.drawable.salesman);
			break;
		case 109:// 插画
			img.setImageResource(R.drawable.flower_arrangement);
			break;
		case 110:// 汽车维修
			img.setImageResource(R.drawable.vehicle_repair);
			break;
		case 111:// 乐器调音
			img.setImageResource(R.drawable.instrument_tuner);
			break;

		}
	}

	private Dialog buildMessageDialog(Context context, String message) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setIcon(R.drawable.icon1);
		builder.setTitle(mContext.getResources().getString(R.string.title));
		builder.setMessage(message);
		builder.setPositiveButton(
				mContext.getResources().getString(R.string.ensure),
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						setTitle("");
					}
				});
		return builder.create();

	}

	private class VentureSkillContentTask extends
			AsyncTask<Void, WSError, InfoList> {
		ProgressDialog dialog = null;

		 
		protected void onPreExecute() {
			dialog = new ProgressDialog(mContext);
			dialog.setCancelable(false);
			dialog.setTitle(mContext.getResources().getString(R.string.title));
			dialog.setMessage(mContext.getResources()
					.getString(R.string.loadin));
			dialog.show();

			super.onPreExecute();
		}

		 
		protected InfoList doInBackground(Void... params) {
			Log.i("Log", "doInBackground");
			IGet2Api server = new Get2ApiImpl();
			InfoList infoList = null;
			try {
				infoList = server.getInfoList(VentureSchoolData.VOCATIONSKILL,
						index, level);
				// 获取政策消息列表
			} catch (WSError e) {
				e.printStackTrace();
			}
			return infoList;
		}

		 
		protected void onPostExecute(InfoList infoList) {
			setProgressBarIndeterminateVisibility(false);
			dialog.dismiss();
			contentTV = (TextView) mContext
					.findViewById(R.id.vocationskill_info_scroll_linear_tv);

			if (infoList != null && infoList.getInfoList() != null) {
				Info info = infoList.getInfoList().get(0);
				if (info.getContent() != null) {
					contentTV.setText(infoList.getInfoList().get(0)
							.getContent());
				}
				String imgID = info.getIconUrlStr();
				//正则
				if (imgID.matches("^\\d+$")) {
					Log.i("Img", info.getIconUrlStr());
						img_index = Integer.parseInt(imgID);
					if (img_index >= 0) {
						setImageSrc(img_index);
					}
				}
			} else {
				buildMessageDialog(mContext,
						mContext.getResources().getString(R.string.load_failed))
						.show();
			}

			super.onPostExecute(infoList);
		}

		 
		protected void onProgressUpdate(WSError... values) {
			super.onProgressUpdate(values);
		}
	}

	 
	public GameActivity getGameContext() {
		// TODO Auto-generated method stub
		return this;
	}
	
	 
	public void finish() 
	{
		mContext = null;
		super.finish();
	}

}
