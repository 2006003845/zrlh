package com.example.llkc_sdk_flash.ui;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.llkc_sdk_flash.beans.RankItem;
import com.zzl.zl_app.cache.ImageCache;

public class RankAdapter extends BaseAdapter {
	private List<RankItem> rankList;
	Context context;
	LayoutInflater inflater;
	private String util;// 单位
	private ItemViewInterface viewInterface;

	ImageCache imageCache;

	public void setViewInterface(ItemViewInterface viewInterface) {
		this.viewInterface = viewInterface;
	}

	public RankAdapter(Context context, List<RankItem> rankList, String util,
			ItemViewInterface viewInterface) {
		this.rankList = rankList;
		this.context = context;
		inflater = LayoutInflater.from(context);
		this.util = util;
		this.viewInterface = viewInterface;
		imageCache = ImageCache.getInstance();
	}

	@Override
	public int getCount() {
		return rankList.size();
	}

	@Override
	public Object getItem(int position) {
		return rankList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public void setRankList(List<RankItem> rankList) {
		this.rankList = rankList;
		update();
	}

	public void update() {
		this.notifyDataSetChanged();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (viewInterface != null)
			return viewInterface.getItemView(inflater, position, convertView,
					parent, rankList, util);
		return convertView;
	}

}
