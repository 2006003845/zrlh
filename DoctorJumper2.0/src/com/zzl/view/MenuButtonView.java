package com.zzl.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.zzl.api.SystemApi;
import com.zzl.tool.BitmapUtils;

public class MenuButtonView extends View {

	private Context mContext;

	private boolean isUnFold = false;// 是否为展开状态
	//
	public static float SET_X_OFFSET = 0;
	public static float SET_Y_OFFSET = 0;
	private int[] resIds;// 按钮图片资源ID
	private Bitmap[] btnBms;// 按钮集Bitmap[]
	private Bitmap btnsBack;
	private Paint p;

	private float degress = 0;

	private float bgoffset = 0;// 控件背景图片Id
	float syoffset; // 上移
	float xyoffset; // 下移

	private float viewWith;
	private float viewHigh;

	private int flag;

	public void setBtnsBack(int BtnsBackResId) {
		btnsBack = BitmapUtils.getBitmapFromRes(mContext, BtnsBackResId);
	}

	public void setResId(int[] resIds) {
		this.resIds = resIds;
		initBtnBm();
	}

	private void initBtnBm() {
		btnBms = new Bitmap[resIds.length];
		for (int i = 0; i < resIds.length; i++) {
			btnBms[i] = BitmapUtils.getBitmapFromRes(mContext, resIds[i]);
		}
		getLocaltionAndWH();
	}

	public MenuButtonView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mContext = context;

		p = new Paint();
		p.setAntiAlias(true);
	}

	public MenuButtonView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		p = new Paint();
		p.setAntiAlias(true);
	}

	public MenuButtonView(Context context) {
		super(context);
		mContext = context;
		p = new Paint();
		p.setAntiAlias(true);
	}

	// 平行矩阵
	Matrix m1 = new Matrix();
	// 旋转矩阵
	Matrix m2 = new Matrix();
	// 组合矩阵
	Matrix m3 = new Matrix();
	boolean c = false;
	private boolean degressFlag = false;

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (resIds == null) {
			return;
		}
		if (!c) {
			viewWith = this.getWidth();
			viewHigh = this.getHeight();
			getLocaltionAndWH();
			c = true;
		}

		if (degressFlag) {
			if (isUnFold && syoffset <= btnsBack.getHeight()) {
				syoffset += 60;
				degress = (float) (Math.toDegrees(syoffset)) / 1.3f;
			} else if (!isUnFold && xyoffset <= btnsBack.getHeight()) {
				syoffset += 60;
				degress = (float) (Math.toDegrees(syoffset)) / 1.3f;
			}
			m1.setTranslate(btn_location[0][0], btn_location[0][1]);
			m2.setRotate(degress,
					btn_location[0][0] + btnBms[0].getWidth() / 2,
					btn_location[0][1] + btnBms[0].getHeight() / 2);
			m3.setConcat(m2, m1);
		} else {
			m3.setTranslate(btn_location[0][0], btn_location[0][1]);
		}
		// 绘制设置按钮
		canvas.drawBitmap(btnBms[0], btn_location[0][0], btn_location[0][1], p);
		canvas.drawBitmap(btnBms[0], m3, p);
		canvas.restore();
		if (isUnFold) {
			if (syoffset <= btnsBack.getHeight()) {
				// 上移
				canvas.clipRect(btn_location[1][0],
						viewHigh - btnBms[0].getHeight() - syoffset,
						btn_location[1][0] + btnsBack.getWidth(), viewHigh
								- btnBms[1].getHeight());
				for (int i = 1, len = btnBms.length; i < len; i++) {
					// 绘制上移子菜单背景
					canvas.drawBitmap(btnsBack, btn_location[i][0], viewHigh
							- btn_location[i][1] - syoffset, p);
					// 绘制上移子菜单
					canvas.drawBitmap(btnBms[i], btn_location[i][0], viewHigh
							- btnBms[0].getHeight() + i * btnBms[i].getHeight()
							- syoffset, p);
				}
			} else {
				degressFlag = false;
				// 绘制移动完成的子菜单
				canvas.drawBitmap(btnsBack,
						btn_location[btn_location.length - 1][0],
						btn_location[btn_location.length - 1][1], p);
				for (int i = btnBms.length - 1; i > 0; i--) {
					canvas.drawBitmap(btnBms[i], btn_location[i][0],
							btn_location[i][1], p);
				}
			}
		}
		//
		if (!isUnFold) {
			if (xyoffset <= btnsBack.getHeight()) {
				// 下移
				xyoffset += 60;
				degress = (float) (Math.toDegrees(-xyoffset)) / 1.3f;
				// degreesFlag = true;
				canvas.clipRect(
						btn_location[1][0],
						viewHigh - btnBms[1].getHeight()
								- btnBms[1].getHeight() + xyoffset,
						btn_location[1][0] + btnBms[1].getWidth(), viewHigh
								- btnBms[1].getHeight());
				for (int i = btnBms.length - 1; i > 0; i--) {
					// 绘控件背景
					canvas.drawBitmap(btnsBack, btn_location[3][0], viewHigh
							- btnBms[1].getHeight() - btnBms[i].getHeight()
							+ xyoffset, p);
					// 绘制按键集合
					canvas.drawBitmap(btnBms[i], btn_location[i][0], viewHigh
							- btnBms[0].getHeight() - btnsBack.getHeight()
							+ xyoffset + btnBms[i].getHeight() * (3 - i), p);
				}

			} else {
				degressFlag = false;
			}
		}
		SystemApi.sleep(10);
		this.invalidate();

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		this.invalidate();
		float tx = event.getX();
		float ty = event.getY();
		int action = event.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			if (tx > btn_location[0][0]
					&& tx < btn_location[0][0] + btn_location[0][2]
					&& ty > btn_location[0][1]
					&& ty < btn_location[0][1] + btn_location[0][3]) {
				degressFlag = true;
				isUnFold = !isUnFold;
				if (isUnFold) {
					syoffset = 0;
				} else {
					xyoffset = 0;
				}
			}
			break;

		case MotionEvent.ACTION_UP:

			break;

		}

		return super.onTouchEvent(event);
	}

	// 按钮集
	public float[][] btn_location;

	// 初始化按钮集
	public float[][] getLocaltionAndWH() {
		if (btnBms == null)
			return null;
		int len = btnBms.length;
		// 按钮间距
		float set_Space = (btnBms[0].getWidth() - btnsBack.getWidth()) / 2;
		btn_location = new float[len][4];
		for (int i = 0; i < len; i++) {
			if (i == 0) {
				btn_location[0][0] = SET_X_OFFSET;
				btn_location[0][1] = viewHigh - btnBms[0].getHeight()
						- SET_Y_OFFSET;
			} else {
				btn_location[i][0] = SET_X_OFFSET + set_Space;
				btn_location[i][1] = btn_location[i - 1][1]
						- btnBms[i].getHeight();
			}
			btn_location[i][2] = btnBms[i].getWidth();
			btn_location[i][3] = btnBms[i].getHeight();
		}
		SystemApi.sleep(10);
		return btn_location;
	}

}
