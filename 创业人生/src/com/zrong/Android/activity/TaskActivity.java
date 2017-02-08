package com.zrong.Android.activity;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.zrong.Android.Listener.ActivityTransform;
import com.zrong.Android.Listener.TabWidget;
import com.zrong.Android.Util.Music;
import com.zrong.Android.Util.SystemAPI;
import com.zrong.Android.View.FreshManLead;
import com.zrong.Android.View.SpriteView;
import com.zrong.Android.element.Task;
import com.zrong.Android.game.Connection;
import com.zrong.Android.game.ConstructData;
import com.zrong.Android.game.GameData;
import com.zrong.Android.game.GameDefinition;
import com.zrong.Android.online.network.GameProtocol;

public class TaskActivity extends GameActivity implements 
		TabWidget, OnTouchListener {
	String name = MainActivity.resources.getString(R.string.task_items);
    private String statName[] = name.split(",");
	//private String statName[] = { "可接受", "可领取", "未完成", "不可接受", "可提交", "未完成",
	//		"回答问题", "领取奖励", "奖励已领取", "任务已关闭" };
	private Button main;
	private Button extension;
	 private Button daily;
	public static TaskActivity mContext ;

	private ListView mainView;
	private ListView extensionView;
	private ListView dailyView;

	private SpriteView taskListArrows;

	private int select = 0;
	public static final byte[] taskDailyId={5,6,7,8,9,12};
	public static final String[] taskName = {"店铺","员工","商会","答题","交互","博士"};
	public static int itemID;

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		mContext = this;
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		this.setContentView(R.layout.task);

		main = (Button) this.findViewById(R.id.task_button_main);

		extension = (Button) this.findViewById(R.id.task_button_extensions);
		daily = (Button)this.findViewById(R.id.task_button_daily);

		main.setOnTouchListener(this);

		extension.setOnTouchListener(this);
		daily.setOnTouchListener(this);

		Button returnback = (Button) this
				.findViewById(R.id.task_button_returnback);

		Button cancel = (Button) this.findViewById(R.id.task_button_cancel);

		returnback.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {

				TaskActivity.this.finish();
			}

		}

		);

		cancel.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				TaskActivity.this.finish();
			}
		});

		this.setBackgroundResource(main);

		mainView = (ListView) this.findViewById(R.id.task_listview_main);
		

		mainView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				//zhouzhilong add ---新手引导
				if (GameData.isFreshMan) {
					if (FreshManLead.caseId == 4 || FreshManLead.caseId == 17
							&& position == 0) {
						Log.i("Log", "FreshManLead----caseId :"+FreshManLead.caseId);
						taskListArrows.setVisibility(View.GONE);
						FreshManLead.caseId++;
					}
				}

				Connection.sendMessage(
						GameProtocol.CONNECTION_SEND_TASK_DETAIL, ConstructData
								.getTaskData(GameData.task[1][position].id));

			}

		}

		);

		extensionView = (ListView) this
				.findViewById(R.id.task_listview_extension);

		extensionView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {

				Connection.sendMessage(
						GameProtocol.CONNECTION_SEND_TASK_DETAIL, ConstructData
								.getTaskData(GameData.task[2][position].id));

			}

		}

		);

		dailyView = (ListView) this.findViewById(R.id.task_listview_daily);

		dailyView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				/*Connection.sendMessage(
						GameProtocol.CONNECTION_SEND_TASK_DETAIL, ConstructData
								.getTaskData(GameData.task[position + 5][0].id));*/
				itemID=position;
				Intent intent = new Intent();
				Bundle bundle  = new Bundle();
				bundle.putInt("itemId", position);
				intent.putExtras(bundle);
				mContext.Activitychange(TaskListActivity.class, intent);

			}

		}

		);

		setSelectIndex(0);

		main.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {

				setSelectIndex(0);
			}
		}

		);

		extension.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {

				setSelectIndex(1);
			}
		});
		daily.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				
				setSelectIndex(2);
			}
		});

		// 新手引导任务指向
		if (GameData.isFreshMan) {
			if (FreshManLead.caseId == 4 || FreshManLead.caseId == 17) {
				Log.i("Log", "FreshManLead--Task--caseId :"+FreshManLead.caseId);
				taskListArrows = (SpriteView) this
						.findViewById(R.id.task_list_arrows);
//				taskListArrows.setId(1391);
				taskListArrows.setSeries(0);
				taskListArrows.setVisibility(View.VISIBLE);
			}
		}
		

	}

	public void Activitychange(Class calss, Intent intent) {

		if (intent == null) {
			intent = new Intent();
		}

		intent.setClass(this, calss);
//		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		
		this.startActivityForResult(intent, GameDefinition.REQTASK_DETAILS);

	}

	 
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		super.onActivityResult(requestCode, resultCode, data);
	}

	public void setSelect() {
		setSelectIndex(select);
	}

	public void setSelectIndex(int index) {
		select = index;
		mainView.setVisibility(View.GONE);
		extensionView.setVisibility(View.GONE);
		dailyView.setVisibility(View.GONE);

		if (index == 0)// 主线任务
		{
			mainView.setVisibility(View.VISIBLE);
			Task task[] = GameData.task[1];

			ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

			HashMap map = new HashMap<String, Object>();// ????

			String nametag = MainActivity.resources.getString(R.string.task_nametag);
			if(task !=null){
			for (int i = 0; i < task.length; i++) {
				map = new HashMap<String, Object>();// ????

				map.put("name",
						nametag + SystemAPI.getText(task[i].description));

				map.put("status", statName[task[i].status]);
				list.add(map);
				//Log.i("log1", statName[task[i].status]);
			}

			SimpleAdapter adapter = new SimpleAdapter(this, list,
					R.layout.task_item, new String[] { "name", "status" },
					new int[] { R.id.task_item_name, R.id.task_item_status });
			mainView.setAdapter(adapter);
			}
		} else if (index == 1) {
			extensionView.setVisibility(View.VISIBLE);
			Task task[] = GameData.task[2];

			ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

			HashMap map = new HashMap<String, Object>();

			String nametag = MainActivity.resources.getString(R.string.task_nametag);

			for (int i = 0; i < task.length; i++) {
				map = new HashMap<String, Object>();

				map.put("name",
						nametag + SystemAPI.getText(task[i].description));

				map.put("status", statName[task[i].status]);
				list.add(map);
			}

			SimpleAdapter adapter = new SimpleAdapter(this, list,
					R.layout.task_item, new String[] { "name", "status" },
					new int[] { R.id.task_item_name, R.id.task_item_status });
			extensionView.setAdapter(adapter);
		} else if (index == 2) {
			dailyView.setVisibility(View.VISIBLE);
			ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

			HashMap map = new HashMap<String, Object>();

			String nametag = MainActivity.resources.getString(R.string.task_nametag);
         if(GameData.taskGroup != null)
		        {
		        	for(int  i = 0;i < 6; i++ )
		        	{
		        		map = new HashMap<String, Object>();
		        		byte id=taskDailyId[i];
		        		int indx=SystemAPI.getByteArrayIndex(GameData.taskGroup, taskDailyId[i]);
		        		int finish=GameData.finish[indx];
		        		int giveward=GameData.giveward[indx];
		        		int maxGiveward=GameData.maxGiveward[indx];
		        		int taskSize=GameData.taskSize[indx];
		        		map.put("name", taskName[i]+"          "+"任务："+finish+"/"+taskSize);
		        		
//		        		Log.i("juj", ("第"+i+"组"+finish));
					//	Log.i("size", ("第"+i+"组数据大小"+GameData.task[i].length));
					//	Log.i("Log", ""+GameData.task[i][0].description);
						map.put("status", "加成："+giveward+"/"+maxGiveward);
						list.add(map);

		        		 
		        	}
		        }
/*		if(GameData.taskGroup != null){
    	for (int i = 5; i < 10; i++) {
				map = new HashMap<String, Object>();

				map.put("name",
						nametag
								+ SystemAPI
										.getText(GameData.task[i][0].description));
				map.put("name", GameData.task[i][0].description);
				Log.i("size", ("第"+i+"组数据大小"+GameData.task[i].length));
				map.put("status", statName[GameData.task[i][0].status]);
				list.add(map);
			}
			
			}*/

			SimpleAdapter adapter = new SimpleAdapter(this, list,
					R.layout.task_item, new String[] { "name", "status" },
					new int[] { R.id.task_item_name, R.id.task_item_status });
			dailyView.setAdapter(adapter);
		}
	}

	

	/* 重新激活 */
	protected void onResume() {
		
		
		if(FreshManLead.caseId == 19){
			doPromoter(FreshManLead.prompt18);
			FreshManLead.caseId ++;
		}
		super.onResume();
	}


	

	public boolean onTouch(View view, MotionEvent arg1) {

		if (view == main) {
			setBackgroundResource(main);
		} else if (view == extension) {
			setBackgroundResource(extension);
		}
		 else if(view == daily)
		 {
			 setBackgroundResource(daily);
		 }

		return false;
	}

	public void setBackgroundResource(Button button) {
		main.setBackgroundResource(R.drawable.button_task_main);
		extension.setBackgroundResource(R.drawable.button_task_extension);
		daily.setBackgroundResource(R.drawable.button_task_daily);

		if (button == main) {
			main.setBackgroundResource(R.drawable.button_task_main_pressed);
		} else if (button == extension) {
			extension
					.setBackgroundResource(R.drawable.button_task_extension_pressed);
		}
		 else if(button == daily)
		 {
		 daily.setBackgroundResource(R.drawable.button_task_daily_pressed);
		 }
	}
	
	
	// ================提示框
	private Handler endAnimationHandler = new Handler();

	// zhouzhilong add
	public void doPromoter(final String str) {
		new Thread() {
			public void run() {
				endAnimationHandler.postDelayed((new Runnable() {
					 
					public void run() {
						Log.i("Log", "doPromoter---");
						setText(str);
					}
				}), 100);
			};
		}.start();
	}

	private LinearLayout linearPromot;
	private TextView promot;

	// zhouzhilong add
	private void setText(String textStr) {

		Log.i("Log", "setText-CreateShop");
		linearPromot = (LinearLayout) this
				.findViewById(R.id.linearlayout_promotframe7);
		Button close = (Button) this.findViewById(R.id.close_promotframe7);
		promot = (TextView) this.findViewById(R.id.promotFrame7);
		if (textStr != null) {
			promot.setText(textStr);
		}
		close.setOnClickListener(new OnClickListener() {

			 
			public void onClick(View v) {
				Log.i("Log", "close---");
				linearPromot.setVisibility(View.GONE);
			}
		});

		linearPromot.setVisibility(View.VISIBLE);
		final View promotParent = (View) linearPromot.getParent();
		Animation anim = new TranslateAnimation(-promotParent.getWidth() * 3
				/ 2 + linearPromot.getWidth() / 2, 0.0f, 0.0f, 0.0f);
		anim.setDuration(1500);
		anim.setStartOffset(300);
		anim.setFillAfter(false);
		anim.setInterpolator(this, android.R.anim.overshoot_interpolator);
		linearPromot.startAnimation(anim);

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
