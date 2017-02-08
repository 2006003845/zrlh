package com.zrong.Android.Util;

import java.util.List;
import java.util.Map;


import com.zrong.Android.activity.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class GridAdapter extends BaseAdapter{
	private Context context;
	List<Map<String, Object>> data;
	private int layoutResId;
	private LayoutInflater inflater;

	public GridAdapter(Context context, List<Map<String, Object>> data,
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
 
	public long getItemId(int position) {
		return position;
	}
 
	public View getView(int position, View convertView, ViewGroup parent) {
		Log.i("Log", "2--getView");
		View view = convertView;
		if (convertView == null) {
			view = inflater.inflate(layoutResId, null);
		}
		ViewHolder holder = new ViewHolder();
		
		holder.grid_item_icon = (ImageView) view
				.findViewById(R.id.grid_icon_item);

		int id = (Short) ((Map<String, Object>) data.get(position))
				.get("iconId");
		Log.i("Log", "id:"+id);
		  ImageView imageView = null;
		  imageView = (ImageView)holder.grid_item_icon;
		  imageView.setImageBitmap(BitmapUtil.getBitmap(id,0,(float)1));
		//new ImageDownloadTask().execute(holder.grid_item_icon, id, 810);//º”‘ÿÕº∆¨

		view.setTag(holder);

		view.setLayoutParams(new GridView.LayoutParams(200, 100));

		return view;
	}

	class ViewHolder {
		ImageView grid_item_icon;
	}

}
