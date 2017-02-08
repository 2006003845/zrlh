package com.zrong.Android.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zrong.Android.activity.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class chatAdapter extends BaseAdapter
{
	public Context mContext;
	public ArrayList<HashMap<String, String>> mArraylist;
	public int layoutId;
	public String keyString[];
	public int valueId[];
	public List<Boolean> isCheck = new ArrayList<Boolean>();
	private LayoutInflater mInflater;

	private AdapterView.OnItemClickListener listener;
	
	public chatAdapter(Context c,ArrayList<HashMap<String, String>> arrylist,int layid,String from[],int to[])
	{
		this.mContext = c;
		this.mArraylist = arrylist;
		this.layoutId = layid;
		this.keyString = from;
		this.valueId = to;

		mInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		isCheck.clear();
		for(int i=0;i<getCount();i++)
		{
			isCheck.add(false);
		}
	}
	
	public int getCount()
	{
		// TODO Auto-generated method stub
		return mArraylist.size();
	}

	public Object getItem(int position)
	{
		// TODO Auto-generated method stub
		return mArraylist.get(position);
	}

	public long getItemId(int position)
	{
		// TODO Auto-generated method stub
		return position;
	}
	 /**
     * @param position      position就是位置从0开始
     * @param convertView   convertView是Spinner,ListView中每一项要显示的view
     * @param parent        parent就是父窗体了，也就是Spinner,ListView,GridView了
     * @return              通常return 的view也就是convertView
     * 绘制的内容均在此实现
     */
	public View getView(int position, View convertView, ViewGroup parent)
	{
		// TODO Auto-generated method stub
		convertView = mInflater.inflate(layoutId, null);
		HashMap map  = (HashMap) mArraylist.get(position);
		/**标题*/
		TextView title = new TextView(parent.getContext());
		title = (TextView)convertView.findViewById(valueId[0]);
		title.setText((String)map.get(keyString[0]));
		/**内容*/
		TextView text = new TextView(parent.getContext());
		text = (TextView)convertView.findViewById(valueId[1]); 
		text.setText((String)map.get(keyString[1]));
		
		if(isCheck.size()>position)    
		if(isCheck(position))
		{
			convertView.setBackgroundResource(R.drawable.shoplist_back_pressed);
		}
		else
		{
			convertView.setBackgroundResource(R.drawable.shoplist_back);
		}
		
		return convertView;
	}

	public void setOnItemButtonClickListener( AdapterView.OnItemClickListener listener)
	{
		this.listener= listener;
	}
	public boolean isCheck(int p)
	{
		return isCheck.get(p);
	}
	
	public void setCheck(int p,boolean b,boolean singleSelect)
	{

		
		if(b && singleSelect)
		{
			isCheck.clear();
			for(int i=0;i<getCount();i++)
			{ 
				isCheck.add(false);
			}
		}
		this.isCheck.set(p, b);
	}
	public void addCheck(boolean value,boolean singleSelect)
	{
//		Log.d("zzx",""+getCount());
		if(value && singleSelect)
		{
			isCheck.clear();
			for(int i=0;i<getCount();i++)
			{ 
				isCheck.add(false);
			}
		}
		isCheck.add(getCount()-1, value); 
	}
	
	/**
	 * 适用于单选获取选中项
	 * */
	public int getSelectIndex()
	{
		for(int i=0;i<isCheck.size();i++)
		{
			if(isCheck.get(i))
			{
				return i;
			}
		}
		return -1;
	}

	/**
	 * 适用于多选获取选中项
	 * */
	public int[] getSelectIndexs()
	{
		int temp[] = new int[isCheck.size()]; 
		int index = 0;
		for(int i=0;i<isCheck.size();i++)
		{
			if(isCheck.get(i))
			{
				temp[index++] = i;
			}
		}
		int temp2[] = new int[index];
		System.arraycopy(temp, 0, temp2, 0, index);
		temp = null;
		return temp2;
	}
}
