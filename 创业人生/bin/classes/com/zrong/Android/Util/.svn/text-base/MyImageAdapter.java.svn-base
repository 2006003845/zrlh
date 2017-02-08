package com.zrong.Android.Util;

import com.zrong.Android.activity.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class MyImageAdapter extends BaseAdapter
{
	// 定义Context
	private Context		mContext;
	// 定义整型数组 即图片源
	private int[]	mImageIds	= 
	{ 
			R.drawable.venture_story,
			R.drawable.venture_evaluation,
			R.drawable.venture_activity,
			R.drawable.policy_information,
			R.drawable.vocational_skills,
			R.drawable.expert_consultation,
			R.drawable.authority_issued,
			R.drawable.doctor_information
	};

	public MyImageAdapter(Context c )
	{
		mContext = c;
		
		
	}
	
	public MyImageAdapter(Context c,int ids[])
	{
		mContext = c;
		mImageIds = new int[ids.length];
		System.arraycopy(ids, 0, mImageIds, 0, ids.length);
	}


	

	// 获取图片的个数
	public int getCount()
	{
		return mImageIds.length;
	}

	// 获取图片在库中的位置
	public Object getItem(int position)
	{
		return position;
	}


	// 获取图片ID
	public long getItemId(int position)
	{
		return position;
	}


	public View getView(int position, View convertView, ViewGroup parent)
	{
		ImageView imageView;
		if (convertView == null)
		{
			// 给ImageView设置资源
			imageView = new ImageView(mContext);
			// 设置布局 图片120×120显示
		//	imageView.setLayoutParams(new GridView.LayoutParams(120, 120));
			// 设置显示比例类型
		//	imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
		}
		else
		{
			imageView = (ImageView) convertView;
		}
		imageView.setPadding(50, 10, 50, 5);
		imageView.setImageResource(mImageIds[position]);
		return imageView;
	}
	
	
}
