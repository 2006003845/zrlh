package cn.zrong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import cn.zrong.activity.R;

public class ImageAdapter extends BaseAdapter {

	private Context context;
	private ImageAdapter self;
	private LayoutInflater inflater;
	private int[] imgResIds = new int[] { R.drawable.guide01,
			R.drawable.guide03, R.drawable.guide04 };

	public ImageAdapter(Context context) {
		this.context = context;
		this.self = this;
		inflater = (LayoutInflater) context
				.getSystemService(android.content.Context.LAYOUT_INFLATER_SERVICE);
	}

	public int getCount() {
		return imgResIds.length;
	}

	public Object getItem(int position) {
		return imgResIds[position];
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if (view == null) {
			view = inflater.inflate(R.layout.imgv_item, null);
		}
		ImageView imgV = (ImageView) view.findViewById(R.id.imgv_item_imgv);
		imgV.setImageResource(imgResIds[position]);

		return view;
	}
}
