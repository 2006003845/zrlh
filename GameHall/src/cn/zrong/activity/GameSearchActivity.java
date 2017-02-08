package cn.zrong.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import cn.zrong.activity.base.BaseActivity;

public class GameSearchActivity extends BaseActivity {
	private Context context;

	public static void launch(Context c, Intent intent) {
		if (intent == null) {
			intent = new Intent();
		}
		intent.setClass(c, GameClassifyActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		c.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;
		setContentView(R.layout.friends);
		initView();
	}

	private void initView() {

	}

	@Override
	public BaseActivity getGameContext() {

		return this;
	}

}
