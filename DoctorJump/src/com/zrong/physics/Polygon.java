package com.zrong.physics;

import org.jbox2d.collision.PolygonDef;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Path;

import com.zrong.game.Sprite;

public class Polygon extends Physics {

	private PolygonDef polygonDef;

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
	protected Polygon(World world, float x, float y, float density,
			float friction, float restitution) {
		// TODO Auto-generated constructor stub
		polygonDef = new PolygonDef();
		path = new Path();
		polygonDef.density = density;
		polygonDef.friction = friction;
		polygonDef.restitution = restitution;
		polygonDef.vertices.clear();
		bodyDef.position.x = x;
		bodyDef.position.y = y;
		body = world.createBody(bodyDef);
	}

	public PolygonDef getPolygonDef() {
		return polygonDef;
	}

	public void setPolygonDef(PolygonDef polygonDef) {
		this.polygonDef = polygonDef;
	}

	/**
	 * 严格按顺时针依次添加，否则出现质量为负数导致物体不动等非物理现象
	 * 
	 * @param vec2
	 *            点向量
	 * @param last
	 *            是否最后一个向量
	 */
	public void add(Vec2 vec2, boolean last) {
		polygonDef.vertices.add(vec2);
		if (last) {
			this.vertices = new float[polygonDef.vertices.size() << 1];
			body.createShape(polygonDef);
			body.setMassFromShapes();
		}
	}

	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		Vec2 vec2 = body.getPosition();
		float degrees = (body.getAngle() * RAD_TO_DEG) % 360;
		path.reset();
		for (int i = 0, j = 0; i < polygonDef.vertices.size(); i++) {
			vertices[j++] = polygonDef.vertices.get(i).x + vec2.x;
			vertices[j++] = polygonDef.vertices.get(i).y + vec2.y;
			if (i == 0)
				path.moveTo(vertices[j - 2], vertices[j - 1]);
			else
				path.lineTo(vertices[j - 2], vertices[j - 1]);
		}
		path.close();
		matrix.reset();
		canvas.save();
		if (degrees != 0)
			matrix.preRotate(degrees, vec2.x, vec2.y);
		canvas.setMatrix(matrix);
		if (sprite != null) {
			sprite.draw(canvas, vec2.x, vec2.y);
		} else if (bitmap != null) {
			canvas.drawBitmap(bitmap, vec2.x, vec2.y, null);
		} else {
			setColor(drawColor);
			canvas.drawPath(path, paint);
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
