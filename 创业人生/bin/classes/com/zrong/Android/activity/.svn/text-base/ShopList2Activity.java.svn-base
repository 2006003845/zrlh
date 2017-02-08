package com.zrong.Android.activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.zrong.Android.Util.ImageAdapter;
import com.zrong.Android.element.Shop;
import com.zrong.Android.game.GameData;

public class ShopList2Activity extends GameActivity
{
	public static ShopList2Activity mContext;
	public ViewFlipper flipper;
	public buttonListener listener;
	public itemListener itemlistener;
	public Button button[],button_exit[];
	public Gallery gallery[];
	public TextView name[];
	private int currentIndex;
	
	public ShopList2Activity()
	{
		mContext = this;
		listener = new buttonListener();
		itemlistener = new itemListener();
	}
	
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shoplistshow);
		init();
	}
	
	public void init()
	{
		button = new Button[2];
		button[0] = (Button)findViewById(R.id.create_shop);
		button[1] = (Button)findViewById(R.id.create_building);
		
		button_exit = new Button[2];
		button_exit[0] = (Button)findViewById(R.id.back1);
		button_exit[1] = (Button)findViewById(R.id.back2);
		
		for(int i=0;i<button.length;i++)
		{
			button[i].setOnClickListener(listener);
			button_exit[i].setOnClickListener(listener);
		}
		
		name = new TextView[2];
		name[0] = (TextView)findViewById(R.id.info1);
		name[1] = (TextView)findViewById(R.id.info2);
		
		gallery = new Gallery[2];
		gallery[0] = (Gallery)findViewById(R.id.gallery1);
		gallery[0].setOnItemSelectedListener(itemlistener);
		gallery[1] = (Gallery)findViewById(R.id.gallery2);
		gallery[1].setOnItemSelectedListener(itemlistener);
		
		flipper = (ViewFlipper)findViewById(R.id.flipper);
		
		setSelect(0);
	}
	
	public void initShopView()
	{ 
		ImageAdapter adapter = new ImageAdapter(this, ImageAdapter.GALLERY);
		final Shop shop[] = GameData.corporation.shop;
		int resid[] = new int[shop.length];
		for(int i=0;i<shop.length;i++)
		{
			resid[i] = GameData.getUIResId(shop[i].trade_id);
		}
		adapter.setImageIdArray(resid);
		gallery[0].setAdapter(adapter);
		gallery[0].setOnItemClickListener(new OnItemClickListener()
		{

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id)
			{
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				bundle.putInt("position", position);
				intent.putExtras(bundle);
				MainActivity.mContext.Activitychange(shopInfo2Activity.class, intent);
			}
		});
		
		
	}

	public void finish()
	{
		mContext = null;
		super.finish();
	}

	public GameActivity getGameContext()
	{
		return mContext;
	}
	
	/** 设置选中项 */
	public void setSelect(int index)
	{

		currentIndex = index;
		if (index == 0)
		{
			button[0].setBackgroundResource(R.drawable.shop_pressed);
			button[1].setBackgroundResource(R.drawable.building);
			initShopView();
		} else if (index == 1)
		{

			button[0].setBackgroundResource(R.drawable.shop);
			button[1].setBackgroundResource(R.drawable.building_pressed);
		}
		flipper.setDisplayedChild(index); 

	}
	
	public class buttonListener implements OnClickListener
	{
		public void onClick(View v)
		{
			int id = v.getId();
			switch(id)
			{
			case R.id.create_shop:
				setSelect(0);
				break;
			case R.id.create_building:
				setSelect(1);
				break;
			case R.id.back1:
			case R.id.back2:
				finish();
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
//				updataGalleryBuilding(parent, view, position, id);
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
		String name = GameData.corporation.shop[position].name;
		Gallery gallery = (Gallery) parent;

		ImageAdapter adappert = (ImageAdapter) gallery.getAdapter();
		this.name[0].setText(name);

		adappert.notifyDataSetChanged(position);
	}
}
