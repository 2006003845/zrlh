package com.zrong.ui;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.zrong.data.DoctorJumpDB;
import com.zrong.data.GameData;
import com.zrong.engine.BoxScreen;
import com.zrong.engine.GameView;
import com.zrong.entity.Goods;
import com.zrong.entity.Music;
import com.zrong.entity.Player;
import com.zrong.entity.Stage;
import com.zrong.ui.base.BaseActivity;
import com.zrong.utils.DataChangeListener;
import com.zrong.utils.MyCountDownTimer;
import com.zrong.view.BloodView;
import com.zrong.view.NumView;
import com.zrong.view.SkillButtonView;
import com.zrong.view.TimeView;

public class MainActivity extends BaseActivity implements DataChangeListener {

	private BloodView bloodV;
	private GameView gameView;
	private BoxScreen boxScreen;
	private TimeView timerV;
	private NumView scoreV;

	private ImageView pauseBtn;

	private ViewFlipper mViewFlipper;

	private Context mContext;
	private DoctorJumpTimer timer;
	// private AudioManager audioManager;
	// private int currentVoiceValue;

	private SharedPreferences prefs;
	private Stage stage;

	private DoctorJumpDB doctorDB;

	public static MainActivity mIntance;
	private ImageView teachV;
	private SkillButtonView skillBtn;

	@Override
	public void init(Context context) {
		mIntance = this;
		Random rand = new Random();
		if (rand.nextInt(2) == 0) {
			Music.getInstance(this).start(R.raw.playing_01, true);
		} else {
			Music.getInstance(this).start(R.raw.playing_02, true);
		}
		// 初始化数据库
		// doctorDB = DoctorJumpDB.newInstanceOfDB(MainActivity.this);
		doctorDB = new DoctorJumpDB(this);

		skill_goodss = getGoodss(Goods.G_TYPE + "=?",
				new String[] { Goods.GOODS_TYPE_SKILL + "" });

		prefs = PreferenceManager.getDefaultSharedPreferences(this);
		GameData.comboList.clear();// 对comboList 进行清空
		getGamePlayMode();

		if (GameData.currentGameMode == GameData.GAME_MODE_STAGE) {
			// 获得当前关卡对象
			int id;
			if (getIntent().getExtras() != null
					&& (id = getIntent().getExtras()
							.getInt(GameData.NEXTSTSSGE)) > 0) {
				int current_stage_id = id;
				Cursor cursor = doctorDB.query(Stage.TAB_NAME, null, "_id=?",
						new String[] { "" + current_stage_id }, null, null,
						null);
				cursor.moveToFirst();
				stage = Stage.getStage(cursor);

				cursor.close();
				doctorDB.closeDB();

				GameData.currentStage = stage;
			} else {
				stage = GameData.currentStage;
			}

		}

		Log.i("Log", "start");
		setLandscape(true);// 设置横屏
		setFullScreen(true);// 设置全屏
		setFilter(true);// 设置图片无锯齿
		createEngine();// 创建引擎
		setContentView(R.layout.main);
		gameView = (GameView) this.findViewById(R.id.gameview);
		setView(gameView);
		boxScreen = new BoxScreen(MainActivity.this, stage);
		setScreen(boxScreen);// 设置显示屏幕
		showFPS(true, 0xffffff);// 设置显示FPS
		mContext = this;
		initView();
		// audioManager = (AudioManager)
		// getSystemService(Context.AUDIO_SERVICE);
		// currentVoiceValue = audioManager
		// .getStreamVolume(AudioManager.STREAM_MUSIC);

	}

	// 对View组件初始化
	private void initView() {
		teachV = (ImageView) this.findViewById(R.id.mian_teach);
		Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
		teachV.startAnimation(shake);
		shake.setFillEnabled(true);
		teachV.startAnimation(shake);
		mViewFlipper = (ViewFlipper) this.findViewById(R.id.ViewFlipper);
		if (GameData.currentGameMode == GameData.GAME_MODE_STAGE) {
			mViewFlipper.setDisplayedChild(0);
			skillBtn = (SkillButtonView) this
					.findViewById(R.id.btn_stage_skill);
//			if (getSkillBtn(GameData.skillbifrost_ID).isEquipment == Goods.HAVEEQUIP) {
//				skillBtn.setSkillID(SkillButtonView.SKILL_RAINBOW);
//				skillBtn.setImgId(R.drawable.skill_bifrost);
//			} else {
//				skillBtn.setSkillID(SkillButtonView.SKILL_BLOOD);
//				skillBtn.setImgId(R.drawable.skill_life);
//			}
			bloodV = (BloodView) this
					.findViewById(R.id.main_top_stage_bloodview);
			bloodV.setImageId(R.drawable.img_heart, R.drawable.img_badheart);
			bloodV.setCount(3);
			// bloodV.setKeepScreenOn(true);
			reSetViewOfHeart();
			timerV = (TimeView) this.findViewById(R.id.main_top_stage_timeview);
			timerV.setImgId(R.drawable.img_timenum);
			// timerV.setKeepScreenOn(true);
			timerV.setTime(stage.gameTime);
			scoreV = (NumView) this.findViewById(R.id.main_top_stage_scoreview);
			scoreV.setImgId(R.drawable.img_scorenum);
			scoreV.setFrameCount(10);
			scoreV.setNum("0");
			// scoreV.setKeepScreenOn(true);
			pauseBtn = (ImageView) this
					.findViewById(R.id.main_top_stage_pausebtn);
		} else if (GameData.currentGameMode == GameData.GAME_MODE_LIMITTIME) {
			mViewFlipper.setDisplayedChild(1);
			skillBtn = (SkillButtonView) this
					.findViewById(R.id.btn_limit_skill);
//			if (getSkillBtn(GameData.skillbifrost_ID).isEquipment == Goods.HAVEEQUIP) {
//				skillBtn.setSkillID(SkillButtonView.SKILL_RAINBOW);
//				skillBtn.setImgId(R.drawable.skill_bifrost);
//			} else if (getSkillBtn(32).isEquipment == Goods.HAVEEQUIP) {
//				skillBtn.setSkillID(SkillButtonView.SKILL_STOP);
//				skillBtn.setImgId(R.drawable.skill_clock);
//			} else {
//				skillBtn.setVisibility(View.GONE);
//			}
			timerV = (TimeView) this.findViewById(R.id.main_top_limit_timeview);
			timerV.setImgId(R.drawable.img_timenum);
			// timerV.setKeepScreenOn(true);
			timerV.setTime(30);
			scoreV = (NumView) this.findViewById(R.id.main_top_limit_scoreview);
			scoreV.setImgId(R.drawable.img_scorenum);
			scoreV.setFrameCount(10);
			scoreV.setNum("0");
			// scoreV.setKeepScreenOn(true);
			pauseBtn = (ImageView) this
					.findViewById(R.id.main_top_limit_pausebtn);
		} else if (GameData.currentGameMode == GameData.GAME_MODE_ENDLESS) {
			mViewFlipper.setDisplayedChild(2);
			skillBtn = (SkillButtonView) this
					.findViewById(R.id.btn_endless_skill);
//			if (getSkillBtn(GameData.skillbifrost_ID).isEquipment == Goods.HAVEEQUIP) {
//				skillBtn.setSkillID(SkillButtonView.SKILL_RAINBOW);
//				skillBtn.setImgId(R.drawable.skill_bifrost);
//			} else if (getSkillBtn(31).isEquipment == Goods.HAVEEQUIP) {
//				skillBtn.setSkillID(SkillButtonView.SKILL_BLOOD);
//				skillBtn.setImgId(R.drawable.skill_life);
//			} else {
//				skillBtn.setVisibility(View.GONE);
//			}
			bloodV = (BloodView) this
					.findViewById(R.id.main_top_endless_bloodview);
			bloodV.setImageId(R.drawable.img_heart, R.drawable.img_badheart);
			bloodV.setCount(6);
			// bloodV.setKeepScreenOn(true);
			reSetViewOfHeart();
			timerV = (TimeView) this
					.findViewById(R.id.main_top_endless_timeview);
			timerV.setImgId(R.drawable.img_timenum);
			timerV.setTime(0);
			timerV.invalidate();
			pauseBtn = (ImageView) this
					.findViewById(R.id.main_top_endless_pausebtn);
		}
//		teachV = (ImageView) this.findViewById(R.id.mian_teach);

		pauseBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO 游戏操作菜单
				showMenuDialog();
			}
		});

		// if (GameData.currentGameMode == GameData.GAME_MODE_LIMITTIME) {
		// skill02Btn.setImgId(R.drawable.clockstop);
		// } else {
		// skill02Btn.setImgId(R.drawable.fullblood);
		// }
		skillBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO 技能2
				skillBtn.onClick(v);
				if (skillBtn.isCoolDowning) {
					return;
				}
				// 闯关
				if (GameData.currentGameMode == GameData.GAME_MODE_STAGE) {

					reSetViewOfHeart();
				}// 无尽
				else if (GameData.currentGameMode == GameData.GAME_MODE_ENDLESS) {

					reSetViewOfHeart();
				}
			}
		});

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_MENU
				|| keyCode == KeyEvent.KEYCODE_BACK) {
			showMenuDialog();
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}

	class DoctorJumpTimer extends MyCountDownTimer {

		public DoctorJumpTimer(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onTick(long millisUntilFinished, int percent) {
			timerV.setTime(millisUntilFinished / 1000);
			timerV.invalidate();
			if (millisUntilFinished == 0) {
				GameData.leftTimeOfTimeLimit = 0;
			}
			// GameOver
			if (boxScreen.isGameOver()) {
				Log.i("Log14", "onTick---");
				if (GameData.currentGameMode == GameData.GAME_MODE_LIMITTIME) {
					boxScreen.countCombo();
				}
				this.pause();
				this.cancel();
				this.onFinish();
			}
		}

		@Override
		public void onFinish() {
			Log.i("Log14", "onFinish---");
			// GameOver
			gameOverShow();
			this.cancel();
		}
	}

	private boolean isPause = false;

	private void showMenuDialog() {
		isPause = true;
		boxScreen.setPausePhysics(true);
		if (GameData.currentGameMode != GameData.GAME_MODE_ENDLESS) {
			if (timer != null) {
				timer.pause();
			}
		}
		LayoutInflater inflater = getLayoutInflater();
		View layout = inflater.inflate(R.layout.play_menu, null);
		final AlertDialog dialog = new AlertDialog.Builder(this).create();
		dialog.show();
		dialog.getWindow().setContentView(layout);
		dialog.findViewById(R.id.play_menu_btn_continue).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						isPause = false;
						boxScreen.resetPausePhysic();
						if (timer != null) {
							timer.resume();
						}
						dialog.cancel();

					}
				});
		dialog.findViewById(R.id.play_menu_btn_replay).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						isPause = false;
						if (timer != null) {
							timer.cancel();
						}
						dialog.cancel();
						activityStart(new Intent(mContext,
								TransitionActivity.class));

						int version = Integer
								.valueOf(android.os.Build.VERSION.SDK);
						if (version >= 5) {
							overridePendingTransition(R.anim.zoomin,
									R.anim.zoomout);
						}
					}
				});
		// 返回主菜单
		dialog.findViewById(R.id.play_menu_btn_back).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						// activityStart(new Intent(mContext,
						// HomeActivity.class));
						quit();
						isPause = false;
						dialog.cancel();
						int version = Integer
								.valueOf(android.os.Build.VERSION.SDK);
						if (version >= 5) {
							overridePendingTransition(R.anim.zoomin,
									R.anim.zoomout);
						}
					}
				});
		// 音效
		// 音乐
		// final ImageView musicBtn = (ImageView) dialog
		// .findViewById(R.id.play_menu_btn_music);
		// musicBtn.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// if (GameData.isVolumeOpen) {
		// musicBtn.setImageResource(R.drawable.tv_music_forbid_selector);
		// audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0,
		// 0);
		// GameData.isVolumeOpen = false;
		// } else {
		// musicBtn.setImageResource(R.drawable.tv_music_selector);
		// audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
		// currentVoiceValue, 0);
		// GameData.isVolumeOpen = true;
		// }
		// boxScreen.resetPausePhysic();
		// }
		// });
	}

	@Override
	protected void onRestart() {
		super.onRestart();
	}

	private void activityStart(Intent intent) {
		if (intent == null) {
			return;
		}
		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		this.startActivity(intent);
		quit();
	}

	@Override
	protected void onStop() {
		doctorDB.closeDB();
		doctorDB.close();
		// boxScreen.
		// exit();
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		if (mIntance != null) {
			mIntance = null;
		}
		super.onDestroy();
	}

	private void getGamePlayMode() {
		// int PlayMode = PreferenceManager.getDefaultSharedPreferences(
		// MainActivity.this).getInt(HomeActivity.Curr_PLAYMODE, 0);
		if (GameData.currentGameMode == GameData.GAME_MODE_STAGE) {
			// GameData.currentGameMode = GameData.GAME_MODE_STAGE;
			if (GameData.player == null) {
				GameData.player = Player.newInstanceOfPlayer();
				GameData.player.setWealth(PreferenceManager
						.getDefaultSharedPreferences(MainActivity.this).getInt(
								HomeActivity.PLAYER_WEALTH, 0));
			}
			GameData.player.setLive_value(3);
		}
		if (GameData.currentGameMode == GameData.GAME_MODE_ENDLESS) {
			// GameData.currentGameMode = GameData.GAME_MODE_ENDLESS;
			if (GameData.player == null) {
				GameData.player = Player.newInstanceOfPlayer();
				GameData.player.setWealth(PreferenceManager
						.getDefaultSharedPreferences(MainActivity.this).getInt(
								HomeActivity.PLAYER_WEALTH, 0));
			}
			GameData.player.setLive_value(6);
		}
		if (GameData.currentGameMode == GameData.GAME_MODE_LIMITTIME) {
			// GameData.currentGameMode = GameData.GAME_MODE_LIMITTIME;
			if (GameData.player == null) {
				GameData.player = Player.newInstanceOfPlayer();
				GameData.player.setWealth(PreferenceManager
						.getDefaultSharedPreferences(MainActivity.this).getInt(
								HomeActivity.PLAYER_WEALTH, 0));
			}
		}
	}

	private boolean isStartGame = false;
	private boolean isArouse = false;

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (!isArouse) {
			boxScreen.startImitate();
			isArouse = true;
		}
		// 启动定时器---
		startTimer();
		teachV.clearAnimation();
		teachV.setVisibility(View.GONE);
		return super.onTouchEvent(event);
	}

	private Timer endless_timer;

	/**
	 * 启动计时器
	 */
	private void startTimer() {
		if (!isStartGame) {
			isStartGame = true;
			if (GameData.currentGameMode == GameData.GAME_MODE_LIMITTIME) {
				if (timer == null)
					timer = new DoctorJumpTimer(60000, 1000);
				timer.start();
			} else if (GameData.currentGameMode == GameData.GAME_MODE_STAGE) {
				Log.i("Log3", "Stage_GameTime" + stage.gameTime);
				if (timer == null)
					timer = new DoctorJumpTimer(stage.gameTime * 1000, 1000);
				timer.start();
			}

			if (GameData.currentGameMode == GameData.GAME_MODE_ENDLESS) {
				GameData.playLastTime = 0;
				// 启动计时器
				endless_timer = new Timer();
				endless_timer.schedule(new TimerTask() {

					@Override
					public void run() {
						if (boxScreen.isGameOver()) {
							this.cancel();
							gameOverShow();
						}
						if (!isPause) {
							GameData.playLastTime++;
							GameData.currentResult.lastplaytime = GameData.playLastTime;
							Message msg = handle2.obtainMessage();
							handle2.sendMessage(msg);
						}

					}
				}, 1000, 1000);
			}
		}
	}

	private Handler handle2 = new Handler() {
		public void handleMessage(Message msg) {
			timerV.setTime(GameData.playLastTime);
			timerV.invalidate();
		};
	};

	private void quit() {
		if (timer != null) {
			timer.cancel();
			timer = null;
		}
		GameView.setIsStart(false);
		gameView.stop();
		this.finish();
	}

	public int currentScore = 0;
	public int tempScore = 0;

	@Override
	public void scoreChangeListener(int score, boolean isCombo) {
		Log.i("Log10", "scoreChangeListener" + score + "---" + isCombo);
		if (GameData.currentGameMode == GameData.GAME_MODE_LIMITTIME) {
			if (isCombo) {
				currentScore = currentScore + score;
				scoreV.setNum(currentScore + "");
				tempScore = currentScore;
			} else {
				tempScore = tempScore + score;
				scoreV.setNum(tempScore + "");
			}
			scoreV.postInvalidate();
		}
		if (GameData.currentGameMode == GameData.GAME_MODE_STAGE) {
			currentScore = currentScore + score;
			scoreV.setNum(currentScore + "");
			scoreV.postInvalidate();
		}

	}

	@Override
	public void lifeChangeListener(Bundle b) {
		Log.i("Log10", "lifeChangeListener");
		reSetViewOfHeart();
		if (boxScreen.isGameOver()) {
			gameOverShow();
		}
	}

	public void reSetViewOfHeart() {
		bloodV.setBloodNum(GameData.player.getLive_value());
		bloodV.postInvalidate();
	}

	// 清除楼层数据
	private void clearData() {
		for (int i = 0, len = GameData.floors.length; i < len; i++) {
			for (int j = 0, l = GameData.floors[0].length; j < l; j++) {
				GameData.floors[i][j] = 0;
			}
		}
	}

	private void gameOverShow() {
		clearData();
		if (timer != null) {
			timer.pause();
			timer.cancel();
		}
		// 过关失败--显示结果窗口
		if (GameData.currentGameMode == GameData.GAME_MODE_STAGE) {
			Bundle b = new Bundle();
			b.putInt(PlayResultActivity.RESULT_KEY,
					PlayResultActivity.STAGERESULT);
			Intent intent = new Intent(MainActivity.this,
					PlayResultActivity.class);
			intent.putExtras(b);
			activityStart(intent);
			MainActivity.this.finish();
		}// 无尽模式
		else if (GameData.currentGameMode == GameData.GAME_MODE_ENDLESS) {
			Bundle b = new Bundle();
			b.putInt(PlayResultActivity.RESULT_KEY,
					PlayResultActivity.ENDLESSRESULT);
			Intent intent = new Intent(MainActivity.this,
					PlayResultActivity.class);
			intent.putExtras(b);
			activityStart(intent);
			MainActivity.this.finish();
		}
		// 限时模式
		else if (GameData.currentGameMode == GameData.GAME_MODE_LIMITTIME) {

			boxScreen.countCombo();// 获取最后一个Combo

			Bundle b = new Bundle();
			b.putInt(PlayResultActivity.RESULT_KEY,
					PlayResultActivity.LIMTEDRESULT);
			Intent intent = new Intent(MainActivity.this,
					PlayResultActivity.class);
			intent.putExtras(b);
			activityStart(intent);
			MainActivity.this.finish();
		}
		boxScreen.bed = null;
	}

	@Override
	public void stopTimeListener(Bundle b) {

	}

	private ArrayList<Goods> skill_goodss;

	private ArrayList<Goods> getGoodss(String where, String[] whereArgs) {
		ArrayList<Goods> list = Goods.getGoodsList(doctorDB.query(
				Goods.TAB_NAME, null, where, whereArgs, null, null, null));
		doctorDB.closeDB();
		return list;
	}

	private Goods getSkillBtn(int id) {
		for (Goods g : skill_goodss) {
			if (g.g_id == id) {
				return g;
			}
		}
		return null;
	}

}