package com.zrong.Android.activity;



import com.zrong.Android.Util.Music;
import com.zrong.Android.game.Connection;
import com.zrong.Android.game.ConstructData;
import com.zrong.Android.game.GameData;
import com.zrong.Android.online.network.GameProtocol;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class DoctorInfoActivity extends GameActivity{
	public static DoctorInfoActivity mContext=null;
	private Button level,point,power,gain;
	
	 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		mContext = this;
		setContentView(R.layout.doctorinfo);
		Button turnback = (Button)findViewById(R.id.doctor_button_returnback);
		Button cancel = (Button)findViewById(R.id.doctor_button_cancel);
		Button upgrade = (Button)findViewById(R.id.doctor_button_upgrade);
		Button custody = (Button)findViewById(R.id.doctor_button_custody);
		Button task = (Button)findViewById(R.id.doctor_button_task);
		Button shop = (Button)findViewById(R.id.doctor_button_shop);
		level = (Button)findViewById(R.id.doctor_button_level);
		point = (Button)findViewById(R.id.doctor_button_points);
		power = (Button)findViewById(R.id.doctor_button_strength);
		gain = (Button)findViewById(R.id.doctor_button_shopincome);
		
		level.setText(String.valueOf(GameData.doctorLevel));
		point.setText(String.valueOf(GameData.doctorPoint));
	
	
		power.setText(String.valueOf(GameData.doctorPower +"/"+GameData.doctorMaxPower));
		gain.setText(String.valueOf(GameData.doctorGain));
		
		turnback.setOnClickListener(new Button.OnClickListener(){

			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				DoctorInfoActivity.mContext.finish();
				
			}});
		cancel.setOnClickListener(new Button.OnClickListener(){
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				DoctorInfoActivity.mContext.finish();
				
			}});
		custody.setOnClickListener(new Button.OnClickListener(){
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mContext.Activitychange(DoctorCustodyActivity.class, null);
				DoctorInfoActivity.mContext.finish();
				
			}});
		task.setOnClickListener(new Button.OnClickListener(){
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mContext.Activitychange(DoctorTaskActivity.class, null);
				DoctorInfoActivity.mContext.finish();
				
			}});
		shop.setOnClickListener(new Button.OnClickListener(){
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//发包请求图片数据，然后在收包处，跳转界面
				//mContext.Activitychange(DoctorShopActivity.class, null);
				//mContext.Activitychange(PlanningDeptActivity.class, null);
				mContext.Activitychange(DoctorShopActivity.class, null);
				DoctorInfoActivity.mContext.finish();
				
			}});
		upgrade.setOnClickListener(new Button.OnClickListener(){
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//发包,在收包处跳转到DoctorLevelUpActivity界面
			//	Connection.sendMessage(GameProtocol.CONNECTION_REQ_LEVELUP_VIEW,ConstructData.getLevelUPInfo((byte)3, 0));
			/*	Intent intent = new Intent();

				Bundle bundle = new Bundle();

				bundle.putByte("type", (byte) 3);
				intent.putExtra("doctor", bundle);

				//intent.putExtras(bundle);

				intent.setClass(DoctorInfoActivity.this,
						DepartmentLevelInfoActivity.class);*/
				
			  Connection.sendMessage(GameProtocol.CONNECTION_REQ_LEVELUP_VIEW,ConstructData.getLevelUPInfo((byte)3, 0));
			}});
	}

	
	
	
	public void Activitychange(Class calss, Intent intent) {
		// TODO Auto-generated method stub
		if (intent == null) {
			intent = new Intent();
		}
		intent.setClass(DoctorInfoActivity.this, calss);
		
		
		this.startActivity(intent);
		
	}
	
	public  void updateDate(){
		level.setText(String.valueOf(GameData.doctorLevel));
		point.setText(String.valueOf(GameData.doctorPoint));	
		power.setText(String.valueOf(GameData.doctorPower +"/"+GameData.doctorMaxPower));
		gain.setText(String.valueOf(GameData.doctorGain));
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
