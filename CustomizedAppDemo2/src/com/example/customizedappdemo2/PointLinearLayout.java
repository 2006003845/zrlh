package com.example.customizedappdemo2;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

public class PointLinearLayout extends LinearLayout {

	private int[] pointImgResIds = { R.drawable.icon_point_unselected,
			R.drawable.icon_point_selected };
	private ImageView[] imgvs;

	public PointLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);

	}

	public PointLinearLayout(Context context) {
		super(context);

	}

	int count = 0;

	public void addViews(Context context, int count,int top) {
		this.count = count;

		// lp.gravity = Gravity.CENTER;
		imgvs = new ImageView[count];
		for (int i = 0; i < count; i++) {
			LayoutParams lp = new LinearLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.FILL_PARENT);
			lp.setMargins(15, top, 0, 9);
			ImageView imgv = new ImageView(context);
			imgv.setScaleType(ScaleType.CENTER_INSIDE);
			imgv.setImageResource(pointImgResIds[0]);
			imgv.setLayoutParams(lp);
			imgvs[i] = imgv;
			this.addView(imgv);
		}
		imgvs[0].setImageResource(pointImgResIds[1]);
	}

	public void setPageIndex(int index) {
		if (index > count || index < 0) {
			return;
		}
		for (int i = 0; i < count; i++) {
			imgvs[i].setImageResource(pointImgResIds[0]);
		}
		imgvs[index].setImageResource(pointImgResIds[1]);
		this.postInvalidate();
	}

}
