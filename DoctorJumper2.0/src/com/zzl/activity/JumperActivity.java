package com.zzl.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.zzl.view.MenuButtonView;

public class JumperActivity extends Activity {

	private ImageView stageV, limitV, endlessV;
	private Animation rotate_large_anim1, rotate_large_anim2;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		MenuButtonView mbv = (MenuButtonView) this.findViewById(R.id.mbv);
		mbv.setBtnsBack(R.drawable.set_back);
		mbv.setResId(new int[] { R.drawable.set_cl, R.drawable.set_ck,
				R.drawable.set_sy, R.drawable.set_xx });
		rotate_large_anim1 = AnimationUtils.loadAnimation(this,
				R.anim.animset_rotate_scale);

		rotate_large_anim2 = AnimationUtils.loadAnimation(this,
				R.anim.animset_rotate_scale2);
		stageV = (ImageView) this.findViewById(R.id.home_btn_stagemode);
		limitV = (ImageView) this.findViewById(R.id.home_btn_timemode);
		endlessV = (ImageView) this.findViewById(R.id.home_btn_endlessmode);
		stageV.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				int act = event.getAction();
				switch (act) {
				case MotionEvent.ACTION_DOWN:
					rotate_large_anim1.setDuration(rotate_large_anim1
							.getDuration());
					stageV.startAnimation(rotate_large_anim1);
					break;

				case MotionEvent.ACTION_UP:
					stageV.clearAnimation();
					stageV.startAnimation(rotate_large_anim2);
					break;
				case MotionEvent.ACTION_MOVE:
					
					break;
				}

				return false;
			}
		});
		limitV.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				int act = event.getAction();
				switch (act) {
				case MotionEvent.ACTION_DOWN:
					limitV.startAnimation(rotate_large_anim1);
					break;

				case MotionEvent.ACTION_UP:
					limitV.clearAnimation();
					limitV.startAnimation(rotate_large_anim2);
					break;
				}
				return false;
			}
		});
		endlessV.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				int act = event.getAction();
				switch (act) {
				case MotionEvent.ACTION_DOWN:
					endlessV.startAnimation(rotate_large_anim1);

					break;

				case MotionEvent.ACTION_UP:
					endlessV.clearAnimation();
					endlessV.startAnimation(rotate_large_anim2);
					break;
				}
				return false;
			}
		});

	}
}