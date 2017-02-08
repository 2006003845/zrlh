package com.zrong.Android.activity;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.zrong.Android.Listener.ActivityTransform;
import com.zrong.Android.Listener.DataChangeListener;
import com.zrong.Android.Util.Music;
import com.zrong.Android.View.SpriteView;
import com.zrong.Android.element.Employee;
import com.zrong.Android.element.Props;
import com.zrong.Android.game.Connection;
import com.zrong.Android.game.ConstructData;
import com.zrong.Android.game.GameData;
import com.zrong.Android.game.GameDefinition;
import com.zrong.Android.online.network.GameProtocol;

public class EmployeeInfoActivity extends GameActivity implements
		 DataChangeListener {

	public static EmployeeInfoActivity mContext = null;

	public static Employee e = null;

	private byte type = 0;// 1自己的员工2人才市场员工3猎头中心员工

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mContext = this;
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		Bundle bundle = this.getIntent().getExtras();

		type = bundle.getByte("type");
		Log.i("Diglist", "Employee--type" + type);
		// zhouzhilong amend
		int index = bundle.getByte("index");
		Log.i("Diglist", "Employee--index:" + index);

		this.setContentView(R.layout.employee_info);

		Button returnback = (Button) this
				.findViewById(R.id.employeeinfo_button_returnback);

		Button cancel = (Button) this
				.findViewById(R.id.employeeinfo_button_cancel);

		returnback.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {

				EmployeeInfoActivity.this.finish();
			}

		}

		);

		cancel.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {

				EmployeeInfoActivity.this.finish();
			}

		}

		);

		if (type == 1) 
		{
			e = GameData.corporation.employee[index];

			RelativeLayout study1 = (RelativeLayout) this
					.findViewById(R.id.employeeinfo_study1);

			Button buttonstudy1 = (Button) study1
					.findViewById(R.id.employeeinfo_skill_item_buttonright);

			buttonstudy1.setVisibility(View.VISIBLE);

			buttonstudy1.setOnClickListener(new OnClickListener() {

				public void onClick(View arg0) {
					// 使用道具
					final Props[] propsForSkill = getPropsForSkillLearn();// 用于学习技能的道具
					final AlertDialog dlg = new AlertDialog.Builder(EmployeeInfoActivity.this).create();
					dlg.show();					
					dlg.getWindow().setContentView(R.layout.employee_menu);
					Button use = (Button)dlg.findViewById(R.id.menu_use);
					Button training = (Button)dlg.findViewById(R.id.menu_training);
//					use.setTypeface(GameDefinition.face);
//					training.setTypeface(GameDefinition.face);
					use.setOnClickListener(new OnClickListener() {
						
						 
						public void onClick(View v) {
							// TODO Auto-generated method stub
							if (propsForSkill == null
									|| propsForSkill.length == 0) {
								// 提示没有道具，去商场购买
								buildMessageDialog(
										mContext,
										mContext.getResources()
												.getString(
														R.string.to_buy_props))
										.show();
								dlg.dismiss();
							} else {
								final String[] propssStr = getPropsName(propsForSkill);
								final  AlertDialog dlg1 = new AlertDialog.Builder(EmployeeInfoActivity.this).create();
								dlg1.show();
								dlg1.getWindow().setContentView(R.layout.mapmenu3_list);
								ListView lv = (ListView)dlg1.findViewById(R.id.mapmenu3_list);				
								ArrayList<HashMap<String, String>>list= new  ArrayList<HashMap<String,String>>();
								HashMap<String, String> map;
								for (int i = 0; i < propssStr.length; i++) {
									//	shop[i] = GameData.corporation.shop[i].name;
										 map = new HashMap<String, String>();						 
										 map.put("name", propssStr[i]);
										 list.add(map);
									}

									 SimpleAdapter listAdapter = new SimpleAdapter(EmployeeInfoActivity.this,list,   
								                R.layout.mapmenu3_item, new String[] {"name"},   
								                new int[] {R.id.mapmenu3_list_text});   
							        lv.setAdapter(listAdapter);
							        lv.setOnItemClickListener(new OnItemClickListener() {

										 
										public void onItemClick(
												AdapterView<?> parent,
												View view, int position, long id) {
											// TODO Auto-generated method stub
											Connection
											.sendMessage(
													GameProtocol.PROPS_USE,
													ConstructData
															.useProps(
																	(byte) 1,
																	propsForSkill[position].id,
																	(byte) 2,
																	e.id,
																	propsForSkill[position].count));
											dlg1.dismiss();
										}
									});
							        dlg.dismiss();
							}
						}
					});
					training.setOnClickListener(new OnClickListener() {
						
						 
						public void onClick(View v) {
							// TODO Auto-generated method stub
							String skillname[] = new String[GameData.skills.length];
							for (int i = 0; i < skillname.length; i++) {
								skillname[i] = GameData.skills[i].name;
							}
							final  AlertDialog dlg2 = new AlertDialog.Builder(EmployeeInfoActivity.this).create();
							dlg2.show();
							dlg2.getWindow().setContentView(R.layout.mapmenu3_list);
							ListView lv = (ListView)dlg2.findViewById(R.id.mapmenu3_list);				
							ArrayList<HashMap<String, String>>list= new  ArrayList<HashMap<String,String>>();
							HashMap<String, String> map;
				//			String[] shop = new String[GameData.corporation.shop.length];
					//		Log.i("Log", "shop的长度=" + shop.length);
							if (skillname.length == 0) {
							/*	Toast.makeText(MainActivity.mContext, MainActivity.resources.getString(R.string.mapview_toast2),
										Toast.LENGTH_LONG).show();*/
								dlg2.dismiss();
								return;
							}
							else if(skillname.length > 0){
							for (int i = 0; i < skillname.length; i++) {
							//	shop[i] = GameData.corporation.shop[i].name;
								 map = new HashMap<String, String>();						 
								 map.put("name", skillname[i]);
								 list.add(map);
							}

							 SimpleAdapter listAdapter = new SimpleAdapter(EmployeeInfoActivity.this,list,   
						                R.layout.mapmenu3_item, new String[] {"name"},   
						                new int[] {R.id.mapmenu3_list_text});   
					        lv.setAdapter(listAdapter);
					        lv.setOnItemClickListener(new OnItemClickListener() {

								 
								public void onItemClick(AdapterView<?> parent,
										View view, int position, long id) {
									// TODO Auto-generated method stub
									Connection.sendMessage(
											GameProtocol.CONNECTION_STAFF_SKILL_LEARN,
											ConstructData.getStaffSkillLearn((byte) (1),
													(GameData.skills[position].id), e.id));
									dlg2.dismiss();
								}
							});
					        dlg.dismiss();
							}
						}
					});
					/*String[] items = { "使用道具", "技能培训" };
					Builder builder = new AlertDialog.Builder(mContext);
					
					builder.setItems(items,
							new DialogInterface.OnClickListener() {

								 
								public void onClick(DialogInterface dialog,
										int which) {
									if (which == 0) {// 使用道具
										if (propsForSkill == null
												|| propsForSkill.length == 0) {
											// 提示没有道具，去商场购买
											buildMessageDialog(
													mContext,
													mContext.getResources()
															.getString(
																	R.string.to_buy_props))
													.show();
										} else {
											// 弹出道具列表
											final String[] propssStr = getPropsName(propsForSkill);
											Builder builder = new AlertDialog.Builder(
													mContext);
											builder.setItems(
													propssStr,
													new DialogInterface.OnClickListener() {

														 
														public void onClick(
																DialogInterface dialog,
																int which) {
															// 使用道具
															Connection
																	.sendMessage(
																			GameProtocol.PROPS_USE,
																			ConstructData
																					.useProps(
																							(byte) 1,
																							propsForSkill[which].id,
																							(byte) 2,
																							e.id,
																							propsForSkill[which].count));
														}
													}).create().show();

										}

									} else if (which == 1) {// 技能培训
										buildAlertDiagForSkillLearn();
									}
								}
							}).create().show();
*/
				}

			}

			);

			RelativeLayout study2 = (RelativeLayout) this
					.findViewById(R.id.employeeinfo_study2);

			Button buttonstudy2 = (Button) study2
					.findViewById(R.id.employeeinfo_skill_item_buttonright);
			buttonstudy2.setVisibility(View.VISIBLE);
			buttonstudy2.setOnClickListener(new OnClickListener() {

				public void onClick(View arg0) {
					final Props[] propsForSkill = getPropsForSkillLearn();// 用于学习技能的道具
					final AlertDialog dlg = new AlertDialog.Builder(EmployeeInfoActivity.this).create();
					dlg.show();					
					dlg.getWindow().setContentView(R.layout.employee_menu);
					Button use = (Button)dlg.findViewById(R.id.menu_use);
					Button training = (Button)dlg.findViewById(R.id.menu_training);
//					use.setTypeface(GameDefinition.face);
//					training.setTypeface(GameDefinition.face);
					use.setOnClickListener(new OnClickListener() {
						
						 
						public void onClick(View v) {
							// TODO Auto-generated method stub
							if (propsForSkill == null
									|| propsForSkill.length == 0) {
								// 提示没有道具，去商场购买
								buildMessageDialog(
										mContext,
										mContext.getResources()
												.getString(
														R.string.to_buy_props))
										.show();
								dlg.dismiss();
							} else {
								final String[] propssStr = getPropsName(propsForSkill);
								final  AlertDialog dlg1 = new AlertDialog.Builder(EmployeeInfoActivity.this).create();
								dlg1.show();
								dlg1.getWindow().setContentView(R.layout.mapmenu3_list);
								ListView lv = (ListView)dlg1.findViewById(R.id.mapmenu3_list);				
								ArrayList<HashMap<String, String>>list= new  ArrayList<HashMap<String,String>>();
								HashMap<String, String> map;
								for (int i = 0; i < propssStr.length; i++) {
									//	shop[i] = GameData.corporation.shop[i].name;
										 map = new HashMap<String, String>();						 
										 map.put("name", propssStr[i]);
										 list.add(map);
									}

									 SimpleAdapter listAdapter = new SimpleAdapter(EmployeeInfoActivity.this,list,   
								                R.layout.mapmenu3_item, new String[] {"name"},   
								                new int[] {R.id.mapmenu3_list_text});   
							        lv.setAdapter(listAdapter);
							        lv.setOnItemClickListener(new OnItemClickListener() {

										 
										public void onItemClick(
												AdapterView<?> parent,
												View view, int position, long id) {
											// TODO Auto-generated method stub
											Connection
											.sendMessage(
													GameProtocol.PROPS_USE,
													ConstructData
															.useProps(
																	(byte) 1,
																	propsForSkill[position].id,
																	(byte) 2,
																	e.id,
																	propsForSkill[position].count));
											dlg1.dismiss();
										}
									});
							        dlg.dismiss();
							}
						}
					});
					training.setOnClickListener(new OnClickListener() {
						
						 
						public void onClick(View v) {
							// TODO Auto-generated method stub
							String skillname[] = new String[GameData.skills.length];
							for (int i = 0; i < skillname.length; i++) {
								skillname[i] = GameData.skills[i].name;
							}
							final  AlertDialog dlg2 = new AlertDialog.Builder(EmployeeInfoActivity.this).create();
							dlg2.show();
							dlg2.getWindow().setContentView(R.layout.mapmenu3_list);
							ListView lv = (ListView)dlg2.findViewById(R.id.mapmenu3_list);				
							ArrayList<HashMap<String, String>>list= new  ArrayList<HashMap<String,String>>();
							HashMap<String, String> map;
				//			String[] shop = new String[GameData.corporation.shop.length];
					//		Log.i("Log", "shop的长度=" + shop.length);
							if (skillname.length == 0) {
							/*	Toast.makeText(MainActivity.mContext, MainActivity.resources.getString(R.string.mapview_toast2),
										Toast.LENGTH_LONG).show();*/
								dlg2.dismiss();
								return;
							}
							else if(skillname.length > 0){
							for (int i = 0; i < skillname.length; i++) {
							//	shop[i] = GameData.corporation.shop[i].name;
								 map = new HashMap<String, String>();						 
								 map.put("name", skillname[i]);
								 list.add(map);
							}

							 SimpleAdapter listAdapter = new SimpleAdapter(EmployeeInfoActivity.this,list,   
						                R.layout.mapmenu3_item, new String[] {"name"},   
						                new int[] {R.id.mapmenu3_list_text});   
					        lv.setAdapter(listAdapter);
					        lv.setOnItemClickListener(new OnItemClickListener() {

								 
								public void onItemClick(AdapterView<?> parent,
										View view, int position, long id) {
									// TODO Auto-generated method stub
									Connection.sendMessage(
											GameProtocol.CONNECTION_STAFF_SKILL_LEARN,
											ConstructData.getStaffSkillLearn((byte) (1),
													(GameData.skills[position].id), e.id));
									dlg2.dismiss();
								}
							});
					        dlg.dismiss();
							}
						}
					});
					// 使用道具
					/*String[] items = { "使用道具", "技能培训" };
					Builder builder = new AlertDialog.Builder(mContext);
					builder.setItems(items,
							new DialogInterface.OnClickListener() {

								 
								public void onClick(DialogInterface dialog,
										int which) {
									if (which == 0) {// 使用道具
										if (propsForSkill == null
												|| propsForSkill.length == 0) {
											// 提示没有道具，去商场购买
											buildMessageDialog(
													mContext,
													mContext.getResources()
															.getString(
																	R.string.to_buy_props))
													.show();
										} else {
											// 弹出道具列表
											final String[] propssStr = getPropsName(propsForSkill);
											Builder builder = new AlertDialog.Builder(
													mContext);
											builder.setItems(
													propssStr,
													new DialogInterface.OnClickListener() {

														 
														public void onClick(
																DialogInterface dialog,
																int which) {
															// 使用道具
															Connection
																	.sendMessage(
																			GameProtocol.PROPS_USE,
																			ConstructData
																					.useProps(
																							(byte) 1,
																							propsForSkill[which].id,
																							(byte) 2,
																							e.id,
																							propsForSkill[which].count));
														}
													}).create().show();
										}
									} else if (which == 1) {// 技能培训
										buildAlertDiagForSkillLearn();
									}
								}
							}).create().show();*/
				}

			}

			);

			RelativeLayout study3 = (RelativeLayout) this
					.findViewById(R.id.employeeinfo_study3);

			Button buttonstudy3 = (Button) study3
					.findViewById(R.id.employeeinfo_skill_item_buttonright);
			buttonstudy3.setVisibility(View.VISIBLE);
			buttonstudy3.setOnClickListener(new OnClickListener() {

				public void onClick(View arg0) {
					final Props[] propsForSkill = getPropsForSkillLearn();// 用于学习技能的道具
					final AlertDialog dlg = new AlertDialog.Builder(EmployeeInfoActivity.this).create();
					dlg.show();					
					dlg.getWindow().setContentView(R.layout.employee_menu);
					Button use = (Button)dlg.findViewById(R.id.menu_use);
					Button training = (Button)dlg.findViewById(R.id.menu_training);
//					use.setTypeface(GameDefinition.face);
//					training.setTypeface(GameDefinition.face);
					use.setOnClickListener(new OnClickListener() {
						
						 
						public void onClick(View v) {
							// TODO Auto-generated method stub
							if (propsForSkill == null
									|| propsForSkill.length == 0) {
								// 提示没有道具，去商场购买
								buildMessageDialog(
										mContext,
										mContext.getResources()
												.getString(
														R.string.to_buy_props))
										.show();
								dlg.dismiss();
							} else {
								final String[] propssStr = getPropsName(propsForSkill);
								final  AlertDialog dlg1 = new AlertDialog.Builder(EmployeeInfoActivity.this).create();
								dlg1.show();
								dlg1.getWindow().setContentView(R.layout.mapmenu3_list);
								ListView lv = (ListView)dlg1.findViewById(R.id.mapmenu3_list);				
								ArrayList<HashMap<String, String>>list= new  ArrayList<HashMap<String,String>>();
								HashMap<String, String> map;
								for (int i = 0; i < propssStr.length; i++) {
									//	shop[i] = GameData.corporation.shop[i].name;
										 map = new HashMap<String, String>();						 
										 map.put("name", propssStr[i]);
										 list.add(map);
									}

									 SimpleAdapter listAdapter = new SimpleAdapter(EmployeeInfoActivity.this,list,   
								                R.layout.mapmenu3_item, new String[] {"name"},   
								                new int[] {R.id.mapmenu3_list_text});   
							        lv.setAdapter(listAdapter);
							        lv.setOnItemClickListener(new OnItemClickListener() {

										 
										public void onItemClick(
												AdapterView<?> parent,
												View view, int position, long id) {
											// TODO Auto-generated method stub
											Connection
											.sendMessage(
													GameProtocol.PROPS_USE,
													ConstructData
															.useProps(
																	(byte) 1,
																	propsForSkill[position].id,
																	(byte) 2,
																	e.id,
																	propsForSkill[position].count));
											dlg1.dismiss();
										}
									});
							        dlg.dismiss();
							}
						}
					});
					training.setOnClickListener(new OnClickListener() {
						
						 
						public void onClick(View v) {
							// TODO Auto-generated method stub
							String skillname[] = new String[GameData.skills.length];
							for (int i = 0; i < skillname.length; i++) {
								skillname[i] = GameData.skills[i].name;
							}
							final  AlertDialog dlg2 = new AlertDialog.Builder(EmployeeInfoActivity.this).create();
							dlg2.show();
							dlg2.getWindow().setContentView(R.layout.mapmenu3_list);
							ListView lv = (ListView)dlg2.findViewById(R.id.mapmenu3_list);				
							ArrayList<HashMap<String, String>>list= new  ArrayList<HashMap<String,String>>();
							HashMap<String, String> map;
				//			String[] shop = new String[GameData.corporation.shop.length];
					//		Log.i("Log", "shop的长度=" + shop.length);
							if (skillname.length == 0) {
							/*	Toast.makeText(MainActivity.mContext, MainActivity.resources.getString(R.string.mapview_toast2),
										Toast.LENGTH_LONG).show();*/
								dlg2.dismiss();
								return;
							}
							else if(skillname.length > 0){
							for (int i = 0; i < skillname.length; i++) {
							//	shop[i] = GameData.corporation.shop[i].name;
								 map = new HashMap<String, String>();						 
								 map.put("name", skillname[i]);
								 list.add(map);
							}

							 SimpleAdapter listAdapter = new SimpleAdapter(EmployeeInfoActivity.this,list,   
						                R.layout.mapmenu3_item, new String[] {"name"},   
						                new int[] {R.id.mapmenu3_list_text});   
					        lv.setAdapter(listAdapter);
					        lv.setOnItemClickListener(new OnItemClickListener() {

								 
								public void onItemClick(AdapterView<?> parent,
										View view, int position, long id) {
									// TODO Auto-generated method stub
									Connection.sendMessage(
											GameProtocol.CONNECTION_STAFF_SKILL_LEARN,
											ConstructData.getStaffSkillLearn((byte) (1),
													(GameData.skills[position].id), e.id));
									dlg2.dismiss();
								}
							});
					        dlg.dismiss();
							}
						}
					});
					
					/*// 使用道具
					String[] items = { "使用道具", "技能培训" };
					Builder builder = new AlertDialog.Builder(mContext);
					builder.setItems(items,
							new DialogInterface.OnClickListener() {

								 
								public void onClick(DialogInterface dialog,
										int which) {
									final Props[] propsForSkill = getPropsForSkillLearn();// 用于学习技能的道具
									if (which == 0) {// 使用道具
										if (propsForSkill == null
												|| propsForSkill.length == 0) {
											// 提示没有道具，去商场购买
											buildMessageDialog(
													mContext,
													mContext.getResources()
															.getString(
																	R.string.to_buy_props))
													.show();
										} else {
											// 弹出道具列表
											final String[] propssStr = getPropsName(propsForSkill);
											Builder builder = new AlertDialog.Builder(
													mContext);
											builder.setItems(
													propssStr,
													new DialogInterface.OnClickListener() {

														 
														public void onClick(
																DialogInterface dialog,
																int which) {
															// 使用道具
															Connection
																	.sendMessage(
																			GameProtocol.PROPS_USE,
																			ConstructData
																					.useProps(
																							(byte) 1,
																							propsForSkill[which].id,
																							(byte) 2,
																							e.id,
																							propsForSkill[which].count));
														}
													}).create().show();

										}

									} else if (which == 1) {// 技能培训
										buildAlertDiagForSkillLearn();
									}
								}
							}).create().show();*/
				}
			});

			LinearLayout quality1 = (LinearLayout) this
					.findViewById(R.id.employeeinfo_quality1);
			Button up1 = (Button) quality1
					.findViewById(R.id.employeeinfo_quality_item_up);
			up1.setVisibility(View.VISIBLE);
			up1.setOnClickListener(new OnClickListener() {

				public void onClick(View arg0) {
					final Props[] propsForSkill = getPropsForSkillLearn();// 用于学习技能的道具
					final AlertDialog dlg = new AlertDialog.Builder(EmployeeInfoActivity.this).create();
					dlg.show();					
					dlg.getWindow().setContentView(R.layout.employee_menu);
					Button use = (Button)dlg.findViewById(R.id.menu_use);
					Button training = (Button)dlg.findViewById(R.id.menu_training);
//					use.setTypeface(GameDefinition.face);
//					training.setTypeface(GameDefinition.face);
					use.setOnClickListener(new OnClickListener() {
						
						 
						public void onClick(View v) {
							// TODO Auto-generated method stub
							final Props propssForQuality = getPropsForQuality(GameData.qualities[0].itemTemplateId);
							// TODO
							if (propssForQuality == null) {
								// 提示没有道具，去商场购买
								buildMessageDialog(
										mContext,
										mContext.getResources()
												.getString(
														R.string.to_buy_props))
										.show();
								dlg.dismiss();
							} else {// 直接消耗
								Connection
								.sendMessage(
										GameProtocol.PROPS_USE,
										ConstructData
												.useProps(
														(byte) 1,
														propssForQuality.id,
														(byte) 2,
														e.id,
														propssForQuality.count));
								dlg.dismiss();
					}
						}
					});
					training.setOnClickListener(new OnClickListener() {
						
						 
						public void onClick(View v) {		
							Connection
							.sendMessage(
									GameProtocol.CONNECTION_STAFF_SKILL_LEARN,
									ConstructData
											.getStaffSkillLearn(
													(byte) (2),
													(GameData.qualities[0].id),
													e.id));
							dlg.dismiss();
						}
					});
					
					/*// 使用道具
					String[] items = { "使用道具", "素质培训" };
					Builder builder = new AlertDialog.Builder(mContext);
					builder.setItems(items,
							new DialogInterface.OnClickListener() {

								 
								public void onClick(DialogInterface dialog,
										int which) {
									if (which == 0) {// 使用道具
										final Props propssForQuality = getPropsForQuality(GameData.qualities[0].itemTemplateId);
										// TODO
										if (propssForQuality == null) {
											// 提示没有道具，去商场购买
											buildMessageDialog(
													mContext,
													mContext.getResources()
															.getString(
																	R.string.to_buy_props))
													.show();
										} else {// 直接消耗
											Connection
													.sendMessage(
															GameProtocol.PROPS_USE,
															ConstructData
																	.useProps(
																			(byte) 1,
																			propssForQuality.id,
																			(byte) 2,
																			e.id,
																			propssForQuality.count));
										}
									} else if (which == 1) {
										// 素质培训
										Connection
												.sendMessage(
														GameProtocol.CONNECTION_STAFF_SKILL_LEARN,
														ConstructData
																.getStaffSkillLearn(
																		(byte) (2),
																		(GameData.qualities[0].id),
																		e.id));
									}
								}
							}).create().show();*/
				}
			});

			LinearLayout quality2 = (LinearLayout) this
					.findViewById(R.id.employeeinfo_quality2);
			Button up2 = (Button) quality2
					.findViewById(R.id.employeeinfo_quality_item_up);
			up2.setVisibility(View.VISIBLE);
			up2.setOnClickListener(new OnClickListener() {

				public void onClick(View arg0) {
					final Props[] propsForSkill = getPropsForSkillLearn();// 用于学习技能的道具
					final AlertDialog dlg = new AlertDialog.Builder(EmployeeInfoActivity.this).create();
					dlg.show();					
					dlg.getWindow().setContentView(R.layout.employee_menu);
					Button use = (Button)dlg.findViewById(R.id.menu_use);
					Button training = (Button)dlg.findViewById(R.id.menu_training);
//					use.setTypeface(GameDefinition.face);
//					training.setTypeface(GameDefinition.face);
					use.setOnClickListener(new OnClickListener() {
						
						 
						public void onClick(View v) {
							// TODO Auto-generated method stub
							final Props propssForQuality = getPropsForQuality(GameData.qualities[1].itemTemplateId);
							// TODO
							if (propssForQuality == null) {
								// 提示没有道具，去商场购买
								buildMessageDialog(
										mContext,
										mContext.getResources()
												.getString(
														R.string.to_buy_props))
										.show();
								dlg.dismiss();
							} else {// 直接消耗
								Connection
								.sendMessage(
										GameProtocol.PROPS_USE,
										ConstructData
												.useProps(
														(byte) 1,
														propssForQuality.id,
														(byte) 2,
														e.id,
														propssForQuality.count));
								dlg.dismiss();
					}
						}
					});
					training.setOnClickListener(new OnClickListener() {
						
						 
						public void onClick(View v) {		
							Connection
							.sendMessage(
									GameProtocol.CONNECTION_STAFF_SKILL_LEARN,
									ConstructData
											.getStaffSkillLearn(
													(byte) (2),
													(GameData.qualities[1].id),
													e.id));
							dlg.dismiss();
						}
					});
					
					/*// 使用道具
					String[] items = { "使用道具", "素质培训" };
					Builder builder = new AlertDialog.Builder(mContext);
					builder.setItems(items,
							new DialogInterface.OnClickListener() {

								 
								public void onClick(DialogInterface dialog,
										int which) {
									if (which == 0) {// 使用道具
										final Props propssForQuality = getPropsForQuality(GameData.qualities[1].itemTemplateId);
										// TODO
										if (propssForQuality == null) {
											// 提示没有道具，去商场购买
											buildMessageDialog(
													mContext,
													mContext.getResources()
															.getString(
																	R.string.to_buy_props))
													.show();
										} else {// 直接消耗
											Connection
													.sendMessage(
															GameProtocol.PROPS_USE,
															ConstructData
																	.useProps(
																			(byte) 1,
																			propssForQuality.id,
																			(byte) 2,
																			e.id,
																			propssForQuality.count));
										}

									} else if (which == 1) {// 查看员工详情
										// 素质培训
										Connection
												.sendMessage(
														GameProtocol.CONNECTION_STAFF_SKILL_LEARN,
														ConstructData
																.getStaffSkillLearn(
																		(byte) (2),
																		(GameData.qualities[1].id),
																		e.id));
									}
								}
							}).create().show();
*/
				}
			});

			LinearLayout quality3 = (LinearLayout) this
					.findViewById(R.id.employeeinfo_quality3);
			Button up3 = (Button) quality3
					.findViewById(R.id.employeeinfo_quality_item_up);
			up3.setVisibility(View.VISIBLE);
			up3.setOnClickListener(new OnClickListener() {

				public void onClick(View arg0) {
					final Props[] propsForSkill = getPropsForSkillLearn();// 用于学习技能的道具
					final AlertDialog dlg = new AlertDialog.Builder(EmployeeInfoActivity.this).create();
					dlg.show();					
					dlg.getWindow().setContentView(R.layout.employee_menu);
					Button use = (Button)dlg.findViewById(R.id.menu_use);
					Button training = (Button)dlg.findViewById(R.id.menu_training);
//					use.setTypeface(GameDefinition.face);
//					training.setTypeface(GameDefinition.face);
					use.setOnClickListener(new OnClickListener() {
						
						 
						public void onClick(View v) {
							// TODO Auto-generated method stub
							final Props propssForQuality = getPropsForQuality(GameData.qualities[2].itemTemplateId);
							// TODO
							if (propssForQuality == null) {
								// 提示没有道具，去商场购买
								buildMessageDialog(
										mContext,
										mContext.getResources()
												.getString(
														R.string.to_buy_props))
										.show();
								dlg.dismiss();
							} else {// 直接消耗
								Connection
								.sendMessage(
										GameProtocol.PROPS_USE,
										ConstructData
												.useProps(
														(byte) 1,
														propssForQuality.id,
														(byte) 2,
														e.id,
														propssForQuality.count));
								dlg.dismiss();
					}
						}
					});
					training.setOnClickListener(new OnClickListener() {
						
						 
						public void onClick(View v) {		
							Connection
							.sendMessage(
									GameProtocol.CONNECTION_STAFF_SKILL_LEARN,
									ConstructData
											.getStaffSkillLearn(
													(byte) (2),
													(GameData.qualities[2].id),
													e.id));
							dlg.dismiss();
						}
					});
					
				/*	// 使用道具
					String[] items = { "使用道具", "素质培训" };
					Builder builder = new AlertDialog.Builder(mContext);
					builder.setItems(items,
							new DialogInterface.OnClickListener() {

								 
								public void onClick(DialogInterface dialog,
										int which) {
									if (which == 0) {// 使用道具
										final Props propssForQuality = getPropsForQuality(GameData.qualities[2].itemTemplateId);
										// TODO
										if (propssForQuality == null) {
											// 提示没有道具，去商场购买
											buildMessageDialog(
													mContext,
													mContext.getResources()
															.getString(
																	R.string.to_buy_props))
													.show();
										} else {// 直接消耗
											Connection
													.sendMessage(
															GameProtocol.PROPS_USE,
															ConstructData
																	.useProps(
																			(byte) 1,
																			propssForQuality.id,
																			(byte) 2,
																			e.id,
																			propssForQuality.count));
										}

									} else if (which == 1) {// 查看员工详情
										// 素质培训
										Connection
												.sendMessage(
														GameProtocol.CONNECTION_STAFF_SKILL_LEARN,
														ConstructData
																.getStaffSkillLearn(
																		(byte) (2),
																		(GameData.qualities[2].id),
																		e.id));
									}
								}
							}).create().show();*/
				}

			}

			);

			LinearLayout quality4 = (LinearLayout) this
					.findViewById(R.id.employeeinfo_quality4);
			Button up4 = (Button) quality4
					.findViewById(R.id.employeeinfo_quality_item_up);
			up4.setVisibility(View.VISIBLE);
			up4.setOnClickListener(new OnClickListener() {

				public void onClick(View arg0) {
					final Props[] propsForSkill = getPropsForSkillLearn();// 用于学习技能的道具
					final AlertDialog dlg = new AlertDialog.Builder(EmployeeInfoActivity.this).create();
					dlg.show();					
					dlg.getWindow().setContentView(R.layout.employee_menu);
					Button use = (Button)dlg.findViewById(R.id.menu_use);
					Button training = (Button)dlg.findViewById(R.id.menu_training);
//					use.setTypeface(GameDefinition.face);
//					training.setTypeface(GameDefinition.face);
					use.setOnClickListener(new OnClickListener() {
						
						 
						public void onClick(View v) {
							// TODO Auto-generated method stub
							final Props propssForQuality = getPropsForQuality(GameData.qualities[3].itemTemplateId);
							// TODO
							if (propssForQuality == null) {
								// 提示没有道具，去商场购买
								buildMessageDialog(
										mContext,
										mContext.getResources()
												.getString(
														R.string.to_buy_props))
										.show();
								dlg.dismiss();
							} else {// 直接消耗
								Connection
								.sendMessage(
										GameProtocol.PROPS_USE,
										ConstructData
												.useProps(
														(byte) 1,
														propssForQuality.id,
														(byte) 2,
														e.id,
														propssForQuality.count));
								dlg.dismiss();
					}
						}
					});
					training.setOnClickListener(new OnClickListener() {
						
						 
						public void onClick(View v) {		
							Connection
							.sendMessage(
									GameProtocol.CONNECTION_STAFF_SKILL_LEARN,
									ConstructData
											.getStaffSkillLearn(
													(byte) (2),
													(GameData.qualities[3].id),
													e.id));
							dlg.dismiss();
						}
					});
					
				/*	// 使用道具
					String[] items = { "使用道具", "素质培训" };
					Builder builder = new AlertDialog.Builder(mContext);
					builder.setItems(items,
							new DialogInterface.OnClickListener() {

								 
								public void onClick(DialogInterface dialog,
										int which) {
									if (which == 0) {// 使用道具
										final Props propssForQuality = getPropsForQuality(GameData.qualities[3].itemTemplateId);
										// TODO
										if (propssForQuality == null) {
											// 提示没有道具，去商场购买
											buildMessageDialog(
													mContext,
													mContext.getResources()
															.getString(
																	R.string.to_buy_props))
													.show();
										} else {// 直接消耗
											Connection
													.sendMessage(
															GameProtocol.PROPS_USE,
															ConstructData
																	.useProps(
																			(byte) 1,
																			propssForQuality.id,
																			(byte) 2,
																			e.id,
																			propssForQuality.count));
										}

									} else if (which == 1) {// 查看员工详情
										// 素质培训
										Connection
												.sendMessage(
														GameProtocol.CONNECTION_STAFF_SKILL_LEARN,
														ConstructData
																.getStaffSkillLearn(
																		(byte) (2),
																		(GameData.qualities[3].id),
																		e.id));
									}
								}
							}).create().show();*/
				}

			}

			);

			LinearLayout quality5 = (LinearLayout) this
					.findViewById(R.id.employeeinfo_quality5);
			Button up5 = (Button) quality5
					.findViewById(R.id.employeeinfo_quality_item_up);
			up5.setVisibility(View.VISIBLE);
			up5.setOnClickListener(new OnClickListener() {

				public void onClick(View arg0) {
					final Props[] propsForSkill = getPropsForSkillLearn();// 用于学习技能的道具
					final AlertDialog dlg = new AlertDialog.Builder(EmployeeInfoActivity.this).create();
					dlg.show();					
					dlg.getWindow().setContentView(R.layout.employee_menu);
					Button use = (Button)dlg.findViewById(R.id.menu_use);
					Button training = (Button)dlg.findViewById(R.id.menu_training);
//					use.setTypeface(GameDefinition.face);
//					training.setTypeface(GameDefinition.face);
					use.setOnClickListener(new OnClickListener() {
						
						 
						public void onClick(View v) {
							// TODO Auto-generated method stub
							final Props propssForQuality = getPropsForQuality(GameData.qualities[4].itemTemplateId);
							// TODO
							if (propssForQuality == null) {
								// 提示没有道具，去商场购买
								buildMessageDialog(
										mContext,
										mContext.getResources()
												.getString(
														R.string.to_buy_props))
										.show();
								dlg.dismiss();
							} else {// 直接消耗
								Connection
								.sendMessage(
										GameProtocol.PROPS_USE,
										ConstructData
												.useProps(
														(byte) 1,
														propssForQuality.id,
														(byte) 2,
														e.id,
														propssForQuality.count));
								dlg.dismiss();
					}
						}
					});
					training.setOnClickListener(new OnClickListener() {
						
						 
						public void onClick(View v) {		
							Connection
							.sendMessage(
									GameProtocol.CONNECTION_STAFF_SKILL_LEARN,
									ConstructData
											.getStaffSkillLearn(
													(byte) (2),
													(GameData.qualities[4].id),
													e.id));
							dlg.dismiss();
						}
					});
					
				/*	// 使用道具
					String[] items = { "使用道具", "素质培训" };
					Builder builder = new AlertDialog.Builder(mContext);
					builder.setItems(items,
							new DialogInterface.OnClickListener() {

								 
								public void onClick(DialogInterface dialog,
										int which) {
									if (which == 0) {// 使用道具
										final Props propssForQuality = getPropsForQuality(GameData.qualities[4].itemTemplateId);
										// TODO
										if (propssForQuality == null) {
											// 提示没有道具，去商场购买
											buildMessageDialog(
													mContext,
													mContext.getResources()
															.getString(
																	R.string.to_buy_props))
													.show();
										} else {// 直接消耗
											Connection
													.sendMessage(
															GameProtocol.PROPS_USE,
															ConstructData
																	.useProps(
																			(byte) 1,
																			propssForQuality.id,
																			(byte) 2,
																			e.id,
																			propssForQuality.count));
										}

									} else if (which == 1) {// 查看员工详情
										// 素质培训
										Connection
												.sendMessage(
														GameProtocol.CONNECTION_STAFF_SKILL_LEARN,
														ConstructData
																.getStaffSkillLearn(
																		(byte) (2),
																		(GameData.qualities[4].id),
																		e.id));
									}
								}
							}).create().show();*/
				}

			}

			);

			LinearLayout quality6 = (LinearLayout) this
					.findViewById(R.id.employeeinfo_quality6);
			Button up6 = (Button) quality6
					.findViewById(R.id.employeeinfo_quality_item_up);
			up6.setVisibility(View.VISIBLE);
			up6.setOnClickListener(new OnClickListener() {

				public void onClick(View arg0) {
					final Props[] propsForSkill = getPropsForSkillLearn();// 用于学习技能的道具
					final AlertDialog dlg = new AlertDialog.Builder(EmployeeInfoActivity.this).create();
					dlg.show();					
					dlg.getWindow().setContentView(R.layout.employee_menu);
					Button use = (Button)dlg.findViewById(R.id.menu_use);
					Button training = (Button)dlg.findViewById(R.id.menu_training);
//					use.setTypeface(GameDefinition.face);
//					training.setTypeface(GameDefinition.face);
					use.setOnClickListener(new OnClickListener() {
						
						 
						public void onClick(View v) {
							// TODO Auto-generated method stub
							final Props propssForQuality = getPropsForQuality(GameData.qualities[5].itemTemplateId);
							// TODO
							if (propssForQuality == null) {
								// 提示没有道具，去商场购买
								buildMessageDialog(
										mContext,
										mContext.getResources()
												.getString(
														R.string.to_buy_props))
										.show();
								dlg.dismiss();
							} else {// 直接消耗
								Connection
								.sendMessage(
										GameProtocol.PROPS_USE,
										ConstructData
												.useProps(
														(byte) 1,
														propssForQuality.id,
														(byte) 2,
														e.id,
														propssForQuality.count));
								dlg.dismiss();
					}
						}
					});
					training.setOnClickListener(new OnClickListener() {
						
						 
						public void onClick(View v) {		
							Connection
							.sendMessage(
									GameProtocol.CONNECTION_STAFF_SKILL_LEARN,
									ConstructData
											.getStaffSkillLearn(
													(byte) (2),
													(GameData.qualities[5].id),
													e.id));
							dlg.dismiss();
						}
					});
					
				/*	// 使用道具
					String[] items = { "使用道具", "素质培训" };
					Builder builder = new AlertDialog.Builder(mContext);
					builder.setItems(items,
							new DialogInterface.OnClickListener() {

								 
								public void onClick(DialogInterface dialog,
										int which) {
									if (which == 0) {// 使用道具
										final Props propssForQuality = getPropsForQuality(GameData.qualities[5].itemTemplateId);
										// TODO
										if (propssForQuality == null) {
											// 提示没有道具，去商场购买
											buildMessageDialog(
													mContext,
													mContext.getResources()
															.getString(
																	R.string.to_buy_props))
													.show();
										} else {// 直接消耗
											Connection
													.sendMessage(
															GameProtocol.PROPS_USE,
															ConstructData
																	.useProps(
																			(byte) 1,
																			propssForQuality.id,
																			(byte) 2,
																			e.id,
																			propssForQuality.count));
										}

									} else if (which == 1) {// 查看员工详情
										// 素质培训
										Connection
												.sendMessage(
														GameProtocol.CONNECTION_STAFF_SKILL_LEARN,
														ConstructData
																.getStaffSkillLearn(
																		(byte) (2),
																		(GameData.qualities[5].id),
																		e.id));
									}
								}
							}).create().show();*/
				}

			}

			);

			LinearLayout quality7 = (LinearLayout) this
					.findViewById(R.id.employeeinfo_quality7);
			Button up7 = (Button) quality7
					.findViewById(R.id.employeeinfo_quality_item_up);
			up7.setVisibility(View.VISIBLE);
			up7.setOnClickListener(new OnClickListener() {

				public void onClick(View arg0) {
					final Props[] propsForSkill = getPropsForSkillLearn();// 用于学习技能的道具
					final AlertDialog dlg = new AlertDialog.Builder(EmployeeInfoActivity.this).create();
					dlg.show();					
					dlg.getWindow().setContentView(R.layout.employee_menu);
					Button use = (Button)dlg.findViewById(R.id.menu_use);
					Button training = (Button)dlg.findViewById(R.id.menu_training);
//					use.setTypeface(GameDefinition.face);
//					training.setTypeface(GameDefinition.face);
					use.setOnClickListener(new OnClickListener() {
						
						 
						public void onClick(View v) {
							// TODO Auto-generated method stub
							final Props propssForQuality = getPropsForQuality(GameData.qualities[6].itemTemplateId);
							// TODO
							if (propssForQuality == null) {
								// 提示没有道具，去商场购买
								buildMessageDialog(
										mContext,
										mContext.getResources()
												.getString(
														R.string.to_buy_props))
										.show();
								dlg.dismiss();
							} else {// 直接消耗
								Connection
								.sendMessage(
										GameProtocol.PROPS_USE,
										ConstructData
												.useProps(
														(byte) 1,
														propssForQuality.id,
														(byte) 2,
														e.id,
														propssForQuality.count));
								dlg.dismiss();
					}
						}
					});
					training.setOnClickListener(new OnClickListener() {
						
						 
						public void onClick(View v) {		
							Connection
							.sendMessage(
									GameProtocol.CONNECTION_STAFF_SKILL_LEARN,
									ConstructData
											.getStaffSkillLearn(
													(byte) (2),
													(GameData.qualities[6].id),
													e.id));
							dlg.dismiss();
						}
					});
				/*	
					// 使用道具
					String[] items = { "使用道具", "素质培训" };
					Builder builder = new AlertDialog.Builder(mContext);
					builder.setItems(items,
							new DialogInterface.OnClickListener() {

								 
								public void onClick(DialogInterface dialog,
										int which) {
									if (which == 0) {// 使用道具
										final Props propssForQuality = getPropsForQuality(GameData.qualities[6].itemTemplateId);
										// TODO
										if (propssForQuality == null) {
											// 提示没有道具，去商场购买
											buildMessageDialog(
													mContext,
													mContext.getResources()
															.getString(
																	R.string.to_buy_props))
													.show();
										} else {// 直接消耗
											Connection
													.sendMessage(
															GameProtocol.PROPS_USE,
															ConstructData
																	.useProps(
																			(byte) 1,
																			propssForQuality.id,
																			(byte) 2,
																			e.id,
																			propssForQuality.count));
										}

									} else if (which == 1) {// 查看员工详情
										// 素质培训
										Connection
												.sendMessage(
														GameProtocol.CONNECTION_STAFF_SKILL_LEARN,
														ConstructData
																.getStaffSkillLearn(
																		(byte) (2),
																		(GameData.qualities[6].id),
																		e.id));
									}
								}
							}).create().show();*/
				}

			}

			);

			LinearLayout quality8 = (LinearLayout) this
					.findViewById(R.id.employeeinfo_quality8);
			Button up8 = (Button) quality8
					.findViewById(R.id.employeeinfo_quality_item_up);
			up8.setVisibility(View.VISIBLE);
			up8.setOnClickListener(new OnClickListener() {

				public void onClick(View arg0) {
					final Props[] propsForSkill = getPropsForSkillLearn();// 用于学习技能的道具
					final AlertDialog dlg = new AlertDialog.Builder(EmployeeInfoActivity.this).create();
					dlg.show();					
					dlg.getWindow().setContentView(R.layout.employee_menu);
					Button use = (Button)dlg.findViewById(R.id.menu_use);
					Button training = (Button)dlg.findViewById(R.id.menu_training);
//					use.setTypeface(GameDefinition.face);
//					training.setTypeface(GameDefinition.face);
					use.setOnClickListener(new OnClickListener() {
						
						 
						public void onClick(View v) {
							// TODO Auto-generated method stub
							final Props propssForQuality = getPropsForQuality(GameData.qualities[7].itemTemplateId);
							// TODO
							if (propssForQuality == null) {
								// 提示没有道具，去商场购买
								buildMessageDialog(
										mContext,
										mContext.getResources()
												.getString(
														R.string.to_buy_props))
										.show();
								dlg.dismiss();
							} else {// 直接消耗
								Connection
								.sendMessage(
										GameProtocol.PROPS_USE,
										ConstructData
												.useProps(
														(byte) 1,
														propssForQuality.id,
														(byte) 2,
														e.id,
														propssForQuality.count));
								dlg.dismiss();
					}
						}
					});
					training.setOnClickListener(new OnClickListener() {
						
						 
						public void onClick(View v) {		
							Connection
							.sendMessage(
									GameProtocol.CONNECTION_STAFF_SKILL_LEARN,
									ConstructData
											.getStaffSkillLearn(
													(byte) (2),
													(GameData.qualities[7].id),
													e.id));
							dlg.dismiss();
						}
					});
					
				/*	// 使用道具
					String[] items = { "使用道具", "素质培训" };
					Builder builder = new AlertDialog.Builder(mContext);
					builder.setItems(items,
							new DialogInterface.OnClickListener() {

								 
								public void onClick(DialogInterface dialog,
										int which) {
									if (which == 0) {// 使用道具
										final Props propssForQuality = getPropsForQuality(GameData.qualities[7].itemTemplateId);
										// TODO
										if (propssForQuality == null) {
											// 提示没有道具，去商场购买
											buildMessageDialog(
													mContext,
													mContext.getResources()
															.getString(
																	R.string.to_buy_props))
													.show();
										} else {// 直接消耗
											Connection
													.sendMessage(
															GameProtocol.PROPS_USE,
															ConstructData
																	.useProps(
																			(byte) 1,
																			propssForQuality.id,
																			(byte) 2,
																			e.id,
																			propssForQuality.count));
										}

									} else if (which == 1) {// 查看员工详情
										// 素质培训
										Connection
												.sendMessage(
														GameProtocol.CONNECTION_STAFF_SKILL_LEARN,
														ConstructData
																.getStaffSkillLearn(
																		(byte) (2),
																		(GameData.qualities[7].id),
																		e.id));
									}
								}
							}).create().show();*/
				}

			}

			);

			LinearLayout quality9 = (LinearLayout) this
					.findViewById(R.id.employeeinfo_quality9);
			Button up9 = (Button) quality9
					.findViewById(R.id.employeeinfo_quality_item_up);
			up9.setVisibility(View.VISIBLE);
			up9.setOnClickListener(new OnClickListener() {

				public void onClick(View arg0) {
					final Props[] propsForSkill = getPropsForSkillLearn();// 用于学习技能的道具
					final AlertDialog dlg = new AlertDialog.Builder(EmployeeInfoActivity.this).create();
					dlg.show();					
					dlg.getWindow().setContentView(R.layout.employee_menu);
					Button use = (Button)dlg.findViewById(R.id.menu_use);
					Button training = (Button)dlg.findViewById(R.id.menu_training);
//					use.setTypeface(GameDefinition.face);
//					training.setTypeface(GameDefinition.face);
					use.setOnClickListener(new OnClickListener() {
						
						 
						public void onClick(View v) {
							// TODO Auto-generated method stub
							final Props propssForQuality = getPropsForQuality(GameData.qualities[8].itemTemplateId);
							// TODO
							if (propssForQuality == null) {
								// 提示没有道具，去商场购买
								buildMessageDialog(
										mContext,
										mContext.getResources()
												.getString(
														R.string.to_buy_props))
										.show();
								dlg.dismiss();
							} else {// 直接消耗
								Connection
								.sendMessage(
										GameProtocol.PROPS_USE,
										ConstructData
												.useProps(
														(byte) 1,
														propssForQuality.id,
														(byte) 2,
														e.id,
														propssForQuality.count));
								dlg.dismiss();
					}
						}
					});
					training.setOnClickListener(new OnClickListener() {
						
						 
						public void onClick(View v) {		
							Connection
							.sendMessage(
									GameProtocol.CONNECTION_STAFF_SKILL_LEARN,
									ConstructData
											.getStaffSkillLearn(
													(byte) (2),
													(GameData.qualities[8].id),
													e.id));
							dlg.dismiss();
						}
					});
					
					/*// 使用道具
					String[] items = { "使用道具", "素质培训" };
					Builder builder = new AlertDialog.Builder(mContext);
					builder.setItems(items,
							new DialogInterface.OnClickListener() {

								 
								public void onClick(DialogInterface dialog,
										int which) {
									if (which == 0) {// 使用道具
										final Props propssForQuality = getPropsForQuality(GameData.qualities[8].itemTemplateId);
										// TODO
										if (propssForQuality == null) {
											// 提示没有道具，去商场购买
											buildMessageDialog(
													mContext,
													mContext.getResources()
															.getString(
																	R.string.to_buy_props))
													.show();
										} else {// 直接消耗
											Connection
													.sendMessage(
															GameProtocol.PROPS_USE,
															ConstructData
																	.useProps(
																			(byte) 1,
																			propssForQuality.id,
																			(byte) 2,
																			e.id,
																			propssForQuality.count));
										}

									} else if (which == 1) {// 查看员工详情
										// 素质培训
										Connection
												.sendMessage(
														GameProtocol.CONNECTION_STAFF_SKILL_LEARN,
														ConstructData
																.getStaffSkillLearn(
																		(byte) (2),
																		(GameData.qualities[8].id),
																		e.id));
									}
								}
							}).create().show();*/
				}

			}

			);

			LinearLayout quality10 = (LinearLayout) this
					.findViewById(R.id.employeeinfo_quality10);
			Button up10 = (Button) quality10
					.findViewById(R.id.employeeinfo_quality_item_up);
			up10.setVisibility(View.VISIBLE);
			up10.setOnClickListener(new OnClickListener() {

				public void onClick(View arg0) {
					final Props[] propsForSkill = getPropsForSkillLearn();// 用于学习技能的道具
					final AlertDialog dlg = new AlertDialog.Builder(EmployeeInfoActivity.this).create();
					dlg.show();					
					dlg.getWindow().setContentView(R.layout.employee_menu);
					Button use = (Button)dlg.findViewById(R.id.menu_use);
					Button training = (Button)dlg.findViewById(R.id.menu_training);
//					use.setTypeface(GameDefinition.face);
//					training.setTypeface(GameDefinition.face);
					use.setOnClickListener(new OnClickListener() {
						
						 
						public void onClick(View v) {
							// TODO Auto-generated method stub
							final Props propssForQuality = getPropsForQuality(GameData.qualities[9].itemTemplateId);
							// TODO
							if (propssForQuality == null) {
								// 提示没有道具，去商场购买
								buildMessageDialog(
										mContext,
										mContext.getResources()
												.getString(
														R.string.to_buy_props))
										.show();
								dlg.dismiss();
							} else {// 直接消耗
								Connection
								.sendMessage(
										GameProtocol.PROPS_USE,
										ConstructData
												.useProps(
														(byte) 1,
														propssForQuality.id,
														(byte) 2,
														e.id,
														propssForQuality.count));
								dlg.dismiss();
					}
						}
					});
					training.setOnClickListener(new OnClickListener() {
						
						 
						public void onClick(View v) {		
							Connection
							.sendMessage(
									GameProtocol.CONNECTION_STAFF_SKILL_LEARN,
									ConstructData
											.getStaffSkillLearn(
													(byte) (2),
													(GameData.qualities[9].id),
													e.id));
							dlg.dismiss();
						}
					});
					/*
					// 使用道具
					String[] items = { "使用道具", "素质培训" };
					Builder builder = new AlertDialog.Builder(mContext);
					builder.setItems(items,
							new DialogInterface.OnClickListener() {

								 
								public void onClick(DialogInterface dialog,
										int which) {
									if (which == 0) {// 使用道具
										final Props propssForQuality = getPropsForQuality(GameData.qualities[9].itemTemplateId);
										// TODO
										if (propssForQuality == null) {
											// 提示没有道具，去商场购买
											buildMessageDialog(
													mContext,
													mContext.getResources()
															.getString(
																	R.string.to_buy_props))
													.show();
										} else {// 直接消耗
											Connection
													.sendMessage(
															GameProtocol.PROPS_USE,
															ConstructData
																	.useProps(
																			(byte) 1,
																			propssForQuality.id,
																			(byte) 2,
																			e.id,
																			propssForQuality.count));
										}

									} else if (which == 1) {// 查看员工详情
										// 素质培训
										Connection
												.sendMessage(
														GameProtocol.CONNECTION_STAFF_SKILL_LEARN,
														ConstructData
																.getStaffSkillLearn(
																		(byte) (2),
																		(GameData.qualities[9].id),
																		e.id));
									}
								}
							}).create().show();*/
				}
			}

			);
		} 
		else if (type == 2) 
		{
			e = (Employee) GameData.diglist.elementAt(index);

			RelativeLayout study1 = (RelativeLayout) this
					.findViewById(R.id.employeeinfo_study1);
			Button buttonstudy1 = (Button) study1
					.findViewById(R.id.employeeinfo_skill_item_buttonright);
			buttonstudy1.setVisibility(View.GONE);

			RelativeLayout study2 = (RelativeLayout) this
					.findViewById(R.id.employeeinfo_study2);
			Button buttonstudy2 = (Button) study2
					.findViewById(R.id.employeeinfo_skill_item_buttonright);
			buttonstudy2.setVisibility(View.GONE);

			RelativeLayout study3 = (RelativeLayout) this
					.findViewById(R.id.employeeinfo_study3);
			Button buttonstudy3 = (Button) study3
					.findViewById(R.id.employeeinfo_skill_item_buttonright);
			buttonstudy3.setVisibility(View.VISIBLE);
			buttonstudy3.setBackgroundResource(R.drawable.button_dig);
			buttonstudy3.setOnClickListener(new OnClickListener() 
			{

				public void onClick(View arg0) {
					;
					String[] item = new String[8];

					for (int i = 0; i < item.length; i++) {
						item[i] = MainActivity.resources
								.getString(R.string.employeeinfo_digcost)
								+ DiglistActivity.getPay(e, i + 1)
								+ MainActivity.resources
										.getString(R.string.employeeinfo_successrate)
								+ ((i + 1) * 10) + "%";
					}
					final  AlertDialog dlg2 = new AlertDialog.Builder(EmployeeInfoActivity.this).create();
					dlg2.show();
					dlg2.getWindow().setContentView(R.layout.mapmenu3_list);
					ListView lv = (ListView)dlg2.findViewById(R.id.mapmenu3_list);				
					ArrayList<HashMap<String, String>>list= new  ArrayList<HashMap<String,String>>();
					HashMap<String, String> map;
					for (int i = 0; i < item.length; i++) {
						//	shop[i] = GameData.corporation.shop[i].name;
							 map = new HashMap<String, String>();						 
							 map.put("name", item[i]);
							 list.add(map);
						}

						 SimpleAdapter listAdapter = new SimpleAdapter(EmployeeInfoActivity.this,list,   
					                R.layout.mapmenu3_item, new String[] {"name"},   
					                new int[] {R.id.mapmenu3_list_text});   
				        lv.setAdapter(listAdapter);
					    lv.setOnItemClickListener(new OnItemClickListener() {

							 
							public void onItemClick(AdapterView<?> parent,
									View view, int position, long id) {
								// TODO Auto-generated method stub
								Connection.sendMessage(
										GameProtocol.REQ_FINDEMPLOY,
										ConstructData.get_earchPeople(e.id,
												DiglistActivity.getPay(e,
														position + 1)));
							}
						});
					/*
					Builder builder = new AlertDialog.Builder(
							EmployeeInfoActivity.this);

					builder.setItems(item,
							new DialogInterface.OnClickListener() {

								public void onClick(DialogInterface arg0,
										int index) {
									Connection.sendMessage(
											GameProtocol.REQ_FINDEMPLOY,
											ConstructData.get_earchPeople(e.id,
													DiglistActivity.getPay(e,
															index + 1)));
								}

							}

					).create().show();*/
				}
			});

			LinearLayout quality1 = (LinearLayout) this
					.findViewById(R.id.employeeinfo_quality1);
			Button up1 = (Button) quality1
					.findViewById(R.id.employeeinfo_quality_item_up);
			up1.setVisibility(View.GONE);

			LinearLayout quality2 = (LinearLayout) this
					.findViewById(R.id.employeeinfo_quality2);
			Button up2 = (Button) quality2
					.findViewById(R.id.employeeinfo_quality_item_up);
			up2.setVisibility(View.GONE);

			LinearLayout quality3 = (LinearLayout) this
					.findViewById(R.id.employeeinfo_quality3);
			Button up3 = (Button) quality3
					.findViewById(R.id.employeeinfo_quality_item_up);
			up3.setVisibility(View.GONE);

			LinearLayout quality4 = (LinearLayout) this
					.findViewById(R.id.employeeinfo_quality4);
			Button up4 = (Button) quality4
					.findViewById(R.id.employeeinfo_quality_item_up);
			up4.setVisibility(View.GONE);

			LinearLayout quality5 = (LinearLayout) this
					.findViewById(R.id.employeeinfo_quality5);
			Button up5 = (Button) quality5
					.findViewById(R.id.employeeinfo_quality_item_up);
			up5.setVisibility(View.GONE);

			LinearLayout quality6 = (LinearLayout) this
					.findViewById(R.id.employeeinfo_quality6);
			Button up6 = (Button) quality6
					.findViewById(R.id.employeeinfo_quality_item_up);
			up6.setVisibility(View.GONE);

			LinearLayout quality7 = (LinearLayout) this
					.findViewById(R.id.employeeinfo_quality7);
			Button up7 = (Button) quality7
					.findViewById(R.id.employeeinfo_quality_item_up);
			up7.setVisibility(View.GONE);

			LinearLayout quality8 = (LinearLayout) this
					.findViewById(R.id.employeeinfo_quality8);
			Button up8 = (Button) quality8
					.findViewById(R.id.employeeinfo_quality_item_up);
			up8.setVisibility(View.GONE);

			LinearLayout quality9 = (LinearLayout) this
					.findViewById(R.id.employeeinfo_quality9);
			Button up9 = (Button) quality9
					.findViewById(R.id.employeeinfo_quality_item_up);
			up9.setVisibility(View.GONE);

			LinearLayout quality10 = (LinearLayout) this
					.findViewById(R.id.employeeinfo_quality10);
			Button up10 = (Button) quality10
					.findViewById(R.id.employeeinfo_quality_item_up);
			up10.setVisibility(View.GONE);

		} else if (type == 3) 
		{
			e = (Employee) GameData.diglist.elementAt(index);
		}

		e.addDataChangeListener(this);
		loadData();

	}

	

	public void OnDataChange(Bundle bundle) {
		long id = bundle.getLong("id");

		e = GameData.getEmployeeById1(id);

		loadData();

	}

	public void loadData() {
		SpriteView sv = (SpriteView) this.findViewById(R.id.employeeinfo_head);

		sv.setSeries(e.sex);

		TextView name = (TextView) this.findViewById(R.id.employeeinfo_name);

		name.setText(MainActivity.resources
				.getString(R.string.employeeinfo_name) + e.name);

		TextView duty = (TextView) this.findViewById(R.id.employeeinfo_duty);

		duty.setText(MainActivity.resources
				.getString(R.string.employeeinfo_position) + e.dutyName[e.duty]);

		TextView salary = (TextView) this
				.findViewById(R.id.employeeinfo_salary);

		salary.setText(MainActivity.resources
				.getString(R.string.employeeinfo_salary) + e.salary);

		Button level = (Button) this
				.findViewById(R.id.employeeinfo_button_level);

		Button loyalty = (Button) this
				.findViewById(R.id.employeeinfo_button_loyalty);

		Button ability = (Button) this
				.findViewById(R.id.employeeinfo_button_ability);

		Button exprience = (Button) this
				.findViewById(R.id.employeeinfo_button_exprience);

		Button physical = (Button) this
				.findViewById(R.id.employeeinfo_button_physical);

		level.setText(String.valueOf(e.level));

		loyalty.setText(String.valueOf(e.loyalty) + "/" + e.maxLoyalty);

		ability.setText(String.valueOf(e.ability) + "/" + e.maxAbility);

		exprience.setText(String.valueOf(e.experence) + "/" + e.maxExperence);

		physical.setText(String.valueOf(e.strength) + "/" + e.maxExperence);

		RelativeLayout study1 = (RelativeLayout) this
				.findViewById(R.id.employeeinfo_study1);

		((TextView) study1.findViewById(R.id.employeeinfo_skill_item_textleft))
				.setText(MainActivity.resources
						.getString(R.string.employeeinfo_skill_1));
		((TextView) study1
				.findViewById(R.id.employeeinfo_skill_item_textcenter))
				.setText(e.getSkillName(0, MainActivity.resources
						.getString(R.string.employeeinfo_vacancy)));

		Button buttonstudy1 = (Button) study1
				.findViewById(R.id.employeeinfo_skill_item_buttonright);

		if (e.getSkillName(0,
				MainActivity.resources.getString(R.string.employeeinfo_vacancy))
				.equals(MainActivity.resources
						.getString(R.string.employeeinfo_vacancy))) {
			buttonstudy1.setBackgroundResource(R.drawable.button_study);
		} else {
			// buttonstudy1.setBackgroundResource(R.drawable.button_change);
			buttonstudy1.setVisibility(View.GONE);
		}

		RelativeLayout study2 = (RelativeLayout) this
				.findViewById(R.id.employeeinfo_study2);
		((TextView) study2.findViewById(R.id.employeeinfo_skill_item_textleft))
				.setText(MainActivity.resources
						.getString(R.string.employeeinfo_skill_2));
		((TextView) study2
				.findViewById(R.id.employeeinfo_skill_item_textcenter))
				.setText(e.getSkillName(1, MainActivity.resources
						.getString(R.string.employeeinfo_vacancy)));
		if (e.getSkillName(1,
				MainActivity.resources.getString(R.string.employeeinfo_vacancy))
				.equals(MainActivity.resources
						.getString(R.string.employeeinfo_vacancy))) {
			((Button) study2
					.findViewById(R.id.employeeinfo_skill_item_buttonright))
					.setBackgroundResource(R.drawable.button_study);
		} else {
			((Button) study2
					.findViewById(R.id.employeeinfo_skill_item_buttonright))
					.setVisibility(View.GONE);

		}

		if (type == 1) {
			RelativeLayout study3 = (RelativeLayout) this
					.findViewById(R.id.employeeinfo_study3);
			((TextView) study3
					.findViewById(R.id.employeeinfo_skill_item_textleft))
					.setText(MainActivity.resources
							.getString(R.string.employeeinfo_skill_3));
			((TextView) study3
					.findViewById(R.id.employeeinfo_skill_item_textcenter))
					.setText(e.getSkillName(2, MainActivity.resources
							.getString(R.string.employeeinfo_vacancy)));
			if (e.getSkillName(
					2,
					MainActivity.resources
							.getString(R.string.employeeinfo_vacancy)).equals(
					MainActivity.resources
							.getString(R.string.employeeinfo_vacancy))) {
				((Button) study3
						.findViewById(R.id.employeeinfo_skill_item_buttonright))
						.setBackgroundResource(R.drawable.button_study);
			} else {
				((Button) study3
						.findViewById(R.id.employeeinfo_skill_item_buttonright))
						.setVisibility(View.GONE);
			}
		}

		LinearLayout quality1 = (LinearLayout) this
				.findViewById(R.id.employeeinfo_quality1);
		TextView qualityname1 = (TextView) quality1
				.findViewById(R.id.employeeinfo_quality_item_name);
		qualityname1.setText(GameData.qualities[0].name + ":");
		setStar(quality1, e.getQualityLevel(GameData.qualities[0].id, 0));

		LinearLayout quality2 = (LinearLayout) this
				.findViewById(R.id.employeeinfo_quality2);
		TextView qualityname2 = (TextView) quality2
				.findViewById(R.id.employeeinfo_quality_item_name);
		qualityname2.setText(GameData.qualities[1].name + ":");
		setStar(quality2, e.getQualityLevel(GameData.qualities[1].id, 0));

		LinearLayout quality3 = (LinearLayout) this
				.findViewById(R.id.employeeinfo_quality3);
		TextView qualityname3 = (TextView) quality3
				.findViewById(R.id.employeeinfo_quality_item_name);
		qualityname3.setText(GameData.qualities[2].name + ":");
		setStar(quality3, e.getQualityLevel(GameData.qualities[2].id, 0));

		LinearLayout quality4 = (LinearLayout) this
				.findViewById(R.id.employeeinfo_quality4);
		TextView qualityname4 = (TextView) quality4
				.findViewById(R.id.employeeinfo_quality_item_name);
		qualityname4.setText(GameData.qualities[3].name + ":");
		setStar(quality4, e.getQualityLevel(GameData.qualities[3].id, 0));

		LinearLayout quality5 = (LinearLayout) this
				.findViewById(R.id.employeeinfo_quality5);
		TextView qualityname5 = (TextView) quality5
				.findViewById(R.id.employeeinfo_quality_item_name);
		qualityname5.setText(GameData.qualities[4].name + ":");
		setStar(quality5, e.getQualityLevel(GameData.qualities[4].id, 0));

		LinearLayout quality6 = (LinearLayout) this
				.findViewById(R.id.employeeinfo_quality6);
		TextView qualityname6 = (TextView) quality6
				.findViewById(R.id.employeeinfo_quality_item_name);
		qualityname6.setText(GameData.qualities[5].name + ":");
		setStar(quality6, e.getQualityLevel(GameData.qualities[5].id, 0));

		LinearLayout quality7 = (LinearLayout) this
				.findViewById(R.id.employeeinfo_quality7);
		TextView qualityname7 = (TextView) quality7
				.findViewById(R.id.employeeinfo_quality_item_name);
		qualityname7.setText(GameData.qualities[6].name + ":");
		setStar(quality7, e.getQualityLevel(GameData.qualities[6].id, 0));

		LinearLayout quality8 = (LinearLayout) this
				.findViewById(R.id.employeeinfo_quality8);
		TextView qualityname8 = (TextView) quality8
				.findViewById(R.id.employeeinfo_quality_item_name);
		qualityname8.setText(GameData.qualities[7].name + ":");
		setStar(quality8, e.getQualityLevel(GameData.qualities[7].id, 0));

		LinearLayout quality9 = (LinearLayout) this
				.findViewById(R.id.employeeinfo_quality9);
		TextView qualityname9 = (TextView) quality9
				.findViewById(R.id.employeeinfo_quality_item_name);
		qualityname9.setText(GameData.qualities[8].name + ":");
		setStar(quality9, e.getQualityLevel(GameData.qualities[8].id, 0));

		LinearLayout quality10 = (LinearLayout) this
				.findViewById(R.id.employeeinfo_quality10);
		TextView qualityname10 = (TextView) quality10
				.findViewById(R.id.employeeinfo_quality_item_name);
		qualityname10.setText(GameData.qualities[9].name + ":");
		setStar(quality10, e.getQualityLevel(GameData.qualities[9].id, 0));

	}

	private void setStar(LinearLayout layout, int level) {
		for (int i = 0; i < level; i++) {
			if (i == 0) {
				ImageView star1 = (ImageView) layout
						.findViewById(R.id.employeeinfo_quality_item_star1);

				star1.setBackgroundResource(R.drawable.star1);

			} else if (i == 1) {
				ImageView star2 = (ImageView) layout
						.findViewById(R.id.employeeinfo_quality_item_star2);
				star2.setBackgroundResource(R.drawable.star1);
			} else if (i == 2) {
				ImageView star3 = (ImageView) layout
						.findViewById(R.id.employeeinfo_quality_item_star3);
				star3.setBackgroundResource(R.drawable.star1);
			} else if (i == 3) {
				ImageView star4 = (ImageView) layout
						.findViewById(R.id.employeeinfo_quality_item_star4);
				star4.setBackgroundResource(R.drawable.star1);
			} else if (i == 4) {
				ImageView star5 = (ImageView) layout
						.findViewById(R.id.employeeinfo_quality_item_star5);
				star5.setBackgroundResource(R.drawable.star1);
			}
		}
	}

	private void buildAlertDiagForSkillLearn() {
		String skillname[] = new String[GameData.skills.length];
		for (int i = 0; i < skillname.length; i++) {
			skillname[i] = GameData.skills[i].name;
		}
		Builder builder = new AlertDialog.Builder(EmployeeInfoActivity.this);
		builder.setItems(skillname, new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int index) {
				Connection.sendMessage(
						GameProtocol.CONNECTION_STAFF_SKILL_LEARN,
						ConstructData.getStaffSkillLearn((byte) (1),
								(GameData.skills[index].id), e.id));
			}
		}).create().show();
	}

	

	/**
	 * 获取技能培训的道具
	 * 
	 * @return Props[]
	 */
	private Props[] getPropsForSkillLearn() {
		Props[] propss;
		Props[] temps= new Props[GameData.props.length];// 拥有的道具
		if (GameData.props != null) {
			int i = 0;
			int j = 0;
			while (true) {
				if(GameData.props.length!=0){
				if (isUsedForSkillLearn(GameData.props[i])) {
					temps[j] = GameData.props[i];
					j++;
				}}
				i++;
				if (i == GameData.props.length)
					break;
			}
			propss = new Props[j];
			System.arraycopy(temps, 0, propss, 0, j);
			if (j > 0) {
				return propss;
			}
		}
		return null;

	}

	/**
	 * 判断道具是否用于学习技能
	 * 
	 * @param props
	 * @return boolean
	 */
	private boolean isUsedForSkillLearn(Props props) {
		long[] itemsTemplateIds = new long[GameData.skills.length];// 所有有关于技能的道具模板ID
		for (int i = 0; i < itemsTemplateIds.length; i++) {
			if (props.templateId == GameData.skills[i].itemTemplateId)
				return true;
		}
		return false;
	}

	/**
	 * 获取素质提升的道具
	 * 
	 * @return Props[]
	 */
	private Props getPropsForQuality(long itemTemplateId) {
		Log.i("Log", "itemTemplateId:" + itemTemplateId);
		if (GameData.props != null) {
			for (int i = 0, len = GameData.props.length; i < len; i++) {
				Log.i("Log", "GameData.props[i].templateId:"
						+ GameData.props[i].templateId);
				if (itemTemplateId == GameData.props[i].templateId) {
					return GameData.props[i];
				}
			}
		}
		return null;
	}

	/**
	 * 获取道具的名称
	 * 
	 * @param propss
	 * @return
	 */
	private String[] getPropsName(Props[] propss) {
		String[] propssName = new String[propss.length];
		for (int i = 0; i < propssName.length; i++) {
			propssName[i] = propss[i].name;
		}
		return propssName;
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
