package com.zrong.Android.game;

import java.util.Vector;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zrong.Android.Util.SystemAPI;
import com.zrong.Android.Util.UpdataSoftWave;
import com.zrong.Android.activity.AnswersQuesActivity;
import com.zrong.Android.activity.ChatWindowActivity;  
import com.zrong.Android.activity.CommerceInfoActivity;
import com.zrong.Android.activity.CommerceStaffActivity;
import com.zrong.Android.activity.CompanyTabActivity;
import com.zrong.Android.activity.DepartmentActivity; 
import com.zrong.Android.activity.DiglistActivity;
import com.zrong.Android.activity.DoctorCustodyActivity;
import com.zrong.Android.activity.DoctorInfoActivity;
import com.zrong.Android.activity.DoctorShopActivity;
import com.zrong.Android.activity.DoctorTaskActivity;
import com.zrong.Android.activity.MailDetailActivity;
import com.zrong.Android.activity.MailboxActivity;
import com.zrong.Android.activity.MainActivity;
import com.zrong.Android.activity.PlanningDeptActivity;
import com.zrong.Android.activity.PromotersActivity;
import com.zrong.Android.activity.R;
import com.zrong.Android.activity.SeacherFriendActivity;
import com.zrong.Android.activity.ShopInfoActivity;
import com.zrong.Android.activity.ShopListActivity;
import com.zrong.Android.activity.SocialActivity;
import com.zrong.Android.activity.StoreActivity;
import com.zrong.Android.activity.TaskActivity;
import com.zrong.Android.activity.TaskAnswerResultActivity;
import com.zrong.Android.activity.TaskListActivity;
import com.zrong.Android.activity.VentureEvalutionActivity;
import com.zrong.Android.activity.shopInfo2Activity;
import com.zrong.Android.element.Chat;
import com.zrong.Android.element.Employee;
import com.zrong.Android.logic.Loading;
import com.zrong.Android.logic.MainMenu;
import com.zrong.Android.online.network.GameProtocol;

public class MessageHandler extends Handler {

	public static final String TAG = "MessageHandler";

	private MainActivity context;

	private GameGroupControl control;

	private static LayoutInflater factory = null;

	public MessageHandler(Looper looper, Context context,
			GameGroupControl control) {
		super(looper);
		this.context = (MainActivity) context;
		this.control = control;
	}

	public void handleMessage(Message msg) {

		Log.v(TAG, "message");

		if (msg.what == GameDefinition.Message_SetGameStatus) {
//			control.setGameStatus(msg.arg1, (Vector) msg.obj);
			switch(msg.arg1)
			{
			
			case GameDefinition.Game_SelectSever:
				MainMenu.mainmenu.setElem((Vector) msg.obj);
				MainMenu.mainmenu.initMiddleDialog(3);
				break;
			case GameDefinition.Game_CreateCharactor:
//				control.setGameStatus(msg.arg1, (Vector) msg.obj);
				MainMenu.mainmenu.initMiddleDialog(6);
				break;
			case GameDefinition.Game_Map:
				control.setGameStatus(msg.arg1, (Vector) msg.obj);
				break;
			}
			
		} else if (msg.what == GameDefinition.Message_removeView) {
			Vector v = (Vector) msg.obj;

			control.removeView((View) v.elementAt(0));
		} else if (msg.what == GameDefinition.Message_changeActivity) {

			Vector v = (Vector) msg.obj;
			Class c = (Class) v.elementAt(0);

			Intent intent = new Intent();

			Bundle b = msg.getData();

			if (b != null) 
			{
				intent.putExtras(b);
			}

			if (msg.arg1 == 0) {
				MainActivity.mContext.Activitychange(c, intent);
			} else if (msg.arg1 == 1) {
				ShopInfoActivity.mContext.Activitychange(c, intent);
			} else if (msg.arg1 == 2)// 任务详情
			{
				if (TaskActivity.mContext != null) {
					TaskActivity.mContext.Activitychange(c, intent);

				} else if (DoctorTaskActivity.mContext != null) {
					DoctorTaskActivity.mContext.Activitychange(c, intent);
				}
				if (msg.arg2 == 6 && VentureEvalutionActivity.mContext != null) {
					VentureEvalutionActivity.mContext.changeActivity(c, intent);
				}
				Log.i("Log", "Message--msg.arg2:--" + msg.arg2);

			} else if (msg.arg1 == 3) {
				SocialActivity.mContext.Activitychange(c, intent);
			} else if (msg.arg1 == 4) {
				PromotersActivity.mContext.Activitychange(c, intent);
			} else if (msg.arg1 == 5) {
				MainActivity.mContext.Activitychange(c, intent);
			} else if (msg.arg1 == 6) {
				MainActivity.mContext.Activitychange(c, intent);
			} else if (msg.arg1 == 7) {
				MainActivity.mContext.Activitychange(c, intent);
			} else if (msg.arg1 == 8) {
				MainActivity.mContext.Activitychange(c, intent);

			} else if (msg.arg1 == 9) {
				Vector v2 = (Vector) msg.obj;
				Class c2 = (Class) v2.elementAt(0);
				long id = (Long) v2.elementAt(1);
				Intent intent2 = new Intent();
				Bundle b2 = msg.getData();
				b2.putLong("id", id);

				if (b2 != null) {
					if (msg.arg2 == 3) {
						b2.putString("doctor", "isDoctor");
					}
					intent2.putExtras(b2);
				}

				if (msg.arg2 == 0) {//公司升级信息
					CompanyTabActivity.mContext.Activitychange(c2, intent2);
				} else if (msg.arg2 == 1) {//部门升级信息
					DepartmentActivity.mContext.Activitychange(c2, intent2);
				} else if (msg.arg2 == 2) {// 店铺升级信息
					shopInfo2Activity.mContext.showDialog_shengji(intent2);
				} else if (msg.arg2 == 3) {// 小博士升级
					// TODO
					DoctorInfoActivity.mContext.Activitychange(c2, intent2);
				}

			} else if (msg.arg1 == 10)

			{
				MainActivity.mContext.Activitychange(c, intent);

			} else if (msg.arg1 == 11) {
				MainActivity.mContext.Activitychange(c, intent);
			} else if (msg.arg1 == 12) {
				MainActivity.mContext.Activitychange(c, intent);

			}else if(msg.arg1 ==17){
				MainActivity.mContext.Activitychange(c, intent);
			}else if(msg.arg1 == 18){
				MainActivity.mContext.Activitychange(c, intent);
			} else if (msg.arg1 == 19) {
				if (TaskAnswerResultActivity.mContext == null) {
					MainActivity.mContext.Activitychange(c, intent);
				} else {
					Bundle bundle = (Bundle) v.elementAt(1);
					TaskAnswerResultActivity.mContext.updateView(bundle);
				}

			} else if (msg.arg1 == 20) {// 答题
				Log.i("Log", "-----HandleMessage-答题");
				if (AnswersQuesActivity.mContext == null) 
				{
					Log.i("Log", "-----HandleMessage-答题1");
					MainActivity.mContext.Activitychange(c, intent);
				} else {
					Log.i("Log", "-----HandleMessage-答题2");
					Bundle bundle = (Bundle) v.elementAt(1);
					AnswersQuesActivity.mContext.updateView(bundle);
				}
			}
			else if(msg.arg1 == 21)//店铺列表招揽
			{
				ShopListActivity.mContext.Activitychange(c, intent);
			}
			else if(msg.arg1 == 22)//查看可以帮助驱散店铺列表
			{
				Intent intent2 = new Intent();
				Bundle b2 = msg.getData();
				intent2.putExtra("type", b2.getInt("type"));
				SocialActivity.mContext.Activitychange(c, intent2);
			}

		} else if (msg.what == GameDefinition.Message_showToast) {
			Vector v = (Vector) msg.obj;
			String s = (String) v.elementAt(0);
			Activity a = MainActivity.getTopActivty();
			if (a == null) 
			{
				a = MainActivity.mContext;
			}

			Toast toast = Toast.makeText(MainActivity.mContext,
					SystemAPI.getText(s), Toast.LENGTH_LONG);

			toast.show();

		} else if (msg.what == GameDefinition.Message_changeData) 
		{
			if (msg.arg1 == 0)// 公司数据变化
			{
				GameData.corporation.listener.OnDataChange(null);
				
				
			} else if (msg.arg1 == 1) 
			{
				Employee e = (Employee) msg.obj;
				Bundle bundle = new Bundle();
				bundle.putLong("id", e.id);
				e.listener.OnDataChange(bundle);

			} else if (msg.arg1 == 2) {
				if (StoreActivity.mContext != null) {
					StoreActivity.mContext.initMyGoods();
				}

			} else if (msg.arg1 == 3) {
				if (TaskActivity.mContext != null) {
					TaskActivity.mContext.setSelect();
					if (TaskListActivity.mContext != null) {
						TaskListActivity.mContext.update();
					}

				} else if (DoctorTaskActivity.mContext != null) {
					DoctorTaskActivity.mContext.setview();
				} else if (TaskListActivity.mContext != null) {
					TaskListActivity.mContext.update();
				}

			} else if (msg.arg1 == 4) {
				if (SocialActivity.mContext != null) {
					SocialActivity.mContext.setSelectIndex();
				}
			} else if (msg.arg1 == 5) {
				if (MailboxActivity.mContext != null) {
					MailboxActivity.mContext.setSelect(msg.arg2);
				}

			} else if (msg.arg1 == 6) {
				/*if (ChatBoxActivity.mContext != null) {
					ChatBoxActivity.mContext.addChat(msg.arg2, (Chat) msg.obj);
				}*/
				if(ChatWindowActivity.context != null) 
				{
					ChatWindowActivity.context.addChat(msg.arg2, (Chat)msg.obj);
				}
			} else if (msg.arg1 == 7) {
				if (MailDetailActivity.mContext != null) {
					MailDetailActivity.mContext.updata(msg.arg2);
				}

			} else if (msg.arg1 == 8) {
				if (SeacherFriendActivity.mContext != null) {
					SeacherFriendActivity.mContext.initList();// 启动SeacherFriendActivity
				}
			} else if (msg.arg1 == 9) {
				if (DiglistActivity.mContext != null) {

					DiglistActivity.mContext.initList();
				}
			} else if (msg.arg1 == 10) {
				if (CommerceInfoActivity.mContext != null) {
					// Bundle aaa = msg.getData();
					// String aa=msg.getData().getString("msg");

					CommerceInfoActivity.mContext.update(msg.getData());

				}
			} else if (msg.arg1 == 11)//
			{
				if (CommerceStaffActivity.mContext != null) {
					CommerceStaffActivity.mContext.updatelist();
				}

			} else if (msg.arg1 == 12) {// 小博士更新
				if (msg.arg2 == 0) {
					DoctorInfoActivity.mContext.updateDate();
				} else if (msg.arg2 == 1) {
					DoctorCustodyActivity.mContext.updateAdapter();
				}else if(msg.arg2==3){
					DoctorShopActivity.mContext.init();
				}

			} else if (msg.arg1 == 13) {
				PlanningDeptActivity.mContext.updateAdapter();
			} else if (msg.arg1 == 14) {
				if (TaskActivity.mContext != null) {
					TaskActivity.mContext.setSelect();

				} else if (DoctorTaskActivity.mContext != null) {
					DoctorTaskActivity.mContext.setview();
				}

			}else if(msg.arg1 == 15){
				ShopInfoActivity.mContext.update();
			}else if(msg.arg1 == 20){
				CommerceStaffActivity.mContext.update();
			}else if(msg.arg1 == 21)
			{
				DepartmentActivity.mContext.updata();
			}
			else if(msg.arg1 == 22)//lm新加 可以用以后删除原来的店铺更新shopinfoActivity
			{
				shopInfo2Activity.mContext.update();
			}

		} else if (msg.what == GameDefinition.Message_task_detail) {
			if (TaskActivity.mContext != null) {
				TaskActivity.mContext.setContentView(R.layout.task_details);
			}
		} else if (msg.what == GameDefinition.Message_show_dailog) {
			if (msg.arg1 == 0)// 更新版本
			{
				if (MainActivity.mContext != null) {
					Bundle bundle = (Bundle) msg.obj;
					new UpdataSoftWave(bundle, MainActivity.mContext);
				}
			} else if (msg.arg1 == 1) {
				if (MainActivity.mContext != null) {
					Bundle bundle = (Bundle) msg.obj;

					String message = (String) bundle.get("message");

					AlertDialog.Builder builder = new AlertDialog.Builder(
							MainActivity.mContext);

					builder.setMessage(message);

					builder.setPositiveButton(MainActivity.resources
							.getString(R.string.messagehandler_handle_later),
							new DialogInterface.OnClickListener() {

								public void onClick(DialogInterface arg0,
										int arg1) {
									if (control.logic.get("Loading") != null) {
										((Loading) control.logic.get("Loading"))
												.destroy();
									}
								}
							});
					builder.setNegativeButton(MainActivity.resources
							.getString(R.string.messagehandler_reconnection),
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int index) 
								{
									Connection
											.createConnection(GameDef.gateway,
													control.connection);
									SystemAPI.sleep(1000);
									Connection.sendMessage((short) 0x1002,
											ConstructData.getReConnection(),
											false);
								}
							});
					builder.create().show();
				}
			}
			// jiangxujin add
			else if (msg.arg1 == 2) {
				if (MainActivity.mContext != null) {
					Bundle bundle = (Bundle) msg.obj;
					final int id = (Integer) bundle.get("TwoSureMsg_Input_id");
					final byte size = (Byte) bundle
							.get("TwoSureMsg_Input_controlSize");
					final String[] key = (String[]) bundle
							.get("TwoSureMsg_Input_key");

					factory = LayoutInflater.from(StoreActivity.mContext);

					View view = factory.inflate(R.layout.modify_shop_dialog,
							null);

					final EditText et = (EditText) view
							.findViewById(R.id.modify_dialog_edit);

					AlertDialog.Builder builder = new AlertDialog.Builder(
							StoreActivity.mContext);
					builder.setView(view);
					builder.setPositiveButton(MainActivity.resources
							.getString(R.string.messagehandler_ok),
							new DialogInterface.OnClickListener() {

								public void onClick(DialogInterface arg0,
										int arg1) {
									String input_info[] = new String[] { et
											.getText().toString() };
									Connection
											.sendMessage(
													GameProtocol.CONNECTION_RESP_TWOSURE_INPUT,
													ConstructData
															.getTwoSureInputMsg(
																	id, size,
																	key,
																	input_info));
								}
							});

					builder.setNegativeButton(
							MainActivity.resources
									.getString(R.string.messagehandler_cancel),
							new DialogInterface.OnClickListener() {

								public void onClick(DialogInterface arg0,
										int arg1) {

								}

							}).create().show();

				}

			} else if (msg.arg1 == 3) {
				Dialog builder = new Dialog(MainActivity.mContext,
						R.style.FullHeightDialog);

				View view = View.inflate(MainActivity.mContext,
						R.layout.dialogtemplate, null);

				builder.setContentView(view);

				builder.show();
			}
			else if(msg.arg1 == 4)
			{
				Bundle bundle = (Bundle) msg.obj;
				
				final long id = Long.parseLong(String.valueOf(bundle.get("id")));
				final long cofcId = Long.parseLong(String.valueOf(bundle.get("cofcId")));
				String reqMsg = String.valueOf(bundle.get("reqMsg"));
				
				
				Builder builde = new AlertDialog.Builder(context);

				builde.setMessage(
//						MainActivity.resources
//								.getString(R.string.analysisdata_request_info_message)
						reqMsg
								)
						.setPositiveButton(
								MainActivity.resources
										.getString(R.string.analysisdata_request_info_ok),
								new DialogInterface.OnClickListener() {

									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub
										byte agree = 0;// 同意
										Connection
												.sendMessage(
														GameProtocol.CONNECTION_SEND_CofC_Request_Resp,
														ConstructData
																.getCofC_Request_Resp(
																		id, cofcId,
																		agree));
										dialog.dismiss();
									}
								})

						.setNegativeButton(
								MainActivity.resources
										.getString(R.string.analysisdata_request_info_no),
								new DialogInterface.OnClickListener() {

									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub
										byte agree = 1;// 不同意
										Connection
												.sendMessage(
														GameProtocol.CONNECTION_SEND_CofC_Request_Resp,
														ConstructData
																.getCofC_Request_Resp(
																		id, cofcId,
																		agree));
										dialog.dismiss();
									}
								});
				builde.create().show();
			}else if(msg.arg1 == 5)
			{
				Intent intent = new Intent();

				Bundle b = msg.getData();

				if (b != null) 
				{
					intent.putExtras(b);
				}
				
				shopInfo2Activity.mContext.showDialog_zhaolan(intent);
			}
		} else if (msg.what == GameDefinition.Message_update_progress) {
			ProgressDialog progress = (ProgressDialog) msg.obj;

			progress.setProgress(msg.arg2);
		}

	}

	public TextView getTextView(String s) {
		TextView tv = new TextView(context);

		SpannableStringBuilder ss = null;

		s = MainActivity.resources.getString(R.string.messagehandler_message);

		char[] chars = s.toCharArray();

		int oldColor = 0;

		int color = 0;

		int start = 0;

		int end = 0;

		for (int i = 0; i < chars.length; i++) {
			if (i == 0)// 第一个要特殊处理
			{
				if (chars[i] == '/') {
					// 这里就不处理了，交给下面统一处理

				} else {
					oldColor = 0;
					start = 0;
				}
			}

			if (i == chars.length - 1)// 最后一个了
			{
				// ss= new SpannableStringBuilder(String.copyValueOf(chars,
				// start, chars.length-start));
				// ss.setSpan(new ForegroundColorSpan(color), 0,
				// ss.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
				// tv.append(ss);
				tv.append(String
						.copyValueOf(chars, start, chars.length - start));
				tv.setTextColor(0);
			}

			if (chars[i] == '/') {
				end = i;
				if (end > 0) {
					// ss= new SpannableStringBuilder(String.copyValueOf(chars,
					// start, end-start));
					// ss.setSpan(new ForegroundColorSpan(color), 0,
					// ss.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
					// tv.append(ss);
					tv.append(String.copyValueOf(chars, start, end - start));
					tv.setTextColor(0);
				}

				if (i + 2 < chars.length) {
					String tmp = new String(chars, i + 1, 2);
					start = i + 3;
					for (int j = 0; j < Script.colorTable.length; j++) {
						if (tmp.equals(Script.colorTable[j])) {
							if (tmp.equals(MainActivity.resources
									.getString(R.string.messagehandler_recover))) {
								color = oldColor;
							} else {
								color = Script.colorValue[j];
							}

						}

					}
				}
			}
		}
		return tv;
	}

}
