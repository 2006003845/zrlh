package com.zrong.ui;

import android.content.Context;
import android.media.AudioManager;
import android.widget.ImageView;

import com.zrong.entity.Music;
import com.zrong.ui.base.Base2Activity;

public class AchievementsActivity extends Base2Activity {
	private AudioManager audioManager;
	private int currentVoiceValue;

	@Override
	public void init() {
		super.init();
		Music.getInstance(this).start(R.raw.other, true);
		audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		currentVoiceValue = audioManager
				.getStreamVolume(AudioManager.STREAM_MUSIC);
		setContentView(R.layout.achevements);
		initView();

	}

	private ImageView infoBtn, voiceBtn, musicBtn, msgBtn;

	private void initView() {
	}

}
