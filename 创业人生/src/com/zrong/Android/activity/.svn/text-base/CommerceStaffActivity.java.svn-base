package com.zrong.Android.activity;









import java.util.ArrayList;
import java.util.HashMap;

import com.zrong.Android.Util.Music;
import com.zrong.Android.element.Chat;
import com.zrong.Android.element.MemberEmployee;
import com.zrong.Android.game.Connection;
import com.zrong.Android.game.ConstructData;
import com.zrong.Android.game.GameData;
import com.zrong.Android.game.GameDefinition;
import com.zrong.Android.online.network.GameProtocol;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;


import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.DigitsKeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class CommerceStaffActivity extends GameActivity{
	public static CommerceStaffActivity mContext = null;
	private LayoutInflater factory = null;
	private ListView lv;
	 ArrayList<HashMap<String, Object>> list;
	private SimpleAdapter listAdapter;
	/**捐款类型*/
	byte optype = 1;
	
	
	 
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		mContext = this;
		setContentView(R.layout.commercestaff);
		factory = LayoutInflater.from(this);
		Button returnback = (Button)findViewById(R.id.commercestaff_returnback);
		Button cancel = (Button)findViewById(R.id.commercestaff_cancel);
		Button info = (Button)findViewById(R.id.commercestaff_button_info);
		Button uper = (Button)findViewById(R.id.commercestaff_button_uper);
		Button diary = (Button)findViewById(R.id.commercestaff_button_diary);
		
		returnback.setOnClickListener(new Button.OnClickListener(){

			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CommerceStaffActivity.this.finish();
			}});
		cancel.setOnClickListener(new Button.OnClickListener(){
			
			public void onClick(View v) { 
				
				CommerceStaffActivity.this.finish();

			}});
		
		info.setOnClickListener(new Button.OnClickListener(){
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mContext.Activitychange(CommerceInfoActivity.class, null);
				CommerceStaffActivity.this.finish();
			}});
		uper.setOnClickListener(new Button.OnClickListener(){
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mContext.Activitychange(CommerceUperActivity.class, null);
				CommerceStaffActivity.this.finish();
			}});
		diary.setOnClickListener(new Button.OnClickListener(){
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mContext.Activitychange(CommerceDiaryActivity.class, null);
				CommerceStaffActivity.this.finish();
			}});
		
		
	     lv = (ListView)this.findViewById(R.id.commercestafflistview);
		
		 list = new ArrayList<HashMap<String,Object>>();
		 HashMap<String, Object> map;
		 for (int i = 0; i < GameData.member.memberNum; i++) 
		 {
			 map = new HashMap<String, Object>();
			 
			 map.put("name", GameData.member.employee[i].name);
			 map.put("companyrate", GameData.member.employee[i].companyLevel);
			 map.put("sex", GameData.member.employee[i].gender == 0 ? MainActivity.resources.getString(R.string.sex_men) : MainActivity.resources.getString(R.string.sex_lady) );
			 map.put("position", GameData.member.employee[i].duty == 99 ? "    "+MainActivity.resources.getString(R.string.chairman):GameData.member.employee[i].duty == 98 ? MainActivity.resources.getString(R.string.vice_chairman) : "    "+MainActivity.resources.getString(R.string.member));
			 list.add(map);
		 }
		
		   listAdapter = new SimpleAdapter(this,list,   
	                R.layout.commercestaff_item, new String[] {"name","companyrate","sex","position"},   
	                new int[] {R.id.name,R.id.companyrate,R.id.sex,R.id.position});   
	  
	        lv.setAdapter(listAdapter);
	        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){

				 
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					
					final int index=arg2;
					String commercestaff_menu = MainActivity.resources.getString(R.string.commercestaff_menu);
					String[] menu = commercestaff_menu.split(",");
					final  AlertDialog dlg1 = new AlertDialog.Builder(CommerceStaffActivity.this).create();
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

						 SimpleAdapter listAdapter = new SimpleAdapter(CommerceStaffActivity.this,list,   
					                R.layout.mapmenu3_item, new String[] {"name"},   
					                new int[] {R.id.mapmenu3_list_text});   
				        lv.setAdapter(listAdapter);
				        lv.setOnItemClickListener(new OnItemClickListener() {

							 
							public void onItemClick(AdapterView<?> parent,
									View view, int which, long id) {
								// TODO Auto-generated method stub
								 Intent intent = new Intent();
								 Bundle bundle = new Bundle();
								 switch(which){
								 case 0:								 
									 bundle.putString("receiver",  GameData.member.employee[index].name);
									 
									 intent.putExtras(bundle);
									 
									 intent.setClass(CommerceStaffActivity.this, WriteMailActivity.class);
									 
									 CommerceStaffActivity.this.startActivityForResult(intent,GameDefinition.REQWRITE_MAIL);
									 dlg1.dismiss();
									break;
								case 1:

									
									bundle.putInt("type", 1);

									bundle.putString("destname", GameData.member.employee[index].name);
									bundle.putLong("destId", GameData.member.employee[index].memId);

									intent.putExtras(bundle);
									 intent.setClass(CommerceStaffActivity.this, ChatActivity.class);
									CommerceStaffActivity.this.startActivityForResult(intent,GameDefinition.REQWRITE_MESSAGE);
//									CommerceStaffActivity.this.Activitychange(
//											ChatActivity.class, intent);
									dlg1.dismiss();
									break;
								case 2:
									Connection.sendMessage(GameProtocol.CONNECTION_Relation_Add_Req,ConstructData.get_Relationship_operation(GameData.member.employee[index].memId, SocialActivity.FRIEND_TYPE_FRIEND));
									dlg1.dismiss();
									break;
									
								case 3:
									Builder builde = new AlertDialog.Builder(
											CommerceStaffActivity.this);
									
									builde.setMessage(MainActivity.resources.getString(R.string.appointed)+GameData.member.employee[index].name+MainActivity.resources.getString(R.string.as_vice_chairman))
								       .setPositiveButton(MainActivity.resources.getString(R.string.dialog_ok), new DialogInterface.OnClickListener(){				    	   
										 
										public void onClick(DialogInterface dialog, int which) {
											// TODO Auto-generated method stub
											Connection.sendMessage(GameProtocol.
													CONNECTION_SEND_COfC_Change_Duty_Req,
													ConstructData.getCOfC_Change_Duty_Req(GameData.member.employee[index].memId,(byte)98));//98任命副会长
											
											dialog.dismiss();
											dlg1.dismiss();
										}})
										
								       .setNegativeButton(MainActivity.resources.getString(R.string.dialog_cancel), new DialogInterface.OnClickListener(){

										 
										public void onClick(DialogInterface dialog, int which) {
											// TODO Auto-generated method stub
											dialog.dismiss();
											dlg1.dismiss();
										}} );
								builde.create().show();
								dlg1.dismiss();
									break;
									
								case 4:
									Builder build = new AlertDialog.Builder(
											CommerceStaffActivity.this);
									
									build.setMessage(MainActivity.resources.getString(R.string.fire_member))
								       .setPositiveButton(MainActivity.resources.getString(R.string.dialog_ok), new DialogInterface.OnClickListener(){				    	   
										 
										public void onClick(DialogInterface dialog, int which) {
											// TODO Auto-generated method stub
											if(GameData.player.id == GameData.member.employee[index].memId)
											{
												display_fire();
												
												return;
											}
											else
											{
												Connection.sendMessage(GameProtocol.
														CONNECTION_SEND_COfC_Change_Duty_Req,
														ConstructData.getCOfC_Change_Duty_Req(GameData.member.employee[index].memId,(byte)-1));//-1退出商会
											}
											dialog.dismiss();
											dlg1.dismiss();
										}})
										
								       .setNegativeButton(MainActivity.resources.getString(R.string.dialog_cancel), new DialogInterface.OnClickListener(){

										 
										public void onClick(DialogInterface dialog, int which) {
											// TODO Auto-generated method stub
											dialog.dismiss();
											dlg1.dismiss();
										}} );
								build.create().show();
								dlg1.dismiss();
									break;
								 }
								 
							}
						});
					
				/*	Builder builder = new AlertDialog.Builder(CommerceStaffActivity.this);
					builder.setItems(menu,new OnClickListener() {
						
						 Intent intent = new Intent();
						 
						 Bundle bundle = new Bundle();
						
					 
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							switch(which){
							case 0:

								
								 
								 bundle.putString("receiver",  GameData.member.employee[index].name);
								 
								 intent.putExtras(bundle);
								 
								 intent.setClass(CommerceStaffActivity.this, WriteMailActivity.class);
								 
								 CommerceStaffActivity.this.startActivityForResult(intent,GameDefinition.REQWRITE_MAIL);
							
								break;
							case 1:

								
								bundle.putInt("type", 1);

								bundle.putString("destname", GameData.member.employee[index].name);
								bundle.putLong("destId", GameData.member.employee[index].memId);

								intent.putExtras(bundle);
								 intent.setClass(CommerceStaffActivity.this, ChatActivity.class);
								CommerceStaffActivity.this.startActivityForResult(intent,GameDefinition.REQWRITE_MESSAGE);
//								CommerceStaffActivity.this.Activitychange(
//										ChatActivity.class, intent);
								
								break;
							case 2:
								Connection.sendMessage(GameProtocol.CONNECTION_Relation_Add_Req,ConstructData.get_Relationship_operation(GameData.member.employee[index].memId, SocialActivity.FRIEND_TYPE_FRIEND));

								break;
								
							case 3:
								Builder builde = new AlertDialog.Builder(
										CommerceStaffActivity.this);
								
								builde.setMessage(MainActivity.resources.getString(R.string.appointed)+GameData.member.employee[index].name+MainActivity.resources.getString(R.string.as_vice_chairman))
							       .setPositiveButton(MainActivity.resources.getString(R.string.dialog_ok), new DialogInterface.OnClickListener(){				    	   
									 
									public void onClick(DialogInterface dialog, int which) {
										// TODO Auto-generated method stub
										Connection.sendMessage(GameProtocol.
												CONNECTION_SEND_COfC_Change_Duty_Req,
												ConstructData.getCOfC_Change_Duty_Req(GameData.member.employee[index].memId,(byte)98));//98任命副会长
										
										dialog.dismiss();
									}})
									
							       .setNegativeButton(MainActivity.resources.getString(R.string.dialog_cancel), new DialogInterface.OnClickListener(){

									 
									public void onClick(DialogInterface dialog, int which) {
										// TODO Auto-generated method stub
										dialog.dismiss();
									}} );
							builde.create().show();
						 
								break;
								
							case 4:
								Builder build = new AlertDialog.Builder(
										CommerceStaffActivity.this);
								
								build.setMessage(MainActivity.resources.getString(R.string.fire_member))
							       .setPositiveButton(MainActivity.resources.getString(R.string.dialog_ok), new DialogInterface.OnClickListener(){				    	   
									 
									public void onClick(DialogInterface dialog, int which) {
										// TODO Auto-generated method stub
										if(GameData.player.id == GameData.member.employee[index].memId)
										{
											display_fire();
											
											return;
										}
										else
										{
											Connection.sendMessage(GameProtocol.
													CONNECTION_SEND_COfC_Change_Duty_Req,
													ConstructData.getCOfC_Change_Duty_Req(GameData.member.employee[index].memId,(byte)-1));//-1退出商会
										}
										dialog.dismiss();
									}})
									
							       .setNegativeButton(MainActivity.resources.getString(R.string.dialog_cancel), new DialogInterface.OnClickListener(){

									 
									public void onClick(DialogInterface dialog, int which) {
										// TODO Auto-generated method stub
										dialog.dismiss();
									}} );
							build.create().show();
								
								break;
								
								
								
							}
							
						}
					}).show();*/
					
					
				}});
	        }

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == GameDefinition.REQWRITE_MESSAGE) {

			if (data == null) 
			{
				return;
			}
			Bundle bundle = data.getExtras();

			int type = bundle.getInt("type");// 0世界1私聊

			String content = bundle.getString("content");

			int channle = bundle.getInt("channle", 0);

			long destId = bundle.getLong("destId");

			String name = bundle.getString("destname");

			Chat chat = new Chat();
			chat.content = content;
			chat.channel = channle;
			chat.name = GameData.player.name;
			chat.id = GameData.player.id;

			int c = 0;

			if (chat.channel == 0) {
				c = 1;
			} else if (chat.channel == 1) {
				c = 2;
			}
			// 添加到长住内存中
			GameData.addChat(0, chat);// 向全部里添加聊天信息
			GameData.addChat(c, chat);// 向频道里添加
			// 添加到显示界面中

			Connection.sendMessage(GameProtocol.CONNECTION_SEND_CHAT_REQ,
					ConstructData.getChatData((byte) chat.channel,
							chat.content, destId));
		} else if (requestCode == GameDefinition.REQWRITE_MAIL)// 写邮件
		{
			if (data == null) {
				return;
			}

			Bundle bundle = data.getExtras();
			long receiverId = bundle.getLong("receiverId", 0);
			String receiver = bundle.getString("receiver");
			String title = bundle.getString("title");
			String content = bundle.getString("content");
			long money = bundle.getLong("money");
			if (receiver.trim().length() == 0) {
				// Game.instance.initClewBox("系统消息",
				// "您不填写收件人的姓名，信件就不知道会寄到哪儿去了……", true);
				Toast.makeText(CommerceStaffActivity.this,
						MainActivity.resources.getString(R.string.chatbox_receiver), Toast.LENGTH_SHORT)
						.show();
			} else if (title.trim().length() == 0) {
				// Game.instance.initClewBox("系统消息", "给您的邮件起一个言简意赅的主题吧。", true);
				Toast.makeText(CommerceStaffActivity.this, MainActivity.resources.getString(R.string.chatbox_title),
						Toast.LENGTH_SHORT).show();
			} else if (content.trim().length() == 0) {
				// Game.instance.initClewBox("系统消息", "请您尽情挥洒文采，写一封生动的邮件吧~",
				// true);
				Toast.makeText(CommerceStaffActivity.this, MainActivity.resources.getString(R.string.chatbox_content),
						Toast.LENGTH_SHORT).show();
			} else {
				if (receiverId != 0) {
					Connection.sendMessage(
							GameProtocol.CONNECTION_SEND_MAIL_REQ,
							ConstructData.getMail(receiverId, "", title,
									content, money, (byte) 0));
				} else {
					Connection.sendMessage(
							GameProtocol.CONNECTION_SEND_MAIL_REQ,
							ConstructData.getMail(0, receiver, title, content,
									money, (byte) 0));
				}
			}
		}

	}
	public void updatelist()
	{
		GameData.member.memberNum=(short) GameData.member.employee.length;
		quickMemSort(0,GameData.member.employee.length-1);
	}
	public  void quickMemSort(int left,int right)
	{
		for(int i = 0;i< GameData.member.employee.length;i++)
		{
			for(int j=i+1;j<GameData.member.employee.length;j++)
			{
				if(GameData.member.employee[i].duty < GameData.member.employee[j].duty)
				{
					MemberEmployee mm = GameData.member.employee[i];
					GameData.member.employee[i] = GameData.member.employee[j];
					GameData.member.employee[j] = mm ;
				}
			}
		}
    }
	public void update(){
		for(int i= 0; i<list.size();i++){
			HashMap<String,Object> map2 = list.get(i);
			 map2.put("name", GameData.member.employee[i].name);
			 map2.put("companyrate", GameData.member.employee[i].companyLevel);
			 map2.put("sex", GameData.member.employee[i].gender == 0 ? MainActivity.resources.getString(R.string.sex_men) : MainActivity.resources.getString(R.string.sex_lady) );
			 map2.put("position", GameData.member.employee[i].duty == 99 ? MainActivity.resources.getString(R.string.chairman):GameData.member.employee[i].duty == 98 ? MainActivity.resources.getString(R.string.vice_chairman) : MainActivity.resources.getString(R.string.member));
			list.set(i, map2);
		}	
		
		listAdapter.notifyDataSetChanged();
	}
	
	public void display(){
		Toast.makeText(this,MainActivity.resources.getString(R.string.commerce_donate), Toast.LENGTH_SHORT).show();
	}
	public void display_1(){
		Toast.makeText(this,MainActivity.resources.getString(R.string.set_donate_number), Toast.LENGTH_SHORT).show();
	}
	public void display_fire(){
		Toast.makeText(this,MainActivity.resources.getString(R.string.fire_member_self), Toast.LENGTH_SHORT).show();
	}
	

	 
	public void Activitychange(Class calss, Intent intent) {
		// TODO Auto-generated method stub
		if(intent==null)
		{
			intent = new Intent();
		}
		intent.setClass(CommerceStaffActivity.this, calss);
		this.startActivity(intent);
		
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
