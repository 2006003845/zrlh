package com.zrong.ui;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.AudioManager;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.Toast;

import com.zrong.adapter.Image4Adapter;
import com.zrong.data.DoctorJumpDB;
import com.zrong.data.GameData;
import com.zrong.engine.DynamicBG;
import com.zrong.entity.Music;
import com.zrong.entity.Player;
import com.zrong.entity.Stage;
import com.zrong.ui.base.Base2Activity;
import com.zrong.view.CoverFlow;
import com.zrong.view.DrawDynamicView;

public class StageActivity extends Base2Activity {

	public static final String STAGE_ID = "stage_id";
	private CoverFlow myGallery;
	private AudioManager audioManager;
	private int currentVoiceValue;
	private ImageView musicBtn, voiceBtn;

	private DoctorJumpDB doctorDB;
	private ArrayList<Stage> stages;// 关卡

	@Override
	public void init() {
		super.init();
		Music.getInstance(this).start(R.raw.other, true);
		// TODO 从数据库中获取到所有关卡信息
		// doctorDB = DoctorJumpDB.newInstanceOfDB(this);
		doctorDB = new DoctorJumpDB(this);
		stages = getStageList(doctorDB.query(Stage.TAB_NAME, null, null, null,
				null, null, null));
		doctorDB.closeDB();
		initView();
		audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		currentVoiceValue = audioManager
				.getStreamVolume(AudioManager.STREAM_MUSIC);
	}

	private void initView() {
		setContentView(R.layout.stage);

		int w = getWindowManager().getDefaultDisplay().getWidth();
		int h = getWindowManager().getDefaultDisplay().getHeight();
		DynamicBG dbg = DynamicBG.createDynamicBG(this, DynamicBG.SNOW, w, h);
		DrawDynamicView ddv = (DrawDynamicView) this
				.findViewById(R.id.stage_draw);
		ddv.setDynamicBG(dbg);

		myGallery = (CoverFlow) findViewById(R.id.stage_gallery);
		Image4Adapter adapter = new Image4Adapter(this, stages,
				R.layout.stage_item);
		myGallery.setAdapter(adapter);
		myGallery.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (position > 0) {
					Stage s = stages.get(position - 1);
					if (s.isPassed == Stage.UNPASSED) {
						Toast.makeText(StageActivity.this, "该关卡未开启", 1000)
								.show();
						return;
					}

				}
				GameData.currentStage = stages.get(position);
				// 判断是否为闯关模式,并对currentGameMode重新赋值
				// int PlayMode = PreferenceManager.getDefaultSharedPreferences(
				// StageActivity.this).getInt(HomeActivity.Curr_PLAYMODE,
				// 0);
				// if (PlayMode == GameData.GAME_MODE_STAGE) {
				GameData.currentGameMode = GameData.GAME_MODE_STAGE;
				if (GameData.player == null) {
					GameData.player = Player.newInstanceOfPlayer();
					GameData.player.setWealth(PreferenceManager
							.getDefaultSharedPreferences(mContext).getInt(
									HomeActivity.PLAYER_WEALTH, 0));
				}
				// }

				activityStart(new Intent(mContext, MainActivity.class));
			}
		});

		this.findViewById(R.id.stage_btn_help).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						activityStart(new Intent(mContext, HelpActivity.class));
					}
				});
		this.findViewById(R.id.stage_btn_about).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						activityStart(new Intent(mContext,
								AboutUSActivity.class));
					}
				});

		musicBtn = (ImageView) this.findViewById(R.id.stage_btn_music);
		
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


		this.findViewById(R.id.stage_btn_left).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						myGallery.onScroll(null, null, -30, 0);
					}
				});
		this.findViewById(R.id.stage_btn_right).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						myGallery.onScroll(null, null, 30, 0);
					}
				});

		this.findViewById(R.id.stage_btn_backhome).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						activitySwitch();
					}
				});
	}

	private void activityStart(Intent intent) {
		if (intent == null) {
			return;
		}
		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		this.startActivity(intent);
		this.finish();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			activitySwitch();
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}

	private ArrayList<Stage> getStageList(Cursor cursor) {
		cursor.moveToFirst();
		ArrayList<Stage> list = new ArrayList<Stage>();
		int count = cursor.getCount();
		do {
			if (count == 0) {
				Toast.makeText(this, "表为空的", 1000).show();
				// break;
			}
			list.add(Stage.getStage(cursor));
		} while (cursor.moveToNext());
		cursor.close();
		return list;
	}

	@Override
	protected void onDestroy() {
		doctorDB.closeDB();
		doctorDB.close();
		super.onDestroy();
	}

	int version = Integer.valueOf(android.os.Build.VERSION.SDK);

	// 界面切换效果
	private void activitySwitch() {

		if (version >= 5) {
			overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
		}
		this.finish();
	}
}
