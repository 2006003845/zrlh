package com.zrong.entity;

public class Player {
	private int live_value;// 生命值(0,1,2,3)
	private int wealth;// 财富

	private static Player player;

	private Player() {

	}

	public static Player newInstanceOfPlayer() {
		if (player == null) {
			player = new Player();
		}
		return player;
	}

	public Player(int id, int live_value, int wealth) {
		super();
		this.live_value = live_value;
		this.wealth = wealth;
	}

	public int getLive_value() {
		return live_value;
	}

	public void setLive_value(int live_value) {
		this.live_value = live_value;
	}

	public int getWealth() {
		return wealth;
	}

	public void setWealth(int wealth) {
		this.wealth = wealth;
	}

}
