package cn.zrong.activity.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public abstract class BaseActivity extends Activity {
	/**
		 * 
		 */
	private boolean onChange = false;

	public void onChange() {
		onChange = true;
	}

	public void disOnChange() {
		onChange = false;
	}

	public boolean isOnChange() {
		return onChange;
	}

	private BaseActivity GameContext = null;

	// public abstract GameActivity getInstance();

	private BaseActivity GameContext() {
		GameContext = getGameContext();
		return GameContext;
	}

	public abstract BaseActivity getGameContext();

	public void clearGameContext() {
		GameContext = null;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		if (!isOnChange()) {
			// Music.getInstance(GameContext()).pause();
		}
		super.onPause();
	}

	@Override
	protected void onRestart() {
		// Music.getInstance(GameContext()).resume();
		super.onRestart();
	}

	@Override
	protected void onResume() {
		// Music.getInstance(GameContext()).resume();
		super.onResume();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	protected void onStop() {
		if (!isOnChange()) {
			// Music.getInstance(GameContext()).stop();
		} else {
			disOnChange();
		}
		super.onStop();
	}

	@Override
	public void finish() {
		clearGameContext();

		onChange();
		super.finish();
	}

	@Override
	public void finishActivity(int requestCode) {
		// TODO Auto-generated method stub
		super.finishActivity(requestCode);
	}

	@Override
	public void finishActivityFromChild(Activity child, int requestCode) {
		// TODO Auto-generated method stub
		super.finishActivityFromChild(child, requestCode);
	}

	@Override
	public void finishFromChild(Activity child) {
		// TODO Auto-generated method stub
		super.finishFromChild(child);
	}

	@Override
	public void startActivity(Intent intent) {
		// TODO Auto-generated method stub

		onChange();
		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		super.startActivity(intent);
	}

	@Override
	public void startActivityForResult(Intent intent, int requestCode) {
		// TODO Auto-generated method stub
		onChange();
		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		super.startActivityForResult(intent, requestCode);
	}

	@Override
	public void startActivityFromChild(Activity child, Intent intent,
			int requestCode) {
		// TODO Auto-generated method stub
		onChange();
		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		super.startActivityFromChild(child, intent, requestCode);
	}

	@Override
	public boolean startActivityIfNeeded(Intent intent, int requestCode) {
		// TODO Auto-generated method stub
		onChange();
		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		return super.startActivityIfNeeded(intent, requestCode);
	}
}