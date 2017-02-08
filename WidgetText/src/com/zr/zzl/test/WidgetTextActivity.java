package com.zr.zzl.test;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;

public class WidgetTextActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		this.findViewById(R.id.btn).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showVerUpdateDialog();
			}
		});
		this.findViewById(R.id.btn2).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				T2Activity.launch(WidgetTextActivity.this, null);
				finish();
			}
		});

	}

	// 连接对象
	private ServiceConnection conn = new ServiceConnection() {
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			Log.i("Service", "连接成功！");

		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			Log.i("Service", "断开连接！");
		}
	};

	public void showVerUpdateDialog() {
		LayoutInflater inflater = getLayoutInflater();
		final View layout = inflater.inflate(R.layout.dialog_verupdate, null);
		final AlertDialog dialog = new AlertDialog.Builder(
				WidgetTextActivity.this).create();
		dialog.setCanceledOnTouchOutside(true);
		dialog.show();
		dialog.getWindow().setLayout(
				getWindowManager().getDefaultDisplay().getWidth(),
				getWindowManager().getDefaultDisplay().getHeight());
		dialog.getWindow().setContentView(layout);
		dialog.setCanceledOnTouchOutside(true);

		dialog.findViewById(R.id.dialog_verupdate_ok).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						dialog.dismiss();
						// TODO
						Intent intent = new Intent(WidgetTextActivity.this,
								DownloadService.class);
						intent.putExtra("updateURL",
								"http://preview.51zhixun.com/jiajiao/images/pxjs.png");
						startService(intent);
						
					}
				});
		dialog.findViewById(R.id.dialog_verupdate_cancel).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						dialog.dismiss();
					}
				});

	}
}