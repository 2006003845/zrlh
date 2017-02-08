package com.zzl.api;

public class SystemApi {

	/**
	 * sleep
	 * 
	 * @param sleepTime
	 */
	public static final void sleep(long sleepTime) {
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static final void gc() {
		System.gc();
	}

}
