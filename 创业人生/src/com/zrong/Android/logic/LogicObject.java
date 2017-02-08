package com.zrong.Android.logic;

import android.content.Context;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import com.zrong.Android.game.GameGroupControl;
import com.zrong.Android.game.GameObject;

public abstract class LogicObject extends GameObject{
	
	public static final String TAG = "LogicObject";
	
	public Context context;
	
	public LogicObject(Context context,GameGroupControl control) {
		this.context = context;
		registerControl(control);
	}
	
	//public abstract void creatLogicObject();
	
	public  static  void creatLogicObject(Context context,GameGroupControl control)
	{
		//�ȴ�����ȥʵ��;
	}
	
	public void registerContext(Context context)
	{
		this.context = context;
	}
	
	public Context getContext()
	{
		return context;
	}
	
	private View view;
	
	public void registerView(View view)
	{
		this.view = view;
	}
	
	public View getView()
	{
		return view;
	}
	
	
	public GameGroupControl control;
	
	public void registerControl (GameGroupControl control)
	{
		this.control = control;
	}
	
	public GameGroupControl getContol()
	{
		return control;
	}
	/**
	 * �߼�������
	 */
	public abstract void update();
	 
	
	/**
	 * ��ʼ��LogicObject
	 */ 
	public abstract void init();
	
	
	/**
	 * ��ʼ��View
	 */
	public abstract void initView();
	
	/**
	 * ͬ����������
	 */ 
	public abstract void synchviewdata();
	
	/**
	 * ��ʾ��ǰ�Ѿ�ע���view
	 */
	public void refreshView()
	{
		if(view == null)
		{
			initView();
		}
		
		if(view == null)return;
		
		int index =control.indexOfChild(view);
		
		if(index < 0)
		{
			control.addView(view);
		}
		else
		{
			control.bringChildToFront(view);
		}
	}

	

}
