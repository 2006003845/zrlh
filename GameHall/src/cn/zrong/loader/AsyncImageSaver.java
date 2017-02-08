package cn.zrong.loader;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import cn.zrong.utils.LocalMemory;

/**
 * 异步图像保存
 * 
 * @author starry
 * 
 */
public class AsyncImageSaver {
	private ExecutorService executorService = Executors
			.newSingleThreadExecutor();

	private static AsyncImageSaver asyncImageSaver;

	public static AsyncImageSaver getInstance() {
		if (asyncImageSaver == null) {
			asyncImageSaver = new AsyncImageSaver();
		}
		return asyncImageSaver;
	}

	public void saveImage(final Bitmap bitmap, final String name,
			final String cate) {
		executorService.submit(new Runnable() {

			@Override
			public void run() {
				new LocalMemory().saveBitmap(bitmap, name, cate);
			}
		});
	}

	public void saveImage(final BitmapDrawable bitmap, final String name,
			final String cate) {
		executorService.submit(new Runnable() {

			@Override
			public void run() {
				new LocalMemory().saveDrawable(bitmap, name, cate);
			}
		});
	}

}
