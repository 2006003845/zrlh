package cn.zrong.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;
import cn.zrong.activity.R;

public class StarsImageView extends ImageView {
	private int[] starImgResIds = new int[] { R.drawable.img_star_null,
			R.drawable.img_star_half, R.drawable.img_star_all };
	private Bitmap[] starsBitms = new Bitmap[3];

	private int starNum = -1;
	private Paint p;

	private int offset_x = 2;
	private int offset_y = 11;

	public StarsImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public StarsImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public StarsImageView(Context context) {
		super(context);
		init(context);
	}

	private void init(Context context) {
		p = new Paint();
		p.setAntiAlias(true);
		for (int i = 0; i < 3; i++) {
			starsBitms[i] = BitmapFactory.decodeResource(
					context.getResources(), starImgResIds[i]);
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (starNum == -1) {
			return;
		}
		drawStars(canvas);
	}

	private void drawStars(Canvas canvas) {
		if (starNum % 2 == 0) {
			for (int i = 0; i < starNum / 2; i++) {
				canvas.drawBitmap(starsBitms[2],
						offset_x + starsBitms[2].getWidth() * i, offset_y, p);
			}
			for (int i = starNum / 2; i < 5; i++) {
				canvas.drawBitmap(starsBitms[0],
						offset_x + starsBitms[0].getWidth() * i, offset_y, p);
			}
		} else {
			int num = starNum / 2;
			int f = -1;
			for (int i = 0; i < num; i++) {
				canvas.drawBitmap(starsBitms[2],
						offset_x + starsBitms[2].getWidth() * i, offset_y, p);
				f = offset_x + starsBitms[2].getWidth() * i;
			}

			canvas.drawBitmap(starsBitms[1], f + starsBitms[1].getWidth(),
					offset_y, p);
			for (int i = starNum / 2 + 1; i < 5; i++) {
				canvas.drawBitmap(starsBitms[0],
						offset_x + starsBitms[0].getWidth() * (i + 1),
						offset_y, p);
			}
		}

	}

	public void setStarNum(float starNum) {
		this.starNum = Math.round(starNum * 2);
		if (this.starNum > 10) {
			this.starNum = 10;
		}
		update();
	}

	public void update() {
		this.postInvalidate();
	}

}
