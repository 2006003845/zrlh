package com.zzl.zl_app.cache;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.v4.app.LoaderManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.zzl.zl_app.act.base.MyToast;
import com.zzl.zl_app.util.Tools;

public class ImageCache {

	LoaderManager loaderManger;
	// private static final Map<String, Bitmap> imgCache = new HashMap<String,
	// Bitmap>();
	// private BitmapCache bitCache;
	// private ExecutorService executorService =
	// Executors.newFixedThreadPool(5);
	// int cpuNums = Runtime.getRuntime().availableProcessors();
	// 获取当前系统的CPU 数目
	private ExecutorService executorService = Executors.newFixedThreadPool(3);

	private LocalMemory localMemory = LocalMemory.getInstance();
	private Handler handler;

	/**
	 * 同步获取 图片
	 * 
	 * @param context
	 * @param url
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public Bitmap getUnSyncImgCach(Context context, String url)
			throws MalformedURLException, IOException {
		String fileName = Tools.getFileNameFromUrl(url);
		if (fileName == null) {
			return null;
		}
		String dirName = Tools.getDirNameFromUrl(url);
		fileName = dirName + "_" + fileName;

		Bitmap bm = BitmapCache.getInstance().getBitmap(fileName);
		if (bm == null) {
			bm = localMemory.getBitmap(fileName);
			if (bm == null) {
				try {
					bm = BitmapFactory.decodeStream(new PatchInputStream(
							new URL(url).openStream()));
				} catch (OutOfMemoryError e) {
					Log.e("error", "OutOfMemoryError");
				}
				// 获取图片
				if (bm != null) {
					setImgKey(url, bm);
				}
			}
		}
		return bm;
	}

	private Bitmap getImgCach2(String fileName) {
		return BitmapCache.getInstance().getBitmap(fileName);
	}

	public void setImgKey(String key, Bitmap bm) {
		String fileName = Tools.getFileNameFromUrl(key);
		if (fileName == null) {
			return;
		}
		String dirName = Tools.getDirNameFromUrl(key);
		fileName = dirName + "_" + fileName;
		BitmapCache.getInstance().addCacheBitmap(bm, fileName);
		AsyncImageSaver.getInstance().saveImage(bm, fileName); // 异步保存至本地
	}

	public void setHandler(Handler h) {
		this.handler = h;
	}

	public void clear() {
		BitmapCache.getInstance().clearCache();
	}

	public void cleanCache() {
		BitmapCache.getInstance().clearCache();
	}

	public void clear(String url) {
		String fileNa = Tools.getFileNameFromUrl(url);
		if (fileNa == null) {
			return;
		}
		String dirName = Tools.getDirNameFromUrl(url);
		String key = dirName + "_" + fileNa;
		BitmapCache.getInstance().clearCache(key);
	}

	public static ImageCache getInstance() {
		if (mInstance == null) {
			mInstance = new ImageCache();
		}
		return mInstance;
	}

	private ImageCache() {
	}

	private static ImageCache mInstance;

	/**
	 * 获取图片资源
	 * 
	 * @param url
	 *            图片的网络地址
	 * @param imgv
	 * @param defaultImgResId
	 *            默认图片的resId
	 */
	public void loadImg(final String url, final ImageView imgv,
			final int defaultImgResId) {
		String fileNa = Tools.getFileNameFromUrl(url);
		if (fileNa == null) {
			return;
		}
		String dirName = Tools.getDirNameFromUrl(url);
		final String fileName = dirName + "_" + fileNa;
		Bitmap bm = getImgCach2(fileName);
		if (bm != null && imgv != null) {
			imgv.setImageBitmap(bm);
			imgv.postInvalidate();
		} else {
			executorService.execute(new Runnable() {
				Bitmap bitmap = null;

				@Override
				public void run() {
					bitmap = localMemory.getBitmap(fileName);
					if (bitmap == null) {
						// 若本地没有指定图片，从网上下载
						try {
							bitmap = BitmapFactory
									.decodeStream(new PatchInputStream(new URL(
											url).openStream()));
							if (bitmap != null) {
								// imgCache.put(fileName, bitmap);
								BitmapCache.getInstance().addCacheBitmap(
										bitmap, fileName);
								AsyncImageSaver.getInstance().saveImage(bitmap,
										fileName); // 异步保存至本地
							}
						} catch (OutOfMemoryError e) {
							Log.e("error", "OutOfMemoryError");
						} catch (MalformedURLException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else {
						BitmapCache.getInstance().addCacheBitmap(bitmap,
								fileName);
					}
					handler.postDelayed(new Runnable() {

						@Override
						public void run() {
							if (imgv == null)
								return;
							if (bitmap != null) {
								imgv.setImageBitmap(bitmap);
								imgv.postInvalidate();
							} else {
								imgv.setImageResource(defaultImgResId);
								imgv.postInvalidate();
							}
						}
					}, 50);
				}
			});
		}
	}

	public void loadImg(final String url, final ImageView imgv,
			final ProgressBar pb) {
		if (pb != null) {
			pb.setVisibility(View.VISIBLE);
		}
		String fileNa = Tools.getFileNameFromUrl(url);
		if (fileNa == null) {
			return;
		}
		String dirName = Tools.getDirNameFromUrl(url);
		final String fileName = dirName + "_" + fileNa;
		Bitmap bm = getImgCach2(fileName);
		if (bm != null && imgv != null) {
			imgv.setImageBitmap(bm);
			imgv.postInvalidate();
			if (pb != null) {
				pb.setVisibility(View.GONE);
			}
			bm = null;
		} else {
			// 否则打开线程从本地或者网络地址下载图片
			executorService.submit(new Runnable() {
				Bitmap bitmap = null;

				@Override
				public void run() {
					bitmap = localMemory.getBitmap(fileName);
					if (bitmap == null) {
						// 若本地没有指定图片，从网上下载
						try {
							bitmap = BitmapFactory
									.decodeStream(new PatchInputStream(new URL(
											url).openStream()));
							if (bitmap != null) {
								BitmapCache.getInstance().addCacheBitmap(
										bitmap, fileName);
								AsyncImageSaver.getInstance().saveImage(bitmap,
										fileName); // 异步保存至本地
							}
						} catch (OutOfMemoryError e) {
							Log.e("error", "OutOfMemoryError");
						} catch (MalformedURLException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else {
						BitmapCache.getInstance().addCacheBitmap(bitmap,
								fileName);
					}
					if (bitmap != null) {

						// hanlder在主线程加载图像
						handler.post(new Runnable() {

							@Override
							public void run() {
								if (imgv != null) {
									imgv.setImageBitmap(bitmap);
									imgv.postInvalidate();
								}
								if (pb != null) {
									pb.setVisibility(View.GONE);
								}
								bitmap = null;
							}
						});
					}
				}
			});
		}
	}

	public void loadImgAndSave(final Context context, final String url,
			final boolean isShowToast) {
		String fileNa = Tools.getFileNameFromUrl(url);
		if (fileNa == null) {
			return;
		}
		String dirName = Tools.getDirNameFromUrl(url);
		final String fileName = dirName + "_" + fileNa;
		Bitmap bm = getImgCach2(fileName);

		if (bm != null && isShowToast) {
			handler.post(new Runnable() {

				@Override
				public void run() {
					MyToast.getToast().showToast(
							context,
							"图片保存于:" + FileConstant.savePath
									+ FileConstant.Path_Img + "/" + fileName);
				}
			});
		} else
			executorService.submit(new Runnable() {
				Bitmap bitmap = null;

				@Override
				public void run() {
					bitmap = localMemory.getBitmap(fileName);
					if (bitmap == null) {
						// 若本地没有指定图片，从网上下载
						try {
							bitmap = BitmapFactory
									.decodeStream(new PatchInputStream(new URL(
											url).openStream()));
							if (bitmap != null) {
								BitmapCache.getInstance().addCacheBitmap(
										bitmap, fileName);
								AsyncImageSaver.getInstance().saveImage(bitmap,
										fileName);
							}
						} catch (MalformedURLException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					if (bitmap != null && isShowToast) {
						handler.post(new Runnable() {

							@Override
							public void run() {

								MyToast.getToast().showToast(
										context,
										"图片保存于:" + FileConstant.savePath
												+ FileConstant.Path_Img + "/"
												+ fileName);
							}
						});
					}
				}
			});
	}

}
