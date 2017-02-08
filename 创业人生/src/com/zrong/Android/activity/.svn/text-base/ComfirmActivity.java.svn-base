package com.zrong.Android.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zrong.Android.Listener.ActivityTransform;
import com.zrong.Android.Util.Music;
import com.zrong.Android.View.TipBoxView;
import com.zrong.Android.game.GameDefinition;

public class ComfirmActivity extends GameActivity implements
		OnTouchListener {

	public static ComfirmActivity mContext = null;

	private int price;
	private int count;

	private View confirm;

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		final Bundle bundle = this.getIntent().getExtras();
		if (bundle == null) {
			return;
		}
		price = bundle.getInt("price");

		mContext = this;

		confirm = View.inflate(this, R.layout.confirm_twobutton, null);

		TextView title = (TextView) confirm
				.findViewById(R.id.confirm_twobutton_title);

		final TipBoxView content = (TipBoxView) confirm
				.findViewById(R.id.confirm_twobutton_content);

		title.setText(bundle.getString("title"));

		Button confirmbutton = (Button) confirm
				.findViewById(R.id.confirm_twobutton_confirm);

		Button cancelbutton = (Button) confirm
				.findViewById(R.id.confirm_twobutton_cancel);

		confirmbutton.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				Intent i = new Intent();
				bundle.putInt("count", count);
				i.putExtras(bundle);
				Log.i("ppqq", "confirmactivity.count========="+count);
				ComfirmActivity.this.setResult(GameDefinition.RESULT_OK, i);

				ComfirmActivity.this.finish();
			}
		});

		cancelbutton.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				Intent i = new Intent();

				i.putExtras(bundle);

				ComfirmActivity.this.setResult(GameDefinition.RESULT_CANCEL, i);

				ComfirmActivity.this.finish();
			}
		});

		EditText count_et = (EditText) confirm
				.findViewById(R.id.confirm_twobutton_et);
		count_et.addTextChangedListener(new TextWatcher() {

			 
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}

			 
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			 
			public void afterTextChanged(Editable s) {
				count = Integer.parseInt(s.toString().trim());
				if (count < 0) {
					// 提示输入不合格
					content.setText(mContext.getResources().getString(
							R.string.confirm_twobutton_match));
					return;
				}
				content.setText(MainActivity.resources
						.getString(R.string.store_content)
						+ count
						* price
						+ MainActivity.resources
								.getString(R.string.store_price));
			}
		});

		this.setContentView(confirm);
	}

	 

	public boolean onTouch(View arg0, MotionEvent arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	

	 
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	 
	public GameActivity getGameContext() {
		// TODO Auto-generated method stub
		return this;
	}
	
	 
	public void finish() 
	{
		mContext = null;
		super.finish();
	}

}
