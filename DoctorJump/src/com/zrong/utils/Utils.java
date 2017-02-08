package com.zrong.utils;

import java.util.Random;

import org.jbox2d.common.Vec2;

public class Utils {
	public static final Vec2[] jumpLinearV1 = { new Vec2(71, -51),
			new Vec2(73, -50), new Vec2(78, -53), new Vec2(75, -52),
			new Vec2(77, -55), new Vec2(80, -54) };
	public static final Vec2[] jumpLinearV2 = { new Vec2(87, -80),
			new Vec2(82, -83), new Vec2(88, -87), new Vec2(86, -82),
			new Vec2(85, -85), new Vec2(81, -95) };
	public static final Vec2[] jumpLinearV3 = { new Vec2(97, -116),
			new Vec2(89, -110), new Vec2(93, -111), new Vec2(92, -115),
			new Vec2(91, -91), new Vec2(94, -112) };

	/**
	 * 随机获取受力
	 * 
	 * @return Vec2
	 */
	public static Vec2 getRandomLinearV(int floor) {
		Random rand = new Random();
		switch (floor) {
		case 1:
			return jumpLinearV1[rand.nextInt(6)];
		case 2:
			return jumpLinearV2[rand.nextInt(6)];
		case 3:
			return jumpLinearV3[rand.nextInt(6)];
		default:
			break;
		}
		return jumpLinearV1[rand.nextInt(6)];
	}

}
