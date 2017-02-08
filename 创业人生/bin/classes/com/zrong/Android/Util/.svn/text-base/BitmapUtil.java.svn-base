package com.zrong.Android.Util;

import res.Png;
import res.Res;
import res.ResManager;
import res.SpriteInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.graphics.Shader.TileMode;

public class BitmapUtil {
	
	public static Bitmap createTxtImage(String txt, int txtSize) {
		Bitmap mbmpTest = Bitmap.createBitmap(txt.length() * txtSize + 4,
				txtSize + 4, Config.ARGB_8888);
		Canvas canvasTemp = new Canvas(mbmpTest);
		Paint p = new Paint();
		p.setAntiAlias(true);
		p.setColor(Color.WHITE);
		p.setTextSize(txtSize);
		canvasTemp.drawText(txt, 2, txtSize - 2, p);
		return mbmpTest;
	}
	
	public static Bitmap createSelectImage(Bitmap originalImage,Bitmap selectMark)
	{
		int width = originalImage.getWidth();
		
		int height = originalImage.getHeight();
		
		Bitmap selectBitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		
		Canvas canvas = new Canvas(selectBitmap);
		
		canvas.drawBitmap(originalImage, 0,0, null);
		
		canvas.drawBitmap(selectMark, width/2, height/2, null);
		
		return selectBitmap;
	}
	
	
	public static Bitmap createReflectedImage(Bitmap originalImage) {
		// The gap we want between the reflection and the original image
		final int reflectionGap = 0;

		int width = originalImage.getWidth();
		int height = originalImage.getHeight();

		// This will not scale but will flip on the Y axis
		Matrix matrix = new Matrix();
		matrix.preScale(1, -1);

		// Create a Bitmap with the flip matrix applied to it.
		// We only want the bottom half of the image
		Bitmap reflectionImage = Bitmap.createBitmap(originalImage, 0,
				height / 2, width, height / 2, matrix, false);

		// Create a new bitmap with same width but taller to fit reflection
		Bitmap bitmapWithReflection = Bitmap.createBitmap(width,
				(height + height / 2), Config.ARGB_8888);

		// Create a new Canvas with the bitmap that's big enough for
		// the image plus gap plus reflection
		Canvas canvas = new Canvas(bitmapWithReflection);
		// Draw in the original image
		canvas.drawBitmap(originalImage, 0, 0, null);
		// Draw in the gap
		Paint defaultPaint = new Paint();
		canvas.drawRect(0, height, width, height + reflectionGap, defaultPaint);
		// Draw in the reflection
		canvas.drawBitmap(reflectionImage, 0, height + reflectionGap, null);

		// Create a shader that is a linear gradient that covers the reflection
		Paint paint = new Paint();
		LinearGradient shader = new LinearGradient(0,
				originalImage.getHeight(), 0, bitmapWithReflection.getHeight()
						+ reflectionGap, 0x70ffffff, 0x00ffffff, TileMode.CLAMP);
		// Set the paint to use this shader (linear gradient)
		paint.setShader(shader);
		// Set the Transfer mode to be porter duff and destination in
		paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
		// Draw a rectangle using the paint with our linear gradient
		canvas.drawRect(0, height, width, bitmapWithReflection.getHeight()
				+ reflectionGap, paint);
		return bitmapWithReflection;
	}
	
	
	
	/**
	 * 
	 * @param resouceId dat资源的id
	 * @param index dat资源文件的索引
	 * @param scale 大小 1表示原始大小
	 * @return
	 */
	public static Bitmap getBitmap(int resouceId,int index,float scale)
	{
		
		Res res = ResManager.getRes(resouceId, false);
		
		if(!res.loaded )return null;
		
		Png p =  null;
		
		if(res instanceof Png)
		{
			p = (Png)res;
		}

		if(p== null || (p!= null&&p.getImage()==null))
			return null;

		
		Bitmap bitmap = p.getImage().getBitmap();
		
		int width = (int)(bitmap.getWidth()*scale);
		int height = (int) (bitmap.getHeight()*scale);
		
		bitmap =Bitmap.createScaledBitmap(bitmap, width,height,true);
		
		Bitmap b = null;
		
		if(index < 0)
		{
			b = bitmap;
		}
		else
		{
			if(index <0)
			{
				b = Bitmap.createBitmap(bitmap, 0, 0, p.getWidth(), p.getHeight());
			}
			else
			{
				short[] info = p.getSelRefInfo(index);
				
				b = Bitmap.createBitmap(bitmap, (int)(info[0]*scale), (int)(info[1]*scale), (int)(info[2]*scale), (int)(info[3]*scale));
			}
			
		}
		return b;
	}
	
	
	

}
