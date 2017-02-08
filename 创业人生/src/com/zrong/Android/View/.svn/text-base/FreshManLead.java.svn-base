package com.zrong.Android.View;

import android.util.Log;

import com.zrong.Android.Util.SystemAPI;
import com.zrong.Android.game.Connection;
import com.zrong.Android.game.ConstructData;
import com.zrong.Android.game.GameData;
import com.zrong.Android.game.GameGroupControl;
import com.zrong.Android.logic.Map;
import com.zrong.Android.online.network.GameProtocol;

public class FreshManLead implements Runnable {
	private static Map map;
	public static int caseId = 0;


	public FreshManLead() {
		super();
	}

	public static boolean isBank = false;// 银行绘图标识
	public static boolean isCommerical = false;// 工商局绘图标识
	public static boolean isRevenue = false;// 税务局绘图标识
	
	 
	public void run() {
		Log.i("Log", "NewFresh---run---1111");
		int isDo = 0;
		while (true) {
			map = (Map) GameGroupControl.gameGroupControl.logic.get("Map");
			if (map != null)
				break;
			SystemAPI.sleep(500);
		}
		Log.i("Log", "NewFresh---run---2222");
		
		while (GameData.isFreshMan) {
			switch (caseId) {
			case 0:// 银行
					// 弹出提示信息
				if (isDo == 0) {
					Log.i("Log", "进入银行街道");
					GameData.frushOriginalFocus();// 把当前focus值设置为初始focus值
					GameData.isResetMap = true;
					GameData.mapIdIndexBack = (short) SystemAPI
							.getShortArrayIndex(
									GameData.mapIds,
									GameData.mapIds[0]);
					Connection.sendMessage(
							GameProtocol.CONNECTION_SEND_MAPAROUND_SHOP,
							ConstructData.getMapAroundShop(GameData.mapIds[0],
									(short) (77), (short) (14), (byte) 1,
									(byte) (GameData.ARRAY_LENTH / 2)));
					Log.i("Log", "setText1---银行");
					map.doPromoter(prompt1);
					isDo++;

				}
				break;
			case 1:// 工商局
				isBank = false;
				isCommerical = true;
				if (isDo == 1) {
					Log.i("Log", "进入工商局街道");
					Connection.sendMessage(
							GameProtocol.CONNECTION_SEND_MAPAROUND_SHOP,
							ConstructData.getMapAroundShop(GameData.mapIds[0],
									(short) (80), (short) (16), (byte) 1,
									(byte) (GameData.ARRAY_LENTH / 2)));
					isDo++;
					
				}
				break;
			case 2:// 税务局
				isCommerical = false;
				isRevenue = true;
				if (isDo == 2) {
					Log.i("Log", "进入税务局街道");
					Connection.sendMessage(
							GameProtocol.CONNECTION_SEND_MAPAROUND_SHOP,
							ConstructData.getMapAroundShop(GameData.mapIds[0],
									(short) (79), (short) (11), (byte) 1,
									(byte) (GameData.ARRAY_LENTH / 2)));
					isDo++;
								
				}
				break;

			case 3:// 任务
				isRevenue = false;
				if (isDo == 3) {
					Log.i("Log", "地图---任务 ---箭头");
				//	map.showArrows();
					isDo++;
				}
				break;
			case 4:// 任务可接受
				break;
			case 5:// 接受
				break;
			case 6:// 点击地图
				if (isDo == 4) {
					Log.i("Log", "setText1---点击地图");
					map.doPromoter(prompt6);
					isDo++;
				}
				break;
			case 7:// 创建店铺确定
				break;
			case 8:// 点击地图上的店铺
				if (isDo == 5) {
					Log.i("Log", "setText1---点击我的店铺");
					map.doPromoter(prompt9);
					isDo++;
				}
				break;

			case 9:// 进入我的店铺--采购
				break;
			case 10:// 调配
				break;
			case 11:// 菜单
				if (isDo == 6) {
					Log.i("Log", "地图--菜单--箭头");
					map.showArrows();
					isDo++;
				}
				break;
			case 12:// 主菜单--员工

				break;

			case 13:// 招聘

				break;
			case 14:// 主菜单--店铺

				break;
			case 15:// 店铺列表
				break;
			case 16:// 任务
				if (isDo == 7) {
					Log.i("Log", "setText1---点击我的店铺");
					map.doPromoter(prompt17);
					map.showArrows();
					isDo++;
				}
				break;
			case 17:// 任务点击完成
				break;

			case 18:// 领取

				break;
			case 19:// 结束新手引导
				GameData.isFreshMan = false;
				break;
			}
		}
	}

	private String prompt1 =          "\t   欢迎进入中国就业促进会创业专  \n\t 业委员会推荐游戏——《创业人生\n\t 》！这里可以轻松实现您的创业\n\t 梦想！";
	public static String prompt2 =  "\t  请先到创业指导中心指定的银行 \n\t 领取创业扶持金吧。";
	public static String prompt3 =  "\t  恭喜您领取了100000创业扶持 \n\t  基金作为公司的启动资金。启动  \n\t 资金是您开办企业必须购买的物   \n\t 资和必要的其他开支的总费用。  \n\t 下一步您需要到工商所办理营业执照。";
	public static String prompt4 =  "\t  营业执照是企业或组织合法经营  \n\t 的凭证，登记事项为：名称、地  \n\t 址、负责人、资金数额、经济成  \n\t 分、经营范围、经营方式、从业  \n\t人 数、经营期限等。您已经领取 \n\t 了营业执照，接下来需要到税务 \n\t 局办理税务证明。";
	public static String prompt5 =  "\t  与小企业有关的主要税种有增值  \n\t 税、营业税、企业所得税、个人  \n\t 所得税、消费税、关税、城市维  \n\t 护建设税、教育附加等。您已经  \n\t办理了税务证明。公司已开始正\n\t 常运作，下面我来向您讲解公司 \n\t  的各项功能吧~";
	private String prompt6 =          "\t  店铺是公司的基础组成部分，是 \n\t 公司产生收益的重要来源，马上 \n\t 去开设您的第一间店铺吧";
	public static String prompt7 =  "\t  您可以点击地图里的空闲土地开 \n\t 设店铺，请点击一块空闲土地然 \n\t 后选择 “创建店铺”";
	public static String prompt8 =  "\t  您可以选择开设店铺的种类和规 \n\t 模，他们的创建成本和带来的收 \n\t 益不同，最后别忘记给店铺起一 \n\t 个响亮的名字哦~";
	private String prompt9 =          "\t  恭喜您成功创建属于自己的第一 \n\t 间店铺！点击店铺即可查看店铺 \n\t 的详细信息。";
	public static String prompt10 = "\t  店铺界面显示您店铺的基本信息 \n\t ，您需要采购货物并安排员工到 \n\t 您的店铺，才能获得更多的收益。";
	public static String prompt11 =  "\t  “店铺库存”显示您店铺当前的货 \n\t 物数量，点击“采购”后即可对该 \n\t 店铺进行货物采购。";
	public static String prompt12 = "\t   您可以选择采购货物的数量将货\n\t 物运送到店铺进行销售。";
	public static String prompt13 = "\t  您的店铺已经完成了采购，还需 \n\t 要安排员工进入店铺工作。";
	public static String prompt14 = "\t  您的公司尚未有空闲员工，马上\n\t 到人才市场招聘员工吧~(进入到 \n\t 地图界面)";
	public static String prompt15 = "\t  恭喜您成功招聘一名员工！并完 \n\t 成了开设店铺的基本操作";
	public static String prompt16 = "\t  您可以从主菜单进入到店铺列表 \n\t 中，对店铺进行查看和统一管理！";
	private String prompt17 =         "\t  恭喜您完成了全部的新手教程！\n\t 可以领取我们为您特别送出的新 \n\t 手豪华大礼包一份！";
	public static String prompt18 = "\t  现在的您已经完全具备了在商海 \n\t 中自由驰骋的能力！期待您创造 \n\t 出更多的商业奇迹！请 \n\t 继续完成任务体验与众不同的创 \n\t 业生涯吧~";
	public static String prompt19 = "\t 您可以在此处对您所拥有的店铺 \n\t 进行、操作与查看！接下来，请 \n\t  您回到地图主页，有给您的惊喜哦~";
}
