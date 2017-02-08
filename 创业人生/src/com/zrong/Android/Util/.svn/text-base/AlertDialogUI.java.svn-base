package com.zrong.Android.Util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;

import com.zrong.Android.activity.R;
import com.zrong.Android.game.GameDefinition;

public class AlertDialogUI
{
	public static boolean showAlertDialog;
	static AlertDialog dialog;
	static View dialogView;
	static Context mContext;
	public static Animation animation_down, animation_up;

	public static void showDialog_zhaolan(Context context, Intent intent)
	{
		if (showAlertDialog)
			return;
		showAlertDialog = true;
		dialogView = View.inflate(context, R.layout.dialog_zl, null);// 获得View

		mContext = context;
		dialog = new AlertDialog.Builder(context).create();
		dialog.show();
		dialog.setOnKeyListener(new OnKeyListener()
		{
			
			public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event)
			{
				// TODO Auto-generated method stub
				if(keyCode == KeyEvent.KEYCODE_BACK)
				{
					closeMiddleDialog();
				}
				return false;
			}
		});
		dialog.setContentView(dialogView);
		showMiddleDialog();
	}

	/** 弹出弹出框 */
	public static void showMiddleDialog()
	{
		if (animation_down == null)
		{

			animation_down = new TranslateAnimation(0.0f, 0.0f,
					-GameDefinition.screenWidth * 3 / 2, 0.0f);

		}
		animation_down.setDuration(1000);
		animation_down.setFillAfter(false);
		dialogView.startAnimation(animation_down);
		animation_down.setInterpolator(AnimationUtils.loadInterpolator(
				mContext, android.R.anim.bounce_interpolator));

		animation_down.setAnimationListener(new AnimationListener()
		{

			public void onAnimationStart(Animation animation)
			{
				// TODO Auto-generated method stub
				Log.d("zzx", "start");
			}

			public void onAnimationRepeat(Animation animation)
			{
				// TODO Auto-generated method stub
				Log.d("zzx", "----------");

			}

			public void onAnimationEnd(Animation animation)
			{
				Log.d("zzx", "end");
			}
		});

	}

	/** 关闭弹出框 */
	public static void closeMiddleDialog()
	{
		if (!showAlertDialog)
			return;
		showAlertDialog = false;

		animation_down = null;

		animation_up = new TranslateAnimation(0, 0, 0.0f,
				-GameDefinition.screenWidth * 3 / 2);
		animation_up.setDuration(1000);
		animation_up.setFillAfter(false);
		animation_up.setInterpolator(AnimationUtils.loadInterpolator(mContext,
				android.R.anim.accelerate_interpolator));

		dialogView.startAnimation(animation_up);
		animation_up.setAnimationListener(new AnimationListener()
		{

			public void onAnimationStart(Animation animation)
			{
				Log.d("zzx", "close_start");
			}

			public void onAnimationRepeat(Animation animation)
			{
				// TODO Auto-generated method stub

			}

			public void onAnimationEnd(Animation animation)
			{
				animation_up = null;
				Log.d("zzx", "close_end");
			}
		});
		// handler.removeCallbacks(runnable_up);
		// handler.post(runnable_up);
	}

}
