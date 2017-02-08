package com.zrong.Android.Util;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;


import com.zrong.Android.View.SpriteView;
import com.zrong.Android.activity.R;

public class GridSpriteAdapter extends BaseAdapter{

	private Context context;
	List<Map<String, Object>> data;
	private int layoutResId;
	private LayoutInflater inflater;

	public GridSpriteAdapter(Context context, List<Map<String, Object>> data,
			int layoutResId) {
		Log.i("Log", "≥ı ºªØ2");
		this.context = context;
		this.data = data;
		this.layoutResId = layoutResId;

		inflater = (LayoutInflater) context
				.getSystemService(android.content.Context.LAYOUT_INFLATER_SERVICE);

	}

	private void initData() {

	}

	
	public int getCount() {
		return data.size();
	}

	
	public Object getItem(int position) {
		return data.get(position);
	}

	
	public long getItemId(int position)
	{
		return position;
	}
	
	
	Vector v = new Vector();
	
	public View getView(int position, View convertView, ViewGroup parent) 
	{
		convertView = null;
		View view = null;
		ViewHolder holder = null;
		int id;
		String isUnlock;
		
		if(position < v.size())
		{
			convertView = (View)v.elementAt(position);
		}
		if (convertView == null) 
		{
			id = (Integer) ((Map<String, Object>) data.get(position)).get("spriteId");
			isUnlock = (String) ((Map<String, Object>) data.get(position)).get("image");
			view = inflater.inflate(layoutResId, null);
			holder = new ViewHolder();

			holder.grid_item_imageview = (ImageView) view
					.findViewById(R.id.grid_item_imageview);
			holder.grid_item_sprite = (SpriteView) view
					.findViewById(R.id.grid_item_sprite);
			holder.grid_item_sprite.setId(id);
			holder.grid_item_sprite.setSeries(0);
			holder.index = position;
			view.setTag(holder);
			view.setLayoutParams(new GridView.LayoutParams(200, 100));
			v.addElement(view);
		}
		else
		{
			view = convertView;
			holder = (ViewHolder)view.getTag();
			id = (Integer) ((Map<String, Object>) data.get(holder.index)).get("spriteId");
			isUnlock = (String) ((Map<String, Object>) data.get(holder.index)).get("image");
			
		}
//		if(position != 0)
//		{
//			Log.i("yz", "postion = "+position+",isUnlock=true"+"index ="+holder.index);
//		}
		if(isUnlock.equals("unlock"))
		{
			holder.grid_item_imageview.setVisibility(8);
//			if(position != 0)
//			{
//				Log.i("yz", "postion = "+position+",isUnlock=true");
//			}
		}
		else
		{
//			if(position != 0)
//			{
//				Log.i("yz", "postion = "+position+",isUnlock=false");
//			}
		}
		return view;
	}

	class ViewHolder {
		ImageView grid_item_imageview;
		SpriteView grid_item_sprite;
		int index;
	}



}
