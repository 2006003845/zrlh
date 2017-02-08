package com.zrong.Android.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
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
import com.zrong.Android.Util.ImageDownloadTask;
import com.zrong.Android.Util.Music;
import com.zrong.Android.Util.SystemAPI;
import com.zrong.Android.View.FreshManLead;
import com.zrong.Android.View.SpriteView;
import com.zrong.Android.element.Employee;
import com.zrong.Android.element.MapBuilding;
import com.zrong.Android.element.Shop;
import com.zrong.Android.game.Connection;
import com.zrong.Android.game.ConstructData;
import com.zrong.Android.game.GameData;
import com.zrong.Android.game.GameDefinition;
import com.zrong.Android.game.GameGroupControl;
import com.zrong.Android.online.network.GameProtocol;

public class ShopInfoActivity extends GameActivity implements
		DataChangeListener {

	private int type = 0;
	public static ShopInfoActivity mContext = null;

	private Shop shop = null;
	
	private MapBuilding mbuilding = null;

	private int selectIndex = 0;
	public static long shop_ID;
	public static long player_ID;

	private TextView income, bonus, name, coordinates, flow, inventory,
			employees, quality, popularity, morale, flow_button,

			employee_button,purchase,strategy_button;


	private SpriteView buyArrows, employeeArrows;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		Bundle bundle = this.getIntent().getExtras();

		setContentView(R.layout.shopinfo);
		
		type = bundle.getInt("type");

		selectIndex = bundle.getInt("selectIndex");

		GameData.corporation.listener = this;

		int resId = 0;

		if (type == 0)// 普通建筑
		{
			mbuilding = GameData.build[selectIndex].mb;

			resId = GameData.getUIResId(mbuilding.trade_id);
		} else// 我的店铺
		{
			shop = GameData.corporation.shop[selectIndex];

			resId = GameData.getUIResId(shop.trade_id);
		}

		ImageView imageview = (ImageView) this
				.findViewById(R.id.shopinfo_shopImageview);

		
		new ImageDownloadTask().execute(imageview, resId, 811);

		Button cancel = (Button) this.findViewById(R.id.shopinfo_button_cancel);

		Button returnback = (Button) this
				.findViewById(R.id.shopinfo_button_returnback);

		cancel.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {

				ShopInfoActivity.this.finish();
			}

		});

		returnback.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				ShopInfoActivity.this.finish();
			}
		});

		income = (TextView) this.findViewById(R.id.shopinfo_income);

		bonus = (TextView) this.findViewById(R.id.shopinfo_bonus);

		name = (TextView) this.findViewById(R.id.shopinfo_shopname);

		coordinates = (TextView) this.findViewById(R.id.shopinfo_coordinates);

		flow = (TextView) this.findViewById(R.id.shopinfo_flow);

		inventory = (TextView) this.findViewById(R.id.shopinfo_inventory);

		employees = (TextView) this.findViewById(R.id.shopinfo_employees);

		quality = (TextView) this.findViewById(R.id.shopinfo_quality);

		popularity = (TextView) this.findViewById(R.id.shopinfo_popularity);

		morale = (TextView) this.findViewById(R.id.shopinfo_morale);

		flow_button = (Button) this.findViewById(R.id.shopinfo_flow_button);

		employee_button = (Button) this
				.findViewById(R.id.shopinfo_employees_button);
		purchase = (Button) this.findViewById(R.id.shopinfo_purchase_button);
	    strategy_button = (Button) this
	    .findViewById(R.id.shopinfo_strategy_button);

		if (shop == null)// 不是我店铺,按普通建筑处理
		{
			// income.setVisibility(View.GONE);
			// this.findViewById(R.id.shopinfo_parting_line1).setVisibility(View.GONE);
			income.setText(MainActivity.resources
					.getString(R.string.shopinfo_income));

			// bonus.setVisibility(View.GONE);
			// this.findViewById(R.id.shopinfo_parting_line2).setVisibility(View.GONE);
			bonus.setText(MainActivity.resources
					.getString(R.string.shopinfo_incomeadd));

			// flow.setVisibility(View.GONE);
			// flow_button.setVisibility(View.GONE);
			// this.findViewById(R.id.shopinfo_parting_line5).setVisibility(View.GONE);
			flow.setText(MainActivity.resources
					.getString(R.string.shopinfo_customer_flow));
			strategy_button.setBackgroundResource(R.drawable.usestrategy);
		    //strategy_button.setVisibility(View.GONE);
			Button see = (Button) mContext.findViewById(R.id.shopinfo_see_button);
			see.setVisibility(View.GONE);
			strategy_button.setOnClickListener(new OnClickListener() {
				
				 
				public void onClick(View v) {
					if (GameData.corporation.shop.length <= 0) {
						Toast toast = Toast.makeText(MainActivity.mContext,
								MainActivity.resources
										.getString(R.string.shopinfo_toast2),
								Toast.LENGTH_LONG);
						toast.show();
						return;
					}else if(GameData.corporation.employee.length <= 0){
						Toast toast = Toast.makeText(MainActivity.mContext,
								MainActivity.resources
										.getString(R.string.shopinfo_toast3),
								Toast.LENGTH_LONG);
						toast.show();
						return;
					}else{
					// TODO Auto-generated method stub
					//进行界面的跳转，或是发包，跳到策划部界面
					Shop shop = new Shop();
					shop_ID=shop.id;
					player_ID = mbuilding.playerId;
					Intent intent = new Intent();
					intent.putExtra("jumpshop", "isFromShopinfo");	
					mContext.Activitychange(PlanningDeptActivity.class, intent);
				}
					}
			});
			flow_button.setBackgroundResource(R.drawable.button_recruit);

			flow_button.setOnClickListener(new OnClickListener() {
				public void onClick(View arg0) {
					if (GameData.corporation.shop.length <= 0) {
						Toast toast = Toast.makeText(MainActivity.mContext,
								MainActivity.resources
										.getString(R.string.shopinfo_toast),
								Toast.LENGTH_LONG);
						toast.show();
						return;
					}

					final Vector v = new Vector();

					for (int i = 0; i < GameData.corporation.shop.length; i++) {
						if (GameData.corporation.shop[i].employees.length > 0
								&& GameData.corporation.shop[i].trade_id == mbuilding.trade_id
								&& !GameData.corporation.shop[i].isRecruiting()
								&& GameData.mapIds[GameData.mapIdIndex] == GameData.corporation.shop[i].map_id) {
							v.addElement(GameData.corporation.shop[i]);
						}
					}

					if (v.size() <= 0) {
						Toast toast = Toast.makeText(MainActivity.mContext,
								MainActivity.resources
										.getString(R.string.shopinfo_toast1),
								Toast.LENGTH_LONG);
						toast.show();
						return;
					}

					String shopName[] = new String[v.size()];

					for (int i = 0; i < shopName.length; i++) {
						shopName[i] = ((Shop) v.elementAt(i)).name;
					}
					final AlertDialog dlg2 = new AlertDialog.Builder(ShopInfoActivity.this).create();
					dlg2.show();
					dlg2.getWindow().setContentView(R.layout.pm_emoloyeelist);
			    	ListView lv = (ListView)dlg2.findViewById(R.id.pm_emoloyeelist_listview);
			    	lv.setLayoutParams(new RelativeLayout.LayoutParams(200,RelativeLayout.LayoutParams.WRAP_CONTENT));
					ArrayList<HashMap<String, String>>list= new  ArrayList<HashMap<String,String>>();
					HashMap<String, String> map;
					if (shopName.length == 0) {
						dlg2.dismiss();
						Toast.makeText(MainActivity.mContext, MainActivity.resources.getString(R.string.shopinfo_toast1),
								Toast.LENGTH_LONG).show();
						
					}
					else if(shopName.length>0){
					for (int i = 0; i < shopName.length; i++) {
						map = new HashMap<String, String>();
						map.put("name", shopName[i]);
						list.add(map);
					}
					 SimpleAdapter listAdapter = new SimpleAdapter(MainActivity.mContext,list,   
				                R.layout.pm_employeelist_item, new String[] {"name"},   
				                new int[] {R.id.pm_employeelist_item_name});   
			        lv.setAdapter(listAdapter);
					lv.setOnItemClickListener(new OnItemClickListener() {

						 
						public void onItemClick(AdapterView<?> parent,
								View view, int position, long id) {
							// TODO Auto-generated method stub
							Shop s = ((Shop) v.elementAt(position));
							Connection
							.sendMessage(
									GameProtocol.CONNECTION_Shop_Recruit_Req,
									ConstructData
											.getShop_Recruit_Req(
													s.buildingId,
													mbuilding.id,
													s.employees[0].id,
													(short) s.map_id));
							dlg2.dismiss();
						}
					});
					}
					
					
					
				/*	Builder builder = new AlertDialog.Builder(
							ShopInfoActivity.this);

					builder.setItems(shopName,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int index) {
									Shop s = ((Shop) v.elementAt(index));
									Connection
											.sendMessage(
													GameProtocol.CONNECTION_Shop_Recruit_Req,
													ConstructData
															.getShop_Recruit_Req(
																	s.buildingId,
																	mbuilding.id,
																	s.employees[0].id,
																	(short) s.map_id));
								}
							}).create().show();
*/
				}
			});

			// inventory.setVisibility(View.GONE);
			// this.findViewById(R.id.shopinfo_parting_line6).setVisibility(View.GONE);
			inventory.setText(MainActivity.resources
					.getString(R.string.shopinfo_store));

			// employees.setVisibility(View.GONE);
			employee_button.setVisibility(View.GONE);
			// changed
			purchase.setVisibility(View.GONE);
			// this.findViewById(R.id.shopinfo_parting_line7).setVisibility(View.GONE);
			employees.setText(MainActivity.resources
					.getString(R.string.shopinfo_shopleader));

			// quality.setVisibility(View.GONE);
			// this.findViewById(R.id.shopinfo_parting_line8).setVisibility(View.GONE);
			quality.setText(MainActivity.resources
					.getString(R.string.shopinfo_shopquality));

			// popularity.setVisibility(View.GONE);
			// this.findViewById(R.id.shopinfo_parting_line9).setVisibility(View.GONE);
			popularity.setText(MainActivity.resources
					.getString(R.string.shopinfo_shopawareness));

			// morale.setVisibility(View.GONE);
			// this.findViewById(R.id.shopinfo_parting_line10).setVisibility(View.GONE);
			morale.setText(MainActivity.resources
					.getString(R.string.shopinfo_shopmoral));

			// manager.setVisibility(View.GONE);
//TODO
			name.setText(MainActivity.resources
					.getString(R.string.shopinfo_shopname) + mbuilding.name);
			//mbuilding.playerId

			coordinates.setText(MainActivity.resources
					.getString(R.string.shopinfo_shoplocation)
					+ " X:"
					+ mbuilding.cityX + " Y:" + mbuilding.cityY);

		} else {
			
            strategy_button.setVisibility(View.GONE);
			name.setText(MainActivity.resources
					.getString(R.string.shopinfo_shopname) + shop.name);

			coordinates.setText(MainActivity.resources
					.getString(R.string.shopinfo_shoplocation)
					+ " X:"
					+ shop.cityX + " Y:" + shop.cityY);
			// 添加点击事件
			Button see = (Button) mContext.findViewById(R.id.shopinfo_see_button);
			//TODO
			see.setOnClickListener(new OnClickListener() {
				 
				public void onClick(View v) {
					GameData.frushOriginalFocus();// 把当前focus值设置为初始focus值
					GameData.isResetMap = true;
					GameData.mapIdIndexBack = (short) SystemAPI
							.getShortArrayIndex(
									GameData.mapIds,
									shop.map_id);
					Connection.sendMessage(
							GameProtocol.CONNECTION_SEND_MAPAROUND_SHOP,
							ConstructData.getMapAroundShop(shop.map_id,
									shop.cityX, shop.cityY, (byte) 0,
									(byte) (GameData.ARRAY_LENTH / 2)));
					Vector v2 = new Vector(5, 5);
					v2.addElement(GameProtocol.CONNECTION_SEND_MAPAROUND_SHOP);
					GameGroupControl.gameGroupControl.setGameStatus(
							GameDefinition.Game_Loading, v2);
					mContext.finish();
					if (ShopListActivity.mContext != null) 
					{
						ShopListActivity.mContext.finish();
					}
					if (MapmainMenuActivity.mContext != null) 
					{
						MapmainMenuActivity.mContext.finish();
					}
				}
			});

			// income.append(String.valueOf(shop.incomeToday));
			income.setText(MainActivity.resources
					.getString(R.string.shopinfo_income1) + shop.incomeToday);

			// bonus.append(String.valueOf(shop.gain+100)+"%");
			bonus.setText(MainActivity.resources
					.getString(R.string.shopinfo_incomeadd1)
					+ (shop.gain + 100) + "%");

			// flow.append(String.valueOf(shop.flowGain));
			flow.setText(MainActivity.resources
					.getString(R.string.shopinfo_customer_flow1)
					+ shop.flowGain);

			if (shop.isRecruited())// 被招揽
			{
				flow_button.setBackgroundResource(R.drawable.button_dispel);

				flow_button.setOnClickListener(new OnClickListener() {

					public void onClick(View arg0) {
						Connection.sendMessage(
								GameProtocol.CONNECTION_Shop_UnRecruit_Req,
								ConstructData.getShop_UnRecruit_Req((byte)1,
										GameData.player.id, shop.buildingId));
						
					}
				}
				);
			} 
			else 
			{

				if (shop.isRecruiting())// 取消招揽
				{
					flow_button
							.setBackgroundResource(R.drawable.button_cancel_recruit);
					flow_button.setOnClickListener(new OnClickListener() {
						public void onClick(View view) {

							Connection.sendMessage(
									GameProtocol.CONNECTION_Shop_UnRecruit_Req,
									ConstructData.getShop_UnRecruit_Req((byte)1,
											GameData.player.id, shop.buildingId));
							
						}
					});

				} 
				else// 招揽
				{

					flow_button
							.setBackgroundResource(R.drawable.button_recruit);

					flow_button.setOnClickListener(new OnClickListener() {
						public void onClick(View arg0) {
						/*	if(GameData.corporation. ==0){
								Toast toast = Toast.makeText(MainActivity.mContext, MainActivity.resources
										.getString(R.string.shopinfo_toast1), Toast.LENGTH_SHORT);
								toast.show();
							}*/
							Connection
									.sendMessage(
											GameProtocol.CONNECTION_SHOP_Avai_Recruit_List_req,
											ConstructData.getAvaiRecruit(
													(byte) 0, 0, shop.trade_id,
													shop.map_id, 0, (byte) 50));
							GameData.RecruitedShopId = shop.id;
						}
						
					});
				}
			}

			if (shop.employees.length > 0) {
				Log.i("Log", "调配=========1");
				employee_button.setBackgroundResource(R.drawable.outgoing);

				employee_button.setOnClickListener(new OnClickListener() {

					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						Connection.sendMessage(
								GameProtocol.CONNECTION_SEND_STAFF_OP_REQ,
								ConstructData.Staff_Appoint_Req((byte) 1,
										shop.id, (byte) 0,
										new long[] { shop.employees[0].id }));
					}
				});

			} else {
				Log.i("Log", "调配=========2");
				employee_button.setBackgroundResource(R.drawable.deployment);

				employee_button.setOnClickListener(new OnClickListener() {

					public void onClick(View arg0) {
						final Vector vector = new Vector();
						Log.i("Log", "调配=========2--onClick");
						for (int i = 0; i < GameData.corporation.employee.length; i++) {
							if (GameData.corporation.employee[i].department <= 0) {
								vector.addElement(i);
							}
						}
						// 新手引导部分
						Log.i("Log", "调配2=========" + FreshManLead.caseId);
						if (GameData.isFreshMan && FreshManLead.caseId == 10
								&& employeeArrows != null) {
							Log.i("Log", "ShopInfo==调配店长");
							doPromoter(FreshManLead.prompt14);
							employeeArrows.setVisibility(View.GONE);
							FreshManLead.caseId++;
						}

						String[] name = new String[vector.size()];
						if(name.length == 0){
							Toast.makeText(mContext, MainActivity.resources.getString(R.string.shopinfo_toast3), Toast.LENGTH_SHORT)
							.show();
							return;
						}else{
						for (int i = 0; i < name.length; i++) {
							name[i] = GameData.corporation.employee[Integer
									.parseInt(String.valueOf(vector
											.elementAt(i)))].name;
						}
						final long shopId = shop.id;
						final byte shopType = shop.type;
						final AlertDialog dlg2 = new AlertDialog.Builder(ShopInfoActivity.this).create();
						dlg2.show();
						dlg2.getWindow().setContentView(R.layout.mapmenu3_list);
				    	ListView lv = (ListView)dlg2.findViewById(R.id.mapmenu3_list);
						ArrayList<HashMap<String, String>>list= new  ArrayList<HashMap<String,String>>();
						HashMap<String, String> map;
						if(name.length == 0){
							dlg2.dismiss();
							Toast.makeText(mContext, MainActivity.resources.getString(R.string.shopinfo_toast3), Toast.LENGTH_SHORT)
							.show();							
							return;
						}else{
						if (GameData.corporation.employee.length > 0) {
							for (int i = 0; i < name.length; i++) {
								map = new HashMap<String, String>();
								map.put("name", name[i]);
								list.add(map);
							}
							 SimpleAdapter listAdapter = new SimpleAdapter(ShopInfoActivity.this,list,   
						                R.layout.mapmenu3_item, new String[] {"name"},   
						                new int[] {R.id.mapmenu3_list_text});   
					        lv.setAdapter(listAdapter);
					        lv.setOnItemClickListener(new OnItemClickListener() {

								 
								public void onItemClick(AdapterView<?> parent,
										View view, int position, long id) {
									// TODO Auto-generated method stub
									int idx = Integer.parseInt(String
											.valueOf(vector
													.elementAt(position)));
									Connection
									.sendMessage(
											GameProtocol.CONNECTION_SEND_STAFF_OP_REQ,
											ConstructData
													.Staff_Appoint_Req(
															(byte) 0,
															shopId,
															shopType,
															new long[] { GameData.corporation.employee[idx].id }));
									dlg2.dismiss();
								}
							});
							/*Builder builder = new AlertDialog.Builder(
									ShopInfoActivity.this);
							final long shopId = shop.id;
							final byte shopType = shop.type;
							builder.setItems(name,
									new DialogInterface.OnClickListener() {

										public void onClick(
												DialogInterface dialog,
												int index) {
											int idx = Integer.parseInt(String
													.valueOf(vector
															.elementAt(index)));

											Connection
													.sendMessage(
															GameProtocol.CONNECTION_SEND_STAFF_OP_REQ,
															ConstructData
																	.Staff_Appoint_Req(
																			(byte) 0,
																			shopId,
																			shopType,
																			new long[] { GameData.corporation.employee[idx].id }));

										}

									}

							).create();
							builder.show();*/
						} else {
							Log.i("Log", "显示提示框-----");
							doPromoter(FreshManLead.prompt14);
						}
						}
						}
						}
				});
			}

			// inventory.append(String.valueOf(shop.goodsNum));
			inventory.setText(MainActivity.resources
					.getString(R.string.shopinfo_store1) + shop.goodsNum+"/"+shop.maxGoodsNum);
			// Button purchase = (Button) this
			// .findViewById(R.id.shopinfo_purchase_button);
			// purchase.setVisibility(View.GONE);
			purchase.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
//					String[] num = new String[] { "100件", "500件", "3000件",
//							"10000件", "30000件" };
					final AlertDialog dlg = new AlertDialog.Builder(ShopInfoActivity.this).create();
					dlg.show();					
					dlg.getWindow().setContentView(R.layout.popmenu_goodsnumber);
					
					Button  button1 = (Button)dlg.findViewById(R.id.popmenu_goodsnumber_button_100);	
					Button  button2 = (Button)dlg.findViewById(R.id.popmenu_goodsnumber_button_500);	
					Button  button3 = (Button)dlg.findViewById(R.id.popmenu_goodsnumber_button_3000);	
					Button  button4 = (Button)dlg.findViewById(R.id.popmenu_goodsnumber_button_10000);
					button1.setOnClickListener(new OnClickListener() {
						
						 
						public void onClick(View v) {
							// TODO Auto-generated method stub
							if (GameData.isFreshMan
									&& FreshManLead.caseId == 9
									&& buyArrows != null
									&& employeeArrows != null) {
								Log.i("Log", "购买-->>调配人员----"
										+ FreshManLead.caseId);
								// 取消新手引导的购买指向箭头
								buyArrows.setVisibility(View.GONE);
								FreshManLead.caseId++;// caseId=10
								Log.i("Log", "购买-->>调配人员----"
										+ FreshManLead.caseId);
								doPromoter(FreshManLead.prompt13);// 提示调配
								// 并添加新搜引导的调配指向箭头
								employeeArrows
										.setVisibility(View.VISIBLE);
							}
							Connection
									.sendMessage(
											GameProtocol.CONNECTION_REQ_PURCHASE_GOODS,
											ConstructData
													.getPurchaseGoods(
															(Long) shop.id,
															(byte) 0,
															(byte) 0));
							
						}
					});
					button2.setOnClickListener(new OnClickListener() {
						
						 
						public void onClick(View v) {
							// TODO Auto-generated method stub
							if (GameData.isFreshMan
									&& FreshManLead.caseId == 9
									&& buyArrows != null
									&& employeeArrows != null) {
								Log.i("Log", "购买-->>调配人员----"
										+ FreshManLead.caseId);
								// 取消新手引导的购买指向箭头
								buyArrows.setVisibility(View.GONE);
								FreshManLead.caseId++;// caseId=10
								Log.i("Log", "购买-->>调配人员----"
										+ FreshManLead.caseId);
								doPromoter(FreshManLead.prompt13);// 提示调配
								// 并添加新搜引导的调配指向箭头
								employeeArrows
								.setVisibility(View.VISIBLE);
							}
							Connection
							.sendMessage(
									GameProtocol.CONNECTION_REQ_PURCHASE_GOODS,
									ConstructData
									.getPurchaseGoods(
											(Long) shop.id,
											(byte) 1,
											(byte) 0));
							
						}
					});
					button3.setOnClickListener(new OnClickListener() {
						
						 
						public void onClick(View v) {
							// TODO Auto-generated method stub
							if (GameData.isFreshMan
									&& FreshManLead.caseId == 9
									&& buyArrows != null
									&& employeeArrows != null) {
								Log.i("Log", "购买-->>调配人员----"
										+ FreshManLead.caseId);
								// 取消新手引导的购买指向箭头
								buyArrows.setVisibility(View.GONE);
								FreshManLead.caseId++;// caseId=10
								Log.i("Log", "购买-->>调配人员----"
										+ FreshManLead.caseId);
								doPromoter(FreshManLead.prompt13);// 提示调配
								// 并添加新搜引导的调配指向箭头
								employeeArrows
								.setVisibility(View.VISIBLE);
							}
							Connection
							.sendMessage(
									GameProtocol.CONNECTION_REQ_PURCHASE_GOODS,
									ConstructData
									.getPurchaseGoods(
											(Long) shop.id,
											(byte) 2,
											(byte) 0));
							
						}
					});
					button4.setOnClickListener(new OnClickListener() {
						
						 
						public void onClick(View v) {
							// TODO Auto-generated method stub
							if (GameData.isFreshMan
									&& FreshManLead.caseId == 9
									&& buyArrows != null
									&& employeeArrows != null) {
								Log.i("Log", "购买-->>调配人员----"
										+ FreshManLead.caseId);
								// 取消新手引导的购买指向箭头
								buyArrows.setVisibility(View.GONE);
								FreshManLead.caseId++;// caseId=10
								Log.i("Log", "购买-->>调配人员----"
										+ FreshManLead.caseId);
								doPromoter(FreshManLead.prompt13);// 提示调配
								// 并添加新搜引导的调配指向箭头
								employeeArrows
								.setVisibility(View.VISIBLE);
							}
							Connection
							.sendMessage(
									GameProtocol.CONNECTION_REQ_PURCHASE_GOODS,
									ConstructData
									.getPurchaseGoods(
											(Long) shop.id,
											(byte) 3,
											(byte) 0));
							
						}
					});
					/*Builder builder = new AlertDialog.Builder(
							ShopInfoActivity.this);

					builder.setItems(num,
							new DialogInterface.OnClickListener() {

								public void onClick(DialogInterface dialog,
										int index) {

									if (GameData.isFreshMan
											&& FreshManLead.caseId == 9
											&& buyArrows != null
											&& employeeArrows != null) {
										Log.i("Log", "购买-->>调配人员----"
												+ FreshManLead.caseId);
										// 取消新手引导的购买指向箭头
										buyArrows.setVisibility(View.GONE);
										FreshManLead.caseId++;// caseId=10
										Log.i("Log", "购买-->>调配人员----"
												+ FreshManLead.caseId);
										doPromoter(FreshManLead.prompt13);// 提示调配
										// 并添加新搜引导的调配指向箭头
										employeeArrows
												.setVisibility(View.VISIBLE);
									}
									Connection
											.sendMessage(
													GameProtocol.CONNECTION_REQ_PURCHASE_GOODS,
													ConstructData
															.getPurchaseGoods(
																	(Long) shop.id,
																	(byte) index,
																	(byte) 0));
									
								}
							}).create().show();
*/
				}
			});
			String emp = MainActivity.resources
					.getString(R.string.shopinfo_vacancy);
			if (shop.employees.length > 0) {
				emp = shop.employees[0].name;
			}
			employees.setText(MainActivity.resources
					.getString(R.string.shopinfo_shopleader1) + emp);

			// quality.append(String.valueOf(shop.quality)+"/"+shop.maxQuality);
			quality.setText(MainActivity.resources
					.getString(R.string.shopinfo_shopquality1)
					+ shop.quality
					+ "/" + shop.maxQuality);

			// popularity.append(String.valueOf(shop.popularity)+"/"+shop.maxPopularity);
			popularity.setText(MainActivity.resources
					.getString(R.string.shopinfo_shopawareness1)
					+ shop.popularity + "/" + shop.maxPopularity);

			// morale.append(String.valueOf(shop.morale)+"/"+shop.maxMorale);
			morale.setText(MainActivity.resources
					.getString(R.string.shopinfo_shopmoral1)
					+ shop.morale
					+ "/" + shop.maxMorale);

			Employee ev = GameData.getEmployeeById1(shop.managerId);

			// zhouzhilong add--------新手引导提示框
			if (GameData.isFreshMan && FreshManLead.caseId == 8) {
				doPromoter(FreshManLead.prompt10);
				Log.i("Log", "shopInfo--prompt10===" + FreshManLead.caseId);
				FreshManLead.caseId++;
				if (FreshManLead.caseId == 9) {
					Log.i("Log", "CaseID===" + FreshManLead.caseId);
					buyArrows = (SpriteView) this
							.findViewById(R.id.shopinfo_buy_arrows);
					buyArrows.setSeries(0);
					buyArrows.setVisibility(View.VISIBLE);

					employeeArrows = (SpriteView) this
							.findViewById(R.id.shopinfo_employee_arrows);
					employeeArrows.setSeries(0);
				}
			}

		}

	}
	public void update(){
		if(shop !=null){
		inventory.setText(MainActivity.resources
				.getString(R.string.shopinfo_store1) + shop.goodsNum+"/"+shop.maxGoodsNum);
		
		if (shop.isRecruited())// 被招揽
		{
			flow_button.setBackgroundResource(R.drawable.button_dispel);

			flow_button.setOnClickListener(new OnClickListener() {

				public void onClick(View arg0) {
					Connection.sendMessage(
							GameProtocol.CONNECTION_Shop_UnRecruit_Req,
							ConstructData.getShop_UnRecruit_Req((byte)1,
									GameData.player.id, shop.buildingId));
					
				}

			}

			);

		} else {

			if (shop.isRecruiting())// 取消招揽
			{
				flow_button
						.setBackgroundResource(R.drawable.button_cancel_recruit);
				flow_button.setOnClickListener(new OnClickListener() {
					public void onClick(View view) {

								Connection.sendMessage(
										GameProtocol.CONNECTION_Shop_UnRecruit_Req,
										ConstructData.getShop_UnRecruit_Req((byte)2,
												GameData.player.id, shop.buildingId));
						
					}
				});

			} else// 招揽
			{

				flow_button
						.setBackgroundResource(R.drawable.button_recruit);

				flow_button.setOnClickListener(new OnClickListener() {
					public void onClick(View arg0) {
					/*	if(GameData.corporation. ==0){
							Toast toast = Toast.makeText(MainActivity.mContext, MainActivity.resources
									.getString(R.string.shopinfo_toast1), Toast.LENGTH_SHORT);
							toast.show();
						}*/
						Connection
								.sendMessage(
										GameProtocol.CONNECTION_SHOP_Avai_Recruit_List_req,
										ConstructData.getAvaiRecruit(
												(byte) 0, 0, shop.trade_id,
												shop.map_id, 0, (byte) 50));
						GameData.RecruitedShopId = shop.id;
					}
					
				});
			}
		}
/*		if (shop.isRecruited())// 被招揽
		{
			flow_button.setBackgroundResource(R.drawable.button_dispel);
		} else {

			if (shop.isRecruiting())// 取消招揽
			{
				flow_button
						.setBackgroundResource(R.drawable.button_cancel_recruit);}}*/
	}
	}

	// ==========================================提示框
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
				}), 500);
			};
		}.start();
	}

	private LinearLayout linearPromot;
	private TextView promot;

	// zhouzhilong add
	private void setText(String textStr) {

		Log.i("Log", "setText--ShopInfo");
		linearPromot = (LinearLayout) this
				.findViewById(R.id.linearlayout_promotframe4);
		Button close = (Button) this.findViewById(R.id.close_promotframe4);
		promot = (TextView) this.findViewById(R.id.promotFrame4);
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
				// 连续弹出两个提示框
				if (GameData.isFreshMan) {
					if (FreshManLead.caseId == 9) {
						int i = 0;
						while (i < 2) {
							if (i == 0) {
								new Thread() {
									public void run() {
										endAnimationHandler.postDelayed(
												(new Runnable() {
													 
													public void run() {
														promot.setText(FreshManLead.prompt11);
													}
												}), 1000);
									};
								}.start();
							}
							if (i == 1) {
								new Thread() {
									public void run() {
										endAnimationHandler.postDelayed(
												(new Runnable() {
													 
													public void run() {
														promot.setText(FreshManLead.prompt12);
													}
												}), 1000);
									};
								}.start();
							}
							i++;
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}

						}
					}
				}
			}
		});
		linearPromot.startAnimation(anim);
	}


	public void OnDataChange(Bundle bundle) {
		if (shop == null)
			return;

		shop = GameData.corporation.shop[selectIndex];
		String emp = MainActivity.resources
				.getString(R.string.shopinfo_vacancy);
		if (shop.employees.length > 0) {
			emp = shop.employees[0].name;
		}
		employees.setText(MainActivity.resources
				.getString(R.string.shopinfo_shopstaff) + emp);

		Employee ev = GameData.getEmployeeById1(shop.managerId);

		String manangerName = null;
		if (ev == null) {
			manangerName = MainActivity.resources
					.getString(R.string.shopinfo_vacancy);
		} else {
			manangerName = ev.name;
		}

		if (shop.employees.length > 0) {
			employee_button.setBackgroundResource(R.drawable.outgoing);

			employee_button.setOnClickListener(new OnClickListener() {

				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					Connection.sendMessage(
							GameProtocol.CONNECTION_SEND_STAFF_OP_REQ,
							ConstructData.Staff_Appoint_Req((byte) 1, shop.id,
									(byte) 0,
									new long[] { shop.employees[0].id }));
				}
			});

		} else {
			employee_button.setBackgroundResource(R.drawable.deployment);

			employee_button.setOnClickListener(new OnClickListener() {

				public void onClick(View arg0) {
					final Vector vector = new Vector();
					Log.i("Log", "调配=========2--onClick");
					for (int i = 0; i < GameData.corporation.employee.length; i++) {
						if (GameData.corporation.employee[i].department <= 0) {
							vector.addElement(i);
						}
					}

					// 新手引导部分
					Log.i("Log", "调配2=========" + FreshManLead.caseId);
					if (GameData.isFreshMan && FreshManLead.caseId == 10
							&& employeeArrows != null) {
						Log.i("Log", "ShopInfo==调配店长");
						doPromoter(FreshManLead.prompt14);
						employeeArrows.setVisibility(View.GONE);
						FreshManLead.caseId++;
					}

					String[] name = new String[vector.size()];

					for (int i = 0; i < name.length; i++) {
						name[i] = GameData.corporation.employee[Integer
								.parseInt(String.valueOf(vector.elementAt(i)))].name;
					}
					final long shopId = shop.id;
					final byte shopType = shop.type;
					final AlertDialog dlg2 = new AlertDialog.Builder(ShopInfoActivity.this).create();
					dlg2.show();
					dlg2.getWindow().setContentView(R.layout.mapmenu3_list);
			    	ListView lv = (ListView)dlg2.findViewById(R.id.mapmenu3_list);
					ArrayList<HashMap<String, String>>list= new  ArrayList<HashMap<String,String>>();
					HashMap<String, String> map;
					if (name.length == 0) {
						dlg2.dismiss();
						Toast.makeText(MainActivity.mContext, MainActivity.resources.getString(R.string.deploy_toast),
								Toast.LENGTH_LONG).show();
						
					}
					else if(name.length>0){
					for (int i = 0; i < name.length; i++) {
						map = new HashMap<String, String>();
						map.put("name", name[i]);
						list.add(map);
					}
					 SimpleAdapter listAdapter = new SimpleAdapter(ShopInfoActivity.this,list,   
				                R.layout.mapmenu3_item, new String[] {"name"},   
				                new int[] {R.id.mapmenu3_list_text});   
			        lv.setAdapter(listAdapter);
			        lv.setOnItemClickListener(new OnItemClickListener() {

						 
						public void onItemClick(AdapterView<?> parent,
								View view, int position, long id) {
							// TODO Auto-generated method stub
							int idx = Integer.parseInt(String
									.valueOf(vector.elementAt(position)));
							Connection
							.sendMessage(
									GameProtocol.CONNECTION_SEND_STAFF_OP_REQ,
									ConstructData
											.Staff_Appoint_Req(
													(byte) 0,
													shopId,
													shopType,
													new long[] { GameData.corporation.employee[idx].id }));
							dlg2.dismiss();
						}
					});
			      				}
					
					
					
//					Builder builder = new AlertDialog.Builder(
//							ShopInfoActivity.this);
				/*	final long shopId = shop.id;
					final byte shopType = shop.type;
					builder.setItems(name,
							new DialogInterface.OnClickListener() {

								public void onClick(DialogInterface dialog,
										int index) {
									int idx = Integer.parseInt(String
											.valueOf(vector.elementAt(index)));

									Connection
											.sendMessage(
													GameProtocol.CONNECTION_SEND_STAFF_OP_REQ,
													ConstructData
															.Staff_Appoint_Req(
																	(byte) 0,
																	shopId,
																	shopType,
																	new long[] { GameData.corporation.employee[idx].id }));
								}

							}

					).create().show();*/
					if (GameData.corporation.employee.length > 0) {
						Log.i("Log", "显示窗口-----");
					//	builder.show();
					} else {
						Log.i("Log", "显示提示框-----");
						doPromoter(FreshManLead.prompt14);
					}
				}
			});
		}
	}
	
	
	

	public void Activitychange(Class calss, Intent intent) {
		if (intent == null) {
			intent = new Intent();
		}
		
		intent.setClass(ShopInfoActivity.this, calss);
		
		this.startActivity(intent);
	}
	
	public GameActivity getGameContext() 
	{
		// TODO Auto-generated method stub
		return this;
	}
	
	 
	public void finish() 
	{
		mContext = null;
		super.finish();
	}

}
