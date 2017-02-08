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
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.zrong.Android.Listener.ActivityTransform;
import com.zrong.Android.Util.Music;
import com.zrong.Android.View.FreshManLead;
import com.zrong.Android.View.SpriteView;
import com.zrong.Android.game.Connection;
import com.zrong.Android.game.ConstructData;
import com.zrong.Android.game.GameData;
import com.zrong.Android.online.network.GameProtocol;

public class MapmainMenuActivity extends GameActivity {


 
	
	//public static final String [] menu ={"地图","部门","店铺","员工","好友","邮件","聊天" ,"学堂" ,"商城","公司","商会","福利彩站"};
	public static final String mapmain_menu =String.valueOf(MainActivity.resources.getString(R.string.mapmainmenu_menu)) ;
	public static final String menu[] = mapmain_menu.split(",");
	public static MapmainMenuActivity mContext; 

	public static boolean isStoreFromMainMenu = false;// 用作判断商城是否有主菜单触发
	public static boolean isMaillFromMainMenu = false;// 用作判断邮件是否有主菜单触发

/*	public static final String[] menu = { "地图", "部门", "店铺", "员工", "好友", "邮件",
			"聊天", "学堂", "商城", "公司", "商会", "福利彩站" };

	public static MapmainMenuActivity mContext;*/
	private SpriteView employeeArrows, shopArrows;


	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		mContext = this;

		this.setContentView(R.layout.mapmainmenu);

		Button returnback = (Button) this
				.findViewById(R.id.mapmainmenu_button_returnback);

		Button cancel = (Button) this
				.findViewById(R.id.mapmainmenu_button_cancel);

		returnback.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				MapmainMenuActivity.this.finish();

			}
		});
		cancel.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				MapmainMenuActivity.this.finish();

			}
		});

		ListView lv = (ListView) this.findViewById(R.id.mapmainmenu_listview);

		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map;
		for (int i = 0; i < menu.length; i++) {
			map = new HashMap<String, String>();

			map.put("menu", menu[i]);
			list.add(map);
		}

		SimpleAdapter listAdapter = new SimpleAdapter(this, list,
				R.layout.mapmainmenu_item, new String[] { "menu" },
				new int[] { R.id.mapmainmenu_name });

		lv.setAdapter(listAdapter);

		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int index,
					long arg3) {

				switch (index) {
				case 0:
					Builder builder = new AlertDialog.Builder(
							MapmainMenuActivity.this);

					builder.setItems(GameData.mapNames,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface Dialog,
										int index) {
									GameData.isResetMap = true;
									GameData.mapIdIndexBack = (short) index;
									Connection
											.sendMessage(
													GameProtocol.CONNECTION_SEND_MAPAROUND_SHOP,
													ConstructData
															.getMapAroundShop(
																	GameData.mapIds[index],
																	(short) -1,
																	(short) -1,
																	(byte) 1,
																	(byte) (GameData.ARRAY_LENTH / 2)));
									Vector v = new Vector(5, 5);
									v.addElement(GameProtocol.CONNECTION_SEND_MAPAROUND_SHOP);
									MapmainMenuActivity.this.finish();
								}
							}).create().show();

					break;

				case 1:

					if (DepartmentActivity.mContext == null) {
						mContext.Activitychange(DepartmentActivity.class, null);
					}
					break;

				case 2:
					if (ShopListActivity.mContext == null) {
						mContext.Activitychange(ShopListActivity.class, null);
					}
					//zhouzhilong add ---新手引导
					if(GameData.isFreshMan && FreshManLead.caseId == 14){
						shopArrows.setVisibility(View.GONE);
						FreshManLead.caseId++;
					}
					break;
				case 3:
					if (EmployeeListActivity.mContext == null) {
						
						if(GameData.isFreshMan && FreshManLead.caseId==12){
							employeeArrows.setVisibility(View.GONE);
							FreshManLead.caseId ++;
						}
						mContext.Activitychange(EmployeeListActivity.class,
								null);						
					}
					break;
				case 4:
					if (SocialActivity.mContext == null) {
						mContext.Activitychange(SocialActivity.class, null);
					}
					break;
				case 5:// Mail
					isMaillFromMainMenu = true;
					MailboxActivity.isShow = true;
					Connection
							.sendMessage(
									GameProtocol.CONNECTION_SHOW_MAIL_LIST_REQ,
									ConstructData
											.getShowMailListData(MailboxActivity.IN_MAIL_BOX));
					Connection
							.sendMessage(
									GameProtocol.CONNECTION_SHOW_MAIL_LIST_REQ,
									ConstructData
											.getShowMailListData(MailboxActivity.OUT_MAIL_BOX));
					Connection
							.sendMessage(
									GameProtocol.CONNECTION_SHOW_MAIL_LIST_REQ,
									ConstructData
											.getShowMailListData(MailboxActivity.EVENT_LIST_BOX));
					MapmainMenuActivity.this.finish();
					MainActivity.mContext.Activitychange(MailboxActivity.class,
							null);
					break;
				case 6:// Chat
					if (ChatWindowActivity.context == null) {
						mContext.Activitychange(ChatWindowActivity.class, null);
					}
					break;
				case 7:
					if (VentureSchoolMain.mContext == null) {
						mContext.Activitychange(VentureSchoolMain.class,
								null);
					}
					break;
				case 8:
					// 商城
					isStoreFromMainMenu = true;
					Connection.sendMessage(GameProtocol.MALL_ASK_LIST,
							ConstructData.getPropsMallListType((byte) 1));
					// MapmainMenuActivity.this.finish();
					
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) 
					{
						e.printStackTrace();
					}
						break;
					case 9:
						if (CompanyTabActivity.mContext == null) {
							mContext.Activitychange(CompanyTabActivity.class, null);
						}
						break;
					case 10:
						String commerce_menu =MainActivity.resources.getString(R.string.commerce_menu);
						String[] menu = commerce_menu.split(",");							
						Builder build =new AlertDialog.Builder(MapmainMenuActivity.this);
						build.setItems(menu, new DialogInterface.OnClickListener() {
							
							 
							public void onClick(DialogInterface dialog, int index) {
								// TODO Auto-generated method stub
								if(index==0)
								{
									//mContext.Activitychange(CreateCommerceActivity.class, null);
									
								byte value = 0;
								Connection
										.sendMessage(
												GameProtocol.CONNECTION_SEND_COfC_Create_Info_Req,
												ConstructData
														.getCOfC_Create_Info_ReqData(value));
							} else if (index == 1) {

								 

								
								if (GameData.member == null||GameData.player.id==-1) {

									display();
								} else {
									mContext.Activitychange(
											CommerceInfoActivity.class, null);
								}

							} else if (index == 2) {
								
								// mContext.Activitychange(CommerceListActivity.class,
								// null);
								Connection
										.sendMessage(
												GameProtocol.CONNECTION_SEND_COfC_List_Info_Req,
												ConstructData
														.getMemberListRequestData(
																"", (short) 0,
																(short) 0,
																(short) 0,
																(short) 0));

							} else if (index == 3) {
								mContext.Activitychange(
										PropagandaActivity.class, null);
							} else if (index == 4) {
								
								Connection
										.sendMessage(
												GameProtocol.CONNECTION_SEND_ClientDatas_Req,
												ConstructData
														.ClientDatas_Req((byte) 8));
								Log.i("ppq", "输出信息");
								 
								// mContext.Activitychange(PropagandaListActivity.class,
								// null);
							}

						}
					}).show();

					break;
				case 11:
					
					if (Lottery_Station_Activity.mContext == null) {
						Connection
								.sendMessage(
										GameProtocol.LOTTERY_TACKET_ASK_LIST,
										ConstructData
												.getLotteryTicketAskList((byte) 2));
						// mContext.Activitychange(Lottery_Station_Activity.class,
						// null);
					}

					break;
				case 12:
					if (PlanningDeptActivity.mContext == null) {
						mContext.Activitychange(PlanningDeptActivity.class, null);
					}
					break;

				}
			}

		});
		
		
		// zhouzhilong add -->新手引导---指向箭头箭头
		if (GameData.isFreshMan && FreshManLead.caseId == 12) {
			employeeArrows = (SpriteView) this
					.findViewById(R.id.mapmainmenu_employee_arrows);
			employeeArrows.setSeries(0);
			employeeArrows.setVisibility(View.VISIBLE);
			
			shopArrows = (SpriteView) this
					.findViewById(R.id.mapmainmenu_shop_arrows);
			shopArrows.setSeries(0);
		}
		if(GameData.isFreshMan && FreshManLead.caseId == 14){
			shopArrows.setVisibility(View.VISIBLE);
		}
	}

	public void display() {
		Toast.makeText(this, MainActivity.resources.getString(R.string.toast_createcommerce), Toast.LENGTH_SHORT).show();
	}

	
	

	public void Activitychange(Class calss, Intent intent) {

		if (intent == null) {
			intent = new Intent();
		}
		intent.setClass(MapmainMenuActivity.this, calss);
		
		this.startActivity(intent);
	}
	
	@Override
	public void finish() 
	{
		mContext = null;
		super.finish();
	}

	@Override
	public GameActivity getGameContext() {
		// TODO Auto-generated method stub
		return this;
	}

}
