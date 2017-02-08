package com.zrong.Android.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Gallery;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.zrong.Android.Listener.ActivityTransform;
import com.zrong.Android.Listener.DataChangeListener;
import com.zrong.Android.Util.EmployeeItemAdapter;
import com.zrong.Android.Util.GridsimpleImageAdapter;
import com.zrong.Android.Util.Music;
import com.zrong.Android.Util.ShopItemAdapter;
import com.zrong.Android.View.FreshManLead;
import com.zrong.Android.View.SpriteView;
import com.zrong.Android.element.Branch;
import com.zrong.Android.element.Employee;
import com.zrong.Android.element.Props;
import com.zrong.Android.game.Connection;
import com.zrong.Android.game.ConstructData;
import com.zrong.Android.game.GameData;
import com.zrong.Android.game.GameDefinition;
import com.zrong.Android.online.network.GameProtocol;

public class EmployeeListActivity extends GameActivity implements
		 DataChangeListener {
	
	public static final String routin_menu =String.valueOf(MainActivity.resources.getString(R.string.routine_menu1)) ;
	public static final String menu[] = routin_menu.split(",");
	private TextView  routin_desc;
	private Button confirm;
	public static int p=0;
	private ListView emplistview;

	private TextView employeeNum;
	
	private String[] myGoodsForEmployee;

	public static EmployeeListActivity mContext = null;

	private SpriteView recruitmentArrows;
	
	private boolean where = false;
	
	private Button look,employee_routine,deploy,employ/*,train*/;

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		mContext = this;
		where = getIntent().getStringExtra("where") != null ;

		getWindow().requestFeature(Window.FEATURE_NO_TITLE);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		this.setContentView(R.layout.employeelist);
		
		Button title = (Button) this
		.findViewById(R.id.employeelist_button_title);
//		title.setTypeface(GameDefinition.face);

		init();
		
		
		//默认选中第一个人
		EmployeeItemAdapter adapter = (EmployeeItemAdapter)emplistview.getAdapter();
		
		if(adapter.getCount() > 0)
		{
			adapter.setCheck(0, true);
			adapter.notifyDataSetChanged();
		}
	}
	/**
	 * 
	 * @return
	 */
	public int[] getCheckIndex()
	{
		EmployeeItemAdapter adpter = (EmployeeItemAdapter) emplistview.getAdapter();
		
		Vector v = new Vector();
		
		for(int i =0; i < adpter.getCount(); i++)
		{
			if(adpter.isCheck(i))
			{
				v.addElement(i);
			}
		}
		
		int [] idx = new int[v.size()];
		
		for(int i =0; i < v.size(); i++)
		{
			idx[i] = Integer.parseInt(String.valueOf(v.elementAt(i)));
		}
		
		return idx;
	}
	
	/**
	 * 
	 */
	public void checkButton()
	{
		int [] idx = getCheckIndex();
		
		if(idx!= null&& idx.length>0)
		{
			if(idx.length > 1)
			{
				look.setVisibility(View.GONE);
				employee_routine.setVisibility(View.VISIBLE);
				deploy.setVisibility(View.GONE);
				employ.setVisibility(View.VISIBLE);
				
//				train.setVisibility(View.VISIBLE);
			}
			else
			{
				look.setVisibility(View.VISIBLE);
				employee_routine.setVisibility(View.VISIBLE);
				deploy.setVisibility(View.VISIBLE);
				employ.setVisibility(View.VISIBLE);
				
//				train.setVisibility(View.VISIBLE);
			}
		}
		else
		{
			look.setVisibility(View.GONE);
			employee_routine.setVisibility(View.GONE);
			deploy.setVisibility(View.GONE);
			employ.setVisibility(View.VISIBLE);
			
//			train.setVisibility(View.GONE);
		}
	}
	
	

	private void init() {

		Button returnback = (Button) this
				.findViewById(R.id.employeelist_button_returnback);

		Button cancle = (Button) this
				.findViewById(R.id.employeelist_button_cancel);

		returnback.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				EmployeeListActivity.this.finish();
			}

		});

		cancle.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				EmployeeListActivity.this.finish();
			}

		});

		emplistview = (ListView) findViewById(R.id.employeelist_listview);

		final Employee employee[] = GameData.corporation.employee;

		GameData.corporation.addDataChangeListener(this);

		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		HashMap map = null;

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
				EmployeeListActivity.this, list, R.layout.employeelist_item,
				new String[] { "name", "level", "contract", "section",
						"loyalty",  }, new int[] {
						R.id.employeelist_item_name,
						R.id.employeelist_item_level,
						R.id.employeelist_item_contract,
						R.id.employeelist_item_section,
						R.id.employeelist_item_loyalty,
						 });

		emplistview.setAdapter(adpter);

//		adpter.setOnItemButtonClickListener(new AdapterView.OnItemClickListener() {
//
//			public void onItemClick(AdapterView<?> parent, View v,
//					int position, long id) {
//				if (v instanceof CheckBox)// 选中checkbox
//				{
//					Vector vector = new Vector(5, 5);
//					for (int i = 0; i < emplistview.getChildCount(); i++) {
//						CheckBox check = (CheckBox) emplistview.getChildAt(i)
//								.findViewById(R.id.employeelist_item_checkbox);
//
//						if (check.isChecked()) 
//						{
//							vector.addElement(i);
//						}
//					}
//					
//					
//					if (vector.size() > GameData
//							.getDepartment(Branch.PERSONNAL).employees.length) {
//						CheckBox check = (CheckBox) emplistview.getChildAt(
//								position).findViewById(
//								R.id.employeelist_item_checkbox);
//						check.setChecked(false);
//
//					}
//				} else if (v instanceof Button) {
//					if (employee[position].department <= 0)// 调配
//					{
//						String section[] = new String[] {
//								MainActivity.resources
//										.getString(R.string.employeelist_dept),
//								MainActivity.resources
//										.getString(R.string.employeelist_shop),
//								MainActivity.resources
//										.getString(R.string.employeelist_building) };
//
//						Builder builder = new AlertDialog.Builder(
//								EmployeeListActivity.this);
//
//						final long eid = employee[position].id;
//						builder.setItems(section,
//								new DialogInterface.OnClickListener() {
//									public void onClick(DialogInterface dialog,
//											int index) {
//										if (index == 0)// 部门
//										{
//											Builder builder = new AlertDialog.Builder(
//													EmployeeListActivity.this);
//
//											String department[] = new String[GameData.corporation.department.length];
//
//											for (int i = 0; i < department.length; i++) {
//												department[i] = GameData.corporation.department[i].name;
//											}
//
//											builder.setItems(
//													department,
//													new DialogInterface.OnClickListener() {
//
//														public void onClick(
//																DialogInterface dialog,
//																int index) {
//
//															Connection
//																	.sendMessage(
//																			GameProtocol.CONNECTION_SEND_STAFF_OP_REQ,
//																			ConstructData
//																					.Staff_Appoint_Req(
//																							(byte) 0,
//																							GameData.corporation.department[index].id,
//																							GameData.corporation.department[index].type,
//																							new long[] { eid }));
//														}
//
//													}).create().show();
//										} else if (index == 1)// 店铺
//										{
//											Builder builder = new AlertDialog.Builder(
//													EmployeeListActivity.this);
//
//											String shop[] = new String[GameData.corporation.shop.length];
//
//											for (int i = 0; i < shop.length; i++) {
//												shop[i] = GameData.corporation.shop[i].name;
//											}
//
//											builder.setItems(
//													shop,
//													new DialogInterface.OnClickListener() {
//
//														public void onClick(
//																DialogInterface dialog,
//																int index) {
//
//															Connection
//																	.sendMessage(
//																			GameProtocol.CONNECTION_SEND_STAFF_OP_REQ,
//																			ConstructData
//																					.Staff_Appoint_Req(
//																							(byte) 0,
//																							GameData.corporation.shop[index].id,
//																							GameData.corporation.shop[index].type,
//																							new long[] { eid }));
//														}
//													}
//
//											).create().show();
//
//										} else if (index == 2)// 公益建筑
//										{
//											Builder builder = new AlertDialog.Builder(
//													EmployeeListActivity.this);
//
//											String building[] = new String[GameData.corporation.build.length];
//
//											for (int i = 0; i < building.length; i++) {
//												building[i] = GameData.corporation.build[i].name;
//											}
//
//											builder.setItems(
//													building,
//													new DialogInterface.OnClickListener() {
//
//														public void onClick(
//																DialogInterface dialog,
//																int index) {
//
//															Connection
//																	.sendMessage(
//																			GameProtocol.CONNECTION_SEND_STAFF_OP_REQ,
//																			ConstructData
//																					.Staff_Appoint_Req(
//																							(byte) 0,
//																							GameData.corporation.build[index].id,
//																							GameData.corporation.build[index].type,
//																							new long[] { eid }));
//														}
//
//													}
//
//											).create().show();
//										}
//
//									}
//								}).create().show();
//
//					} else// 调配空闲
//					{
//
//						Connection.sendMessage(
//								GameProtocol.CONNECTION_SEND_STAFF_OP_REQ,
//								ConstructData.Staff_Appoint_Req((byte) 1,
//										employee[position].department,
//										(byte) 0,
//										new long[] { employee[position].id }));
//					}
//				}
//
//			}
//		}
//
//		);

		emplistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					public void onItemClick(AdapterView<?> parent, View v,
							final int index, long id) 
					{
						EmployeeItemAdapter adapter = (EmployeeItemAdapter)emplistview.getAdapter();
						
						if(adapter.isCheck(index))
						{
							adapter.setCheck(index, false);
						}
						else
						{
							adapter.setCheck(index, true);
						}
						adapter.notifyDataSetChanged();
						checkButton();
						
						// TODO
						// 获取能被员工使用的我的道具
						/**
						String[] myGoodsTemp = new String[GameData.props.length];
						if (GameData.props != null&&GameData.props.length!=0) {
							Log.i("Log", GameData.props.length + "");
							int i = 0;
							int j = 0;
							while (true) 
							{
								// 判断该道具能否被员工所用
								if (canUsedByEmployee(GameData.props[i])) 
								{
									myGoodsTemp[j] = GameData.props[i].name+"\t\t"+GameData.props[i].count;
									j++;
								}
								i++;
								if (i == GameData.props.length)
									break;
							}
							Log.i("Log4", "j__last:"+j);
							myGoodsForEmployee = new String[j];
							System.arraycopy(myGoodsTemp, 0,
									myGoodsForEmployee, 0, j);
						}
						if(DoctorCustodyActivity.mContext == null){

						String[] items = { "使用道具", "查看员工详情" };
						Builder builder = new AlertDialog.Builder(mContext);
						builder.setItems(items,
								new DialogInterface.OnClickListener() {

									 
									public void onClick(DialogInterface dialog,
											int which) {
										if (which == 0) {// 使用道具
											if (myGoodsForEmployee==null) {
												// 弹出提示框
												buildMessageDialog(
														mContext,
														mContext.getResources()
																.getString(
																		R.string.to_buy_props))
														.show();
											} else {
												// 弹出道具列表
												for (String ss : myGoodsForEmployee) {
													Log.i("Log4", ss);
												}
												Builder builder = new AlertDialog.Builder(
														mContext);
												builder.setItems(
														myGoodsForEmployee,
														new DialogInterface.OnClickListener() {

															 
															public void onClick(
																	DialogInterface dialog,
																	int which) {
																Props props = getPropsByName(myGoodsForEmployee[which]);
																if (props == null)
																	return;
																
																// 使用道具
																Connection
																		.sendMessage(
																				GameProtocol.PROPS_USE,
																				ConstructData
																						.useProps(
																								(byte) 1,
																								props.id,
																								(byte) 2,
																								GameData.corporation.employee[index].id,
																								props.count));

															}
														}).create().show();
											}

										} else if (which == 1) {// 查看员工详情
											Intent intent = new Intent();
											Bundle bundle = new Bundle(); // 携带数据
											bundle.putByte("type", (byte) 1);
											bundle.putByte("index",
													(byte) index);
											intent.putExtras(bundle);
											EmployeeListActivity.this
													.Activitychange(
															EmployeeInfoActivity.class,
															intent);
										}
									}
								}).create().show();

					}
					else 
					{
						
					}*/
					}
				});

		


		Button employee_allSelect = (Button) this
				.findViewById(R.id.employeelist_layout_allselect);
		//查看
		look = (Button)this.findViewById(R.id.emp_button_look);
//		look.setTypeface(GameDefinition.face);
		//常务
		employee_routine = (Button) this
		.findViewById(R.id.emp_button_routine);
//		employee_routine.setTypeface(GameDefinition.face);
		//调配
		deploy = (Button)this.findViewById(R.id.emp_button_deploy);
//		deploy.setTypeface(GameDefinition.face);
		//招聘
		employ = (Button)this.findViewById(R.id.emp_button_employ);
//		employ.setTypeface(GameDefinition.face);
		//培训
		
//		train =(Button)this.findViewById(R.id.emp_button_train);
//		train.setTypeface(GameDefinition.face);
		
	    
		
		if(DoctorCustodyActivity.mContext != null)
		{
			employee_routine.setVisibility(View.GONE);
			employee_allSelect.setVisibility(View.VISIBLE);
			employee_allSelect.setBackgroundResource(R.drawable.planningdept_confirm);
			
			employ.setVisibility(View.GONE);
		}
		
		look.setOnClickListener(new OnClickListener()
		{
			 
			public void onClick(View v) 
			{
				
				int[] idx = getCheckIndex();
				
				if(idx != null && idx.length>0)//选中了至少一项
				{
					Intent intent = new Intent();
					Bundle bundle = new Bundle(); // 携带数据
					bundle.putByte("type", (byte) 1);
					bundle.putByte("index",
							(byte) idx[0]);
					intent.putExtras(bundle);
					EmployeeListActivity.this
							.Activitychange(
									EmployeeInfoActivity.class,
									intent);
				}
				else//一项都没有选中
				{
					Toast.makeText(EmployeeListActivity.this, MainActivity.resources.getString(R.string.employeelist_choosestaff),
							Toast.LENGTH_SHORT).show();
				}
				
				
			}	
		}
		);
		
		
		

		employee_routine.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {

				EmployeeItemAdapter adpter = (EmployeeItemAdapter) emplistview
						.getAdapter();
				Vector v = new Vector(5, 5);
				for (int i = 0; i < adpter.getCount(); i++) {
					if (adpter.isCheck(i)) {
						v.addElement(i);
					}
				}
				final long employeeId[] = new long[v.size()];

				for (int i = 0; i < v.size(); i++) {
					// zhouzhilong amend
					employeeId[i] = GameData.corporation.employee[(Integer) v
							.get(i)].id;
				}

				if (v.size() <= 0) {
					Toast.makeText(
							EmployeeListActivity.this,
							MainActivity.resources
									.getString(R.string.employeelist_choosestaff),
							Toast.LENGTH_SHORT).show();
				} else {
					EmployeeListActivity.this
							.setContentView(R.layout.employeelist_routine);

					 routin_desc = (TextView)EmployeeListActivity.this.findViewById(R.id.routin_desc);
					 routin_desc.setText(menu[0]);
					 confirm = (Button)EmployeeListActivity.this.findViewById(R.id.confirm);
					Button cancel = (Button) EmployeeListActivity.this
							.findViewById(R.id.employeelist_routin_button_cancel);
					
					cancel.setOnClickListener(new OnClickListener() {
						public void onClick(View arg0) {

							EmployeeListActivity.this
									.setContentView(R.layout.employeelist);
							init();

						}
					});
					confirm.setOnClickListener(new OnClickListener() {
						public void onClick(View arg0) {
							if (p == 0)// 开会
							{
								Connection
										.sendMessage(
												GameProtocol.CONNECTION_SEND_EMPLOYEEMENT_MEET,
												ConstructData
														.getMeetMsg(
																(byte) 2,
																employeeId));
							} else if (p == 1)// 沟通
							{
								Connection
										.sendMessage(
												GameProtocol.CONNECTION_COMMUNICATION_REQ,
												ConstructData
														.getCommunication(
																(byte) 2,
																employeeId));
							} else if (p == 2)// 义工
							{
								Connection
										.sendMessage(
												GameProtocol.CONNECTION_COMMONWELL_REQ,
												ConstructData
														.getCommonWell(
																(byte) 1,
																employeeId));
							}

							else if (p == 3)// 解雇
							{
								Connection
										.sendMessage(
												GameProtocol.CONNECTION_SEND_STAFF_OP_REQ,
												ConstructData
														.unEmployStaffBatch(employeeId));
							} else if (p == 4)// 续约
							{
								Connection
										.sendMessage(
												GameProtocol.CONNECTION_SEND_STAFF_CONTINUE,
												ConstructData
														.getContract(employeeId));
							} else if (p == 5)// 培训
							{
								Connection
										.sendMessage(
												GameProtocol.CONNECTION_SKILL_TRAINING_REQ,
												ConstructData
														.getTraining(
																(byte) (1),
																employeeId));
							} else if (p == 6)// 进修
							{
								Connection
										.sendMessage(
												GameProtocol.CONNECTION_SKILL_TRAINING_REQ,
												ConstructData
														.getTraining(
																(byte) (2),
																employeeId));
							}	

							
						}
					});
					
					final Gallery emplistroutin = (Gallery) EmployeeListActivity.this
							.findViewById(R.id.employeelist_routin_gridview);

					GridsimpleImageAdapter adapter = new GridsimpleImageAdapter(
							EmployeeListActivity.this);

					adapter.setImageIdArray(new int[] { 153, 169, 37, 178, 177,
							147, 181 });

					emplistroutin.setAdapter(adapter);
					
					emplistroutin.setOnItemSelectedListener(new OnItemSelectedListener(){

						 
						public void onItemSelected(AdapterView<?> arg0, View arg1,
								int position, long arg3) {
							// TODO Auto-generated method stub
							p=position;
							routin_desc.setText(menu[position]);
							}

						 
						public void onNothingSelected(AdapterView<?> parent) {
							// TODO Auto-generated method stub
							
						}
						});
					emplistroutin
							.setOnItemClickListener(new OnItemClickListener() {

								public void onItemClick(AdapterView<?> parent,
										View v, int position, long id) {
									p = position;
									 routin_desc.setText(menu[position]);
									 /*	if (position == 0)// 开会
									{
										Connection
												.sendMessage(
														GameProtocol.CONNECTION_SEND_EMPLOYEEMENT_MEET,
														ConstructData
																.getMeetMsg(
																		(byte) 2,
																		employeeId));
									} else if (position == 1)// 沟通
									{
										Connection
												.sendMessage(
														GameProtocol.CONNECTION_COMMUNICATION_REQ,
														ConstructData
																.getCommunication(
																		(byte) 2,
																		employeeId));
									} else if (position == 2)// 义工
									{
										Connection
												.sendMessage(
														GameProtocol.CONNECTION_COMMONWELL_REQ,
														ConstructData
																.getCommonWell(
																		(byte) 1,
																		employeeId));
									}

									else if (position == 3)// 解雇
									{
										Connection
												.sendMessage(
														GameProtocol.CONNECTION_SEND_STAFF_OP_REQ,
														ConstructData
																.unEmployStaffBatch(employeeId));
									} else if (position == 4)// 续约
									{
										Connection
												.sendMessage(
														GameProtocol.CONNECTION_SEND_STAFF_CONTINUE,
														ConstructData
																.getContract(employeeId));
									} else if (position == 5)// 培训
									{
										Connection
												.sendMessage(
														GameProtocol.CONNECTION_SKILL_TRAINING_REQ,
														ConstructData
																.getTraining(
																		(byte) (1),
																		employeeId));
									} else if (position == 6)// 进修
									{
										Connection
												.sendMessage(
														GameProtocol.CONNECTION_SKILL_TRAINING_REQ,
														ConstructData
																.getTraining(
																		(byte) (2),
																		employeeId));
									}
								*/}

							}

							);

				}

			}

		});
		
		deploy.setOnClickListener(new OnClickListener()
		{
			 
			public void onClick(View v) 
			{
				int[] idx = getCheckIndex();
				
				if(idx != null && idx.length>0)//选中了至少一项
				{
					int position = idx[0];
					if (employee[position].department <= 0)// 调配
					{
						final long eid = employee[position].id;
						String section[] = new String[] {
								MainActivity.resources
										.getString(R.string.employeelist_dept),
								MainActivity.resources
										.getString(R.string.employeelist_shop),
								MainActivity.resources
										.getString(R.string.employeelist_building) };
						final  AlertDialog dlg1 = new AlertDialog.Builder(EmployeeListActivity.this).create();
						dlg1.show();
						dlg1.getWindow().setContentView(R.layout.mapmenu3_list);
						ListView lv = (ListView)dlg1.findViewById(R.id.mapmenu3_list);				
						ArrayList<HashMap<String, String>>list= new  ArrayList<HashMap<String,String>>();
						HashMap<String, String> map;
						for (int i = 0; i < section.length; i++) {
							//	shop[i] = GameData.corporation.shop[i].name;
								 map = new HashMap<String, String>();						 
								 map.put("name", section[i]);
								 list.add(map);
							}

							 SimpleAdapter listAdapter = new SimpleAdapter(EmployeeListActivity.this,list,   
						                R.layout.mapmenu3_item, new String[] {"name"},   
						                new int[] {R.id.mapmenu3_list_text});   
					        lv.setAdapter(listAdapter);
					        lv.setOnItemClickListener(new OnItemClickListener() {

								 
								public void onItemClick(AdapterView<?> parent,
										View view, int position, long id) {
									// TODO Auto-generated method stub
									if(position ==0){
										String department[] = new String[GameData.corporation.department.length];
										for (int i = 0; i < department.length; i++) {
											department[i] = GameData.corporation.department[i].name;
										}
										final  AlertDialog dlg2 = new AlertDialog.Builder(EmployeeListActivity.this).create();
										dlg2.show();
										dlg2.getWindow().setContentView(R.layout.mapmenu3_list);
										ListView lv = (ListView)dlg2.findViewById(R.id.mapmenu3_list);				
										ArrayList<HashMap<String, String>>list= new  ArrayList<HashMap<String,String>>();
										HashMap<String, String> map;
										if(department.length==0){
											Toast.makeText(MainActivity.mContext, MainActivity.resources.getString(R.string.employeelist_toast),
													Toast.LENGTH_LONG).show();
											    dlg2.dismiss();
											return;
										}else{
										for (int i = 0; i < department.length; i++) {
											//	shop[i] = GameData.corporation.shop[i].name;
												 map = new HashMap<String, String>();						 
												 map.put("name", department[i]);
												 list.add(map);
											}

											 SimpleAdapter listAdapter = new SimpleAdapter(EmployeeListActivity.this,list,   
										                R.layout.mapmenu3_item, new String[] {"name"},   
										                new int[] {R.id.mapmenu3_list_text});   
									        lv.setAdapter(listAdapter);
									        lv.setOnItemClickListener(new OnItemClickListener() {

												 
												public void onItemClick(
														AdapterView<?> parent,
														View view,
														int position, long id) {
													// TODO Auto-generated method stub

													Connection
															.sendMessage(
																	GameProtocol.CONNECTION_SEND_STAFF_OP_REQ,
																	ConstructData
																			.Staff_Appoint_Req(
																					(byte) 0,
																					GameData.corporation.department[position].id,
																					GameData.corporation.department[position].type,
																					new long[] { eid }));
													dlg2.dismiss();
												}
												});
									        }
										dlg1.dismiss();
									}else if(position == 1){
										String shop[] = new String[GameData.corporation.shop.length];

										for (int i = 0; i < shop.length; i++) {
											shop[i] = GameData.corporation.shop[i].name;
										}
										final  AlertDialog dlg2 = new AlertDialog.Builder(EmployeeListActivity.this).create();
										dlg2.show();
										dlg2.getWindow().setContentView(R.layout.mapmenu3_list);
										ListView lv = (ListView)dlg2.findViewById(R.id.mapmenu3_list);				
										ArrayList<HashMap<String, String>>list= new  ArrayList<HashMap<String,String>>();
										HashMap<String, String> map;
										if(shop.length==0){
											Toast.makeText(MainActivity.mContext, MainActivity.resources.getString(R.string.employeelist_toast),
													Toast.LENGTH_LONG).show();
											    dlg2.dismiss();
											return;
										}else{
										for (int i = 0; i < shop.length; i++) {
											//	shop[i] = GameData.corporation.shop[i].name;
												 map = new HashMap<String, String>();						 
												 map.put("name", shop[i]);
												 list.add(map);
											}

											 SimpleAdapter listAdapter = new SimpleAdapter(EmployeeListActivity.this,list,   
										                R.layout.mapmenu3_item, new String[] {"name"},   
										                new int[] {R.id.mapmenu3_list_text});   
									        lv.setAdapter(listAdapter);
									        lv.setOnItemClickListener(new OnItemClickListener() {

												 
												public void onItemClick(
														AdapterView<?> parent,
														View view,
														int position, long id) {
													// TODO Auto-generated method stub
													Connection
													.sendMessage(
															GameProtocol.CONNECTION_SEND_STAFF_OP_REQ,
															ConstructData
																	.Staff_Appoint_Req(
																			(byte) 0,
																			GameData.corporation.shop[position].id,
																			GameData.corporation.shop[position].type,
																			new long[] { eid }));
													dlg2.dismiss();
												}
												});
									        }
										dlg1.dismiss();
										
									}else if(position ==2){
										String building[] = new String[GameData.corporation.build.length];

										for (int i = 0; i < building.length; i++) {
											building[i] = GameData.corporation.build[i].name;
										}
										final  AlertDialog dlg2 = new AlertDialog.Builder(EmployeeListActivity.this).create();
										dlg2.show();
										dlg2.getWindow().setContentView(R.layout.mapmenu3_list);
										ListView lv = (ListView)dlg2.findViewById(R.id.mapmenu3_list);				
										ArrayList<HashMap<String, String>>list= new  ArrayList<HashMap<String,String>>();
										HashMap<String, String> map;
										if(building.length==0){
											Toast.makeText(MainActivity.mContext, MainActivity.resources.getString(R.string.employeelist_toast),
													Toast.LENGTH_LONG).show();
											    dlg2.dismiss();
											return;
										}else{
										for (int i = 0; i < building.length; i++) {
											//	shop[i] = GameData.corporation.shop[i].name;
												 map = new HashMap<String, String>();						 
												 map.put("name", building[i]);
												 list.add(map);
											}

											 SimpleAdapter listAdapter = new SimpleAdapter(EmployeeListActivity.this,list,   
										                R.layout.mapmenu3_item, new String[] {"name"},   
										                new int[] {R.id.mapmenu3_list_text});   
									        lv.setAdapter(listAdapter);
									        lv.setOnItemClickListener(new OnItemClickListener() {

												 
												public void onItemClick(
														AdapterView<?> parent,
														View view,
														int position, long id) {
													// TODO Auto-generated method stub
													Connection
													.sendMessage(
															GameProtocol.CONNECTION_SEND_STAFF_OP_REQ,
															ConstructData
																	.Staff_Appoint_Req(
																			(byte) 0,
																			GameData.corporation.build[position].id,
																			GameData.corporation.build[position].type,
																			new long[] { eid }));
													dlg2.dismiss();
												}
												});
									        }
										dlg1.dismiss();
										
										
									}
								}
								});
					        
				/*		
						Builder builder = new AlertDialog.Builder(
								EmployeeListActivity.this);

					//	final long eid = employee[position].id;
						builder.setItems(section,
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int index) {
										if (index == 0)// 部门
										{
											Builder builder = new AlertDialog.Builder(
													EmployeeListActivity.this);

											String department[] = new String[GameData.corporation.department.length];

											for (int i = 0; i < department.length; i++) {
												department[i] = GameData.corporation.department[i].name;
											}

											builder.setItems(
													department,
													new DialogInterface.OnClickListener() {

														public void onClick(
																DialogInterface dialog,
																int index) {

															Connection
																	.sendMessage(
																			GameProtocol.CONNECTION_SEND_STAFF_OP_REQ,
																			ConstructData
																					.Staff_Appoint_Req(
																							(byte) 0,
																							GameData.corporation.department[index].id,
																							GameData.corporation.department[index].type,
																							new long[] { eid }));
														}

													}).create().show();
										} else if (index == 1)// 店铺
										{
											Builder builder = new AlertDialog.Builder(
													EmployeeListActivity.this);

											String shop[] = new String[GameData.corporation.shop.length];

											for (int i = 0; i < shop.length; i++) {
												shop[i] = GameData.corporation.shop[i].name;
											}

											builder.setItems(
													shop,
													new DialogInterface.OnClickListener() {

														public void onClick(
																DialogInterface dialog,
																int index) {

															Connection
																	.sendMessage(
																			GameProtocol.CONNECTION_SEND_STAFF_OP_REQ,
																			ConstructData
																					.Staff_Appoint_Req(
																							(byte) 0,
																							GameData.corporation.shop[index].id,
																							GameData.corporation.shop[index].type,
																							new long[] { eid }));
														}
													}

											).create().show();

										} else if (index == 2)// 公益建筑
										{
											Builder builder = new AlertDialog.Builder(
													EmployeeListActivity.this);

											String building[] = new String[GameData.corporation.build.length];

											for (int i = 0; i < building.length; i++) {
												building[i] = GameData.corporation.build[i].name;
											}

											builder.setItems(
													building,
													new DialogInterface.OnClickListener() {

														public void onClick(
																DialogInterface dialog,
																int index) {

															Connection
																	.sendMessage(
																			GameProtocol.CONNECTION_SEND_STAFF_OP_REQ,
																			ConstructData
																					.Staff_Appoint_Req(
																							(byte) 0,
																							GameData.corporation.build[index].id,
																							GameData.corporation.build[index].type,
																							new long[] { eid }));
														}
													}

											).create().show();
										}
									}
								}).create().show();
*/
					} else// 调配空闲
					{

						Connection.sendMessage(
								GameProtocol.CONNECTION_SEND_STAFF_OP_REQ,
								ConstructData.Staff_Appoint_Req((byte) 1,
										employee[position].department,
										(byte) 0,
										new long[] { employee[position].id }));
					}
				}
				else
				{
					Toast.makeText(EmployeeListActivity.this, MainActivity.resources.getString(R.string.employeelist_choosestaff),
							Toast.LENGTH_SHORT).show();
				}
			}	
		}
		);
		
//		train.setOnClickListener(new OnClickListener()
//		{
//			 
//			public void onClick(View v) 
//			{
//				int[] idx = getCheckIndex();
//				
//				if(idx != null && idx.length>0)//选中了至少一项
//				{
//					
//				}
//				else
//				{
//					Toast.makeText(EmployeeListActivity.this, MainActivity.resources.getString(R.string.employeelist_choosestaff),
//							Toast.LENGTH_SHORT).show();
//				}
//			}	
//		}
//		);
		
		


/*		employee_allSelect.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				if(DoctorCustodyActivity.mContext ==null){
				EmployeeItemAdapter adpter = (EmployeeItemAdapter) emplistview
						.getAdapter();
				Vector vector = new Vector(5, 5);
				for (int i = 0; i < adpter.getCount(); i++) {
					if (adpter.isCheck(i)) {
						vector.addElement(i);
					}
				}

				int count = GameData.getDepartment(Branch.PERSONNAL).employees.length
						- vector.size();

				for (int i = 0; i < adpter.getCount(); i++) {
					if (count <= 0)
						break;
					if (!adpter.isCheck(i)) {
						adpter.setCheck(i, true);
						count -= 1;

					}
				}

				adpter.notifyDataSetChanged();

			}else{
				//發包操作
				EmployeeItemAdapter adpter = (EmployeeItemAdapter) emplistview
				.getAdapter();
		Vector v1 = new Vector(5, 5);
		for (int i = 0; i < adpter.getCount(); i++) {
			if (adpter.isCheck(i)) {zs
				v1.addElement(i);
			}
		}
		final long employeeId[] = new long[v1.size()];

		for (int i = 0; i < v1.size(); i++) {
			// zhouzhilong amend
			employeeId[i] = GameData.corporation.employee[(Integer) v1
					.get(i)].id;
		}
		if (v1.size() <= 0) {
			Toast.makeText(EmployeeListActivity.this, MainActivity.resources.getString(R.string.employeelist_choosestaff),
					Toast.LENGTH_SHORT).show();
		}else{
				Connection.sendMessage(GameProtocol.DoctorTrust, ConstructData.getDoctorTrustData((byte)GameData.trustType[DoctorCustodyActivity.ID], (short)GameData.trustId[DoctorCustodyActivity.ID], (short)0, (byte)1, (byte)2, (byte)v1.size(),employeeId));
		}
			}
			}

		});*/
		
		employ.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				final AlertDialog dlg = new AlertDialog.Builder(EmployeeListActivity.this).create();
				dlg.show();					
				dlg.getWindow().setContentView(R.layout.pm_employee);
				
				Button  fast = (Button)dlg.findViewById(R.id.pm_employee_button_fastemployee);	
				Button  talentmarket = (Button)dlg.findViewById(R.id.pm_employee_button_talentmarket);	
				Button  hunter = (Button)dlg.findViewById(R.id.pm_employee_button_hunter);	
				
				fast.setOnClickListener(new OnClickListener()
				{

					 
					public void onClick(View v) 
					{
						// TODO Auto-generated method stub
//						Connection
//						.sendMessage(
//								GameProtocol.CONNECTION_REQ_EMPLOYMENT_STAFF_BATCH,
//								ConstructData
//										.getEmployStaffBatch(
//												(byte) 2,
//												shopId));
						Connection
						.sendMessage(
								GameProtocol.CONNECTION_REQ_EMPLOYMENT_STAFF_BATCH,
								ConstructData.getEmployStaffBatch(
										(byte) 0, new long[] { 0 }));
						dlg.dismiss();
					}
					
				}
				);
				talentmarket.setOnClickListener(new OnClickListener()
				{
					
					 
					public void onClick(View v) 
					{
						// TODO Auto-generated method stub
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
						
													intent.setClass(EmployeeListActivity.this,
															DiglistActivity.class);
													
													EmployeeListActivity.this.startActivity(intent);
						
													Connection.sendMessage(
															GameProtocol.REQSearchStaff_Req,
															ConstructData.SearchStaff_Req((byte) 1, "",
																	"", (byte) 1, new byte[] { 0 },
																	new byte[] { 0 }, new byte[] { 0 },
																	0, 50));
													dlg.dismiss();
					}
					
				}
				);
				hunter.setOnClickListener(new OnClickListener()
				{
					 
					public void onClick(View v) 
					{
						Intent intent = new Intent();
						
						intent.setClass(EmployeeListActivity.this,
								SearchconditionActivity.class);
						EmployeeListActivity.this.startActivity(intent);
						dlg.dismiss();
					}
				}
				);
				
				
//				Builder builder = new AlertDialog.Builder(
//						EmployeeListActivity.this);
//				
//				String item[] = new String[] {
//						MainActivity.resources
//								.getString(R.string.employeelist_fasthire),
//						MainActivity.resources
//								.getString(R.string.diglist_talentmarket),
//						MainActivity.resources
//								.getString(R.string.diglist_huntingcenter) };
//				builder.setItems(item, new DialogInterface.OnClickListener() {
//
//					public void onClick(DialogInterface dialog, int index) {
//
//						if (index == 0) {
//							Connection
//									.sendMessage(
//											GameProtocol.CONNECTION_REQ_EMPLOYMENT_STAFF_BATCH,
//											ConstructData.getEmployStaffBatch(
//													(byte) 0, new long[] { 0 }));
//							// zhouzhilong add --- 新手引导
//							if (GameData.isFreshMan
//									&& FreshManLead.caseId == 13) {
//								recruitmentArrows.setVisibility(View.GONE);
//								FreshManLead.caseId++;
//								doPromoter(FreshManLead.prompt15);
//							}
//						} else if (index == 1) {
//							Intent intent = new Intent();
//
//							Bundle bundle = new Bundle();
//
//							bundle.putByte("type", (byte) 1);
//
//							bundle.putString("staffname", "");
//
//							bundle.putString("mastername", "");
//
//							bundle.putByte("size", (byte) 1);
//
//							bundle.putByteArray("office", new byte[] { 0 });
//
//							bundle.putByteArray("tType", new byte[] { 0 });
//
//							bundle.putByteArray("level", new byte[] { 0 });
//
//							bundle.putInt("begin", 0);
//
//							bundle.putInt("count", 50);
//
//							intent.putExtras(bundle);
//
//							intent.setClass(EmployeeListActivity.this,
//									DiglistActivity.class);
//							
//							EmployeeListActivity.this.startActivity(intent);
//
//							Connection.sendMessage(
//									GameProtocol.REQSearchStaff_Req,
//									ConstructData.SearchStaff_Req((byte) 1, "",
//											"", (byte) 1, new byte[] { 0 },
//											new byte[] { 0 }, new byte[] { 0 },
//											0, 50));
//							// Connection.sendMessage(GameProtocol.REQSearchStaff_Req,
//							// Connection.SearchStaff_Req(GameData.seaSta_Optype,GameData.seaSta_Staffname,GameData.seaSta_Mastername,
//							// GameData.seaSta_Size,GameData.seaSta_office,GameData.seaSta_type,GameData.seaSta_level,
//							// GameData.seaSta_Start,GameData.seaSta_Num));
//						} else if (index == 2) {
//							Intent intent = new Intent();
//							
//							intent.setClass(EmployeeListActivity.this,
//									SearchconditionActivity.class);
//							EmployeeListActivity.this.startActivity(intent);
//						}
//					}
//
//				}).create().show();
				// Connection.sendMessage(GameProtocol.CONNECTION_REQ_EMPLOYMENT_STAFF_BATCH,
				// ConstructData.getEmployStaffBatch((byte)0, new long[]{0}));
			}
		}
		);
		
		// zhouzhilong add ---新手引导--指向箭头
		if (GameData.isFreshMan && FreshManLead.caseId == 13) {
			recruitmentArrows = (SpriteView) this
					.findViewById(R.id.employeelist_recruitment_arrows);			
			recruitmentArrows.setSeries(0);
			recruitmentArrows.setVisibility(View.VISIBLE);
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
				.findViewById(R.id.linearlayout_promotframe5);
		Button close = (Button) this.findViewById(R.id.close_promotframe5);
		promot = (TextView) this.findViewById(R.id.promotFrame5);
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
		anim.setAnimationListener(new AnimationListener() {

			 
			public void onAnimationStart(Animation animation) {
			}

			 
			public void onAnimationRepeat(Animation animation) {
			}

			 
			public void onAnimationEnd(Animation animation) {
				if (FreshManLead.caseId == 14) {
					new Thread() {
						public void run() {
							endAnimationHandler.postDelayed((new Runnable() {
								 
								public void run() {
									promot.setText(FreshManLead.prompt16);
								}
							}), 2000);
						};
					}.start();
				}

			}
		});
		linearPromot.startAnimation(anim);

	}

	

	
	public void Activitychange(Class calss, Intent intent) {
		if (intent == null) {
			intent = new Intent();
		}

		intent.setClass(EmployeeListActivity.this, calss);
		

		this.startActivity(intent);
	}

	public void finish() {
		GameData.corporation.listener = null;
		mContext = null;
		super.finish();
	}

	public void OnDataChange(Bundle bundle) {

		final Employee employee[] = GameData.corporation.employee;

		// TextView shopNum =
		// (TextView)this.findViewById(R.id.employeelist_num);

		/*employeeNum.setText(MainActivity.resources
				.getString(R.string.Companytab_staffnumber) + employee.length);*/

		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		HashMap map = null;

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

		for (int i = 0; i < employee.length; i++) {
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
//			if (employee[i].department <= 0) {
//				if(!where){
//				map.put("button", R.drawable.deployment);
//				}else{
//					map.put("button", 200);
//				}
//			} else {
//				if(!where){
//				map.put("button", R.drawable.outgoing);
//				}else{
//					map.put("button", 200);
//				}
//			}

			list.add(map);
		}

		EmployeeItemAdapter adpter = new EmployeeItemAdapter(
				EmployeeListActivity.this, list, R.layout.employeelist_item,
				new String[] { "name", "level", "contract", "section",
						"loyalty"}, new int[] {
						R.id.employeelist_item_name,
						R.id.employeelist_item_level,
						R.id.employeelist_item_contract,
						R.id.employeelist_item_section,
						R.id.employeelist_item_loyalty,
						 });

//		emplistview.setAdapter(adpter);
		EmployeeItemAdapter old =(EmployeeItemAdapter)emplistview.getAdapter();

//		Log.d("bug", ""+adpter.getCount()+","+old.getCount());
		for(int i = 0; i< old.getCount();i++)
		{
			if(i>=adpter.getCount())
				return;
			if(old.isCheck(i))
			{
				adpter.setCheck(i, true);
			}
			else
			{
				adpter.setCheck(i, false);
			}
		}
		emplistview.setAdapter(adpter);
		
		

//		adpter.setOnItemButtonClickListener(new AdapterView.OnItemClickListener() {
//
//			public void onItemClick(AdapterView<?> parent, View v,
//					int position, long id) {
//				if (employee[position].department <= 0)// 调配
//				{
//					String section[] = new String[] {
//							MainActivity.resources
//									.getString(R.string.employeelist_dept),
//							MainActivity.resources
//									.getString(R.string.employeelist_shop),
//							MainActivity.resources
//									.getString(R.string.employeelist_building)};
//
//					Builder builder = new AlertDialog.Builder(
//							EmployeeListActivity.this);
//
//					final long eid = employee[position].id;
//					builder.setItems(section,
//							new DialogInterface.OnClickListener() {
//								public void onClick(DialogInterface dialog,
//										int index) {
//
//									if (index == 0)// 部门
//									{
//										Builder builder = new AlertDialog.Builder(
//												EmployeeListActivity.this);
//
//										String department[] = new String[GameData.corporation.department.length];
//
//										for (int i = 0; i < department.length; i++) {
//											department[i] = GameData.corporation.department[i].name;
//										}
//
//										builder.setItems(
//												department,
//												new DialogInterface.OnClickListener() {
//
//													public void onClick(
//															DialogInterface dialog,
//															int index) {
//
//														Connection
//																.sendMessage(
//																		GameProtocol.CONNECTION_SEND_STAFF_OP_REQ,
//																		ConstructData
//																				.Staff_Appoint_Req(
//																						(byte) 0,
//																						GameData.corporation.department[index].id,
//																						GameData.corporation.department[index].type,
//																						new long[] { eid }));
//													}
//
//												}).create().show();
//									} else if (index == 1)// 店铺
//									{
//										Builder builder = new AlertDialog.Builder(
//												EmployeeListActivity.this);
//
//										String shop[] = new String[GameData.corporation.shop.length];
//
//										for (int i = 0; i < shop.length; i++) {
//											shop[i] = GameData.corporation.shop[i].name;
//										}
//
//										builder.setItems(
//												shop,
//												new DialogInterface.OnClickListener() {
//
//													public void onClick(
//															DialogInterface dialog,
//															int index) {
//
//														Connection
//																.sendMessage(
//																		GameProtocol.CONNECTION_SEND_STAFF_OP_REQ,
//																		ConstructData
//																				.Staff_Appoint_Req(
//																						(byte) 0,
//																						GameData.corporation.shop[index].id,
//																						GameData.corporation.shop[index].type,
//																						new long[] { eid }));
//													}
//
//												}
//
//										).create().show();
//
//									} else if (index == 2)// 公益建筑
//									{
//										Builder builder = new AlertDialog.Builder(
//												EmployeeListActivity.this);
//
//										String building[] = new String[GameData.corporation.build.length];
//
//										for (int i = 0; i < building.length; i++) {
//											building[i] = GameData.corporation.build[i].name;
//										}
//
//										builder.setItems(
//												building,
//												new DialogInterface.OnClickListener() {
//
//													public void onClick(
//															DialogInterface dialog,
//															int index) {
//
//														Connection
//																.sendMessage(
//																		GameProtocol.CONNECTION_SEND_STAFF_OP_REQ,
//																		ConstructData
//																				.Staff_Appoint_Req(
//																						(byte) 0,
//																						GameData.corporation.build[index].id,
//																						GameData.corporation.build[index].type,
//																						new long[] { eid }));
//													}
//
//												}
//
//										).create().show();
//									}
//
//								}
//							}).create().show();
//
//				} else// 调配空闲
//				{
//					Toast.makeText(
//							EmployeeListActivity.this,
//							MainActivity.resources
//									.getString(R.string.employeelist_allocatefree),
//							Toast.LENGTH_SHORT).show();
//					Connection.sendMessage(
//							GameProtocol.CONNECTION_SEND_STAFF_OP_REQ,
//							ConstructData.Staff_Appoint_Req((byte) 1,
//									employee[position].department, (byte) 0,
//									new long[] { employee[position].id }));
//				}
//
//			}
//		}
//
//		);
	}

	private Dialog buildMessageDialog(Context context, String message) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(mContext.getResources().getString(R.string.title));
		builder.setMessage(message);
		builder.setPositiveButton(
				mContext.getResources().getString(R.string.ensure),
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						// 跳转到商城
						Connection.sendMessage(GameProtocol.MALL_ASK_LIST,
								ConstructData.getPropsMallListType((byte) 1));
					}
				});
		builder.setNegativeButton(
				mContext.getResources().getString(R.string.cancel),
				new DialogInterface.OnClickListener() {

					 
					public void onClick(DialogInterface dialog, int which) {
					}
				});
		return builder.create();

	}

	private boolean canUsedByEmployee(Props prop) {
		if(prop!=null){
		byte[] targetTypeId = prop.targetTypeld;
		
		if(targetTypeId == null){
			return false;
		}
		for (byte b : targetTypeId) {
			if (b == 2)
				return true;
		}
		}
		return false;
		
	}

	private Props getPropsByName(String name) {
		int i = 0;
		while (true) {
			if (name.startsWith(GameData.props[i].name)) {
				return GameData.props[i];
			}
			i++;
			if (i == GameData.props.length) {
				break;
			}
		}
		return null;
	}

	 
	public GameActivity getGameContext() {
		// TODO Auto-generated method stub
		return this;
	}
	
	

}
