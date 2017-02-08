package com.zzl.zl_app.act;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View.OnClickListener;

import com.zzl.zl_app.act.base.BaseActivity;

public class MainActivity extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	@Override
	public BaseActivity getContext() {
		// TODO Auto-generated method stub
		return null;
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
