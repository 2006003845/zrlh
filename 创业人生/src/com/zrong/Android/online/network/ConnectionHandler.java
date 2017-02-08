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
	 * �������ݰ�����
	 * @param type �յ������ݰ�����
	 * @param length ���峤��
	 * @param body ���ݰ���
	 * @return true�ɹ� falseʧ��
	 */
	public boolean recvMessage( int type, int length, byte[] body );
	/**
	 * �������ӳ�ʱ
	 */
    public void connectionTimeout();
    /**
     * ������������
     */
    public void connectionCreated();
    /**
     * �Ͽ���������
     */
    public void connectionBroken();
    /**
     * ȡ������
     */
    public void ConnectionCancel();

    /**
     * ���´�������
     */
    public void connectionReCreated();
    
    /**
     * 
     */
    public void connectionConnected();
}
