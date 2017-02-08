package com.zrong.ui;

import android.view.View;
import android.view.View.OnClickListener;

import com.zrong.entity.Music;
import com.zrong.ui.base.Base2Activity;

public class AboutUSActivity extends Base2Activity {

	@Override
	public void init() {
		super.init();
		Music.getInstance(this).start(R.raw.other, true);
		setContentView(R.layout.about);
		this.findViewById(R.id.about_btn_back).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						AboutUSActivity.this.finish();
					}
				});

	}
}
