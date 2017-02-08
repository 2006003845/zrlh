package com.zrong.Android.logic;

 

import java.util.Vector;

 

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.zrong.Android.Util.Music;
import com.zrong.Android.View.OfficeView;
import com.zrong.Android.activity.MainActivity;
import com.zrong.Android.activity.R;
import com.zrong.Android.game.Connection;
import com.zrong.Android.game.ConstructData;
import com.zrong.Android.game.GameData;
import com.zrong.Android.game.GameDefinition;
import com.zrong.Android.game.GameGroupControl; 
import com.zrong.Android.online.network.GameProtocol;

public class Office extends LogicObject{

	public Office(Context context, GameGroupControl control) {
		super(context, control);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		initView();
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		View v = View.inflate(getContext(), R.layout.office, null);
		
		registerView(v);
		
		v.setId(GameDefinition.OfficeView);
		
		Button menu = (Button)v.findViewById(R.id.office_menu);
		
		menu.setOnClickListener(new OnClickListener()
		{

			public void onClick(View arg0) 
			{
				GameData.isResetMap = true;
				Connection.sendMessage(GameProtocol.CONNECTION_SEND_MAPAROUND_SHOP, ConstructData.getMapAroundShop(GameData.mapIds[0],(short)-1, (short)-1, (byte)1, (byte)(GameData.ARRAY_LENTH/2)));
				Vector v = new Vector(5,5);
				v.addElement(GameProtocol.CONNECTION_SEND_MAPAROUND_SHOP);
				control.setGameStatus(GameDefinition.Game_Loading, v);
			}
		}
		);
		 
//		Intent itent  = new Intent("android.intent.action.MAIN");
//	        
//        Bundle bundle = new Bundle();
//        bundle.putInt("musicId", R.raw.m2);
//        bundle.putBoolean("loop", true);
//        itent.putExtras(bundle);
//        MainActivity.mContext.startService(itent);
		Music.getInstance(MainActivity.mContext).start(R.raw.m2, true);
	}

	@Override
	public void synchviewdata() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadProperties(Vector v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void reCycle() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void refurbish() {
		// TODO Auto-generated method stub
		
	}

}
