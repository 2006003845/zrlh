package com.zrong.Android.Util;



import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.util.Random;
import java.util.Vector;

import javax.microedition.lcdui.Image;

import com.zrong.Android.game.GameDef;
import com.zrong.Android.online.network.HttpConnection;

/**
 * 
 *<p>Titile:SystemAPI this is a SystemAPI used by all of the game </p>
 *<p>Description:</p>
 *<p>Copyright:Copyright(c)2010</p>
 *<p>Company:zrong</p>
 * @author Administrator
 * @version 1.0
 * @date 2012-3-8
 */
public class SystemAPI {

	/** 两矩形相离 */
	public static final int FIELD_OUT = 1;
	/** 两矩形一个在另一个里面 */
	public static final int FIELD_IN = 2;
	/** 两矩形相交 */
	public static final int FIELD_CROSS = 4;

	/**
	 * 判断两个矩形的交集情况
	 * 
	 * @param x1
	 *        子矩形
	 * @param y1
	 * @param w1
	 * @param h1
	 * @param x2
	 *        父矩形
	 * @param y2
	 * @param w2
	 * @param h2
	 * @return int FIELD_OUT | FIELD_IN | FIELD_CROSS
	 */
	public static int inField (int x1, int y1, int w1, int h1, int x2, int y2, int w2, int h2)
	{
		if(x1 > x2 && y1 > y2 && x1 + w1 < x2 + w2 && y1 + h1 < y2 + h2)
		{
			return FIELD_IN;
		}
		else if(x1 > x2 + w2 || y1 > y2 + h2 || x1 + w1 < x2 || y1 + h1 < y2)
		{
			return FIELD_OUT;
		}
		else
		{
			return FIELD_CROSS;
		}
	}

	/**
	 * 使用进一法用除数对被除数进行整除
	 * 
	 * @param dividend
	 *        被除数
	 * @param divider
	 *        除数
	 * @return
	 */
	public static int divEnter (int dividend, int divider)
	{
		return dividend / divider + ((dividend % divider) == 0 ? 0 : 1);
	}

	/**
	 * 关闭InputStream
	 * 
	 * @param in
	 *        InputStream
	 */
	public static void closeInputStream (InputStream in)
	{
		if(in != null)
		{
			try
			{
				in.close();
			}catch(Exception e)
			{

			}
			finally
			{
				in = null;
			}
		}
	}

	/**
	 * 关闭DataInputStream
	 * 
	 * @param dis
	 *        DataInputStream
	 */
	public static void closeDataInputSream (DataInputStream dis)
	{
		if(dis != null)
		{
			try
			{
				dis.close();
			}catch(Exception e)
			{

			}
			finally
			{
				dis = null;
			}
		}
	}

	/**
	 * 关闭ByteArrayInputStream
	 * 
	 * @param bais
	 *        ByteArrayInputStream
	 */
	public static void closeByteArrayInputStream (ByteArrayInputStream bais)
	{
		if(bais != null)
		{
			try
			{
				bais.close();
			}catch(Exception e)
			{

			}
			finally
			{
				bais = null;
			}
		}
	}

	/**
	 * 关闭DataOutputStream
	 * 
	 * @param dos
	 *        DataOutputStream
	 */
	public static void closeDataOutputStream (DataOutputStream dos)
	{
		if(dos != null)
		{
			try
			{
				dos.close();
			}catch(Exception e)
			{

			}
			finally
			{
				dos = null;
			}
		}
	}

	/**
	 * 关闭ByteArrayOutputStream
	 * 
	 * @param baos
	 *        ByteArrayOutputStream
	 */
	public static void closeByteArrayOutputStream (ByteArrayOutputStream baos)
	{
		if(baos != null)
		{
			try
			{
				baos.close();
			}catch(Exception e)
			{

			}
			finally
			{
				baos = null;
			}
		}
	}

	/**
	 * 关闭ByteArrayInputStream和DataInputStream
	 * 
	 * @param bais
	 *        ByteArrayInputStream
	 * @param dis
	 *        DataInputStream
	 */
	public static void closeInputStream (ByteArrayInputStream bais, DataInputStream dis)
	{
		closeByteArrayInputStream(bais);
		closeDataInputSream(dis);
	}

	/**
	 * 关闭ByteArrayOutputStream和DataOutputStream
	 * 
	 * @param baos
	 *        ByteArrayOutputStream
	 * @param dos
	 *        DataOutputStream
	 */
	public static void closeOutputStream (ByteArrayOutputStream baos, DataOutputStream dos)
	{
		closeByteArrayOutputStream(baos);
		closeDataOutputStream(dos);
	}

	/**
	 * 线程休眠
	 * 
	 * @param time
	 *        毫秒
	 */
	public static void sleep (long time)
	{
		try
		{
			Thread.sleep(time);
		}catch(Exception e)
		{
			e.printStackTrace();
		}	
	}
	
	/**
	 * 每给
	 * 
	 * @param time
	 */
	public static void sleepGame (long time)
	{
//		long waitStartTime = System.currentTimeMillis();
//		while(System.currentTimeMillis() - waitStartTime < time)
//		{
			sleep(100);
//		}
	}
	/**
	 * 从文件系统载入图片的封装
	 * 
	 * @param path
	 * @return
	 */
	public static Image createImage (String path)
	{
		Image image = null;
		try
		{
			image = Image.createImage(path);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return image;
	}

	/**
	 * 判断字符串是否有效 true有效 false无效
	 * 
	 * @param s
	 *        String
	 * @return boolean
	 */
	public static boolean isStringAvisible (String s)
	{
		if(s == null || s.equals(""))
		{
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 * 判断一个点是否在一个菱形内 px,py,pw,ph为菱形所构成的长方形的四个点顶点
	 * @param px 
	 * @param py
	 * @param pw
	 * @param ph
	 * @param pointX
	 * @param pointY
	 * @return
	 */
	public static boolean runIsPoint (int px, int py, int pw, int ph,int pointX,int pointY)
	{
		 
			boolean p = SystemAPI.IsPtInPoly(pointX, pointY, new int[][]
			{
					{
							px, py + (ph >> 1)
					},
					{
							px + (pw >> 1), py + ph
					},
					{
							px + pw, py + (ph >> 1)
					},
					{
							px + (pw >> 1), py
					}
			});
			if(p)
			{ 
				return true;
			}
		 
		return false;
	}
	/**
	 * 
	 * 判断一个点是否在一个四边形内
	 * @param px
	 * @param py
	 * @param arrayPoint 菱形的四个顶点
	 * @return
	 */
	public static boolean IsPtInPoly (int px, int py, int[][] arrayPoint)
	{
		if(arrayPoint == null || arrayPoint.length <= 2)
		{
			return false;
		}
		int sum = 0;
		int x1, y1, x2, y2;
		for(int i = 0;i < arrayPoint.length;i++)
		{
			if(i == arrayPoint.length - 1)
			{
				x1 = arrayPoint[i][0];
				y1 = arrayPoint[i][1];
				x2 = arrayPoint[0][0];
				y2 = arrayPoint[0][1];
			}
			else
			{
				x1 = arrayPoint[i][0];
				y1 = arrayPoint[i][1];
				x2 = arrayPoint[i + 1][0];
				y2 = arrayPoint[i + 1][1];
			}
			if(py >= y1 && py < y2 || py >= y2 && py < y1)
			{
				if(Math.abs(y1 - y2) > 0)
				{
					int x = x1 - (y1 - py) * (x1 - x2) / (y1 - y2);
					if(x < px)
					{
						sum += 1;
					}
				}
			}
		}
		if(sum % 2 != 0)
		{
			return true;
		}
		return false;
	}

	/**
	 *获取指定int元素在数组中的索引
	 * @param array
	 * @param value
	 */
	public static int getShortArrayIndex (short[] array, short value)
	{
		if(array == null)
			return -1;
		for(int i = 0;i < array.length;i++)
		{
			if(array[i] == value)
			{
				return i;
			}
		}
		return -1;
	}

	/**
	 * 获取指定int元素在数组中的索引
	 * @param array
	 * @param value
	 * @return
	 */
	public static int getIntArrayIndex (int[] array, int value)
	{
		if(array == null)
			return -1;
		for(int i = 0;i < array.length;i++)
		{
			if(array[i] == value)
			{
				return i;
			}
		}
		return -1;
	}

	/**
	 * 获取指定byte元素在数组中的索引
	 * @param array
	 * @param value
	 * @return
	 */
	public static int getByteArrayIndex (byte[] array, byte value)
	{
		if(array == null)
			return -1;
		for(int i = 0;i < array.length;i++)
		{
			if(array[i] == value)
			{
				return i;
			}
		}
		return -1;
	}

	/**
	 * 获取指定long元素在数组中的索引
	 * @param array
	 * @param value
	 * @return
	 */
	public static int getLongArrayIndex (long array[], long value)
	{
		if(array == null)
			return -1;
		for(int i = 0;i < array.length;i++)
		{
			if(array[i] == value)
			{
				return i;
			}
		}
		return -1;
	}

	/**
	 * 获取指定Object元素在数组中的索引
	 * @param array 
	 * @param value
	 * @return
	 */
	public static int getStringArrayIndex (Object array[], Object value)
	{
		if(array == null)
			return -1;
		for(int i = 0;i < array.length;i++)
		{
			if(array[i] != null && value != null)
				if(array[i].equals(value))
				{
					return i;
				}
		}
		return -1;
	}

	/**
	 * 从byte型数组中获取指定的元素
	 * @param array 数组
	 * @param index 索引
	 * @return 目标值
	 */
	public static byte getByteArrayValue (byte[] array, int index)
	{
		if(array == null)
			return -1;

		if(index >= 0 && index < array.length)
		{
			return array[index];
		}
		return -1;
	}

	/**
	 * 从short型数组中获取指定的元素
	 * @param array 数组
	 * @param index 索引
	 * @return 目标值
	 */
	public static short getShortArrayValue (short[] array, int index)
	{
		if(array == null)
			return -1;

		if(index >= 0 && index < array.length)
		{
			return array[index];
		}
		return -1;
	}

	/**
	 * 从int型数组中获取指定的元素
	 * @param array 数组
	 * @param index 索引
	 * @return 目标值
	 */
	public static int getIntArrayValue (int[] array, int index)
	{
		if(array == null)
			return -1;

		if(index >= 0 && index < array.length)
		{
			return array[index];
		}
		return -1;
	}

	/**
	 * 从long型数组中获取指定的元素
	 * @param array 数组
	 * @param index 索引
	 * @return 目标值
	 */
	public static long getLongArrayValue (long[] array, int index)
	{
		if(array == null)
			return -1;

		if(index >= 0 && index < array.length)
		{
			return array[index];
		}
		return -1;
	}

	/**
	 * 
	 * 从字符串数组中获取指定的元素
	 * @param array
	 * @param index
	 * @return
	 */
	public static String getStringArrayValue (String[] array, int index)
	{
		if(array == null)
			return null;

		if(index >= 0 && index < array.length)
		{
			return array[index];
		}
		return null;
	}

	/**
	 * 从Object数组中获取指定的元素
	 * @param array 数组
	 * @param index 索引
	 * @return 目标值
	 */
	public static Object getObjectArrayValue (Object[] array, int index)
	{
		if(array == null)
			return null;

		if(index >= 0 && index < array.length)
		{
			return array[index];
		}
		return null;
	}

	/**
	 * 分割字符串，原理：检测字符串中的分割字符串，然后取子串
	 * 
	 * @param original
	 *        需要分割的字符串
	 * @paran regex 分割字符串
	 * @return 分割后生成的字符串数组
	 */
	public static String[] split (String original, String regex)
	{
		int startIndex = 0;
		Vector v = new Vector();
		String[] str = null;
		int index = 0;
		if(original != null)
		{
		startIndex = original.indexOf(regex);
		
			while(startIndex < original.length() && startIndex != -1)
			{
				String temp = original.substring(index, startIndex);
				System.out.println(" " + startIndex);
				v.addElement(temp);
				index = startIndex + regex.length();
				startIndex = original.indexOf(regex, startIndex + regex.length());
			}
			v.addElement(original.substring(index + 1 - regex.length()));
			str = new String[v.size()];
			for(int i = 0;i < v.size();i++)
			{
				str[i] = (String)v.elementAt(i);
			}
		}else
		{
			str = null;
		}
		return str;
	}
	
	public static boolean keyWord_in_arrory (String key, String keyWord[])
	{
		for(int i = 0;i < keyWord.length;i++)
		{
			if(keyWord[i] != null)
			{
				if(key.indexOf(keyWord[i]) >= 0)
				{
					System.out.println(key + "----------存在----------" + keyWord[i]);
				}
				else
				{
					return false;
				}
			}
		}
		return true;
	}
	//获取随机数
	static Random random = new Random();
	/**
	 * 产生随机数
	 * @param min 随机数的最小值
	 * @param max 随机数的最大值
	 * @return 产生的随机数 
	 */
	public static int getRandom(int min,int max)
	{
		
		int a = random.nextInt();
		a = Math.abs(a%max)+min;
		return a;
	}
	
	
    public static String getText(String s)
    {
	   	char chars[]=s.toCharArray();
	   	
	   	for(int i = 0 ; i< s.length();i++)
	   	{
	   		if(s.charAt(i)== '/' && i+2<s.length())
	   		{
	   			String temp = s.substring(0, i);
	   			
	   			s = temp+s.substring(i+3, s.length());
	   		}
	   	}
	   	return s;
    }
    /**
     * 
     * <p>Description:把long转成byte数组</p>
     * @author YangZheng
     * @param value
     * @return
     */
    public static byte[] parseLongToBytes(long value)
    {
    	ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = null;
		
		try
		{
			dos=new DataOutputStream(baos);
			dos.writeLong(value);
			
			return baos.toByteArray();
			
		}catch(Exception e)
		{
			return null;
		}
		finally
		{
			SystemAPI.closeOutputStream(baos, dos);
		}
    }
    /**
     * 
     * <p>Description:把int转成byte数组</p>
     * @author YangZheng
     * @param value
     * @return
     */
    public static byte[] parseIntToBytes(int value)
    {
    	ByteArrayOutputStream baos = new ByteArrayOutputStream();
    	DataOutputStream dos = null;
    	
    	try
    	{
    		dos=new DataOutputStream(baos);
    		dos.writeInt(value);
    		
    		return baos.toByteArray();
    		
    	}catch(Exception e)
    	{
    		return null;
    	}
    	finally
    	{
    		SystemAPI.closeOutputStream(baos, dos);
    	}
    }
    /**
     * 
     * <p>Description:把short转成byte数组</p>
     * @author YangZheng
     * @param value
     * @return
     */
    public static byte[] parseShortToBytes(short value)
    {
    	ByteArrayOutputStream baos = new ByteArrayOutputStream();
    	DataOutputStream dos = null;
    	
    	try
    	{
    		dos=new DataOutputStream(baos);
    		dos.writeShort(value);
    		
    		return baos.toByteArray();
    		
    	}catch(Exception e)
    	{
    		return null;
    	}
    	finally
    	{
    		SystemAPI.closeOutputStream(baos, dos);
    	}
    }
    /**
     * 
     * <p>Description:把byte转成byte数组</p>
     * @author YangZheng
     * @param value
     * @return
     */
    public static byte[] parsebyteToBytes(byte value)
    {
    	return new byte[]{value};
    }
    
}
