package res;


import res.mapLayer.AttrLayer;
import res.mapLayer.SurfaceLayer;
import res.mapLayer.TileLayer;
import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import com.zrong.Android.game.GameDefinition;
 

public abstract class Res
{
	public static boolean loaded = false;
	
	
	/** 资源类型 */
	public static final byte RES_TYPE_NULL = 0; //空单元
	public static final byte RES_TYPE_IMAGE = 1; //图片
	public static final byte RES_TYPE_COLORIMAGE = 13; //变色图片
	public static final byte RES_TYPE_SPRITE = 3; //精灵
	public static final byte RES_TYPE_MAP = 4; //地图

	/** 资源对象变量 */
	public short id;

	/** 是否为本地资源 */
	public boolean isLocalRes;

	/** 存储指定id对应的.dat资源以及变色索引 */
	public static short[][] resIndex;
	public static byte[] colorIndex;

	/** png图片文件头、尾字节数组 */
	protected static String[] pngFileChunkTypeList =
	{
			"tEXt", "IHDR", "PLTE", "tRNS", "IDAT"
	};
	protected static final byte[] pngFileHead =
	{
			(byte)0x89, 0x50, 0x4e, 0x47, 0x0d, 0x0a, 0x1a, 0x0a
	};
	protected static final byte[] pngFileEnd =
	{
			0x00, 0x00, 0x00, 0x00, 0x49, 0x45, 0x4e, 0x44, (byte)0xae, 0x42, 0x60, (byte)0x82
	};

	//MIDP 2.0 系列翻转定义
	public static final int TRANS_NONE = 0;//无翻转
	public static final int TRANS_ROT90 = 5;
	public static final int TRANS_ROT180 = 3;//水平+垂直翻转
	public static final int TRANS_ROT270 = 6;
	public static final int TRANS_MIRROR = 2;//水平翻转
	public static final int TRANS_MIRROR_ROT90 = 7;
	public static final int TRANS_MIRROR_ROT180 = 1;//垂直翻转
	public static final int TRANS_MIRROR_ROT270 = 4;
	public static final int TRANS_ARRAYS[] =
	{
			TRANS_NONE, TRANS_MIRROR_ROT180, TRANS_MIRROR, TRANS_ROT180, TRANS_MIRROR, TRANS_MIRROR_ROT90, TRANS_MIRROR_ROT180, TRANS_MIRROR_ROT270
	};

	public static final int DEF_NONE = 0;//无翻转
	public static final int DEF_ROT90 = 1;
	public static final int DEF_ROT180 = 2;//垂直水平翻转
	public static final int DEF_ROT270 = 3;
	public static final int DEF_MIRROR = 4;//水平翻转
	public static final int DEF_MIRROR_ROT90 = 5;
	public static final int DEF_MIRROR_ROT180 = 6;//垂直翻转
	public static final int DEF_MIRROR_ROT270 = 7;
	public static final int[] TRANS_RESULT =
	{
			DEF_NONE, DEF_MIRROR_ROT180, DEF_MIRROR, DEF_ROT180
	};

	/** Nokia翻转定义 */
	public static final int NOKIA_TRANS_NONE = 0;
	public static final int NOKIA_TRANS_ROT90 = 90;
	public static final int NOKIA_TRANS_ROT180 = 180;
	public static final int NOKIA_TRANS_ROT270 = 270;
	public static final int NOKIA_TRANS_MIRROR = 0x2000;
	public static final int NOKIA_TRANS_MIRROR_ROT90 = NOKIA_TRANS_MIRROR | NOKIA_TRANS_ROT90;
	public static final int NOKIA_TRANS_MIRROR_ROT180 = 0x4000;
	public static final int NOKIA_TRANS_MIRROR_ROT270 = NOKIA_TRANS_MIRROR_ROT180 | NOKIA_TRANS_ROT90;

	public static final int NOKIA_TRANS_ARRAYS[] =
	{
			NOKIA_TRANS_NONE, NOKIA_TRANS_MIRROR_ROT180, NOKIA_TRANS_MIRROR, NOKIA_TRANS_ROT180, NOKIA_TRANS_MIRROR_ROT270, NOKIA_TRANS_ROT90, NOKIA_TRANS_ROT270, NOKIA_TRANS_MIRROR_ROT90
	};

	 
	public static final String pngPath = "/png/";
 

 
    public static final String commPath = "/resbig/";
 
	/** 资源对象方法 */
	public abstract void draw (Graphics g, int x, int y, int colorIndex, int index, int frameId, int flip, int anchor);

	public abstract void destroy ();

	/**
	 * 判断资源是否为变色图片
	 * 
	 * @param id
	 *        int 资源id
	 * @return int 返回索引值
	 */
	public static int colorResIndex (int id)
	{
		for(int i = 0;i < resIndex.length;i++)
		{
			if(id == resIndex[i][0])
			{
				return i;
			}
		}
		return -1;
	}

	/**
	 * 从资源池中获取资源，若资源不存在则创建
	 * 
	 * @param id
	 *        int 资源id
	 * @return Res
	 */
	public static Res getRes (int id)
	{
		return ResManager.getRes(id, true);
	}

	//public static Res getMapRes

	/** 读取资源 */
	public static Res getRes (DataInputStream is) throws IOException
	{
		short id = is.readShort();
		byte resType = is.readByte();
//		//#ifdef Debug
//		Debug.println("读取资源|| resType = " + resType + "   id = " + id);
//		//#endif
		switch(resType)
		{
			case RES_TYPE_IMAGE://常规图片
				System.out.println("==================================Png:"+id);
				Png narmalPng = (Png)ResManager.allRes.get(new Integer(id));
				if(narmalPng == null)
				{
					 narmalPng = new Png(RES_TYPE_IMAGE);
				}
				else
				{ 
					narmalPng.type = RES_TYPE_IMAGE;
				}
				narmalPng.loadNormalImage(is);
				narmalPng.id = id;
				narmalPng.loaded = true;
				return narmalPng;
			case RES_TYPE_COLORIMAGE://变色图片
				System.out.println("==================================Png:"+id);
				Png colorPng =(Png)ResManager.allRes.get(new Integer(id));
				if(colorPng == null)
				{
					colorPng = new Png(RES_TYPE_COLORIMAGE);
				}
				else
				{ 
					colorPng.type = RES_TYPE_COLORIMAGE;
				}
				colorPng.readColorImgData(is);
				colorPng.id = id;
				colorPng.loaded = true;
				return colorPng;
			case RES_TYPE_SPRITE://精灵
				SpriteInfo spr = new SpriteInfo();
				spr.load(is);
				spr.id = id;
				spr.loaded = true;
				return spr;
			case RES_TYPE_MAP://地图
				MapInfo map = new MapInfo();
				map.id = id;
				map.width = is.readShort();
				map.height = is.readShort();
				byte layerNum = is.readByte();
				for(int i = 0;i < layerNum;i++)
				{
					byte type = is.readByte();
					if(type == 1)
					{ //地底层
						TileLayer tL = new TileLayer();
						tL.load(is);
						map.title = tL;
						map.layers.addElement(tL);
					}
					else if(type == 2)
					{ //地表层
						SurfaceLayer sL = new SurfaceLayer();
						sL.load(is);
						map.surface = sL;
						map.layers.addElement(sL);
					}
					else if(type == 3)
					{ //属性层
						AttrLayer aL = new AttrLayer();
						aL.load(is);
						map.attr = aL;
						map.layers.addElement(aL);
					}
					else if(type == 7)
					{ //活动对象层
						int w = is.readShort(); //width
						int h = is.readShort(); //height
						int actsNum = is.readShort();
						map.actsRes = new Res[actsNum];
						map.actsResId = new short[actsNum];
						map.serieShow = new byte[actsNum];
						map.actsX = new short[actsNum];
						map.actsY = new short[actsNum];
						map.flips = new byte[actsNum];
						map.colorIndex = new byte[actsNum];
						map.frameId = new byte[actsNum];
						map.lastChangeTimeUpFace = new long[actsNum];
						for(int m = 0;m < map.actsResId.length;m++)
						{
							int resId = is.readShort();
							System.out.println("map.actsRresId="+resId);
							//变色索引(常规图片为-1)
							int index = Res.colorResIndex(resId);
							if(index != -1)
							{
								map.colorIndex[m] = Res.colorIndex[index];
							}
							else
							{
								map.colorIndex[m] = -1;
							}
							map.actsRes[m] = ResManager.getRes(resId, true);
							map.actsResId[m] = (short)resId;
							map.serieShow[m] = is.readByte();
							map.actsX[m] = is.readShort();
							map.actsY[m] = is.readShort();
							map.flips[m] = is.readByte();
						}
					}
				}
				map.loaded = true;
				return map;
			default:
				break;
		}
		is.close();
		return null;
	}

	/**
	 * 获取png
	 * 
	 * @param id
	 *        int 资源id
	 * @return Png
	 */
	public static Png getPng (int id)
	{
		Res res = ResManager.getRes(id, true);
		if(res != null)
			return (Png)res;
		return null;
	}

	/**
	 * 获取精灵
	 * 
	 * @param id
	 *        int 资源id
	 * @return SpriteInfo
	 */
	public static SpriteInfo getSpriteInfo (int id)
	{
		Res res = getRes(id);
		if(res != null)
			return (SpriteInfo)res;
		return null;
	}

	/**
	 * 获取地图
	 * 
	 * @param id
	 *        int 资源id
	 * @return MapInfo
	 */
	public static MapInfo getMapInfo (int id)
	{
		Res res = ResManager.getRes(id, true);
		if(res != null)
			return (MapInfo)res;
		return null;
	}

	public static Res getMapRes (int id)
	{
		Res res = ResManager.getRes(id, true);
		if(res != null)
			return res;
		return null;

	}

	/**
	 * 画翻转图片
	 * 
	 * @param g
	 *        Graphics
	 * @param img
	 *        Image 图片
	 * @param subX
	 *        int
	 * @param subY
	 *        int
	 * @param subW
	 *        int
	 * @param subH
	 *        int
	 * @param tran
	 *        int
	 * @param x
	 *        int 绘制的x坐标
	 * @param y
	 *        int
	 * @param auth
	 *        int
	 */
	public static void drawRegion (Graphics g, Image img, int subX, int subY, int subW, int subH, int tran, int x, int y, int auth)
	{
		//#ifdef NokiaN73
		//# g.drawRegion(img, subX, subY, subW, subH, Res.TRANS_ARRAYS[tran], x, y, auth);
		//#elifdef NokiaE62
		//# g.drawRegion(img, subX, subY, subW, subH, Res.TRANS_ARRAYS[tran], x, y, auth);
		//#elifdef NokiaN97
		//# g.drawRegion(img, subX, subY, subW, subH, Res.TRANS_ARRAYS[tran], x, y, auth);
		//#elifdef Nokia
		//# g.drawRegion(img, subX, subY, subW, subH, Res.TRANS_ARRAYS[tran], x, y, auth);
		//# 
//# //		# int clipX = g.getClipX();
//# //		# int clipY = g.getClipY();
//# //		# int clipW = g.getClipWidth();
//# //		# int clipH = g.getClipHeight();
//# //		# int[] drawClip = {subX, subY, subW, subH};
//# //		# int[] drawInfo = getTransOff(img, Res.TRANS_ARRAYS[tran], drawClip);
//# //		# int[] authOff = getAuthOff(auth, drawInfo[2], drawInfo[3]);
//# //		# DirectGraphics dg = DirectUtils.getDirectGraphics(g);
//# //		# int clipX1 = Math.max(clipX, x - authOff[0]);
//# //		# int clipY1 = Math.max(clipY, y - authOff[1]);
//# //		# int clipX2 = Math.min(clipX+clipW, x - authOff[0]+drawInfo[2]);
//# //		# int clipY2 = Math.min(clipY+clipH, y - authOff[1]+drawInfo[3]);
//# //		# 
//# //		# g.setClip(clipX1, clipY1, clipX2-clipX1, clipY2-clipY1);
//# //		# dg.drawImage(img, x - authOff[0] - drawInfo[0], y - authOff[1] - drawInfo[1], auth, NOKIA_TRANS_ARRAYS[Res.TRANS_ARRAYS[tran]]);
//# //		# g.setClip(clipX, clipY, clipW, clipH);
		//#else
		g.drawRegion(img, subX, subY, subW, subH, Res.TRANS_ARRAYS[tran], x, y, auth);
		//#endif
	}
	
	 

	public static int[] getAuthOff (int auth, int w, int h)
	{
		int[] off = new int[2];

		if((auth & Graphics.LEFT) != 0)
		{
			off[0] = 0;
		}
		else if((auth & Graphics.HCENTER) != 0)
		{
			off[0] = w / 2;
		}
		else if((auth & Graphics.RIGHT) != 0)
		{
			off[0] = w;
		}

		if((auth & Graphics.TOP) != 0)
		{
			off[1] = 0;
		}
		else if((auth & Graphics.VCENTER) != 0)
		{
			off[1] = h / 2;
		}
		else if((auth & Graphics.BOTTOM) != 0)
		{
			off[1] = h;
		}

		return off;
	}

	/**
	 * 得到图片翻转后的绘制区域
	 * 
	 * @param image
	 *        Image 图片
	 * @param trans
	 *        int 翻转值
	 * @param drawClip
	 *        int[] 原图片的绘制区域
	 * @return int[] 返回翻转后应绘制的区域
	 */
	public static int[] getTransOff (Image image, int trans, int drawClip[])
	{
		int[] drawInfo = new int[4]; //0:offX, 1:offY, 2:clipW, 3:clipH
		int w = image.getWidth();
		int h = image.getHeight();
		drawInfo[2] = drawClip[2];
		drawInfo[3] = drawClip[3];
		switch(trans)
		{
			case TRANS_NONE:
				drawInfo[0] = drawClip[0];
				drawInfo[1] = drawClip[1];
				break;
			case TRANS_ROT90:
				drawInfo[0] = drawClip[1];
				drawInfo[1] = w - drawClip[0] - drawClip[2];
				drawInfo[2] = drawClip[3];
				drawInfo[3] = drawClip[2];
				break;
			case TRANS_ROT180:
				drawInfo[0] = w - drawClip[0] - drawClip[2];
				drawInfo[1] = h - drawClip[3] - drawClip[1];
				break;
			case TRANS_ROT270:
				drawInfo[0] = h - drawClip[3] - drawClip[1];
				drawInfo[1] = drawClip[0];
				drawInfo[2] = drawClip[3];
				drawInfo[3] = drawClip[2];
				break;
			case TRANS_MIRROR:
				drawInfo[0] = w - drawClip[0] - drawClip[2];
				drawInfo[1] = drawClip[1];
				break;
			case TRANS_MIRROR_ROT90:
				drawInfo[0] = h - drawClip[3] - drawClip[1];
				drawInfo[1] = w - drawClip[0] - drawClip[2];
				drawInfo[2] = drawClip[3];
				drawInfo[3] = drawClip[2];
				break;
			case TRANS_MIRROR_ROT180:
				drawInfo[0] = drawClip[0];
				drawInfo[1] = h - drawClip[3] - drawClip[1];
				break;
			case TRANS_MIRROR_ROT270:
				drawInfo[0] = drawClip[1];
				drawInfo[1] = drawClip[0];
				drawInfo[2] = drawClip[3];
				drawInfo[3] = drawClip[2];
				break;
		}
		return drawInfo;
	}

	/**
	 * 得到一个矩形off[]在随着大小为size[]的框架旋转trans后的相对于框架左上点的位置
	 * 
	 * @param trans
	 *        ,int,旋转值
	 * @param size
	 *        ,int[]，框架大小
	 * @param off
	 *        ,int[]，在框架中的矩形,int[0] x,int[1] y,int[2] w,int[3] h
	 * @return int[]，得到的位置
	 */
	public static int[] getTransOff (int trans, int sizeW, int sizeH, int offX, int offY, int offW, int offH)
	{
		int transOff[] = new int[2];
		switch(trans)
		{
			case DEF_NONE:
				transOff[0] = offX;
				transOff[1] = offY;
				break;
			case DEF_ROT90:
				transOff[0] = sizeH - offH - offY;
				transOff[1] = offX;
				break;
			case DEF_ROT180:
				transOff[0] = sizeW - offX - offW;
				transOff[1] = sizeH - offH - offY;
				break;
			case DEF_ROT270:
				transOff[0] = offY;
				transOff[1] = sizeW - offX - offW;
				break;
			case DEF_MIRROR:
				transOff[0] = sizeW - offX - offW;
				transOff[1] = offY;
				break;
			case DEF_MIRROR_ROT90:
				transOff[0] = sizeH - offH - offY;
				transOff[1] = sizeW - offX - offW;
				break;
			case DEF_MIRROR_ROT180:
				transOff[0] = offX;
				transOff[1] = sizeH - offH - offY;
				break;
			case DEF_MIRROR_ROT270:
				transOff[0] = offY;
				transOff[1] = offX;
				break;
		}
		return transOff;
	}

	/**
	 * 得到一个点off[]在随着大小为size[]的框架旋转trans后的相对于框架左上点的位置
	 * 
	 * @param trans
	 *        ,int,旋转值
	 * @param sizeW
	 *        ,sizeH,框架大小
	 * @param offX
	 *        ,offY,在框架中的点定义
	 * @return int[]，得到的位置
	 */
	public static int[] getTransOff (int trans, int sizeW, int sizeH, int offX, int offY)
	{
		int transOff[] = new int[2];
		switch(trans)
		{
			case DEF_NONE:
				transOff[0] = offX;
				transOff[1] = offY;
				break;
			case DEF_ROT90:
				transOff[0] = sizeH - offY;
				transOff[1] = offX;
				break;
			case DEF_ROT180:
				transOff[0] = sizeW - offX;
				transOff[1] = sizeH - offY;
				break;
			case DEF_ROT270:
				transOff[0] = offY;
				transOff[1] = sizeW - offX;
				break;
			case DEF_MIRROR:
				transOff[0] = sizeW - offX;
				transOff[1] = offY;
				break;
			case DEF_MIRROR_ROT90:
				transOff[0] = sizeH - offY;
				transOff[1] = sizeW - offX;
				break;
			case DEF_MIRROR_ROT180:
				transOff[0] = offX;
				transOff[1] = sizeH - offY;
				break;
			case DEF_MIRROR_ROT270:
				transOff[0] = offY;
				transOff[1] = offX;
				break;
		}
		return transOff;
	}

	/**
	 * 返回精灵播放帧的索引
	 * 
	 * @param index
	 *        int 精灵id
	 * @param seriesIdx
	 *        int 精灵系列
	 * @param nowFrameId
	 *        int 当前帧
	 * @param lastChangeTime
	 *        long 最后播放的当前帧时间
	 * @param nowTime
	 *        long 当前时间
	 * @return int 返回要播放的帧
	 */
	public static int autoRunSprite (int index, int seriesIdx, int nowFrameId, long lastChangeTime, long nowTime)
	{
		SpriteInfo spr = Res.getSpriteInfo(index);
		return autoRunSprite(spr, seriesIdx, nowFrameId, lastChangeTime, nowTime);
	}

	/**
	 * 
	 * @param res
	 * @param seriesIdx
	 * @param nowFrameId
	 * @param lastChangeTime
	 * @param nowTime
	 * @return
	 */
	public static int autoRunSprite (Res res, int seriesIdx, int nowFrameId, long lastChangeTime, long nowTime)
	{
		if(res == null)
			return -1;
		if(!(res instanceof SpriteInfo))
			return 0;
		SpriteInfo spr = (SpriteInfo)res;
		short delay = spr.delay;
		delay = 100;
		int frameSize = spr.getSeriesSize(seriesIdx);
		if(nowTime - lastChangeTime >= delay)
		{
			nowFrameId++;
			return nowFrameId >= frameSize ? 0 : nowFrameId;
		}
		else
		{
			return -1;
		}
	}
	
	 
	public static int autoRunSprite (Res res, int seriesIdx, int nowFrameId, long lastChangeTime, long nowTime,long delay)
	{
		if(res == null)
			return -1;
		if(!(res instanceof SpriteInfo))
			return 0;
		SpriteInfo spr = (SpriteInfo)res;
//		short delay = spr.delay;
//		delay = 100;
		
		int frameSize = spr.getSeriesSize(seriesIdx);
		if(nowTime - lastChangeTime >= delay)
		{
			nowFrameId++;
			return nowFrameId >= frameSize ? 0 : nowFrameId;
		}
		else
		{
			return -1;
		}
	}

//	/**
//	 * 返回精灵播放帧的索引
//	 * 
//	 * @param index
//	 *        int 精灵id
//	 * @param seriesIdx
//	 *        int 精灵系列
//	 * @param nowFrameId
//	 *        int 当前帧
//	 * @param lastChangeTime
//	 *        long 最后播放的当前帧时间
//	 * @param nowTime
//	 *        long 当前时间
//	 * @param delay
//	 *        long 当前系列的帧延迟
//	 * @return int 返回要播放的帧
//	 */
//	public static int autoRunSprite (int index, int seriesIdx, int nowFrameId, long lastChangeTime, long nowTime, long delay)
//	{
//		SpriteInfo spr = Res.getSpriteInfo(index);
//		delay = spr.delay;
//		delay = 100;
//		int frameSize = spr.getSeriesSize(seriesIdx);
//		if(nowTime - lastChangeTime >= delay)
//		{
//			nowFrameId++;
//			return nowFrameId >= frameSize ? 0 : nowFrameId;
//		}
//		else
//		{
//			return -1;
//		}
//	}

	/**
	 * 获取指定资源的锚点坐标x<精灵动画>
	 * 
	 * @param resId
	 *        int 资源id
	 * @param index
	 *        int 精灵系列
	 * @return int
	 */
	public static int getOffX (int resId, int index)
	{
		Res res = Res.getRes(resId);
		return getOffX(res, index);
	}

	/**
	 * 返回指定资源的锚点坐标
	 * 
	 * @param res
	 *        Res
	 * @param index
	 *        int
	 * @return int
	 */
	public static int getOffX (Res res, int index)
	{
		if(res instanceof SpriteInfo)
		{
			return ((SpriteInfo)res).getBaseX_series(index);
		}
		return 0;
	}

	/**
	 * 获取指定资源的锚点坐标y
	 * 
	 * @param resId
	 *        int 资源id
	 * @param index
	 *        int 精灵系列
	 * @return int
	 */
	public static int getOffY (int resId, int index)
	{
		Res res = Res.getRes(resId);
		return getOffY(res, index);
	}

	/**
	 * 返回指定资源的锚点坐标
	 * 
	 * @param res
	 *        Res
	 * @param index
	 *        int
	 * @return int
	 */
	public static int getOffY (Res res, int index)
	{
		if(res instanceof SpriteInfo)
		{
			return ((SpriteInfo)res).getBaseY_series(index);
		}
		return 0;
	}

	/**
	 * 获取指定资源指定索引的宽度
	 * 
	 * @param resId
	 *        int 资源id
	 * @param index
	 *        int 索引<图片的切割索引、精灵的系列>
	 * @return int
	 */
	public static int getWidth (int resId, int index)
	{
		Res res = Res.getRes(resId);
		return getWidth(res, index);
	}

	/**
	 * 获取指定资源的指定索引的宽度
	 * 
	 * @param res
	 *        Res
	 * @param index
	 *        int
	 * @return int
	 */
	public static int getWidth (Res res, int index)
	{
		if(res!=null)
		{
			if(res instanceof Png)
			{
				if(index == -1)
				{
					return ((Png)res).getWidth();
				}
				short[] refInfo= ((Png)res).getSelRefInfo(index);
				if(refInfo!=null)
				{
					return ((Png)res).getSelRefInfo(index)[2];
				}
				else
				{
					return 0;
				}
			}
			else if(res instanceof SpriteInfo)
			{
				return ((SpriteInfo)res).getWidth(index);
			}
		}
		return 0;
	}

	/**
	 * 获取指定资源指定索引的高度
	 * 
	 * @param resId
	 *        int 资源id
	 * @param index
	 *        int 索引<图片的切割索引、精灵的系列>
	 * @return int
	 */
	public static int getHeight (int resId, int index)
	{
		Res res = Res.getRes(resId);
		return getHeight(res, index);
	}

	/**
	 * 获取指定资源指定索引的高度
	 * 
	 * @param res
	 *        Res
	 * @param index
	 *        int
	 * @return int
	 */
	public static int getHeight (Res res, int index)
	{   if(res!=null)
		{
			if(res instanceof Png)
			{
				if(index == -1)
				{
					return ((Png)res).getHeight();
				}
				short[] refInfo= ((Png)res).getSelRefInfo(index);
				if(refInfo!=null)
				{
					return ((Png)res).getSelRefInfo(index)[3];
				}
				else
				{
					return 0;
				}
			}
			else if(res instanceof SpriteInfo)
			{
				return ((SpriteInfo)res).getHeight(index);
			}
		}
		return 0;
	}

	/**
	 * 返回绘制的物件是否在当前屏幕内
	 * 
	 * @param mapX
	 *        int 当前地图左上X坐标
	 * @param mapY
	 *        int
	 * @param x
	 *        int 绘制物件的x坐标
	 * @param y
	 *        int
	 * @param w
	 *        int 绘制物件的宽
	 * @param h
	 *        int
	 * @return boolean
	 */
	public static boolean inField (int mapX, int mapY, int x, int y, int w, int h)
	{
		if(x + w < mapX || y + h < mapY || x > mapX + GameDefinition.screenWidth|| y > mapY + GameDefinition.screenHeight)
		{
			return false;
		}
		return true;
	}

	/**
	 * 绘制指定资源
	 * 
	 * @param g
	 *        Graphics
	 * @param resId
	 *        int 资源id
	 * @param x
	 *        int 绘制X坐标
	 * @param y
	 *        int
	 * @param index
	 *        int 索引<图片的切割索引、精灵的系列>
	 * @param colorIndex
	 *        int 变色索引
	 * @param frameId
	 *        int 精灵绘制的帧索引
	 * @param flip
	 *        int 翻转方式 详见本类中的TRANS_ARRAYS数组
	 */
	public static void draw (Graphics g, int resId, int x, int y, int index, int colorIndex, int frameId, int flip, int anchor)
	{
		Res res = Res.getRes(resId);
		 
		if(res != null)
		{
			res.draw(g, x, y, colorIndex, index, frameId, flip, anchor);
		}
	}

	/**
	 * 绘制指定资源 
	 * @param g
	 * @param res
	 * @param x
	 * @param y
	 * @param index
	 * @param colorIndex
	 * @param frameId
	 * @param flip 翻转方式，详见本类中的TRANS_ARRAYS数组
	 */
	public static void draw (Graphics g, Res res, int x, int y, int index, int colorIndex, int frameId, int flip, int anchor)
	{
		if(res != null)
		{
			res.draw(g, x, y, colorIndex, index, frameId, flip, anchor);
		}
	}
}
