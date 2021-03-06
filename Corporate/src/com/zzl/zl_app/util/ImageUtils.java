package com.zzl.zl_app.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;

public class ImageUtils {
	private static BitmapFactory.Options opt = new BitmapFactory.Options();

	/**
	 * 从assert中读取图片资源
	 * 
	 * @param context
	 * @param fileName
	 * @return
	 */
	public static Bitmap getBitmap(Context context, String fileName) {

		opt.inPreferredConfig = Bitmap.Config.RGB_565;
		opt.inPurgeable = true;
		opt.inInputShareable = true;

		InputStream is = null;
		try {
			is = context.getAssets().open(fileName);
			if (is == null)
				throw new NullPointerException("file path is error");
			return BitmapFactory.decodeStream(is, null, opt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * get circular bead bitmap
	 * 
	 * @param bitmap
	 * @param radix
	 *            圆角比例系数>1
	 * @return Bitmap
	 */
	public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int radix) {
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		final int color = 0xff424242;
		final Paint paint = new Paint();
		int w = bitmap.getWidth();
		int h = bitmap.getHeight();
		final Rect rect = new Rect(0, 0, w, h);
		final RectF rectF = new RectF(rect);
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		float roundPx = w / radix;
		float roundPy = h / radix;
		canvas.drawRoundRect(rectF, roundPx, roundPy, paint);

		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		return output;
	}

	public static Bitmap safeDecodeStream(Context context, String url, int scale)
			throws MalformedURLException, IOException {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inSampleSize = scale;
		options.inJustDecodeBounds = false;
		URL uUL = new URL(url);
		// 取得链接
		URLConnection conn = uUL.openConnection();
		conn.connect();
		// 取得返回的InputStream
		InputStream is = conn.getInputStream();
		InputStream stream = new BufferedInputStream(is, 300 * 1024);
		// while(){
		//
		// }
		Bitmap bm = BitmapFactory.decodeStream(stream, null, options);
		stream.close();
		return bm;
	}

	public static Bitmap getScaleBitmap(Uri uri, Context context) {
		InputStream is = null;
		try {
			is = context.getContentResolver().openInputStream(uri);
			if (is == null) {
				return null;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		// PatchInputStream pis = new PatchInputStream(is);
		// 获取这个图片的宽和高
		BitmapFactory.decodeStream(is, null, options);// 此时返回bm为空
		// 计算缩放比
		int be = (int) (options.outHeight / (float) 100);
		if (be <= 0)
			be = 1;
		options.inSampleSize = be;
		options.inJustDecodeBounds = false;
		try {
			is = context.getContentResolver().openInputStream(uri);
			if (is == null) {
				return null;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 重新读入图片，注意这次要把options.inJustDecodeBounds设为false哦
		Bitmap bm = BitmapFactory.decodeStream(is, null, options);

		return bm;
	}

	/**
	 * 旋转
	 * 
	 * @param b
	 * @param degrees
	 * @return
	 */
	public static Bitmap rotate(Bitmap b, int degrees) {
		if (degrees != 0 && b != null) {
			Matrix m = new Matrix();
			m.setRotate(degrees, (float) b.getWidth() / 2,
					(float) b.getHeight() / 2);
			try {
				Bitmap b2 = Bitmap.createBitmap(b, 0, 0, b.getWidth(),
						b.getHeight(), m, true);
				if (b != b2) {
					// b.recycle();
					b = b2;
				}
			} catch (OutOfMemoryError ex) {
				// We have no memory to rotate. Return the original bitmap.
			}
		}
		return b;
	}

	/**
	 * 
	 * @param uri
	 * @return
	 */
	public static Bitmap decodeUriAsBitmap(Uri uri, Context context) {
		Bitmap bitmap = null;
		try {
			bitmap = BitmapFactory.decodeStream(context.getContentResolver()
					.openInputStream(uri));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		return bitmap;
	}

	/**
	 * 
	 * @param uri
	 * @return
	 */
	public static byte[] decodeUri2Bytes(Uri uri, Context context) {
		try {
			return streamToBytes(context.getContentResolver().openInputStream(
					uri));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * bitmap -- >> byte[]
	 * 
	 * @param bm
	 * @return byte[]
	 */
	public static byte[] Bitmap2Bytes(Bitmap bm) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
		return baos.toByteArray();
	}

	/**
	 * Stream 2 byte[]
	 * 
	 * @param is
	 * @return byte[]
	 */
	public static byte[] streamToBytes(InputStream is) {
		ByteArrayOutputStream os = new ByteArrayOutputStream(1024);
		byte[] buffer = new byte[1024];
		int len;
		try {
			while ((len = is.read(buffer)) >= 0) {
				os.write(buffer, 0, len);
			}
		} catch (java.io.IOException e) {
		}
		return os.toByteArray();
	}

	/**
	 * byte[] 2 Bitmap
	 * 
	 * @param b
	 * @return BitMap
	 */
	public static Bitmap Bytes2Bitmap(byte[] b) {
		if (b.length != 0) {
			return BitmapFactory.decodeByteArray(b, 0, b.length);
		} else {
			return null;
		}
	}
}
