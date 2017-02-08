package com.zrong.Android.logic;

import java.util.Vector;

import com.zrong.Android.activity.R;
import com.zrong.Android.game.GameDefinition;
import com.zrong.Android.game.GameGroupControl; 

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class TipBox extends LogicObject{

	private LinearLayout mLayout;
	
	private ScrollView mScrollView;
	
	private final Handler mHandler= new Handler();
	public TipBox(Context context, GameGroupControl control) {
		super(context, control);
		// TODO Auto-generated constructor stub
	}


	public void init() {
		
		initView();
	}

	 
	public void initView() {
		// TODO Auto-generated method stub
		View v = View.inflate(context, R.layout.tipbox, null);
		
		registerView(v);
		
		v.setId(GameDefinition.TipBoxView);
		
//		R.id.t
//		
//		mScrollView = (ScrollView)v.findViewById(R.id.tipbox_scrollview);
//		
//		mLayout = (LinearLayout)v.findViewById(R.id.tipbox_layout);
		
		//mHandler.post(aotuScroll);
	}

	@Override
	public void synchviewdata() {
		// TODO Auto-generated method stub
		
	}

	public void update(){
		int off = mLayout.getMeasuredHeight()-mScrollView.getHeight();
		if(off > 0)
		{
			mScrollView.scrollTo(0, off);
		}
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
	
	private Runnable aotuScroll = new Runnable()
	{

		public void run(){
			while(true)
			{
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				break;
			}
			
		}
		
	};

}
