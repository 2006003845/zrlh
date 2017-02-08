package javax.microedition.lcdui;

import java.io.IOException;
import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.util.Log;
import cn.zrong.activity.HomeActivity;

public class Image {
	public String name;

	public static Image createImage(InputStream stream) throws IOException {
		Bitmap bitmap = BitmapFactory.decodeStream(stream);

		return new Image(bitmap);
	}

	public static Image createImage(int width, int height) {
		Bitmap bitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		return new Image(bitmap);
	}

	public static Image createImage(String name) throws IOException {
		Image image = createImage(getResourceAsStream(name));
		if (image != null) {
			image.name = name;
		}
		return createImage(getResourceAsStream(name));
	}

	public static Image createImage(String name, InputStream stream)
			throws IOException {
		Image image = createImage(stream);
		if (image != null) {
			image.name = name;
		}
		return image;
	}

	public static Image createImage(byte[] imageData, int imageOffset,
			int imageLength) {
		Bitmap bitmap = BitmapFactory.decodeByteArray(imageData, imageOffset,
				imageLength);

		return new Image(bitmap);
	}

	public static final Image createRGBImage(int[] rgb, int width, int height,
			boolean processAlpha) {
		Bitmap.Config config;
		if (processAlpha) {
			config = Bitmap.Config.ARGB_8888;
		} else {
			config = Bitmap.Config.RGB_565;
		}
		Bitmap bitmap = Bitmap.createBitmap(rgb, width, height, config);
		return new Image(bitmap);
	}

	private Bitmap bitmap;

	public Image(Bitmap bitmap) {
		this.bitmap = bitmap;
	}

	public Bitmap getBitmap() {
		return this.bitmap;
	}

	public int getWidth() {
		return this.bitmap.getWidth();
	}

	public int getHeight() {
		return this.bitmap.getHeight();
	}

	public Graphics getGraphics() {
		return new Graphics(this.bitmap);
	}

	/**
	 * Obtains ARGB pixel data from the specified region of this image and
	 * stores it in the provided array of integers. Each pixel value is stored
	 * in 0xAARRGGBB format, where the high-order byte contains the alpha
	 * channel and the remaining bytes contain color components for red, green
	 * and blue, respectively.
	 * 
	 * @param rgb
	 *            data[] an array of integers in which the ARGB pixel data is
	 *            stored.
	 * @param offset
	 *            the index into the array where the first ARGB value is stored.
	 * @param scanlength
	 *            the relative offset in the array between corresponding pixels
	 *            in consecutive rows of the region.
	 * @param x
	 *            the x-coordinate of the upper left corner of the region.
	 * @param y
	 *            the y-coordinate of the upper left corner of the region.
	 * @param width
	 *            the width of the region.
	 * @param height
	 *            the height of the region.
	 */
	public void getRGB(int[] rgb, int offset, int scanlength, int x, int y,
			int width, int height) {
		if (bitmap == null)
			return;
		this.bitmap.getPixels(rgb, offset, scanlength, x, y, width, height);
	}

	public static Image createImage(Image src, int xSrc, int ySrc, int width,
			int height, int transform) {
		// TODO Auto-generated method stub
		return null;
	}

	public static InputStream getResourceAsStream(String name)
			throws IOException {
		Log.d("Load", "Assert " + name + " loaded.");
		InputStream is = null;
		if (name.startsWith("/")) {
			try {
				is = HomeActivity.mInstance.getAssets().open(name.substring(1));
			} catch (Exception e) {
				throw new IOException();
			}
		} else {
			return getResourceAsStream("/" + name);
		}
		return is;
	}

	public void clear() {
		if (bitmap != null)
			bitmap.recycle();
		bitmap = null;
	}

}
