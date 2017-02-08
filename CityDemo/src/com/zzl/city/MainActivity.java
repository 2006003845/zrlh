package com.zzl.city;

import java.io.BufferedReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.zzl.api.Get2ApiImpl;
import com.zzl.api.IGet2Api;
import com.zzl.api.WSError;

public class MainActivity extends Activity {

	private List<City> mCityList;
	// 首字母集
	private List<String> mSections;
	// 根据首字母存放数据
	private Map<String, List<City>> mMap;
	// 首字母位置集
	private List<Integer> mPositions;
	// 首字母对应的位置
	private Map<String, Integer> mIndexer;

	private Context mContext;

	private CityDB cityDB;

	IGet2Api api;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mContext = this;
		cityDB = new CityDB(mContext);
		api = new Get2ApiImpl(mContext);
		init();
		initView();

		new MyTask().execute();

	}

	private void init() {
		mCityList = new ArrayList<City>();
		mSections = new ArrayList<String>();
		mMap = new HashMap<String, List<City>>();
		mPositions = new ArrayList<Integer>();
		mIndexer = new HashMap<String, Integer>();
	}

	ListView listv;
	ProgressBar pb;
	CityAdapter cityAdapter;

	private void initView() {
		pb = (ProgressBar) this.findViewById(R.id.pb);
		listv = (ListView) this.findViewById(R.id.listv);
		cityAdapter = new CityAdapter(this, mCityList, mMap, mSections,
				mPositions);
		listv.setAdapter(cityAdapter);
	}

	Handler handler = new Handler();

	class MyTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			if (mCityList.size() == 0)
				mCityList.addAll(cityDB.getAllCity());
			if (mCityList.size() == 0) {
				try {
					mCityList.addAll(api.getCityList(Get2ApiImpl.From_Assert,
							"cities_all.xml"));
				} catch (WSError e) {
					e.printStackTrace();
				}
				cityDB.saveCity(mCityList);
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if (mCityList.size() == 0)
				mCityList.addAll(cityDB.getAllCity());
			if (mCityList != null && mCityList.size() > 0) {
				new Thread(new Runnable() {

					@Override
					public void run() {
						prepareCityList();
						handler.post(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								cityAdapter.notifyDataSetChanged();
								pb.setVisibility(View.GONE);
							}
						});
					}
				}).start();

			}

		}
	}

	private static final String FORMAT = "^[a-z,A-Z].*$";

	public boolean prepareCityList() {
		if (mCityList.size() == 0)
			mCityList.addAll(cityDB.getAllCity());// 获取数据库中的城市
		for (City city : mCityList) {
			String firstName = city.getFirstPY();// 第一个字拼音的第一個字母
			if (firstName != null) {
				if (firstName.matches(FORMAT)) {
					if (mSections.contains(firstName)) {
						mMap.get(firstName).add(city);
					} else {
						mSections.add(firstName);
						List<City> list = new ArrayList<City>();
						list.add(city);
						mMap.put(firstName, list);
					}
				} else {
					if (mSections.contains("#")) {
						mMap.get("#").add(city);
					} else {
						mSections.add("#");
						List<City> list = new ArrayList<City>();
						list.add(city);
						mMap.put("#", list);
					}
				}
			}
		}
		Collections.sort(mSections);// 按照字母重新排序
		int position = 0;
		for (int i = 0; i < mSections.size(); i++) {
			mIndexer.put(mSections.get(i), position);// 存入map中，key为首字母字符串，value为首字母在listview中位�?
			mPositions.add(position);// 首字母在listview中位置，存入list�?
			position += mMap.get(mSections.get(i)).size();// 计算下一个首字母在listview的位�?
		}
		return true;
	}

}
