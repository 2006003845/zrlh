package com.krislq.sliding.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.actionbarsherlock.app.ActionBar;
import com.don.fragment.FlipViewFragment;
import com.krislq.sliding.DemoFragmentAdapter;
import com.krislq.sliding.MainActivity;
import com.krislq.sliding.R;
import com.slidingmenu.lib.SlidingMenu;
/**
 * menu fragment ，主要是用于显示menu菜单
 * @author <a href="mailto:kris@krislq.com">Kris.lee</a>
 * @since Mar 12, 2013
 * @version 1.0.0
 * dong
 * @since 2013年3月17日 00:00:44
 * @version 1.0.1
 * 为了兼容改成fragment
 * 
 */
public class MenuFragment extends Fragment implements OnClickListener{
    private int index = -1;
    private ViewPager mViewPager = null;
    private FrameLayout mFrameLayout = null;
    private MainActivity   mActivity = null;
    private View contextView=null;
    private FragmentManager fragmentManager=null;
    private ContentFragment contentFragmen=null;
    private FlipViewFragment flipViewFragment=null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mActivity = (MainActivity)getActivity();
        mViewPager = (ViewPager)mActivity.findViewById(R.id.viewpager);
        mFrameLayout = (FrameLayout)mActivity.findViewById(R.id.content);
//        //set the preference xml to the content view
//        addPreferencesFromResource(R.xml.menu);
        //add listener
//        findPreference("a").setOnPreferenceClickListener(this);
//        findPreference("b").setOnPreferenceClickListener(this);
//        findPreference("n").setOnPreferenceClickListener(this);
    }
    

    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
    	contextView=inflater.inflate(R.layout.list_menu, container,false);
    	LinearLayout layout=(LinearLayout)contextView.findViewById(R.id.listMenu_layout);
    	for(int i=0;i<layout.getChildCount();i++)
    		layout.getChildAt(i).setOnClickListener(this);
		return contextView;
	}
	
    private SlidingMenu getSlidingMenu() {
        return mActivity.getSlidingMenu();
    }
    
    ViewPager.SimpleOnPageChangeListener onPageChangeListener = new ViewPager.SimpleOnPageChangeListener() {
        @Override
        public void onPageSelected(int position) {
            mActivity.getSupportActionBar().setSelectedNavigationItem(position);
            switch (position) {
                case 0:
                    getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
                    break;
                default:
                    getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
                    break;
            }
        }

    };
    
    ActionBar.TabListener tabListener = new ActionBar.TabListener() {
        @Override
        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
            if (mViewPager.getCurrentItem() != tab.getPosition())
                mViewPager.setCurrentItem(tab.getPosition());
        }

        @Override
        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

        }

        @Override
        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

        }
    };
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch (v.getId()) {
		case R.id.listMenu_1:
			 if(index == 1) {
				    mActivity.getSlidingMenu().toggle();
	                return;
	            }
	            //otherwise , replace the content view via a new Content fragment
	            index = 1;
	            mFrameLayout.setVisibility(View.VISIBLE);
	            mViewPager.setVisibility(View.GONE);
	            mActivity.getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
	            FragmentManager fragmentManager = mActivity.getSupportFragmentManager();
	            ContentFragment contentFragment = (ContentFragment)fragmentManager.findFragmentByTag("A");
	            fragmentManager.beginTransaction()
	            .replace(R.id.content, contentFragment == null ?new ContentFragment("Menu:Fragment#First"):contentFragment ,"A")
	            .commit();
			break;
		case R.id.listMenu_2:
			 if(index == 2) {
	                mActivity.getSlidingMenu().toggle();
	                return;
	            }
	            index = 2;
	            mFrameLayout.setVisibility(View.GONE);
	            mViewPager.setVisibility(View.VISIBLE);
	            
	            DemoFragmentAdapter demoFragmentAdapter = new DemoFragmentAdapter(mActivity.getSupportFragmentManager());
	            mViewPager.setOffscreenPageLimit(5);
	            mViewPager.setAdapter(demoFragmentAdapter);
	            mViewPager.setOnPageChangeListener(onPageChangeListener);
	            
	            ActionBar actionBar = mActivity.getSupportActionBar();
	            actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	            actionBar.removeAllTabs();
	            actionBar.addTab(actionBar.newTab()
	                    .setText("First Tab")
	                    .setTabListener(tabListener));

	            actionBar.addTab(actionBar.newTab()
	                    .setText("Second Tab")
	                    .setTabListener(tabListener));
	            actionBar.addTab(actionBar.newTab()
	                    .setText("Third Tab")
	                    .setTabListener(tabListener));
			break;
		case R.id.listMenu_3:
			 if(index == 3) {
	                mActivity.getSlidingMenu().toggle();
	                return;
	            }
	            index = 3;
	            mFrameLayout.setVisibility(View.VISIBLE);
	            mViewPager.setVisibility(View.GONE);
	            mActivity.getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
	            fragmentManager = mActivity.getSupportFragmentManager();
	            contentFragment = (ContentFragment)fragmentManager.findFragmentByTag("C");
	            fragmentManager.beginTransaction()
	            .replace(R.id.content, contentFragment == null ? new ContentFragment("This is N Menu"):contentFragment,"C")
	            .commit();
			break;
		case R.id.listMenu_4:
			 if(index == 4) {
	                mActivity.getSlidingMenu().toggle();
	                return;
	            }
	            index = 4;
	            mFrameLayout.setVisibility(View.VISIBLE);
	            mViewPager.setVisibility(View.GONE);
	            mActivity.getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
	            fragmentManager = mActivity.getSupportFragmentManager();
	            flipViewFragment = (FlipViewFragment)fragmentManager.findFragmentByTag("flipView");
	            fragmentManager.beginTransaction()
	            .replace(R.id.content, flipViewFragment == null ? new FlipViewFragment():flipViewFragment,"flipView")
	            .commit();
			break;
		default:
			break;
		}
	    mActivity.getSlidingMenu().toggle();
	}
}
