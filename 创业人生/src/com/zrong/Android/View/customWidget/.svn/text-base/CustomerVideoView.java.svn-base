package com.zrong.Android.View.customWidget;

import com.zrong.Android.game.GameDefinition;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.VideoView;
import android.view.GestureDetector.OnGestureListener;

/**
 * 
 *<p>
 * Titile:CustomerVideoView
 * </p>
 *<p>
 * Description:
 * </p>
 * 播放视频的类,用实现自定义的控制方式
 *<p>
 * Copyright:Copyright(c)2010
 * </p>
 *<p>
 * Company:zrong
 * </p>
 * 
 * @author yangzheng
 * @version 1.0
 */
public class CustomerVideoView extends VideoView implements OnGestureListener {

	private static String TAG = "customer.videoplayer";

	private GestureDetector gestureDetector;

	public CustomerVideoView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.gestureDetector = new GestureDetector(this);
		this.onMeasure(GameDefinition.screenWidth, GameDefinition.screenHeight);
	}

	public CustomerVideoView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.gestureDetector = new GestureDetector(this);
	}

	public CustomerVideoView(Context context) {
		super(context);
		this.gestureDetector = new GestureDetector(this);
	}

	public void setVisibility(int visibility) {
		super.setVisibility(visibility);
	}

	// public boolean onTouchEvent(MotionEvent ev){
	// Log.v(TAG, "OnTouchEvent");
	//		   
	// // return this.gestureDetector.onTouchEvent(ev);
	// return false;
	// }

	public boolean onDown(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean onFling(MotionEvent arg0, MotionEvent arg1, float arg2,
			float arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	public void onLongPress(MotionEvent arg0) {
		// TODO Auto-generated method stub

	}

	public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2,
			float arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	public void onShowPress(MotionEvent arg0) {
		// TODO Auto-generated method stub

	}

	public boolean onSingleTapUp(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		// Log.i("@@@@", "onMeasure");
		int mVideoWidth = super.getMeasuredWidth();
		int mVieeoHeight = super.getMeasuredHeight();
		Log.v("tag", "mwidth=" + mVideoWidth + "mheight=" + mVieeoHeight);
		int width = getDefaultSize(mVideoWidth, widthMeasureSpec);
		int height = getDefaultSize(mVieeoHeight, heightMeasureSpec);
		/**
		 * if (mVideoWidth > 0 && mVideoHeight > 0) { if ( mVideoWidth * height
		 * > width * mVideoHeight ) { //Log.i("@@@",
		 * "image too tall, correcting"); height = width * mVideoHeight /
		 * mVideoWidth; } else if ( mVideoWidth * height < width * mVideoHeight
		 * ) { //Log.i("@@@", "image too wide, correcting"); width = height *
		 * mVideoWidth / mVideoHeight; } else { //Log.i("@@@",
		 * "aspect ratio is correct: " + //width+"/"+height+"="+
		 * //mVideoWidth+"/"+mVideoHeight); } } //Log.i("@@@@@@@@@@",
		 * "setting size: " + width + 'x' + height);
		 */
		Log.v(TAG, "width =" + width + "height =" + height);
		setMeasuredDimension(width, height);

	}

}
