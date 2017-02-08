package com.zrong.Android.activity;

 

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.zrong.Android.Listener.ActivityTransform;
import com.zrong.Android.Listener.TabWidget;
import com.zrong.Android.Util.Music;
import com.zrong.Android.element.Mail;
import com.zrong.Android.game.Connection;
import com.zrong.Android.game.ConstructData;
import com.zrong.Android.game.GameData;
import com.zrong.Android.game.GameDefinition;
import com.zrong.Android.logic.Map;
import com.zrong.Android.online.network.GameProtocol;

public class MailboxActivity extends GameActivity implements TabWidget,OnTouchListener{

	public static final byte IN_MAIL_BOX = 0;
	public static final byte OUT_MAIL_BOX = 1;
	public static final byte EVENT_LIST_BOX = 2;
	public static final byte Customer_ServerSe = 3;
	public static final byte Customer_ServerIn = 4;
	
	public static final byte MAIL_OPTION_READ_MAIL = 0;
	public static final byte MAIL_OPTION_GET_ACCESSORY = 1;
	public static final byte MAIL_OPTION_DELETE_MAIL = 2;
	public static final byte MAIL_OPTION_DELETE_READ = 3;//删除已读邮件
	 
	/**
	 * 是否显示mailbox
	 */ 
	public static boolean isShow = false;
	public static MailboxActivity mContext = null;
	
	private Button writeMail,inMail,outMail,eventMail;
	
	private ListView inMailView,outMailView,eventMailView;
	
	private int select;
	 
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		mContext = this;
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		this.setContentView(R.layout.mailbox);
		
		Button returnback = (Button)this.findViewById(R.id.mailbox_button_returnback);
		Button cancel = (Button)this.findViewById(R.id.mailbox_button_cancel);
		
		returnback.setOnClickListener(new OnClickListener()
		{
			public void onClick(View arg0) 
			{
			/*	if(MapmainMenuActivity.isMaillFromMainMenu){
					Intent intent = new Intent (mContext,MapmainMenuActivity.class);
					
					intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
					mContext.startActivity(intent);
				}
				MapmainMenuActivity.isMaillFromMainMenu= false;*/				
				MailboxActivity.this.finish();
			}
		}
		);
		
		cancel.setOnClickListener(new OnClickListener()
		{
			public void onClick(View arg0) 
			{
			/*	if(MapmainMenuActivity.isMaillFromMainMenu){
					Intent intent = new Intent (mContext,MapmainMenuActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
					mContext.startActivity(intent);
				}
				MapmainMenuActivity.isMaillFromMainMenu= false;*/
				MailboxActivity.this.finish();
			}
		}
		);
		
		writeMail = (Button)this.findViewById(R.id.mailbox_button_write);
		inMail = (Button)this.findViewById(R.id.mailbox_button_inmail);
		outMail = (Button)this.findViewById(R.id.mailbox_button_outmail);
		eventMail = (Button)this.findViewById(R.id.mailbox_button_event);
		inMail.setOnTouchListener(this);
		outMail.setOnTouchListener(this);
		eventMail.setOnTouchListener(this);
		
		writeMail.setOnClickListener(new OnClickListener()
		{

			public void onClick(View arg0) 
			{
				Intent intent = new Intent();
				
				Bundle bundle = new Bundle();
				
				bundle.putString("receiver", "");
				
				intent.putExtras(bundle);
				
				MailboxActivity.this.Activitychange(WriteMailActivity.class,intent) ;
				
			}
			
		}
		);
		inMail.setOnClickListener(new OnClickListener()
		{
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				setSelectIndex(1);
			}
			
		}
		);
		outMail.setOnClickListener(new OnClickListener()
		{
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				setSelectIndex(2);
			}
			
		}
		);
		eventMail.setOnClickListener(new OnClickListener()
		{
			
			public void onClick(View arg0) {
				 
				setSelectIndex(3);
			}
			
		}
		);
		
		inMailView=(ListView)this.findViewById(R.id.mailbox_listview_inmail);
		
		outMailView=(ListView)this.findViewById(R.id.mailbox_listview_outmail);
		
		eventMailView=(ListView)this.findViewById(R.id.mailbox_listview_event);
		
		inMailView.setOnItemClickListener(new OnItemClickListener()
		{

			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) 
			{
				 final int p = position;
				 String [] item = new String[]{MainActivity.resources.getString(R.string.mailbox_check),MainActivity.resources.getString(R.string.mailbox_answer),MainActivity.resources.getString(R.string.mailbox_delete),MainActivity.resources.getString(R.string.mailbox_deleteread)};
				 final  AlertDialog dlg1 = new AlertDialog.Builder(MailboxActivity.this).create();
					dlg1.show();
					dlg1.getWindow().setContentView(R.layout.mapmenu3_list);
					ListView lv = (ListView)dlg1.findViewById(R.id.mapmenu3_list);				
					ArrayList<HashMap<String, String>>list= new  ArrayList<HashMap<String,String>>();
					HashMap<String, String> map;
					for (int i = 0; i < item.length; i++) {
						//	shop[i] = GameData.corporation.shop[i].name;
							 map = new HashMap<String, String>();						 
							 map.put("name", item[i]);
							 list.add(map);
						}

						 SimpleAdapter listAdapter = new SimpleAdapter(MailboxActivity.this,list,   
					                R.layout.mapmenu3_item, new String[] {"name"},   
					                new int[] {R.id.mapmenu3_list_text});   
				        lv.setAdapter(listAdapter);
				        lv.setOnItemClickListener(new OnItemClickListener() {

							 
							public void onItemClick(AdapterView<?> parent,
									View view, int index, long id) {
								// TODO Auto-generated method stub
								Mail mail = (Mail)GameData.inMailbox.elementAt(p);
								if(index == 0)//查看
								{
									Intent intent = new Intent();
									
									Bundle bundle = new Bundle();
									
									bundle.putByte("type", IN_MAIL_BOX);
									
									bundle.putInt("index", p);
									
									intent.putExtras(bundle);
									
									MailboxActivity.this.Activitychange(MailDetailActivity.class, intent);
									Connection.sendMessage(GameProtocol.CONNECTION_MAIL_OPTION_REQ,ConstructData.getMailOptionData(mail.id,IN_MAIL_BOX, MAIL_OPTION_READ_MAIL));
									dlg1.dismiss();
								}
								else if(index == 1)//回复
								{
									Intent intent = new Intent();
									 
									 Bundle bundle = new Bundle();
									 
									 bundle.putString("receiver", mail.sender);
									 
									 intent.putExtras(bundle);
									 
									 intent.setClass(MailboxActivity.this, WriteMailActivity.class);
									 
									 MailboxActivity.this.startActivityForResult(intent,GameDefinition.REQWRITE_MAIL);
									 dlg1.dismiss();
								}
								else if(index == 2)//删除
								{
									Connection.sendMessage(GameProtocol.CONNECTION_MAIL_OPTION_REQ,ConstructData.getMailOptionData(mail.id,IN_MAIL_BOX, MAIL_OPTION_DELETE_MAIL));//zhangxiaoqing add
									dlg1.dismiss();
								}
								else if(index == 3)//删除已读
								{
									Connection.sendMessage(GameProtocol.CONNECTION_MAIL_OPTION_REQ,ConstructData.getMailOptionData(0,IN_MAIL_BOX, MAIL_OPTION_DELETE_READ));
									dlg1.dismiss();
								}
							
							}
						});
				 
				/* Builder builder = new AlertDialog.Builder(MailboxActivity.this);
				 
				 final int p = position;
				 builder.setItems(item, new DialogInterface.OnClickListener()
					{

						public void onClick(DialogInterface Dialog, int index) {
							Mail mail = (Mail)GameData.inMailbox.elementAt(p);
							if(index == 0)//查看
							{
								Intent intent = new Intent();
								
								Bundle bundle = new Bundle();
								
								bundle.putByte("type", IN_MAIL_BOX);
								
								bundle.putInt("index", p);
								
								intent.putExtras(bundle);
								
								MailboxActivity.this.Activitychange(MailDetailActivity.class, intent);
								Connection.sendMessage(GameProtocol.CONNECTION_MAIL_OPTION_REQ,ConstructData.getMailOptionData(mail.id,IN_MAIL_BOX, MAIL_OPTION_READ_MAIL));
							}
							else if(index == 1)//回复
							{
								Intent intent = new Intent();
								 
								 Bundle bundle = new Bundle();
								 
								 bundle.putString("receiver", mail.sender);
								 
								 intent.putExtras(bundle);
								 
								 intent.setClass(MailboxActivity.this, WriteMailActivity.class);
								 
								 MailboxActivity.this.startActivityForResult(intent,GameDefinition.REQWRITE_MAIL);
							}
							else if(index == 2)//删除
							{
								Connection.sendMessage(GameProtocol.CONNECTION_MAIL_OPTION_REQ,ConstructData.getMailOptionData(mail.id,IN_MAIL_BOX, MAIL_OPTION_DELETE_MAIL));//zhangxiaoqing add
							}
							else if(index == 3)//删除已读
							{
								Connection.sendMessage(GameProtocol.CONNECTION_MAIL_OPTION_REQ,ConstructData.getMailOptionData(0,IN_MAIL_BOX, MAIL_OPTION_DELETE_READ));
							}
						}
					 
					}).create().show();*/
				
			}
			
		}
		
		);
		
		outMailView.setOnItemClickListener(new OnItemClickListener()
		{
			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) 
			{
//				Mail mail = (Mail)GameData.inMailbox.elementAt(p);
				Intent intent = new Intent();
				
				Bundle bundle = new Bundle();
				
				bundle.putByte("type", OUT_MAIL_BOX);
				
				bundle.putInt("index", position);
				
				intent.putExtras(bundle);
				
				MailboxActivity.this.Activitychange(MailDetailActivity.class, intent);
				 
			}
		}
		);
		
		 
		eventMailView.setOnItemClickListener(new OnItemClickListener()
		{
			
			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) 
			{
				 final int p = position;
				String [] item = new String[]{MainActivity.resources.getString(R.string.mailbox_check),MainActivity.resources.getString(R.string.mailbox_deleteread)};
				final  AlertDialog dlg1 = new AlertDialog.Builder(MailboxActivity.this).create();
				dlg1.show();
				dlg1.getWindow().setContentView(R.layout.mapmenu3_list);
				ListView lv = (ListView)dlg1.findViewById(R.id.mapmenu3_list);				
				ArrayList<HashMap<String, String>>list= new  ArrayList<HashMap<String,String>>();
				HashMap<String, String> map;
				for (int i = 0; i < item.length; i++) {
					//	shop[i] = GameData.corporation.shop[i].name;
						 map = new HashMap<String, String>();						 
						 map.put("name", item[i]);
						 list.add(map);
					}

					 SimpleAdapter listAdapter = new SimpleAdapter(MailboxActivity.this,list,   
				                R.layout.mapmenu3_item, new String[] {"name"},   
				                new int[] {R.id.mapmenu3_list_text});   
			        lv.setAdapter(listAdapter);
			        lv.setOnItemClickListener(new OnItemClickListener() {

						 
						public void onItemClick(AdapterView<?> parent,
								View view, int index, long id) {
							// TODO Auto-generated method stub
							Mail mail = (Mail)GameData.eventListbox.elementAt(p);
							if(index == 0)
							{
								Intent intent = new Intent();
								
								Bundle bundle = new Bundle();
								
								bundle.putByte("type", EVENT_LIST_BOX);
								
								bundle.putInt("index", p);
								
								intent.putExtras(bundle);
								
								MailboxActivity.this.Activitychange(MailDetailActivity.class, intent);
//								Log.v("mail", "mail.id = "+mail.id+",mail.type="+mail.type+",opType="+MAIL_OPTION_READ_MAIL+",mail.content"+mail.content);
								Connection.sendMessage(GameProtocol.CONNECTION_MAIL_OPTION_REQ,ConstructData.getMailOptionData(mail.id,EVENT_LIST_BOX, MAIL_OPTION_READ_MAIL));
							}
							else if(index == 1)
							{
								Connection.sendMessage(GameProtocol.CONNECTION_MAIL_OPTION_REQ,ConstructData.getMailOptionData(0,EVENT_LIST_BOX, MAIL_OPTION_DELETE_READ));
							}
						}
					});
				/* final int p = position;
				Builder builder = new AlertDialog.Builder(MailboxActivity.this);
				
				builder.setItems(item, new DialogInterface.OnClickListener()
				{
					Mail mail = (Mail)GameData.eventListbox.elementAt(p);
					public void onClick(DialogInterface Dialog, int index) 
					{
						
						if(index == 0)
						{
							Intent intent = new Intent();
							
							Bundle bundle = new Bundle();
							
							bundle.putByte("type", EVENT_LIST_BOX);
							
							bundle.putInt("index", p);
							
							intent.putExtras(bundle);
							
							MailboxActivity.this.Activitychange(MailDetailActivity.class, intent);
//							Log.v("mail", "mail.id = "+mail.id+",mail.type="+mail.type+",opType="+MAIL_OPTION_READ_MAIL+",mail.content"+mail.content);
							Connection.sendMessage(GameProtocol.CONNECTION_MAIL_OPTION_REQ,ConstructData.getMailOptionData(mail.id,EVENT_LIST_BOX, MAIL_OPTION_READ_MAIL));
						}
						else if(index == 1)
						{
							Connection.sendMessage(GameProtocol.CONNECTION_MAIL_OPTION_REQ,ConstructData.getMailOptionData(0,EVENT_LIST_BOX, MAIL_OPTION_DELETE_READ));
						}
					}
					
				}).create().show();*/
				
			}
			
		}
		
		);
		
		 
		setSelectIndex(1);
		inMail.setBackgroundResource(R.drawable.button_inmail_pressed);
	}
	

	public void Activitychange(Class calss, Intent intent) 
	{
		if(intent == null)
		{
			intent = new Intent();
		}
		
		 intent.setClass(MailboxActivity.this, calss);
		
		 this.startActivityForResult(intent,GameDefinition.REQWRITE_MAIL);
		  
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		
		super.onActivityResult(requestCode, resultCode, data);
		
		if(data == null)
		{
			return;
		}
		Bundle bundle = data.getExtras();
		long receiverId = bundle.getLong("receiverId", 0);
		String receiver = bundle.getString("receiver");
		String title = bundle.getString("title");
		String content = bundle.getString("content");
		long money = bundle.getLong("money");
		if(receiver.trim().length() == 0)
		{
			//Game.instance.initClewBox("系统消息", "您不填写收件人的姓名，信件就不知道会寄到哪儿去了……", true);
			Toast.makeText(MailboxActivity.this,MainActivity.resources.getString(R.string.chatbox_receiver), 
		            Toast.LENGTH_SHORT).show();
		}
		else if(title.trim().length() == 0)
		{
			//Game.instance.initClewBox("系统消息", "给您的邮件起一个言简意赅的主题吧。", true);
			Toast.makeText(MailboxActivity.this,MainActivity.resources.getString(R.string.chatbox_title), 
		            Toast.LENGTH_SHORT).show();
		}
		else if(content.trim().length() == 0)
		{
			//Game.instance.initClewBox("系统消息", "请您尽情挥洒文采，写一封生动的邮件吧~", true);
			Toast.makeText(MailboxActivity.this,MainActivity.resources.getString(R.string.chatbox_content), 
		            Toast.LENGTH_SHORT).show();
		}
		else
		{
			if(receiverId != 0)
			{
				Connection.sendMessage(GameProtocol.CONNECTION_SEND_MAIL_REQ,ConstructData.getMail(receiverId,"", title, content, money,(byte) 0));
			}else{
				Connection.sendMessage(GameProtocol.CONNECTION_SEND_MAIL_REQ,ConstructData.getMail(0,receiver, title, content, money,(byte) 0));
			}
		}
//		 
		
		
	}

	public void setSelect(int index)
	{
		if(select == index)
		{
		setSelectIndex(select);
		}
	}
	
	public void setSelectIndex(int index) 
	{
		select = index;
		inMailView.setVisibility(View.GONE);
		outMailView.setVisibility(View.GONE);
		eventMailView.setVisibility(View.GONE);
		
		if(index == 1)//收件箱
		{
			inMailView.setVisibility(View.VISIBLE);
			
			ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
			HashMap<String,Object> map = null;
			Mail mail = null;
			for(int i = 0 ; i < GameData.inMailbox.size(); i++)
			{
				mail = (Mail)GameData.inMailbox.elementAt(i);
				map = new HashMap<String,Object> ();
				map.put("name", mail.sender);
				map.put("title", mail.title);
				list.add(map);
			}
			
			SimpleAdapter adapter = new SimpleAdapter(this,list,R.layout.mailbox_item,new String[]{"name","title" },new int[]{R.id.mailbox_item_name,R.id.mailbox_item_title });
			inMailView.setAdapter(adapter);
			
			
		}
		else if(index == 2)//发件箱
		{
			outMailView.setVisibility(View.VISIBLE);
			
			ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
			HashMap<String,Object> map = null;
			Mail mail = null;
			for(int i = 0 ; i < GameData.outMailbox.size(); i++)
			{
				mail = (Mail)GameData.outMailbox.elementAt(i);
				map = new HashMap<String,Object> ();
				map.put("name", mail.sender);
				map.put("title", mail.title);
				list.add(map);
			}
			
			SimpleAdapter adapter = new SimpleAdapter(this,list,R.layout.mailbox_item,new String[]{"name","title" },new int[]{R.id.mailbox_item_name,R.id.mailbox_item_title });
			outMailView.setAdapter(adapter);
		}
		else if(index == 3)//时间簿
		{
			eventMailView.setVisibility(View.VISIBLE);
			
			ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
			HashMap<String,Object> map = null;
			Mail mail = null;
			for(int i = 0 ; i < GameData.eventListbox.size(); i++)
			{
				mail = (Mail)GameData.eventListbox.elementAt(i);
				map = new HashMap<String,Object> ();
				map.put("name", mail.sender);
				map.put("title", mail.title);
				list.add(map);
			}
			
			SimpleAdapter adapter = new SimpleAdapter(this,list,R.layout.mailbox_item,new String[]{"name","title" },new int[]{R.id.mailbox_item_name,R.id.mailbox_item_title });
			eventMailView.setAdapter(adapter);
		}
	}

	public boolean onTouch(View v, MotionEvent arg1) {
		 
		inMail.setBackgroundResource(R.drawable.button_inmail);
		outMail.setBackgroundResource(R.drawable.button_outmail);
		eventMail.setBackgroundResource(R.drawable.button_event);
		
		if(v == inMail)
		{
			inMail.setBackgroundResource(R.drawable.button_inmail_pressed);
		}
		else if(v == outMail)
		{
			outMail.setBackgroundResource(R.drawable.button_outmail_pressed);
		}
		else if(v == eventMail)
		{
			eventMail.setBackgroundResource(R.drawable.button_event_pressed);
		}
		
		return false;
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
