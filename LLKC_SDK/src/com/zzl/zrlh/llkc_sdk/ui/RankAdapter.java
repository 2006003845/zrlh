package com.zzl.zrlh.llkc_sdk.ui;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zzl.zl_app.cache.ImageCache;
import com.zzl.zrlh.llkc_sdk.R;
import com.zzl.zrlh.llkc_sdk.beans.RankItem;

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
		else
			return defaultViewInterface.getItemView(inflater, position,
					convertView, parent, rankList, util);
	}

	private ItemViewInterface defaultViewInterface = new ItemViewInterface() {

		@Override
		public View getItemView(LayoutInflater inflater, int position,
				View convertView, ViewGroup parent, List<RankItem> itemList,
				String util) {
			ViewHolder holder = null;
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.dialog_rank_item, null);
				holder = new ViewHolder();
				holder.num = (TextView) convertView
						.findViewById(R.id.item_rank_num);
				holder.head = (ImageView) convertView
						.findViewById(R.id.item_rank_head);
				holder.name = (TextView) convertView
						.findViewById(R.id.item_rank_name);
				holder.score = (TextView) convertView
						.findViewById(R.id.item_rank_score);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			RankItem item = itemList.get(position);
			holder.num.setText(item.getRanking() + "");
			holder.name.setText(item.getUname());
			holder.head.setTag(item.getHead());
			imageCache.loadImg(item.getHead(), item.getHead(), parent,
					R.drawable.llkc_icon);
			holder.score.setText(item.getScore() + util);
			return convertView;
		}

		class ViewHolder {
			TextView num;
			ImageView head;
			TextView name;
			TextView score;
		}
	};

}
