package com.zrong.Android.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zrong.Android.activity.R;


public class ImageAdpter extends BaseAdapter {


	private Context mContext;
	private ArrayList appList;
	private int layout;
	private String keyString[];
	private int[] valueViewID;
	private List<Boolean> chkb =  new ArrayList<Boolean>();
	private Holder holder;
	private AdapterView.OnItemClickListener listener;
	private LayoutInflater mInflater;		        
    public ImageAdpter(Context context,ArrayList<HashMap<String, Object>> appList,int resource,String[] from, int[] to)
    {  	        	        
        mContext= context;
		this.appList = appList;
		this.layout = resource;
		keyString = new String[from.length];
		valueViewID = new int[to.length];
		System.arraycopy(from, 0, keyString, 0, from.length);
		System.arraycopy(to, 0, valueViewID, 0, to.length);
		mInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		chkb =  new ArrayList<Boolean>();
		chkb.clear();
		
		for(int i =0; i < getCount();i++)
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

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
			
			 
			 
	 public View getView(int position, View convertView, ViewGroup parent) { 
		   
				if(convertView != null)
				{
					holder = (Holder)convertView.getTag();
				}
				else
				{
					holder = new Holder();
					convertView = mInflater.inflate(layout, null);
					holder.image =(ImageView)convertView.findViewById(R.id.propagandalist_logo);
					holder.info =(TextView)convertView.findViewById(R.id.propagandalist_detail);
					
				}
				HashMap map = (HashMap)appList.get(position);
				
				Bitmap bitmap = BitmapUtil.getBitmap(Integer.parseInt(String.valueOf(map.get(keyString[0]))),0,(float)1);
				holder.image.setImageBitmap(bitmap);
				holder.info.setText((String)map.get(keyString[1]));
				convertView.setTag(holder);
				return convertView;
	 }
			
			private class Holder
			{   
				ImageView image;
				TextView info;
			}


}
