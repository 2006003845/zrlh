package res;

import javax.microedition.lcdui.*;
import res.*;

public class Sprite
{

	Res spInfo;//�������ŵ���Դ ������ͼƬ�ڲ�ͬλ�ò��ųɶ��� Ҳ�����Ǿ��鶯��
	public boolean isShowNow = true;//���ƴ˶����Ƿ�������ʾ �漰����Դ��ͬ���첽����
	public boolean isVisable;//�Ƿ�ɼ�
	
	public int curTime;//��ǰ����
	public int maxTime;//������

	public int width;//���
	public int height;//�߶�

	public int vX;
	public int vY;
	public int aX;
	public int aY;
	public int centiX;
	public int centiY;

	private int curFrame;//��ǰ֡
	private int curSeries;//��ǰϵ��

	public int resExIndex;//sprite����Դ����
	public int color;//Ҫ������ɫ
	public int trans;//��ת
	public short showX;//��������x
	public short showY;//��������y
	private boolean off;
	public long lastChangeTime = System.currentTimeMillis();//��һ�λ���ʱ��

	/**
	 * ���캯��
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
	 * ���صĹ��캯��������ʵ�ּ򵥵Ķ������� Ҳ���Լ���ͼƬ
	 * 
	 * @param spInfo
	 *        SpriteInfo
	 * @param locX
	 *        int
	 * @param locY
	 *        int
	 * @param resExIndex
	 *        int
	 * @param index ��Ƭ
	 *        int
	 * @param color
	 *        int
	 * @param tran
	 *        int
	 * @param off
	 *        boolean
	 * @param isShowNow
	 *        boolean �Ƿ��������������Դ
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
	 * ��ʼ����
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
	 * �����Ļ���
	 * 
	 * @param g
	 *        Graphics
	 */
	public void draw (Graphics g)
	{
		Res.draw(g, getSpInfo(), showX, showY, curSeries, color, curFrame, trans,0);
	}
	
	 

	/**
	 * �����Ļ��� �����߼��ж�
	 * 
	 * @param g
	 *        Graphics
	 * @param x
	 *        int �������Ƶ�λ��
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
	 * �õ���������һ֡ ׼����nextCycFrame����
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
	 * �õ���������һ֡ ׼����nextCycFrame����
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
	 * ѭ�����Ŷ���
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
	 * ���Ŷ��������������ֹͣ
	 * 
	 * @return boolean true����������� false����δ�������
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
	 * ��õ�ǰ�������е�֡
	 * 
	 * @return int
	 */
	public int getCurFrame ()
	{
		return curFrame;
	}

	/**
	 * ��õ�ǰ��������
	 * 
	 * @return int
	 */
	public int getCurSeries ()
	{
		return curSeries;
	}

	/**
	 * ���õ�ǰ�������еĶ���֡
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
	 * ���õ�ǰ��������
	 * 
	 * @param series
	 *        int
	 */
	public void setCurSeries (int series)
	{
		curSeries = (byte)series;
	}

	/**
	 * ��ȡ������ǰ����ϵ�е�X��������
	 * 
	 * @return int
	 */
	public int getcurSeriesbaseX ()
	{
		return Res.getOffX(getSpInfo(), curSeries);
	}

	/**
	 * ��ȡ������ǰ����ϵ�е�Y��������
	 * 
	 * @return int
	 */
	public int getcurSeriesbaseY ()
	{
		return Res.getOffY(getSpInfo(), curSeries);
	}

	/**
	 * �õ������x����
	 * 
	 * @return int
	 */
	public int getRefreshX ()
	{
		return showX - (off ? Res.getOffX(getSpInfo(), curSeries) : 0);
	}

	/**
	 * �õ������y����
	 * 
	 * @return int
	 */
	public int getRefreshY ()
	{
		return showY - (off ? Res.getOffY(getSpInfo(), curSeries) : 0);
	}

	/**
	 * �õ�����Ŀ�
	 * 
	 * @return int
	 */
	public int getRefreshWidth ()
	{
		return Res.getWidth(getSpInfo(), curSeries);
	}

	/**
	 * �õ�����ĸ�
	 * 
	 * @return int
	 */
	public int getRefreshHeight ()
	{
		return Res.getHeight(getSpInfo(), curSeries);
	}

	/**
	 * ��ȡָ��ϵ�е�֡��
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
	 * ��ȡ������Դ
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
	 * ��ȡ������Դ
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
	 * ���� x,y������
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
	 * �ͷ���Դ
	 */
	public void release ()
	{
		ResManager.clearRes(spInfo.id);
		spInfo = null;
	}
}
