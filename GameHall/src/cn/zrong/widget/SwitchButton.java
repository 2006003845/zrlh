package cn.zrong.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import cn.zrong.activity.R;

public class SwitchButton extends ImageView {

	private int[] imgResIds = new int[] { R.drawable.img_switch_off,
			R.drawable.img_switch_on };

	public SwitchButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public SwitchButton(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public SwitchButton(Context context) {
		super(context);
	}

	private boolean isOn = false;

	public boolean getSwitchState() {
		return isOn;
	}

	public void setSwitchState(boolean isOn) {
		this.isOn = isOn;
		if (isOn) {
			this.setImageResource(imgResIds[1]);
		} else {
			this.setImageResource(imgResIds[0]);
		}
		this.invalidate();
	}

}
