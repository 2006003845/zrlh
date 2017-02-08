package res;

import javax.microedition.lcdui.*;
import res.*;

public class Sprite
{

	Res spInfo;//动画播放的资源 可以是图片在不同位置播放成动画 也可以是精灵动画
	public boolean isShowNow = true;//控制此动画是否立即显示 涉及到资源的同步异步载入
	public boolean isVisable;//是否可见
	
	public int curTime;//当前次数
	public int maxTime;//最大次数

	public int width;//宽度
	public int height;//高度

	public int vX;
	public int vY;
	public int aX;
	public int aY;
	public int centiX;
	public int centiY;

	private int curFrame;//当前帧
	private int curSeries;//当前系列

	public int resExIndex;//sprite的资源索引
	public int color;//要画的颜色
	public int trans;//翻转
	public short showX;//绘制坐标x
	public short showY;//绘制坐标y
	private boolean off;
	public long lastChangeTime = System.currentTimeMillis();//上一次画的时间

	/**
	 * 构造函数
	 * 
	 * @param spInfo
	 *        Res
	 * @param locX
	 *        int
	 * @param locY
	 *        int
	 */
	public Sprite(Res spInfo, int locX, int locY)
	{
		init(locX, locY);
		this.spInfo = spInfo;
	}

	/**
	 * 重载的构造函数，用来实现简单的动画播放 也可以加载图片
	 * 
	 * @param spInfo
	 *        SpriteInfo
	 * @param locX
	 *        int
	 * @param locY
	 *        int
	 * @param resExIndex
	 *        int
	 * @param index 切片
	 *        int
	 * @param color
	 *        int
	 * @param tran
	 *        int
	 * @param off
	 *        boolean
	 * @param isShowNow
	 *        boolean 是否必须立即载入资源
	 */
	public Sprite(int resExIndex, int index, int color, int locX, int locY, int tran, boolean off, boolean isShowNow)
	{
		init(locX, locY);
		this.resExIndex = resExIndex;
		this.isShowNow = isShowNow;
		this.spInfo = ResManager.getRes(resExIndex, isShowNow);
		
		this.off = off;
		this.color = color;
		this.trans = tran;
		curSeries = index;
	}
	

	public Sprite(int index, int color, int locX, int locY, int tran, boolean off)
	{
		init(locX, locY);
		
		this.off = off;
		this.color = color;
		this.trans = tran;
		curSeries = index;
	}
	public void setSpInfo(Res res,int resExindex/*,boolean showNow*/)
	{
		this.spInfo = res;
		this.resExIndex = resExindex;
//		this.isShowNow = showNow;
	}

	/**
	 * 初始设置
	 * 
	 * @param x
	 *        int
	 * @param y
	 *        int
	 */
	private void init (int x, int y)
	{
		isVisable = true;
		setShowXY(x, y);
		centiX = 100 * showX;
		centiY = 100 * showY;
	}

	/**
	 * 动画的画法
	 * 
	 * @param g
	 *        Graphics
	 */
	public void draw (Graphics g)
	{
		Res.draw(g, getSpInfo(), showX, showY, curSeries, color, curFrame, trans,0);
	}
	
	 

	/**
	 * 动画的绘制 包括逻辑判断
	 * 
	 * @param g
	 *        Graphics
	 * @param x
	 *        int 动画绘制的位置
	 * @param y
	 *        int
	 */
	public void draw (Graphics g, int x, int y)
	{
		if(getSpInfo() == null || !isVisable)
			return;
		setShowXY(x, y);
//		autoRunSprite();
		Res.draw(g, spInfo, showX, showY, curSeries, color, curFrame, trans,0);
	}

	/**
	 * 得到动画的下一帧 准备用nextCycFrame代替
	 * 
	 * @return int
	 */
	public int autoRunSprite ()
	{
		long nowTime = System.currentTimeMillis();
		int t = Res.autoRunSprite(getSpInfo(), curSeries, curFrame, lastChangeTime, nowTime,100);
		if(t >= 0)
		{
			if(curFrame != t)
			{
				curFrame = (byte)t;
			}
			lastChangeTime = nowTime;
			if(t == 0)
			{
				return 2;
			}
			else
			{
				return 1;
			}
		}
		return 0;
	}
	
	/**
	 * 得到动画的下一帧 准备用nextCycFrame代替
	 * 
	 * @return int
	 */
	public int autoRunSprite (long delay)
	{
		long nowTime = System.currentTimeMillis();
		int t = Res.autoRunSprite(getSpInfo(), curSeries, curFrame, lastChangeTime, nowTime,delay);
		if(t >= 0)
		{
			if(curFrame != t)
			{
				curFrame = (byte)t;
			}
			lastChangeTime = nowTime;
			if(t == 0)
			{
				return 2;
			}
			else
			{
				return 1;
			}
		}
		return 0;
	}

	/**
	 * 循环播放动画
	 */
	public void nextCycFrame ()
	{
		long nowTime = System.currentTimeMillis();
		int t = Res.autoRunSprite(getSpInfo(), curSeries, curFrame, lastChangeTime, nowTime);
		if(t >= 0)
		{
			curFrame = (byte)t;
			lastChangeTime = nowTime;
		}
	}

	/**
	 * 播放动画，当播放完后停止
	 * 
	 * @return boolean true——播放完毕 false——未播放完毕
	 */
	public boolean nextFrame ()
	{
		long nowTime = System.currentTimeMillis();
		int t = Res.autoRunSprite(getSpInfo(), curSeries, curFrame, lastChangeTime, nowTime);
		if(t > 0)
		{
			curFrame = (byte)t;
			lastChangeTime = nowTime;
			return false;
		}
		else if(t < 0)
		{
			return false;
		}
		return true;//t == 0
	}

	/**
	 * 获得当前动画序列的帧
	 * 
	 * @return int
	 */
	public int getCurFrame ()
	{
		return curFrame;
	}

	/**
	 * 获得当前动画序列
	 * 
	 * @return int
	 */
	public int getCurSeries ()
	{
		return curSeries;
	}

	/**
	 * 设置当前动画序列的动画帧
	 * 
	 * @param frame
	 *        int
	 */
	public void setCurFrame (int frame)
	{
		int frameSize = getSeriesSize();
		if (frame < 0)
		{
			frame = 0;
		}
		else if(frame >= frameSize)
		{
			frame = frameSize -1;
		}
		curFrame = (byte)frame;
	}

	/**
	 * 设置当前动画序列
	 * 
	 * @param series
	 *        int
	 */
	public void setCurSeries (int series)
	{
		curSeries = (byte)series;
	}

	/**
	 * 获取动画当前播放系列的X基点坐标
	 * 
	 * @return int
	 */
	public int getcurSeriesbaseX ()
	{
		return Res.getOffX(getSpInfo(), curSeries);
	}

	/**
	 * 获取动画当前播放系列的Y基点坐标
	 * 
	 * @return int
	 */
	public int getcurSeriesbaseY ()
	{
		return Res.getOffY(getSpInfo(), curSeries);
	}

	/**
	 * 得到精灵的x坐标
	 * 
	 * @return int
	 */
	public int getRefreshX ()
	{
		return showX - (off ? Res.getOffX(getSpInfo(), curSeries) : 0);
	}

	/**
	 * 得到精灵的y坐标
	 * 
	 * @return int
	 */
	public int getRefreshY ()
	{
		return showY - (off ? Res.getOffY(getSpInfo(), curSeries) : 0);
	}

	/**
	 * 得到精灵的宽
	 * 
	 * @return int
	 */
	public int getRefreshWidth ()
	{
		return Res.getWidth(getSpInfo(), curSeries);
	}

	/**
	 * 得到精灵的高
	 * 
	 * @return int
	 */
	public int getRefreshHeight ()
	{
		return Res.getHeight(getSpInfo(), curSeries);
	}

	/**
	 * 获取指定系列的帧数
	 * 
	 * @param series
	 *        int
	 * @return int
	 */
	public int getFrameSize (int series)
	{
		SpriteInfo sp = getSpriteInfo();
		if(sp == null)
		{
			return -1;
		}
		return sp.getSeriesSize(series);
	}
	
	public int getSeriesSize()
	{
		SpriteInfo sp = getSpriteInfo();
		if(sp == null)
		{
			return -1;
		}
		if(sp.baseX_Series==null)
		{
			return -1;
		}
			 
		return sp.baseX_Series.length;
	}

	/**
	 * 获取精灵资源
	 * 
	 * @return Res
	 */
	public Res getSpInfo ()
	{
		if(spInfo == null)
		{
			spInfo = ResManager.getRes(resExIndex, isShowNow);
		}
		return spInfo;
	}

	/**
	 * 获取动画资源
	 * 
	 * @return SpriteInfo
	 */
	public SpriteInfo getSpriteInfo ()
	{
		Res res = getSpInfo();
		if(res == null || !(res instanceof SpriteInfo))
		{
			return null;
		}
		return (SpriteInfo)res;
	}

	/**
	 * 设置 x,y的坐标
	 * 
	 * @param x
	 *        int
	 * @param y
	 *        int
	 */
	public void setShowXY (int x, int y)
	{
		showX = (short)x;
		showY = (short)y;
	}

	/**
	 * 释放资源
	 */
	public void release ()
	{
		ResManager.clearRes(spInfo.id);
		spInfo = null;
	}
}
