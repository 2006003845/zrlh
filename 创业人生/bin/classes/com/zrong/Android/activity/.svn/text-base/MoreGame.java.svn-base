package com.zrong.Android.activity;





import com.zrong.Android.Listener.ActivityTransform;
import com.zrong.Android.Util.Music;
import com.zrong.Android.activity.R;
import com.zrong.Android.game.GameDef;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.URLUtil;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MoreGame extends GameActivity 
{
	public static MoreGame mContext = null;
	
	private final String DEBUG_TAG	= "Activity01";
	
	private WebView		mWebView;
	private  String url =null;
	
	/** Called when the activity is first created. */
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.moregame);
		
		url = this.getIntent().getExtras().getString("url");
		Music.getInstance(MainActivity.mContext).pause();
		mWebView = (WebView) findViewById(R.id.WebView01);
		//设置支持JavaScript脚本
		WebSettings webSettings = mWebView.getSettings();  
		webSettings.setJavaScriptEnabled(true);
		//设置可以访问文件
		webSettings.setAllowFileAccess(true);
		//设置支持缩放
		webSettings.setBuiltInZoomControls(true);
		//设置WebViewClient
		mWebView.setWebViewClient(new WebViewClient()
		{   
		    public boolean shouldOverrideUrlLoading(WebView view, String url) 
		    {   
		        view.loadUrl(url);   
		        return true;   
		    }  
			@Override
			public void onPageFinished(WebView view, String url) 
			{
				super.onPageFinished(view, url);
			}
			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) 
			{
				super.onPageStarted(view, url, favicon);
			}
		});
		//设置WebChromeClient
		mWebView.setWebChromeClient(new WebChromeClient(){
			@Override
			//处理javascript中的alert
			public boolean onJsAlert(WebView view, String url, String message,
					final JsResult result) 
			{
				//构建一个Builder来显示网页中的对话框
				Builder builder = new Builder(MoreGame.this);
				builder.setTitle(MainActivity.resources.getString(R.string.moregame_dialog1));
				builder.setMessage(message);
				builder.setPositiveButton(android.R.string.ok,
						new AlertDialog.OnClickListener() {
							public void onClick(DialogInterface dialog, int which) {
								//点击确定按钮之后,继续执行网页中的操作
								result.confirm();
							}
						});
				builder.setCancelable(false);
				builder.create();
				builder.show();
				return true;
			};
			@Override
			//处理javascript中的confirm
			public boolean onJsConfirm(WebView view, String url, String message,
					final JsResult result) 
			{
				Builder builder = new Builder(MoreGame.this);
				builder.setTitle(MainActivity.resources.getString(R.string.moregame_dialog2));
				builder.setMessage(message);
				builder.setPositiveButton(android.R.string.ok,
						new AlertDialog.OnClickListener() {
							public void onClick(DialogInterface dialog, int which) {
								result.confirm();
							}
						});
				builder.setNegativeButton(android.R.string.cancel,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int which) {
								result.cancel();
							}
						});
				builder.setCancelable(false);
				builder.create();
				builder.show();
				return true;
			};
			@Override
			//处理javascript中的prompt
			//message为网页中对话框的提示内容
			//defaultValue在没有输入时，默认显示的内容
			public boolean onJsPrompt(WebView view, String url, String message,
					String defaultValue, final JsPromptResult result) {
				//自定义一个带输入的对话框由TextView和EditText构成
				final LayoutInflater factory = LayoutInflater.from(MoreGame.this);
				//final View dialogview = factory.inflate(R.layout.prom_dialog, null);
				//设置TextView对应网页中的提示信息
				//((TextView) dialogview.findViewById(R.id.TextView_PROM)).setText(message);
				//设置EditText对应网页中的输入框
				//((EditText) dialogview.findViewById(R.id.EditText_PROM)).setText(defaultValue);
				
				Builder builder = new Builder(MoreGame.this);
				builder.setTitle(MainActivity.resources.getString(R.string.moregame_dialog3));
			//	builder.setView(dialogview);
				builder.setPositiveButton(android.R.string.ok,
						new AlertDialog.OnClickListener() {
							public void onClick(DialogInterface dialog, int which) {
								//点击确定之后，取得输入的值，传给网页处理
								//String value = ((EditText) dialogview.findViewById(R.id.EditText_PROM)).getText().toString();
								//String value ="http://game.10086.cn/a";
								//result.confirm(value);
								
							}
						});
				builder.setNegativeButton(android.R.string.cancel,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int which) {
								result.cancel();
							}
						});
				builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
							public void onCancel(DialogInterface dialog) {
								result.cancel();
							}
						});
				builder.show();
				return true;
			};
			
			//设置网页加载的进度条
			public void onProgressChanged(WebView view, int newProgress) 
			{
				MoreGame.this.getWindow().setFeatureInt(Window.FEATURE_PROGRESS, newProgress * 100);
				super.onProgressChanged(view, newProgress);
			}

			
			//设置应用程序的标题title
			public void onReceivedTitle(WebView view, String title) 
			{
				MoreGame.this.setTitle(title);
				super.onReceivedTitle(view, title);
			}
		});
		//连接按钮事件监听
		/*mButton.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				try 
				{
					//取得编辑框中我们输入的内容
			    	String url = mEditText.getText().toString();
			    	//判断输入的内容是不是网址
			    	if ( URLUtil.isNetworkUrl(url) )
					{  
			    		//装载网址
			    		mWebView.loadUrl(url);
					}
			    	else
			    	{
			    		mEditText.setText("输入网址错误，请重新输入");
					}
				}
				catch (Exception e) 
				{
					Log.e(DEBUG_TAG, e.toString());
				}
			}
		});*/
		//装载网址
		mWebView.loadUrl(url);
	}
	
	//zhouzhilong delete ---这段若是加上，从webView退出将十分困难
	/*public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if ((keyCode == KeyEvent.KEYCODE_BACK) && 
			 mWebView.canGoBack())
		{
			//返回前一个页面
			mWebView.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	} */ 
	public void Activitychange(Class activityClass,Intent intent) {
		 
		 
		if(intent == null)
		{
			intent = new Intent();
		}
		
		 
		 intent.setClass(MoreGame.this, activityClass);
 
		 
		 this.startActivity(intent);
		 
		 
	}

	@Override
	public GameActivity getGameContext() {
		// TODO Auto-generated method stub
		return this;
	}
	
	@Override
	public void finish() 
	{
		mContext = null;
		super.finish();
	}
		
}

