package com.zrong.utils;

import android.os.Handler;
import android.os.Message;

public abstract class MyCountDownTimer {
	private static final int MSG_RUN = 1;

	private static final int MSG_RESTART = 2;

	private final long mCountdownInterval;// 定时间隔，以毫秒计
	private long mTotalTime;// 定时时间
	private long mRemainTime;// 剩余时间
	
	private boolean isPause = false;

	// 构造函数
	public MyCountDownTimer(long millisInFuture, long countDownInterval) {
		mTotalTime = millisInFuture;
		mCountdownInterval = countDownInterval;
		mRemainTime = millisInFuture;
	}

	// 取消计时
	public final void cancel() {
		isPause = true;
		mHandler.removeMessages(MSG_RUN);
	}

	// 继续计时
	public final void resume() {
		isPause = false;
		mHandler.sendMessageAtFrontOfQueue(mHandler.obtainMessage(MSG_RUN));
	}

	public final void reStart(long time) {
		mRemainTime = time;
		isPause = false;
		mHandler.sendMessageAtFrontOfQueue(mHandler.obtainMessage(MSG_RUN));
	}

	// 暂停计时
	public final void pause() {
		isPause = true;
		mHandler.removeMessages(MSG_RUN);
	}

	// 开始计时
	public synchronized final MyCountDownTimer start() {
		if (mRemainTime <= 0) {// 计时结束后返回
			onFinish();
			return this;
		}
		// 设置计时间隔
		mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_RUN),
				mCountdownInterval);
		return this;
	}

	public abstract void onTick(long millisUntilFinished, int percent);// 计时中

	public abstract void onFinish();// 计时结束

	// 通过handler更新android UI，显示定时时间
	private Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			if(isPause){
				return;
			}

			synchronized (MyCountDownTimer.this) {
				if (msg.what == MSG_RUN) {
					mRemainTime = mRemainTime - mCountdownInterval;

					if (mRemainTime <= 0) {
						onFinish();
					} else if (mRemainTime < mCountdownInterval) {
						sendMessageDelayed(obtainMessage(MSG_RUN), mRemainTime);
					} else {

						onTick(mRemainTime,
								new Long(100 * (mTotalTime - mRemainTime)
										/ mTotalTime).intValue());

						sendMessageDelayed(obtainMessage(MSG_RUN),
								mCountdownInterval);
					}
				}
			}
		}
	};
}
