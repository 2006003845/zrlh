package cn.zrong.utils;

import android.view.View;
import android.view.animation.*;
import android.view.animation.Animation.AnimationListener;

/**
 * 
 * <p>
 * Titile:AnimationUtil
 * </p>
 * <p>
 * Description:定义动画,增强重用性
 * </p>
 * <p>
 * Copyright:Copyright(c)2010
 * </p>
 * <p>
 * Company:zrong
 * </p>
 * 
 * @author yz
 * @version 1.0
 * @date 2012-5-25
 */
public class AnimationUtil {
	/**
	 * 
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @author yz
	 * @param fromXDelta
	 * @param toXDelta
	 * @param fromYDelta
	 * @param toYDelta
	 * @param view
	 *            需要启动动画的对象
	 * @param listener
	 *            动画状态监听器，可以为空
	 */
	public static void TranslateAnimation(float fromXDelta, float toXDelta,
			float fromYDelta, float toYDelta, View view,
			AnimationListener listener, long duration) {
		TranslateAnimation tanslate = new TranslateAnimation(fromXDelta,
				toXDelta, fromYDelta, toYDelta);
		if (listener != null) {
			tanslate.setAnimationListener(listener);
		}
		tanslate.setDuration(1000);
		view.startAnimation(tanslate);
	}

	/**
	 * 
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @author yz
	 * @param fromAlpha
	 * @param toAlpha
	 * @param view
	 * @param listener
	 */
	public static void alphaAnimation(float fromAlpha, float toAlpha,
			View view, AnimationListener listener, long duration) {
		AlphaAnimation alpha = new AlphaAnimation(fromAlpha, toAlpha);

		if (listener != null) {
			alpha.setAnimationListener(listener);
		}

		alpha.setDuration(duration);
		view.startAnimation(alpha);
	}

	/**
	 * 
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @author yz
	 * @param fromX
	 * @param toX
	 * @param fromY
	 * @param toY
	 * @param pivotXType
	 * @param pivotXValue
	 * @param pivotYType
	 * @param pivotYValue
	 * @param view
	 * @param listener
	 */
	public static void scaleAnimation(float fromX, float toX, float fromY,
			float toY, int pivotXType, float pivotXValue, int pivotYType,
			float pivotYValue, View view, AnimationListener listener,
			long duration) {
		ScaleAnimation scale = new ScaleAnimation(fromX, toX, fromY, toY,
				pivotXType, pivotXValue, pivotYType, pivotYValue);
		if (listener != null) {
			scale.setAnimationListener(listener);
		}

		scale.setDuration(duration);
		view.startAnimation(scale);
	}

	/**
	 * 
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @author yz
	 * @param fromDegrees
	 * @param toDegrees
	 * @param pivotXType
	 * @param pivotXValue
	 * @param pivotYType
	 * @param pivotYValue
	 * @param view
	 * @param listener
	 * @param duration
	 */
	public static void rotateAnimation(float fromDegrees, float toDegrees,
			int pivotXType, float pivotXValue, int pivotYType,
			float pivotYValue, View view, AnimationListener listener,
			long duration) {
		RotateAnimation rotate = new RotateAnimation(fromDegrees, toDegrees,
				pivotXType, pivotXValue, pivotYType, pivotYValue);

		if (listener != null) {
			rotate.setAnimationListener(listener);
		}

		rotate.setDuration(duration);
		view.startAnimation(rotate);
	}
}
