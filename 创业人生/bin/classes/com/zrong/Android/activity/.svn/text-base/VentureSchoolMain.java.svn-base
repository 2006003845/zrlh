package com.zrong.Android.activity;

import java.util.ArrayList; 

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.zrong.Android.Listener.ActivityTransform;
import com.zrong.Android.Util.Music;
import com.zrong.Android.Util.MyImageAdapter;
import com.zrong.Android.api.Get2ApiImpl;
import com.zrong.Android.api.IGet2Api;
import com.zrong.Android.api.WSError;
import com.zrong.Android.entity.Area;
import com.zrong.Android.entity.MapInfo;
import com.zrong.Android.entity.VentureSchoolData;
import com.zrong.Android.game.Connection;
import com.zrong.Android.game.ConstructData;
import com.zrong.Android.game.GameDefinition;
import com.zrong.Android.online.network.GameProtocol;

public class VentureSchoolMain extends GameActivity {

	public static VentureSchoolMain mContext = null;
	public static final String school_menu =String.valueOf(MainActivity.resources.getString(R.string.school_menu)) ;
	public static final String menu[] = school_menu.split(",");
	private TextView school_desc;
	public static int p=0;
	 
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		mContext = this;

		this.setContentView(R.layout.characterdetail);


		Button cancel = (Button) this
				.findViewById(R.id.characterdetail_button_cancel);
		Button card = (Button) this
		.findViewById(R.id.characterdetail_button_returnback);
		Button confirm = (Button)this.findViewById(R.id.confirm);
		 school_desc =(TextView)this.findViewById(R.id.venture_desc);
		school_desc.setText(menu[0]);

		cancel.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				VentureSchoolMain.this.finish();

			}
		});
	card.setOnClickListener(new Button.OnClickListener() {
			
			public void onClick(View arg0) {
				
			//MainActivity.mContext.Activitychange(CardListActivity.class, null);
				Intent intent = new Intent();
				intent.setClass(VentureSchoolMain.this, CardListActivity.class);
				  
				startActivity(intent);
				 
				VentureSchoolMain.this.finish();
			}
		});	
	confirm.setOnClickListener(new OnClickListener() {
		
		 
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			Bundle bundle = new Bundle();
			switch(p){

			case 0:// 创业故事
				Activitychange(VentureStoryActivity.class, null);
				// 发包--创业故事
				Connection.sendMessage(
						GameProtocol.CONNECTION_SEND_VENTURESCHOOL_Req,
						ConstructData.getVentureSchoolInfo((byte) 5));
				break;
			case 1:// 创业测评	
				Activitychange(VentureEvalutionActivity.class, null);
				// 发包--创业测评
				Connection.sendMessage(
						GameProtocol.CONNECTION_SEND_VENTURESCHOOL_Req,
						ConstructData.getVentureSchoolInfo((byte) 3));
				break;
			case 2:// 创业活动
				Activitychange(VentureActionActivity.class, null);
				break;
			case 3:// 政策信息
				Activitychange(PolicyInfoListActivity.class, null);
				// 发包--政策信息
				Connection.sendMessage(
						GameProtocol.CONNECTION_SEND_VENTURESCHOOL_Req,
						ConstructData.getVentureSchoolInfo((byte) 0));
				break;
			case 4:// 职业技能
				Activitychange(VocationSkillActivity.class, null);
				// 发包--职业技能
				Connection.sendMessage(
						GameProtocol.CONNECTION_SEND_VENTURESCHOOL_Req,
						ConstructData.getVentureSchoolInfo((byte) 4));
				break;
			case 5:// 专家答疑
				Activitychange(ExpertAnswersActivity.class, null);
				// 发包--专家答疑
				Connection.sendMessage(
						GameProtocol.CONNECTION_SEND_VENTURESCHOOL_Req,
						ConstructData.getVentureSchoolInfo((byte) 2));
				break;
			case 6://权威发布
			//	Activitychange(ExpertAnswersActivity.class, intent);
				MainActivity.mContext.Activitychange(
					AuthorityIssuedActivity.class, intent);
				// 发包--创业测评
				Connection.sendMessage(
						GameProtocol.CONNECTION_SEND_VENTURESCHOOL_Req,
						ConstructData.getVentureSchoolInfo((byte) 1));
				break;
			case 7:
				bundle.putBoolean("state", false);
				bundle.putString("receiver", "睿博士");
				intent.putExtras(bundle);
				intent.setClass(VentureSchoolMain.this,
						WriteMailActivity.class);
				VentureSchoolMain.this.startActivityForResult(intent,
						GameDefinition.REQWRITE_MAIL);
				break;
			
			
			}
		}
	});
		// 取得GridView对象
	/*	GridView gridview = (GridView) this
				.findViewById(R.id.characterdetail_GridView);*/
		Gallery gallery = (Gallery) this
		.findViewById(R.id.logo1_gallery);
		// 添加元素给gridview
	//	gridview.setAdapter(new MyImageAdapter(this));
		gallery.setAdapter(new MyImageAdapter(this));

		// 设置Gallery的背景
		// gridview.setBackgroundResource(R.drawable.bg0);

		// 事件监听
		gallery.setOnItemSelectedListener(new OnItemSelectedListener() {

			 
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				p = position;
				school_desc.setText(menu[position]);
			}

			 
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
		gallery.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				p = position;
				school_desc.setText(menu[position]);
				// Toast.makeText(Activity01.this, "你选择了" + (position + 1) +
				// " 号图片", Toast.LENGTH_SHORT).show();
			/*	Intent intent = new Intent();

				Bundle bundle = new Bundle();

				switch (position) {
				case 0:// 创业故事
					Activitychange(VentureStoryActivity.class, null);
					// 发包--创业故事
					Connection.sendMessage(
							GameProtocol.CONNECTION_SEND_VENTURESCHOOL_Req,
							ConstructData.getVentureSchoolInfo((byte) 5));
					break;
				case 1:// 创业测评	
					Activitychange(VentureEvalutionActivity.class, null);
					// 发包--创业测评
					Connection.sendMessage(
							GameProtocol.CONNECTION_SEND_VENTURESCHOOL_Req,
							ConstructData.getVentureSchoolInfo((byte) 3));
					break;
				case 2:// 创业活动
					Activitychange(VentureActionActivity.class, null);
					break;
				case 3:// 政策信息
					Activitychange(PolicyInfoListActivity.class, null);
					// 发包--政策信息
					Connection.sendMessage(
							GameProtocol.CONNECTION_SEND_VENTURESCHOOL_Req,
							ConstructData.getVentureSchoolInfo((byte) 0));
					break;
				case 4:// 职业技能
					Activitychange(VocationSkillActivity.class, null);
					// 发包--职业技能
					Connection.sendMessage(
							GameProtocol.CONNECTION_SEND_VENTURESCHOOL_Req,
							ConstructData.getVentureSchoolInfo((byte) 4));
					break;
				case 5:// 专家答疑
					Activitychange(ExpertAnswersActivity.class, null);
					// 发包--专家答疑
					Connection.sendMessage(
							GameProtocol.CONNECTION_SEND_VENTURESCHOOL_Req,
							ConstructData.getVentureSchoolInfo((byte) 2));
					break;
				case 6://权威发布
				//	Activitychange(ExpertAnswersActivity.class, intent);
					MainActivity.mContext.Activitychange(
						AuthorityIssuedActivity.class, intent);
					// 发包--创业测评
					Connection.sendMessage(
							GameProtocol.CONNECTION_SEND_VENTURESCHOOL_Req,
							ConstructData.getVentureSchoolInfo((byte) 1));
					break;
				case 7:
					bundle.putBoolean("state", false);
					bundle.putString("receiver", "睿博士");
					intent.putExtras(bundle);
					intent.setClass(VentureSchoolMain.this,
							WriteMailActivity.class);
					VentureSchoolMain.this.startActivityForResult(intent,
							GameDefinition.REQWRITE_MAIL);
					break;
				}
			}*/
			}
		});
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		super.onActivityResult(requestCode, resultCode, data);

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
			// Game.instance.initClewBox("系统消息", "您不填写收件人的姓名，信件就不知道会寄到哪儿去了……",
			// true);
			Toast.makeText(VentureSchoolMain.this,
					"您不填写收件人的姓名，信件就不知道会寄到哪儿去了……", Toast.LENGTH_SHORT).show();
		} else if (title.trim().length() == 0) {
			// Game.instance.initClewBox("系统消息", "给您的邮件起一个言简意赅的主题吧。", true);
			Toast.makeText(VentureSchoolMain.this, "给您的邮件起一个言简意赅的主题吧。",
					Toast.LENGTH_SHORT).show();
		} else if (content.trim().length() == 0) {
			// Game.instance.initClewBox("系统消息", "请您尽情挥洒文采，写一封生动的邮件吧~", true);
			Toast.makeText(VentureSchoolMain.this, "请您尽情挥洒文采，写一封生动的邮件吧~",
					Toast.LENGTH_SHORT).show();
		} else {
			if (receiverId != 0) {
				Connection.sendMessage(GameProtocol.CONNECTION_SEND_MAIL_REQ,
						ConstructData.getMail(receiverId, "", title, content,
								money, (byte) 0));
			} else {
				Connection.sendMessage(GameProtocol.CONNECTION_SEND_MAIL_REQ,
						ConstructData.getMail(0, receiver, title, content,
								money, (byte) 0));
			}
		}
	}

	public void Activitychange(Class calss, Intent intent) {

		if (intent == null) {
			intent = new Intent();
		}
		if (calss == null) {
			return;
		}
		intent.setClass(VentureSchoolMain.this, calss);
	
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
