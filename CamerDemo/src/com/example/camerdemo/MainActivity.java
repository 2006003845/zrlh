package com.example.camerdemo;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.zzl.zl_app.util.FileTools;
import com.zzl.zl_app.util.ImageUtils;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		imageUri = Uri.fromFile(new File(IMAGE_FILE_LOCATION));
		img = (ImageView) this.findViewById(R.id.imgv);
		this.findViewById(R.id.btn).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showPicDialog();
			}
		});
	}

	ImageView img;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void showPicDialog() {
		AlertDialog dialog = new AlertDialog.Builder(this).setItems(
				R.array.picFrom, picListener).create();
		dialog.show();
	}

	private static final int PIC_TAKE_PHONE = 1;
	private static final int PIC_ALBUM = 2;
	private Uri imageUri;
	private static String IMAGE_FILE_LOCATION = Environment
			.getExternalStorageDirectory().getAbsolutePath() + "/temp.jpg";
	private DialogInterface.OnClickListener picListener = new DialogInterface.OnClickListener() {

		@Override
		public void onClick(DialogInterface dialog, int which) {
			if (which == 0) {
				try {
					FileTools.deleteFile(IMAGE_FILE_LOCATION);
				} catch (IOException e) {

				}
				if (imageUri != null) {
					// capture a bitmap and store it in Uri
					Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
					// if (LocalMemory.getInstance().checkSDCard())
					intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
					startActivityForResult(intent, PIC_TAKE_PHONE);
				}
			} else if (which == 1) {
				Intent intent = new Intent();
				// TypeΪimage
				intent.setType("image/*");
				// Action:ѡ�����Ȼ�󷵻�
				intent.setAction(Intent.ACTION_GET_CONTENT);
				startActivityForResult(intent, PIC_ALBUM);
			}
		}
	};

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {

		case PIC_TAKE_PHONE:
			if (resultCode != RESULT_OK) {
				return;
			}
			/*
			 * if (!LocalMemory.getInstance().checkSDCard()) { Bundle bundle =
			 * data.getExtras(); // ��ȡ���ص���ݣ���ת��ΪͼƬ��ʽ Bitmap bitmap2 = (Bitmap)
			 * bundle.get("data"); if (bitmap2 != null) { Bitmap bm =
			 * ImageUtils.zoomBitmap(bitmap2, 480, 800); sendImgMsg(bm); } }
			 * else
			 */
			if (imageUri != null) {
//				Bitmap bitmap = ImageUtils.compressBimap(IMAGE_FILE_LOCATION);
				Bitmap  bitmap =ImageUtils.getScaleBitmap(imageUri, this);/*getBitmap(this, IMAGE_FILE_LOCATION);*/
				if (bitmap == null) {
					return;
				}
				int degree = ImageUtils.readPictureDegree(IMAGE_FILE_LOCATION);
				if (degree != 0)
					bitmap = ImageUtils.rotaingImageView(degree, bitmap);
				img.setImageBitmap(bitmap);
			}
			break;
		case PIC_ALBUM:// from crop_big_picture
			if (resultCode != RESULT_OK) {
				return;
			}
			Uri originalUri = data.getData(); // ���ͼƬ��uri
			String[] proj = { MediaStore.Images.Media.DATA };
			Cursor cursor = managedQuery(originalUri, proj, null, null, null);
			String path = "";
			if (cursor != null) {
				int column_index = cursor
						.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
				cursor.moveToFirst();
				path = cursor.getString(column_index);
			} else {
				path = originalUri.getPath();
			}
			Bitmap bitmap = ImageUtils.compressBimap(path);
			if (bitmap != null) {
				int degree = ImageUtils.readPictureDegree(path);
				if (degree != 0)
					bitmap = ImageUtils.rotaingImageView(degree, bitmap);
				Bitmap newBm = ImageUtils.zoomBitmap(bitmap, (int) (450),
						(int) (780));
				if (!bitmap.isRecycled())
					bitmap.recycle();
				if (newBm != null) {
					img.setImageBitmap(newBm);
				}
			}
			break;
		}
	}
}
