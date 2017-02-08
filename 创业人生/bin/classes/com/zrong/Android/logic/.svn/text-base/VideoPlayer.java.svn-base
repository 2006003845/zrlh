package com.zrong.Android.logic;

import java.util.Hashtable;
import java.util.Vector;



import com.zrong.Android.View.customWidget.CustomerVideoView;
import com.zrong.Android.activity.MainActivity;
import com.zrong.Android.activity.R;
import com.zrong.Android.game.GameDefinition;
import com.zrong.Android.game.GameGroupControl;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.GestureDetector.OnGestureListener;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.FrameLayout.LayoutParams;

public class VideoPlayer extends LogicObject implements OnTouchListener,OnGestureListener,OnCompletionListener {
	
	public static final String TAG = "VideoPlayer";
	CustomerVideoView cvv;
	public Button PlayButton;
	MainActivity a;
	VideoPlayer videoPlayer;
    CustomerVideoView videoView;
	public boolean isPlaying = false;
	private Button button_end;
	private ImageView iview;
	public VideoPlayer(Context context,GameGroupControl control)
	{
		super(context,control);
		
		videoPlayer = this;
		
		a = (MainActivity)context;
		
		View v=View.inflate(context, R.layout.videoplayerview, null);//获得播放界面
		
		v.setId(GameDefinition.VideoPlayerView);
		
		 
		videoView = (CustomerVideoView)v.findViewById(R.id.videoView);
		
		//videoView.setVideoPath("/sdcard/video/大明宫-001(幻影迷城).mp4");
		videoView.setVideoPath("/sdcard/video/power.mp4");
		
		 
	 
		button_end =(Button)v.findViewById(R.id.button_end);
		
		iview = (ImageView)v.findViewById(R.id.imageView_endBack);
		button_end.setOnClickListener(new OnClickListener()
		{
			public void onClick(View arg0) 
			{
				videoView.seekTo(videoView.getDuration());
				 
			}
		}
		); 
	 
		videoView.setVisibility(View.VISIBLE);
		videoView.setOnTouchListener(videoPlayer);
		videoView.requestFocus();
		videoView.setOnCompletionListener(videoPlayer);
		videoView.start();
		 
		this.registerView(v);
		a.setContentView(v);
	}
	 
	public void loadProperties(Vector v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void reCycle() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void refurbish() {
		// TODO Auto-generated method stub
		
	}

	public boolean onTouch(View arg0, MotionEvent arg1){
		
		Log.v(TAG, "onTouch");
		 
		if(button_end.getVisibility() == View.VISIBLE)
		{
			button_end.setVisibility(View.INVISIBLE);
			iview.setVisibility(View.INVISIBLE);
			button_end.setClickable(false);
		}
		else
		{
			button_end.setVisibility(View.VISIBLE);
			iview.setVisibility(View.VISIBLE);
			button_end.setClickable(true);
		}
		return false;
	}

	public boolean onDown(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean onFling(MotionEvent arg0, MotionEvent arg1, float arg2,
			float arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	public void onLongPress(MotionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2,
			float arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	public void onShowPress(MotionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public boolean onSingleTapUp(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}
 
	/**
	 * 视频播放完的监听方法
	 */
	public void onCompletion(MediaPlayer arg0) {
		 
	    ((MainActivity)context).setContentView(((MainActivity)context).gameGroupControl);
	}

	 
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void synchviewdata() {
		// TODO Auto-generated method stub
		
	}

	 

}
