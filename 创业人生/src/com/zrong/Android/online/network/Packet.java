package com.zrong.Android.online.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import com.zrong.Android.activity.MainActivity;
import com.zrong.Android.activity.R;

import android.util.Log;

/**
 * 基础数据包类. 包数据头结构为:<br>
 * 0~1位。长度反码 2~3位。类型 数据写入格式为c++模式，低位在前。
 */
public class Packet
{
	public static final String TAG = "Packet";
	/** value的最值 */
	private static final int MAX = 0xFFFF;
	/** 顺序编号 */
	private static int value = 0;

	public static long getUnique()
	{
		try
		{
			return System.currentTimeMillis() << 20 | value;
		}
		finally
		{
			value = value++ < MAX ? value : 0;
		}
	}

	/** 包类型 */
	private short type;
	/** 包数据 */
	private byte[] data;
	private long uniqueId;
	private long playId;

	/**
	 * 从已知的数据构建一个数据包,<br>
	 * data未同步使用时不要对其操作,<br>
	 * type未同步使用时不要对其操作<br>
	 * 
	 * @param type
	 *        类型
	 * @param data
	 *        数据段
	 */
	public Packet(short type, byte[] data)
	{
		if(data.length > Short.MAX_VALUE)
			throw new ArrayIndexOutOfBoundsException(MainActivity.resources.getString(R.string.packet_info));
		this.type = type;
		this.data = data;
		uniqueId = getUnique();
	}

	/**
	 * 包大小位
	 * 
	 * @return short,包数据大小的反码
	 */
	public short sizePosition ()
	{
		return (short)(~(data.length));
	}

	/**
	 * 包类型位
	 * 
	 * @return short,包数据类型
	 */
	public short TypePosition ()
	{
		return type;
	}

	/**
	 * 得到包数据的类型
	 * 
	 * @return short,类型
	 */
	public short getType ()
	{
		return type;
	}

	/**
	 * 得到包数据的大小
	 * 
	 * @return int,大小
	 */
	public int getSize ()
	{
		return data.length;
	}

	/**
	 * 得到包数据
	 * 
	 * @return 返回 data。
	 */
	public byte[] getData ()
	{
		return data;
	}

	public void setUniqueId (long uid)
	{
		this.uniqueId = uid;
	}

	public long getUniqueId ()
	{
		return uniqueId;
	}

	public void setPlayId (long playId)
	{
		this.playId = playId;
	}

	public long getPlayId ()
	{
		return playId;
	}

	
	public Object clone ()
	{
		Packet p = new Packet(type, new byte[data.length]);
		System.arraycopy(data, 0, p.data, 0, data.length);
		p.uniqueId = uniqueId;
		p.playId = playId;
		return p;
	}

	public static Packet readPacket (DataInputStream dis, boolean isEncript) throws Exception
	{
		int rd1;
		int rd2;
		int size = dis.readShort();
		long uniqueID=dis.readLong();
		rd1 = dis.read();
		rd2 = dis.read();
		short type = (short)((rd2 << 8) + rd1);
		
		 
		Log.v(TAG, "read pacekt: size="+size+",uniqueId="+uniqueID+",type"+Integer.toHexString(type));
		 
		size-=12; 
		byte[] data = new byte[size];
		int readSize = 0;
			while(readSize < size)
			{
				readSize += dis.read(data, readSize, size - readSize);
			}
		if(isEncript)
		{
			decript(data, type, (short)size);
		}
		Packet p = new Packet(type, data);
		p.setUniqueId(uniqueID);
		return p;
	}

	public static void sendPacket (DataOutputStream os, Packet packet, boolean isEncript) throws Exception
	{
		if(isEncript)
		{
			encript(packet.getData(), packet.getType(), (short)packet.getSize());
		}
		short sizePos = (short)packet.getSize();
		sizePos+=12;//其中8位是uid,2位是type
		
		os.writeShort(sizePos);
		os.writeLong(packet.getUniqueId());//写uid
		
		os.write(packet.getType());//写type
		os.write(packet.getType() >>> 8);
		
		byte[] data = packet.getData();//写数据
		Log.d(TAG, "send packet: size="+sizePos+",UniqueId="+packet.getUniqueId()+",type="+Integer.toHexString(packet.getType())) ;
		 
		 
		os.write(data);
	}

	/**
	 * 加密缓冲区中的从position开始长度为size的区域
	 */
	public static void encript (byte[] buf, short type, short size)
	{
		byte encriptKey = (byte)((byte)size ^ (byte)(size >>> 8) ^ (byte)type ^ (byte)(type >>> 8));
		byte key;
		for(int i = 0;i < size;i++)
		{
			key = (byte)(buf[i] ^ encriptKey);
			buf[i] = key;
			encriptKey = key;
		}
	}

	/**
	 * 解密缓冲区中的从position开始长度为size的区域
	 */
	public static void decript (byte[] buf, short type, short size)
	{
		byte encriptKey = (byte)((byte)size ^ (byte)(size >>> 8) ^ (byte)type ^ (byte)(type >>> 8));
		byte nextencriptKey;
		for(int i = 0;i < size;i++)
		{
			nextencriptKey = buf[i];
			buf[i] = (byte)(nextencriptKey ^ encriptKey);
			encriptKey = nextencriptKey;
		}
	}
}
