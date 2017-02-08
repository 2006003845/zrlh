/**
 * 
 */
package res;

import java.io.DataInputStream;
import java.io.IOException;

import javax.microedition.lcdui.Graphics;

import android.util.Log;

import com.zrong.Android.View.FreshManLead;
import com.zrong.Android.View.MapView;
import com.zrong.Android.element.MapBuilding;
import com.zrong.Android.game.GameData;

import res.Res;
import res.ResManager;
import res.SpriteInfo;
import res.UseResListNew;

/**
 * <p>
 * Titile:BuildSprite
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright:Copyright(c)2010
 * </p>
 * <p>
 * Company:zrong
 * </p>
 * 
 * @author yangzheng
 * @version 1.0
 */
public class BuildingSprite {

	public static short offXY[] = {};
	public static final short[][] SmallGRID_OffXY = { {/* 1,39 */3, 32 },
			{/*-23,20*/-19, 21 }, { -41, 10 }, { 26, 21 }, { 3, 10 },
			{ -19, -1 }, { 47, 10 }, { 25, -1 }, { 3, -12 } };
	public MapBuilding mb;
	public byte showType = 0;// 0显示1不显示

	public void addDraw(int i) {
		if (showType == 0) {
			if (mb.res == null) {
				mb.res = ResManager.getRes(mb.resId, false);
			} else {
				MapView.addDraw((short) (i + MapView.Draw_BUILDING), mb.focusY,
						mb.focusX, mb.focusX - Res.getOffX(mb.res, 0),
						mb.focusY - Res.getOffY(mb.res, 0),
						Res.getWidth(mb.res, 0), Res.getHeight(mb.res, 0));
			}
		}
	}

	private int curFrame = 0;// 当前帧
	private int curSeries = 0;// 当前系列
	public long lastChangeTime = System.currentTimeMillis();// 上一次画的时间
	Res spInfo;// 动画播放的资源 可以是图片在不同位置播放成动画 也可以是精灵动画
	public boolean isShowNow = true;// 控制此动画是否立即显示 涉及到资源的同步异步载入

	/**
	 * 得到动画的下一帧 准备用nextCycFrame代替
	 * 
	 * @return int
	 */
	public int autoRunSprite() {
		long nowTime = System.currentTimeMillis();
		int t = Res.autoRunSprite(mb.res, curSeries, curFrame, lastChangeTime,
				nowTime);
		if (t >= 0) {
			if (curFrame != t) {
				curFrame = (byte) t;
			}
			lastChangeTime = nowTime;
			if (t == 0) {
				return 2;
			} else {
				return 1;
			}
		}
		return 0;
	}

	public int color = 0;// 要画的颜色
	public int trans = 0;// 翻转

	public void draw(Graphics g) {
		if (mb.res == null) {
			mb.res = ResManager.getRes(mb.resId, false);
		} else {
			if (mb.res instanceof SpriteInfo) {
				if (autoRunSprite() == 2)
					curFrame = 0;
				Res.draw(g, mb.res, mb.focusX - GameData.mapX, mb.focusY
						- GameData.mapY, curSeries, color, curFrame, 0, 0);
			} else {
				mb.res.draw(g, mb.focusX - GameData.mapX, mb.focusY
						- GameData.mapY, 0, 0, 0, 0, 0);

			}
			if (mb.scale == 1) {
				Draw.drawShadowString(g, mb.simpleName, mb.focusX
						- GameData.mapX, mb.focusY - GameData.mapY - 60,
						Graphics.TOP | Graphics.HCENTER, 0xa1fe01, 0);
			} else if (mb.scale == 2) {
				Draw.drawShadowString(g, mb.simpleName, mb.focusX
						- GameData.mapX - 15, mb.focusY - GameData.mapY - 80,
						Graphics.TOP | Graphics.HCENTER, 0xa1fe01, 0);
			} else if (mb.scale == 3) {
				Draw.drawShadowString(g, mb.simpleName, mb.focusX
						- GameData.mapX - 15, mb.focusY - GameData.mapY - 100,
						Graphics.TOP | Graphics.HCENTER, 0xa1fe01, 0);
			}
		}

		if (GameData.isFreshMan) {
			// 给银行绘制箭头
			if (FreshManLead.isBank && mb.trade_id == 115) {
				// Log.i("Log", "绘制箭头---银行");
				drawFreshLead(g, FreshManLead.caseId);
			}// 给工商局绘制箭头
			else if (FreshManLead.isCommerical && mb.trade_id == 114) {
				// Log.i("Log", "绘制箭头---工商局");
				drawFreshLead(g, FreshManLead.caseId);
			}// 给税务局绘制箭头
			else if (FreshManLead.isRevenue && mb.trade_id == 113) {
				// Log.i("Log", "绘制箭头---税务局");
				drawFreshLead(g, FreshManLead.caseId);
			} else if (FreshManLead.caseId == 8 && mb.isMyShop) {
				// Log.i("Log", "绘制箭头---我的店铺");
				drawFreshLead(g, FreshManLead.caseId);
			}else if(FreshManLead.caseId == 6){
//				drawFreshLead(g, FreshManLead.caseId);
			}

		}
	}

	Sprite lead;

	/**
	 * 新手引导绘制
	 * 
	 * @param g
	 */
	public void drawFreshLead(Graphics g, int caseID) {
		if (lead == null) {
			lead = new Sprite(1392, 0, 0, 0, 0, 0, false, true);
		}
		if (caseID == 0) {
			lead.setShowXY(mb.focusX - GameData.mapX, mb.focusY - GameData.mapY
					- 180);
		} else if (caseID == 1) {
			lead.setShowXY(mb.focusX - GameData.mapX, mb.focusY - GameData.mapY
					- 220);
		} else if (caseID == 2) {
			lead.setShowXY(mb.focusX - GameData.mapX, mb.focusY - GameData.mapY
					- 180);
		} else if (caseID == 8) {
			lead.setShowXY(mb.focusX - GameData.mapX-30, mb.focusY - GameData.mapY
					- 120);
		} else {
			lead.setShowXY(-1, -1);
		}
		lead.autoRunSprite(100);
		lead.draw(g);
	}

	public static Sprite diamond;
	public int step = 0;

	public boolean hasPoint(int x, int y) {
		boolean isPoint = false;
		int px, py, pw, ph;
		int strWidth = Draw.SPS.stringWidth(mb.simpleName);
		pw = strWidth;
		ph = Draw.FONT_HEIGHT;
		if (mb.scale == 1) {
			px = x - (strWidth >> 1);
			py = y - 60;
		} else if (mb.scale == 2) {
			px = x - 15 - (strWidth >> 1);
			py = y - 80;
		} else if (mb.scale == 3) {
			px = x - 15 - (strWidth >> 1);
			py = y - 100;
		} else {
			px = x - (strWidth >> 1);
			py = y - 60;
		}

		return isPoint;
	}

	public void draw(Graphics g, int x, int y, boolean focus) {
		if (mb.res == null) {
			mb.res = ResManager.getRes(mb.resId, false);
		} else {
			if (mb.res instanceof SpriteInfo) {
				if (autoRunSprite() == 2)
					curFrame = 0;
				Res.draw(g, mb.res, x, y, curSeries, color, curFrame, 0, 0);
			} else {
				Res.draw(g, mb.res, x, y, 0, 0, 0, 0, 0);
			}
		}

		int color = 0xffffff;
		if (focus)// 我的店铺
		{
			step += 1;
			if (step >= 0 && step < 5) {
				color = 0xa1fe01;
			} else if (step >= 5 && step < 10) {
				color = 0xffffff;
			} else {
				step = 0;
			}
		} else {
			color = 0xff6c00;
		}
		if (diamond == null)
			diamond = new Sprite(UseResListNew.IMAGE_596, 0, 0, 0, 0, 0, false,
					true);
		diamond.autoRunSprite();
		if (mb.scale == 1) {
			Draw.drawShadowString(g, mb.simpleName, x, y - 60, Graphics.TOP
					| Graphics.HCENTER, color, 0);
			if (focus) {
				diamond.setShowXY(x, y - 65);
				diamond.draw(g);
			}
		} else if (mb.scale == 2) {
			Draw.drawShadowString(g, mb.simpleName, x - 15, y - 80,
					Graphics.TOP | Graphics.HCENTER, color, 0);
			if (focus) {
				diamond.setShowXY(x - 15, y - 65);
				diamond.draw(g);
			}
		} else if (mb.scale == 3) {
			Draw.drawShadowString(g, mb.simpleName, x - 15, y - 100,
					Graphics.TOP | Graphics.HCENTER, color, 0);
			if (focus) {
				diamond.setShowXY(x - 15, y - 65);
				diamond.draw(g);
			}
		}

	}

	public static BuildingSprite readBuildSprite(DataInputStream dis) {
		try {
			BuildingSprite bs = null;
			bs = new BuildingSprite();
			bs.mb = new MapBuilding();

			bs.mb.id = dis.readLong();
			bs.mb.simpleName = dis.readUTF();
			bs.mb.resId = dis.readInt();
			bs.mb.startGrid = dis.readByte();
			bs.mb.cellCount = dis.readByte();
			bs.mb.cityX = dis.readShort();
			bs.mb.cityY = dis.readShort();
			bs.mb.trade_id = dis.readByte();
			bs.mb.type = dis.readByte();

			bs.mb.scale = dis.readByte();
			bs.mb.taskIcon = dis.readByte();
			bs.mb.flowGain = dis.readInt();
			bs.mb.playerId = dis.readLong();
			bs.mb.playerName = dis.readUTF();
			bs.mb.level = dis.readByte();
			bs.mb.isMyShop = GameData.isMyShop(bs.mb.id);
			bs.mb.name = GameData.getProfessionName(bs.mb.simpleName,
					bs.mb.trade_id, bs.mb.scale);
			return bs;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void setCityXY(short x, short y) {
		mb.cityX = x;
		mb.cityY = y;
	}

	public void setMapXY(short x, short y) {
		mb.focusX = (short) (x + SmallGRID_OffXY[mb.startGrid][0]);
		mb.focusY = (short) (y + SmallGRID_OffXY[mb.startGrid][1]);
		System.out.println("name=" + mb.name + ",focusX=" + mb.focusX
				+ ",focusY=" + mb.focusY + ",cityX=" + mb.cityX + ",cityY="
				+ mb.cityY);
	}
}
