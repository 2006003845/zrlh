package com.zr.zzl.cus.demo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.homepage);
		initView();
	}

	private ScrollLayout lst_views;
	private ListView listView;

	private List<ImgItem> imgItemList = new ArrayList<ImgItem>();

	public static final int PAGE_SIZE = 6;
	ArrayList<ArrayList<Item>> lists = new ArrayList<ArrayList<Item>>();// 全部数据的集合集lists.size()==countpage;
	ArrayList<Item> lstDate = new ArrayList<Item>();// 每一页的数据

	SensorManager sm;
	SensorEventListener lsn;
	boolean isClean = false;
	Vibrator vibrator;
	int rockCount = 0;

	ArrayList<ListView> listviews = new ArrayList<ListView>();

	private PointLinearLayout pointLayout;
	private ViewPager hotViewPager;
	private List<View> hotViews = new ArrayList<View>();
	private int[] hotViewIds = new int[] { R.layout.item_hot_gallery,
			R.layout.item_hot_gallery, R.layout.item_hot_gallery };

	private void initView() {
		lst_views = (ScrollLayout) findViewById(R.id.homepage_views);
		pointLayout = (PointLinearLayout) this
				.findViewById(R.id.homepage_playout_pageturn);
		pointLayout.addViews(this, 3);
		pointLayout.setPageIndex(0);

		for (int i = 0, len = 3; i < len; i++) {
			hotViews.add(getView(hotViewIds[i]));
		}
		initImgItems();
		initHotViews();
		hotViewPager = (ViewPager) this.findViewById(R.id.homepage_viewpager);
		ViewPagerAdapter adapter = new ViewPagerAdapter(this, hotViews);
		hotViewPager.setAdapter(adapter);
		hotViewPager
				.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

					@Override
					public void onPageSelected(int arg0) {

					}

					@Override
					public void onPageScrolled(int arg0, float arg1, int arg2) {
						pointLayout.setPageIndex(arg0);
					}

					@Override
					public void onPageScrollStateChanged(int arg0) {

					}
				});

		Configure.init(this);

		if (listView != null) {
			lst_views.removeAllViews();
		}
		for (int i = 0; i < 8; i++) {
			lstDate.add(new Item(i, "name" + i, "url" + i, "", 0));
		}
		initData();

		for (int i = 0; i < Configure.countPages; i++) {
			lst_views.addView(addListView(i));
		}

		lst_views.setPageListener(new ScrollLayout.PageListener() {
			@Override
			public void page(int page) {

			}
		});

		this.findViewById(R.id.homepage_btn_contactus).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO
					}
				});
		this.findViewById(R.id.homepage_btn_sina).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO
					}
				});
		this.findViewById(R.id.homepage_btn_setting).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO
					}
				});
	}

	private void initImgItems() {
		// TODO
		imgItemList.clear();
		for (int i = 0; i < 3; i++) {
			imgItemList.add(new ImgItem());
		}
	}

	public void initHotViews() {
		for (int i = 0, len = hotViews.size(); i < len; i++) {
			View view = hotViews.get(i);
			ImageView imgv = (ImageView) view
					.findViewById(R.id.item_hot_gallery_imgv);
			ImgItem item = imgItemList.get(i);
			// TODO
			if (item.icon != null && !item.icon.equals("")) {

			} else {
				imgv.setImageResource(R.drawable.bg_default);
			}
			final String url = item.url;
			imgv.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO 跳转页面webActivity

				}
			});

		}
	}

	public void initData() {
		Configure.countPages = (int) Math.ceil(lstDate.size()
				/ (float) PAGE_SIZE);
		lists = new ArrayList<ArrayList<Item>>();
		for (int i = 0; i < Configure.countPages; i++) {
			lists.add(new ArrayList<Item>());
			for (int j = PAGE_SIZE * i; j < (PAGE_SIZE * (i + 1) > lstDate
					.size() ? lstDate.size() : PAGE_SIZE * (i + 1)); j++)
				lists.get(i).add(lstDate.get(j));
		}
		// boolean isLast = true;
		// for (int i = lists.get(Configure.countPages - 1).size(); i <
		// PAGE_SIZE; i++) {
		// if (isLast) {
		// lists.get(Configure.countPages - 1).add(null);
		// isLast = false;
		// } else
		// lists.get(Configure.countPages - 1).add(new Item());
		// }
	}

	public LinearLayout addListView(int i) {
		LinearLayout v = (LinearLayout) LayoutInflater.from(this).inflate(
				R.layout.listv, null);

		listView = (ListView) v.findViewById(R.id.listv);
		listView.setAdapter(new ShelfAdapter(this, lists.get(i)));
		listviews.add(listView);
		return v;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private View getView(int layout) {
		View view = LayoutInflater.from(this).inflate(layout, null);
		return view;
	}
}
