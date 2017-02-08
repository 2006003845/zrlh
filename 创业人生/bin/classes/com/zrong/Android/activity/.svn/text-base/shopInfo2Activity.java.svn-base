package com.zrong.Android.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import sun.util.logging.resources.logging;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.zrong.Android.Util.BitmapUtil;
import com.zrong.Android.View.SpriteView;
import com.zrong.Android.element.Employee;
import com.zrong.Android.element.Shop;
import com.zrong.Android.game.Connection;
import com.zrong.Android.game.ConstructData;
import com.zrong.Android.game.GameData;
import com.zrong.Android.game.GameDefinition;
import com.zrong.Android.online.network.GameProtocol;

public class shopInfo2Activity extends GameActivity
{
	public static shopInfo2Activity mContext;
	public buttonListener listener;

	public Shop shop;
	public int resId;
	public int sprId;
	public Button exit;
	/** 中部信息 */
	public ImageView shop_pic;
	public SpriteView spr_people;
	public TextView peo_name;
	public TextView shop_level;
	public TextView textInfo[];
	/** 下部按钮 */
	public ImageButton button[];

	public shopInfo2Activity()
	{
		mContext = this;
	}

	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shopinfo2);
		int position = this.getIntent().getExtras().getInt("position");
		shop = GameData.corporation.shop[position];
		resId = GameData.getUIResId(shop.trade_id);
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

		spr_people = (SpriteView) findViewById(R.id.sprite_people);
		spr_people.setSeries(GameData.player.headResIdx);
		
		shop_level = (TextView) findViewById(R.id.shop_level);
		shop_level.setText(""+shop.level);

		textInfo = new TextView[6];
		textInfo[0] = (TextView) findViewById(R.id.text1);// 收入
		textInfo[1] = (TextView) findViewById(R.id.text2);// 加成
		textInfo[2] = (TextView) findViewById(R.id.text3);// 坐标
		textInfo[3] = (TextView) findViewById(R.id.text4);// 品质
		textInfo[4] = (TextView) findViewById(R.id.text5);// 士气
		textInfo[5] = (TextView) findViewById(R.id.text6);// 知名度

		textInfo[0].setText("" + shop.incomeToday);
		textInfo[1].setText("" + shop.gain);
		textInfo[2].setText("X:" + shop.cityX + "/Y:" + shop.cityY);
		textInfo[3].setText("" + shop.quality + "/" + shop.maxQuality);
		textInfo[4].setText("" + shop.morale + "/" + shop.maxMorale);
		textInfo[5].setText("" + shop.popularity + "/" + shop.maxPopularity);

		button = new ImageButton[6];
		button[0] = (ImageButton) findViewById(R.id.button0);
		button[1] = (ImageButton) findViewById(R.id.button1);
		button[2] = (ImageButton) findViewById(R.id.button2);
		button[3] = (ImageButton) findViewById(R.id.button3);
		button[4] = (ImageButton) findViewById(R.id.button4);
		button[5] = (ImageButton) findViewById(R.id.button5);
		
		peo_name = (TextView) findViewById(R.id.peo_name);
		
		init_zhaolan();
		
		if(shop.employees.length>0){
			button[4].setImageResource(R.drawable.image_96);
			peo_name.setText("："+GameData.getEmployeeById1(shop.managerId).name);
		}
		else{
			button[4].setImageResource(R.drawable.image_61);
			peo_name.setText("："+MainActivity.resources.getString(R.string.shopinfo_vacancy));
		}

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
			case R.id.button0: 
				if (shop.isRecruited())// 被招揽  驱逐招揽
				{
					Connection.sendMessage(GameProtocol.CONNECTION_Shop_UnRecruit_Req,ConstructData.getShop_UnRecruit_Req((byte)1,0,shop.buildingId));
				}else 
				{
					if (shop.isRecruiting())// 取消招揽
					{
						Connection.sendMessage(GameProtocol.CONNECTION_Shop_UnRecruit_Req,ConstructData.getShop_UnRecruit_Req((byte)2,shop.buildingId,0));
					}else//可以招揽
					{
						Connection.sendMessage(
								GameProtocol.CONNECTION_SHOP_Avai_Recruit_List_req,
								ConstructData.getAvaiRecruit((byte) 0, 0,
										shop.trade_id, shop.map_id, 0, (byte) 50));
						GameData.RecruitedShopId = shop.id;
					}
				}
				break;
			case R.id.button1:
				showDialog_caigou();
				break;
			case R.id.button2:
				showDialog_gongguan();
				break;
			case R.id.button3:
				Connection.sendMessage(GameProtocol.CONNECTION_SEND_TOUR_REQ,ConstructData.getTour_req(new long[]{shop.id},(byte)1));
				break;
			case R.id.button4:
				if(shop.employees.length>0){
					Connection.sendMessage(GameProtocol.CONNECTION_SEND_STAFF_OP_REQ,
							ConstructData.Staff_Appoint_Req((byte) 1,
									shop.id, (byte) 4,
									new long[] { shop.employees[0].id }));
				}
				else{
					showDialog_diaopei();
				}
				break;
			case R.id.button5:
				Connection.sendMessage(GameProtocol.CONNECTION_REQ_LEVELUP_VIEW,ConstructData.getLevelUPInfo((byte)2, shop.id));
				break;
				//by lm 采购界面按钮操作
			case R.id.middle_exit:
				closeMiddleDialog();
				break;
			case R.id.buttonleft:
				if (shopNumIndex - 1 >= 0)
				{
					shopNumIndex--;
				} else
				{
					shopNumIndex = cgNum.length - 1;
				}
				shoptext2.setText(""+cgNum[shopNumIndex]*575*30*31/(100*30));
				shoptext3.setText(""+cgNum[shopNumIndex]);
				break;
			case R.id.buttonright:
				if (shopNumIndex < cgNum.length - 1)
				{
					shopNumIndex++;
				} else
				{
					shopNumIndex = 0;
				}
				shoptext2.setText(""+cgNum[shopNumIndex]*575*30*31/(100*30));
				shoptext3.setText(""+cgNum[shopNumIndex]);
				break;
			case R.id.middle_confirme:
				closeMiddleDialog();
				Connection.sendMessage(GameProtocol.CONNECTION_REQ_PURCHASE_GOODS,ConstructData.getPurchaseGoods(shop.id, (byte)shopNumIndex, (byte)1));
//				dialog.cancel();
//				showAlertDialog = false;
				break;
				//by lm 公关界面按钮操作
			case R.id.gg_middle_exit:
				closeMiddleDialog();
				break;
			case R.id.gg_button1:
				shopNumIndex = 0;
				shoptext1.setText(shopGg[shopNumIndex][0]);
				shoptext2.setText(shopGg[shopNumIndex][1]);
				image1.setBackgroundResource(R.drawable.image_97);
				image2.setBackgroundResource(R.drawable.image_2);
				image3.setBackgroundResource(R.drawable.image_2);
				image4.setBackgroundResource(R.drawable.image_2);
				
				break;
			case R.id.gg_button2:
				shopNumIndex = 1;
				shoptext1.setText(shopGg[shopNumIndex][0]);
				shoptext2.setText(shopGg[shopNumIndex][1]);
				image1.setBackgroundResource(R.drawable.image_2);
				image2.setBackgroundResource(R.drawable.image_97);
				image3.setBackgroundResource(R.drawable.image_2);
				image4.setBackgroundResource(R.drawable.image_2);
				break;
			case R.id.gg_button3:
				shopNumIndex = 2;
				shoptext1.setText(shopGg[shopNumIndex][0]);
				shoptext2.setText(shopGg[shopNumIndex][1]);
				image1.setBackgroundResource(R.drawable.image_2);
				image2.setBackgroundResource(R.drawable.image_2);
				image3.setBackgroundResource(R.drawable.image_97);
				image4.setBackgroundResource(R.drawable.image_2);
				break;
			case R.id.gg_button4:
				shopNumIndex = 3;
				shoptext1.setText(shopGg[shopNumIndex][0]);
				shoptext2.setText(shopGg[shopNumIndex][1]);
				image1.setBackgroundResource(R.drawable.image_2);
				image2.setBackgroundResource(R.drawable.image_2);
				image3.setBackgroundResource(R.drawable.image_2);
				image4.setBackgroundResource(R.drawable.image_97);
				break;
			case R.id.gg_middle_confirme:
				closeMiddleDialog();
				if(shopNumIndex == 0) 
				{
					Connection.sendMessage(GameProtocol.CONNECTION_SEND_SHOPPR,ConstructData.getShopRP(new long[]{shop.id},(short)505));
				}
				else if(shopNumIndex == 1)
				{
					Connection.sendMessage(GameProtocol.CONNECTION_SEND_SHOPPR,ConstructData.getShopRP(new long[]{shop.id},(short)504));
				}
				else if(shopNumIndex == 2)
				{
					Connection.sendMessage(GameProtocol.CONNECTION_SEND_SHOPPR,ConstructData.getShopRP(new long[]{shop.id},(short)503));
				}
				else if(shopNumIndex == 3)
				{
					Connection.sendMessage(GameProtocol.CONNECTION_SEND_SHOPPR,ConstructData.getShopRP(new long[]{shop.id},(short)502));
				}
//				dialog.cancel();
//				showAlertDialog = false;
				break;
				//by lm 升级界面按钮操作
			case R.id.sj_middle_exit:
				closeMiddleDialog();
				break;
			case R.id.sj_middle_confirme:
				closeMiddleDialog();
				if (isMatchToLevelUp()) {
					Connection.sendMessage(GameProtocol.CONNECTION_REQ_LEVELUP,
							ConstructData.getLevelUPInfo((byte) 2, shopSJid));
				} else {
					// 弹出提示框---级别不够，无法升级
					Toast toast = Toast.makeText(shopInfo2Activity.this,
							MainActivity.resources.getString(R.string.Shoplevelinfo_message), 1000);
					toast.show();
				}
				break;

				//by lm 调配界面按钮操作
			case R.id.dp_middle_exit:
				closeMiddleDialog();
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

	public void showDialog_zhaolan(Intent intent)
	{
		if (showAlertDialog)
			return;
		showAlertDialog = true;
		dialogView = View.inflate(mContext, R.layout.dialog_zl, null);// 获得View
		dialog = new AlertDialog.Builder(mContext).create();
		dialog.show();
		
		Button button1 = (Button)dialogView.findViewById(R.id.middle_exit);
		button1.setOnClickListener(listener);

		dialog.setOnKeyListener(new OnKeyListener()
		{
			public boolean onKey(DialogInterface dialog, int keyCode,
					KeyEvent event)
			{
				if (keyCode == KeyEvent.KEYCODE_BACK)
				{
					closeMiddleDialog();
					close_zhaolan();
				}
				return false;
			}
		});
		dialog.setContentView(dialogView);

		showMiddleDialog();

		Bundle bundle = intent.getExtras();

		long recruitShopId = bundle.getLong("shopId");

		byte type = bundle.getByte("type");
		updata_zhaolan(dialog, recruitShopId, type);

	}
	
	private void init_diaopei(){
		Button button = (Button)dialogView.findViewById(R.id.dp_middle_exit);
		button.setOnClickListener(listener);
	}
	
	public void showDialog_diaopei(){
		if (showAlertDialog)
			return;
		showAlertDialog = true;
		dialogView = View.inflate(mContext, R.layout.dialog_dp, null);// 获得View
		
		dialog = new AlertDialog.Builder(mContext).create();
		dialog.show();
		
		init_diaopei();
		
		dialog.setOnKeyListener(new OnKeyListener()
		{
			public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event)
			{
				if(keyCode == KeyEvent.KEYCODE_BACK)
				{
					closeMiddleDialog();
				}
				return false;
			}
		});
		 
		dialog.setContentView(dialogView);
		
		showMiddleDialog();
		
		setListView();
		
	}
	
	private void setListView(){
		ListView listView = (ListView) dialog.findViewById(R.id.list_dp);
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		final Vector vector = new Vector();
		for(int i = 0;i < GameData.corporation.employee.length;i++)
		{
			Employee emp = GameData.corporation.employee[i];
			
			if(emp.type == 6)//空闲
			{
				vector.addElement(i);
				HashMap<String, Object> map = new HashMap<String, Object>();

				map.put("name", emp.name);
				map.put("type", "空闲");
				list.add(map);
			}
		}
		
		listView.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> parent,
					View view, int position, long id) {
				// TODO Auto-generated method stub
				int idx = Integer.parseInt(String.valueOf(vector.elementAt(position)));
				Connection.sendMessage(GameProtocol.CONNECTION_SEND_STAFF_OP_REQ,
						ConstructData.Staff_Appoint_Req((byte) 0,shop.id,(byte)4,
										new long[] { GameData.corporation.employee[idx].id }));
				closeMiddleDialog();
			}
			
		});
		
		SimpleAdapter adapter = new SimpleAdapter(this, list,
				R.layout.diaopei_emp_item, new String[]
				{ "name", "type" }, new int[]
				{ R.id.name, R.id.type });

		listView.setAdapter(adapter);
	}
	
	TextView shoptext1;
	TextView shoptext2;
	TextView shoptext3;
	int cgNum[] = {100,500,3000,10000};
	private int shopNumIndex;
	public void showDialog_caigou()
	{
		if (showAlertDialog)
			return;
		showAlertDialog = true;
		dialogView = View.inflate(mContext, R.layout.dialog_cg, null);// 获得View
		
		dialog = new AlertDialog.Builder(mContext).create();
		dialog.show();
		
		Button button1 = (Button)dialogView.findViewById(R.id.middle_exit);
		button1.setOnClickListener(listener);
		ImageButton button2 = (ImageButton)dialogView.findViewById(R.id.buttonleft);
		button2.setOnClickListener(listener);
		ImageButton button3 = (ImageButton)dialogView.findViewById(R.id.buttonright);
		button3.setOnClickListener(listener);
		Button button4 = (Button)dialogView.findViewById(R.id.middle_confirme);
		button4.setOnClickListener(listener);
		
		shopNumIndex = 0;
		shoptext1 = (TextView) dialogView.findViewById(R.id.text1);
		shoptext2 = (TextView) dialogView.findViewById(R.id.text2);
		shoptext3 = (TextView) dialogView.findViewById(R.id.text3);
		shoptext1.setText(""+shop.goodsNum+"/"+shop.maxGoodsNum);
		shoptext2.setText(""+cgNum[shopNumIndex]*575*30*31/(100*30));
		shoptext3.setText(""+cgNum[shopNumIndex]);
		
		dialog.setOnKeyListener(new OnKeyListener()
		{
			public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event)
			{
				if(keyCode == KeyEvent.KEYCODE_BACK)
				{
					closeMiddleDialog();
				}
				return false;
			}
		});
		 
		dialog.setContentView(dialogView);
		
		showMiddleDialog();
	}

	private String[][] shopGg = new String[4][2];
	ImageView image1,image2,image3,image4; 
	private void init_gongguan(){
		//0 花费 1效果
		shopGg[0]=MainActivity.resources.getString(R.string.jiangzuo).split("#");
		shopGg[1]=MainActivity.resources.getString(R.string.wenzhang).split("#");
		shopGg[2]=MainActivity.resources.getString(R.string.xinwen).split("#");
		shopGg[3]=MainActivity.resources.getString(R.string.yaoqing).split("#");
		
		Button button = (Button)dialogView.findViewById(R.id.gg_middle_exit);
		button.setOnClickListener(listener);
		ImageButton button1 = (ImageButton)dialogView.findViewById(R.id.gg_button1);
		button1.setOnClickListener(listener);
		ImageButton button2 = (ImageButton)dialogView.findViewById(R.id.gg_button2);
		button2.setOnClickListener(listener);
		ImageButton button3 = (ImageButton)dialogView.findViewById(R.id.gg_button3);
		button3.setOnClickListener(listener);
		ImageButton button4 = (ImageButton)dialogView.findViewById(R.id.gg_button4);
		button4.setOnClickListener(listener);
		Button button5 = (Button)dialogView.findViewById(R.id.gg_middle_confirme);
		button5.setOnClickListener(listener);
		
		image1 = (ImageView) dialogView.findViewById(R.id.gg_image1);
		image2 = (ImageView) dialogView.findViewById(R.id.gg_image2);
		image3 = (ImageView) dialogView.findViewById(R.id.gg_image3);
		image4 = (ImageView) dialogView.findViewById(R.id.gg_image4);

		image1.setBackgroundResource(R.drawable.image_97);
		
		shopNumIndex=0;
		shoptext1 = (TextView) dialogView.findViewById(R.id.gg_text1);
		shoptext2 = (TextView) dialogView.findViewById(R.id.gg_text2);
		shoptext1.setText(shopGg[0][0]);
		shoptext2.setText(shopGg[0][1]);
		
	}
	
	public void showDialog_gongguan()
	{
		if (showAlertDialog)
			return;
		showAlertDialog = true;
		dialogView = View.inflate(mContext, R.layout.dialog_gg, null);// 获得View
		
		dialog = new AlertDialog.Builder(mContext).create();
		dialog.show();
		
		init_gongguan();
		
		dialog.setOnKeyListener(new OnKeyListener()
		{
			public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event)
			{
				if(keyCode == KeyEvent.KEYCODE_BACK)
				{
					closeMiddleDialog();
				}
				return false;
			}
		});
		 
		dialog.setContentView(dialogView);
		
		showMiddleDialog();
	}
	
	private void init_shengji(){
		Button button = (Button)dialogView.findViewById(R.id.sj_middle_exit);
		button.setOnClickListener(listener);
		Button button1 = (Button)dialogView.findViewById(R.id.sj_middle_confirme);
		button1.setOnClickListener(listener);
		
		shoptext1 = (TextView) dialogView.findViewById(R.id.sj_text1);
		shoptext2 = (TextView) dialogView.findViewById(R.id.sj_text2);
		setText();
	}
	

	private Vector<Vector> vector;// 升级条件信息

	private String unmatchedCinditionStr = "";
	/**
	 * 显示升级条件与升级效果
	 */
	private void setText() {
		vector = GameData.levelUpInfo;
		String matchedCinditionStr = "";
		Vector v0 = vector.elementAt(0);// 符合的条件
		if (v0 != null && !v0.isEmpty()) {
			matchedCinditionStr = matchedCinditionStr + v0.elementAt(0) + "\n";
			matchedCinditionStr = matchedCinditionStr.substring(3);
			v0.remove(0);

			while (!v0.isEmpty()) {

				if (v0.elementAt(0) != null) {
					matchedCinditionStr = matchedCinditionStr + ((String) v0.elementAt(0)).substring(3) + "\n";
				}
				v0.remove(0);
			}
		}

		Vector v1 = vector.elementAt(1);// 不符合的条件
		if (v1 != null && !v1.isEmpty()) {
			unmatchedCinditionStr = unmatchedCinditionStr + v1.elementAt(0)
					+ "\n";
			unmatchedCinditionStr = unmatchedCinditionStr.substring(3);

			v1.remove(0);
			while (!v1.isEmpty()) {
				if (v1.elementAt(0) != null) {
					unmatchedCinditionStr = unmatchedCinditionStr
							+ ((String) v1.elementAt(0)).substring(3) + "\n";
				}
				v1.remove(0);
			}

		}

		Spanned span = Html.fromHtml(matchedCinditionStr + "\n"
				+ "<font color=#ff0000>" + unmatchedCinditionStr + "</font>");
		shoptext1.setText(span);

		String resultInfoStr = "";
		Vector v2 = vector.elementAt(2);// 升级效果
		while (!v2.isEmpty()) {
			if (v2.elementAt(0) != null) {
				resultInfoStr = resultInfoStr + v2.elementAt(0) + "\n";
			}
			v2.remove(0);
		}
		shoptext2.setText(resultInfoStr);
	}

	/**
	 * 判断条件是否允许升级
	 * 
	 * @return
	 */
	private boolean isMatchToLevelUp() {
		if (unmatchedCinditionStr == null || unmatchedCinditionStr.equals(""))
			return true;
		return false;
	}
	
	private long shopSJid;
	public void showDialog_shengji(Intent intent)
	{
		if (showAlertDialog)
			return;
		showAlertDialog = true;
		dialogView = View.inflate(mContext, R.layout.dialog_sj, null);// 获得View
		
		dialog = new AlertDialog.Builder(mContext).create();
		dialog.show();
		
		init_shengji();
		
		dialog.setOnKeyListener(new OnKeyListener()
		{
			public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event)
			{
				if(keyCode == KeyEvent.KEYCODE_BACK)
				{
					closeMiddleDialog();
				}
				return false;
			}
		});
		 
		dialog.setContentView(dialogView);
		
		showMiddleDialog();
		
		Bundle bundle = intent.getExtras();

		shopSJid = bundle.getLong("id");
	}
	
	public void updata_zhaolan(AlertDialog dialog, long id, byte ty)
	{
		final long recruitShopId = id;
		final byte type = ty;
		// dialogView = dialog.getCurrentFocus();
		// dialog.fi
		ListView listView = (ListView) dialog.findViewById(R.id.list_zl);
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		String shopnametag = MainActivity.resources
				.getString(R.string.recruit_shop);
		String flowtag = MainActivity.resources
				.getString(R.string.recruit_customernumber);
		String companynametag = MainActivity.resources
				.getString(R.string.recruit_company);

		final Shop shop[] = GameData.recruitShop;

		for (int i = 0; i < shop.length; i++)
		{
			HashMap<String, Object> map = new HashMap<String, Object>();

			map.put("image", R.drawable.hostile);
			map.put("shopname", shopnametag + shop[i].simpleName);
			map.put("flow", flowtag + shop[i].flowGain);
			map.put("companyname", companynametag + shop[i].shopKeeper);
			map.put("button", R.drawable.button_recruit);
			list.add(map);
		}

		listView.setOnItemClickListener(new OnItemClickListener()
		{

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id)
			{
				Log.d("zzx", "1111111111111111111111");
				final long rid = shop[position].buildingId;

				final Shop myshop = GameData.getShop(recruitShopId);

				String name[] = new String[myshop.employees.length];
				for (int i = 0; i < name.length; i++)
				{
					name[i] = myshop.employees[i].name;
				}
				final AlertDialog dlg2 = new AlertDialog.Builder(
						shopInfo2Activity.this).create();
				dlg2.show();
				dlg2.getWindow().setContentView(R.layout.mapmenu3_list);
				ListView lv = (ListView) dlg2.findViewById(R.id.mapmenu3_list);
				ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
				HashMap<String, String> map;
				if (name.length == 0)
				{
					dlg2.dismiss();
					Toast toast = Toast.makeText(MainActivity.mContext,
							MainActivity.resources
									.getString(R.string.recruit_toast1),
							Toast.LENGTH_LONG);
					toast.show();
//					RecruitActivity.mContext.finish();
				} else
				{

					for (int i = 0; i < name.length; i++)
					{
						map = new HashMap<String, String>();
						map.put("name", name[i]);
						list.add(map);
					}
					SimpleAdapter listAdapter = new SimpleAdapter(
							shopInfo2Activity.this, list,
							R.layout.mapmenu3_item, new String[]
							{ "name" }, new int[]
							{ R.id.mapmenu3_list_text });
					lv.setAdapter(listAdapter);
					lv.setOnItemClickListener(new OnItemClickListener()
					{

						public void onItemClick(AdapterView<?> parent,
								View view, int position, long id)
						{
							// TODO Auto-generated method stub
							Connection.sendMessage(
									GameProtocol.CONNECTION_Shop_Recruit_Req,
									ConstructData.getShop_Recruit_Req(
											myshop.buildingId, rid,
											myshop.employees[position].id,
											(short) myshop.map_id));
							dlg2.dismiss();
						}
					});

				}

			}
		});
		SimpleAdapter adapter = new SimpleAdapter(this, list,
				R.layout.recruit_shop_item, new String[]
				{ "shopname", "flow" }, new int[]
				{ R.id.name, R.id.flow });

		listView.setAdapter(adapter);
	}
	
	public void update(){
		if(shop!=null){
			init_zhaolan();
			
			shop_level.setText(""+shop.level);
			
			textInfo[0].setText("" + shop.incomeToday);
			textInfo[1].setText("" + shop.gain);
			textInfo[3].setText("" + shop.quality + "/" + shop.maxQuality);
			textInfo[4].setText("" + shop.morale + "/" + shop.maxMorale);
			textInfo[5].setText("" + shop.popularity + "/" + shop.maxPopularity);
			
			if(shop.employees.length>0){
				button[4].setImageResource(R.drawable.image_96);
				peo_name.setText("："+GameData.getEmployeeById1(shop.managerId).name);
			}
			else{
				button[4].setImageResource(R.drawable.image_61);
				peo_name.setText("："+MainActivity.resources.getString(R.string.shopinfo_vacancy));
			}
		}
	}

	public void init_zhaolan()
	{
		
		if (shop.isRecruited())// 被招揽
		{
			button[0].setImageResource(R.drawable.image_72);
		}else 
		{
			if (shop.isRecruiting())// 取消招揽
			{
				button[0].setImageResource(R.drawable.image_71);
			}else
			{
				button[0].setImageResource(R.drawable.image_57);
			}
		}
	}
	public void close_zhaolan()
	{
		init_zhaolan();
		
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

}
