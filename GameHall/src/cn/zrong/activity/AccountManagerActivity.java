package cn.zrong.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.zrong.ApplicationData;
import cn.zrong.GameAPI;
import cn.zrong.activity.base.BaseActivity;
import cn.zrong.connection.Connection;
import cn.zrong.connection.IOWriter;
import cn.zrong.connection.Protocol;
import cn.zrong.loader.AsyncImageLoader;

public class AccountManagerActivity extends BaseActivity {

	public static AccountManagerActivity mInstance;

	public ProgressDialog dialog;

	private Context context;
	private SharedPreferences prefs;
	private static final String FIRST_Edit_PREFERENCE = "first_edit";
	private boolean isFirstEdit = false;
	private boolean isEditState = false;

	public static void launch(Context c) {
		Intent intent = new Intent(c, AccountManagerActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		c.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;
		mInstance = this;
		isFirstEdit = PreferenceManager.getDefaultSharedPreferences(this)
				.getBoolean(FIRST_Edit_PREFERENCE, true);
		prefs = PreferenceManager.getDefaultSharedPreferences(this);

		setContentView(R.layout.account_manage);
		initView();
	}

	private ImageView user_imgv;
	private TextView user_nickname_tv;
	private EditText account_et, account_new_et, psd_et, psd_new_et,
			psd_new2_et;
	private RelativeLayout changeAccountLayout;
	private LinearLayout newAccountLayout;
	private LinearLayout psdLayout;
	private Button commitBtn;

	private void initView() {

		this.findViewById(R.id.back_btn).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						getGameContext().finish();
					}
				});

		user_imgv = (ImageView) this.findViewById(R.id.account_manage_imgv);
		AsyncImageLoader.getInstance().loadPortrait(
				ApplicationData.currentUser.getUserInfo().headerUrl,
				ApplicationData.currentUser.getUserInfo().headerUrl, user_imgv);
		user_nickname_tv = (TextView) this
				.findViewById(R.id.account_manage_nickname);
		user_nickname_tv
				.setText(ApplicationData.currentUser.getUserInfo().nickName);
		account_et = (EditText) this
				.findViewById(R.id.account_manage_account_et);
		account_et.setText(ApplicationData.currentUser.getU_name());
		account_new_et = (EditText) this
				.findViewById(R.id.account_manage_account_et2);
		psd_et = (EditText) this.findViewById(R.id.account_manage_psd_et);
		psd_new_et = (EditText) this.findViewById(R.id.account_manage_psd1_et);
		psd_new2_et = (EditText) this.findViewById(R.id.account_manage_psd2_et);
		newAccountLayout = (LinearLayout) this
				.findViewById(R.id.account_manage_newaccount_layout);
		psdLayout = (LinearLayout) this
				.findViewById(R.id.account_manage_psd_layout);
		this.findViewById(R.id.account_manage_setting_btn).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO
						if (!isEditState) {
							isEditState = true;
							// if (isFirstEdit) {
							// newAccountLayout.setVisibility(View.VISIBLE);
							// psdLayout.setVisibility(View.VISIBLE);
							// } else {
							newAccountLayout.setVisibility(View.GONE);
							psdLayout.setVisibility(View.VISIBLE);
							// }
							commitBtn.setVisibility(View.VISIBLE);
						} else {
							isEditState = false;
							newAccountLayout.setVisibility(View.GONE);
							psdLayout.setVisibility(View.GONE);
							commitBtn.setVisibility(View.GONE);
						}
					}
				});

		commitBtn = (Button) this.findViewById(R.id.account_manage_commit);
		commitBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String psd_orig = psd_et.getText().toString().trim();
				String psd1 = psd_new_et.getText().toString().trim();
				String psd2 = psd_new2_et.getText().toString().trim();

				if (psd1 == null || psd1.equals("") || psd2 == null
						|| psd2.equals("")) {
					Toast.makeText(context, "输入新密码不能为空", 1000).show();
					return;
				}
				if (!psd1.equals(psd2)) {
					Toast.makeText(context, "两次输入密码不同", 1000).show();
					return;
				}

				dialog = new ProgressDialog(context,
						android.R.style.Widget_ProgressBar_Small);
				// dialog.
				dialog.setMessage("提交中...");
				dialog.show();
				// TODO 提交
				// if (isFirstEdit) {
				// String accountName = account_new_et.getText().toString()
				// .trim();
				// if (accountName == null || accountName.equals("")) {
				// Toast.makeText(context, "新账号不能为空", 1000).show();
				// return;
				// }
				//
				// // 更改用户名跟密码
				// Connection.sendMessage(
				// Protocol.msgType_changeAccountAndPsd,
				// IOWriter.getSetAccountAndPsdRequest(
				// Protocol.msgType_changeAccountAndPsd,
				// ApplicationData.currentUser.getKeyID(),
				// accountName, psd2).getBytes(),
				// GameAPI.Port_User);
				// prefs.edit().putBoolean(FIRST_Edit_PREFERENCE, false)
				// .commit();
				// ApplicationData.temporaryAccountName = accountName;
				// ApplicationData.temporaryPwd = psd2;
				// }
				// // 修改密码
				// else {
				if (psd_orig == null || psd_orig.equals("")) {
					Toast.makeText(context, "输入原始密码不能为空", 1000).show();
					return;
				}

				Connection.sendMessage(
						Protocol.msgType_setPsd,
						IOWriter.getSetPsdRequest(Protocol.msgType_setPsd,
								ApplicationData.currentUser.getKeyID(),
								ApplicationData.currentUser.getU_psd(), psd2)
								.getBytes(), GameAPI.Port_User);
				ApplicationData.temporaryPwd = psd2;
			}
			// }
		});
		this.findViewById(R.id.account_manage_changeaccount)
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO
						AccountChangeActivity.launch(context);
					}
				});
	}

	@Override
	public BaseActivity getGameContext() {
		return AccountManagerActivity.this;
	}

}
