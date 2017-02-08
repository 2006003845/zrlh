package com.zrong.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public abstract class Layer {

	protected float x;
	protected float y;
	protected float width;
	protected float height;
	protected Bitmap bitmap;

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public Bitmap getBitmap() {
		return bitmap;
	}

	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}

	public abstract void draw(Canvas canvas, float x, float y);

	public abstract void update();

}
