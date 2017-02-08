package jtapp.updateapksamples;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.util.Log;

public class GagaConfig
{
	
	private static  String UPDATE_SERVER = "http://10.20.147.117/jtapp12/";
	
	private static  String SWITCH_JSON = "ver.json";
	
	private static String packageName ="";
	
	private static Activity mActivity;
	
	
	/**
	 * 
	 * <p>Description:</p>
	 * @author yz
	 * @param activity
	 * @param updateSever
	 * @param switchJson
	 * @return 字符串数组 长度为4, 0 gameUrl ,1 weiboUrl,2 roleUrl,3 gameSwitch 0000表示全用正式 1111表示全用替代图
	 */
	public static String[] getSwitch(Activity activity,String updateSever,String switchJson)
	{
		mActivity = activity;
		
		UPDATE_SERVER = updateSever;
		
		SWITCH_JSON =switchJson;
		
		packageName = activity.getPackageName();
		
		return getServer(activity);
		
	}
	private static String userUrl="";
	private static String gameUrl = "";
	private static String weiboUrl = "";
	private static String roleUrl = "";
	private static String gameSwitch ="";
	private static String GameSwitchChannel="";
	private static String GameSwitchversion="";
	private static String GamecenterUrl="";
    
	
	private static String[] getServer(Activity activity) 
	{
		String data[] = new String[8];
		try {
			String verjson = NetworkTool.getContent(GagaConfig.UPDATE_SERVER
					+ GagaConfig.SWITCH_JSON,activity);
			JSONArray array = new JSONArray(verjson);
			if (array.length() > 0) 
				{
				JSONObject obj = array.getJSONObject(0);
				try {
					 userUrl = obj.getString("userUrl");
					 weiboUrl = obj.getString("weiboUrl");
					 roleUrl = obj.getString("roleUrl");
					 gameUrl = obj.getString("gameUrl");
					 gameSwitch = obj.getString("GameSwitch");
					 GameSwitchChannel=obj.getString("GameSwitchChannel");
					 GameSwitchversion=obj.getString("GameSwitchversion");
					 GamecenterUrl = obj.getString("gamecenterUrl");
					 
					 int newVerCode = Integer.parseInt(obj.getString("verCode"));
					 String  newVerName = obj.getString("verName");
					 String	UPDATE_APKNAME = obj.getString("apkname");
					 String	apkName = obj.getString("appname");
					 
					 Config.checkUpdate(activity, packageName, UPDATE_SERVER, newVerCode, newVerName, UPDATE_APKNAME, apkName);
					 
					 data[0] = userUrl;
					 data[1] = weiboUrl;
					 data[2] = roleUrl;
					 data[3] = gameUrl;
					 data[4] = gameSwitch;
					 data[5] = GameSwitchChannel;
					 data[6] =GameSwitchversion;
					 data[7] = GamecenterUrl;
					 return data;
				}
				catch (Exception e) 
				{
					for(int i =0; i < data.length; i++)
					{
						if(data[i] == null)
						{
							data[i] = "";
						}
					}
				}
			}
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return data;
		 
	}
}
