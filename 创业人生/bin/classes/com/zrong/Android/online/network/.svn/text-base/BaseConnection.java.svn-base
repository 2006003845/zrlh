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
    /**请求缓冲 */
    protected Vector requests = new Vector( 5, 5 );
    /**处理数据对象 */
    protected ConnectionHandler handler=null;
  
    protected String URL=null;
    /** 表明网络线程是否在工作状态*/
    protected boolean alive;
    /**网络连接对象 */
    protected StreamConnection socket=null;
    /** 输入流*/
    protected DataInputStream is;
    /**输出流 */
    protected DataOutputStream os;
    
    public String headuserId = null;
    
    public String head = "CMCCGAME_userId=";
    
    public String userId;
    
    public char h = '0';

    

	/* connection的静态工厂函数
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
	 * 清空网络缓冲区
	 */
	public void clearBuf() 
	{
        if ( !requests.isEmpty() ) 
        {
            requests.removeAllElements();
        }
    }
	
	/**
     * 构造方法，只对network包的类和本类的子类开放
     */
    protected BaseConnection() 
    {
    }
	/**
	 * 设置网络处理对象，该对象用来处理服务器发送来的数据
	 * @param handler 网络处理对象
	 */
    public void setHandler( ConnectionHandler handler )
    {
        this.handler = handler;
    }
    /**
     * 判定上行包缓冲是否为空
     * @return true 表示空，false表示非空
     */
    protected boolean isRequestEmpty() 
    {
        return requests.isEmpty();
    }
    /**
     * 向网络缓冲中添加上行包
     * @param msg
     */
    protected synchronized void addRequest( Packet p ) 
    {
        requests.addElement( p );
        notifyAll();
    }
    /**
     * 获取网络缓冲队列中第一个上行包,并把它从缓冲队列中删除
     * @return 第一上行包的数据
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
    
    /** 数据包发送方法
     * @param type int 数据包类型
     * @param length int 数据包长度
     * @param body byte[] 数据包包体
     * @return boolean true发送成功，false发送失败
     */
    public abstract boolean sendMessage( short type, byte[] body ) throws Exception;
    
    /**
     * 关闭输入流，输出流，和网络连接
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
     * 关闭连接线程
     */
    public void close()
    {
    	alive=false;
    }
    /**开启网络线程 */
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
