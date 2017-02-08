package com.example.corporate;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.corporate.base.BaseActivity;
import com.example.corporate.base.MyToast;
import com.zzl.zl_app.connection.Community;
import com.zzl.zl_app.io.HttpThreadManager;

public class CorporateActivity extends BaseActivity {
	public static final String Tag = "corporate";

	public static void launch(Context c, Intent intent) {
		if (intent == null) {
			intent = new Intent();
		}
		intent.setClass(c, CorporateActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		c.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Community.initHandler();
		HttpThreadManager.sharedManager(this);
		MyToast.initToast(getLayoutInflater(), R.layout.toast, R.id.toast_tv);
		addActMap("Tag", getContext());
		setContentView(R.layout.corporate);
		initView();
	}

	private Button unregistBtn;

	private void initView() {
		this.findViewById(R.id.back).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				closeOneAct(Tag);
			}
		});
		this.findViewById(R.id.btn).setVisibility(View.GONE);
		unregistBtn = (Button) this.findViewById(R.id.corporate_btn_unregist);
		unregistBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO
				AuthenticationEditActivity.launch(getContext(), getIntent());
				switchActIn();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.corporate, menu);
		return true;
	}

	@Override
	public BaseActivity getContext() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public void setDialogContent(AlertDialog dialog, int layoutId, String msg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setDialogTitle(AlertDialog dialog, int layoutId, String title) {
		// TODO Auto-generated method stub

	}

	@Override
	public OnClickListener setPositiveClickListener(AlertDialog dialog,
			int layoutId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OnClickListener setNegativeClickListener(AlertDialog dialog,
			int layoutId) {
		// TODO Auto-generated method stub
		return null;
	}

}
