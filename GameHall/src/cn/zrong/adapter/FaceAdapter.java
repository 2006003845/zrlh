package cn.zrong.adapter;

import java.util.HashMap;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import cn.zrong.entity.Face;

public class FaceAdapter extends BaseAdapter {
	private Context context;
	private String[] faceNames;
	private HashMap<String, Integer> faces;

	public FaceAdapter(Context context, String[] faceNames) {
		this.context = context;
		this.faceNames = faceNames;
		this.faces = Face.getfaces(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return faceNames == null ? 0 : faceNames.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ImageView imageView;
		imageView = (ImageView) convertView;
		if (imageView == null) {
			// 给ImageView设置资源
			imageView = new ImageView(context);
			// 设置布局 图片120×120显示
			imageView.setLayoutParams(new GridView.LayoutParams(50, 50));
			// 设置显示比例类型
			imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
		}
		if (faces.containsKey(Face.faceNames[position])) {
			imageView.setImageResource(faces.get(Face.faceNames[position]));
		}
		return imageView;
	}

}
