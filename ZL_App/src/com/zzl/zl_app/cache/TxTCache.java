package com.zzl.zl_app.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.os.Handler;
import android.support.v4.app.LoaderManager;
import android.util.Log;
import android.widget.TextView;

import com.zzl.zl_app.net_port.ConnectionCaller;
import com.zzl.zl_app.net_port.WSError;
import com.zzl.zl_app.util.Tools;

public class TxTCache {

	private static TxTCache txtCache;

	private TxTCache() {
	}

	public static TxTCache getInstance() {
		if (txtCache == null) {
			txtCache = new TxTCache();
		}
		return txtCache;
	}

	LoaderManager loaderManger;
	// 获取当前系统的CPU 数目
	private ExecutorService executorService = Executors.newFixedThreadPool(3);

	private LocalMemory localMemory = LocalMemory.getInstance();
	private Handler handler;

	public void setHandler(Handler h) {
		this.handler = h;
	}

	Map<String, String> cache = new HashMap<String, String>();

	/**
	 * 异步获取文本
	 * 
	 * @param txtUrl
	 * @param tv
	 */
	public void getSyncTxt(final String txtUrl, final TextView tv) {
		String fileName = Tools.getFileNameFromUrl(txtUrl);
		if (fileName == null) {
			return;
		}
		String dirName = Tools.getDirNameFromUrl(txtUrl);
		final String fileName2 = dirName + "_" + fileName;
		String txt = cache.get(fileName2);
		if (txt != null) {
			if (tv != null)
				tv.setText(txt);
		} else {
			txt = localMemory.getTxt(fileName2);
			if (txt != null && !txt.equals("")) {
				if (tv != null)
					tv.setText(txt);
			} else {
				executorService.execute(new Runnable() {

					@Override
					public void run() {
						String txt = "";
						try {
							txt = ConnectionCaller.doGet(txtUrl);
						} catch (OutOfMemoryError e) {
							Log.e("error", "OutOfMemoryError");
						} catch (WSError e) {
							e.printStackTrace();
						} catch (Exception e) {
							e.printStackTrace();
						}
						if (txt != null) {
							setTxtKey(fileName2, txt);
							saveTxt(txt, fileName2);
							final String txt2 = txt;
							if (handler != null) {
								handler.post(new Runnable() {

									@Override
									public void run() {
										if (tv != null)
											tv.setText(txt2);
									}
								});
							}
						}
					}
				});

			}
		}
	}

	/**
	 * 同步获取文本
	 * 
	 * @param txtUrl
	 * @return
	 */
	public String getUnSyncTxt(final String txtUrl) {
		String fileName = Tools.getFileNameFromUrl(txtUrl);
		// loaderManger.ini
		if (fileName == null) {
			return "";
		}
		String dirName = Tools.getDirNameFromUrl(txtUrl);
		fileName = dirName + "_" + fileName;
		String txt = cache.get(fileName);
		if (txt == null) {
			txt = localMemory.getTxt(fileName);
			if (txt == null || txt.equals("")) {
				try {
					txt = ConnectionCaller.doGet(txtUrl);
				} catch (OutOfMemoryError e) {
					Log.e("error", "OutOfMemoryError");
				} catch (WSError e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (txt != null) {
					setTxtKey(fileName, txt);
					saveTxt(txt, fileName);

				}
			}
		}
		return txt;
	}

	private void setTxtKey(String fileName, String content) {
		cache.put(fileName, content);
	}

	/**
	 * 
	 * @param bm
	 * @param filename
	 * @param cate
	 */
	public void saveTxt(String txt, String fileName) {
		localMemory.saveTxt(txt, fileName);
	}

	public void clear() {
		cache.clear();
	}

}
