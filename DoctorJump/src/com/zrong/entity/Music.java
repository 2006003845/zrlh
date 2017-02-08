package com.zrong.entity;

import java.io.IOException;

import android.content.Context;
import android.media.MediaPlayer;

import com.zrong.data.GameData;

public class Music {
	private Context mcontext;

	private MediaPlayer player;

	private int musicId = -1;
	private boolean loop;

	private static Music music;

	private Music(Context context) {
		this.mcontext = context;
	}

	public static synchronized Music getInstance(Context context) {
		if (music == null) {
			music = new Music(context);
		}
		return music;
	}

	public void resume() {
		if (musicId == -1) {
			return;
		}
		if (GameData.isMusicOpen) {
			if (player != null && player.isPlaying()) {
				return;
			}
			if (player != null) {
				player.stop();
				player = null;
			}
			player = MediaPlayer.create(this.mcontext, musicId);
			player.start();
			player.setLooping(loop);

		}

	}

	public void start(int musicId, boolean loop) {
		if (GameData.isMusicOpen) {
			if (player != null) {
				if (this.musicId == musicId && player.isPlaying()) {
					return;
				} else {
					player.stop();
					player.release();
					player = null;
				}
			}
			player = MediaPlayer.create(this.mcontext, musicId);
			try {
				player.prepare();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			player.start();
			player.setLooping(loop);
		}
		this.musicId = musicId;
		this.loop = loop;
	}

	public void stop() {
		if (player != null) {
			player.stop();
			player.release();
			player = null;
		}
	}

	public void pause() {
		if (player != null) {
			player.pause();
		}
	}

}
