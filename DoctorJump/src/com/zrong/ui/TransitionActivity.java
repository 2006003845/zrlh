package com.zrong.ui;

import android.content.Intent;
import android.os.CountDownTimer;
import android.widget.TextView;

import com.zrong.entity.Music;
import com.zrong.ui.base.Base2Activity;

public class TransitionActivity extends Base2Activity {
	private TextView tv;

	@Override
	public void init() {
		super.init();
		Music.getInstance(this).start(R.raw.other, true);
		setContentView(R.layout.transition);
//		tv = (TextView) this.findViewById(R.id.textV);
//		tv.setText("15");
		new MyTimer(15000, 1000).start();
	}

	class MyTimer extends CountDownTimer {

		public MyTimer(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
		}

		@Override
		public void onTick(long millisUntilFinished) {
//			tv.setText("" + millisUntilFinished / 1000);
		}

		@Override
		public void onFinish() {
			startActivity(new Intent(TransitionActivity.this,
					MainActivity.class));
			TransitionActivity.this.finish();
		}

	}

}
