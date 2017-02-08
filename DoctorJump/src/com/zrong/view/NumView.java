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

import com.zrong.utils.BitmapUtils;

public class NumView extends View {
	private Paint mPaint;
	private String num;
	private Bitmap[] bms;
	private int imgId = -1;
	private boolean isAddMarkImg = false;
	private int frameCount = -1;

	public NumView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mPaint = new Paint();
		this.num = "";
		// TODO Auto-generated constructor stub
	}

	public NumView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mPaint = new Paint();
		this.num = "";
		// TODO Auto-generated constructor stub
	}

	public NumView(Context context) {
		super(context);
		mPaint = new Paint();
		this.num = "";
		// TODO Auto-generated constructor stub
	}

	public void setNum(String num) {
		this.num = num;
	}

	public void setFrameCount(int frameCount) {
		this.frameCount = frameCount;
	}

	public void setImgId(int imgId) {
		this.imgId = imgId;
	}

	public void setAddMarkImg(boolean isAddMarkImg) {
		this.isAddMarkImg = isAddMarkImg;
	}

	@Override
	public void draw(Canvas canvas) {
		super.onDraw(canvas);

		if (imgId == -1 || num == null) {
			return;
		}
		if (canvas == null) {
			return;
		}

		int base = 0;
		mPaint.setAntiAlias(true);

		DisplayMetrics dm = new DisplayMetrics();
		dm = getResources().getDisplayMetrics();
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inDensity = dm.densityDpi;

		Bitmap resource = BitmapFactory.decodeResource(this.getResources(),
				imgId, options);
		bms = BitmapUtils.cutBitmap(resource, resource.getWidth() / frameCount,
				resource.getHeight());
		if (frameCount == 11 && isAddMarkImg) {
			Rect rect = new Rect(base, 0, bms[10].getWidth(),
					bms[10].getHeight());
			canvas.drawBitmap(bms[10], null, rect, mPaint);
			base = bms[10].getWidth();
		}

		char nums[] = num.toCharArray();
		for (int i = 0; i < nums.length; i++) {
			Rect rect = new Rect();
			rect.set(base + i * bms[0].getWidth(), 0,
					base + i * bms[0].getWidth() + bms[0].getWidth(),
					bms[0].getHeight());
			Bitmap bitmap = null;
			if (nums[i] == '0') {
				bitmap = bms[0];
			} else if (nums[i] == '1') {
				bitmap = bms[1];
			} else if (nums[i] == '2') {
				bitmap = bms[2];
			} else if (nums[i] == '3') {
				bitmap = bms[3];
			} else if (nums[i] == '4') {
				bitmap = bms[4];
			} else if (nums[i] == '5') {
				bitmap = bms[5];
			} else if (nums[i] == '6') {
				bitmap = bms[6];
			} else if (nums[i] == '7') {
				bitmap = bms[7];
			} else if (nums[i] == '8') {
				bitmap = bms[8];
			} else if (nums[i] == '9') {
				bitmap = bms[9];
			}
			if (bitmap == null)
				return;
			canvas.drawBitmap(bitmap, null, rect, mPaint);
		}
	}
}
