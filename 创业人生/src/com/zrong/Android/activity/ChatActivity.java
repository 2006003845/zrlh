package com.zrong.Android.activity;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.zrong.Android.Listener.ActivityTransform;
import com.zrong.Android.Util.Music;
import com.zrong.Android.game.GameDefinition;

public class ChatActivity extends GameActivity{

	private int type = 0;
	private String name ="";
	private long id;
	
//	private RadioButton rb;
	private EditText et;
	public static ChatActivity mContext = null;
	protected void onCreate(Bundle savedInstanceState) {
	 
		super.onCreate(savedInstanceState);
		
		Bundle bundle = this.getIntent().getExtras();
		
		mContext=this;
		
		type = bundle.getInt("type");
		
		name = bundle.getString("destname");
		
		id = bundle.getLong("destId");
		
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.chat);
		
		et = (EditText)this.findViewById(R.id.chat_edit_content);
		
		/*rb=(RadioButton)this.findViewById(R.id.chat_radiobutton_world);
		
		if(type == 0)
		{			
			rb.setChecked(true);
		}
		else 
		{
			Button title = (Button)this.findViewById(R.id.chat_title);
			
			title.setText("与"+name+"私聊中");
			
			rb.setVisibility(View.GONE);
			
		}*/
		if (type == 1)
		{
			Button title = (Button) this.findViewById(R.id.chat_title);

			title.setText("与" + name + "私聊中");
		}
		
		
		
		
		Button ok = (Button)this.findViewById(R.id.chat_button_ok);
		Button cancle = (Button)this.findViewById(R.id.chat_button_cancel);
		
		ok.setOnClickListener(new OnClickListener()
		{
			public void onClick(View arg0) 
			{
				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				
				bundle.putInt("type", type);
				
				bundle.putString("content", et.getText().toString());
				
				if(type == 0)//频道聊天
				{
					int channel = 0;
					
					bundle.putInt("channle", channel);
				}
				else if(type == 1)//私聊
				{
					bundle.putInt("channle", 1);
					
					bundle.putLong("destId", id);
					
					bundle.putString("destname", name);
				}else if(type == 2)//频道聊天
				{
					bundle.putInt("channle", 2);
				}
				
				
				
				intent.putExtras(bundle);
				
				ChatActivity.this.setResult(GameDefinition.RESULT_OK, intent);   

				ChatActivity.this.finish();
			}
		}
		);
		cancle.setOnClickListener(new OnClickListener()
		{
			
			public void onClick(View arg0) 
			{
				ChatActivity.this.finish();
			}
			
		}
		);
		
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
