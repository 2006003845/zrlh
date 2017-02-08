package com.zrong.Android.activity;



import java.util.ArrayList;
import java.util.HashMap;

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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class Lottery_Station_Activity extends GameActivity{
	public static Lottery_Station_Activity mContext = null;
	private ListView lv;
	private int a;

	 
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		mContext = this;
		setContentView(R.layout.lottery_station);
		
		Button turnback = (Button)findViewById(R.id.lottery_station_returnback);
		Button cancel = (Button)findViewById(R.id.lottery_station_cancel);
		Button bet = (Button)findViewById(R.id.lottery_station_button_info);
		Button rules = (Button)findViewById(R.id.lottery_station_button_uper);
		lv = (ListView)findViewById(R.id.lottery_stationlistview);
	    ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
				
		HashMap<String, String> map;
		
		for(int i = 0; i<GameData.lottery_tickey_info.length/3;i++)
		{   
			map = new HashMap<String, String>();
			a=Integer.parseInt(GameData.lottery_tickey_info[3*i+2]);
			map.put("issued_number", GameData.lottery_tickey_info[3*i]);
			map.put("ticket_number", GameData.lottery_tickey_info[3*i+1]);
			//map.put("state", type.equals("1")?"未中奖":(type.equals(2)?"未领取":"已领取"));
		    map.put("state", a==0? MainActivity.resources.getString(R.string.lotterystation_state0):(a==1? MainActivity.resources.getString(R.string.lotterystation_state1):MainActivity.resources.getString(R.string.lotterystation_state2)));
			list.add(map);
			
		}
		
		SimpleAdapter listAdapter = new SimpleAdapter(this, list, R.layout.lottery_station_item,
				                                       new String[]{"issued_number","ticket_number","state"},
				                                       new int[]{R.id.issued_number,R.id.ticket_number,R.id.ticket_bet_number});
		
		lv.setAdapter(listAdapter);
		
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			 
			public void onItemClick(AdapterView<?> arg0, View v, int index,
					long arg3) {
				// TODO Auto-generated method stub
				Connection.sendMessage(GameProtocol.LOTTERY_TACKET_GET_MONEY, ConstructData.getLotteryTicketMoney((byte)1));
				/*if(GameData.lottery_tickey_info[3*index+2].equals("2"))
				{
			
				}*/
				a=Integer.parseInt(GameData.lottery_tickey_info[3*index+2]);
				if(a==1)
				{
					a=2;
				}
				TextView tv ;
			    tv =(TextView)v.findViewById(R.id.ticket_bet_number);
			    tv.setText(a==0?MainActivity.resources.getString(R.string.lotterystation_state0):(a==1?MainActivity.resources.getString(R.string.lotterystation_state2):MainActivity.resources.getString(R.string.lotterystation_state2)));
 
			}
		});
		listAdapter.notifyDataSetChanged();
		turnback.setOnClickListener(new Button.OnClickListener(){

			 
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Lottery_Station_Activity.mContext.finish();
			}});
		cancel.setOnClickListener(new Button.OnClickListener(){
			
			 
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Lottery_Station_Activity.mContext.finish();
			}});
		bet.setOnClickListener(new Button.OnClickListener(){
			
			 
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
					mContext.Activitychange(Lottery_Bet_Activity.class, null);
					
				
			}});
		rules.setOnClickListener(new Button.OnClickListener(){
			
			 
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				mContext.Activitychange(Lottery_RulesActivity.class, null);
			}});
 
	}


	
	public void Activitychange(Class calss, Intent intent) {
		// TODO Auto-generated method stub
		if (intent == null) {
			intent = new Intent();
		}
		intent.setClass(Lottery_Station_Activity.this, calss);
		
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
