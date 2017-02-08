package res;

import java.util.*;
import javax.microedition.lcdui.*;
import res.mapLayer.*;

public class MapInfo extends Res
{
	/** 所有地图层次 */
	public Vector layers = new Vector();

	/** 活动对象层 */
	public Res[] actsRes;
	public short[] actsResId;
	public short[] actsX;
	public short[] actsY;
	public byte[] flips;
	public byte[] serieShow;
	public byte[] colorIndex;
	public byte[] frameId; //精灵显示到第几帧
	public long[] lastChangeTimeUpFace; //播放到此帧的时间

	/** 属性层 */
	public AttrLayer attr;

	/** 地底层 */
	public TileLayer title;

	/** 地表层 */
	public SurfaceLayer surface;
	
	 
	
	
	/** 地图宽、高 */
	public int width;
	public int height;

	protected MapInfo()
	{
	}

	
	public void destroy ()
	{
	}

	/**
	 * 把地图的指定区域绘制到画布的指定位置
	 * 
	 * @param g
	 * @param drawX
	 *        画布上的指定位置
	 * @param drawY
	 * @param offX
	 *        地图的指定区域
	 * @param offY
	 * @param w
	 * @param h
	 */
	public void paint (Graphics g, int drawX, int drawY, int offX, int offY, int w, int h)
	{
		if(offX<0)
		{
			offX=Math.abs(offX);
			offX=offX%title.mapWidth;
			offX=title.mapWidth-offX;
		}
		else
		{
			offX=offX%title.mapWidth;
		}
		if(offY<0)
		{
			offY=Math.abs(offY);
			offY=offY%title.mapHeight;
			offY=title.mapHeight-offY;
		}
		else
		{
			offY=offY%title.mapHeight;
		}
		for(int i = layers.size() - 1;i >= 0;i--)
		{
			if(layers.elementAt(i) instanceof TileLayer)
			{
				((TileLayer)layers.elementAt(i)).paint(g, drawX, drawY, offX, offY, w, h);
			}
			else if(layers.elementAt(i) instanceof SurfaceLayer)
			{
				((SurfaceLayer)layers.elementAt(i)).paint(g, drawX, drawY, offX, offY, w, h,title.mapWidth,title.mapHeight);
			}
			g.setClip(0, 0, Short.MAX_VALUE, Short.MAX_VALUE);
		}
				
	}

	/** 绘制地底层、地表层,活动对象层在排序时绘制 */
	
	public void draw (Graphics g, int x, int y, int colorIndex, int index, int frameId, int flip, int anchor)
	{
		for(int i = layers.size() - 1;i >= 0;i--)
		{
			if(layers.elementAt(i) instanceof AttrLayer)
				continue;
			g.setClip(0, 0, Short.MAX_VALUE, Short.MAX_VALUE);
			((Res)layers.elementAt(i)).draw(g, x, y, 0, 0, 0, 0, anchor);
		}
	}

	public void paintAttr (Graphics g, int drawX, int drawY, int offX, int offY)
	{
		for(int i = layers.size() - 1;i >= 0;i--)
		{
			if(layers.elementAt(i) instanceof AttrLayer)
			{
				((AttrLayer)layers.elementAt(i)).draw(g, drawX - offX, drawY - offY, 0, 0, 0, 0, 0);
			}
		}
	}
}
