package com.zrong.ui;

import android.view.View;
import android.view.View.OnClickListener;

import com.zrong.entity.Music;
import com.zrong.ui.base.Base2Activity;

public class HelpActivity extends Base2Activity {

	@Override
	public void init() {
		super.init();
		initView();
		Music.getInstance(this).start(R.raw.other, true);
	}

	private void initView() {
		setContentView(R.layout.help);
		this.findViewById(R.id.help_btn_back).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						HelpActivity.this.finish();
					}
				});
	}

}
