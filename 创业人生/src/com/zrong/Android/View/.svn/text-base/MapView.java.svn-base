package com.zrong.Android.View;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Vector;

import javax.microedition.lcdui.Graphics;

import res.Draw;
import res.Res;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.format.Time;
import android.util.AttributeSet;
import android.util.FloatMath;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.zrong.Android.Util.SystemAPI;
import com.zrong.Android.activity.CheckBuildingActivity;
import com.zrong.Android.activity.CreateBuildingActivity;
import com.zrong.Android.activity.CreateShopActivity;
import com.zrong.Android.activity.CreateShopAndBuildActivity;
import com.zrong.Android.activity.MainActivity;
import com.zrong.Android.activity.MyShopAndBuildingActivity;
import com.zrong.Android.activity.OtherShopInfoActivity;
import com.zrong.Android.activity.R;
import com.zrong.Android.activity.SeacherFriendActivity;
import com.zrong.Android.activity.ShopInfoActivity;
import com.zrong.Android.activity.SocialActivity;
import com.zrong.Android.activity.shopInfo2Activity;
import com.zrong.Android.game.Connection;
import com.zrong.Android.game.ConstructData;
import com.zrong.Android.game.GameData;
import com.zrong.Android.game.GameDefinition;
import com.zrong.Android.game.GameGroupControl;
import com.zrong.Android.game.Script;
import com.zrong.Android.online.network.GameProtocol;

public class MapView extends View implements OnGestureListener {

	public static final String TAG = "MapView";
	//public static final  AlertDialog dlg1=new AlertDialog.Builder(MainActivity.mContext).create();;
	public static  Handler handler = new Handler();
	private Runnable updateThread;
	private String DEFAULT_TIME_FORMAT = "MM-dd HH:mm:ss";
//	private ArrayList<HashMap<String, String>> list ;
//	private ListView lv;
	private TextView tv;
	//private String time;
	Matrix matrix = null;
	Matrix testMatrix = new Matrix();
	Matrix savedMatrix = new Matrix();
	PointF start = new PointF();
	PointF mid = new PointF();
	float oldDist;

	static final int NONE = 0;
	static final int DRAG = 1;
	static final int ZOOM = 2;
	public int mode = NONE;

	private float originalcaleX = 1;

	private float originalcaleY = 1;

	private static final float MAX_SCALE = (float) 3.5;

	private Graphics g;

	private Bitmap viewBitmap;

	private Canvas buffer;

	public static Vector drawIdList = new Vector();// 绘制排序容器

	public static final short Draw_BUILDING = 1000;
	public static final short Draw_Map_Up = 2000;

	public MapView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public MapView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public MapView(Context context) {
		super(context);

		init();
	}

	public TextView money;
	public TextView employeeNum;
	public TextView canledar;
	public TextView shopNum;
	public TextView streetView;

	private void init() {

		GameData.map = Script.readMap((short) 137);

		viewBitmap = Bitmap.createBitmap(original_width, original_height,
				Bitmap.Config.ARGB_8888);

		buffer = new Canvas(viewBitmap);

		mGestureDetector = new GestureDetector(this);

		mGestureDetector.setIsLongpressEnabled(true);

		matrix = new Matrix();

		originalcaleX = (float) (GameDefinition.screenWidth / (float) original_width);
		originalcaleY = (float) (GameDefinition.screenHeight / (float) original_height);

		// matrix.postScale((float)2.5, 2, 0, 0);
		matrix.postScale(originalcaleX, originalcaleY);
		GameData.isEnterMap = true;
	}

	private static int original_width = 544;

	private static int original_height = 352;

	int change_mapX = 0;

	int change_mapY = 0;

	protected void onDraw(Canvas canvas) {
		if(GameData.player == null){
			return ;
		}
		int change_cityX = 0;

		int change_cityY = 0;
		if (money != null) {
			money.setText(String.valueOf(GameData.player.money));
		}

		if (employeeNum != null) {
			employeeNum.setText(String
					.valueOf(GameData.corporation.employee.length)
					+ "/"
					+ GameData.corporation.employeesMaxNum);
		}

		if (canledar != null) {
/*			handler.post(updateThread);
	        updateThread = new Runnable() {
				
				 
				public void run() {
					// TODO Auto-generated method stub
				handler.postDelayed(updateThread, 1000);
				Time time = new Time("GMT+8");    
		        time.setToNow();
		        int minute = time.minute;   
		        int hour = time.hour;
		     //   SimpleDateFormat dateFormatter = new SimpleDateFormat(DEFAULT_TIME_FORMAT);	        
		   //     time = dateFormatter.format(Calendar.getInstance().getTime());
				//canledar.setText(String.valueOf(GameData.Months) + "-"
					//	+ GameData.Date +"   "+GameData.Hour+":"+GameData.Minute+1);
		        canledar.setText(String.valueOf(time));
				}
			};*/
/*	canledar.setText(String.valueOf(GameData.Months) + "月"
					+ GameData.Date +"日");*/
    	canledar.setText(String.valueOf(GameData.Months) + "-"
			+ GameData.Date +"   "+GameData.Hour+":"+GameData.Minute);
	}
		 
		if (shopNum != null) {
			shopNum.setText(String.valueOf(GameData.corporation.shop.length)
					+ "/" + GameData.corporation.shopMaxNum);
		}

		if (streetView != null) {
			streetView.setText("街:" + GameData.cityX + "道:" + GameData.cityY);
		}

		if (isNoMove() && GameData.isNeedNewMapData())// 需要请求新数据了
		{
			Log.v(TAG, "+++++++++++++++++++++++++++++++++++++++");
			change_mapX = 0;

			change_mapY = 0;

			Log.v(TAG, "fx=" + GameData.focusX + "fy=" + GameData.focusY
					+ "ofx=" + GameData.original_focusX + "ofy="
					+ GameData.original_focusY);

			int new_mapX = GameData.focusX / Draw.showWidh
					+ (GameData.focusX % Draw.showWidh == 0 ? 0 : 1);

			int new_mapY = GameData.focusY / Draw.showHeight
					+ (GameData.focusY % Draw.showHeight == 0 ? 0 : 1);

			Log.v(TAG, "new_mapX =" + new_mapX + ",new_mapY =" + new_mapY);

			int old_mapX = GameData.original_focusX / Draw.showWidh
					+ (GameData.original_focusX % Draw.showWidh == 0 ? 0 : 1);

			int old_mapY = GameData.original_focusY / Draw.showHeight
					+ (GameData.original_focusY % Draw.showHeight == 0 ? 0 : 1);

			Log.v(TAG, "old_mapX=" + old_mapX + ",old_mapY" + old_mapY);

			change_mapX = new_mapX - old_mapX;

			change_mapY = new_mapY - old_mapY;

			Log.v(TAG, "change_mapX=" + change_mapX + "change_mapY="
					+ change_mapY);

			// ****************计算出需要移动的街道数,未加修正 ****************
			change_cityX = change_mapX * 3;

			change_cityY = change_mapX * 3;

			change_cityX += change_mapY * 4;

			change_cityY += -change_mapY * 4;

			Log.v(TAG, "移动的街道数  change_cityX =" + change_cityX
					+ "change_cityY =" + change_cityY);

			// ***********************************************************

			// *****************开始修正************************************
			int offsetX = GameData.focusX % Draw.showWidh;

			int offsetY = GameData.focusY % Draw.showHeight;

			int min = 10000;

			int minIndex = 0;
			;

			for (int i = 0; i < Script.locationSize; i++) {
				int cur = Math.abs(offsetX - Script.locationX[i])
						+ Math.abs(offsetY - Script.locationY[i]);

				if (cur < min) {
					min = cur;
					minIndex = i;
				}
			}

			int origi_index = GameData.getBlockIndex(GameData.cityX,
					GameData.cityY);// 移动前index

			// 开始计算偏移量
			change_cityX -= Script.offset_cityXY[origi_index][0];

			change_cityX += Script.offset_cityXY[minIndex][0];

			change_cityY -= Script.offset_cityXY[origi_index][1];

			change_cityY += Script.offset_cityXY[minIndex][1];

			// Log.v(TAG,
			// "加入偏移 change_cityX="+change_cityX+"change_cityY="+change_cityY);

			GameData.frushOriginalFocus();// 把当前focus值设置为初始focus值

			Log.v(TAG, "原街道值 cityX=" + GameData.cityX + ",cityY="
					+ GameData.cityY);
			//
			Log.v(TAG, "最终请求的街道值 cityX = " + (GameData.cityX + change_cityX)
					+ ",cityY = " + (GameData.cityY + change_cityY));
			//
			// Log.v(TAG,
			// "---------------------------------------------------------");

			// zhouzhilong add---新手引导
			if (GameData.isFreshMan && FreshManLead.isBank) {// 银行
				Log.i("Log", "进入银行街道");
				Connection.sendMessage(
						GameProtocol.CONNECTION_SEND_MAPAROUND_SHOP,
						ConstructData.getMapAroundShop(GameData.mapIds[0],
								(short) (77), (short) (14), (byte) 1,
								(byte) (GameData.ARRAY_LENTH / 2)));
			} else if (GameData.isFreshMan && FreshManLead.isCommerical) {// 工商
				Connection.sendMessage(
						GameProtocol.CONNECTION_SEND_MAPAROUND_SHOP,
						ConstructData.getMapAroundShop(GameData.mapIds[0],
								(short) (80), (short) (16), (byte) 1,
								(byte) (GameData.ARRAY_LENTH / 2)));
			} else if (GameData.isFreshMan && FreshManLead.isRevenue) {// 税务
				Connection.sendMessage(
						GameProtocol.CONNECTION_SEND_MAPAROUND_SHOP,
						ConstructData.getMapAroundShop(GameData.mapIds[0],
								(short) (79), (short) (11), (byte) 1,
								(byte) (GameData.ARRAY_LENTH / 2)));
			} else {
				Log.v("yz", "sendMessage  mapAround="+System.currentTimeMillis());
				Connection.sendMessage(
						GameProtocol.CONNECTION_SEND_MAPAROUND_SHOP,
						ConstructData.getMapAroundShop(
								GameData.mapIds[GameData.mapIdIndex],
								(short) (GameData.cityX + change_cityX),
								(short) (GameData.cityY + change_cityY),
								(byte) 1, (byte) (GameData.ARRAY_LENTH / 2)));
			}

			Vector v = new Vector(5, 5);

			v.addElement(GameProtocol.CONNECTION_SEND_MAPAROUND_SHOP);
			
			Message message = new Message();

			message.what = GameDefinition.Message_SetGameStatus;
			message.arg1 = GameDefinition.Game_Loading;
			message.obj = v;
			Log.v("yz", "init  Game_Loading="+System.currentTimeMillis());
			GameData.mhandler.sendMessage(message);

		}

		super.onDraw(canvas);

		if (g == null) {
			g = new Graphics(buffer);
		} else {
			g.setCanvas(buffer);
		}

		GameData.map.paint(g, 0, 0);

		drawMapUpArea(GameData.mapX, GameData.mapY, original_width,
				original_height);

		drawBuilding(g);

		drawSort(g);

		canvas.setMatrix(matrix);

		canvas.drawBitmap(viewBitmap, 0, 0, null);

		onFlingMove();

	}

	public void drawLend() {
		// drawFreshLead(g);
	}

	private String street;

	private GestureDetector mGestureDetector;

	public boolean onTouchEvent(MotionEvent event) {
		clearVelocity();
		mGestureDetector.onTouchEvent(event);
		switch (event.getAction() & MotionEvent.ACTION_MASK) {
		// 设置拖拉模式
		case MotionEvent.ACTION_DOWN:
			savedMatrix.set(matrix);
			start.set(event.getX(), event.getY());
			Log.i(TAG, "mode=DRAG");
			mode = DRAG;
			break;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_POINTER_UP:
			mode = NONE;
			Log.d(TAG, "mode=NONE");
			break;
		// 设置多点触摸模式
		case MotionEvent.ACTION_POINTER_DOWN:
			oldDist = spacing(event);
			Log.d(TAG, "oldDist=" + oldDist);
			if (oldDist > 10f) {
				savedMatrix.set(matrix);
				midPoint(mid, event);
				mode = ZOOM;
				Log.i(TAG, "mode=ZOOM");
			}
			break;
		// 若为DRAG模式，则点击移动图片
		case MotionEvent.ACTION_MOVE:
			if (mode == DRAG) {
			}
			// 若为ZOOM模式，则多点触摸缩放
			else if (mode == ZOOM) {
				float newDist = spacing(event);
				Log.d(TAG, "newDist=" + newDist);
				if (newDist > 10f) {

					matrix.set(savedMatrix);
					testMatrix.set(savedMatrix);

					float scale = newDist / oldDist;

					testMatrix.postScale(scale, scale, mid.x, mid.y);

					float testvalue[] = new float[9];
					float value[] = new float[9];

					testMatrix.getValues(testvalue);
					matrix.getValues(value);

					float tscaleX = testvalue[Matrix.MSCALE_X];// 测试Matrix宽度缩放倍数
					float tscaleY = testvalue[Matrix.MSCALE_Y];
					float scaleX = scale, scaleY = scale;

					if (tscaleX > MAX_SCALE) {
						scaleX = MAX_SCALE / value[Matrix.MSCALE_X];
					} else if (tscaleX < originalcaleX) {
						scaleX = originalcaleX / value[Matrix.MSCALE_X];
					}

					if (tscaleY > MAX_SCALE) {
						scaleY = MAX_SCALE / value[Matrix.MSCALE_Y];
					} else if (tscaleY < originalcaleY) {
						scaleY = originalcaleY / value[Matrix.MSCALE_Y];
					}

					matrix.postScale(scaleX, scaleY, mid.x, mid.y);

					matrix.getValues(value);

					float mscalex = value[Matrix.MSCALE_X];// 保存的上次宽度缩放倍数
					float mscaley = value[Matrix.MSCALE_Y];
					float mtrans_x = value[Matrix.MTRANS_X];// 保存的上次左上顶点X坐标
					float mtrans_y = value[Matrix.MTRANS_Y];// 保存的上次左上顶点Y坐标
					float mtrans_nx = mtrans_x + original_width * mscalex;// 保存的上次右下顶点X坐标
					float mtrans_ny = mtrans_y + original_height * mscaley;// 保存的上次右下顶点Y坐标

					if (mtrans_x > 0) {
						matrix.postTranslate(-mtrans_x, 0);
					}
					if (mtrans_y > 0) {
						matrix.postTranslate(0, -mtrans_y);
					}

					if (mtrans_nx < GameDefinition.screenWidth) {
						matrix.postTranslate(GameDefinition.screenWidth
								- mtrans_nx, 0);
					}

					if (mtrans_ny < GameDefinition.screenHeight) {
						matrix.postTranslate(0, GameDefinition.screenHeight
								- mtrans_ny);
					}
				}
			}
			break;

		}

		return true;
	}

	// 计算移动距离
	private float spacing(MotionEvent event) {
		float x = event.getX(0) - event.getX(1);
		float y = event.getY(0) - event.getY(1);
		return FloatMath.sqrt(x * x + y * y);
	}

	// 计算中点位置
	private void midPoint(PointF point, MotionEvent event) {
		float x = event.getX(0) + event.getX(1);
		float y = event.getY(0) + event.getY(1);
		point.set(x / 2, y / 2);
	}

	public void drawBuilding(Graphics g) {
		if (GameData.build != null) {
			for (int i = 0; i < GameData.build.length; i++) {
				if (GameData.build[i] != null) {
					GameData.build[i].addDraw(i);
				}
			}
		}
	}

	/**
	 * 绘制的范围
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public void drawMapUpArea(int x, int y, int w, int h) {
		if (GameData.map.info.actsRes == null)
			return;// 没有活动对象层
		int nx = x / GameData.map.mapInfo_W;
		int ny = y / GameData.map.mapInfo_H;
		int curx = x % GameData.map.mapInfo_W;
		int cury = y % GameData.map.mapInfo_H;
		int numX = (curx + w) / GameData.map.mapInfo_W
				+ (((curx + w) % GameData.map.mapInfo_W == 0) ? 0 : 1);
		int numY = (cury + h) / GameData.map.mapInfo_H
				+ (((cury + h) % GameData.map.mapInfo_H == 0) ? 0 : 1);
		Res res;
		for (int i = 0; i < numX; i++) {
			for (int j = 0; j < numY; j++) {
				for (int k = 0; k < GameData.map.info.actsRes.length; k++) {
					if (GameData.map.info.actsRes[k] != null) {
						res = GameData.map.info.actsRes[k];
						if (SystemAPI.inField(GameData.map.info.actsX[k] + i
								* GameData.map.mapInfo_W,
								GameData.map.info.actsY[k] + j
										* GameData.map.mapInfo_H, 5, 5, curx,
								cury, w, h) != SystemAPI.FIELD_OUT)// 在屏幕内
						{
							int offX;
							int offY;
							int width;
							int height;
							if (res != null) {
								offX = (short) Res.getOffX(res,
										GameData.map.info.serieShow[k]);
								offY = (short) Res.getOffY(res,
										GameData.map.info.serieShow[k]);
								width = (short) Res.getWidth(res,
										GameData.map.info.serieShow[k]);
								height = (short) Res.getHeight(res,
										GameData.map.info.serieShow[k]);
							} else {
								continue;
							}
							addDraw((short) (Draw_Map_Up + k),
									GameData.map.info.actsY[k] + (ny + j)
											* GameData.map.mapInfo_H,
									GameData.map.info.actsX[k] + (nx + i)
											* GameData.map.mapInfo_W,
									GameData.map.info.actsX[k] + (nx + i)
											* GameData.map.mapInfo_W - offX,
									GameData.map.info.actsY[k] + (ny + j)
											* GameData.map.mapInfo_H - offY,
									width, height);
						}
					}
				}
			}
		}
	}

	/**
	 * 
	 * 
	 * @param drawId
	 *            short
	 * @param compareValue_Y
	 *            int
	 * @param compareValue_X
	 *            int
	 * @param refreshX
	 *            int
	 * @param refreshY
	 *            int
	 * @param refreshWidth
	 *            int
	 * @param refreshHeight
	 *            int
	 */
	public static void addDraw(short drawId, int compareValue_Y,
			int compareValue_X, int refreshX, int refreshY, int refreshWidth,
			int refreshHeight) {
		if ((refreshX + refreshWidth - GameData.mapX < 0)
				|| refreshX - GameData.mapX > original_width
				|| refreshY + refreshHeight - GameData.mapY < 0
				|| refreshY - GameData.mapY > original_height) {
			return;
		}
		inSertDrawId(drawId, compareValue_Y, compareValue_X);
		return;
	}

	public static void inSertDrawId(short drawId, int compareValue_Y,
			int compareValue_X) {
		int length = drawIdList.size();
		boolean inserted = false;
		int[] temp = new int[3];
		temp[0] = drawId;
		temp[1] = compareValue_Y;
		temp[2] = compareValue_X;
		if (length == 0) {
			drawIdList.addElement(temp);
			temp = null;
			return;
		} else {
			for (int i = 0; i < length; i++) {
				int y = ((int[]) drawIdList.elementAt(i))[1];
				int x = ((int[]) drawIdList.elementAt(i))[2];
				if (temp[1] + temp[2] * 2 / 10 <= y + x * 2 / 10) {
					drawIdList.insertElementAt(temp, i);
					inserted = true;
					temp = null;
					break;
				}
			}
			if (!inserted) {
				drawIdList.addElement(temp);
				temp = null;
			}
		}
	}

	/**
	 * 插入一个显示到绘制队列中
	 * 
	 * @param drawId
	 *            short
	 * @param compareValue
	 *            int
	 * @param refreshX
	 *            int
	 */
	public static void inSertDrawId(short drawId, int compareValue) {
		int length = drawIdList.size();
		boolean inserted = false;
		int[] temp = new int[2];
		temp[0] = drawId;
		temp[1] = compareValue;
		if (length == 0) {
			drawIdList.addElement(temp);
			temp = null;
			return;
		} else {
			for (int i = 0; i < length; i++) {
				int y = ((int[]) drawIdList.elementAt(i))[1];
				if (temp[1] <= y) {
					drawIdList.insertElementAt(temp, i);
					inserted = true;
					temp = null;
					break;
				}
			}
			if (!inserted) {
				drawIdList.addElement(temp);
				temp = null;
			}
		}
	}

	/**
	 * 绘制排序系列
	 * 
	 * @param g
	 *            Graphics
	 */
	public void drawSort(Graphics g) {

		int length = drawIdList.size();
		for (int i = 0; i < length; i++) {
			int[] temp = new int[3];
			temp = (int[]) drawIdList.elementAt(i);
			draw(g, temp[0], GameData.mapX, GameData.mapY);
		}
		drawIdList.removeAllElements();
	}

	/**
	 * 绘制显示队列
	 * 
	 * @param g
	 *            Graphics
	 * @param drawId
	 *            int
	 * @param visionX
	 *            int
	 * @param visionY
	 *            int
	 */

	public void draw(Graphics g, int drawId, int visionX, int visionY) {
		if (drawId >= 2000)// 绘制活动对象层
		{
			GameData.map.drawUp(g, drawId - 2000, visionX, visionY,
					original_width, original_height);
		} else if (drawId >= 1000)// 绘制建筑物
		{
			if (drawId - 1000 < GameData.build.length && drawId - 1000 >= 0) {
				if (GameData.build[drawId - 1000] != null) {
					GameData.build[drawId - 1000].draw(g);
				}
			}
		}
	}

	public void moveMapXY(float moveX, float moveY) {
		GameData.setMapXY((int) (GameData.focusX + moveX),
				(int) (GameData.focusY + moveY));
	}

	public boolean onDown(MotionEvent arg0) {

		return false;
	}

	public boolean onFling(MotionEvent arg0, MotionEvent arg1, float vx,
			float vy) {
		velocityX = -vx;
		velocityY = -vy;
		return false;
	}

	private float velocityX;
	private float velocityY;

	/*
	 * 摩擦力
	 */
	private float scrub(float f) {

		return f * 9 / 10;

	}

	private void clearVelocity() {
		velocityX = 0;
		velocityY = 0;
	}

	public boolean isNoMove() {
		return velocityX == 0 && velocityY == 0 && mode == NONE;
	}

	private void onFlingMove() {
		/** 每帧移动的距离 */
		float move_x = 0;
		float move_y = 0;

		if (velocityX != 0) {
			move_x = velocityX / 100;
			velocityX = scrub(velocityX);// 摩擦力为9/10
		}

		if (velocityY != 0) {
			move_y = velocityY / 100;
			velocityY = scrub(velocityY);
		}

		if (move_y == 0 && move_x == 0) {
			this.clearVelocity();
			return;
		}

		float values[] = new float[9];

		// matrix.postTranslate(move_x, move_y);

		matrix.getValues(values);
		float mscalex = values[Matrix.MSCALE_X];// 保存的上次宽度缩放倍数
		float mscaley = values[Matrix.MSCALE_Y];

		move_x = move_x / mscalex;

		if (Math.abs(move_x) < 1) {
			velocityX = 0;
		}
		//
		move_y = move_y / mscaley;

		if (Math.abs(move_y) < 1) {
			velocityY = 0;
		}

		moveMapXY(move_x, move_y);

		GameData.map.updata(GameData.mapX, GameData.mapY);
	}

	public void onLongPress(MotionEvent event) {

	}

	public boolean onScroll(MotionEvent event1, MotionEvent event2,
			float distanceX, float distanceY) {
		if (mode == DRAG) {
			float values[] = new float[9];
			matrix.getValues(values);
			float tscaleX = values[Matrix.MSCALE_X];
			float tscaleY = values[Matrix.MSCALE_Y];
			moveMapXY(distanceX / tscaleX, distanceY / tscaleY);
			GameData.map.updata(GameData.mapX, GameData.mapY);
		}

		return false;
	}

	private void build(MotionEvent event) {
		float values[] = new float[9];

		matrix.getValues(values);
		// x,y方向上扩大或缩小的倍数
		float tscaleX = values[Matrix.MSCALE_X]; // 放大的倍数
		float tscaleY = values[Matrix.MSCALE_Y];
		// x,y方向上移动的距离
		float tx = values[Matrix.MTRANS_X];// 左上角点的坐标
		float ty = values[Matrix.MTRANS_Y];

		float x = event.getX();

		float y = event.getY();

		int px = (int) ((x - tx) / tscaleX);// 原图中的位置

		int py = (int) ((y - ty) / tscaleY);

		// 算出 触摸点是在哪个大格子中
		int girdIndex = -1;//

		for (int i = 0; i < GameData.xyArray.length; i++) {
			if (GameData.xyArray[i] != null) {
				if (SystemAPI.runIsPoint(GameData.xyArray[i][0] - GameData.mapX
						- 68, GameData.xyArray[i][1] - GameData.mapY - 32, 136,
						64, px, py))// 点中的是哪个大格子
				{

					girdIndex = i;
					break;
				}
			}
		}
		// 算出是在哪个小格子中
		int smallgridIndex = -1;

		if (girdIndex > 0) {
			for (int j = 0; j < GameData.smallxyArray.length; j++) {
				if (SystemAPI.runIsPoint(GameData.xyArray[girdIndex][0]
						+ GameData.smallxyArray[j][0] - GameData.mapX - 22,
						GameData.xyArray[girdIndex][1]
								+ GameData.smallxyArray[j][1] - GameData.mapY
								- 11, 44, 22, px, py)) {

					smallgridIndex = j;
					break;
				}
			}
		}
		// 计算在GameData.build数组中的索引
		int cityX = girdIndex % 11;

		int cityY = girdIndex / 11;

		cityX = GameData.cityX + (cityX - 5);

		cityY = GameData.cityY + (cityY - 5);

		int buildIndex = -1;

		for (int k = 0; k < GameData.build.length; k++) {
			if (GameData.build[k].mb.cityX == cityX
					&& GameData.build[k].mb.cityY == cityY) {
				if (GameData.build[k].mb.scale == 1) {
					if (smallgridIndex == GameData.build[k].mb.startGrid) {
						buildIndex = k;
						break;
					}
				} else if (GameData.build[k].mb.scale == 2) {
					if (smallgridIndex >= GameData.build[k].mb.startGrid
							&& smallgridIndex < GameData.build[k].mb.startGrid + 3) {
						buildIndex = k;
						break;
					}
				} else if (GameData.build[k].mb.scale == 3) {
					if (smallgridIndex >= GameData.build[k].mb.startGrid
							&& smallgridIndex < GameData.build[k].mb.startGrid + 6) {
						buildIndex = k;
						break;
					}
				} else if (GameData.build[k].mb.scale == 4) {
					buildIndex = k;
					break;
				}
			}
		}

		if (buildIndex >= 0)// 查看
		{
			Log.v(TAG, "查看 buildname = " + GameData.build[buildIndex].mb.name);

			Intent intent = new Intent();

			Bundle bundle = new Bundle(); // 携带数据

			if (GameData.build[buildIndex].mb.type == 5)// 公益建筑
			{
				bundle.putInt("selectIndex", buildIndex);
				intent.putExtras(bundle);
				MainActivity.mContext.Activitychange(
						CheckBuildingActivity.class, intent);
			} else if (GameData.build[buildIndex].mb.type == 4)// 店铺
			{
				int shopIndex = -1;
				if (GameData.corporation.shop != null) {
					for (int i = 0; i < GameData.corporation.shop.length; i++) {
						Log.i("Log2",
								(GameData.corporation.shop[i].buildingId + "==?==")
										+ GameData.build[buildIndex].mb.id);
						Log.i("Log2", "buildIndex=" + buildIndex);
						if (GameData.corporation.shop[i].buildingId == GameData.build[buildIndex].mb.id) {
							shopIndex = i;
						}
					}
				}
				Log.i("Log2", "shopIndex:==" + shopIndex);
//				if (shopIndex >= 0) {
//					bundle.putInt("type", 1);
//
//					bundle.putInt("selectIndex", shopIndex);
//				} else {
//					bundle.putInt("type", 0);
//
//					bundle.putInt("selectIndex", buildIndex);
//				}
//
//				intent.putExtras(bundle);
//
//				MainActivity.mContext.Activitychange(ShopInfoActivity.class,
//						intent);
				//by lm
				if (shopIndex >= 0) {//我的店铺

					bundle.putInt("position", shopIndex);
					
					intent.putExtras(bundle);
					
					MainActivity.mContext.Activitychange(shopInfo2Activity.class,
							intent);
				} else {//其他人店铺

					bundle.putInt("position", buildIndex);
					
					intent.putExtras(bundle);
					
					MainActivity.mContext.Activitychange(OtherShopInfoActivity.class,
							intent);
				}

			}
		} else// 建造
		{

			final Intent intent = new Intent();

			Bundle bundle = new Bundle();

			bundle.putInt("cityX", cityX);

			bundle.putInt("cityY", cityY);

			bundle.putInt("grid", smallgridIndex);

			intent.putExtras(bundle);

			boolean canBuildBuilding = true;

			for (int i = 0; i < GameData.build.length; i++) {
				if (GameData.build[i].mb.cityX == cityX
						&& GameData.build[i].mb.cityY == cityY) {
					canBuildBuilding = false;
				}
			}

			final boolean can = canBuildBuilding;
			
			final AlertDialog dlg = new AlertDialog.Builder(MainActivity.mContext).create();
			dlg.show();					
			dlg.getWindow().setContentView(R.layout.middle);
			ImageButton middle1 = (ImageButton)dlg.findViewById(R.id.middle1);
			ImageButton middle2 = (ImageButton)dlg.findViewById(R.id.middle2);
			ImageButton middle3 = (ImageButton)dlg.findViewById(R.id.middle3);
			/**开店*/
			middle1.setOnClickListener(new OnClickListener() {
				
				 
				public void onClick(View v) {
					// TODO Auto-generated method stub
					MainActivity.mContext.Activitychange(
							CreateShopAndBuildActivity.class, intent);
					dlg.dismiss();
				}
			});
			/**搜索*/
			middle2.setOnClickListener(new OnClickListener() {
				
				public void onClick(View v) {

//					MainActivity.mContext.Activitychange(
//							MyShopAndBuildingActivity.class, intent);
					MainActivity.mContext
					.Activitychange(
							SeacherFriendActivity.class,
							null);
					Connection.sendMessage(GameProtocol.CONNECTION_Search_User_Req,ConstructData.get_Search_User_Req((byte)0,(byte)1,null));
				
					dlg.dismiss();
				}
			});
			/**查看*/
			middle3.setOnClickListener(new OnClickListener()
			{
				
				public void onClick(View v)
				{
					/*if (can) {
						MainActivity.mContext.Activitychange(
								CreateBuildingActivity.class, intent);
					} else {
						Toast.makeText(MainActivity.mContext,
								MainActivity.resources.getString(R.string.mapview_toast1), Toast.LENGTH_LONG)
								.show();
					}*/
					showMyShopListDialog(); 
					dlg.dismiss();
				}
			});
			

//			Log.v(TAG, "建造");
//
//			final Intent intent = new Intent();
//
//			Bundle bundle = new Bundle();
//
//			bundle.putInt("cityX", cityX);
//
//			bundle.putInt("cityY", cityY);
//
//			bundle.putInt("grid", smallgridIndex);
//
//			intent.putExtras(bundle);
//
//			boolean canBuildBuilding = true;
//
//			for (int i = 0; i < GameData.build.length; i++) {
//				if (GameData.build[i].mb.cityX == cityX
//						&& GameData.build[i].mb.cityY == cityY) {
//					canBuildBuilding = false;
//				}
//			}
//
//			final boolean can = canBuildBuilding;
//            String mapview_item = MainActivity.resources.getString(R.string.mapview_item);
//			String item[] = mapview_item.split(","); 
//			
//			final AlertDialog dlg = new AlertDialog.Builder(MainActivity.mContext).create();
//			dlg.show();
//			dlg.getWindow().setContentView(R.layout.map_menu);
//	    //	tv =(TextView)dlg.findViewById(R.id.mapmenu3_list_text);
//	    //	tv.setTypeface(GameDefinition.face);
//     	//	lv = (ListView)dlg.findViewById(R.id.mapmenu3_list);
//			Button mapmenu_1 = (Button)dlg.findViewById(R.id.map_menu1);
//			mapmenu_1.setText(item[0]);
////			mapmenu_1.setTypeface(GameDefinition.face);
//			Button mapmenu_2 = (Button)dlg.findViewById(R.id.map_menu2);
//			mapmenu_2.setText(item[1]);
////			mapmenu_2.setTypeface(GameDefinition.face);
//			Button mapmenu_3 = (Button)dlg.findViewById(R.id.map_menu3);
//			mapmenu_3.setText(item[2]);
////			mapmenu_3.setTypeface(GameDefinition.face);
//			Button mapmenu_4 = (Button)dlg.findViewById(R.id.map_menu4);
//			mapmenu_4.setText(item[3]);
////			mapmenu_4.setTypeface(GameDefinition.face);
//			mapmenu_1.setOnClickListener(new OnClickListener() {
//				
//				 
//				public void onClick(View v) {
//					// TODO Auto-generated method stub
//					MainActivity.mContext.Activitychange(
//							CreateShopActivity.class, intent);
//					dlg.dismiss();
//				}
//			});
//			mapmenu_2.setOnClickListener(new OnClickListener() {
//				
//				 
//				public void onClick(View v) {
//					// TODO Auto-generated method stub
//					if (can) {
//						MainActivity.mContext.Activitychange(
//								CreateBuildingActivity.class, intent);
//					} else {
//						Toast.makeText(MainActivity.mContext,
//								MainActivity.resources.getString(R.string.mapview_toast1), Toast.LENGTH_LONG)
//								.show();
//					}
//					dlg.dismiss();
//				}
//			});
//			mapmenu_3.setOnClickListener(new OnClickListener() {
//				
//				 
//				public void onClick(View v) {
//					showMyShopListDialog(); 
//					dlg.dismiss();
//				}
//			});
//			mapmenu_4.setOnClickListener(new OnClickListener() {
//				
//				 
//				public void onClick(View v) {
//					// TODO Auto-generated method stub
//					showMyBuildingDialog();
//					dlg.dismiss();
//				}
//			});
		}

		Log.v(TAG, "onShowPress");
	}
	/**显示我的店铺列表*/
	public void showMyShopListDialog()
	{

		// TODO Auto-generated method stub
		String[] shop = new String[GameData.corporation.shop.length];
		Log.i("Log", "shop的长度=" + shop.length);
		for (int i = 0; i < shop.length; i++) {
			shop[i] = GameData.corporation.shop[i].name;
		}
		final  AlertDialog dlg1 = new AlertDialog.Builder(MainActivity.mContext).create();
		dlg1.show();
		dlg1.getWindow().setContentView(R.layout.mapmenu3_list);
		ListView lv = (ListView)dlg1.findViewById(R.id.mapmenu3_list);				
		ArrayList<HashMap<String, String>>list= new  ArrayList<HashMap<String,String>>();
		HashMap<String, String> map;
//			String[] shop = new String[GameData.corporation.shop.length];
//		Log.i("Log", "shop的长度=" + shop.length);
		if (shop.length == 0) {
			Toast.makeText(MainActivity.mContext, MainActivity.resources.getString(R.string.mapview_toast2),
					Toast.LENGTH_LONG).show();
			dlg1.dismiss();
			return;
		}
		else if(shop.length > 0){
		for (int i = 0; i < shop.length; i++) {
		//	shop[i] = GameData.corporation.shop[i].name;
			 map = new HashMap<String, String>();						 
			 map.put("name", GameData.corporation.shop[i].name);
			 list.add(map);
		}

		 SimpleAdapter listAdapter = new SimpleAdapter(MainActivity.mContext,list,   
	                R.layout.mapmenu3_item, new String[] {"name"},   
	                new int[] {R.id.mapmenu3_list_text});   
        lv.setAdapter(listAdapter);
	    lv.setOnItemClickListener(new OnItemClickListener(){
							 
							public void onItemClick(
									AdapterView<?> parent,
									View view, int position, long id) {
								// TODO Auto-generated method stub
								GameData.frushOriginalFocus();// 把当前focus值设置为初始focus值
								GameData.isResetMap = true;
								GameData.mapIdIndexBack = (short) SystemAPI
										.getShortArrayIndex(
												GameData.mapIds,
												GameData.corporation.shop[position].map_id);
								Connection
										.sendMessage(
												GameProtocol.CONNECTION_SEND_MAPAROUND_SHOP,
												ConstructData
														.getMapAroundShop(
																GameData.corporation.shop[position].map_id,
																(short) GameData.corporation.shop[position].cityX,
																(short) (short) GameData.corporation.shop[position].cityY,
																(byte) 0,
																(byte) (GameData.ARRAY_LENTH / 2)));
								Vector v = new Vector(5, 5);
								v.addElement(GameProtocol.CONNECTION_SEND_MAPAROUND_SHOP);
								GameGroupControl.gameGroupControl
										.setGameStatus(
												GameDefinition.Game_Loading,
												v);
								dlg1.dismiss();
							}
	    
				
	         });   
		}
	}
	/**显示我的公益建筑列表*/
	public void showMyBuildingDialog()
	{
		String[] shop = new String[GameData.corporation.build.length];

		for (int i = 0; i < shop.length; i++) {
			shop[i] = GameData.corporation.build[i].name;
		}

		final AlertDialog dlg2 = new AlertDialog.Builder(MainActivity.mContext).create();
		dlg2.show();
		dlg2.getWindow().setContentView(R.layout.mapmenu3_list);
    	ListView lv = (ListView)dlg2.findViewById(R.id.mapmenu3_list);
		ArrayList<HashMap<String, String>>list= new  ArrayList<HashMap<String,String>>();
		HashMap<String, String> map;
		if (shop.length == 0) {
			dlg2.dismiss();
			Toast.makeText(MainActivity.mContext, MainActivity.resources.getString(R.string.mapview_toast3),
					Toast.LENGTH_LONG).show();
			
		}
		else if(shop.length>0){
		for (int i = 0; i < shop.length; i++) {
			map = new HashMap<String, String>();
			map.put("name", GameData.corporation.build[i].name);
			list.add(map);
		}
		 SimpleAdapter listAdapter = new SimpleAdapter(MainActivity.mContext,list,   
	                R.layout.mapmenu3_item, new String[] {"name"},   
	                new int[] {R.id.mapmenu3_list_text});   
        lv.setAdapter(listAdapter);
        lv.setOnItemClickListener(new OnItemClickListener() {

			 
			public void onItemClick(AdapterView<?> parent,
					View view, int position, long id) {
				// TODO Auto-generated method stub

				GameData.isResetMap = true;
				GameData.mapIdIndexBack = (short) SystemAPI
						.getShortArrayIndex(
								GameData.mapIds,
								GameData.corporation.build[position].map_id);
				Connection
						.sendMessage(
								GameProtocol.CONNECTION_SEND_MAPAROUND_SHOP,
								ConstructData
										.getMapAroundShop(
												GameData.corporation.build[position].map_id,
												(short) GameData.corporation.build[position].address_x,
												(short) (short) GameData.corporation.build[position].address_y,
												(byte) 0,
												(byte) (GameData.ARRAY_LENTH / 2)));
				Vector v = new Vector(5, 5);
				v.addElement(GameProtocol.CONNECTION_SEND_MAPAROUND_SHOP);
				GameGroupControl.gameGroupControl
						.setGameStatus(
								GameDefinition.Game_Loading,
								v);
				dlg2.dismiss();
			}
			
		});
		}
	}
	
	public void onShowPress(MotionEvent event) {
		// build(event);
	}

	public boolean onSingleTapUp(MotionEvent event) {
		build(event);
		Log.v(TAG, "onSingleTapUp");
		return false;
	}

}
