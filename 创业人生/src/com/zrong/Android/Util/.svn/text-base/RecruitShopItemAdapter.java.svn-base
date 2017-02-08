package com.zrong.Android.Util;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class RecruitShopItemAdapter extends BaseAdapter {

	private Context mContext;
	private ArrayList appList;
	private int layout;
	private String keyString[];
	private int[] valueViewID;
	private Holder holder;
	private AdapterView.OnItemClickListener listener;
	private LayoutInflater mInflater;

	public RecruitShopItemAdapter(Context context,
			ArrayList<HashMap<String, Object>> appList, int resource,
			String[] from, int[] to)

	{
		mContext = context;
		this.appList = appList;
		this.layout = resource;
		keyString = new String[from.length];
		valueViewID = new int[to.length];
		System.arraycopy(from, 0, keyString, 0, from.length);
		System.arraycopy(to, 0, valueViewID, 0, to.length);
		mInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return appList.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return appList.get(position);
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView != null) {
			holder = (Holder) convertView.getTag();
		} else {
			holder = new Holder();
			convertView = mInflater.inflate(layout, null);

			holder.image = (ImageView) convertView.findViewById(valueViewID[0]);
			holder.shopname = (TextView) convertView
					.findViewById(valueViewID[1]);
			holder.flow = (TextView) convertView.findViewById(valueViewID[2]);
			holder.companyname = (TextView) convertView
					.findViewById(valueViewID[3]);
			holder.button = (Button) convertView.findViewById(valueViewID[4]);

		}

		HashMap map = (HashMap) appList.get(position);

		holder.image.setBackgroundResource(Integer.parseInt(String.valueOf(map
				.get(keyString[0]))));
		holder.shopname.setText((String) map.get(keyString[1]));
		holder.flow.setText((String) map.get(keyString[2]));
		holder.companyname.setText((String) map.get(keyString[3]));

		holder.button.setBackgroundResource(Integer.parseInt(String.valueOf(map
				.get(keyString[4]))));
		final int p = position;
		holder.button.setOnClickListener(new OnClickListener() 
		{

			public void onClick(View arg0) 
			{
				listener.onItemClick(null, null, p, 0);
			}
		}
		);
		convertView.setTag(holder);
		return convertView;
	}

	private class Holder 
	{
		ImageView image;
		TextView shopname;
		TextView flow;
		TextView companyname;
		Button button;
	}

	public void setOnItemButtonClickListener(
			AdapterView.OnItemClickListener listener) {
		this.listener = listener;
	}

}
