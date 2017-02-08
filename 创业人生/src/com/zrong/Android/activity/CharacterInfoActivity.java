package com.zrong.Android.activity;

import java.util.ArrayList;
import java.util.HashMap;
import android.widget.ImageView;





import com.zrong.Android.Util.ImageAdapter;
import com.zrong.Android.Util.Music;
import com.zrong.Android.Util.MyImageAdapter;
import com.zrong.Android.View.SpriteView;

import com.zrong.Android.game.GameData;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class CharacterInfoActivity extends GameActivity{
	public static CharacterInfoActivity mContext=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.characterinfo);
		
		
	
	
		SpriteView spriteview=(SpriteView)this.findViewById(R.id.characterinfo_smallback_1);
		spriteview.setSeries(GameData.player.headResIdx);
	
		TextView tv1=(TextView)this.findViewById(R.id.characterinfo_name);
		tv1.setText(MainActivity.resources.getString(R.string.characterinfo_name)+GameData.player.name);
		TextView tv2=(TextView)this.findViewById(R.id.characterinfo_sex);
		tv2.setText(MainActivity.resources.getString(R.string.characterinfo_sex)+GameData.player.sex);
		TextView tv3=(TextView)this.findViewById(R.id.characterinfo_companyName);
		tv3.setText(MainActivity.resources.getString(R.string.characterinfo_company)+GameData.player.companyName);
		TextView tv4=(TextView)this.findViewById(R.id.characterinfo_level);
		tv4.setText(MainActivity.resources.getString(R.string.characterinfo_level)+GameData.player.level);
		TextView tv5=(TextView)this.findViewById(R.id.characterinfo_assets);
		tv5.setText(MainActivity.resources.getString(R.string.characterinfo_assets)+GameData.player.assets);
		TextView tv6=(TextView)this.findViewById(R.id.characterinfo_strategy);
		tv6.setText(MainActivity.resources.getString(R.string.characterinfo_strategy));
		Button bn1=(Button)this.findViewById(R.id.characterinfo_strategy_1);
		bn1.setText(String.valueOf(GameData.player.strategy) );
	    TextView tv8=(TextView)this.findViewById(R.id.characterinfo_execution);
		tv8.setText(MainActivity.resources.getString(R.string.characterinfo_execution));
		Button bn2=(Button)this.findViewById(R.id.characterinfo_execution_1);
		bn2.setText(String.valueOf(GameData.player.execution));
		TextView tv10=(TextView)this.findViewById(R.id.characterinfo_leadership);
		tv10.setText(MainActivity.resources.getString(R.string.characterinfo_leadership));
		Button bn3=(Button)this.findViewById(R.id.characterinfo_leadership_1);
		bn3.setText(String.valueOf(GameData.player.leadership));
		TextView tv12=(TextView)this.findViewById(R.id.characterinfo_strength);
		tv12.setText(MainActivity.resources.getString(R.string.characterinfo_strength));
		Button bn4=(Button)this.findViewById(R.id.characterinfo_strength_1);
		bn4.setText(String.valueOf(GameData.player.strength));
		

	        
	        Button button_info_returnback=(Button)findViewById(R.id.characterinfo_button_returnback);
	        Button button_info_cancel=(Button)findViewById(R.id.characterinfo_button_cancel);

	        button_info_returnback.setOnClickListener(new Button.OnClickListener()
	        {
	        	public void onClick(View v) 
	            { 
	        		//MainActivity.mContext.Activitychange(MapmainMenuActivity.class,null);
	        		CharacterInfoActivity.this.finish();
	              } 
	            });
	        button_info_cancel.setOnClickListener(new Button.OnClickListener()
	        {
	        	public void onClick(View v) 
	            { 
	        		//MainActivity.mContext.Activitychange(MapmainMenuActivity.class,null);
	        		CharacterInfoActivity.this.finish();
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
