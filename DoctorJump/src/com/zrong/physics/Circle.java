package com.zrong.physics;

import org.jbox2d.collision.CircleDef;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.zrong.game.Sprite;

public class Circle extends Physics {

	private CircleDef circleDef;

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
	protected Circle(World world, float x, float y, float radius,
			float density, float friction, float restitution, int index) {

		// TODO Auto-generated constructor stub
		circleDef = new CircleDef();
		circleDef.radius = radius;
		circleDef.density = density;
		circleDef.friction = friction;
		circleDef.restitution = restitution;
		circleDef.filter.groupIndex = index;
		bodyDef.position.x = x;
		bodyDef.position.y = y;
		body = world.createBody(bodyDef);
		body.createShape(circleDef);
		body.setMassFromShapes();
	}

	public int getDiameter() {
		return (int) (circleDef.radius * 2);
	}

	public Vec2 getPosition() {
		return body.getPosition();
	}

	public CircleDef getCircleDef() {
		return circleDef;
	}

	public Bitmap getBitmap() {
		return bitmap;
	}

	public void setCircleDef(CircleDef circleDef) {
		this.circleDef = circleDef;
	}

	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		Vec2 vec2 = getPosition();
		// if (vec2 == null || body == null) {
		// return;
		// }
		float degrees = (body.getAngle() * RAD_TO_DEG) % 360;
		matrix.reset();
		canvas.save();
		if (degrees != 0)
			matrix.preRotate(degrees, vec2.x, vec2.y);
		canvas.setMatrix(matrix);
		if (sprite != null) {
			sprite.draw(canvas, vec2.x - circleDef.radius, vec2.y
					- circleDef.radius);
		} else if (bitmap != null) {
			canvas.drawBitmap(bitmap, vec2.x - circleDef.radius, vec2.y
					- circleDef.radius, paint);
		} else {
			setColor(drawColor);
			canvas.drawCircle(vec2.x, vec2.y, circleDef.radius, paint);
		}
		canvas.restore();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if (sprite != null)
			sprite.update();
	}

	@Override
	public void bind(Bitmap bitmap, int frameWidth, int frameHeight, int delay) {
		// TODO Auto-generated method stub
		sprite = new Sprite(bitmap, frameWidth, frameHeight);
		sprite.setDelay(delay);
	}

	@Override
	public void bind(Bitmap bitmap) {
		// TODO Auto-generated method stub
		this.bitmap = bitmap;
	}

}
