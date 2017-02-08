package com.zrong.Android.activity;



import com.zrong.Android.Listener.TabWidget;
import com.zrong.Android.Util.Music;
import com.zrong.Android.Util.SystemAPI;
import com.zrong.Android.game.Connection;
import com.zrong.Android.game.ConstructData;
import com.zrong.Android.game.GameData;
import com.zrong.Android.online.network.GameProtocol;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.TextView;

public class PromoterActivity extends GameActivity implements TabWidget,OnTouchListener{
	
	public static PromoterActivity mContext;
	private short id;
	private Button returnback,cancel,send,friend;
	private TextView address,content;
	private String caption_title[] ;
	private String modeContent = "";
	public static final String res_string_path1 = "string/9/";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mContext = this;
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		this.setContentView(R.layout.promoter);
		
		String str = MainActivity.resources.getString(R.string.promoterstr) +
		GameData.player.recommendCode +
		MainActivity.resources.getString(R.string.promoterstr1);
		
		modeContent=MainActivity.resources.getString(R.string.promotercontent)+GameData.player.recommendCode ;
		
		/*String strTitle[][] = SystemAPI.readStringIndex(res_string_path1 + "index.bin");
		caption_title = new String[strTitle[1].length];
		for(int i=0;i<strTitle[1].length;i++)
		{
			caption_title[i] = SystemAPI.readStringPackage(res_string_path1 +strTitle[1][i]+".bin")[0];
		}
		modeContent = caption_title[0]+"="+GameData.player.recommendCode+"¡£";*/
		
		returnback = (Button)this.findViewById(R.id.promoter_button_returnback);
		cancel = (Button)this.findViewById(R.id.promoter_button_cancel);
		send = (Button)this.findViewById(R.id.promoter_send);
		friend = (Button)this.findViewById(R.id.promoter_friend);
		
		address = (TextView)this.findViewById(R.id.promoter_address);
		content = (TextView)this.findViewById(R.id.promoter_content);
		
		address.setText(GameData.player.recommendCode);
		content.setText(str);
		
		returnback.setOnClickListener(new Button.OnClickListener() {
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				PromoterActivity.this.finish();
			}
		});
		cancel.setOnClickListener(new Button.OnClickListener() {
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				PromoterActivity.this.finish();
			}
		});
		send.setOnClickListener(new Button.OnClickListener() {
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"));
				intent.putExtra("sms_body", modeContent);
				startActivity(intent);
				
			}
		});
		friend.setOnClickListener(new Button.OnClickListener() {
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Connection.sendMessage(GameProtocol.CONNECTION_SEND_PROMOTER_Req,ConstructData.getPromoterData(id, (byte)1));
				/* Intent intent=new Intent();
				 Bundle bundle = new Bundle();*/
				 
                 //bundle.putString("friendlist", "http://218.247.140.193:9999/WapGame/study.do?cmd=chuangyegushi");
				 
				/* intent.putExtras(bundle);*/
				 
				
				/*setContentView(R.layout.promoter_detail);
				Button bn = (Button)findViewById(R.id.promoter_returnback);
				bn.setOnClickListener(new Button.OnClickListener(){

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						//setContentView(R.layout.promoter);
					}});*/
			}
		});
	}

	 
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

	 
	public void setSelectIndex(int index) {
		// TODO Auto-generated method stub
		
	}
	

	 
	public void Activitychange(Class calss, Intent intent) {
		// TODO Auto-generated method stub
		if(intent == null)
		{
			intent = new Intent();
		}
		
		intent.setClass(PromoterActivity.this, calss);
		 
	
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
