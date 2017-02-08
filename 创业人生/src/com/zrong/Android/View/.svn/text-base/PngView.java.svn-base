package com.zrong.Android.View;

import javax.microedition.lcdui.Graphics;

import res.Png;
import res.Res;
import res.ResManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class PngView extends ImageView
{

	private Bitmap bitmap  = null;
	/**自定义的一些属性 */
	/**
	 * 资源ID
	 */
	private static final String PngID = "resID";
	/**
	 * 图片切片的索引
	 */
	private static final String PngIndex ="PngIndex";
	/**
	 * 图片的缩放
	 */
	private static final String PngScale ="PngScale";
	/**
	 * 图片的旋转
	 */
	private static final String PngRotate ="PngRotate";
	
	public PngView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}


	public PngView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		
		int resouceId = -1;//资源id
		int index = -1;//资源切片索引
		float scale = 1;//图片缩放
		float Rotate =0;//图片旋转
		
		resouceId = attrs.getAttributeIntValue(null, PngID, 0);
		
		index = attrs.getAttributeIntValue(null,PngIndex,-1);
		
		scale =attrs.getAttributeIntValue(null, PngScale, 1);
		
		
		
		Png p = (Png)ResManager.getRes(resouceId, true);
		
		bitmap = p.getImage().getBitmap();
		
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
			short[] info = p.getSelRefInfo(index);
			b = Bitmap.createBitmap(bitmap, (int)(info[0]*scale), (int)(info[1]*scale), (int)(info[2]*scale), (int)(info[3]*scale));
		}
		
		setImageBitmap(b);
	}
	
	public PngView(Context context)
	{
		super(context);
	}
	
	public void setImageBitmap(int resouceId,int index,float scale)
	{
		Png p = (Png)ResManager.getRes(resouceId, true);
		
		bitmap = p.getImage().getBitmap();
		
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
			short[] info = p.getSelRefInfo(index);
			b = Bitmap.createBitmap(bitmap, (int)(info[0]*scale), (int)(info[1]*scale), (int)(info[2]*scale), (int)(info[3]*scale));
		}
		
		setImageBitmap(b);
	} 

	 
	 
	
	
	
	 
	
	
	
}
