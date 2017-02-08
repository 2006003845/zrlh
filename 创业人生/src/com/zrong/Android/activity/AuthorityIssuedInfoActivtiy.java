package com.zrong.Android.activity;

import java.util.ArrayList;
import java.util.HashMap;

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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.zrong.Android.api.Get2ApiImpl;
import com.zrong.Android.api.IGet2Api;
import com.zrong.Android.api.WSError;
import com.zrong.Android.entity.Info;
import com.zrong.Android.entity.InfoList;
import com.zrong.Android.entity.VentureSchoolData;


public class AuthorityIssuedInfoActivtiy extends GameActivity {
	public static AuthorityIssuedInfoActivtiy mContext;

	private ArrayList<HashMap<String, Info>> authorityIssuedInfoData = new ArrayList<HashMap<String, Info>>();
	private SimpleAdapter authorityIssuedInfoAdapter;
	private ListView authorityIssuedInfoListView;

	private String index,level;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		mContext = this;
		
		// 接受数据
		Intent intent = getIntent();
		Bundle bundle = intent.getBundleExtra("info");
		if (bundle != null) {
			index = bundle.getString("index");
			level = bundle.getString("level");
		}
		
		setContentView(R.layout.infolist2);
		Button title = (Button) this.findViewById(R.id.infolist2_button_title);
		title.setText("权威发布");
		Button returnback = (Button) this
				.findViewById(R.id.infolist2_button_returnback);
		Button cancel = (Button) this
				.findViewById(R.id.infolist2_button_cancel);
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

		authorityIssuedInfoListView = (ListView) this
				.findViewById(R.id.infolist2_listview);
		new VentureStoryInfoTask().execute((Void) null);

	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

//	public static void changeActivity(Class calss, Intent intent) {
//		if (intent == null) {
//			intent = new Intent();
//		}
//		if (calss == null) {
//			return;
//		}
//		intent.setClass(mContext, calss);
//		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//		mContext.startActivity(intent);
//	}

	/**
	 * 数据初始化
	 */
	private void initData(InfoList infoList) {
		if (infoList == null) {
			return;
		}
		ArrayList<Info> list = infoList.getInfoList();
		for (Info info : list) {
			HashMap<String, Info> map = new HashMap<String, Info>();
			map.put("ventureStory", info);
			authorityIssuedInfoData.add(map);
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

	private class VentureStoryInfoTask extends
			AsyncTask<Void, WSError, InfoList> {
		ProgressDialog dialog = null;
		
		@Override
		protected void onPreExecute() {
			dialog = new ProgressDialog(mContext);
			dialog.setCancelable(false);
			dialog.setTitle(mContext.getResources().getString(R.string.title));
			dialog.setMessage(mContext.getResources().getString(R.string.loadin));
			dialog.show();

			super.onPreExecute();
		}

		@Override
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

		@Override
		protected void onPostExecute(InfoList infoList) {
			setProgressBarIndeterminateVisibility(false);
			dialog.dismiss();
			
			if (infoList == null || infoList.getInfoList() == null) {
				buildMessageDialog(mContext,
						mContext.getResources().getString(R.string.load_failed)).show();
				return;
			}
			
			initData(infoList);
			authorityIssuedInfoListView
					.setOnItemClickListener(new OnItemClickListener() {
 
						public void onItemClick(AdapterView<?> parent,
								View view, int position, long id) {
							Info info = ((HashMap<String, Info>) parent
									.getItemAtPosition(position))
									.get("ventureStory");
							if(info == null){
								return ;
							}
							Toast.makeText(mContext, info.getTitle(), 1000)
									.show();

							Intent intent = new Intent();
							Bundle bundle = new Bundle();
							bundle.putString("index", info.getId());
							bundle.putString("level", info.getLevel());
							intent.putExtra("info", bundle);
							intent.setClass(getGameContext(), AuthorityIssuedInfoContentActivity.class);
//							changeActivity(
//									AuthorityIssuedInfoContentActivity.class,
//									intent);
						}
					});
			if (authorityIssuedInfoData == null) {
				return;
			}
			authorityIssuedInfoAdapter = new SimpleAdapter(mContext,
					authorityIssuedInfoData, R.layout.infolist_item,
					new String[] { "ventureStory" },
					new int[] { R.id.policyinfo_listview2_tv });
			authorityIssuedInfoListView.setAdapter(authorityIssuedInfoAdapter);
			super.onPostExecute(infoList);
		}

		@Override
		protected void onProgressUpdate(WSError... values) {
			super.onProgressUpdate(values);
		}
	}

	@Override
	public GameActivity getGameContext() {
		// TODO Auto-generated method stub
		return this;
	}
	@Override
	public void finish() 
	{
		mContext = null;
		super.finish();
	}
}
