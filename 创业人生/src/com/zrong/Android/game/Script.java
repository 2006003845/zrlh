/**
 * 
 */
package com.zrong.Android.game;



 

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.microedition.lcdui.Image;

import com.zrong.Android.activity.MainActivity;
import com.zrong.Android.activity.R;

import android.util.Log;

import res.Map;
import res.MapInfo;
import res.Res;

/**
 *<p>Titile:Script</p>
 *<p>Description:读取和存储脚本</p>
 *<p>Copyright:Copyright(c)2010</p>
 *<p>Company:zrong</p>
 * @author yangzheng
 * @version 1.0
 */
public class Script {
	
	public static final String TAG = "Script";
	/**
	 * 用户信息和版本号
	 */
	private static final int RECORD_ACCOUNT=1;
	/**
	 * 设置信息
	 */
	private static final int RECORD_SETTING=2;
	
	private static final int RECORD_SELECTSER = 3;
	public static boolean saveAccount = true;//保存
	//脚本相关信息
	/** 脚本名字 */
	public static final String[] SC_RES =
	{
			"buildLocation.script",
	};
	/**脚本路径 */
	public static String scriptPath = "/script/";
	
	//建筑层脚本信息
    /**格子的数量 */
	public static short locationSize;
	/**格子的ID */
	public static short[] id;
	/**每个格子的X坐标 */
	public static short[] locationX;
	/**每个格子的Y坐标 */
	public static short[] locationY;
	
	public static short[] location_Offset_X; 
	
	public static short[] location_Offset_Y;
	 
	
	/**
	 * 每一个格子相对于0号格子的街道偏移量,高四位为x偏移量,第四位为y偏移量
	 */
	public static byte[][] offset_cityXY;
	
	 
 
  
	 
	 
	 
	
	public static void initScript()
	{
		try{
		for(int i=0;i<SC_RES.length;i++)
		{	
			DataInputStream dis = new DataInputStream(Image.getResourceAsStream(scriptPath + SC_RES[i]));//add_wang
			readScript(i,dis);
		}
		}catch(IOException io)
		{
			io.printStackTrace();
		}
	}
	
	public static void readScript(int idx,DataInputStream dis) throws IOException
	{
		switch(idx)
		{
		case 0:
			readBuildLocation(dis);
			break;
		default:
			break;
		}
	}
	/**
	 * 读取建筑脚本信息
	 * @param dis
	 * @throws IOException
	 */
	public static void readBuildLocation (DataInputStream dis) throws IOException
	{
		short size=dis.readShort();
		
		locationSize = size;
		id=new short[size];
		locationX=new short[size];
		locationY=new short[size];
		
		
		location_Offset_X=new short[size*4];
		location_Offset_Y=new short[size*4];
		 
		offset_cityXY= new byte[size][2];
		
		for(int i=0;i<size;i++)
		{
			id[i]=dis.readShort();
			locationX[i]=dis.readShort();
			locationY[i]=dis.readShort();
			dis.readByte();
			setOffsetCityXY(id[i]);
			Log.v(TAG, "LX ="+locationX[i]+"LY ="+locationY[i]+"index="+i+"id="+id[i]);
		}
	}
	
	
	public static void setOffsetCityXY(short index)
	{
		
		switch(index)
		{
		case 0:
			offset_cityXY[0][0] = 0;
			
			offset_cityXY[0][1] = 0;
			break;
		case 1:
			
            offset_cityXY[1][0] = 0;
			
			offset_cityXY[1][1] = 1;
		 
			break;
		case 2:
			offset_cityXY[2][0]= 0;
			offset_cityXY[2][1]=2;
			break;
		case 3:
			offset_cityXY[3][0]= 1;
			offset_cityXY[3][1]=-4;
			 
			break;
		case 4:
			
			offset_cityXY[4][0]= 1;
			offset_cityXY[4][1]=-3;
			 
			break;
		case 5:
			offset_cityXY[5][0]= 1;
			offset_cityXY[5][1]=-2;
			break;
		case 6:
			offset_cityXY[6][0]= 1;
			offset_cityXY[6][1]=-1;
			break;
		case 7:
			offset_cityXY[7][0]= 1;
			offset_cityXY[7][1]=0;
			break;
		case 8:
			offset_cityXY[8][0]= 1;
			offset_cityXY[8][1]=1;
			break;
		case 9:
			offset_cityXY[9][0]= -2;
			offset_cityXY[9][1]=-1;
			break;
		 
		case 10:
			offset_cityXY[10][0]= -2;
			offset_cityXY[10][1]=0;
			break;
		case 11:
			offset_cityXY[11][0]= -2;
			offset_cityXY[11][1]=1;
			break;
		case 12:
			offset_cityXY[12][0]= 2;
			offset_cityXY[12][1]=-2;
			break;
		case 13:
			offset_cityXY[13][0]= 2;
			offset_cityXY[13][1]=-1;
			break;
			
		 
		case 14:
			offset_cityXY[14][0]= 2;
			offset_cityXY[14][1]=0;
			break;
			 
		case 15:
			offset_cityXY[15][0]= -1;
			offset_cityXY[15][1]=-2;
			break;
		 
		case 16:
			offset_cityXY[16][0]= -1;
			offset_cityXY[16][1]=-1;
			break;
			 
		case 17:
			offset_cityXY[17][0]= -1;
			offset_cityXY[17][1]=0;
			break;
			 
		case 18:
			offset_cityXY[18][0]= -1;
			offset_cityXY[18][1]=1;
			 
			break;
		case 19:
			
			offset_cityXY[19][0]= -1;
			offset_cityXY[19][1]=2;
			 
			break;
		case 20:
			offset_cityXY[20][0]= 3;
			offset_cityXY[20][1]=-1;
			break;
		case 21:
			
			offset_cityXY[21][0]= 0;
			offset_cityXY[21][1]=-3; 
			break;
		case 22:
			offset_cityXY[22][0]= 0;
			offset_cityXY[22][1]=-2; 
			break;
		case 23:
			offset_cityXY[23][0]= 0;
			offset_cityXY[23][1]=-1; 
			break;
		}
	}
	
	public static void getLocationOffset()
	{
		for(int i=0;i<id.length;i++)
		{
			int index_x=0;
			int index_y=i;
			//先算x方向上索引的偏移量
			//x+1 应该 应该是x增大，增大
			short[] xy= GameData.transformCITY_TO_MAP(index_x+1,index_y);
			if(xy[0]-locationX[i]>0)
			{
				location_Offset_X[i*4]=(short)(xy[0]-locationX[i]);
			}
			else
			{
				location_Offset_X[i*4]=(short)(xy[0]-locationX[i]+GameData.ORIGINAL_MAP_X);
			}
			 
			if(xy[1]-locationY[i]>0)
			{
				location_Offset_Y[i*4]=(short)(xy[1]-locationY[i]);
			}
			else
			{
				location_Offset_Y[i*4]=(short)(xy[1]-locationY[i]+GameData.ORIGINAL_MAP_Y);
			}
			//x-1 应该是 x减少，y减少
			 xy= GameData.transformCITY_TO_MAP(index_x-1,index_y);
			 if(xy[0]-locationX[i]>0)
			 {
				 location_Offset_X[i*4+1]=(short)(xy[0]-locationX[i]-GameData.ORIGINAL_MAP_X);
			 }
			 else
			 {
				 location_Offset_X[i*4+1]=(short)(xy[0]-locationX[i]);
			 }
			 if(xy[1]-locationY[i]>0)
			 {
				 location_Offset_Y[i*4+1]=(short)(xy[1]-locationY[i]-GameData.ORIGINAL_MAP_Y);
			 }
			 else
			 {
				location_Offset_Y[i*4+1] =(short)(xy[1]-locationY[i]);
			 }
			//y+1 x增加y减少
			 xy=GameData.transformCITY_TO_MAP(index_x, index_y+1);
			 	if(xy[0]-locationX[i]>0)
				{
					location_Offset_X[i*4+2]=(short)(xy[0]-locationX[i]);
				}
				else
				{
					location_Offset_X[i*4+2]=(short)(xy[0]-locationX[i]+GameData.ORIGINAL_MAP_X);
				}
			 	if(xy[1]-locationY[i]>0)
				 {
					 location_Offset_Y[i*4+2]=(short)(xy[1]-locationY[i]-GameData.ORIGINAL_MAP_Y);
				 }
				 else
				 {
					location_Offset_Y[i*4+2] =(short)(xy[1]-locationY[i]);
				 }
			//y-1 x减少y增加
			 	xy=GameData.transformCITY_TO_MAP(index_x, index_y-1);
			 	 if(xy[0]-locationX[i]>0)
				 {
					 location_Offset_X[i*4+3]=(short)(xy[0]-locationX[i]-GameData.ORIGINAL_MAP_X);
				 }
				 else
				 {
					 location_Offset_X[i*4+3]=(short)(xy[0]-locationX[i]);
				 }
		 		if(xy[1]-locationY[i]>0)
		 		{
		 			location_Offset_Y[i*4+3]=(short)(xy[1]-locationY[i]);
		 		}
		 		else
		 		{
		 			location_Offset_Y[i*4+3]=(short)(xy[1]-locationY[i]+GameData.ORIGINAL_MAP_Y);
		 		}
		}
	}
 
	
	/**
	 * 读取地图数据
	 * 
	 * @param mapId
	 *        short
	 * @return Map
	 */
	public static Map readMap (short mapId)
	{
		try
		{
			MapInfo mapInfo = Res.getMapInfo(mapId);
			if(mapInfo != null)
			{
				Map map = new Map(mapInfo);
				if(map != null)
				{
					map.id = mapId;
					return map;
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	public static final String script_colortable = MainActivity.resources.getString(R.string.script_colortable);
	public static final String colorTable [] = script_colortable.split(",");
	//public static final String colorTable [] = {"黑色","橙色","湖蓝", "白色", "红色", "绿色", "蓝色", "黄色", "世色", "商色", "密色", "系色", "恢复"};
	public static final int colorValue[] = {0, 0xff8a00,0x25bbcd,0xffffff, 0xff0000, 0x00ff00, 0x0000ff, 0x00ffff, 0x7B00DD, 0xFF4BE8, 0x22CB1E, 0xFF653C};
	public static final String script_face = MainActivity.resources.getString(R.string.script_face);
	public static final String face[] = script_face.split(",");
	/*public static final String face[] = 
	{
		"男羞",
		"男鄙",
		"男闭",
		"男笑",
		"男得",
		"男呆",
		"男坏",
		"男讶",
		"男睡",
		"男泣",
		"男汗",
		"男亲",
		"男吐",
		"男微",
		"男屈",
		"男爱",
		"男奈",
		"男气",
		"女羞",
		"女鄙",
		"女闭",
		"女笑",
		"女得",
		"女呆",
		"女坏",
		"女讶",
		"女睡",
		"女泣",
		"女汗",
		"女亲",
		"女吐",
		"女微",
		"女屈",
		"女爱",
		"女奈",
		"女气",
	};*/
}
