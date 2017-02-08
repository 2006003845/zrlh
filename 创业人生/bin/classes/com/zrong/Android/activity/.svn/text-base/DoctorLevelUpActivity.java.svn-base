package com.zrong.Android.activity;

import java.util.Vector;

import com.zrong.Android.Util.Music;
import com.zrong.Android.game.Connection;
import com.zrong.Android.game.ConstructData;
import com.zrong.Android.game.GameData;
import com.zrong.Android.online.network.GameProtocol;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DoctorLevelUpActivity extends GameActivity{
	public static DoctorLevelUpActivity mContext = null;
	private Context context;
	private Vector<Vector> vector;// 升级条件信息
	private EditText levelUpConditionInfo;
	private EditText levelUpResultInfo;

	private long companyId;

	private String unmatchedCinditionStr = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				              WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.doctor_levelup);
		mContext = this;
		
		//Intent intent = getIntent();
		//companyId = intent.getExtras().getLong("id");

		levelUpConditionInfo = (EditText) this
				.findViewById(R.id.et_levelupConditionInfo);
		levelUpResultInfo = (EditText) this
				.findViewById(R.id.et_levelupResultInfo);
		levelUpConditionInfo.setSelection(0);// 设置光标
		levelUpResultInfo.setSelection(0);
		Button title = (Button) this.findViewById(R.id.leveupInfo_button_title);
		setText();
		// 返回键
		Button returnback = (Button) this
				.findViewById(R.id.leveupInfo_button_returnback);
		returnback.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				DoctorLevelUpActivity.this.finish();
			}
		});
		// 取消键
		Button cancel = (Button) this
				.findViewById(R.id.leveupInfo_button_cancel);
		cancel.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				DoctorLevelUpActivity.this.finish();
			}
		});
		Log.i("Log", "company--levelButton");
		// 升级确定键
		Button levelupConfirm = (Button) this
				.findViewById(R.id.levelup_button_confirm);
		levelupConfirm.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				if (isMatchToLevelUp()) {
					Log.i("Log", "MatchTOLevel");
					Connection.sendMessage(GameProtocol.CONNECTION_REQ_LEVELUP,
							ConstructData.getLevelUPInfo((byte) 0, companyId));
					DoctorLevelUpActivity.this.finish();
				} else {
					Log.i("Log", "UNMatchTOLevel");
					// 弹出提示框---级别不够，无法升级
					Toast toast = Toast.makeText(DoctorLevelUpActivity.this,
							MainActivity.resources.getString(R.string.Companylevelinfo_message), 1000);
					toast.show();
				}
			}
		});

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
		if (unmatchedCinditionStr == null || unmatchedCinditionStr.equals(""))
			return true;
		return false;
	}
	
	
	
	
	
	public void Activitychange(Class calss, Intent intent) {
		// TODO Auto-generated method stub
		if (intent == null) {
			intent = new Intent();
		}
		intent.setClass(DoctorLevelUpActivity.this, calss);
		
		this.startActivity(intent);
		
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
