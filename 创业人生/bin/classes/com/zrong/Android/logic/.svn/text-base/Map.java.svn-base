package com.zrong.Android.logic;

import java.util.Vector;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zrong.Android.View.FreshManLead;
import com.zrong.Android.View.MapView;
import com.zrong.Android.View.SpriteView; 
import com.zrong.Android.activity.ChatWindowActivity;
import com.zrong.Android.activity.CommerceInfoActivity;
import com.zrong.Android.activity.CompanyTabActivity;
import com.zrong.Android.activity.DepartmentActivity; 
import com.zrong.Android.activity.DoctorInfoActivity;
import com.zrong.Android.activity.EmployeeListActivity;
import com.zrong.Android.activity.Lottery_Station_Activity;
import com.zrong.Android.activity.MailboxActivity;
import com.zrong.Android.activity.MainActivity; 
import com.zrong.Android.activity.PromotersActivity; 
import com.zrong.Android.activity.R; 
import com.zrong.Android.activity.ShopList2Activity;
import com.zrong.Android.activity.ShopListActivity;
import com.zrong.Android.activity.SocialActivity;
import com.zrong.Android.activity.SystemSettingActivity;
import com.zrong.Android.activity.TaskActivity;
import com.zrong.Android.activity.VentureSchoolMain;
import com.zrong.Android.game.Connection;
import com.zrong.Android.game.ConstructData;
import com.zrong.Android.game.GameData;
import com.zrong.Android.game.GameDefinition;
import com.zrong.Android.game.GameGroupControl;
import com.zrong.Android.online.network.GameProtocol;

public class Map extends LogicObject implements OnTouchListener {

	private MapView mapView;
	private View v;
	private static Handler endAnimationHandler = new Handler();

	private SpriteView taskArrows;
	private SpriteView menuArrows;
	private SpriteView unfoldArrows;
	
	private Button social,mail,shop,task,system,promoter,sharedCircle,doctor,map,company,department,store,employee,school,lottery;
	private LinearLayout linearTask;
	//public static boolean isMaillFromMainMenu = false;// 用作判断邮件是否有主菜单触发
	private boolean isUnFold = false;
	private boolean isUnMenu = false;
	private boolean isFromDept = false;
	private boolean isFromSocial = false;
	private boolean isFromCommerce = false;
	private boolean isShow = false;
	private Animation unFoldRightAnimation,unFoldDownAnimation,unFoldLeftAnimation,unFoldDownAnimation_1, foldRightAnimation, foldDownAnimation,foldLeftAnimation,foldDownAnimation_1;
	private Handler animationHandler;
	private Runnable unFoldAnimationRunnable , foldAnimationRunnable,unFoldAnimationRunnable_1 , foldAnimationRunnable_1;

	public Map(Context context, GameGroupControl control) {
		super(context, control);
	}

	 
	public void init() {
		initView();
	}

	public void initView() {

		v = View.inflate(context, R.layout.map, null);
		registerView(v);

		v.setId(GameDefinition.MapView);

		mapView = (MapView) v.findViewById(R.id.map_mapview);

		Button button = (Button) v.findViewById(R.id.map_menu);
		linearTask = (LinearLayout) v.findViewById(R.id.relative_task_arrows);
		/**社交*/
		social = (Button) v.findViewById(R.id.map_social);
		/**邮件*/
//		mail = (Button) v.findViewById(R.id.map_mail);
		/**商城*/
	    shop = (Button) v.findViewById(R.id.map_shop);
		/**任务*/
		task = (Button) v.findViewById(R.id.map_task); 
		/**系统*/
		system = (Button) v.findViewById(R.id.map_system);
		/**推广*/
		promoter = (Button) v.findViewById(R.id.map_promoter);
		/**分享圈*/
//		sharedCircle = (Button) v.findViewById(R.id.map_sharedcircle);
		/**睿博士*/
		doctor = (Button)v.findViewById(R.id.map_doctor);
		/**地图*/
		map = (Button)v.findViewById(R.id.map_map);
		/**公司*/
		company = (Button)v.findViewById(R.id.map_company);
		/**部门*/
		department = (Button)v.findViewById(R.id.map_department);
		/**店铺*/
		store = (Button)v.findViewById(R.id.map_store);
		/**员工*/
		employee = (Button)v.findViewById(R.id.map_employee);
		/**学堂*/
		school = (Button)v.findViewById(R.id.map_school);
		/**彩票*/
		lottery = (Button)v.findViewById(R.id.map_lottery);
		
		
		Button unfold = (Button) v.findViewById(R.id.map_unfold);
    	unfold.setOnClickListener(new OnClickListener() {			
			 
			public void onClick(View v) { 
				if(isUnMenu){//展开状态
		    		animationHandler.removeCallbacks(foldAnimationRunnable_1);
		    		animationHandler.post(foldAnimationRunnable_1);	
		    		isUnMenu = !isUnMenu;
		    		}
			
				showMenuOrNot();
				unfoldArrows.setVisibility(View.GONE);
			}
		});
    	
        unFoldRightAnimation = AnimationUtils.loadAnimation(v.getContext(), R.anim.slide_in_right);
        unFoldDownAnimation = AnimationUtils.loadAnimation(v.getContext(), R.anim.slide_in_down);
        unFoldDownAnimation_1 = AnimationUtils.loadAnimation(v.getContext(), R.anim.slide_in_down);
        unFoldLeftAnimation = AnimationUtils.loadAnimation(v.getContext(), R.anim.slide_in_left);
        
        unFoldRightAnimation.setAnimationListener(new AnimationListener() {			
			 
			public void onAnimationStart(Animation animation) {				
			}			
			 
			public void onAnimationRepeat(Animation animation) {
			}			
			 
			public void onAnimationEnd(Animation animation) {
				if (GameData.isFreshMan) {
					if (FreshManLead.caseId == 3 || FreshManLead.caseId == 16) {
						Log.i("Log", "FreshManLead----caseId :"+FreshManLead.caseId);						
						taskArrows.setVisibility(View.VISIBLE);
					}
				}
				linearTask.setVisibility(View.VISIBLE); 
				promoter.setVisibility(View.VISIBLE);
				system.setVisibility(View.VISIBLE);	
			}
		});
        unFoldDownAnimation.setAnimationListener(new AnimationListener() {			
			 
			public void onAnimationStart(Animation animation) {			
			}			
			 
			public void onAnimationRepeat(Animation animation) {	
			}			
			 
			public void onAnimationEnd(Animation animation) {
				lottery.setVisibility(View.VISIBLE); 
				task.setVisibility(View.VISIBLE);				
				social.setVisibility(View.VISIBLE);				
				doctor.setVisibility(View.VISIBLE);
							
			}
		});
        unFoldLeftAnimation.setAnimationListener(new AnimationListener() {			
        	 
        	public void onAnimationStart(Animation animation) {				
        	}			
        	 
        	public void onAnimationRepeat(Animation animation) {
        	}			
        	 
        	public void onAnimationEnd(Animation animation) {
        		store.setVisibility(View.VISIBLE);
        		employee.setVisibility(View.VISIBLE);
        		school.setVisibility(View.VISIBLE);	
        	}
        });
        unFoldDownAnimation_1.setAnimationListener(new AnimationListener() {			
        	 
        	public void onAnimationStart(Animation animation) {			
        	}			
        	 
        	public void onAnimationRepeat(Animation animation) {	
        	}			
        	 
        	public void onAnimationEnd(Animation animation) {
        		department.setVisibility(View.VISIBLE);
        		company.setVisibility(View.VISIBLE);				
        		map.setVisibility(View.VISIBLE);
        		
        	}
        });
        foldRightAnimation = AnimationUtils.loadAnimation(v.getContext(), R.anim.slide_out_right);      
        foldLeftAnimation = AnimationUtils.loadAnimation(v.getContext(), R.anim.slide_out_left);      
        foldDownAnimation = AnimationUtils.loadAnimation(v.getContext(), R.anim.slide_out_down);
        foldDownAnimation_1 = AnimationUtils.loadAnimation(v.getContext(), R.anim.slide_out_down);
        
        foldRightAnimation.setAnimationListener(new AnimationListener() {		
		 
		public void onAnimationStart(Animation animation) {		
		}		
		 
		public void onAnimationRepeat(Animation animation) {
		}
		 
		public void onAnimationEnd(Animation animation) { 
			system.setVisibility(View.GONE);
			promoter.setVisibility(View.GONE); 
			linearTask.setVisibility(View.GONE);
				
		}
	});
        foldDownAnimation.setAnimationListener(new AnimationListener() {		
			 
			public void onAnimationStart(Animation animation) {
			}			
			 
			public void onAnimationRepeat(Animation animation) {		
			}			
			 
			public void onAnimationEnd(Animation animation) {
				doctor.setVisibility(View.GONE);	
				social.setVisibility(View.GONE);
				task.setVisibility(View.GONE);						
				lottery.setVisibility(View.GONE);						
			}
		});
        foldLeftAnimation.setAnimationListener(new AnimationListener() {		
        	 
        	public void onAnimationStart(Animation animation) {		
        	}		
        	 
        	public void onAnimationRepeat(Animation animation) {
        	}
        	 
        	public void onAnimationEnd(Animation animation) {
        		school.setVisibility(View.GONE);
        		employee.setVisibility(View.GONE);
        		store.setVisibility(View.GONE);
        		
        	}
        });
        foldDownAnimation_1.setAnimationListener(new AnimationListener() {		
        	 
        	public void onAnimationStart(Animation animation) {
        	}			
        	 
        	public void onAnimationRepeat(Animation animation) {		
        	}			
        	 
        	public void onAnimationEnd(Animation animation) {
        		map.setVisibility(View.GONE);	
        		company.setVisibility(View.GONE);
        		department.setVisibility(View.GONE);						
        	}
        });
        unFoldRightAnimation.setFillAfter(false);
        unFoldDownAnimation.setFillAfter(false);
        foldRightAnimation.setFillAfter(true);
        foldDownAnimation.setFillAfter(true);
        unFoldLeftAnimation.setFillAfter(false);
        unFoldDownAnimation_1.setFillAfter(false);
        foldLeftAnimation.setFillAfter(true);
        foldDownAnimation_1.setFillAfter(true);
        animationHandler = new Handler();
        unFoldAnimationRunnable = new Runnable() {			
			 
			public void run() {		
				lottery.startAnimation(unFoldDownAnimation);	
				linearTask.startAnimation(unFoldRightAnimation);	
				task.startAnimation(unFoldDownAnimation);	
				promoter.startAnimation(unFoldRightAnimation);
				social.startAnimation(unFoldDownAnimation);	
				system.startAnimation(unFoldRightAnimation);			
				doctor.startAnimation(unFoldDownAnimation);
//				sharedCircle.startAnimation(unFoldRightAnimation);				
			}
		};
		foldAnimationRunnable = new Runnable() {
			
			 
			public void run() {					
				lottery.startAnimation(foldDownAnimation);	
				linearTask.startAnimation(foldRightAnimation);	
				task.startAnimation(foldDownAnimation);	
				promoter.startAnimation(foldRightAnimation);
				social.startAnimation(foldDownAnimation);	
				system.startAnimation(foldRightAnimation);			
				doctor.startAnimation(foldDownAnimation); 
			}
		};
		unFoldAnimationRunnable_1 = new Runnable() {			
			 
			public void run() {		
				store.startAnimation(unFoldLeftAnimation);
				department.startAnimation(unFoldDownAnimation_1);
				employee.startAnimation(unFoldLeftAnimation);
				company.startAnimation(unFoldDownAnimation_1);		
				school.startAnimation(unFoldLeftAnimation);
				map.startAnimation(unFoldDownAnimation_1);			
			}
		};
		foldAnimationRunnable_1 = new Runnable() {
			
			 
			public void run() {					
				store.startAnimation(foldLeftAnimation);
				department.startAnimation(foldDownAnimation_1);
				employee.startAnimation(foldLeftAnimation);
				company.startAnimation(foldDownAnimation_1);		
				school.startAnimation(foldLeftAnimation);
				map.startAnimation(foldDownAnimation_1);		
			}
		};
		mapView.money = (TextView) v.findViewById(R.id.map_text_money);
		mapView.employeeNum = (TextView) v.findViewById(R.id.map_text_employee);
		mapView.canledar = (TextView) v.findViewById(R.id.map_text_calendar);
		mapView.shopNum = (TextView) v.findViewById(R.id.map_text_shop);
		mapView.streetView = (TextView) v.findViewById(R.id.map_text_find);

		// sharedCircle promoter frame
//		if (GameData.isLogined) {
//			doPromoter(null);
//			GameData.isLogined = false;
//		}
		// 新手引导指向任务的箭头
		
		unfoldArrows = (SpriteView) v.findViewById(R.id.map_unfold_arrows);
		unfoldArrows.setSeries(0);
		
		
		
		taskArrows = (SpriteView) v.findViewById(R.id.map_task_arrows);
		taskArrows.setSeries(0);
		// 新手引导指向菜单的箭头		
		menuArrows = (SpriteView) v.findViewById(R.id.map_menu_arrows);
		menuArrows.setSeries(0);

		social.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {

				//MainActivity.mContext
				//		.Activitychange(SocialActivity.class, null);

				// TODO Auto-generated method stub
				animationHandler.removeCallbacks(foldAnimationRunnable);
				animationHandler.post(foldAnimationRunnable);	
				isUnFold = !isUnFold;
				isFromSocial = true;
				showmenu();
				/*String[] menu = new String[] { 
						MainActivity.resources.getString(R.string.social_menu1),
						MainActivity.resources.getString(R.string.social_menu2),
						MainActivity.resources.getString(R.string.social_menu3),
						MainActivity.resources.getString(R.string.social_menu4)
						};
				Builder builder = new AlertDialog.Builder(MainActivity.mContext);
				builder.setItems(menu, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface Dialog, int index) {
						if (index == 0) {
							MainActivity.mContext	.Activitychange(SocialActivity.class, null);
									
						} else if (index == 1) {
							if (ChatBoxActivity.mContext == null) {
								MainActivity.mContext.Activitychange(ChatBoxActivity.class, null);
							}
						}else if(index ==2){
							//isMaillFromMainMenu = true;
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
						//	MapmainMenuActivity.this.finish();
							MainActivity.mContext.Activitychange(MailboxActivity.class,
									null);
						}else if(index ==3){
							String commerce_menu =MainActivity.resources.getString(R.string.commerce_menu);
							String[] menu = commerce_menu.split(",");							
							Builder build =new AlertDialog.Builder(MainActivity.mContext);
							build.setItems(menu, new DialogInterface.OnClickListener() {
								
								 
								public void onClick(DialogInterface dialog, int index) {
									// TODO Auto-generated method stub
									if(index==0)
									{
										//mContext.Activitychange(CreateCommerceActivity.class, null);
										//mContext.change = true;
									byte value = 0;
									Connection
											.sendMessage(
													GameProtocol.CONNECTION_SEND_COfC_Create_Info_Req,
													ConstructData
															.getCOfC_Create_Info_ReqData(value));
								} else if (index == 1) {

									 

								//	mContext.change = true;
									if (GameData.member == null||GameData.player.id==-1) {

										display();
									} else {
									MainActivity.	mContext.Activitychange(
												CommerceInfoActivity.class, null);
									}

								} else if (index == 2) {
									//mContext.change = true;
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
									MainActivity.mContext.Activitychange(
											PropagandaActivity.class, null);
								} else if (index == 4) {
									//mContext.change = true;
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
						}
					}
				}).create().show();	
				*/
			
			}

		}

		);
		/*mail.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {

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
				MainActivity.mContext.Activitychange(MailboxActivity.class,
						null);
			}

		}

		);*/
		shop.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				animationHandler.removeCallbacks(foldAnimationRunnable);
				animationHandler.post(foldAnimationRunnable);	
				isUnFold = !isUnFold;

				Connection.sendMessage(GameProtocol.MALL_ASK_LIST,
						ConstructData.getPropsMallListType((byte) 1));
			}

		}

		);
		task.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// 撤销新手引导的任务指向
				if (GameData.isFreshMan) {
					if (FreshManLead.caseId == 3 || FreshManLead.caseId == 16) {
						taskArrows.setVisibility(View.GONE);
						FreshManLead.caseId++;
					}
				}
				animationHandler.removeCallbacks(foldAnimationRunnable);
				animationHandler.post(foldAnimationRunnable);	
				isUnFold = !isUnFold;
				MainActivity.mContext.Activitychange(TaskActivity.class, null);
			}

		}

		);
		system.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				animationHandler.removeCallbacks(foldAnimationRunnable);
				animationHandler.post(foldAnimationRunnable);	
				isUnFold = !isUnFold;
				final AlertDialog dlg = new AlertDialog.Builder(MainActivity.mContext).create();
				dlg.show();					
				dlg.getWindow().setContentView(R.layout.system_dlg);
				Button helping =(Button)dlg.findViewById(R.id.system_helping);
				Button setting =(Button)dlg.findViewById(R.id.system_setting);
				helping.setText(MainActivity.resources.getString(R.string.map_item1));
//				helping.setTypeface(GameDefinition.face);
				setting.setText(MainActivity.resources.getString(R.string.map_item2));
//				setting.setTypeface(GameDefinition.face);
				helping.setOnClickListener(new OnClickListener() {
					
					 
					public void onClick(View v) {
						// TODO Auto-generated method stub
						/*MainActivity.mContext.Activitychange(
								HelpSettingActivity.class, null);*/
						dlg.dismiss();
					}
				});
				setting.setOnClickListener(new OnClickListener() {
					
					 
					public void onClick(View v) {
						// TODO Auto-generated method stub
						MainActivity.mContext.Activitychange(
								SystemSettingActivity.class, null);
						dlg.dismiss();
					}
				});
				/*String[] menu = new String[] { MainActivity.resources.getString(R.string.map_item1), MainActivity.resources.getString(R.string.map_item2)};
				Builder builder = new AlertDialog.Builder(MainActivity.mContext);
				builder.setItems(menu, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface Dialog, int index) {
						if (index == 0) {
							MainActivity.mContext.Activitychange(
									HelpSettingActivity.class, null);
						} else if (index == 1) {
							MainActivity.mContext.Activitychange(
									SystemSettingActivity.class, null);
						}
					}
				}).create().show();
*/
			}

		}

		);

		promoter.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				animationHandler.removeCallbacks(foldAnimationRunnable);
				animationHandler.post(foldAnimationRunnable);	
				isUnFold = !isUnFold;
				MainActivity.mContext.Activitychange(PromotersActivity.class,
						null);
			}
		});
		doctor.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				animationHandler.removeCallbacks(foldAnimationRunnable);
	    		animationHandler.post(foldAnimationRunnable);	
	    		isUnFold = !isUnFold;
				MainActivity.mContext.Activitychange(DoctorInfoActivity.class,
						null);
			}
		});
		lottery.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				animationHandler.removeCallbacks(foldAnimationRunnable);
				animationHandler.post(foldAnimationRunnable);	
				isUnFold = !isUnFold;
				//mContext.change = true;
				if (Lottery_Station_Activity.mContext == null) {
					Connection
							.sendMessage(
									GameProtocol.LOTTERY_TACKET_ASK_LIST,
									ConstructData
											.getLotteryTicketAskList((byte) 2));
			}
				}
		});
		
		department.setOnClickListener(new OnClickListener() {
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				animationHandler.removeCallbacks(foldAnimationRunnable_1);
	    		animationHandler.post(foldAnimationRunnable_1);			
	    		isUnMenu = !isUnMenu;
	    		/*isFromDept = true;
	    		showmenu();*/
	    		/**by zzx
	    		 * */
	    		if (ShopList2Activity.mContext == null) {
					MainActivity.mContext.Activitychange(ShopList2Activity.class, null);
				}
//	    		if(DepartmentActivity.mContext == null)
//	    		{
//	    			MainActivity.mContext.Activitychange(DepartmentActivity.class, null);
//	    		}
			}
		});
		company.setOnClickListener(new OnClickListener() {
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				animationHandler.removeCallbacks(foldAnimationRunnable_1);
	    		animationHandler.post(foldAnimationRunnable_1);			
	    		isUnMenu = !isUnMenu;

	    		if (CompanyTabActivity.mContext == null) {
	    			MainActivity.mContext.Activitychange(CompanyTabActivity.class, null);
				}
				
			}
		});
		map.setOnClickListener(new OnClickListener() {
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				animationHandler.removeCallbacks(foldAnimationRunnable_1);
				animationHandler.post(foldAnimationRunnable_1);			
				isUnMenu = !isUnMenu;
				showmap();
				
			}
		});
		store.setOnClickListener(new OnClickListener() {
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				animationHandler.removeCallbacks(foldAnimationRunnable_1);
				animationHandler.post(foldAnimationRunnable_1);	
				isUnMenu = !isUnMenu;
				if (ShopListActivity.mContext == null) {
					MainActivity.mContext.Activitychange(ShopListActivity.class, null);
				}			
				/*try {
					Thread.sleep(500);
				} catch (InterruptedException e) 
				{
					e.printStackTrace();
				}*/
				
				
			}
		});
		employee.setOnClickListener(new OnClickListener() {
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				animationHandler.removeCallbacks(foldAnimationRunnable_1);
				animationHandler.post(foldAnimationRunnable_1);			
				isUnMenu = !isUnMenu;
			MainActivity.mContext.Activitychange(EmployeeListActivity.class,
						null);	
			}
		});
		school.setOnClickListener(new OnClickListener() {
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				animationHandler.removeCallbacks(foldAnimationRunnable_1);
				animationHandler.post(foldAnimationRunnable_1);			
				isUnMenu = !isUnMenu;
				if (VentureSchoolMain.mContext == null) {
					MainActivity.mContext.Activitychange(VentureSchoolMain.class,
							null);
				}
			}
		});

		button.setOnClickListener(new OnClickListener() {//菜单

			public void onClick(View arg0) {
				// 撤销新手引导的菜单指向
/*	if (GameData.isFreshMan) {
					if (FreshManLead.caseId == 11 ) {
						menuArrows.setVisibility(View.GONE);
						FreshManLead.caseId++;
					}
				}
				
				MainActivity.mContext.Activitychange(MapmainMenuActivity.class,
						null);*/
		 if(isUnFold){
				animationHandler.removeCallbacks(foldAnimationRunnable);
	    		animationHandler.post(foldAnimationRunnable);	
	    		isUnFold = !isUnFold;
	    		}
	    	
			    showMenuOrNot_1();
				
			}
		});

		RelativeLayout relative = (RelativeLayout) v
				.findViewById(R.id.map_rlayout5);

		relative.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Builder builder = new AlertDialog.Builder(MainActivity.mContext);

				final View view = View.inflate(MainActivity.mContext,
						R.layout.findstreet_item, null);

				builder.setView(view);

				builder.setPositiveButton(MainActivity.resources.getString(R.string.map_ok),
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface arg0, int arg1) {
								GameData.isResetMap = true;
								GameData.mapIdIndexBack = GameData.mapIdIndex;

								EditText etx = (EditText) view
										.findViewById(R.id.findstreet_item_editx);
								EditText ety = (EditText) view
										.findViewById(R.id.findstreet_item_edity);

								int x = -1;
								int y = -1;

								String sx = etx.getText().toString();
								String sy = ety.getText().toString();

								if (sx != null && !sx.equals("")) {
									x = Integer.parseInt(sx);
								}
								if (sy != null && !sy.equals("")) {
									y = Integer.parseInt(sy);
								}

								if (x >= 0 || y >= 0) {
									Connection
											.sendMessage(
													GameProtocol.CONNECTION_SEND_MAPAROUND_SHOP,
													ConstructData
															.getMapAroundShop(
																	GameData.mapIds[GameData.mapIdIndex],
																	(short) x,
																	(short) y,
																	(byte) 1,
																	(byte) (GameData.ARRAY_LENTH / 2)));
									Vector v = new Vector(5, 5);
									v.addElement(GameProtocol.CONNECTION_SEND_MAPAROUND_SHOP);
									GameGroupControl.gameGroupControl
											.setGameStatus(
													GameDefinition.Game_Loading,
													v);
								}

							}

						});
				builder.setNegativeButton(MainActivity.resources.getString(R.string.map_cancel),
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface arg0, int arg1) {

							}
						});
				builder.create().show();
			}

		});

		// zhouzhilong add
//		sharedCircle.setOnClickListener(new OnClickListener() {
//
//			 
//			public void onClick(View v) {
//				MainActivity.mContext.Activitychange(ShareCircleActivity.class,
//						null);
//			}
//		});

	}

	private LinearLayout linearPromot;
	private LinearLayout linearDepartment;
	private LinearLayout linearMap;
	public void showmap() {
		new Thread() {
			public void run() {
				endAnimationHandler.postDelayed((new Runnable() {
					 
					public void run() {
						mapmenu ();
						
					}
				}), 100);
			};
		}.start();
	}
	public void showmenu() {
		new Thread() {
			public void run() {
				endAnimationHandler.postDelayed((new Runnable() {
					 
					public void run() {
						departmentmenu ();
						
					}
				}), 100);
			};
		}.start();
	}

	private void mapmenu () {
		
		
		linearMap = (LinearLayout) v
		.findViewById(R.id.linearlayout_map);
		Button map_1 = (Button) v.findViewById(R.id.map1);
		Button map_2 = (Button) v.findViewById(R.id.map2);
		Button map_3 = (Button) v.findViewById(R.id.map3);
		Button map_4 = (Button) v.findViewById(R.id.map4);
		Button map_5 = (Button)v.findViewById(R.id.map5);
		Button map_6 = (Button)v.findViewById(R.id.map6);
		Button close = (Button)v.findViewById(R.id.map_close);
		close.setOnClickListener(new OnClickListener() {
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				linearMap.setVisibility(View.GONE);
			}
		});
		map_1.setOnClickListener(new OnClickListener() {
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GameData.isResetMap = true;
				GameData.mapIdIndexBack = (short) 0;
				Connection
						.sendMessage(
								GameProtocol.CONNECTION_SEND_MAPAROUND_SHOP,
								ConstructData
										.getMapAroundShop(
												GameData.mapIds[0],
												(short) -1,
												(short) -1,
												(byte) 1,
												(byte) (GameData.ARRAY_LENTH / 2)));
				Vector v1 = new Vector(5, 5);
				v1.addElement(GameProtocol.CONNECTION_SEND_MAPAROUND_SHOP);
				linearMap.setVisibility(View.GONE);
			}
		});
		map_2.setOnClickListener(new OnClickListener() {
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GameData.isResetMap = true;
				GameData.mapIdIndexBack = (short) 1;
				Connection
						.sendMessage(
								GameProtocol.CONNECTION_SEND_MAPAROUND_SHOP,
								ConstructData
										.getMapAroundShop(
												GameData.mapIds[1],
												(short) -1,
												(short) -1,
												(byte) 1,
												(byte) (GameData.ARRAY_LENTH / 2)));
				Vector v1 = new Vector(5, 5);
				v1.addElement(GameProtocol.CONNECTION_SEND_MAPAROUND_SHOP);
				linearMap.setVisibility(View.GONE);
			}
		});
		map_3.setOnClickListener(new OnClickListener() {
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GameData.isResetMap = true;
				GameData.mapIdIndexBack = (short) 2;
				Connection
						.sendMessage(
								GameProtocol.CONNECTION_SEND_MAPAROUND_SHOP,
								ConstructData
										.getMapAroundShop(
												GameData.mapIds[2],
												(short) -1,
												(short) -1,
												(byte) 1,
												(byte) (GameData.ARRAY_LENTH / 2)));
				Vector v1 = new Vector(5, 5);
				v1.addElement(GameProtocol.CONNECTION_SEND_MAPAROUND_SHOP);
				linearMap.setVisibility(View.GONE);
			}
		});
		map_4.setOnClickListener(new OnClickListener() {
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GameData.isResetMap = true;
				GameData.mapIdIndexBack = (short) 3;
				Connection
						.sendMessage(
								GameProtocol.CONNECTION_SEND_MAPAROUND_SHOP,
								ConstructData
										.getMapAroundShop(
												GameData.mapIds[3],
												(short) -1,
												(short) -1,
												(byte) 1,
												(byte) (GameData.ARRAY_LENTH / 2)));
				Vector v1 = new Vector(5, 5);
				v1.addElement(GameProtocol.CONNECTION_SEND_MAPAROUND_SHOP);
				linearMap.setVisibility(View.GONE);
			}
		});
		map_5.setOnClickListener(new OnClickListener() {
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GameData.isResetMap = true;
				GameData.mapIdIndexBack = (short) 4;
				Connection
						.sendMessage(
								GameProtocol.CONNECTION_SEND_MAPAROUND_SHOP,
								ConstructData
										.getMapAroundShop(
												GameData.mapIds[4],
												(short) -1,
												(short) -1,
												(byte) 1,
												(byte) (GameData.ARRAY_LENTH / 2)));
				Vector v1 = new Vector(5, 5);
				v1.addElement(GameProtocol.CONNECTION_SEND_MAPAROUND_SHOP);
				linearMap.setVisibility(View.GONE);
			}
		});
		map_6.setOnClickListener(new OnClickListener() {
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GameData.isResetMap = true;
				GameData.mapIdIndexBack = (short) 5;
				Connection
						.sendMessage(
								GameProtocol.CONNECTION_SEND_MAPAROUND_SHOP,
								ConstructData
										.getMapAroundShop(
												GameData.mapIds[5],
												(short) -1,
												(short) -1,
												(byte) 1,
												(byte) (GameData.ARRAY_LENTH / 2)));
				Vector v1 = new Vector(5, 5);
				v1.addElement(GameProtocol.CONNECTION_SEND_MAPAROUND_SHOP);
				linearMap.setVisibility(View.GONE);
			}
		});
		linearMap.setVisibility(View.VISIBLE);
		final View promotParent = (View) linearMap.getParent();
		Animation anim = new TranslateAnimation( 0.0f, 0.0f,-promotParent.getHeight() * 3
				/ 2 + linearMap.getHeight() / 2, 0.0f);
		anim.setDuration(1500);
		anim.setStartOffset(500);
		anim.setFillAfter(false);
		/*		anim.setInterpolator(v.getContext(),
				android.R.anim.overshoot_interpolator);*/
		anim.setAnimationListener(new AnimationListener() {			
			 
			public void onAnimationStart(Animation animation) {}			
			 
			public void onAnimationRepeat(Animation animation) {}		
			 
			public void onAnimationEnd(Animation animation) {
				
				new Thread() {
					public void run() {
						endAnimationHandler.postDelayed((new Runnable() {
							 
							public void run() {
							}
						}), 500);
					};
				}.start();
				
			}
		});
		linearMap.startAnimation(anim);
		
	}
	private void departmentmenu () {


		linearDepartment = (LinearLayout) v
				.findViewById(R.id.linearlayout_department);
		Button personal = (Button) v.findViewById(R.id.department_personal);//好友
		Button relation = (Button) v.findViewById(R.id.department_relation);//聊天
		Button finance = (Button) v.findViewById(R.id.department_finance);//商会
		Button strategy = (Button) v.findViewById(R.id.department_strategy);//邮件
		Button close =(Button)v.findViewById(R.id.department_close);
		close.setOnClickListener(new OnClickListener() {
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				linearDepartment.setVisibility(View.GONE);
			}
		});
	//	Button other = (Button)v.findViewById(R.id.department_other);
	 /*    if(isFromCommerce){
	    	 other.setVisibility(View.VISIBLE);
	    	 isFromCommerce = false;
	     }*/
		if(isFromDept){
		 personal.setOnClickListener(new OnClickListener() {

			 
			public void onClick(View v) {

				linearDepartment.setVisibility(View.GONE);
				isFromDept = false;
			}
		});
		 }else if (isFromSocial){
			 personal.setOnClickListener(new OnClickListener() {

					 
					public void onClick(View v) {
						MainActivity.mContext	.Activitychange(SocialActivity.class, null);
						linearDepartment.setVisibility(View.GONE);
						isFromSocial = false;
					}
				}); 
			 relation.setOnClickListener(new OnClickListener() {
				 
				  
				 public void onClick(View v) {
					/* if (ChatBoxActivity.mContext == null) {
							MainActivity.mContext.Activitychange(ChatBoxActivity.class, null);
						}*/
					 if(ChatWindowActivity.context == null)
					 {
						 MainActivity.mContext.Activitychange(ChatWindowActivity.class, null);
					 }
					 linearDepartment.setVisibility(View.GONE);
					 isFromSocial = false;
				 }
			 }); 
			 finance.setOnClickListener(new OnClickListener() {
				 
				  
				 public void onClick(View v) {
					 if(GameData.player.businessId<=0){
							Connection
							.sendMessage(
									GameProtocol.CONNECTION_SEND_ClientDatas_Req,
									ConstructData
											.ClientDatas_Req((byte) 8));	
					 }else{ 
						 MainActivity.	mContext.Activitychange( CommerceInfoActivity.class, null);
					 }
						/*final AlertDialog dlg = new AlertDialog.Builder(MainActivity.mContext).create();
						dlg.show();					
						dlg.getWindow().setContentView(R.layout.commerce_menu);
					 String commerce_menu =MainActivity.resources.getString(R.string.commerce_menu);
						String[] menu = commerce_menu.split(",");
						Button menu_1 =(Button)dlg.findViewById(R.id.commerce_menu1);
						menu_1.setText(menu[0]);
//						menu_1.setTypeface(GameDefinition.face);
						Button menu_2 =(Button)dlg.findViewById(R.id.commerce_menu2);
						menu_2.setText(menu[1]);
//						menu_2.setTypeface(GameDefinition.face);
						Button menu_3 =(Button)dlg.findViewById(R.id.commerce_menu3);
						menu_3.setText(menu[2]);
//						menu_3.setTypeface(GameDefinition.face);
						Button menu_4 =(Button)dlg.findViewById(R.id.commerce_menu4);
						menu_4.setText(menu[3]);
//						menu_4.setTypeface(GameDefinition.face);
						Button menu_5 =(Button)dlg.findViewById(R.id.commerce_menu5);
						menu_5.setText(menu[4]);
//						menu_5.setTypeface(GameDefinition.face);
						menu_1.setOnClickListener(new OnClickListener() {
							
							 
							public void onClick(View v) {
								// TODO Auto-generated method stub
								byte value = 0;
								Connection
										.sendMessage(
												GameProtocol.CONNECTION_SEND_COfC_Create_Info_Req,
												ConstructData
														.getCOfC_Create_Info_ReqData(value));	
								dlg.dismiss();
							}
						});
						menu_2.setOnClickListener(new OnClickListener() {
							
							 
							public void onClick(View v) {
								// TODO Auto-generated method stub
								if (GameData.member == null||GameData.player.id==-1) {

									display();
								} else {
								MainActivity.	mContext.Activitychange(
											CommerceInfoActivity.class, null);
								}
								dlg.dismiss();
							}
						});
						menu_3.setOnClickListener(new OnClickListener() {
							
							 
							public void onClick(View v) {
								// TODO Auto-generated method stub
								Connection
								.sendMessage(
										GameProtocol.CONNECTION_SEND_COfC_List_Info_Req,
										ConstructData
												.getMemberListRequestData(
														"", (short) 0,
														(short) 0,
														(short) 0,
														(short) 0));
								dlg.dismiss();
								
							}
						});
						menu_4.setOnClickListener(new OnClickListener() {
							
							 
							public void onClick(View v) {
								// TODO Auto-generated method stub
								MainActivity.mContext.Activitychange(
										PropagandaActivity.class, null);
								dlg.dismiss();
							}
						});
						menu_5.setOnClickListener(new OnClickListener() {
							
							 
							public void onClick(View v) {
								// TODO Auto-generated method stub
								Connection
								.sendMessage(
										GameProtocol.CONNECTION_SEND_ClientDatas_Req,
										ConstructData
												.ClientDatas_Req((byte) 8));	
								dlg.dismiss();
							}
						});*/
/*						Builder build =new AlertDialog.Builder(MainActivity.mContext);
						build.setItems(menu, new DialogInterface.OnClickListener() {
							
							 
							public void onClick(DialogInterface dialog, int index) {
								// TODO Auto-generated method stub
								if(index==0)
								{
									//mContext.Activitychange(CreateCommerceActivity.class, null);
									//mContext.change = true;
								byte value = 0;
								Connection
										.sendMessage(
												GameProtocol.CONNECTION_SEND_COfC_Create_Info_Req,
												ConstructData
														.getCOfC_Create_Info_ReqData(value));
							} else if (index == 1) {

								 

							//	mContext.change = true;
								if (GameData.member == null||GameData.player.id==-1) {

									display();
								} else {
								MainActivity.	mContext.Activitychange(
											CommerceInfoActivity.class, null);
								}

							} else if (index == 2) {
								//mContext.change = true;
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
								MainActivity.mContext.Activitychange(
										PropagandaActivity.class, null);
							} else if (index == 4) {
								//mContext.change = true;
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
					}).show();	*/										
					 linearDepartment.setVisibility(View.GONE);
					 isFromSocial = false;
				 }
			 }); 
			 strategy.setOnClickListener(new OnClickListener() {
				 
				  
				 public void onClick(View v) {					
						//isMaillFromMainMenu = true;
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
					//	MapmainMenuActivity.this.finish();
						MainActivity.mContext.Activitychange(MailboxActivity.class,
								null);
					 linearDepartment.setVisibility(View.GONE);
					 isFromSocial = false;
				 }
			 }); 
		 }else if(isFromCommerce){
			 personal.setOnClickListener(new OnClickListener() {

					 
					public void onClick(View v) {

						linearDepartment.setVisibility(View.GONE);
						isFromCommerce = false;
					}
				}); 
			 relation.setOnClickListener(new OnClickListener() {
				 
				  
				 public void onClick(View v) {
					 
					 linearDepartment.setVisibility(View.GONE);
					 isFromCommerce = false;
				 }
			 }); 
		 }

		 linearDepartment.setVisibility(View.VISIBLE);
		final View promotParent = (View) linearDepartment.getParent();
		Animation anim = new TranslateAnimation( 0.0f, 0.0f,-promotParent.getHeight() * 3
				/ 2 + linearDepartment.getHeight() / 2, 0.0f);
		anim.setDuration(1500);
		anim.setStartOffset(500);
		anim.setFillAfter(false);
/*		anim.setInterpolator(v.getContext(),
				android.R.anim.overshoot_interpolator);*/
		anim.setAnimationListener(new AnimationListener() {			
			 
			public void onAnimationStart(Animation animation) {}			
			 
			public void onAnimationRepeat(Animation animation) {}		
			 
			public void onAnimationEnd(Animation animation) {
 
					new Thread() {
						public void run() {
							endAnimationHandler.postDelayed((new Runnable() {
								 
								public void run() {
								}
							}), 500);
						};
					}.start();
				
			}
		});
		linearDepartment.startAnimation(anim);

	}
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
	private TextView promot;
	// zhouzhilong add
	private void setText(String textStr) {

		Log.i("Log", "setText--Map");
		linearPromot = (LinearLayout) v
				.findViewById(R.id.linearlayout_promotframe);
		Button close = (Button) v.findViewById(R.id.close_promotframe);
		 promot = (TextView) v.findViewById(R.id.promotFrame);
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
		anim.setStartOffset(500);
		anim.setFillAfter(false);
		anim.setInterpolator(v.getContext(),
				android.R.anim.overshoot_interpolator);
		anim.setAnimationListener(new AnimationListener() {			
			 
			public void onAnimationStart(Animation animation) {}			
			 
			public void onAnimationRepeat(Animation animation) {}		
			 
			public void onAnimationEnd(Animation animation) {
				Log.i("Log", "FreshManLead--Map--caseId :"+FreshManLead.caseId);
				// 当caseId为0时，弹出下一个提示框
				if (GameData.isFreshMan && FreshManLead.caseId == 0) {
					new Thread() {
						public void run() {
							endAnimationHandler.postDelayed((new Runnable() {
								 
								public void run() {
									promot.setText(FreshManLead.prompt2);
								}
							}), 1500);
						};
					}.start();
					//当caseId==6时弹出下一个提示框
				}else if(FreshManLead.caseId==6){
					new Thread() {
						public void run() {
							endAnimationHandler.postDelayed((new Runnable() {
								 
								public void run() {
									promot.setText(FreshManLead.prompt7);
								}
							}), 1500);
						};
					}.start();
				}				
			}
		});
		linearPromot.startAnimation(anim);

	}
	//新手引导指向箭头
	public void showArrows(){
		
		new Thread() {
			public void run() {
				endAnimationHandler.postDelayed((new Runnable() {
					 
					public void run() {
						if(GameData.isFreshMan && FreshManLead.caseId == 3 || FreshManLead.caseId == 16){
							unfoldArrows.setVisibility(View.VISIBLE);
						}					
						if (GameData.isFreshMan) {
							if (FreshManLead.caseId == 11 ) {
								Log.i("Log", "FreshManLead----caseId :"+FreshManLead.caseId);
								
								menuArrows.setVisibility(View.VISIBLE);
							}
						}
					}
				}), 2000);
			};
		}.start();
		
		
	}
	
    private void showMenuOrNot(){
    	if(isUnFold){//展开状态
    		animationHandler.removeCallbacks(foldAnimationRunnable);
    		animationHandler.post(foldAnimationRunnable);			
		}else{//未展开状态
			animationHandler.removeCallbacks(unFoldAnimationRunnable);
			animationHandler.post(unFoldAnimationRunnable);
		}
    	isUnFold = ! isUnFold;
    }
    private void showMenuOrNot_1(){
    	if(isUnMenu){//展开状态
    		animationHandler.removeCallbacks(foldAnimationRunnable_1);
    		animationHandler.post(foldAnimationRunnable_1);			
    	}else{//未展开状态
    		animationHandler.removeCallbacks(unFoldAnimationRunnable_1);
    		animationHandler.post(unFoldAnimationRunnable_1);
    	}
    	isUnMenu = ! isUnMenu;
    }

	public void synchviewdata() {
		// TODO Auto-generated method stub

	}

	public void update() {

	}

	 
	public void loadProperties(Vector v) {

	}

	 
	protected void reCycle() {
		isUnFold = false;
		isUnMenu = false;
	}

	 
	protected void refurbish() {
		// TODO Auto-generated method stub

	}

	// zhouzhilong add
	 
	public boolean onTouch(View v, MotionEvent event) {
		return false;
	}
	public void display() {
		Toast.makeText(MainActivity.mContext, MainActivity.resources.getString(R.string.toast_createcommerce), Toast.LENGTH_SHORT).show();
	}

}
