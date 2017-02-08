package com.zzl.appdemo;

import java.io.IOException;
import java.net.MalformedURLException;

import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.zzl.appdemo.base.BaseActivity;
import com.zzl.appdemo.base.MyToast;
import com.zzl.zl_app.cache.ImageCache;
import com.zzl.zl_app.cache.LocalMemory;

public class SplashActivity extends BaseActivity {
	public static final String Tag = "splash";

	private MyCountDownTimer timer;
	private ImageView splashImgV;
	private Animation an;
	private Bitmap wel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addActMap(Tag, this);
		setContentView(R.layout.splash);

		init();
		initView();
		timer = new MyCountDownTimer(5 * 1000, 1000);
		timer.start();
	}

	private void init() {
		MyToast.initToast(getLayoutInflater(), R.layout.toast, R.id.toast_tv);
		LocalMemory.getInstance()
				.initStoreData(getContext(), R.string.app_name);
	}

	private void initView() {
		splashImgV = (ImageView) this.findViewById(R.id.splash_imgv);
		try {
			wel = ImageCache.getInstance().getUnSyncImgCachFromDisk(
					getContext(), ZLApplication.getApp().wel_name);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (wel != null)
			splashImgV.setImageBitmap(wel);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (timer != null) {
			timer.cancel();
		}
		MainActivity.launch(getContext(), null);
		closeOneAct(Tag);
		return super.onTouchEvent(event);
	}

	class MyCountDownTimer extends CountDownTimer {

		public MyCountDownTimer(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
		}

		@Override
		public void onFinish() {
			MainActivity.launch(getContext(), null);
			closeOneAct(Tag);
		}

		@Override
		public void onTick(long millisUntilFinished) {
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash, menu);
		return true;
	}

	@Override
	public BaseActivity getContext() {
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
