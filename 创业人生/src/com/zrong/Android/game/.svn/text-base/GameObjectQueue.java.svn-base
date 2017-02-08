package com.zrong.Android.game;
import java.util.Enumeration;
import java.util.Hashtable;

import com.zrong.Android.logic.LogicObject;
/**
 * 
 *<p>Titile:GameObjectQueue</p>
 *<p>Description:对于游戏对象的管理类，提供id管理和返回所有管理的对象方法,各个类型的对象，可以通过继承这个类来实现一些特殊的功能</p>
 *<p>Copyright:Copyright(c)2010</p>
 *<p>Company:zrong</p>
 * @author yangzheng
 * @version 1.0
 * @date Jul 8, 2011
 */
public class GameObjectQueue extends Hashtable
{
	private String id=null;
	public String getId() 
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public Object find(String gameObjectId)
	{
		if (this.containsKey(gameObjectId))
		{
			return this.get(gameObjectId);
		}
		else{
			return null;
		}
	}
	public Object[] list(){
		Object[] result=new Object[this.size()];
		Enumeration enumer=this.elements();
		int i=0;
		while(enumer.hasMoreElements()){
			result[i++]=enumer.nextElement();
		}
		return result;
	}
	 
	
	
}

