package cn.zrong.connection;

public class JsonPacket extends Packet
{

	public JsonPacket(short type, byte[] data)
	{
		super(type, data);
		// TODO Auto-generated constructor stub
	}
	
	public byte[] getSendData()
	{
		return this.data;
	}
	
}
