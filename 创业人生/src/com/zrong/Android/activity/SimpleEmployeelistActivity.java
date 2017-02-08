package com.zrong.Android.activity;

import java.util.ArrayList;
import java.util.HashMap;

import com.zrong.Android.Util.EmployeeItemAdapter;
import com.zrong.Android.element.Employee;
import com.zrong.Android.game.Connection;
import com.zrong.Android.game.ConstructData;
import com.zrong.Android.game.GameData;
import com.zrong.Android.online.network.GameProtocol;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

public class SimpleEmployeelistActivity extends GameActivity{
	public static SimpleEmployeelistActivity mContext = null;
	String nametag = MainActivity.resources
	.getString(R.string.diglist_name);
String leveltag = MainActivity.resources
	.getString(R.string.diglist_rate);
String contract = MainActivity.resources
	.getString(R.string.employeelist_contract);
String sectiontag = MainActivity.resources
	.getString(R.string.employeelist_dept);
String loyaltytag = MainActivity.resources
	.getString(R.string.diglist_loyalty);
	/** 
	 * ´ÓÄÄÀ´µÄ
	 */
	private String where;
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		
		mContext = this;
		where = getIntent().getStringExtra("where") ;
		if(where == null)where ="";
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.simple_employeelist);
		
		Button cancle = (Button) this.findViewById(R.id.simple_employeelist_button_cancel);

		cancle.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View arg0) 
			{
				SimpleEmployeelistActivity.this.finish();
			}
		});
		
		ListView employeeList = (ListView) this.findViewById(R.id.simple_employeelist_listview);
		
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		
		final Employee[] employee = GameData.corporation.employee;
		
		HashMap<String,Object> map = null;
		for (int i = 0; i < employee.length; i++) 
		{
			map = new HashMap<String, Object>();
			map.put("name", nametag + employee[i].name);
			map.put("level", leveltag + employee[i].level);
			map.put("contract",
					contract
							+ Long.toString((employee[i].compact) / 24)
							+ MainActivity.resources
									.getString(R.string.employeelist_day));
			map.put("section", sectiontag + employee[i].departName);
			map.put("loyalty", loyaltytag + employee[i].loyalty);
			list.add(map);
		}

		EmployeeItemAdapter adpter = new EmployeeItemAdapter(
				SimpleEmployeelistActivity.this, list, R.layout.simple_employeelist_item,
				new String[] { "name", "level", "contract", "section",
						"loyalty",  }, new int[] {
						R.id.simple_employeelist_item_name,
						R.id.simple_employeelist_item_level,
						R.id.simple_employeelist_item_contract,
						R.id.simple_employeelist_item_section,
						R.id.simple_employeelist_item_loyalty,
						 });

		employeeList.setAdapter(adpter);
		
		employeeList.setOnItemClickListener(new OnItemClickListener()
		{

			 
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) 
			{
				Connection.sendMessage(GameProtocol.DoctorTrust, ConstructData.getDoctorTrustData((byte)GameData.trustType[DoctorCustodyActivity.ID], (short)GameData.trustId[DoctorCustodyActivity.ID], (short)0, (byte)1, (byte)2, (byte)1,new long[]{employee[position].id}));
			}
		}
		
		);
	}
	 
	public GameActivity getGameContext() {
		// TODO Auto-generated method stub
		return this;
	}
	
	 
	public void finish() {
		
		mContext = null;
		super.finish();
	}
	
	

}
