package com.zrong.utils;

import android.os.Handler;
import android.os.Message;

public abstract class MyCountDownTimer {
	private static final int MSG_RUN = 1;

	private static final int MSG_RESTART = 2;

	private final long mCountdownInterval;// ��ʱ������Ժ����
	private long mTotalTime;// ��ʱʱ��
	private long mRemainTime;// ʣ��ʱ��
	
	private boolean isPause = false;

	// ���캯��
	public MyCountDownTimer(long millisInFuture, long countDownInterval) {
		mTotalTime = millisInFuture;
		mCountdownInterval = countDownInterval;
		mRemainTime = millisInFuture;
	}

	// ȡ����ʱ
	public final void cancel() {
		isPause = true;
		mHandler.removeMessages(MSG_RUN);
	}

	// ������ʱ
	public final void resume() {
		isPause = false;
		mHandler.sendMessageAtFrontOfQueue(mHandler.obtainMessage(MSG_RUN));
	}

	public final void reStart(long time) {
		mRemainTime = time;
		isPause = false;
		mHandler.sendMessageAtFrontOfQueue(mHandler.obtainMessage(MSG_RUN));
	}

	// ��ͣ��ʱ
	public final void pause() {
		isPause = true;
		mHandler.removeMessages(MSG_RUN);
	}

	// ��ʼ��ʱ
	public synchronized final MyCountDownTimer start() {
		if (mRemainTime <= 0) {// ��ʱ�����󷵻�
			onFinish();
			return this;
		}
		// ���ü�ʱ���
		mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_RUN),
				mCountdownInterval);
		return this;
	}

	public abstract void onTick(long millisUntilFinished, int percent);// ��ʱ��

	public abstract void onFinish();// ��ʱ����

	// ͨ��handler����android UI����ʾ��ʱʱ��
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
