package res;

import java.util.*;

import javax.microedition.lcdui.*;
import java.io.*;

public class SpriteInfo extends Res
{
	/** ����ϵ��*/
	public Vector frames = new Vector();
	/**���������õ�����png��Դ��id */
	public Vector allPngId=new Vector();

	/** ÿһϵ�еĻ���,��,�� */
	public short[] baseX_Series; //
	public short[] baseY_Series;
	short[] width;
	short[] height;

	/** �ӳ٣�ms */
	public short delay;

	protected SpriteInfo()
	{
	}
	
	private byte seriesNum= 0;
	
	public byte getSeriesNum()
	{
		return seriesNum;
	}
	
	public int getSeries(int series)
	{
		if(series < 0)
			series = 0;
		if(series > baseX_Series.length - 1)
			series = baseX_Series.length - 1;
		return series;
	}
	
	void load (DataInputStream dis) throws IOException
	{
		frames.removeAllElements();
		delay = dis.readShort();
		seriesNum = dis.readByte();
		baseX_Series = new short[seriesNum];
		baseY_Series = new short[seriesNum];
		width = new short[seriesNum];
		height = new short[seriesNum];
		for(int i = 0;i < seriesNum;i++)
		{
			Vector frms = new Vector();
			int frmNum = dis.readByte();
			//����ÿһϵ�еĻ���
			width[i] = dis.readShort();
			height[i] = dis.readShort();
			baseX_Series[i] = dis.readShort();
			baseY_Series[i] = dis.readShort();
			for(int j = 0;j < frmNum;j++)
			{
				SpriteFrame sf = new SpriteFrame();
				sf.load(dis);
				frms.addElement(sf);
			}
			frames.addElement(frms);
		}
	}

	
	public void destroy ()
	{
	}

	
	public void draw (Graphics g, int x, int y, int colorIndex, int index, int frameId, int flip, int anchor)
	{
		int curSeries = index;
		int curFrame = frameId;
		 
		if(curSeries < 0 || curSeries >= frames.size())
			return;
		Vector frms = (Vector)frames.elementAt(curSeries);
		if(curFrame >= frms.size())
			return;
		if(curFrame < 0)
			curFrame = 0;
		((SpriteFrame)frms.elementAt(curFrame)).draw(g, x - getBaseX_series(curSeries), y - getBaseY_series(curSeries), getWidth(curSeries), getHeight(curSeries), colorIndex, flip, anchor);
	}

	public short getBaseX_series (int series)
	{
		if(series < 0)
			series = 0;
		if(series > baseX_Series.length - 1)
			series = baseX_Series.length - 1;
		return baseX_Series[series];
	}

	public short getBaseY_series (int series)
	{
		if(series < 0)
			series = 0;
		if(series > baseY_Series.length - 1)
			series = baseY_Series.length - 1;
		return baseY_Series[series];
	}

	/** ��ȡָ���������е�֡������Ч�����з���-1 */
	public int getSeriesSize (int seriesIdx)
	{
		if(seriesIdx < 0 || seriesIdx >= frames.size())
			return -1;
		Vector frms = (Vector)frames.elementAt(seriesIdx);
		return frms.size();
	}

	/** ��ȡָ��ϵ�����֡��� */
	public int getWidth (int serie)
	{
		if(serie < 0)
			serie = 0;
		if(serie > width.length - 1)
			serie = width.length - 1;
		return width[serie];
		//if(width[serie] > 0){
		//return width[serie];
		//}
		//int w = 20;
		//Vector frms = (Vector) frames.elementAt(serie);
		//for (int j = 0; j < frms.size(); j++) {
		//SpriteFrame frm = ( (SpriteFrame) frms.elementAt(j));
		//if (w < frm.getWidth()) {
		//w = frm.getWidth();
		//}
		//}
		//return w;
	}

	/** ��ȡ���֡�߶� */
	public int getHeight (int serie)
	{
		if(serie < 0)
			serie = 0;
		if(serie > height.length - 1)
			serie = height.length - 1;
		return height[serie];
		//if(height[serie] > 0){
		//return height[serie];
		//}
		//int h = 20;
		//Vector frms = (Vector) frames.elementAt(serie);
		//for (int j = 0; j < frms.size(); j++) {
		//SpriteFrame frm = ( (SpriteFrame) frms.elementAt(j));
		//if (h < frm.getHeight()) {
		//h = frm.getHeight();
		//}
		//}
		//return h;
	}

	 

	/** ����֡ */
	class SpriteFrame
	{
		Res[] refsRes;
		/** ��λ�洢֡����Ϣ 1~13ΪͼƬid 14~16Ϊ���÷�ת���� */
		short[] refsInfoOne;
		/**
		 * ��λ�洢֡����Ϣ 1~9ΪrefsX 10~16ΪcolorIndex colorIndex--��Ϊ��ɫͼƬ,��Ϊ��ɫ����,����ͼƬΪ-1
		 */
		short[] refsInfoTwo;
		/** ��λ�洢֡����Ϣ 1~9ΪrefsY 10~16ΪrefIndexͼƬ�������� */
		short[] refsInfoThree;

		public void load (DataInputStream dis) throws IOException
		{
			dis.readShort();
			int size = dis.readByte(); 
			if(size < 0)
			{
				size = 255&size;
			} 
			
			refsRes = new Res[size];
			refsInfoOne = new short[size];
			refsInfoTwo = new short[size];
			refsInfoThree = new short[size];
			short[] refsId = new short[size];
			byte[] flips = new byte[size];
			short[] refsX = new short[size];
			short[] refsY = new short[size];
			byte[] colorIndex = new byte[size];
			byte[] refIndex = new byte[size];
			for(int i = 0;i < size;i++)
			{
				refsId[i] = dis.readShort();//ͼƬid
				//��ɫ����(����ͼƬΪ-1)
				int index = Res.colorResIndex(refsId[i]);
				if(index != -1)
				{
					colorIndex[i] = Res.colorIndex[index];
				}
				else
				{
					colorIndex[i] = 63;
				}
				refsRes[i] = ResManager.getRes(refsId[i],false);
				refIndex[i] = dis.readByte();
				refsX[i] = dis.readShort();
				refsY[i] = dis.readShort();
				flips[i] = dis.readByte();

				refsInfoOne[i] = (short)((flips[i] << 13) + refsId[i]);
				refsInfoTwo[i] = (short)((colorIndex[i] << 9) + refsX[i]);
				refsInfoThree[i] = (short)((refIndex[i] << 9) + refsY[i]);
				
				Integer resId=new Integer(refsId[i]);
				if(!allPngId.contains(resId))
				{
					allPngId.addElement(resId);
				}
			}
		}

		/**
		 * ��ȡָ���п����õ�ͼƬid
		 * 
		 * @param index
		 *        int
		 * @return int
		 */
		public int getRefsId (int index)
		{
			return (short)(refsInfoOne[index] & 0x1fff);
		}

		/**
		 * ��ȡָ���п����õ�����
		 * 
		 * @param index
		 *        int
		 * @return int
		 */
		public int getRefIndex (int index)
		{
			return (refsInfoThree[index] >>> 9) & 0x7f;
		}

		/**
		 * ��ȡָ���п�����õ�ͼƬ�ķ�ת����
		 * 
		 * @param index
		 *        int
		 * @return int
		 */
		public int getRefsFlip (int index)
		{
			return refsInfoOne[index] >> 13;
		}

		/**
		 * ��ȡָ���п�Xƫ��ֵ
		 * 
		 * @param index
		 *        int
		 * @return int
		 */
		public int getRefsX (int index)
		{
			return (short)(refsInfoTwo[index] & 0x1ff);
		}

		/**
		 * ��ȡָ���п�Yƫ��ֵ
		 * 
		 * @param index
		 *        int
		 * @return int
		 */
		public int getRefsY (int index)
		{
			return refsInfoThree[index] & 0x1ff;
		}

		/**
		 * ��ȡָ���п��ɫ����
		 * 
		 * @param index
		 *        int
		 * @return int
		 */
		public int getColorIndex (int index)
		{
			//return refsInfoTwo[index]>>>9;
			short color = (short)(refsInfoTwo[index] >>> 9);
			return (color == 63) ? -1 : color;
		}

		/** ��ȡ֡��ȣ�������������Դ����󵽴ﷶΧ */
		//public int getWidth() {
		//int w = 20;
		//for (int i = 0; i < refsId.length; i++) {
		//if (refsRes[i] == null) {
		//refsRes[i] = Res.getRes(refsId[i]);
		//}
		//if (refsRes[i] == null || !(refsRes[i] instanceof Png)) continue;
		//int x = refsX[i];
		//if (w < x + ((Png)refsRes[i]).getSelRefInfo(refIndex[i])[2]) {
		//w = x + ((Png)refsRes[i]).getSelRefInfo(refIndex[i])[2]; //����x+ͼƬ���õĿ��
		//}
		//}
		//return w;
		//}

		/** ��ȡ֡��ȣ�������������Դ����󵽴ﷶΧ */
		//public int getHeight() {
		//int h = 20;
		//for (int i = 0; i < refsId.length; i++) {
		//if (refsRes[i] == null) {
		//refsRes[i] = Res.getRes(refsId[i]);
		//}
		//if (refsRes[i] == null || !(refsRes[i] instanceof Png)) continue;
		//int y = refsY[i];
		//if (h < y + ((Png)refsRes[i]).getSelRefInfo(refIndex[i])[3]) {
		//h = y + ((Png)refsRes[i]).getSelRefInfo(refIndex[i])[3]; //����x+ͼƬ���õĿ��
		//}
		//}
		//return h;
		//}

		public void draw (Graphics g, int x, int y, int w, int h, int colorindex, int flip_SpriteFrame, int anchor)
		{
			if(refsRes == null )return;
			for(int i = 0;i < refsRes.length;i++)
			{
//				if(refsRes[i] == null)
//				{
//					refsRes[i] = Res.getRes(getRefsId(i));
//				}
				if(refsRes[i] == null || !(refsRes[i] instanceof Png)||!refsRes[i].loaded)
					continue;
				int color = getColorIndex(i);
				if(((Png)refsRes[i]).type == Res.RES_TYPE_COLORIMAGE)
				{ //�����ɫ
					if(colorindex != -1 && colorindex <= ((Png)refsRes[i]).colorNum)
					{
						color = colorindex;
					}
				}
				
				short[] refInfo = ((Png)refsRes[i]).getSelRefInfo(getRefIndex(i));
				if(refInfo == null)continue;
				int[] data = setSpriteFrame(refInfo, getRefsX(i), getRefsY(i), w, h, getRefsFlip(i), flip_SpriteFrame);
				int x_ = data[0];
				int y_ = data[1];
				int fliP_ = data[2];
				refsRes[i].draw(g, x + x_, y + y_, color, getRefIndex(i), 0, fliP_, anchor);
			}
		}

		/** ���þ��鷭ת��֡�����õ��������ͷ�ת���Եĸı� */
		public int[] setSpriteFrame (short[] ref, int x, int y, int w, int h, int flip, int flip_Surface)
		{
			int[] set = new int[3];
			int width = ref[2];
			int height = ref[3];
			if(flip_Surface == 2)
			{
				if(flip == 0)
				{
					flip = 2;
				}
				else if(flip == 2)
				{
					flip = 0;
				}
				else if(flip == 1)
				{
					flip = 3;
				}
				else if(flip == 3)
				{
					flip = 1;
				}
				x = w - x - width;
			}
			else if(flip_Surface == 1)
			{
				if(flip == 0)
				{
					flip = 1;
				}
				else if(flip == 1)
				{
					flip = 0;
				}
				else if(flip == 2)
				{
					flip = 3;
				}
				else if(flip == 3)
				{
					flip = 2;
				}
				y = h - y - height;
			}
			else if(flip_Surface == 3)
			{
				if(flip == 0)
				{
					flip = 3;
				}
				else if(flip == 1)
				{
					flip = 2;
				}
				else if(flip == 2)
				{
					flip = 1;
				}
				else if(flip == 3)
				{
					flip = 0;
				}
				x = w - x - width;
				y = h - y - height;
			}
			set[0] = x;
			set[1] = y;
			set[2] = flip;
			return set;
		}
	}
}
