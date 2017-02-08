package com.zrong.Android.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zrong.Android.activity.R;
import com.zrong.Android.activity.ShopListActivity;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class ShopItemAdapter extends BaseAdapter {

	private Context mContext;
	private ArrayList appList;
	private int layout;
	private String keyString[];
	private int[] valueViewID;
	private Holder holder;
	private AdapterView.OnItemClickListener listener;
	private LayoutInflater mInflater;

	private List<Boolean> chkb = new ArrayList<Boolean>();

	public ShopItemAdapter(Context context,
			ArrayList<HashMap<String, Object>> appList, int resource,
			String[] from, int[] to)

	{
		if(this.appList !=null){
			appList.clear();
			appList = null;
		}
		mContext = context;
		this.appList = appList;
		this.layout = resource;
		keyString = new String[from.length];
		valueViewID = new int[to.length];
		System.arraycopy(from, 0, keyString, 0, from.length);
		System.arraycopy(to, 0, valueViewID, 0, to.length);
		mInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		chkb = new ArrayList<Boolean>();
		chkb.clear();
		
		for (int i = 0; i < getCount(); i++) 
		{
			chkb.add(false);
		}
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return appList.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return appList.get(position);
	}

	public long getItemId(int position) 
	{
		return position;
	}

	@SuppressWarnings("unchecked")
	public View getView(int position,  View convertView, ViewGroup parent) {
		if (convertView != null) 
		{
			holder = (Holder) convertView.getTag();
		} 
		else 
		{
			holder = new Holder();// Ñ¡ÖÐÏî
			convertView = mInflater.inflate(layout, null);
			holder.checkbox = (CheckBox) convertView
					.findViewById(R.id.shoplist_item_checkbox);
			holder.name = (TextView) convertView.findViewById(valueViewID[0]);
			holder.level = (TextView) convertView.findViewById(valueViewID[1]);
			holder.type = (TextView) convertView.findViewById(valueViewID[2]);
			holder.stock = (TextView) convertView.findViewById(valueViewID[3]);
			holder.flow = (TextView) convertView.findViewById(valueViewID[4]);
			holder.employee = (TextView) convertView
					.findViewById(valueViewID[5]);
//			holder.button = (Button) convertView.findViewById(valueViewID[6]);
			holder.image1 = (ImageView) convertView
					.findViewById(valueViewID[6]);
			holder.image2 = (ImageView) convertView
					.findViewById(valueViewID[7]);

		}

		HashMap<String, Object> map = (HashMap<String, Object>) appList
				.get(position);
		if (map.isEmpty()) 
		{

		}
		else 
		{
			holder.name.setText((String) map.get(keyString[0]));
			holder.level.setText((String) map.get(keyString[1]));
			holder.type.setText((String) map.get(keyString[2]));
			holder.stock.setText((String) map.get(keyString[3]));
			holder.flow.setText((String) map.get(keyString[4]));
			holder.employee.setText((String) map.get(keyString[5]));
//			int resID = Integer.parseInt(map.get(keyString[6]).toString());
//			if(resID == 200)
//			{
//				holder.button.setVisibility(8);
//			}else
//			{
//				holder.button.setBackgroundResource(resID);
//			}
//			
//			holder.button.setOnClickListener(new OnClickListener() 
//			{
//				public void onClick(View arg0) 
//				{
//					listener.onItemClick(null, null, p, 0);
//				}
//
//			});
			final int p = position;
			
			
			if(holder.checkbox != null)
			{
				holder.checkbox.setOnCheckedChangeListener(new OnCheckedChangeListener() 
				{
							public void onCheckedChanged(CompoundButton buttonView,
									boolean ischecked) 
							{
								if (ischecked)
								{
									chkb.set(p, true);
								} 
								else 
								{
									chkb.set(p, false);
								}
								
								if(mContext instanceof ShopListActivity)
								{
									((ShopListActivity)mContext).checkButton();
								}
							}
						});
				
				holder.checkbox.setChecked(chkb.get(position));
				
				if(chkb.get(position)) 
				{
					convertView.setBackgroundResource(R.drawable.shoplist_back_pressed);
				}
				else
				{
					convertView.setBackgroundResource(R.drawable.shoplist_selector);
				}
			}
			

			if (map.get(keyString[6]) != null && map.get(keyString[7]) != null) 
			{
				if(holder.image1 != null)
				{
					holder.image1.setVisibility(Integer.parseInt(String.valueOf(map
							.get(keyString[6]))));
				}
				
				if(holder.image2 != null)
				{
					holder.image2.setVisibility(Integer.parseInt(String.valueOf(map
							.get(keyString[7]))));
				}
				
			}
		}
		convertView.setTag(holder);
		return convertView;
	}

	private class Holder 
	{
		CheckBox checkbox;
		TextView name;
		TextView level;
		TextView type;
		TextView stock;
		TextView flow;
		TextView employee;
		
		ImageView image1;
		ImageView image2;

	}

	

	public void setCheck(int p, boolean ischeck) 
	{
		
		chkb.set(p, ischeck);
		
		
	}

	public boolean isCheck(int p) {
		return chkb.get(p);
	}
	
	

}
