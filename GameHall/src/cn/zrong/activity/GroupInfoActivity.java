package cn.zrong.activity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import json.JSONException;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import cn.zrong.ApplicationData;
import cn.zrong.GameAPI;
import cn.zrong.activity.GroupCreateActivity.UploadPicThread;
import cn.zrong.activity.base.BaseActivity;
import cn.zrong.connection.Community;
import cn.zrong.connection.Connection;
import cn.zrong.connection.IOWriter;
import cn.zrong.connection.Protocol;
import cn.zrong.connection.Utility;
import cn.zrong.entity.Group;
import cn.zrong.loader.AsyncDataLoader;
import cn.zrong.loader.AsyncDataLoader.Callback;
import cn.zrong.loader.AsyncImageLoader;
import cn.zrong.utils.Tools;

public class GroupInfoActivity extends BaseActivity {
	private Context context;
	public static GroupInfoActivity mInstance;

	public static void launch(Context c, Intent intent) {
		if (intent == null) {
			intent = new Intent();
		}
		intent.setClass(c, GroupInfoActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		c.startActivity(intent);
	}

	private Group group;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;
		mInstance = this;
		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			group = (Group) bundle.getSerializable("group");
		}
		setContentView(R.layout.group_info);
		initView();
	}

	private ImageView groupFormImgV;
	private TextView groupInfoBtn, groupMembsBtn;
	private TextView groupName, groupCreateAt, groupMaster, groupMemberNum;
	private EditText groupNoticeET, groupIntoET;
	private Button saveBtn, dismissBtn;

	private void initView() {
		this.findViewById(R.id.back_btn).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						getGameContext().finish();
					}
				});
		this.findViewById(R.id.group_info_home).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						if (GroupActivity.mInstance != null)
							GroupActivity.mInstance.finish();
						if (GroupSearchActivity.mInstance != null) {
							GroupSearchActivity.mInstance.finish();
						}
						getGameContext().finish();
					}
				});
		saveBtn = (Button) this.findViewById(R.id.group_info_save);
		dismissBtn = (Button) this
				.findViewById(R.id.group_info_btn_dismissgroup);
		groupFormImgV = (ImageView) this.findViewById(R.id.group_info_imgv);
		groupFormImgV.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showPic();
			}
		});
		groupName = (TextView) this.findViewById(R.id.group_info_name);
		groupCreateAt = (TextView) this.findViewById(R.id.group_info_createat);
		groupMaster = (TextView) this.findViewById(R.id.group_info_master);
		groupMemberNum = (TextView) this
				.findViewById(R.id.group_info_membernum);
		groupIntoET = (EditText) this.findViewById(R.id.group_info_intro);
		groupNoticeET = (EditText) this.findViewById(R.id.group_info_notice);

		if (group != null) {
			groupName.setText(group.getgName());
			groupMaster.setText(group.getgAdName());
			groupMemberNum.setText(group.getgSize());
			String headUrl = group.getgImgUrl();
			if (headUrl != null && !headUrl.equals("")) {
				AsyncImageLoader.getInstance().loadPortrait(headUrl, headUrl,
						groupFormImgV);
			}
			if (group.isAdmin()) {
				saveBtn.setVisibility(View.VISIBLE);
				dismissBtn.setVisibility(View.VISIBLE);
				groupIntoET.setFocusable(true);
				groupNoticeET.setFocusable(true);
				setEditTextEditable(groupIntoET, true);
				groupNoticeET.setFocusable(true);
				setEditTextEditable(groupNoticeET, true);
			} else {
				saveBtn.setVisibility(View.GONE);
				dismissBtn.setVisibility(View.GONE);
				groupIntoET.setFocusable(false);
				groupNoticeET.setFocusable(false);
				setEditTextEditable(groupIntoET, false);
				groupNoticeET.setFocusable(false);
				setEditTextEditable(groupNoticeET, false);
			}
			saveBtn.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					group.setgNot(groupNoticeET.getText().toString().trim());
					group.setgExp(groupIntoET.getText().toString().trim());
					new AlterGroupTask(group).execute((Void) null);
				}
			});
			dismissBtn.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO 解散
					showRemoveGroupDiag();
				}
			});
			groupIntoET.setText(group.getgExp());
			groupNoticeET.setText(group.getgNot());
			if (group.getgCreateAt() != null) {
				groupCreateAt.setText(group.getgCreateAt());
			} else {
				new AsyncDataLoader(loadGroupInfoCallback).execute();
			}
		}
	}

	class AlterGroupTask extends AsyncTask<Object, Integer, Boolean> {
		Group group;

		public AlterGroupTask(Group group) {
			this.group = group;
		}

		@Override
		protected Boolean doInBackground(Object... params) {
			while (headPicName == "") {
			}
			try {
				Community comm = Community.getInstance(context);
				if (comm != null) {
					return comm.alterGroup(group, headPicName);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return false;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			if (result) {
				Tools.showToast(context, "修改成功");
				if (GroupActivity.mInstance != null) {
					GroupActivity.mInstance.updateGroup();
				}
				if (HomeActivity.mInstance != null) {
					HomeActivity.mInstance.updateMyGroups();
				}
			} else {
				Tools.showToast(context, "修改失败");
			}
			super.onPostExecute(result);
		}

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
				groupName.setText(group.getgName());
				groupMaster.setText(group.getgAdName());
				groupCreateAt.setText(group.getgCreateAt());
				groupMemberNum.setText(group.getgSize());
				groupIntoET.setText(group.getgExp());
				groupNoticeET.setText(group.getgNot());
				String headUrl = group.getgImgUrl();
				if (headUrl != null && !headUrl.equals("")) {
					AsyncImageLoader.getInstance().loadPortrait(
							group.getgNum(), headUrl, groupFormImgV);
				}
			}
		}
	};

	@Override
	public BaseActivity getGameContext() {
		return this;
	}

	/**
	 * 设置EditText是否可编辑
	 * 
	 * @author com.tiantian
	 * @param editText
	 *            要设置的EditText
	 * @param value
	 *            可编辑:true 不可编辑:false
	 */
	private void setEditTextEditable(EditText editText, boolean value) {
		if (value) {
			editText.setFocusableInTouchMode(true);
			editText.requestFocus();
			InputMethodManager m = (InputMethodManager) context
					.getSystemService(INPUT_METHOD_SERVICE);
			m.toggleSoftInput(0, InputMethodManager.RESULT_SHOWN);
		} else {
			editText.setFocusableInTouchMode(false);
			editText.clearFocus();
		}
	}

	private static final int DIALOG_PIC_SOURCE = 2; // 询问图片来源

	/**
	 * 插入图片（未完成Camera）
	 */
	private void showPic() {
		showDialog(DIALOG_PIC_SOURCE);
	}

	protected Dialog onCreateDialog(int id, Bundle args) {
		switch (id) {
		case DIALOG_PIC_SOURCE:
			return new AlertDialog.Builder(this).setItems(R.array.picFrom,
					picListener).create();
		}
		return super.onCreateDialog(id);
	}

	private String SD_CARD_TEMP_DIR = Environment.getExternalStorageDirectory()
			+ File.separator + "tmpPhoto2.jpg";
	private DialogInterface.OnClickListener picListener = new DialogInterface.OnClickListener() {

		@Override
		public void onClick(DialogInterface dialog, int which) {
			if (which == 0) {
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				intent.putExtra(MediaStore.EXTRA_OUTPUT,
						Uri.fromFile(new File(SD_CARD_TEMP_DIR)));
				startActivityForResult(intent, 0);
			} else if (which == 1) {
				Intent intent = new Intent();
				// Type为image
				intent.setType("image/*");
				// Action:选择数据然后返回
				intent.setAction(Intent.ACTION_GET_CONTENT);
				startActivityForResult(intent, 1);
			}
		}
	};

	private byte[] contentPicByte;
	private Bitmap contentPicBm;

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		try {

			super.onActivityResult(requestCode, resultCode, data);
			switch (requestCode) {
			case 0:
				BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
				bitmapOptions.inSampleSize = 8;
				File file = new File(SD_CARD_TEMP_DIR);
				if (file.exists()) {
					contentPicBm = BitmapFactory.decodeFile(SD_CARD_TEMP_DIR,
							bitmapOptions);
					if (contentPicBm != null) {
						groupFormImgV.setImageBitmap(contentPicBm);
						new UploadPicThread(contentPicBm).start();
					}
				}
				break;

			case 1:
				if (resultCode == RESULT_OK) {
					Uri uri = data.getData();
					try {
						InputStream is = getContentResolver().openInputStream(
								uri);
						ByteArrayOutputStream outputStream = new ByteArrayOutputStream(
								1024);
						byte[] buffer = new byte[1024];
						int lenth;
						while ((lenth = is.read(buffer)) >= 0) {
							outputStream.write(buffer, 0, lenth);
						}
						contentPicByte = outputStream.toByteArray();
						// Drawable drawable = BitmapDrawable.createFromStream(
						// getContentResolver().openInputStream(uri), "");
						contentPicBm = BitmapFactory.decodeByteArray(
								contentPicByte, 0, contentPicByte.length);
						// contentPic.setImageDrawable(drawable);
						if (contentPicBm != null) {
							groupFormImgV.setImageBitmap(contentPicBm);
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
		} catch (Exception e) {
			Log.e("error", e.getMessage());
		}
	}

	private String headPicName = null;

	private String uploadPic(Bitmap bm) {
		bm = Bitmap.createScaledBitmap(bm, 150, 150, false);
		String result = "";
		// 重新读入图片，注意这次要把options.inJustDecodeBounds 设为 false哦
		String imgUrl = context.getResources().getString(R.string.imgUrl);
		try {
			result = Utility.openUrl(this, imgUrl + "?keyID="
					+ ApplicationData.currentUser.getKeyID() + "&type=3",
					"POST", bm);
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
			headPicName = uploadPic(bm);
			Log.i("Loh", "head:" + headPicName);
			super.run();
		}
	}

	private void showRemoveGroupDiag() {

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(android.content.Context.LAYOUT_INFLATER_SERVICE);
		final View layout = inflater.inflate(R.layout.dialog_twice_true, null);
		final AlertDialog dialog = new AlertDialog.Builder(context).create();
		dialog.show();
		dialog.getWindow().setContentView(layout);
		TextView conTV = (TextView) dialog
				.findViewById(R.id.dialog_twice_content);
		conTV.setText("圈子内还有其他成员，您确认要解散" + group.getgName() + "吗?");
		dialog.findViewById(R.id.dialog_twice_ok).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						Connection.sendMessage(
								Protocol.msgType_removeGroup,
								IOWriter.getRemoveGroup(
										Protocol.msgType_removeGroup,
										ApplicationData.currentUser.getKeyID(),
										group.getgId()).getBytes(),
								GameAPI.Port_Weibo);
						dialog.dismiss();
					}
				});
		dialog.findViewById(R.id.dialog_twice_cancel).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						dialog.dismiss();
					}
				});
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mInstance = null;
	}

}
