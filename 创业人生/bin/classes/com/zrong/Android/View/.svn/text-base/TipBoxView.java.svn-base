package com.zrong.Android.View;

import android.content.Context;
import android.graphics.Canvas;
import android.text.method.ScrollingMovementMethod;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class TipBoxView extends TextView{
	
	//private TextView tb;
	public TipBoxView(Context arg0, AttributeSet arg1, int arg2) {
		super(arg0, arg1, arg2);
		 
		setMovementMethod(ScrollingMovementMethod.getInstance()); 
		if(isScroll())
		{
			scrollTo(0, -(getHeight()>>1));
		}
			
		
	}

	public TipBoxView(Context arg0, AttributeSet arg1) {
		super(arg0, arg1);
		 
		setMovementMethod(ScrollingMovementMethod.getInstance()); 
		if(isScroll())
		{
			scrollTo(0, -(getHeight()>>1));
		}
	}

	public TipBoxView(Context arg0){
		super(arg0);
		 
		setMovementMethod(ScrollingMovementMethod.getInstance()); 
		if(isScroll())
		{
			scrollTo(0, -(getHeight()>>1));
		}
	}

	public boolean isScroll()
	{
		int height = getHeight();   
		
		int line = getLineCount();
		
		int lineHeight = getLineHeight();
		
		if(height - line*lineHeight > 0)
		{
			return false;
		}
		return true;
	}
	
	 
	protected void onDraw(Canvas arg0)
	{
		super.onDraw(arg0);
		
		if(isScroll())
		{
			this.scrollBy(0, 1);
			int scrollY = getScrollY();
			
			int line = getLineCount();
			
			int lineHeight = getLineHeight();
			if(scrollY >line*lineHeight)
			{
				scrollTo(0, -(getHeight()>>1));
			}
		}
	}
	
	
	
	
}
