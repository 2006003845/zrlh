package com.zrong.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.zrong.data.DoctorJumpDB;
import com.zrong.data.GameData;
import com.zrong.entity.Combo;
import com.zrong.entity.Music;
import com.zrong.entity.Result;
import com.zrong.entity.Stage;
import com.zrong.ui.base.Base2Activity;
import com.zrong.view.BloodView;
import com.zrong.view.NumView;
import com.zrong.view.TimeView;

public class PlayResultActivity extends Base2Activity {
	private DoctorJumpDB doctorDB;
	private SharedPreferences prefs;

	public static final int STAGERESULT = 0;
	public static final int LIMTEDRESULT = 1;
	public static final int ENDLESSRESULT = 2;
	public static final String RESULT_KEY = "result_key";

	@Override
	public void init() {
		super.init();
		Music.getInstance(this).start(R.raw.other, true);
		// doctorDB = DoctorJumpDB.newInstanceOfDB(mContext);
		doctorDB = new DoctorJumpDB(this);
		prefs = PreferenceManager.getDefaultSharedPreferences(this);
		initView();
	}

	private void initView() {
		Bundle b = getIntent().getExtras();
		int from = b.getInt(RESULT_KEY);
		// 过关模式
		if (from == STAGERESULT) {
			showStageResultDialog();
		}// 限时模式
		else if (from == LIMTEDRESULT) {
			showLimitedResultDialog();
		}// 无尽模式
		else if (from == ENDLESSRESULT) {
			showEndlessResultDialog();
		}
	}

	// 重置Game 数据
	private void setGameStat() {
		if (GameData.currentGameMode == GameData.GAME_MODE_STAGE) {
			GameData.player.setLive_value(3);
			GameData.currentResult.bloodvalue = 3;

		}
		if (GameData.currentGameMode == GameData.GAME_MODE_ENDLESS) {
			GameData.player.setLive_value(6);
		}
		if (GameData.currentGameMode == GameData.GAME_MODE_LIMITTIME) {
			GameData.comboList.clear();
		}
	}

	private void showStageResultDialog() {
		setContentView(R.layout.play_result_stage);
		Result result = GameData.currentResult;
		final NumView floor1V = (NumView) this
				.findViewById(R.id.play_result_stage_floor1_numview);
		floor1V.setImgId(R.drawable.img_pricenum);
		floor1V.setFrameCount(10);
		final NumView floor2V = (NumView) this
				.findViewById(R.id.play_result_stage_floor2_numview);
		floor2V.setImgId(R.drawable.img_pricenum);
		floor2V.setFrameCount(10);
		final NumView floor3V = (NumView) this
				.findViewById(R.id.play_result_stage_floor3_numview);
		floor3V.setImgId(R.drawable.img_pricenum);
		floor3V.setFrameCount(10);
		floor1V.setNum("" + result.floor1);
		floor2V.setNum("" + result.floor2);
		floor3V.setNum("" + result.floor3);

		final NumView scoreV = (NumView) this
				.findViewById(R.id.play_result_stage_score_numview);
		final NumView moneyV = (NumView) this
				.findViewById(R.id.play_result_stage_moneyview);
		int score = (result.floor1 + result.floor2 * 2 + result.floor3 * 3)
				* (result.floor1 + result.floor2 + result.floor3)
				* result.bloodvalue / 10;
		scoreV.setImgId(R.drawable.img_num);
		scoreV.setFrameCount(11);
		scoreV.setAddMarkImg(false);
		scoreV.setNum("" + score);
		// scoreV.setKeepScreenOn(true);

		moneyV.setImgId(R.drawable.img_num);
		moneyV.setFrameCount(11);
		moneyV.setAddMarkImg(true);
		moneyV.setNum("" + score / 10);
		// moneyV.setKeepScreenOn(true);

		// 更新金币
		prefs.edit()
				.putInt(HomeActivity.PLAYER_WEALTH,
						prefs.getInt(HomeActivity.PLAYER_WEALTH, 0) + score
								/ 10).commit();
		// 总金币数
		NumView wealthV = (NumView) this
				.findViewById(R.id.play_result_stage_wealthview);
		wealthV.setImgId(R.drawable.img_goldnum);
		wealthV.setFrameCount(10);
		wealthV.setNum("" + prefs.getInt(HomeActivity.PLAYER_WEALTH, 0));
		wealthV.invalidate();
		// 游戏等级
		BloodView starV = (BloodView) this
				.findViewById(R.id.play_result_stage_starview);
		starV.setImageId(R.drawable.img_star_y, R.drawable.img_star_n);
		starV.setCount(3);
		starV.setBloodNum(result.bloodvalue);
		// starV.setKeepScreenOn(true);
		// 更新数据库该关的状态
		final Stage stage = GameData.currentStage;
		if (stage.isPassed == Stage.PASSED) {
			doctorDB.updataStage(
					PreferenceManager.getDefaultSharedPreferences(
							PlayResultActivity.this).getInt(
							StageActivity.STAGE_ID, 0), Stage.PASSED,
					result.bloodvalue);
		} else {
			doctorDB.updataStage(stage.numb,
					result.bloodvalue == 0 ? Stage.UNPASSED : Stage.PASSED,
					result.bloodvalue);
			if (result.bloodvalue > 0) {
				stage.isPassed = Stage.PASSED;
			}
		}

		this.findViewById(R.id.play_result_stage_btn_next).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {

						if (stage.isPassed == Stage.UNPASSED) {
							Toast.makeText(PlayResultActivity.this,
									"此关卡未过，无法进行下关游戏", 1000).show();
							return;
						}
						// TODO 下一关
						Bundle b = new Bundle();
						b.putInt(GameData.NEXTSTSSGE, stage.numb + 1);
						Intent intent = new Intent(mContext, MainActivity.class);
						intent.putExtras(b);
						activityStart(intent);

					}
				});
		this.findViewById(R.id.play_result_stage_btn_replay)
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO 重玩
						activityStart(new Intent(mContext, MainActivity.class));
					}
				});
		this.findViewById(R.id.play_result_stage_btn_back).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						// activityStart(new Intent(mContext,
						// HomeActivity.class));
						exit();
					}
				});

	}

	/**
	 * 显示无尽模式结果窗口
	 */
	private void showEndlessResultDialog() {
		setContentView(R.layout.play_result_endless);
		Result result = GameData.currentResult;

		final TimeView holdtimeV = (TimeView) this
				.findViewById(R.id.play_result_endless_timeview);
		holdtimeV.setImgId(R.drawable.img_timenum);

		final NumView moneyV = (NumView) this
				.findViewById(R.id.play_result_endless_getwealthview);
		moneyV.setImgId(R.drawable.img_num);
		moneyV.setFrameCount(11);
		moneyV.setAddMarkImg(true);
		holdtimeV.setTime(result.lastplaytime);// 支持时间
		int gold = result.lastplaytime
				* (((result.lastplaytime > 181 ? 181 : result.lastplaytime) - 1) / 90 + 1)
				/ 10;
		moneyV.setNum("" + gold);
		// 更新金币
		prefs.edit()
				.putInt(HomeActivity.PLAYER_WEALTH,
						prefs.getInt(HomeActivity.PLAYER_WEALTH, 0) + gold)
				.commit();

		// 总金币数
		NumView wealthV = (NumView) this
				.findViewById(R.id.play_result_endless_wealthview);
		wealthV.setImgId(R.drawable.img_goldnum);
		wealthV.setFrameCount(10);
		wealthV.setNum("" + prefs.getInt(HomeActivity.PLAYER_WEALTH, 0));
		wealthV.invalidate();
		this.findViewById(R.id.play_result_endless_btn_replay)
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO replay
						activityStart(new Intent(mContext, MainActivity.class));

					}
				});
		this.findViewById(R.id.play_result_endless_btn_back)
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO replay
						// activityStart(new Intent(mContext,
						// HomeActivity.class));
						exit();
					}
				});
	}

	/**
	 * 显示限时模式结果窗口
	 */
	private void showLimitedResultDialog() {
		setContentView(R.layout.play_result_limit);
		int len = GameData.comboList.size();
		Combo[] combos = new Combo[len];
		for (int i = 0; i < len; i++) {
			combos[i] = GameData.comboList.get(i);
		}
		final NumView maxComboV = (NumView) this
				.findViewById(R.id.play_result_timed_comboview);
		maxComboV.setImgId(R.drawable.img_num);
		maxComboV.setFrameCount(11);
		maxComboV.setAddMarkImg(false);
		final NumView scoreV = (NumView) this
				.findViewById(R.id.play_result_timed_scoreview);
		scoreV.setImgId(R.drawable.img_num);
		scoreV.setFrameCount(11);
		scoreV.setAddMarkImg(false);
		final NumView moneyV = (NumView) this
				.findViewById(R.id.play_result_timed_moneyview);
		moneyV.setImgId(R.drawable.img_num);
		moneyV.setFrameCount(11);
		moneyV.setAddMarkImg(true);
		Combo[] comboss = sortCombGroup(combos);
		if (comboss == null) {
			comboss = new Combo[] { new Combo(0, 0) };
		}
		maxComboV.setNum("" + comboss[comboss.length - 1].score);// 最大Cambo
		int score = getScoreFromCombo(comboss);
		scoreV.setNum("" + score);// 得分
		moneyV.setNum("" + score / 10);
		// 更新金币
		prefs.edit()
				.putInt(HomeActivity.PLAYER_WEALTH,
						prefs.getInt(HomeActivity.PLAYER_WEALTH, 0) + score
								/ 10).commit();

		// 总金币数
		NumView totalWealthV = (NumView) this
				.findViewById(R.id.play_result_timed_wealthview);
		totalWealthV.setImgId(R.drawable.img_goldnum);
		totalWealthV.setFrameCount(10);
		totalWealthV.setNum("" + prefs.getInt(HomeActivity.PLAYER_WEALTH, 0));
		totalWealthV.invalidate();
		this.findViewById(R.id.play_result_timed_btn_replay)
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO replay
						activityStart(new Intent(mContext, MainActivity.class));
					}
				});
		this.findViewById(R.id.play_result_timed_btn_back).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO replay
						// activityStart(new Intent(mContext,
						// HomeActivity.class));
						exit();
					}
				});

	}

	/**
	 * 获取得分
	 * 
	 * @param combos
	 * @return
	 */
	private static int getScoreFromCombo(Combo[] combos) {
		int len = combos.length;
		int count = 0;
		for (int i = 0; i < len - 1; i++) {
			count = count + combos[i].score;
		}
		return count + combos[len - 1].score * combos[len - 1].combindex;
	}

	/**
	 * 排列combo 数组
	 * 
	 * @param combos
	 * @return
	 */
	private static Combo[] sortCombGroup(Combo[] combos) {
		if (combos == null) {
			return null;
		}
		for (int i = 0; i < combos.length - 1; i++) {
			Combo temp;
			if (combos[i].score > combos[i + 1].score) {
				temp = combos[i];
				combos[i] = combos[i + 1];
				combos[i + 1] = temp;
			}
		}
		return combos;
	}

	private void activityStart(Intent intent) {
		if (intent == null) {
			return;
		}
		exit();
		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		this.startActivity(intent);

	}

	private void exit() {
		setGameStat();
		PlayResultActivity.this.finish();

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			exit();
		}
		return super.onKeyDown(keyCode, event);
	}

}
