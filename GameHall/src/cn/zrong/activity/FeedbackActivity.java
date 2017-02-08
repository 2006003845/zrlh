package cn.zrong.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import cn.zrong.ApplicationData;
import cn.zrong.GameAPI;
import cn.zrong.activity.base.BaseActivity;
import cn.zrong.connection.Connection;
import cn.zrong.connection.IOWriter;
import cn.zrong.connection.Protocol;

public class FeedbackActivity extends BaseActivity {

	private Context context;
	private String commitContent;
	private EditText contentEt;

	public static void launch(Context c) {
		Intent intent = new Intent(c, FeedbackActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		c.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;
		setContentView(R.layout.feedback);
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
		contentEt = (EditText) this.findViewById(R.id.feedback_editv);

		this.findViewById(R.id.feedback_commit_btn).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						commitContent = contentEt.getText().toString().trim();
						// 发送邮件
						sendMessage(commitContent);
						getGameContext().finish();

					}
				});
	}

	private void sendMessage(String msg) {
		Connection
				.sendMessage(
						Protocol.msgType_sendFeedBack,
						IOWriter.getSendFeedBack(Protocol.msgType_sendFeedBack,
								ApplicationData.currentUser.getKeyID(), msg)
								.getBytes(), GameAPI.Port_Role);
	}

	@Override
	public BaseActivity getGameContext() {
		return FeedbackActivity.this;
	}

	public static final String emailAddress = "zzl3845@gmail.com";

}
