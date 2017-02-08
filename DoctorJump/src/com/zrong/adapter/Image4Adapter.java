package com.zrong.adapter;

import java.io.InputStream;
import java.util.ArrayList;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.zrong.entity.Stage;
import com.zrong.ui.R;
import com.zrong.utils.BitmapUtils;
import com.zrong.view.BloodView;

public class Image4Adapter extends BaseAdapter {
	private int mGalleryItemBackground;
	private Context mContext;
	ArrayList<Stage> tages;
	private int layoutId;
	private LayoutInflater inflater;
	private Bitmap[] bms;

	private ViewFlipper mViewFlipper;

	private Bitmap getBitmap() {
		DisplayMetrics dm = new DisplayMetrics();
		dm = mContext.getResources().getDisplayMetrics();
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inDensity = dm.densityDpi;

		return BitmapFactory.decodeResource(mContext.getResources(),
				R.drawable.stage_item_bg, options);
	}

	public Image4Adapter(Context context, ArrayList<Stage> stages, int layoutId) {
		mContext = context;
		this.tages = stages;
		this.layoutId = layoutId;
		Bitmap bb = getBitmap();
		bms = BitmapUtils.cutBitmap(bb, bb.getWidth() / 3, bb.getHeight());
		TypedArray typedArray = mContext
				.obtainStyledAttributes(R.styleable.Gallery);
		mGalleryItemBackground = typedArray.getResourceId(
				R.styleable.Gallery_android_galleryItemBackground, 0);
		inflater = (LayoutInflater) mContext
				.getSystemService(android.content.Context.LAYOUT_INFLATER_SERVICE);
	}

	public int getCount() {
		return tages.size();
	}

	public Object getItem(int position) {
		return tages.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if (view == null) {
			view = inflater.inflate(layoutId, null);
		}

		mViewFlipper = (ViewFlipper) view.findViewById(R.id.ViewFlipper);

		view.setBackgroundDrawable(new BitmapDrawable(bms[position % 3]));
		ImageView item_img = (ImageView) view.findViewById(R.id.stage_item_img);
		if (tages.get(position).isPassed == Stage.UNPASSED && position > 0
				&& tages.get(position - 1).isPassed == Stage.UNPASSED) {
			mViewFlipper.setDisplayedChild(1);
		} else {
			mViewFlipper.setDisplayedChild(0);
			// // 第2点改进，通过取余来循环取得resIds数组中的图像资源ID
			Resources re = mContext.getResources();
			InputStream is = re.openRawResource(tages.get(position).imgId);
			BitmapDrawable mapdraw = new BitmapDrawable(is);
			Bitmap bitmap = mapdraw.getBitmap();
			item_img.setImageBitmap(bitmap);
			// set star num
			BloodView item_star = (BloodView) view
					.findViewById(R.id.stage_item_star);
			item_star.setImageId(R.drawable.img_stage_star,
					R.drawable.img_stage_star_n);
			item_star.setCount(3);
			item_star.setBloodNum(tages.get(position).starNum);
		}
		view.setLayoutParams(new Gallery.LayoutParams(80, 80));
		// imageView.setBackgroundResource(mGalleryItemBackground);
		return view;
	}
}