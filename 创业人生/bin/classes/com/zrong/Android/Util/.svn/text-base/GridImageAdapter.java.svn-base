package com.zrong.Android.Util;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.zrong.Android.activity.R;

public class GridImageAdapter extends BaseAdapter {
	private Context context;
	List<Map<String, Object>> data;
	private int layoutResId;
	private LayoutInflater inflater;

	public GridImageAdapter(Context context, List<Map<String, Object>> data,
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

	/*		 
public View getView(int position, View convertView, ViewGroup parent) {
		
		View view = convertView;
		if (convertView == null) {
			view = inflater.inflate(layoutResId, null);
		}
		ViewHolder holder = new ViewHolder();

		holder.grid_item_text = (TextView) view
				.findViewById(R.id.grid_item_text);
		holder.grid_item_icon = (ImageView) view
				.findViewById(R.id.grid_item_icon);

		int id = (Short) ((Map<String, Object>) data.get(position))
				.get("iconId");
		Log.i("Log", "id:"+id);

		if (id == 1443) 
		{
			id = 1376;
		}
		if (id == 1444) {
			id = 1377;
		}
		if(id == 1500)
		{
			id = 1427;
		}
		if(id == 1501)
		{
			id = 1426;
		}
	//	new ImageDownloadTask().execute(holder.grid_item_icon, id, 810);//º”‘ÿÕº∆¨
		holder.grid_item_text
				.setText(((Map<String, Object>) data.get(position)).get("text")
						.toString());
		view.setTag(holder);

	//	view.setLayoutParams(new GridView.LayoutParams(200, 100));
		view.setPadding(50, 10, 50, 5);
		return view;
	}

	class ViewHolder {
		TextView grid_item_text;
		ImageView grid_item_icon;
	}*/
	public View getView(int position, View convertView, ViewGroup parent)
	{
		ImageView imageView = null;
		int id = (Short) ((Map<String, Object>) data.get(position))
		.get("iconId");
Log.i("Log", "id:"+id);

if (id == 1443) 
{
	id = 1376;
}
if (id == 1444) {
	id = 1377;
}
if(id == 1500)
{
	id = 1427;
}
if(id == 1501)
{
	id = 1426;
}

		if(convertView == null)
		{
			imageView = new ImageView(context);
		}
		else
		{
			imageView = (ImageView)convertView;
		}
		 Bitmap bitmap = BitmapUtil.getBitmap(id,0,(float)1);
    	 
    	 if(bitmap == null)
	 {
		 bitmap = BitmapUtil.getBitmap(811,0,(float)1);
    	 }
    	 imageView.setImageBitmap(bitmap);
    	 imageView.setPadding(20, 10, 20, 5);
    	 return imageView;
	}

}
