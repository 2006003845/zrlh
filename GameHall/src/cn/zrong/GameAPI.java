package cn.zrong;

import android.content.res.Resources;
import cn.zrong.activity.R;

/**
 * 
 * <p>
 * Titile:GameAPI
 * </p>
 * <p>
 * Description:defined Method or constant or varouble when can be used in this
 * project
 * </p>
 * <p>
 * Copyright:Copyright(c)2010
 * </p>
 * <p>
 * Company:zrong
 * </p>
 * 
 * @author Administrator
 * @version 1.0
 * @date 2012-4-23
 */
public class GameAPI {
	public static String userApp = "";

	public static String channel = "";

	public static String platform = "";

	public static String iMobile = "";

	public static String userUrl = "";

	public static String weiboUrl = "";

	public static String roleUrl = "";

	public static String gameUrl = "";

	public static String gameCenterUrl = "";

	public static char[] gameSwitch;

	public static String packageStructure = "JSON";

	public static final String JSONTAG = "JSON";

	public static final String SELF1TAG = "SELF1";

	/** senceId */
	public static String adventure = "";

	public static final int Port_User = 0;
	public static final int Port_Weibo = 1;
	public static final int Port_Role = 2;
	public static final int Port_Game = 3;

	public static void init(Resources resource) {
		GameAPI.userApp = resource.getString(R.string.userApp);

		GameAPI.channel = resource.getString(R.string.channel);

		GameAPI.platform = resource.getString(R.string.platform);
		packageStructure = resource.getString(R.string.packageStructure);
	}

	public static void init2(Resources resource) {
		GameAPI.userApp = resource.getString(R.string.userApp);

		GameAPI.channel = resource.getString(R.string.channel);

		GameAPI.platform = resource.getString(R.string.platform);

		GameAPI.userUrl = resource.getString(R.string.userUrl);

		GameAPI.weiboUrl = resource.getString(R.string.weiboUrl);

		GameAPI.roleUrl = resource.getString(R.string.roleUrl);

		GameAPI.gameUrl = resource.getString(R.string.gameUrl);

		GameAPI.gameCenterUrl = resource.getString(R.string.gameCenterUrl);

		packageStructure = resource.getString(R.string.packageStructure);
	}

	public static int screen_width = 0;
	public static int screen_height = 0;

	public static int statusBarHeight = 0;

}
