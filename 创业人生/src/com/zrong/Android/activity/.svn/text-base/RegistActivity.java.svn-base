package com.zrong.Android.activity;

import java.util.Hashtable;

 
import com.zrong.Android.Util.Preferences;
 
import com.zrong.Android.Util.Music;
 
import com.zrong.Android.View.Event;
import com.zrong.Android.game.EventManager;
import com.zrong.Android.game.GameGroupControl;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegistActivity extends GameActivity
{
	public static RegistActivity mContext = null;
	private Button button_Regist,button_cancel;
	private EditText editText_account,editText_password,editText_pwcheck;
	private TextView textview_userRegist,textview_account,textview_password,textview_pw_check;
	 
	public void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		mContext = this;
		setContentView(R.layout.regist);
		//textview_userRegist=(TextView)findViewById(R.id.textView_userRegist);
//		textview_account=(TextView)findViewById(R.id.textView_account);
//		textview_password=(TextView)findViewById(R.id.textView_password);
//		textview_pw_check=(TextView)findViewById(R.id.textView_pw_check);
		button_Regist=(Button)findViewById(R.id.lottery_bet_button_ok);
		button_cancel=(Button)findViewById(R.id.lottery_station_cancel);
		editText_account=(EditText)findViewById(R.id.lottery_bet_edit_number);
		editText_password=(EditText)findViewById(R.id.lottery_bet_edit_buy_number);
		editText_pwcheck=(EditText)findViewById(R.id.lottery_bet_edit_buy_confirm);
		
		editText_account.setHint(MainActivity.resources.getString(R.string.regist_account_hint));
		editText_password.setHint(MainActivity.resources.getString(R.string.regist_password_hint));
		editText_pwcheck.setHint(MainActivity.resources.getString(R.string.regist_pwcheck_hint));
/*		editText_account.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final AlertDialog updatedialog=new AlertDialog.Builder(RegistActivity.mContext).create();
				updatedialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				updatedialog.getWindow().setFlags(
						WindowManager.LayoutParams.FLAG_FULLSCREEN,
						WindowManager.LayoutParams.FLAG_FULLSCREEN);
				
				//显示软键盘
			//	LayoutInflater factory=LayoutInflater.from(RegistActivity.mContext);
			//	View view=factory.inflate(R.layout.invite_player_dlg, null);
			//	view.findFocus();
				updatedialog.show();
				//显示修改密码布局文件
			//	updatedialog.getWindow().setContentView(view);
				//设置软键盘
				getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
				InputMethodManager imm=(InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
				//imm.showSoftInput(view, 0);
				imm.toggleSoftInput(0,InputMethodManager.HIDE_NOT_ALWAYS);
			}
		});*/
		button_Regist.setOnClickListener(new OnClickListener()
		  {
			   public void onClick(View arg0)
			   {
				   
				String password=  editText_password.getText().toString();
				String password_check=editText_pwcheck.getText().toString();
				String account=editText_account.getText().toString();
				
				if(account.length()<2)
				{
					 Toast.makeText(RegistActivity.this, MainActivity.resources.getString(R.string.regist_toast),Toast.LENGTH_LONG).show();
				}
				
				
				if(password.equals(password_check))
				{
					//执行具体操作
					Hashtable hash = new Hashtable();
					hash.put("name", editText_account.getText().toString());
					hash.put("password", editText_password.getText().toString());
					GameGroupControl.eventManger.removeAllEvent();
					Event e = new Event(EventManager.ACTION_REGIST,hash);
					GameGroupControl.eventManger.addEvent(e);
					
					Event e1 = new Event(EventManager.ACTION_LOGIN_SEVER,hash);
					GameGroupControl.eventManger.addEvent(e1);
					GameGroupControl.eventManger.nextAction();
					  RegistActivity.this.finish(); 
				}else if(password.length()<2)
				{
					   
					   Toast.makeText(RegistActivity.this, MainActivity.resources.getString(R.string.regist_toast1),Toast.LENGTH_LONG).show();
					   
                     
				}else
				{
                    	 
                    	 Toast.makeText(RegistActivity.this, MainActivity.resources.getString(R.string.regist_toast2),Toast.LENGTH_LONG).show();
                }
				
				  
			}
		  }
		  );
		  
		button_cancel.setOnClickListener(new OnClickListener()
		  {
			  
			  public void onClick(View arg0){
				  
				  RegistActivity.this.finish(); 
			 }
		  }
		  );
		  

	}
	public void Activitychange(Class calss, Intent intent) {
		if(intent==null)
		{
			intent =new Intent();
		}
		intent.setClass(RegistActivity.this, calss);
		
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
