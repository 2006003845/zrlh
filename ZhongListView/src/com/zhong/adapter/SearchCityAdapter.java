package com.zhong.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.zhong.bean.City;
import com.zhong.ui.R;

/**
 * 搜索框的内容适配器
 */
public class SearchCityAdapter extends BaseAdapter implements Filterable {

	private List<City> mAllCities;
	private List<City> mResultCities;
	private LayoutInflater mInflater;
	private Context mContext;

	public SearchCityAdapter(Context context, List<City> allCities) {
		mContext = context;
		mAllCities = allCities;
		mResultCities = new ArrayList<City>();
		mInflater = LayoutInflater.from(mContext);
	}

	@Override
	public int getCount() {
		return mResultCities.size();
	}

	@Override
	public City getItem(int position) {
		return mResultCities.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.search_city_item, null);
		}
		TextView cityTv = (TextView) convertView.findViewById(R.id.column_title);
		cityTv.setText(mResultCities.get(position).getNa());
		return convertView;
	}

	@Override
	public Filter getFilter() {
		Filter filter = new Filter() {

			protected void publishResults(CharSequence constraint, FilterResults results) {
				mResultCities = (ArrayList<City>) results.values;
				if (results.count > 0) {
					notifyDataSetChanged();
				} else {
					notifyDataSetInvalidated();
				}
			}

			protected FilterResults performFiltering(CharSequence s) {
				String str = s.toString().toUpperCase();
				// mFilterStr = str;
				FilterResults results = new FilterResults();
				ArrayList<City> cityList = new ArrayList<City>();
				if (mAllCities != null && mAllCities.size() != 0) {
					for (City cb : mAllCities) {
						// 匹配全屏、首字母、和城市名中文
						if (cb.getAs().indexOf(str) > -1 || cb.getAs().indexOf(str) > -1 || cb.getNa().indexOf(str) > -1) {
							cityList.add(cb);
						}
					}
				}
				results.values = cityList;
				results.count = cityList.size();
				return results;
			}
		};
		return filter;
	}

}
