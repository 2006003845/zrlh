package com.zrong.Android.View;

import javax.microedition.lcdui.Graphics;

import res.Sprite;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;

import android.widget.ImageView;

public class SpriteView extends ImageView {

	public static final String spriteID = "resID";

	public static final String spriteSeries = "spriteSeries";

	private int spriteid = 0;

	private int curSeries = 0;

	private int trans = 0;

	private Graphics g;

	private Bitmap viewBitmap;

	private Canvas buffer;

	public SpriteView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public SpriteView(Context context, AttributeSet attrs) {

		super(context, attrs);

		spriteid = attrs.getAttributeIntValue(null, spriteID, 0);

		curSeries = attrs.getAttributeIntValue(null, spriteSeries, 0);

		if (sprite == null) {
			sprite = new Sprite(spriteid, 0, 0, 0, 0, 0, false, true);
			sprite.setShowXY(sprite.getcurSeriesbaseX(),
					sprite.getcurSeriesbaseY());
		}

		int height = sprite.getRefreshHeight();

		int width = sprite.getRefreshWidth();

		viewBitmap = Bitmap
				.createBitmap(width, height, Bitmap.Config.ARGB_8888);

		buffer = new Canvas(viewBitmap);

		g = new Graphics(buffer);

		setImageBitmap(viewBitmap);
	}

	private Sprite sprite;
	
	protected void onDraw(Canvas canvas) {

		super.onDraw(canvas);
		if (sprite == null) {
			Log.i("Log", "onDraw---sprite==null");
			sprite = new Sprite(spriteid, 0, 0, 0, 0, 0, false, true);
			sprite.setShowXY(sprite.getcurSeriesbaseX(),
					sprite.getcurSeriesbaseY());
		} else {
			Log.i("Log", "onDraw---sprite !=null");
		}

		if (changeSeries) {
//			changeSeries = false;
			setImageBitmap(viewBitmap);
		}

		if (sprite != null) {
			sprite.autoRunSprite();
			viewBitmap.eraseColor(Color.TRANSPARENT);

			if (sprite != null) {
				sprite.draw(g);
			}
		}
		
		
	}

	public SpriteView(Context context) {
		super(context);
	}

	private boolean changeSeries = false;

	public void setSeries(int series) {
		sprite.setCurSeries(series);

		int height = sprite.getRefreshHeight();

		int width = sprite.getRefreshWidth();

		viewBitmap = Bitmap
				.createBitmap(width, height, Bitmap.Config.ARGB_8888);

		buffer.setBitmap(viewBitmap);

		setFrame(0);

		changeSeries = true;
		
	}

	public void setId(int id) {
		spriteid = id;
		sprite = null;
		if (sprite == null) {
			sprite = new Sprite(spriteid, 0, 0, 0, 0, 0, false, true);
			sprite.setShowXY(sprite.getcurSeriesbaseX(),
					sprite.getcurSeriesbaseY());
			
			int height = sprite.getRefreshHeight();

			int width = sprite.getRefreshWidth();
			
			if(height <0 || width <0){
				return ;
			}

			viewBitmap = Bitmap.createBitmap(width, height,
					Bitmap.Config.ARGB_8888);

			buffer = new Canvas(viewBitmap);

			g = new Graphics(buffer);

			setImageBitmap(viewBitmap);
		}
	}

	//zhouzhilong add
	public void setXY(int x, int y) {

		sprite.setShowXY(x, y);
		int height = sprite.getRefreshHeight();

		int width = sprite.getRefreshWidth();

		if (viewBitmap == null) {
			viewBitmap = Bitmap.createBitmap(width, height,
					Bitmap.Config.ARGB_8888);
		}
		buffer = new Canvas(viewBitmap);

		g = new Graphics(buffer);

		setImageBitmap(viewBitmap);
		invalidate();//view ÖØ»æ
	}

	public int getSeries() {
		return curSeries;
	}

	public void setFrame(int frame) {
		sprite.setCurFrame(frame);
	}

	public int getFrame() {
		return sprite.getCurFrame();
	}
	
	@Override
	public void invalidate() {
		
		super.invalidate();
	}

}
