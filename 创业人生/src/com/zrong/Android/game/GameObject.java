package com.zrong.Android.game;

import java.util.Hashtable;
import java.util.Vector;

/**
 * 
 *<p>Titile:GameObject</p>
 *<p>Description:</p> 游戏对象类，所有的游戏对象都需要继承这个类，但不包括view和view的子类,他们单独有android的viewGruop类来管理
 *<p>Copyright:Copyright(c)2010</p>
 *<p>Company:zrong</p>
 * @author yangzheng
 * @version 1.0
 */
public abstract class GameObject
{
	/**
	 * 这个id默认是类名，如果特殊需要可以自己设置
	 */
	private String id=this.getClass().getSimpleName();
	
	//private String completeId=this.getClass().getName();

	
	public String getId() 
	{
		return id;
	}
	public void setId(String id) 
	{
		this.id = id;
	}
	/**
	 * 统一的属性加载方法，需要每个子类自己去实现
	 * @param v
	 */
	public abstract void loadProperties(Vector v);
	
	/**
	 * 覆盖toString方法，返回这个类的id(默认就是类的名字)
	 */
	public String toString()
	{
		return ("id="+id);
	}
	/**
	 * 回收资源
	 *
	 */
	protected abstract void reCycle();	
	
	/**
	 * 刷新
	 *
	 */
	protected abstract void refurbish();
}

