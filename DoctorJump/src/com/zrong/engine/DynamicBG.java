package com.zrong.engine;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.util.Vector;

import com.zrong.entity.Soul;
import com.zrong.game.Sprite;
import com.zrong.utils.BitmapUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

public class DynamicBG {
	/**
	 * ����Ч��
	 */
	public static final byte RAIN = 0;// ��
	/**
	 * ��ѩЧ��
	 */
	public static final byte SNOW = 1;// ѩ
	/**
	 * ��ҶЧ��
	 */
	public static final byte LEAVES = 2;// ��Ҷ
	/**
	 * ���
	 */
	public static final byte SOUL = 3;
	public static final byte NOTHING = 4;
	public byte Dynamic_Type;
	public Context context;

	private Bitmap[] bms = new Bitmap[3];

	public int width = 0;
	public int height = 0;

	private DynamicBG(Context context, int type, int width, int height) {
		this.context = context;
		Dynamic_Type = (byte) type;
		this.width = width;
		this.height = height;
		init();
	}

	// private DynamicBG(Context context, int type, int width, int height,
	// float location) {
	// this.context = context;
	// this.location = location;
	// Dynamic_Type = (byte) type;
	// this.width = width;
	// this.height = height;
	// init();
	// }

	/**
	 * 
	 * @param context
	 * @param type
	 *            ��̬������������
	 * @param width
	 * @param height
	 * @return
	 */
	public static DynamicBG createDynamicBG(Context context, int type,
			int width, int height) {
		return new DynamicBG(context, type, width, height);
	}

	// /**
	// *
	// * @param context
	// * @param type
	// * ��̬������������
	// * @param width
	// * @param height
	// * @param location
	// * @return
	// */
	// public static DynamicBG createDynamicBG(Context context, int type,
	// int width, int height, float location) {
	// return new DynamicBG(context, type, width, height, location);
	// }

	public void init() {
		switch (Dynamic_Type) {
		case RAIN:
			break;
		case SNOW:
			bms[0] = getBitmap("snow0.png");
			bms[1] = getBitmap("snow1.png");
			bms[2] = getBitmap("snow2.png");
			break;
		case LEAVES:
			bms[0] = getBitmap("leaves.png");
			break;

		case SOUL:
			bms[0] = getBitmap("soul1.png");
			bms[1] = getBitmap("soul2.png");
			break;
		}
	}

	public void update(boolean isAdd, float location) {
		switch (Dynamic_Type) {
		case RAIN:
			break;
		case SNOW:
			updateSnow();
			break;
		case LEAVES:
			updateWind();
			break;
		case SOUL:
			updateSoul(isAdd, location);
			break;
		case NOTHING:
			break;
		}
	}

	public void draw(Canvas canvas) {
		switch (Dynamic_Type) {
		case RAIN:
			Paint p = new Paint();
			p.setAntiAlias(true); // �������
			drawRain(canvas, p);
			break;
		case SNOW:
			drawSnow(canvas);
			break;
		case LEAVES:
			drawLeaves(canvas);
			break;
		case SOUL:
			drawSoul(canvas);
			break;
		case NOTHING:
			break;
		}
	}

	public Random r = null;

	/**
	 * �漴����������
	 * 
	 * @param max
	 *            ��ʾ��������������ܴ������ֵ
	 * @return �����������
	 */
	public int getRandom(int max) {
		if (r == null) {
			r = new Random();
			r.setSeed(System.currentTimeMillis());
		}

		return r.nextInt() % max;
	}

	/**
	 * ����,��ѩ������Ҷ(�η�)�ļ��ϵļ���
	 */
	public Vector weatherEle = new Vector();
	/**
	 * �����������ı���
	 */
	public long pre = 0;

	public void drawRain(Canvas canvas, Paint p) {
		int x, y;
		p.setColor(0xffffff);
		pre += 1;

		if (pre == 1) {
			weatherEle.removeAllElements();
		}

		for (int i = 0; i < 150; i++) {
			if (pre == 1) {
				x = getRandom(width);
				y = getRandom(height);
				weatherEle.addElement(new int[] { x, y });
			} else {
				int[] xy = (int[]) weatherEle.elementAt(i);
				x = xy[0];
				y = xy[1];
			}
			canvas.drawLine(x, y, x - 1, y + 8, p);
		}

		if (pre > 2) {
			pre = 0;
		}
	}

	public Vector<Soul> soulEle = new Vector<Soul>();

	/**
	 * ���
	 */
	public void updateSoul(boolean isAddSoul, float location) {
		if (isAddSoul) {

			soulEle.addElement(new Soul(bms[getRandom(1)], 100, (int) location,
					height));
		}
		for (int i = 0; i < soulEle.size(); i++) {
			Soul s = soulEle.elementAt(i);
			s.y -= 6;
			s.alphNum -= 1;
			if (s.alphNum == 0) {
				soulEle.removeElementAt(i);
			}
		}
	}

	public void drawSoul(Canvas canvas) {
		for (int i = 0; i < soulEle.size(); i++) {
			Soul s = soulEle.elementAt(i);
			p.setAlpha(s.alphNum);
			canvas.drawBitmap(s.soulBm, s.x, s.y, p);
		}
	}

	/**
	 * �η��߼�
	 */
	public void updateWind() {
		// ������Ҷ,ÿ֡����0��9����Ҷ

		int num = 0;
		if (((pre++) & 3) == 0) {
			num = getRandom(2);

		}

		for (int i = 0; i < num; i++) {
			int x = getRandom(width);
			weatherEle.addElement(new int[] { x, 0 });
		}

		for (int i = 0; i < weatherEle.size(); i++) {
			int[] xy = (int[]) weatherEle.elementAt(i);
			xy[0] -= 1.5;
			xy[1] += 1;
			// ���������ֵ�
			int s = getRandom(2);
			if (s == 0) {
				xy[0] += 2;
			}

			if (xy[1] > height + 20 || xy[0] < -10) {

				weatherEle.removeElementAt(i);
			}
		}
	}

	public void drawLeaves(Canvas canvas) {

		for (int i = 0; i < weatherEle.size(); i++) {
			int[] xy = (int[]) weatherEle.elementAt(i);
			Bitmap bm = bms[0];
			bind(bm, bm.getWidth() / 5, bm.getHeight(), 50);
			if (sprite != null) {
				sprite.draw(canvas, xy[0], xy[1]);
				sprite.update();
			}
		}
	}

	/**
	 * ��ѩ�߼�
	 */
	public void updateSnow() {
		// ����ѩ��,ÿ֡����0��4��ѩ��

		int num = 0;
		if (((pre++) & 3) == 0) {
			num = getRandom(2);
		}

		for (int i = 0; i < num; i++) {
			int x = getRandom(width);
			int index = getRandom(2);
			weatherEle.addElement(new int[] { x, 0, index });
		}

		for (int i = 0; i < weatherEle.size(); i++) {
			int[] xy = (int[]) weatherEle.elementAt(i);
			xy[1] += 3;
			if (xy[1] > height + 20) {

				weatherEle.removeElementAt(i);
			}
		}
	}

	Paint p = new Paint();

	/**
	 * ������ѩ
	 * 
	 * @param g
	 */
	public void drawSnow(Canvas canvas) {

		for (int i = 0; i < weatherEle.size(); i++) {
			int[] xy = (int[]) weatherEle.elementAt(i);
			int index = xy[2];
			Bitmap bm = bms[index < bms.length && index >= 0 ? index
					: bms.length - 1];
			bind(bm, bm.getWidth() / 3, bm.getHeight(), 50);
			if (sprite != null) {
				sprite.draw(canvas, xy[0], xy[1]);
				sprite.update();
			}
			// else {
			// canvas.drawBitmap(bm, xy[0], xy[1], p);
			// }
		}
	}

	private InputStream is;

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

	protected Sprite sprite;

	public void bind(Bitmap bitmap, int frameWidth, int frameHeight, int delay) {
		// TODO Auto-generated method stub
		sprite = new Sprite(bitmap, frameWidth, frameHeight);
		sprite.setDelay(delay);
	}
}
