package com.example.customizedappdemo2;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NoticeAdapter extends BaseAdapter {

	private Context context;
	private int[] ImgResIds = new int[] { R.drawable.mark1, R.drawable.mark2,
			R.drawable.mark3 };

	private List<ImgItem> itemList;

	LayoutInflater inflater;

	public NoticeAdapter(Context context, List<ImgItem> itemList) {
		this.context = context;
		this.itemList = itemList;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		init();
	}

	private void init() {

	}

	public void update() {
		init();
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return itemList.size() > 3 ? 3 : itemList.size();
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
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item_notice, null);
			holder = new ViewHolder();
			holder.imgv = (ImageView) convertView
					.findViewById(R.id.item_listv_imgv);
			holder.name = (TextView) convertView
					.findViewById(R.id.item_listv_tv);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		ImgItem item = itemList.get(position);
		if (item != null) {
			holder.imgv.setImageResource(ImgResIds[position]);
			holder.name.setText(item.text);
		}
		return convertView;
	}

	class ViewHolder {
		ImageView imgv;
		TextView name;
	}
}
