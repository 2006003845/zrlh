package com.zrong.Android.activity; 
 

import java.util.ArrayList; 
import java.util.HashMap; 

 

import com.zrong.Android.Util.Music;
import com.zrong.Android.game.Connection;
import com.zrong.Android.game.ConstructData;
import com.zrong.Android.online.network.GameProtocol;

import android.app.Activity;
import android.content.Intent; 
import android.os.Bundle; 
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView; 
public class SystemSettingActivity extends GameActivity {

	ArrayList<HashMap<String, String>> list = null;
	SimpleAdapter listAdapter = null;
	public static final  String [] name ={ MainActivity.resources.getString(R.string.systemsetting_name1),
		                                   MainActivity.resources.getString(R.string.systemsetting_name2),
		                                 /*"接受商会邀请  ",*/
		                                   MainActivity.resources.getString(R.string.systemsetting_name3),
		                                   MainActivity.resources.getString(R.string.systemsetting_name4)
		                                 };
	
	public static boolean [] state ={false,false,/*false,*/false,false};
	
 
	public static SystemSettingActivity mContext; 
	
	private ListView lv;
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
 
		mContext = this;
		
		
 
		this.setContentView(R.layout.systemsetting);
		lv = (ListView)this.findViewById(R.id.systemsetting_listview);
		
		list = new ArrayList<HashMap<String,String>>();
		 HashMap<String, String> map;
		 for (int i = 0; i < name.length; i++) 
		 {
			 map = new HashMap<String, String>();
			 
			 map.put("name", name[i]);
			 
			 if(i<state.length)
			 {
				 map.put("state", state[i]? MainActivity.resources.getString(R.string.systemsetting_open): MainActivity.resources.getString(R.string.systemsetting_close));
			 }			 
			 list.add(map);
		 }
		 
		 listAdapter = new SimpleAdapter(this,list,   
	                R.layout.systemsetting_item, new String[] {"name","state"},   
	                new int[] {R.id.systemsetting_name,R.id.systemsetting_state});
	  
	        lv.setAdapter(listAdapter);
	        
	        lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
			 {

				public void onItemClick(AdapterView<?> arg0, View v,
						int index, long arg3) {
					 Log.i("SystemSetting", arg3+"");
				 TextView tv = null;
			/*	 View vv =  lv.getChildAt(index);
				 Log.i("SystemSetting", (vv==null)+"");*/
					 tv =(TextView)v.findViewById(R.id.systemsetting_state);
					 state[index]=!state[index]; 
					
/*					 HashMap<String, String> map = (HashMap<String, String>)arg0.getItemAtPosition(index);
					 map.put("state", state[index]?"开启":"关闭");
					 list.set(index, map);*/
					 
					 tv.setText(state[index]? MainActivity.resources.getString(R.string.systemsetting_open): MainActivity.resources.getString(R.string.systemsetting_close));
					 
//					listAdapter.notifyDataSetChanged();
					 
					 
					 
					 switch(index)
					 {
					 case 0:
						 if(state[index])//开启音乐
						 { 
							 Music.getInstance(MainActivity.mContext).resume();
						 }
						 else//关闭音乐
						 {
							 Music.getInstance(MainActivity.mContext).stop();
						 }
						 break;
					 case 1:
						 break;
					 case 2:
						 break;
					 case 3:
						 break;
					 case 4:
						 break;
					 }
				}
	 }
	);
	        Button turnback=(Button)findViewById(R.id.systemsetting_button_returnback);
	       
	        turnback.setOnClickListener(new Button.OnClickListener()
	        {

				 
				public void onClick(View v)
				{
					int [] s = new int[4];
					s[0] = state[1]?0:1;//h好友
					s[1] =1;//商会
					s[2]= state[2]?0:1;//世界
					s[3]= state[3]?0:1;//私聊
					Connection.sendMessage(GameProtocol.SYSTEM_SETING, ConstructData.getSystemInfo(s)); 
					SystemSettingActivity.this.finish();
				}
			});
	       
	        Button cancel=(Button)findViewById(R.id.systemsetting_button_cancel);

	        cancel.setOnClickListener(new Button.OnClickListener()
	        {
	        	
	        	 
	        	public void onClick(View v)
	        	{
	        		// TODO Auto-generated method stub
	        		int [] s = new int[4];
					s[0] = state[1]?0:1;//h好友
					s[1] =1;//商会
					s[2]= state[2]?0:1;//世界
					s[3]= state[3]?0:1;//私聊
					Connection.sendMessage(GameProtocol.SYSTEM_SETING, ConstructData.getSystemInfo(s)); 
					SystemSettingActivity.this.finish();
	        	}
	        });
				
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

