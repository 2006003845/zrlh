package com.zrong.Android.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import com.zrong.Android.Util.BitmapUtil;
import com.zrong.Android.Util.ImageAdapter;
import com.zrong.Android.View.SpriteView;
import com.zrong.Android.element.Employee;
import com.zrong.Android.game.Connection;
import com.zrong.Android.game.ConstructData;
import com.zrong.Android.game.GameData;
import com.zrong.Android.online.network.GameProtocol;

public class CreateShopAndBuildActivity extends GameActivity
{
	public CreateShopAndBuildActivity mContext;
	/**
	 * 切屏幕对象 画廊
	 * */
	public ViewFlipper flipper;
	public Gallery gallery[];
	public Button button[];
	public Button name[], back[];
	private Button dialog_top[], dialog_down[];
	/** 监听对象 */
	public buttonListener listener;
	public itemListener itemlistener;

	public int currentIndex = 0;
	public static int shopSizeSelect;
	private int shopIndex,buildIndex;
	public static int idx1 = -1;

	/** 建造小地址 */
	private int cityX;
	private int cityY;
	private int startGrid;
	private int[] canBuildIndex;

	public RelativeLayout relatve;
	private Animation animation_down, animation_up;
	private Runnable runnable_down, runnable_up;
	private Handler handler;
	private boolean showDialog;
	private String shopSize[];
	
	/**店铺规模*/
	private TextView text_shopSize;
	/**
	 * 工艺建筑  动画
	 * 创建店铺  图片
	 * */
	private SpriteView sprite; 
	private ImageView img;
	/**
	 * 名称图片
	 * 创建店铺  名称
	 * 创建建筑  员工名称
	 * */
	private ImageView nameImage;
	private EditText shopName; 
	private TextView buildName;
	
	private ImageButton middleDialogName;
	
	
	

	public CreateShopAndBuildActivity()
	{
		mContext = this;
	}

	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shopandbuilding);
		initButton();
		setSelect(0);
	}

	public void initButton()
	{
		shopSize = MainActivity.resources.getString(R.string.shopsize).split(
				",");

		listener = new buttonListener();
		itemlistener = new itemListener();

		button = new Button[2];
		button[0] = (Button) findViewById(R.id.create_shop);
		button[1] = (Button) findViewById(R.id.create_building);

		for (int i = 0; i < button.length; i++)
		{
			button[i].setOnClickListener(listener);
		}

		name = new Button[2];
		name[0] = (Button) findViewById(R.id.info1);
		name[1] = (Button) findViewById(R.id.info2);

		back = new Button[2];
		back[0] = (Button) findViewById(R.id.back1);
		back[1] = (Button) findViewById(R.id.back2);
		for (int i = 0; i < back.length; i++)
		{
			back[i].setOnClickListener(listener);
		}

		flipper = (ViewFlipper) findViewById(R.id.flipper);

		gallery = new Gallery[2];
		gallery[0] = (Gallery) findViewById(R.id.gallery1);
		gallery[1] = (Gallery) findViewById(R.id.gallery2);

		for (int i = 0; i < gallery.length; i++)
		{
			gallery[i].setOnItemSelectedListener(itemlistener);
		}

		relatve = (RelativeLayout) findViewById(R.id.middle_menu);

		dialog_top = new Button[2];
		dialog_down = new Button[2];

		dialog_top[0] = (Button) findViewById(R.id.middle_exit);
		dialog_top[1] = (Button) findViewById(R.id.middle_yes);

		dialog_down[0] = (Button) findViewById(R.id.middle_shop_chose_1);
		dialog_down[1] = (Button) findViewById(R.id.middle_shop_chose_3);

		for (int i = 0; i < dialog_top.length; i++)
		{
			dialog_top[i].setOnClickListener(listener);
			dialog_down[i].setOnClickListener(listener);
		}
		
		middleDialogName = (ImageButton)findViewById(R.id.middle_name);
		nameImage = (ImageView)findViewById(R.id.middle_shop_name);
		shopName = (EditText)findViewById(R.id.middle_shop_name_edit);
		buildName = (TextView)findViewById(R.id.middle_building_name_edit);
		img = (ImageView) findViewById(R.id.middle_shop_pic);
		sprite = (SpriteView) findViewById(R.id.middle_building_pic);
		text_shopSize = (TextView) findViewById(R.id.middle_shop_chose_2);
		
		buildName.setOnClickListener(listener);
	}

	public void initView()
	{

		switch (currentIndex)
		{
		case 0:// 创建店铺
			initShopView();
			break;
		case 1:// 创建工艺建筑
			initBuildView();
			break;
		}

		/*
		 * Random r = new Random(); r.setSeed(100); int random =
		 * Math.abs(r.nextInt());
		 * 
		 * int select = random % GameData.professionId.length;
		 * 
		 * if (select == 0 || select == GameData.professionId.length - 1) {
		 * select = GameData.professionId.length / 2; }
		 * gallery[currentIndex].setSelection(select);
		 */

	}

	/** 初始化 创建店铺界面 */
	public void initShopView()
	{
		int imageId[] = null;
		Bundle bundle = null;

		bundle = this.getIntent().getExtras();

		cityX = bundle.getInt("cityX");

		cityY = bundle.getInt("cityY");

		startGrid = bundle.getInt("grid");

		ImageAdapter adapter = new ImageAdapter(this, ImageAdapter.GALLERY);
		imageId = new int[GameData.professionId.length];

		for (int i = 0; i < GameData.professionId.length; i++)
		{
			imageId[i] = GameData.getUIResId(GameData.professionId[i]);
		}

		adapter.setImageIdArray(imageId);
		gallery[currentIndex].setAdapter(adapter);
		gallery[currentIndex].setOnItemClickListener(new OnItemClickListener()
		{

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id)
			{
				showMiddleDialog(position);
			}

		});

	}

	/** 初始化 创建建筑界面 */
	public void initBuildView()
	{

		int imageId[] = null;
		Bundle bundle = null;

		canBuildIndex = GameData.getIndex_accessBuilding();
		bundle = new Bundle();

		cityX = bundle.getInt("cityX");

		cityY = bundle.getInt("cityY");

		ImageAdapter adapter2 = new ImageAdapter(this,
				ImageAdapter.SPRITEGALLERY);

		canBuildIndex = GameData.getIndex_accessBuilding();

		imageId = new int[canBuildIndex.length];

		for (int i = 0; i < canBuildIndex.length; i++)
		{
			imageId[i] = GameData
					.getUIResId(GameData.buildingId[canBuildIndex[i]]);
		}

		adapter2.setImageIdArray(imageId);

		gallery[1].setAdapter(adapter2);
		gallery[1].setOnItemClickListener(new OnItemClickListener()
		{

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id)
			{
//				clickGalleryBuilding(parent, view, position, id);
				showMiddleDialog(position);
			}
		});
	}

	/** 关闭弹出框 */
	public void closeMiddleDialog()
	{
		showDialog = false;
		animation_down = null;
		runnable_down = null;
		shopName.setText("");
		//

		clickAble(true);
		final View parentView = (View) relatve.getParent();
		animation_up = new TranslateAnimation(0, 0, 0.0f,
				-parentView.getHeight() * 3 / 2 + relatve.getHeight() / 2);
		animation_up.setDuration(1000);
		animation_up.setFillAfter(false);
		animation_up.setInterpolator(AnimationUtils.loadInterpolator(this, android.R.anim.accelerate_interpolator));

		runnable_up = new Runnable()
		{

			public void run()
			{
				relatve.startAnimation(animation_up);
			}
		};
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
				runnable_up = null;
				animation_up = null;
			}
		});
		handler.removeCallbacks(runnable_up);
		handler.post(runnable_up);
	}

	/** 弹出弹出框 */
	public void showMiddleDialog(int position)
	{
		switch(currentIndex)
		{
		case 0:
			shopIndex = position;
			updataShopInfo(position);
			break;
		case 1:
			buildIndex = position;
			updataBuildingInfo(position);
			break;
		}

		if (showDialog)
		{
			return;
		}
		clickAble(false);
		showDialog = true;
		if (animation_down == null)
		{
			final View parentView = (View) relatve.getParent();
			

			animation_down = new TranslateAnimation(0, 0,
					-parentView.getHeight() * 3 / 2 + relatve.getHeight() / 2,
					0.0f);
			animation_down.setDuration(1000);
			animation_down.setStartOffset(500);
			animation_down.setFillAfter(false);
			animation_down.setInterpolator(AnimationUtils.loadInterpolator(this, android.R.anim.bounce_interpolator));

		}
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

		if (runnable_down == null)
			runnable_down = new Runnable()
			{

				public void run()
				{
					relatve.startAnimation(animation_down);
				}
			};

		if (handler == null)
		{
			handler = new Handler();
		}

		handler.removeCallbacks(runnable_down);
		handler.post(runnable_down);
	}

	/** 更新要创建的店铺信息 */
	public void updataShopInfo(int position)
	{
		int shopImageId = GameData.getUIResId(GameData.professionId[position]);
		int pepFlow = 0;
		if (shopSizeSelect == 0)
		{
			pepFlow = (GameData.mapTraffic[0] * 9);
		} else if (shopSizeSelect == 1)
		{
			pepFlow = (GameData.mapTraffic[0] * 25);
		} else
		{
			pepFlow = (GameData.mapTraffic[0] * 49);
		}
		long createMoney = (GameData.createShop_Fees[position
				* GameData.shop_scale_size + shopSizeSelect]);
		int dayRent= GameData.mapRent[0];

		Bitmap bitmap = BitmapUtil.getBitmap(shopImageId, 0, (float) 1);
		img.setImageBitmap(bitmap);

		text_shopSize.setText(shopSize[shopSizeSelect]);
		//客流量
		TextView text_pepFlow = (TextView) findViewById(R.id.middle_shop_num_edit);
		text_pepFlow.setText("" + pepFlow);
		//费用
		TextView text_money = (TextView) findViewById(R.id.middle_shop_price_edit);
		text_money.setText("" + createMoney);
		//租金
		ImageView rentView = (ImageView)findViewById(R.id.middle_shop_rent);
		rentView.setBackgroundResource(R.drawable.image_40);
		TextView text_rent = (TextView) findViewById(R.id.middle_shop_rent_edit);
		text_rent.setText("" + dayRent);
		
		nameImage.setBackgroundResource(R.drawable.image_5);
		middleDialogName.setImageResource(R.drawable.image_9);
		
		shopName.setVisibility(View.VISIBLE); 
		shopName.setClickable(true);
		buildName.setClickable(false);
		buildName.setVisibility(View.GONE);
		
		sprite.setVisibility(View.GONE);
		img.setVisibility(View.VISIBLE);
		dialog_down[0].setVisibility(View.VISIBLE);
		dialog_down[1].setVisibility(View.VISIBLE);
		text_shopSize.setVisibility(View.VISIBLE);
	}
	
	/**更新要创建建筑的信息*/
	public void updataBuildingInfo(int position)
	{
		int shopImageId = GameData.getUIResId(GameData.buildingId[canBuildIndex[position]]);
		int pepFlow = GameData.flow_gain[position];
		
		long createMoney = GameData.buildingFees[position];


		((SpriteView) sprite).setId(shopImageId);


		TextView text_pepFlow = (TextView) findViewById(R.id.middle_shop_num_edit);
		text_pepFlow.setText("" + pepFlow);

		TextView text_money = (TextView) findViewById(R.id.middle_shop_price_edit);
		text_money.setText("" + createMoney);

		//租金
		ImageView rentView = (ImageView)findViewById(R.id.middle_shop_rent);
		rentView.setBackgroundResource(R.drawable.image_41);
		
		long dayRent = GameData.weeklyFees[position];
		TextView text_rent = (TextView) findViewById(R.id.middle_shop_rent_edit);
		text_rent.setText("" + dayRent);
		
		nameImage.setBackgroundResource(R.drawable.image_14);
		middleDialogName.setImageResource(R.drawable.image_13);
		shopName.setVisibility(View.GONE); 
		shopName.setClickable(false);
		buildName.setClickable(true);
		buildName.setVisibility(View.VISIBLE);
		img.setVisibility(View.GONE);
		dialog_down[0].setVisibility(View.GONE);
		dialog_down[1].setVisibility(View.GONE);
		text_shopSize.setVisibility(View.GONE);
		sprite.setVisibility(View.VISIBLE);
	}

	/** 设置选中项 */
	public void setSelect(int index)
	{

		currentIndex = index;
		if (index == 0)
		{
			button[0].setBackgroundResource(R.drawable.shop_pressed);
			button[1].setBackgroundResource(R.drawable.building);
		} else if (index == 1)
		{

			button[0].setBackgroundResource(R.drawable.shop);
			button[1].setBackgroundResource(R.drawable.building_pressed);
		}
		flipper.setDisplayedChild(index);
		initView();

	}

	public void finish()
	{
		if (showDialog)
		{
			closeMiddleDialog();
		} else
		{
			super.finish();
			mContext = null;
		}
	}

	public GameActivity getGameContext()
	{
		return mContext;
	}

	public void Dispaly(String text)
	{
		Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
	}

	public class buttonListener implements OnClickListener
	{
		public void onClick(View v)
		{
			int id = v.getId();
			switch (id)
			{
			case R.id.create_shop:
				setSelect(0);
				break;
			case R.id.create_building:
				setSelect(1);
				break;
			case R.id.back1:
				finish();
				break;
			case R.id.back2:
				finish();
				break;
			case R.id.middle_building_name_edit:
				clickGalleryBuilding();
				break;
			case R.id.middle_shop_chose_1:
				if (shopSizeSelect + 1 < shopSize.length)
				{
					shopSizeSelect++;
				} else
				{
					shopSizeSelect = 0;
				}
				updataShopInfo(shopIndex);
				break;
			case R.id.middle_shop_chose_3:
				if (shopSizeSelect - 1 > 0)
				{
					shopSizeSelect--;
				} else
				{
					shopSizeSelect = shopSize.length - 1;
				}
				updataShopInfo(shopIndex);
				break;
			case R.id.middle_exit:
				closeMiddleDialog();
				break;
			case R.id.middle_yes:
				
				GameData.buildCityX = (short) cityX;
				GameData.buildCityY = (short) cityY; 
				String name = shopName.getText().toString().trim();
				closeMiddleDialog();
				
				Connection.sendMessage(
						GameProtocol.CONNECTION_SEND_SHOP_CREATE, ConstructData
								.getCreateShop(name,
										(byte) (shopSizeSelect + 1),
										GameData.professionId[gallery[0]
												.getSelectedItemPosition()],
										GameData.mapIds[GameData.mapIdIndex],
										(short) cityX, (short) cityY,
										(byte) startGrid));
				
				break;
			}
		}
	}

	public class itemListener implements OnItemSelectedListener
	{

		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id)
		{
			switch (currentIndex)
			{
			case 0:
				updataGalleryShop(parent, view, position, id);
				break;
			case 1:
				updataGalleryBuilding(parent, view, position, id);
				break;
			}

		}

		public void onNothingSelected(AdapterView<?> parent)
		{
			// TODO Auto-generated method stub

		}
	}

	public void updataGalleryShop(AdapterView<?> parent, View view,
			int position, long id)
	{
		String name = "";
		long cost = 0;
		if (GameData.shopTemplate_hidded[position * GameData.shop_scale_size
				+ shopSizeSelect] == 1)
		{
			for (int i = 0; i < GameData.hiddedShopId.length; i++)
			{
				if (GameData.shopTemplate_id[position
						* GameData.shop_scale_size + shopSizeSelect] == GameData.shopTemplate_id[(int) GameData.hiddedShopId[i][0]])
				{
					if (GameData.hiddedShopId[i][1] == 1)
					{
						idx1 = position;
						Dispaly(MainActivity.resources
								.getString(R.string.createbuilding_toast_lock));
					} else
					{
						name = GameData.shopName[position
								* GameData.shop_scale_size + shopSizeSelect];
						cost = GameData.createShop_Fees[position
								* GameData.shop_scale_size + shopSizeSelect];
						Dispaly(MainActivity.resources
								.getString(R.string.createbuilding_toast_choose)
								+ name
								+ "  ;"
								+ MainActivity.resources
										.getString(R.string.createbuilding_toast_fee)
								+ cost);
					}
				}
			}

		} else
		{
			name = GameData.shopName[position * GameData.shop_scale_size
					+ shopSizeSelect];
			cost = GameData.createShop_Fees[position * GameData.shop_scale_size
					+ shopSizeSelect];
		}
		Gallery gallery = (Gallery) parent;

		ImageAdapter adappert = (ImageAdapter) gallery.getAdapter();
		this.name[0].setText(name);

		adappert.notifyDataSetChanged(position);
	}

	public void updataGalleryBuilding(AdapterView<?> parent, View view,
			int position, long id)
	{
		String name = GameData.buildingName[position];
		Gallery gallery = (Gallery) parent;

		ImageAdapter adappert = (ImageAdapter) gallery.getAdapter();
		this.name[1].setText(name);
	}

	public void clickAble(boolean value)
	{
		for (int i = 0; i < button.length; i++)
		{
			button[i].setClickable(value);
			gallery[i].setClickable(value); 
			back[i].setClickable(value);
		}
	}
	public void clickGalleryBuilding()
	{
		Dispaly(MainActivity.resources
				.getString(R.string.createbuilding_toast_choose)
				+ GameData.buildingName[buildIndex]);

		final Vector vector = new Vector();

		for (int i = 0; i < GameData.corporation.employee.length; i++)
		{
			if (GameData.corporation.employee[i].department <= 0)
			{
				vector.addElement(i);
			}
		}

		if (vector.isEmpty())
		{
			Dispaly(MainActivity.resources
					.getString(R.string.createbuilding_toast_message));
		}

		String[] name = new String[vector.size()];
		if (name.length == 0)
		{
			return;
		} else
		{
			

			final int p = buildIndex;
			final AlertDialog dialog = new AlertDialog.Builder(
					CreateShopAndBuildActivity.this).create();
			dialog.show();
			dialog.getWindow().setContentView(R.layout.mapmenu3_list);
			ListView listView = (ListView) dialog.findViewById(R.id.mapmenu3_list);
			List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
			
			for (int i = 0; i < name.length; i++)
			{
				name[i] = GameData.corporation.employee[Integer.parseInt(String
						.valueOf(vector.elementAt(i)))].name;
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("name", name[i]);
				list.add(map);
			}
			
			SimpleAdapter adapter = new SimpleAdapter(mContext, list, R.layout.mapmenu3_item, new String[]{"name"}, new int[]{R.id.mapmenu3_list_text});
			listView.setAdapter(adapter);
			listView.setOnItemClickListener(new OnItemClickListener()
			{

				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id)
				{
					Employee e = (Employee) GameData.corporation.employee[position];
					Connection.sendMessage(
							GameProtocol.CONNECTION_SEND_CREATE_PUBLICBUIDING,
							ConstructData.getCreatePublicBuilding(
									GameData.buildingName[canBuildIndex[p]],
									GameData.buildingId[canBuildIndex[p]],
									GameData.mapIds[GameData.mapIdIndex],
									(short) cityX, (short) cityY, (byte) 0,
									e.id));
					dialog.dismiss();
				}
			});
			
			/*Builder builder = new AlertDialog.Builder(
					CreateShopAndBuildActivity.this);
			
			builder.setItems(name, new DialogInterface.OnClickListener()
			{

				public void onClick(DialogInterface dialog, int index)
				{
					int idx = Integer.parseInt(String.valueOf(vector
							.elementAt(index)));
					Employee e = (Employee) GameData.corporation.employee[index];
					Connection.sendMessage(
							GameProtocol.CONNECTION_SEND_CREATE_PUBLICBUIDING,
							ConstructData.getCreatePublicBuilding(
									GameData.buildingName[canBuildIndex[p]],
									GameData.buildingId[canBuildIndex[p]],
									GameData.mapIds[GameData.mapIdIndex],
									(short) cityX, (short) cityY, (byte) 0,
									e.id));
					CreateShopAndBuildActivity.this.finish();

				}

			}

			).create().show();

		
*/
			ImageAdapter adappert = (ImageAdapter) gallery[1].getAdapter();

			adappert.notifyDataSetChanged(buildIndex);
		}
	}
}
