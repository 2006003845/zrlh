package com.zrong.Android.logic;

import java.util.Vector;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.zrong.Android.activity.R;
import com.zrong.Android.element.MapBuilding;
import com.zrong.Android.element.PublicBuilding;
import com.zrong.Android.element.Shop;
import com.zrong.Android.game.GameDefinition;
import com.zrong.Android.game.GameGroupControl; 

public class ShopInfo extends LogicObject
{
	private Shop shop;
	private MapBuilding mapBuilding;
	private PublicBuilding publicBuilding;
	
	public ShopInfo(Context context, GameGroupControl control) {
		super(context, control);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		initView();
	}

	@Override
	public void initView(){
		// TODO Auto-generated method stub
		View v = View.inflate(context, R.layout.shopinfo, null);
		
		registerView(v);
		
		v.setId(GameDefinition.ShopInfoView);
		
		Button cancel = (Button)v.findViewById(R.id.shopinfo_button_cancel);
		
		Button returnback = (Button)v.findViewById(R.id.shopinfo_button_returnback);
		
		cancel.setOnClickListener(new OnClickListener()
		{
			public void onClick(View arg0) {
				
				control.logic.remove("ShopInfo"); 
			}
		}
		);
		
		returnback.setOnClickListener(new OnClickListener()
		{

			public void onClick(View arg0) {
				control.logic.remove("ShopInfo"); 
			}
		}
		);
		
		
		
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
	public void loadProperties(Vector v) 
	{
		if(v != null && v.size() >0)
		{
			//shop =(Shop)v.elementAt(0);
		}
		
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
