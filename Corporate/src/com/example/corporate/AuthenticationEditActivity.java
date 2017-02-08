package com.example.corporate;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.json.JSONException;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.corporate.base.BaseActivity;
import com.example.corporate.base.MyToast;
import com.zzl.zl_app.cache.FileConstant;
import com.zzl.zl_app.connection.Community;

public class AuthenticationEditActivity extends BaseActivity {
	public static final String Tag = "auth_edit";

	public static void launch(Context c, Intent intent) {
		if (intent == null) {
			intent = new Intent();
		}
		intent.setClass(c, AuthenticationEditActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		c.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addActMap(Tag, getContext());
		setContentView(R.layout.authentication_edit);
		initView();
	}

	private TextView titleTV;

	private EditText nameET, phoneET, addrET, introET;
	private TextView leftTV;

	private ImageView licenseImgV;

	private void initView() {
		this.findViewById(R.id.back).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				closeOneAct(Tag);
			}
		});
		titleTV = (TextView) this.findViewById(R.id.title_tv);
		titleTV.setText(R.string.fillin_auth_info);
		this.findViewById(R.id.btn).setVisibility(View.GONE);
		nameET = (EditText) this.findViewById(R.id.auth_edit_et_name);
		phoneET = (EditText) this.findViewById(R.id.auth_edit_et_phone);
		addrET = (EditText) this.findViewById(R.id.auth_edit_et_addr);
		introET = (EditText) this.findViewById(R.id.auth_edit_et_intro);
		leftTV = (TextView) this.findViewById(R.id.auth_edit_tv_left);
		licenseImgV = (ImageView) this
				.findViewById(R.id.auth_edit_imgv_license);
		this.findViewById(R.id.auth_edit_btn_commit).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						AuthenticationResultActivity.launch(getContext(), getIntent());
						// TODO
						String name = nameET.getText().toString();
						String phone = phoneET.getText().toString();
						String addr = addrET.getText().toString();
						String intro = introET.getText().toString();
						if (name == null || name.equals("")) {
							MyToast.getToast().showToast(getContext(),
									"单位名称不能为空");
							return;
						}
						if (phone == null || phone.equals("")) {
							MyToast.getToast().showToast(getContext(),
									"单位电话不能为空");
							return;
						}
						if (addr == null || addr.equals("")) {
							MyToast.getToast().showToast(getContext(),
									"单位地址不能为空");
							return;
						}
						if (intro == null || intro.equals("")) {
							MyToast.getToast().showToast(getContext(),
									"单位介绍不能为空");
							return;
						}

//						if (lisence == null) {
//							MyToast.getToast().showToast(getContext(),
//									"请上传营业执照");
//							return;
//						}

						new CommitAuthTask(name, phone, addr, intro).execute();
					}
				});
		this.findViewById(R.id.auth_edit_layout_license).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO
						showPicDialog();
					}
				});
		introET.addTextChangedListener(new TextWatcher() {
			String text = "";

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				int size = introET.getText().length();
				if (size == 100) {
					text = introET.getText().toString().trim();
				}
				if (size > 100) {
					introET.setText(text);
				}
				leftTV.setText("" + (100 - size));
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});

	}

	private void showPicDialog() {
		AlertDialog dialog = new AlertDialog.Builder(getContext()).setItems(
				R.array.picFrom, picListener).create();
		dialog.show();
	}

	private Handler handler = new Handler();
	private static String IMAGE_FILE_LOCATION = FileConstant.savePath
			+ "/temp.jpg";
	private byte[] contentPicByte;
	private Bitmap contentPicBm;
	private DialogInterface.OnClickListener picListener = new DialogInterface.OnClickListener() {

		@Override
		public void onClick(DialogInterface dialog, int which) {
			if (which == 0) {
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				intent.putExtra(MediaStore.EXTRA_OUTPUT,
						Uri.fromFile(new File(IMAGE_FILE_LOCATION)));
				getContext().startActivityForResult(intent, 0);
			} else if (which == 1) {
				Intent intent = new Intent();
				// Type为image
				intent.setType("image/*");
				// Action:选择数据然后返回
				intent.setAction(Intent.ACTION_GET_CONTENT);
				getContext().startActivityForResult(intent, 1);
			}
		}
	};

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case 0:
			BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
			// bitmapOptions.inSampleSize = 8;
			bitmapOptions.inJustDecodeBounds = true;
			// 获取这个图片的宽和高
			Bitmap bitmap = BitmapFactory.decodeFile(IMAGE_FILE_LOCATION,
					bitmapOptions);// 此时返回bm为空
			bitmapOptions.inJustDecodeBounds = false;
			// 计算缩放比
			int be = (int) (bitmapOptions.outHeight / (float) 500);
			if (be <= 0)
				be = 1;
			bitmapOptions.inSampleSize = be;

			File file = new File(IMAGE_FILE_LOCATION);
			long len = file.length();
			if (file.exists()) {
				contentPicBm = BitmapFactory.decodeFile(IMAGE_FILE_LOCATION,
						bitmapOptions);
				// TODO 设置headview中的图片

				if (contentPicBm != null) {
					licenseImgV.setVisibility(View.VISIBLE);
					licenseImgV.setImageBitmap(contentPicBm);
					new CommitLisenceTask(contentPicBm).execute();
				}
			}
			break;

		case 1:
			if (resultCode == RESULT_OK) {
				final Uri uri = data.getData();

				new Thread(new Runnable() {

					@Override
					public void run() {
						InputStream is = null;
						ByteArrayOutputStream outputStream = null;
						try {
							is = getContentResolver().openInputStream(uri);
							outputStream = new ByteArrayOutputStream(1024);
							byte[] buffer = new byte[1024];
							int lenth;
							while ((lenth = is.read(buffer)) >= 0) {
								outputStream.write(buffer, 0, lenth);
							}
							try {
								contentPicByte = outputStream.toByteArray();
								contentPicBm = BitmapFactory.decodeByteArray(
										contentPicByte, 0,
										contentPicByte.length);
								contentPicBm.compress(CompressFormat.PNG, 75,
										outputStream);
								int w = getContext().getWindowManager()
										.getDefaultDisplay().getWidth();
								int h = (int) (contentPicBm.getHeight() * ((w + 0.0) / contentPicBm
										.getWidth()));
								contentPicBm = Bitmap.createScaledBitmap(
										contentPicBm, w, h, false);
							} catch (OutOfMemoryError err) {

							}
							// TODO 设置headview中的图片
							if (contentPicBm != null) {
								handler.post(new Runnable() {

									@Override
									public void run() {
										licenseImgV.setVisibility(View.VISIBLE);
										licenseImgV
												.setImageBitmap(contentPicBm);
										new CommitLisenceTask(contentPicBm)
												.execute();
									}
								});
								int bitWidth = contentPicBm.getWidth();
								int bitHeight = contentPicBm.getHeight();
								Bitmap bm = Bitmap
										.createScaledBitmap(
												contentPicBm,
												1000,
												(int) (bitHeight * ((1000 + 0.0) / bitWidth)),
												false);
							}
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						} finally {
							try {
								is.close();
								outputStream.close();
							} catch (IOException e) {
								e.printStackTrace();
							}

						}
					}
				}).start();

			}
			break;
		}
	}

	private String lisence = null;

	class CommitLisenceTask extends AsyncTask<Object, Integer, String> {

		Bitmap bm;

		public CommitLisenceTask(Bitmap bm) {
			this.bm = bm;

		}

		ProgressDialog dialog = null;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = new ProgressDialog(getContext());
			dialog.setCancelable(false);
			dialog.setMessage("上传中...");
			dialog.show();
		}

		@Override
		protected String doInBackground(Object... params) {
			// TODO
			try {
				return Community.getInstance(getContext()).companyImgOp(
						getContext(), PlatformAPI.Name, PlatformAPI.Pwd, "2",
						bm);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			setProgressBarIndeterminateVisibility(false);
			dialog.dismiss();
			if (result != null) {
				lisence = result;
				MyToast.getToast().showToast(getContext(), "上传成功");
			} else {
				MyToast.getToast().showToast(getContext(), "上传失败,请重新上传");
			}
			super.onPostExecute(result);
		}
	}

	class CommitAuthTask extends AsyncTask<Object, Integer, Boolean> {

		String name;
		String phone;
		String addr;
		String intro;

		public CommitAuthTask(String name, String phone, String addr,
				String intro) {
			super();
			this.name = name;
			this.phone = phone;
			this.addr = addr;
			this.intro = intro;
		}

		ProgressDialog dialog = null;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = new ProgressDialog(getContext());
			dialog.setCancelable(false);
			dialog.setMessage("提交中,请稍后");
			dialog.show();
		}

		@Override
		protected Boolean doInBackground(Object... params) {
			try {
				return Community.getInstance(getContext()).companyIdent(
						PlatformAPI.Name, PlatformAPI.Pwd, name, phone, addr,
						intro);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return false;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			setProgressBarIndeterminateVisibility(false);
			dialog.dismiss();
			if (result) {
				AuthenticationResultActivity.launch(getContext(), getIntent());
			} else {
				MyToast.getToast().showToast(getContext(), "提交失败,请重试");
			}
			
			super.onPostExecute(result);
		}
	}

	@Override
	public BaseActivity getContext() {
		return this;
	}

	@Override
	public void setDialogContent(AlertDialog dialog, int layoutId, String msg) {
	}

	@Override
	public void setDialogTitle(AlertDialog dialog, int layoutId, String title) {
	}

	@Override
	public OnClickListener setPositiveClickListener(AlertDialog dialog,
			int layoutId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OnClickListener setNegativeClickListener(AlertDialog dialog,
			int layoutId) {
		// TODO Auto-generated method stub
		return null;
	}

}
