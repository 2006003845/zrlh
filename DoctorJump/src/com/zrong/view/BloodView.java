package com.zrong.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

public class BloodView extends View {

	private int bloodNum = 0;
	private int count = 0;
	private Paint mPaint;
	private int goodheart_imgId = -1, badheart_imgId = -1;

	public BloodView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mPaint = new Paint();
	}

	public BloodView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mPaint = new Paint();
	}

	public BloodView(Context context) {
		super(context);
		mPaint = new Paint();
	}

	public void setBloodNum(int bloodNum) {
		this.bloodNum = bloodNum;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setImageId(int goodheart_imgId, int badheart_imgId) {
		this.goodheart_imgId = goodheart_imgId;
		this.badheart_imgId = badheart_imgId;
	}

	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);

		if (count < 0 || bloodNum < 0 || goodheart_imgId == -1
				|| badheart_imgId == -1) {
			return;
		}
		mPaint.setAntiAlias(true);

		DisplayMetrics dm = new DisplayMetrics();
		dm = getResources().getDisplayMetrics();
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inDensity = dm.densityDpi;

		Bitmap goodheart = BitmapFactory.decodeResource(this.getResources(),
				goodheart_imgId, options);
		int goodheart_w = goodheart.getWidth();
		int goodheart_h = goodheart.getHeight();
		Bitmap badheart = BitmapFactory.decodeResource(this.getResources(),
				badheart_imgId, options);
		int badheart_w = badheart.getWidth();
		int badheart_h = badheart.getHeight();
		for (int i = 0; i < bloodNum; i++) {
			Rect rect = new Rect(i * goodheart_w, 0, (i + 1) * goodheart_w,
					goodheart_h);
			canvas.drawBitmap(goodheart, null, rect, mPaint);
		}
		for (int i = 0; i < count - bloodNum; i++) {
			Rect rect = new Rect(bloodNum * goodheart_w + i * badheart_w, 0,
					bloodNum * goodheart_w + (i + 1) * badheart_w, badheart_h);
			canvas.drawBitmap(badheart, null, rect, mPaint);
		}

	}

}
