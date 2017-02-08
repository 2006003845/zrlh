package com.zrong.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;

public class SkillButtonView extends View {
	public static final String imgID = "imgID";

	private int skillID;

	public static final int SKILL_BLOOD = 0;
	public static final int SKILL_RAINBOW = 1;
	public static final int SKILL_STOP = 2;

	public int getSkillID() {
		return skillID;
	}

	public void setSkillID(int skillID) {
		this.skillID = skillID;
	}

	public int getImgId() {
		return imgId;
	}

	public void setImgId(int imgId) {
		this.imgId = imgId;
		imgBac = (BitmapDrawable) this.getResources().getDrawable(imgId);
		bm = imgBac.getBitmap();
	}

	private int imgId = -1;
	private BitmapDrawable imgBac;
	private Bitmap bm;
	private int degress = 0;
	// private SurfaceHolder mHolder; // 用于控制SurfaceView
	private int w;
	private int h;

	private Context mContext;

	public SkillButtonView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		setFocusable(true);
		imgId = attrs.getAttributeResourceValue(null, imgID, 0);
		if (imgId != 0) {
			imgBac = (BitmapDrawable) mContext.getResources()
					.getDrawable(imgId);
			bm = imgBac.getBitmap();
		}
		
		w = 60;
		h = 60;
		paint = new Paint();
		paint2 = new Paint();
		paint.setColor(Color.argb(111, 100, 100, 55));
	}

	public SkillButtonView(Context context) {
		super(context);
		w = getWidth();
		h = getHeight();
		paint = new Paint();
		paint2 = new Paint();
		paint.setColor(Color.argb(111, 100, 100, 55));
	}

	private Paint paint, paint2;

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (imgId == -1 || imgId == 0) {
			return;
		}
		if (degress == 361) {
			isCoolDowning = false;
		}
		if (bm == null) {
			imgBac = (BitmapDrawable) mContext.getResources()
					.getDrawable(imgId);
			bm = imgBac.getBitmap();
		}
		if (bm == null)
			return;
		mDraw(canvas);
	}

	protected void mDraw(Canvas canvas) {
		canvas.setViewport(w, h);
		canvas.getHeight();
		canvas.getWidth();
		// canvas.drawColor(Color.alpha(255));
		paint.setAntiAlias(true); // 消除锯齿
		paint2.setAntiAlias(true); // 消除锯齿
		canvas.drawBitmap(bm, null, new Rect(0, 0, w, h), paint2);

		// TODO 控制速度
		int i = 0;
		while (i++ < 50) {

		}

		if (isCoolDowning == true) {
			// int r = Math.round((float) (w * 0.7));
			RectF mArc = new RectF(-7, -7, w + 7, h + 7);
			canvas.drawArc(mArc, (degress++) - 90, 360 - degress, true, paint);
		}
		this.invalidate();
	}

	public boolean isCoolDowning = false;

	public void onClick(View v) {
		Log.i("Log", "onClick...");
		if (!isCoolDowning) {
			isCoolDowning = true;
			degress = 0;
		}
	}

}
