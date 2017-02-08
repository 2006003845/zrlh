package com.zrong.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

public class Sprite extends Layer {

	private int frameIndex;
	private int[] frames;
	private int frameW;
	private int frameH;
	private Rect src;
	private RectF dst;
	private int delay;
	private int delayStep;

	public Sprite(Bitmap bitmap, int frameW, int frameH) {
		// TODO Auto-generated constructor stub
		if (bitmap.getWidth() % frameW != 0 || bitmap.getHeight() % frameH != 0)
			throw new IllegalArgumentException("Not exactly divisible by");
		this.bitmap = bitmap;
		this.frameW = frameW;
		this.frameH = frameH;
		int col = bitmap.getWidth() / frameW;
		int row = bitmap.getHeight() / frameH;
		frames = new int[row * col];
		src = new Rect();
		dst = new RectF();
	}

	@Override
	public void draw(Canvas canvas, float x, float y) {
		if (!isLoop && frameIndex >= getFrameCount()) {
			return;
		}
		// TODO Auto-generated method stub
		this.x = x;
		this.y = y;
		src.left = frameW * frameIndex;
		src.top = 0;
		src.right = frameW * (frameIndex + 1);
		src.bottom = frameH;

		dst.left = x;
		dst.top = y;
		dst.right = dst.left + frameW;
		dst.bottom = dst.top + frameH;
		canvas.drawBitmap(bitmap, src, dst, null);
	}

	@Override
	public void update() {

		if (delay != 0 && ++delayStep < delay) {
			return;
		}
		delayStep = 0;
		nextFrame();
	}

	public void setFrame(int index) {
		if (index < 0 || index >= getFrameCount())
			throw new IllegalArgumentException();
		this.frameIndex = index;
	}

	public int getFrame() {
		return frameIndex;
	}

	public void nextFrame() {
		if (!isLoop) {
			++frameIndex;
		} else {
			frameIndex = ++frameIndex < getFrameCount() ? frameIndex : 0;
		}
	}

	private boolean isLoop = true;

	public void setLoop(boolean isLoop) {
		this.isLoop = isLoop;
	}

	public int getFrameCount() {
		return frames.length;
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

}
