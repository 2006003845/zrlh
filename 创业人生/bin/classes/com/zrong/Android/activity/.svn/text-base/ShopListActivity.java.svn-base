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
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.animation.Animation.AnimationListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Gallery;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import com.zrong.Android.Listener.ActivityTransform;
import com.zrong.Android.Listener.DataChangeListener;
import com.zrong.Android.Util.GridsimpleImageAdapter;
import com.zrong.Android.Util.Music;
import com.zrong.Android.Util.MyImageAdapter;
import com.zrong.Android.Util.ShopItemAdapter;
import com.zrong.Android.Util.SystemAPI;
import com.zrong.Android.View.FreshManLead;
import com.zrong.Android.element.Shop;
import com.zrong.Android.game.Connection;
import com.zrong.Android.game.ConstructData;
import com.zrong.Android.game.GameData;
import com.zrong.Android.game.GameDefinition;
import com.zrong.Android.game.GameGroupControl;
import com.zrong.Android.online.network.GameProtocol;

public class ShopListActivity extends GameActivity implements 
		DataChangeListener /*,OnItemSelectedListener*/{
/*	private GridsimpleImageAdapter adapter;
	DisplayMetrics dm;
	  static int w1;
	  static int h1;*/
	private ListView shopList;
	private TextView shopNum;
	private TextView  routin_desc;
	private Button confirm;
	private boolean where = false;
	public static int p=0;
	private String nametag = MainActivity.resources.getString(R.string.shoplist_nametag);
	private String leveltag = MainActivity.resources.getString(R.string.shoplist_leveltag);
	private String typetag = MainActivity.resources.getString(R.string.shoplist_typetag);
	private String employeetag = MainActivity.resources.getString(R.string.shoplist_employeetag);
	private String stocktag = MainActivity.resources.getString(R.string.shoplist_stocktag);
	private String flowtag = MainActivity.resources.getString(R.string.shoplist_flowtag);
	public static final String routin_menu =String.valueOf(MainActivity.resources.getString(R.string.routine_menu)) ;
	public static final String menu[] = routin_menu.split(",");
	
	public static ShopListActivity mContext = null;
	
	
	public Button detail;
	public Button routine;
	public Button deploy;
	public Button purchase;
	public Button recruit;
	public Button location;

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
//		   dm=new DisplayMetrics();
//	        getWindowManager().getDefaultDisplay().getMetrics(dm);
//	        w1=dm.widthPixels;
//	        h1=dm.heightPixels;
		mContext = this;
		
		where = getIntent().getStringExtra("where") != null ;

		getWindow().requestFeature(Window.FEATURE_NO_TITLE);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.shoplist);
		
		Button title = (Button) this.findViewById(R.id.shoplist_button_title);
		


		initShopList();
		
		
		
		
		ShopItemAdapter adpter = (ShopItemAdapter) shopList.getAdapter();
		
		
		
		if(adpter.getCount() > 0)
		{
			adpter.setCheck(0, true);
			adpter.notifyDataSetChanged();
		}
		
		if (GameData.isFreshMan && FreshManLead.caseId == 15) {
			doPromoter(FreshManLead.prompt19);
			FreshManLead.caseId = 16;
		}
	}
	
	public int[] getCheckIndex()
	{
		ShopItemAdapter adpter = (ShopItemAdapter) shopList.getAdapter();
		
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
	 * 检查当前能够显示的button
	 */
	public void checkButton()
	{
		int [] idx = getCheckIndex();
		
		if(idx!= null&& idx.length>0)
		{
			if(idx.length > 1)
			{
				location.setVisibility(View.GONE);
				detail.setVisibility(View.GONE);
				deploy.setVisibility(View.GONE);
				recruit.setVisibility(View.GONE);
				purchase.setVisibility(View.VISIBLE);
				routine.setVisibility(View.VISIBLE);
			}
			else
			{
				location.setVisibility(View.VISIBLE);
				detail.setVisibility(View.VISIBLE);
				deploy.setVisibility(View.VISIBLE);
				recruit.setVisibility(View.VISIBLE);
				purchase.setVisibility(View.VISIBLE);
				routine.setVisibility(View.VISIBLE);
				
				
				if(GameData.corporation.shop[idx[0]].employees.length >0 )//卸任
				{
					deploy.setText(R.string.shoplist_deploy);
				}
				else//调配
				{
					deploy.setText(R.string.shoplist_employ);
				}
			}
		}
		else
		{
			location.setVisibility(View.GONE);
			detail.setVisibility(View.GONE);
			deploy.setVisibility(View.GONE);
			recruit.setVisibility(View.GONE);
			purchase.setVisibility(View.GONE);
			routine.setVisibility(View.GONE);
		}
		
	}

	public void initShopList() 
	{
		Button cancle = (Button) this.findViewById(R.id.shoplist_button_cancel);

		cancle.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) 
			{
				ShopListActivity.this.finish();
			}

		});

		final Shop shop[] = GameData.corporation.shop;

		GameData.corporation.addDataChangeListener(this);

		shopList = (ListView) this.findViewById(R.id.shoplist_listview);

		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

			for (int i = 0; i < shop.length; i++) 
			{
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("name", nametag + shop[i].name);
				map.put("level", leveltag + shop[i].level);
				map.put("type",
						typetag + GameData.getShopScale(shop[i].scale, ""));
				map.put("stock", stocktag + shop[i].goodsNum+"/"+shop[i].maxGoodsNum);
				map.put("flow", flowtag + shop[i].flowGain);
				
				if(shop[i].isRecruiting())
				{
					map.put("recruiting", View.VISIBLE);
				}
				else
				{
					map.put("recruiting", View.GONE);
				}
				
				
				if (shop[i].isRecruited()) 
				{
					map.put("recruited", View.VISIBLE);
				} else 
				{
					map.put("recruited", View.GONE);
				}
				list.add(map);
			} 

		ShopItemAdapter adpter = new ShopItemAdapter(ShopListActivity.this,
				list, R.layout.shoplist_item, new String[] { "name", "level",
						"type", "stock", "flow", "employee", 
						"recruiting", "recruited" }, new int[] {
						R.id.shoplist_item_name, R.id.shoplist_item_level,
						R.id.shoplist_item_type, R.id.shoplist_item_stock,
						R.id.shoplist_item_flow, R.id.shoplist_item_employee,
						
						R.id.shoplist_item_recruiting,
						R.id.shoplist_item_recruted });
		shopList.setAdapter(adpter);

		shopList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int index,
					long arg3) {
				ShopItemAdapter adpter = (ShopItemAdapter) shopList.getAdapter();
				
				if(adpter.isCheck(index))
				{
					adpter.setCheck(index,false);
				}
				else
				{
					adpter.setCheck(index,true);
				}
				
				adpter.notifyDataSetChanged();
				
				checkButton();
				
			}
		}); 
     
     
     	detail =(Button) this.findViewById(R.id.em_button_look); 
		routine =(Button) this.findViewById(R.id.em_button_routine); 
		deploy =(Button)this.findViewById(R.id.em_button_employ); 
		purchase=(Button)this.findViewById(R.id.em_button_buy); 
		recruit=(Button)this.findViewById(R.id.em_button_recruit); 
		location =(Button)this.findViewById(R.id.em_button_location); 
		
		
		/**查看*/
		detail.setOnClickListener(new OnClickListener()
		{
			 
			public void onClick(View v) {
				Log.v("yz", "detail 1");
				int idx [] = getCheckIndex();
				
				Log.v("yz", "detail 2");
				
				if(idx != null && idx.length>0)
				{
					Intent intent = new Intent();
					
					Bundle bundle = new Bundle(); // 携带数据
					
				    bundle.putInt("type", 1);
					
				    bundle.putInt("selectIndex", idx[0]);
					
				    intent.putExtras(bundle);
					
					ShopListActivity.this.Activitychange(ShopInfoActivity.class,
							intent);
				}
				else//show toast
				{
					Toast.makeText(ShopListActivity.this, MainActivity.resources.getString(R.string.shoplist_toast1),
							Toast.LENGTH_SHORT).show();
				}
			}
		}
		);
		/**常务*/
		routine.setOnClickListener(new OnClickListener()
		{ 
			public void onClick(View v) {
				int idx [] = getCheckIndex();
				if ( idx== null &&idx.length <= 0) 
				{
					Toast.makeText(ShopListActivity.this, MainActivity.resources.getString(R.string.shoplist_toast1),
							Toast.LENGTH_SHORT).show();
				} 
				else 
				{
					final long shopId[] = new long[idx.length];

					for (int i = 0; i < idx.length; i++) {
						// zhouzhilong amend---GameData.corporation.shop[i].id-->>如下
						shopId[i] = GameData.corporation.shop[idx[i]].id;
					}
					ShopListActivity.this
							.setContentView(R.layout.shoproutine);

					 routin_desc = (TextView)ShopListActivity.this.findViewById(R.id.routin_desc);
					 routin_desc.setText(menu[0]);
					confirm = (Button)ShopListActivity.this.findViewById(R.id.confirm);

					Button cancel = (Button) ShopListActivity.this
							.findViewById(R.id.employeelist_routin_button_cancel);					

					cancel.setOnClickListener(new OnClickListener() {
						public void onClick(View arg0)
						{
							ShopListActivity.this
									.setContentView(R.layout.shoplist);
							initShopList();
						}
					});
					
					final Gallery shoproutin = (Gallery) ShopListActivity.this
							.findViewById(R.id.employeelist_routin_gridview);

					GridsimpleImageAdapter adapter = new GridsimpleImageAdapter(
							ShopListActivity.this);				
					//  adapter=new GridsimpleImageAdapter(ShopListActivity.this,w1,h1);
					  adapter.setImageIdArray(new int[] { /*159, */146, /*1369,*/ 157,
								149 });
					  shoproutin.setAdapter(adapter);
					//  shoproutin.setOnItemSelectedListener(ShopListActivity.this);
					  shoproutin.setOnItemSelectedListener(new OnItemSelectedListener() {

						 
						public void onItemSelected(AdapterView<?> parent,
								View view, int position, long id) {
							// TODO Auto-generated method stub
							p=position;
							 routin_desc.setText(menu[position]);
							
						}

						 
						public void onNothingSelected(AdapterView<?> parent) {
							// TODO Auto-generated method stub
							
						}
					});
					confirm.setOnClickListener(new OnClickListener() {
						
						 
						public void onClick(View v) {
							// TODO Auto-generated method stub
							 if (p == 0){


									final AlertDialog dlg = new AlertDialog.Builder(ShopListActivity.this).create();
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
											Connection
											.sendMessage(
													GameProtocol.CONNECTION_REQ_EMPLOYMENT_STAFF_BATCH,
													ConstructData
															.getEmployStaffBatch(
																	(byte) 2,
																	shopId));
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

											bundle.putByte(
													"type",
													(byte) 1);

											bundle.putString(
													"staffname",
													"");

											bundle.putString(
													"mastername",
													"");

											bundle.putByte(
													"size",
													(byte) 1);

											bundle.putByteArray(
													"office",
													new byte[] { 0 });

											bundle.putByteArray(
													"tType",
													new byte[] { 0 });

											bundle.putByteArray(
													"level",
													new byte[] { 0 });

											bundle.putInt(
													"begin", 0);

											bundle.putInt(
													"count", 50);

											intent.putExtras(bundle);

											intent.setClass(
													ShopListActivity.this,
													DiglistActivity.class);
											
											ShopListActivity.this
													.startActivity(intent);

											Connection
													.sendMessage(
															GameProtocol.REQSearchStaff_Req,
															ConstructData
																	.SearchStaff_Req(
																			(byte) 1,
																			"",
																			"",
																			(byte) 1,
																			new byte[] { 0 },
																			new byte[] { 0 },
																			new byte[] { 0 },
																			0,
																			50));
											dlg.dismiss();
										}
										
									}
									);
									hunter.setOnClickListener(new OnClickListener()
									{
										 
										public void onClick(View v) 
										{
											// TODO Auto-generated method stub
											Intent intent = new Intent();
											
											intent.setClass(
													ShopListActivity.this,
													SearchconditionActivity.class);
											ShopListActivity.this
													.startActivity(intent);
											dlg.dismiss();
										}
										
									}
									);
								
							 }else if (p == 1)// 公关
								{
									Connection
											.sendMessage(
													GameProtocol.CONNECTION_SEND_SHOPPR,
													ConstructData
															.getShopRP(
																	shopId,
																	(short) 505));
								}	else if (p == 2)// 巡视
								{
									Connection
											.sendMessage(
													GameProtocol.CONNECTION_SEND_TOUR_REQ,
													ConstructData
															.getTour_req(
																	shopId,
																	(byte) 1));
								}
						}
					});
 

					shoproutin
							.setOnItemClickListener(new OnItemClickListener() {

								public void onItemClick(AdapterView<?> parent,
										View v, int position, long id) {
								    	p= position;
								routin_desc.setText(menu[position]);
								}
							}

							);
				}
			}
			
		}
		);
		/**调配*/
		deploy.setOnClickListener(new OnClickListener()
		{
			 
			public void onClick(View v) {
				
				int[] idx = getCheckIndex();
				
				if(idx != null && idx.length>0)
				{
					if (shop[idx[0]].employees.length > 0)// 卸任
					{
						Connection
								.sendMessage(
										GameProtocol.CONNECTION_SEND_STAFF_OP_REQ,
										ConstructData
												.Staff_Appoint_Req(
														(byte) 1,
														shop[idx[0]].id,
														(byte) 0,
														new long[] { shop[idx[0]].employees[0].id }));
					} else// 调配
					{
						final Vector vector = new Vector();

						for (int i = 0; i < GameData.corporation.employee.length; i++) {
							if (GameData.corporation.employee[i].department <= 0) {
								vector.addElement(i);
							}
						}

						String[] name = new String[vector.size()];

						for (int i = 0; i < name.length; i++) {
							name[i] = GameData.corporation.employee[Integer
									.parseInt(String.valueOf(vector.elementAt(i)))].name;
						}
						
						final AlertDialog dlg = new AlertDialog.Builder(ShopListActivity.this).create();
						
						dlg.show();					
						dlg.getWindow().setContentView(R.layout.pm_emoloyeelist);
						
						ListView lv = (ListView)dlg.findViewById(R.id.pm_emoloyeelist_listview);
						
						ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
						
						for(int i =0; i < name.length; i++)
						{
							HashMap<String, Object> map = new HashMap<String, Object>();
							map.put("name", name[i]);
							list.add(map);
						}
						
						SimpleAdapter adpter = new SimpleAdapter(ShopListActivity.this,
								list, R.layout.pm_employeelist_item, new String[] { "name" }, new int[] {
								R.id.pm_employeelist_item_name });
						lv.setAdapter(adpter);
						final long shopId = shop[idx[0]].id;
						final byte shopType = shop[idx[0]].type;
						lv.setOnItemClickListener(new OnItemClickListener()
						{
							 
							public void onItemClick(AdapterView<?> parent,
									View view, int position, long id) 
							{
								Connection
								.sendMessage(
										GameProtocol.CONNECTION_SEND_STAFF_OP_REQ,
										ConstructData
												.Staff_Appoint_Req(
														(byte) 0,
														shopId,
														shopType,
														new long[] { GameData.corporation.employee[position].id }));
								dlg.dismiss();
								
							}
							
						}
						
						);
//						Builder builder = new AlertDialog.Builder(
//								ShopListActivity.this);
//						final long shopId = shop[idx[0]].id;
//						final byte shopType = shop[idx[0]].type;
//						builder.setItems(name,
//								new DialogInterface.OnClickListener() {
//
//									public void onClick(DialogInterface dialog,
//											int index) {
//										int idx = Integer.parseInt(String
//												.valueOf(vector.elementAt(index)));
//
//										Connection
//												.sendMessage(
//														GameProtocol.CONNECTION_SEND_STAFF_OP_REQ,
//														ConstructData
//																.Staff_Appoint_Req(
//																		(byte) 0,
//																		shopId,
//																		shopType,
//																		new long[] { GameData.corporation.employee[idx].id }));
//									}
//
//								}
//
//						).create().show();
					}
				}
				else
				{
					Toast.makeText(ShopListActivity.this, MainActivity.resources.getString(R.string.shoplist_toast1),
							Toast.LENGTH_SHORT).show();
				}
			}
		}
		);
		/**进货*/
		purchase.setOnClickListener(new OnClickListener()
		{
			 
			public void onClick(View v) 
			{
				
				int[] idx = getCheckIndex();
				
				if(idx != null && idx.length>0)
				{
					final long shopId[] = new long[idx.length];

					for (int i = 0; i < idx.length; i++) {
						// zhouzhilong amend---GameData.corporation.shop[i].id-->>如下
						shopId[i] = GameData.corporation.shop[idx[i]].id;
					}
					final AlertDialog dlg = new AlertDialog.Builder(ShopListActivity.this).create();
					dlg.show();					
					dlg.getWindow().setContentView(R.layout.popmenu_goodsnumber);
					
					Button  button1 = (Button)dlg.findViewById(R.id.popmenu_goodsnumber_button_100);	
					Button  button2 = (Button)dlg.findViewById(R.id.popmenu_goodsnumber_button_500);	
					Button  button3 = (Button)dlg.findViewById(R.id.popmenu_goodsnumber_button_3000);	
					Button  button4 = (Button)dlg.findViewById(R.id.popmenu_goodsnumber_button_10000);
					
					button1.setOnClickListener(new OnClickListener()
					{

						 
						public void onClick(View v) {
							// TODO Auto-generated method stub
							Connection
							.sendMessage(
									GameProtocol.CONNECTION_SEND_GOODS_BATCH,
									ConstructData
											.getPurchaseGoodsBatch(
													(byte) 0,
													shopId));
							dlg.dismiss();
						}
						
					}
					);
					button2.setOnClickListener(new OnClickListener()
					{
						
						 
						public void onClick(View v) {
							// TODO Auto-generated method stub
							Connection
							.sendMessage(
									GameProtocol.CONNECTION_SEND_GOODS_BATCH,
									ConstructData
											.getPurchaseGoodsBatch(
													(byte) 1,
													shopId));
							dlg.dismiss();
						}
						
					}
					);
					button3.setOnClickListener(new OnClickListener()
					{
						
						 
						public void onClick(View v) {
							// TODO Auto-generated method stub
							Connection
							.sendMessage(
									GameProtocol.CONNECTION_SEND_GOODS_BATCH,
									ConstructData
											.getPurchaseGoodsBatch(
													(byte) 2,
													shopId));
							dlg.dismiss();
						}
						
					}
					);
					button4.setOnClickListener(new OnClickListener()
					{
						
						 
						public void onClick(View v) {
							// TODO Auto-generated method stub
							Connection
							.sendMessage(
									GameProtocol.CONNECTION_SEND_GOODS_BATCH,
									ConstructData
											.getPurchaseGoodsBatch(
													(byte) 3,
													shopId));
							dlg.dismiss();
						}
					}
					);
					 
					
//					String[] num = new String[] { "100件",
//							"500件", "3000件", "10000件" };
//
//					Builder builder = new AlertDialog.Builder(
//							ShopListActivity.this);
//
//					builder.setItems(
//							num,
//							new DialogInterface.OnClickListener() {
//
//								public void onClick(
//										DialogInterface dialog,
//										int index) {
//
//									Connection
//											.sendMessage(
//													GameProtocol.CONNECTION_SEND_GOODS_BATCH,
//													ConstructData
//															.getPurchaseGoodsBatch(
//																	(byte) index,
//																	shopId));
//								}
//							}).create().show();
				}
				else
				{
					Toast.makeText(ShopListActivity.this, MainActivity.resources.getString(R.string.shoplist_toast1),
							Toast.LENGTH_SHORT).show();
				}
				
			}
				
			
		}
		);
		/**招揽*/
		recruit.setOnClickListener(new OnClickListener()
		{
			 
			public void onClick(View v) 
			{
				
				int[] idx = getCheckIndex();
				
				if(idx != null && idx.length>0)
				{
					final Shop shop = GameData.corporation.shop[idx[0]];
					
					final AlertDialog dlg = new AlertDialog.Builder(ShopListActivity.this).create();
					dlg.show();					
					dlg.getWindow().setContentView(R.layout.popumenu);
//					LayoutInflater factory = LayoutInflater.from(ShopListActivity.this);
//					View view = factory.inflate(R.layout.popumenu, null);
					Button  start = (Button)dlg.findViewById(R.id.popmenu_button_startrecruit);	
//					start.setTypeface(GameDefinition.face);
					start.setOnClickListener(new OnClickListener()
					{
						public void onClick(View v) 
						{
							Connection
							.sendMessage(
									GameProtocol.CONNECTION_SHOP_Avai_Recruit_List_req,
									ConstructData.getAvaiRecruit(
											(byte) 0, 0, shop.trade_id,
											shop.map_id, 0, (byte) 50));
							GameData.RecruitedShopId = shop.id;
							Log.v("yz", "startRecruit");
							dlg.dismiss();
						}
						
					}
					);
					
					Button cancel = (Button)dlg.findViewById(R.id.popmenu_button_cancelrecruit);
//					cancel.setTypeface(GameDefinition.face);
					cancel.setOnClickListener(new OnClickListener()
					{
						 
						public void onClick(View v) 
						{
							Connection.sendMessage(
									GameProtocol.CONNECTION_Shop_UnRecruit_Req,
									ConstructData.getShop_UnRecruit_Req(
											(byte)2,
											GameData.player.id, shop.buildingId));
							dlg.dismiss();
						}
						
					}
					);
					
					Button dispel = (Button)dlg.findViewById(R.id.popmenu_button_dispelrecruit);
//					dispel.setTypeface(GameDefinition.face);
					
					dispel.setOnClickListener(new OnClickListener()
					{
						 
						public void onClick(View v) 
						{
							Connection.sendMessage(
									GameProtocol.CONNECTION_Shop_UnRecruit_Req,
									ConstructData.getShop_UnRecruit_Req((byte)1,
											GameData.player.id, shop.buildingId));
							dlg.dismiss();
						}
					}
					);
					
					if(shop.isRecruited())
					{
						dispel.setVisibility(View.VISIBLE);
					}
					else
					{
						dispel.setVisibility(View.GONE);
					}
					
					if(shop.isRecruiting())
					{
						cancel.setVisibility(View.VISIBLE);
						start.setVisibility(View.GONE);
					}
					else
					{
						cancel.setVisibility(View.GONE);
						start.setVisibility(View.VISIBLE);
					}
					/**
					 * 
					 * 
					 * LayoutInflater factory = LayoutInflater.from(context);
						View view = factory.inflate(R.layout.alert_style, null);
						gv = (GridView) view.findViewById(R.id.myGrid);
						gv.setAdapter(new ImageAdapter(context, new Integer[]         {R.drawable.menu_mark_editor,R.drawable.menu_delete}));
	dlg.getWindow().setContentView(gv);

					 */
				}
				else
				{
					Toast.makeText(ShopListActivity.this, MainActivity.resources.getString(R.string.shoplist_toast1),
							Toast.LENGTH_SHORT).show();
				}
				
				
				
			}	
		}
		);
		/**位置*/
		location.setOnClickListener(new OnClickListener()
		{
			
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				int[] idx = getCheckIndex();
				
				if(idx != null && idx.length>0)
				{
					Shop shop  = GameData.corporation.shop[idx[0]];
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
					
					if (MapmainMenuActivity.mContext != null) 
					{
						MapmainMenuActivity.mContext.finish();
					}
				}
				else
				{
					Toast.makeText(ShopListActivity.this, MainActivity.resources.getString(R.string.shoplist_toast1),
							Toast.LENGTH_SHORT).show();
				}
				
			}
			
		}
		);
	}

	public void Activitychange(Class calss, Intent intent) {
		// TODO Auto-generated method stub
		if (intent == null) {
			intent = new Intent();
		}

		intent.setClass(ShopListActivity.this, calss);
		
		this.startActivity(intent);
	}

	
	/* 重新激活 */
	protected void onResume() 
	{
		// zhouzhilong add
		//yangzheng delete maybe make trouble
//		OnDataChange(null);
		super.onResume();
	}
	
	public void OnDataChange(Bundle bundle) 
	{
		final Shop shop[] = GameData.corporation.shop;
		Log.v("yz", "OnDataChange");

		GameData.corporation.addDataChangeListener(this);

		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		HashMap<String, Object> map = new HashMap<String, Object>();

		for (int i = 0; i < shop.length; i++) 
		{
			map = new HashMap<String, Object>();
			map.put("name", nametag + shop[i].name);
			map.put("level", leveltag + shop[i].level);
			map.put("type", typetag + GameData.getShopScale(shop[i].scale, ""));
			map.put("stock", stocktag + shop[i].goodsNum+"/"+shop[i].maxGoodsNum);
			map.put("flow", flowtag + shop[i].flowGain);

			if (shop[i].isRecruiting()) {
				map.put("recruiting", View.VISIBLE);
			} else {
				map.put("recruiting", View.GONE);
			}

			if (shop[i].isRecruited()) {
				map.put("recruited", View.VISIBLE);
			} else {
				map.put("recruited", View.GONE);
			}
			list.add(map);
		}

		ShopItemAdapter adpter = new ShopItemAdapter(ShopListActivity.this,
				list, R.layout.shoplist_item, new String[] { "name", "level",
						"type", "stock", "flow", "employee",
						"recruiting", "recruited" }, new int[] {
						R.id.shoplist_item_name, R.id.shoplist_item_level,
						R.id.shoplist_item_type, R.id.shoplist_item_stock,
						R.id.shoplist_item_flow, R.id.shoplist_item_employee,
						
						R.id.shoplist_item_recruiting,
						R.id.shoplist_item_recruted});
		
		//拷贝老的adpter中选中项到新的中
		ShopItemAdapter oldAdpter = (ShopItemAdapter)shopList.getAdapter();
		
		for(int i = 0; i< oldAdpter.getCount();i++)
		{
			if(oldAdpter.isCheck(i))
			{
				adpter.setCheck(i, true);
			}
			else
			{
				adpter.setCheck(i, false);
			}
		}

		shopList.setAdapter(adpter);
	}

	 
	public void finish() {
		GameData.corporation.listener = null;
		mContext = null;
		super.finish();
	}

	// ================提示框
	private Handler endAnimationHandler = new Handler();

	// zhouzhilong add
	public void doPromoter(final String str) 
	{
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
				.findViewById(R.id.linearlayout_promotframe6);
		Button close = (Button) this.findViewById(R.id.close_promotframe6);
		promot = (TextView) this.findViewById(R.id.promotFrame6);
		if (textStr != null) {
			promot.setText(textStr);
		}
		close.setOnClickListener(new OnClickListener() 
		{
			 
			public void onClick(View v) 
			{
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

	
	

}
