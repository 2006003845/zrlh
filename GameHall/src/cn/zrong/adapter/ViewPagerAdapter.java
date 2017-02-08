package cn.zrong.adapter;

import java.util.List;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

public class ViewPagerAdapter extends PagerAdapter
{
	  private List<View> views;  
	  private List<String> title;
	  private Context context;
	  
	  public ViewPagerAdapter(Context context, List<View> list,List<String> title)
	  {
		  this.context = context;
		  this.views = list;
		  this.title = title;
	  }
	@Override
	public int getCount()
	{
		// TODO Auto-generated method stub
		return views.size();
	}

	@Override
	public boolean isViewFromObject(View view, Object object)
	{
		// TODO Auto-generated method stub
		return view ==object;
	}

	@Override
	public void destroyItem(View container, int position, Object object)
	{
		// TODO Auto-generated method stub
		((ViewPager)container).removeView((View)object);
	}

	@Override
	public void finishUpdate(View container)
	{
		// TODO Auto-generated method stub
		 
	}

	@Override
	public CharSequence getPageTitle(int position)
	{
		// TODO Auto-generated method stub
		return this.title.get(position);
	}

	@Override
	public Object instantiateItem(View container, int position)
	{
		// TODO Auto-generated method stub
//		((ViewPager) container).addView(mListViews.get(position),0);
//		return mListViews.get(arg1);
		((ViewPager) container).addView(views.get(position));
		return views.get(position);
	}

	@Override
	public void restoreState(Parcelable state, ClassLoader loader)
	{
		// TODO Auto-generated method stub
		 
	}

	@Override
	public Parcelable saveState()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void startUpdate(View container)
	{
		// TODO Auto-generated method stub
		
	}
	
	

}
