package cn.zrong.loader;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import cn.zrong.utils.LocalMemory;
import cn.zrong.utils.Tools;

public class ImageCache {
	private static final Map<String, Bitmap> imgCache = new HashMap<String, Bitmap>();
	private ExecutorService executorService = Executors.newFixedThreadPool(3);
	private LocalMemory localMemory = new LocalMemory();
	private Handler handler;

	public Bitmap getImgCach(String url) throws MalformedURLException,
			IOException {
		String fileName = Tools.getFileNameFromUrl(url);
		if (fileName == null) {
			return null;
		}
		Bitmap bm = imgCache.get(fileName);
		if (bm == null) {
			bm = localMemory.getBitmap(fileName, LocalMemory.OTHER);
			if (bm == null) {
				bm = BitmapFactory.decodeStream(new URL(url).openStream()); // 获取图片
				if (bm != null) {
					setImgKey(url, bm);
				}
			}
		}
		return bm;
	}

	public Bitmap getImgCach2(String url) {
		String fileName = Tools.getFileNameFromUrl(url);
		if (fileName == null) {
			return null;
		}
		Bitmap bm = imgCache.get(fileName);
		if (bm == null) {
			bm = localMemory.getBitmap(fileName, LocalMemory.OTHER);
		}
		return bm;
	}

	public void setImgKey(String key, Bitmap bm) {
		String fileName = Tools.getFileNameFromUrl(key);
		if (fileName == null) {
			return;
		}
		imgCache.put(fileName, bm);
		AsyncImageSaver.getInstance()
				.saveImage(bm, fileName, LocalMemory.OTHER); // 异步保存至本地
	}

	public void setHandler(Handler h) {
		this.handler = h;
	}

	public void clear() {
		Set<String> keySet = imgCache.keySet();
		for (String string : keySet) {
			Bitmap bm = imgCache.get(string);
			if (bm != null && !bm.isRecycled()) {
				bm.recycle();
			}
		}
		imgCache.clear();
		System.gc();
	}

	public static ImageCache getInstance() {
		if (mInstance == null) {
			mInstance = new ImageCache();
		}
		return mInstance;
	}

	private static ImageCache mInstance;

	private Bitmap bm;

	public void loadImg(Context context, final String url, final ImageView imgv) {
		final String fileName = Tools.getFileNameFromUrl(url);
		if (fileName == null) {

			return;
		}
		bm = imgCache.get(fileName);
		if (bm != null && imgv != null) {
			imgv.setImageBitmap(bm);
			imgv.postInvalidate();

		} else {
			// bm = getBitmap(context, picName);

			// 否则开新线程从本地或者网络地址加载图像
			executorService.submit(new Runnable() {
				Bitmap bitmap = null;

				@Override
				public void run() {
					bitmap = localMemory.getBitmap(fileName, LocalMemory.OTHER);
					if (bitmap == null) {
						// 若本地没有指定图片，从网上下载
						try {
							bitmap = BitmapFactory.decodeStream(new URL(url)
									.openStream()); // 获取图片
							if (bitmap != null) {
								imgCache.put(fileName, bitmap);
								bm = bitmap;
							}
							if (bm != null && imgv != null) {

								// hanlder在主线程加载图像
								handler.post(new Runnable() {

									@Override
									public void run() {
										imgv.setImageBitmap(bm);
										imgv.postInvalidate();
									}
								});
							}
							AsyncImageSaver.getInstance().saveImage(bitmap,
									fileName, LocalMemory.OTHER); // 异步保存至本地
						} catch (MalformedURLException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			});

		}
	}

	public Bitmap loadImg(Context context, final String url,
			final ImageView imgv, final BaseAdapter adapter) {
		final String fileName = Tools.getFileNameFromUrl(url);
		if (fileName == null) {

			return null;
		}
		bm = imgCache.get(fileName);
		if (bm != null && imgv != null) {
			imgv.setImageBitmap(bm);
			imgv.postInvalidate();
			adapter.notifyDataSetChanged();
			return bm;
		} else {
			// bm = getBitmap(context, picName);

			// 否则开新线程从本地或者网络地址加载图像
			executorService.submit(new Runnable() {
				Bitmap bitmap = null;

				@Override
				public void run() {
					bitmap = localMemory.getBitmap(fileName, LocalMemory.OTHER);
					if (bitmap == null) {
						// 若本地没有指定图片，从网上下载
						try {
							bitmap = BitmapFactory.decodeStream(new URL(url)
									.openStream()); // 获取图片
							if (bitmap != null) {
								imgCache.put(fileName, bitmap);
								bm = bitmap;
								AsyncImageSaver.getInstance().saveImage(bitmap,
										fileName, LocalMemory.OTHER); // 异步保存至本地
							}

						} catch (MalformedURLException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					if (bm != null && imgv != null) {

						// hanlder在主线程加载图像
						handler.post(new Runnable() {

							@Override
							public void run() {
								imgv.setImageBitmap(bm);
								imgv.postInvalidate();
								adapter.notifyDataSetChanged();
							}
						});
					}
				}
			});
			return bm;
		}
	}

	private static Bitmap getBitmap(Context context, String fileName) {
		InputStream is = null;
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
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
