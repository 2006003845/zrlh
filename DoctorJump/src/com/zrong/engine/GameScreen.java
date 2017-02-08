package com.zrong.engine;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.util.Vector;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.view.KeyEvent;
import android.view.MotionEvent;

public abstract class GameScreen {

	public static int screenW;
	public static int screenH;
	protected Context context;
	protected Paint paint;
	protected static Random rand = new Random();
	private InputStream is;

	public GameScreen(Context context) {
		this.context = context;
		paint = new Paint(Paint.ANTI_ALIAS_FLAG);// 无锯齿
		paint.setTextSize(15);
		paint.setStyle(Style.FILL);
	}

	public Bitmap getBitmap(String fileName) {
		try {
			is = context.getAssets().open(fileName);
			if (is == null)
				throw new NullPointerException("file path is error");
			return BitmapFactory.decodeStream(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	public abstract void update();

	public abstract void draw(Canvas canvas);

	public abstract boolean onTouchEvent(MotionEvent event);

	public abstract boolean onKeyDown(int keyCode, KeyEvent event);

	public abstract boolean onKeyUp(int keyCode, KeyEvent event);

	public void setColor(int color) {
		int red = (color & 0xff0000) >> 16;
		int green = (color & 0x00ff00) >> 8;
		int blue = (color & 0x0000ff);
		paint.setColor(Color.rgb(red, green, blue));
	}

	public void postInvalidate() {
		if (GameView.getInstance() != null)
			GameView.getInstance().postInvalidate();
	}

	public static String[] toRowString(String src, int width, Paint paint) {
		if (src == null)
			return null;
		Vector<String> rows = new Vector<String>();
		char ch;
		String tmp = "";
		for (int i = 0; i < src.length(); i++) {
			ch = src.charAt(i);
			if (ch == '\n') {
				if (tmp.length() > 0) {
					rows.addElement(tmp + "\n");
					tmp = "";
				}
			} else {
				if (width < paint.measureText("" + ch)) {
					width = (int) paint.measureText("" + ch);
				}
				if (paint.measureText(tmp + ch) > width) {
					rows.addElement(tmp);
					tmp = "" + ch;
				} else {
					tmp += ch;
				}
			}
		}
		if (tmp.length() > 0) {
			// 添加没有结束的字符串
			rows.addElement(tmp);
		}
		String[] rs = new String[rows.size()];
		rows.copyInto(rs);
		tmp = null;
		rows = null;
		System.gc();
		return rs;
	}

	public void drawBorder(Canvas canvas, String str, float left, float top,
			Paint paint) {
		paint.setColor(Color.BLACK);
		canvas.drawText(str, left + 1, top, paint);
		canvas.drawText(str, left - 1, top, paint);
		canvas.drawText(str, left, top + 1, paint);
		canvas.drawText(str, left, top - 1, paint);
		paint.setColor(Color.WHITE);
		canvas.drawText(str, left, top, paint);
	}

	public int getWidth() {
		return screenW;
	}

	public int getHeight() {
		return screenH;
	}

}
