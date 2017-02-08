package com.zrong.Android.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ViewFlipper;

public class MyShopAndBuildingActivity extends GameActivity
{
	public static MyShopAndBuildingActivity mContext;
	
	private ViewFlipper flipper;
	private Button button_top[],btton_exit[];
	private buttonListener listener;
	
	private int currentIndex;
	
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		mContext = this;
		listener = new buttonListener();
		setContentView(R.layout.myshopandbuilding);
		initButton();
		setView(0);
	}
	
	public void initButton()
	{
		flipper = (ViewFlipper)findViewById(R.id.flipper);
		
		button_top = new Button[2];
		button_top[0] = (Button)findViewById(R.id.my_shop);
		button_top[1] = (Button)findViewById(R.id.my_building);
		btton_exit = new Button[2];
		btton_exit[0] = (Button)findViewById(R.id.exitgame1);
		btton_exit[1] = (Button)findViewById(R.id.exitgame2);
		
		for(int i=0;i<button_top.length;i++)
		{
			button_top[i].setOnClickListener(listener);
			btton_exit[i].setOnClickListener(listener);
		}
	}
	
	public void initView()
	{
		switch(currentIndex)
		{
		case 0:
			initMyShopView();
			break;
		case 1:
			initMyBuildingView();
			break;
		}
	}
	
	public void initMyShopView()
	{
		
	}
	
	public void initMyBuildingView()
	{
		
	}
	
	public void setView(int index)
	{
		currentIndex = index;
		if (index == 0)
		{
			button_top[0].setBackgroundResource(R.drawable.shop_pressed);
			button_top[1].setBackgroundResource(R.drawable.building);
		} else if (index == 1)
		{
			button_top[0].setBackgroundResource(R.drawable.shop);
			button_top[1].setBackgroundResource(R.drawable.building_pressed);
		}
		flipper.setDisplayedChild(index);
		initView();
	}
	



	public void finish()
	{
		super.finish();
		mContext = null;
	}



	public GameActivity getGameContext()
	{
		return mContext;
	}
	
	public class buttonListener implements OnClickListener
	{

		public void onClick(View v)
		{
			int id = v.getId();
			switch(id)
			{
			case R.id.my_shop:
				setView(0);
				break;
			case R.id.my_building:
				setView(1);
				break;
			case R.id.exitgame1:
				finish();
				break;
			case R.id.exitgame2:
				finish();
				break;
				
			}
		}
		
	}

}
