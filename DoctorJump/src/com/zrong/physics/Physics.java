package com.zrong.physics;

import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;

import com.zrong.game.Sprite;

public abstract class Physics {

	public static final float DEG_TO_RAD = 0.0174532925f;
	public static final float RAD_TO_DEG = 57.2957795f;
	protected Paint paint;
	protected Matrix matrix;
	protected Path path;
	protected Body body;
	protected BodyDef bodyDef;
	protected float[] vertices;
	protected int drawColor = 0x000000;
	protected Sprite sprite;
	protected Bitmap bitmap;

	protected Physics() {
		bodyDef = new BodyDef();
		matrix = new Matrix();
		paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		setColor(drawColor);
	}

	public int getDrawColor() {
		return drawColor;
	}

	public void setDrawColor(int drawColor) {
		this.drawColor = drawColor;
	}

	public void setColor(int color) {
		int red = (color & 0xff0000) >> 16;
		int green = (color & 0x00ff00) >> 8;
		int blue = (color & 0x0000ff);
		paint.setColor(Color.rgb(red, green, blue));
	}

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}

	public BodyDef getBodyDef() {
		return bodyDef;
	}

	public void setBodyDef(BodyDef bodyDef) {
		this.bodyDef = bodyDef;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public abstract void draw(Canvas canvas);

	public abstract void update();

	public abstract void bind(Bitmap bitmap, int frameWidth, int frameHeight,
			int delay);

	public abstract void bind(Bitmap bitmap);

}
