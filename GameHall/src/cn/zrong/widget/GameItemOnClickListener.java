package cn.zrong.widget;

import android.view.View;
import android.view.View.OnClickListener;
import cn.zrong.entity.GameItem;

public abstract class GameItemOnClickListener implements OnClickListener {
	private GameItem gameItem;

	public GameItemOnClickListener(GameItem gameItem) {
		this.gameItem = gameItem;
	}

	@Override
	public void onClick(View v) {
		if (gameItem == null) {
			return;
		}
		onGameItemClick(gameItem);
	}

	public abstract void onGameItemClick(GameItem gameItem);

}
