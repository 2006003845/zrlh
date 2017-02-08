package com.zrong.Android.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zrong.Android.Util.GridAdapter;
import com.zrong.Android.Util.GridImageAdapter;
import com.zrong.Android.game.GameData;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;

public class CardListActivity extends GameActivity {
	public static CardListActivity mContext =null;
	private List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	private GridView gv;
	 
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		mContext = this;
		this.setContentView(R.layout.cardlist);
		gv = (GridView)findViewById(R.id.cardlist_gridview);
		Button returnback = (Button)this.findViewById(R.id.cardlist_button_returnback);
		Button cancel = (Button)this.findViewById(R.id.cardlist_button_cancel);
		returnback.setOnClickListener(new Button.OnClickListener() {
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CardListActivity.mContext.finish();
			}
		});
		cancel.setOnClickListener(new Button.OnClickListener() {
		
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CardListActivity.mContext.finish();
			}
		});
    	if (GameData.card != null) {
			Log.i("Log", GameData.card.length + "");
			for (int i = 0; i < GameData.card.length; i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("iconId", GameData.card[i].cardSmallicon);
				list.add(map);
			}
			GridAdapter adapter = new GridAdapter(this, list,
					R.layout.grid_icon);

			gv.setAdapter(adapter);	
	}else{
		return;
	}
		gv.setOnItemClickListener(new OnItemClickListener() {

			 
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();				 
				 Bundle bundle = new Bundle();				 
				 bundle.putInt("index", position);	
				 Log.i("juj", "position="+position);
				 intent.putExtras(bundle);
				 
				 intent.setClass(CardListActivity.this, CardinfoActivity.class);
				 
				// change = true;
				 
				 CardListActivity.this.startActivity(intent);
				//mContext.Activitychange(CardinfoActivity.class, intent);
			}
		});
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
