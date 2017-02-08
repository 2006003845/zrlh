package cn.zrong.loader;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import cn.zrong.entity.GameItem;
import cn.zrong.utils.LocalMemory;
import cn.zrong.utils.TextUtil;
import cn.zrong.utils.Tools;
import cn.zrong.widget.ImageZoomView;
import cn.zrong.widget.MyDrawable;

/**
 * 异步加载图像<br>
 * 图像保存规则：<br>
 * 保存头像：sdcard-pina-portrait 以用户id命名<br>
 * 保存微博图片：sdcard-pina-pre 以图片的url的最后一段命名
 * 
 * @author starry
 * 
 */
public class AsyncImageLoader {
	private ExecutorService executorService = Executors.newFixedThreadPool(3);
	private Map<String, SoftReference<Drawable>> imageCache = new HashMap<String, SoftReference<Drawable>>();
	private Map<String, SoftReference<MyDrawable>> imageCache2 = new HashMap<String, SoftReference<MyDrawable>>();
	private Handler handler = new Handler();
	private LocalMemory localMemory = new LocalMemory();

	private static AsyncImageLoader asyncImageLoader;

	public static AsyncImageLoader getInstance() {
		if (asyncImageLoader == null) {
			asyncImageLoader = new AsyncImageLoader();
		}
		return asyncImageLoader;
	}

	public SoftReference<Drawable> getImgDrawable(String key) {
		if (imageCache.containsKey(key)) // 图像存在软引用中，直接加载
			return imageCache.get(key);
		return null;
	}

	/**
	 * 加载头像
	 * 
	 * @param id
	 *            用户id
	 * @param url
	 *            头像url
	 * @param iv
	 *            头像ImageView
	 */
	public void loadPortrait(final String id, final String url,
			final ImageView iv) {
		if (null == url || "".equals(url)) {
			return;
		}
		final String fileName = Tools.getFileNameFromUrl(url);
		if (fileName == null) {
			return;
		}
		if (imageCache.containsKey(fileName)) { // 图像存在软引用中，直接加载
			SoftReference<Drawable> softReference = imageCache.get(fileName);
			if (softReference != null) {
				if (softReference.get() != null) {
					log("load portrait not null");
					iv.setImageDrawable(softReference.get());
					iv.invalidate();
				}
			}
		}
		// 否则开新线程从本地或者网络地址加载图像
		executorService.submit(new Runnable() {
			BitmapDrawable bitmapDrawable = null;

			@Override
			public void run() {
				bitmapDrawable = localMemory.getDrawable(fileName,
						LocalMemory.PORTRAIT);
				if (bitmapDrawable == null) { // 若本地没有指定图片，从网上下载
					try {
						bitmapDrawable = new BitmapDrawable(new URL(url)
								.openStream()); // 获取图片
						imageCache.put(fileName, new SoftReference<Drawable>(
								bitmapDrawable)); // 加入软引用
						AsyncImageSaver.getInstance().saveImage(bitmapDrawable,
								fileName, LocalMemory.PORTRAIT); // 异步保存至本地
					} catch (MalformedURLException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (bitmapDrawable != null) {
					// hanlder在主线程加载图像
					handler.post(new Runnable() {

						@Override
						public void run() {
							iv.setImageDrawable(bitmapDrawable);
							iv.invalidate();
						}
					});
				}
			}
		});
	}

	/**
	 * 加载图片
	 * 
	 * @param id
	 *            图片所在微博id
	 * @param url
	 *            图片url
	 * @param iv
	 *            显示图片的ImageView
	 */
	public void loadPre(final String id, final String url, final ImageView iv) {
		if (null == url || "".equals(url)) {
			iv.setVisibility(View.GONE);
			return;
		}
		final String fileName = Tools.getFileNameFromUrl(url);
		if (fileName == null) {
			iv.setVisibility(View.GONE);
			return;
		}
		iv.setVisibility(View.VISIBLE);
		if (imageCache.containsKey(fileName)) { // 图像存在在软引用中，直接加载
			SoftReference<Drawable> softReference = imageCache.get(fileName);
			if (softReference != null) {
				if (softReference.get() != null) {
					log("load pre null");
					iv.setImageDrawable(softReference.get());
					iv.invalidate();
				}
			}
		}
		// 否则开新线程从本地或者网络加载
		executorService.submit(new Runnable() {
			BitmapDrawable bitmapDrawable = null;

			@Override
			public void run() {
				bitmapDrawable = localMemory.getDrawable(fileName,
						LocalMemory.PRE);
				if (bitmapDrawable == null) { // 若本地没有指定图片，从网上下载
					try {
						bitmapDrawable = new BitmapDrawable(new URL(url)
								.openStream()); // 获取图片
						imageCache.put(fileName, new SoftReference<Drawable>(
								bitmapDrawable)); // 加入软引用
						AsyncImageSaver.getInstance().saveImage(bitmapDrawable,
								fileName, LocalMemory.PRE); // 异步保存至本地
					} catch (MalformedURLException e) {
						e.printStackTrace();
						log(e.toString());
					} catch (IOException e) {
						e.printStackTrace();
						log(e.toString());
					}
				}
				// handler在主线程加载图像
				handler.post(new Runnable() {

					@Override
					public void run() {
						iv.setImageDrawable(bitmapDrawable);
						iv.invalidate();
					}
				});
			}
		});

	}

	public void loadPre(final String id, final String url,
			final ImageZoomView iv) {
		if (null == url || "".equals(url)) {
			iv.setVisibility(View.GONE);
			return;
		}
		final String fileName = Tools.getFileNameFromUrl(url);
		if (fileName == null) {
			iv.setVisibility(View.GONE);
			return;
		}
		iv.setVisibility(View.VISIBLE);
		if (imageCache.containsKey(fileName)) { // 图像存在在软引用中，直接加载
			SoftReference<Drawable> softReference = imageCache.get(fileName);
			if (softReference != null) {
				if (softReference.get() != null) {
					log("load pre null");
					Drawable d = softReference.get();
					BitmapDrawable bd = (BitmapDrawable) d;
					iv.setImage(bd.getBitmap());
					return;
				}
			}
		}
		// 否则开新线程从本地或者网络加载
		executorService.submit(new Runnable() {
			BitmapDrawable bitmapDrawable = null;

			@Override
			public void run() {
				bitmapDrawable = localMemory.getDrawable(fileName,
						LocalMemory.PRE);
				if (bitmapDrawable == null) { // 若本地没有指定图片，从网上下载
					try {
						bitmapDrawable = new BitmapDrawable(new URL(url)
								.openStream()); // 获取图片
						imageCache.put(fileName, new SoftReference<Drawable>(
								bitmapDrawable)); // 加入软引用
						AsyncImageSaver.getInstance().saveImage(bitmapDrawable,
								fileName, LocalMemory.PRE); // 异步保存至本地
					} catch (MalformedURLException e) {
						e.printStackTrace();
						log(e.toString());
					} catch (IOException e) {
						e.printStackTrace();
						log(e.toString());
					}
				}
				// handler在主线程加载图像
				handler.post(new Runnable() {

					@Override
					public void run() {
						iv.setImage(bitmapDrawable.getBitmap());
					}
				});
			}
		});

	}

	public void loadGamePre(final String id, final String url,
			final ImageView iv, final GameItem item) {
		if (null == url || "".equals(url)) {
			iv.setVisibility(View.GONE);
			return;
		}
		final String fileName = Tools.getFileNameFromUrl(url);
		if (fileName == null) {
			iv.setVisibility(View.GONE);
			return;
		}
		iv.setVisibility(View.VISIBLE);
		if (imageCache.containsKey(fileName)) { // 图像存在在软引用中，直接加载
			SoftReference<Drawable> softReference = imageCache.get(fileName);
			if (softReference != null) {
				if (softReference.get() != null) {
					log("load pre null");
					item.gameDrawable = softReference.get();
					iv.setImageDrawable(softReference.get());
					iv.invalidate();
				}
			}
		}
		// 否则开新线程从本地或者网络加载
		executorService.submit(new Runnable() {
			BitmapDrawable bitmapDrawable = null;

			@Override
			public void run() {
				bitmapDrawable = localMemory.getDrawable(fileName,
						LocalMemory.PRE);
				if (bitmapDrawable == null) { // 若本地没有指定图片，从网上下载
					try {
						bitmapDrawable = new BitmapDrawable(new URL(url)
								.openStream()); // 获取图片
						imageCache.put(fileName, new SoftReference<Drawable>(
								bitmapDrawable)); // 加入软引用
						AsyncImageSaver.getInstance().saveImage(bitmapDrawable,
								fileName, LocalMemory.PRE); // 异步保存至本地
					} catch (MalformedURLException e) {
						e.printStackTrace();
						log(e.toString());
					} catch (IOException e) {
						e.printStackTrace();
						log(e.toString());
					}
				}
				// handler在主线程加载图像
				handler.post(new Runnable() {

					@Override
					public void run() {
						item.gameDrawable = bitmapDrawable;
						iv.setImageDrawable(bitmapDrawable);
						iv.invalidate();
					}
				});
			}
		});

	}

	void log(String msg) {
		Log.i("weibo", "AsyncImageLoader--" + msg);
	}

}
