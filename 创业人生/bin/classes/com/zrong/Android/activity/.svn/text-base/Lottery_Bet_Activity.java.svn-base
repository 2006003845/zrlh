package com.zrong.Android.activity;



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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Lottery_Bet_Activity extends GameActivity{
	public static Lottery_Bet_Activity mContext = null;
	private EditText number;
	private EditText count;
	 
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		mContext = this;
		setContentView(R.layout.lottery_bet);
		 number = (EditText)findViewById(R.id.lottery_bet_edit_number);
		 count = (EditText)findViewById(R.id.lottery_bet_edit_buy_number);
		Button confirm = (Button)findViewById(R.id.lottery_bet_button_ok);
		Button cancel = (Button)findViewById(R.id.lottery_station_cancel);
		
		number.setHint(MainActivity.resources.getString(R.string.lotterybet_num_hint));
		count.setHint(MainActivity.resources.getString(R.string.lotterybet_count_hint));
		
		
		
		confirm.setOnClickListener(new Button.OnClickListener(){

			 
			public void onClick(View v) {
				//Log.i("LOG", "confirm");
				 String num = number.getText().toString().trim() ;
				 String cout = count.getText().toString().trim() ;
				 if(num.equals("")&&cout.equals(""))
				 {
					 Display(MainActivity.resources.getString(R.string.lotterybet_toast1));
				 }
				 else if(!cout.equals("")&&num.equals(""))
				 {
					 Display(MainActivity.resources.getString(R.string.lotterybet_toast2)); 
				 }
				 else if(!num.equals("")&&cout.equals(""))
				 {
					 Display(MainActivity.resources.getString(R.string.lotterybet_toast3));
				 }
				 else
				 {
				   int lottery_count=Integer.parseInt(cout);
				   int lottery_num=Integer.parseInt(num);
				   if(lottery_num==0||lottery_num>16||lottery_count==0)
				   {
					   Display(MainActivity.resources.getString(R.string.lotterybet_toast4)); 
				   }else
				   {
						Connection.sendMessage(GameProtocol.LOTTERY_TACKET_BUY, ConstructData.getLotteryTicketBuy(String.valueOf(lottery_num), (short)lottery_count));

					 //  Display("good!");
				   }
				   }
				// Log.i("LOG", "num"+num);
				// TODO Auto-generated method stub
				/* if()
				{
					Display("您输入的数字超出范围！");
				}else if())
				{
					Display("您的购买数量为零！");
				}else if()
				{
					Display("good!");
					
				}*/
				
			}});
		cancel.setOnClickListener(new Button.OnClickListener(){
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Lottery_Bet_Activity.mContext.finish();
				
			}});
	}
   public void Display(String str)
   {
	   Toast.makeText(this, str, Toast.LENGTH_SHORT).show(); 
   }
	
	
	
	public void Activitychange(Class calss, Intent intent) {
		// TODO Auto-generated method stub
		if (intent == null) {
			intent = new Intent();
		}
		intent.setClass(Lottery_Bet_Activity.this, calss);
		
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
