package com.zrong.ui;

import android.content.Intent;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.zrong.engine.DynamicBG;
import com.zrong.entity.Music;
import com.zrong.ui.base.Base2Activity;
import com.zrong.view.DrawDynamicView;

public class WelcomeActivity extends Base2Activity {
	@Override
	public void init() {
		super.init();
		initView();
		Music.getInstance(mContext).start(R.raw.start, false);
	}

	private void initView() {
		setContentView(R.layout.welcome);
		int w = getWindowManager().getDefaultDisplay().getWidth();
		int h = getWindowManager().getDefaultDisplay().getHeight();
		DynamicBG dbg = DynamicBG.createDynamicBG(this, DynamicBG.SNOW, w, h);
		DrawDynamicView ddv = (DrawDynamicView) this
				.findViewById(R.id.welcome_draw);
		ddv.setDynamicBG(dbg);
		new MyCountDownTimer(5 * 1000, 1000).start();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Intent intent = new Intent(WelcomeActivity.this, HomeActivity.class);
		activityStart(intent);
		return super.onTouchEvent(event);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		return false;
	}

	private void activityStart(Intent intent) {
		if (intent == null) {
			return;
		}
		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		startActivity(intent);
		this.finish();
		activitySwitch();
	}

	// 界面切换效果
	private void activitySwitch() {
		int version = Integer.valueOf(android.os.Build.VERSION.SDK);
		if (version >= 5) {
			overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
		}
		WelcomeActivity.this.finish();
	}

	class MyCountDownTimer extends CountDownTimer {

		public MyCountDownTimer(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
		}

		@Override
		public void onFinish() {
			Intent intent = new Intent(WelcomeActivity.this, HomeActivity.class);
			activityStart(intent);
		}

		@Override
		public void onTick(long millisUntilFinished) {
		}

	}

}
