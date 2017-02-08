package com.slidingmenu.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jeremyfeinstein.slidingmenu.custom.Fragment_A;
import com.jeremyfeinstein.slidingmenu.custom.Fragment_B;
import com.jeremyfeinstein.slidingmenu.custom.Fragment_C;

public class MyPagerAdapter extends FragmentPagerAdapter{

	public MyPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int arg0) {
		switch (arg0) {
		case 0:
			return new Fragment_A();
		case 1:
			return new Fragment_B();
		case 2:
			return new Fragment_C();
		default:
			break;
		}
		return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 3;
	}


}
