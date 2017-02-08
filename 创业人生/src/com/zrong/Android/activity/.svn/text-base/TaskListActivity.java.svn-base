package com.zrong.Android.activity;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.zrong.Android.Util.Music;
import com.zrong.Android.element.Task;
import com.zrong.Android.game.Connection;
import com.zrong.Android.game.ConstructData;
import com.zrong.Android.game.GameData;
import com.zrong.Android.online.network.GameProtocol;

public class TaskListActivity extends GameActivity{
	  public static  TaskListActivity mContext=null;
	  ArrayList<HashMap<String, Object>> list;
	  private ListView lv;
	  private int itemId ;
	String name = MainActivity.resources.getString(R.string.task_items);
	private String statName[] = name.split(",");
	 
	protected void onCreate(Bundle savedInstanceState) {	
		super.onCreate(savedInstanceState);
		
		Bundle bundle = getIntent().getExtras();
		if(bundle == null){
			return ;
		}
		//final int itemId = bundle.getInt("itemId");
		itemId = bundle.getInt("itemId");
		
		mContext = this;
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		this.setContentView(R.layout.tasklist);
		
		  Button returnback = (Button) this.findViewById(R.id.tasklist_button_returnback);
          Button cancel = (Button) this.findViewById(R.id.tasklist_button_cancel);
          lv = (ListView)this.findViewById(R.id.tasklist_listview_daily);

          returnback.setOnClickListener(new OnClickListener() {

	           public void onClick(View arg0) {
		     TaskListActivity.this.finish();
	}

}

);

        cancel.setOnClickListener(new OnClickListener() {
	         public void onClick(View arg0) {
		TaskListActivity.this.finish();
	         }
        });
       update();

        lv.setOnItemClickListener(new OnItemClickListener() {

			 
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				if(itemId==5){
				Connection.sendMessage(
						GameProtocol.CONNECTION_SEND_TASK_DETAIL, ConstructData
								.getTaskData(GameData.task[TaskActivity.itemID+7][position].id));
				}else{
					Connection.sendMessage(
							GameProtocol.CONNECTION_SEND_TASK_DETAIL, ConstructData
									.getTaskData(GameData.task[TaskActivity.itemID+5][position].id));
				}
			}
		});
	}
public void update(){
    list = new ArrayList<HashMap<String, Object>>();

	HashMap map ;
	int len = 0;
	if( itemId == 5){
	//	itemId=7;	
		len = GameData.task[itemId+ 7].length;
		
	}else{
		len = GameData.task[itemId+5].length;
	}
	
	for(int i=5;i<5+len;i++){
		map = new HashMap<String, Object>();			
		if(itemId==5){
			map.put("name", GameData.task[itemId+7][i-5].description);			
			map.put("state", statName[GameData.task[itemId+7][i-5].status]);
		}else{
			map.put("name", GameData.task[itemId+5][i-5].description);			
			map.put("state", statName[GameData.task[itemId+5][i-5].status]);
		}
		list.add(map);
	}
	SimpleAdapter adapter = new SimpleAdapter(this, list,
			R.layout.tasklist_item, new String[] { "name", "state" },
			new int[] { R.id.tasklist_item_name, R.id.tasklist_item_state });
	lv.setAdapter(adapter);
    	
}




public void Activitychange(Class calss, Intent intent) {
	// TODO Auto-generated method stub
	if (intent == null) {
		intent = new Intent();
	}
	intent.setClass(TaskListActivity.this, calss);
	
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
