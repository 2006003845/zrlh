package res.mapLayer;

import java.util.*;
import javax.microedition.lcdui.*;

import com.zrong.Android.Util.SystemAPI;

import res.Res;

import java.io.*;
import res.SpriteInfo;
/**
 * 
 * <p>Title:SurfaceLayer.java </p>
 * <p>Description: 地表层</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: </p>
 * @author yangzheng
 * @version 1.0
 */
public class SurfaceLayer extends Res
{
	/** 地面层资源引用系列 */
	public Res[] refRes;
	public short[][] refs;//0-资源id 1-精灵系列/图片引用索引 2-x 3-y 4-翻转属性 5-资源物件为精灵动画时储存播放第几帧
	public byte[] colorIndex;//变色索引,非变色图片为-1
	public long[] lastChangeTime;

	public SurfaceLayer()
	{
	}

	public void load (DataInputStream dis) throws IOException
	{
		dis.readShort(); //width
		dis.readShort(); //height
		int size = dis.readShort();
		refRes = new Res[size];
		refs = new short[size][6];
		colorIndex = new byte[size];
		lastChangeTime = new long[size];
		for(int i = 0;i < refs.length;i++)
		{
			refs[i][0] = dis.readShort();
			//变色索引(常规图片为-1)
			int index = Res.colorResIndex(refs[i][0]);
			if(index != -1)
			{
				colorIndex[i] = Res.colorIndex[index];
			}
			else
			{
				colorIndex[i] = -1;
			}
			//colorIndex[i] = (byte)Res.resIndex[refs[i][0]][1];//变色索引(常规图片为-1)

			refRes[i] = Res.getMapRes(refs[i][0]); //预载入引用资源
			refs[i][1] = dis.readByte();
			refs[i][2] = dis.readShort();
			refs[i][3] = dis.readShort();
			refs[i][4] = dis.readByte();
			if(refRes[i] instanceof SpriteInfo)
			{//精灵随机设定初始帧
				Random rdm = new Random();
				int max = ((SpriteInfo)refRes[i]).getSeriesSize(refs[i][1]);
				refs[i][5] = (short)((rdm.nextInt() >>> 1) % max);
			}
			lastChangeTime[i] = 0;
		}
	}

	
	public void destroy ()
	{
	}

	public void paint (Graphics g, int drawX, int drawY, int offX, int offY, int w, int h,int mapW,int mapH )
	{
		g.setClip(drawX, drawY, w, h);
		int numW=(offX+w)/mapW+((offX+w)%mapW==0?0:1);
		int numH=(offY+h)/mapH+((offY+h)%mapH==0?0:1);
		int tempOffX;
		int tempOffY;
		int tempW;
		int tempH;
		
		for(int j=0;j<numW;j++)
		{
			
			if(j==0)
			{
				tempOffX=offX;
				if(offX+w>mapW)
				{
					tempW=mapW-offX;
				}
				else
				{
					tempW=w;
				}
			}
			else
			{
				tempOffX=0;
				if(offX+w>mapW*(j+1))
				{
					tempW=mapW;
				}
				else
				{
					tempW=offX+w-mapW*j;
				}
			}
			for(int k=0;k<numH;k++)
			{
				
				//计算y方向
				if(k==0)
				{
					tempOffY=offY;
					if(offY+h>mapH)
					{
						tempH=mapH-offY;
					}
					else
					{
						tempH=h;
					}
				}
				else
				{
					tempOffY=0;
					if(offY+h>mapH*(k+1))
					{
						tempH=mapH;
					}
					else
					{
						tempH=offY+h-mapH*k;
					}
				}
				for(int i = 0;i < refs.length;i++)
				{
					if(refRes[i] == null)
					{
						
					}
					int containType = SystemAPI.inField(refs[i][2] - Res.getOffX(refRes[i], refs[i][1]), refs[i][3] - Res.getOffY(refRes[i], refs[i][1]), Res.getWidth(refRes[i], refs[i][1]), Res.getHeight(refRes[i], refs[i][1]), tempOffX, tempOffY, tempW, tempH);
					if(containType == SystemAPI.FIELD_IN || containType == SystemAPI.FIELD_CROSS)
					{
						if(refRes[i] instanceof SpriteInfo)
						{
							int nextFrame = Res.autoRunSprite(refRes[i], refs[i][1], refs[i][5], lastChangeTime[i], 0);
							if(nextFrame >= 0)
							{
								refs[i][5] = (short)nextFrame;
							}
						}
						refRes[i].draw(g, refs[i][2] + drawX - offX+j*mapW, refs[i][3] + drawY - offY+k*mapH, colorIndex[i], refs[i][1], refs[i][5], refs[i][4], 0);
					}
				}
			}
		}
		
		g.setClip(0, 0, Short.MAX_VALUE, Short.MAX_VALUE);
	}

	
	public void draw (Graphics g, int x, int y, int colorIndex_, int index, int frameId, int flip, int anchor)
	{
		long nowTime = System.currentTimeMillis();
		for(int i = 0;i < refs.length;i++)
		{
			if(refRes[i] == null)
			{
//				refRes[i] = ResManager.getRes(refs[i][0], false);
//				if(refRes[i] == null)
					continue;
			}
			if(Res.inField(x, y, refs[i][2] - Res.getOffX(refRes[i], refs[i][1]), refs[i][3] - Res.getOffY(refRes[i], refs[i][1]), Res.getWidth(refRes[i], refs[i][1]), Res.getHeight(refRes[i], refs[i][1])))
			{
				if(refRes[i] instanceof SpriteInfo)
				{
					int nextFrame = Res.autoRunSprite(refRes[i], refs[i][1], refs[i][5], lastChangeTime[i], nowTime);
					if(nextFrame >= 0)
					{
						refs[i][5] = (short)nextFrame;
						lastChangeTime[i] = nowTime;
					}
				}
				refRes[i].draw(g, refs[i][2] - x, refs[i][3] - y, colorIndex[i], refs[i][1], refs[i][5], refs[i][4], anchor);
			}
		}
	}
}
