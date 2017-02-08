package com.zrong.Android.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import com.zrong.Android.Util.SystemAPI;
import com.zrong.Android.Util.chatAdapter;
import com.zrong.Android.element.Chat;
import com.zrong.Android.game.Connection;
import com.zrong.Android.game.ConstructData;
import com.zrong.Android.game.GameData;
import com.zrong.Android.game.GameDefinition;
import com.zrong.Android.online.network.GameProtocol;

import android.content.Intent;
import android.os.Bundle;
import android.text.style.BulletSpan;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.ViewFlipper;


public class ChatWindowActivity extends GameActivity
{
	public static ChatWindowActivity context;
	public int currentIndex;
	/**
	 * 切换对象
	 * */
	public ViewFlipper flipper;
	/**
	 * 上层按钮
	 * */
	public Button button[];
	public Button button2[][];
	public Button back;
	public buttonListener listener;
	/**
	 * 界面列表
	 * */
	public ListView listview[];
	public int listviewId[] = {R.id.listView1,R.id.listView2,R.id.listView3,R.id.listView4,R.id.listView5};
	ArrayList<HashMap<String, String>> list[] = new ArrayList[5];
	chatAdapter adapter[] = new chatAdapter[5];
	/**
	 * */
	private  static final String chatbox_channel = MainActivity.resources.getString(R.string.chatbox_channel);
	private static final String channel[] = chatbox_channel.split(",");	
	
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		context = this;
		setContentView(R.layout.chatwindow);
		flipper = (ViewFlipper)findViewById(R.id.flipper);
		listview = new ListView[5];
		for(int i=0;i<5;i++)
		{
			list[i] = new ArrayList<HashMap<String, String>>(); 
		}
		
		listener = new buttonListener();
		initButton();
		
		setButtonSelection(0);
		for(int i=1;i<5;i++)
		{
			initListView(i);
		}
	}
	/**
	 * 初始化当前列表
	 * */
	public void initListView(int index)
	{
		if(GameData.chatInfo[index]== null)
		{
			GameData.chatInfo[index] = new Vector(10);
		}
		
		{
			list[index].clear();
//			Log.i("zzx", "切换频道:" +index+"数据量："+GameData.chatInfo[index].size());
			for(int i=0;i<GameData.chatInfo[index].size();i++)
			{
				HashMap<String, String> map = new HashMap<String, String>();
				Chat chat = (Chat) GameData.chatInfo[index].elementAt(i);
				map.put("title", channel[chat.channel]);
				map.put("name", getChatInfo(chat)); 
				map.put("image", getName(chat));
//				map.put("sprite", getName(chat));

//				Log.i("zzx", "该频道:" +index+":"+i+"="+SystemAPI.getText(chat.content));
				list[index].add(map);
			}
//			Log.i("zzx", "结束");
			
			adapter[index] = new chatAdapter(this, list[index], R.layout.chat_item, new String[]{"title","name","image","sprite"}, new int[]{R.id.chat_title,R.id.chat_item_text,R.id.image/*,R.id.sprite*/});
			if(list[index].size()>0)
			adapter[index].setCheck(list[index].size()-1, true, true);
			
			listview[index] = (ListView)findViewById(listviewId[index]);
			listview[index].setAdapter(adapter[index]);
			listview[index].setSelection(list[index].size());
			listview[index].setOnItemClickListener(new OnItemClickListener()
			{

				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id)
				{
					chatAdapter adapter = (chatAdapter) listview[currentIndex].getAdapter(); 
					if(adapter.isCheck(position))
					{
						adapter.setCheck(position, false, true);
					}else
					{
						adapter.setCheck(position, true, true);
					}
					adapter.notifyDataSetChanged();
				}
			});
		}
		
	}
	/**
	 * 添加新的数据
	 * */
	public void addChat(int index, Chat chat)
	{ 
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("title", channel[chat.channel]);
		map.put("name", getChatInfo(chat));
		map.put("image", getName(chat));
//		map.put("sprite", getName(chat));

		list[0].add(map);
		adapter[0].notifyDataSetChanged();
		adapter[0].addCheck(true, true);
		listview[0].setSelection(list[0].size());

		list[index + 1].add(map);
		adapter[index + 1].notifyDataSetChanged();
		adapter[index + 1].addCheck(true, true);
		listview[index + 1].setSelection(list[index + 1].size());

//		Log.i("zzx", "新增数据"+getChatInfo(chat)+"平台ID"+(index+1));
	}
	
	public String getChatInfo(Chat chat)
	{
		return getName(chat)+":"
		+ SystemAPI.getText(chat.content);
	}
	public String getName(Chat chat)
	{
		String title = chat.name;
		if(title.indexOf('$')>=0)
		{
			int end = title.lastIndexOf('$');
//			title = title.substring(1, end);//截取的是 中间的
			title = title.substring(end+1,title.length());
		}
		return title;
	}
	/**
	 * 初始化 button 按钮
	 * */
	public void initButton()
	{
		back = (Button)findViewById(R.id.out);
		back.setOnClickListener(listener);
		
		button = new Button[5];
		button[0] = (Button)findViewById(R.id.button1);
		button[1] = (Button)findViewById(R.id.button2);
		button[2] = (Button)findViewById(R.id.button3);
		button[3] = (Button)findViewById(R.id.button4);
		button[4] = (Button)findViewById(R.id.button5);
		for(int i=0;i<button.length;i++)
		{
			button[i].setOnClickListener(listener);
		}
		
		button2 = new Button[5][5];
		
		button2[0][0] = (Button)findViewById(R.id.button0_1);
		button2[0][1] = (Button)findViewById(R.id.button0_2);
		button2[0][2] = (Button)findViewById(R.id.button0_3);
		button2[0][3] = (Button)findViewById(R.id.button0_4);
		button2[0][4] = (Button)findViewById(R.id.button0_5);
		

		button2[1][0] = (Button)findViewById(R.id.button1_1);
		button2[1][1] = (Button)findViewById(R.id.button1_2);
		button2[1][2] = (Button)findViewById(R.id.button1_3);
		button2[1][3] = (Button)findViewById(R.id.button1_4);
		button2[1][4] = (Button)findViewById(R.id.button1_5);
		

		button2[2][0] = (Button)findViewById(R.id.button2_1);
		button2[2][1] = (Button)findViewById(R.id.button2_2);
		button2[2][2] = (Button)findViewById(R.id.button2_3);
		button2[2][3] = (Button)findViewById(R.id.button2_4);
		button2[2][4] = (Button)findViewById(R.id.button2_5);
		

		button2[3][0] = (Button)findViewById(R.id.button3_1);
		button2[3][1] = (Button)findViewById(R.id.button3_2);
		button2[3][2] = (Button)findViewById(R.id.button3_3);
		button2[3][3] = (Button)findViewById(R.id.button3_4);
		button2[3][4] = (Button)findViewById(R.id.button3_5);
		

		button2[4][0] = (Button)findViewById(R.id.button4_1);
		button2[4][1] = (Button)findViewById(R.id.button4_2);
		button2[4][2] = (Button)findViewById(R.id.button4_3);
		button2[4][3] = (Button)findViewById(R.id.button4_4);
		button2[4][4] = (Button)findViewById(R.id.button4_5);
		
		for(int i=0;i<button2.length;i++)
		{
			for(int j=0;j<button2[i].length;j++)
			{
				button2[i][j].setOnClickListener(listener);
			}
		}
		
	}

	/**
	 * 设置当前选中项
	 * */
	public void setButtonSelection(int index)
	{
		for(int i=0;i<button.length;i++)
		{
			if(i == index)
			{
				currentIndex = index;
				button[index].setBackgroundResource(R.drawable.selection_selected);
				flipper.setDisplayedChild(index);
				initListView(index);
			}else
			{
				button[i].setBackgroundResource(R.drawable.selection);
			}
		}
		
	}

	public GameActivity getGameContext()
	{
		// TODO Auto-generated method stub
		return context;
	}
	
	public void finish()
	{
		// TODO Auto-generated method stub
		super.finish();
		context = null;
	}

	public class buttonListener implements OnClickListener
	{

		public void onClick(View v)
		{
			int id = v.getId();
			Intent intent = new Intent();
			Bundle bundle = new Bundle();
			String name = "";
			Chat chat = null;
			switch(id)
			{
			case R.id.out:
				finish();
				break;
			case R.id.button1://全部
				setButtonSelection(0);
				break;
			case R.id.button2://世界
				setButtonSelection(1);
				break;
			case R.id.button3://商会
				setButtonSelection(2);
				break;
			case R.id.button4://私聊
				setButtonSelection(3);
				break;
			case R.id.button5://系统
				setButtonSelection(4);
				break;
			case R.id.button0_1://发送邮件
			case R.id.button1_1:
			case R.id.button2_1:
			case R.id.button3_1:
			case R.id.button4_1:
				chat = getSelectChat(); 
				if(chat == null)
				{
					Display("请选择一个人物");
					return;
				}
				if(chat.id == GameData.player.id)
				{
					Display("不能对自己私聊");
					return;
				}
				bundle.putString("receiver", chat.name); 
				intent.putExtras(bundle); 
				intent.setClass(ChatWindowActivity.this,
						WriteMailActivity.class); 
				ChatWindowActivity.this.startActivityForResult(intent,
						GameDefinition.REQWRITE_MAIL);
				break;
			case R.id.button0_2://发起私聊
			case R.id.button1_2:
			case R.id.button2_2:
			case R.id.button3_2:
			case R.id.button4_2:
				chat = getSelectChat();
				
				if(chat == null)
				{
					Display("请选择人物进行私聊");
					return;
				}
				if(chat.id == GameData.player.id)
				{
					Display("不能对自己私聊");
					return;
				}
				name = chat.name;
				name = name.indexOf("<")>=0?(name = name.substring(name.indexOf("<")+1, name.indexOf(">"))):name;
				bundle.putInt("type", 1);
				bundle.putString("destname",name);
				bundle.putLong("destId", chat.id);
				intent.putExtras(bundle);
				intent.setClass(ChatWindowActivity.this, ChatActivity.class);
				ChatWindowActivity.this.startActivityForResult(intent, GameDefinition.REQWRITE_MESSAGE);
				break;
			case R.id.button0_3://频道发言
			case R.id.button1_3:
			case R.id.button2_3:
			case R.id.button3_3:
			case R.id.button4_3:
				 intent = new Intent();
				 bundle = new Bundle();
				if (id == R.id.button3_3)
				{
					if(GameData.player.businessId<0)
					{
						Display("您不是商会员工");
						return;
					}
					bundle.putInt("type", 2);
				} else
				{
					bundle.putInt("type", 0);
				}
				bundle.putByte("channel", (byte)0); 
				intent.putExtras(bundle);
				intent.setClass(ChatWindowActivity.this, ChatActivity.class);
				ChatWindowActivity.this.startActivityForResult(intent, GameDefinition.REQWRITE_MESSAGE);
				break;
			case R.id.button0_4://添加好友
			case R.id.button1_4:
			case R.id.button2_4:
			case R.id.button3_4:
			case R.id.button4_4:
				chat = getSelectChat();
				if(chat == null)
				{
					Display("请选择一个人物");
					return;
				}
				if(chat.id == GameData.player.id)
				{
					Display("不能对添加自己为好友");
					return;
				}
				Connection.sendMessage(
						GameProtocol.CONNECTION_Relation_Add_Req,
						ConstructData.get_Relationship_operation(
								chat.id,
								SocialActivity.FRIEND_TYPE_FRIEND));
				break;
			case R.id.button0_5://工会邀请
			case R.id.button1_5:
			case R.id.button2_5:
			case R.id.button3_5:
			case R.id.button4_5:
				chat = getSelectChat(); 
				if(chat == null)
				{
					Display("请选择一个人物");
					return;
				}
				if(chat.id == GameData.player.id)
				{
					Display("不能邀请自己 加入商会");
					return;
				}
				if(GameData.member == null)
				{
					Display("nin还为加入任何商会，不能邀请他人");
					return;
				}
				Connection.sendMessage(GameProtocol.
						CONNECTION_SEND_COfC_Join_Req,
						ConstructData.getCOfC_Join_Req((byte)0,0,chat.name,GameData.member.id));
				break;
			}
		}
		
	}
	/**
	 * 得到当前选中项的人物信息
	 * */
	public Chat getSelectChat()
	{
		Chat chat = null;
		int index = ((chatAdapter)listview[currentIndex].getAdapter()).getSelectIndex();
		if(index >=0)
		   chat = (Chat)GameData.chatInfo[currentIndex].elementAt(index);
		
		return chat;
	}
	/**
	 * 两个界面切换时 数据的交换
	 * requestCode 请求码
	 * reusltCode 返回码
	 * data 数据
	 * */
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(data == null)
			return;
		
		Bundle bundle = data.getExtras();
		/**类型 0世界1私聊 2商会 3系统*/
		int type = bundle.getInt("type");
		/**内容*/
		String content = bundle.getString("content");
		/**频道*/
		int channel = bundle.getInt("channle", 0);
		/**人物id*/
		long Id = bundle.getLong("destId");
		/**名称*/
		String name = bundle.getString("destname");
		
		switch(requestCode)
		{
		case GameDefinition.REQWRITE_MESSAGE:
			
			Connection.sendMessage(GameProtocol.CONNECTION_SEND_CHAT_REQ,
					ConstructData.getChatData((byte)channel,content, Id));
			break;
		}
	}
	
	/** 屏幕提示 */
	public void Display(String str)
	{
		Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
	}

}
