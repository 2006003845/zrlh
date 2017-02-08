package com.zrong.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;

import com.zrong.ui.R;

public class DrawableAnimation extends View {
	private Context mContext;
	private Drawable mDrawable;
	private Animation mAiAnimation;
	private Transformation mTransformation = new Transformation();

	public DrawableAnimation(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		mContext = context;
	}

	public DrawableAnimation(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		mContext = context;
	}

	public DrawableAnimation(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		mContext = context;
	}

	private boolean isInit = false;

	/**
	 * 
	 * @param fromXDelta“∆∂Ø∑∂Œß
	 * @param toXDelta
	 * @param fromYDelta
	 * @param toYDelta
	 * @param durationMillis
	 *            —”≥Ÿ ±º‰
	 */
	public void init(float fromXDelta, float toXDelta, float fromYDelta,
			float toYDelta, long durationMillis) {
		setFocusable(true);
		setFocusableInTouchMode(true);
		mDrawable = mContext.getResources().getDrawable(R.drawable.img_teach);
		mAiAnimation = new TranslateAnimation(fromXDelta, toXDelta, fromYDelta,
				toYDelta);
		mAiAnimation.initialize(10, 10, 10, 10);
		mAiAnimation.setDuration(durationMillis);
		mAiAnimation.setRepeatCount(-1);
		mDrawable.setBounds(0, 0, mDrawable.getIntrinsicWidth(),
				mDrawable.getIntrinsicHeight());
		mAiAnimation.startNow();
		isInit = true;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		if (!isInit) {
			return;
		}

		if (mDrawable != null) {
			int sc = canvas.save();
			if (mAiAnimation != null) {
				mAiAnimation.getTransformation(
						AnimationUtils.currentAnimationTimeMillis(),
						mTransformation);
				canvas.concat(mTransformation.getMatrix());
			}
			mDrawable.draw(canvas);
			canvas.restoreToCount(sc);
		}
		invalidate();
	}

}
