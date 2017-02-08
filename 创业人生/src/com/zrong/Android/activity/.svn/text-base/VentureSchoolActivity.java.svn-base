package com.zrong.Android.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.zrong.Android.Listener.ActivityTransform;
import com.zrong.Android.Util.Music;
import com.zrong.Android.Util.MyImageAdapter;
import com.zrong.Android.game.Connection;
import com.zrong.Android.game.ConstructData;
import com.zrong.Android.game.GameDefinition;
import com.zrong.Android.online.network.GameProtocol;

public class VentureSchoolActivity extends GameActivity  {

	public static VentureSchoolActivity mContext = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);}
/*
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		mContext = this;

		this.setContentView(R.layout.characterdetail);

		Button returnback = (Button) this
				.findViewById(R.id.characterdetail_button_returnback);

		Button cancel = (Button) this
				.findViewById(R.id.characterdetail_button_cancel);
		
		Button card = (Button) this
		.findViewById(R.id.characterdetail_button_card);

		returnback.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				VentureSchoolActivity.this.finish();

			}
		});
	cancel.setOnClickListener(new OnClickListener() {
	 

			public void onClick(View arg0) {
				VentureSchoolActivity.this.finish();
			
			}
		});
		card.setOnClickListener(new Button.OnClickListener() {
			
			public void onClick(View arg0) {
				
			MainActivity.mContext.Activitychange(CardListActivity.class, null);
		
			}
		});	

		// 取得GridView对象
		GridView gridview = (GridView) this
				.findViewById(R.id.characterdetail_GridView);
		// 添加元素给gridview
		gridview.setAdapter(new MyImageAdapter(this));

		// 设置Gallery的背景
		// gridview.setBackgroundResource(R.drawable.bg0);

		// 事件监听
		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				// Toast.makeText(Activity01.this, "你选择了" + (position + 1) +
				// " 号图片", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent();

				Bundle bundle = new Bundle();

				switch (position) {
				case 0:

					bundle.putString("url",
							"http://218.247.140.193:9999/WapGame/study.do?cmd=chuangyegushi");

					intent.putExtras(bundle);

					MainActivity.mContext
							.Activitychange(MoreGame.class, intent);
					break;
				case 1:

					bundle.putString("url",
							"http://218.247.140.193:9999/WapGame/study.do?cmd=chuangyezhidao");

					intent.putExtras(bundle);

					MainActivity.mContext
							.Activitychange(MoreGame.class, intent);
					break;
				case 2:

					bundle.putString("url",
							"http://218.247.140.193:9999/WapGame/study.do?cmd=chuangyehuodong");

					intent.putExtras(bundle);

					MainActivity.mContext
							.Activitychange(MoreGame.class, intent);
					break;
				case 3:

					bundle.putString("url",
							"http://218.247.140.193:9999/WapGame/study.do?cmd=zhengcexinxi");

					intent.putExtras(bundle);

					MainActivity.mContext
							.Activitychange(MoreGame.class, intent);
					break;
				case 4:

					bundle.putString("url",
							"http://218.247.140.193:9999/WapGame/study.do?cmd=zhiyejineng");

					intent.putExtras(bundle);

					MainActivity.mContext
							.Activitychange(MoreGame.class, intent);
					break;
				case 5:

					bundle.putString("url",
							"http://218.247.140.193:9999/WapGame/study.do?cmd=zhuanjiadayi");

					intent.putExtras(bundle);

					mContext
							.Activitychange(MoreGame.class, intent);
					break;
				case 6:

					bundle.putString("url",
							"http://218.247.140.193:9999/WapGame/study.do?cmd=quanweifabu");

					intent.putExtras(bundle);

					mContext
							.Activitychange(MoreGame.class, intent);
					break;
				case 7:
					bundle.putBoolean("state", false);
					bundle.putString("receiver", MainActivity.resources.getString(R.string.ventureschool_doctor));
					intent.putExtras(bundle);
					intent.setClass(VentureSchoolActivity.this,
							WriteMailActivity.class);
					
					VentureSchoolActivity.this.startActivityForResult(intent,
							GameDefinition.REQWRITE_MAIL);
					break;
				}
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
			Toast.makeText(VentureSchoolActivity.this,
					MainActivity.resources.getString(R.string.social_toast1), Toast.LENGTH_SHORT).show();
		} else if (title.trim().length() == 0) {
			// Game.instance.initClewBox("系统消息", "给您的邮件起一个言简意赅的主题吧。", true);
			Toast.makeText(VentureSchoolActivity.this, MainActivity.resources.getString(R.string.social_toast2),
					Toast.LENGTH_SHORT).show();
		} else if (content.trim().length() == 0) {
			// Game.instance.initClewBox("系统消息", "请您尽情挥洒文采，写一封生动的邮件吧~", true);
			Toast.makeText(VentureSchoolActivity.this, MainActivity.resources.getString(R.string.social_toast3),
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
*/



	public void Activitychange(Class calss, Intent intent) {

		if (intent == null) {
			intent = new Intent();
		}

		intent.setClass(VentureSchoolActivity.this, calss);
	
		this.startActivity(intent);
	}

	@Override
	public GameActivity getGameContext() {
		// TODO Auto-generated method stub
		return this;
	}
	
	@Override
	public void finish() 
	{
		mContext = null;
		super.finish();
	}
}
