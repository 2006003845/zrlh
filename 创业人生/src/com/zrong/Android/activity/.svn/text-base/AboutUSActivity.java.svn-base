package com.zrong.Android.activity;

 

import java.util.ArrayList;
import java.util.Arrays;

import java.util.HashMap;

import org.w3c.dom.Text;

 

import com.zrong.Android.Listener.ActivityTransform;
import com.zrong.Android.Util.Music;
import com.zrong.Android.Util.xmlPhaser.XElem;
import com.zrong.Android.game.Connection;
import com.zrong.Android.game.ConstructData;
import com.zrong.Android.game.GameDef;
import com.zrong.Android.online.network.GameProtocol;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class AboutUSActivity extends GameActivity{

	

	/*public static final String [] menu ={"关于我们","客服电话：010—62259819","客服邮箱：13810483124@139.com",
		                                 "联系地址：北京市海淀区西外大街168号腾达大厦701室",
		                                 "版权所有：兆荣联合（北京）科技发展有限公司",
		                                 "出品人：张新   刘泽琦","制作人：丛菲","策划：许日   晋江   李昕君",
		                                 "客户端技术：杨征    蒋绪金     陈晴    王青松     赵忠祥      李英鹏      张小清     周志龙  ",
	                                     };*/
	public static final String  aboutus_message = MainActivity.resources.getString(R.string.aboutus_message);
 
	
 
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		 
//		mContext = this;
 
		this.setContentView(R.layout.about);
		TextView tv = (TextView)findViewById(R.id.about_textview);
		tv.setText(aboutus_message);
		tv.setMovementMethod(ScrollingMovementMethod.getInstance());
	        
	        Button turnback=(Button)findViewById(R.id.about_button_returnback);
	       
	        turnback.setOnClickListener(new Button.OnClickListener()
	        {
				public void onClick(View v)
				{
					AboutUSActivity.this.finish();
				}
			});
	       
	        Button cancel=(Button)findViewById(R.id.about_button_cancel);

	        cancel.setOnClickListener(new Button.OnClickListener()
	        {
	        	public void onClick(View v)
	        	{
	        		AboutUSActivity.this.finish();
	        	}
	        });
				
	}
	@Override
	public GameActivity getGameContext() 
	{
		return this;
	}
}

