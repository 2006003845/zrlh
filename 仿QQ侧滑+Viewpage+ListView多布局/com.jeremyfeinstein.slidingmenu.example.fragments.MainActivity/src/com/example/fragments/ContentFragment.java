package com.example.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.adapter.MyPagerAdapter;
import com.slidingmenu.example.R;

@SuppressLint("ValidFragment")
public class ContentFragment extends Fragment implements OnPageChangeListener  {

	private View view;
	public ViewPager viewPager;
	private MyPagerAdapter adapter;
	private SlidingMenu menu;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.layout_content, null);
		viewPager = (ViewPager) view.findViewById(R.id.first_pager);
		adapter = new MyPagerAdapter(getFragmentManager());
		viewPager.setAdapter(adapter);
		viewPager.setOnPageChangeListener(this);
		return view;
	}

	public ContentFragment(SlidingMenu menu) {
		super();
		this.menu=menu;
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {

	}

	@Override
	public void onPageSelected(int arg0) {
		if (adapter != null && adapter.getCount() > 1) {
			if (arg0 == 0) {// 第一页
				menu.setMode(SlidingMenu.LEFT);
				menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
//			} else if (arg0 == adapter.getCount() - 1) { // 最后一页
//				menu.setMode(SlidingMenu.RIGHT);
//				menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
			} else { // 中间页面
				menu.setMode(SlidingMenu.LEFT_RIGHT);
				menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
			}
		}
	}

}
