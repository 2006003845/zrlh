package com.zrong.Android.logic;

import java.util.Hashtable;

import java.util.Vector;

import com.zrong.Android.View.Event; 
import com.zrong.Android.activity.MainActivity;
import com.zrong.Android.activity.MoreGame;
import com.zrong.Android.activity.R;
import com.zrong.Android.activity.RegistActivity;
import com.zrong.Android.game.EventManager; 
import com.zrong.Android.game.GameDefinition;
import com.zrong.Android.game.GameGroupControl; 

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;

public class Login extends LogicObject{

	public static final String TAG ="Login";
	
	private Button button_login,button_return;
	
	private String userName = null;
	
	private String password = null;
	
	public Login(Context context,GameGroupControl control)
	{
		super(context,control);
	}

	public void loadProperties(Vector v)
	{
		
		if(v != null && v.size() > 1)
		{
			this.userName = String.valueOf(v.elementAt(0));
			this.password = String.valueOf(v.elementAt(1));
			synchviewdata();
		}
		
	}

	@Override
	protected void reCycle()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void refurbish() 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(){
		
	}


	@Override
	public void initView() {
		
	 View v = View.inflate(context, R.layout.login, null);//获得View
	
	 registerView(v);//注册view
	
	 v.setId(GameDefinition.LoginView);//给view设置id
		
		    
	    
     button_login = (Button)v.findViewById(R.id.lottery_bet_button_ok);
	 button_return = (Button)v.findViewById(R.id.lottery_station_cancel);
	 
//	 button_login.setTypeface(GameDefinition.face);
//	 TextPaint tp = button_return.getPaint(); 
//	 //tp.setTypeface(Typeface.);
//	 tp.setFakeBoldText(true);
	 //tp.setStyle(Style.)
	  
	 button_return.setOnClickListener(new OnClickListener()
	  {
		public void onClick(View view)
		{
			GameGroupControl.gameGroupControl.setGameStatus(GameDefinition.Game_MainMenu,null);
		}
	  });
		  
		  
		  button_login.setOnClickListener(new OnClickListener()
		  {
			  
			  public void onClick(View arg0){
				  
				  Log.v(TAG, "button_login");
				  
				  userName = ((EditText)getView().findViewById(R.id.lottery_bet_edit_number)).getText().toString().trim();
				  
				  password =((EditText)getView().findViewById(R.id.lottery_bet_edit_buy_number)).getText().toString();
				  
				  Hashtable table = new Hashtable();
				  
				  table.put("name", userName);
				  table.put("password", password);
				  
				  Event e = new Event(EventManager.ACTION_LOGIN,table);
				  
				  Event e1 = new Event(EventManager.ACTION_LOGIN_SEVER,table);
				  
				  
				  
				  control.eventManger.addEvent(e).addEvent(e1);
				  
				  control.eventManger.nextAction();
			  }
		  }
		  );
		  
		  synchviewdata();
	}
	 
	public void init() 
	{
		initView();
	}
	
	@Override
	public void synchviewdata(){
		
		View v = this.getView();
		
		if(v == null) return;
		
		EditText accout = (EditText) v.findViewById(R.id.lottery_bet_edit_number);
		
		EditText password = (EditText) v.findViewById(R.id.lottery_bet_edit_buy_number);
		
		accout.setText(this.userName == null ? "" : this.userName);
		
		password.setText(this.password== null ? "" : this.password);
		
	}

	 


	 


 


	 

}
