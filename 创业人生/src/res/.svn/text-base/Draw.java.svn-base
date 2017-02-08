/**
 * 
 */
package res;



import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
//#ifdef Nokia
//# import com.nokia.mid.ui.DirectUtils;
//#endif
import res.Res;
import res.ResManager;
import res.UseResListNew;
import java.io.ByteArrayInputStream;

/**
 *<p>Titile:Draw</p>
 *<p>Description:公共绘制方法，定义font,和颜色及各种通用画法</p>
 *<p>Copyright:Copyright(c)2010</p>
 *<p>Company:zrong</p>
 * @author yangzheng
 * @version 1.0
 */
public class Draw {
	
	public static final char ColorSign = '&';
	/** 输入字体,正体,最小 */
	public static Font SPS = Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_PLAIN, Font.SIZE_SMALL);
	 
	public static int showWidh =544;
	
	public static int showHeight =352;
	 
	public static final int FONT_HEIGHT = SPS.getHeight();
	 
	//===========================
	//所有出现的系统颜色,如无特殊需要，一律定义在这里
	public static final int SYSTEM_COLOR_BAK_UI=0xffffff;//系统半透明颜色值
	public static final int SYSTEM_COLOR_BAK_ALPHA=0x8c;//系统半透明alpha值
	 
	 
	 
	 
	//menu
	public static final int MENU_TEXT_COLOR = 0x0005d3; //菜单选项颜色
	public static final int MENU_TEXT_FOCUS_COLOR = 0x000100;
	public static final int MENUBACK_COLOR = 0xffffff; //菜单选中颜色
	public static final int MENUFOCUS_COLOR = 0xfeb794; //菜单背景颜色
	public static final int MENU_RIM_COLOR = 0x4165ff; //菜单边框颜色
	public static final int MENUFOCUS_INPU_COLOR = 0x000000;//input框被选中的色
	//input
	public static final int MENU_INPUT_TEXT_COLOR=0xffffff;//普通输入框颜色
	public static final int MENU_INPUT_TEXT_ORANGE_COLOR=0xFF9000;//橘红色输入框颜色值
	
/**
 * add by zzx;
 */
	public static final int color_grid = 0xff8243;
	public static final int color_backgrid = 0xffffff;
	public static final int color_grid_chose= 0xfef8b7;
	
	//===========================
	public static final int COMPANYUI_LINE_COLOR = 0x87009B;//线条颜色
	public static final int COMPANYUI_TEXT_COLOR = 0x000000;//字体颜色
	public static final int COMPANYUI_MENU_COLOR = 0x000000;//框颜色
	
	public static final int COMPANYUI_RECT_COLOR = 0xff9c6a;//线条色
	
	private static int alphaImageSize;
	private static int[] alphaImageSign = new int[0];
	private static boolean[] alphaImageUse = new boolean[0];
	private static Image[] alphaImage = new Image[0];
	 
	private static Image alphImage;
	
	public static void clearAlphImage()
	{
		alphImage=null;
	} 
	 
 
	
	public static void fillAlpheRect (Graphics g, int color, int alph, int x, int y, int w, int h)
	{
		g.setClip(x, y, w, h);
		int drawX = g.getClipX();
		int drawY = g.getClipY();
		int drawW = g.getClipWidth();
		int drawH = g.getClipHeight();
		try
		{
			//#ifdef Nokia
			//# if(alphImage == null){
			//# try{
				//# Image img = Image.createImage(Res.pngPath+"xdt.png");
				//# alphImage = Image.createImage(img, 2, 2, 32, 32, 0);
			//# }catch(Exception e){ 
			//# }
			//# }
			//#elifdef SEW958c
			//# if(alphImage == null){
			//# try{
			  //# Image img = Image.createImage(Res.pngPath+"xdt.png");
			  //# alphImage = Image.createImage(img, 2, 2, 32, 32, 0);
			//# }catch(Exception e){
			//# }
			//# }
			//#else
			  alphImage = getAlph(color, (byte)alph);
			//#endif
			int pngW = alphImage.getWidth();
			int pngH = alphImage.getHeight();
			drawW = drawW / pngW + (drawW % pngW == 0 ? 0 : 1);
			drawH = drawH / pngH + (drawH % pngH == 0 ? 0 : 1);
			for(int i = 0;i < drawH;i++)
			{
				for(int j = 0;j < drawW;j++)
				{
					g.drawImage(alphImage, drawX + j * pngW, drawY + i * pngH, 0);
				}
			}
		}
		finally
		{
			g.setClip(0, 0, showWidh,showHeight);
		}
	}
	
	protected static final byte[] alphData =
	{
			(byte)0x89, 0x50, 0x4e, 0x47, 0x0d, 0x0a, 0x1a, 0x0a, //png文件头
			0x00, 0x00, 0x00, 0x0D, 0x49, 0x48, 0x44, 0x52, 0x00, 0x00, 0x00, 0x20, 0x00, 0x00, 0x00, 0x20, 0x01, 0x03, 0x00, 0x00, 0x00, 0x49, (byte)0xB4, (byte)0xE8, (byte)0xB7,
			0x00, 0x00, 0x00, 0x03, 0x50, 0x4C, 0x54, 0x45, (byte)0xBD, 0x2E, 0x24, 0x5D, (byte)0xF8, (byte)0xBD, 0x64, 
			0x00, 0x00, 0x00, 0x01, 0x74, 0x52, 0x4E, 0x53,
			(byte)0x99, (byte)0xC9, 0x35, (byte)0xF3, (byte)0x86, 0x00, 0x00, 0x00, 0x0C, 0x49, 0x44, 0x41, 0x54, 0x78, (byte)0x9C, 0x63, 0x60, 0x18, (byte)0xDC, 0x00, 0x00, 0x00, (byte)0xA0, 0x00, 0x01, (byte)0xB0, 0x06, 0x62, 0x18, 0x00, 0x00, 0x00, 0x00, 0x49, 0x45, 0x4e, 0x44, (byte)0xae, 0x42, 0x60, (byte)0x82
	};

	private static Image getAlph (int color, byte alph)
	{
		int sign = alph << 24 | color;
		int index = getIndex(alphaImageSign, sign);
		if(index < 0)
		{
			alphData[41] = (byte)(color >>> 16);
			alphData[42] = (byte)(color >>> 8);
			alphData[43] = (byte)(color);
			int crc = checksum(alphData, 37, 7);
			alphData[44] = (byte)(crc >>> 24);
			alphData[45] = (byte)(crc >>> 16);
			alphData[46] = (byte)(crc >>> 8);
			alphData[47] = (byte)(crc);
			alphData[56] = alph;
			crc = checksum(alphData, 52, 5);
			alphData[57] = (byte)(crc >>> 24);
			alphData[58] = (byte)(crc >>> 16);
			alphData[59] = (byte)(crc >>> 8);
			alphData[60] = (byte)(crc);
			if(alphaImageSize >= alphaImageSign.length)
			{
				extendAlphaImage();
			}
			try
			{
				index = alphaImageSize;
				//#ifdef Nokia
				//# alphaImage[index] = DirectUtils.createImage(32, 32, sign);
				//#else
				alphaImage[index] = Image.createImage(new ByteArrayInputStream(alphData));
				//#endif
				alphaImageSign[index] = sign;
				alphaImageSize++;
			}catch(Exception e)
			{
				e.printStackTrace();
				return null;
			}
		}
		alphaImageUse[index] = true;
		return alphaImage[index];
	}
	
//	public static void drawPinkRect(Graphics g, int x, int y, int width, int height, String text, boolean isFocus)
//	{
//		g.setClip(x, y, width + 1, height + 1);
//		if(isFocus)
//		{
//			Draw.drawChoosePanel(g, x, y, width, height);
//		}
//		else
//		{
//			Draw.drawCommonPanel(g, x, y, width, height,UIManager.COLOR_CHOSE_1);
//		}
////older version
///*		g.setColor(0xffffff);
//		g.fillRoundRect(x, y, width, height, 8, 8);
//		
//		if(isFocus)
//			g.setColor(0xff0000);
//		else
//			g.setColor(0xFFB582);
//		
//		g.drawRoundRect(x, y, width, height, 8, 8);*/
//		g.setColor(0);
//		g.drawString(text, x + width / 2, y + ((height - UIManager.FONT_HEIGHT)/2 ), Graphics.TOP|Graphics.HCENTER);		
//	}
	
//	static Res soltImage;
//	static Res head_force;
//	static Res mailImage;
//	public static Sprite head;
//	private static int mailAnimIndex;
//	private static int draw_force_x = Res.getWidth(UseResListNew.IMAGAE_TITLE_PEO, 0);
//	private static int max_force ;
//	public static void drawHeadSlot(Graphics g, int x, int y, boolean hasNewMail)
//	{
//		//#ifdef S360x640
//		//# int numX = 52; 
//		//#else
//		int numX = 24; 
//		//#endif
//		g.setClip(0, 0, SysDef.SCREEN_W, SysDef.SCREEN_H);
//		
//		if(soltImage == null)
//		{
//			soltImage = ResManager.getRes(UseResListNew.IMAGAE_TITLE_PEO, true);
//		}
//		if(head_force == null)
//		{
//			head_force = ResManager.getRes(UseResListNew.IMAGAE_TITLE_FORCE, true);
//		}
//		if(titleimg==null)
//		{
//			titleimg=SystemAPI.createImage(Res.pngPath + "titletiao.png");
//		}
//		if(day == null)
//		{
//			day =ResManager.getRes(UseResListNew.IMAGE_690, true);//月日今日收入.png
//			img_513 =ResManager.getRes(UseResListNew.IMAGE_513, true);//冒号.png
//		}
////		绘制体力条
//		g.setClip(0, 0, SysDef.showWidth, SysDef.showHeigh);
//		g.drawImage(titleimg, 0, 0, 20);
//		max_force = SysDef.SCREEN_W-draw_force_x-UIResManager.miniMapImage.getWidth()+5;
//		int current_force = GameData.player.strength*max_force/GameData.player.maxStrength;
//		Res.draw(g, head_force, draw_force_x - 1 , SysDef.title_H, 0, 0, 0, 0, 0);
//		Res.draw(g, head_force, draw_force_x + current_force  , SysDef.title_H, 0, 0, 0, 2, 0);
//		for(int i=0;i<current_force;i++)
//		{
//			Res.draw(g, head_force, draw_force_x + i , SysDef.title_H, 1, 0, 0, 0, 0);
//		}
//		Draw.drawTwoNum(g, UseResListNew.NUM, GameData.player.strength, GameData.player.maxStrength, draw_force_x + max_force/2, SysDef.title_H+5, 0, 0,(byte)1, 0);
////		绘制头像
//		Res.draw(g, soltImage, x, 0, 0, 0, 0, 0, 0);
//		g.setClip(0, 0, UIManager.SCREEN_WIDTH, UIManager.SCREEN_HEIGHT);
//		g.drawImage(UIResManager.miniMapImage, x + SysDef.SCREEN_W, 0, Graphics.RIGHT|Graphics.TOP);
//		if(head == null)
//		{
//			head=new Sprite(UseResListNew.SPRITE_662, 0, 0, 0, 0, 0, false, true);
//			head.setCurSeries(GameData.player.headResIdx);
//			head.setShowXY(x+25, y+24);
//		}
//		head.draw(g);
////		绘制 金币
//		Res.draw(g, day, numX + 92 + 14, 8, 2, 0, 0, 0, Graphics.LEFT | Graphics.TOP);//现金
//		Res.draw(g, img_513, numX + 130, 12, -1, 0, 0, 0, Graphics.LEFT | Graphics.TOP);//冒号
//
//		drawNum(g, UseResListNew.NUM, Months, numX + 32, 9, 0, (byte)1,0);// 月
//		Res.draw(g, day, numX + 41, 8, 0, 0, 0, 0, Graphics.LEFT | Graphics.TOP);//月
//		drawNum(g, UseResListNew.NUM, Date, numX + 62 , 9, 0, (byte)1,0);// 日
//		Res.draw(g, day, numX + 72, 8, 1, 0, 0, 0, Graphics.LEFT | Graphics.TOP);//月 
//		
//
//		if(GameData.player!=null)
//		{
////		money = SystemAPI.getNumString(GameData.player.money);
////		drawNum(g, UseResListNew.NUM, money, numX + 136 , 9, 0, (byte)0,2);// 收入数字
//		
//		Draw.drawMoney(g,GameData.player.money, numX + 136 , 9,(byte)0,true);
//		}
//		piantMoneySp(g);
//		
////		g.setColor(0);
////		g.drawString(titleStr==null?"":titleStr, 100, 5, Graphics.LEFT|Graphics.TOP);
////		绘制邮件提示
//		if(hasNewMail && mailAnimIndex++ % 20 < 10)
//		{
//			if(mailImage == null)
//			{
//				mailImage = ResManager.getRes(UseResListNew.MAIL_ICON, true);
//			}
//			Res.draw(g, mailImage, x+SysDef.mail_W , SysDef.title_H*2 , 0, 0, 0, 0, 0);
//		}
//		else if(!hasNewMail)
//		{
//			if(mailImage == null)
//			{
//				mailImage = ResManager.getRes(UseResListNew.MAIL_ICON, true);
//			}
//			Res.draw(g, mailImage, x+SysDef.mail_W, SysDef.title_H*2 , 0, 0, 0, 0, 0);
//		}
////		old version
//		/*
//		g.drawImage(UIResManager.strengthBarImage, x + 56, y + 5, 0);
//		Res.draw(g, soltImage, x, y, 0, 0, 0, 0, 0);
//		Res.draw(g, soltImage, x + SysDef.SCREEN_W, y, 1, 0, 0, 0, Graphics.RIGHT|Graphics.TOP);
//		g.setClip(x + 55, y + 5, 98 * GameData.player.strength / GameData.player.maxStrength, 15);
//		Res.draw(g, soltImage, x + 56, y + 5, 2, 0, 0, 0, 0);
//		g.setClip(0, 0, UIManager.SCREEN_WIDTH, UIManager.SCREEN_HEIGHT);
//		Draw.drawTwoNum(g, UseResListNew.NUM, GameData.player.strength, GameData.player.maxStrength, x + 90, y + 8, 0, 0,(byte)0, 0);
//		g.drawImage(UIResManager.miniMapImage, x + SysDef.SCREEN_W, y, Graphics.RIGHT|Graphics.TOP);
//		g.drawImage(UIResManager.headBackImage, x + 6, y + 5, 0);
//		
//		if(head == null)
//		{
////			headImage = ResManager.getRes(UseResListNew.IMAGE_31, true);
//			
//			head=new Sprite(UseResListNew.SPRITE_662, 0, 0, 0, 0, 0, false, true);
//			head.setCurSeries(GameData.player.headResIdx);
//			head.setShowXY(x+25, y+24);
//		}
//		head.draw(g);
////		Res.draw(g, headImage, x + 8, y + 4, GameData.player.headResIdx, 0, 0, 0, 0);
//		
//		if(hasNewMail && mailAnimIndex++ % 20 < 10)
//		{
//			if(mailImage == null)
//			{
//				mailImage = ResManager.getRes(UseResListNew.MAIL_ICON, true);
//			}
//			Res.draw(g, mailImage, x + 55, y + 28, 0, 0, 0, 0, 0);
//		}
//		else if(!hasNewMail)//zhangxiaoqing add
//		{
//			if(mailImage == null)
//			{
//				mailImage = ResManager.getRes(UseResListNew.MAIL_ICON, true);
//			}
//			Res.draw(g, mailImage, x + 55, y + 28, 0, 0, 0, 0, 0);
//		}//zhangxiaoqing ends
//		*/	
//		}

	/**
	 * @author 画地图区域
	 * @param g
	 */
//	public static void drawMapRegional(Graphics g)
//	{
//		g.setColor(-1);
//		g.drawString(""+GameData.getMapRegional()+" 区 ", draw_force_x+max_force+5, SysDef.title_H, 20);
//		g.setColor(0);
//		g.drawString(""+GameData.getMapRegional()+" 区 ", draw_force_x+max_force+5-1, SysDef.title_H, 20);
//	}

	// add by yangzheng
//	private static Res streetRes;//街
//	private static Res roadRes;//到
//	public static void drawMapXY(Graphics g,int mapX,int mapY)
//	{
//		if(streetRes==null)streetRes=ResManager.getRes(UseResListNew.IMAGE_640, true);
//		if(roadRes==null) roadRes=ResManager.getRes(UseResListNew.IMAGE_641, true);
//		int strx=draw_force_x+45;
//		int stry = SysDef.title_H+20;
//		Res.draw(g, streetRes, draw_force_x, stry, -1, 0, 0, 0, 0);
//		Draw.drawNum(g, UseResListNew.NUM, mapX, draw_force_x+25, stry+3, 0, (byte)0, 0);
//		Res.draw(g, roadRes, strx, stry, -1, 0, 0, 0, 0);
//		Draw.drawNum(g, UseResListNew.NUM, mapY, strx+25, stry+3, 0, (byte)0, 0);
//	}
	
	private static final int MINI_MAP_WIDTH = 73;
	private static final int MINI_MAP_HEIGHT = 61;
	
//	public static void drawRadarMap(Graphics g, int x, int y)
//	{
//		//#ifdef 240x320
//		  	//# int _mapWidth = 173;
//		//#elifdef 320x240
//		//# // int _mapWidth = 254;
//		//#else 
//		int _mapWidth = 292;
//		//#endif
//		
//		if(GameData.corporation == null || GameData.corporation.shop == null)
//		{
//			return;
//		}
//		
//		int clip[]=Draw.clipRect(g, x, y, 73, 61);
//		if(Game.instance.gameRun.build != null)
//		{
//    		for(int i = 0;i < Game.instance.gameRun.build.length;i++)
//    		{
//    			BuildingSprite bd = Game.instance.gameRun.build[i];
//    			if(bd != null)
//    			{
//    				int shopX = (bd.mb.cityX - GameData.originalCityX) * MINI_MAP_WIDTH / 7;
//    				int shopY = (bd.mb.cityY - GameData.originalCityY) * MINI_MAP_HEIGHT / 7;
//    				int size = bd.mb.cellCount;
//    				
//    				if(bd.mb.type == 5)//中立
//    				{
//    					g.setColor(0xFFD800);
//    				}
//    				else if(bd.mb.isMyShop&&bd.mb.type==4)
//    				{
//    					g.setColor(0x10E700);
//    				}
//    				if((x + shopX + MINI_MAP_WIDTH / 2 + size)<= _mapWidth)
//    				{
//    					if((y - shopY + MINI_MAP_HEIGHT / 2+size) >= 85)
//    					{
//    						continue ;
//    					}
//    				}
//    				g.fillRect(x + shopX + MINI_MAP_WIDTH / 2, y - shopY + MINI_MAP_HEIGHT / 2, size, size);
//    			}
//    		}
//		}
//		g.setColor(0x10E700);
//		int shopX = (GameData.cityX - GameData.originalCityX) * MINI_MAP_WIDTH / 7;
//		int shopY = (GameData.cityY - GameData.originalCityY) * MINI_MAP_HEIGHT / 7;
//		
//		int rectW = 18;
//		int rectH = 24;
//		g.drawRect(x + shopX + MINI_MAP_WIDTH / 2 - rectW/2, y - shopY + MINI_MAP_HEIGHT/ 2 - rectH/2, rectW, rectH);
//		g.setClip(clip[0], clip[1], clip[2], clip[3]);
//	}
	
	private static final int BIG_RADAR_MAP_WIDTH = 208;
	private static final int BIG_RADAR_MAP_HEIGHT = 208;
	
	private static final int BIG_READAR_MAP_X = 20 ;
	private static final int BIG_READAR_MAP_Y = 78 ;
	
	//private static Image bigRadarImage 
//	public static void drawBigRadarMap(Graphics g, int x, int y)
//	{
//		//#ifdef 320x240
//		//# y = (UIManager.SCREEN_HEIGHT  - 208)>>1 -SysDef.title_H ;
//		//#endif
//		y = ((UIManager.SCREEN_HEIGHT  -SysDef.title_H- BIG_RADAR_MAP_HEIGHT)>>1) + SysDef.title_H;
//		x = (UIManager.SCREEN_WIDTH - 208) >> 1 ;
//		//#ifdef smallHeap
//		g.setClip(0, 0, UIManager.SCREEN_WIDTH, UIManager.SCREEN_HEIGHT);
//		g.drawRegion(UIResManager.bigRadarImage, 0, 0, 16, 16, 0, x, y, 0);
//		g.drawRegion(UIResManager.bigRadarImage, 0, 0, 16, 16, 1, x, y + BIG_RADAR_MAP_HEIGHT, Graphics.LEFT|Graphics.BOTTOM);
//		g.drawRegion(UIResManager.bigRadarImage, 0, 0, 16, 16, 7, x + BIG_RADAR_MAP_WIDTH, y + BIG_RADAR_MAP_HEIGHT, Graphics.RIGHT|Graphics.BOTTOM);
//		g.drawRegion(UIResManager.bigRadarImage, 0, 0, 16, 16, 2, x + BIG_RADAR_MAP_WIDTH, y, Graphics.RIGHT|Graphics.TOP);
//		
//		for(int i = 0;i < 11;i++)
//		{
//			g.drawRegion(UIResManager.bigRadarImage, 16, 0, 16, 16, 0, x + 16 + i * 16, y, 0);//top
//			g.drawRegion(UIResManager.bigRadarImage, 16, 0, 16, 16, 3, x + 16 + i * 16, y + BIG_RADAR_MAP_HEIGHT, Graphics.LEFT|Graphics.BOTTOM);//bottom
//			g.drawRegion(UIResManager.bigRadarImage, 0, 16, 16, 16, 0, x, y + 16 + i * 16, 0);//left
//			g.drawRegion(UIResManager.bigRadarImage, 0, 16, 16, 16, 2, x + BIG_RADAR_MAP_WIDTH, y + 16 + i * 16, Graphics.RIGHT|Graphics.TOP);//right
//			
//			for(int j = 0;j < 11;j++)
//			{
//				g.drawRegion(UIResManager.bigRadarImage, 16, 16, 16, 16, 0, x + 16 + i * 16, y + 16 + j * 16, 0);//top
//			}
//		}
//		//#else
//		//# Res resMap = Res.getMapRes(1077);
//		//# Res.draw(g, resMap, x - BIG_READAR_MAP_X, y - BIG_READAR_MAP_Y, -1, 0, 0, 0, 0);
//		//#endif
//		
//		int _width = SystemAPI.getShortArrayValue(GameData.mapWidth,GameData.mapIdIndex);
//		int _height = SystemAPI.getShortArrayValue(GameData.mapHeight,GameData.mapIdIndex);
//		Res res = Res.getMapRes(1076);
//		for(int i = 0;i < GameData.corporation.shop.length;i++)
//		{
//			Shop s = GameData.corporation.shop[i];
//			int _flagX = s.cityX*BIG_RADAR_MAP_WIDTH/_width ;
//			int _flagY = s.cityY*BIG_RADAR_MAP_HEIGHT/_height ;
//			
//			if(_flagX < 10)
//			{
//				_flagX += 5;
//			}
//			if(_flagY < 10)
//			{
//				_flagY += 5;
//			}
//			if(_flagX > 192)
//			{
//				_flagX -= 10 ;
//			}
//			if(_flagY > 192)
//			{
//				_flagY -= 16 ;
//			}
//			if((s.map_id-1) == GameData.mapIdIndex)
//			Res.draw(g, res, x+_flagX, y + _flagY, -1, 0, 0, 0, 0);
//		}
//	}
	
	/**垂直方向绘制*/
	public static final int ORIENTATION_V = 0;
	/**水平方向绘制*/
	public static final int ORIENTATION_H = 1;
	
	public static Res recruiting ;//被招揽资源Id
	public static long count = 0;
	
	public static void drawRecruiting(Graphics g,int x,int y,int resId)
	{
		 if(recruiting == null)
		 {
			 recruiting = ResManager.getRes(resId, true);
			 count = System.currentTimeMillis();
		 }
		 else
		 {
			 long cur = System.currentTimeMillis();
			 if(cur - count > 1000)
			 {
				 Res.draw(g, recruiting, x, y, 0, 0, 0, 0, 0);
			 }
			 
			 if(cur - count > 2000)
			 {
				 count = cur;
			 }		 
		 }
	}
	
	public static Res managing = null;
//	public static void drawLastTime(Graphics g, int x,int y)
//	{
//		if(managing == null)
//		{
//			managing = ResManager.getRes(1293, true);
//		}
//		else
//		{
//			 long curs =GameData.lastTime-(System.currentTimeMillis() -GameData.curReciveTime)/1000;
//			 if(curs > 0)
//			 {
//				 int cur = (int)(curs/60+(curs%60==0?0:1));
//				 int m = cur%60;
//				 int h = cur/60;
//				 if(h > 0 || m > 0)
//				 {
//					 StringBuffer num = new StringBuffer();
//					 if( h == 0 )
//					 {  
//						 num.append("00");
//					 }
//					 else if(h < 10)
//					 { 
//						 num.append("0").append(String.valueOf(h));
//					 }
//					 else
//					 {
//						 num.append(String.valueOf(h));
//					 }
//					 
//					 num.append(":");
//					 
//					 if( m == 0 )
//					 {
//						 //num.concat("00");
//						 num.append("00");
//					 }
//					 else if(m < 10)
//					 {
//						 //num.concat("0").concat(String.valueOf(m));
//						 num.append("0").append(String.valueOf(m));
//					 }
//					 else
//					 {
//						  //num.concat(String.valueOf(m));
//						  num.append(String.valueOf(m));
//					 }
//					 
//					 //num = "00:00".concat("0");
//					 Res.draw(g, managing, x, y, 0, 0, 0, 0, g.HCENTER|g.TOP);
//					 //Draw.drawTwoNumS(g, UseResListNew.NUM, h, m, x, y+14, 0, 0, (byte)1, 2,14);
//					 Draw.drawNum(g, UseResListNew.NUM, num.toString(), x-15, y+14, 0, (byte)0, 0);
//					 num.delete(0, num.length());
//				 }
//			 }
//		}
//	}
	
	
	
	private static void extendAlphaImage ()
	{
		Image[] i = alphaImage;
		int[] s = alphaImageSign;
		boolean[] u = alphaImageUse;
		int newLength = i.length + 1;
		alphaImage = new Image[newLength];
		alphaImageSign = new int[newLength];
		alphaImageUse = new boolean[newLength];
		System.arraycopy(i, 0, alphaImage, 0, i.length);
		System.arraycopy(s, 0, alphaImageSign, 0, s.length);
		System.arraycopy(u, 0, alphaImageUse, 0, u.length);
	}
	
	public static int getIndex (int[] l, int v)
	{
		for(int i = 0;i < l.length;i++)
		{
			if(l[i] == v)
			{
				return i;
			}
		}
		return -1;
	}
	
	private static int[] crc_table;

	private static void make_crc_table ()
	{
		int c;
		int n, k;
		crc_table = new int[256];
		for(n = 0;n < 256;n++)
		{
			c = n;
			for(k = 0;k < 8;k++)
			{
				if((c & 1) == 1)
					c = 0xedb88320 ^ (c >>> 1);
				else
					c = c >>> 1;
			}
			crc_table[n] = c;
		}
	}
	
	private static int update_crc (int crc, byte[] buf, int off, int len)
	{
		int c = crc ^ 0xffffffff;
		int n;
		if(crc_table == null)
		{
			make_crc_table();
		}
		for(n = off;n < len + off;n++)
		{
			c = crc_table[(c ^ buf[n]) & 0xff] ^ (c >>> 8);
		}
		return c;
	}
	
	public static int checksum (byte[] buf, int off, int len)
	{
		return update_crc(0, buf, off, len) ^ 0xffffffff;
	}

	public static int checksum (int crc, byte[] buf, int off, int len)
	{
		return update_crc(crc, buf, off, len) ^ 0xffffffff;
	}
	
	public static void drawShadowString (Graphics g, String string, int x, int y, int color, int bColor)
	{
		g.setFont(SPS);
		drawShadowStringOut(g, string, x, y, 20, bColor,0);
		g.setColor(color);
		g.drawString(string, x, y, 20);
	}
	
	/**
	 * 
	 * @param g
	 * @param string
	 * @param x
	 * @param y
	 * @param auth
	 * @param bColor
	 * @param style 0表示普通画两遍 其他表示画六遍
	 */
	public static void drawShadowStringOut (Graphics g, String string, int x, int y, int auth, int bColor,int style)
	{
		
		g.setColor(bColor);
		if(style==0)
		{
			g.drawString(string, x + 1, y, auth);
			g.drawString(string, x, y + 1, auth);
		}
		else
		{
			g.drawString(string, x - 1, y - 1, auth);
			g.drawString(string, x, y - 1, auth);
			g.drawString(string, x - 1, y, auth);
			g.drawString(string, x + 1, y, auth);
			g.drawString(string, x, y + 1, auth);
			g.drawString(string, x + 1, y + 1, auth);
		}
 
	}
	/**
	public static void drawTwoColorRect (Graphics g, int x, int y, int w, int h, int rc, int uc, int dc)
	{
		g.setColor(uc);
		g.fillRect(x + 1, y + 1, w - 1, h / 2);
		g.setColor(dc);
		g.fillRect(x + 1, y + 1 + h / 2, w - 1, h - h / 2);
		g.setColor(rc);
		g.drawRect(x, y, w, h);
	}

	public static void drawTwoColorRect (Graphics g, int x, int y, int w, int h, int rc, int uc, int dc, int maxW)
	{
		g.setColor(uc);
		g.fillRect(x + 1, y + 1, w - 1, h / 2);
		g.setColor(dc);
		g.fillRect(x + 1, y + 1 + h / 2, w - 1, h - h / 2);
		g.setColor(rc);
		g.drawRect(x, y, maxW, h);
	}
	*/
	
	
	public static void resume (Graphics g, int[] clip)
	{
		g.setClip(clip[0], clip[1], clip[2], clip[3]);
	}
	
	public static int[] clipRect (Graphics g, int x, int y, int w, int h)
	{
		int[] temp = new int[]
		{
				g.getClipX(), g.getClipY(), g.getClipWidth(), g.getClipHeight()
		};
		g.clipRect(x, y, w, h);
		return temp;
	}
	/**
	 * @param c
	 * @param charOff
	 * @param charLength
	 * @param limitWidth
	 * @return
	 */
//	public static int[] getDrawCharsInfo (char[] c, int charOff, int charLength, int limitWidth)
//	{
//		//s每一行的起始索引值，dX当前累计的长度，charWidth每个char的宽度
//		int[] charInfoTemp = new int[20];
//		int dX = 0, s = 0, charWidth;
//		int index = 0;
//		int rows = 0;
//		boolean color = false; //表明这行道目前为止全是color
//		for(int i = 0;i < charLength;i++)
//		{
//			if(c[charOff + i] == ColorSign)
//			{ //如果是颜色标识位
//				boolean isColor = true;
//				for(int j = 0;j < 6;j++)
//				{
//					if(!isHex(c[charOff + i + 1 + j]))
//					{ //如果不是16进制字符，isColor=false,
//						isColor = false;
//						break;
//					}
//				}
//				if(isColor)
//				{ //index索引加6
//					if(charOff + i == s)
//					{
//						color = true;
//					}
//					i += 6;
//					continue;
//
//				}
//			}
//			color = false;
//			if(c[charOff + i] == '\r' || c[charOff + i] == '\n')
//			{ //如果是回车，或换行
//				if(index >= charInfoTemp.length)
//				{ //如果超过charinfoTemp的存储上线，就把存储加10
//					int[] temp = charInfoTemp;
//					charInfoTemp = new int[temp.length + 10];
//					System.arraycopy(temp, 0, charInfoTemp, 0, temp.length);
//				}
//				charInfoTemp[index++] = s;
//				charInfoTemp[index++] = i - s;
//				rows++;
//				dX = 0;
//				s = i + 1;
//				if(charOff + i + 1 >= c.length || c[charOff + i + 1] == '\r' || c[charOff + i + 1] == '\n')
//				{
//					i += 1;
//				}
//			}
//			else
//			{
//				charWidth = UIManager.font.charWidth(c[charOff + i]);
//				dX += charWidth;
//				if(dX - limitWidth > 0)
//				{ //dX超过了限制宽度
//					if(index >= charInfoTemp.length)
//					{ //如果超过charinfoTemp的存储上线，就把存储加10
//						int[] temp = charInfoTemp;
//						charInfoTemp = new int[temp.length + 10];
//						System.arraycopy(temp, 0, charInfoTemp, 0, temp.length);
//					}
//					charInfoTemp[index++] = s; //记录行首位置
//					charInfoTemp[index++] = i - s; //记录行长度
//					rows++; //行数加1
//					dX = charWidth; //重置dX宽度
//					s = i; //记录下一行的行首
//				}
//			}
//		}
//		if(index >= charInfoTemp.length)
//		{
//			int[] temp = charInfoTemp;
//			charInfoTemp = new int[temp.length + 10];
//			System.arraycopy(temp, 0, charInfoTemp, 0, temp.length);
//		}
//		if(!color)
//		{
//			charInfoTemp[index++] = s;
//			charInfoTemp[index++] = charLength - s;
//			rows++;
//		}
//		int[] returnArray = new int[2 * rows];
//		System.arraycopy(charInfoTemp, 0, returnArray, 0, rows * 2);
//		return returnArray;
//	}

	/**
	 * @param g
	 * @param c
	 * @param charOff
	 * @param info
	 * @param infoOff
	 * @param row
	 * @param x
	 * @param y
	 */
	public static void drawChars (Graphics g, char[] c, int charOff, int[] info, int infoOff, int row, int x, int y)
	{
		int fontHeight = g.getFont().getHeight();
		int sY = g.getClipY() - y - fontHeight;
		sY = sY < 0 ? 0 : sY;
		sY = sY / fontHeight;
		int eY = g.getClipY() + g.getClipHeight() + fontHeight - y;
		eY = eY < 0 ? 0 : eY;
		eY = eY / fontHeight;
		int size = row;
		int dY = y + sY * fontHeight + 4;
		int index = infoOff;
		int charStart = charOff;
		int bakColor = g.getColor();
		int lastColor = bakColor;
		for(int i = sY - 1;i >= 0 && i < row;i--)
		{
			int sline = index + i * 2;
			int off = charStart + info[sline];
			int length = info[sline + 1];
			int colorSign;
			boolean hasColor = false;
			do
			{
				colorSign = haveColorSign(c, off, length);
				if(colorSign >= 0)
				{
					lastColor = getHexInterValue(c, colorSign + 1, 6);
					hasColor = true;
					length = length - (colorSign + 7 - off);
					off = colorSign + 7;
				}
			}while(colorSign >= 0);
			if(hasColor)
			{
				break;
			}
		}
		g.setColor(lastColor);
		for(int i = sY;i < eY && i < size;i++)
		{
			int sline = index + i * 2;
			int off = charStart + info[sline];
			int length = info[sline + 1];
			int drawX = x;
			int colorSign;
			do
			{
				colorSign = haveColorSign(c, off, length);
				if(colorSign >= 0)
				{
					int color = getHexInterValue(c, colorSign + 1, 6);
					g.drawChars(c, off, colorSign - off, drawX, dY, 0);
					drawX += g.getFont().charsWidth(c, off, colorSign - off);
					length = length - (colorSign + 7 - off);
					off = colorSign + 7;
					g.setColor(color);
				}
				else
				{
					g.drawChars(c, off, length, drawX, dY, 0);
				}
			}while(colorSign >= 0);
			dY += fontHeight;
		}
		g.setColor(bakColor);
	}

	public static int haveColorSign (char[] c, int off, int length)
	{
		for(int i = 0;i < length;i++)
		{
			if(c[off + i] == ColorSign)
			{
				boolean isColor = true;
				for(int j = 0;j < 6;j++)
				{
					if(!isHex(c[off + i + 1 + j]))
					{
						isColor = false;
						break;
					}
				}
				if(isColor)
				{
					return i + off;
				}
			}
		}
		return -1;
	}

	public static int getHexValue (char value)
	{
		if(value >= 0x0061 && value <= 0x0066)
		{
			return (0x000a + value - 0x0061);
		}
		else if(value >= 0x0041 && value <= 0x0046)
		{
			return (0x000a + value - 0x0041);
		}
		else if(value >= 0x0030 && value <= 0x0039)
		{
			return (0x0000 + value - 0x0030);
		}
		return -1;
	}

	public static boolean isHex (char c)
	{
		return (((c - '0') | ('9' - c)) >= 0) || (((c - 'a') | ('f' - c)) >= 0) || (((c - 'A') | ('F' - c)) >= 0);
	}

	public static int getHexInterValue (char[] c, int off, int length)
	{
		int value = 0;
		for(int i = 0;i < length;i++)
		{
			if(!isHex(c[off + i]))
			{
				return value;
			}
			else
			{
				value = (value << 4) + getHexValue(c[off + i]);
			}
		}
		return value;
	}
	
//	public static void drawSelectCell (Graphics g, String dec, boolean isFocus, int x, int y, int w, int h)
//	{
//		g.setClip(x, y, w, h);
//		g.setColor(isFocus ? 0x000000 : COMPANYUI_TEXT_COLOR);
//		if(GameDef.DEBUG_ARC)
//		{
//			g.drawArc(x + 2, y + h / 2 - 4, 10, 10, 0, 360);
//			g.fillArc(x + 4, y + h / 2 - 2, 7, 7, 0, 360);
//		}
//		else
//		{
//			g.drawRect(x + 2, y + h / 2 - 4, 10, 10);
//			g.fillRect(x + 4, y + h / 2 - 2, 7, 7);
//		}
//		g.setFont(SPS);
//		g.drawString(dec, x + 11+5, y + (h - UIManager.FONT_HEIGHT >> 1)+1, 0);
//		g.setClip(0, 0, SysDef.showWidth, SysDef.showHeigh);
//	}
////	以下是只被一个类调用的静态函数
//	public static void drawNoSelectCell (Graphics g, String dec, boolean isFocus, int x, int y, int w, int h)
//	{
//		g.setClip(x, y, w, h);
//		g.setColor(isFocus ? 0x000000 : COMPANYUI_TEXT_COLOR);
//		if(GameDef.DEBUG_ARC)
//		{
//			g.drawArc(x + 2, y + h / 2 - 4, 10, 10, 0, 360);
//		}
//		else
//		{
//			g.drawRect(x + 2, y + h / 2 - 4, 10, 10);
//		}
//		g.setFont(SPS);
//		g.drawString(dec, x + 11+5, y + ((h - UIManager.FONT_HEIGHT) / 2)+1, 0);
//		g.setClip(0, 0, SysDef.showWidth, SysDef.showHeigh);
//	}
//	可优化的函数
	public static final byte Style_Static = 0;

	public static final byte Command_Active_None = 0;
	//public static final byte Command_Active_Pressed = 1;
	
	public static final int LINE_COLOR = 0x000f49; //菜单线颜色
	public static final int RIM_COLOR = 0x4165ff; //菜单边框颜色
	
//	public static void drawCommand (Graphics g, String name, int style, int activeStat, boolean isFocus, int x, int y, int w, int h)
//	{
//		if(w < SPS.stringWidth(name))
//			w = SPS.stringWidth(name);
//		if(h < UIManager.FONT_HEIGHT)
//			h = UIManager.FONT_HEIGHT;
//		int nameAddX = x + w / 2;
//		int nameAddY;
//		//#ifdef NokiaE62
//		//# nameAddY = y + h / 2 -UIManager.FONT_HEIGHT / 2+2;//E62的字都靠上
//		//#else
//		nameAddY = y + h / 2 - UIManager.FONT_HEIGHT / 2;
//		//#endif
//		g.setFont(SPS);
//		//old version
//		/*switch(style)
//		{
//		case Style_Static:
////			g.setColor(RIM_COLOR);
//			g.setColor(Draw.COMPANYUI_TEXT_COLOR);
////			g.drawRect(x, y, w, h);
//			
//			drawRect(g,x, y, w, h,10,10, false);
//			g.setColor(isFocus ? MENUBACK_COLOR :MENUFOCUS_COLOR );
////			g.fillRect(x + 2, y + 2, w - 3, h - 3);
//			g.fillRoundRect(x + 2, y + 2, w - 3, h - 3, 10, 10);
//			//g.setColor(LINE_COLOR);
//			//if (activeStat == Command_Active_Pressed) {
//			//g.drawRect(x + 3, y + 3, w - 4, h - 4);
//			//}
//			g.setColor(Draw.COMPANYUI_TEXT_COLOR);
////			g.drawRect(x + 1, y + 1, w - 2, h - 2);
//			drawRect(g,x + 1, y + 1, w - 2, h - 2,10,10, false);
//			
//			if(!name.equals("更多游戏"))
//				g.setColor(MENU_TEXT_COLOR);
//			else
//				g.setColor(isFocus ? 0xff0000 : 0xaa0000);
//			//if (activeStat == Command_Active_Pressed) {
//			//g.drawString(name, nameAddX + 1, nameAddY + 1, Graphics.HCENTER | Graphics.TOP);
//			//}
//			//else {
//			g.setColor(Draw.COMPANYUI_TEXT_COLOR);
//			g.drawString(name, nameAddX, nameAddY+UIManager.OFFSET_FONT_HEIGHT, Graphics.HCENTER | Graphics.TOP);
//			//}
//			break;
//		}*/
//		if(isFocus)
//		{
//			Draw.drawChoosePanel(g, x, y, w, h+1);
//		}
//		else
//		{
//			Draw.drawCommonPanel(g, x, y, w, h+1,UIManager.COLOR_CHOSE_1);
//		}
//		g.setColor(0);
//		g.drawString(name, nameAddX, nameAddY+UIManager.OFFSET_FONT_HEIGHT, Graphics.HCENTER | Graphics.TOP);
//	}
	public static void drawShadowString (Graphics g, String string, int x, int y, int auth, int color, int bColor)
	{
		g.setFont(SPS);
		 
		drawShadowStringOut(g, string, x, y, auth, bColor,0);
		g.setColor(color);
		g.drawString(string, x, y, auth);
	}
	public static Res numRes;
	public static Res redNumRes;
	/**
	 * 
	 * @param g
	 * @param numResIndex
	 * @param curNum
	 * @param totalNum
	 * @param x
	 * @param y
	 * @param color  红色2，正常颜色是0
	 * @param bo 0表示画起，1表示从中间画起，2表示从右边画起
	 * @param relation type 0表示1表示数字后加一个%
	 */
	public static void drawTwoNum(Graphics g,int numResIndex,long curNum,long totalNum,int x,int y,int curcolor,int totalColor,byte bo,int typeSuffix)
	{
//		Res goodsNumRes;
//		if(numResIndex == UseResListNew.NUM)
//		{
//			if(numRes == null)
//				numRes = Res.getRes(UseResListNew.NUM);
//			goodsNumRes = numRes;
//		}
//		else
//		{
//			goodsNumRes = Res.getRes(numResIndex);
//		}
//		int numW = Res.getWidth(goodsNumRes, 0);
//		byte[] curNums = SystemAPI.getNumBytes(curNum);
//		byte[] totalNums=SystemAPI.getNumBytes(totalNum);
//		int length=(curNums.length+totalNums.length)*numW;
//		switch(bo)
//		{
//		case 0://drawNumLeft
//			break;
//		case 1://drawNumMidle
//			x-=(length>>1);
//			break;
//		case 2://drawNumRight
//			x-=length;
//			break;
//		}
//		x=drawNum (g, numResIndex, curNum,  x, y,  curcolor, (byte)0,typeSuffix);
//		
//		Res.draw(g, goodsNumRes, x, y, 10, 0, 0, 0, 0);//绘制斜杠
//		x+=numW;
//		drawNum (g, numResIndex, totalNum,  x, y,  totalColor, (byte)0,typeSuffix);
//		drawTwoNumS( g, numResIndex, curNum, totalNum, x, y, curcolor, totalColor, bo, typeSuffix,10);
	}
	
//	public static void drawTwoNumS(Graphics g,int numResIndex,long curNum,long totalNum,int x,int y,int curcolor,int totalColor,byte bo,int typeSuffix,int separator)
//	{
//		Res goodsNumRes;
//		if(numResIndex == UseResListNew.NUM)
//		{
//			if(numRes == null)
//				numRes = Res.getRes(UseResListNew.NUM);
//			goodsNumRes = numRes;
//		}
//		else
//		{
//			goodsNumRes = Res.getRes(numResIndex);
//		}
//		int numW = Res.getWidth(goodsNumRes, 0);
//		byte[] curNums = SystemAPI.getNumBytes(curNum);
//		byte[] totalNums=SystemAPI.getNumBytes(totalNum);
//		int length=(curNums.length+totalNums.length)*numW;
//		switch(bo)
//		{
//		case 0://drawNumLeft
//			break;
//		case 1://drawNumMidle
//			x-=(length>>1);
//			break;
//		case 2://drawNumRight
//			x-=length;
//			break;
//		}
//		x=drawNum (g, numResIndex, curNum,  x, y,  curcolor, (byte)0,typeSuffix);
//		
//		Res.draw(g, goodsNumRes, x, y, separator, 0, 0, 0, 0);//绘制斜杠
//		x+=Res.getWidth(goodsNumRes, separator);
//		drawNum (g, numResIndex, totalNum,  x, y,  totalColor, (byte)0,typeSuffix);
//	}
	


	/**
	 * 绘制数字
	 * 
	 * @param g
	 *        Graphics
	 * @param numResIndex
	 *        int
	 * @param num
	 *        long
	 * @param x
	 *        int
	 * @param y
	 *        int
	 * @param color 红色2，正常颜色是0
	 *        int
	 * @param anchor
	 *        byte 0表示左画起，1表示从中间画起，2表示从右边画起
	 * @param type 0表示只画数字1表示数字后加一个%
	 */
//	public static int drawNum (Graphics g, int numResIndex, long num, int x, int y, int color, byte anchor,int type)
//	{
//		Res goodsNumRes;
//		
//		if(numResIndex == UseResListNew.NUM)
//		{
//			if(numRes == null)
//				numRes = Res.getRes(UseResListNew.NUM);
//			goodsNumRes = numRes;
//		}
//		else
//		{
//			goodsNumRes = Res.getRes(numResIndex);
//		}
//		int numW = Res.getWidth(goodsNumRes, 0);
//		byte[] nums = SystemAPI.getNumBytes(num);
//		int length = nums.length;
//		switch(anchor)
//		{
//			case 0://drawNumLeft
//				for(int i = 0;i < length;i++)
//				{
//					Res.draw(g, goodsNumRes, x + i * numW, y, nums[i], color, 0, 0, 0);
//				}
//				break;
//			case 1://drawNumMidle
//				x -= (numW * length >> 1);
//				for(int i = 0;i < length;i++)
//				{
//					Res.draw(g, goodsNumRes, x + i * numW, y, nums[i], color, 0, 0, 0);
//				}
//				break;
//			case 2://drawNumRight
//				x -= numW * length;
//				for(int i = 0;i < length;i++)
//				{
//					Res.draw(g, goodsNumRes, x + i * numW, y, nums[i], color, 0, 0, 0);
//				}
//				break;
//		}
//		x=x+length*numW;
//		if(type==1)//数字后面加入%
//		{
//			Res.draw(g, goodsNumRes, x, y, 18, color, 0, 0, 0);//
//			x=x+Res.getWidth(goodsNumRes, 18);
//		}
//		
//		return x;
//	}
	/**
	 * 
	 * @param g
	 * @param numResIndex
	 * @param num
	 * @param x
	 * @param y
	 * @param color
	 * @param anchor
	 * @param type
	 * @return
	 */
//	public static int drawNum(Graphics g, int numResIndex, String num, int x, int y, int color, byte anchor,int type,boolean show)
//	{
//		return drawNum (g, numResIndex, num, x, y, color, anchor, show?type:0);
//	}
//	public static int drawNum (Graphics g, int numResIndex, String num, int x, int y, int color, byte anchor,int type)
//	{
//		Res goodsNumRes;
//		
//		if(numResIndex == UseResListNew.NUM)
//		{
//			if(numRes == null)
//				numRes = Res.getRes(UseResListNew.NUM);
//			goodsNumRes = numRes;
//		}
//		else
//		{
//			goodsNumRes = Res.getRes(numResIndex);
//		}
//		int numW = Res.getWidth(goodsNumRes, 0);
//		byte[] nums = SystemAPI.getNumBytes(num);
//		int length = nums.length;
//		switch(anchor)
//		{
//			case 0://drawNumLeft
//				for(int i = 0;i < length;i++)
//				{
//					Res.draw(g, goodsNumRes, x + i * numW+(nums[i]==14?2:0), y, nums[i], color, 0, 0, 0);
//				}
//				break;
//			case 1://drawNumMidle
//				x -= (numW * length >> 1);
//				for(int i = 0;i < length;i++)
//				{
//					Res.draw(g, goodsNumRes, x + i * numW, y, nums[i]+(nums[i]==14?2:0), color, 0, 0, 0);
//				}
//				break;
//			case 2://drawNumRight
//				x -= numW * length;
//				for(int i = 0;i < length;i++)
//				{
//					Res.draw(g, goodsNumRes, x + i * numW, y, nums[i]+(nums[i]==14?2:0), color, 0, 0, 0);
//				}
//				break;
//		}
//		x=x+length*numW;
//		if(type==1)//数字后面加入%
//		{
//			Res.draw(g, goodsNumRes, x, y, 18, color, 0, 0, 0);//
//			x=x+Res.getWidth(goodsNumRes, 18);
//		}else if(type == 2)//数字后面加上G
//		{
//			Res.draw(g, goodsNumRes, x+1, y, 15, color, 0, 0, 0);//
//			x=x+Res.getWidth(goodsNumRes, 15);
//		}else if(type == 3)//数字后面加上万
//		{
//			Res.draw(g, goodsNumRes, x+1, y, 11, color, 0, 0, 0);//
//			x=x+Res.getWidth(goodsNumRes, 11);
//			Res.draw(g, goodsNumRes, x+2, y, 15, color, 0, 0, 0);//
//			x=x+Res.getWidth(goodsNumRes, 15);
//		}else if(type == 4)//数字后面加上亿
//		{
//			Res.draw(g, goodsNumRes, x+1, y, 12, color, 0, 0, 0);//
//			x=x+Res.getWidth(goodsNumRes, 12);
//			Res.draw(g, goodsNumRes, x+2, y, 15, color, 0, 0, 0);//
//			x=x+Res.getWidth(goodsNumRes, 15);
//		}
//		return x;
//	}
	
	/**
	 *  绘制按钮
	 */
	public static Res smallButton;
	public static void drawButton(Graphics g,int x,int y,int w,boolean isFocus)
	{
		if(smallButton == null)
		{
			smallButton = ResManager.getRes(UseResListNew.IMAGAE_MENUITEM_INDES,true);
		}
		if(smallButton != null){
//			System.out.println("asd ="+smallButton.getHeight(smallButton, 0));
			int clip[] = Draw.clipRect(g, x, y, w, smallButton.getHeight(smallButton, 0));
//			g.setColor(0);
//			g.drawRect(x, y, w-1, smallButton.getHeight(smallButton, 0)-2);
//			g.setClip(x, y, w, smallButton.getHeight(smallButton, 0)+10);
			int imgW = 2;
			Res.draw(g, smallButton, x, y, 0+(isFocus?2:0), 0, 0, 0, 0);//左
			int time = (w-6)/2+(((w-6)%2==0)?0:1);
			
			for(int i = 0; i < time; i++){
				Res.draw(g, smallButton, x+3 + (i * imgW), y, 1+(isFocus?2:0), 0, 0, 0, 0);//中
			}
			Res.draw(g, smallButton, x + w-3, y, 0+(isFocus?2:0), 0, 0, Res.TRANS_MIRROR, 0);//右
			g.setClip(clip[0], clip[1], clip[2], clip[3]);
		}
	}
	
	public static Res arrowSelected;
	
	public static void drawArrowSeleced(Graphics g,int x,int y,boolean isFocus)
	{
		if(arrowSelected == null)
		{
			arrowSelected = ResManager.getRes(UseResListNew.IMAGE_ARROW_SELECTED,true);
		}
		if(arrowSelected != null){
			 
			Res.draw(g, arrowSelected, x  , y, 0+(isFocus?0:1), 0, 0, 0, 0);//右
		}
	}
	/**
	 * 绘制通用框
	 */
	public static Res CommonPanelRes;
//	public static void drawCommonPanel(Graphics g,int x,int y,int w,int h)
//	{
//		g.setColor(UIManager.COLOR_CHOSE_1);
//		g.setColor(0xffffff);
//		g.fillRect(x, y, w-1, h-1);
//		g.setColor(UIManager.COLOR_CHOSE_k);
//		g.drawRect(x, y, w-1, h-1); 
//	}
	public static void drawCommonPanel(Graphics g,int x,int y,int w,int h,int color)
	{
		/*g.setColor(UIManager.COLOR_CHOSE_1);
		g.setColor(0xffffff);
		g.fillRoundRect(x+1,  y+1, w, h,5,5);
		g.setColor(color);
		g.drawRoundRect(x, y, w, h,5,5); */

		g.setColor( color);
		g.drawRoundRect(x,  y, w, h,5,5);
		g.setColor( 0xffffff);
		g.drawRoundRect(x+1, 1 + y, w-2, h-2,2,2);
		g.drawRoundRect(x+2, 2 + y, w-4, h-4,0,0);
		g.fillRoundRect(x+3, 3 + y, w-5, h-5,0,2);
		//old version
		/*
		if(w<32||h<32)
		{
			System.out.println("非法的宽度和高度,宽度和高度不能小于32");
			
			return;
		}
		 
		
		if(CommonPanelRes==null)
		{
			CommonPanelRes=ResManager.getRes(UseResListNew.RIM,true);
		}else
		{
			int rimWidth0=Res.getWidth(CommonPanelRes, 0);//边角的宽度和高度相等，是个正方形
			int rimHeight0=Res.getHeight(CommonPanelRes, 0);
			int rimWidth2=Res.getWidth(CommonPanelRes, 2);
			int rimHeight2=Res.getHeight(CommonPanelRes, 2);
			int DrawW=(w-2*rimWidth2)/rimWidth2+((w-2*rimWidth2)%rimWidth2==0?0:1);
			g.setClip(x+rimWidth2, y, w-rimWidth2*2, h);
			for(int i=0;i<DrawW;i++)
			{
				Res.draw(g, CommonPanelRes, x+(i+1)*rimWidth2, y, 2, 0, 0, 0, 0);
				Res.draw(g, CommonPanelRes, x+(i+1)*rimWidth2, y+h-rimHeight2, 2, 0, 0, 3, 0);
			}
//			
			int rimWidth1=Res.getWidth(CommonPanelRes, 1);
			int rimHeight1=Res.getHeight(CommonPanelRes, 1);
			int DrawH=(h-2*rimHeight1)/rimHeight1+((h-2*rimHeight1)%rimHeight1==0?0:1);
			g.setClip(x, y+rimHeight1, w, h-rimHeight1*2);
			for(int i=0;i<DrawH;i++)
			{
				Res.draw(g, CommonPanelRes, x, y+(i+1)*rimHeight1, 1, 0, 0, 0, 0);
				Res.draw(g, CommonPanelRes, x+w-rimWidth1, y+(i+1)*rimHeight1, 1, 0, 0, 2, 0);
			}

			g.setClip(x, y, w, h);
			Res.draw(g, CommonPanelRes, x, y, 0, 0, 0, 0, 0);
			Res.draw(g, CommonPanelRes, x+w-rimWidth0, y, 0, 0, 0, 4, 0);
			Res.draw(g, CommonPanelRes,	x, y+h-rimHeight0, 0, 0, 0, 6, 0);
			Res.draw(g, CommonPanelRes, x+w-rimWidth0, y+h-rimHeight0, 0, 0, 0, 3, 0);
			g.setColor(0xffe9c2);
			g.fillRect(x+rimWidth1, y+rimHeight2, w-2*rimWidth1, h-2*rimHeight2);
		}
		*/	
	}
	
	public static void drawChoosePanel(Graphics g,int x,int y,int w,int h,int color0,int color1,int color2)
	{
		g.setColor( color0);
		g.drawRoundRect(x,  y, w, h,5,5);
		g.setColor( color1);
		g.drawRoundRect(x+1, 1 + y, w-2, h-2,2,2);
		g.drawRoundRect(x+2, 2 + y, w-4, h-4,0,0);
		g.setColor(color2);
		g.fillRoundRect(x+3, 3 + y, w-5, h-5,0,2);
	}
	
	/**
	 * 绘制通用框
	 */
	public static Res choosePanelRes;
//	public static void drawChoosePanel(Graphics g,int x,int y,int w,int h)
//	{ 
//		
//		g.setColor(UIManager.COLOR_CHOSE_0);
//		g.drawRoundRect(x,  y, w, h,5,5);
//		g.setColor(UIManager.COLOR_CHOSE_1);
//		g.drawRoundRect(x+1, 1 + y, w-2, h-2,2,2);
//		g.drawRoundRect(x+2, 2 + y, w-4, h-4,0,0);
//		g.setColor(UIManager.COLOR_CHOSE_2);
//		g.fillRoundRect(x+3, 3 + y, w-5, h-5,0,2);
//		//old version
//	/*	if(w<32||h<32)
//		{
//			System.out.println("非法的宽度和高度,宽度和高度不能小于32");
//			
//			return;
//		}
//		 
//		
//		if(choosePanelRes==null)
//		{
//			choosePanelRes=ResManager.getRes(UseResListNew.IMAGE_501,true);
//		}else
//		{
//			int rimWidth0=Res.getWidth(choosePanelRes, 0);//边角的宽度和高度相等，是个正方形
//			int rimHeight0=Res.getHeight(choosePanelRes, 0);
//			int rimWidth2=Res.getWidth(choosePanelRes, 2);
//			int rimHeight2=Res.getHeight(choosePanelRes, 2);
//			int DrawW=(w-2*rimWidth2)/rimWidth2+((w-2*rimWidth2)%rimWidth2==0?0:1);
//			g.setClip(x+rimWidth2, y, w-rimWidth2*2, h);
//			for(int i=0;i<DrawW;i++)
//			{
//				Res.draw(g, choosePanelRes, x+(i+1)*rimWidth2, y, 2, 0, 0, 0, 0);
//				Res.draw(g, choosePanelRes, x+(i+1)*rimWidth2, y+h-rimHeight2, 2, 0, 0, 3, 0);
//			}
////			
//			int rimWidth1=Res.getWidth(choosePanelRes, 1);
//			int rimHeight1=Res.getHeight(choosePanelRes, 1);
//			int DrawH=(h-2*rimHeight1)/rimHeight1+((h-2*rimHeight1)%rimHeight1==0?0:1);
//			g.setClip(x, y+rimHeight1, w, h-rimHeight1*2);
//			for(int i=0;i<DrawH;i++)
//			{
//				Res.draw(g, choosePanelRes, x, y+(i+1)*rimHeight1, 1, 0, 0, 0, 0);
//				Res.draw(g, choosePanelRes, x+w-rimWidth1, y+(i+1)*rimHeight1, 1, 0, 0, 2, 0);
//			}
//
//			g.setClip(x, y, w, h);
//			Res.draw(g, choosePanelRes, x, y, 0, 0, 0, 0, 0);
//			Res.draw(g, choosePanelRes, x+w-rimWidth0, y, 0, 0, 0, 4, 0);
//			Res.draw(g, choosePanelRes,	x, y+h-rimHeight0, 0, 0, 0, 6, 0);
//			Res.draw(g, choosePanelRes, x+w-rimWidth0, y+h-rimHeight0, 0, 0, 0, 3, 0);
//			g.setColor(0xffffed);
//			g.fillRect(x+rimWidth1, y+rimHeight2, w-2*rimWidth1, h-2*rimHeight2);
//		}	*/
//	}
    /**绘制框**/
//    public static void drawRect(Graphics g,int x, int y,int w,int h,int cw,int ch, boolean isFocus)
//    {
//    	if(isFocus)
//		{
//			Draw.drawChoosePanel(g, x, y, w, h);
//		}
//		else
//		{
//			Draw.drawCommonPanel(g, x, y, w, h,UIManager.COLOR_CHOSE_1);
//		}
//    	/*if(isFocus)
//    	{
//    		g.setColor(0xffffff);
//			g.fillRoundRect(x, y, w, h, cw, ch);
//    	}
//    	
//    	g.setColor(temp);
//    	g.drawRoundRect(x, y, w, h, cw, ch);*/
//    }
    /**绘制线条**/
    public static void drawLine(Graphics g,int x, int y,int w,int h)
    {
    	g.drawLine(x, y, w, h);
    }
    /**绘制文字**/
	public static void drawStr(Graphics g,String str,int x,int y,int o){
		if(str!=null)
		g.drawString(str, x, y, o);
	}
 
	
	/**
	 * 
	 * @param str  获取字符行数来进行设置提示框的大小
	 * title 为标题
	 */

//	public static int  inIntLine(String box,int w)
//	{	
//		int[] decInfo; //描述数据
//		char boxstr[] = box.toCharArray();
//		decInfo = Draw.getDrawCharsInfo(boxstr, 0, boxstr.length, w-UIManager.FONT_WIDTH*2);
//		int boxLing  = (byte)(decInfo.length >> 1);
//		return boxLing;
//	}
//	
//	 /**
//	  * 
//	  * @param g 画游戏中背景
//	  */
//	public static void drawgameBg(Graphics g,int x,int index)
//	{
//		 
//		for(int i = 0;i < index;i++)
//		{
//			g.drawImage(UIResManager.gameBeamImage,  x + i*1, 0, 0);
//		}
//	}
//	public static void drawBg(Graphics g)
//	{  
//
//		drawgameBg(g,0,SysDef.SCREEN_W);
////		
//
//	}
	public static Res arrow;
	public static void drawArrow(Graphics g,int x,int y,int distance,int num,int num1)
	{
		if(arrow==null)
		{
			arrow=ResManager.getRes(UseResListNew.ARROW, true);
		}
		else
		{
			int w=Res.getWidth(arrow, 0);//宽度
			Res.draw(g, arrow, x-w-distance, y, -1, 0, 0, 0, Graphics.HCENTER|Graphics.VCENTER);
			Res.draw(g, arrow, x+distance+w, y, -1, 0, 0, Res.DEF_MIRROR, Graphics.HCENTER|Graphics.VCENTER);
		  	if(num>0&&num1>0)
		  	{
		  		drawTwoNum(g, UseResListNew.NUM,num,num1, x,  y-5, 0,0, (byte)1,0);//Y
		  	}
		}
	}
	static int arrowTick;
	public static void drawArrow(Graphics g,int x,int y,int distance,int num,int num1,boolean bian)
	{
		if(bian)
			arrowTick++;
		if(arrow==null)
		{
			arrow=ResManager.getRes(UseResListNew.ARROW, true);
		}
		else
		{
			int w=Res.getWidth(arrow, 0);//宽度
			Res.draw(g, arrow, x-w-distance-(arrowTick/5)%2*3, y, -1, 0, 0, 0, Graphics.HCENTER|Graphics.VCENTER);
			Res.draw(g, arrow, x+distance+w+(arrowTick/5)%2*3, y, -1, 0, 0, Res.DEF_MIRROR, Graphics.HCENTER|Graphics.VCENTER);
		  	if(num>0&&num1>0)
		  	{
		  		drawTwoNum(g, UseResListNew.NUM,num,num1, x,  y-5, 0,0, (byte)1,0);//Y
		  	}
		}
	}
	/**
	 * add by zzx
	 */
	public static final int LEFT_MARGIN = 3;
//	public static final int HEIGHT = UIManager.FACE_SIZE;//图片表情高度是
//	public static final int WIDTH = UIManager.FACE_SIZE;//图片表情宽度是
	public static int offsetH;
	public static Res faceIcon;
	public static int oldColor;
	public static boolean isStop;
	public static int sumlines;
	
//	public static void drawCurrent(Graphics g,String text,int color,int x,int y, int sw,int sh/*,int anchot*/,ScrollBar bar)
//	{
////		g.setClip(x, y, sw, sh);
//		g.setColor(color);
//		if(faceIcon == null)
//		{
//			faceIcon = ResManager.getRes(UseResListNew.FACE_ICON, true);
//		}
//		
//		char[] chars = text.toCharArray();
//		
//		int width = LEFT_MARGIN;
//		int lines = 0;
//		
//		int lineHeight = UIManager.FONT_HEIGHT > UIManager.FACE_SIZE ? UIManager.FONT_HEIGHT: UIManager.FACE_SIZE;
//		
//		for(int i = 0;i < chars.length;i++)
//		{
//			if(chars[i] == '\n')
//			{
//				width = LEFT_MARGIN;
//				lines ++;
//				continue;
//			}
//			else if(chars[i] == '\\')
//			{
//				if(i + 1 < chars.length)
//				{
//					if(chars[i + 1] == 'n')
//					{
//						i++;
//						width = LEFT_MARGIN;
//						lines ++;
//						continue;
//					}
//				}
//			}
//			
//			int faceId = -1;
//			
//			if(chars[i] == '/')
//			{
//				if(i + 2 < chars.length)
//				{
//					String tmp = new String(chars, i + 1, 2);
//					
//					for(int j = 0;j < UIManager.face.length;j++)
//					{
//						if(tmp.equals(UIManager.face[j]))
//						{
//							faceId = j;
//							i += 2;
//							break;
//						}
//					}
//					
//					boolean jumpOut = false;
//					
//					for(int j = 0;j < UIManager.colorTable.length;j++)
//					{
//						if(tmp.equals(UIManager.colorTable[j]))
//						{
//							if(tmp.equals("恢复"))
//							{
//								g.setColor(oldColor);
//							}
//							else
//							{
//								oldColor = g.getColor();
//								g.setColor(UIManager.colorValue[j]);
//							}
//							
//							i += 2;
//							jumpOut = true;
//							break;
//						}
//					}
//					
//					if(jumpOut)
//					{
//						continue;
//					}
//				}
//			}
//			
//			int w = 0;
//			
//			if(faceId == -1)
//			{
//				w = UIManager.font.charWidth(chars[i]);
//			}
//			else
//			{
//				w = UIManager.FACE_SIZE;//表情图片宽度
//			}
//			
//			if(width + w > sw - (bar == null ? 0 : bar.getWidth()))
//			{
//				width = LEFT_MARGIN;
//				lines ++;
//			}
//			
//			if(faceId == -1)
//			{
//				g.drawChar(chars[i], x+width, y+(lines - offsetH) * lineHeight, 0);
//			}
//			else
//			{
//				Res.draw(g, faceIcon, x+width, y+(lines - offsetH) * lineHeight, faceId, 0, 0, 0, 0);
//			}
//			
//			width += w;
//		}
//		if(bar != null)
//		{
//			if(isStop)
//			{
//				sumlines = (UIManager.FONT_HEIGHT * (lines+1));
////				System.out.println(" == "+lineHeight+" =sumlines == " + sumlines+"  == "+bar.getHeight());
//				isStop = false;
//			}
//
//			bar.setRange(0, lines - sh / lineHeight + 1);
//			if(sumlines > sh)
//			{
//				bar.draw(g);
//			}
//		}
//	}
	/**
	 * 自动根据宽度来计算高度,所以可以忽略参数 add yangzheng
	 */

	public static int ceshiLing;
//	public static int getLineNumber(String text,int maxW)
//	{ 
//		char[] chars = text.toCharArray();
//		
//		int width = LEFT_MARGIN;
//		int lines = 0;
//		
//		int lineHeight = UIManager.FONT_HEIGHT > UIManager.FACE_SIZE ? UIManager.FONT_HEIGHT: UIManager.FACE_SIZE;
//		
//		for(int i = 0;i < chars.length;i++)
//		{
//			if(chars[i] == '\n')
//			{
//				width = LEFT_MARGIN;
//				lines ++;
//				ceshiLing++;
//				continue;
//			}
//			else if(chars[i] == '\\')
//			{
//				if(i + 1 < chars.length)
//				{
//					if(chars[i + 1] == 'n')
//					{
//						i++;
//						width = LEFT_MARGIN;
//						lines ++;
//						ceshiLing++;
//						continue;
//					}
//				}
//			}
//			
//			int faceId = -1;
//			
//			if(chars[i] == '/')
//			{
//				if(i + 2 < chars.length)
//				{
//					String tmp = new String(chars, i + 1, 2);
//					
//					for(int j = 0;j < UIManager.face.length;j++)
//					{
//						if(tmp.equals(UIManager.face[j]))
//						{
//							faceId = j;
//							i += 2;
//							break;
//						}
//					}
//					
//					boolean jumpOut = false;
//					
//					for(int j = 0;j < UIManager.colorTable.length;j++)
//					{
//						if(tmp.equals(UIManager.colorTable[j]))
//						{  
//							i += 2;
//							jumpOut = true;
//							break;
//						}
//					}
//					
//					if(jumpOut)
//					{
//						continue;
//					}
//				}
//			}
//			
//			int w = 0;
//			
//			if(faceId == -1)
//			{
//				w = UIManager.font.charWidth(chars[i]);
//			}
//			else
//			{
//				w = UIManager.FACE_SIZE;//表情图片宽度
//			}
//			
//			if(width + w > maxW /*- (bar == null ? 0 : bar.getWidth())*/)
//			{
//				width = LEFT_MARGIN;
//				lines ++;
//				ceshiLing++;
//			} 
//			
//			width += w;
//		}
//		
//		return lines*lineHeight+lineHeight;
//	}

	/**
	 * 得到当前字的高度
	 * 
	 */
//	public static int getFontH(){
//		int h = 0;
//		if(Draw.HEIGHT>UIManager.FONT_HEIGHT){
//			h = Draw.HEIGHT;
//		}else{
//			h = UIManager.FONT_HEIGHT;
//		}
//		return h;
//	}
//	public static int getFontW()
//	{
//		int w = 0;
//		if(WIDTH>UIManager.FONT_WIDTH)
//		{
//			w = WIDTH;
//		}else
//		{
//			w = UIManager.FONT_WIDTH;
//		}
//		return w;
//	}
	/**
	 * 绘制九宫格
	 * @param g
	 * @param x 起始x位置
	 * @param y 起始y位置
	 * @param w_number 横向数量
	 * @param h_number 纵向数量
	 * @param gridWidth 每个框的宽度
	 * @param gridHeight 每个框的高度
	 * @param offsetgridW 框框横向间隔
	 * @param offsetgridH 框框纵向间隔
	 */
	
//	public static Res noGoods=null;
//	public static void drawNineSquareGrid(Graphics g,boolean candraw,int choseIndex,Props info[],int x,int y,int w_number,int h_number,int gridWidth,int gridHeight,int offsetgridW,int offsetgridH,boolean drawNum,int  currentPage){
////		System.out.println("choseIndex ="+choseIndex);
//		for(int i = 0;i < w_number;i++){
//			for(int j = 0;j<h_number;j++){
////				绘制背景框
//				if((i+j*w_number) == choseIndex && candraw)
//				{
//					Draw.drawChoosePanel(g, x+i*offsetgridW, y+j*offsetgridH, gridWidth, gridHeight);
//	    		}
//	    		else
//	    		{
//	    			Draw.drawCommonPanel(g, x+i*offsetgridW, y+j*offsetgridH, gridWidth, gridHeight,UIManager.COLOR_CHOSE_1);
//	    		}
////					drawRect(g,x+i*offsetgridW, y+j*offsetgridH, gridWidth, gridHeight,Draw.color_grid_chose,Draw.color_grid);
////				}
////				else
////				{
////					drawRect(g,x+i*offsetgridW, y+j*offsetgridH, gridWidth, gridHeight,Draw.color_backgrid,Draw.color_grid);
////				}
//        	}
//		}
//		//绘制道具
//		if(noGoods==null)
//		{
//			noGoods=ResManager.getRes(UseResListNew.IMAGE_GOOD_DEFALUT, true);
//		}
//		Res good=null;
//		if(info!=null)
//		{
//			for(int i=0;i<(w_number*h_number);i++){
//				if((currentPage*w_number+i)<info.length)
//				if(info[currentPage*w_number+i]!=null)
//				{
////					绘制图片
//					if(info[currentPage*w_number+i].icon>0)
//					{
//						good=ResManager.getRes(info[currentPage*w_number+i].icon, false);
//					}
//					if(good==null)
//					{
//						good=noGoods;
//					}
//					
//					Res.draw(g, good, x+(i%w_number)*offsetgridW+gridWidth/2, y+(i/w_number)*offsetgridH+gridHeight/2, -1, 0, 0, 0, UIManager.ANCHOR_HCENTER_VCENTER);
////					绘制数量
//					if(drawNum)
//					Draw.drawNum(g, UseResListNew.NUM, info[currentPage*w_number+i].count, x+(i%w_number)*offsetgridW+offsetgridW-3, y+(i/w_number)*offsetgridH+offsetgridH-16, 0, (byte)2, 0);
//				}
//				
//			}
//		}
//	}

	public static void drawRect(Graphics g,int x,int y,int w,int h,int color,int color2){
		if(color!=-2){
			g.setColor(color);
			g.fillRect(x, y, w, h);
		}
		g.setColor(color2);
		g.drawRect(x, y, w, h);
	}
//	----------------------------------------------------------------
	public static final int DELTA_Y = 27;
	public static Image titleimg;
	public static String titleStr;
	
	public static void updataTitleStr(long money)
	{
	
		Income = money;
	}
	/**
	 * @author 日期更新
	 * @param m
	 * @param d
	 */

	public static int numX = 14; 
	public static void updataDay(byte m,byte d)
	{
		Months = m;
		Date = d;
	}
	public static Res day = null;
	public static Res img_513;
	public static byte Months=0,Date=0;
	public static long Income=0;
//	public static void drawTitle(Graphics g)
//	{	 
//		if(titleimg==null)
//		{
//			titleimg=SystemAPI.createImage(Res.pngPath + "titletiao.png");
//		}
//		if(day == null)
//		{
//			day =ResManager.getRes(UseResListNew.IMAGE_690, true);//月日今日收入.png
//			img_513 =ResManager.getRes(UseResListNew.IMAGE_513, true);//冒号.png
//		}
//		g.setClip(0, 0, SysDef.showWidth, SysDef.showHeigh);
//		g.drawImage(titleimg, 0, 0, 20);
//		Res.draw(g, day, numX + 92 + 14, 8, 2, 0, 0, 0, Graphics.LEFT | Graphics.TOP);//现金
//		Res.draw(g, img_513, numX + 130, 12, -1, 0, 0, 0, Graphics.LEFT | Graphics.TOP);//冒号
////		String money = "";
//		if(GameData.player!=null)
//		{
////		money = SystemAPI.getNumString(GameData.player.money);
////		drawNum(g, UseResListNew.NUM, money, numX + 136 , 9, 0, (byte)0,2);// 收入数字
//		
//		Draw.drawMoney(g,GameData.player.money, numX + 136 , 9,(byte)0,true);
//		}
//		g.setColor(0);
//		g.drawString(titleStr==null?"":titleStr, 100, 5, Graphics.LEFT|Graphics.TOP);
//		piantMoneySp(g);
////		old version 
//		drawNum(g, UseResListNew.NUM, Months, numX + 32, 9, 0, (byte)1,0);// 月
//		Res.draw(g, day, numX + 41, 8, 0, 0, 0, 0, Graphics.LEFT | Graphics.TOP);//月
//		drawNum(g, UseResListNew.NUM, Date, numX + 62 , 9, 0, (byte)1,0);// 日
//		Res.draw(g, day, numX + 72, 8, 1, 0, 0, 0, Graphics.LEFT | Graphics.TOP);//月 
//		 
//	}
	public static Sprite moneySprite;
//	public static void piantMoneySp(Graphics g)
//	{
//
//		if(moneySprite == null)
//		{
//			moneySprite=new Sprite(UseResListNew.IMAGE_1075, 0, 0, 0, 0, 0, false, true);
//			moneySprite.setCurSeries(0);
//			moneySprite.setShowXY(SysDef.SCREEN_W - 16, 18);
//			
//		}else
//		{
//			moneySprite.draw(g);
//			if(moneySprite.autoRunSprite() == 2)
//			{
//				moneySprite.setCurSeries(0);
//			}
//			
//		}
//	}
	/**
	 * 绘制UITitleBar
	 */
	public static Res employeeTitleBar;
	/**
	 * 
	 * @param g
	 * @param x
	 * @param y
	 * @param type 为类弄 0为中间的  1为画左的线条  2为画右边的线条
	 */
	public static void drawUITitleBar(Graphics g,int x,int y, int type)
	{
		if(employeeTitleBar==null)
		{
			employeeTitleBar = ResManager.getRes(UseResListNew.IMAGE_77, true);
		}

		if(type == 0)
		{
			Res.draw(g, employeeTitleBar, x, y, 1, 0, 0, 0, Graphics.LEFT|Graphics.TOP);
		}
		else if(type == 1)
		{
			Res.draw(g, employeeTitleBar, x, y, 0, 0, 0, 0, Graphics.LEFT|Graphics.TOP);
		}
		else if(type == 2)
		{
			Res.draw(g, employeeTitleBar, x, y, 0, 0, 0, Res.TRANS_MIRROR, Graphics.LEFT|Graphics.TOP);
		}
	}

	
//	public static void drawUITitleBar(Graphics g,int x,int y,int w,String  str)
//	{
//		if(employeeTitleBar==null)
//		{
//			 employeeTitleBar = ResManager.getRes(UseResListNew.IMAGE_77, true);
//		}
//		int count = (w -12)/ 3 + ((w-12)%3 == 0 ? 0 : 1);
//		int barHeight=Res.getHeight(employeeTitleBar, 1);
//		int i=0;
//		
//		
//		
//		for( i = 0;i < count;i++)
//		{
//			Draw.drawUITitleBar(g , 6+x+i*3, y, 0);
//		}
//		Draw.drawUITitleBar(g,x,y,1);
//		
//		Draw.drawUITitleBar(g,x+w - 6,y, 2);
//		
//		g.setColor(0);
////		Res.draw(g, res, x+(w>>1), y+4, -1, 0, 0, 0, Graphics.HCENTER|Graphics.TOP);
//		g.drawString(str==null?"":str, x+(w>>1), y+((barHeight-UIManager.FONT_HEIGHT)>>1), g.TOP|g.HCENTER);
//	}

	
	/**
	 * @author 画任务动画
	 */
	public static Sprite taskTile;
	public static Sprite taskIsOK;
//	public static void drawTaskMove(Graphics g)
//	{
//		if(GameData.getTaskStatus()==0)
//		{
//			if(taskTile == null)
//			{
//				taskIsOK = null;
//			
//				//#ifdef Pointer
//				taskTile=new Sprite(UseResListNew.SPRITE_1072, 0, 0, 0, 0, 0, false, true);
//				taskTile.setShowXY((SysDef.SCREEN_W)>>1, SysDef.showHeigh-5);
//				//#else
//				//# taskTile=new Sprite(UseResListNew.SPRITE_818, 0, 0, 0, 0, 0, false, true);
//				//# taskTile.setShowXY((SysDef.SCREEN_W)>>1, SysDef.showHeigh-5);
//				//#endif
//				
//			}else
//			{
//				if(GameRun.compText.getText().equals(""))
//				{
//					taskTile.draw(g);
//				}
////				System.out.println("黄色000````````````````````````````");
//			}
//		}
//	}
	public static Sprite skip;
	
//	public static boolean runSkip()
//	{
//		if(skip != null)
//		{
//			if(UIManager.isPointerPressed(SysDef.SCREEN_W/2-37, SysDef.SCREEN_H-34-23, 73, 23)||UIManager.isKeyPressed(UIManager.KEY_STAR))
//			{
//				return true;
//			}
//		}
//		return false;
//	}
//	public static void drawSkip(Graphics g)
//	{
//		if(skip == null)
//		{
//			//#ifdef Pointer
//				skip = new Sprite(UseResListNew.SPRITE_948, 0, 0, 0, 0, 0, false, true);
//			//#else
//				//# skip = new Sprite(UseResListNew.SPRITE_1230, 0, 0, 0, 0, 0, false, true);
//			//#endif
//			
//			skip.setShowXY(SysDef.SCREEN_W/2, SysDef.SCREEN_H-34);
//		}else
//		{
//			g.setClip(0, 0, SysDef.SCREEN_W, SysDef.SCREEN_H);
//			skip.draw(g);
//			skip.autoRunSprite();  
//		}
//	}
//	public static boolean draw_shop_face;
//	public static int draw_shop_face_id;
//	public static Image back;
//	public static Sprite shop_face;
//	public static Res res_shop;
//	public static void drawShopFace(Graphics g,int resID,int defresId)
//	{
//		// 动画
//		if(shop_face == null)
//		{
//			shop_face = new Sprite(UseResListNew.SPRITE_1189, 0, 0, 0, 0, 0, false, true);
//			shop_face.setShowXY(SysDef.SCREEN_W / 2, SysDef.SCREEN_H / 2);
//		}
//		//动画图片
//		if(res_shop == null)
//		{
//			res_shop = ResManager.getRes(resID, false);
//			if(res_shop != null)
//			{
//				shop_face.setSpInfo(res_shop, resID/* , false */);
//			}
//		}
//		// 背景图片
//		if(back == null)
//		{
//			back = SystemAPI.createImage(Res.pngPath + "back.png");
//		}
//		// 动画
//		g.setClip(0, 0, SysDef.SCREEN_W, SysDef.SCREEN_H);
//		int w_number = SysDef.SCREEN_W/16;
//		int h_number = SysDef.SCREEN_H/16;
//		for(int i=0;i<w_number;i++)
//		{
//			for(int j=0;j<h_number;j++)
//			{
//				g.drawImage(back, i*16, j*16, 0);
//			}
//		}
//		shop_face.draw(g);
//		shop_face.autoRunSprite();
//	}
//	/**
//	 * @绘制进度条
//	 * @param g
//	 * @param res
//	 * @param x
//	 * @param y
//	 * @param w
//	 * @param num
//	 * @param maxnum
//	 * @param is
//	 */
//	public static void drawRectInfo(Graphics g,int res,int x,int y,int w,int num,int maxnum,boolean is,boolean isFocus)
//	{
//		if(maxnum == 0)
//			maxnum = 10;
//		int h = 15;
//		int clipw = w*num/maxnum;
//		/*g.setColor(0xA68E34);
//		Draw.drawRect(g, x-3, y, w+3, h, 10, 10, false);*/
//    	if(isFocus)
//		{
//			Draw.drawChoosePanel(g, x, y, w, h);
//		}
//		else
//		{
//			Draw.drawCommonPanel(g, x, y, w, h,UIManager.COLOR_CHOSE_1);
//		}
////		Draw.drawRect(g, x-2, y, w+2, h, 10, 10, true);
//        if(is)
//        {
//        	g.setColor(res);
//        	g.fillRoundRect( x+1, y+1, clipw-1, h-1,2,2);       
//        } 
////		if(is)
////		{
////			g.setClip(x-2, y, clipw, h);
////			for(int i = 0;i < w;i++)
////				Res.draw(g, res, x+i*1, y+2, 1, 0, 0, 0, Graphics.LEFT|Graphics.TOP);
////			g.setClip(0, 0, getWidth(), getHeight());
////		}
//	}
//	public static void drawMoney(Graphics g,long num,int x,int y,byte type,boolean showG)
//	{
//		String money ="";
//		 if(num>100000000)//亿
//		{
//			long num2 =  (num%100000000)/1000000;
//			money =String.valueOf(num/100000000)  + (num2>0 ? (","+(num2>10?String.valueOf(num2):"0"+num2)):(""));
//			Draw.drawNum(g, UseResListNew.NUM, money, x, y, 0, type,4,showG);			
//		}
//		else if(num>10000)//万
//		{
//			long num2 = (num%10000)/100;
//			money =String.valueOf(num/10000)+ (num2>0 ? (","+(num2>10?String.valueOf(num2):"0"+num2)):(""));
//			Draw.drawNum(g, UseResListNew.NUM, money, x, y, 0, type,3,showG);
//		}else
//		{
//			money = ""+num;
//			Draw.drawNum(g, UseResListNew.NUM, money, x, y, 0, type,2,showG);
//		}
//	}
//	public static int getContentWidht(String text)
//	{
//		if(text==null)
//		{
//			return 0;
//		}
//		
//		char[] chars = text.toCharArray();
//		
//		int width = 3;
//		
//		for(int i = 0;i < chars.length;i++)
//		{
//			if(chars[i] == '\n')
//			{
//				continue;
//			}
//			else if(chars[i] == '\\')
//			{
//				if(i + 1 < chars.length)
//				{
//					if(chars[i + 1] == 'n')
//					{
//						i++;
//						continue;
//					}
//				}
//			}
//			
//			int faceId = -1;
//			
//			if(chars[i] == '/')
//			{
//				if(i + 2 < chars.length)
//				{
//					String tmp = new String(chars, i + 1, 2);
//					
//					for(int j = 0;j < UIManager.face.length;j++)
//					{
//						if(tmp.equals(UIManager.face[j]))
//						{
//							faceId = j;
//							i += 2;
//							break;
//						}
//					}
//					
//					boolean jumpOut = false;
//					
//					for(int j = 0;j < UIManager.colorTable.length;j++)
//					{
//						if(tmp.equals(UIManager.colorTable[j]))
//						{
//							i += 2;
//							jumpOut = true;
//							break;
//						}
//					}
//					
//					if(jumpOut)
//					{
//						continue;
//					}
//				}
//			}
//			
//			int w = 0;
//			
//			if(faceId == -1)
//			{
//				w = UIManager.font.charWidth(chars[i]);
//			}
//			else
//			{
//				w = UIManager.FACE_SIZE;//表情图片宽度
//			}
//			width += w;
//		}
//		return width;
//	}
}
