package com.zrong.Android.activity;

import com.zrong.Android.Util.Music;
import com.zrong.Android.game.GameDefinition;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class WriteMailActivity extends GameActivity{

	 
	private EditText receiver;
	private EditText title;
	private EditText content;
    private boolean isfocus = false;
	private String destName = null; 
	 
	public static WriteMailActivity mContext = null;
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		mContext= this;
		this.setContentView(R.layout.writemail);
		 		
		 Bundle bundle = this.getIntent().getExtras();
		
		destName = bundle.getString("receiver");
		
		isfocus =bundle.getBoolean("state");
		
		Button ok = (Button)this.findViewById(R.id.writemail_button_ok);
		
		Button cancel = (Button)this.findViewById(R.id.writemail_button_cancel);
		
		receiver = (EditText)this.findViewById(R.id.writemail_edit_receiver);
		
		receiver.setHint(MainActivity.resources.getString(R.string.writemail_receiver_hint));
		
		if(destName !=null || !destName.equals(""))
		{
			receiver.setText(destName);
		}
		if(!isfocus)
		{
			receiver.setFocusable(true);
		}else
		{
			receiver.setFocusable(false);
			}
		
		 
		
		title = (EditText)this.findViewById(R.id.writemail_edit_title);
		title.setHint(MainActivity.resources.getString(R.string.writemail_title_hint));
		
		content =  (EditText)this.findViewById(R.id.writemail_edit_content);
		content.setHint(MainActivity.resources.getString(R.string.writemail_content_hint));
		
		ok.setOnClickListener(new OnClickListener()
		{

			public void onClick(View arg0) {
				 
				Intent i = new Intent();
				Bundle bundle = new Bundle();
				
			 
				bundle.putString("receiver", receiver.getText().toString());
				bundle.putString("title", title.getText().toString());
				bundle.putString("content", content.getText().toString());
				
				i.putExtras(bundle);
				
				WriteMailActivity.this.setResult(GameDefinition.RESULT_OK, i);   

				WriteMailActivity.this.finish();
			}
		}
		);
		
		cancel.setOnClickListener(new OnClickListener()
		{
			public void onClick(View arg0) 
			{
				WriteMailActivity.this.finish();
			}
		}
		);
	}

	
	@Override
	public GameActivity getGameContext() 
	{
		return this;
	}
	
	@Override
	public void finish() 
	{
		mContext = null;
		super.finish();
	}
}
