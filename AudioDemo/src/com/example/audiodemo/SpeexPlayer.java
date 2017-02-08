/**
 * 
 */
package com.example.audiodemo;

import java.io.File;

import com.gauss.speex.encode.SpeexDecoder;

/**
 * @author Gauss
 * 
 */
public class SpeexPlayer {
	private String fileName = null;
	private SpeexDecoder speexdec = null;

	public SpeexPlayer(String fileName) {
		this.fileName = fileName;
	}

	RecordPlayThread rpt;

	public void startPlay() {
		stopPlay();
		try {
			speexdec = new SpeexDecoder(new File(this.fileName));
		} catch (Exception e) {
			e.printStackTrace();
		}
		rpt = new RecordPlayThread();
		isPlay = true;
		Thread th = new Thread(rpt);
		th.start();
	}

	public void stopPlay() {
		if (speexdec != null) {
			isPlay = false;
			speexdec.setPaused(true);
			speexdec = null;
		}
	}

	boolean isPlay = false;

	class RecordPlayThread extends Thread {

		public void run() {
			try {
				if (speexdec != null)
					speexdec.decode();
			} catch (Exception t) {
				t.printStackTrace();
			}
		}
	};
}
