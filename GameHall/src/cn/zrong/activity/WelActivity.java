package cn.zrong.activity;

import jtapp.updateapksamples.GagaConfig;
import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import cn.zrong.ApplicationData;
import cn.zrong.GameAPI;
import cn.zrong.activity.base.BaseActivity;
import cn.zrong.connection.Community;
import cn.zrong.connection.Connection;
import cn.zrong.connection.IOWriter;
import cn.zrong.connection.Protocol;
import cn.zrong.db.HallDB;
import cn.zrong.entity.User;
import cn.zrong.handler.MessageHandler;
import cn.zrong.io.HttpThreadManager;
import cn.zrong.utils.ImageUtils;

public class WelActivity extends BaseActivity {
	private Context context;
	private HallDB hallDB;
	public static WelActivity mIntance;
	public static boolean isOpen = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wel);
		String[] gagaUrls = GagaConfig.getSwitch(this,
				"http://game.91juhe.com/PassSystem/upDate/", "gagaSever.json");
		Community.initHandler();
		if (gagaUrls != null) {
			GameAPI.userUrl = gagaUrls[0];
			GameAPI.weiboUrl = gagaUrls[1];
			GameAPI.roleUrl = gagaUrls[2];
			GameAPI.gameUrl = gagaUrls[3];
			// if (gagaUrls[4] != null) {
			// // GameAPI.gameSwitch = gagaUrls[4].toCharArray();
			// GameAPI.gameSwitch = new char[] { '0', '0', '0', '0', '0', '0' };
			// } else {
			// GameAPI.gameSwitch = new char[] { '1', '1', '1', '1', '1', '1' };
			// }
			GameAPI.gameSwitch = new char[] { '0', '0', '0', '0', '0', '0' };
			GameAPI.init2(getResources());
			String vercode = "";
			// try {
			// vercode = String
			// .valueOf(this.getPackageManager().getPackageInfo(
			// getApplication().getPackageName(), 0).versionCode);
			// } catch (NameNotFoundException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			if (gagaUrls[5] != null && gagaUrls[6] != null) {

				String gamechannle[] = gagaUrls[5].split(",");
				String gameVercode[] = gagaUrls[6].split(",");

				boolean b1 = false;
				boolean b2 = false;
				for (int i = 0; i < gameVercode.length; i++) {
					if (gameVercode[i].equals(vercode)) {
						b1 = true;
						break;
					}
				}
				String channel = this.getResources()
						.getString(R.string.channel);
				for (int i = 0; i < gamechannle.length; i++) {
					if (gamechannle[i].equals(channel)) {
						b2 = true;
						break;
					}
				}
				isOpen = b1 & b2;
			}
		} else {

			GameAPI.init(getResources());
			GameAPI.gameSwitch = new char[] { '0', '0', '0', '0', '0', '0' };
		}
		isOpen = true;
		context = this;
		Looper looper = Looper.getMainLooper();
		new MessageHandler(looper, context);
		GameAPI.init2(this.getResources());
		HttpThreadManager.sharedManager(this);

		if (GameAPI.gameSwitch[0] == '0' && isOpen) {

			setContentView(R.layout.wel);

			RelativeLayout layout = (RelativeLayout) this
					.findViewById(R.id.wel_bg);

			layout.setBackgroundResource(R.drawable.bg_wel);

			ImageView imageView = (ImageView) this.findViewById(R.id.game_icon);

			if (GameAPI.gameSwitch[5] == '1') {
				imageView.setVisibility(View.INVISIBLE);
			}

		} else {
			setContentView(R.layout.wel2);

			ImageView image = (ImageView) this.findViewById(R.id.wel2back);

			Bitmap bm = ImageUtils.getBitmap(context, "wel.png");

			if (bm != null) {
				image.setBackgroundDrawable(new BitmapDrawable(bm));
			} else {
				image.setBackgroundResource(R.drawable.bg_wel);
			}
		}

		mIntance = this;

		new CheckNetConnectionTask().execute((Void) null);
	}

	@Override
	public BaseActivity getGameContext() {
		return this;
	}

	class CheckNetConnectionTask extends AsyncTask<Object, Integer, Boolean> {
		ProgressDialog dialog = null;

		@Override
		protected void onPreExecute() {
			dialog = new ProgressDialog(context);
			dialog.setCancelable(true);
			dialog.setMessage("检查网络连接...");
			dialog.show();
			super.onPreExecute();
		}

		@Override
		protected Boolean doInBackground(Object... params) {
			boolean bool = checkNetWorkStatus(context);
			return bool;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			setProgressBarIndeterminateVisibility(false);
			dialog.dismiss();
			if (!result) {
				Toast.makeText(context, "网络连接有误,请退出后 检查网络重新登录",
						Toast.LENGTH_LONG).show();
			} else {
				Toast.makeText(context, "网络连接良好", Toast.LENGTH_LONG).show();
				loginOrRegist();
			}
			super.onPostExecute(result);
		}
	}

	public ProgressDialog dialog;

	private boolean checkNetWorkStatus(Context context) {
		boolean result;
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netinfo = cm.getActiveNetworkInfo();
		if (netinfo != null && netinfo.isConnected()) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}

	private String devievid;

	public void loginOrRegist() {
		HomeActivity.launch(context, getIntent());
		dialog = new ProgressDialog(this);
		// dialog.setCancelable(false);
		dialog.show();
		// 获取手机序列号
		TelephonyManager tm = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		devievid = tm.getDeviceId();
		// 获取用户名跟密码
		hallDB = HallDB.getHallDBInstance(context);
		Cursor cursor = hallDB.query(User.UserTable.TAB_NAME, null, null, null,
				null, null, null);
//		ApplicationData.currentUser = User.UserTable.getUser(cursor);
		// 快速注册
		if (ApplicationData.currentUser == null) {
			fastRegist();

		}// 登录
		else {
			login(ApplicationData.currentUser.getU_name(),
					ApplicationData.currentUser.getU_psd());
			// Toast.makeText(context, "登录中...", 1000).show();
		}
		dialog.setMessage("登录中...");
		dialog.show();
	}

	private void fastRegist() {
		String str = IOWriter.getFastRegist(Protocol.msgType_fastRegist,
				GameAPI.userApp, GameAPI.channel, GameAPI.platform, "",
				GameAPI.iMobile);
		Connection.sendMessage(Protocol.msgType_fastRegist, str.getBytes(),
				GameAPI.Port_User);
	}

	private void login(String userName, String password) {
		Connection.sendMessage(
				Protocol.msgType_login,
				IOWriter.getLogin(Protocol.msgType_login, userName, password,
						GameAPI.userApp, GameAPI.channel, GameAPI.platform, "",
						0 + "", GameAPI.iMobile).getBytes(), GameAPI.Port_User);
	}

}
