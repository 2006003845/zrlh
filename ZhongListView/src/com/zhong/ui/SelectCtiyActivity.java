package com.zhong.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhong.adapter.CityAdapter;
import com.zhong.adapter.SearchCityAdapter;
import com.zhong.bean.City;
import com.zhong.bean.ResultBean;
import com.zhong.view.BladeView;
import com.zhong.view.PinnedHeaderListView;

/**
 * 显示搜索城市的Activity
 */
public class SelectCtiyActivity extends Activity {

	/* ===============Model================== */
	/** 所有的城市集合 */
	private List<City> mCityList;
	/** 首字母集合 */
	private List<String> mSections;
	/** 根据首字母存放数据 */
	private Map<String, List<City>> mMap;
	/** 首字母位置集合 */
	private List<Integer> mPositions;
	/** 首字母对应的位置 */
	private Map<String, Integer> mIndexer;
	/** 搜索框中输入内容时需要的适配器 */
	private SearchCityAdapter mSearchCityAdapter;
	/** 显示带头字母的整个城市的ListView适配器 */
	private CityAdapter mCityAdapter;
	/* ===============View===================== */
	/** 输入法控制 */
	private InputMethodManager mInputMethodManager;
	/** 界面搜索框 */
	private EditText mSearchEditText;
	/** 搜索框清空图片的小图标 */
	private ImageButton mClearSearchBtn;
	/** 右侧带字母排序功能的整个大布局，获取这个控件的用途是判断是否隐藏 */
	private View mCityContainer;
	/** 搜索框输入文字时的整个大布局，获取这个控件的用途是判断是否隐藏 */
	private View mSearchContainer;
	/** 自定义的滑动时带标题字母的ListView */
	private PinnedHeaderListView mCityListView;
	/** 为方法查询自定义的右侧显示26字母的控件 */
	private BladeView mLetter;
	/** 搜索框的显示城市的ListView */
	private ListView mSearchListView;
	/** 左上角的返回按钮 */
	private ImageView mBackBtn;
	/* ===============Control================= */
	/** 搜索框监听 */
	private TextWatcher mTextWatcher = new TextWatcher() {

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			// do nothing
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			mSearchCityAdapter = new SearchCityAdapter(SelectCtiyActivity.this, mCityList);
			mSearchListView.setAdapter(mSearchCityAdapter);
			mSearchListView.setTextFilterEnabled(true);
			if (mCityList.size() < 1 || TextUtils.isEmpty(s)) {
				mCityContainer.setVisibility(View.VISIBLE);
				mSearchContainer.setVisibility(View.INVISIBLE);
				mClearSearchBtn.setVisibility(View.GONE);
			} else {
				mClearSearchBtn.setVisibility(View.VISIBLE);
				mCityContainer.setVisibility(View.INVISIBLE);
				mSearchContainer.setVisibility(View.VISIBLE);
				mSearchCityAdapter.getFilter().filter(s);
			}
		}

		@Override
		public void afterTextChanged(Editable s) {
			// 如何搜索字符串长度为0，是否隐藏输入法
			// if(TextUtils.isEmpty(s)){
			// mInputMethodManager.hideSoftInputFromWindow(
			// mSearchEditText.getWindowToken(), 0);
			// }

		}
	};
	/** 界面上的单击事件监听 */
	private OnClickListener mOnClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.ib_clear_text:
				Toast.makeText(SelectCtiyActivity.this, "单击了ib_clear_text", Toast.LENGTH_LONG).show();
				if (!TextUtils.isEmpty(mSearchEditText.getText().toString())) {
					mSearchEditText.setText("");
					mInputMethodManager.hideSoftInputFromWindow(mSearchEditText.getWindowToken(), 0);
				}
				break;
			case R.id.title_back:
				Toast.makeText(SelectCtiyActivity.this, "单击了title_back", Toast.LENGTH_LONG).show();
				break;
			default:
				break;
			}
		}
	};
	/** 自定义的右侧26个字母布局的item项的单击监听 */
	private com.zhong.view.BladeView.OnItemClickListener mOnItemClickListener26 = new com.zhong.view.BladeView.OnItemClickListener() {

		@Override
		public void onItemClick(String s) {

			if (mIndexer.get(s) != null) {
				mCityListView.setSelection(mIndexer.get(s));
			}
		};
	};
	/** 自定义界面Touch监听，用于搜索框显示的ListView的Touch事件 ,作用是软键盘是否显示 */
	private android.view.View.OnTouchListener mOnTouchListener = new OnTouchListener() {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			mInputMethodManager.hideSoftInputFromWindow(mSearchEditText.getWindowToken(), 0);
			return false;
		}

	};
	/** 自定义的带标头字母的ListView的item监听 */
	private android.widget.AdapterView.OnItemClickListener mOnItemListViewClickListener = new android.widget.AdapterView.OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			City city = mCityAdapter.getItem(position);
			Toast.makeText(SelectCtiyActivity.this, "单击了startActivity()" + city.getNa(), Toast.LENGTH_LONG).show();
		}

	};
	/** 搜索框显示城市的ListView的item监听 */
	private android.widget.AdapterView.OnItemClickListener mOnItemShowCityListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			City city = mSearchCityAdapter.getItem(position);
			Toast.makeText(SelectCtiyActivity.this, "单击了startActivity()" + city.getNa(), Toast.LENGTH_LONG).show();
		}
	};

	/* =============Left cycle============== */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initData();
		initView();
	}

	/* =================子过程============== */
	/**
	 * 初始化数据
	 */
	private void initData() {
		/* 弹出的键盘对象获取 */
		mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
		/* 初始化数据源 */
		mCityList = new ArrayList<City>();
		mSections = new ArrayList<String>();
		mMap = new HashMap<String, List<City>>();
		mPositions = new ArrayList<Integer>();
		mIndexer = new HashMap<String, Integer>();
		prepareCityList();
		mCityAdapter = new CityAdapter(SelectCtiyActivity.this, mCityList, mMap, mSections, mPositions);
	}

	/**
	 * 初始化布局
	 */
	private void initView() {
		/* 主界面布局 */
		setContentView(R.layout.biz_plugin_weather_select_city);
		/* 左上角按钮 */
		mBackBtn = (ImageView) findViewById(R.id.title_back);
		mBackBtn.setOnClickListener(mOnClickListener);
		/* 界面中的搜索框 */
		mSearchEditText = (EditText) findViewById(R.id.search_edit);
		mSearchEditText.addTextChangedListener(mTextWatcher);
		/* 界面中搜索框上的清空按钮 */
		mClearSearchBtn = (ImageButton) findViewById(R.id.ib_clear_text);
		mClearSearchBtn.setOnClickListener(mOnClickListener);
		/* 编辑框输入文字时应该显示这个大布局 */
		mSearchContainer = findViewById(R.id.search_content_container);
		mSearchContainer.setVisibility(View.GONE);
		/* 右侧带字母排序的大布局初始化 */
		mCityContainer = findViewById(R.id.city_content_container);
		/* 自定义的滑动时带标题字母的ListView 初始化 */
		mCityListView = (PinnedHeaderListView) findViewById(R.id.citys_list);
		mCityListView.setEmptyView(findViewById(R.id.citys_list_empty));
		mCityListView.setOnItemClickListener(mOnItemListViewClickListener);
		mCityListView.setAdapter(mCityAdapter);
		mCityListView.setOnScrollListener(mCityAdapter);
		mCityListView.setPinnedHeaderView(LayoutInflater.from(SelectCtiyActivity.this).inflate(R.layout.biz_plugin_weather_list_group_item, mCityListView, false));
		/* 自定义的右边的26个字母的控件 */
		mLetter = (BladeView) findViewById(R.id.citys_bladeview);
		mLetter.setOnItemClickListener(mOnItemClickListener26);
		mLetter.setVisibility(View.VISIBLE);
		/* 搜索框显示的ListView初始化 */
		mSearchListView = (ListView) findViewById(R.id.search_list);
		mSearchListView.setEmptyView(findViewById(R.id.search_empty));
		mSearchListView.setOnTouchListener(mOnTouchListener);
		mSearchListView.setOnItemClickListener(mOnItemShowCityListener);
	}

	private boolean prepareCityList() {
		String FORMAT = "^[a-z,A-Z].*$";
		mCityList = getAllCity();// 获取数据库中所有城市
		for (City city : mCityList) {
			String firstName = city.getFirstPY();// 第一个字拼音的第一个字母
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
		Collections.sort(mSections);// 按照字母重新排序
		int position = 0;
		for (int i = 0; i < mSections.size(); i++) {
			mIndexer.put(mSections.get(i), position);// 存入map中，key为首字母字符串，value为首字母在listview中位置
			mPositions.add(position);// 首字母在listview中位置，存入list中
			position += mMap.get(mSections.get(i)).size();// 计算下一个首字母在listview的位置
		}
		return true;
	}

	/**
	 * 获取到本地的所有城市
	 * 
	 * @return 所有城市
	 */
	private List<City> getAllCity() {
		String data = "";
		InputStream is = null;
		try {
			is = getResources().getAssets().open("life_city.json");
			Log.i("tag", "==is-->" + is);
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			Log.i("tag", "==sb-->" + sb.toString());
			data = sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Log.i("tag", "===data-->" + data);
		Gson gson = new Gson();
		ResultBean mResultBean = gson.fromJson(data, ResultBean.class);
		// Log.i("tag", "===str-->" + str);
		// ResultBean mResultBean = gson.fromJson(str, ResultBean.class);
		Log.i("tag", "===mResultBean-->" + mResultBean);
		// List<City> list = jsonStringToList(data, new String[] { "na", "id", "as", "ss", "lon", "lat" },
		// "citylist");
		return mResultBean.getCitylist();
	}

	// // json字符串反序列化成List对象
	// public List<City> jsonStringToList(String jsonString, String[] keyNames, String key) {
	// JSONArray jsonArray = null;
	// List<City> list = new ArrayList<City>();
	// try {
	// if (key == null) {
	// jsonArray = new JSONArray(jsonString);
	// } else {
	// jsonArray = new JSONObject(jsonString).getJSONArray(key);
	// }
	// for (int i = 0; i < jsonArray.length(); i++) {
	// JSONObject jsonObject = jsonArray.getJSONObject(i);
	// City city = new City(jsonObject.get(keyNames[0]).toString(), jsonObject.get(keyNames[1]).toString(),
	// jsonObject.get(keyNames[2]).toString(), jsonObject.get(keyNames[3]).toString(),
	// jsonObject.get(keyNames[4]).toString(), jsonObject.get(keyNames[5]).toString());
	// list.add(city);
	// }
	// } catch (Exception ex) {
	// ex.printStackTrace();
	// }
	// return list;
	// }
}
