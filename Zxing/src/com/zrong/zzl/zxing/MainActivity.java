package com.zrong.zzl.zxing;

import java.util.Hashtable;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class MainActivity extends Activity {

	ImageView tdImgV;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tdImgV = (ImageView) this.findViewById(R.id.td_imgv);
		this.findViewById(R.id.btn_create).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						createImage();
					}
				});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public int QR_WIDTH = 300;
	public int QR_HEIGHT = 300;

	// ����QRͼ
	private void createImage() {
		try {
			// ��Ҫ����core��
			QRCodeWriter writer = new QRCodeWriter();

			String text = "����һֻ�򵥵�Сǿ";

			if (text == null || "".equals(text) || text.length() < 1) {
				return;	
			}
			// ��������ı�תΪ��ά��
			BitMatrix martix = writer.encode(text, BarcodeFormat.QR_CODE,
					QR_WIDTH, QR_HEIGHT);

			System.out.println("w:" + martix.getWidth() + "h:"
					+ martix.getHeight());

			Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
			hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
			BitMatrix bitMatrix = new QRCodeWriter().encode(text,
					BarcodeFormat.QR_CODE, QR_WIDTH, QR_HEIGHT, hints);
			int[] pixels = new int[QR_WIDTH * QR_HEIGHT];
			for (int y = 0; y < QR_HEIGHT; y++) {
				for (int x = 0; x < QR_WIDTH; x++) {
					if (bitMatrix.get(x, y)) {
						pixels[y * QR_WIDTH + x] = 0xff000000;
					} else {
						pixels[y * QR_WIDTH + x] = 0xffffffff;
					}
				}
			}

			Bitmap bitmap = Bitmap.createBitmap(QR_WIDTH, QR_HEIGHT,
					Bitmap.Config.ARGB_8888);

			bitmap.setPixels(pixels, 0, QR_WIDTH, 0, 0, QR_WIDTH, QR_HEIGHT);
			tdImgV.setImageBitmap(bitmap);

		} catch (WriterException e) {
			e.printStackTrace();
		}
	}

}
