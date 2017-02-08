package com.zrong.Android.Util;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ImageView;

public class GridsimpleImageAdapter extends BaseAdapter{
	DisplayMetrics dm;
	  static int w1;
	  static int h1;
	private int selectItem;
	// 定义Context
	private Context		mContext;
	// 定义整型数组 即图片源
	private int[]	mImageIds = null; 

	public GridsimpleImageAdapter(Context c)
	{
		mContext = c;
	}
//	public GridsimpleImageAdapter(Context mContext,int w1,int h1){
//		this.mContext=mContext;
//		this.w1=w1;
//		this.h1=h1;
//	}

	// 获取图片的个数
	public int getCount()
	{
		return mImageIds.length;
	}
	
	public void setImageIdArray(int [] array)
	{
		if(array != null)
		{
			mImageIds = new int[array.length];
			
			System.arraycopy(array, 0, mImageIds, 0, array.length);
		}
	}

	// 获取图片的位置
	public Object getItem(int position)
	{
		return position;
	}


	// 获取图片ID
	public long getItemId(int position)
	{
		return position;
	}
//	public void setSelectItem(int selectItem) {
//		
//		if (this.selectItem != selectItem) {                
//		this.selectItem = selectItem;
//		notifyDataSetChanged();         
//		}
//	}

	public View getView(int position, View convertView, ViewGroup parent)
	{
		ImageView imageView;
	/*	if (convertView == null)
		{
			// 给ImageView设置资源
			imageView = new ImageView(mContext);*/
			// 设置布局 图片120×120显示
		//	imageView.setLayoutParams(new GridView.LayoutParams(120, 120));
			// 设置显示比例类型
			//imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
			
			
	/*		Bitmap bitmap = BitmapUtil.getBitmap(mImageIds[position],0,(float)1);
	    	 
				if(bitmap == null)
				{
					bitmap = BitmapUtil.getBitmap(811,0,(float)1);
				}
			imageView.setImageBitmap(bitmap);
			return imageView;*/
			//new ImageDownloadTask().execute(imageView,mImageIds[position],810,0);
	/*	}
		else
		{
			imageView = (ImageView) convertView;
		}
		imageView.setPadding(50, 10, 50, 5);
		new ImageDownloadTask().execute(imageView,mImageIds[position],810,0);
		return imageView;*/
		if(convertView == null)
		{
			imageView = new ImageView(mContext);
//			if(selectItem==position){
//				imageView.setLayoutParams(new Gallery.LayoutParams(w1/2,(int) (h1/2.5)));
//			}
		}
		else
		{
			imageView = (ImageView)convertView;
		}
		 Bitmap bitmap = BitmapUtil.getBitmap(mImageIds[position],0,(float)1);
    	 
    	 if(bitmap == null)
	 {
		 bitmap = BitmapUtil.getBitmap(811,0,(float)1);
    	 }
    	 imageView.setImageBitmap(bitmap);
    	 imageView.setPadding(50, 10, 50, 5);
    	 return imageView;
	}
	}

