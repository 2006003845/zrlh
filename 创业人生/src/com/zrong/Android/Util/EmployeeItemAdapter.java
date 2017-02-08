package com.zrong.Android.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zrong.Android.activity.EmployeeListActivity;
import com.zrong.Android.activity.R;
import com.zrong.Android.activity.ShopListActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class EmployeeItemAdapter extends BaseAdapter{

	private Context mContext;
	private ArrayList appList;
	private int layout;
	private String keyString[];
	private int[] valueViewID;
	private Holder holder;
	private AdapterView.OnItemClickListener listener;
	private LayoutInflater mInflater;
	
	private List<Boolean> chkb =  new ArrayList<Boolean>();

	
	public EmployeeItemAdapter(Context c, ArrayList<HashMap<String, Object>> appList, int resource, 
            String[] from, int[] to) 
	{
		mContext= c;
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
			holder.checkbox = (CheckBox)convertView.findViewById(R.id.employeelist_item_checkbox);
			holder.name = (TextView)convertView.findViewById(valueViewID[0]);
			holder.leve = (TextView)convertView.findViewById(valueViewID[1]);
			holder.contract = (TextView)convertView.findViewById(valueViewID[2]);
			holder.section = (TextView)convertView.findViewById(valueViewID[3]);
			holder.loyalty = (TextView)convertView.findViewById(valueViewID[4]);
//			holder.button = (Button)convertView.findViewById(valueViewID[5]);
		}
		
		HashMap map =(HashMap) appList.get(position);
		
		holder.name.setText((String)map.get(keyString[0]));
		holder.leve.setText((String)map.get(keyString[1]));
		holder.contract.setText((String)map.get(keyString[2]));
		holder.section.setText((String)map.get(keyString[3]));
		holder.loyalty.setText((String)map.get(keyString[4]));
		//holder.button.setBackgroundResource(Integer.parseInt(String.valueOf(map.get(keyString[5]))));
//		int resID = Integer.parseInt(String.valueOf(map.get(keyString[5])));
//		if(resID == 200){
//			holder.button.setVisibility(8);
//		}else{
//		holder.button.setBackgroundResource(resID);
//		}
//		final int p = position; 
//		holder.button.setOnClickListener(new OnClickListener()
//		{
//
//			public void onClick(View arg0)
//			{
//				listener.onItemClick(null, holder.button, p, 0);
//				
//			}
//			
//		}
//		);
		
//		
		final int p = position; 
		
		if(holder.checkbox != null)
		{
			holder.checkbox.setOnCheckedChangeListener(new OnCheckedChangeListener()
			{
				public void onCheckedChanged(CompoundButton buttonView, boolean ischecked) 
				{
					 if(ischecked)
					 {
						 chkb.set(p, true);
						
					 }
					 else
					 {
						 chkb.set(p, false);
					 }
					 
					 if(mContext instanceof EmployeeListActivity)
						{
							((EmployeeListActivity)mContext).checkButton();
						}
				}
			}
			);
			holder.checkbox.setChecked(chkb.get(position));
		}
		
		
		
		convertView.setTag(holder);
		return convertView;
	}
	
	private class Holder
	{
		CheckBox checkbox; 
		TextView name;
		TextView leve;
		TextView contract;
		TextView section;
		TextView loyalty;
	}
	
	public void setOnItemButtonClickListener( AdapterView.OnItemClickListener listener)
	{
		this.listener= listener;
	}
	
	public void setCheck(int p,boolean ischeck)
	{
		chkb.set(p, ischeck);
	}
	
	public boolean isCheck(int p)
	{
		return chkb.get(p);
	}

}
