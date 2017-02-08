package com.zrong.Android.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zrong.Android.Listener.DataChangeListener;
import com.zrong.Android.element.Department;
import com.zrong.Android.game.Connection;
import com.zrong.Android.game.ConstructData;
import com.zrong.Android.game.GameData;
import com.zrong.Android.online.network.GameProtocol;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class DepartmentActivity extends GameActivity implements
		DataChangeListener
{
	/** 当前 */
	public static DepartmentActivity mContext;
	/** 监听 */
	public buttonLinster linster;
	/**
	 * 当前界面flipper 当期界面停留项
	 * */
	public ViewFlipper flipper;
	public int currentIndex;
	/** 返回按钮 */
	public Button back;
	/** 人事部 ，公关部 ，财务部，策划部 */
	public Button department[];
	/** 升级，任命，招聘 ，使用策略 ，常务 */
	public Button button[][];
	/**
	 * 部门 名称 首席职称 部门职称级别 0普通职员或店员;1主管或店长;2助理;3经理;4总监;5首席
	 * */
	public int dapartmentName[] =
	{ R.string.diglist_rate, R.string.department_peoplenum, 0,
			R.string.department_director, R.string.department_manager,
			R.string.department_assistant, R.string.department_competent };
	public int departmentChief[] =
	{ R.string.department_ceo, R.string.department_marcom_ceo,
			R.string.department_finance_dept_ceo_1,
			R.string.department_finance_dept_ceo };
	public int departmentDuty[] =
	{ 0, 0, 5, 4, 3, 2, 1 };
	/**
	 * 招聘 文字
	 * 快速招聘 人才市场  猎头中心
	 * */
	public int employType[] = {R.string.employeelist_fasthire,R.string.diglist_talentmarket,R.string.diglist_huntingcenter};
	/**
	 * 常务文字
	 * 交租 纳税 发薪 
	 * **/
	public int routineType[] = {R.string.department_concerning,R.string.department_tax,R.string.department_paid};
	/**
	 * 策划部 文字
	 * 诱导喝酒 蜚语之谋 非议示众 店铺狙击 意外背后 健身伙伴
	 * **/
	public int policyType[] = {R.string.policy_1,R.string.policy_2,R.string.policy_3,R.string.policy_4,R.string.policy_5,R.string.policy_6};
	
	/**
	 * 界面对象及id
	 * */
	public ListView listView[] = new ListView[4];
	public int listViewId[] =
	{ R.id.listView0, R.id.listView1, R.id.listView2, R.id.listView3 };

//	public Button ceshi;
	Department department_relation;
	Department[] departmentAll;

	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mContext = this;
		setContentView(R.layout.department);
		linster = new buttonLinster();

		flipper = (ViewFlipper) findViewById(R.id.flipper);
		/** 返回按键 */
		back = (Button) findViewById(R.id.commerceinfo_cancel);
		/**
		 * 初始化按钮 并 添加 监听 部门按钮 人事部，公关部，财务部，策划部
		 * */
		department = new Button[4];
		department[0] = (Button) findViewById(R.id.personnel);
		department[1] = (Button) findViewById(R.id.relation);
		department[2] = (Button) findViewById(R.id.finance);
		department[3] = (Button) findViewById(R.id.strategy);
		for (int i = 0; i < department.length; i++)
		{
			department[i].setOnClickListener(linster);
		}
		/**
		 * 初始化下部按钮 并 添加 监听 升级，任命，招聘 ，使用策略 ，常务
		 * */
		initButton();
		
		/** 设置默认选中项是第0个 */
		setButtonSelection(0);

		back.setOnClickListener(linster);

//		ceshi = (Button) findViewById(R.id.button7);
//		ceshi.setOnClickListener(linster);
	}
	
	/**
	 * 初始化所有的按键button
	 * */
	public void initButton()
	{
		button = new Button[4][5];
		
		button[0][0] = (Button) findViewById(R.id.button0_1);
		button[0][1] = (Button) findViewById(R.id.button0_2);
		button[0][2] = (Button) findViewById(R.id.button0_3);
		button[0][3] = (Button) findViewById(R.id.button0_4); 
		
		button[1][0] = (Button) findViewById(R.id.button1_1);
		button[1][1] = (Button) findViewById(R.id.button1_2);
		button[1][2] = (Button) findViewById(R.id.button1_3);
		button[1][3] = (Button) findViewById(R.id.button1_4); 
		
		button[2][0] = (Button) findViewById(R.id.button2_1);
		button[2][1] = (Button) findViewById(R.id.button2_2);
		button[2][2] = (Button) findViewById(R.id.button2_3);
		button[2][3] = (Button) findViewById(R.id.button2_4); 
		button[2][4] = (Button) findViewById(R.id.button2_5); 

		button[3][0] = (Button) findViewById(R.id.button3_1);
		button[3][1] = (Button) findViewById(R.id.button3_2);
		button[3][2] = (Button) findViewById(R.id.button3_3);
		button[3][3] = (Button) findViewById(R.id.button3_4); 
		button[3][4] = (Button) findViewById(R.id.button3_5);

		for (int i = 0; i < button.length; i++)
		{
			for(int j = 0;j<button[i].length;j++)
			{
				if(button[i][j] != null)
				button[i][j].setOnClickListener(linster);
			}
		}
	}
	
	/**
	 * 初始化界面列表
	 * */
	public void initDepartment(int type)
	{
		final int index = type;
		departmentAll = GameData.corporation.department;
		department_relation = departmentAll[type];
		listView[index] = (ListView) findViewById(listViewId[index]);
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map;
		{
			for (int i = 0; i < dapartmentName.length; i++)
			{
				map = new HashMap<String, String>();

				String name = "";

				if (i < 2)
				{
					name = ""
							+ (i == 0 ? departmentAll[type].level
									: departmentAll[type].employees.length);
				} else
				{
					if (i == 2)
						dapartmentName[i] = departmentChief[type];

					name = GameData.getEmployeeNameByDuty(departmentDuty[i],
							departmentAll[type].id, MainActivity.resources
									.getString(R.string.department_vacancy));
				}
				map.put("name",
						MainActivity.resources.getString(dapartmentName[i])
								+ name);
				list.add(map);
			}
		}
		;
		SimpleAdapter adapter = new SimpleAdapter(this, list,
				R.layout.department_item, new String[]
				{ "name" }, new int[]
				{ R.id.departemtnItem });
		listView[index].setAdapter(adapter);
		/** 添加 选中效果 */
		/*
		 * DepartmentAdapter adapter = new DepartmentAdapter(this, list,
		 * R.layout.department_item1, new String[]{"name"}, new int[]
		 * {R.id.departemtnItem}); listView[index].setAdapter(adapter);
		 * listView[index].setSelected(true);
		 * listView[index].setOnItemClickListener(new
		 * AdapterView.OnItemClickListener() {
		 * 
		 * public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
		 * long arg3) { DepartmentAdapter adapter =
		 * (DepartmentAdapter)listView[index].getAdapter();
		 * if(adapter.isCheck(arg2)) { adapter.setCheck(arg2, false,true); }else
		 * { adapter.setCheck(arg2, true,true); }
		 * adapter.notifyDataSetChanged();
		 * 
		 * } });
		 */

	}

	/**
	 * 设置当前选中项
	 * */
	public void setButtonSelection(int index)
	{
		for (int i = 0; i < department.length; i++)
		{
			if (i == index)
			{
				department[i]
						.setBackgroundResource(R.drawable.selection_selected);
				flipper.setDisplayedChild(i);
				initDepartment(i);
				currentIndex = i;
			} else
			{
				department[i].setBackgroundResource(R.drawable.selection);
			}
		}
	}

	protected void onStart()
	{
		// TODO Auto-generated method stub
		super.onStart();
	}

	public void finish()
	{
		// TODO Auto-generated method stub
		super.finish();
		mContext = null;
	}

	public GameActivity getGameContext()
	{

		return mContext;
	}

	/** 屏幕提示 */
	public void Display(String str)
	{
		Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
	}

	/** 界面切换 */
	public void Activitychange(Class cclass, Intent intent)
	{
		if (intent == null)
		{
			intent = new Intent();
		}
		intent.setClass(DepartmentActivity.this, cclass);
		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		this.startActivity(intent);
	}

	/** 按键统一处理 */
	public class buttonLinster implements OnClickListener
	{

		public void onClick(View v)
		{
			int id = v.getId();
			switch (id)
			{
			case R.id.commerceinfo_cancel:
				finish();
				break;
			case R.id.personnel:// 人事部
				setButtonSelection(0);
				break;
			case R.id.relation:// 公关部
				setButtonSelection(1);
				break;
			case R.id.finance:// 财务部
				setButtonSelection(2);
				break;
			case R.id.strategy:// 策划部
				setButtonSelection(3);
				break;
			case R.id.button0_1:// 升级 
			case R.id.button1_1:
			case R.id.button2_1:
			case R.id.button3_1:
				if (department_relation != null)
					Connection.sendMessage(
							GameProtocol.CONNECTION_REQ_LEVELUP_VIEW,
							ConstructData.getLevelUPInfo((byte) 1,
									department_relation.id));
				break;
			case R.id.button0_2:// 任命
			case R.id.button1_2:
			case R.id.button2_2:
			case R.id.button3_2:
				appointDialog();
				break;
			case R.id.button0_3:// 卸任
			case R.id.button1_3:
			case R.id.button2_3:
			case R.id.button3_3:
				deployDialog();
				break;
			case R.id.button0_4:// 招聘 
			case R.id.button1_4:
			case R.id.button2_4:
			case R.id.button3_4:
				employDialog();
				break;
			case R.id.button2_5:// 常务
				RoutineDialog();
				break;
			case R.id.button3_5:// 策划 
				if (DepartmentActivity.mContext != null)
				{ 
					/*
					 * 日后去掉	
					MainActivity.mContext.Activitychange(
							PolicyActivity.class, null);*/
					mContext.Activitychange(PlanningDeptActivity.class, null);
				}
				break;
			/*case R.id.button7://
				if (DepartmentActivity.mContext != null)
				{
//					MainActivity.mContext.Activitychange(
//							ChatWindowActivity.class, null);
					MainActivity.mContext.Activitychange(
							PromotersActivity.class, null);
				}
				break;*/
			}
		}
	}


	public void OnDataChange(Bundle bundle)
	{
	}

	public void updata()
	{
		initDepartment(currentIndex);
	}
	
	/**
	 * 任命弹出框
	 * */
	public void appointDialog()
	{
		currentIndex = flipper.getDisplayedChild();

		final AlertDialog dialog = new AlertDialog.Builder(
				DepartmentActivity.this).create();
		dialog.show();
		dialog.getWindow().setContentView(R.layout.mapmenu3_list);
		ListView listView = (ListView) dialog.findViewById(R.id.mapmenu3_list);
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < 5; i++)
		{
			HashMap<String, Object> map = new HashMap<String, Object>();
			if (i == 0)
				dapartmentName[2] = departmentChief[currentIndex];

			String name = MainActivity.resources
					.getString(dapartmentName[i + 2]);
			name = name
					+ GameData.getEmployeeNameByDuty(departmentDuty[i + 2],
							departmentAll[currentIndex].id,
							MainActivity.resources
									.getString(R.string.department_vacancy));
			map.put("name", name);
			map.put("duty", departmentDuty[i + 2]);

			list.add(map);
		}
		SimpleAdapter adapter = new SimpleAdapter(DepartmentActivity.this,
				list, R.layout.mapmenu3_item, new String[]
				{ "name" }, new int[]
				{ R.id.mapmenu3_list_text });
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener()
		{

			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3)
			{

				dialog.dismiss();
				/**
				 * 获取数据
				 * */
				HashMap map = (HashMap) arg0.getAdapter().getItem(position);
				int id = Integer.parseInt(map.get("duty").toString());

				peopleDialog(id);
			}
		});
	}

	/**
	 * 候选人名店列表
	 * */
	public void peopleDialog(int Index)
	{
		final int selectIndex = Index;
		String name[] = new String[department_relation.employees.length];
		if (name.length <= 0)
		{
			Display(MainActivity.resources.getString(R.string.ttoast1));
			return;
		}

		List<HashMap<String, Object>> list_emp = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < name.length; i++)
		{
			/**
			 * 当员工有职位时 不参与
			 * */
			if (department_relation.employees[i].duty > 0)
			{
				break;
			}
			name[i] = department_relation.employees[i].name;
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("name", name[i]);
			map.put("id", department_relation.employees[i].id);
			map.put("duty", department_relation.employees[i].duty);
			list_emp.add(map);
		}
		if (list_emp.size() > 0)
		{
			final AlertDialog dialog = new AlertDialog.Builder(
					DepartmentActivity.this).create();
			dialog.show();
			dialog.getWindow().setContentView(R.layout.pm_emoloyeelist);
			ListView listView = (ListView) dialog
					.findViewById(R.id.pm_emoloyeelist_listview);
			SimpleAdapter adapter = new SimpleAdapter(
					DepartmentActivity.this, list_emp,
					R.layout.pm_employeelist_item, new String[]
					{ "name", "id", "duty" }, new int[]
					{ R.id.pm_employeelist_item_name });
			listView.setAdapter(adapter);
			listView.setOnItemClickListener(new OnItemClickListener()
			{

				public void onItemClick(AdapterView<?> arg0, View arg1,
						int position, long arg3)
				{
					dialog.dismiss();
					/**
					 * 获取数据
					 * */
					HashMap map = (HashMap) arg0.getAdapter().getItem(position);
					int id = Integer.parseInt(map.get("id").toString());

					Connection.sendMessage(
							GameProtocol.CONNECTION_SEND_Staff_DUTY_CHANGE_REQ,
							ConstructData.getStaffDutyChange(id,
									(byte) selectIndex));
				}
			});
		} else
		{
			Display(MainActivity.resources.getString(R.string.ttoast1));
			return;
		}
	}

	/**
	 * 卸任弹出框
	 * */
	public void deployDialog()
	{

		currentIndex = flipper.getDisplayedChild();
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		for(int i=0;i<5;i++)
		{
			if (i == 0)
				dapartmentName[2] = departmentChief[currentIndex];
			
			String name = GameData.getEmployeeNameByDuty(departmentDuty[i + 2],
					departmentAll[currentIndex].id,
					MainActivity.resources
							.getString(R.string.department_vacancy));
			
			if(!name.endsWith(MainActivity.resources.getString(R.string.department_vacancy)))
			{
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("name", MainActivity.resources
						.getString(dapartmentName[i + 2])+name);
				map.put("id", GameData.getEmployeeByDuty(departmentDuty[i + 2],
						departmentAll[currentIndex].id).id);
//				map.put("duty", departmentDuty[i+2]);
				list.add(map);
			} 
		}
		if(list.size()>0)
		{
			final AlertDialog dialog = new AlertDialog.Builder(DepartmentActivity.this).create();
			dialog.show();
			dialog.setContentView(R.layout.mapmenu3_list);
			ListView listView = (ListView)dialog.findViewById(R.id.mapmenu3_list);
			SimpleAdapter adapter = new SimpleAdapter(DepartmentActivity.this, list, R.layout.mapmenu3_item, new String[]{"name","id","duty"}, new int[]{R.id.mapmenu3_list_text});
			listView.setAdapter(adapter);
			listView.setOnItemClickListener(new OnItemClickListener()
			{

				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id)
				{
					HashMap map = (HashMap)parent.getAdapter().getItem(position);
					int empId = Integer.parseInt(map.get("id").toString());
//					int duty = Integer.parseInt(map.get("duty").toString());
					Connection.sendMessage(GameProtocol.CONNECTION_SEND_Staff_DUTY_CHANGE_REQ,
							 ConstructData.getStaffDutyChange(empId, (byte)0));
					dialog.dismiss();
				}
				
			});
		}else
		{
			Display(MainActivity.resources.getString(R.string.ttoast1));
			return;
		}
		
	}

	/**
	 * 招聘弹出框
	 * */
	public void employDialog()
	{
		List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		for(int i=0;i<3;i++)
		{
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("name", MainActivity.resources.getString(employType[i]));
			list.add(map);
		}
		final AlertDialog dialog = new AlertDialog.Builder(DepartmentActivity.this).create();
		dialog.show();
		dialog.setContentView(R.layout.mapmenu3_list);
		ListView listView = (ListView)dialog.findViewById(R.id.mapmenu3_list);
		SimpleAdapter adapter = new SimpleAdapter(DepartmentActivity.this, list, R.layout.mapmenu3_item, new String[]{"name"}, new int[]{R.id.mapmenu3_list_text});
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener()
		{

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id)
			{
				dialog.dismiss();
				switch(position)
				{
				case 0://快速招聘
					Connection
					.sendMessage(
							GameProtocol.CONNECTION_REQ_EMPLOYMENT_STAFF_BATCH,
							ConstructData.getEmployStaffBatch(
									(byte) 0, new long[] { 0 }));
					break;
				case 1://人才市场 
					Intent intent = new Intent();
					
					Bundle bundle = new Bundle();

					bundle.putByte("type", (byte) 1);

					bundle.putString("staffname", "");

					bundle.putString("mastername", "");

					bundle.putByte("size", (byte) 1);

					bundle.putByteArray("office", new byte[] { 0 });

					bundle.putByteArray("tType", new byte[] { 0 });

					bundle.putByteArray("level", new byte[] { 0 });

					bundle.putInt("begin", 0);

					bundle.putInt("count", 50);

					intent.putExtras(bundle);

					intent.setClass(DepartmentActivity.this,
							DiglistActivity.class);
					
					DepartmentActivity.this.startActivity(intent);

					Connection.sendMessage(
							GameProtocol.REQSearchStaff_Req,
							ConstructData.SearchStaff_Req((byte) 1, "",
									"", (byte) 1, new byte[] { 0 },
									new byte[] { 0 }, new byte[] { 0 },
									0, 50));
					break;
				case 2://猎头中心
					if (DepartmentActivity.mContext == null)
					{
						MainActivity.mContext.Activitychange(
								SearchconditionActivity.class, null);
					}
					break;
				}
			}
		});
	}

	/**
	 * 常务
	 * */
	public void RoutineDialog()
	{
		List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		for(int i=0;i<3;i++)
		{
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("name", MainActivity.mContext.getString(routineType[i]));
			list.add(map);
		}
		final AlertDialog dialog = new AlertDialog.Builder(DepartmentActivity.this).create();
		dialog.show();
		dialog.setContentView(R.layout.mapmenu3_list);
		ListView listView = (ListView)dialog.findViewById(R.id.mapmenu3_list);
		SimpleAdapter adapter = new SimpleAdapter(DepartmentActivity.this, list, R.layout.mapmenu3_item, new String[]{"name"}, new int[]{R.id.mapmenu3_list_text});
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener()
		{

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id)
			{
				dialog.dismiss();
				Connection.sendMessage(GameProtocol.CONNECTION_SEND_FINANCE,new byte[]{(byte)(position+1)});
			}
		});
	}

	/**
	 * 策划部弹出框
	 * */
	/*public void PolicyDialog()
	{
		List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		for(int i=0;i<policyType.length;i++)
		{
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("name", MainActivity.mContext.getString(policyType[i]));
			list.add(map);
		}
		final AlertDialog dialog = new AlertDialog.Builder(DepartmentActivity.this).create();
		dialog.show();
		dialog.setContentView(R.layout.mapmenu3_list);
		ListView listView = (ListView)dialog.findViewById(R.id.mapmenu3_list);
		SimpleAdapter adapter = new SimpleAdapter(DepartmentActivity.this, list, R.layout.mapmenu3_item, new String[]{"name"}, new int[]{R.id.mapmenu3_list_text});
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener()
		{

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id)
			{
				switch(position)
				{
				case 0:
					break;
				case 1:
					break;
				case 2:
					break;
				case 3:
					break;
				case 4:
					break;
				case 5:
					break;
				}
				Display("按下了"+position);
				dialog.dismiss();
			}
		});
	}
*/






}
