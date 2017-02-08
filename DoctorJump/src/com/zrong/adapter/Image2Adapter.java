package com.zrong.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;

public class Image2Adapter extends BaseAdapter {

	private Context mContext;
	private int mGalleryItemBackground;
	private int[] imgs;

	public Image2Adapter(Context context, int[] imgs) {
		this.mContext = context;
		this.imgs = imgs;
	}

	// 表示Adapter封裝數據的條數
	@Override
	public int getCount() {
		return imgs.length;
	}

	@Override
	public Object getItem(int position) {
		return imgs[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView view = new ImageView(mContext);
		view.setImageResource(imgs[position]);
		// 设置ImageView的伸缩规格，用了自带的属性值
		view.setScaleType(ImageView.ScaleType.FIT_CENTER);
		view.setAdjustViewBounds(true);
		view.setLayoutParams(new Gallery.LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT));
		view.setBackgroundResource(mGalleryItemBackground);
		return view;
	}

	public int getmGalleryItemBackground() {
		return mGalleryItemBackground;
	}

	public void setmGalleryItemBackground(int mGalleryItemBackground) {
		this.mGalleryItemBackground = mGalleryItemBackground;
	}

}
