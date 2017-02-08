package com.zrong.Android.activity;

 

import java.util.ArrayList;
import java.util.HashMap;

 

import com.zrong.Android.element.Social;
import com.zrong.Android.game.Connection;
import com.zrong.Android.game.ConstructData;
import com.zrong.Android.game.GameData;
import com.zrong.Android.online.network.GameProtocol;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class SeacherFriendActivity extends GameActivity{

	 
	
	public static SeacherFriendActivity mContext = null;
	private ListView listview = null;
	
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		mContext = this;
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		this.setContentView(R.layout.seachfriendlist);
		
        Button returnback = (Button)this.findViewById(R.id.seachfriendlist_button_returnback);
		
		Button cancel = (Button)this.findViewById(R.id.seachfriendlist_button_cancel);
		
		Button refresh = (Button)this.findViewById(R.id.seachfriendlist_refresh);
		
		returnback.setOnClickListener(new OnClickListener()
		{

			public void onClick(View arg0) 
			{
				
				SeacherFriendActivity.this.finish();
			}
			
		}
		
		);
		
		cancel.setOnClickListener(new OnClickListener()
		{
			public void onClick(View arg0) 
			{	
				
				SeacherFriendActivity.this.finish();
			}
		}
		);
		
		refresh.setOnClickListener(new OnClickListener()
		{

			public void onClick(View arg0)
			{
				 
				Connection.sendMessage(GameProtocol.CONNECTION_Search_User_Req,ConstructData.get_Search_User_Req((byte)0,(byte)1,null));
			}
			
		}
		);
		//Connection.sendMessage(GameProtocol.CONNECTION_Search_User_Req,Connection.get_Search_User_Req((byte)1,null));
		listview = (ListView)this.findViewById(R.id.seachfriendlist_listview);
		
		listview.setOnItemClickListener(new OnItemClickListener()
		{

			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) 
			{
				String[] item = new String[]{MainActivity.resources.getString(R.string.searchfriend_addfriend),MainActivity.resources.getString(R.string.searchfriend_checkdocument)};
				
				Builder builder = new AlertDialog.Builder(SeacherFriendActivity.this);
				 
				final int p =position;
				
				 builder.setItems(item, new DialogInterface.OnClickListener()
					{

						public void onClick(DialogInterface dialog,
								int index)
						{
							Social fr = GameData.seachPlayer[p];
							if(index == 0)//加为好友
							{
								Connection.sendMessage(GameProtocol.CONNECTION_Relation_Add_Req,ConstructData.get_Relationship_operation(fr.id, SocialActivity.FRIEND_TYPE_FRIEND));
							}
							else//查看资料
							{
								Intent intent = new Intent();
								 Bundle bundle = new Bundle();
								 bundle.putByte("type", (byte)5);
								 bundle.putInt("index", p);
								 
								 intent.putExtras(bundle);
								 intent.setClass(SeacherFriendActivity.this, SocialDetailActivity.class);
								 SeacherFriendActivity.this.startActivity(intent);
								 //SocialActivity.this.Activitychange(SocialDetailActivity.class,intent);
								 
							}
						}
					}).create().show();
			}
		});
		initList();
	}
	
	public void initList()
	{
		if(GameData.seachPlayer != null)
		{
			ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
			HashMap<String,Object> map = null;
			Social s = null;
			
			for(int i = 0 ; i < GameData.seachPlayer.length; i++)
			{
				s = GameData.seachPlayer[i];
				map = new HashMap<String,Object>();
				map.put("name", s.name);
				map.put("level", s.level+MainActivity.resources.getString(R.string.searchfriend_ji));
				map.put("online", s.status== 0?MainActivity.resources.getString(R.string.searchfriend_outline):MainActivity.resources.getString(R.string.searchfriend_online));
				list.add(map);
			}
			SimpleAdapter adapter = new SimpleAdapter(this,list,R.layout.social_item,new String[]{"name","level","online"},new int[]{R.id.social_item_name,R.id.social_item_level,R.id.social_item_isonlie});
			listview.setAdapter(adapter);
		}
	}

	
	public void finish()
	{
		GameData.seachPlayer = null;
		mContext = null;
		super.finish();
	}

	@Override
	public GameActivity getGameContext() {
		// TODO Auto-generated method stub
		return this;
	}
	
	
}
