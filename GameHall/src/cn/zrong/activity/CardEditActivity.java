package cn.zrong.activity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import cn.zrong.ApplicationData;
import cn.zrong.GameAPI;
import cn.zrong.activity.base.BaseActivity;
import cn.zrong.connection.Connection;
import cn.zrong.connection.IOWriter;
import cn.zrong.connection.Protocol;
import cn.zrong.connection.Utility;
import cn.zrong.entity.City;
import cn.zrong.entity.RoleInfo;
import cn.zrong.entity.RoleInfo.Gender;
import cn.zrong.loader.AsyncImageLoader;
import cn.zrong.loader.ImageCache;

public class CardEditActivity extends BaseActivity {
	private Context context;
	public static CardEditActivity mInstance;

	public static void launch(Context c, Intent intent) {
		intent.setClass(c, CardEditActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		c.startActivity(intent);
	}

	private RoleInfo roleInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle b = getIntent().getExtras();
		context = this;
		mInstance = this;

		if (b != null) {
			roleInfo = (RoleInfo) b.getSerializable("roleInfo");
		}
		setContentView(R.layout.card_edit);
		initView();
	}

	private byte[] headPicByte;
	private ImageView headImgV;
	private Bitmap headPicBm;

	private EditText nickNameTV, introTV;
	private Spinner citySpinner;

	private String sex;
	private String imgUrl;
	private String nickName;
	private String intro;
	private City city;

	public ProgressDialog dialog;

	private void initView() {

		if (roleInfo != null) {
			sex = roleInfo.gender.toString();
			nickName = roleInfo.nickName.toString();
			intro = roleInfo.intro.toString();
			city = roleInfo.city;
		}

		this.findViewById(R.id.back_btn).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						getGameContext().finish();
					}
				});
		this.findViewById(R.id.card_edit_save).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						RoleInfo ui = new RoleInfo(ApplicationData.currentUser
								.getUserInfo().index, nickNameTV.getText()
								.toString().trim(), Gender.getGender((sex
								.equals("男") ? 0 : 1) + ""), city, introTV
								.getText().toString().trim(), "", "", "1");
						String str = ui.toString();
						Log.i("Loh", str);
						Connection.sendMessage(
								Protocol.msgType_alertRole,
								IOWriter.getAlterRole(
										Protocol.msgType_alertRole,
										ApplicationData.currentUser.getKeyID(),
										ui).getBytes(), GameAPI.Port_Role);
						dialog = new ProgressDialog(context,
								android.R.style.Widget_ProgressBar_Small);
						// dialog.
						dialog.setCancelable(true);
						dialog.setMessage("发送修改...");
						dialog.show();
					}
				});
		this.findViewById(R.id.card_edit_protraitlayout).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						showDialog(DIALOG_PIC_SOURCE);
					}
				});
		headImgV = (ImageView) this.findViewById(R.id.card_edit_headportrait);
		if (roleInfo.headerUrl != null && !roleInfo.headerUrl.equals("")) {
			// AsyncImageLoader.getInstance().loadPortrait(roleInfo.headerUrl,
			// roleInfo.headerUrl, headImgV);
			ImageCache.getInstance().loadImg(context, roleInfo.headerUrl, headImgV);

		}
		RadioGroup group = (RadioGroup) this
				.findViewById(R.id.card_edit_radiogroup);
		RadioButton nanrb = (RadioButton) getGameContext().findViewById(
				R.id.card_edit_radio_nan);
		RadioButton nvrb = (RadioButton) getGameContext().findViewById(
				R.id.card_edit_radio_nv);
		if (sex != null && sex.equals("男")) {
			nanrb.setChecked(true);
		} else {
			nvrb.setChecked(true);
		}

		group.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup arg0, int arg1) {
				// 获取变更后的选中项的ID
				int radioButtonId = arg0.getCheckedRadioButtonId();
				// 根据ID获取RadioButton的实例
				RadioButton rb = (RadioButton) getGameContext().findViewById(
						radioButtonId);
				sex = rb.getText().toString().trim();
			}
		});
		nickNameTV = (EditText) this.findViewById(R.id.card_card_nickname_tv);
		if (nickName != null) {
			nickNameTV.setText(nickName);
		}

		citySpinner = (Spinner) this.findViewById(R.id.card_edit_location_sp);
		ArrayAdapter<City> adapter = new ArrayAdapter<City>(context,
				android.R.layout.simple_spinner_item, ApplicationData.cityList);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		citySpinner.setAdapter(adapter);
		if (roleInfo != null && roleInfo.city != null) {
			citySpinner.setSelection(getCityPosition(roleInfo.city));
		}
		citySpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				city = (City) parent.getItemAtPosition(position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
			}
		});

		introTV = (EditText) this.findViewById(R.id.card_edit_intro_tv);
		if (intro != null) {
			introTV.setText(intro);
		}

	}

	private int getCityPosition(City city) {
		int position = 0;
		String name = city.name;
		for (int i = 0, len = ApplicationData.cityList.size(); i < len; i++) {
			City c = ApplicationData.cityList.get(i);
			if ((c.name).equals(name)) {
				position = i;
				break;
			}
		}
		return position;
	}

	public static final int Request_TAKEPHOTO = 0;
	public static final int Request_ALBUM = 1;
	public static final int Request_NICKNAME = 2;
	public static final int Request_INTRO = 3;

	private String SD_CARD_TEMP_DIR = Environment.getExternalStorageDirectory()
			+ File.separator + "tmpPhoto1.jpg";

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		try {
			super.onActivityResult(requestCode, resultCode, data);
			switch (requestCode) {
			case Request_TAKEPHOTO:
				BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
				bitmapOptions.inSampleSize = 8;
				File file = new File(SD_CARD_TEMP_DIR);
				if (file.exists()) {
					headPicBm = BitmapFactory.decodeFile(SD_CARD_TEMP_DIR,
							bitmapOptions);
					headImgV.setImageBitmap(headPicBm);
				}
				break;

			case Request_ALBUM:
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
						headPicByte = outputStream.toByteArray();
						// Drawable drawable = BitmapDrawable.createFromStream(
						// getheadResolver().openInputStream(uri), "");
						headPicBm = BitmapFactory.decodeByteArray(headPicByte,
								0, headPicByte.length);
						// headPic.setImageDrawable(drawable);
						if (headPicBm != null) {
							// 上传头像
							new UploadPicThread(headPicBm).start();
							headImgV.setImageBitmap(headPicBm);
						}

					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						Log.e("error", e.getMessage());
					}
				}

				break;
			}
		} catch (Exception e) {
			Log.e("error", e.getMessage());
		}
	}

	private static final int DIALOG_PIC_SOURCE = 2; // 图片来源

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DIALOG_PIC_SOURCE:
			return new AlertDialog.Builder(this).setItems(R.array.picFrom,
					picListener).create();
		}
		return super.onCreateDialog(id);
	}

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

	private String headPicName = null;

	private String uploadPic(Bitmap bm) {
		bm = Bitmap.createScaledBitmap(bm, 80, 80, false);
		String result = "";
		// 重新读入图片，注意这次要把options.inJustDecodeBounds 设为 false哦
		String imgUrl = context.getResources().getString(R.string.imgUrl);
		try {
			result = Utility.openUrl(this, imgUrl + "?keyID="
					+ ApplicationData.currentUser.getKeyID() + "&type=2",
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

	@Override
	public BaseActivity getGameContext() {
		return this;
	}

}
