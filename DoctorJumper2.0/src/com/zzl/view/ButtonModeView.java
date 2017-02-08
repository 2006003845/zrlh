package com.zzl.view;

import com.zzl.tool.BitmapUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class ButtonModeView extends View {

	private int[] resIds;
	private Bitmap[] bitms;
	private Context mContext;
	private boolean rotateFlag = false;// 旋转标记
	private boolean enlargeFlag = false;// 放大标记

	public void setResIds(int[] resIds) {
		this.resIds = resIds;
		initBitms();
	}

	private void initBitms() {
		bitms = new Bitmap[resIds.length];
		for (int i = 0, len = bitms.length; i < resIds.length; i++) {
			bitms[i] = BitmapUtils.getBitmapFromRes(mContext, resIds[i]);
		}
	}

	public ButtonModeView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mContext = context;
	}

	public ButtonModeView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
	}

	public ButtonModeView(Context context) {
		super(context);
		mContext = context;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (bitms == null) {
			return;
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int action = event.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			rotateFlag = true;
			break;

		case MotionEvent.ACTION_UP:

			break;
		}
		return super.onTouchEvent(event);
	}
}
