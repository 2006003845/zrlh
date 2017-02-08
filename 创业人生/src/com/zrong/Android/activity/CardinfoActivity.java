package com.zrong.Android.activity;

import com.zrong.Android.Util.BitmapUtil;

import com.zrong.Android.Util.ImageDownloadTask;
import com.zrong.Android.game.GameData;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class CardinfoActivity extends GameActivity  {
	public static CardinfoActivity mContext = null;
	private int iconId;
	private TextView text_desc,text_collective,text_name;
 
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		mContext = this;
		Bundle bundle = new Bundle();
		bundle = getIntent().getExtras();
		iconId =bundle.getInt("index");
		Log.i("juj", "iconId="+iconId);
		this.setContentView(R.layout.cardinfo);
		ImageView imageview = (ImageView) this
		.findViewById(R.id.cardinfo_Imageview);
		ImageView imv = null;
		imv = (ImageView)imageview;
		imv.setImageBitmap(BitmapUtil.getBitmap(GameData.card[iconId].cardIcon,0,(float)1));
		//new ImageDownloadTask().execute(imageview, GameData.card[iconId].cardIcon, 811);
		Button returnback = (Button)this.findViewById(R.id.cardinfo_button_returnback);
		Button cancel = (Button)this.findViewById(R.id.cardinfo_button_cancel);
		text_name = (TextView)this.findViewById(R.id.cardinfo_name_text);
		text_collective = (TextView)this.findViewById(R.id.cardinfo_collective_text);
		text_desc = (TextView)this.findViewById(R.id.cardinfo_desc_text);
		text_name.setText(GameData.card[iconId].cardName);
		text_collective.setText(String.valueOf(GameData.card[iconId].cardCollective));
		text_desc.setText(GameData.card[iconId].cardDesc);
		
		returnback.setOnClickListener(new Button.OnClickListener(){
 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CardinfoActivity.mContext.finish();
			}});
		cancel.setOnClickListener(new Button.OnClickListener(){
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CardinfoActivity.mContext.finish();
			}});
	} 
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
