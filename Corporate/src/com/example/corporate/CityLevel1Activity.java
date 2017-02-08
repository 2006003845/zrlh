package com.example.corporate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.corporate.base.BaseActivity;
import com.zzl.zl_app.city.CityDB;
import com.zzl.zl_app.net_port.Get2ApiImpl;
import com.zzl.zl_app.net_port.IGet2Api;
import com.zzl.zl_app.net_port.WSError;

public class CityLevel1Activity extends BaseActivity {
	public static final String Tag = "level1";

	public static void launch(Context c, Intent intent) {
		if (intent == null) {
			intent = new Intent();
		}
		intent.setClass(c, CityLevel1Activity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		c.startActivity(intent);
	}

	private List<City> mCityList;
	// 首字母集
	private List<String> mSections;
	// 根据首字母存放数据
	private Map<String, List<City>> mMap;
	// 首字母位置集
	private List<Integer> mPositions;
	// 首字母对应的位置
	private Map<String, Integer> mIndexer;

	private CityDB cityDB;

	IGet2Api api;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.citylist);

		cityDB = new CityDB(getContext());
		api = new Get2ApiImpl(getContext());
		init();
		initView();
		new MyTask().execute();
	}

	ListView objListV;

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
		listv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				City obj = mCityList.get(arg2);
				String[] countrys = obj.getCountys();
				if (countrys != null) {
					ArrayList<Obj> list = new ArrayList<Obj>();
					for (int i = 0; i < countrys.length; i++) {
						list.add(new Obj(countrys[i]));
					}
					Intent intent = getIntent();
					intent.setClass(getContext(), Level2Activity.class);
					Bundle b = new Bundle();
					b.putSerializable("list", list);
					b.putSerializable("key", obj);
					intent.putExtras(b);
					startActivityForResult(intent, 200);
				}
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 200) {
			if (data != null) {
				Bundle b = data.getExtras();
				if (b != null) {
					getContext().setResult(RESULT_OK, data);
					getContext().finish();
				}
			}

		}
	}

	@Override
	public BaseActivity getContext() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public void setDialogContent(AlertDialog dialog, int layoutId, String msg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setDialogTitle(AlertDialog dialog, int layoutId, String title) {
		// TODO Auto-generated method stub

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

	Handler handler = new Handler();

	class MyTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			if (mCityList.size() == 0)
				mCityList.addAll(cityDB.getAllCity());
			if (mCityList.size() == 0) {
				try {
					mCityList
							.addAll(api
									.getCityList(Get2ApiImpl.From_Net,
											"http://nan.51zhixun.com/api.php?MsgType=CityControllers"));
				} catch (JSONException e) {
					e.printStackTrace();
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
