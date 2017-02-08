package com.zrong.Android.Util;

import java.util.Vector;

import android.os.Message;
import android.util.Log;

import com.zrong.Android.game.GameData;
import com.zrong.Android.game.GameDefinition;

/**
 * 
 *<p>Titile:GameAPI</p>
 *<p>Description:this is a GameAPI used by this game can not used by another game</p>
 *<p>Copyright:Copyright(c)2010</p>
 *<p>Company:zrong</p>
 * @author YangZheng
 * @version 1.0
 * @date 2012-3-8
 */
public class GameAPI
{
	/**
	 * 
	 * <p>Description: 展示toast</p>
	 * @param msg toast中得内容
	 */
	public static void showToast(String msg)
	{
		Message message = Message.obtain();
		
		message.what = GameDefinition.Message_showToast;
		Vector v = new Vector();
		v.addElement(msg==null?"":msg);
		message.obj = v;
		GameData.mhandler.sendMessage(message);
	}
	
}
