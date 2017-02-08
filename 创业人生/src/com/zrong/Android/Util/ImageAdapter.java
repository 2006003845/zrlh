package com.zrong.Android.Util;

import com.zrong.Android.View.SpriteView;
import com.zrong.Android.activity.CreateShopActivity;
import com.zrong.Android.activity.R;
import com.zrong.Android.game.GameData;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter
{
	private int type = 0;

	public static final int STOREGRID = 0;

	public static final int GALLERY = 1;

	public static final int SPRITEGALLERY = 2;

	// 定义Context
	private Context mContext;
	// 定义整型数组 即图片源
	private int[] mImageIds;

	// {
	// R.drawable.img1,
	// R.drawable.img2,
	// R.drawable.img3,
	// R.drawable.img4,
	// R.drawable.img5,
	// R.drawable.img6,
	// R.drawable.img7,
	// R.drawable.img8,
	// };

	public void setImageIdArray(int array[])
	{
		if (array != null)
		{
			mImageIds = new int[array.length];

			System.arraycopy(array, 0, mImageIds, 0, array.length);
		}
	}

	// 声明 ImageAdapter
	public ImageAdapter(Context c, int type)
	{
		mContext = c;
		this.type = type;
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

	/**
	 * 选中项的索引
	 */
	private int select;

	public void notifyDataSetChanged(int albumId)
	{
		select = albumId;
		super.notifyDataSetChanged();

	}

	public View getView(int position, View convertView, ViewGroup parent)
	{
		int index = position % mImageIds.length;

		ImageView imageView = null;

		if (type == GALLERY)
		{

			if (convertView == null)
			{
				imageView = new ImageView(mContext);
			} else
			{
				imageView = (ImageView) convertView;
			}

			if (select == position)
			{
				Bitmap bitmap = BitmapUtil.getBitmap(mImageIds[index], 0,
						(float) 1);

				if (bitmap == null)
				{
					bitmap = BitmapUtil.getBitmap(811, 0, (float) 1);
				}

				Bitmap bitmapSelectIcon = BitmapFactory.decodeResource(
						mContext.getResources(), R.drawable.selectedicon);

				imageView.setImageBitmap(BitmapUtil.createSelectImage(bitmap,
						bitmapSelectIcon));
				// new
				// SelectImageDownLoadTask().execute(imageView,bitmapSelectIcon,mImageIds[index],811);

				imageView.setLayoutParams(new Gallery.LayoutParams(200, 200));

			} else
			{
				if (GameData.shopTemplate_hidded[index
						* GameData.shop_scale_size
						+ CreateShopActivity.radioSelect] == 1)
				{
					for (int i = 0; i < GameData.hiddedShopId.length; i++)
					{
						if (GameData.shopTemplate_id[index
								* GameData.shop_scale_size
								+ CreateShopActivity.radioSelect] == GameData.shopTemplate_id[(int) GameData.hiddedShopId[i][0]])
						{
							int k = i;
							if (GameData.hiddedShopId[k][1] == 1)
							{
								Bitmap bitmap = BitmapUtil.getBitmap(
										mImageIds[index], 0, (float) 1);

								if (bitmap == null)
								{
									bitmap = BitmapUtil.getBitmap(811, 0,
											(float) 1);
								}

								Bitmap bitmapSelectIcon = BitmapFactory
										.decodeResource(
												mContext.getResources(),
												R.drawable.lock1);

								imageView.setImageBitmap(BitmapUtil
										.createSelectImage(bitmap,
												bitmapSelectIcon));
								// new
								// SelectImageDownLoadTask().execute(imageView,bitmapSelectIcon,mImageIds[index],811);

								imageView
										.setLayoutParams(new Gallery.LayoutParams(
												200, 200));
							} else if (GameData.hiddedShopId[k][1] == 0)
							{
								Bitmap bitmap = BitmapUtil.getBitmap(
										mImageIds[index], 0, (float) 1);

								if (bitmap == null)
								{
									bitmap = BitmapUtil.getBitmap(811, 0,
											(float) 1);
								}
								imageView.setImageBitmap(bitmap);

								imageView
										.setLayoutParams(new Gallery.LayoutParams(
												200, 200));
							}
						}
					}
				}
				/*
				 * Log.i("qqq",
				 * "index====="+index+"+++++"+"CreateShopActivity.idx["
				 * +index+"]====="+CreateShopActivity.idx[index]);
				 * 
				 * if( index==CreateShopActivity.idx[index]){ Bitmap bitmap =
				 * BitmapUtil
				 * .getBitmap(mImageIds[CreateShopActivity.idx[index]],
				 * 0,(float)1);
				 * 
				 * if(bitmap == null) { bitmap =
				 * BitmapUtil.getBitmap(811,0,(float)1); }
				 * 
				 * Bitmap bitmapSelectIcon =
				 * BitmapFactory.decodeResource(mContext
				 * .getResources(),R.drawable.lock);
				 * 
				 * imageView.setImageBitmap(BitmapUtil.createSelectImage(bitmap,
				 * bitmapSelectIcon)); // new
				 * SelectImageDownLoadTask().execute(imageView
				 * ,bitmapSelectIcon,mImageIds[index],811);
				 * 
				 * imageView.setLayoutParams(new Gallery.LayoutParams(200,
				 * 200)); }
				 */

				// new
				// ImageDownloadTask().execute(imageView,mImageIds[index],811);
				else
				{
					Bitmap bitmap = BitmapUtil.getBitmap(mImageIds[index], 0,
							(float) 1);

					if (bitmap == null)
					{
						bitmap = BitmapUtil.getBitmap(811, 0, (float) 1);
					}
					imageView.setImageBitmap(bitmap);

					imageView
							.setLayoutParams(new Gallery.LayoutParams(200, 200));

				}
			}
			// 设置显示比例类型
			imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
		} else if (type == SPRITEGALLERY)
		{
			if (convertView == null)
			{
				imageView = new SpriteView(mContext);

				((SpriteView) imageView).setId(mImageIds[index]);
				((SpriteView) imageView).setSeries(0);
				imageView.setLayoutParams(new Gallery.LayoutParams(250, 250));
			} else
			{
				imageView = (SpriteView) convertView;
			}
		}
		return imageView;
	}

	public void setSelected(int position)
	{
		for (int i = 0; i < getCount(); i++)
		{
			ImageView view = (ImageView) getView(i, null, null);
			if (position == i)
			{
				view.setLayoutParams(new Gallery.LayoutParams(300, 400));
			} else
			{
				view.setLayoutParams(new Gallery.LayoutParams(200, 300));
			}

		}
	}

}
