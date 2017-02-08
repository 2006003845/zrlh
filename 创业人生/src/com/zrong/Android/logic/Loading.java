package com.zrong.Android.logic;

import java.util.Vector;

import android.content.Context;
import android.os.Message;
import android.util.Log;
import android.view.View;

import com.zrong.Android.View.SpriteView; 
import com.zrong.Android.activity.MainActivity;
import com.zrong.Android.activity.R;
import com.zrong.Android.game.GameData;
import com.zrong.Android.game.GameDefinition;
import com.zrong.Android.game.GameGroupControl; 

public class Loading extends LogicObject{
	
	 /**发送的包id */
    private int packetId;
	
	public Loading(Context context, GameGroupControl control) {
		super(context, control);
	}

	public void init() {
		initView();
	}
	
	public void setPacketId(int packetId)
	{
		this.packetId = packetId;
	}
	
	public void getPacketId(int packetId)
	{
		this.packetId = packetId;
	}
	
	public void destroy(int pid)
	{
		if(pid == packetId)
		{
			destroy();
		}
	}
	
	public void destroy()
	{
		 Log.v("yz", "loading destroy"+System.currentTimeMillis());
		 Message message = Message.obtain();
		 message.what = GameDefinition.Message_removeView;
		 Vector v = new Vector(5,5);
		 v.addElement(this.getView());
		 message.obj= v;
		 GameData.mhandler.sendMessage(message);
	}
	
	
	
	public void initView()
	{
		View v = this.getView(); 
		Log.v("yz", "initView"+System.currentTimeMillis());
		if(v == null)
		{
			v = View.inflate(context, R.layout.loading, null);//获得View
		    registerView(v);//注册view
		    v.setId(GameDefinition.LoadingView);//给view设置id
		}
	}

	@Override
	public void synchviewdata() {
		
	}

	 
	
	public void update() {
	 
	}

	@Override
	public void loadProperties(Vector v)
	{
		if(v != null && v.size() > 0)
		{
			int pid = Integer.parseInt(String.valueOf(v.elementAt(0)));
			setPacketId(pid);
			 Log.v("yz", "setPacketId ="+pid+",time="+System.currentTimeMillis());
		}
	}

	@Override
	protected void reCycle() {
		
	}

	@Override
	protected void refurbish(){
		
	}

}
