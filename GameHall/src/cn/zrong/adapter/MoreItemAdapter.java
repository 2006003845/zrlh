package cn.zrong.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import cn.zrong.activity.R;
import cn.zrong.entity.MoreItem;

import cn.zrong.entity.MoreItem;

import cn.zrong.entity.MoreItem;

import cn.zrong.entity.MoreItem;

public class MoreItemAdapter extends BaseAdapter {
	private List<MoreItem> itemList;
	private Context context;
	private LayoutInflater inflater;

	public MoreItemAdapter(Context context, List<MoreItem> itemList) {
		this.context = context;
		this.itemList = itemList;
		inflater = (LayoutInflater) context
				.getSystemService(android.content.Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {

		return itemList.size();
	}

	@Override
	public Object getItem(int position) {

		return itemList.get(position);
	}

	@Override
	public long getItemId(int position) {

		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if (view == null) {
			view = inflater.inflate(R.layout.home_more_gridv_item, null);
		}
		ImageView imgv = (ImageView) view
				.findViewById(R.id.home_more_item_imgv);
		TextView nameTV = (TextView) view.findViewById(R.id.home_more_item_tv);
		MoreItem item = itemList.get(position);
		imgv.setImageResource(item.imgResId);
		nameTV.setText(item.nameResId);
		return view;
	}

}
