package com.zzl.voicetalk;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Button start, mediastop, play;
	private MediaRecorder mediarecorder = null;
	private MediaPlayer mediaplayer;
	private File tempMeidFile;

	private File mediapath;

	private boolean isSDcard;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		start = (Button) this.findViewById(R.id.start);
		mediastop = (Button) this.findViewById(R.id.mediastop);
		mediastop.setEnabled(false);
		play = (Button) this.findViewById(R.id.play);
		play.setEnabled(false);
		start.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});
		mediastop.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});
		play.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});

		isSDcard = Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED);
		System.out.println(isSDcard);

		if (!isSDcard) {
			mediapath = Environment.getExternalStorageDirectory();
			System.out.println("文件路径:" + mediapath);
		} else {
			// 如果SDcard不存在，提示并退出程序
			Toast.makeText(MainActivity.this, "SDCard不存在，请插入SDcard",
					Toast.LENGTH_SHORT);
			this.finish();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void startRecorder() {
		try {
			tempMeidFile = File.createTempFile("tempMediaFile", ".arm",
					mediapath);
			// 生成一个MediaRecorder对象
			mediarecorder = new MediaRecorder();
			// 设置音频源为麦克风
			mediarecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
			// 设置录制的音频格式
			mediarecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
			// 设置录制音频的编码
			mediarecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
			// 设置储存的文件路径
			mediarecorder.setOutputFile(tempMeidFile.getAbsolutePath());
			// 准备录制
			mediarecorder.prepare();
			// 开始录制
			mediarecorder.start();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 允许停止
		mediastop.setEnabled(true);
		// 设置开始录音键不能按
		start.setEnabled(false);
		start.setText("正在录音......");
	}

	public void stopRecorder() {
		if (mediarecorder != null) {
			// 停止录音
			mediarecorder.stop();
			// 释放资源
			mediarecorder.release();
			mediarecorder = null;
			// 设置按键的变化
			start.setText("开始录音");
			start.setEnabled(true);
			play.setEnabled(true);
			mediastop.setEnabled(false);
			System.out.println("OK");
		} else {
			System.out.println("this is null");
		}
	}

	public void playRecorder() {
		mediaplayer = new MediaPlayer();
		// 重置播放器
		mediaplayer.reset();
		// 设置比方的路径
		try {
			// 得到音频文件
			mediaplayer.setDataSource(tempMeidFile.getAbsolutePath());
			// 准备播放
			mediaplayer.prepare();
			// 开始播放
			mediaplayer.start();
			// 设置当播放完了的监听
			mediaplayer.setOnCompletionListener(new OnCompletionListener() {
				// 如果播放完了，删除文件
				@Override
				public void onCompletion(MediaPlayer mp) {
					if (tempMeidFile.exists()) {
						if (tempMeidFile.isFile()) {
							tempMeidFile.delete();
							Toast.makeText(MainActivity.this, "成功删除文件",
									Toast.LENGTH_SHORT).show();
						} else {
							Toast.makeText(MainActivity.this, "不是文件",
									Toast.LENGTH_SHORT).show();
						}
					} else {
						Toast.makeText(MainActivity.this, "文件不存在",
								Toast.LENGTH_SHORT).show();
					}
				}

			});
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
