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

public class TimeView extends View {

	private Paint mPaint;
	private long time = -1;
	private Bitmap[] bms;
	private int imgId = -1;

	private long[] times;//  ±∑÷√Î

	public TimeView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mPaint = new Paint();
		times = new long[2];
	}

	public TimeView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mPaint = new Paint();
		times = new long[2];
	}

	public TimeView(Context context) {
		super(context);
		mPaint = new Paint();
		times = new long[2];
	}

	public void setTime(long time) {
		this.time = time;
	}

	public void setImgId(int imgId) {
		this.imgId = imgId;
	}

	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		if (imgId == -1 || time == -1) {
			return;
		}
		mPaint.setAntiAlias(true);
		DisplayMetrics dm = new DisplayMetrics();
		dm = getResources().getDisplayMetrics();
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inDensity = dm.densityDpi;

		Bitmap resource = BitmapFactory.decodeResource(this.getResources(),
				imgId, options);
		if(resource == null){
			return;
		}
		bms = BitmapUtils.cutBitmap(resource, resource.getWidth() / 11,
				resource.getHeight());

		// times[0] = time / 3600;
		times[0] = (time % 3600) / 60;
		times[1] = (time % 3600) % 60;
		String num = "";
		for (int i = 0; i < times.length; i++) {
			// if (times[i] == 0 && i == 0) {
			// continue;
			// }
			if (times[i] < 10) {
				num = "0" + String.valueOf(times[i]);
			} else {
				num = String.valueOf(times[i]);
			}
			drawTime(canvas, num, i);
		}

	}

	private void drawTime(Canvas canvas, String num, int index) {
		int w = bms[0].getWidth();
		int h = bms[0].getHeight();
		int left = 0;
		char nums[] = num.toCharArray();
		for (int i = 0; i < nums.length; i++) {
			Rect rect = new Rect();
			left = index * w * 2 + (index == 1 ? w : 0) + i * w;
			rect.set(left, 0, left + w, h);
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
			canvas.drawBitmap(bitmap, null, rect, mPaint);
		}
		if (index == 1) {
			return;
		}
		canvas.drawBitmap(bms[10], null,
				new Rect(left + w, 0, left + 2 * w, h), mPaint);
	}

}
