package com.zrong.Android.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnKeyListener;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.view.animation.Animation.AnimationListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.zrong.Android.Util.BitmapUtil;
import com.zrong.Android.View.SpriteView;
import com.zrong.Android.element.MapBuilding;
import com.zrong.Android.element.Shop;
import com.zrong.Android.game.Connection;
import com.zrong.Android.game.ConstructData;
import com.zrong.Android.game.GameData;
import com.zrong.Android.game.GameDefinition;
import com.zrong.Android.online.network.GameProtocol;

public class OtherShopInfoActivity extends GameActivity{


	public static OtherShopInfoActivity mContext;
	public buttonListener listener;

	private MapBuilding mbuilding = null;
	public int resId;
	public int sprId;
	public Button exit;
	/** 中部信息 */
	public ImageView shop_pic;
	public TextView name;
	public TextView textInfo[];
	/** 下部按钮 */
	public ImageButton button[];

	public OtherShopInfoActivity()
	{
		mContext = this;
	}

	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.othershopinfo);
		int position = this.getIntent().getExtras().getInt("position");
		mbuilding = GameData.build[position].mb;
		resId = GameData.getUIResId(mbuilding.trade_id);
		listener = new buttonListener();

		init();

	}

	public void init()
	{
		shop_pic = (ImageView) findViewById(R.id.shop_pic);
		Bitmap bitmap = BitmapUtil.getBitmap(resId, 0, (float) 1);
		if (bitmap == null)
		{
			bitmap = BitmapUtil.getBitmap(811, 0, (float) 1);
		}
		shop_pic.setImageBitmap(bitmap);

		exit = (Button) findViewById(R.id.exitgame);
		exit.setOnClickListener(listener);

		textInfo = new TextView[7];
		textInfo[0] = (TextView) findViewById(R.id.text1);// 收入
		textInfo[1] = (TextView) findViewById(R.id.text2);// 加成
		textInfo[2] = (TextView) findViewById(R.id.text3);// 坐标
		textInfo[3] = (TextView) findViewById(R.id.text4);// 品质
		textInfo[4] = (TextView) findViewById(R.id.text5);// 士气
		textInfo[5] = (TextView) findViewById(R.id.text6);// 知名度
		textInfo[6] = (TextView) findViewById(R.id.text7);// 店长

		textInfo[2].setText("X:" + mbuilding.cityX + "/Y:" + mbuilding.cityY);
		
		name = (TextView) findViewById(R.id.info);
		name.setText(mbuilding.name);

		button = new ImageButton[2];
		button[0] = (ImageButton) findViewById(R.id.button0);
		button[1] = (ImageButton) findViewById(R.id.button1);

		for (int i = 0; i < button.length; i++)
		{
			button[i].setOnClickListener(listener);
		}
	}

	public void finish()
	{

		mContext = null;
		super.finish();
	}

	public GameActivity getGameContext()
	{
		// TODO Auto-generated method stub
		return mContext;
	}

	public class buttonListener implements OnClickListener
	{

		public void onClick(View v)
		{
			int id = v.getId();
			switch (id)
			{
			case R.id.exitgame:
				finish();
				break;
			case R.id.button0: //招揽
				showDialog_zhaolan();
				break;
			case R.id.button1://使用策略
				useStrategy();
				break;
			}
		}
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			finish();
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}

	public boolean showAlertDialog;
	public View dialogView;
	public AlertDialog dialog;
	public Animation animation_down, animation_up;

	public void showDialog_zhaolan()
	{
		if (showAlertDialog)
			return;
		showAlertDialog = true;
		
		//没有店铺提示
		if (GameData.corporation.shop.length <= 0) {
			Toast toast = Toast.makeText(MainActivity.mContext,
					MainActivity.resources.getString(R.string.shopinfo_toast),
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
		//没有可用的店铺或员工
		if (v.size() <= 0) {
			Toast toast = Toast.makeText(MainActivity.mContext,
					MainActivity.resources
							.getString(R.string.shopinfo_toast1),
					Toast.LENGTH_LONG);
			toast.show();
			return;
		}
		
		dialogView = View.inflate(mContext, R.layout.dialog_zl, null);// 获得View
		dialog = new AlertDialog.Builder(mContext).create();
		dialog.show();

		dialog.setOnKeyListener(new OnKeyListener()
		{
			public boolean onKey(DialogInterface dialog, int keyCode,
					KeyEvent event)
			{
				if (keyCode == KeyEvent.KEYCODE_BACK)
				{
					closeMiddleDialog();
				}
				return false;
			}
		});
		
		String shopName[] = new String[v.size()];

		for (int i = 0; i < shopName.length; i++) {
			shopName[i] = ((Shop) v.elementAt(i)).name;
		}
    	ListView lv = (ListView)dialog.findViewById(R.id.list_zl); 
		ArrayList<HashMap<String, String>>list= new  ArrayList<HashMap<String,String>>();
		HashMap<String, String> map;
		for (int i = 0; i < shopName.length; i++) {
			map = new HashMap<String, String>();
			map.put("name", shopName[i]);
			list.add(map);
		}
		 SimpleAdapter listAdapter = new SimpleAdapter(MainActivity.mContext,list,   
	                R.layout.recruit_shop_item, new String[] {"name"},   
	                new int[] {R.id.name});   
        lv.setAdapter(listAdapter);
		lv.setOnItemClickListener(new OnItemClickListener() {

			 
			public void onItemClick(AdapterView<?> parent,
					View view, int position, long id) {
				// TODO Auto-generated method stub
				Shop s = ((Shop) v.elementAt(position));
				Connection.sendMessage(
						GameProtocol.CONNECTION_Shop_Recruit_Req, ConstructData
								.getShop_Recruit_Req(s.buildingId,
										mbuilding.id, s.employees[0].id,
										(short) s.map_id));
				closeMiddleDialog();
			}
		});
		
		dialog.setContentView(dialogView);

		showMiddleDialog();

	}		
	
	public static long shop_ID;
	public static long player_ID;
	//使用策略 跳到策划部 策划部界面还未修改
	public void useStrategy(){
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
//			Shop shop = new Shop();
//			shop_ID=shop.id;
			shop_ID = mbuilding.id;
			player_ID = mbuilding.playerId;
			Intent intent = new Intent();
			intent.putExtra("jumpshop", "isFromShopinfo");	
			mContext.Activitychange(PlanningDeptActivity.class, intent);
		}
	}

	/** 弹出弹出框 */
	public void showMiddleDialog()
	{
		if (animation_down == null)
		{
			animation_down = new TranslateAnimation(0.0f, 0.0f,
					-GameDefinition.screenWidth * 3 / 2, 0.0f);
		}

		animation_down.setDuration(1000);
		animation_down.setFillAfter(false);
		dialogView.startAnimation(animation_down);
		animation_down.setInterpolator(AnimationUtils.loadInterpolator(
				mContext, android.R.anim.bounce_interpolator));

		animation_down.setAnimationListener(new AnimationListener()
		{

			public void onAnimationStart(Animation animation)
			{
				Log.d("zzx", "start");
			}

			public void onAnimationRepeat(Animation animation)
			{
				Log.d("zzx", "----------");
			}

			public void onAnimationEnd(Animation animation)
			{
				Log.d("zzx", "end");
			}
		});
	}

	/** 关闭弹出框 */
	public void closeMiddleDialog()
	{
		if (!showAlertDialog)
			return;
		showAlertDialog = false;

		animation_down = null;

		animation_up = new TranslateAnimation(0, 0, 0.0f,
				-GameDefinition.screenWidth * 3 / 2);
		animation_up.setDuration(1000);
		animation_up.setFillAfter(false);
		animation_up.setInterpolator(AnimationUtils.loadInterpolator(mContext,
				android.R.anim.accelerate_interpolator));

		dialogView.startAnimation(animation_up);
		animation_up.setAnimationListener(new AnimationListener()
		{

			public void onAnimationStart(Animation animation)
			{
				Log.d("zzx", "close_start");
			}

			public void onAnimationRepeat(Animation animation)
			{
				// TODO Auto-generated method stub

			}

			public void onAnimationEnd(Animation animation)
			{
				animation_up = null;
				dialog.cancel();
				Log.d("zzx", "close_end");
			}
		});
	}

	public void Activitychange(Class calss, Intent intent) {
		if (intent == null) {
			intent = new Intent();
		}
		
		intent.setClass(OtherShopInfoActivity.this, calss);
		
		this.startActivity(intent);
	}

	
	
}
