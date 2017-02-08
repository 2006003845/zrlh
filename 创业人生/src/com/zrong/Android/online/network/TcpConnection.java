/**
 * 
 */
package com.zrong.Android.online.network;



 

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;

import com.zrong.Android.activity.MainActivity;
import com.zrong.Android.activity.R;
import com.zrong.Android.game.GameDef;
import com.zrong.Android.game.GameDefinition;

import android.util.Log;






/**
 *<p>Titile:TcpConnection</p>
 *<p>Description:</p>
 *<p>Copyright:Copyright(c)2010</p>
 *<p>Company:zrong</p>
 * @author yangzheng
 * @version 1.0
 */
public class TcpConnection extends BaseConnection {
	
	public static final String TAG = "TcpConnection";
	 /**��ȡ�����߳� */
	 private ReaderThread rthread;
	 /**���������߳� */
	 private WriterThread wthread;
	 
	 /**��ʾ���������Ƿ�ر� */
	 private boolean isClosing;
	
	/**
	 * Tcp���캯��
	 * @param server ������ip
	 * @param port ������port
	 * @param recver ���ݴ�����
	 */
	TcpConnection(String server,ConnectionHandler recver,String userId)
	{
		handler=recver;
		this.userId = userId;
		setServiceInfo(server);
		initHead();
		open();
	}
	/**
	 * ���÷�������ַ
	 * @param ip
	 * @param port
	 */
	 public void setServiceInfo( String ip ){
		 	StringBuffer sname = new StringBuffer();
//	        sname.append( "socket://" );
	        sname.append( ip );
	        URL = sname.toString();
	 }
	
	/** 
	 * �������߳�
	 */
	 
	public void open() {
	        super.open();
	        new Thread( this ).start();
	        //#ifdef Debug
	        System.out.println("open");
	        //#endif
	 }
	 
	 
	public synchronized void close(){
		 	System.out.println("=================�ر�����");
		 	super.close();
	        isClosing = true;
	        notifyAll();
	}
	 
	public void closeTCPConnection()
	{
		System.out.println("=================�ر�socket����");

        
	}
	 /**����socket�����߳� */
	public void run() {
		if(openSocket())
		{
			isClosing = false;
			rthread = new ReaderThread();
			wthread = new WriterThread();
		}
	}
	/**��ʼ��socket */
	private boolean openSocket(){
		try {
			
            socket = ( StreamConnection ) Connector.open(URL);

            is = socket.openDataInputStream();
            os = socket.openDataOutputStream();
            

            byte[] b = headuserId.getBytes();
        	os.write(b);
          
           String _agentCodeHi = GameDef.agentCodeHi ;
           String _agentCodeLo = GameDef.agentCodeLo ;
           
           System.out.println("hi=="+_agentCodeHi);
           System.out.println("lo=="+_agentCodeLo);
           Log.v("socket", "url="+URL+",hi="+_agentCodeHi+",lo="+_agentCodeLo);
            if(_agentCodeHi.indexOf(",")!=-1)
            {
            	while(!_agentCodeHi.equals(""))
            	{
            		int _start = 0;
                	int _end = _agentCodeHi.indexOf(",");
                	if(_end!=-1)
                	{
                		String str = _agentCodeHi.substring(_start, _end) ;
                    	os.write(Integer.parseInt(_agentCodeHi.substring(_start, _end)));
                    	_agentCodeHi =_agentCodeHi.substring(_end+1);
                	}
                	else
                	{
                		os.write(Integer.parseInt(_agentCodeHi));
                		_agentCodeHi = "";
                	}
            	}
            	
            }
            else
            {
            	os.write(Integer.parseInt(_agentCodeHi));
            }
            
            if(_agentCodeLo.indexOf(",")!=-1)
            {
            	while(!_agentCodeLo.equals(""))
            	{
            		int _start = 0;
                	int _end = _agentCodeLo.indexOf(",");
                	if(_end!=-1)
                	{
                		String str = _agentCodeLo.substring(_start, _end) ;
                    	os.write(Integer.parseInt(_agentCodeLo.substring(_start, _end)));
                    	_agentCodeLo =_agentCodeLo.substring(_end+1);
                	}
                	else
                	{
                		os.write(Integer.parseInt(_agentCodeLo));
                		_agentCodeLo = "";
                	}
            	}
            	
            }
            else
            {
            	os.write(Integer.parseInt(_agentCodeLo));
            }
            
            System.out.println("��������ɹ�=="+URL);
            alive = true;
            
            handler.connectionCreated();
            
            return true;
        } 
		catch(SecurityException se)
		{
			se.printStackTrace();
			 handler.connectionBroken();
			return false;
		}
		catch ( IOException ex ) {
            ex.printStackTrace(); 
            handler.connectionBroken();
            return false;
        }
		catch( Exception e)
		{
			 handler.connectionBroken();
			e.printStackTrace();
			return false;
		}
	}
	
	/** 
	 * ������ɵ����ݰ�,���뻺���У��ȴ�����
	 */
	
	public boolean sendMessage(short type, byte[] body) throws Exception {
		  Packet p = packageMessage( type, body );
		  if(p==null)
		  {
			  throw new Exception(MainActivity.resources.getString(R.string.tcpconnection_info));
		  }
	      addRequest( p );
	      return true;
	}
	 /** 
	  * �������ݰ�
	  * @param type
	  * @param body
	  * @return
	  * @throws Exception
	  */
	 public Packet packageMessage( short type, byte[] body ) throws Exception{
		 Packet p=new Packet(type,body);
		 return p;
	 }
	/**
	 * ��ȡ����
	 */
	 private void reading(){
        while ( alive ) {
            issueRead();
        }
        this.closeStreams();
        if ( !isClosing ){//���û�йر����磬˵����ʱ���緢���쳣��,��Ҫ����
        	 System.out.println("readingOpen");
        	 clearBuf();
        	 handler.connectionBroken();
        	 //handler.connectionReCreated();
        }
    }
	
	/** 
	 * ��������
	 * @throws InterruptedException
	 */
    private void sending() throws InterruptedException{
        while ( alive ) {
        	snooze( 200 );
            Packet pak = getRequest();
            if ( pak != null ) {
                issueWrite( pak );
                pak = null;
            }
        }
        this.closeStreams();
    }
    
    private void issueRead()
    {
    	try{
    		Packet packet = Packet.readPacket(is, true);
	        handler.recvMessage( packet.getType(), packet.getSize(), packet.getData());
    	}catch(Exception e)
    	{
    		e.printStackTrace();
			super.close();
			closeStreams();
    	}
    }
    
    private void issueWrite(Packet pak)
    {
    	byte[] data;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		try
		{	
			Packet.sendPacket(dos, pak, true);
			data = baos.toByteArray();
			os.write(data);
			os.flush();

		}catch(Exception e)
		{
			e.printStackTrace();
			super.close();
			this.closeStreams();
		}
		finally
		{
		}
    }
    /**
     * ��Ϣһ��
     * @param ms
     */
    private void snooze( int ms ) {
        try {
            if ( ms > 0 ) {
                Thread.sleep( ms );
            }
            Thread.yield();
        } catch ( InterruptedException ex ) 
        {
        }
    }
	/**
	 * 
	 *<p>Titile:ReaderThread</p>
	 *<p>Description:�ڲ��࣬��ȡ�߳�</p>
	 *<p>Copyright:Copyright(c)2010</p>
	 *<p>Company:zrong</p>
	 * @author yangzheng
	 * @version 1.0
	 */
	private class ReaderThread implements Runnable 
	{
        ReaderThread() 
        {
            new Thread( this ).start();
        }
        public void run() {
            reading();
        }
    }
	/**
	 * 
	 *<p>Titile:WriterThread</p>
	 *<p>Description:�ڲ��࣬�����߳�</p>
	 *<p>Copyright:Copyright(c)2010</p>
	 *<p>Company:zrong</p>
	 * @author yangzheng
	 * @version 1.0
	 */
    private class WriterThread implements Runnable {
        WriterThread() {
            new Thread( this ).start();
        }
        public void run() 
        {
            try {
                sending();
            } catch ( InterruptedException ex ) {
            	
            }
        }
    }
    /**
     * ������
     */
    
	public void keepAlive() 
	{
    	
		if ( isClosing ) return;//����Ѿ������ر�����,�Ͳ���keepAlive��
//    	if(!alive)//��������Ѿ��������������ˣ���Ҫ���·����µ�����
//		{
//    		System.out.println("keepAlive Open");
//    		clearBuf();
//		 	open();
//		 	handler.connectionBroken();//����1002�Ķ���������
//		 	return;
//		}
        if ( requests.isEmpty()){
            try {
				sendMessage(GameProtocol.CONNECTION_SEND_KEEPALIVE, new byte[]{1});
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
    }
}
