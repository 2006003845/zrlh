package com.zrong.Android.activity;

import com.zrong.Android.Listener.ActivityTransform;
import com.zrong.Android.Listener.DataChangeListener;

import com.zrong.Android.Util.Music;

import com.zrong.Android.game.Connection;
import com.zrong.Android.game.ConstructData;
import com.zrong.Android.game.GameData;
import com.zrong.Android.online.network.GameProtocol;

import android.app.Activity;

import android.content.Context;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;

import android.widget.Button;
import android.widget.Toast;

import android.widget.ScrollView;

import android.widget.TextView;

public class CompanyTabActivity extends GameActivity implements
		DataChangeListener {

	private TextView level;
	public static CompanyTabActivity mContext = null;

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		mContext = this;

		getWindow().requestFeature(Window.FEATURE_NO_TITLE);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		this.setContentView(R.layout.company);

		GameData.corporation.listener = this;

		initCompany(0, this);
	}

	private void initCompany(int index, Context context) {
		setSelectIndex(index);

		Button returnback = (Button) this
				.findViewById(R.id.company_button_returnback);
		Button cancle = (Button) this.findViewById(R.id.company_button_cancel);

		returnback.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {

				CompanyTabActivity.this.finish();
			}
		}

		);

		cancle.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				CompanyTabActivity.this.finish();
			}

		}

		);
	}

	

	public void Activitychange(Class activityClass, Intent intent) {
		if (intent == null) {
			intent = new Intent();
		}
		Log.i("Log", activityClass.getName());
		intent.setClass(CompanyTabActivity.this, activityClass);
		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		
		this.startActivity(intent);

	}

	public void setSelectIndex(int index) {

		ScrollView info = (ScrollView) this
				.findViewById(R.id.companyinfo_scrollview);
		switch (index) {
		case 0:

			info.setVisibility(View.VISIBLE);
			initCompanyView();
			break;

		}
	}

/*	
	public void initCompanyInfo()
	{
		TextView name = (TextView)this.findViewById(R.id.companyinfo_name);
	    level = (TextView)this.findViewById(R.id.companyinfo_level);
		TextView cash = (TextView)this.findViewById(R.id.companyinfo_cash);
		TextView asset = (TextView)this.findViewById(R.id.companyinfo_asset);
		TextView popularity = (TextView)this.findViewById(R.id.companyinfo_popularity);
		TextView morale = (TextView)this.findViewById(R.id.companyinfo_morale);
		TextView shop = (TextView)this.findViewById(R.id.companyinfo_shop);
		TextView employee = (TextView)this.findViewById(R.id.companyinfo_employee);
		Button levelup = (Button)this.findViewById(R.id.companyinfo_levelup);
		//name.append(GameData.corporation.name);
		name.setText(MainActivity.resources.getString(R.string.Companytab_companyname)+GameData.corporation.name);
		
		levelup.setOnClickListener(new OnClickListener()
		{*/


	private TextView cash, asset, popularity, morale, shop, employee;

	public void initCompanyView() {
		TextView name = (TextView) this.findViewById(R.id.companyinfo_name);
		level = (TextView) this.findViewById(R.id.companyinfo_level);
		cash = (TextView) this.findViewById(R.id.companyinfo_cash);
		asset = (TextView) this.findViewById(R.id.companyinfo_asset);
		popularity = (TextView) this.findViewById(R.id.companyinfo_popularity);
		morale = (TextView) this.findViewById(R.id.companyinfo_morale);
		shop = (TextView) this.findViewById(R.id.companyinfo_shop);
		employee = (TextView) this.findViewById(R.id.companyinfo_employee);
		Button levelup = (Button) this.findViewById(R.id.companyinfo_levelup);
		// name.append(GameData.corporation.name);
		name.setText("公司名称:" + GameData.corporation.name);

		levelup.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				Connection.sendMessage(
						GameProtocol.CONNECTION_REQ_LEVELUP_VIEW, ConstructData
								.getLevelUPInfo((byte) 0,
										GameData.corporation.companyId));

			}

		}

		);

		
		//level.append(String.valueOf(GameData.corporation.level));
		level.setText(MainActivity.resources.getString(R.string.Companytab_companylevel)+GameData.corporation.level);
		
		//cash.append(String.valueOf(GameData.corporation.wealth));
		cash.setText(MainActivity.resources.getString(R.string.Companytab_companywealth)+GameData.player.money);
		
		//asset.append(String.valueOf(GameData.corporation.wealth));
		asset.setText(MainActivity.resources.getString(R.string.Companytab_companyassets)+GameData.player.assets);
		
		//popularity.append(String.valueOf(GameData.corporation.awareness));
		popularity.setText(MainActivity.resources.getString(R.string.Companytab_companyawareness)+GameData.corporation.awareness);
		
		//morale.append(String.valueOf(GameData.corporation.prestige));
		morale.setText(MainActivity.resources.getString(R.string.Companytab_companyprestige)+GameData.corporation.prestige);
		
		//shop.append(String.valueOf(GameData.corporation.shop.length)+"/"+GameData.corporation.shopMaxNum);
		
		shop.setText(MainActivity.resources.getString(R.string.Companytab_shopnumber)+GameData.corporation.shop.length+"/"+GameData.corporation.shopMaxNum);
		
		//employee.append(String.valueOf(GameData.corporation.employee.length)+"/"+GameData.corporation.employeesMaxNum);
		employee.setText(MainActivity.resources.getString(R.string.Companytab_staffnumber)+GameData.corporation.employee.length+"/"+GameData.corporation.employeesMaxNum);
	}


	/*	OnDataChange(null);// 数据加载


	public void OnDataChange(Bundle bundle) 
	{
	 
		level.setText(MainActivity.resources.getString(R.string.Companytab_companylevel)+GameData.corporation.level);
		

	}
*/
	/**
	 * 数据加载
	 */
	public void OnDataChange(Bundle bundle) {
		if (bundle != null) {
			Toast.makeText(mContext, "恭喜您升级成功", Toast.LENGTH_LONG).show();
		}
		level.setText("公司等级:" + GameData.corporation.level);
		cash.setText("公司现金:" + GameData.corporation.wealth);
		asset.setText("公司资产:" + GameData.corporation.wealth);
		popularity.setText("公司知名度:" + GameData.corporation.awareness);
		morale.setText("公司荣誉度:" + GameData.corporation.prestige);
		shop.setText("店铺数量:" + GameData.corporation.shop.length + "/"
				+ GameData.corporation.shopMaxNum);
		employee.setText("员工数量:" + GameData.corporation.employee.length + "/"
				+ GameData.corporation.employeesMaxNum);

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
