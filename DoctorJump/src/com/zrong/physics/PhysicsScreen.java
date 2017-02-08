package com.zrong.physics;

import java.util.ArrayList;

import org.jbox2d.collision.AABB;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Handler;

import com.zrong.engine.BoxScreen;
import com.zrong.engine.GameScreen;

public abstract class PhysicsScreen extends GameScreen {

	private ArrayList<Physics> physicsV;
	private AABB aabb;
	private World world;
	private float dt = 1f / 15f;
	private int iterations = 50;
	private Bitmap background;
	private Handler mHandler;

	public PhysicsScreen(Context context) {
		super(context);
		physicsV = new ArrayList<Physics>();
		mHandler = new Handler();
		mHandler.post(runable);
	}

	public Runnable runable = new Runnable() {

		@Override
		public void run() {
			if (pausePhysics) {
				world.step(0, 1);
			} else {
				world.step(dt, iterations);
			}
			mHandler.postDelayed(runable, (long) dt * 1000);
		}
	};

	public ArrayList<Physics> getPhysics() {
		return physicsV;
	}

	public void setPhysics(ArrayList<Physics> physics) {
		this.physicsV = physics;
	}

	public AABB getAabb() {
		return aabb;
	}

	public void setAabb(AABB aabb) {
		this.aabb = aabb;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public float getDt() {
		return dt;
	}

	public void setDt(float dt) {
		this.dt = dt;
	}

	public int getIterations() {
		return iterations;
	}

	public void setIterations(int iterations) {
		this.iterations = iterations;
	}

	public Bitmap getBackground() {
		return background;
	}

	public void setBackground(Bitmap background) {
		this.background = background;
	}

	public void createWorld(Vec2 lower, Vec2 upper, Vec2 gravity,
			boolean doSleep) {
		aabb = new AABB();

		aabb.lowerBound = lower;
		aabb.upperBound = upper;
		world = new World(aabb, gravity, doSleep);
	}

	/**
	 * @param world
	 *            世界
	 * @param x
	 *            中心坐标x
	 * @param y
	 *            中心坐标y
	 * @param radius
	 *            半径
	 * @param density
	 *            密度
	 * @param friction
	 *            摩擦力
	 * @param restitution
	 *            碰撞后恢复系数
	 */
	public synchronized Circle createCircle(float x, float y, float radius,
			float density, float friction, float restitution, int index) {
		return new Circle(world, x, y, radius, density, friction, restitution,
				index);
	}

	/**
	 * @param world
	 *            世界
	 * @param x
	 *            中心坐标x
	 * @param y
	 *            中心坐标y
	 * @param halfWidth
	 *            宽度一半
	 * @param halfHeight
	 *            高度一半
	 * @param density
	 *            密度
	 * @param friction
	 *            摩擦力
	 * @param restitution
	 *            碰撞后恢复系数
	 * @param index
	 *            FilterData.groupIndex 碰撞组编号
	 */
	public Rectangle createRectangle(float x, float y, float halfWidth,
			float halfHeight, float density, float friction, float restitution,
			int index) {
		return new Rectangle(world, x, y, halfWidth, halfHeight, density,
				friction, restitution, index);
	}

	/**
	 * 创建一个多边形，顶点添加顺序为顺时针依次添加
	 * 
	 * @param world
	 *            世界
	 * @param x
	 *            中心坐标x
	 * @param y
	 *            中心坐标y
	 * @param vertices
	 *            顶点个数
	 * @param density
	 *            密度
	 * @param friction
	 *            摩擦力
	 * @param restitution
	 *            碰撞后恢复系数
	 */
	public Polygon createPolygon(float x, float y, float density,
			float friction, float restitution) {
		return new Polygon(world, x, y, density, friction, restitution);
	}

	public void add(Physics physics) {
		synchronized (physicsV) {
			if (physics instanceof DistanceJoint_) {
				physicsV.add(physics);
				physicsV.add(((DistanceJoint_) physics).getP1());
				physicsV.add(((DistanceJoint_) physics).getP2());
			} else {
				physicsV.add(physics);
			}
		}
	}

	public void remove(int location) {
		physicsV.remove(location);
	}

	@Override
	public void update() {
		for (int i = 0; i < physicsV.size(); i++) {
			Physics physics = physicsV.get(i);
			physics.update();
		}
	}

	public boolean pausePhysics = false;

	public boolean isPausePhysics() {
		return pausePhysics;
	}

	/**
	 * 暂停物理引擎
	 * 
	 * @param pausePhysics
	 */
	public void setPausePhysics(boolean pausePhysics) {
		this.pausePhysics = pausePhysics;
	}

	public void resetPausePhysic() {
		this.pausePhysics = false;
	}

	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		if (background != null) {
			canvas.drawBitmap(background, 0, 0, paint);
		}
		if (BoxScreen.dymamicBG != null) {
			BoxScreen.dymamicBG.update(true, 0);
			BoxScreen.dymamicBG.draw(canvas);
		}
		for (int i = 0; i < physicsV.size(); i++) {
			Physics physics = physicsV.get(i);
			physics.draw(canvas);
		}

	}

}
