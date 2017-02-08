package com.zrong.Android.activity;

import java.util.ArrayList;
import java.util.HashMap;

import com.zrong.Android.Util.ShopItemAdapter;
import com.zrong.Android.element.Shop;
import com.zrong.Android.game.Connection;
import com.zrong.Android.game.ConstructData;
import com.zrong.Android.game.GameData;
import com.zrong.Android.online.network.GameProtocol;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

public class SimpleShopListActivity extends GameActivity{
	public static SimpleShopListActivity mContext = null;
	 
	public GameActivity getGameContext() 
	{
		return this;
	}
	private String nametag = MainActivity.resources.getString(R.string.shoplist_nametag);
	private String leveltag = MainActivity.resources.getString(R.string.shoplist_leveltag);
	private String typetag = MainActivity.resources.getString(R.string.shoplist_typetag);
	private String employeetag = MainActivity.resources.getString(R.string.shoplist_employeetag);
	private String stocktag = MainActivity.resources.getString(R.string.shoplist_stocktag);
	private String flowtag = MainActivity.resources.getString(R.string.shoplist_flowtag);
	
	
	public static final int TYPE_DOCTOR = 1;
	public static final int TYPE_SOCIAL = 2;
	
	private Shop shop[] = null;
    
	/** 
	 * 从哪来的
	 */
	private int type;
	
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		mContext = this;
		
		type =getIntent().getIntExtra("type", -1);
		
	
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.simple_shoplist);
		
		shop = getShop(type);;
		
		Button cancle = (Button) this.findViewById(R.id.simple_shoplist_button_cancel);

		cancle.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View arg0) 
			{
				SimpleShopListActivity.this.finish();
			}
		});
		
		ListView shopList = (ListView) this.findViewById(R.id.simple_shoplist_listview);

		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		
		for (int i = 0; i < shop.length; i++) 
		{
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("name", nametag + shop[i].name);
			map.put("level", leveltag + shop[i].level);
			map.put("type",
					typetag + GameData.getShopScale(shop[i].scale, ""));
			map.put("stock", stocktag + shop[i].goodsNum+"/"+shop[i].maxGoodsNum);
			map.put("flow", flowtag + shop[i].flowGain);
			
			if(shop[i].isRecruiting())
			{
				map.put("recruiting", View.VISIBLE);
			}
			else
			{
				map.put("recruiting", View.GONE);
			}
			
			
			if (shop[i].isRecruited()) 
			{
				map.put("recruited", View.VISIBLE);
			} else 
			{
				map.put("recruited", View.GONE);
			}
			list.add(map);
		}

		ShopItemAdapter adpter = new ShopItemAdapter(SimpleShopListActivity.this,
			list, R.layout.pm_shoplist_item, new String[] { "name", "level",
					"type", "stock", "flow", "employee", 
					"recruiting", "recruited" }, new int[] {
					R.id.shoplist_item_name, R.id.shoplist_item_level,
					R.id.shoplist_item_type, R.id.shoplist_item_stock,
					R.id.shoplist_item_flow, R.id.shoplist_item_employee,
					
					R.id.shoplist_item_recruiting,
					R.id.shoplist_item_recruted });
		shopList.setAdapter(adpter);
		
		shopList.setOnItemClickListener(new OnItemClickListener()
		{
			 
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				
				excute(type,position);
				SimpleShopListActivity.this.finish();
			}
			
		}
		);
	}
	
	/**
	 * 
	 * <p>Description:根据类型返回店铺列表</p>
	 * @author YangZheng
	 * @param type
	 * @return
	 */
	public Shop[] getShop(int type)
	{
		if(type == TYPE_DOCTOR)
		{
			return GameData.corporation.shop;
		}
		else if(type ==TYPE_SOCIAL)
		{
			return GameData.otherCorporation.shop;
		}
		return null;	
	}
	
	public void excute(int type,int position)
	{
		if(type == TYPE_DOCTOR)
		{
			Connection.sendMessage(GameProtocol.DoctorTrust, ConstructData.getDoctorTrustData((byte)GameData.trustType[DoctorCustodyActivity.ID], (short)GameData.trustId[DoctorCustodyActivity.ID], (short)0, (byte)1, (byte)1, (byte)1,new long[]{shop[position].id}));
		}
		else if(type ==TYPE_SOCIAL)
		{
			Connection.sendMessage(
					GameProtocol.CONNECTION_Shop_UnRecruit_Req,
					ConstructData.getShop_UnRecruit_Req(
							(byte)1,
							GameData.player.id, shop[position].buildingId));
			
			
		}
	}
	
	public void finish() {
		
		mContext = null;
		super.finish();
	}

}
