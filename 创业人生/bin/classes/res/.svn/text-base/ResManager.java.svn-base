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
	/** 动态资源地址 */
	public static String RES_SERVER_URL;
	/**本地资源存放的路径 */ 
	public static final String commPath = "/resbig/";
	
	
	/** 用于动态更新资源的连接 */
	private static HttpConnection httpConn = null;
	/**表示下载线程是否还在运行 */
	private static boolean isRun = true;
 
	/** 资源池 */
	public static Hashtable allRes = new Hashtable();
	/**所有资源id的池 */
	public static Vector bufferedIDS = new Vector();
	
	
	public static int RES_BUF_SIZE = 0;
	

	public static int RES_BUF_MAX = 1000 * 1000;//资源缓冲区域的最大值

//	public static final int PACKET_MAX_SIZE = 20480;

	
	/** 等待加载的资源 */
	private static Vector reqReses = new Vector();

	/** 异步资源加载线程 */
	private static Thread threadResLoad = null;

	/** 判断是否在清除图片中，若true则有清除资源的操作需等待，直到图片资源清除完毕 */
	
	
	//方法体####################################################################
	/**
	 * 资源管理器初始化，设置动态库地址、启动动态连接、维护资源索引文件等
	 */
	public static void init (String resServerURL, int resBufSize)
	{
		if(resServerURL != null)
		{
			RES_SERVER_URL = resServerURL;
		}
		
		/** 变色图片索引表载入内存 */
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
		/** 启动异步资源装载线程 */
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
	 * 从资源池中获取资源，若资源不存在则创建，若本地不存在则联网获取
	 * 
	 * @param id
	 *        要获取的资源ID
	 * @param isWaitForReady
	 *        联网下载时是进行等待还是返回NULL值
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
	 * 资源存入缓存
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
				while(RES_BUF_SIZE > RES_BUF_MAX)//大于最大的资源限制
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
//					Debug.debug = "清除资源 id="+idObj.intValue();
					//#ifdef Debug
					System.out.println("最大的资源限制"+RES_BUF_MAX+"当前资源大小="+RES_BUF_SIZE+"清除资源 id="+idObj.intValue());
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
	 * 从资源池中获取资源，若资源不存在则返回NULL
	 * 
	 * @param id
	 *        int 资源id
	 * @return Res
	 */
	private static Res getRes (int id)
	{
		int datID = -1;
		/** 从资源索引表中查找资源数据ID */
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
			//对缓冲资源进行使用排序，会影响效率，看情况使用
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
	 * 获取png
	 * 
	 * @param id
	 *        int 资源id
	 * @param isWaitForReady
	 *        是否对资源载入、下载等操作进行阻塞
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
	 * 获取精灵
	 * 
	 * @param id
	 *        int 资源id
	 * @param isWaitForReady
	 *        是否对资源载入、下载等操作进行阻塞
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
	 * 获取地图
	 * 
	 * @param id
	 * @param isWaitForReady
	 *        是否对资源载入、下载等操作进行阻塞
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
	 * 获取png，默认使用载入阻塞方式
	 * 
	 * @param id
	 *        int 资源id
	 * @return Png
	 */
	public static Png getPng (int id)
	{
		return getPng(id, true);
	}

	/**
	 * 获取精灵，默认使用载入阻塞方式
	 * 
	 * @param id
	 *        int 资源id
	 * @return SpriteInfo
	 */
	public static SpriteInfo getSpriteInfo (int id)
	{
		return getSpriteInfo(id, true);
	}

	/**
	 * 获取地图，默认使用载入阻塞方式
	 * 
	 * @param id
	 *        int 资源id
	 * @return MapInfo
	 */
	public static MapInfo getMapInfo (int id)
	{
		return getMapInfo(id, true);
	}

	/**
	 * 从变色图片索引表查出图片资源的表索引
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
				return i; //由于现在的索引表是包含所有ID的，所以resID直接对应resIndex索引排列
		}
		return -1;
	}

	/**
	 * 获取指定id的Png的图片
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
				idx = 0; //原变色图片并不写入索引数组中
			return png.getColorImg(Res.colorIndex[idx]);
		}
	}

	/**
	 * 从缓冲中清除指定ID的资源,只适用于本地资源
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
	 * 清除所有缓冲的资源,慎用
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
	 * 清除所有图片资源(不清除png数据)
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
				
				/** 清除本地的资源 */
				if(res != null && res.isLocalRes)
				{
					 
					Log.d(TAG, "清除本地资源 : " + intObj.intValue());
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
	 * 独立线程调用，线程阻塞
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
						//若网络连接忙则根据参数判断是否等待
						if(httpConn.isBusy())
						{
							if(!isWaitForReady)
							{
								return null;
								//阻塞式下载时，不应该在这里出现忙线情况
							}
							else
							{
								//等待连接空闲以启动下载
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
								return null; //不等待则直接返回NULL，等待下次调用本方法时再由上面的检查程序创建资源到库
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
										SystemAPI.sleepGame(50); //用主线程加载时，需要绘制进度条
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
										//主线程加载不能返回空
									}
									 
								}
								return getRes(id, true); //使用嵌套，isWaitForReady设为true以防死循环
							}
						}
						else
						{//启动http失败
							if(Thread.currentThread() == threadResLoad)
							{
								return null;
							}
							else
							{
								//主线程加载不能返回空
							}
							return getRes(id, true); //使用嵌套，isWaitForReady设为true以防死循环
						}
					}	 
				}
			}
		}
		return null;
	}
 

	/**
	 * 独立线程进行资源加载
	 */
	public void run ()
	{
		//如果reqReses中有待加载资源则进行加载
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
	 * 结束资源加载线程
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
		/** 从资源索引表中查找资源数据ID */
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
	 * 从资源池中获取资源，若资源不存在则返回NULL
	 * 
	 * @param id
	 *        int 资源id
	 * @return Res
	 */
	private static Res getBufferRes (int datID)
	{
		Integer idObj = new Integer(datID);
		if(allRes.containsKey(idObj))
		{
			 //对缓冲资源进行使用排序，会影响效率，看情况使用
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
							SystemAPI.sleepGame(50); //用主线程加载时，需要绘制进度条
						}
				 }
				 else
				 {
					 return null;
				 }
		    }
		    
		    
		    if(!httpConn.startURL(GameDef.resSever + datID + ".dat"))
    		{
		    	return null;//启动失败
    		}
		    if(!isWaitForReady)
			{
				return null; //不等待则直接返回NULL，等待下次调用本方法时再由上面的检查程序创建资源到库
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
						SystemAPI.sleepGame(50); //用主线程加载时，需要绘制进度条
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
						//主线程下载，不许返回空，强制下载
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
