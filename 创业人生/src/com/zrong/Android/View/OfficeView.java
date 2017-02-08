package com.zrong.Android.View;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import com.zrong.Android.game.GameDefinition;

import res.ResManager;
import res.Sprite;
import res.SpriteInfo;
import res.UseResListNew;

import android.content.Context;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.FloatMath;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.OnGestureListener;
import android.widget.ImageView;
/**
 * 
 *<p>Titile:BackGroundView</p>
 *<p>Description: 游戏背景类,如办公室背景，地图等等</p>
 *<p>Copyright:Copyright(c)2010</p>
 *<p>Company:zrong</p>
 * @author yangzheng
 * @version 1.0
 * @date Jul 19, 2011
 */
public class OfficeView extends View implements OnGestureListener {
	
	public static final String TAG = "BackGroundView";
	
	public OfficeView(Context context, AttributeSet attrs, int defStyle){
		super(context, attrs, defStyle); 
		
		viewBitmap = Bitmap.createBitmap(original_width, original_height, Bitmap.Config.ARGB_8888);
		
		buffer = new Canvas(viewBitmap);
		
		mGestureDetector = new GestureDetector(this);
		
		matrix = new Matrix();
		 
		originalcaleX = (float) (GameDefinition.screenWidth/(float)480);
		originalcaleY = (float) (GameDefinition.screenHeight/(float)560);
		
		matrix.postScale(originalcaleX, originalcaleY, 0, 0);
	}

	public OfficeView(Context context, AttributeSet attrs) {
		super(context, attrs);
		viewBitmap = Bitmap.createBitmap(original_width, original_height, Bitmap.Config.ARGB_8888);
		
		buffer = new Canvas(viewBitmap);
		
		mGestureDetector = new GestureDetector(this);
		
		matrix = new Matrix();
		 
		originalcaleX = (float) (GameDefinition.screenWidth/(float)480);
		originalcaleY = (float) (GameDefinition.screenHeight/(float)560);
		
		matrix.postScale(originalcaleX, originalcaleY, 0, 0);
	}

	public OfficeView(Context context) {
		super(context);
		
		viewBitmap = Bitmap.createBitmap(original_width, original_height, Bitmap.Config.ARGB_8888);
		
		buffer = new Canvas(viewBitmap);
		
		mGestureDetector = new GestureDetector(this);
		
		matrix = new Matrix();
		 
		originalcaleX = (float) (GameDefinition.screenWidth/(float)480);
		originalcaleY = (float) (GameDefinition.screenHeight/(float)560);
		
		matrix.postScale(originalcaleX, originalcaleY, 0, 0);
	}
	
	   private int original_width = 480;
	   
	   private int original_height = 560;
	   
	   private float originalcaleX;
	   
	   private float originalcaleY;
	
	   Matrix matrix = null;
	   Matrix testMatrix = new Matrix();
	   Matrix savedMatrix = new Matrix(); 
	   PointF start = new PointF(); 
	   PointF mid = new PointF(); 
	   float  oldDist; 
	   
	   
	   static final int NONE = 0; 
	   static final int DRAG = 1; 
	   static final int ZOOM = 2; 
	   int mode = NONE; 
	

	private Graphics g;
	
	private Bitmap viewBitmap;
	
	private Canvas buffer;
	
	private Sprite office;
	
	private Sprite loading;
	
	private static final float MAX_SCALE = (float)2.5;
	
	 
	
	private GestureDetector mGestureDetector;
	
	private float velocityX;
	private float velocityY;
	
	protected void onDraw(Canvas canvas){
		
		super.onDraw(canvas);
		if(g == null)
		{
			g = new Graphics(buffer);
		}
		else
		{
			g.setCanvas(buffer);
		}
		
		if(office == null)
		{
			office = new Sprite(UseResListNew.IMAGE_517, 0, 0, 0, 0, 0, false, true);
			office.setShowXY(0, 0);
		}
		
		if(office != null)
		{
			office.autoRunSprite(100);
			office.draw(g);
		}
		 
		 
		
		if(loading != null)
		{
			loading.autoRunSprite();
			loading.draw(g);
		}
		
		drawImageZoom(viewBitmap,canvas,0,0,GameDefinition.screenWidth,GameDefinition.screenHeight);
		
		
		
	}
	 

	
	public void drawImageZoom(Bitmap bitmap, Canvas canvas, int x, int y,int screen_w, int screen_h) {
		
		int temp_w = bitmap.getWidth();
		int temp_y = bitmap.getHeight();
		canvas.setMatrix(matrix);
		canvas.drawBitmap(bitmap, 0, 0, null);
		onFlingMove();
	}
	
	private void onFlingMove()
	{   
		/**每帧移动的距离 */
		float move_x = 0;
		float move_y = 0;
		
		if(velocityX != 0)
		{
			move_x =velocityX/50;
			velocityX = scrub(velocityX);//摩擦力为9/10
		}
		
		if (velocityY !=0)
		{
			move_y = velocityY/100;
			velocityY = scrub(velocityY);
		}
		
		if(move_y == 0 || move_x == 0)return;
		
		
		
		float values[] = new float[9];
		
        matrix.postTranslate(move_x, move_y);
        
        matrix.getValues(values);
		float mscalex = values[Matrix.MSCALE_X];//保存的上次宽度缩放倍数 
		float mscaley = values[Matrix.MSCALE_Y];
        float mtrans_x = values[Matrix.MTRANS_X];//保存的上次左上顶点X坐标   
        float mtrans_y = values[Matrix.MTRANS_Y];//保存的上次左上顶点Y坐标
        float mtrans_nx = mtrans_x+original_width*mscalex;//保存的上次右下顶点X坐标
        float mtrans_ny = mtrans_y+original_height*mscaley;//保存的上次右下顶点Y坐标
        
        if(mtrans_x >0)
        {
        	move_x = -mtrans_x;
        	velocityX =0;
        }
        if(mtrans_y > 0)
        {
        	move_y = -mtrans_y;
        	velocityY = 0;
        }
        
        if(mtrans_nx < GameDefinition.screenWidth)
        {
        	move_x = GameDefinition.screenWidth - mtrans_nx;
        	velocityX =0;
        }
        
        if(mtrans_ny < GameDefinition.screenHeight)
        {
        	move_y = GameDefinition.screenHeight - mtrans_ny;
        	velocityY = 0;
        }
        matrix.postTranslate(move_x, move_y);
	}
	/*
	 *摩擦力
	 */
	private float scrub(float f)
	{
		return f*9/10;
	}
	
	private void clearVelocity()
	{
		velocityX = 0;
		velocityY = 0;
	}
	 
	public boolean onTouchEvent(MotionEvent event){
		 clearVelocity();
		 mGestureDetector.onTouchEvent(event);
		  
	      switch (event.getAction() & MotionEvent.ACTION_MASK)
	      { 
	         //设置拖拉模式 
	         case MotionEvent.ACTION_DOWN: 
	            savedMatrix.set(matrix); 
	            start.set(event.getX(), event.getY()); 
	            Log.i(TAG, "mode=DRAG" ); 
	            mode = DRAG; 
	            break; 

	         case MotionEvent.ACTION_UP: 
	         case MotionEvent.ACTION_POINTER_UP: 
	            mode = NONE; 
	            Log.d(TAG, "mode=NONE" ); 
	            break; 
	         //设置多点触摸模式 
	         case MotionEvent.ACTION_POINTER_DOWN: 
	            oldDist = spacing(event); 
	            Log.d(TAG, "oldDist=" + oldDist); 
	            if (oldDist > 10f) { 
	               savedMatrix.set(matrix); 
	               midPoint(mid, event); 
	               mode = ZOOM; 
	               Log.i(TAG, "mode=ZOOM" ); 
	            } 
	            break; 
	          //若为DRAG模式，则点击移动图片 
	         case MotionEvent.ACTION_MOVE: 
	            if (mode == DRAG) { 
	               matrix.set(savedMatrix);
	               testMatrix.set(savedMatrix);
	               // 设置位移 
	               float tran_x = event.getX() - start.x;
	               float tran_y = event.getY() - start.y;
	               
	               testMatrix.postTranslate(tran_x, 
	            		   tran_y); 
	               float testvalue[] = new float[9];
	               float value[] = new float[9];
	               testMatrix.getValues(testvalue);
	               matrix.getValues(value);
	               
	               float mtrans_x = testvalue[Matrix.MTRANS_X];//左上顶点X坐标   
	               float mtrans_y = testvalue[Matrix.MTRANS_Y];//左上顶点Y坐标
	               float mscale_x = value[Matrix.MSCALE_X];//宽度缩放倍数 
	               float mscale_y = value[Matrix.MSCALE_Y];
	               
	               
	               if(mtrans_x > 0.0)
	               {
	            	   tran_x = tran_x -mtrans_x;
	               }
	               if(mtrans_x < -original_width*(mscale_x-originalcaleX))
	               {
	            	   tran_x = -original_width*(mscale_x-originalcaleX)-value[Matrix.MTRANS_X];
	               }
	               if(mtrans_y > 0.0)
	               {
	            	   tran_y =tran_y-mtrans_y;
	               }
	               if(mtrans_y < -original_height*(mscale_y-originalcaleY))
	               {
	            	   tran_y = -original_height*(mscale_y-originalcaleY)-value[Matrix.MTRANS_Y];
	               }
	               matrix.postTranslate(tran_x, tran_y);   
	              
	            } 
	            //若为ZOOM模式，则多点触摸缩放 
	               else if (mode == ZOOM) { 
	               float newDist = spacing(event); 
	               Log.d(TAG, "newDist=" + newDist); 
	               if (newDist > 10f) {
	            	   
	                  matrix.set(savedMatrix); 
	                  testMatrix.set(savedMatrix);
	                  
	                  float scale = newDist / oldDist;
	                  
	                  testMatrix.postScale(scale, scale, mid.x, mid.y);  
	                  
	                  float testvalue[] = new float[9];
	                  float value[] = new float[9];
	                  
	                  testMatrix.getValues(testvalue);
	                  matrix.getValues(value);
	                  
	                  float tscaleX = testvalue[Matrix.MSCALE_X];//测试Matrix宽度缩放倍数
	                  float tscaleY = testvalue[Matrix.MSCALE_Y];
	                  float scaleX =scale,scaleY=scale;
	                  
	                  if(tscaleX>MAX_SCALE)
	                  {
	                	  scaleX = MAX_SCALE /value[Matrix.MSCALE_X];
	                  } 
	                  else if(tscaleX<originalcaleX)
	                  {
	                	  scaleX = originalcaleX/value[Matrix.MSCALE_X];
	                  }
	                  
	                  if(tscaleY>MAX_SCALE)
	                  {
	                	  scaleY = MAX_SCALE /value[Matrix.MSCALE_Y];
	                  } 
	                  else if(tscaleY<originalcaleY)
	                  {
	                	  scaleY = originalcaleY/value[Matrix.MSCALE_Y];
	                  }
	                  
	                   matrix.postScale(scaleX, scaleY,  mid.x, mid.y); 
	                   
	                   matrix.getValues(value);
	                   
	                   float mscalex = value[Matrix.MSCALE_X];//保存的上次宽度缩放倍数 
	                   float mscaley = value[Matrix.MSCALE_Y];
		               float mtrans_x = value[Matrix.MTRANS_X];//保存的上次左上顶点X坐标   
		               float mtrans_y = value[Matrix.MTRANS_Y];//保存的上次左上顶点Y坐标
		               float mtrans_nx = mtrans_x+original_width*mscalex;//保存的上次右下顶点X坐标
			           float mtrans_ny = mtrans_y+original_height*mscaley;//保存的上次右下顶点Y坐标
		               
			           
		               if(mtrans_x >0)
		               {
		            	   matrix.postTranslate(-mtrans_x, 0);
		               }
		               if(mtrans_y > 0)
		               {
		            	   matrix.postTranslate(0, -mtrans_y);
		               }
		               
		               if(mtrans_nx < GameDefinition.screenWidth)
		               {
		            	   matrix.postTranslate(GameDefinition.screenWidth-mtrans_nx, 0);
		               }
		               
		               if(mtrans_ny < GameDefinition.screenHeight)
		               {
		            	   matrix.postTranslate(0, GameDefinition.screenHeight-mtrans_ny);
		               }
	               } 
	            } 
	            break; 

	      }
	            return true ; 
	}
	
	
	
	   
    //计算移动距离 
      private float spacing(MotionEvent event) { 
         float x = event.getX(0) - event.getX(1); 
         float y = event.getY(0) - event.getY(1); 
         return FloatMath.sqrt(x * x + y * y); 
      } 
   //计算中点位置 
      private void midPoint(PointF point, MotionEvent event) { 
         float x = event.getX(0) + event.getX(1); 
         float y = event.getY(0) + event.getY(1); 
         point.set(x / 2, y / 2); 
      }
      
   // 用户轻触触摸屏，由1个MotionEvent ACTION_DOWN触发
	public boolean onDown(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	// 用户按下触摸屏、快速移动后松开，由1个MotionEvent ACTION_DOWN, 多个ACTION_MOVE, 1个ACTION_UP触发

	public boolean onFling(MotionEvent arg0, MotionEvent arg1, float vx,
			float vy) {
		// TODO Auto-generated method stub
		velocityX = vx;
		velocityY = vy;
		Log.i(TAG, "onFling vx ="+vx +",vy ="+vy);
		return false;
	}
	// 用户长按触摸屏，由多个MotionEvent ACTION_DOWN触发

	public void onLongPress(MotionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	// 用户按下触摸屏，并拖动，由1个MotionEvent ACTION_DOWN, 多个ACTION_MOVE触发

	public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float vx,
			float vy)
	{
		Log.i(TAG, "onScroll");
		return false;
	}
	// 用户轻触触摸屏，尚未松开或拖动，由一个1个MotionEvent ACTION_DOWN触
	public void onShowPress(MotionEvent arg0){
		// TODO Auto-generated method stub
		Log.i(TAG, "onShowPress");
	}
	//用户（轻触触摸屏后）松开，由一个1个MotionEvent ACTION_UP触发

	public boolean onSingleTapUp(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	 

	
}
