package cn.zrong.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;
import cn.zrong.ApplicationData;
import cn.zrong.GameAPI;
import cn.zrong.activity.base.BaseActivity;
import cn.zrong.connection.Connection;
import cn.zrong.connection.IOWriter;
import cn.zrong.connection.Protocol;

public class AccountChangeActivity extends BaseActivity {
	private Context context;

	public static void launch(Context c) {
		Intent intent = new Intent(c, AccountChangeActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		c.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;
		setContentView(R.layout.account_change);
		initView();
	}

	private EditText accountET, pwdET;

	private void initView() {
		this.findViewById(R.id.back_btn).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						getGameContext().finish();
					}
				});
		accountET = (EditText) this.findViewById(R.id.changeaccount_account_et);
		pwdET = (EditText) this.findViewById(R.id.changeaccount_psd_et);
		this.findViewById(R.id.changeaccount_login_btn).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						String accountName = accountET.getText().toString()
								.trim();
						if (accountName == null || "".equals(accountName)) {
							Toast.makeText(context, "账户名不能为空", 1000).show();
						}
						String pwd = pwdET.getText().toString().trim();
						if (pwd == null || "".equals(pwd)) {
							Toast.makeText(context, "密码不能为空", 1000).show();
						}
						Connection.sendMessage(
								Protocol.msgType_login,
								IOWriter.getLogin(Protocol.msgType_login,
										accountName, pwd, GameAPI.userApp,
										GameAPI.channel, GameAPI.platform, "",
										0 + "", GameAPI.iMobile).getBytes(),
								GameAPI.Port_User);
						ApplicationData.temporaryAccountName = accountName;
						ApplicationData.temporaryPwd = pwd;
					}
				});
	}

	@Override
	public BaseActivity getGameContext() {
		return AccountChangeActivity.this;
	}

}
