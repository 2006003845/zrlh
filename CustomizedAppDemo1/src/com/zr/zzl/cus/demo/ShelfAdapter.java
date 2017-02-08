package com.zr.zzl.cus.demo;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ShelfAdapter extends BaseAdapter {

	private Context context;

	private List<Item> itemList;

	private int num;
	LayoutInflater inflater;

	public ShelfAdapter(Context context, List<Item> itemList) {
		this.context = context;
		this.itemList = itemList;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		init();
	}

	private void init() {
		num = (int) Math.ceil(itemList.size() / (float) 2);
	}

	public void update() {
		init();
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return num > 2 ? 2 : num;
	}

	@Override
	public Object getItem(int position) {

		return null;
	}

	@Override
	public long getItemId(int position) {

		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item_shelf, null);
			holder = new ViewHolder();
			holder.layout1 = (LinearLayout) convertView
					.findViewById(R.id.item_shelf_layout1);
			holder.imgv1 = (ImageView) convertView
					.findViewById(R.id.item_shelf_imgv1);
			holder.name1 = (TextView) convertView
					.findViewById(R.id.item_shelf_tv1);
			holder.layout2 = (LinearLayout) convertView
					.findViewById(R.id.item_shelf_layout2);
			holder.imgv2 = (ImageView) convertView
					.findViewById(R.id.item_shelf_imgv2);
			holder.name2 = (TextView) convertView
					.findViewById(R.id.item_shelf_tv2);
			holder.layout3 = (LinearLayout) convertView
					.findViewById(R.id.item_shelf_layout3);
			holder.imgv3 = (ImageView) convertView
					.findViewById(R.id.item_shelf_imgv3);
			holder.name3 = (TextView) convertView
					.findViewById(R.id.item_shelf_tv3);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		int pos = position * 3;
		if (pos < itemList.size()) {
			holder.layout1.setVisibility(View.VISIBLE);
			final Item item1 = itemList.get(pos);
			if (item1 != null) {
				if (item1.icon != null && !item1.icon.equals("")) {
				} else {
					holder.imgv1.setImageResource(R.drawable.img);
				}
				String name = item1.name == null ? "" : item1.name;
				holder.name1.setText(name);
				holder.layout1.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						String url = item1.url;
						// TODO 跳转页面 webActivity
						Toast.makeText(context, url, Toast.LENGTH_SHORT).show();
					}
				});
			} else {
				holder.layout1.setVisibility(View.GONE);
			}
		} else {
			holder.layout1.setVisibility(View.GONE);
		}
		if (pos + 1 < itemList.size()) {
			holder.layout2.setVisibility(View.VISIBLE);
			final Item item2 = itemList.get(pos + 1);
			if (item2 != null) {
				if (item2.icon != null && !item2.icon.equals("")) {
					// TODO
				} else {
					holder.imgv2.setImageResource(R.drawable.img);
				}
				String name = item2.name == null ? "" : item2.name;
				holder.name2.setText(name);
				holder.layout2.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						String url = item2.url;
						// TODO 跳转页面 webActivity
						Toast.makeText(context, url, Toast.LENGTH_SHORT).show();
					}
				});
			} else {
				holder.layout2.setVisibility(View.GONE);
			}
		} else {
			holder.layout2.setVisibility(View.GONE);
		}
		if (pos + 2 < itemList.size()) {
			final Item item3 = itemList.get(pos + 2);

			holder.layout3.setVisibility(View.VISIBLE);
			if (item3 != null) {
				if (item3.icon != null && !item3.icon.equals("")) {
					// TODO
				} else {
					holder.imgv3.setImageResource(R.drawable.img);
				}
				String name = item3.name == null ? "" : item3.name;
				holder.name3.setText(name);
				holder.layout3.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						String url = item3.url;
						// TODO 跳转页面 webActivity
						Toast.makeText(context, url, Toast.LENGTH_SHORT).show();
					}
				});
			} else {
				holder.layout3.setVisibility(View.GONE);
			}
		} else {
			holder.layout3.setVisibility(View.GONE);
		}

		return convertView;
	}

	class ViewHolder {
		LinearLayout layout1, layout2, layout3;
		ImageView imgv1;
		TextView name1;
		ImageView imgv2;
		TextView name2;
		ImageView imgv3;
		TextView name3;
	}
}
