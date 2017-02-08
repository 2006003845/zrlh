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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ViewFlipper;
import com.zrong.Android.Listener.TabWidget;
import com.zrong.Android.Util.CheckedItemAdapter;
import com.zrong.Android.Util.GameAPI;
import com.zrong.Android.Util.SystemAPI;
import com.zrong.Android.element.Chat;
import com.zrong.Android.element.Social;
import com.zrong.Android.game.Connection;
import com.zrong.Android.game.ConstructData;
import com.zrong.Android.game.GameData;
import com.zrong.Android.game.GameDefinition;
import com.zrong.Android.online.network.GameProtocol;

public class SocialActivity extends GameActivity implements
		TabWidget, OnTouchListener {

	private ButtonListener buttonListener;
	private LayoutInflater factory = null;
	
	public static final byte FRIEND_TYPE_FRIEND = 1;
	public static final byte FRIEND_TYPE_BLOCKED = 2;
	public static final byte FRIEND_TYPE_STRENGER = 3;
	public static final byte FRIEND_TYPE_VIE = 4;
	
	public static Social s = null;
	
	public static int friend_ID;
	
	public static SocialActivity mContext = null;
	
	private boolean isfromPlanningDept =false;
	
	private Button tabButton[] = null;
	
	private Button friendButton[] = null;
	private Button competitorButton[] = null;
	private Button stangeButton[] = null;
	private Button blackButton[] = null;
	
	
	
	private static final int  ID_SEND_MESSAGE = 1;
	public static final int ID_SEND_EMAIL = 2;
	public static final int ID_RECUIT = 3;
	public static final int ID_HELP_RECRUIT = 4;
	
	private String [] menu={"发信息","发邮件","招揽客流","帮助驱散"};
	
	private int [] menuId={ID_SEND_MESSAGE,ID_SEND_EMAIL,ID_RECUIT,ID_HELP_RECRUIT};
	
	private ListView listView[] = null;

	public static final byte FRIENDVIEW = 0;
	public static final byte COMPETITORVIEW = 1;
	public static final byte STANGERVIEW = 2;
	public static final byte BLACKVIEW = 3;
	
	
	private int select = 0;
	
	private ViewFlipper mViewFlipper; 

	protected void onCreate(Bundle savedInstanceState) 
	{

		super.onCreate(savedInstanceState);
		
		mContext = this;
		
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		this.setContentView(R.layout.social);
		
		buttonListener = new ButtonListener();
		
		isfromPlanningDept = getIntent().getStringExtra("fromPlanningdept") !=null;

		factory = LayoutInflater.from(this);


		
		Button cancel = (Button) this.findViewById(R.id.social_button_cancel);
		
		cancel.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) 
			{
				SocialActivity.this.finish();
				mContext = null;
			}
		});
		
		mViewFlipper = (ViewFlipper)this.findViewById(R.id.social_flipper);
		
		listView = new ListView[4];
		
		listView[FRIENDVIEW] = (ListView) this.findViewById(R.id.social_listview_friend);
		listView[COMPETITORVIEW] = (ListView) this.findViewById(R.id.social_listview_competitor);
		listView[STANGERVIEW] = (ListView) this.findViewById(R.id.social_listview_stranger);
		listView[BLACKVIEW] = (ListView) this.findViewById(R.id.social_listview_blacklist);

//		friendView = (ListView) this.findViewById(R.id.social_listview_friend);
//		
//		competitorView = (ListView) this
//				.findViewById(R.id.social_listview_competitor);
//
//		strangerView = (ListView) this
//				.findViewById(R.id.social_listview_stranger);
//
//		blacklistView = (ListView) this
//				.findViewById(R.id.social_listview_blacklist);

		listView[FRIENDVIEW].setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View v,
					final int position, long id) 
			{
				final int idex = position;
				
				CheckedItemAdapter adapter = (CheckedItemAdapter)listView[FRIENDVIEW].getAdapter();
				
				if(adapter.isCheck(position))
				{
					adapter.setCheck(position, false);
				}
				else
				{
					adapter.setCheck(position, true);
				}
//				if(! isfromPlanningDept)
//				{
//				String social_item3 = MainActivity.resources.getString(R.string.social_item3);
//				String[] item = social_item3.split(",");				
//				//String[] item = new String[] {"发送消息", "查看资料", "发送邮件", "加入黑名单",
//				//		"删除", "招揽客流" };
//				final int p = position;
//				final  AlertDialog dlg1 = new AlertDialog.Builder(SocialActivity.this).create();
//				dlg1.show();
//				dlg1.getWindow().setContentView(R.layout.mapmenu3_list);
//				ListView lv = (ListView)dlg1.findViewById(R.id.mapmenu3_list);				
//				ArrayList<HashMap<String, String>>list= new  ArrayList<HashMap<String,String>>();
//				HashMap<String, String> map;
//				for (int i = 0; i < item.length; i++) 
//				{
//						 map = new HashMap<String, String>();						 
//						 map.put("name", item[i]);
//						 list.add(map);
//			    }
//
//					 SimpleAdapter listAdapter = new SimpleAdapter(SocialActivity.this,list,   
//				                R.layout.mapmenu3_item, new String[] {"name"},   
//				                new int[] {R.id.mapmenu3_list_text});   
//			        lv.setAdapter(listAdapter);
//			        lv.setOnItemClickListener(new OnItemClickListener() {
//
//						@Override
//						public void onItemClick(AdapterView<?> parent,
//								View view, int index, long id) {
//							// TODO Auto-generated method stub
//							Social fr = (Social) GameData.friend.elementAt(p);
//							if (index == 0)// 发送消息
//							{
//								Intent intent = new Intent();
//								Bundle bundle = new Bundle();
//								bundle.putInt("type", 1);
//
//								bundle.putString("destname", fr.name);
//								bundle.putLong("destId", fr.id);
//
//								intent.putExtras(bundle);
//
//								intent.setClass(SocialActivity.this,
//										ChatActivity.class);
//
//								SocialActivity.this.startActivityForResult(intent,
//										GameDefinition.REQWRITE_MESSAGE);
//								dlg1.dismiss();
//							} else if (index == 1)// 查看资料
//							{
//								Intent intent = new Intent();
//								Bundle bundle = new Bundle();
//								bundle.putByte("type", (byte) 1);
//								bundle.putInt("index", p);
//
//								intent.putExtras(bundle);
//
//								SocialActivity.this.Activitychange(
//										SocialDetailActivity.class, intent);
//								dlg1.dismiss();
//							} else if (index == 2)// 发送邮件
//							{
//								Intent intent = new Intent();
//
//								Bundle bundle = new Bundle();
//
//								bundle.putString("receiver", fr.name);
//
//								intent.putExtras(bundle);
//
//								intent.setClass(SocialActivity.this,
//										WriteMailActivity.class);
//
//								SocialActivity.this.startActivityForResult(intent,
//										GameDefinition.REQWRITE_MAIL);
//								dlg1.dismiss();
//							} else if (index == 3)// 加入黑名单
//							{
//								Connection.sendMessage(
//										GameProtocol.CONNECTION_Relation_Add_Req,
//										ConstructData.get_Relationship_operation(
//												fr.id, FRIEND_TYPE_BLOCKED));
//								dlg1.dismiss();
//							} else if (index == 4)// 删除
//							{
//								Connection
//										.sendMessage(
//												GameProtocol.CONNECTION_Relation_Delete_Req,
//												ConstructData
//														.get_Relationship_operation(
//																fr.id,
//																FRIEND_TYPE_FRIEND));
//								dlg1.dismiss();
//							} else if (index == 5)// 招揽客流
//							{
//								Connection
//										.sendMessage(
//												GameProtocol.CONNECTION_SHOP_Avai_Recruit_List_req,
//												ConstructData.getAvaiRecruit(
//														(byte) 1, fr.id, (byte) 0,
//														(short) 0, 0, (byte) 50));
//								dlg1.dismiss();
//							}else if(index==6)
//							{
//								Intent intent = new Intent();
//								intent.putExtra("jumpsocial", "isFromSocial");																			
//								s = (Social) GameData.friend.elementAt(position);
//								friend_ID = idex;
//								mContext.Activitychange(PlanningDeptActivity.class, intent);
//								dlg1.dismiss();
//							}
//						}
//					});
//
//			}else if(isfromPlanningDept){
//				//进入员工列表
//				if(PlanningDeptActivity.pd_index == 1||PlanningDeptActivity.pd_index ==5)
//				{  friend_ID = idex;
//					s = (Social) GameData.friend.elementAt(position);
//					Intent intent = new Intent();
//
//				Bundle bundle = new Bundle();
//
//				bundle.putByte("type", (byte) 3);
//
//				bundle.putString("staffname", s.name);
//
//				bundle.putString("mastername", "");
//
//				bundle.putByte("size", (byte) 0);
//
//				bundle.putByteArray("office", new byte[] { 0 });
//
//				bundle.putByteArray("tType", new byte[] { 0 });
//
//				bundle.putByteArray("level", new byte[] { 0 });
//
//				bundle.putInt("begin", 0);
//
//				bundle.putInt("count", 50);
//				bundle.putString("fromsocial", "socialactivity");
//
//				intent.putExtras(bundle);
//
//				intent.setClass(SocialActivity.this,
//						DiglistActivity.class);
//				
//				SocialActivity.this.startActivity(intent);
//
//					Connection.sendMessage(
//							GameProtocol.REQSearchStaff_Req,
//							ConstructData.SearchStaff_Req((byte) 3,s.name, "",
//									 (byte) 0, new byte[] { 0 },
//									new byte[] { 0 }, new byte[] { 0 },
//									0, 50));
//				}//进入店铺列表
//				else if(PlanningDeptActivity.pd_index == 2 ||PlanningDeptActivity.pd_index == 3||PlanningDeptActivity.pd_index == 4)
//				{   friend_ID = idex;
//				    s = (Social) GameData.friend.elementAt(position);
//				  Intent intent = new Intent();
//				  Bundle bundle = new Bundle();
//				  bundle.putString("fromsocial", "socialactivity");
//				  intent.putExtras(bundle);
//				  intent.setClass(SocialActivity.this,
//						PlanningDeptShoplistActivity.class);
//				 
//				  SocialActivity.this.startActivity(intent);
//					Connection.sendMessage(
//							GameProtocol.SEARCHSHOP_REQ,
//							ConstructData.getShopAskList((long)s.id));
//				
//					//SocialActivity.mContext.Activitychange(PlanningDeptShoplistActivity.class, null);
//				}
//				/**add by zzx 
//				 * 直接对人物产生影响
//				 * */
//				else if(PlanningDeptActivity.pd_index == 6)
//				{ s = (Social) GameData.friend.elementAt(position);
//					Connection.sendMessage(
//							GameProtocol.PLANNINGACTION_REQ,
//							ConstructData.getPlanningAction((byte)PlanningDeptActivity.pd_index,(long)s.id,(byte)1,new long[]{0}));
//					
//				}
//			}
		    }
		}

		);

		listView[COMPETITORVIEW].setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				if(!isfromPlanningDept){
				String social_item4 = MainActivity.resources.getString(R.string.social_item4);
				String[] item = social_item4.split(",");
				//String[] item = new String[] { "发送消息", "查看资料", "发送邮件", "加入黑名单",
				//		"加为好友", "删除", "招揽客流" };
				final int p = position;
				final  AlertDialog dlg1 = new AlertDialog.Builder(SocialActivity.this).create();
				dlg1.show();
				dlg1.getWindow().setContentView(R.layout.mapmenu3_list);
				ListView lv = (ListView)dlg1.findViewById(R.id.mapmenu3_list);				
				ArrayList<HashMap<String, String>>list= new  ArrayList<HashMap<String,String>>();
				HashMap<String, String> map;
				for (int i = 0; i < item.length; i++) 
				{
					//	shop[i] = GameData.corporation.shop[i].name;
						 map = new HashMap<String, String>();						 
						 map.put("name", item[i]);
						 list.add(map);
					}

					 SimpleAdapter listAdapter = new SimpleAdapter(SocialActivity.this,list,   
				                R.layout.mapmenu3_item, new String[] {"name"},   
				                new int[] {R.id.mapmenu3_list_text});   
			        lv.setAdapter(listAdapter);
			        lv.setOnItemClickListener(new OnItemClickListener() {

						public void onItemClick(AdapterView<?> parent,
								View view, int index, long id) {
							// TODO Auto-generated method stub
							Social fr = (Social) GameData.competitor.elementAt(p);
							if (index == 0)// 发送消息
							{
								Intent intent = new Intent();
								Bundle bundle = new Bundle();
								bundle.putInt("type", 1);

								bundle.putString("destname", fr.name);
								bundle.putLong("destId", fr.id);

								intent.putExtras(bundle);

								intent.setClass(SocialActivity.this,
										ChatActivity.class);

								SocialActivity.this.startActivityForResult(intent,
										GameDefinition.REQWRITE_MESSAGE);
								dlg1.dismiss();
							} else if (index == 1)// 查看资料
							{
								Intent intent = new Intent();
								Bundle bundle = new Bundle();
								bundle.putByte("type", (byte) 4);
								bundle.putInt("index", p);

								intent.putExtras(bundle);

								SocialActivity.this.Activitychange(
										SocialDetailActivity.class, intent);
								dlg1.dismiss();
							} else if (index == 2)// 发送邮件
							{
								Intent intent = new Intent();

								Bundle bundle = new Bundle();

								bundle.putString("receiver", fr.name);

								intent.putExtras(bundle);

								intent.setClass(SocialActivity.this,
										WriteMailActivity.class);

								SocialActivity.this.startActivityForResult(intent,
										GameDefinition.REQWRITE_MAIL);
								dlg1.dismiss();
							} else if (index == 3)// 加入黑名单
							{
								Connection.sendMessage(
										GameProtocol.CONNECTION_Relation_Add_Req,
										ConstructData.get_Relationship_operation(
												fr.id, FRIEND_TYPE_BLOCKED));
								dlg1.dismiss();
							} else if (index == 4)// 加入好友
							{
								Connection.sendMessage(
										GameProtocol.CONNECTION_Relation_Add_Req,
										ConstructData.get_Relationship_operation(
												fr.id, FRIEND_TYPE_FRIEND));
								dlg1.dismiss();
							} else if (index == 5)// 删除
							{
								Connection
										.sendMessage(
												GameProtocol.CONNECTION_Relation_Delete_Req,
												ConstructData
														.get_Relationship_operation(
																fr.id,
																FRIEND_TYPE_VIE));
								dlg1.dismiss();
							} else if (index == 6)// 招揽客流
							{
								Connection
										.sendMessage(
												GameProtocol.CONNECTION_SHOP_Avai_Recruit_List_req,
												ConstructData.getAvaiRecruit(
														(byte) 1, fr.id, (byte) 0,
														(short) 0, 0, (byte) 50));
								dlg1.dismiss();

							}else if(index==7)
							{
								mContext.Activitychange(PlanningDeptActivity.class, null);
								dlg1.dismiss();
							}
						}
					});
				/*Builder builder = new AlertDialog.Builder(SocialActivity.this);
				final int p = position;

				builder.setItems(item, new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int index) {
						Social fr = (Social) GameData.competitor.elementAt(p);
						if (index == 0)// 发送消息
						{
							Intent intent = new Intent();
							Bundle bundle = new Bundle();
							bundle.putInt("type", 1);

							bundle.putString("destname", fr.name);
							bundle.putLong("destId", fr.id);

							intent.putExtras(bundle);

							intent.setClass(SocialActivity.this,
									ChatActivity.class);

							SocialActivity.this.startActivityForResult(intent,
									GameDefinition.REQWRITE_MESSAGE);
						} else if (index == 1)// 查看资料
						{
							Intent intent = new Intent();
							Bundle bundle = new Bundle();
							bundle.putByte("type", (byte) 4);
							bundle.putInt("index", p);

							intent.putExtras(bundle);

							SocialActivity.this.Activitychange(
									SocialDetailActivity.class, intent);
						} else if (index == 2)// 发送邮件
						{
							Intent intent = new Intent();

							Bundle bundle = new Bundle();

							bundle.putString("receiver", fr.name);

							intent.putExtras(bundle);

							intent.setClass(SocialActivity.this,
									WriteMailActivity.class);

							SocialActivity.this.startActivityForResult(intent,
									GameDefinition.REQWRITE_MAIL);
						} else if (index == 3)// 加入黑名单
						{
							Connection.sendMessage(
									GameProtocol.CONNECTION_Relation_Add_Req,
									ConstructData.get_Relationship_operation(
											fr.id, FRIEND_TYPE_BLOCKED));
						} else if (index == 4)// 加入好友
						{
							Connection.sendMessage(
									GameProtocol.CONNECTION_Relation_Add_Req,
									ConstructData.get_Relationship_operation(
											fr.id, FRIEND_TYPE_FRIEND));
						} else if (index == 5)// 删除
						{
							Connection
									.sendMessage(
											GameProtocol.CONNECTION_Relation_Delete_Req,
											ConstructData
													.get_Relationship_operation(
															fr.id,
															FRIEND_TYPE_VIE));
						} else if (index == 6)// 招揽客流
						{
							Connection
									.sendMessage(
											GameProtocol.CONNECTION_SHOP_Avai_Recruit_List_req,
											ConstructData.getAvaiRecruit(
													(byte) 1, fr.id, (byte) 0,
													(short) 0, 0, (byte) 50));

						}else if(index==7)
						{
							mContext.Activitychange(PlanningDeptActivity.class, null);
						}
					}

				}

				).create().show();*/
			}else if(isfromPlanningDept){

				//进入员工列表
				if(PlanningDeptActivity.pd_index == 1||PlanningDeptActivity.pd_index ==5)
				{  
					s = (Social) GameData.competitor.elementAt(position);
					Intent intent = new Intent();

				Bundle bundle = new Bundle();

				bundle.putByte("type", (byte) 3);

				bundle.putString("staffname", s.name);

				bundle.putString("mastername", "");

				bundle.putByte("size", (byte) 0);

				bundle.putByteArray("office", new byte[] { 0 });

				bundle.putByteArray("tType", new byte[] { 0 });

				bundle.putByteArray("level", new byte[] { 0 });

				bundle.putInt("begin", 0);

				bundle.putInt("count", 50);
				bundle.putString("fromsocial", "socialactivity");
				intent.putExtras(bundle);

				intent.setClass(SocialActivity.this,
						DiglistActivity.class);
				
				SocialActivity.this.startActivity(intent);

					Connection.sendMessage(
							GameProtocol.REQSearchStaff_Req,
							ConstructData.SearchStaff_Req((byte) 3,s.name, "",
									 (byte) 0, new byte[] { 0 },
									new byte[] { 0 }, new byte[] { 0 },
									0, 50));
				}//进入店铺列表
				else if(PlanningDeptActivity.pd_index == 2 ||PlanningDeptActivity.pd_index == 3||PlanningDeptActivity.pd_index == 4)
				{ s = (Social) GameData.competitor.elementAt(position);
				  Intent intent = new Intent();
				  intent.setClass(SocialActivity.this,
						PlanningDeptShoplistActivity.class);
				  
				  SocialActivity.this.startActivity(intent);
					Connection.sendMessage(
							GameProtocol.SEARCHSHOP_REQ,
							ConstructData.getShopAskList((long)s.id));
				
					//SocialActivity.mContext.Activitychange(PlanningDeptShoplistActivity.class, null);
				}
				/**add by zzx 
				 * 直接对人物产生影响
				 * */
				else if(PlanningDeptActivity.pd_index == 6)
				{ s = (Social) GameData.competitor.elementAt(position);
					Connection.sendMessage(
							GameProtocol.PLANNINGACTION_REQ,
							ConstructData.getPlanningAction((byte)PlanningDeptActivity.pd_index,(long)s.id,(byte)1,new long[]{0}));
					
				}
			
			}
			}
		}

		);

		listView[STANGERVIEW].setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				if(!isfromPlanningDept){
				String social_item5 = MainActivity.resources.getString(R.string.social_item5);
				String[] item = social_item5.split(",");
				//String[] item = new String[] { "发送消息", "加为好友", "查看资料", "加入黑名单" };
				final int p = position;
				final  AlertDialog dlg1 = new AlertDialog.Builder(SocialActivity.this).create();
				dlg1.show();
				dlg1.getWindow().setContentView(R.layout.mapmenu3_list);
				ListView lv = (ListView)dlg1.findViewById(R.id.mapmenu3_list);				
				ArrayList<HashMap<String, String>>list= new  ArrayList<HashMap<String,String>>();
				HashMap<String, String> map;
				for (int i = 0; i < item.length; i++) 
				{
						 map = new HashMap<String, String>();						 
						 map.put("name", item[i]);
						 list.add(map);
			    }

					 SimpleAdapter listAdapter = new SimpleAdapter(SocialActivity.this,list,   
				                R.layout.mapmenu3_item, new String[] {"name"},   
				                new int[] {R.id.mapmenu3_list_text});   
			        lv.setAdapter(listAdapter);
			        lv.setOnItemClickListener(new OnItemClickListener() {

						public void onItemClick(AdapterView<?> parent,
								View view, int index, long id) {
							// TODO Auto-generated method stub
							Social fr = (Social) GameData.stranger.elementAt(p);
							if (index == 0)// 发送消息
							{
								Intent intent = new Intent();
								Bundle bundle = new Bundle();
								bundle.putInt("type", 1);

								bundle.putString("destname", fr.name);

								bundle.putLong("destId", fr.id);

								intent.putExtras(bundle);

								intent.setClass(SocialActivity.this,
										ChatActivity.class);

								SocialActivity.this.startActivityForResult(intent,
										GameDefinition.REQWRITE_MESSAGE);
								dlg1.dismiss();
							} else if (index == 1)// 加为好友
							{
								Connection.sendMessage(
										GameProtocol.CONNECTION_Relation_Add_Req,
										ConstructData.get_Relationship_operation(
												fr.id, FRIEND_TYPE_FRIEND));
								dlg1.dismiss();
							} else if (index == 2)// 查看资料
							{
								Intent intent = new Intent();
								Bundle bundle = new Bundle();
								bundle.putByte("type", (byte) 3);
								bundle.putInt("index", p);

								intent.putExtras(bundle);

								SocialActivity.this.Activitychange(
										SocialDetailActivity.class, intent);
								dlg1.dismiss();
							} else if (index == 3)// 加入黑名单
							{
								Connection.sendMessage(
										GameProtocol.CONNECTION_Relation_Add_Req,
										ConstructData.get_Relationship_operation(
												fr.id, FRIEND_TYPE_BLOCKED));
								dlg1.dismiss();
							}else if(index==4)
							{
								mContext.Activitychange(PlanningDeptActivity.class, null);
								dlg1.dismiss();
							}
						}
					});
				/*Builder builder = new AlertDialog.Builder(SocialActivity.this);
				final int p = position;

				builder.setItems(item, new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int index) {
						Social fr = (Social) GameData.stranger.elementAt(p);
						if (index == 0)// 发送消息
						{
							Intent intent = new Intent();
							Bundle bundle = new Bundle();
							bundle.putInt("type", 1);

							bundle.putString("destname", fr.name);

							bundle.putLong("destId", fr.id);

							intent.putExtras(bundle);

							intent.setClass(SocialActivity.this,
									ChatActivity.class);

							SocialActivity.this.startActivityForResult(intent,
									GameDefinition.REQWRITE_MESSAGE);
						} else if (index == 1)// 加为好友
						{
							Connection.sendMessage(
									GameProtocol.CONNECTION_Relation_Add_Req,
									ConstructData.get_Relationship_operation(
											fr.id, FRIEND_TYPE_FRIEND));
						} else if (index == 2)// 查看资料
						{
							Intent intent = new Intent();
							Bundle bundle = new Bundle();
							bundle.putByte("type", (byte) 3);
							bundle.putInt("index", p);

							intent.putExtras(bundle);

							SocialActivity.this.Activitychange(
									SocialDetailActivity.class, intent);
						} else if (index == 3)// 加入黑名单
						{
							Connection.sendMessage(
									GameProtocol.CONNECTION_Relation_Add_Req,
									ConstructData.get_Relationship_operation(
											fr.id, FRIEND_TYPE_BLOCKED));
						}else if(index==4)
						{
							mContext.Activitychange(PlanningDeptActivity.class, null);
						}

					}

				}

				).create().show();*/
			}else{

				//进入员工列表
				if(PlanningDeptActivity.pd_index == 1||PlanningDeptActivity.pd_index ==5)
				{  
					s = (Social) GameData.stranger.elementAt(position);
					Intent intent = new Intent();

				Bundle bundle = new Bundle();

				bundle.putByte("type", (byte) 3);

				bundle.putString("staffname", s.name);

				bundle.putString("mastername", "");

				bundle.putByte("size", (byte) 0);

				bundle.putByteArray("office", new byte[] { 0 });

				bundle.putByteArray("tType", new byte[] { 0 });

				bundle.putByteArray("level", new byte[] { 0 });

				bundle.putInt("begin", 0);

				bundle.putInt("count", 50);
				bundle.putString("fromsocial", "socialactivity");
				intent.putExtras(bundle);

				intent.setClass(SocialActivity.this,
						DiglistActivity.class);
				
				SocialActivity.this.startActivity(intent);

					Connection.sendMessage(
							GameProtocol.REQSearchStaff_Req,
							ConstructData.SearchStaff_Req((byte) 3,s.name, "",
									 (byte) 0, new byte[] { 0 },
									new byte[] { 0 }, new byte[] { 0 },
									0, 50));
				}//进入店铺列表
				else if(PlanningDeptActivity.pd_index == 2 ||PlanningDeptActivity.pd_index == 3||PlanningDeptActivity.pd_index == 4)
				{ s = (Social) GameData.stranger.elementAt(position);
				  Intent intent = new Intent();
				  intent.setClass(SocialActivity.this,
						PlanningDeptShoplistActivity.class);
				 
				  SocialActivity.this.startActivity(intent);
					Connection.sendMessage(
							GameProtocol.SEARCHSHOP_REQ,
							ConstructData.getShopAskList((long)s.id));
				
					//SocialActivity.mContext.Activitychange(PlanningDeptShoplistActivity.class, null);
				}
				/**add by zzx 
				 * 直接对人物产生影响
				 * */
				else if(PlanningDeptActivity.pd_index == 6)
				{ s = (Social) GameData.stranger.elementAt(position);
					Connection.sendMessage(
							GameProtocol.PLANNINGACTION_REQ,
							ConstructData.getPlanningAction((byte)PlanningDeptActivity.pd_index,(long)s.id,(byte)1,new long[]{0}));
					
				}
			
			}
		}
		}

		);

		listView[BLACKVIEW].setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				if(! isfromPlanningDept){
				String social_item6 = MainActivity.resources.getString(R.string.social_item6);
				String[] item = social_item6.split(",");
				//String[] item = new String[] { "加为好友", "查看资料", "删除" };
				final int p = position;
				final  AlertDialog dlg1 = new AlertDialog.Builder(SocialActivity.this).create();
				dlg1.show();
				dlg1.getWindow().setContentView(R.layout.mapmenu3_list);
				ListView lv = (ListView)dlg1.findViewById(R.id.mapmenu3_list);				
				ArrayList<HashMap<String, String>>list= new  ArrayList<HashMap<String,String>>();
				HashMap<String, String> map;
				for (int i = 0; i < item.length; i++) {
					//	shop[i] = GameData.corporation.shop[i].name;
						 map = new HashMap<String, String>();						 
						 map.put("name", item[i]);
						 list.add(map);
					}

					 SimpleAdapter listAdapter = new SimpleAdapter(SocialActivity.this,list,   
				                R.layout.mapmenu3_item, new String[] {"name"},   
				                new int[] {R.id.mapmenu3_list_text});   
			        lv.setAdapter(listAdapter);
			        lv.setOnItemClickListener(new OnItemClickListener() {

						public void onItemClick(AdapterView<?> parent,
								View view, int index, long id) {
							// TODO Auto-generated method stub
							long pid = ((Social) GameData.blacklist.elementAt(p)).id;
							if (index == 0)// 加为好友
							{
								Connection.sendMessage(
										GameProtocol.CONNECTION_Relation_Add_Req,
										ConstructData.get_Relationship_operation(
												pid, FRIEND_TYPE_FRIEND));
								dlg1.dismiss();
							} else if (index == 1)// 查看资料
							{

								Intent intent = new Intent();
								Bundle bundle = new Bundle();
								bundle.putByte("type", (byte) 2);
								bundle.putInt("index", p);

								intent.putExtras(bundle);

								SocialActivity.this.Activitychange(
										SocialDetailActivity.class, intent);
								dlg1.dismiss();
							} else if (index == 2)// 删除
							{
								Connection
										.sendMessage(
												GameProtocol.CONNECTION_Relation_Delete_Req,
												ConstructData
														.get_Relationship_operation(
																pid,
																FRIEND_TYPE_BLOCKED));
								dlg1.dismiss();
							}else if(index==3)
							{
								mContext.Activitychange(PlanningDeptActivity.class, null);
								dlg1.dismiss();
							}
						}
					});
				/*Builder builder = new AlertDialog.Builder(SocialActivity.this);

				final int p = position;
				builder.setItems(item, new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int index) {
						long pid = ((Social) GameData.blacklist.elementAt(p)).id;
						if (index == 0)// 加为好友
						{
							Connection.sendMessage(
									GameProtocol.CONNECTION_Relation_Add_Req,
									ConstructData.get_Relationship_operation(
											pid, FRIEND_TYPE_FRIEND));
						} else if (index == 1)// 查看资料
						{

							Intent intent = new Intent();
							Bundle bundle = new Bundle();
							bundle.putByte("type", (byte) 2);
							bundle.putInt("index", p);

							intent.putExtras(bundle);

							SocialActivity.this.Activitychange(
									SocialDetailActivity.class, intent);
						} else if (index == 2)// 删除
						{
							Connection
									.sendMessage(
											GameProtocol.CONNECTION_Relation_Delete_Req,
											ConstructData
													.get_Relationship_operation(
															pid,
															FRIEND_TYPE_BLOCKED));
						}else if(index==3)
						{
							mContext.Activitychange(PlanningDeptActivity.class, null);
						}

					}

				}

				).create().show();*/

			}else{

				//进入员工列表
				if(PlanningDeptActivity.pd_index == 1||PlanningDeptActivity.pd_index ==5)
				{  
					s = (Social) GameData.blacklist.elementAt(position);
					Intent intent = new Intent();

				Bundle bundle = new Bundle();

				bundle.putByte("type", (byte) 3);

				bundle.putString("staffname", s.name);

				bundle.putString("mastername", "");

				bundle.putByte("size", (byte) 0);

				bundle.putByteArray("office", new byte[] { 0 });

				bundle.putByteArray("tType", new byte[] { 0 });

				bundle.putByteArray("level", new byte[] { 0 });

				bundle.putInt("begin", 0);

				bundle.putInt("count", 50);
				bundle.putString("fromsocial", "socialactivity");
				intent.putExtras(bundle);

				intent.setClass(SocialActivity.this,
						DiglistActivity.class);
				
				SocialActivity.this.startActivity(intent);

					Connection.sendMessage(
							GameProtocol.REQSearchStaff_Req,
							ConstructData.SearchStaff_Req((byte) 3,s.name, "",
									 (byte) 0, new byte[] { 0 },
									new byte[] { 0 }, new byte[] { 0 },
									0, 50));
				}//进入店铺列表
				else if(PlanningDeptActivity.pd_index == 2 ||PlanningDeptActivity.pd_index == 3||PlanningDeptActivity.pd_index == 4)
				{ s = (Social) GameData.blacklist.elementAt(position);
				  Intent intent = new Intent();
				  intent.setClass(SocialActivity.this,
						PlanningDeptShoplistActivity.class);
				 
				  SocialActivity.this.startActivity(intent);
					Connection.sendMessage(
							GameProtocol.SEARCHSHOP_REQ,
							ConstructData.getShopAskList((long)s.id));
				
					//SocialActivity.mContext.Activitychange(PlanningDeptShoplistActivity.class, null);
				}
				/**add by zzx 
				 * 直接对人物产生影响
				 * */
				else if(PlanningDeptActivity.pd_index == 6)
				{ s = (Social) GameData.blacklist.elementAt(position);
					Connection.sendMessage(
							GameProtocol.PLANNINGACTION_REQ,
							ConstructData.getPlanningAction((byte)PlanningDeptActivity.pd_index,(long)s.id,(byte)1,new long[]{0}));
					
				}
			
			}
			}
		}

		);
		tabButton = new Button[4];
		
		tabButton[0] = (Button) this.findViewById(R.id.social_tab_friend);

		tabButton[1] = (Button) this.findViewById(R.id.social_tab_competitor);

		tabButton[2] = (Button) this.findViewById(R.id.social_tab_stranger);

		tabButton[3] = (Button) this.findViewById(R.id.social_tab_blacklist);

		for(int i =0; i < tabButton.length; i++)
		{
			tabButton[i].setOnTouchListener(this);
			tabButton[i].setOnClickListener(buttonListener);
		}
		
		
		

		tabButton[0].setBackgroundResource(R.drawable.selection_selected);
		setTabBackGround(tabButton[0]);
		setSelectIndex(0);
		//=================================================================功能菜单
		//好友
		friendButton = new Button[5];
		
		friendButton[0] =(Button) this.findViewById(R.id.socail_friend_button_seach);
		friendButton[1] =(Button) this.findViewById(R.id.socail_friend_button_addblack);
		friendButton[2] = (Button) this.findViewById(R.id.socail_friend_button_look);
		friendButton[3] = (Button) this.findViewById(R.id.socail_friend_button_delete);
		friendButton[4] = (Button) this.findViewById(R.id.socail_friend_button_interact);
		
		for(int i =0; i < friendButton.length; i++)
		{
			friendButton[i].setOnClickListener(buttonListener);
		}
		//竞争者
		competitorButton = new Button[4];
		competitorButton[0] = (Button)this.findViewById(R.id.socail_competitor_button_addblack);
		competitorButton[1] = (Button)this.findViewById(R.id.socail_competitor_button_look);
		competitorButton[2] = (Button)this.findViewById(R.id.socail_competitor_button_delete);
		competitorButton[3] = (Button)this.findViewById(R.id.socail_competitor_button_interact);
		for(int i =0; i < competitorButton.length; i++)
		{
			competitorButton[i].setOnClickListener(buttonListener);
		}
		//陌生人
		stangeButton = new Button[4];
		stangeButton[0] = (Button)this.findViewById(R.id.socail_stranger_button_addblack);
		stangeButton[1] = (Button)this.findViewById(R.id.socail_stranger_button_look);
		stangeButton[2] = (Button)this.findViewById(R.id.socail_stranger_button_delete);
		stangeButton[3] = (Button)this.findViewById(R.id.socail_stranger_button_interact);
		for(int i =0; i < stangeButton.length; i++)
		{
			stangeButton[i].setOnClickListener(buttonListener);
		}
		//黑名单
		blackButton = new Button[3];
		blackButton[0] =(Button)this.findViewById(R.id.socail_blacklist_button_look);
		blackButton[1] =(Button)this.findViewById(R.id.socail_blacklist_button_delete);
		blackButton[2] =(Button)this.findViewById(R.id.socail_blacklist_button_interact);
		for(int i =0; i < blackButton.length; i++)
		{
			blackButton[i].setOnClickListener(buttonListener);
		}
	}

	public void Activitychange(Class calss, Intent intent)  
	{
		if (intent == null) {
			intent = new Intent();
		}

		intent.setClass(SocialActivity.this, calss);
		
		this.startActivity(intent);

	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == GameDefinition.REQWRITE_MESSAGE) {

			if (data == null) {
				return;
			}
			Bundle bundle = data.getExtras();

			int type = bundle.getInt("type");// 0世界1私聊

			String content = bundle.getString("content");

			int channle = bundle.getInt("channle", 0);

			long destId = bundle.getLong("destId");

			String name = bundle.getString("destname");

			Chat chat = new Chat();
			chat.content = content;
			chat.channel = channle;
			chat.name = GameData.player.name;
			chat.id = GameData.player.id;

			int c = 0;

			if (chat.channel == 0) {
				c = 1;
			} else if (chat.channel == 1) {
				c = 2;
			}
//			// 添加到长住内存中
//			GameData.addChat(0, chat);// 向全部里添加聊天信息
//			GameData.addChat(c, chat);// 向频道里添加
			// 添加到显示界面中

			Connection.sendMessage(GameProtocol.CONNECTION_SEND_CHAT_REQ,
					ConstructData.getChatData((byte) chat.channel,
							chat.content, destId));
		} else if (requestCode == GameDefinition.REQWRITE_MAIL)// 写邮件
		{
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
				// Game.instance.initClewBox("系统消息",
				// "您不填写收件人的姓名，信件就不知道会寄到哪儿去了……", true);
				Toast.makeText(SocialActivity.this,
						MainActivity.resources.getString(R.string.social_toast1), Toast.LENGTH_SHORT)
						.show();
			} else if (title.trim().length() == 0) {
				// Game.instance.initClewBox("系统消息", "给您的邮件起一个言简意赅的主题吧。", true);
				Toast.makeText(SocialActivity.this, MainActivity.resources.getString(R.string.social_toast2),
						Toast.LENGTH_SHORT).show();
			} else if (content.trim().length() == 0) {
				// Game.instance.initClewBox("系统消息", "请您尽情挥洒文采，写一封生动的邮件吧~",
				// true);
				Toast.makeText(SocialActivity.this, MainActivity.resources.getString(R.string.social_toast3),
						Toast.LENGTH_SHORT).show();
			} else {
				if (receiverId != 0) {
					Connection.sendMessage(
							GameProtocol.CONNECTION_SEND_MAIL_REQ,
							ConstructData.getMail(receiverId, "", title,
									content, money, (byte) 0));
				} else {
					Connection.sendMessage(
							GameProtocol.CONNECTION_SEND_MAIL_REQ,
							ConstructData.getMail(0, receiver, title, content,
									money, (byte) 0));
				}
			}
		}

	}

	public void setSelectIndex() 
	{
		setSelectIndex(select);
	}
	
	public void setSelectIndex(int index) 
	{
		select = index;
		mViewFlipper.setDisplayedChild(select);
		Log.v("yz", "setSelectIndex="+index);
		
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		
		HashMap<String, Object> map = null;
		
		Vector v = GameData.getSocial(index);
		
		for(int i =0; i < v.size();i++)
		{
			s = (Social) v.elementAt(i);
			
			map = new HashMap<String, Object>();
			
			map.put("name", s.name);
			map.put("level", s.level + MainActivity.resources.getString(R.string.social_level));
			map.put("online", s.status == 0 ? MainActivity.resources.getString(R.string.social_outline) : MainActivity.resources.getString(R.string.social_online));
			map.put("isrecurited", s.isUnrecruit == 0?getString(R.string.social_norecruit):getString(R.string.social_recruited));
			list.add(map);
		}
		CheckedItemAdapter adapter = new CheckedItemAdapter(this, list,
				R.layout.social_item, new String[] { "name", "level",
						"online","isrecurited" }, new int[] { R.id.social_item_name,
						R.id.social_item_level, R.id.social_item_isonlie,R.id.social_item_isrecurited});
		listView[index].setAdapter(adapter);
	}

	public boolean onTouch(View view, MotionEvent arg1) 
	{
		setTabBackGround(view);
		return false;
	}
	
	public void setTabBackGround(View view)
	{
		for(int i =0; i < tabButton.length; i++)
		{
			tabButton[i].setBackgroundResource(R.drawable.selection);
			tabButton[i].setTextColor(R.color.black);
		}
		view.setBackgroundResource(R.drawable.selection_selected);
		((Button)view).setTextColor(R.color.red);
	}
	

	@Override
	public GameActivity getGameContext() 
	{
		return this;
	}
	
	@Override
	public void finish() 
	{
		mContext = null;
		super.finish();
	}
	
	public class ButtonListener implements OnClickListener
	{
		public void onClick(View v)
		{
			int id = v.getId();
			
			switch(id)
			{
			case R.id.social_tab_friend:
				setSelectIndex(0);
				break;
			case R.id.social_tab_competitor:
				setSelectIndex(1);
				break;
			case R.id.social_tab_stranger:
				setSelectIndex(2);
				break;
			case R.id.social_tab_blacklist:
				setSelectIndex(3);
				break;
			case R.id.socail_friend_button_seach:
				seachFriend();
				break;
			case R.id.socail_friend_button_addblack:
			case R.id.socail_competitor_button_addblack:
			case R.id.socail_stranger_button_addblack:
				addBlackItem(getCurSocialId(select));
				break;
			case R.id.socail_friend_button_look:
			case R.id.socail_competitor_button_look:
			case R.id.socail_stranger_button_look:
			case R.id.socail_blacklist_button_look:
				lookDetail(getSocialIndex(select),select);
				break;
			case R.id.socail_friend_button_delete:
			case R.id.socail_competitor_button_delete:
			case R.id.socail_stranger_button_delete:
			case R.id.socail_blacklist_button_delete:
				deleteItem(select);
				break;
			case R.id.socail_friend_button_interact:
			case R.id.socail_competitor_button_interact:
			case R.id.socail_stranger_button_interact:
			case R.id.socail_blacklist_button_interact:
				interact(select);
				break;
			
				
			
				
				
			case ID_SEND_MESSAGE:
				sendMessage(select);
				break;
			case ID_SEND_EMAIL:
				sendEmail(select);
				break;
			case ID_RECUIT:
				recruit(select);
				break;
			case ID_HELP_RECRUIT:
				dispel();
				break;
			default:
				break;
			}
		}
	}
	
	public void seachFriend()
	{
		String item[] = new String[] { MainActivity.resources.getString(R.string.social_item1), MainActivity.resources.getString(R.string.social_item2) };
		final AlertDialog dlg = new AlertDialog.Builder(SocialActivity.this).create();
		dlg.show();
		dlg.getWindow().setContentView(R.layout.system_dlg);
		Button level_search = (Button)dlg.findViewById(R.id.system_helping);
		Button name_search = (Button)dlg.findViewById(R.id.system_setting);
		//level_search.setTypeface(GameDefinition.face);
		//name_search.setTypeface(GameDefinition.face);
		level_search.setText(item[0]);
		name_search.setText(item[1]);
		level_search.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Connection.sendMessage(
						GameProtocol.CONNECTION_Search_User_Req,
						ConstructData.get_Search_User_Req((byte)0,(byte) 1,
								null));

				SocialActivity.this.Activitychange(
						SeacherFriendActivity.class, null);
				dlg.dismiss();
			}
		});
		name_search.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				View view = factory.inflate(
						R.layout.searchcondition_player_dailog,
						null);

				final EditText et = (EditText) view
						.findViewById(R.id.searchcondition_player_dialog_edit);

				Builder builder = new AlertDialog.Builder(
						SocialActivity.this);

				builder.setView(view);

				builder.setPositiveButton(MainActivity.resources.getString(R.string.social_ok),
						new DialogInterface.OnClickListener() 
				{

							public void onClick(
									DialogInterface arg0, int arg1) {
								String name = et.getText()
										.toString();
								SocialActivity.this
										.Activitychange(
												SeacherFriendActivity.class,
												null);
								Connection
										.sendMessage(
												GameProtocol.CONNECTION_Search_User_Req,
												ConstructData
														.get_Search_User_Req((byte)0,
																(byte) 2,
																name == null ? ""
																		: name));
								dlg.dismiss();
							}

						});

				builder.setNegativeButton(MainActivity.resources.getString(R.string.social_cancel),
						new DialogInterface.OnClickListener() {

							public void onClick(
									DialogInterface arg0, int arg1) {
								dlg.dismiss();
							}

						}).create().show();
				dlg.dismiss();
			}
		});
	}
	/**
	 * 
	 * <p>Description:添加黑名单</p>
	 * @author YangZheng
	 * @param id 成员ID
	 */
	public void addBlackItem(long id)
	{
		if(id <=0)
		{
			GameAPI.showToast(getString(R.string.social_noselect));
		}
		else
		{
			Connection.sendMessage(
					GameProtocol.CONNECTION_Relation_Add_Req,
					ConstructData.get_Relationship_operation(
							id, FRIEND_TYPE_BLOCKED));
		}
	}
	/**
	 * 
	 * <p>Description:</p>
	 * @author YangZheng
	 * @param index 对应的集合中的索引位置
	 * @param type 类型 0是好友1竞争者2陌生人3黑名单
	 */
	public void lookDetail(int index,int type)
	{
		if(index < 0)
		{
			GameAPI.showToast(getString(R.string.social_noselect));
		}
		else
		{
			Intent intent = new Intent();
			Bundle bundle = new Bundle();
			bundle.putByte("type", (byte) (type+1));
			bundle.putInt("index", index);
			intent.putExtras(bundle);
			SocialActivity.this.Activitychange(
					SocialDetailActivity.class, intent);
		}
	}
	/**
	 * 
	 * <p>Description:删除选中项</p>
	 * @author Administrator
	 * @param select
	 */
	public void deleteItem(int select)
	{
		long id = getCurSocialId(select);
		if(id <=0)
		{
			GameAPI.showToast(getString(R.string.social_noselect));
		}
		else
		{
			Connection
			.sendMessage(
					GameProtocol.CONNECTION_Relation_Delete_Req,
					ConstructData
							.get_Relationship_operation(
									id,
									FRIEND_TYPE_FRIEND));
		}
	}
	/**
	 * 
	 * <p>Description:社交包括发消息，发邮件，帮助招揽等内容</p>
	 * @author yangzheng
	 * @param select 0是好友1竞争者2陌生人3黑名单 其中只有select=0是才有帮助招揽选项
	 */
	public void interact(int select)
	{
		if(select ==0)
		{
			showMenuDalog(new int[]{0,1,2,3});
		}
		else
		{
			showMenuDalog(new int[]{0,1,2});
		}
		
	}
	
	
	public void sendMessage(int select)
	{
		dlgDismiss();
		Intent intent = new Intent();
		
		Bundle bundle = new Bundle();
		
		Social s =getCurSocial(select);
		
		if(s != null)
		{
			bundle.putInt("type", 1);

			bundle.putString("destname", s.name);
			bundle.putLong("destId", s.id);

			intent.putExtras(bundle);

			intent.setClass(SocialActivity.this,
					ChatActivity.class);

			SocialActivity.this.startActivityForResult(intent,
					GameDefinition.REQWRITE_MESSAGE);
		}
		else
		{
			GameAPI.showToast(getString(R.string.social_noselect));
		}
		
	}
	
	public void sendEmail(int select)
	{
		dlgDismiss();
		Intent intent = new Intent();
		
		Bundle bundle = new Bundle();
		
		Social s =getCurSocial(select);
		
		if(s != null)
		{
			bundle.putString("receiver", s.name);
			
			intent.putExtras(bundle);
		
			intent.setClass(SocialActivity.this,
					WriteMailActivity.class);
		
			SocialActivity.this.startActivityForResult(intent,
					GameDefinition.REQWRITE_MAIL);
		}
		else
		{
			GameAPI.showToast(getString(R.string.social_noselect));
		}
	}
	
	public void recruit(int select)
	{
		dlgDismiss();
		
		Social s =getCurSocial(select);
		
		if(s != null)
		{
			Connection.sendMessage(
					GameProtocol.CONNECTION_SHOP_Avai_Recruit_List_req,
					ConstructData.getAvaiRecruit(
							(byte) 1, s.id, (byte) 0,
							(short) 0, 0, (byte) 50));
		}
		else
		{
			GameAPI.showToast(getString(R.string.social_noselect));
		}
		
	}
	/**
	 * 
	 * <p>Description:帮忙驱散</p>
	 * @author YangZheng
	 */
	public void dispel()
	{
		dlgDismiss();
		Social s =getCurSocial(0);
		
		if(s != null)
		{
			Connection.sendMessage(GameProtocol.Shop_Unrecruit_List_Req, SystemAPI.parseLongToBytes(s.id));
		}
		else
		{
			GameAPI.showToast(getString(R.string.social_noselect));
		}
		
	}
	
	
	
	/**
	 * 
	 * <p>Description:获取当前集合中选中项的ID</p>
	 * @author YangZheng
	 * @return
	 */
	public long getCurSocialId(int select)
	{
		Social s = getCurSocial(select);
		
		if(s != null)
		{
			return s.id;
		}
		
		return 0;
	}
	/**
	 * 
	 * <p>Description: 获取当前集合中选中项</p>
	 * @author YangZheng
	 * @return 
	 */
	public Social getCurSocial(int select)
	{
		int index = getSocialIndex(select);
		
		Social s = null;
		
		if(index >=0)	
		{
			 s = (Social)GameData.getSocial(select).elementAt(index);
		}
		
		return s;
	}
	/**
	 * 
	 * <p>Description:获取当前集合中选中项的索引</p>
	 * @author YangZheng
	 * @param select 选中的集合的索引
	 * @return 集合中选中项的索引 如果没有选中返回-1
	 */
	public int getSocialIndex(int select)
	{
	    ListView list = listView[select];
		
		CheckedItemAdapter adapter = (CheckedItemAdapter)list.getAdapter();
		
		int checked[] = adapter.getCheck();
		
		if(checked.length > 0) 
		{
			return checked[0];
		}
		return -1;	 
	}
	
	/**
	 * 弹出框所用的dialog
	 */
	public AlertDialog dlg = null;
	
	
	public void dlgDismiss()
	{
		if(dlg != null)
		{
			dlg.dismiss();
		}
		
	}
	/**
	 * 
	 * <p>Description:弹出菜单</p>
	 * @author YangZheng
	 * @param menu 菜单内容
	 */
	public void showMenuDalog(int[] index)
	{
		RelativeLayout relativelayout=new RelativeLayout (this);
		
		Button[] bt= new Button[index.length];
		
		RelativeLayout.LayoutParams lp = null;
		
		for(int i =0; i < menu.length; i++)
		{
			bt[i] = new Button(this);
			bt[i].setText(menu[index[i]]);
			bt[i].setBackgroundResource(R.drawable.popmenu_back);
			bt[i].setId(menuId[index[i]]);
			relativelayout.addView(bt[i]);
			lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
			bt[i].setLayoutParams(lp);
			
			if(i != 0)
			{
				lp.addRule(RelativeLayout.BELOW, bt[i-1].getId());
			}
			
			bt[i].setOnClickListener(buttonListener);
		}
		
	    dlg = new AlertDialog.Builder(SocialActivity.this).create();
		dlg.show();
		dlg.setContentView(relativelayout);
	}
	
 
	
	
	
}
