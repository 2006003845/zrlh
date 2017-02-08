package com.zrong.physics;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.joints.DistanceJointDef;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class DistanceJoint_ extends Physics {

	private DistanceJointDef distanceJointDef;
	private float[] pts;
	private Physics p1;
	private Physics p2;

	/**
	 * @param p1
	 *            第一个物体
	 * @param p2
	 *            第二个物体
	 * @param vec1
	 *            定位在第一个物体上面一点
	 * @param vec2
	 *            定位在第二个物体上面一点
	 */
	protected DistanceJoint_(Physics p1, Physics p2, Vec2 vec1, Vec2 vec2) {
		// TODO Auto-generated constructor stub
		this.p1 = p1;
		this.p2 = p2;
		distanceJointDef = new DistanceJointDef();
		distanceJointDef.initialize(p1.body, p2.body, vec1, vec2);
		pts = new float[4];
	}

	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		setColor(drawColor);
		canvas.drawLines(pts, paint);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		pts[0] = distanceJointDef.body1.getPosition().x;
		pts[1] = distanceJointDef.body1.getPosition().y;
		pts[2] = distanceJointDef.body2.getPosition().x;
		pts[3] = distanceJointDef.body2.getPosition().y;
	}

	public DistanceJointDef getDistanceJointDef() {
		return distanceJointDef;
	}

	public void setDistanceJointDef(DistanceJointDef distanceJointDef) {
		this.distanceJointDef = distanceJointDef;
	}

	@Override
	public void bind(Bitmap bitmap, int frameWidth, int frameHeight, int delay) {
		// TODO Auto-generated method stub

	}

	@Override
	public void bind(Bitmap bitmap) {
		// TODO Auto-generated method stub

	}

	public Physics getP1() {
		return p1;
	}

	public void setP1(Physics p1) {
		this.p1 = p1;
	}

	public Physics getP2() {
		return p2;
	}

	public void setP2(Physics p2) {
		this.p2 = p2;
	}

}
