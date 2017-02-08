/**
 * 
 */
package com.zrong.Android.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import com.zrong.Android.activity.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SimpleAdapter;

/**
 *<p>Titile:CheckedItemAdapter</p>
 *<p>Description:使用组合关系,内部含有一个SimpleAdapter,实际工作的是SimpleAdapter </p>
 *<p>Copyright:Copyright(c)2010</p>
 *<p>Company:zrong</p>
 * @author yangzheng
 * @version 1.0
 * @date 2012-2-23
 */
public class CheckedItemAdapter extends BaseAdapter {
	SimpleAdapter adapter = null;
	
	private List<Boolean> chkb = new ArrayList<Boolean>();
	
	private int select = 0;
	/**
	 * 
	 */
	public CheckedItemAdapter(Context context,
			ArrayList<HashMap<String, Object>> appList, int resource,
			String[] from, int[] to) 
	{
		// TODO Auto-generated constructor stub
		adapter = new SimpleAdapter(context,appList,resource,from,to);
		chkb = new ArrayList<Boolean>();
		chkb.clear();
		
		for (int i = 0; i < getCount(); i++) 
		{
			chkb.add(false);
		}
	}
	
	public void clear()
	{
		chkb.clear();
		
		for (int i = 0; i < getCount(); i++) 
		{
			chkb.add(false);
		}
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getCount()
	 */
	
	public int getCount() {
		// TODO Auto-generated method stub
		return adapter.getCount();
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItem(int)
	 */
	
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return adapter.getItem(position);
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItemId(int)
	 */
	
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return adapter.getItemId(position);
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	
	public View getView(int position, View convertView, ViewGroup parent) 
	{
		View v = adapter.getView(position,convertView,parent);
		
		if(v != null)
		{
			if(isCheck(position))
			{
				v.setBackgroundResource(R.drawable.shoplist_back_pressed);
			}
			else
			{
				v.setBackgroundResource(R.drawable.shoplist_selector);
			}
		}
		return adapter.getView(position,convertView,parent);
	}
	
	public void setCheck(int p, boolean ischeck) 
	{
		chkb.set(select, false);
		chkb.set(p, ischeck);
		select = p;
		this.notifyDataSetChanged();
	}

	public boolean isCheck(int p) 
	{
		return chkb.get(p);
	}
	
	
	public int[] getCheck()
	{
		Vector v = new Vector();
		
		for(int i =0; i < chkb.size(); i++  )
		{
			if(isCheck(i))
			{
				v.addElement(i);
			}
		}
		
		int value[] = new int[v.size()];
		
		for(int i =0; i < value.length; i++)
		{
			value[i] =Integer.parseInt(String.valueOf(v.elementAt(i)));
		}
		
		return value;
	}
	

}
