package com.zrong.Android.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zrong.Android.game.GameData;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.ViewFlipper;
/**推广员界面*/
public class PromotersActivity extends GameActivity
{
	public static PromotersActivity mContext;
	public int currentIndex;
	
	/**
	 * 初始化按钮
	 * */
	public Button button[];
	public Button out;
	public Button send;
	public buttonLinster linster;
	/**
	 * 切换页面
	 * */
	public ViewFlipper flipper;
	/**
	 * 推荐短信
	 * */
	public String modeContent;
	public TextView textViewId,textViewInfo;
	
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.promoters);
		linster = new buttonLinster();
		flipper = (ViewFlipper)findViewById(R.id.flipper);
		
		initButton();
		
		modeContent = MainActivity.resources.getString(R.string.promotercontent)+GameData.player.recommendCode ;
		
	}
	/**
	 * 初始化按键
	 * */
	public void initButton()
	{
		button = new Button[2];
		button[0] = (Button)findViewById(R.id.button0);
		button[1] = (Button)findViewById(R.id.button1);
//		button[2] = (Button)findViewById(R.id.button2);
		for(int i=0;i<button.length;i++)
		{
			button[i].setOnClickListener(linster);
		}
		
		out = (Button)findViewById(R.id.out);
		out.setOnClickListener(linster);
		
		send = (Button)findViewById(R.id.button0_1);
		send.setOnClickListener(linster);
		
		setButtonSelection(0);
	}
	/**
	 * 选中按钮
	 * */
	public void setButtonSelection(int index)
	{
		for(int i=0;i<button.length;i++)
		{
			if(i == index)
			{
				button[i].setBackgroundResource(R.drawable.selection_selected);
				currentIndex = i;
				initFlipper(i);
				flipper.setDisplayedChild(i);
			}else
			{
				button[i].setBackgroundResource(R.drawable.selection);
			}
		}
	}
	/**
	 * 
	 * */
	public void initFlipper(int index)
	{
		if(index ==0 )
		{
		 textViewId=(TextView)findViewById(R.id.text0);
		 String strID = MainActivity.resources.getString(R.string.promoters_myid)+GameData.player.recommendCode;
		 textViewId.setText(strID);
		 
		 String str = MainActivity.resources.getString(R.string.promoterstr) +
			GameData.player.recommendCode +
			MainActivity.resources.getString(R.string.promoterstr1);
		 textViewInfo=(TextView)findViewById(R.id.text2);
		 textViewInfo.setText(str);
		}else if(index == 1)
		{
			List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
			if(GameData.friendList != null)
			{
				 for (int i = 0; i < GameData.friendList.length; i++) 
				 {
					 HashMap<String, String> map = new HashMap<String, String>(); 
					 map.put("name", GameData.friendList[i].userName);
					 list.add(map);
				 }
				 ListView listView = (ListView)findViewById(R.id.listview);
				 SimpleAdapter adapter = new SimpleAdapter(PromotersActivity.this, list,  R.layout.promoter_detail_item, new String[] {"name"},   
			                new int[] {R.id.promoter_name});
				 listView.setAdapter(adapter);
			}
		}
	}
	
	public void finish()
	{
		// TODO Auto-generated method stub
		super.finish();
		mContext = null;
	}
	public class buttonLinster implements OnClickListener
	{

		public void onClick(View v)
		{
			int id = v.getId();
			switch(id)
			{
			case R.id.button0:
				setButtonSelection(0);
					break;
			case R.id.button1:
				setButtonSelection(1);
				break;
//			case R.id.button2:
//				setButtonSelection(2);
//				break;
			case R.id.button0_1:
				Intent intent = new Intent(Intent.ACTION_SENDTO,Uri.parse("smsto:"));
				intent.putExtra("sms_body", modeContent);
				startActivity(intent);
				break;
			case R.id.out:
				finish();
				break;
			}
		}
		
	}

	public GameActivity getGameContext()
	{
		// TODO Auto-generated method stub
		return mContext;
	}
	
	public void Activitychange(Class calss, Intent intent) { 
		if(intent == null)
		{
			intent = new Intent();
		}
		
		intent.setClass(PromotersActivity.this, calss);

		this.startActivity(intent);
	}

}
