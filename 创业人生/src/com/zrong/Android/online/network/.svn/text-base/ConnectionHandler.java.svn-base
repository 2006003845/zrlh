/**
 * 
 */
package com.zrong.Android.online.network;

/**
 *<p>Titile:ConnectionHandler</p>
 *<p>Description:</p>
 *<p>Copyright:Copyright(c)2010</p>
 *<p>Company:zrong</p>
 * @author yangzheng
 * @version 1.0
 */
public interface ConnectionHandler {
	/**
	 * 接受数据包方法
	 * @param type 收到的数据包类型
	 * @param length 包体长度
	 * @param body 数据包体
	 * @return true成功 false失败
	 */
	public boolean recvMessage( int type, int length, byte[] body );
	/**
	 * 网络连接超时
	 */
    public void connectionTimeout();
    /**
     * 开启网络连接
     */
    public void connectionCreated();
    /**
     * 断开网络连接
     */
    public void connectionBroken();
    /**
     * 取消联网
     */
    public void ConnectionCancel();

    /**
     * 重新创建连接
     */
    public void connectionReCreated();
    
    /**
     * 
     */
    public void connectionConnected();
}
