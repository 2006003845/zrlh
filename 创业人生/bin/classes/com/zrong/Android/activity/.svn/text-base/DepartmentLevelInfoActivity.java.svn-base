package com.zrong.Android.activity;

import java.util.Vector;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zrong.Android.game.Connection;
import com.zrong.Android.game.ConstructData;
import com.zrong.Android.game.GameData;
import com.zrong.Android.online.network.GameProtocol;

public class DepartmentLevelInfoActivity extends GameActivity {
	 
	private Vector<Vector> vector = null;// 升级条件信息
	private EditText levelUpConditionInfo;
	private EditText levelUpResultInfo;
	private long departmentId;
	public boolean isDoctor = false;
	
	//不符合的条件
	private String unmatchedCinditionStr = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.levelup_info);
		//
		Intent intent = getIntent();
		if(intent.getExtras().getString("doctor") != null && intent.getExtras().getString("doctor").equals("isDoctor")){
			isDoctor = true;	
		}
		departmentId = intent.getExtras().getLong("id");
		levelUpConditionInfo = (EditText) this
				.findViewById(R.id.et_levelupConditionInfo);
		levelUpResultInfo = (EditText) this
				.findViewById(R.id.et_levelupResultInfo);

		levelUpConditionInfo.setSelection(0);// 设置光标
		levelUpResultInfo.setSelection(0);

		

		// 返回键

		Button title = (Button) this.findViewById(R.id.leveupInfo_button_title);
		if(isDoctor==true){
			title.setText(MainActivity.resources.getString(R.string.doctorlevelup_title));
		}else{
		title.setText(GameData.getDepartment(departmentId).name);
		}
		Button returnback = (Button) this
				.findViewById(R.id.leveupInfo_button_returnback);
		returnback.setOnClickListener(new OnClickListener() {

			 
			public void onClick(View v) {
				DepartmentLevelInfoActivity.this.finish();
			}
		});
		// 取消键
		Button cancel = (Button) this
				.findViewById(R.id.leveupInfo_button_cancel);
		cancel.setOnClickListener(new OnClickListener() {

		 
			public void onClick(View v) {
				DepartmentLevelInfoActivity.this.finish();
			}
		});

		// 升级确定键
		Button levelupConfirm = (Button) this
				.findViewById(R.id.levelup_button_confirm);
		Log.i("Log", "levelup");
		levelupConfirm.setOnClickListener(new OnClickListener() {

			 
			public void onClick(View v) {
				if (isMatchToLevelUp()) {
					Log.i("Log", "MatchTOLevel");
					if(isDoctor){
						Connection.sendMessage(GameProtocol.CONNECTION_REQ_LEVELUP,ConstructData.getLevelUPInfo((byte)3, departmentId));
					}else{
					Connection.sendMessage(GameProtocol.CONNECTION_REQ_LEVELUP,
							ConstructData
									.getLevelUPInfo((byte) 1, departmentId));
					DepartmentLevelInfoActivity.this.finish();
					}
				} else {
					Log.i("Log", "UNMatchTOLevel");
					// 弹出提示框---级别不够，无法升级
					Toast.makeText(DepartmentLevelInfoActivity.this,
							MainActivity.resources.getString(R.string.departmentlevel_info_toast), 1000).show();
				}
			}
		});

		setText();

	}

	/**
	 * 显示升级条件与升级效果
	 */
	private void setText() {
		vector = GameData.levelUpInfo;
		String matchedCinditionStr = "";
		Vector v0 = vector.elementAt(0);// 符合的条件
		if (v0 != null && !v0.isEmpty()) {
			matchedCinditionStr = matchedCinditionStr + v0.elementAt(0) + "\n";
			matchedCinditionStr = matchedCinditionStr.substring(3);
			v0.remove(0);

			while (!v0.isEmpty()) {
				if (v0.elementAt(0) != null) {
					matchedCinditionStr = matchedCinditionStr + v0.elementAt(0)
							+ "\n";
				}
				v0.remove(0);
			}
		}

		
		Vector v1 = vector.elementAt(1);// 不符合的条件
		if (v1 != null && !v1.isEmpty()) {
			unmatchedCinditionStr = unmatchedCinditionStr + v1.elementAt(0)
					+ "\n";
			unmatchedCinditionStr = unmatchedCinditionStr.substring(3);

			v1.remove(0);
			while (!v1.isEmpty()) {
				if (v1.elementAt(0) != null) {
					unmatchedCinditionStr = unmatchedCinditionStr
							+ v1.elementAt(0) + "\n";
				}
				v1.remove(0);
			}

		}

		Spanned span = Html.fromHtml(matchedCinditionStr + "\n"
				+ "<font color=#ff0000>" + unmatchedCinditionStr + "</font>");
		levelUpConditionInfo.setText(span);

		String resultInfoStr = "";
		Vector v2 = vector.elementAt(2);// 升级效果
		while (!v2.isEmpty()) {
			if (v2.elementAt(0) != null) {
				resultInfoStr = resultInfoStr + v2.elementAt(0) + "\n";
			}
			v2.remove(0);
		}
		levelUpResultInfo.setText(resultInfoStr);
	}

	/**
	 * 判断条件是否允许升级
	 * 
	 * @return
	 */
	private boolean isMatchToLevelUp() {
		if (unmatchedCinditionStr==null || unmatchedCinditionStr.equals(""))
			return true;
		return false;
	}
	
	@Override
	protected void onStop() {
		isDoctor = false;
		super.onStop();
	}
	
	@Override
	protected void onDestroy() {
		if(isDoctor == true){
			isDoctor =  false;
		}
		super.onDestroy();
	}

	@Override
	public GameActivity getGameContext() {
		// TODO Auto-generated method stub
		return this;
	}
	
	
	
	

}
