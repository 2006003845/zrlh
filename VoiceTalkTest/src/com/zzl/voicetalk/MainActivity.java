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
			System.out.println("�ļ�·��:" + mediapath);
		} else {
			// ���SDcard�����ڣ���ʾ���˳�����
			Toast.makeText(MainActivity.this, "SDCard�����ڣ������SDcard",
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
			// ����һ��MediaRecorder����
			mediarecorder = new MediaRecorder();
			// ������ƵԴΪ��˷�
			mediarecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
			// ����¼�Ƶ���Ƶ��ʽ
			mediarecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
			// ����¼����Ƶ�ı���
			mediarecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
			// ���ô�����ļ�·��
			mediarecorder.setOutputFile(tempMeidFile.getAbsolutePath());
			// ׼��¼��
			mediarecorder.prepare();
			// ��ʼ¼��
			mediarecorder.start();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ����ֹͣ
		mediastop.setEnabled(true);
		// ���ÿ�ʼ¼�������ܰ�
		start.setEnabled(false);
		start.setText("����¼��......");
	}

	public void stopRecorder() {
		if (mediarecorder != null) {
			// ֹͣ¼��
			mediarecorder.stop();
			// �ͷ���Դ
			mediarecorder.release();
			mediarecorder = null;
			// ���ð����ı仯
			start.setText("��ʼ¼��");
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
		// ���ò�����
		mediaplayer.reset();
		// ���ñȷ���·��
		try {
			// �õ���Ƶ�ļ�
			mediaplayer.setDataSource(tempMeidFile.getAbsolutePath());
			// ׼������
			mediaplayer.prepare();
			// ��ʼ����
			mediaplayer.start();
			// ���õ��������˵ļ���
			mediaplayer.setOnCompletionListener(new OnCompletionListener() {
				// ����������ˣ�ɾ���ļ�
				@Override
				public void onCompletion(MediaPlayer mp) {
					if (tempMeidFile.exists()) {
						if (tempMeidFile.isFile()) {
							tempMeidFile.delete();
							Toast.makeText(MainActivity.this, "�ɹ�ɾ���ļ�",
									Toast.LENGTH_SHORT).show();
						} else {
							Toast.makeText(MainActivity.this, "�����ļ�",
									Toast.LENGTH_SHORT).show();
						}
					} else {
						Toast.makeText(MainActivity.this, "�ļ�������",
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
