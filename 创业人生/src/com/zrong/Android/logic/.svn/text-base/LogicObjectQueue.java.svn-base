package com.zrong.Android.logic;

import android.content.Context;

import com.zrong.Android.game.GameDefinition;
import com.zrong.Android.game.GameGroupControl;
import com.zrong.Android.game.GameObjectQueue;

public class LogicObjectQueue extends GameObjectQueue {

	private Context context;

	private GameGroupControl control;

	public LogicObjectQueue(Context context, GameGroupControl control) {
		this.context = context;

		this.control = control;
	}

	public synchronized LogicObject get(Object key) {
		return (LogicObject) super.get(key);
	}

	public synchronized LogicObject get(Object key, int status) {
		LogicObject lgobj = (LogicObject) super.get(key);

		if (lgobj == null) {
			switch (status) {
			case GameDefinition.Game_Logo:
				lgobj = new Logo(context, control);
				break;
			case GameDefinition.Game_MainMenu:
				lgobj = new MainMenu(context, control);
				break;
			case GameDefinition.Game_Login:
				lgobj = new Login(context, control);
				break;
			case GameDefinition.Game_SelectSever:
				lgobj = new SelectSever(context, control);
				break;
			case GameDefinition.Game_Loading:
				lgobj = new Loading(context, control);
				break;
			case GameDefinition.Game_CreateCharactor:
				lgobj = new CreateCharactor(context, control);
				break;
			case GameDefinition.Game_TipBox:
				lgobj = new TipBox(context, control);
				break;
			case GameDefinition.Game_Office:
				lgobj = new Office(context, control);
				break;
			case GameDefinition.Game_Map:
				lgobj = new Map(context, control);
				break;

			case GameDefinition.Game_CreateBuilding:
				lgobj = new CreateBuilding(context, control);
				break;
			case GameDefinition.Game_ShopInfo:
				lgobj = new ShopInfo(context, control);
				break;
			}

			lgobj.init();

			put(key, lgobj);
		}

		return lgobj;
	}

	@Override
	public synchronized void clear() {
		control.removeAllViews();
		super.clear();
	}

	@Override
	public synchronized Object remove(Object key) {

		LogicObject logic = this.get(key);
		if (logic != null) {
			control.removeView(logic.getView());
			return super.remove(key);
		}
		return null;
	}

}
