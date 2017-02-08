/**
 * 
 */
package com.zrong.Android.online.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Vector;

import javax.microedition.io.StreamConnection;



/**
 *<p>Titile:BaseConnection</p>
 *<p>Description:</p>
 *<p>Copyright:Copyright(c)2010</p>
 *<p>Company:zrong</p>
 * @author yangzheng
 * @version 1.0
 */
public abstract class BaseConnection implements Runnable {
	/** */
	static public final int C_CONNECTION_TYPE_TCP = 1;
	/** */
    static public final int C_CONNECTION_TYPE_HTTP = 2;
    /** */
    static public final int C_PACKAGE_HEADER_LENGTH = 32;
    /** */
    static public final int C_PACKAGE_RECEIVE_HEADER_LENGTH = 28;
    /**���󻺳� */
    protected Vector requests = new Vector( 5, 5 );
    /**�������ݶ��� */
    protected ConnectionHandler handler=null;
  
    protected String URL=null;
    /** ���������߳��Ƿ��ڹ���״̬*/
    protected boolean alive;
    /**�������Ӷ��� */
    protected StreamConnection socket=null;
    /** ������*/
    protected DataInputStream is;
    /**����� */
    protected DataOutputStream os;
    
    public String headuserId = null;
    
    public String head = "CMCCGAME_userId=";
    
    public String userId;
    
    public char h = '0';

    

	/* connection�ľ�̬��������
	 * @see java.lang.Runnable#run()
	 */
	public static BaseConnection CreateConnection
	(int type,String server,ConnectionHandler receiver,String userId)
	{
		BaseConnection connection=null;
		
		
		if(type==C_CONNECTION_TYPE_TCP)
		{
			connection=new TcpConnection(server,receiver,userId);
		}
		else
		{
			
		}
		return connection;
	}
	/**
	 * ������绺����
	 */
	public void clearBuf() 
	{
        if ( !requests.isEmpty() ) 
        {
            requests.removeAllElements();
        }
    }
	
	/**
     * ���췽����ֻ��network������ͱ�������࿪��
     */
    protected BaseConnection() 
    {
    }
	/**
	 * �������紦����󣬸ö����������������������������
	 * @param handler ���紦�����
	 */
    public void setHandler( ConnectionHandler handler )
    {
        this.handler = handler;
    }
    /**
     * �ж����а������Ƿ�Ϊ��
     * @return true ��ʾ�գ�false��ʾ�ǿ�
     */
    protected boolean isRequestEmpty() 
    {
        return requests.isEmpty();
    }
    /**
     * �����绺����������а�
     * @param msg
     */
    protected synchronized void addRequest( Packet p ) 
    {
        requests.addElement( p );
        notifyAll();
    }
    /**
     * ��ȡ���绺������е�һ�����а�,�������ӻ��������ɾ��
     * @return ��һ���а�������
     * @throws InterruptedException
     */
    protected synchronized Packet getRequest() throws InterruptedException
    {
        if ( requests.isEmpty() ) 
        {
            wait();
        }
        if(!requests.isEmpty())
        {
	        Packet pak = ( Packet ) requests.elementAt( 0 );
	        requests.removeElementAt( 0 );
	        return pak;
        }
        return null;
    }
    
    /** ���ݰ����ͷ���
     * @param type int ���ݰ�����
     * @param length int ���ݰ�����
     * @param body byte[] ���ݰ�����
     * @return boolean true���ͳɹ���false����ʧ��
     */
    public abstract boolean sendMessage( short type, byte[] body ) throws Exception;
    
    /**
     * �ر��������������������������
     */
    protected void closeStreams() {
    	
        try {
            if ( is != null ) {
                is.close();
                is = null;
            }
        }catch(Exception e)
        {
        	e.printStackTrace();
        }
        finally
        {
        	is = null;
        }
        try{
            if ( os != null ) {
                os.close();
                os = null;
            }
        }catch(Exception e)
        {
        	e.printStackTrace();
        }
        finally
        {
        	os = null;
        }
        try{
            if ( socket != null ) {
                socket.close();
                socket = null;
            }
        } catch ( Exception e ) {
        	e.printStackTrace();
        }
        finally
        {
        	socket = null;
        }
    }
    /**
     * �ر������߳�
     */
    public void close()
    {
    	alive=false;
    }
    /**���������߳� */
    public void open() {
         
    }
    public void keepAlive() {
    }
    
    public void initHead ()
	{
		StringBuffer sb = new StringBuffer();
		sb.append(head);
		if(userId == null)
		{
			for(int i = 0;i < 10;i++)
			{
				sb.append(h);
			}
		}
		else
		{
			for(int i = 0;i < 10 - userId.length();i++)
			{
				sb.append(h);
			}
			sb.append(userId);
		}
		headuserId = sb.toString();
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
