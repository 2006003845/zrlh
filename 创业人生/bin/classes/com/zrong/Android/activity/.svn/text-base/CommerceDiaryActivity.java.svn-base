package com.zrong.Android.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;



import com.zrong.Android.Util.Music;
import com.zrong.Android.game.GameData;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class CommerceDiaryActivity extends GameActivity implements TabWidget {
	public static CommerceDiaryActivity mContext = null;
	TextView tv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		mContext = this;
		setContentView(R.layout.commercedairy);
		
		Button returnback = (Button)findViewById(R.id.commercediary_returnback);
		Button cancel = (Button)findViewById(R.id.commercediary_cancel);
		Button info = (Button)findViewById(R.id.commercediary_button_info);
		Button uper = (Button)findViewById(R.id.commercediary_button_uper);
		Button member = (Button)findViewById(R.id.commercediary_button_member);
		
		
		returnback.setOnClickListener(new Button.OnClickListener(){

			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CommerceDiaryActivity.this.finish();
			}});
		cancel.setOnClickListener(new Button.OnClickListener(){
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CommerceDiaryActivity.this.finish();
			}});
		
		info.setOnClickListener(new Button.OnClickListener(){
			
		 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mContext.Activitychange(CommerceInfoActivity.class, null);
				CommerceDiaryActivity.this.finish();
			}});
		uper.setOnClickListener(new Button.OnClickListener(){
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mContext.Activitychange(CommerceUperActivity.class, null);
				CommerceDiaryActivity.this.finish();
			}});
		member.setOnClickListener(new Button.OnClickListener(){
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mContext.Activitychange(CommerceStaffActivity.class, null);
				CommerceDiaryActivity.this.finish();
			}});
		
		
		
		tv = (TextView)this.findViewById(R.id.commercediarytextview);
		tv.setMovementMethod(ScrollingMovementMethod.getInstance());
		
		StringBuffer buffer=new StringBuffer();
    	String mebstr[];
    	for(int i = 0; i< GameData.mebstr.size();i++)
    	{
    		mebstr=(String[])GameData.mebstr.elementAt(i);
    		if(mebstr!=null)
    		{
    			for(int j=0;j<mebstr.length;j++)
    			{
    				buffer.append(mebstr[j]==null?"":mebstr[j]);
    			}
    		}
    		buffer.append("\n");
    	}    	
    	tv.setText(buffer.toString());
    	
    	
		/*ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		HashMap<String, String> map = new HashMap<String, String>();
		for(int i=0;i<5;i++)
		{
			map.put("diary", "ÀîÄþ");
			list.add(map);
		}
		SimpleAdapter lisAdapter = new SimpleAdapter(this, list,
				                   R.layout.commercediary_item, new String[]{"diary"},
				                   new int[]{R.id.diary});
		lv.setAdapter(lisAdapter);*/
		
	}
/*	 public void setinit(Vector str)
	    {
		//	GameData.mebstr.add(str);
	    	StringBuffer buffer=new StringBuffer();
	    	String mebstr[];
	    	for(int i = 0; i< GameData.mebstr.size();i++)
	    	{
	    		mebstr=(String[])GameData.mebstr.elementAt(i);
	    		if(mebstr!=null)
	    		{
	    			for(int j=0;j<mebstr.length;j++)
	    			{
	    				buffer.append(mebstr[j]==null?"":mebstr[j]);
	    			}
	    		}
	    		buffer.append("\n");
	    	}
	    	//System.out.println("buffer.toString() == " + (buffer.toString()));
	    	tv.setText(buffer.toString());
	    }*/
	 
	 public void setSelectIndex(int index) {
			// TODO Auto-generated method stub
			
		}
	public void Activitychange(Class calss, Intent intent) {
		// TODO Auto-generated method stub
		if(intent==null)
		{
			intent = new Intent();
		}
		intent.setClass(CommerceDiaryActivity.this, calss);
		
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
