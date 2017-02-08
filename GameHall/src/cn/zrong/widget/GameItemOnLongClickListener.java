package cn.zrong.widget;

import android.view.View;
import android.view.View.OnLongClickListener;
import cn.zrong.entity.GameItem;

public abstract class GameItemOnLongClickListener implements
		OnLongClickListener {
	private GameItem gameItem;

	public GameItemOnLongClickListener(GameItem gameItem) {
		this.gameItem = gameItem;
	}

	@Override
	public boolean onLongClick(View v) {
		if (gameItem != null) {
			onGameItemLongClick(gameItem);
		}
		return false;
	}

	public abstract void onGameItemLongClick(GameItem gameItem);

}
