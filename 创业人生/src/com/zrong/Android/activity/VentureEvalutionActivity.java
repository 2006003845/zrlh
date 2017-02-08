package com.zrong.Android.activity;

import com.zrong.Android.element.Task;
import com.zrong.Android.game.Connection;
import com.zrong.Android.game.ConstructData;
import com.zrong.Android.game.GameData;
import com.zrong.Android.online.network.GameProtocol;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class VentureEvalutionActivity extends GameActivity {

	public static VentureEvalutionActivity mContext = null;

	 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		mContext = this;
		setContentView(R.layout.venture_evaluation);
		Button returnback = (Button) this
				.findViewById(R.id.venture_evaluation_button_returnback);
		Button cancel = (Button) this
				.findViewById(R.id.venture_evaluation_button_cancel);
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
		TextView tv1 = (TextView) this.findViewById(R.id.venture_evalution_tv1);
		TextView tv2 = (TextView) this.findViewById(R.id.venture_evalution_tv2);
		final Task task[] = GameData.task[10];
		if(task != null){
			tv1.setText(task[0].description);
			tv2.setText(task[1].description);
		}		
		RelativeLayout relative1 = (RelativeLayout) this
				.findViewById(R.id.venture_evaluation_relative1);
		RelativeLayout relative2 = (RelativeLayout) this
				.findViewById(R.id.venture_evaluation_relative2);
		relative1.setOnClickListener(new OnClickListener() {
			 
			public void onClick(View v) {
				Toast.makeText(mContext, "自我认知测评", 2000).show();
			Log.i("Log", "taskId:----"+task[0].id);
//				changeActivity(TaskDetailActivity.class,intent);
				Connection.sendMessage(
						GameProtocol.CONNECTION_SEND_TASK_DETAIL, ConstructData
								.getTaskData(task[0].id));
			}
		});

		relative2.setOnClickListener(new OnClickListener() {
			 
			public void onClick(View v) {
				Log.i("Log", "taskId:----"+task[1].id);
				Toast.makeText(mContext, "创业潜力测评", 2000).show();
				Connection.sendMessage(
						GameProtocol.CONNECTION_SEND_TASK_DETAIL, ConstructData
								.getTaskData(task[1].id));
			}
		});

	}
	
	 
	protected void onResume() {
		super.onResume();
	}

	 
	protected void onStop() {
		super.onStop();
	}

	 
	protected void onDestroy() {
		super.onDestroy();
	}

	public  void changeActivity(Class calss, Intent intent) {
		Log.i("Log", "VentureEvalution--changeActivity");
		if (intent == null) {
			intent = new Intent();
		}
		if (calss == null) {
			return;
		}
		intent.setClass(mContext, calss);
		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		mContext.startActivity(intent);
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
