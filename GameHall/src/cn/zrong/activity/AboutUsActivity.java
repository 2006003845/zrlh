package cn.zrong.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import cn.zrong.activity.base.BaseActivity;

public class AboutUsActivity extends BaseActivity {
	private Context context;

	public static void launch(Context c) {
		Intent intent = new Intent(c, AboutUsActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		c.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;
		setContentView(R.layout.aboutus);
		initView();
	}

	private void initView() {

		this.findViewById(R.id.back_btn).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						getGameContext().finish();
					}
				});
	}

	@Override
	public BaseActivity getGameContext() {
		// TODO Auto-generated method stub
		return AboutUsActivity.this;
	}

}
