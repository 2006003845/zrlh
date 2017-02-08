package com.zrong.Android.activity;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.zrong.Android.Util.ImageAdapter;
import com.zrong.Android.Util.Music;
import com.zrong.Android.View.FreshManLead;
import com.zrong.Android.game.Connection;
import com.zrong.Android.game.ConstructData;
import com.zrong.Android.game.GameData;
import com.zrong.Android.online.network.GameProtocol;

public class CreateShopActivity extends GameActivity {

	public static CreateShopActivity mContext = null;
	public static int[] idx;
    public static int idx1=-1;
    public static int idx2;
	private RadioGroup rg;
	private RadioButton smallRadioButton;
	private RadioButton midRadioButton;
	private RadioButton bigRadioButton;

	public static  int radioSelect;
	//private int radioSelect;

	private EditText shopName;

	private Gallery gallery;
	/**
	 * 建造地址
	 */
	private int cityX;

	private int cityY;
	/**
	 * 建造小地址
	 */
	private int startGrid;

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		mContext = this;

		Bundle bundle = this.getIntent().getExtras();

		cityX = bundle.getInt("cityX");

		cityY = bundle.getInt("cityY");

		startGrid = bundle.getInt("grid");

		getWindow().requestFeature(Window.FEATURE_NO_TITLE);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.createshop);

		rg = (RadioGroup) findViewById(R.id.createshop_radiogroup);

		smallRadioButton = (RadioButton) findViewById(R.id.createshop_radiobutton_small);

		midRadioButton = (RadioButton) findViewById(R.id.createshop_radiobutton_mid);

		bigRadioButton = (RadioButton) findViewById(R.id.createshop_radiobutton_big);

		rg.check(smallRadioButton.getId());

		radioSelect = 0;

		shopName = (EditText) findViewById(R.id.createshop_EditText_name);

		gallery = (Gallery) findViewById(R.id.createshop_gallery01);

		ImageAdapter adapter = new ImageAdapter(this, ImageAdapter.GALLERY);
	 
		int imageId[] = new int[GameData.professionId.length];

		for (int i = 0; i < GameData.professionId.length; i++) {
			imageId[i] = GameData.getUIResId(GameData.professionId[i]);
		}

		adapter.setImageIdArray(imageId);

		gallery.setAdapter(adapter);

		rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

			public void onCheckedChanged(RadioGroup group, int checkId) {
				// TODO Auto-generated method stub
				if (checkId == smallRadioButton.getId()) {
					radioSelect = 0;
				} else if (checkId == midRadioButton.getId()) {
					radioSelect = 1;
				} else if (checkId == bigRadioButton.getId()) {
					radioSelect = 2;
				}
			}
		}

		);

		gallery.setOnItemSelectedListener(new OnItemSelectedListener() {

			 
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub

			    //  GameData.hiddedShopId = new int[GameData.professionId.length][2];
				  if(GameData.shopTemplate_hidded[position*GameData.shop_scale_size+radioSelect]== 1){
					  for(int i=0;i<GameData.hiddedShopId.length;i++){
						  if(GameData.shopTemplate_id[position*GameData.shop_scale_size+radioSelect]== GameData.shopTemplate_id[(int)GameData.hiddedShopId[i][0]]) 
						  {
							  if(GameData.hiddedShopId[i][1]==1){
								  idx1 =position;
								  Log.i("qqq", "position===="+idx1);
								  Toast.makeText(MainActivity.mContext, MainActivity.resources.getString(R.string.createbuilding_toast_lock), Toast.LENGTH_SHORT).show();
							  }else{
								  String name = GameData.shopName[position*GameData.shop_scale_size+radioSelect];
									long cost = GameData.createShop_Fees[position*GameData.shop_scale_size+radioSelect];
							        Toast.makeText(CreateShopActivity.mContext,MainActivity.resources.getString(R.string.createbuilding_toast_choose)+name+
							        	"  ;"+	MainActivity.resources.getString(R.string.createbuilding_toast_fee)+cost, 
							            Toast.LENGTH_SHORT).show();
							  }
						  }
					  }
					
				  }else {
					String name = GameData.shopName[position*GameData.shop_scale_size+radioSelect];
					long cost = GameData.createShop_Fees[position*GameData.shop_scale_size+radioSelect];
			        /*Toast.makeText(CreateShopActivity.mContext,MainActivity.resources.getString(R.string.createbuilding_toast_choose)+name+
			        	"  ;"+	MainActivity.resources.getString(R.string.createbuilding_toast_fee)+cost, 
			            Toast.LENGTH_SHORT).show();*/
				  }
			        Gallery  gallery = (Gallery)parent;
			        
			        ImageAdapter adappert = (ImageAdapter)gallery.getAdapter();
			        
			        
			        adappert.notifyDataSetChanged(position);

				
				  
			}

			 
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
		
	/*gallery.setOnItemClickListener(new OnItemClickListener()
		{
			public void onItemClick(AdapterView<?> parent, View v, int position, long id)
			{
		    //  GameData.hiddedShopId = new int[GameData.professionId.length][2];
			  if(GameData.shopTemplate_hidded[position*GameData.shop_scale_size+radioSelect]== 1){
				  for(int i=0;i<GameData.hiddedShopId.length;i++){
					  if(GameData.shopTemplate_id[position*GameData.shop_scale_size+radioSelect]== GameData.shopTemplate_id[(int)GameData.hiddedShopId[i][0]]) 
					  {
						  if(GameData.hiddedShopId[i][1]==1){
							  idx1 =position;
							  Log.i("qqq", "position===="+idx1);
							  Toast.makeText(MainActivity.mContext, MainActivity.resources.getString(R.string.createbuilding_toast_lock), Toast.LENGTH_SHORT).show();
						  }else{
							  String name = GameData.shopName[position*GameData.shop_scale_size+radioSelect];
								long cost = GameData.createShop_Fees[position*GameData.shop_scale_size+radioSelect];
						        Toast.makeText(CreateShopActivity.mContext,MainActivity.resources.getString(R.string.createbuilding_toast_choose)+name+
						        	"  ;"+	MainActivity.resources.getString(R.string.createbuilding_toast_fee)+cost, 
						            Toast.LENGTH_SHORT).show();
						  }
					  }
				  }
				
			  }else {
				String name = GameData.shopName[position*GameData.shop_scale_size+radioSelect];
				long cost = GameData.createShop_Fees[position*GameData.shop_scale_size+radioSelect];
		        Toast.makeText(CreateShopActivity.mContext,MainActivity.resources.getString(R.string.createbuilding_toast_choose)+name+
		        	"  ;"+	MainActivity.resources.getString(R.string.createbuilding_toast_fee)+cost, 
		            Toast.LENGTH_SHORT).show();
			  }
		        Gallery  gallery = (Gallery)parent;
		        
		        ImageAdapter adappert = (ImageAdapter)gallery.getAdapter();
		        
		        
		        adappert.notifyDataSetChanged(position);

			
			  }
		});*/

    	Random r = new Random();

		r.setSeed(100);

		int random = Math.abs(r.nextInt());

		int select = random % imageId.length;

		if (select == 0 || select == imageId.length - 1) {
			select = imageId.length / 2;
		}

		adapter.notifyDataSetChanged(select);

		gallery.setSelection(select);
		
		for(int j=0;j<gallery.getCount();j++){
			idx = new int[gallery.getCount()];
			idx[0] = -1;
			  if(GameData.shopTemplate_hidded[j*GameData.shop_scale_size+radioSelect]== 1){
				  for(int i=0;i<GameData.hiddedShopId.length;i++){
					  if(GameData.shopTemplate_id[j*GameData.shop_scale_size+radioSelect]== GameData.shopTemplate_id[(int)GameData.hiddedShopId[i][0]]) 
					  {
						  if(GameData.hiddedShopId[i][1]==1){
							  
							  idx[j] =j;
							  Log.i("qqq", "idx["+j+"]======="+j);
						  }
		          }
	     	  }
		  }
	  }

		Button cancel = (Button) findViewById(R.id.createshop_button_cancel);

		Button returnBack = (Button) findViewById(R.id.createshop_button_returnback);

		Button create = (Button) findViewById(R.id.createshop_button_create);

		cancel.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {

				CreateShopActivity.this.finish();
			}
		});

		returnBack.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				CreateShopActivity.this.finish();
			}
		});


		create.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {			
				if(gallery.getSelectedItemPosition() ==idx1){
					 Toast.makeText(MainActivity.mContext, MainActivity.resources.getString(R.string.createbuilding_toast_lock), Toast.LENGTH_SHORT).show();
				}else{
				GameData.buildCityX = (short) cityX;
				GameData.buildCityY = (short) cityY;
				CreateShopActivity.this.finish();

				Connection.sendMessage(
						GameProtocol.CONNECTION_SEND_SHOP_CREATE, ConstructData
								.getCreateShop(shopName.getText().toString(),
										(byte) (radioSelect + 1),
										GameData.professionId[gallery
												.getSelectedItemPosition()],
										GameData.mapIds[GameData.mapIdIndex],
										(short) cityX, (short) cityY,
										(byte) startGrid));
				}
				//新手引导---zhouzhilong add---
				if(GameData.isFreshMan && FreshManLead.caseId == 7){
					FreshManLead.caseId = 8;
				}
			}
		});
		
		//zhouzhilong -->新手引导
	if (GameData.isFreshMan) {
			if (FreshManLead.caseId == 6) {
				Log.i("Log", "FreshManLead--CreateShop--caseId :"+FreshManLead.caseId);
				doPromoter(FreshManLead.prompt8);
				FreshManLead.caseId++;
			}
		}

	}
	 


	// ================提示框
	private Handler endAnimationHandler = new Handler();

	// zhouzhilong add
	public void doPromoter(final String str) {
		new Thread() {
			public void run() {
				endAnimationHandler.postDelayed((new Runnable() {
					 
					public void run() {
						Log.i("Log", "doPromoter---");
						setText(str);
					}
				}), 100);
			};
		}.start();
	}

	private LinearLayout linearPromot;

	// zhouzhilong add
	private void setText(String textStr) {

		Log.i("Log", "setText-CreateShop");
		linearPromot = (LinearLayout) this
				.findViewById(R.id.linearlayout_promotframe3);
		Button close = (Button) this.findViewById(R.id.close_promotframe3);
		TextView promot = (TextView) this.findViewById(R.id.promotFrame3);
		if (textStr != null) {
			promot.setText(textStr);
		}
		close.setOnClickListener(new OnClickListener() {

			 
			public void onClick(View v) {
				Log.i("Log", "close---");
				linearPromot.setVisibility(View.GONE);

			}
		});

		linearPromot.setVisibility(View.VISIBLE);
		final View promotParent = (View) linearPromot.getParent();
		Animation anim = new TranslateAnimation(-promotParent.getWidth() * 3
				/ 2 + linearPromot.getWidth() / 2, 0.0f, 0.0f, 0.0f);
		anim.setDuration(1500);
		anim.setStartOffset(300);
		anim.setFillAfter(false);
		anim.setInterpolator(this, android.R.anim.overshoot_interpolator);
		linearPromot.startAnimation(anim);

	}

	

	 
	public GameActivity getGameContext() {
		// TODO Auto-generated method stub
		return this;
	}
	
	 
	public void finish() 
	{
		mContext = null;
		super.finish();
	}

}
