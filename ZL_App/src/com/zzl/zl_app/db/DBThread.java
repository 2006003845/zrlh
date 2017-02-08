package com.zzl.zl_app.db;

import java.util.Vector;

import android.content.Context;

import com.zzl.zl_app.sup.ActionListener;
import com.zzl.zl_app.sup.SupThread;
import com.zzl.zl_app.util.SystemAPI;

public class DBThread extends SupThread {

	private boolean isRun = false;

	private ActionListener listener;

	public ActionListener getListener() {
		return listener;
	}

	public void setListener(ActionListener listener) {
		this.listener = listener;
	}

	public void startRun() {

		if (!isRun) {
			isRun = true;

			Thread t = new Thread(this);

			t.start();
		} else {
			synchronized (this) {
				// Log.v("IO", "notify");
				notify();
			}
		} 
	}

	private Object obj = new Object();

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (isRun) {

			// Log.v("IO", "http working");
			stat = Stat_Word;
			// TODO
			if (listener != null) {
				listener.doAction();
				listener.onResult();
			}
			stat = Stat_Word;
		}
	}

	// @Override
	// public void doAction() {
	// }
	//
	// @Override
	// public void onResult() {
	// }

	static class ThreadManager implements Runnable {

		private static final int ThreadNumber = 1;

		private static Vector<SupThread> threadPool = new Vector<SupThread>();

		private ThreadManager() {

		}

		private static ThreadManager manager = null;

		public static ThreadManager sharedManager(Context context) {
			if (manager == null) {
				manager = new ThreadManager();
				manager.loop = true;
				Thread th = new Thread(manager);
				th.start();
			}
			return manager;
		}

		public static SupThread getThread() {

			for (int i = 0; i < threadPool.size(); i++) {
				SupThread th = threadPool.elementAt(i);
				if (th != null && th.stat == SupThread.Stat_IDLE) {
					return th;
				}
			}
			if (threadPool.size() < ThreadNumber) {
				SupThread th = new DBThread();
				threadPool.addElement(th);
				return th;
			}
			return null;
		}

		private boolean loop = false;

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (loop) {
				
				SystemAPI.sleep(1000);
			}
		}

	}

}
