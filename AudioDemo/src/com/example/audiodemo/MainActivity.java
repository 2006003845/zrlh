package com.example.audiodemo;

import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	public static final int STOPPED = 0;
	public static final int RECORDING = 1;

	// PcmRecorder recorderInstance = null;
	SpeexRecorder recorderInstance = null;

	Button startButton = null;
	Button stopButton = null;
	Button playButton = null;
	Button exitButon = null;
	TextView textView = null;
	int status = STOPPED;

	String fileName = null;
	SpeexPlayer splayer = null;

	public void onClick(View v) {
		if (v == startButton) {
			this.setTitle("��ʼ¼���ˣ�");
			// fileName = "/mnt/sdcard/gauss.mp3";
			splayer = null;
			fileName = "/mnt/sdcard/" + (new Date()).getTime() + ".spx";
			if (recorderInstance == null) {
				// recorderInstance = new PcmRecorder();
				recorderInstance = new SpeexRecorder(fileName);
				Thread th = new Thread(recorderInstance);
				th.start();
			}
			recorderInstance.setRecording(true);
		} else if (v == stopButton) {
			this.setTitle("ֹͣ��");

			if (recorderInstance != null) {
				recorderInstance.setRecording(false);
				recorderInstance = null;
			}
		} else if (v == playButton) {
			// if (fileName == null)
			// return;
			// if (recorderInstance != null) {
			// recorderInstance.setRecording(false);
			// recorderInstance = null;
			// }
			// play here........
			this.setTitle("��ʼ����");
			System.out.println("filename====" + fileName);
			if (splayer == null)
				splayer = new SpeexPlayer(fileName);
			if (!splayer.isPlay)
				splayer.startPlay();
			else
				splayer.stopPlay();

		} else if (v == exitButon) {
			if (recorderInstance != null) {
				recorderInstance.setRecording(false);
				recorderInstance = null;
			}
			System.exit(0);
		}
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		startButton = new Button(this);
		stopButton = new Button(this);
		exitButon = new Button(this);
		playButton = new Button(this);
		textView = new TextView(this);

		startButton.setText("Start");
		stopButton.setText("Stop");
		playButton.setText("����");
		exitButon.setText("�˳�");
		textView.setText("android ¼������\n(1)��ȡPCM����." + "\n(2)ʹ��speex����");

		startButton.setOnClickListener(this);
		playButton.setOnClickListener(this);
		stopButton.setOnClickListener(this);
		exitButon.setOnClickListener(this);

		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.addView(textView);
		layout.addView(startButton);
		layout.addView(stopButton);
		layout.addView(playButton);
		layout.addView(exitButon);
		this.setContentView(layout);
	}
}
