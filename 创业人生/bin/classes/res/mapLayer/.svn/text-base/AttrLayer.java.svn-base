package res.mapLayer;

import javax.microedition.lcdui.*;
import res.Res;
import java.io.*;

public class AttrLayer extends Res
{
	/** 属性块颜色属性 */
	public static final int ATTR_TRANSPARENT = 0;
	public static final int ATTR_RED = 1;
	public static final int ATTR_BLUE = 2;
	public static final int ATTR_YELLOW = 3;
	public static final int ATTR_GRAY = 4;
	public static final int ATTR_PINK = 5;
	public static final int ATTR_WHITE = 6;

	/** 属性块大小 */
	int propW = 8;
	int propH = 8;

	/** 块属性矩阵 */
	public byte[][] propInfo;

	public AttrLayer()
	{
	}

	/** 读取属性层 */
	public void load (DataInputStream dis) throws IOException
	{
		propW = dis.readShort();
		propH = dis.readShort();
		int rows = dis.readShort();
		int cols = dis.readShort();
		propInfo = new byte[rows][cols];
		int size = rows * cols;
		byte[] props = new byte[size];
		for(int i = 0;i < size / 4;i++)
		{
			byte value = dis.readByte();
			//属性层按位储存 2位储存一个属性块 暂时只支持0~3属性
			props[i * 4] = (byte)(value >> 6);
			props[i * 4 + 1] = (byte)((value >> 4) & 0x0003);
			props[i * 4 + 2] = (byte)((value >> 2) & 0x0003);
			props[i * 4 + 3] = (byte)(value & 0x0003);
		}
		//补余
		for(int i = 0;i < (rows * cols) % 4;i++)
		{
			props[props.length - ((rows * cols) % 4) + i] = dis.readByte();
		}
		int m = 0;
		for(int i = 0;i < rows;i++)
		{
			for(int j = 0;j < cols;j++)
			{
				propInfo[i][j] = props[m];
				m++;
			}
		}
	}

	/** 获取属性块宽 */
	public int getPropW ()
	{
		return this.propW;
	}

	/** 获取属性块高 */
	public int getPropH ()
	{
		return this.propH;
	}

	
	public void destroy ()
	{
		propInfo = null;
	}

	/** 绘制属性层 */
	
	public void draw (Graphics g, int x, int y, int colorIndex, int index, int frameId, int flip, int anchor)
	{
		for(int i = 0;i < propInfo.length;i++)
		{
			for(int j = 0;j < propInfo[i].length;j++)
			{
				switch(propInfo[i][j])
				{
					//case ATTR_RED:
					//g.setColor(255, 0, 0);
					//break;
					//case ATTR_BLUE:
					//g.setColor(0, 0, 255);
					//break;
					//case ATTR_GRAY:
					//g.setColor(128, 128, 128);
					//break;
					//case ATTR_PINK:
					//g.setColor(128, 0, 0);
					//break;
					//case ATTR_YELLOW:
					//g.setColor(0, 255, 255);
					//break;
					//case ATTR_WHITE:
					//g.setColor(255, 255, 255);
					//break;
					default:
						break;
				}
				if(propInfo[i][j] != 0)
					g.fillRect(x + j * propW, y + i * propH, propW, propH);
			}
		}
	}
}
