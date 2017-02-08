package com.zrong.Android.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.zrong.Android.Util.Music;
import com.zrong.Android.Util.Preferences;
import com.zrong.Android.Util.SystemAPI;
import com.zrong.Android.Util.xmlPhaser.XElem;
import com.zrong.Android.View.Event;
import com.zrong.Android.View.SpriteView;
import com.zrong.Android.activity.AboutUSActivity;
import com.zrong.Android.activity.HelpSettingActivity;
import com.zrong.Android.activity.MainActivity;
import com.zrong.Android.activity.MoreGame;
import com.zrong.Android.activity.R;
import com.zrong.Android.activity.RegistActivity;
import com.zrong.Android.activity.SystemSettingActivity;
import com.zrong.Android.game.Connection;
import com.zrong.Android.game.ConstructData;
import com.zrong.Android.game.EventManager;
import com.zrong.Android.game.GameData;
import com.zrong.Android.game.GameDef;
import com.zrong.Android.game.GameDefinition;
import com.zrong.Android.game.GameGroupControl;
import com.zrong.Android.online.network.GameProtocol;
import com.zrong.Android.online.network.HttpConnection;

public class MainMenu extends LogicObject implements OnTouchListener
{
	public static MainMenu mainmenu;
	public View currentView; 
	public static final String TAG = "MainMenu";
	/**
	 * 弹出框 状态
	 * */
	public static final int DIALOG_ABOUT = 2;
	public static final int DIALOG_HELP = 0;
	public static final int DIALOG_SETING = 1;
	public static final int DIALOG_SEVER = 3;
	public static final int DIALOG_LOGIN = 4;
	public static final int DIALOG_REGIST = 5;
	public static final int DIALOG_CREATEROLE = 6;
	/** 按键监听 */
	buttonListener listener;
	/** button 按键和图片资源 */
	private Button button[];
	private int buttonRes[] =
	{ R.drawable.startgame_new, R.drawable.fastgame_new,
			R.drawable.moregame_new, R.drawable.helpsetting_new,
			R.drawable.about_new, R.drawable.exitgame_new };

	private int buttonPressRes[] =
	{ R.drawable.startgame_new_pressed, R.drawable.fastgame_new_pressed,
			R.drawable.moregame_new_pressed,
			R.drawable.helpsetting_new_pressed, R.drawable.about_new_pressed,
			R.drawable.exitgame_new_pressed };

	/** 弹出框 */
	private RelativeLayout relatve;
	private int middleDialog_type;
	/** 弹出框 修改参数 */
	private ImageView image_title;
	private RelativeLayout lay_help, lay_selectsever,lay_createRole;
	private LinearLayout lay_login, lay_about, lay_seting, lay_regist;
	private Button middle_close, middle_sure, middle_other;
	private ListView listview;
	TextView oldline;
	/**
	 * 创建角色信息
	 * */
	private int role_sex;
	private int role_sex_index;
	private ImageButton role_R_1,role_R_2,role_R_3,role_R_5;
	private SpriteView role_R_4;
	private EditText role_L_1_edit,role_L_2_edit,role_L_3_edit;
	

	/**
	 * 分区分线信息
	 * 
	 * 保存的分区分线的数据 保存上次登录时的对应的index 即 XElem line[] = elem.elems();
	 * line[index]就可以取到上次登录的那条线
	 * */
	public XElem elem;
	public int lastLoginIndex = 0;

	/**
	 * 游戏设置界面 按钮
	 * */
	public static boolean seting[] =
	{ true, true, true };
	public Button button_seting[], button_setingResult[];

	/**
	 * 动画
	 * */
	public boolean showDialog;
	public int nextDialogId;
	private Animation animation_down, animation_up;
	private Handler handler;
	private Runnable runnable_down, runnable_up;

	public MainMenu(Context context, GameGroupControl control)
	{

		super(context, control); 
		mainmenu = this;
		listener = new buttonListener();
		button = new Button[6];
		Music.getInstance(MainActivity.mContext).start(R.raw.m1, true);
	}

	public void init()
	{
		initView();
	}

	public void initView()
	{

		View v = View.inflate(context, R.layout.mainmenu, null);// 获得View

		registerView(v);// 注册view

		v.setId(GameDefinition.MainMenuView);// 给view设置id
		currentView = v;
		/** 初始化所有按钮 */
		initButton(v);

		GameData.isEnterMainMenu = true;
	}

	/** 初始化按钮 */
	public void initButton(View v)
	{
		button[0] = (Button) v.findViewById(R.id.startgame);
		button[1] = (Button) v.findViewById(R.id.fastgame);
		button[2] = (Button) v.findViewById(R.id.moregame);
		button[3] = (Button) v.findViewById(R.id.helpsetting);
		button[4] = (Button) v.findViewById(R.id.about);
		button[5] = (Button) v.findViewById(R.id.exitgame);
		for (int i = 0; i < button.length; i++)
		{
			button[i].setOnClickListener(listener);
			button[i].setOnTouchListener(this);
		}

		relatve = (RelativeLayout) v.findViewById(R.id.middle_menu);

		image_title = (ImageView) v.findViewById(R.id.middle_name);
		lay_about = (LinearLayout) v.findViewById(R.id.lay_about);
		lay_help = (RelativeLayout) v.findViewById(R.id.lay_help);
		lay_seting = (LinearLayout) v.findViewById(R.id.lay_seting);
		lay_selectsever = (RelativeLayout) v.findViewById(R.id.lay_selectsever);
		lay_login = (LinearLayout) v.findViewById(R.id.lay_login);
		lay_regist = (LinearLayout) v.findViewById(R.id.lay_regist);
		lay_createRole = (RelativeLayout)v.findViewById(R.id.lay_createRole);

		middle_close = (Button) v.findViewById(R.id.middle_exit);
		middle_close.setOnClickListener(listener);
		middle_sure = (Button) v.findViewById(R.id.middle_yes);
		middle_sure.setOnClickListener(listener);

		listview = (ListView) v.findViewById(R.id.listview);
		oldline = (TextView) v.findViewById(R.id.oldline);
		/**创建角色信息*/
		role_R_1 = (ImageButton)v.findViewById(R.id.role_r_1);
		role_R_2 = (ImageButton)v.findViewById(R.id.role_r_2);
		role_R_3 = (ImageButton)v.findViewById(R.id.role_r_3);
		role_R_5 = (ImageButton)v.findViewById(R.id.role_r_5);
		role_R_4 = (SpriteView)v.findViewById(R.id.role_r_4);
		role_L_1_edit = (EditText)v.findViewById(R.id.role_l_1_edit);
		role_L_2_edit = (EditText)v.findViewById(R.id.role_l_2_edit);
		role_L_3_edit = (EditText)v.findViewById(R.id.role_l_3_edit);
		
		role_R_1.setImageResource(R.drawable.image_49);
		role_sex = 0;
		role_R_1.setOnClickListener(listener);
		role_R_2.setOnClickListener(listener);
		role_R_3.setOnClickListener(listener);
		role_R_5.setOnClickListener(listener);
		
	}

	public int intch_width(View view)
	{
		int width = view.getWidth();
		return width;
	}

	public int intch_height(View view)
	{
		int height = view.getHeight();
		return height;
	}

	public boolean onTouch(View view, MotionEvent event)
	{

		if (!showDialog)
			for (int i = 0; i < button.length; i++)
			{
				if (view == button[i])
				{
					button[i].setBackgroundResource(buttonPressRes[i]);
				} else
				{
					button[i].setBackgroundResource(buttonRes[i]);
				}
			}
		return false;
	}

	public static LogicObject createLogicObject(LogicObjectQueue logic,
			Context context, GameGroupControl control)
	{

		return null;
	}

	public void synchviewdata()
	{
		// TODO Auto-generated method stub

	}

	public class buttonListener implements OnClickListener
	{
		public void onClick(View v)
		{
			int id = v.getId();
			switch (id)
			{
			case R.id.startgame:
				startGame();
				break;
			case R.id.fastgame:
				fastGame();
				break;
			case R.id.moregame:
				moreGame();
				break;
			case R.id.helpsetting:
				helpsetting();
				break;
			case R.id.about:
				aboutGame();
				break;
			case R.id.exitgame:
				exitGame();
				break;
			case R.id.middle_exit:
				closeMiddleDialog(-1);

				break;
			case R.id.middle_yes:
				switch (middleDialog_type)
				{
				case DIALOG_LOGIN:
					gotoLogin();
					break;
				case DIALOG_REGIST:
					gotoRegist();
					break;
				case DIALOG_CREATEROLE:
					gotoCreateRole();
					break;
				}
				break;
			case R.id.login_3:
				closeMiddleDialog(DIALOG_REGIST);
				break;
			case R.id.role_r_1://性别男
				role_sex = 0;
				role_R_1.setImageResource(R.drawable.image_49);
				role_R_2.setImageResource(R.drawable.image_46);
				role_R_4.setSeries(0);
				break;
			case R.id.role_r_2://性别女
				role_sex = 1;
				role_R_1.setImageResource(R.drawable.image_48);
				role_R_2.setImageResource(R.drawable.image_47);
				role_R_4.setSeries(1);
				break;
			case R.id.role_r_3://右侧选择
				role_R_4.setSeries(0);
				break;
			case R.id.role_r_5://左侧选择
				role_R_4.setSeries(1);
				break;
				
			case R.id.seting_1:
			case R.id.seting_1_1:
				if (seting[0])
				{
					seting[0] = false;
					Music.getInstance(MainActivity.mContext).stop();
				} else
				{
					seting[0] = true;
					Music.getInstance(MainActivity.mContext).resume();
				}
				button_setingResult[0]
						.setBackgroundResource(seting[0] ? R.drawable.image_24
								: R.drawable.image_25);
				break;
			case R.id.seting_2:
			case R.id.seting_2_1:
				if (seting[1])
				{
					seting[1] = false;
				} else
				{
					seting[1] = true;
				}
				button_setingResult[1]
						.setBackgroundResource(seting[1] ? R.drawable.image_24
								: R.drawable.image_25);
				break;
			case R.id.seting_3:
			case R.id.seting_3_1:
				if (seting[2])
				{
					seting[2] = false;
				} else
				{
					seting[2] = true;
				}
				button_setingResult[2]
						.setBackgroundResource(seting[2] ? R.drawable.image_24
								: R.drawable.image_25);
				break;
			}
		}
	}

	/** 开始游戏 */
	public void startGame()
	{
		// 清除游戏数据
		GameData.clearData();
		if (GameDef.channelId.equals(GameDef.CMCC))
		{
			HttpConnection severlist = new HttpConnection(
					GameProtocol.HTTP_CMCC, control.connection);
			severlist.startURL(GameDef.GATE_SERVER_PAGE);// 启动目标为指定url的HTTP请求
		}

		control.eventManger.removeAllEvent();
		Event e = new Event(EventManager.ACTION_SEVER_LIST, null);

		if (control.eventManger != null)
		{
			control.eventManger.addEvent(e);

			Event e1 = new Event(EventManager.ACTION_GO_SEVERLIST, null);
			control.eventManger.addEvent(e1);
			control.eventManger.nextAction();
		}
	}

	/** 快速游戏 */
	public void fastGame()
	{
		Log.d(TAG, "fastGame onClick");
		// 清除聊天记录
		GameData.clearChatNotes();
		// 清除新手引导
		GameData.clearFreshManLead();
		if (GameDef.channelId.equals(GameDef.CMCC))
		{
			HttpConnection severlist = new HttpConnection(
					GameProtocol.HTTP_CMCC, control.connection);
			severlist.startURL(GameDef.GATE_SERVER_PAGE);// 启动目标为指定url的HTTP请求
		}

		control.eventManger.removeAllEvent();
		Event e1 = new Event(EventManager.ACTION_SEVER_LIST, null);
		control.eventManger.addEvent(e1);

		String uname = control.preferences.getString(Preferences.USERNAME);

		String password = control.preferences.getString(Preferences.PASSWORD);

		if (uname.equals("") || password.equals(""))// 快速注册
		{
			// if (GameDef.channelId.equals(GameDef.dangle)) {
			// Event e = new Event(EventManager.ACTION_GO_SEVERLIST,
			// null);
			// control.eventManger.addEvent(e);
			// } else {
			Event e = new Event(EventManager.ACTION_FASTREGIST, null);
			control.eventManger.addEvent(e);
			// }

		} else
		// 登录平台
		{
			Hashtable table = new Hashtable();

			table.put("name", uname);
			table.put("password", password);
			Event e3 = new Event(EventManager.ACTION_LOGIN, table);
			control.eventManger.addEvent(e3);
		}

		Event e2 = new Event(EventManager.ACTION_LOGIN_SEVER, null);
		control.eventManger.addEvent(e2);

		control.eventManger.nextAction();
	}

	/** 更多游戏 */
	public void moreGame()
	{
		if (GameDef.channelId.equals(GameDef.dangle))
		{
			Toast.makeText(MainActivity.mContext,
					MainActivity.resources.getString(R.string.mainmenu_toast),
					Toast.LENGTH_LONG).show();
		} else
		{
			Intent intent = new Intent();

			Bundle bundle = new Bundle();

			bundle.putString("url", "http://game.10086.cn/a");

			intent.putExtras(bundle);

			MainActivity.mContext.Activitychange(MoreGame.class, intent);
		}

	}

	/** 帮助设置 */
	public void helpsetting()
	{
		final AlertDialog dlg = new AlertDialog.Builder(MainActivity.mContext)
				.create();
		dlg.show();
		dlg.getWindow().setContentView(R.layout.pm_emoloyeelist);

		ListView lv = (ListView) dlg
				.findViewById(R.id.pm_emoloyeelist_listview);

		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		String[] name =
		{ "游戏帮助", "游戏设置" };

		for (int i = 0; i < name.length; i++)
		{
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("name", name[i]);
			list.add(map);
		}

		SimpleAdapter adpter = new SimpleAdapter(MainActivity.mContext, list,
				R.layout.pm_employeelist_item, new String[]
				{ "name" }, new int[]
				{ R.id.pm_employeelist_item_name });
		lv.setAdapter(adpter);

		// listview响应
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
		{

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id)
			{
				// TODO Auto-generated method stub
				if (position == 0)
				{
					dlg.dismiss();
					// MainActivity.mContext.Activitychange(
					// HelpSettingActivity.class, null);

					initMiddleDialog(DIALOG_HELP);
				} else if (position == 1)
				{
					dlg.dismiss();
					// MainActivity.mContext.Activitychange(
					// SystemSettingActivity.class, null);

					initMiddleDialog(DIALOG_SETING);
				}

			}

		});
	}

	/** 关于 */
	public void aboutGame()
	{
		// MainActivity.mContext.Activitychange(AboutUSActivity.class,null);

		initMiddleDialog(DIALOG_ABOUT);
	}

	/** 退出游戏 */
	public void exitGame()
	{
		GameGroupControl.gameGroupControl.showDialog1();
	}

	/** 初始化弹出框信息 */

	public void initMiddleDialog(int id)
	{
		middleDialog_type = id;
		lay_about.setVisibility(View.GONE);
		lay_seting.setVisibility(View.GONE);
		lay_help.setVisibility(View.GONE);
		lay_selectsever.setVisibility(View.GONE);
		lay_login.setVisibility(View.GONE);
		lay_regist.setVisibility(View.GONE);
		lay_createRole.setVisibility(View.GONE);
		middle_sure.setVisibility(View.GONE);
		middle_sure.setBackgroundResource(R.drawable.confirme_0_1);
		// image_title.setBackgroundResource(R.drawable.image_1);

		switch (id)
		{
		case DIALOG_HELP:// 帮助
			image_title.setImageResource(R.drawable.image_28);
			lay_help.setVisibility(View.VISIBLE);
			break;
		case DIALOG_SETING:// 设置
			image_title.setImageResource(R.drawable.image_27);
			lay_seting.setVisibility(View.VISIBLE);
			initSeting();
			break;
		case DIALOG_ABOUT:// 关于
			image_title.setImageResource(R.drawable.image_26);
			lay_about.setVisibility(View.VISIBLE);
			break;
		case DIALOG_SEVER:// 分区分线地址
			image_title.setImageResource(R.drawable.image_36);
			lay_selectsever.setVisibility(View.VISIBLE);
			initSeverList();
			break;
		case DIALOG_LOGIN:// 登录界面
			image_title.setImageResource(R.drawable.image_29);
			lay_login.setVisibility(View.VISIBLE);
			middle_sure.setVisibility(View.VISIBLE);
			middle_sure.setBackgroundResource(R.drawable.image_34_pressed);

			EditText passname = (EditText) currentView
					.findViewById(R.id.login_1_edit);
			EditText password = (EditText) currentView
					.findViewById(R.id.login_2_edit);
			ImageButton buttonRegist = (ImageButton) currentView
					.findViewById(R.id.login_3);
			buttonRegist.setOnClickListener(listener);
			passname.setText(control.preferences
					.getString(Preferences.USERNAME));
			password.setText(control.preferences
					.getString(Preferences.PASSWORD));
			break;
		case DIALOG_REGIST:// 注册界面
			image_title.setImageResource(R.drawable.image_30);
			lay_regist.setVisibility(View.VISIBLE);
			middle_sure.setVisibility(View.VISIBLE);
			break;
		case DIALOG_CREATEROLE://创建角色
			image_title.setImageResource(R.drawable.image_54);
			lay_createRole.setVisibility(View.VISIBLE);
			middle_sure.setVisibility(View.VISIBLE);
			role_L_1_edit.setText(control.preferences.getString(Preferences.USERNAME));
			role_L_2_edit.setText(control.preferences.getString(Preferences.USERNAME));
			break;
		}
		showMiddleDialog();
	}

	/** 展开中间框 */
	public void showMiddleDialog()
	{
		if (showDialog)
			return;

		clickable(false);
		showDialog = true;
		if (animation_down == null)
		{
			final View parentView = (View) relatve.getParent();

			animation_down = new TranslateAnimation(0.0f, 0.0f,
					-parentView.getHeight() * 3 / 2 + relatve.getHeight() / 2,
					0.0f);
			animation_down.setDuration(1000);
			animation_down.setFillAfter(false);
			animation_down.setInterpolator(AnimationUtils.loadInterpolator(
					context, android.R.anim.bounce_interpolator));

			animation_down.setAnimationListener(new AnimationListener()
			{

				public void onAnimationStart(Animation animation)
				{
					// TODO Auto-generated method stub

				}

				public void onAnimationRepeat(Animation animation)
				{
					// TODO Auto-generated method stub

				}

				public void onAnimationEnd(Animation animation)
				{
					relatve.setVisibility(View.VISIBLE);
				}
			});
		}
		if (runnable_down == null)
		{
			runnable_down = new Runnable()
			{

				public void run()
				{
					relatve.startAnimation(animation_down);
				}
			};
		}
		if (handler == null)
		{
			handler = new Handler();
		}
		if (handler != null)
		{
			handler.removeCallbacks(runnable_down);
			handler.post(runnable_down);
		}
	}

	/** 关闭中间框 */
	public void closeMiddleDialog(int nextDialog)
	{
		if (showDialog == false)
			return;
		nextDialogId = nextDialog;

		clickable(true);
		showDialog = false;

		animation_down = null;
		runnable_down = null;

		if (animation_up == null)
		{
			final View parentView = (View) relatve.getParent();

			animation_up = new TranslateAnimation(0.0f, 0.0f, 0.0f,
					-parentView.getHeight() * 3 / 2 + relatve.getHeight() / 2);
			animation_up.setDuration(1000);
			// animation_up.setInterpolator(AnimationUtils.loadInterpolator(context,
			// android.R.anim.accelerate_interpolator));

			animation_up.setAnimationListener(new AnimationListener()
			{

				public void onAnimationStart(Animation animation)
				{
					// TODO Auto-generated method stub

				}

				public void onAnimationRepeat(Animation animation)
				{
					// TODO Auto-generated method stub

				}

				public void onAnimationEnd(Animation animation)
				{
					relatve.setVisibility(View.INVISIBLE);

					if (nextDialogId != -1)
					{
						initMiddleDialog(nextDialogId);
					}
				}
			});
		}
		if (runnable_up == null)
		{
			runnable_up = new Runnable()
			{

				public void run()
				{
					relatve.startAnimation(animation_up);
				}
			};
		}
		if (handler == null)
		{
			handler = new Handler();
		}
		if (handler != null)
		{
			handler.removeCallbacks(runnable_up);
			handler.post(runnable_up);
		}
		switch (middleDialog_type)
		{
		case DIALOG_SETING:
			int[] s = new int[4];
			s[0] = seting[1] ? 0 : 1;// h好友
			s[1] = 0;// 商会
			s[2] = seting[2] ? 0 : 1;// 世界
			s[3] = 0;// 私聊

			Connection.sendMessage(GameProtocol.SYSTEM_SETING,
					ConstructData.getSystemInfo(s));

			break;
		}
	}

	/** 弹出中间框后，关闭部分按键 */
	public void clickable(boolean value)
	{
		for (int i = 0; i < button.length; i++)
		{
			button[i].setClickable(value);
		}
	}

	/** 分区分线信息 */
	public void setElem(Vector v)
	{
		if (v != null && v.size() > 0)
		{
			elem = (XElem) v.elementAt(0);
			synchviewdata();
		}
	}

	public void setElem(XElem e)
	{
		this.elem = e;
	}

	public void initSeverList()
	{
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		XElem lines[] = elem.elems();
		if (lines == null)
			return;
		if (lines.length == 0)
			return;

		int savedId = control.preferences.getInt(Preferences.SEVERLINE_ID);

		oldline.setText(MainActivity.resources
				.getString(R.string.selectsever_lastlogin));

		for (int i = 0; i < lines.length; i++)
		{
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("lineName",
					SystemAPI.getText(lines[i].elem("name").text().toString()));
			int id = Integer.parseInt((lines[i].elem("id").text().toString()));
			if (id == savedId)
			{
				oldline.setText(MainActivity.resources
						.getString(R.string.selectsever_lastlogin)
						+ SystemAPI.getText(lines[i].elem("name").text()
								.toString()));
				GameDef.gateway = lines[i].elem("gateway").text().string();
				GameDef.agentCodeHi = lines[i].elem("agentCodeHi").text()
						.string();
				GameDef.agentCodeLo = lines[i].elem("agentCodeLo").text()
						.string();
				GameDef.resServer = lines[i].elem("resServer").text().string();
				GameDef.platform = lines[i].elem("platform").text().string();
				control.preferences.set(Preferences.SEVERLINE_ID, id);
			}
			list.add(map);
		}
		SimpleAdapter listAdapter = new SimpleAdapter(context, list,
				R.layout.selectsever_item, new String[]
				{ "lineName" }, new int[]
				{ R.id.sever_name });

		listview.setAdapter(listAdapter);

		listview.setOnItemClickListener(new OnItemClickListener()
		{
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id)
			{
				closeMiddleDialog(DIALOG_LOGIN);
				 XElem line[] = elem.elems();
				 
				 String idstr =line[position].elem("id").text().string();
				 
				 control.preferences.set(Preferences.SEVERLINE_ID, Integer.parseInt(idstr));
			}
		});
	}

	/** 设置界面初始化 */
	public void initSeting()
	{
		button_seting = new Button[3];
		button_setingResult = new Button[3];

		button_seting[0] = (Button) currentView.findViewById(R.id.seting_1);
		button_seting[1] = (Button) currentView.findViewById(R.id.seting_2);
		button_seting[2] = (Button) currentView.findViewById(R.id.seting_3);

		button_setingResult[0] = (Button) currentView
				.findViewById(R.id.seting_1_1);
		button_setingResult[1] = (Button) currentView
				.findViewById(R.id.seting_2_1);
		button_setingResult[2] = (Button) currentView
				.findViewById(R.id.seting_3_1);

		for (int i = 0; i < button_seting.length; i++)
		{
			button_seting[i].setOnClickListener(listener);
			button_setingResult[i].setOnClickListener(listener);
			if (seting[i])
			{
				button_setingResult[i]
						.setBackgroundResource(R.drawable.image_24);
			} else
			{
				button_setingResult[i]
						.setBackgroundResource(R.drawable.image_25);
			}
		}
	}
	
	public void Dispaly(String text)
	{
		Toast.makeText(MainActivity.mContext, text, Toast.LENGTH_SHORT).show();
	}
	/**
	 * 注册帐号
	 * */
	public void gotoRegist()
	{
		EditText editText_account = (EditText)currentView.findViewById(R.id.register_1_edit);
		EditText editText_password = (EditText)currentView.findViewById(R.id.register_2_edit);
		EditText editText_pwcheck = (EditText)currentView.findViewById(R.id.register_3_edit);
		
		String password=  editText_password.getText().toString();
		String password_check=editText_pwcheck.getText().toString();
		String account=editText_account.getText().toString();
		
		if(account.length()<2)
		{
			Dispaly(MainActivity.resources.getString(R.string.regist_toast));
		}
		
		
		if(password.equals(password_check))
		{
			//执行具体操作
			Hashtable hash = new Hashtable();
			hash.put("name", editText_account.getText().toString());
			hash.put("password", editText_password.getText().toString());
			GameGroupControl.eventManger.removeAllEvent();
			Event e = new Event(EventManager.ACTION_REGIST,hash);
			GameGroupControl.eventManger.addEvent(e);
			
			Event e1 = new Event(EventManager.ACTION_LOGIN_SEVER,hash);
			GameGroupControl.eventManger.addEvent(e1);
			GameGroupControl.eventManger.nextAction();
		}else if(password.length()<2)
		{
				Dispaly(MainActivity.resources.getString(R.string.regist_toast1));
		}else
		{
			Dispaly(MainActivity.resources.getString(R.string.regist_toast2));
        }
	}

	/**
	 * 登录游戏
	 * */
	public void gotoLogin()
	{
		EditText passname = (EditText) currentView
				.findViewById(R.id.login_1_edit);
		EditText password = (EditText) currentView
				.findViewById(R.id.login_2_edit);

		String uname = passname.getText().toString();
		String upassword = password.getText().toString();

		Hashtable table = new Hashtable();

		table.put("name", uname);
		table.put("password", upassword);
		Event e = new Event(EventManager.ACTION_LOGIN, table);

		Event e1 = new Event(EventManager.ACTION_LOGIN_SEVER, table);

		control.eventManger.addEvent(e).addEvent(e1);
		control.eventManger.nextAction();
	}
	
	/**创建角色*/
	public void gotoCreateRole()
	{
		long pid = 0;
		if (!("".equals(role_L_3_edit.getText()))) {
			try {
				pid = Long.parseLong(role_L_3_edit.getText().toString());
				
			} catch (NumberFormatException e) {
			} 
		} 
		GameData.cpName = role_L_2_edit.getText().toString().trim();
		if (GameData.usersStatus == 0)// 注册公司
		{
			Connection.sendMessage(
					(short) GameProtocol.CONNECTION_SEND_CPINFOR,
					ConstructData.getCpName(GameData.cpName));

		} else
		{
			Connection.sendMessage(
					GameProtocol.CONNECTION_SEND_CREATE_REG,
					ConstructData.getCreatRoleTest(role_L_1_edit.getText().toString()
							.trim(), "", (byte) (role_sex),
							role_sex_index, "", pid));
		}
	}
	
	public void update()
	{
		// TODO Auto-generated method stub

	}

	public void loadProperties(Vector v)
	{
		// TODO Auto-generated method stub

	}

	protected void reCycle()
	{
		// TODO Auto-generated method stub

	}

	protected void refurbish()
	{
		// TODO Auto-generated method stub

	}
}
