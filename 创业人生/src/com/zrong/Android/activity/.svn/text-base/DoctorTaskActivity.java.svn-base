package com.zrong.Android.activity;

import java.util.ArrayList;
import java.util.HashMap;

import com.zrong.Android.Util.Music;
import com.zrong.Android.Util.SystemAPI;
import com.zrong.Android.element.Task;
import com.zrong.Android.game.Connection;
import com.zrong.Android.game.ConstructData;
import com.zrong.Android.game.GameData;
import com.zrong.Android.game.GameDefinition;
import com.zrong.Android.online.network.GameProtocol;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class DoctorTaskActivity extends GameActivity{
	public static DoctorTaskActivity mContext = null;
	private ListView lv;
	String name = MainActivity.resources.getString(R.string.task_items);
    private String statName[] = name.split(",");
    Task task[] ;
	 
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//task = GameData.task[11];
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		mContext = this;
		setContentView(R.layout.doctor_task);
		lv = (ListView)findViewById(R.id.doctortask_listview);
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			 
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				//发包操作；
				Connection.sendMessage(
						GameProtocol.CONNECTION_SEND_TASK_DETAIL, ConstructData
								.getTaskData(GameData.task[11][position].id));
			}
		});
		setview();
		Button turnback = (Button)findViewById(R.id.doctor_button_returnback);
		Button cancel = (Button)findViewById(R.id.doctor_button_cancel);
		Button info = (Button)findViewById(R.id.doctor_button_info);
		Button custody = (Button)findViewById(R.id.doctor_button_custody);
		Button shop = (Button)findViewById(R.id.doctor_button_shop);
		turnback.setOnClickListener(new Button.OnClickListener(){

			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				DoctorTaskActivity.mContext.finish();
			}});
		cancel.setOnClickListener(new Button.OnClickListener(){
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				DoctorTaskActivity.mContext.finish();
			}});
		info.setOnClickListener(new Button.OnClickListener(){
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mContext.Activitychange(DoctorInfoActivity.class, null);
				DoctorTaskActivity.mContext.finish();
			}});
		custody.setOnClickListener(new Button.OnClickListener(){
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mContext.Activitychange(DoctorCustodyActivity.class, null);
				DoctorTaskActivity.mContext.finish();
			}});
		shop.setOnClickListener(new Button.OnClickListener(){
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//发包请求图片数据，然后在收包处，跳转界面
				mContext.Activitychange(DoctorShopActivity.class, null);
				DoctorTaskActivity.mContext.finish();
			}});
		
	}
	public void setview()
	{   task = GameData.task[11];
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		HashMap<String, Object> map ;
		String nametag = MainActivity.resources.getString(R.string.task_nametag);
		if(task==null){
			return;
		}else{
		for (int i = 0; i < task.length; i++) {
			map = new HashMap<String, Object>();// ????

			map.put("name",
					nametag + SystemAPI.getText(task[i].description));

			map.put("state", statName[task[i].status]);
			list.add(map);
			Log.i("log2",SystemAPI.getText(task[0].description)+":"+ statName[task[0].status]);
		}
		
		SimpleAdapter listaAdapter = new SimpleAdapter(this, list, R.layout.doctortask_item, 
				                                       new String[] {"name","state"}, new int[]{R.id.task_name,R.id.task_state});
		lv.setAdapter(listaAdapter);
		}
	}
	
	
	

	public void Activitychange(Class calss, Intent intent) {

		if (intent == null) {
			intent = new Intent();
		}

		intent.setClass(this, calss);
		
		this.startActivityForResult(intent, GameDefinition.REQTASK_DETAILS);

	}
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		super.onActivityResult(requestCode, resultCode, data);
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
