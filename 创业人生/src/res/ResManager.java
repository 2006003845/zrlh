package res;


import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Vector;

import javax.microedition.lcdui.Image;

import android.util.Log;

import com.zrong.Android.Util.SystemAPI;
import com.zrong.Android.game.GameDef;
import com.zrong.Android.online.network.HttpConnection;


public class ResManager implements Runnable
{
	public static final String TAG= "ResManager";
	/** ��̬��Դ��ַ */
	public static String RES_SERVER_URL;
	/**������Դ��ŵ�·�� */ 
	public static final String commPath = "/resbig/";
	
	
	/** ���ڶ�̬������Դ������ */
	private static HttpConnection httpConn = null;
	/**��ʾ�����߳��Ƿ������� */
	private static boolean isRun = true;
 
	/** ��Դ�� */
	public static Hashtable allRes = new Hashtable();
	/**������Դid�ĳ� */
	public static Vector bufferedIDS = new Vector();
	
	
	public static int RES_BUF_SIZE = 0;
	

	public static int RES_BUF_MAX = 1000 * 1000;//��Դ������������ֵ

//	public static final int PACKET_MAX_SIZE = 20480;

	
	/** �ȴ����ص���Դ */
	private static Vector reqReses = new Vector();

	/** �첽��Դ�����߳� */
	private static Thread threadResLoad = null;

	/** �ж��Ƿ������ͼƬ�У���true���������Դ�Ĳ�����ȴ���ֱ��ͼƬ��Դ������ */
	
	
	//������####################################################################
	/**
	 * ��Դ��������ʼ�������ö�̬���ַ��������̬���ӡ�ά����Դ�����ļ���
	 */
	public static void init (String resServerURL, int resBufSize)
	{
		if(resServerURL != null)
		{
			RES_SERVER_URL = resServerURL;
		}
		
		/** ��ɫͼƬ�����������ڴ� */
		DataInputStream dis = null;
		try
		{
			dis = new DataInputStream(Image.getResourceAsStream(commPath + "index.dat"));
			int size = dis.readShort();
			Res.resIndex = new short[size][2];
			Res.colorIndex = new byte[size];
			
			for(int i = 0;i < size;i++)
			{
				Res.resIndex[i][0] = dis.readShort();
				Res.resIndex[i][1] = dis.readShort();
				System.out.println("Res.resIndex"+i+"="+Res.resIndex[i][0]+"==="+Res.resIndex[i][1]);
				Res.colorIndex[i] = dis.readByte();
				System.out.println("Res.colorIndex"+i+"="+Res.colorIndex[i]);
			}
			dis.close();
		}catch(Exception e)
		{
			//#ifdef Debug 
			e.printStackTrace();
			//#endif
		}
		finally
		{
			SystemAPI.closeDataInputSream(dis);
		}
		/** �����첽��Դװ���߳� */
		isRun = true;
		if(threadResLoad == null)
		{
			threadResLoad = new Thread(new ResManager());
			threadResLoad.start();
		}
	}
	
	
	
	
//	public static Res getRes (int id, int defaultId)
//	{
//		if(id < 0)
//			return null;
//		 
//		 
//		int datID=getDatId(id);
//		
//		Res res=getBufferRes(datID);
//		
//		if(res != null)
//		{
//			return res;
//		}
//		else
//		{
//			res=getLocalRes(datID);
//			
//			if(res!=null)
//			{
//				return res;
//			}
//			else
//			{
//				Integer idObj = new Integer(id);
//				
//				Res save = (Res)allRes.get(new Integer(datID));
//				
//				if(save == null)
//				{
//					save = new Png(Res.RES_TYPE_IMAGE);
//					save.id = (short)datID;
//					addResToBuf(save); 
//				}
//				if(!reqReses.contains(idObj))
//				{
//					reqReses.addElement(idObj); 
//				}
//				
//				if(!save.loaded)
//				{
//					return getRes(defaultId,defaultId);
//				}
//				else
//				{
//					return save;
//				}
//			
//				
//			}
//		}
//	}

	/**
	 * ����Դ���л�ȡ��Դ������Դ�������򴴽��������ز�������������ȡ
	 * 
	 * @param id
	 *        Ҫ��ȡ����ԴID
	 * @param isWaitForReady
	 *        ��������ʱ�ǽ��еȴ����Ƿ���NULLֵ
	 * @return
	 */
	public static Res getRes (int id, boolean isWaitForReady)
	{
		if(id < 0)
			return null;
		int datID=getDatId(id);
		
		Res res=getBufferRes(datID);
		
		if(res != null)
		{
			return res;
		}
		else
		{
			res=getLocalRes(datID);
			
			if(res!=null)
			{
				return res;
			}
			else
			{
				Integer idObj = new Integer(id);
				
				Res save = (Res)allRes.get(new Integer(datID));
				
				if(save == null)
				{
					save = new Png(Res.RES_TYPE_IMAGE);
					save.id = (short)datID;
					addResToBuf(save); 
				}
				if(!reqReses.contains(idObj))
				{
					reqReses.addElement(idObj); 
				}
				
				return save;
			}
		}
	}

	/**
	 * ��Դ���뻺��
	 * 
	 * @param id
	 */
	private synchronized static void addResToBuf ( Res res)
	{
		try
		{
			allRes.put(new Integer(res.id), res);
			bufferedIDS.addElement(new Integer(res.id));
			 
			if(res instanceof Png)
			{
				Png png = (Png)res;
				RES_BUF_SIZE += png.getWidth() * png.getHeight();
				while(RES_BUF_SIZE > RES_BUF_MAX)//����������Դ����
				{		
					Integer idObj = (Integer)bufferedIDS.elementAt(0);
					Res rmRes = (Res)allRes.get(idObj);
					if(rmRes instanceof Png)
					{
						Png rmPng = (Png)rmRes;
						RES_BUF_SIZE -= rmPng.getWidth() * rmPng.getHeight();
					}
					allRes.remove(idObj);
					bufferedIDS.removeElement(idObj);
//					Debug.debug = "�����Դ id="+idObj.intValue();
					//#ifdef Debug
					System.out.println("������Դ����"+RES_BUF_MAX+"��ǰ��Դ��С="+RES_BUF_SIZE+"�����Դ id="+idObj.intValue());
					//#endif
				}
			}
			
			if(bufferedIDS.size() <= 0)
			{
				RES_BUF_SIZE = 0;
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	/**
	 * ����Դ���л�ȡ��Դ������Դ�������򷵻�NULL
	 * 
	 * @param id
	 *        int ��Դid
	 * @return Res
	 */
	private static Res getRes (int id)
	{
		int datID = -1;
		/** ����Դ�������в�����Դ����ID */
		int idx = getColorPngResIdx(id);
		if(idx >= 0)
		{
			datID = Res.resIndex[idx][1];
		}
		else
		{
			datID = id;
		}
		Integer idObj = new Integer(datID);
		if(allRes.containsKey(idObj))
		{
			//�Ի�����Դ����ʹ�����򣬻�Ӱ��Ч�ʣ������ʹ��
			 bufferedIDS.removeElement(idObj);
			 bufferedIDS.addElement(idObj);
			return (Res)allRes.get(new Integer(datID));
		}
		else
		{
			return null;
		}
	}

	/**
	 * ��ȡpng
	 * 
	 * @param id
	 *        int ��Դid
	 * @param isWaitForReady
	 *        �Ƿ����Դ���롢���صȲ�����������
	 * @return Png
	 */
	public static Png getPng (int id, boolean isWaitForReady)
	{
		Res res = getRes(id, isWaitForReady);
		//if(!(res instanceof Png)) return null;
		if(res != null)
			return (Png)res;
		return null;
	}

	/**
	 * ��ȡ����
	 * 
	 * @param id
	 *        int ��Դid
	 * @param isWaitForReady
	 *        �Ƿ����Դ���롢���صȲ�����������
	 * @return SpriteInfo
	 */
	public static SpriteInfo getSpriteInfo (int id, boolean isWaitForReady)
	{
		Res res = getRes(id, isWaitForReady);
		//if(!(res instanceof SpriteInfo)) return null;
		if(res != null)
			return (SpriteInfo)res;
		return null;
	}

	/**
	 * ��ȡ��ͼ
	 * 
	 * @param id
	 * @param isWaitForReady
	 *        �Ƿ����Դ���롢���صȲ�����������
	 * @return
	 */
	public static MapInfo getMapInfo (int id, boolean isWaitForReady)
	{
		Res res = getRes(id, isWaitForReady);
		if(res != null)
			return (MapInfo)res;
		return null;
	}

	/**
	 * ��ȡpng��Ĭ��ʹ������������ʽ
	 * 
	 * @param id
	 *        int ��Դid
	 * @return Png
	 */
	public static Png getPng (int id)
	{
		return getPng(id, true);
	}

	/**
	 * ��ȡ���飬Ĭ��ʹ������������ʽ
	 * 
	 * @param id
	 *        int ��Դid
	 * @return SpriteInfo
	 */
	public static SpriteInfo getSpriteInfo (int id)
	{
		return getSpriteInfo(id, true);
	}

	/**
	 * ��ȡ��ͼ��Ĭ��ʹ������������ʽ
	 * 
	 * @param id
	 *        int ��Դid
	 * @return MapInfo
	 */
	public static MapInfo getMapInfo (int id)
	{
		return getMapInfo(id, true);
	}

	/**
	 * �ӱ�ɫͼƬ��������ͼƬ��Դ�ı�����
	 * 
	 * @param resID
	 * @return
	 */
	private static int getColorPngResIdx (int resID)
	{
		if(Res.resIndex == null)
			return -1;
		for(int i = 0;i < Res.resIndex.length;i++)
		{
			if(resID == Res.resIndex[i][0])
				return i; //�������ڵ��������ǰ�������ID�ģ�����resIDֱ�Ӷ�ӦresIndex��������
		}
		return -1;
	}

	/**
	 * ��ȡָ��id��Png��ͼƬ
	 * 
	 * @param id
	 *        int
	 * @return Image
	 */
	public static javax.microedition.lcdui.Image getPngImg (int id)
	{
		Png png = getPng(id);
		int idx = getColorPngResIdx(id);
		if(png.getType() == Res.RES_TYPE_IMAGE)
		{
			return png.getImage();
		}
		else
		{
			if(idx < 0)
				idx = 0; //ԭ��ɫͼƬ����д������������
			return png.getColorImg(Res.colorIndex[idx]);
		}
	}

	/**
	 * �ӻ��������ָ��ID����Դ,ֻ�����ڱ�����Դ
	 * 
	 * @param id
	 */
	public static void clearRes (int id)
	{
		 
		try
		{
			Object idObj = new Integer(getDatId(id));
			
			if(bufferedIDS.contains(idObj))
			{
				Res res = null;
				if(allRes.containsKey(idObj))
				{
					res=(Res)allRes.get(idObj);
					if(res != null)
					{
						if(res instanceof Png)
						{
							Png png=(Png)res;
							png.clearImage();
							RES_BUF_SIZE -= png.getWidth() * png.getHeight();
						}
						else if( res instanceof SpriteInfo)
						{
							SpriteInfo sprite=(SpriteInfo)res;
							for(int i=0;i<sprite.allPngId.size();i++)
							{
								clearRes(((Integer)sprite.allPngId.elementAt(i)).intValue());
							}
						}
						else if(res instanceof MapInfo)
						{
							MapInfo mapInfo = (MapInfo)res;
							mapInfo.title.basePic.clearImage();
							if(mapInfo.surface != null && mapInfo.surface.refRes != null)
							{
								int length = mapInfo.surface.refRes.length;
								for(int j = 0;j < length;j++)
								{
									Res refRes = mapInfo.surface.refRes[j];
									if(refRes instanceof Png)
									{
										Png png=(Png)refRes;
										RES_BUF_SIZE -= png.getWidth() * png.getHeight();
										((Png)refRes).clearImage();
									}
								}
							}
						}
					}
				}
				if(res != null && res.isLocalRes)
				{
					bufferedIDS.removeElement(idObj);
					allRes.remove(idObj);
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		 
	}

	/**
	 * ������л������Դ,����
	 */
	public static void clearAllBuffer ()
	{
		allRes = null;
		bufferedIDS = null;
		System.gc();
		allRes = new Hashtable();
		bufferedIDS = new Vector();
		RES_BUF_SIZE = 0;
	}

	/**
	 * �������ͼƬ��Դ(�����png����)
	 */
	public static void clearAllImg ()
	{
		 
		Integer intObj = null;
		Res res = null;
		try
		{
			for(int i = 0;i < bufferedIDS.size();i++)
			{
				try
				{
					intObj = (Integer)bufferedIDS.elementAt(i);
				}catch(Exception e)
				{
					e.printStackTrace();
					continue;
				}
				if(allRes.containsKey(intObj))
				{
					res = (Res)allRes.get(intObj);
					
					if(res != null)
					{
						if(res instanceof Png)
						{
							Png png = (Png)res;
							RES_BUF_SIZE -= png.getWidth() * png.getHeight();
							png.clearImage();
						}
						else if( res instanceof SpriteInfo)
						{
							SpriteInfo sprite=(SpriteInfo)res;
							for(int j=0;j<sprite.allPngId.size();j++)
							{
								clearRes(((Integer)sprite.allPngId.elementAt(j)).intValue());
							}
						}
						else if(res instanceof MapInfo)
						{
							MapInfo mapInfo = (MapInfo)allRes.get(intObj);
							mapInfo.title.basePic.clearImage();
							if(mapInfo.surface != null && mapInfo.surface.refRes != null)
							{
								int length = mapInfo.surface.refRes.length;
								for(int j = 0;j < length;j++)
								{
									Res refRes = mapInfo.surface.refRes[j];
									if(refRes instanceof Png)
									{
										Png png=(Png)refRes;
										RES_BUF_SIZE -= png.getWidth() * png.getHeight();
										((Png)refRes).clearImage();
									}
								}
							}
						}
					}
					
				}
				
				/** ������ص���Դ */
				if(res != null && res.isLocalRes)
				{
					 
					Log.d(TAG, "���������Դ : " + intObj.intValue());
					allRes.remove(intObj);
					bufferedIDS.removeElement(intObj);
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}

	}
	
	/**
	 * �����̵߳��ã��߳�����
	 * @param id
	 * @return
	 */
	private static Res getNetWorkRes(int id)
	{
		int datID=getDatId(id);
		long startTime = System.currentTimeMillis();
		httpConn = new HttpConnection(true);
		Res res = null;
		if(httpConn.startURL(GameDef.resSever + datID + ".dat"))
		{
			while(httpConn.isBusy()&&System.currentTimeMillis()-startTime<1000*30)
			{
				SystemAPI.sleepGame(50);
			}
		    res=checkNetWorkRes();
		    httpConn.close();
		}
		return res;
	}
	
	private static Res readRess(int id,boolean isWaitForReady)
	{
		 
		int datID=getDatId(id);
		Res res = null;
//		=getBufferRes(datID);
		if(res!=null)
		{
			return res;
		}
		else
		{
			//res=getLocalRes(datID);
			if(res!=null)
			{
				return res;
			}
			else
			{	
				res=checkNetWorkRes();
				if(res!=null)
				{
					return null;
				}
				else
				{
					if(httpConn != null)
					{
						//����������æ����ݲ����ж��Ƿ�ȴ�
						if(httpConn.isBusy())
						{
							if(!isWaitForReady)
							{
								return null;
								//����ʽ����ʱ����Ӧ�����������æ�����
							}
							else
							{
								//�ȴ����ӿ�������������
								while(httpConn.isBusy())
								{
									SystemAPI.sleepGame(50);
								}
							}
						}
						if(httpConn.startURL(GameDef.resSever + datID + ".dat"))
						{
							 
							if(!isWaitForReady)
							{
								return null; //���ȴ���ֱ�ӷ���NULL���ȴ��´ε��ñ�����ʱ��������ļ����򴴽���Դ����
							}
							else
							{
								while(httpConn.isBusy())
								{
									if(Thread.currentThread() == threadResLoad)
									{
										SystemAPI.sleep(50);
									}
									else
									{
										SystemAPI.sleepGame(50); //�����̼߳���ʱ����Ҫ���ƽ�����
									}
								}
								if(httpConn.checkRespData() == null)
								{
									if(Thread.currentThread() == threadResLoad)
									{
										return null;
									}
									else
									{
										//���̼߳��ز��ܷ��ؿ�
									}
									 
								}
								return getRes(id, true); //ʹ��Ƕ�ף�isWaitForReady��Ϊtrue�Է���ѭ��
							}
						}
						else
						{//����httpʧ��
							if(Thread.currentThread() == threadResLoad)
							{
								return null;
							}
							else
							{
								//���̼߳��ز��ܷ��ؿ�
							}
							return getRes(id, true); //ʹ��Ƕ�ף�isWaitForReady��Ϊtrue�Է���ѭ��
						}
					}	 
				}
			}
		}
		return null;
	}
 

	/**
	 * �����߳̽�����Դ����
	 */
	public void run ()
	{
		//���reqReses���д�������Դ����м���
		while(isRun)
		{
			if(reqReses.size() > 0)
			{
				for(int i = 0;i < reqReses.size();i++)
				{
					int id = -1;
					try
					{
						id = ((Integer)reqReses.elementAt(i)).intValue();
					}catch(Exception e)
					{
						e.printStackTrace();
					}
					if(id < 0)
					{
						continue;
					}
					System.out.println("===================== run id"+id);
					//Res res=readRess(id,false);
					Res res = getNetWorkRes(id);
					if(res != null)
					{
						//#ifdef Debug
//						Debug.debug = "getRes id =" +res.id;
//						System.out.println("==============getRes id =" + res.id);

						//#endif
						Integer te = new Integer(res.id);
						if(reqReses.contains(te))
						{
							try
							{
								reqReses.removeElement(te);
							}catch(Exception e)
							{
								e.printStackTrace();
							}
						}
					}
				}

			}
			SystemAPI.sleep(50);
		}
	}

	/**
	 * ������Դ�����߳�
	 */
	public static void close ()
	{
		isRun = false;
	}

	public static void clearReq ()
	{
		reqReses.removeAllElements();
	}

	public static void reset ()
	{
		clearReq();
	}
	
	private static int getDatId(int id)
	{
		int datID = -1;
		/** ����Դ�������в�����Դ����ID */
		int idx = getColorPngResIdx(id);
		if(idx >= 0)
		{
			datID = Res.resIndex[idx][1];
		}
		else
		{
			datID = id;
		}
		return datID;
	}
	
	/**
	 * ����Դ���л�ȡ��Դ������Դ�������򷵻�NULL
	 * 
	 * @param id
	 *        int ��Դid
	 * @return Res
	 */
	private static Res getBufferRes (int datID)
	{
		Integer idObj = new Integer(datID);
		if(allRes.containsKey(idObj))
		{
			 //�Ի�����Դ����ʹ�����򣬻�Ӱ��Ч�ʣ������ʹ��
			 bufferedIDS.removeElement(idObj);
			 bufferedIDS.addElement(idObj);
			return (Res)allRes.get(new Integer(datID));
		}
		else
		{
			return null;
		}
	}
	private static Object obj1=new Object();
	private static Res getLocalRes(int datID)
	{
		DataInputStream dis=null;
		synchronized(obj1)
		{
			InputStream is = null;
			 
			 
				try {
					is = Image.getResourceAsStream("/resbig/" + datID + ".dat");
					
				} catch (IOException e) 
				{
					
					 
				}
				if(is!=null)
				{
					dis = new DataInputStream(is);
				}
				
			 
		}
		if(dis != null)
		{
			try
			{
				Res tempRes = Res.getRes(dis);
				if(tempRes!=null)
				{
					System.out.println("get res by local res id="+tempRes.id);
					tempRes.isLocalRes = true;
					
					addResToBuf(tempRes); 
					dis.close();
					return tempRes;
				}
			}catch(Exception e)
			{
				 e.printStackTrace();
			}
		}
		return null;
	}
	
	private static Res checkNetWorkRes()
	{
		if(httpConn!=null)
		{	
			if(!httpConn.isBusy() && httpConn.checkRespData() != null)
			{
				byte[] resData = httpConn.pollRespData();
				if(resData != null)
				{
					DataInputStream dis = new DataInputStream(new ByteArrayInputStream(resData));
					if(dis!=null)
					{
						try
						{
							Res tempRes = Res.getRes(dis);
							if(tempRes!=null)
							{
								System.out.println("=========checkNetWorkRes="+tempRes.id);
								addResToBuf(tempRes);
								dis.close();
								return tempRes;
							}
						}catch(Exception e)
						{
							 try
							{
								dis.close();
							} catch (IOException e1)
							{
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							 e.printStackTrace();
						}
					}
				} 
				 
			}
		}
		return null;	
	}
	
	private static Res getNetWorkRes(int id,boolean isWaitForReady)
	{
		
		int datID=getDatId(id);
		if(httpConn!=null)
		{

		    while(httpConn.isBusy()||httpConn.checkRespData()!=null)
		    {
				 if(isWaitForReady)
				 {
					  
					 if(Thread.currentThread() == threadResLoad)
						{
							SystemAPI.sleep(50);
						}
						else
						{
							SystemAPI.sleepGame(50); //�����̼߳���ʱ����Ҫ���ƽ�����
						}
				 }
				 else
				 {
					 return null;
				 }
		    }
		    
		    
		    if(!httpConn.startURL(GameDef.resSever + datID + ".dat"))
    		{
		    	return null;//����ʧ��
    		}
		    if(!isWaitForReady)
			{
				return null; //���ȴ���ֱ�ӷ���NULL���ȴ��´ε��ñ�����ʱ��������ļ����򴴽���Դ����
			}
			else
			{
				while(httpConn.isBusy())
				{
					if(Thread.currentThread() == threadResLoad)
					{
						SystemAPI.sleep(50);
					}
					else
					{
						SystemAPI.sleepGame(50); //�����̼߳���ʱ����Ҫ���ƽ�����
					}
				}
				if(httpConn.checkRespData() == null)
				{
					if(Thread.currentThread() == threadResLoad)
					{
						return null;
					}
					else
					{
						//���߳����أ������ؿգ�ǿ������
					}
					 
				}
				return getRes(id, true); 
			}
		    
		}
		return null;
	}
	 
//	public static Res getDefineResOrDeafultRes(Res defineRes,int defineResId,int defaultResId)
//	{
//		Res temp=null;
//		if(defineRes == null||defineRes.id != defineResId)
//		{
//			temp=ResManager.getRes(defineResId, false);
//			
//			if(temp == null)
//			{
//				if(defineRes == null||defineRes.id != defineResId)
//				{
//					temp = ResManager.getRes(defaultResId, true);
//				}
//				else
//				{
//					temp = defineRes;
//				}
//			}
//		}
//		else
//		{
//			temp=defineRes;
//		}
//		return temp;
//	}
}
