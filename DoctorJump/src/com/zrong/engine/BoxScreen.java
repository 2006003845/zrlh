package com.zrong.engine;

import java.util.ArrayList;
import java.util.Random;

import org.jbox2d.collision.FilterData;
import org.jbox2d.collision.Shape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BoundaryListener;
import org.jbox2d.dynamics.ContactFilter;
import org.jbox2d.dynamics.ContactListener;
import org.jbox2d.dynamics.DestructionListener;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.contacts.ContactPoint;
import org.jbox2d.dynamics.contacts.ContactResult;
import org.jbox2d.dynamics.joints.Joint;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.zrong.data.DoctorJumpDB;
import com.zrong.data.GameData;
import com.zrong.entity.Bed;
import com.zrong.entity.Combo;
import com.zrong.entity.Goods;
import com.zrong.entity.Jumper;
import com.zrong.entity.Music2;
import com.zrong.entity.Player;
import com.zrong.entity.Prop;
import com.zrong.entity.Stage;
import com.zrong.game.Sprite;
import com.zrong.physics.Circle;
import com.zrong.physics.Physics;
import com.zrong.physics.PhysicsScreen;
import com.zrong.physics.Polygon;
import com.zrong.physics.Rectangle;
import com.zrong.ui.HomeActivity;
import com.zrong.ui.MainActivity;
import com.zrong.ui.R;
import com.zrong.utils.BitmapUtils;
import com.zrong.utils.MyCountDownTimer;
import com.zrong.utils.Utils;

public class BoxScreen extends PhysicsScreen {

	private float dt = 1f / 15f;
	private int iterations = 50;

	public boolean isGameStart = false;
	// public boolean isArouseGame = false;// 出发游戏仿真开始

	// private Rectangle jumper;
	// private Circle pig;

	private int isCombBool = 2;
	private int notCombBool = 1;

	private Vec2 collionPoint = new Vec2();
	// private boolean isCollideByJumper = false;
	public Handler mHandler = new Handler();

	public Runnable update2 = new Runnable() {
		public void run() {
			if (!isGameStart) {
				mHandler.removeCallbacks(this);
				return;
			}
			if (isGameOver()) {
				isGameStart = false;
				return;
			}
			if (pausePhysics) {
				world.step(0, 1);
			} else {
				world.step(dt, iterations);
			}
			mHandler.postDelayed(update2, 0);
		}
	};

	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			if (isGameOver()) {
				handler.removeMessages(msg.what);
				isGameStart = false;
				return;
			}
			if (!isGameStart) {
				handler.removeMessages(msg.what);
				return;
			}
			if (MainActivity.mIntance == null) {
				return;
			}
			int key = msg.what;
			switch (key) {
			case 0:// 更新Score
				MainActivity.mIntance.scoreChangeListener(msg.arg1,
						msg.arg2 == isCombBool);
				break;

			case 1:// 更新blood
				MainActivity.mIntance.lifeChangeListener(null);
				break;

			case 2:// 时间停止
				MainActivity.mIntance.stopTimeListener(null);
				break;

			case 3:
//				MainActivity.mIntance.teachVShake();
				break;
			}

		};
	};

	public Bed bed;

	private Rectangle border1, border2, border3, border4, border5, border6;
	private DoctorJumpDB doctorDB;
	private Polygon stone;
	private float friction = 0;
	private boolean isJumped = true;

	private int[][] floorsNums;

	public static BoxScreen boxScreen;

	private Stage stage;
	private int current_stage_id;

	private String senceName = GameData.img_sence_school_name;
	// private String bedName = GameData.img_bed_board_name;
	private String jumperName = GameData.img_npc_default_doctor_name;

	private String boarder1Name = GameData.img_school_board_up_name;
	private String boarder2Name = GameData.img_school_board_mid_name;
	private String boarder3Name = GameData.img_school_board_dow_name;

	private float jumper_radius;
	private float scaling_w = 1, scaling_h = 1;
	private float bed_w, bed_h;

	private Bitmap npc_bitm, bed_bitm;
	private Bitmap npc_knife_bm = getBitmap(GameData.img_npc_knif_kill_name);
	public ArrayList<Goods> goodsList;
	private Prop[] props;
	private World world;

	private Context mContext;

	public static DynamicBG dymamicBG, soulDynamicBG;

	public BoxScreen(Context context, Stage stage) {
		super(context);
		getWidth();
		mContext = context;
		// doctorDB = DoctorJumpDB.newInstanceOfDB(context);
		doctorDB = new DoctorJumpDB(context);

		// 初始化蹦床
		bed = new Bed();
		bed.bedName = GameData.img_bed_board_name;
		bed.style = Bed.BED_BOARD;

		// 关卡模式--数据初始化
		if (GameData.currentGameMode == GameData.GAME_MODE_STAGE) {
			this.stage = stage;
			senceName = stage.sceneName;
			if (stage.numb % 4 == 1) {
				boarder1Name = GameData.img_school_board_up_name;
				boarder2Name = GameData.img_school_board_mid_name;
				boarder3Name = GameData.img_school_board_dow_name;
			} else if (stage.numb % 4 == 2) {
				boarder1Name = GameData.img_snow_board_up_name;
				boarder2Name = GameData.img_snow_board_mid_name;
				boarder3Name = GameData.img_snow_board_dow_name;
			} else if (stage.numb % 4 == 3) {
				boarder1Name = GameData.img_city_board_up_name;
				boarder2Name = GameData.img_city_board_mid_name;
				boarder3Name = GameData.img_city_board_dow_name;
			} else if (stage.numb % 4 == 0) {
				boarder1Name = GameData.img_valley_board_up_name;
				boarder2Name = GameData.img_valley_board_mid_name;
				boarder3Name = GameData.img_valley_board_dow_name;
			}
		}
		goodsList = getGoodsList();
		props = getProps();

		setBedAndSenceName();// 设置背景v
		initNPC();// 设置NPC
		// 获取缩放比例
		scaling_w = getWidth() / GameData.default_w;
		scaling_h = getHeight() / GameData.default_h;
		npc_bitm = getBitmap(GameData.img_npc_default_doctor_name);
		jumper_radius = npc_bitm.getWidth() * scaling_h / 10;
		bed_bitm = getBitmap(bed.bedName);
		bed.currentBedBm = bed_bitm;
		// bed_w = bed_bitm.getWidth() * scaling_w;
		// bed_h = bed_bitm.getHeight() * scaling_h;
		bed_w = bed_bitm.getWidth();
		bed_h = bed_bitm.getHeight();

		GameData.borders = new Vec2[] {
				new Vec2(scaling_w * GameData.board1.x, scaling_h
						* GameData.board1.y),
				new Vec2(scaling_w * GameData.board2.x, scaling_h
						* GameData.board2.y),
				new Vec2(scaling_w * GameData.board3.x, scaling_h
						* GameData.board3.y),
				new Vec2(getWidth() - scaling_w * GameData.board1.x, scaling_h
						* GameData.board1.y),
				new Vec2(getWidth() - scaling_w * GameData.board2.x, scaling_h
						* GameData.board2.y),
				new Vec2(getWidth() - scaling_w * GameData.board3.x, scaling_h
						* GameData.board3.y) };
		Log.i("Log3", "GameData.borders" + GameData.borders[0]
				+ GameData.borders[1] + GameData.borders[2]
				+ GameData.borders[3] + GameData.borders[4]
				+ GameData.borders[5]);
		origins = new float[] { GameData.borders[0].y, GameData.borders[1].y,
				GameData.borders[2].y };
		origins46 = new float[] { GameData.borders[1].y, GameData.borders[2].y };
		origins68 = new float[] { GameData.borders[0].y, GameData.borders[1].y };

		Bitmap bitmap = getBitmap(senceName);
		if (bitmap.getWidth() < getWidth())
			bitmap = Bitmap.createScaledBitmap(bitmap, screenW, screenH, true);
		setBackground(bitmap);
		createWorld(new Vec2(0, 0), new Vec2(getWidth(), getHeight()),
				new Vec2(0, 50), true);

		border1 = createRectangle(GameData.borders[0].x / 2,
				GameData.borders[0].y, GameData.borders[0].x / 2, 0.5f, 0,
				friction, 0, 0);
		border1.bind(getBitmap(boarder1Name));
		border1.getBody().m_userData = "border";
		border1.getBody().m_type = Body.e_staticType;

		border2 = createRectangle(GameData.borders[1].x / 2,
				GameData.borders[1].y, GameData.borders[1].x / 2, 0.5f, 0,
				friction, 0, 0);
		border2.bind(getBitmap(boarder2Name));
		border2.getBody().m_userData = "border";
		border2.getBody().m_type = Body.e_staticType;

		border3 = createRectangle(GameData.borders[2].x / 2,
				GameData.borders[2].y, GameData.borders[2].x / 2, 0.5f, 0,
				friction, 0, 0);
		border3.bind(getBitmap(boarder3Name));
		border3.getBody().m_userData = "border";
		border3.getBody().m_type = Body.e_staticType;

		border4 = createRectangle(GameData.borders[3].x + GameData.borders[0].x
				/ 2, GameData.borders[3].y, GameData.borders[0].x / 2, 0.5f, 0,
				friction, 0, 0);
		border4.bind(getBitmap(boarder1Name));
		border4.getBody().m_userData = "border2";
		border4.getBody().m_type = Body.e_staticType;

		border5 = createRectangle(GameData.borders[4].x + GameData.borders[1].x
				/ 2, GameData.borders[4].y, GameData.borders[1].x / 2, 0.5f, 0,
				friction, 0, 0);
		border5.bind(getBitmap(boarder2Name));
		border5.getBody().m_userData = "border2";
		border5.getBody().m_type = Body.e_staticType;

		border6 = createRectangle(GameData.borders[5].x + GameData.borders[2].x
				/ 2, GameData.borders[5].y, GameData.borders[2].x / 2, 0.5f, 0,
				friction, 0, 0);
		border6.bind(getBitmap(boarder3Name));
		border6.getBody().m_userData = "border2";
		border6.getBody().m_type = Body.e_staticType;

		// bed.bedR = createRectangle(getWidth() >> 1, GameData.borders[2].y
		// + bed_h / 2 + 5, bed_w / 2, bed_h / 2, 0, friction, 1f, 0);
		// bed.bedR.bind(bed_bitm);

		// add(left);
		// add(right);
		// add(jumper);
		// add(pig);
		add(border1);
		add(border2);
		add(border3);
		add(border4);
		add(border5);
		add(border6);
		// add(bed.bedR);

		Bitmap fingerBm = getBitmap("finger.png");
		fingerSprite = bind(fingerBm, fingerBm.getWidth() / 2,
				fingerBm.getHeight(), 10);
		soulBms = new Bitmap[] { getBitmap("soul1.png"), getBitmap("soul2.png") };
		soulDynamicBG = DynamicBG.createDynamicBG(mContext, DynamicBG.SOUL,
				getWidth(), getHeight());

		world = getWorld();

		world.setContactFilter(new ContactFilter() {

			@Override
			public boolean shouldCollide(Shape shape1, Shape shape2) {
				FilterData filter1 = shape1.getFilterData();
				FilterData filter2 = shape2.getFilterData();

				if (filter1.groupIndex == filter2.groupIndex
						&& filter1.groupIndex != 0) {
					return false;
				}

				Body body1 = shape1.m_body;
				Body body2 = shape2.m_body;
				if (body1.getLinearVelocity().y < 0
						|| body2.getLinearVelocity().y < 0) {
					return false;
				}
				Vec2 b1P = body1.getPosition();
				Vec2 b2P = body2.getPosition();
				// if ((b2P.x + jumper_radius >= GameData.borders[3].x - 1 &&
				// b2P.x
				// + jumper_radius <= GameData.borders[3].x + 8)
				// || (b2P.x + jumper_radius >= GameData.borders[4].x - 1 &&
				// b2P.x
				// + jumper_radius <= GameData.borders[4].x + 8)
				// || (b2P.x + jumper_radius >= GameData.borders[5].x - 1 &&
				// b2P.x
				// + jumper_radius <= GameData.borders[5].x + 8)) {
				// return false;
				// }
				if ((b2P.x < GameData.borders[3].x + 1 && body1.equals(border4
						.getBody()))
						|| (b2P.x < GameData.borders[4].x + 1 && body1
								.equals(border5.getBody()))
						|| (b2P.x < GameData.borders[5].x + 1 && body1
								.equals(border6.getBody()))) {
					return false;
				}

				if (body2.getLinearVelocity().x < 0
						&& (border1.getBody().equals(body1)
								|| border2.getBody().equals(body1) || border3
								.getBody().equals(body1))) {
					return false;
				}
				if (body1.getLinearVelocity().x < 0
						&& (border1.getBody().equals(body2)
								|| border2.getBody().equals(body2) || border3
								.getBody().equals(body2))) {
					return false;
				}
				// 道具碰到boarder 无碰撞
				if ((body1.m_userData != null && body2.m_userData != null)
						&& ((body1.m_userData.equals("border2") && (!body2.m_userData
								.equals("jumper") && !body2.m_userData
								.equals("jumper2"))) || ((body2.m_userData
								.equals("border2") && (!body1.m_userData
								.equals("jumper") && !body1.m_userData
								.equals("jumper2")))))) {
					return false;
				}

				boolean collide = (filter1.maskBits & filter2.categoryBits) != 0
						&& (filter1.categoryBits & filter2.maskBits) != 0;
				return collide;

			}

//			@Override
//			public boolean rayCollide(Object userData, Shape shape) {
//				if (((Shape) userData).getFilterData().groupIndex == shape
//						.getFilterData().groupIndex
//						&& shape.getFilterData().groupIndex != 0) {
//					return true;
//				}
//				if (((Shape) userData).m_body.getLinearVelocity().y < 0
//						|| shape.m_body.getLinearVelocity().y < 0) {
//					return true;
//				}
//
//				if (shape.m_body.isDynamic()
//						&& ((shape.m_body.getPosition().x + jumper_radius == GameData.borders[3].x && shape.m_body
//								.getPosition().y + jumper_radius >= GameData.borders[3].y)
//								|| (shape.m_body.getPosition().x
//										+ jumper_radius == GameData.borders[4].x && shape.m_body
//										.getPosition().y + jumper_radius >= GameData.borders[4].y) || (shape.m_body
//								.getPosition().x + jumper_radius == GameData.borders[5].x && shape.m_body
//								.getPosition().y + jumper_radius >= GameData.borders[5].y))) {
//					return true;
//				}
//				// 道具碰到boarder 无碰撞
//				if ((userData != null && shape.m_body.m_userData != null)
//						&& ((userData.equals("border2") && shape.m_body.m_userData
//								.equals("jumper")) || ((shape.m_body.m_userData
//								.equals("border2") && !userData
//								.equals("jumper"))))) {
//					return true;
//				}
//
//				return shouldCollide((Shape) userData, shape);
//				// return false;
//			}
		});
		world.setContactListener(new ContactListener() {

			Body tempBody = null;

			@Override
			public void remove(ContactPoint point) {
				Body b_s1 = point.shape1.getBody();
				Body b_s2 = point.shape2.getBody();
			}

			@Override
			public void persist(ContactPoint point) {
				Body b_s1 = point.shape1.getBody();
				Body b_s2 = point.shape2.getBody();
				// 碰撞跳床
				if (bed != null && bed.bedR.getBody().equals(b_s1)) {
					b_s2.setLinearVelocity(new Vec2(70, -70));
				}
			}

			@Override
			public void add(ContactPoint point) {
				Vec2 v = point.position;

				Body b_s1 = point.shape1.getBody();
				Body b_s2 = point.shape2.getBody();

				if (isGameOver()) {
					return;
				}

				// 碰撞跳床
				if (bed != null && bed.bedR.getBody().equals(b_s1)) {

					// TODO 被带刀NPC砍到
					if (b_s2.getUserData().equals("knife_jumper")) {
						// 蹦床崩断----结束游戏
						breakBed();
						bed.isCollideByKnifeNPC = true;
						isGameStart = false;
						clearDataByUnCatchGameOver();// 清除数据

					}
					if (b_s2.getUserData().equals("jumper")) {

						// 修正蹦床的碰撞角碰撞效果
						if (v.x > b_s2.getPosition().x) {
							b_s2.setLinearVelocity(new Vec2(-100, 300));
						}
						bed.isCollideByNPC = true;
						Vec2 v2 = new Vec2(b_s2.getPosition().x - 5, b_s2
								.getPosition().y + 30);
						collionPoint = v2;
						collionSprite = bind(collionBm,
								collionBm.getWidth() / 6,
								collionBm.getHeight(), 10);
						if (bed.isCollideByThumbtack) {
							b_s2.setLinearVelocity(new Vec2(0, -3000));
						}

						// 判断bed是否在移动
						if (isBedMoving && orientation > 10) {
							// 向右移动
							// TODO 给jumper添加向右的作用力
							b_s2.setLinearVelocity(new Vec2(200, -400));
						}
					} else {
						bed.isCollideByNPC = false;
						collionPoint = null;
						getPropListener(b_s1, b_s2);// 碰撞道具效果
					}
				}
				// 腾空状态--行走(计分)
				if (border4.getBody().equals(b_s1) && b_s2.m_userData != null
						&& b_s2.m_userData.equals("jumper")) {
					if (tempBody != null && b_s2.equals(tempBody)) {
						return;
					}
					if (tempBody == null)
						tempBody = b_s2;

					b_s2.setLinearVelocity(new Vec2(70, 0));
					Message msg = handler.obtainMessage();
					msg.what = 0;
					msg.arg1 = 3;
					msg.arg2 = notCombBool;
					handler.sendMessage(msg);

					// TODO 第三层
					if (GameData.currentGameMode == GameData.GAME_MODE_LIMITTIME) {
						floorsNums[3][0]++;
					} else {
						GameData.floors[3][0]++;
					}
					updateCurrentResult();// 更新Result
					b_s2.m_userData = "jumper2";
				}
				if (border5.getBody().equals(b_s1) && b_s2.m_userData != null
						&& b_s2.m_userData.equals("jumper")) {
					if (tempBody != null && b_s2.equals(tempBody)) {
						return;
					}
					if (tempBody == null)
						tempBody = b_s2;
					b_s2.setLinearVelocity(new Vec2(70, 0));
					Message msg = handler.obtainMessage();
					msg.what = 0;
					msg.arg1 = 2;
					msg.arg2 = notCombBool;
					handler.sendMessage(msg);
					// TODO 第二层
					if (GameData.currentGameMode == GameData.GAME_MODE_LIMITTIME) {
						floorsNums[2][0]++;
					} else {
						GameData.floors[2][0]++;
					}
					updateCurrentResult();// 更新Result
					b_s2.m_userData = "jumper2";// 避免重复计分
				}

				if (border6.getBody().equals(b_s1) && b_s2.m_userData != null
						&& b_s2.m_userData.equals("jumper")) {
					if (tempBody != null && b_s2.equals(tempBody)) {
						return;
					}
					if (tempBody == null)
						tempBody = b_s2;
					b_s2.setLinearVelocity(new Vec2(70, 0));
					Message msg = handler.obtainMessage();
					msg.what = 0;
					msg.arg1 = 1;
					msg.arg2 = notCombBool;
					handler.sendMessage(msg);
					// TODO 第一层
					if (GameData.currentGameMode == GameData.GAME_MODE_LIMITTIME) {
						floorsNums[1][0]++;
					} else {
						GameData.floors[1][0]++;
					}
					updateCurrentResult();// 更新Result
					b_s2.m_userData = "jumper2";
				}

			}

			@Override
			public void result(ContactResult point) {
				// TODO Auto-generated method stub
			}
		});

		world.setBoundaryListener(new BoundaryListener() {

			@Override
			public void violation(Body body) {
				Vec2 position = body.getPosition();
				if (!isGameStart) {
					return;
				}
				if (body.m_userData.equals("jumper")) {

					if (position.y < GameData.borders[3].y
							&& position.x > GameData.borders[3].x) {
						Message msg = handler.obtainMessage();
						msg.what = 0;
						msg.arg1 = 3;
						msg.arg2 = notCombBool;
						handler.sendMessage(msg);

						// TODO 第三层
						if (GameData.currentGameMode == GameData.GAME_MODE_LIMITTIME) {
							floorsNums[3][0]++;
						} else {
							GameData.floors[3][0]++;
						}
					}
					if (position.y > GameData.borders[3].y
							&& position.y < GameData.borders[4].y
							&& position.x > getWidth()) {
						Message msg = handler.obtainMessage();
						msg.what = 0;
						msg.arg1 = 2;
						msg.arg2 = notCombBool;
						handler.sendMessage(msg);

						// TODO 第三层
						if (GameData.currentGameMode == GameData.GAME_MODE_LIMITTIME) {
							floorsNums[3][0]++;
						} else {
							GameData.floors[3][0]++;
						}
					}
					if (position.y > GameData.borders[4].y
							&& position.y < GameData.borders[5].y
							&& position.x > getWidth()) {
						Message msg = handler.obtainMessage();
						msg.what = 0;
						msg.arg1 = 1;
						msg.arg2 = notCombBool;
						handler.sendMessage(msg);
						// TODO 第一层
						if (GameData.currentGameMode == GameData.GAME_MODE_LIMITTIME) {
							floorsNums[1][0]++;
						} else {
							GameData.floors[1][0]++;
						}
					}
					// remove(body.)

					updateCurrentResult();// 更新Result
				}

				if (body.m_userData.equals("jumper")
						&& ((position.y < 0
								&& position.x > GameData.borders[0].x && position.x < GameData.borders[3].x - 5)
								|| position.y > getHeight() || position.x < 0 || (position.x > getWidth() && position.y > GameData.borders[5].y))) {
					// TODO 跳跃失败 --红心破
					if (GameData.currentGameMode == GameData.GAME_MODE_LIMITTIME) {

						floorsNums[0][0]++;
						countCombo();
						floorsNums = new int[4][1];
					} else {
						GameData.floors[0][0]++;
						GameData.player.setLive_value(GameData.player
								.getLive_value() - 1);
						Message msg = handler.obtainMessage();
						msg.what = 1;
						handler.sendMessage(msg);// 生命值更新
						updateCurrentResult();// 更新Result
					}
					// TODO 飞出灵魂
					if (soulDynamicBG != null) {
						soulDynamicBG.update(true, position.x);
					}
				}

				// body.setFrozen(true);
				// body.allowSleeping(true);
				// body.putToSleep();
				// body.m_type = Body.e_staticType;
//				world.setLock(false);
				world.destroyBody(body);

			}
		});

		floorsNums = new int[4][1];// 限时模式(每段Combo值)

		world.setDestructionListener(new DestructionListener() {

			@Override
			public void sayGoodbye(Shape shape) {
				// Shape.destroy(shape);
			}

			@Override
			public void sayGoodbye(Joint joint) {
			}
		});

		// 动态场景
		if (GameData.currentGameMode == GameData.GAME_MODE_STAGE
				&& GameData.currentStage.numb % 4 == 2) {
			dymamicBG = DynamicBG.createDynamicBG(context, DynamicBG.SNOW,
					screenW, screenH);
		} else if (GameData.currentGameMode == GameData.GAME_MODE_STAGE
				&& GameData.currentStage.numb % 4 == 0) {
			dymamicBG = DynamicBG.createDynamicBG(context, DynamicBG.LEAVES,
					screenW, screenH);
		} else {
			if (dymamicBG == null) {
				dymamicBG = DynamicBG.createDynamicBG(context,
						DynamicBG.NOTHING, screenW, screenH);
			}
		}
		collionBm = getBitmap("collion.png");// 碰撞烟雾
		
	}

	private int count = 0;
	ArrayList<Physics> list2 = new ArrayList<Physics>();

	private Bitmap collionBm;

	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		if (bed != null && bed.isCollideByNPC) {
			if (collionSprite != null && collionPoint != null) {
				collionSprite.draw(canvas, collionPoint.x - 17,
						collionPoint.y - 10);
				collionSprite.update();
				collionSprite.setLoop(false);
			}
		}
		if (soulDynamicBG != null) {
			soulDynamicBG.update(false, 0);
			soulDynamicBG.draw(canvas);
		}

		// 绘制点击带刀NPC
		if (knifeJumper != null && !knifeJumper.isClicked
				&& knifeJumper.jumperC != null && fingerSprite != null) {
			fingerSprite.draw(canvas, knifeJumper.jumperC.getPosition().x + 15,
					knifeJumper.jumperC.getPosition().y + 15);
			fingerSprite.update();
			fingerSprite.setLoop(true);
		}
		// 绘制点击fire
		if (fireJ != null && fireJ.jumperC != null && fingerSprite != null) {
			fingerSprite.draw(canvas, fireJ.jumperC.getPosition().x + 15,
					fireJ.jumperC.getPosition().y + 15);
			fingerSprite.update();
			fingerSprite.setLoop(true);
		}
		// Sprite sprite = bird.getSprite();
		/*
		 * if (sprite.getFrame() == sprite.getFrameCount() - 1) { count++; if
		 * (count == 3) { sprite.setFrame(0); pig.getSprite().setFrame(0); count
		 * = 0; } }
		 */
		if (!isGameStart) {
			return;
		}
		list2.clear();
		ArrayList<Physics> list = getPhysics();
		list2.addAll(list);
		synchronized (list2) {
			for (Physics physics : list2) {
				if (physics instanceof Circle) {
					if (((Circle) physics).getBody().m_userData != null
							&& (((Circle) physics).getBody().m_userData
									.equals("jumper") || ((Circle) physics)
									.getBody().m_userData
									.equals("knife_jumper"))) {
						judgeAndChangeBodyStat((Circle) physics);
					}
				}
			}
		}

		// 彩虹桥技能
		if (GameData.isUsedRainbowSkill) {
			if (rainBm == null) {
				rainBm = getBitmap("rainbow.png");
			}
			bed.bedR.bind(rainBm);
			bed_w = getWidth() - 2 * GameData.board3.x + 10;
			bed.bedR.setHalfH(bed_w / 2);
		}
		// else {
		// if (bed != null && bed.currentBedBm != null) {
		// bed_w = bed.currentBedBm.getWidth();
		// }
		// }
	}

	Bitmap rainBm;

	// 判断body跳动的地方
	private void judgeAndChangeBodyStat(Circle body) {
		// TODO
		Body jumperBody = body.getBody();
		Vec2 currentLocation = jumperBody.getPosition();
		Vec2 vv = jumperBody.getLinearVelocity();

		if (vv.x > 0 && currentLocation.x >= GameData.borders[0].x - 6
				&& currentLocation.x <= GameData.borders[0].x + 2
				&& currentLocation.y < GameData.borders[0].y) {

			// TODO 跑步-->起跳
			bodyJump(jumperBody, Utils.getRandomLinearV(1));

		} else if (vv.x > 0 && currentLocation.x >= GameData.borders[1].x - 6
				&& currentLocation.x <= GameData.borders[1].x + 2
				&& currentLocation.y < GameData.borders[1].y
				&& currentLocation.y > GameData.borders[0].y) {// 2层

			bodyJump(jumperBody, Utils.getRandomLinearV(2));

		} else if (vv.x > 0 && currentLocation.x >= GameData.borders[2].x - 6
				&& currentLocation.x <= GameData.borders[2].x + 2
				&& currentLocation.y < GameData.borders[2].y
				&& currentLocation.y > GameData.borders[1].y) {// 3层

			bodyJump(jumperBody, Utils.getRandomLinearV(3));

		} else {

			return;
		}

		if (jumperBody.getUserData().equals("knife_jumper")
				&& knifeJumper != null) {

			knifeJumper.jumperC.bind(npc_knife_bm, npc_knife_bm.getWidth() / 3,
					npc_knife_bm.getHeight(), 10);
		}
		// update();
	}

	// int j = 0;

	public Circle createJumpers(int count, float x, float y, String npcImgName,
			String user_data) {
		npc_bitm = getBitmap(npcImgName);
		jumper_radius = npc_bitm.getWidth() * scaling_h / 12;
		Circle jumper = createCircle(jumper_radius, y - jumper_radius,
				jumper_radius, 10f, friction, 1f, 30);

		jumper.bind(npc_bitm, npc_bitm.getWidth() / 6, npc_bitm.getHeight(), 3);
		jumper.getBody().m_userData = user_data;// 对每一个创建出来的跳跃者进行唯一标识
		jumper.getBody().m_type = Body.e_dynamicType;
		jumper.getBody().wakeUp();
		jumper.getBody().setLinearVelocity(new Vec2(70, 0));
		add(jumper);
		return jumper;
	}

	@Override
	public void update() {
		super.update();
	}

	@Override
	public boolean onKeyDown(int arg0, KeyEvent arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onKeyUp(int arg0, KeyEvent arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	public static Random rand = new Random();
	private float mTouchStartX;
	private float mTouchCurrX;
	private boolean isProp = true;

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO
		int e = event.getAction();

		float x = event.getX();
		float y = event.getY();

		// 点飞火
		if (fireJ != null
				&& fireJ.jumperC != null
				&& (fireJ.jumperC.getBody().getPosition().x >= x - 22 && fireJ.jumperC
						.getBody().getPosition().x <= x + 22)
				&& (fireJ.jumperC.getBody().getPosition().y >= y - 22 && fireJ.jumperC
						.getBody().getPosition().y <= y + 22)) {
			fireJ.jumperC.getBody().setLinearVelocity(new Vec2(300, -300));
			fireJ.isClicked = true;
		}
		// 点飞带刀npc
		if (knifeJumper != null
				&& knifeJumper.jumperC != null
				&& (knifeJumper.jumperC.getBody().getPosition().x >= x - 30 && knifeJumper.jumperC
						.getBody().getPosition().x <= x + 30)
				&& (knifeJumper.jumperC.getBody().getPosition().y >= y - 30 && knifeJumper.jumperC
						.getBody().getPosition().y <= y + 30)) {
			knifeJumper.isClicked = true;
			knifeJumper.jumperC.getBody()
					.setLinearVelocity(new Vec2(300, -300));
		}
		switch (e) {
		// 设置拖拉模式
		case MotionEvent.ACTION_DOWN:

			if (bed.bedR == null) {
				bed.bedR = createRectangle(getWidth() >> 1,
						GameData.borders[2].y + bed_h / 2 + 5, bed_w / 2,
						bed_h / 2, 0, friction, 1f, 0);
				bed.bedR.bind(bed_bitm);
				add(bed.bedR);
			}
			mTouchStartX = mTouchCurrX = x;
			break;
		case MotionEvent.ACTION_UP:
			orientation = 0;
			isBedMoving = false;
			break;
		// 设置多点触摸模式
		case MotionEvent.ACTION_POINTER_DOWN:
			break;
		case MotionEvent.ACTION_POINTER_UP:
			break;
		// 移动滑板
		case MotionEvent.ACTION_MOVE:
			isBedMoving = true;
			mTouchCurrX = x;
			orientation = onScroll(mTouchStartX, mTouchCurrX);
			mTouchStartX = mTouchCurrX;
			break;
		}
		return true;
	}

	/**
	 * 蹦床是否在移动
	 */
	private boolean isBedMoving = false;
	// 蹦床的移动方向--左为负，右为正
	private int orientation = 0;

	/**
	 * 拖动蹦床
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public int onScroll(float startX, float currentX) {
		if (bed == null) {
			return 0;
		}
		float distance_x = currentX - startX;
		Vec2 temp = bed.bedR.getBody().getPosition();
		Vec2 v = new Vec2(temp.x, temp.y);
		v.x += distance_x;
		if (v.x > getWidth() - (GameData.borders[2].x + bed_w / 2)) {
			v.x = getWidth() - (GameData.borders[2].x + bed_w / 2);
		} else if (v.x < (GameData.borders[2].x + bed_w / 2)) {
			v.x = (GameData.borders[2].x + bed_w / 2);
		}
		bed.bedR.getBody().wakeUp();
		bed.bedR.getBody().setXForm(v, 0);
		// 跳床摆动时,清楚图钉
		if (current_orient * distance_x < 0) {
			clearThumbtackBed();// 清理图钉
		}
		current_orient = (int) distance_x;
		return (int) distance_x;
	}

	private int current_orient = 1;

	/**
	 * 回复蹦床(去图钉)
	 */
	private void clearThumbtackBed() {
		if (bed != null && bed.isCollideByThumbtack) {
			bed.isCollideByThumbtack = false;
			bed.bedR.bind(bed_bitm);
			bed.bedR.setHalfH(bed_bitm.getHeight() / 2);
			bed.bedR.setHalfW(bed_bitm.getWidth() / 2);
		}
	}

	/**
	 * 跳跃
	 * 
	 * @param body
	 *            跳跃实体
	 * @param force
	 *            力
	 */
	private void bodyJump(Body body, Vec2 linearV) {
		body.setLinearVelocity(linearV);
	}

	// private boolean isNeedColin(Body body){
	// return false;
	// }
	private float[] origins, origins46, origins68;

	public Handler createHandler = new Handler();

	class CreateJumperThread extends Thread {
		boolean isArouse = false;// 是否被激发
		int arouseTime = -1;

		@Override
		public void run() {
			while (true) {

				// 当游戏暂停时，停止创造NPC
				if (pausePhysics) {
					continue;
				}
				// 当游戏结束，停止创造NPC
				if (isGameOver()) {
					isGameStart = false;
					// clearAllObj();
					break;
				}
				if (!isGameStart) {
					break;
				}

//				switch (GameData.currentGameMode) {
//				case GameData.GAME_MODE_STAGE:
//					if (GameData.currentStage == null) {
//						break;
//					}
//					if (GameData.currentStage.spacingTime == Stage.SPACING_TIME_2_3) {
//						setSleepTime(2000 + rand.nextInt(1000));
//					} else if (GameData.currentStage.spacingTime == Stage.SPACING_TIME_2_25) {
//						setSleepTime(2000 + rand.nextInt(500));
//					} else if (GameData.currentStage.spacingTime == Stage.SPACING_TIME_15_25) {
//						setSleepTime(1500 + rand.nextInt(1000));
//					} else if (GameData.currentStage.spacingTime == Stage.SPACING_TIME_15_2) {
//						setSleepTime(1500 + rand.nextInt(500));
//					} else if (GameData.currentStage.spacingTime == Stage.SPACING_TIME_1_2) {
//						setSleepTime(1000 + rand.nextInt(1000));
//					} else {
//						setSleepTime(2000);
//					}
//					break;
//				case GameData.GAME_MODE_ENDLESS:
//					Log.i("Loh", "GameData.playLastTime"
//							+ GameData.playLastTime);
//					if (GameData.playLastTime > 0
//							&& GameData.playLastTime % 60 == 0) {
//						arouseTime = GameData.playLastTime;
//						isArouse = true;
//					}
//					if (isArouse && GameData.playLastTime < arouseTime + 8) {
//						setSleepTime(500);
//					} else {
//						// 阶段3
//						if (GameData.playLastTime > 180) {
//							if (rand.nextInt(10) < 4) {
//								setSleepTime(500);
//							} else {
//								setSleepTime(1000);
//							}
//						}// 阶段2
//						else if (GameData.playLastTime > 90) {
//							if (rand.nextInt(10) < 3) {
//								setSleepTime(750);
//							} else {
//								setSleepTime(1500);
//							}
//						}// 阶段1
//						else if (GameData.playLastTime >= 0) {
//							if (rand.nextInt(5) < 1) {
//								setSleepTime(1000);
//							} else {
//								setSleepTime(2000);
//							}
//						} else {
//							setSleepTime(2000);
//						}
//					}
//					// setSleepTime(2000);
//					break;
//				case GameData.GAME_MODE_LIMITTIME:
//					Log.i("Loh", "GameData.leftTimeOfTimeLimit"
//							+ GameData.leftTimeOfTimeLimit);
//					// 触发
//					if (GameData.leftTimeOfTimeLimit > 0
//							&& GameData.leftTimeOfTimeLimit < 90
//							&& GameData.leftTimeOfTimeLimit % 15 == 0
//							&& rand.nextInt(10) < 2) {
//						arouseTime = GameData.leftTimeOfTimeLimit;
//						isArouse = true;
//					}
//					if (isArouse
//							&& GameData.leftTimeOfTimeLimit > arouseTime - 8) {
//						setSleepTime(500);
//
//					}// 90-60
//					else {
//						if (GameData.leftTimeOfTimeLimit > 60) {
//							isArouse = false;
//							arouseTime = 90;
//							if (rand.nextInt(10) < 2) {
//								setSleepTime(1000);
//							} else {
//								setSleepTime(2000);
//							}
//						}// 60-30
//						else if (GameData.leftTimeOfTimeLimit > 30) {
//							isArouse = false;
//							arouseTime = 90;
//							if (rand.nextInt(10) < 3) {
//								setSleepTime(3000 / 4);
//							} else {
//								setSleepTime(1500);
//							}
//						}// 30-0
//						else if (GameData.leftTimeOfTimeLimit > 0) {
//							isArouse = false;
//							arouseTime = 90;
//							if (rand.nextInt(100) < 35) {
//								setSleepTime(500);
//							} else {
//								setSleepTime(1000);
//							}
//						} else {
//							setSleepTime(2000);
//						}
//					}
//					break;
//				}

				 setSleepTime(2000);
				if (!isGameStart) {
					createHandler.removeCallbacks(createJumperRunnable);
				}

				createHandler.postDelayed(createJumperRunnable, 500);

			}
			if (isGameOver()) {
				this.interrupt();
				return;
			}
			super.run();
		}
	}

	private Circle doubleC, goldC, lifeC, stopC, slowC, extendC, shortenC,
			thumbtackC, stoneC, fireC;
	private Jumper knifeJumper, fireJ;

	class CreatePropThread extends Thread {
		@Override
		public void run() {
			while (true) {
				// 当游戏暂停时，停止创造NPC
				if (pausePhysics) {
					continue;
				}
				// 当游戏结束，停止创造NPC
				if (isGameOver()) {
					isGameStart = false;
					break;
				}
				if (!isGameStart) {
					break;
				}
				setSleepTime(3000 + (rand.nextInt(2) == 0 ? -1000 : 1000));
				if (!isGameStart) {
					createHandler.removeCallbacks(createPropRunnable);
				}
				createHandler.postDelayed(createPropRunnable, 1000);
			}
			if (!isGameStart) {
				this.interrupt();
				return;
			}
			if (isGameOver()) {
				this.interrupt();
				return;
			}
			super.run();
		}
	}

	Bitmap fireBm;

	/**
	 * 游戏是否结束
	 * 
	 * @return
	 */
	public boolean isGameOver() {
		if (bed == null) {
			return false;
		}
		if (GameData.currentGameMode == GameData.GAME_MODE_LIMITTIME) {
			return GameData.leftTimeOfTimeLimit == 0 || bed.isCollideByFire
					|| bed.isCollideByKnifeNPC;
		} else {
			return GameData.player.getLive_value() == 0 || bed.isCollideByFire
					|| bed.isCollideByKnifeNPC;
		}
	}

	/**
	 * 初始化 在该游戏中出现的npc形象
	 */
	private void initNPC() {
		if (GameData.currentGameMode == GameData.GAME_MODE_STAGE) {
			switch (stage.npc) {
			case Stage.APPEAR_NPC_STU:
				GameData.npc_imgs = new String[] { GameData.img_npc_stu_name };
				break;
			case Stage.APPEAR_NPC_STU_OUTM:
				GameData.npc_imgs = new String[] { GameData.img_npc_stu_name,
						GameData.img_npc_outm_name };
				break;
			case Stage.APPEAR_NPC_STU_WHITE:
				GameData.npc_imgs = new String[] { GameData.img_npc_stu_name,
						GameData.img_npc_white_name };
				break;
			case Stage.APPEAR_NPC_ANDR_NINJA:
				GameData.npc_imgs = new String[] { GameData.img_npc_andr_name,
						GameData.img_npc_ninga_name };
				break;
			case Stage.APPEAR_NPC_DOCTOR_STU:
				GameData.npc_imgs = new String[] {
						GameData.img_npc_luxury_doctor_name,
						GameData.img_npc_stu_name };
				break;
			case Stage.APPEAR_NPC_DOCTOR_OUTM:
				GameData.npc_imgs = new String[] {
						GameData.img_npc_luxury_doctor_name,
						GameData.img_npc_outm_name };
				break;
			case Stage.APPEAR_NPC_DOCTOR_STU_WHITE:
				GameData.npc_imgs = new String[] {
						GameData.img_npc_luxury_doctor_name,
						GameData.img_npc_stu_name, GameData.img_npc_white_name };
				break;
			case Stage.APPEAR_NPC_DOCTOR_ANDR_NINJA:
				GameData.npc_imgs = new String[] { GameData.img_npc_andr_name,
						GameData.img_npc_ninga_name,
						GameData.img_npc_luxury_doctor_name };
				break;
			case Stage.APPEAR_NPC_STU_BLACK:
				GameData.npc_imgs = new String[] { GameData.img_npc_stu_name,
						GameData.img_npc_black_name };
				break;
			case Stage.APPEAR_NPC_NINJA_BLACK:
				GameData.npc_imgs = new String[] { GameData.img_npc_black_name,
						GameData.img_npc_ninga_name };
				break;

			case Stage.APPEAR_NPC_ALL:
				GameData.npc_imgs = new String[] { GameData.img_npc_andr_name,
						GameData.img_npc_black_name,
						GameData.img_npc_knif_name,
						GameData.img_npc_luxury_doctor_name,
						GameData.img_npc_outm_name, GameData.img_npc_stu_name,
						GameData.img_npc_white_name,
						GameData.img_npc_ninga_name };
				break;
			}
		} else {
			// 检索所有装备了的NPC
			ArrayList<Goods> list = new ArrayList<Goods>();
			for (Goods g : goodsList) {
				if (g.g_type == Goods.GOODS_TYPE_JUMPER
						&& g.isEquipment == Goods.HAVEEQUIP) {
					list.add(g);
				}
			}
			int count = list.size();
			if (count == 0) {
				GameData.npc_imgs = new String[] { GameData.img_npc_default_doctor_name };
			} else {
				GameData.npc_imgs = new String[count];
				for (int i = 0; i < count; i++) {
					GameData.npc_imgs[i] = list.get(i).g_describ;
				}
			}
		}
	}

	/**
	 * 设置关卡
	 * 
	 * @param stage
	 */
	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public void setBedAndSenceName() {
		for (Goods g : goodsList) {
			if (g.g_type == Goods.GOODS_TYPE_BED
					&& g.isEquipment == Goods.HAVEEQUIP) {
				bed.bedName = g.g_describ;
				bed.style = g.g_id;
			}
			if (GameData.currentGameMode != GameData.GAME_MODE_STAGE
					&& g.g_type == Goods.GOODS_TYPE_SENCE
					&& g.isEquipment == Goods.HAVEEQUIP) {
				senceName = g.g_describ;
				switch (g.g_id) {
				case GameData.sceneSnow_ID:
					boarder1Name = GameData.img_snow_board_up_name;
					boarder2Name = GameData.img_snow_board_mid_name;
					boarder3Name = GameData.img_snow_board_dow_name;
					// 飘雪花
					dymamicBG = DynamicBG.createDynamicBG(context,
							DynamicBG.SNOW, screenW, screenH);
					break;
				case GameData.sceneCity_ID:
					boarder1Name = GameData.img_city_board_up_name;
					boarder2Name = GameData.img_city_board_mid_name;
					boarder3Name = GameData.img_city_board_dow_name;
					break;
				case GameData.sceneValley_ID:
					boarder1Name = GameData.img_valley_board_up_name;
					boarder2Name = GameData.img_valley_board_mid_name;
					boarder3Name = GameData.img_valley_board_dow_name;
					// 飘落叶
					dymamicBG = DynamicBG.createDynamicBG(context,
							DynamicBG.LEAVES, screenW, screenH);
					break;

				default:
					boarder1Name = GameData.img_school_board_up_name;
					boarder2Name = GameData.img_school_board_mid_name;
					boarder3Name = GameData.img_school_board_dow_name;
					break;
				}

			}
		}
	}

	/**
	 * 获取所有的商品列表
	 * 
	 * @return
	 */
	private ArrayList<Goods> getGoodsList() {
		ArrayList<Goods> list = Goods.getGoodsList(doctorDB.query(
				Goods.TAB_NAME, null, null, null, null, null, null));
		doctorDB.closeDB();
		return list;
	}

	public void setSleepTime(long sleepTime) {
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 从数据库中获取道具列表
	 * 
	 * @return
	 */
	private Prop[] getProps() {
		Prop[] ps = Prop.getProps(doctorDB.query(Prop.TAB_NAME, null, null,
				null, null, null, null));
		doctorDB.closeDB();
		for (int i = 0, count = ps.length; i < count; i++) {
			Prop temp = null;
			// 生命值
			if (ps[i].p_efficacy == 2) {
				temp = ps[i];
				ps[i] = ps[count - 1];
				ps[count - 1] = temp;
			}
			// 时间停止
			if (ps[i].p_efficacy == 3) {
				temp = ps[i];
				ps[i] = ps[count - 2];
				ps[count - 2] = temp;
			}
		}
		return ps;
	}

	/**
	 * 创建道具刚体
	 * 
	 * @param x
	 * @param y
	 * @param radius
	 * @param propBitm
	 * @param userName
	 * @param velocity
	 */
	private Circle createProp(float x, float y, float radius, Bitmap propBitm,
			String userName, Vec2 velocity) {

		Circle prop = createCircle(x, y, radius, 10f, friction, 1f, 30);
		prop.bind(propBitm);
		prop.getBody().m_userData = userName;// 对每一个创建出来的道具进行唯一标识--标识其功能
		prop.getBody().m_type = Body.e_dynamicType;
		prop.getBody().wakeUp();
		prop.getBody().setLinearVelocity(velocity);
		add(prop);
		return prop;
	}

	PropEffectTimer double_propEffectTimer = new PropEffectTimer(10 * 1000,
			1000, 0);
	PropEffectTimer stop_propEffectTimer = new PropEffectTimer(10 * 1000, 1000,
			3);
	PropEffectTimer slow_propEffectTimer = new PropEffectTimer(10 * 1000, 1000,
			4);
	PropEffectTimer extend_propEffectTimer = new PropEffectTimer(10 * 1000,
			1000, 5);
	PropEffectTimer shorten_propEffectTimer = new PropEffectTimer(10 * 1000,
			1000, 6);
	PropEffectTimer stone_propEffectTimer = new PropEffectTimer(10 * 1000,
			1000, 8);

	private void clearDataByUnCatchGameOver() {
		GameData.currentResult.bloodvalue = 0;
		GameData.currentResult.floor1 = 0;
		GameData.currentResult.floor2 = 0;
		GameData.currentResult.floor3 = 0;
		GameData.currentResult.lastplaytime = 0;
		GameData.comboList.clear();
	}

	/**
	 * 获得道具效果
	 * 
	 * @param body1
	 * @param body2
	 */
	private void getPropListener(Body body1, Body body2) {
		if (bed == null) {
			return;
		}
		if (body1.equals(bed.bedR.getBody())) {
			// fire
			if (body2.m_userData.equals(Prop.FIRE_USER_NAME)) {
				// 碰撞音乐+...
				Music2.getInstance(mContext).start(R.raw.getprop, false);
				// 结束游戏
				bed.isCollideByFire = true;
				isGameStart = false;
				GameData.player.setLive_value(0);

				// 清除数据
				clearDataByUnCatchGameOver();

				// 消失道具
				clearPropC(fireJ.jumperC == null ? fireC : fireJ.jumperC);

			}// TODO 双倍--出现双倍的NPC
			else if (body2.m_userData.equals(Prop.DOUBLE_USER_NAME)) {
				// 碰撞音乐+...
				Music2.getInstance(mContext).start(R.raw.getprop, false);

				// 消失道具
				clearPropC(doubleC);
				// doubleC = null;
				double_propEffectTimer.cancel();
				double_propEffectTimer.reStart(10 * 1000);

			}// 金币--增加金币值
			else if (body2.m_userData.equals(Prop.GOLD_USER_NAME)) {
				// 碰撞音乐+...
				Music2.getInstance(mContext).start(R.raw.getprop, false);

				SharedPreferences prefs = prefs = PreferenceManager
						.getDefaultSharedPreferences(context);
				prefs.edit()
						.putInt(HomeActivity.PLAYER_WEALTH,
								prefs.getInt(HomeActivity.PLAYER_WEALTH, 0) + 10)
						.commit();

				// 消失道具
				clearPropC(goldC);

			}// 生命--增加生命值
			else if (body2.m_userData.equals(Prop.LIFE_USER_NAME)) {
				// 碰撞音乐+...
				Music2.getInstance(mContext).start(R.raw.getprop, false);

				// 消失道具
				clearPropC(lifeC);
				// lifeC = null;

				Player p = Player.newInstanceOfPlayer();
				if (GameData.currentGameMode == GameData.GAME_MODE_STAGE) {
					GameData.player.setLive_value(p.getLive_value() < 3 ? p
							.getLive_value() + 1 : p.getLive_value());

				} else if (GameData.currentGameMode == GameData.GAME_MODE_ENDLESS) {
					GameData.player.setLive_value(p.getLive_value() < 6 ? p
							.getLive_value() + 1 : p.getLive_value());
				}
				Message msg = handler.obtainMessage();
				msg.what = 1;
				handler.sendMessage(msg);// 生命值更新
				updateCurrentResult();// 更新Result

			}// TODO 停止--时间停止
			else if (body2.m_userData.equals(Prop.STOP_USER_NAME)) {
				// 碰撞音乐+...
				Music2.getInstance(mContext).start(R.raw.getprop, false);

				// 消失道具
				clearPropC(stopC);
				// stopC = null;

				GameData.getPropOfStopTime = true;
				stop_propEffectTimer.cancel();
				stop_propEffectTimer.reStart(10 * 1000);

			}// TODO 减速--降低NPC速度
			else if (body2.m_userData.equals(Prop.SLOW_USER_NAME)) {
				// 碰撞音乐+...
				Music2.getInstance(mContext).start(R.raw.getprop, false);

				// 消失道具
				clearPropC(slowC);
				// slowC = null;
				slow_propEffectTimer.cancel();
				slow_propEffectTimer.reStart(10 * 1000);
			}// 伸长--伸长蹦床---持续10秒
			else if (body2.m_userData.equals(Prop.EXTEND_USER_NAME)) {
				// 碰撞音乐+...
				Music2.getInstance(mContext).start(R.raw.getprop, false);

				// 消失道具
				clearPropC(extendC);
				// extendC = null;

				bed.size_state = Bed.BED_LONG;
				Bitmap bm = BitmapUtils.zoomBitmap(bed.currentBedBm,
						(float) (bed.currentBedBm.getWidth() * 1.5),
						(float) (bed.currentBedBm.getHeight()));
				bed_w = bm.getWidth();
				bed_h = bm.getHeight();
				bed.bedR.bind(bm);
				bed.bedR.setHalfW(bed_w / 2);
				extend_propEffectTimer.cancel();
				extend_propEffectTimer.reStart(10 * 1000);
			}// 缩短--缩短蹦床
			else if (body2.m_userData.equals(Prop.SHORTEN_USER_NAME)) {
				// 碰撞音乐+...
				Music2.getInstance(mContext).start(R.raw.getprop, false);

				// 消失道具
				clearPropC(shortenC);
				// shortenC = null;

				bed.size_state = Bed.BED_SHORT;
				Bitmap bm = BitmapUtils.zoomBitmap(bed.currentBedBm,
						(float) (bed.currentBedBm.getWidth() * 0.5),
						(float) (bed.currentBedBm.getHeight()));
				bed_w = bm.getWidth();
				bed_h = bm.getHeight();
				bed.bedR.bind(bm);
				bed.bedR.setHalfW(bed_w / 2);
				shorten_propEffectTimer.cancel();
				shorten_propEffectTimer.reStart(10 * 1000);
			}// 图钉--蹦床变为有图钉的
			else if (body2.m_userData.equals(Prop.THUMBTACK_USER_NAME)) {
				Log.i("Log9", "thumb---1");

				// 碰撞音乐+...
				Music2.getInstance(mContext).start(R.raw.getprop, false);

				// 消失道具
				clearPropC(thumbtackC);

				// 此时状态是否为石床状态
				if (bed.isCollideByStone) {
					return;
				}
				// thumbtackC = null;

				bed.isCollideByThumbtack = true;
				Message msg = handler.obtainMessage();
				msg.what = 3;
				handler.sendMessage(msg);

				if (bed.style == Bed.BED_BOARD) {
					Log.i("Log9", "thumb---2");
					Bitmap bm = getBitmap(GameData.img_bed_board_thumbtack_name);
					bed.currentBedBm = bm;
					if (bed.size_state == Bed.BED_SHORT) {
						Log.i("Log9", "thumb---2-short");
						Bitmap bm2 = BitmapUtils.zoomBitmap(bm,
								bm.getWidth() / 2, bm.getHeight() / 2);
						bed.bedR.bind(bm2);
						bed.bedR.setHalfW(bm2.getWidth() / 2);
						bed.bedR.setHalfH(bm2.getHeight() / 2);
					} else if (bed.size_state == Bed.BED_LONG) {
						Log.i("Log9", "thumb---2-long");
						Bitmap bm2 = BitmapUtils.zoomBitmap(bm,
								(float) (bm.getWidth() * 1.5),
								bm.getHeight() / 2);
						bed.bedR.bind(bm2);
						bed.bedR.setHalfW(bm2.getWidth() / 2);
						bed.bedR.setHalfH(bm2.getHeight() / 2);
					} else {
						Log.i("Log9", "thumb---2-nor");
						bed.bedR.bind(bm);
						bed.bedR.setHalfW(bm.getWidth() / 2);
						bed.bedR.setHalfH(bm.getHeight() / 2);
					}
				} else if (bed.style == Bed.BED_ICE) {
					Bitmap bm = getBitmap(GameData.img_bed_ice_thumbtack_name);
					if (bed.size_state == Bed.BED_SHORT) {
						Bitmap bm2 = BitmapUtils.zoomBitmap(bm,
								bm.getWidth() / 2, bm.getHeight() / 2);
						bed.bedR.bind(bm2);
						bed.bedR.setHalfW(bm2.getWidth() / 2);
						bed.bedR.setHalfH(bm2.getHeight() / 2);
					} else if (bed.size_state == Bed.BED_LONG) {
						Bitmap bm2 = BitmapUtils.zoomBitmap(bm,
								(float) (bm.getWidth() * 1.5),
								bm.getHeight() / 2);
						bed.bedR.bind(bm2);
						bed.bedR.setHalfW(bm2.getWidth() / 2);
						bed.bedR.setHalfH(bm2.getHeight() / 2);
					} else {
						bed.bedR.bind(bm);
						bed.bedR.setHalfW(bm.getWidth() / 2);
						bed.bedR.setHalfH(bm.getHeight() / 2);
					}

				} else if (bed.style == Bed.BED_ROPE) {
					Bitmap bm = getBitmap(GameData.img_bed_rope_thumbtack_name);
					if (bed.size_state == Bed.BED_SHORT) {
						Bitmap bm2 = BitmapUtils.zoomBitmap(bm,
								bm.getWidth() / 2, bm.getHeight() / 2);
						bed.bedR.bind(bm2);
						bed.bedR.setHalfW(bm2.getWidth() / 2);
						bed.bedR.setHalfH(bm2.getHeight() / 2);
					} else if (bed.size_state == Bed.BED_LONG) {
						Bitmap bm2 = BitmapUtils.zoomBitmap(bm,
								(float) (bm.getWidth() * 1.5),
								bm.getHeight() / 2);
						bed.bedR.bind(bm2);
						bed.bedR.setHalfW(bm2.getWidth() / 2);
						bed.bedR.setHalfH(bm2.getHeight() / 2);
					} else {
						bed.bedR.bind(bm);
						bed.bedR.setHalfW(bm.getWidth() / 2);
						bed.bedR.setHalfH(bm.getHeight() / 2);
					}
				}
			}// 石化--木板石化
			else if (body2.m_userData.equals(Prop.STONE_USER_NAME)) {
				// 碰撞音乐+...
				Music2.getInstance(mContext).start(R.raw.getprop, false);

				// 消失道具
				clearPropC(stoneC);
				// stoneC = null;

				bed.isCollideByStone = true;

				if (bed.size_state == Bed.BED_SHORT) {
					bed.currentBedBm = getBitmap(GameData.img_bed_stone_name_s);
					bed.bedR.bind(bed.currentBedBm);
					bed.bedR.setHalfW(bed.currentBedBm.getWidth() / 2);
				} else if (bed.size_state == Bed.BED_LONG) {
					bed.currentBedBm = getBitmap(GameData.img_bed_stone_name_l);

					bed.bedR.bind(bed.currentBedBm);
					bed.bedR.setHalfW(bed.currentBedBm.getWidth() / 2);
				} else {
					bed.currentBedBm = getBitmap(GameData.img_bed_stone_name);
					bed.bedR.bind(bed.currentBedBm);
					bed.bedR.setHalfW(bed.currentBedBm.getWidth() / 2);
				}
				bed_w = bed.currentBedBm.getWidth();
				bed_h = bed.currentBedBm.getHeight();
				stone_propEffectTimer.cancel();
				stone_propEffectTimer.reStart(10 * 1000);
			}

		}
	}

	// private Bitmap currentBitmap;

	class PropEffectTimer extends MyCountDownTimer {

		private int effectId;

		public PropEffectTimer(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);

		}

		public PropEffectTimer(long millisInFuture, long countDownInterval,
				int effectId) {
			super(millisInFuture, countDownInterval);
			this.effectId = effectId;
		}

		@Override
		public void onFinish() {

			if (bed == null) {
				return;
			}

			switch (effectId) {
			// double
			case 0:
				break;
			// stopTime
			case 3:
				bed.size_state = Bed.BED_NORMAL;
				bed.bedR.bind(bed_bitm);
				bed.bedR.setHalfW(bed_bitm.getWidth() / 2);

				break;
			// slow
			case 4:
				bed.size_state = Bed.BED_NORMAL;
				bed.bedR.bind(bed_bitm);
				bed.bedR.setHalfW(bed_bitm.getWidth() / 2);
				break;
			// extend
			case 5:
				bed.size_state = Bed.BED_NORMAL;
				bed.bedR.bind(bed_bitm);
				bed.bedR.setHalfW(bed_bitm.getWidth() / 2);
				break;
			// shorten
			case 6:
				bed.size_state = Bed.BED_NORMAL;
				bed.bedR.bind(bed_bitm);
				bed.bedR.setHalfW(bed_bitm.getWidth() / 2);
				break;
			// stone
			case 8:
				bed.size_state = Bed.BED_NORMAL;
				bed.bedR.bind(bed_bitm);
				bed.bedR.setHalfW(bed_bitm.getWidth() / 2);
				break;
			}
			bed.currentBedBm = bed_bitm;
			bed_w = bed.currentBedBm.getWidth();
			bed_h = bed.currentBedBm.getHeight();
			bed.isCollideByStone = false;
			// getThumbtack = false;
		}

		@Override
		public void onTick(long millisUntilFinished, int percent) {

		}

	}

	private void clearPropC(Circle propC) {
		Bitmap bm = propC.getBitmap();
		bm = BitmapUtils.setAlpha(bm, 0);
		propC.bind(bm);
		world.destroyBody(propC.getBody());
	}

	// TODO
	int i = 0;

	public void countCombo() {
		Log.i("Log5", "countCombo----" + i++);
		Combo combo = new Combo();
		combo.combindex = (floorsNums[1][0] + floorsNums[2][0] + floorsNums[3][0]) / 5;
		combo.score = (floorsNums[1][0] + floorsNums[2][0] * 2 + floorsNums[3][0] * 3)
				* combo.combindex;
		Message msg = handler.obtainMessage();
		msg.what = 0;
		msg.arg1 = combo.score;
		msg.arg2 = isCombBool;
		handler.sendMessage(msg);
		GameData.comboList.add(combo);
		Log.i("Log5", "countCombo----score" + combo.score);
	}

	private void updateCurrentResult() {
		GameData.currentResult.floor1 = GameData.floors[1][0];
		GameData.currentResult.floor2 = GameData.floors[2][0];
		GameData.currentResult.floor3 = GameData.floors[3][0];
		GameData.currentResult.bloodvalue = GameData.player.getLive_value();
		GameData.currentResult.lastplaytime = GameData.playLastTime;
	}

	// 弹跳特效--点击提示
	private Sprite collionSprite, fingerSprite;
	private Bitmap[] soulBms;// 灵魂

	public Sprite bind(Bitmap bitmap, int frameWidth, int frameHeight, int delay) {
		// TODO Auto-generated method stub

		Sprite sprite = new Sprite(bitmap, frameWidth, frameHeight);
		sprite.setDelay(delay);
		sprite.setLoop(false);
		return sprite;
	}

	// 折断挡板
	private void breakBed() {
		if (bed == null) {
			return;
		}
		if (bed.isCollideByStone) {
			Bitmap bm;
			if (bed.size_state == Bed.BED_LONG) {
				bm = getBitmap(GameData.img_bed_stone_break_l);
			} else if (bed.size_state == Bed.BED_NORMAL) {
				bm = getBitmap(GameData.img_bed_stone_break_n);
			} else {
				bm = getBitmap(GameData.img_bed_stone_break_s);
			}
			if (bm == null)
				return;
			bed.bedR.bind(bm, bm.getWidth() / 4, bm.getHeight(), 5);
		}
		if (bed.style == Bed.BED_BOARD) {
			Bitmap bm;
			if (bed.size_state == Bed.BED_LONG) {
				bm = getBitmap(GameData.img_bed_board_break_l);
			} else if (bed.size_state == Bed.BED_NORMAL) {
				bm = getBitmap(GameData.img_bed_board_break_n);
			} else {
				bm = getBitmap(GameData.img_bed_board_break_s);
			}
			if (bm == null)
				return;
			bed.bedR.bind(bm, bm.getWidth() / 4, bm.getHeight(), 5);
		} else if (bed.style == Bed.BED_ICE) {
			Bitmap bm;
			if (bed.size_state == Bed.BED_LONG) {
				bm = getBitmap(GameData.img_bed_ice_break_l);
			} else if (bed.size_state == Bed.BED_NORMAL) {
				bm = getBitmap(GameData.img_bed_ice_break_n);
			} else {
				bm = getBitmap(GameData.img_bed_ice_break_s);
			}
			if (bm == null)
				return;
			bed.bedR.bind(bm, bm.getWidth() / 4, bm.getHeight(), 5);
		} else if (bed.style == Bed.BED_ROPE) {
			Bitmap bm;
			if (bed.size_state == Bed.BED_LONG) {
				bm = getBitmap(GameData.img_bed_rope_break_l);

			} else if (bed.size_state == Bed.BED_NORMAL) {
				bm = getBitmap(GameData.img_bed_rope_break_n);
			} else {
				bm = getBitmap(GameData.img_bed_rope_break_s);
			}
			if (bm == null)
				return;
			bed.bedR.bind(bm, bm.getWidth() / 4, bm.getHeight(), 5);
		} else if (bed.style == Bed.BED_IRON) {
			Bitmap bm;
			if (bed.size_state == Bed.BED_LONG) {
				bm = getBitmap(GameData.img_bed_iron_break_l);

			} else if (bed.size_state == Bed.BED_NORMAL) {
				bm = getBitmap(GameData.img_bed_iron_break_n);
			} else {
				bm = getBitmap(GameData.img_bed_iron_break_s);
			}
			if (bm == null)
				return;
			bed.bedR.bind(bm, bm.getWidth() / 4, bm.getHeight(), 5);
		} else if (bed.style == Bed.BED_RAINBOW) {
			Bitmap bm;
			if (bed.size_state == Bed.BED_LONG) {
				bm = getBitmap(GameData.img_bed_rainbow_break_l);

			} else if (bed.size_state == Bed.BED_NORMAL) {
				bm = getBitmap(GameData.img_bed_rainbow_break_n);
			} else {
				bm = getBitmap(GameData.img_bed_rainbow_break_s);
			}
			if (bm == null)
				return;
			bed.bedR.bind(bm, bm.getWidth() / 4, bm.getHeight(), 5);
		}
		bed.bedR.getSprite().setLoop(false);
	}

	public void clearAllObj() {
		if (list2 == null)
			return;
		if (isGameStart) {
			return;
		}
//		world.setLock(false);
		synchronized (list2) {
			for (Physics p : list2) {
				world.destroyBody(p.getBody());
			}
			list2.clear();
		}

	}

	public Runnable createJumperRunnable = new Runnable() {

		@Override
		public void run() {
			if (isGameOver()) {
				isGameStart = false;
				createHandler.removeCallbacks(this);
				return;
			}
			if (!isGameStart) {
				createHandler.removeCallbacks(this);
				return;
			}
			if (context == null) {
				return;
			}
			if (GameData.currentGameMode == GameData.GAME_MODE_STAGE) {
				if (stage == null) {
					return;
				}
				switch (stage.jumppoint) {
				case Stage.JUMP_POINT6:// 6
					createJumpers(1, 0, GameData.borders[1].y,
							GameData.npc_imgs[rand
									.nextInt(GameData.npc_imgs.length)],
							"jumper");
					break;
				case Stage.JUMP_POINT68:// 6,8
					createJumpers(1, 0, origins68[rand.nextInt(2)],
							GameData.npc_imgs[rand
									.nextInt(GameData.npc_imgs.length)],
							"jumper");
					break;
				case Stage.JUMP_POINT46:// 4,6
					createJumpers(1, 0, origins46[rand.nextInt(2)],
							GameData.npc_imgs[rand
									.nextInt(GameData.npc_imgs.length)],
							"jumper");
					break;
				case Stage.JUMP_POINT468:// 4,6,8
					createJumpers(1, 0, origins[rand.nextInt(3)],
							GameData.npc_imgs[rand
									.nextInt(GameData.npc_imgs.length)],
							"jumper");
					break;

				}
			}// 非闯关模式
			else {
				createJumpers(1, 0, origins[rand.nextInt(3)],
						GameData.npc_imgs[rand
								.nextInt(GameData.npc_imgs.length)], "jumper");
			}
			// 5%概率出现带刀NPC
			if (rand.nextInt(20) == 0) {
				setSleepTime(100);
				knifeJumper = new Jumper();
				knifeJumper.jumperC = createJumpers(1, 0,
						origins[rand.nextInt(3)], GameData.img_npc_knif_name,
						"knife_jumper");
			}
			if (rand.nextInt(10) == 0) {
				setSleepTime(100);
				createJumpers(1, 0, origins[rand.nextInt(3)],
						GameData.img_npc_default_doctor_name, "jumper");
			}
		}
	};

	public Runnable createPropRunnable = new Runnable() {

		@Override
		public void run() {
			// 限时模式
			if (isGameOver() || context == null) {
				createHandler.removeCallbacks(this);
				return;
			}
			if (!isGameStart) {
				createHandler.removeCallbacks(this);
			}
			int index = rand.nextInt(props.length - 1);
			if (index < props.length - 2) {
				Prop p = props[index];
				if (p == null || context == null) {
					return;
				}
				// Bitmap b = BitmapFactory.decodeResource(
				// context.getResources(), p.p_imgName);
				Bitmap b = getBitmap(p.p_imgName);
				if (b == null) {
					return;
				}
				float w = b.getWidth() / 2;
				switch (p.p_efficacy) {
				case 9:
					fireJ = new Jumper();
					fireC = fireJ.jumperC = createProp(getWidth() - 10, w
							+ rand.nextInt(30), w, b, p.p_name,
							new Vec2(-90 - rand.nextInt(10) - rand.nextInt(10),
									-1 - rand.nextInt(5)));
					break;
				case 0:
					doubleC = createProp(getWidth() - 10, w + rand.nextInt(30),
							w, b, p.p_name, new Vec2(-90 - rand.nextInt(10)
									- rand.nextInt(10), -1 - rand.nextInt(5)));
					break;
				case 1:
					goldC = createProp(120, w + 10, w, b, p.p_name, new Vec2(
							70 + rand.nextInt(10), -1));
					break;
				case 4:
					slowC = createProp(getWidth() - 10, w + rand.nextInt(30),
							w, b, p.p_name, new Vec2(-90 - rand.nextInt(10)
									- rand.nextInt(10), -1 - rand.nextInt(5)));
					break;
				case 5:
					extendC = createProp(120, w + 10, w, b, p.p_name, new Vec2(
							70 + rand.nextInt(10), -1));
					break;
				case 6:
					shortenC = createProp(getWidth() - 10,
							w + rand.nextInt(30), w, b, p.p_name, new Vec2(-90
									- rand.nextInt(10) - rand.nextInt(10), -1
									- rand.nextInt(5)));
					break;
				case 7:
					thumbtackC = createProp(getWidth() - 10,
							w + rand.nextInt(30), w, b, p.p_name, new Vec2(-90
									- rand.nextInt(10) - rand.nextInt(10), -1
									- rand.nextInt(5)));
					break;
				case 8:
					stoneC = createProp(getWidth() - 10, w + rand.nextInt(30),
							w, b, p.p_name, new Vec2(-90 - rand.nextInt(10)
									- rand.nextInt(10), -1 - rand.nextInt(5)));
					break;

				}
				if (GameData.currentGameMode == GameData.GAME_MODE_LIMITTIME
						&& index == props.length - 2) {
					stopC = createProp(getWidth() - 120, w, w, b, p.p_name,
							new Vec2(-90 - rand.nextInt(10), 1));
				}// 非限时模式
				else if (GameData.currentGameMode != GameData.GAME_MODE_LIMITTIME
						&& index == props.length - 1) {
					lifeC = createProp(getWidth() - 10, w + rand.nextInt(30),
							w, b, p.p_name, new Vec2(-90 - rand.nextInt(10)
									- rand.nextInt(10), -1 - rand.nextInt(5)));
				}
			}
			if (GameData.currentGameMode == GameData.GAME_MODE_LIMITTIME) {
				if (fireBm == null) {
					fireBm = getBitmap(GameData.img_effect_fire_name);
				}
				if (fireBm == null) {
					return;
				}
				if (GameData.leftTimeOfTimeLimit > 30) {
					if (rand.nextInt(10) < 1) {
						if (fireBm == null) {
							return;
						}
						setSleepTime(500);
						float w = fireBm.getWidth() / 2;
						if (fireJ == null) {
							fireJ = new Jumper();
						}
						fireJ.jumperC = createProp(
								getWidth() - 10,
								w + rand.nextInt(30),
								w,
								fireBm,
								Prop.FIRE_USER_NAME,
								new Vec2(-90 - rand.nextInt(10), -1
										- rand.nextInt(5)));
					}
				} else {
					if (rand.nextInt(100) < 15) {
						if (fireBm == null) {
							return;
						}
						if (fireJ == null) {
							fireJ = new Jumper();
						}
						setSleepTime(500);
						float w = fireBm.getWidth() / 2;
						fireJ.jumperC = createProp(
								getWidth() - 120,
								w,
								w,
								fireBm,
								Prop.FIRE_USER_NAME,
								new Vec2(-90 - rand.nextInt(10), -1
										- rand.nextInt(5)));
					}
				}
			}//
			else if (GameData.currentGameMode == GameData.GAME_MODE_ENDLESS) {
				if (fireBm == null) {
					fireBm = getBitmap(GameData.img_effect_fire_name);
				}
				if (GameData.playLastTime > 180) {
					if (rand.nextInt(100) < 15) {
						if (fireBm == null) {
							return;
						}
						if (fireJ == null) {
							fireJ = new Jumper();
						}
						setSleepTime(500);
						float w = fireBm.getWidth() / 2;
						fireJ.jumperC = createProp(
								getWidth() - 120,
								w + 10,
								w,
								fireBm,
								Prop.FIRE_USER_NAME,
								new Vec2(-90 - rand.nextInt(10), -1
										- rand.nextInt(5)));
					}
				} else if (GameData.playLastTime > 60) {
					if (rand.nextInt(10) < 1) {
						if (fireBm == null) {
							return;
						}
						if (fireJ == null) {
							fireJ = new Jumper();
						}
						setSleepTime(500);
						float w = fireBm.getWidth() / 2;
						fireJ.jumperC = createProp(getWidth() - 120, w + 10, w,
								fireBm, Prop.FIRE_USER_NAME, new Vec2(-90
										- rand.nextInt(10), -3));
					}
				}
			}

		}
	};

	// 启动游戏引擎模拟/NPC/PROP创建
	public void startImitate() {
		isGameStart = true;
		mHandler.post(update2);
		new CreateJumperThread().start();// 启动创建跳跃者线程
		new CreatePropThread().start();// 启动创建道具线程
	}

}
