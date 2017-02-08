package com.zr.zzl.test;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class T2Activity extends Activity {
	public static void launch(Context c, Intent intent) {
		if (intent == null) {
			intent = new Intent();
		}
		intent.setClass(c, T2Activity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		c.startActivity(intent);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
	}

}
