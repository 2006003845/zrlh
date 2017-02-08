package com.zzl.appdemo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.webkit.HttpAuthHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zzl.appdemo.base.BaseActivity;
import com.zzl.appdemo.base.MyToast;
import com.zzl.zl_app.util.Tools;

public class MainActivity extends BaseActivity {
	public static final String Tag = "main";

	public static void launch(Context c, Intent intent) {
		if (intent == null) {
			intent = new Intent();
		}
		intent.setClass(c, MainActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		c.startActivity(intent);
	}

	private ZLApplication zlApp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addActMap(Tag, getContext());

		zlApp = ZLApplication.getApp();
		setContentView(R.layout.main);
		setFullScreem();
		initView();
		ViewPager viewPager;
	}

	private void setFullScreem() {
		if (zlApp.isFullScreen) {
			getContext().getWindow().setFlags(
					WindowManager.LayoutParams.FLAG_FULLSCREEN,
					WindowManager.LayoutParams.FLAG_FULLSCREEN);
		} else {
			final WindowManager.LayoutParams attrs = getWindow()
					.getAttributes();
			attrs.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
			getWindow().setAttributes(attrs);
			getWindow().clearFlags(
					WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
		}
	}

	private WebView webView;
	private TextView titleTV;
	private ProgressBar loadPB;

	public Bitmap cacheBM;

	private void initView() {

		loadPB = (ProgressBar) this.findViewById(R.id.webv_pb);

		webView = (WebView) this.findViewById(R.id.webview);
		webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
		webView.setHorizontalScrollBarEnabled(false);
		webView.getSettings().setSupportZoom(false);
		webView.setHorizontalScrollbarOverlay(true);

		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setAllowFileAccess(true);
//		webView.getSettings().setPluginsEnabled(true);
		webView.getSettings().setBuiltInZoomControls(true);
		webView.getSettings().setAppCacheEnabled(true);
		webView.getSettings().setSupportMultipleWindows(true);

		if (!Tools.checkNetWorkStatus(getContext()))
			webView.getSettings().setCacheMode(
					WebSettings.LOAD_CACHE_ELSE_NETWORK);
		else
			webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
		webView.getSettings().setLayoutAlgorithm(LayoutAlgorithm.NORMAL);// 适应屏幕
		// 使用内置WebView进行浏览
		webView.setWebViewClient(new WebViewClient() {
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}
		});
		webView.setWebChromeClient(m_chromeClient);

		webView.setWebViewClient(new WebViewClient() {
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				if (url.startsWith("c")) {
					String num = url.substring(5);
					// phone(num);
				} else
					view.loadUrl(url);

				return true;
			}

			@Override
			public void onReceivedHttpAuthRequest(WebView view,
					HttpAuthHandler handler, String host, String realm) {
				super.onReceivedHttpAuthRequest(view, handler, host, realm);
				loadPB.setVisibility(View.VISIBLE);
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				view.loadUrl("javascript:window.local_obj.showSource('<head>'+"
						+ "document.getElementsByTagName('html')[0].innerHTML+'</head>');");
				// sessionCookie = cookieManager.getCookie(url);
				// if (sessionCookie != null) {
				// cookieManager.setCookie(url, sessionCookie);
				// CookieSyncManager.getInstance().sync();
				// }
				super.onPageFinished(view, url);
				loadPB.setVisibility(View.GONE);
				webView.setVisibility(View.VISIBLE);
			}
		});

		webView.loadDataWithBaseURL("file:///mnt/sdcard/appdemo/", "",
				"text/html", "utf-8", null);
		webView.loadUrl("file:///mnt/sdcard/appdemo/" + zlApp.url);
		webView.pageDown(true);
		webView.pageUp(true);

	}

	private WebChromeClient m_chromeClient = new WebChromeClient() {
		@Override
		public void onShowCustomView(View view, CustomViewCallback callback) {
			// TODO Auto-generated method stub
		}
	};
	Handler handler = new Handler();

	final class InJavaScriptLocalObj {
		public void showSource(String html) {
			final String title = fiterHtmlTag(html, "title");
			handler.post(new Runnable() {

				@Override
				public void run() {
					String t = title;
					if (title.length() > 8) {
						t = title.substring(0, 8) + "...";
					}
					titleTV.setText(t);
				}
			});

			Log.d("HTML", title);
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (webView != null)
			webView.onPause();
	}

	@Override
	protected void onResume() {

		super.onResume();
		if (webView != null)
			webView.onResume();
	}

	// ************************** exit ********************************//
	int count = 0;
	long startTime;
	long nextTime;

	@Override
	public void onBackPressed() {
		if (webView.canGoBack()) {
			webView.goBack();
		}

		if (count == 0) {
			startTime = System.currentTimeMillis();
			MyToast.getToast().showToast(
					getContext(),
					Tools.getStringFromRes(getContext(),
							R.string.pressed_exit_again)
							+ getResources().getString(R.string.app_name));
			count++;
			return;
		} else {
			nextTime = System.currentTimeMillis();
			if (nextTime - startTime <= 1500) {
				super.onBackPressed();
				finishAllActs();
				System.exit(0);
			} else {
				MyToast.getToast().showToast(
						getContext(),
						Tools.getStringFromRes(getContext(),
								R.string.pressed_exit_again)
								+ getResources().getString(R.string.app_name));
				startTime = nextTime;
			}
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (webView != null)
			webView.destroy();
	}

	public static String fiterHtmlTag(String str, String tag) {
		String regxp = "<" + tag + "\\s*.*>.*</" + tag + ">";
		Pattern pattern = Pattern.compile(regxp);
		Matcher matcher = pattern.matcher(str);
		StringBuffer sb = new StringBuffer();
		boolean result1 = matcher.find();
		String res = "";
		if (result1) {
			res = matcher.group();
		}
		String regxp2 = ">.*<";
		Pattern pattern2 = Pattern.compile(regxp2);
		matcher = pattern2.matcher(res);
		result1 = matcher.find();
		if (result1)
			res = matcher.group();
		if (res.length() > 2) {
			res = res.substring(1, res.length() - 1);
		}
		return res;
	}

	public static String getXMLValue(String sXML, String sTag) {

		int idx = sXML.indexOf("<" + sTag + ">");
		if (idx != -1) {
			idx += sTag.length() + 2;
			int idx2 = sXML.indexOf("</" + sTag + ">", idx);
			if (idx2 != -1) {
				return sXML.substring(idx, idx2);
			}
		}
		return null;
	}

	@Override
	public BaseActivity getContext() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public void setDialogContent(AlertDialog dialog, int layoutId, String msg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setDialogTitle(AlertDialog dialog, int layoutId, String title) {
		// TODO Auto-generated method stub

	}

	@Override
	public OnClickListener setPositiveClickListener(AlertDialog dialog,
			int layoutId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OnClickListener setNegativeClickListener(AlertDialog dialog,
			int layoutId) {
		// TODO Auto-generated method stub
		return null;
	}

}
