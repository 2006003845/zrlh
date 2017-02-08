package com.zrong.Android.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Vector;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.zrong.Android.Util.Preferences;
import com.zrong.Android.Util.SystemAPI;
import com.zrong.Android.Util.xmlPhaser.XElem;
import com.zrong.Android.View.Event;

import com.zrong.Android.activity.MainActivity;
import com.zrong.Android.activity.R;
import com.zrong.Android.activity.RegistActivity;
import com.zrong.Android.game.Connection;
import com.zrong.Android.game.EventManager;
import com.zrong.Android.game.GameDef;
import com.zrong.Android.game.GameDefinition;
import com.zrong.Android.game.GameGroupControl; 

public class SelectSever extends LogicObject{
	
	/** 
	 * 保存的分区分线的数据
	 */
	public XElem elem;
	/**
	 * 保存上次登录时的对应的index 即 XElem line[] = elem.elems(); line[index]就可以取到上次登录的那条线
	 */
	public int lastLoginIndex = 0;
	public SelectSever(Context context, GameGroupControl control)
	{
		super(context, control);
	}

	 
	public void init() 
	{
		 
		initView();
		synchviewdata();
	}

	 
	public void initView() {
		// TODO Auto-generated method stub
	    View v = View.inflate(context, R.layout.selectsever, null);//获得View
		
		registerView(v);
		
		v.setId(GameDefinition.SelectSever);
		
		Button button_return = (Button)v.findViewById(R.id.selectsever_button_returnback);
		Button button_cancel = (Button)v.findViewById(R.id.selectsever_button_cancel);
		Button title = (Button)v.findViewById(R.id.selectsever_button_title);
		//Typeface face = Typeface.createFromAsset (context.getAssets() , "fonts/font001.ttf");
//		title.setTypeface(GameDefinition.face);
		
//		 TextPaint tp = title.getPaint();
//		 tp.setFakeBoldText(true);
//		 tp.setStyle(Paint.Style.STROKE );
		 //tp.setFlags(Paint.f)
		button_return.setOnClickListener(new OnClickListener()
		{
			public void onClick(View arg0) 
			{
				control.removeView(getView());
			}
		}
		
		);
		 
		button_cancel.setOnClickListener(new OnClickListener()
		{
			public void onClick(View arg0) 
			{
				control.removeView(getView());
			}
		}
		
		);
		
		RelativeLayout select = (RelativeLayout)v.findViewById(R.id.selectsever_selected);
		
		select.setOnClickListener(new OnClickListener()
		{

			public void onClick(View arg0) 
			{
				final AlertDialog dlg = new AlertDialog.Builder(MainActivity.mContext).create();
				dlg.show();					
				dlg.getWindow().setContentView(R.layout.system_dlg);
				Button login =(Button)dlg.findViewById(R.id.system_helping);
				Button regist =(Button)dlg.findViewById(R.id.system_setting);
				login.setText(MainActivity.resources.getString(R.string.selectsever_login));
				regist.setText(MainActivity.resources.getString(R.string.selectsever_register));
				login.setOnClickListener(new OnClickListener() {
					
					 
					public void onClick(View v) {
						// TODO Auto-generated method stub
//						  Event e1 = new Event(EventManager.ACTION_GO_LOGINVIEW,null); 
//						  
//						  control.eventManger.addEvent(e1);
//						  
//						  control.eventManger.nextAction();
						dlg.dismiss();
					}
				});
				 
				regist.setOnClickListener(new OnClickListener() {
					
					 
					public void onClick(View v) {
						// TODO Auto-generated method stub
						MainActivity.mContext.Activitychange(RegistActivity.class,null);
						dlg.dismiss();
					}
				});
				
				/* AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.mContext);
			     builder.setMessage(MainActivity.resources.getString(R.string.selectsever_setmessage))//设置对话框内容
	    	            .setTitle(MainActivity.resources.getString(R.string.selectsever_settitle))//设置对话框标题
	    	            .setPositiveButton(MainActivity.resources.getString(R.string.selectsever_login), new DialogInterface.OnClickListener() {//设置对话框[肯定]按钮
	    		public void onClick(DialogInterface dialog, int which) {
	    		
					  
					  Event e1 = new Event(EventManager.ACTION_GO_LOGINVIEW,null); 
					  
					  control.eventManger.addEvent(e1);
					  
					  control.eventManger.nextAction();
	    			
	    			 
	    			dialog.dismiss();//关闭对话框
	    			 
	    		}
	    	})
	    	.setNegativeButton(MainActivity.resources.getString(R.string.selectsever_register), new DialogInterface.OnClickListener() {//设置对话框[否定]按钮
	    		public void onClick(DialogInterface dialog, int which) {
////	    		if(GameDef.channelId.equals(GameDef.dangle))
////	    		{
//	    			Toast.makeText(MainActivity.mContext, "功能未开放", Toast.LENGTH_LONG).show();
//	    			
//	    		}
//	    		else
//	    		{
	    			MainActivity.mContext.Activitychange(RegistActivity.class,null);
//	    		}
	    		
	    			dialog.dismiss();//关闭对话框
	    		}
	    	});
			builder.create().show();*/
			}
			
		}
		);
		 ListView lv = (ListView)v.findViewById(R.id.selectsever_listview);
		 
		 
		 
		 lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
				 {
			 	  
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int index, long arg3) 
					{
						 XElem line[] = elem.elems();
						 
						 String idstr =line[index].elem("id").text().string();
						 
						 control.preferences.set(Preferences.SEVERLINE_ID, Integer.parseInt(idstr));
						 
						 final AlertDialog dlg1 = new AlertDialog.Builder(MainActivity.mContext).create();
							dlg1.show();					
							dlg1.getWindow().setContentView(R.layout.system_dlg);
							Button login =(Button)dlg1.findViewById(R.id.system_helping);
							Button regist =(Button)dlg1.findViewById(R.id.system_setting);
							login.setText(MainActivity.resources.getString(R.string.selectsever_login));
							regist.setText(MainActivity.resources.getString(R.string.selectsever_register));
							login.setOnClickListener(new OnClickListener() {
								
								 
								public void onClick(View v) {
									// TODO Auto-generated method stub
//									  Event e1 = new Event(EventManager.ACTION_GO_LOGINVIEW,null); 
//									  
//									  control.eventManger.addEvent(e1);
//									  
//									  control.eventManger.nextAction();
									  dlg1.dismiss();
								}
							});
							regist.setOnClickListener(new OnClickListener() {
								
								 
								public void onClick(View v) {
									// TODO Auto-generated method stub
									MainActivity.mContext.Activitychange(RegistActivity.class,null);
									dlg1.dismiss();
								}
							});
							
						/* AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.mContext);
					     builder.setMessage(MainActivity.resources.getString(R.string.selectsever_setmessage))//设置对话框内容
			    	            .setTitle(MainActivity.resources.getString(R.string.selectsever_settitle))//设置对话框标题
			    	            .setPositiveButton(MainActivity.resources.getString(R.string.selectsever_login), new DialogInterface.OnClickListener() {//设置对话框[肯定]按钮
			    		public void onClick(DialogInterface dialog, int which) {
			    		
							  
							  Event e1 = new Event(EventManager.ACTION_GO_LOGINVIEW,null); 
							  
							  control.eventManger.addEvent(e1);
							  
							  control.eventManger.nextAction();
			    			
			    			 
			    			dialog.dismiss();//关闭对话框
			    			 
			    		}
			    	})
			    	
						.setNegativeButton(MainActivity.resources.getString(R.string.selectsever_register), new DialogInterface.OnClickListener() {//设置对话框[否定]按钮
			    		public void onClick(DialogInterface dialog, int which) 
			    		{
//			    			if(GameDef.channelId.equals(GameDef.dangle))
//				    		{
//				    			Toast.makeText(MainActivity.mContext, "功能未开放", Toast.LENGTH_LONG).show();
//				    		}
//				    		else
//				    		{
				    			MainActivity.mContext.Activitychange(RegistActivity.class,null);
//				    		}
			    			dialog.dismiss();//关闭对话框
			    		}
			    	});
					builder.create().show();*/
						
						if(elem != null)
						{
							try{
								XElem lines =elem.elem(index);
								GameDef.gateway = lines.elem("gateway").text().string();
								GameDef.agentCodeHi = lines.elem("agentCodeHi").text().string();
								GameDef.agentCodeLo = lines.elem("agentCodeLo").text().string();
								GameDef.resServer = lines.elem("resServer").text().string();
								GameDef.platform = lines.elem("platform").text().string();
								 
								
							}catch(ArrayIndexOutOfBoundsException e)
							{
								Log.d(TAG, "数组越界");
							}
						}
					}
			 
				 }
		 );
		
		 
	}

	 
	public void update() 
	{
		
		
	}

	 
	public void loadProperties(Vector v) {
		
		if(v != null && v.size()>0)
		{
			elem=(XElem)v.elementAt(0);
			synchviewdata();
		}
	}
	
	public void setElem(XElem  elem)
	{
		this.elem = elem;
	}

	 
	protected void reCycle() {
		// TODO Auto-generated method stub
		
	}

	 
	protected void refurbish() {
		
		
	}

	 
	public void synchviewdata() 
	{
		 
		    ListView lv = (ListView)this.getView().findViewById(R.id.selectsever_listview);
		    
		    if(elem != null)
		    {
		    	    ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		    	    
		    	    XElem line[] = elem.elems();//所有的服务器
		    	    
				    
				    HashMap<String, String> map;
				    
				    int savedId = control.preferences.getInt(Preferences.SEVERLINE_ID);
					
					int selectIndex = -1;
					
					boolean select = false;
					
					View view =  this.getView().findViewById(R.id.selectsever_selected);
				    TextView tv = (TextView)view.findViewById(R.id.selectedsever_name);
			        for (int i = 0; i < line.length; i++) 
			        {
			            map = new HashMap<String, String>();
			             
			            map.put("user_sever", SystemAPI.getText(line[i].elem("name").text().string()));
			            
			            //String status =line[i].elem("status").text().string();
			            String idstr =line[i].elem("id").text().string();
			            if(!select&&line[i].elem("default").text().string()!= null && line[i].elem("default").text().string().equals("1"))
	            		{
			            	
						        tv.setText(MainActivity.resources.getString(R.string.selectsever_lastlogin)+SystemAPI.getText(line[i].elem("name").text().string()));
						        GameDef.gateway = line[i].elem("gateway").text().string();
								GameDef.agentCodeHi = line[i].elem("agentCodeHi").text().string();
								GameDef.agentCodeLo = line[i].elem("agentCodeLo").text().string();
								GameDef.resServer = line[i].elem("resServer").text().string();
								GameDef.platform = line[i].elem("platform").text().string();
								control.preferences.set(Preferences.SEVERLINE_ID, Integer.parseInt(idstr));
	            		}
			            if(Integer.parseInt(idstr)== savedId)
			            {
			            		select = true;
			            		tv.setText(MainActivity.resources.getString(R.string.selectsever_lastlogin)+SystemAPI.getText(line[i].elem("name").text().string()));
			            		GameDef.gateway = line[i].elem("gateway").text().string();
								GameDef.agentCodeHi = line[i].elem("agentCodeHi").text().string();
								GameDef.agentCodeLo = line[i].elem("agentCodeLo").text().string();
								GameDef.resServer = line[i].elem("resServer").text().string();
								GameDef.platform = line[i].elem("platform").text().string();
								control.preferences.set(Preferences.SEVERLINE_ID, Integer.parseInt(idstr));
			            }
			            list.add(map);
			        }
			        
			        SimpleAdapter listAdapter = new SimpleAdapter(context,list,   
			                R.layout.selectsever_item, new String[] {"user_sever"},   
			                new int[] {R.id.sever_name});   
			  
			        lv.setAdapter(listAdapter);
		    }
			
		   
	}

}
