package com.zrong.view;

import com.zrong.engine.DynamicBG;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class DrawDynamicView extends View {

	private DynamicBG dynamicBG;

	public DrawDynamicView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public DrawDynamicView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public DrawDynamicView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Log.i("Log2", "omDraw");
		if (dynamicBG == null)
			return;
		dynamicBG.update(true, 0);
		dynamicBG.draw(canvas);
		invalidate();
	}

	public void setDynamicBG(DynamicBG dynamicBG) {
		this.dynamicBG = dynamicBG;
	}

}
