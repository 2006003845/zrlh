package com.zrong.Android.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.zrong.Android.Util.ImageDownloadTask;
import com.zrong.Android.Util.Music;
import com.zrong.Android.View.FreshManLead;
import com.zrong.Android.element.Employee;
import com.zrong.Android.element.MapBuilding;
import com.zrong.Android.element.Shop;
import com.zrong.Android.game.Connection;
import com.zrong.Android.game.ConstructData;
import com.zrong.Android.game.GameData;
import com.zrong.Android.online.network.GameProtocol;

public class DoctorShopinfoActivity extends GameActivity{
	
	public static DoctorShopinfoActivity mContext = null;
	private TextView text_desc,text_condition,text_name;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		Bundle bundle = this.getIntent().getExtras();

		setContentView(R.layout.doctor_shopinfo);	
		int resId = 0;
		ImageView imageview = (ImageView) this
				.findViewById(R.id.shopinfo_shopImageview);
		//imageview.setImageBitmap( BitmapUtil.getBitmap(resId,0,(float)1));
		new ImageDownloadTask().execute(imageview, GameData.shopRes[DoctorShopActivity.ID], 811);
		text_desc = (TextView)this.findViewById(R.id.shopinfo_desc_text);
		text_condition = (TextView)this.findViewById(R.id.shopinfo_point_text);
		text_name = (TextView)this.findViewById(R.id.shopinfo_name_text);
		
		//String a =GameData.shopName[1];		
		text_name.setText(GameData.shopName[DoctorShopActivity.ID]);
		text_desc.setText(GameData.shopTemplate_info[DoctorShopActivity.ID]);
		text_desc.setMovementMethod(ScrollingMovementMethod.getInstance());

		text_condition.setText(String.valueOf(GameData.shopTemplate_condition[DoctorShopActivity.ID]));
		Button cancel = (Button) this.findViewById(R.id.shopinfo_button_cancel);

		Button returnback = (Button) this
				.findViewById(R.id.shopinfo_button_returnback);
		Button confirm = (Button) this
		.findViewById(R.id.shopinfo_confirm);

		cancel.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {

				DoctorShopinfoActivity.this.finish();
			}

		});

		returnback.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				DoctorShopinfoActivity.this.finish();
			}
		});
		confirm.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View arg0) 
			{
			//·¢°ü
				Connection.sendMessage(GameProtocol.BuyShop,ConstructData.getDoctorShop(GameData.shopTemplate_id[DoctorShopActivity.ID]));
				DoctorShopinfoActivity.this.finish();
			}
		});
	
		}
	
	
	
	public void Activitychange(Class calss, Intent intent) {
		// TODO Auto-generated method stub
		if (intent == null) {
			intent = new Intent();
		}
		intent.setClass(DoctorShopinfoActivity.this, calss);
		
		this.startActivity(intent);
		
	}
	@Override
	public GameActivity getGameContext() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public void finish() 
	{
		mContext = null;
		super.finish();
	}
}
