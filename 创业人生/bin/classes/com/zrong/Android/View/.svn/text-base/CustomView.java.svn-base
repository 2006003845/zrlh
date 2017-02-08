package com.zrong.Android.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class CustomView extends View {

	public CustomView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public CustomView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public CustomView(Context context) {
		super(context);
		mPaint = new Paint();
		
		 
	}

	 
	
	private Paint mPaint = null;
	
	Canvas Mycanvas = null;
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		Log.v("TAG", "onDraw");
		canvas.drawColor(Color.BLACK);
		mPaint.setAntiAlias(true);
		canvas.clipRect(10, 10, 280, 260);
		
		canvas.save();
		
		canvas.rotate(45.0f);
		
		mPaint.setColor(Color.RED);
		
		canvas.drawRect(new Rect(15,15,140,70), mPaint);
		
		canvas.restore();
		
		mPaint.setColor(Color.GREEN);
		
		canvas.drawRect(new Rect(150,75,260,120), mPaint);
		
		//this.draw(Mycanvas);
		
	    
	}
	
	

}
