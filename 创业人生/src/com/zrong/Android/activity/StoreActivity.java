package com.zrong.Android.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.zrong.Android.Listener.ActivityTransform;
import com.zrong.Android.Listener.TabWidget;
import com.zrong.Android.Util.GridImageAdapter;
import com.zrong.Android.Util.Music;
import com.zrong.Android.activity.R.drawable;
import com.zrong.Android.element.Target;
import com.zrong.Android.game.Connection;
import com.zrong.Android.game.ConstructData;
import com.zrong.Android.game.GameData;
import com.zrong.Android.game.GameDefinition;
import com.zrong.Android.online.network.GameProtocol;

public class StoreActivity extends GameActivity implements 
		TabWidget, OnTouchListener {
	public static StoreActivity mContext = null;
	private Button myGood = null;
	private Button store = null;
	private Button  role_function = null;
	private Button  staff_skill = null;
	private Button  staff_quality = null;
	private Button  staff_function = null;
	private Button   title = null;
	private Button   use = null;
	private Button   buy = null;
	private Button   money = null;
	private Button   delete = null;
	private TextView store_desc = null;
	private TextView mystore_desc = null;
	public static int store_position= -1;
	public static int mystore_position= -1;
	
	
	//private GridView storeGrid = null;
//	private GridView myGoodGrid = null;
	private Gallery storeGallery = null;
	private Gallery mygoodGallery = null;
	private List<Map<String, Object>> myGoods = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> storeGoods = new ArrayList<Map<String, Object>>();

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		mContext = this;
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		this.setContentView(R.layout.store);

		/*Button returnback = (Button) this
				.findViewById(R.id.store_button_returnback);*/
		Button cancel = (Button) this.findViewById(R.id.store_button_cancel);
		title =(Button)this.findViewById(R.id.store_button_title);
		use =(Button)this.findViewById(R.id.store_use);
		buy =(Button)this.findViewById(R.id.store_buy);
		money =(Button)this.findViewById(R.id.store_money);
		delete =(Button)this.findViewById(R.id.store_delete);
		store_desc =(TextView)this.findViewById(R.id.store_desc);
		mystore_desc =(TextView)this.findViewById(R.id.mystore_desc);
//		money.setTypeface(GameDefinition.face);
//		delete.setTypeface(GameDefinition.face);
		if( GameData.propsMall.length>0){
		    money.setText("金钱:"+String.valueOf(GameData.propsMall[0].price));
			 store_desc.setText( GameData.propsMall[0].desc);
		}
	
		if(GameData.props.length>0){
			 mystore_desc.setText( GameData.props[0].desc);
		}
	  
      cancel.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				
			/*	 * if(MapmainMenuActivity.isStoreFromMainMenu){ Intent intent =
				 * new Intent (mContext,MapmainMenuActivity.class);
				 * mContext.startActivity(intent); }
				 * MapmainMenuActivity.isStoreFromMainMenu= false;
				 */
				StoreActivity.this.finish();
			}
		});

/*		returnback.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				
				 * if(MapmainMenuActivity.isStoreFromMainMenu){ Intent intent =
				 * new Intent (mContext,MapmainMenuActivity.class);
				 * mContext.startActivity(intent); }
				 * MapmainMenuActivity.isStoreFromMainMenu= false;
				 
				StoreActivity.this.finish();
			}
		});*/

      myGood = (Button) this.findViewById(R.id.store_button_mystore);
		store = (Button) this.findViewById(R.id.store_button_store);

	//	myGood.setOnTouchListener(this);
	//	store.setOnTouchListener(this);

		myGood.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				store_desc.setVisibility(View.GONE);
				mystore_desc.setVisibility(View.VISIBLE);
				money.setVisibility(View.GONE);
				delete.setVisibility(View.VISIBLE);
				use.setVisibility(View.VISIBLE);
				buy.setVisibility(View.GONE);	
				title.setText("仓库");
			   // title.setBackgroundResource(R.drawable.mystore_title);
				setSelectIndex(0);
			}

		});

		store.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				store_desc.setVisibility(View.VISIBLE);
				mystore_desc.setVisibility(View.GONE);
				money.setVisibility(View.VISIBLE);
				delete.setVisibility(View.GONE);
				use.setVisibility(View.GONE);
				buy.setVisibility(View.VISIBLE);
				title.setText("商城");
			 //   title.setBackgroundResource(R.drawable.store_title);
				setSelectIndex(1);
			}

		});
		buy.setOnClickListener(new OnClickListener() {
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(store_position == -1){
					store_position=0;
				}
				Intent intent = new Intent();

				Bundle bundle = new Bundle(); // 携带数据

				bundle.putString("title",
						MainActivity.resources.getString(R.string.store_title));
				bundle.putInt("price", GameData.propsMall[store_position].price);
				bundle.putInt("position", store_position);
				intent.putExtras(bundle);

				StoreActivity.this
						.Activitychange(ComfirmActivity.class, intent);
			}
		});
		use.setOnClickListener(new OnClickListener() {
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(mystore_position == -1){
					mystore_position = 0;
				}

				final Target[] whos = canUsedByWho(GameData.props[mystore_position].targetTypeld);
				final String[] targetNames = getTargetNames(whos);
				if (targetNames == null||targetNames.length ==0) {
					Toast.makeText(MainActivity.mContext, "没有作用目标", Toast.LENGTH_SHORT).show();
					
				}
				// 单一作用目标
				if (targetNames.length == 1) {
					effectToWho(mystore_position, whos[0]);
				} else if(targetNames.length >1){// 多个作用目标
					Builder builder = new AlertDialog.Builder(
							mContext);
					builder.setItems(targetNames,
							new DialogInterface.OnClickListener() {
								 
								public void onClick(
										DialogInterface dialog,
										int which) {
									Target target = getTargetByName(
											targetNames[which],
											whos);
									if (target == null) {
										return;
									}
									effectToWho(mystore_position, target);
								}
							}).create().show();
				}
				Log.i("ppqq", "targetNames.length ====="+targetNames.length );
			
			}
		});
		delete.setOnClickListener(new OnClickListener() {
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(mystore_position == -1){
					mystore_position = 0;
				}

				Connection.sendMessage(GameProtocol.PROPS_USE,
						ConstructData.useProps((byte) 2,
								GameData.props[mystore_position].id,
								GameData.props[mystore_position].targetTypeld[0],
								GameData.player.id,
								GameData.props[mystore_position].count));
				initMyGoods();
			
			}
		});

	//	storeGrid = (GridView) this.findViewById(R.id.store_gridview);
		storeGallery = (Gallery)this.findViewById(R.id.store_gallery);
/*		storeGrid.setOnScrollListener(new OnScrollListener() {

			 
			public void onScrollStateChanged(AbsListView view, int scrollState) {
			}

			 
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
			}
		});*/

	//	myGoodGrid = (GridView) this.findViewById(R.id.mygoods_gridview);
		mygoodGallery = (Gallery)this.findViewById(R.id.mygoods_gallery);
		//将storeGrid替换为storeGallery
		storeGallery.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				 store_position = position;
				 money.setText("金钱:"+String.valueOf(GameData.propsMall[position].price));
				 store_desc.setText( GameData.propsMall[position].desc);
/*				Intent intent = new Intent();

				Bundle bundle = new Bundle(); // 携带数据

				bundle.putString("title",
						MainActivity.resources.getString(R.string.store_title));
				bundle.putInt("price", GameData.propsMall[position].price);
				bundle.putInt("position", position);
				intent.putExtras(bundle);

				StoreActivity.this
						.Activitychange(ComfirmActivity.class, intent);*/
			}

		}

		);
		
		storeGallery.setOnItemSelectedListener(new OnItemSelectedListener() {

			 
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				store_position=position;
				money.setText("金钱:"+String.valueOf(GameData.propsMall[position].price));
				 store_desc.setText( GameData.propsMall[position].desc);
			}

			 
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
		mygoodGallery.setOnItemSelectedListener(new OnItemSelectedListener() {

			 
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				mystore_position=position;
				mystore_desc.setText( GameData.props[position].desc);
			}

			 
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
		//将myGoodGrid替换为mygoodGallery
		mygoodGallery.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				mystore_position = position;
				mystore_desc.setText( GameData.props[position].desc);
				/*String[] menu = new String[] {
						MainActivity.resources.getString(R.string.store_item1),
						MainActivity.resources.getString(R.string.store_item2),
						MainActivity.resources.getString(R.string.store_item3) };

				Builder builder = new AlertDialog.Builder(StoreActivity.this);
				final int p = position;
				builder.setItems(menu, new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int index) {
						// Connection.sendMessage(GameProtocol.CONNECTION_SEND_Staff_DUTY_CHANGE_REQ,
						// ConstructData.getStaffDutyChange(depart_personal.employees[index].id,
						// (byte)5));
						// //0普通职员或店员;1主管或店长;2助理;3经理;4总监;5首席

						if (index == 0) {
							final Target[] whos = canUsedByWho(GameData.props[p].targetTypeld);
							final String[] targetNames = getTargetNames(whos);
							if (targetNames == null||targetNames.length ==0) {
								Toast.makeText(MainActivity.mContext, "没有作用目标", Toast.LENGTH_SHORT).show();
								
							}
							// 单一作用目标
							if (targetNames.length == 1) {
								effectToWho(p, whos[0]);
							} else if(targetNames.length >1){// 多个作用目标
								Builder builder = new AlertDialog.Builder(
										mContext);
								builder.setItems(targetNames,
										new DialogInterface.OnClickListener() {
											 
											public void onClick(
													DialogInterface dialog,
													int which) {
												Target target = getTargetByName(
														targetNames[which],
														whos);
												if (target == null) {
													return;
												}
												effectToWho(p, target);
											}
										}).create().show();
							}
							Log.i("ppqq", "targetNames.length ====="+targetNames.length );
						} else if (index == 1)// 更新道具
						{
							if(GameData.props[p].count ==0){
							Connection.sendMessage(GameProtocol.PROPS_USE,
									ConstructData.useProps((byte) 1,
											GameData.props[p].id,
											GameData.props[p].targetTypeld[0],
											GameData.player.id,
											GameData.props[p].count));
						}else{
							Connection.sendMessage(GameProtocol.PROPS_USE,
									ConstructData.useProps((byte) 3,
											GameData.props[p].id,
											GameData.props[p].targetTypeld[0],
											GameData.player.id,
											GameData.props[p].count));
							       }
						} else if (index == 2)// 删去道具
						{
							Connection.sendMessage(GameProtocol.PROPS_USE,
									ConstructData.useProps((byte) 2,
											GameData.props[p].id,
											GameData.props[p].targetTypeld[0],
											GameData.player.id,
											GameData.props[p].count));
							initMyGoods();
						}
					}
				}).create().show();
*/
			}

		}

		);

		//setBackgroundResource(store);

		setSelectIndex(1);

	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == GameDefinition.RESULT_OK && data != null) {
			Bundle bundle = data.getExtras();
			int count = bundle.getInt("count");
			int position = bundle.getInt("position");
			Connection.sendMessage(GameProtocol.MALL_BUY, ConstructData
					.getBuyMallInfo(GameData.propsMall[position].id, (byte) 1,
							(byte) count));
		} else if (resultCode == GameDefinition.RESULT_CANCEL) {

		}

	}

	public void Activitychange(Class calss, Intent intent) {

		if (intent == null) {
			intent = new Intent();
		}

		intent.setClass(StoreActivity.this, calss);
		
		StoreActivity.this.startActivityForResult(intent,
				GameDefinition.REQUEST_BUYGOOD);

	}

	public void setSelectIndex(int index) {
		//将storeGrid替换为storeGallery
		storeGallery.setVisibility(View.GONE);
		//将myGoodGrid替换为myGoodGallery
		mygoodGallery.setVisibility(View.GONE);
		if (index == 0) {
			myGood.setVisibility(View.GONE);
			store.setVisibility(View.VISIBLE);
			initMyGoods();
		} else if (index == 1) {
			myGood.setVisibility(View.VISIBLE);
			store.setVisibility(View.GONE);
			initStore();
		}
	}

	public void initMyGoods() {
		//将myGoodGrid替换为mygoodGallery
		mygoodGallery.setVisibility(View.VISIBLE);
		Log.i("Log", "initMyGoods");
		myGoods.clear();
		if (GameData.props != null) 
		{
			Log.i("Log", GameData.props.length + "");
			for (int i = 0; i < GameData.props.length; i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				Log.v("yz", "iconId="+GameData.props[i].icon);
				map.put("iconId", GameData.props[i].icon);
//				Log.i("ppqq", " GameData.props[i].icon======"+ GameData.props[i].icon);
				map.put("text", String.valueOf(GameData.props[i].count) + "个");
//				Log.i("ppqq", " GameData.props[i].count======"+ GameData.props[i].count);
				myGoods.add(map);
			}
			GridImageAdapter adapter = new GridImageAdapter(this, myGoods,
					R.layout.grid_item_icon_text);
			//将myGoodGrid替换为mygoodGallery
			mygoodGallery.setAdapter(adapter);
		}
	}

	private void initStore() 
	{

		//storeGrid = (GridView) this.findViewById(R.id.store_gridview);
		   storeGallery = (Gallery)this.findViewById(R.id.store_gallery);
		   //将storeGrid替换为storeGallery
		   storeGallery.setVisibility(View.VISIBLE);

		
		storeGoods.clear();// 清除
		for (int i = 0; i < GameData.propsMall.length; i++) 
		{
			Map<String, Object> map = new HashMap<String, Object>();
			Log.v("yz", "index="+i+",icon="+GameData.propsMall[i].icon);
			map.put("iconId", GameData.propsMall[i].icon);
			map.put("text", String.valueOf(GameData.propsMall[i].price) + "G");
			storeGoods.add(map);
		}

		GridImageAdapter adapter = new GridImageAdapter(this, storeGoods,
				R.layout.grid_item_icon_text);
		 //将storeGrid替换为storeGallery
		storeGallery.setAdapter(adapter);
	}

	public boolean onTouch(View view, MotionEvent arg1) {

		if (view == myGood) {
			setBackgroundResource(myGood);
		} else if (view == store) {
			setBackgroundResource(store);
		}
		return false;
	}

	public void setBackgroundResource(Button button) 
	{
		myGood.setBackgroundResource(R.drawable.tabbutton);
		store.setBackgroundResource(R.drawable.tabbutton);
		button.setBackgroundResource(R.drawable.tabbutton_pressed);
	}

	

	/**
	 * 作用目标(1角色2员工3公司4店铺5部门)
	 * 
	 * @param targetTypeld
	 */
	private Target[] canUsedByWho(byte[] targetTypeld) {
		Target[] whos = new Target[targetTypeld.length];
		if (whos == null || whos.length <= 0) {
			return null;
		}
		for (int i = 0; i < whos.length; i++) {
			if (targetTypeld[i] == 1)
				whos[i] = new Target("自己", targetTypeld[i]);
			if (targetTypeld[i] == 2)
				whos[i] = new Target("员工", targetTypeld[i]);
			if (targetTypeld[i] == 3)
				whos[i] = new Target("公司", targetTypeld[i]);
			if (targetTypeld[i] == 4)
				whos[i] = new Target("店铺", targetTypeld[i]);
			if (targetTypeld[i] == 5)
				whos[i] = new Target("部门", targetTypeld[i]);
		}
		return whos;
	}

	/**
	 * 获取作用目标的String数组
	 * 
	 * @param targets
	 * @return string
	 */
	private String[] getTargetNames(Target[] targets) {
		if (targets == null || targets.length <= 0) {
			return null;
		}
		String[] targetNames = new String[targets.length];
		for (int i = 0, len = targetNames.length; i < len; i++) {
			targetNames[i] = targets[i].name;
		}
		return targetNames;
	}

	/**
	 * 获取作用目标
	 * 
	 * @param name
	 * @param targets
	 * @return Target
	 */
	private Target getTargetByName(String name, Target[] targets) {
		if (name == null || targets == null || targets.length <= 0) {
			return null;
		}
		for (int i = 0; i < targets.length; i++) {
			if (name.equals(targets[i].name)) {
				return targets[i];
			}
		}
		return null;
	}

	/**
	 * 作用对象实现
	 * 
	 * @param p
	 * @param target
	 */
	private void effectToWho(final int p, Target target) {
		if (target == null) {
			return;
		}
		if (target.id == 1)// 主角
		{
			Log.v("yz", "p="+p+",id="+GameData.props[p].id);
			Connection.sendMessage(GameProtocol.PROPS_USE, ConstructData
					.useProps((byte) 1, GameData.props[p].id,
							GameData.props[p].targetTypeld[0],
							GameData.player.id, GameData.props[p].count));
		} else if (target.id == 2)// 员工
		{
			// initEmployeesList(RecruitmentEmp.USEGOODS);
			// updata_props = true;
			String ename[] = new String[GameData.corporation.employee.length];
			if(ename.length ==0){
				Toast.makeText(MainActivity.mContext, "没有作用目标", Toast.LENGTH_SHORT).show();
			}else{
			for (int i = 0; i < ename.length; i++) {
				ename[i] = GameData.corporation.employee[i].name;
			}
			Builder builder = new AlertDialog.Builder(StoreActivity.this);
			builder.setItems(ename, new DialogInterface.OnClickListener() {

				public void onClick(DialogInterface dialog, int index) {
					Connection.sendMessage(GameProtocol.PROPS_USE,
							ConstructData.useProps((byte) 1,
									GameData.props[p].id, (byte) 2,
									GameData.corporation.employee[index].id,
									GameData.props[p].count));

				}

			}

			).create().show();

			}
			} else if (target.id == 3)// 公司
		{
			Connection.sendMessage(GameProtocol.PROPS_USE, ConstructData
					.useProps((byte) 1, GameData.props[p].id,
							GameData.props[p].targetTypeld[0],
							GameData.player.id, GameData.props[p].count));
		} else if (target.id == 4) // 店铺
		{
			String shopname[] = new String[GameData.corporation.shop.length];

			for (int i = 0; i < shopname.length; i++) {
				shopname[i] = GameData.corporation.shop[i].name;

			}

			Builder builder = new AlertDialog.Builder(StoreActivity.this);
			builder.setItems(shopname, new DialogInterface.OnClickListener() {

				public void onClick(DialogInterface dialog, int index) {
					Connection.sendMessage(GameProtocol.PROPS_USE,
							ConstructData.useProps((byte) 1,
									GameData.props[p].id, (byte) 4,
									GameData.corporation.shop[index].id,
									GameData.props[p].count));
				}
			}).create().show();
		}
	}

	
	public GameActivity getGameContext() 
	{
		return this;
	}
	
	 
	public void finish() 
	{
		mContext = null;
		super.finish();
	}

}
