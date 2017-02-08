package com.zrong.Android.activity;

import com.zrong.Android.Util.Music;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class Lottery_RulesActivity extends GameActivity{
	public static Lottery_RulesActivity mContext = null;
	 
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		mContext = this;
		setContentView(R.layout.lottery_rules);
		Button turnback = (Button)findViewById(R.id.rules_button_returnback);
		Button cancel = (Button)findViewById(R.id.rules_button_cancel);
		TextView tv = (TextView)findViewById(R.id.rules_textview);
		tv.setText(R.string.lottery_rules);
		tv.setMovementMethod(ScrollingMovementMethod.getInstance());
		
		turnback.setOnClickListener(new Button.OnClickListener(){

			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Lottery_RulesActivity.mContext.finish();
				
			}});
		cancel.setOnClickListener(new Button.OnClickListener(){
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Lottery_RulesActivity.mContext.finish();
			}});
	}

	
	
	
	 
	public void Activitychange(Class calss, Intent intent) {
		// TODO Auto-generated method stub
		if (intent == null) {
			intent = new Intent();
		}
		intent.setClass(Lottery_RulesActivity.this, calss);
		
		this.startActivity(intent);
		
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
