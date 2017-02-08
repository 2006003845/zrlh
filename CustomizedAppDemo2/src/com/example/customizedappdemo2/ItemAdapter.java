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

public class ItemAdapter extends BaseAdapter {

	private Context context;

	private List<Item> itemList;

	LayoutInflater inflater;

	public ItemAdapter(Context context, List<Item> itemList) {
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
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item_gridv, null);
			holder = new ViewHolder();
			holder.imgv = (ImageView) convertView
					.findViewById(R.id.item_gridv_imgv);
			holder.name = (TextView) convertView
					.findViewById(R.id.item_gridv_tv);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		Item item = itemList.get(position);
		if (item != null) {
			if (item.icon != null && !item.icon.equals("")) {
				// Bitmap icon =
				// ImageCache.getInstance().getImgCach2(mContext,item.icon);
				// if (icon == null) {
				// ImageCache.getInstance().loadImg(context, item.icon,
				// holder.imgv,R.drawable.img);
				// }else{
				// holder.imgv.setImageBitmap(icon);
				// }
			} else {
				holder.imgv.setImageResource(R.drawable.img);
			}
			holder.name.setText(item.name);
		}
		return convertView;
	}

	class ViewHolder {
		ImageView imgv;
		TextView name;
	}
}
