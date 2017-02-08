package com.zzl.game;

public abstract class GameMode {
	/**
	 * count game score
	 * 
	 * @return int
	 */
	protected abstract int countScore();

	/**
	 * count game money
	 * 
	 * @return int
	 */
	protected abstract int countMoney();

	/**
	 * game obj appear frequency control
	 */
	protected abstract void frequencyControl();

}
