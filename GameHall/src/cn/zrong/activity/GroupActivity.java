package cn.zrong.activity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import json.JSONException;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import cn.zrong.ApplicationData;
import cn.zrong.activity.base.BaseActivity;
import cn.zrong.adapter.TalkItemAdapter;
import cn.zrong.connection.Community;
import cn.zrong.connection.Utility;
import cn.zrong.entity.Group;
import cn.zrong.entity.Status;
import cn.zrong.loader.AsyncDataLoader;
import cn.zrong.loader.AsyncDataLoader.Callback;
import cn.zrong.widget.HeadListView;

public class GroupActivity extends BaseActivity {
	private Context context;
	public static GroupActivity mInstance;

	public static void launch(Context c, Intent intent) {
		if (intent == null) {
			intent = new Intent();
		}
		intent.setClass(c, GroupActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		c.startActivity(intent);
	}

	private String index;
	private Group group;
	boolean isAdmin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;
		mInstance = this;
		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			group = (Group) bundle.getSerializable("group");
			isAdmin = group.isAdmin();
		}
		setContentView(R.layout.group);
		initView();
	}

	private TextView titleTV;
	private HeadListView headListV;
	private TalkItemAdapter gmsgItemAdapter;
	private List<Status> talkList = new ArrayList<Status>();

	private void initView() {
		this.findViewById(R.id.back_btn).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						getGameContext().finish();
					}
				});
		titleTV = (TextView) this.findViewById(R.id.group_name_tv);
		if (group != null) {
			titleTV.setText(group.getgName());
		}
		headListV = (HeadListView) this.findViewById(R.id.group_headlistv);
		headListV.init(context, group);
		gmsgItemAdapter = new TalkItemAdapter(context, talkList);
		headListV.setAdapter(gmsgItemAdapter);
		updateGroup();
	}

	public void updateGroup() {
		new AsyncDataLoader(loadGroupWeibosCallback).execute();
		new AsyncDataLoader(loadGroupInfoCallback).execute();
	}

	private Callback loadGroupInfoCallback = new Callback() {
		ProgressDialog dialog;

		@Override
		public void onStart() {
			try {
				if (group.getgNum() != null) {
					Community comm = Community.getInstance(context);
					if (comm != null) {
						group = comm.getGroupInfo(group.getgNum());
					}
				}

			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void onPrepare() {
			dialog = new ProgressDialog(context,
					android.R.style.Widget_ProgressBar_Small);
			dialog.setCancelable(false);
			dialog.setMessage("加载...");
			dialog.show();
		}

		@Override
		public void onFinish() {
			setProgressBarIndeterminateVisibility(false);
			dialog.dismiss();
			if (group != null) {
				group.haveJoin = 2;
				group.setAdmin(isAdmin);
			}
			headListV.updateGroupInfo(group);
		}
	};

	private Callback loadGroupWeibosCallback = new Callback() {
		ProgressDialog dialog;

		@Override
		public void onStart() {
			try {
				Community comm = Community.getInstance(context);
				if (comm != null) {
					List<Status> list = comm.getGroupStatusList(
							group.getgNum(), 1, 20);

					if (list != null) {
						talkList.clear();
						talkList.addAll(list);
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void onPrepare() {
			dialog = new ProgressDialog(context,
					android.R.style.Widget_ProgressBar_Small);
			dialog.setCancelable(true);
			dialog.setMessage("加载...");
			dialog.show();
		}

		@Override
		public void onFinish() {
			setProgressBarIndeterminateVisibility(false);
			dialog.dismiss();
			gmsgItemAdapter.notifyDataSetChanged();

		}
	};

	private byte[] contentPicByte;
	private Bitmap contentPicBm;
	public static String SD_CARD_TEMP_DIR = Environment
			.getExternalStorageDirectory() + File.separator + "tmpPhoto1.jpg";

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case 0:
			BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
			bitmapOptions.inSampleSize = 8;
			File file = new File(SD_CARD_TEMP_DIR);
			if (file.exists()) {
				contentPicBm = BitmapFactory.decodeFile(SD_CARD_TEMP_DIR,
						bitmapOptions);
				// TODO 设置headview中的图片
				if (contentPicBm != null) {
					headListV.setTalkPic(contentPicBm);
					new UploadPicThread(contentPicBm).start();
				}
			}
			break;

		case 1:
			if (resultCode == RESULT_OK) {
				Uri uri = data.getData();
				try {
					InputStream is = getContentResolver().openInputStream(uri);
					ByteArrayOutputStream outputStream = new ByteArrayOutputStream(
							1024);
					byte[] buffer = new byte[1024];
					int lenth;
					while ((lenth = is.read(buffer)) >= 0) {
						outputStream.write(buffer, 0, lenth);
					}
					contentPicByte = outputStream.toByteArray();
					contentPicBm = BitmapFactory.decodeByteArray(
							contentPicByte, 0, contentPicByte.length);
					// TODO 设置headview中的图片
					if (contentPicBm != null) {
						headListV.setTalkPic(contentPicBm);
						new UploadPicThread(contentPicBm).start();
					}

				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			break;
		}
	}

	public static String PicName = null;

	private String uploadPic(Bitmap bm) {
		bm = Bitmap.createScaledBitmap(bm, 200, 200, false);
		String result = "";
		// 重新读入图片，注意这次要把options.inJustDecodeBounds 设为 false哦
		try {
			result = Utility.openUrl(context,
					"http://192.168.0.220/PassSystem/upImg.php?keyID="
							+ ApplicationData.currentUser.getKeyID()
							+ "&type=1", "POST", bm);
			Log.i("Loh", "result:" + result);
		} catch (Exception e) {
			Log.e("Loh", e.getMessage());
		}
		return result;
	}

	class UploadPicThread extends Thread {
		Bitmap bm;

		public UploadPicThread(Bitmap bm) {
			this.bm = bm;
		}

		@Override
		public void run() {
			PicName = uploadPic(bm);
			Log.i("Loh", "head:" + PicName);
			super.run();
		}
	}

	@Override
	public BaseActivity getGameContext() {
		return this;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mInstance = null;
	}

}
