package com.zrong.Android.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.zrong.Android.View.SpriteView;
import com.zrong.Android.entity.Area;
import com.zrong.Android.entity.MapInfo;
import com.zrong.Android.entity.Province;
import com.zrong.Android.entity.VentureSchoolData;

public class AuthorityIssuedActivity extends GameActivity {
	private static AuthorityIssuedActivity mContext;

	private Spinner areaSpinner, provinceSpinner;
	private ImageView map;

	private SpriteView areaMark;

	private ArrayAdapter<Area> areaAdapter;
	private ArrayAdapter<Province> provinceAdapter;

	private List<Province> currentProvinces;
	private List<Area> areas;
	
	private int provinceLastIndex;
 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		mContext = this;
		setContentView(R.layout.authority_issued);
		areaSpinner = (Spinner) this
				.findViewById(R.id.authorityissed_area_spinner);
		provinceSpinner = (Spinner) this
				.findViewById(R.id.authorityissed_city_spinner);
		areaMark = (SpriteView) this
				.findViewById(R.id.authorityissued_img);
		areaMark.setSeries(0);

//		map = (ImageView) this.findViewById(R.id.authorityissued_img);

		Button returnback = (Button) this
				.findViewById(R.id.authorityissued_button_returnback);
		Button cancel = (Button) this
				.findViewById(R.id.authorityissued_button_cancel);
		Button select = (Button) this.findViewById(R.id.authorityissued_select);
		returnback.setOnClickListener(new OnClickListener() {
 
			public void onClick(View v) 
			{
				mContext.finish();
			}
		});
		cancel.setOnClickListener(new OnClickListener() {
 
			public void onClick(View v) 
			{
				mContext.finish();
			}
		});

		select.setOnClickListener(new OnClickListener() {
 
			public void onClick(View v) {

				String areaStr = areaSpinner.getSelectedItem().toString()
						.trim();
				Log.i("Log", areaStr + areaSpinner.getSelectedItemId());
				if(provinceSpinner.getSelectedItem() == null){
					return ;
				}
				String cityStr = provinceSpinner.getSelectedItem().toString()
						.trim();
				Log.i("Log", cityStr + provinceSpinner.getSelectedItemId());
				
				Province province = (Province) provinceSpinner
						.getSelectedItem();

				if (province == null) {
					return;
				}
				
				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				bundle.putString("index", province.getPid());
				bundle.putString("level", province.getLevel());
				intent.putExtra("info", bundle);
				intent.setClass(mContext, AuthorityIssuedInfoActivtiy.class);
				getGameContext().startActivity(intent);
			}
		});
		initData();
		// 设置适配
		areaAdapter = new ArrayAdapter<Area>(mContext,
				android.R.layout.simple_spinner_item, areas);
		provinceAdapter = new ArrayAdapter<Province>(mContext,
				android.R.layout.simple_spinner_item, currentProvinces);
		areaAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		provinceAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		areaSpinner.setAdapter(areaAdapter);
		provinceSpinner.setAdapter(provinceAdapter);

		areaSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
 
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) 
			{
				Area area = (Area) parent.getItemAtPosition(position);
				currentProvinces.clear();
				Province p = (Province)area.getPrvinces().get(0);
				areaMark.setSeries(Integer.parseInt(p.getPid())-15);
				
				currentProvinces.addAll(area.getPrvinces());
				provinceAdapter.notifyDataSetChanged();
			}
 
			public void onNothingSelected(AdapterView<?> parent) 
			{
			}
		});

		provinceSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				provinceLastIndex = position;//当前的位置
				Province item = (Province) parent.getItemAtPosition(position);
				areaMark.setSeries(Integer.parseInt(item.getPid())-15);
//				Log.i("Log", item + "");
//				int relativeX = item.getCoordX();
//				int relativeY = item.getCoordY();
				// 确定确切坐标位置

//				if (relativeX >= 0 && relativeY >= 0) 
//				{
//					Matrix matrix = map.getImageMatrix();
//					float[] values = new float[9];
//					matrix.getValues(values);
//					float mscale_x = values[Matrix.MSCALE_X];// 宽度缩放倍数
//					float mscale_y = values[Matrix.MSCALE_Y];// 高度缩放倍数
//					int[] location = new int[2];
//					map.getLocationInWindow(location);
//					Log.v("yz", "location[0]="+location[0]+",location[1]="+location[1]);
//					int X = Math.round(location[0]
//							+ (relativeX * mscale_x - 20));
//					int Y = Math.round(location[1]
//							+ (relativeY * mscale_y - 20));
//					Log.i("Log", "Left" + location[0]);
//					Log.i("Log", "Top" + location[1]);
//					Log.i("Log", "X" + X);
//					Log.i("Log", "Y" + Y);
//					
////					RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) areaMark
////							.getLayoutParams();
////					params.setMargins((int) X, (int) Y, (int) 40, (int) 40);
////					areaMark.setLayoutParams(params);
////					onContentChanged();
////					areaMark.postInvalidateDelayed(1000);
//				}
			}
 
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
	}

//	public static void changeActivity(Class calss, Intent intent) {
//		if (intent == null) {
//			intent = new Intent();
//		}
//		if (calss == null) {
//			return;
//		}
//		intent.setClass(mContext, calss);
//		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//		mContext.startActivity(intent);
//	}

	private void initData() {

		MapInfo mapInfo = VentureSchoolData.mapInfo;
		Log.i("Log", (mapInfo == null) + "为空");
		if (mapInfo != null  && mapInfo.getMapInfoList() !=null) {
			areas = mapInfo.getMapInfoList();
			currentProvinces = new ArrayList<Province>(areas.get(0)
					.getPrvinces());
		}

	}
	
	private void showMark(Province p){
		int relativeX = p.getCoordX();
		int relativeY = p.getCoordY();
		// 确定确切坐标位置

		if (relativeX >= 0 && relativeY >= 0) {
			Matrix matrix = map.getImageMatrix();
			float[] values = new float[9];
			matrix.getValues(values);
			float mscale_x = values[Matrix.MSCALE_X];// 宽度缩放倍数
			float mscale_y = values[Matrix.MSCALE_Y];// 高度缩放倍数
			int[] location = new int[2];
			map.getLocationInWindow(location);
			int X = Math.round(location[0]
					+ (relativeX * mscale_x - 20));
			int Y = Math.round(location[1]
					+ (relativeY * mscale_y - 20));
			Log.i("Log", "Left" + location[0]);
			Log.i("Log", "Top" + location[1]);
			Log.i("Log", "X" + X);
			Log.i("Log", "Y" + Y);
			// areaMark.setXY((int) X, (int) Y);
			// //
			RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) areaMark
					.getLayoutParams();
			params.setMargins((int) X, (int) Y, (int) 40, (int) 40);
			areaMark.setLayoutParams(params);
			onContentChanged();
			areaMark.postInvalidateDelayed(1000);
		}
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