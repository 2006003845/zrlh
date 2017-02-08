package com.zrong.Android.activity;

import res.BuildingSprite;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.zrong.Android.Util.Music;
import com.zrong.Android.View.FreshManLead;
import com.zrong.Android.View.SpriteView;
import com.zrong.Android.element.PublicBuilding;
import com.zrong.Android.game.GameData;
//我的工艺建筑界面
public class CheckBuildingActivity extends GameActivity {

	public static CheckBuildingActivity mContext = null;

	private int selectindex;
	private Handler endAnimationHandler = new Handler();

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		getWindow().requestFeature(Window.FEATURE_NO_TITLE);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		mContext = this;

		Bundle bundle = this.getIntent().getExtras();

		selectindex = bundle.getInt("selectIndex");

		BuildingSprite bs = GameData.build[selectindex];

		setContentView(R.layout.checkbuilding1);

		Button title = (Button) this
		.findViewById(R.id.checkbuilding1_button_title);
		title.setText( GameData.build[selectindex].mb.simpleName);
//		Log.i("ppp", "查看 buildname = " + GameData.build[selectindex].mb.name);
		Button returnback = (Button) this
				.findViewById(R.id.checkbuilding1_button_returnback);

		Button cancel = (Button) this
				.findViewById(R.id.checkbuilding1_button_cancel);

		returnback.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				CheckBuildingActivity.this.finish();
			}
		}

		);

		cancel.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				CheckBuildingActivity.this.finish();
			}
		}

		);

		// 新手提示--提示框
		if (GameData.isFreshMan) {
			if (FreshManLead.isBank) {
				doPromoter(FreshManLead.prompt3);
			} else if (FreshManLead.isCommerical) {
				doPromoter(FreshManLead.prompt4);
			} else if (FreshManLead.isRevenue) {
				doPromoter(FreshManLead.prompt5);
			}
		}

		ScrollView sv1 = (ScrollView) this
				.findViewById(R.id.checkbuilding1_scrollview1);

		ScrollView sv2 = (ScrollView) this
				.findViewById(R.id.checkbuilding1_scrollview2);

		String description = GameData.getStringValue(GameData.buildingId,
				GameData.build_description, bs.mb.trade_id);

		SpriteView spriteview = (SpriteView) this
				.findViewById(R.id.checkbuilding1_image);
		
		if (bs.mb.resId > 0) {
			spriteview.setId(bs.mb.resId);
		}
		
		spriteview.setSeries(0);

		if (description == null || description.equals("")) {
			sv1.setVisibility(View.GONE);
			sv2.setVisibility(View.VISIBLE);

			
			TextView type = (TextView)this.findViewById(R.id.checkbuilding1_type);
			
			type.setText(GameData.getProfessionName(String.valueOf(MainActivity.resources.getString(R.string.checkbuilding_buildingtype)), bs.mb.trade_id, (byte)0));
			
			
			TextView manager = (TextView)this.findViewById(R.id.checkbuilding1_manager);
			
			PublicBuilding pb = GameData.getPublicBuildingByBuildingID(bs.mb.id);
			


			/*TextView type = (TextView) this
					.findViewById(R.id.checkbuilding1_type);

			type.setText(GameData.getProfessionName("建筑类型:  ", bs.mb.trade_id,
					(byte) 0));

			TextView manager = (TextView) this
					.findViewById(R.id.checkbuilding1_manager);

			PublicBuilding pb = GameData
					.getPublicBuildingByBuildingID(bs.mb.id);*/


			String managerName = MainActivity.resources.getString(R.string.unknow);
			if (pb != null) {
				managerName = GameData.getEmployeeNameById(pb.manager_id, MainActivity.resources.getString(R.string.unknow));
			}

			
			
			manager.setText(MainActivity.resources.getString(R.string.checkbuilding_manager)+managerName);
			
			
			TextView flow = (TextView)this.findViewById(R.id.checkbuilding1_flow);
			
			
			flow.setText(MainActivity.resources.getString(R.string.checkbuilding_flow)+GameData.getShorttValue(GameData.buildingId, GameData.flow_gain, bs.mb.trade_id));
			
			TextView range = (TextView)this.findViewById(R.id.checkbuilding1_range);
			range.setText(MainActivity.resources.getString(R.string.checkbuilding_range)+GameData.getByteValue(GameData.buildingId, GameData.eff_area, bs.mb.trade_id));
			
			TextView createcost = (TextView)this.findViewById(R.id.checkbuilding1_createcost);
			createcost.setText(MainActivity.resources.getString(R.string.checkbuilding_createcost)+GameData.getLongtValue(GameData.buildingId,GameData.buildingFees,bs.mb.trade_id));
			
			TextView maintenancecost = (TextView)this.findViewById(R.id.checkbuilding1_maintenancecost);
			maintenancecost.setText(MainActivity.resources.getString(R.string.checkbuilding_maintenancecost)+GameData.getLongtValue(GameData.buildingId,GameData.weeklyFees,bs.mb.trade_id));
			
		}
		/*else//只能看到描述信息		


			manager.setText("项目经理:  " + managerName);

			TextView flow = (TextView) this
					.findViewById(R.id.checkbuilding1_flow);

			flow.setText("增益客流:  "
					+ GameData.getShorttValue(GameData.buildingId,
							GameData.flow_gain, bs.mb.trade_id));

			TextView range = (TextView) this
					.findViewById(R.id.checkbuilding1_range);
			range.setText("影响范围:  "
					+ GameData.getByteValue(GameData.buildingId,
							GameData.eff_area, bs.mb.trade_id));

			TextView createcost = (TextView) this
					.findViewById(R.id.checkbuilding1_createcost);
			createcost.setText("创建费用:  "
					+ GameData.getLongtValue(GameData.buildingId,
							GameData.buildingFees, bs.mb.trade_id));

			TextView maintenancecost = (TextView) this
					.findViewById(R.id.checkbuilding1_maintenancecost);
			maintenancecost.setText("维护费用:  "
					+ GameData.getLongtValue(GameData.buildingId,
							GameData.weeklyFees, bs.mb.trade_id));

		}*/ else// 只能看到描述信息

		{
			sv1.setVisibility(View.VISIBLE);
			sv2.setVisibility(View.GONE);
			TextView tv = (TextView) this
					.findViewById(R.id.checkbuilding1_text);

			tv.setText(description);

			// zhouzhilong add 新手引导
			if (GameData.isFreshMan) {
				if (FreshManLead.isBank) {
					FreshManLead.caseId = 1;
				} else if (FreshManLead.isCommerical) {
					FreshManLead.caseId = 2;
				} else if (FreshManLead.isRevenue) {
					FreshManLead.caseId = 3;
				}
			}

		}
	}

	// ================提示框

	// zhouzhilong add
	public void doPromoter(final String str) {
		new Thread() {
			public void run() {
				endAnimationHandler.postDelayed((new Runnable() {
					 
					public void run() {
						Log.i("Log", "doPromoter---");
						setText(str);
					}
				}), 600);
			};
		}.start();
	}

	private LinearLayout linearPromot;

	// zhouzhilong add
	private void setText(String textStr) {

		Log.i("Log", "setText--Map");
		linearPromot = (LinearLayout) this
				.findViewById(R.id.linearlayout_promotframe2);
		Button close = (Button) this.findViewById(R.id.close_promotframe2);
		TextView promot = (TextView) this.findViewById(R.id.promotFrame2);
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
