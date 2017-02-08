package cn.zrong.connection;

import cn.zrong.GameAPI;

public abstract class IOReader
{
	public abstract void recieve(byte[] data);
	
	 
	public static IOReader reader = null;
	
	public static IOReader sharedReader()
	{
		if(reader == null)
		{
			if(GameAPI.packageStructure.equals("JSON"))
			{
				reader = new JSONReader();
			}
			else
			{
				//reader =new Self1Reader();
			}
		}
		
		return reader;
	}
}
