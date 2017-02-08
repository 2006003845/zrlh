package com.zzl.zl_app.sup;

public abstract class SupThread implements Runnable {

	public int stat;

	public static final int Stat_IDLE = 0;

	public static final int Stat_Word = 1;

	@Override
	public void run() {
	}

}
