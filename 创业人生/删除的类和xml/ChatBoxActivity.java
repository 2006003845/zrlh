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
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.zrong.Android.Listener.ActivityTransform;
import com.zrong.Android.Listener.TabWidget;
import com.zrong.Android.Util.Music;
import com.zrong.Android.Util.SystemAPI;
import com.zrong.Android.element.Chat;
import com.zrong.Android.game.Connection;
import com.zrong.Android.game.ConstructData;
import com.zrong.Android.game.GameData;
import com.zrong.Android.game.GameDefinition;
import com.zrong.Android.online.network.GameProtocol;

public class ChatBoxActivity extends GameActivity implements
		TabWidget, OnTouchListener {

	private int select;

	private Button all, world, privatechat, system, speech;

	private ListView allView, worldView, privatechatView, systemView;

	private boolean isInit[] = new boolean[4];

	private ArrayList<HashMap<String, Object>> alllist = new ArrayList<HashMap<String, Object>>();
	private SimpleAdapter alladapter;

	private ArrayList<HashMap<String, Object>> worldlist = new ArrayList<HashMap<String, Object>>();
	private SimpleAdapter worldadapter;

	private ArrayList<HashMap<String, Object>> privatechatlist = new ArrayList<HashMap<String, Object>>();
	private SimpleAdapter privatechatadapter;

	private ArrayList<HashMap<String, Object>> systemlist = new ArrayList<HashMap<String, Object>>();
	private SimpleAdapter systemadapter;
    private  static final String chatbox_channel = MainActivity.resources.getString(R.string.chatbox_channel);
	private static final String channel[] = chatbox_channel.split(",");		
	public static ChatBoxActivity mContext = null;

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mContext = this;
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		this.setContentView(R.layout.chatbox);

		for (int i = 0; i < isInit.length; i++) {
			isInit[i] = false;
		}

		Button returnback = (Button) this
				.findViewById(R.id.chatbox_button_returnback);
		Button cancel = (Button) this.findViewById(R.id.chatbox_button_cancel);

		returnback.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {

				ChatBoxActivity.this.finish();
			}
		});

		cancel.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				ChatBoxActivity.this.finish();
			}
		});

		speech = (Button) this.findViewById(R.id.chatbox_button_speech);

		all = (Button) this.findViewById(R.id.chatbox_button_all);
		world = (Button) this.findViewById(R.id.chatbox_button_world);
		privatechat = (Button) this
				.findViewById(R.id.chatbox_button_privatechat);
		system = (Button) this.findViewById(R.id.chatbox_button_system);
		all.setOnTouchListener(this);
		world.setOnTouchListener(this);
		privatechat.setOnTouchListener(this);
		system.setOnTouchListener(this);

		allView = (ListView) this.findViewById(R.id.chatbox_listview_all);
		worldView = (ListView) this.findViewById(R.id.chatbox_listview_world);
		privatechatView = (ListView) this
				.findViewById(R.id.chatbox_listview_privatechat);
		systemView = (ListView) this.findViewById(R.id.chatbox_listview_system);

		speech.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				bundle.putInt("type", 0);

				intent.putExtras(bundle);

				intent.setClass(ChatBoxActivity.this, ChatActivity.class);

				ChatBoxActivity.this.startActivityForResult(intent,
						GameDefinition.REQWRITE_MESSAGE);

			}

		});

		all.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {

				setSelectIndex(0);
			}
		});
		// init adapter
		initAllAdapter();
		iniWorldAdapter();
		initPrivateChatAdapter();
		world.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {

				setSelectIndex(1);
			}
		});
		privatechat.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {

				setSelectIndex(2);
			}
		});
		system.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {

				setSelectIndex(3);
			}
		});

		allView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				final Chat chat = (Chat) GameData.allChatbox
						.elementAt(position);

				if (chat.id == GameData.player.id || chat.id < 0)// 如果是自己,直接频道发言
				{
					Intent intent = new Intent();
					Bundle bundle = new Bundle();
					bundle.putInt("type", 0);

					intent.putExtras(bundle);

					intent.setClass(ChatBoxActivity.this, ChatActivity.class);

					ChatBoxActivity.this.startActivityForResult(intent,
							GameDefinition.REQWRITE_MESSAGE);
					return;
				}
                String chatbox_item = MainActivity.resources.getString(R.string.chatbox_item);
				String[] item = chatbox_item.split(",");
				final  AlertDialog dlg1 = new AlertDialog.Builder(ChatBoxActivity.this).create();
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

					 SimpleAdapter listAdapter = new SimpleAdapter(ChatBoxActivity.this,list,   
				                R.layout.mapmenu3_item, new String[] {"name"},   
				                new int[] {R.id.mapmenu3_list_text});   
			        lv.setAdapter(listAdapter);
			        lv.setOnItemClickListener(new OnItemClickListener() {

						 
						public void onItemClick(AdapterView<?> parent,
								View view, int index, long id) {
							// TODO Auto-generated method stub
							if (index == 0)// 频道发言
							{
								Intent intent = new Intent();
								Bundle bundle = new Bundle();
								bundle.putInt("type", 0);
								
								intent.putExtras(bundle);

								intent.setClass(ChatBoxActivity.this, ChatActivity.class);

								ChatBoxActivity.this.startActivityForResult(intent,
										GameDefinition.REQWRITE_MESSAGE);
								dlg1.dismiss();
							} else if (index == 1)// 发送私聊
							{

								Intent intent = new Intent();
								Bundle bundle = new Bundle();
								bundle.putInt("type", 1);

								bundle.putString("destname", (chat.name.indexOf('<')>=0)?(chat.name = chat.name.substring(chat.name.indexOf('<')+1,chat.name.indexOf('>'))):(chat.name));
								bundle.putLong("destId", chat.id);

								intent.putExtras(bundle);
								intent.setClass(ChatBoxActivity.this, ChatActivity.class);
								ChatBoxActivity.this.startActivityForResult(intent,
										GameDefinition.REQWRITE_MESSAGE);
								dlg1.dismiss();
							} else if (index == 2)// 加为好友
							{
								Connection.sendMessage(
										GameProtocol.CONNECTION_Relation_Add_Req,
										ConstructData.get_Relationship_operation(
												chat.id,
												SocialActivity.FRIEND_TYPE_FRIEND));
								dlg1.dismiss();
							} else if (index == 3)// 发送邮件
							{

								Intent intent = new Intent();

								Bundle bundle = new Bundle();

								bundle.putString("receiver", chat.name);

								intent.putExtras(bundle);

								intent.setClass(ChatBoxActivity.this,
										WriteMailActivity.class);

								ChatBoxActivity.this.startActivityForResult(intent,
										GameDefinition.REQWRITE_MAIL);
								dlg1.dismiss();
							}
						}
					});
			 /*       
				Builder builder = new AlertDialog.Builder(ChatBoxActivity.this);

				builder.setItems(item, new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int index) {
						if (index == 0)// 频道发言
						{
							Intent intent = new Intent();
							Bundle bundle = new Bundle();
							bundle.putInt("type", 0);
							
							intent.putExtras(bundle);

							intent.setClass(ChatBoxActivity.this, ChatActivity.class);

							ChatBoxActivity.this.startActivityForResult(intent,
									GameDefinition.REQWRITE_MESSAGE);
						} else if (index == 1)// 发送私聊
						{

							Intent intent = new Intent();
							Bundle bundle = new Bundle();
							bundle.putInt("type", 1);

							bundle.putString("destname", (chat.name.indexOf('<')>=0)?(chat.name = chat.name.substring(chat.name.indexOf('<')+1,chat.name.indexOf('>'))):(chat.name));
							bundle.putLong("destId", chat.id);

							intent.putExtras(bundle);
							intent.setClass(ChatBoxActivity.this, ChatActivity.class);
							ChatBoxActivity.this.startActivityForResult(intent,
									GameDefinition.REQWRITE_MESSAGE);
						} else if (index == 2)// 加为好友
						{
							Connection.sendMessage(
									GameProtocol.CONNECTION_Relation_Add_Req,
									ConstructData.get_Relationship_operation(
											chat.id,
											SocialActivity.FRIEND_TYPE_FRIEND));

						} else if (index == 3)// 发送邮件
						{

							Intent intent = new Intent();

							Bundle bundle = new Bundle();

							bundle.putString("receiver", chat.name);

							intent.putExtras(bundle);

							intent.setClass(ChatBoxActivity.this,
									WriteMailActivity.class);

							ChatBoxActivity.this.startActivityForResult(intent,
									GameDefinition.REQWRITE_MAIL);
						}

					}

				}).create().show();*/

			}

		}

		);
		// -->>zhouzhilong add
		allView.setOnScrollListener(new OnScrollListener() {

			 
			public void onScrollStateChanged(AbsListView view, int scrollState) {
			}

		 
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				if (firstVisibleItem + visibleItemCount < totalItemCount) {
					allView.setStackFromBottom(true);
					allView.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
				}
			}
		});
		worldView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				final Chat chat = (Chat) GameData.worldChatbox
						.elementAt(position);

				if (chat.id == GameData.player.id || chat.id < 0)// 如果是自己,直接频道发言
				{
					Intent intent = new Intent();
					Bundle bundle = new Bundle();
					bundle.putInt("type", 0);

					intent.putExtras(bundle);

					intent.setClass(ChatBoxActivity.this, ChatActivity.class);

					ChatBoxActivity.this.startActivityForResult(intent,
							GameDefinition.REQWRITE_MESSAGE);
					return;
				}

				String chatbox_item = MainActivity.resources.getString(R.string.chatbox_item);
				String[] item = chatbox_item.split(",");
				final  AlertDialog dlg1 = new AlertDialog.Builder(ChatBoxActivity.this).create();
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

					 SimpleAdapter listAdapter = new SimpleAdapter(ChatBoxActivity.this,list,   
				                R.layout.mapmenu3_item, new String[] {"name"},   
				                new int[] {R.id.mapmenu3_list_text});   
			        lv.setAdapter(listAdapter);
			        lv.setOnItemClickListener(new OnItemClickListener() {

						 
						public void onItemClick(AdapterView<?> parent,
								View view, int index, long id) {
							// TODO Auto-generated method stub
							if (index == 0)// 频道发言
							{
								Intent intent = new Intent();
								Bundle bundle = new Bundle();
								bundle.putInt("type", 0);

								intent.putExtras(bundle);
								intent.setClass(ChatBoxActivity.this, ChatActivity.class);

								ChatBoxActivity.this.startActivityForResult(intent,
										GameDefinition.REQWRITE_MESSAGE);
								dlg1.dismiss();
							} else if (index == 1)// 发送私聊
							{

								Intent intent = new Intent();
								Bundle bundle = new Bundle();
								bundle.putInt("type", 1);

								bundle.putString("destname", (chat.name.indexOf('<')>=0)?(chat.name = chat.name.substring(chat.name.indexOf('<')+1,chat.name.indexOf('>'))):(chat.name));
								bundle.putLong("destId", chat.id);

								intent.putExtras(bundle);
								intent.setClass(ChatBoxActivity.this, ChatActivity.class);
								ChatBoxActivity.this.startActivityForResult(intent,
										GameDefinition.REQWRITE_MESSAGE);
								dlg1.dismiss();
//								ChatBoxActivity.this.Activitychange(
//										ChatActivity.class, intent);
							} else if (index == 2)// 加为好友
							{
								Connection.sendMessage(
										GameProtocol.CONNECTION_Relation_Add_Req,
										ConstructData.get_Relationship_operation(
												chat.id,
												SocialActivity.FRIEND_TYPE_FRIEND));
								dlg1.dismiss();
							} else if (index == 3)// 发送邮件
							{

								Intent intent = new Intent();

								Bundle bundle = new Bundle();

								bundle.putString("receiver", chat.name);

								intent.putExtras(bundle);

								intent.setClass(ChatBoxActivity.this,
										WriteMailActivity.class);

								ChatBoxActivity.this.startActivityForResult(intent,
										GameDefinition.REQWRITE_MAIL);
								dlg1.dismiss();
							}
						}
					});

				/*Builder builder = new AlertDialog.Builder(ChatBoxActivity.this);

				builder.setItems(item, new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int index) {
						if (index == 0)// 频道发言
						{
							Intent intent = new Intent();
							Bundle bundle = new Bundle();
							bundle.putInt("type", 0);

							intent.putExtras(bundle);
							intent.setClass(ChatBoxActivity.this, ChatActivity.class);

							ChatBoxActivity.this.startActivityForResult(intent,
									GameDefinition.REQWRITE_MESSAGE);
						} else if (index == 1)// 发送私聊
						{

							Intent intent = new Intent();
							Bundle bundle = new Bundle();
							bundle.putInt("type", 1);

							bundle.putString("destname", (chat.name.indexOf('<')>=0)?(chat.name = chat.name.substring(chat.name.indexOf('<')+1,chat.name.indexOf('>'))):(chat.name));
							bundle.putLong("destId", chat.id);

							intent.putExtras(bundle);
							intent.setClass(ChatBoxActivity.this, ChatActivity.class);
							ChatBoxActivity.this.startActivityForResult(intent,
									GameDefinition.REQWRITE_MESSAGE);
//							ChatBoxActivity.this.Activitychange(
//									ChatActivity.class, intent);
						} else if (index == 2)// 加为好友
						{
							Connection.sendMessage(
									GameProtocol.CONNECTION_Relation_Add_Req,
									ConstructData.get_Relationship_operation(
											chat.id,
											SocialActivity.FRIEND_TYPE_FRIEND));
						} else if (index == 3)// 发送邮件
						{

							Intent intent = new Intent();

							Bundle bundle = new Bundle();

							bundle.putString("receiver", chat.name);

							intent.putExtras(bundle);

							intent.setClass(ChatBoxActivity.this,
									WriteMailActivity.class);

							ChatBoxActivity.this.startActivityForResult(intent,
									GameDefinition.REQWRITE_MAIL);
						}

					}

				}).create().show();
*/
			}
		});
		// -->>zhouzhilong add
		worldView.setOnScrollListener(new OnScrollListener() {

			 
			public void onScrollStateChanged(AbsListView view, int scrollState) {
			}

			 
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				if (firstVisibleItem + visibleItemCount < totalItemCount) {
					worldView.setStackFromBottom(true);
					worldView
							.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
				}
			}
		});

		privatechatView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				final Chat chat = (Chat) GameData.privateChatbox
						.elementAt(position);

				if (chat.id == GameData.player.id || chat.id < 0)// 如果是自己,直接频道发言
				{
					Intent intent = new Intent();
					Bundle bundle = new Bundle();
					bundle.putInt("type", 0);

					intent.putExtras(bundle);

					intent.setClass(ChatBoxActivity.this, ChatActivity.class);

					ChatBoxActivity.this.startActivityForResult(intent,
							GameDefinition.REQWRITE_MESSAGE);
					return;
				}

				String chatbox_item = MainActivity.resources.getString(R.string.chatbox_item);
				String[] item = chatbox_item.split(",");
				final  AlertDialog dlg1 = new AlertDialog.Builder(ChatBoxActivity.this).create();
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

					 SimpleAdapter listAdapter = new SimpleAdapter(ChatBoxActivity.this,list,   
				                R.layout.mapmenu3_item, new String[] {"name"},   
				                new int[] {R.id.mapmenu3_list_text});   
			        lv.setAdapter(listAdapter);
			        lv.setOnItemClickListener(new OnItemClickListener() {

						 
						public void onItemClick(AdapterView<?> parent,
								View view, int index, long id) {
							// TODO Auto-generated method stub
							if (index == 0)// 频道发言
							{
								Intent intent = new Intent();
								Bundle bundle = new Bundle();
								bundle.putInt("type", 0);

								intent.putExtras(bundle);
								intent.setClass(ChatBoxActivity.this, ChatActivity.class);

								ChatBoxActivity.this.startActivityForResult(intent,
										GameDefinition.REQWRITE_MESSAGE);
								dlg1.dismiss();
							} else if (index == 1)// 发送私聊
							{
								Intent intent = new Intent();
								Bundle bundle = new Bundle();
								bundle.putInt("type", 1);
								
								//chat.name = (chat.name.indexOf('$')>=0)?(chat.name = chat.name.substring(chat.name.lastIndexOf('$')+1, chat.name.length())):(chat.name);
							//	chat.name = (chat.name.indexOf('<')>=0)?(chat.name = chat.name.substring(chat.name.indexOf('<')+1,chat.name.indexOf('>'))):(chat.name);
							//	bundle.putString("destname", chat.name);
								bundle.putString("destname", (chat.name.indexOf('<')>=0)?(chat.name = chat.name.substring(chat.name.indexOf('<')+1,chat.name.indexOf('>'))):(chat.name));
								bundle.putLong("destId", chat.id);

								intent.putExtras(bundle);
								intent.setClass(ChatBoxActivity.this, ChatActivity.class);
								ChatBoxActivity.this.startActivityForResult(intent,
										GameDefinition.REQWRITE_MESSAGE);
								dlg1.dismiss();
							} else if (index == 2)// 加为好友
							{
								Connection.sendMessage(
										GameProtocol.CONNECTION_Relation_Add_Req,
										ConstructData.get_Relationship_operation(
												chat.id,
												SocialActivity.FRIEND_TYPE_FRIEND));
								dlg1.dismiss();
							} else if (index == 3)// 发送邮件
							{

								Intent intent = new Intent();

								Bundle bundle = new Bundle();

								bundle.putString("receiver",(chat.name.indexOf('<')>=0)?(chat.name = chat.name.substring(chat.name.indexOf('<')+1,chat.name.indexOf('>'))):(chat.name));

								intent.putExtras(bundle);

								intent.setClass(ChatBoxActivity.this,
										WriteMailActivity.class);

								ChatBoxActivity.this.startActivityForResult(intent,
										GameDefinition.REQWRITE_MAIL);
								dlg1.dismiss();
							}
						}
					});
			/*	Builder builder = new AlertDialog.Builder(ChatBoxActivity.this);

				builder.setItems(item, new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int index) {
						if (index == 0)// 频道发言
						{
							Intent intent = new Intent();
							Bundle bundle = new Bundle();
							bundle.putInt("type", 0);

							intent.putExtras(bundle);
							intent.setClass(ChatBoxActivity.this, ChatActivity.class);

							ChatBoxActivity.this.startActivityForResult(intent,
									GameDefinition.REQWRITE_MESSAGE);
						} else if (index == 1)// 发送私聊
						{
							Intent intent = new Intent();
							Bundle bundle = new Bundle();
							bundle.putInt("type", 1);
							
							//chat.name = (chat.name.indexOf('$')>=0)?(chat.name = chat.name.substring(chat.name.lastIndexOf('$')+1, chat.name.length())):(chat.name);
						//	chat.name = (chat.name.indexOf('<')>=0)?(chat.name = chat.name.substring(chat.name.indexOf('<')+1,chat.name.indexOf('>'))):(chat.name);
						//	bundle.putString("destname", chat.name);
							bundle.putString("destname", (chat.name.indexOf('<')>=0)?(chat.name = chat.name.substring(chat.name.indexOf('<')+1,chat.name.indexOf('>'))):(chat.name));
							bundle.putLong("destId", chat.id);

							intent.putExtras(bundle);
							intent.setClass(ChatBoxActivity.this, ChatActivity.class);
							ChatBoxActivity.this.startActivityForResult(intent,
									GameDefinition.REQWRITE_MESSAGE);
						} else if (index == 2)// 加为好友
						{
							Connection.sendMessage(
									GameProtocol.CONNECTION_Relation_Add_Req,
									ConstructData.get_Relationship_operation(
											chat.id,
											SocialActivity.FRIEND_TYPE_FRIEND));
						} else if (index == 3)// 发送邮件
						{

							Intent intent = new Intent();

							Bundle bundle = new Bundle();

							bundle.putString("receiver",(chat.name.indexOf('<')>=0)?(chat.name = chat.name.substring(chat.name.indexOf('<')+1,chat.name.indexOf('>'))):(chat.name));

							intent.putExtras(bundle);

							intent.setClass(ChatBoxActivity.this,
									WriteMailActivity.class);

							ChatBoxActivity.this.startActivityForResult(intent,
									GameDefinition.REQWRITE_MAIL);
						}

					}

				}).create().show();
*/
			}

		});
		// -->>zhouzhilong add
		privatechatView.setOnScrollListener(new OnScrollListener() {

			 
			public void onScrollStateChanged(AbsListView view, int scrollState) {
			}

			 
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				if (firstVisibleItem + visibleItemCount < totalItemCount) {
					privatechatView.setStackFromBottom(true);
					privatechatView
							.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
				}
			}
		});
		// -->>zhouzhilong add
		systemView.setOnScrollListener(new OnScrollListener() {

			 
			public void onScrollStateChanged(AbsListView view, int scrollState) {
			}

			 
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				if (firstVisibleItem + visibleItemCount < totalItemCount) {
					systemView.setStackFromBottom(true);
					systemView
							.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
				}
			}
		});
		setSelectIndex(0);
		all.setBackgroundResource(R.drawable.button_all_pressed);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == GameDefinition.REQWRITE_MESSAGE) {

			if (data == null) {
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
			// //添加到长住内存中
			// GameData.addChat(0, chat);//向全部里添加聊天信息
			// GameData.addChat(c, chat);//向频道里添加
			// //添加到显示界面中
			// this.addChat(0, chat);
			// this.addChat(c, chat);

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
				Toast.makeText(ChatBoxActivity.this,
						R.string.chatbox_receiver, Toast.LENGTH_SHORT)
						.show();
			} else if (title.trim().length() == 0) {
				// Game.instance.initClewBox("系统消息", "给您的邮件起一个言简意赅的主题吧。", true);
				Toast.makeText(ChatBoxActivity.this, R.string.chatbox_title,
						Toast.LENGTH_SHORT).show();
			} else if (content.trim().length() == 0) {
				// Game.instance.initClewBox("系统消息", "请您尽情挥洒文采，写一封生动的邮件吧~",
				// true);
				Toast.makeText(ChatBoxActivity.this,R.string.chatbox_content,
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

	public void setSelectIndex(int index) {
		// select = index;
		allView.setVisibility(View.GONE);
		worldView.setVisibility(View.GONE);
		privatechatView.setVisibility(View.GONE);
		systemView.setVisibility(View.GONE);

		if (index == 0)// 全部
		{
			allView.setVisibility(View.VISIBLE);

		} else if (index == 1)// 世界
		{
			worldView.setVisibility(View.VISIBLE);

		} else if (index == 2)// 私聊
		{
			privatechatView.setVisibility(View.VISIBLE);

		} else if (index == 3)// 系统
		{
			systemView.setVisibility(View.VISIBLE);

		}

	}

	/* zhouzhilong */
	/**************** allAdapter **********************/
	private void initAllAdapter() {

		HashMap<String, Object> allMap = null;
		Chat allChat = null;

		alllist.clear();

		for (int i = 0; i < GameData.allChatbox.size(); i++) {
			allChat = (Chat) GameData.allChatbox.elementAt(i);

			allMap = new HashMap<String, Object>();

			allMap.put("content", channel[allChat.channel] + allChat.name + ":"
					+ SystemAPI.getText(allChat.content));
			alllist.add(allMap);
		}
		alladapter = new SimpleAdapter(this, alllist, R.layout.chat_item,
				new String[] { "content" }, new int[] { R.id.chat_item_text });
		allView.setAdapter(alladapter);
	}

	/**************** worldAdapter **********************/
	private void iniWorldAdapter() {
		HashMap<String, Object> worldMap = null;
		Chat worldChat = null;

		worldlist.clear();

		for (int i = 0; i < GameData.worldChatbox.size(); i++) {
			worldChat = (Chat) GameData.worldChatbox.elementAt(i);

			worldMap = new HashMap<String, Object>();

			worldMap.put("content", channel[worldChat.channel] + worldChat.name
					+ ":" + SystemAPI.getText(worldChat.content));
			worldlist.add(worldMap);
		}
		worldadapter = new SimpleAdapter(this, worldlist, R.layout.chat_item,
				new String[] { "content" }, new int[] { R.id.chat_item_text });
		worldView.setAdapter(worldadapter);
	}

	/**************** privatechatAdapter ****************/
	private void initPrivateChatAdapter() {
		HashMap<String, Object> privMap = null;
		Chat privChat = null;

		privatechatlist.clear();

		for (int i = 0; i < GameData.privateChatbox.size(); i++) {
			privChat = (Chat) GameData.privateChatbox.elementAt(i);

			privMap = new HashMap<String, Object>();

			privMap.put("content", channel[privChat.channel] + privChat.name
					+ ":" + SystemAPI.getText(privChat.content));
			privatechatlist.add(privMap);
		}
		privatechatadapter = new SimpleAdapter(this, privatechatlist,
				R.layout.chat_item, new String[] { "content" },
				new int[] { R.id.chat_item_text });
		privatechatView.setAdapter(privatechatadapter);
		/**************** system ********************/
		HashMap<String, Object> sysMap = null;
		Chat sysChat = null;

		systemlist.clear();

		for (int i = 0; i < GameData.systemChatbox.size(); i++) {
			sysChat = (Chat) GameData.systemChatbox.elementAt(i);

			sysMap = new HashMap<String, Object>();

			sysMap.put("content", channel[sysChat.channel] + sysChat.name + ":"
					+ sysChat.content);
			systemlist.add(sysMap);
		}
		systemadapter = new SimpleAdapter(this, systemlist, R.layout.chat_item,
				new String[] { "content" }, new int[] { R.id.chat_item_text });
		systemView.setAdapter(systemadapter);
	}

	public void addChat(int index, Chat chat) {
		/*
		 * if (index != this.select) { return; }
		 */

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("content", channel[chat.channel] + chat.name + ":"
				+ SystemAPI.getText(chat.content));
		// zhouzhilong delete
		/*
		 * if (index == 0)// 全部 {
		 */
		alllist.add(map);
		alladapter.notifyDataSetChanged();
		/* } else */
		if (index == 1)// 世界
		{
			worldlist.add(map);
			worldadapter.notifyDataSetChanged();
		} else if (index == 2)// 私聊
		{
			privatechatlist.add(map);
			privatechatadapter.notifyDataSetChanged();
		} else if (index == 3)// 系统
		{
			systemlist.add(map);
			systemadapter.notifyDataSetChanged();
		}
	}

	public boolean onTouch(View view, MotionEvent arg1) {
		all.setBackgroundResource(R.drawable.button_all);
		world.setBackgroundResource(R.drawable.button_world);
		privatechat.setBackgroundResource(R.drawable.button_privatechat);
		system.setBackgroundResource(R.drawable.button_system);

		if (view == all) {
			all.setBackgroundResource(R.drawable.button_all_pressed);
		} else if (view == world) {
			world.setBackgroundResource(R.drawable.button_world_pressed);
		} else if (view == privatechat) {
			privatechat
					.setBackgroundResource(R.drawable.button_privatechat_pressed);
		} else if (view == system) {
			system.setBackgroundResource(R.drawable.button_system_pressed);
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
