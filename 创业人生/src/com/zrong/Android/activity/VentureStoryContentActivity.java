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
import android.widget.TextView;

import com.zrong.Android.api.Get2ApiImpl;
import com.zrong.Android.api.IGet2Api;
import com.zrong.Android.api.WSError;
import com.zrong.Android.entity.InfoList;
import com.zrong.Android.entity.VentureSchoolData;

public class VentureStoryContentActivity extends GameActivity {
	private static VentureStoryContentActivity mContext;

	private TextView contentTV;

	private String index, level;

	 
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
		}
		new VentureStoryContentTask().execute((Void) null);

		setContentView(R.layout.infocontent);
		Button title = (Button) this
				.findViewById(R.id.infocontent_button_title);
		title.setText("创业故事");
		Button returnback = (Button) this
				.findViewById(R.id.infocontent_button_returnback);
		Button cancel = (Button) this
				.findViewById(R.id.infocontent_button_cancel);

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

	private class VentureStoryContentTask extends
			AsyncTask<Void, WSError, InfoList> {
		ProgressDialog dialog = null;
		
		 
		protected void onPreExecute() {
			dialog = new ProgressDialog(mContext);
			dialog.setCancelable(false);
			dialog.setTitle(mContext.getResources().getString(R.string.title));
			dialog.setMessage(mContext.getResources().getString(R.string.loadin));
			dialog.show();

			super.onPreExecute();
		}

		 
		protected InfoList doInBackground(Void... params) {
			Log.i("Log", "doInBackground");
			IGet2Api server = new Get2ApiImpl();
			InfoList infoList = null;
			try {
				infoList = server.getInfoList(VentureSchoolData.VENTURESTORY,
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
					.findViewById(R.id.infocontent_scroll_linear_tv);
			if (infoList != null
					&& infoList.getInfoList().get(0).getContent() != null) {
				contentTV.setText(infoList.getInfoList().get(0).getContent());
			}else{
				buildMessageDialog(mContext,
						mContext.getResources().getString(R.string.load_failed)).show();
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
