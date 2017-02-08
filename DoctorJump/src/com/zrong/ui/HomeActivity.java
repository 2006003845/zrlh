package com.zrong.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.zrong.data.DoctorJumpDB;
import com.zrong.data.GameData;
import com.zrong.engine.DynamicBG;
import com.zrong.entity.Achievement;
import com.zrong.entity.Goods;
import com.zrong.entity.Music;
import com.zrong.entity.Player;
import com.zrong.entity.Prop;
import com.zrong.entity.Stage;
import com.zrong.ui.base.Base2Activity;
import com.zrong.view.DrawDynamicView;
import com.zrong.view.NumView;

public class HomeActivity extends Base2Activity {
	public final static String FIRST_PLAY_PREFERENCE = "first_play2";
	// public final static String Curr_PLAYMODE = "current_play_mode";
	public final static String PLAYER_WEALTH = "player_wealth";
	private NumView wealthV;
	private ImageView musicBtn, stageBtn, limitBtn, endlessBtn;
	private SharedPreferences prefs;
	private DoctorJumpDB doctorDB;

	public static void launch(Context context) {
		Intent intent = new Intent(context, HomeActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		context.startActivity(intent);
	}

	@Override
	public void init() {
		super.init();
		// 实例化一个数据库
		// doctorDB = DoctorJumpDB.newInstanceOfDB(this);
		doctorDB = new DoctorJumpDB(this);
		initData();
		initView();
		Music.getInstance(mContext).start(R.raw.other, true);
	}

	// 初始化玩家信息
	private void initData() {
		// 判断是否为第一次进入游戏
		boolean isFirstPlay = PreferenceManager.getDefaultSharedPreferences(
				this).getBoolean(FIRST_PLAY_PREFERENCE, true);
		prefs = PreferenceManager.getDefaultSharedPreferences(this);
		if (isFirstPlay) {
			// 第一次进入游戏时加载游戏数据
			prefs.edit().putBoolean(FIRST_PLAY_PREFERENCE, false).commit();
			prefs.edit().putInt(PLAYER_WEALTH, 0).commit();
			initDBData();
		}
		// initDBData();
		GameData.player = Player.newInstanceOfPlayer();// 创建玩家
		GameData.player.setWealth(PreferenceManager
				.getDefaultSharedPreferences(this).getInt(PLAYER_WEALTH, 0));

	}

	private void initView() {
		setContentView(R.layout.home);

		int w = getWindowManager().getDefaultDisplay().getWidth();
		int h = getWindowManager().getDefaultDisplay().getHeight();
		DynamicBG dbg = DynamicBG.createDynamicBG(this, DynamicBG.SNOW, w, h);
		DrawDynamicView ddv = (DrawDynamicView) this
				.findViewById(R.id.home_draw);
		ddv.setDynamicBG(dbg);

		stageBtn = (ImageView) this.findViewById(R.id.home_btn_stagemode);
		stageBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				stageBtn.setBackgroundResource(R.drawable.bg_mode_pressed);
				stageBtn.setImageResource(R.drawable.tv_stage_pressed);
				GameData.currentGameMode = GameData.GAME_MODE_STAGE;
				activityStart(new Intent(mContext, StageActivity.class));
			}
		});

		limitBtn = (ImageView) this.findViewById(R.id.home_btn_timemode);
		limitBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				limitBtn.setBackgroundResource(R.drawable.bg_mode_pressed);
				limitBtn.setImageResource(R.drawable.tv_timed_pressed);
				GameData.currentGameMode = GameData.GAME_MODE_LIMITTIME;
				activityStart(new Intent(mContext, MainActivity.class));
			}
		});

		endlessBtn = (ImageView) this.findViewById(R.id.home_btn_endlessmode);
		endlessBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				endlessBtn.setBackgroundResource(R.drawable.bg_mode_pressed);
				endlessBtn.setImageResource(R.drawable.tv_endless_pressed);
				GameData.currentGameMode = GameData.GAME_MODE_ENDLESS;
				activityStart(new Intent(mContext, MainActivity.class));
			}
		});

		wealthV = (NumView) this.findViewById(R.id.home_wealthview);
		wealthV.setImgId(R.drawable.img_goldnum);
		wealthV.setFrameCount(10);

		this.findViewById(R.id.home_btn_help).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						activityStart(new Intent(mContext, HelpActivity.class));
					}
				});
		this.findViewById(R.id.home_btn_about).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						activityStart(new Intent(mContext,
								AboutUSActivity.class));
					}
				});

		musicBtn = (ImageView) this.findViewById(R.id.home_btn_music);
		musicBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (GameData.isMusicOpen) {
					musicBtn.setImageResource(R.drawable.tv_music_forbid_selector);
					GameData.isMusicOpen = false;
					Music.getInstance(mContext).pause();
				} else {
					musicBtn.setImageResource(R.drawable.tv_music_selector);
					GameData.isMusicOpen = true;
					Music.getInstance(mContext).resume();
				}
			}
		});

		this.findViewById(R.id.home_btn_achievement).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						activityStart(new Intent(HomeActivity.this,
								AchievementsActivity.class));

					}
				});

		this.findViewById(R.id.home_btn_store).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						activityStart(new Intent(mContext, StoreActivity.class));
					}
				});
	}

	private void activityStart(Intent intent) {
		if (intent == null) {
			return;
		}
		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		this.startActivity(intent);
		activitySwitch();
	}

	int version = Integer.valueOf(android.os.Build.VERSION.SDK);

	// 界面切换效果
	private void activitySwitch() {

		if (version >= 5) {
			overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
		}
	}

	private void showDiag() {
		LayoutInflater inflater = getLayoutInflater();
		View layout = inflater.inflate(R.layout.mydiag, null);
		final AlertDialog dialog = new AlertDialog.Builder(this).create();
		dialog.show();
		dialog.getWindow().setContentView(layout);

		dialog.findViewById(R.id.diag_positive_btn).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						// Toast.makeText(mContext, "exit---", 1000).show();
						HomeActivity.this.finish();
						doctorDB.close();
						dialog.cancel();
					}
				});
		dialog.findViewById(R.id.diag_negative_btn).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						dialog.cancel();
					}
				});
	}

	@Override
	protected void onResume() {
		stageBtn.setBackgroundResource(R.drawable.bg_mode_selector);
		stageBtn.setImageResource(R.drawable.tv_stage_selector);
		limitBtn.setBackgroundResource(R.drawable.bg_mode_selector);
		limitBtn.setImageResource(R.drawable.tv_timed_selector);
		endlessBtn.setBackgroundResource(R.drawable.bg_mode_selector);
		endlessBtn.setImageResource(R.drawable.tv_endless_selector);
		wealthV.setImgId(R.drawable.img_goldnum);
		wealthV.setNum("" + prefs.getInt(PLAYER_WEALTH, 0));
		if (doctorDB == null) {
			// doctorDB = DoctorJumpDB.newInstanceOfDB(this);
			doctorDB = new DoctorJumpDB(this);
		}
		if (GameData.player == null) {
			GameData.player = Player.newInstanceOfPlayer();// 创建玩家
			GameData.player
					.setWealth(PreferenceManager.getDefaultSharedPreferences(
							this).getInt(PLAYER_WEALTH, 0));
		}
		Music.getInstance(mContext).start(R.raw.other, true);
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		exit2();
		super.onDestroy();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			showDiag();
			return false;
		}

		return super.onKeyDown(keyCode, event);
	}

	private void initDBData() {
		// 12关卡
		doctorDB.insertStage(new Stage(1, Stage.JUMP_POINT6,
				Stage.APPEAR_NPC_STU, GameData.img_sence_school_name,
				Stage.SPACING_TIME_2_3, 40, Stage.USEBOTHSKILL, Stage.UNPASSED,
				0, R.drawable.scene_school));
		doctorDB.insertStage(new Stage(2, Stage.JUMP_POINT46,
				Stage.APPEAR_NPC_STU_OUTM, GameData.img_sence_snow_name,
				Stage.SPACING_TIME_2_3, 40, Stage.USELIVESKILL, Stage.UNPASSED,
				0, R.drawable.scene_snow));
		doctorDB.insertStage(new Stage(3, Stage.JUMP_POINT68,
				Stage.APPEAR_NPC_STU_WHITE, GameData.img_sence_city_name,
				Stage.SPACING_TIME_2_25, 50, Stage.USELIVESKILL,
				Stage.UNPASSED, 0, R.drawable.scene_city));
		doctorDB.insertStage(new Stage(4, Stage.JUMP_POINT68,
				Stage.APPEAR_NPC_ANDR_NINJA, GameData.img_sence_valley_name,
				Stage.SPACING_TIME_2_25, 50, Stage.USEBOTHSKILL,
				Stage.UNPASSED, 0, R.drawable.scene_valley));
		doctorDB.insertStage(new Stage(5, Stage.JUMP_POINT68,
				Stage.APPEAR_NPC_DOCTOR_STU, GameData.img_sence_school_name,
				Stage.SPACING_TIME_15_25, 60, Stage.USELIVESKILL,
				Stage.UNPASSED, 0, R.drawable.scene_school));
		doctorDB.insertStage(new Stage(6, Stage.JUMP_POINT68,
				Stage.APPEAR_NPC_DOCTOR_OUTM, GameData.img_sence_snow_name,
				Stage.SPACING_TIME_15_25, 60, Stage.USELIVESKILL,
				Stage.UNPASSED, 0, R.drawable.scene_snow));
		doctorDB.insertStage(new Stage(7, Stage.JUMP_POINT68,
				Stage.APPEAR_NPC_DOCTOR_STU_WHITE,
				GameData.img_sence_city_name, Stage.SPACING_TIME_15_2, 80,
				Stage.USELIVESKILL, Stage.UNPASSED, 0, R.drawable.scene_city));
		doctorDB.insertStage(new Stage(8, Stage.JUMP_POINT68,
				Stage.APPEAR_NPC_DOCTOR_ANDR_NINJA,
				GameData.img_sence_valley_name, Stage.SPACING_TIME_15_2, 80,
				Stage.USEBOTHSKILL, Stage.UNPASSED, 0, R.drawable.scene_valley));
		doctorDB.insertStage(new Stage(9, Stage.JUMP_POINT68,
				Stage.APPEAR_NPC_STU_BLACK, GameData.img_sence_school_name,
				Stage.SPACING_TIME_1_2, 100, Stage.USEBRIFROSTSKILL,
				Stage.UNPASSED, 0, R.drawable.scene_school));
		doctorDB.insertStage(new Stage(10, Stage.JUMP_POINT68,
				Stage.APPEAR_NPC_NINJA_BLACK, GameData.img_sence_snow_name,
				Stage.SPACING_TIME_1_2, 100, Stage.USEBOTHSKILL,
				Stage.UNPASSED, 0, R.drawable.scene_snow));
		doctorDB.insertStage(new Stage(11, Stage.JUMP_POINT68,
				Stage.APPEAR_NPC_ALL, GameData.img_sence_city_name,
				Stage.SPACING_TIME_1_2, 120, Stage.USEBOTHSKILL,
				Stage.UNPASSED, 0, R.drawable.scene_city));
		doctorDB.insertStage(new Stage(12, Stage.JUMP_POINT68,
				Stage.APPEAR_NPC_ALL, GameData.img_sence_valley_name,
				Stage.SPACING_TIME_1_2, 120, Stage.USEBOTHSKILL,
				Stage.UNPASSED, 0, R.drawable.scene_valley));

		// 商城
		doctorDB.insertGoods(new Goods(1, Goods.GOODS_TYPE_JUMPER, "大学生",
				GameData.img_g_stu_name, 500, GameData.img_npc_stu_name,
				R.string.detail_stu, Goods.OWNING, Goods.HAVEEQUIP));
		doctorDB.insertGoods(new Goods(2, Goods.GOODS_TYPE_JUMPER, "白领",
				GameData.img_g_white_name, 500, GameData.img_npc_white_name,
				R.string.detail_white, Goods.UNOWNING, Goods.UNEQUIP));
		doctorDB.insertGoods(new Goods(3, Goods.GOODS_TYPE_JUMPER, "奥特曼",
				GameData.img_g_outm_name, 600, GameData.img_npc_outm_name,
				R.string.detail_outm, Goods.UNOWNING, Goods.UNEQUIP));
		doctorDB.insertGoods(new Goods(4, Goods.GOODS_TYPE_JUMPER, "安卓",
				GameData.img_g_andr_name, 800, GameData.img_npc_andr_name,
				R.string.detail_andr, Goods.UNOWNING, Goods.UNEQUIP));
		doctorDB.insertGoods(new Goods(5, Goods.GOODS_TYPE_JUMPER, "忍者",
				GameData.img_g_ninga_name, 1200, GameData.img_npc_ninga_name,
				R.string.detail_ninja, Goods.UNOWNING, Goods.UNEQUIP));
		doctorDB.insertGoods(new Goods(6, Goods.GOODS_TYPE_JUMPER, "豪华小睿",
				GameData.img_g_luxury_doctor_name, 1500,
				GameData.img_npc_luxury_doctor_name, R.string.detail_lux_doct,
				Goods.UNOWNING, Goods.UNEQUIP));
		doctorDB.insertGoods(new Goods(GameData.blackJumper_ID,
				Goods.GOODS_TYPE_JUMPER, "黑衣人", GameData.img_g_black_name, -1,
				GameData.img_npc_black_name, R.string.detail_black,
				Goods.UNOWNING, Goods.UNEQUIP));

		doctorDB.insertGoods(new Goods(GameData.sceneSnow_ID,
				Goods.GOODS_TYPE_SENCE, "雪地", GameData.img_sence_snow_name,
				400, GameData.img_sence_snow_name, R.string.detail_snow,
				Goods.UNOWNING, Goods.UNEQUIP));
		doctorDB.insertGoods(new Goods(GameData.sceneCity_ID,
				Goods.GOODS_TYPE_SENCE, "都市", GameData.img_sence_city_name,
				600, GameData.img_sence_city_name, R.string.detail_city,
				Goods.UNOWNING, Goods.UNEQUIP));
		doctorDB.insertGoods(new Goods(GameData.sceneValley_ID,
				Goods.GOODS_TYPE_SENCE, "山谷", GameData.img_sence_valley_name,
				-1, GameData.img_sence_valley_name, R.string.detail_valley,
				Goods.UNOWNING, Goods.UNEQUIP));

		doctorDB.insertGoods(new Goods(21, Goods.GOODS_TYPE_EFFECT, "火焰",
				GameData.img_g_fire_name, 800, GameData.img_effect_fire_name,
				R.string.detail_fire, Goods.UNOWNING, Goods.UNEQUIP));
		doctorDB.insertGoods(new Goods(22, Goods.GOODS_TYPE_EFFECT, "双倍",
				GameData.img_g_double_name, 500,
				GameData.img_effect_double_name, R.string.detail_double,
				Goods.UNOWNING, Goods.UNEQUIP));
		doctorDB.insertGoods(new Goods(23, Goods.GOODS_TYPE_EFFECT, "金币",
				GameData.img_g_gold_name, 500, GameData.img_effect_gold_name,
				R.string.detail_gold, Goods.UNOWNING, Goods.UNEQUIP));
		doctorDB.insertGoods(new Goods(24, Goods.GOODS_TYPE_EFFECT, "生命",
				GameData.img_g_life_name, 500, GameData.img_effect_life_name,
				R.string.detail_life, Goods.UNOWNING, Goods.UNEQUIP));
		// doctorDB.insertGoods(new Goods(25, Goods.GOODS_TYPE_EFFECT, "减速",
		// R.drawable.prop_slow, 500, GameData.img_effect_slowdown_name,
		// R.string.detail_slow, Goods.UNOWNING, Goods.UNEQUIP));
		doctorDB.insertGoods(new Goods(26, Goods.GOODS_TYPE_EFFECT, "伸长",
				GameData.img_g_extend_name, 500,
				GameData.img_effect_extend_name, R.string.detail_extend,
				Goods.UNOWNING, Goods.UNEQUIP));
		doctorDB.insertGoods(new Goods(27, Goods.GOODS_TYPE_EFFECT, "缩短",
				GameData.img_g_shorten_name, 500,
				GameData.img_effect_shorten_name, R.string.detail_shorten,
				Goods.UNOWNING, Goods.UNEQUIP));
		doctorDB.insertGoods(new Goods(28, Goods.GOODS_TYPE_EFFECT, "石化",
				GameData.img_g_stone_name, 500, GameData.img_effect_stone_name,
				R.string.detail_stone, Goods.UNOWNING, Goods.UNEQUIP));
		doctorDB.insertGoods(new Goods(29, Goods.GOODS_TYPE_EFFECT, "图钉",
				GameData.img_g_thumbtack_name, 800,
				GameData.img_effect_thumbtack_name, R.string.detail_thumbtack,
				Goods.UNOWNING, Goods.UNEQUIP));

		doctorDB.insertGoods(new Goods(31, Goods.GOODS_TYPE_BED, "冰床",
				GameData.img_bed_ice_name, 500, GameData.img_bed_ice_name,
				R.string.detail_ice, Goods.UNOWNING, Goods.UNEQUIP));
		doctorDB.insertGoods(new Goods(32, Goods.GOODS_TYPE_BED, "铁床",
				GameData.img_bed_iron_name, 600, GameData.img_bed_iron_name,
				R.string.detail_iron, Goods.UNOWNING, Goods.UNEQUIP));
		doctorDB.insertGoods(new Goods(33, Goods.GOODS_TYPE_BED, "绳索",
				GameData.img_bed_rope_name, 700, GameData.img_bed_rope_name,
				R.string.detail_rope, Goods.UNOWNING, Goods.UNEQUIP));
		doctorDB.insertGoods(new Goods(GameData.rainbowBed_ID,
				Goods.GOODS_TYPE_BED, "彩虹桥", GameData.img_bed_rainbow_name, -1,
				GameData.img_bed_rainbow_name, R.string.detail_rairost,
				Goods.UNOWNING, Goods.UNEQUIP));

		doctorDB.insertGoods(new Goods(31, Goods.GOODS_TYPE_SKILL, "生命归还",
				GameData.img_g_skill_life_name, 1000, "生命归还",
				R.string.detail_skill_life, Goods.UNOWNING, Goods.UNEQUIP));
		doctorDB.insertGoods(new Goods(32, Goods.GOODS_TYPE_SKILL, "时间静止",
				GameData.img_g_skill_stop_name, 1000, "时间停止",
				R.string.detail_skill_clockstop, Goods.UNOWNING, Goods.UNEQUIP));
		doctorDB.insertGoods(new Goods(GameData.skillbifrost_ID,
				Goods.GOODS_TYPE_SKILL, "彩虹桥技能",
				GameData.img_g_skill_rainbow_name, -1, "彩虹桥",
				R.string.detail_skill_rainrost, Goods.UNOWNING, Goods.UNEQUIP));

		// 道具
		doctorDB.insertProp(new Prop(Prop.FIRE_USER_NAME, 9,
				GameData.img_effect_fire_name));
		doctorDB.insertProp(new Prop(Prop.DOUBLE_USER_NAME, 0,
				GameData.img_effect_double_name));
		doctorDB.insertProp(new Prop(Prop.GOLD_USER_NAME, 1,
				GameData.img_effect_gold_name));
		doctorDB.insertProp(new Prop(Prop.LIFE_USER_NAME, 2,
				GameData.img_effect_life_name));
		// doctorDB.insertProp(new Prop(Prop.SLOW_USER_NAME, 4,
		// R.drawable.prop_slow));
		doctorDB.insertProp(new Prop(Prop.EXTEND_USER_NAME, 5,
				GameData.img_effect_extend_name));
		doctorDB.insertProp(new Prop(Prop.SHORTEN_USER_NAME, 6,
				GameData.img_effect_shorten_name));
		doctorDB.insertProp(new Prop(Prop.THUMBTACK_USER_NAME, 7,
				GameData.img_effect_thumbtack_name));
		doctorDB.insertProp(new Prop(Prop.STONE_USER_NAME, 8,
				GameData.img_effect_stone_name));

		doctorDB.insertAchievement(new Achievement(1, R.drawable.ach1,
				Achievement.CONDITION_1, Achievement.LOCKED,
				Achievement.AWARD_NOTHING));
		doctorDB.insertAchievement(new Achievement(2, R.drawable.ach2,
				Achievement.CONDITION_2, Achievement.LOCKED,
				Achievement.AWARD_OPENRAINROST));
		doctorDB.insertAchievement(new Achievement(3, R.drawable.ach3,
				Achievement.CONDITION_3, Achievement.LOCKED,
				Achievement.AWARD_500));
		doctorDB.insertAchievement(new Achievement(4, R.drawable.ach4,
				Achievement.CONDITION_4, Achievement.LOCKED,
				Achievement.AWARD_NOTHING));
		doctorDB.insertAchievement(new Achievement(5, R.drawable.ach5,
				Achievement.CONDITION_5, Achievement.LOCKED,
				Achievement.AWARD_500));
		doctorDB.insertAchievement(new Achievement(6, R.drawable.ach6,
				Achievement.CONDITION_6, Achievement.LOCKED,
				Achievement.AWARD_NOTHING));
		doctorDB.insertAchievement(new Achievement(7, R.drawable.ach7,
				Achievement.CONDITION_7, Achievement.LOCKED,
				Achievement.AWARD_500));
		doctorDB.insertAchievement(new Achievement(8, R.drawable.ach8,
				Achievement.CONDITION_8, Achievement.LOCKED,
				Achievement.AWARD_NOTHING));
		doctorDB.insertAchievement(new Achievement(9, R.drawable.ach9,
				Achievement.CONDITION_9, Achievement.LOCKED,
				Achievement.AWARD_500));
		doctorDB.insertAchievement(new Achievement(10, R.drawable.ach10,
				Achievement.CONDITION_10, Achievement.LOCKED,
				Achievement.AWARD_ADDBED));
		doctorDB.insertAchievement(new Achievement(11, R.drawable.ach11,
				Achievement.CONDITION_11, Achievement.LOCKED,
				Achievement.AWARD_ADDSCENE));
		doctorDB.insertAchievement(new Achievement(12, R.drawable.ach12,
				Achievement.CONDITION_12, Achievement.LOCKED,
				Achievement.AWARD_ADDNPC));
		doctorDB.insertAchievement(new Achievement(13, R.drawable.ach13,
				Achievement.CONDITION_13, Achievement.LOCKED,
				Achievement.AWARD_REDUCECOOLING));
		doctorDB.insertAchievement(new Achievement(14, R.drawable.ach14,
				Achievement.CONDITION_14, Achievement.LOCKED,
				Achievement.AWARD_ADDPROP10));

	}
}
