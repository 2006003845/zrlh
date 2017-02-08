package com.zrong.Android.activity;

 

import java.util.ArrayList;
import java.util.HashMap;

import com.zrong.Android.Util.BitmapUtil;
import com.zrong.Android.Util.ImageDownloadTask;
import com.zrong.Android.Util.Music;
import com.zrong.Android.element.Mail;
import com.zrong.Android.game.Connection;
import com.zrong.Android.game.ConstructData;
import com.zrong.Android.game.GameData;
import com.zrong.Android.game.GameDefinition;
import com.zrong.Android.online.network.GameProtocol;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class MailDetailActivity extends GameActivity{

	
	 
	
	private Mail mail = null;
	
	public static MailDetailActivity mContext = null;
	
	private byte type;
	
	private int index;
	
	private TextView sender;
	private TextView title;
	private TextView date;
	private TextView content;
	
	private ImageView good1;
	private ImageView good2;
	private ImageView good3;
	private ImageView good4;
	 
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mContext = this;
		
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	
		Bundle bundle = this.getIntent().getExtras();
		
		  type = bundle.getByte("type");
		
		  index = bundle.getInt("index");
		
		if(type == MailboxActivity.IN_MAIL_BOX)
		{
			mail = (Mail)GameData.inMailbox.elementAt(index);
		}
		else if(type == MailboxActivity.OUT_MAIL_BOX)
		{
			mail = (Mail)GameData.outMailbox.elementAt(index);
		}
		else if(type == MailboxActivity.EVENT_LIST_BOX)
		{
			mail = (Mail)GameData.eventListbox.elementAt(index);
		}
		
		this.setContentView(R.layout.mail_detail);
		
		Button returnback = (Button)this.findViewById(R.id.mail_detail_button_returnback);
		Button cancel = (Button)this.findViewById(R.id.mail_detail_button_cancel);
		returnback.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				MailDetailActivity.this.finish();
			}
		}
		);
		
		cancel.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				MailDetailActivity.this.finish();
			}
		}
		);
		
		  sender = (TextView)this.findViewById(R.id.mail_detail_sender_or_receiver);
		sender.setText(MainActivity.resources.getString(R.string.maildetail_sender)+mail.sender);
		
		  title = (TextView)this.findViewById(R.id.mail_detail_title);
		title.setText(MainActivity.resources.getString(R.string.maildetail_title)+mail.title);
		
		  date = (TextView)this.findViewById(R.id.mail_detail_date);
		date.setText(MainActivity.resources.getString(R.string.maildetail_date)+mail.time);
		
		  content = (TextView)this.findViewById(R.id.mail_detail_content);
		content.setText(mail.content);
		
		  good1 = (ImageView)this.findViewById(R.id.mail_detail_good1);
		  good2 = (ImageView)this.findViewById(R.id.mail_detail_good2);
		  good3 = (ImageView)this.findViewById(R.id.mail_detail_good3);
		  good4 = (ImageView)this.findViewById(R.id.mail_detail_good4);
		
		if(mail.isExist == 0)//δȡ
		{
			 for(int i = 0 ; i <mail.good.length; i++)
			 {
				 if(i == 0)
				 {
					 new ImageDownloadTask().execute(good1,mail.good[i].icon,810);
				 }
				 else if(i == 1)
				 {
					 new ImageDownloadTask().execute(good2,mail.good[i].icon,810);
				 }
				 else if(i == 2)
				 {
					 new ImageDownloadTask().execute(good3,mail.good[i].icon,810);
				 }
				 else if(i == 3)
				 { 
					 new ImageDownloadTask().execute(good4,mail.good[i].icon,810);
				 }
			 }
		}
		
		
		 Button menu = (Button)this.findViewById(R.id.mail_detail_menu);
		 
		 menu.setOnClickListener(new OnClickListener()
		 {
			public void onClick(View v)
			{    
			    String menu [] = null;
			    if(type == MailboxActivity.IN_MAIL_BOX)
				{
			    	   menu = new String[]{MainActivity.resources.getString(R.string.mailbox_answer),MainActivity.resources.getString(R.string.maildetail_receivemail),MainActivity.resources.getString(R.string.mailbox_delete)};
			    	   final  AlertDialog dlg1 = new AlertDialog.Builder(MailDetailActivity.this).create();
						dlg1.show();
						dlg1.getWindow().setContentView(R.layout.mapmenu3_list);
						ListView lv = (ListView)dlg1.findViewById(R.id.mapmenu3_list);				
						ArrayList<HashMap<String, String>>list= new  ArrayList<HashMap<String,String>>();
						HashMap<String, String> map;
						for (int i = 0; i < menu.length; i++) {
							//	shop[i] = GameData.corporation.shop[i].name;
								 map = new HashMap<String, String>();						 
								 map.put("name", menu[i]);
								 list.add(map);
							}

							 SimpleAdapter listAdapter = new SimpleAdapter(MailDetailActivity.this,list,   
						                R.layout.mapmenu3_item, new String[] {"name"},   
						                new int[] {R.id.mapmenu3_list_text});   
					        lv.setAdapter(listAdapter);
					        lv.setOnItemClickListener(new OnItemClickListener() {

								 
								public void onItemClick(AdapterView<?> parent,
										View view, int index, long id) {
									// TODO Auto-generated method stub
									if(index==0){
										Intent intent = new Intent();
										 
										 Bundle bundle = new Bundle();
										 
										 bundle.putString("receiver", mail.sender);
										 
										 intent.putExtras(bundle);
										 
										 intent.setClass(MailboxActivity.mContext, WriteMailActivity.class);
										 
										 MailboxActivity.mContext.startActivityForResult(intent,GameDefinition.REQWRITE_MAIL);
										 
										 MailDetailActivity.this.finish(); 
									}else if(index == 1)
									{
										Connection.sendMessage(GameProtocol.CONNECTION_MAIL_OPTION_REQ,ConstructData.getMailOptionData(mail.id,type, MailboxActivity.MAIL_OPTION_GET_ACCESSORY));//zhangxiaoqing
									}	else if(index == 2)
									{
										Connection.sendMessage(GameProtocol.CONNECTION_MAIL_OPTION_REQ,ConstructData.getMailOptionData(mail.id,type, MailboxActivity.MAIL_OPTION_DELETE_MAIL));//zhangxiaoqing add
										 MailDetailActivity.this.finish();
										 
									}
								}
							});
				}
			    else
			    {
			    	   menu = new String[]{MainActivity.resources.getString(R.string.mailbox_delete)};
			    	   final  AlertDialog dlg1 = new AlertDialog.Builder(MailDetailActivity.this).create();
						dlg1.show();
						dlg1.getWindow().setContentView(R.layout.mapmenu3_list);
						ListView lv = (ListView)dlg1.findViewById(R.id.mapmenu3_list);				
						ArrayList<HashMap<String, String>>list= new  ArrayList<HashMap<String,String>>();
						HashMap<String, String> map;
						for (int i = 0; i < menu.length; i++) {
							//	shop[i] = GameData.corporation.shop[i].name;
								 map = new HashMap<String, String>();						 
								 map.put("name", menu[i]);
								 list.add(map);
							}

							 SimpleAdapter listAdapter = new SimpleAdapter(MailDetailActivity.this,list,   
						                R.layout.mapmenu3_item, new String[] {"name"},   
						                new int[] {R.id.mapmenu3_list_text});   
					        lv.setAdapter(listAdapter);
					        lv.setOnItemClickListener(new OnItemClickListener() {

								 
								public void onItemClick(AdapterView<?> parent,
										View view, int index, long id) {
									// TODO Auto-generated method stub
									Connection.sendMessage(GameProtocol.CONNECTION_MAIL_OPTION_REQ,ConstructData.getMailOptionData(mail.id,type, MailboxActivity.MAIL_OPTION_DELETE_MAIL));//zhangxiaoqing add
									 MailDetailActivity.this.finish();
								}
							});
			    }
				
				 
				/* Builder builder = new AlertDialog.Builder(MailDetailActivity.this);
				
				 
				 builder.setItems(menu, new DialogInterface.OnClickListener()
					{

						public void onClick(DialogInterface Dialog, int index)
						{
							if(index == 0)
							{    
								 if(type == MailboxActivity.IN_MAIL_BOX)
								 {
									 Intent intent = new Intent();
									 
									 Bundle bundle = new Bundle();
									 
									 bundle.putString("receiver", mail.sender);
									 
									 intent.putExtras(bundle);
									 
									 intent.setClass(MailboxActivity.mContext, WriteMailActivity.class);
									 
									 MailboxActivity.mContext.startActivityForResult(intent,GameDefinition.REQWRITE_MAIL);
									 
									 MailDetailActivity.this.finish(); 
								 }
								 else
								 {
									 Connection.sendMessage(GameProtocol.CONNECTION_MAIL_OPTION_REQ,ConstructData.getMailOptionData(mail.id,type, MailboxActivity.MAIL_OPTION_DELETE_MAIL));//zhangxiaoqing add
									 MailDetailActivity.this.finish();
								 }
							}
							else if(index == 1)
							{
								Connection.sendMessage(GameProtocol.CONNECTION_MAIL_OPTION_REQ,ConstructData.getMailOptionData(mail.id,type, MailboxActivity.MAIL_OPTION_GET_ACCESSORY));//zhangxiaoqing
							}
							else if(index == 2)
							{
								Connection.sendMessage(GameProtocol.CONNECTION_MAIL_OPTION_REQ,ConstructData.getMailOptionData(mail.id,type, MailboxActivity.MAIL_OPTION_DELETE_MAIL));//zhangxiaoqing add
								 MailDetailActivity.this.finish();
								 
							}
						}
					}).create().show();*/
			}
			 
		 }
		 
		 );
		
		
	}
	
	public void updata(int type)
	{
		if(this.type == type)
		{
			if(type == MailboxActivity.IN_MAIL_BOX)
			{
				mail = (Mail)GameData.inMailbox.elementAt(index);
			}
			else if(type == MailboxActivity.OUT_MAIL_BOX)
			{
				mail = (Mail)GameData.outMailbox.elementAt(index);
			}
			else if(type == MailboxActivity.EVENT_LIST_BOX)
			{
				mail = (Mail)GameData.eventListbox.elementAt(index);
			}
			sender = (TextView)this.findViewById(R.id.mail_detail_sender_or_receiver);
			sender.setText(MainActivity.resources.getString(R.string.maildetail_sender)+mail.sender);
			
			  title = (TextView)this.findViewById(R.id.mail_detail_title);
			title.setText(MainActivity.resources.getString(R.string.maildetail_title)+mail.title);
			
			  date = (TextView)this.findViewById(R.id.mail_detail_date);
			date.setText(MainActivity.resources.getString(R.string.maildetail_date)+mail.time);
			
			  content = (TextView)this.findViewById(R.id.mail_detail_content);
			content.setText(mail.content);
			
			
			  if( mail.isExist ==0)//δȡ
			  {
				  for(int i = 0 ; i <mail.good.length; i++)
					 {
						 if(i == 0)
						 {
							 Bitmap bitmap = BitmapUtil.getBitmap(631,0,(float)1);
							 good1.setImageBitmap(bitmap);
						 }
						 else if(i == 1)
						 {
							 Bitmap bitmap = BitmapUtil.getBitmap(631,0,(float)1);
							 good2.setImageBitmap(bitmap);
						 }
						 else if(i == 2)
						 {
							 Bitmap bitmap = BitmapUtil.getBitmap(631,0,(float)1);
							 good3.setImageBitmap(bitmap);
						 }
						 else if(i == 3)
						 { 
							 Bitmap bitmap = BitmapUtil.getBitmap(631,0,(float)1);
						     good4.setImageBitmap(bitmap);
						 }
					 }
			  }
			  else
			  {
				  good1.setVisibility(View.GONE);
				  good2.setVisibility(View.GONE);
				  good3.setVisibility(View.GONE);
				  good4.setVisibility(View.GONE);
			  }
			  
			
			
			
		}
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
