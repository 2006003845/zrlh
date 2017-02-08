package com.zrong.Android.View;

 

import java.io.ByteArrayOutputStream;
import java.io.InputStream; 

import gif.GifFrame;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ImageView;

public class GifView extends ImageView {
	
	public static final String ResId="resId";
	
	private GifFrame	mGifFrame	= null;
	
	public GifView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public GifView(Context context, AttributeSet attrs) {
		super(context, attrs);
		int resouceId = -1;
		
		resouceId =attrs.getAttributeResourceValue(null, ResId, 0);
		
		if(resouceId > 0)
		{
			mGifFrame=GifFrame.CreateGifImage(fileConnect(this.getResources().openRawResource(resouceId)));
			Bitmap b=mGifFrame.getImage();
			setImageBitmap(b);
		}
	}

	public GifView(Context context){
		super(context);
		// TODO Auto-generated constructor stub
	}

	 
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		mGifFrame.nextFrame();
		this.setImageBitmap(mGifFrame.getImage());
	}
	
	

	/* ¶ÁÈ¡ÎÄ¼þ */
	public byte[] fileConnect(InputStream is)
	{
		try
		{					    
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int ch = 0;
			while( (ch = is.read()) != -1) 
			{
				baos.write(ch);
			}			      
			byte[] datas = baos.toByteArray();
			baos.close(); 
			baos = null;
			is.close();
			is = null;
			return datas;
		}
		catch(Exception e)
		{
			return null;
		}
	}
}
