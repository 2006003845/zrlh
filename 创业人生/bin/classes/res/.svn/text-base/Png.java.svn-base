package res;

import javax.microedition.lcdui.*;

import com.zrong.Android.Util.SystemAPI;

import java.io.IOException;
import java.io.DataInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;

public class Png extends Res
{
	/** 图片类型 1-常规图片 13-变色图片 */
	byte type;
	
	/** 所有引用切块 <x偏移值 y偏移值 宽 高> */
	short[][] allRefs;

	/** 图片大小 */
	short width = 0;
	short height = 0;

	/** 常规图片 */
	byte pngData[];
	Image image;

	//变色图片数据
	public byte colorNum;
	public short pltesSize;
	public int[] pltes;
	public byte[] pixData;
	public Image[] colorImage;

	/** 新格式变色图片数据 */
	byte[][] iTxt_iDat_iHdrData;//png的tEXt、IHDR、IDAT三个模块数据,此模块数据为固定的
	byte[][] pLte_tRnsData;

	protected Png(Image img)
	{
		image = img;
	}

	/**
	 * 获取指定引用切块的信息
	 * 
	 * @param index
	 *        int 切块索引
	 * @return int[]
	 */
	public short[] getSelRefInfo (int index)
	{
		if(allRefs==null)return null;
		if(index > allRefs.length - 1)
			index = allRefs.length - 1;
		if(index < 0)
			index = 0;
		return allRefs[index];
	}

	protected Png(int type)
	{
		this.type = (byte)type;
	}

	public int getType ()
	{
		return this.type;
	}

	public void setImage (Image image)
	{
		this.image = image;
	}

	public Image getImage ()
	{
		if(image == null&&this.pngData!= null)
		{
			try
			{
				setImage(Image.createImage(new ByteArrayInputStream(this.pngData)));
			}catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
		return image;
	}

	public int getWidth ()
	{
		return this.width;
	}

	public int getHeight ()
	{
		return this.height;
	}

	/**
	 * 获取指定索引变色图片
	 * 
	 * @param colorIndex
	 *        int 变色索引
	 * @return Image
	 */
	public Image getColorImage (int colorIndex)
	{
		if(colorIndex < 0)
			colorIndex = 0;
		if(colorIndex > colorImage.length - 1)
			colorIndex = colorImage.length - 1;
		if(colorImage[colorIndex] == null)
		{
			int start = pltesSize * colorIndex;
			int[] tempPixData = new int[pixData.length];
			for(int i = 0;i < tempPixData.length;i++)
			{
				tempPixData[i] = pltes[start + (pixData[i] & 0x00ff)];
			}
			colorImage[colorIndex] = Image.createRGBImage(tempPixData, width, height, true);
		}
		return colorImage[colorIndex];
	}

	/**
	 * 清除图片资源
	 */
	public void clearImage ()
	{
		image = null;
		if(colorImage != null)
			colorImage = new Image[colorImage.length];
	}

	/**
	 * 读取常规图片数据,并设置png图片
	 * 
	 * @param dis
	 *        DataInputStream
	 */
	public void loadNormalImage (DataInputStream dis)
	{
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try
		{
			width = dis.readShort(); //图片宽
			height = dis.readShort();
			//读出引用
			loadAllRefs(dis);
			dis.readByte();
			//读出图片数据
			int size = dis.readInt();
			byte[] data = new byte[size];
			dis.read(data);

			bos.write(pngFileHead);
			bos.write(data);
			bos.write(pngFileEnd);
			pngData = bos.toByteArray();
		}catch(Exception ex)
		{
			ex.printStackTrace();
			 
		}
		finally
		{
			SystemAPI.closeByteArrayOutputStream(bos);
		}
	}

	/** 读取变色图片数据 */
	public void loadColorImage (DataInputStream dis)
	{
		ByteArrayInputStream bais = null;
		try
		{
			width = dis.readShort(); //图片宽
			height = dis.readShort();
			//读出引用
			loadAllRefs(dis);
			dis.readByte();
			//读出图片数据
			int size = dis.readInt();
			byte[] data = new byte[size];
			dis.read(data);

			bais = new ByteArrayInputStream(data);
			DataInputStream is = new DataInputStream(bais);
			byte colorSize = is.readByte();
			int pltesSize = is.readByte() & 0x000000ff;
			int[] pltes = new int[colorSize * pltesSize];
			for(int i = 0;i < pltes.length;i++)
			{
				pltes[i] = is.readInt();
			}
			int pixDataLength = is.readInt();
			byte[] pixData = new byte[pixDataLength];
			for(int i = 0;i < pixDataLength;i++)
			{
				pixData[i] = is.readByte();
			}

			//设置变色图片的数据
			colorNum = colorSize;
			this.pltesSize = (short)pltesSize;
			this.pltes = pltes;
			this.pixData = pixData;
			colorImage = new Image[colorNum];
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			SystemAPI.closeByteArrayInputStream(bais);
		}
	}

	/** 新的变色图片资源读取方式 */
	public void readColorImgData (DataInputStream dis)
	{
		try
		{
			width = dis.readShort(); //图片宽
			height = dis.readShort();
			//读出引用
			loadAllRefs(dis);
			dis.readByte();

			int num = dis.readByte();
			iTxt_iDat_iHdrData = new byte[num][];
			/** 读出png的tEXt、IHDR、IDAT三个模块数据 */
			for(int i = 0;i < num;i++)
			{
				int size = dis.readInt();
				byte[] data = new byte[size];
				dis.read(data);
				iTxt_iDat_iHdrData[i] = data;
			}
			/** 读出调色板数据,变色数为(colorNum/2) */
			int colorNum = dis.readByte();
			this.colorNum = (byte)(colorNum / 2);
			colorImage = new Image[colorNum / 2];
			pLte_tRnsData = new byte[colorNum][];
			for(int i = 0;i < colorNum;i++)
			{
				int size = dis.readInt();
				byte[] data = new byte[size];
				dis.read(data);
				pLte_tRnsData[i] = data;
			}
		}catch(Exception ex)
		{
			ex.printStackTrace();
			 
		}
	}

	/**
	 * 获取指定索引变色图片
	 * 
	 * @param colorIndex
	 *        int 变色索引
	 * @return Image
	 */
	public Image getColorImg (int colorIndex)
	{
		if(colorIndex < 0)
			colorIndex = 0;
		if(colorIndex > colorImage.length - 1)
			colorIndex = colorImage.length - 1;
		ByteArrayOutputStream bos = null;
		try
		{
			if(colorImage[colorIndex] == null)
			{
				bos = new ByteArrayOutputStream();
				bos.write(Res.pngFileHead);

				byte[][] datas = new byte[iTxt_iDat_iHdrData.length + 2][];
				datas[0] = iTxt_iDat_iHdrData[0];
				datas[1] = iTxt_iDat_iHdrData[1];
				datas[2] = pLte_tRnsData[colorIndex * 2];
				datas[3] = pLte_tRnsData[colorIndex * 2 + 1];
				for(int i = 4;i < datas.length;i++)
				{
					datas[i] = iTxt_iDat_iHdrData[(2 + i) - 4];
				}
				for(int i = 1;i < datas.length;i++)
				{
					if(datas[i] != null && datas[i].length > 4)
					{
						bos.write(getChunkBytes(Res.pngFileChunkTypeList[i > 4 ? 4 : i], datas[i]));
					}
				}
				bos.write(Res.pngFileEnd);
				byte[] data = bos.toByteArray();
				colorImage[colorIndex] = Image.createImage(new ByteArrayInputStream(data));
				bos.close();
			}
		}catch(Exception ex)
		{
			ex.printStackTrace();
			 
		}
		finally
		{
			SystemAPI.closeByteArrayOutputStream(bos);
		}
		return colorImage[colorIndex];
	}

	/**
	 * 得到块的完整格式数据
	 * 
	 * @param chunkName
	 *        块名称
	 * @param data
	 *        数据块和crc块
	 * @return byte[],完整的块
	 */
	public static byte[] getChunkBytes (String chunkName, byte[] data)
	{
		byte[] temp = new byte[data.length + 8];
		int length = data.length - 4;
		temp[0] = (byte)(length >>> 24);
		temp[1] = (byte)(length >>> 16);
		temp[2] = (byte)(length >>> 8);
		temp[3] = (byte)(length);
		System.arraycopy(chunkName.getBytes(), 0, temp, 4, 4);
		System.arraycopy(data, 0, temp, 8, data.length);
		return temp;
	}

	/** 读出所有图片引用 */
	public void loadAllRefs (DataInputStream dis) throws IOException
	{
		int refSize = dis.read();
		allRefs = new short[refSize][4];
		for(int i = 0;i < refSize;i++)
		{
			allRefs[i][0] = dis.readShort();
			allRefs[i][1] = dis.readShort();
			allRefs[i][2] = dis.readShort();
			allRefs[i][3] = dis.readShort();
			if(allRefs[i][0] < 0)
			{
				allRefs[i][2] += allRefs[i][0];
				allRefs[i][0] = 0;
			}
			if(allRefs[i][1] < 0)
			{
				allRefs[i][3] += allRefs[i][1];
				allRefs[i][1] = 0;
			}
			if(allRefs[i][0] + allRefs[i][2] > width)
			{
				allRefs[i][2] = (short)(width - allRefs[i][0]);
			}
			if(allRefs[i][1] + allRefs[i][3] > height)
			{
				allRefs[i][3] = (short)(height - allRefs[i][1]);
			}
		}
	}

	
	public void destroy ()
	{
		//TODO Auto-generated method stub
	}

	
	public void draw (Graphics g, int x, int y, int colorIndex, int index, int frameId, int flip, int anchor)
	{
		if(this.width==0||this.height==0)
		{
			return;
		}
		int refX = 0;
		int refY = 0;
		int width = this.width;
		int height = this.height;
		if(index != -1)
		{
			if(index > allRefs.length - 1)
				index = allRefs.length - 1;
			if(index < 0)
				index = 0;
			refX = allRefs[index][0];
			refY = allRefs[index][1];
			width = allRefs[index][2];
			height = allRefs[index][3];
		}
		Image img;
		if(type == Res.RES_TYPE_IMAGE)
		{//常规图片
			img = getImage();
		}
		else
		{
			img = getColorImg(colorIndex);
		}
		if(img == null)
		{
			return;
		}
		Res.drawRegion(g, img, refX, refY, width, height, flip, x, y, anchor);
	}
}
