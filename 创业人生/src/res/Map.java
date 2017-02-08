package res;

 

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import com.zrong.Android.Util.SystemAPI;
import com.zrong.Android.game.GameData;
import com.zrong.Android.game.GameDefinition;
import com.zrong.Android.game.Script;
 

import res.MapInfo;
import res.Res;
import res.ResManager;
import res.SpriteInfo;
 

public class Map {	
	public int id;
	public MapInfo info; 
	//缓冲区左上角相对Layer的偏移
	private int bakOffX,bakOffY;
	private int offx,offy;
	//缓冲区当前偏移量
	private int _offx,_offy;
	private int _bakOffX,_bakOffY;
	//显示区相对缓冲区的偏移量, 
	private int offx2,offy2;
	private Image buf;//图片
	private Graphics g;//缓冲图上的画笔
	private int width=544;
	private int height=352;
	private static final int w2=544;
	private static final int h2=352;
	//屏幕和缓冲的地图的差值
	private int offw2;
	private int offh2;
	private int bufDrawX;
	private int bufDrawY;
	
	private boolean isBufReset=true;
	public  static int mapInfo_W;
	public static int mapInfo_H;
	
	public Map(MapInfo info)
	{
		 this.info=info;
		 mapInfo_W=info.width;
		 mapInfo_H=info.height;
		 isBufReset=true;
		 updata(GameData.mapX,GameData.mapY);
	}
	
	
	
	public void run()
	{

//		if(!GameRun.isdrawBuildAction)
//		{
//		if(UIManager.isKeyPressed(UIManager.KEY_UP))
//		{
//			GameData.ChangeCityXY(0,1);
//		}
//		else if(UIManager.isKeyPressed(UIManager.KEY_DOWN))
//		{
//			GameData.ChangeCityXY(0,-1);
//		}
//		else if(UIManager.isKeyPressed(UIManager.KEY_LEFT))
//		{
//			GameData.ChangeCityXY(-1,0);
//		}
//		else if(UIManager.isKeyPressed(UIManager.KEY_RIGHT))
//		{
//			GameData.ChangeCityXY(1,0);
//		}
//			if(UIManager.peekIsPointerPressed(0, 0, SysDef.SCREEN_W, SysDef.SCREEN_H))
//			{
//
//				if(GameData.xyArray != null)
//				{
//					for(int i = 0;i < GameData.xyArray.length;i++)
//					{
//						if(GameData.xyArray[i] != null)
//						{
//							if(SystemAPI.runIsPoint(GameData.xyArray[i][0] - GameData.mapX - 68, GameData.xyArray[i][1] - GameData.mapY - 32, 136, 64))
//							{
//								GameData.ChangeCityXY((i % GameData.ARRAY_LENTH) - GameData.ARRAY_LENTH / 2 - GameData.cityX + GameData.originalCityX, i / GameData.ARRAY_LENTH - GameData.ARRAY_LENTH / 2 - GameData.cityY + GameData.originalCityY);
//							}
//						}
//					}
//				}
//			}
//			updata(GameData.mapX, GameData.mapY);
//			if(Math.abs(GameData.cityX - GameData.originalCityX) > 2 || Math.abs(GameData.cityY - GameData.originalCityY) > 2)
//			{
//				GameData.originalCityX = GameData.cityX;
//				GameData.originalCityY = GameData.cityY;
//				Connection.sendMessage(GameProtocol.CONNECTION_SEND_MAPAROUND_SHOP, Connection.getMapAroundShop(GameData.mapIds[GameData.mapIdIndex], GameData.cityX, GameData.cityY, (byte)1, (byte)(GameData.ARRAY_LENTH/2)));
//			}
//		}else
//		{
//			UIManager.setEvent(null);
//		}
	}
	
	public void updata(int offx,int offy)
	{	
		if(Math.abs(offx-this.offx)>w2||Math.abs(offy-this.offy)>h2)
		{
			isBufReset=true;
		}
		if(isBufReset)
		{
			isBufReset=false;
			int bw=SystemAPI.divEnter(width, w2)*w2+w2;
			int bh=SystemAPI.divEnter(height, h2)*h2+h2;
			buf=Image.createImage(bw,bh);//得到一张缓冲图
			offw2=buf.getWidth()-width;
			offh2=buf.getHeight()-height;
			g=buf.getGraphics();//得到缓冲图上的画笔
			this.bakOffX=this.offx=offx;
			this.bakOffY=this.offy=offy;
			if(offx>0)
			{
				this._bakOffX=this._offx=offx/w2*w2;
			}
			else
			{
				this._bakOffX=this._offx=(offx/w2-(offx%w2==0?0:1))*w2;
			}
			
			if(offy>0)
			{
				this._bakOffY=this._offy=offy/h2*h2;
			}
			else
			{
				this._bakOffY=this._offy=(offy/h2-(offy%h2==0?0:1))*h2;
			}
			this.offx2=(this.offx-this._offx)%w2;
			this.offy2=(this.offy-this._offy)%h2;
			bufDrawX=0;
			bufDrawX=0;
			info.paint(g, 0,0,_offx, _offy, buf.getWidth(), buf.getHeight());
		}
		this.offx=offx;
		this.offy=offy;
		int dx=this.offx-bakOffX;
		int dy=this.offy-bakOffY;
		if(dx!=0)
		{
			if(offx2+dx>w2)//超出缓冲区的范围了,需要更新缓冲区
			{
				_offx+=w2;
				offx2=offx2+dx-w2;
			}
			else if(offx2+dx<0)
			{
				_offx-=w2;
				offx2=offx2+dx+w2;
			}
			else//没有超出缓冲区,就只移动相对偏移
			{
				offx2+=dx;
			}
		}
		
		if(dy!=0)
		{
			if(offy2+dy>h2)
			{
				_offy+=h2;
				offy2=offy2+dy-h2;
			}
			else if(offy2+dy<0)
			{
				_offy-=h2;
				offy2=offy2+dy+h2;
			}
			else
			{
				offy2+=dy;
			}
		}
		Buffer(); 
		bakOffX=this.offx;
		bakOffY=this.offy;
	}
	
	 
	
	private void Buffer()
	{
		int bufW=buf.getWidth();
		int bufH=buf.getHeight();
		
		int drawOffX;
		int drawOffY;
		 if(_bakOffX!=_offx)
		 {
			//#ifdef Debug
			 System.out.println("bufW="+bufW+",bufH="+bufH+",_bakOffX="+_bakOffX+"_bakOffY="+_bakOffY);
			 //#endif
			 if(_offx>_bakOffX)//向右移动
			 {
				 drawOffX=this._bakOffX+bufW;
				 drawOffY=this._bakOffY;
				 info.paint(g, bufDrawX, bufDrawY, drawOffX, drawOffY, w2, bufH-bufDrawY);
				 info.paint(g, bufDrawX, 0, drawOffX, drawOffY+(bufH-bufDrawY), w2, bufDrawY);
				 bufDrawX=(bufDrawX+w2)%bufW;
			 }
			 else//向左移动 
			 {
				 bufDrawX=(bufDrawX-w2);
				 if(bufDrawX<0)bufDrawX+=bufW;
				 drawOffX=this._bakOffX-w2;
				 drawOffY=this._bakOffY;
				info.paint(g, bufDrawX, bufDrawY, drawOffX, drawOffY, w2, bufH-bufDrawY);
				info.paint(g, bufDrawX, 0, drawOffX, drawOffY+(bufH-bufDrawY), w2, bufDrawY);
			 }
			 _bakOffX=_offx;
		 }
		 if(_bakOffY!=_offy)
		 {
			//#ifdef Debug
			 System.out.println("bufW="+bufW+",bufH="+bufH+",_bakOffX="+_bakOffX+"_bakOffY="+_bakOffY);
			 //#endif
			 if(_offy>_bakOffY)//想下移动
			 {
				 drawOffX=this._bakOffX;
				 drawOffY=this._bakOffY+bufH;
				 info.paint(g, bufDrawX, bufDrawY, drawOffX, drawOffY, bufW-bufDrawX, h2);
				 info.paint(g, 0, bufDrawY, drawOffX+(bufW-bufDrawX), drawOffY, bufDrawX, h2);
				 bufDrawY=(bufDrawY+h2)%bufH;
			 }
			 else//向上移动
			 {
				 bufDrawY=(bufDrawY-h2);
				 if(bufDrawY<0)bufDrawY+=bufH;
				 drawOffX=this._bakOffX;
				 drawOffY=this._bakOffY-h2;
				 info.paint(g, bufDrawX, bufDrawY, drawOffX, drawOffY, bufW-bufDrawX, h2);
				 info.paint(g, 0, bufDrawY, drawOffX+(bufW-bufDrawX), drawOffY, bufDrawX, h2);
			 }
			 _bakOffY=_offy;
		 }
	}
	 
	public final void paint(Graphics g,int x,int y)
	{
		//下面开始把把缓冲画到真正的图上
		drawRegion(g,buf,bufDrawX+offx2,bufDrawY+offy2,buf.getWidth()-bufDrawX-offx2,buf.getHeight()-bufDrawY-offy2,0,x,y,0);
		drawRegion(g,buf,0,bufDrawY+offy2,width-(buf.getWidth()-bufDrawX-offx2),buf.getHeight()-bufDrawY-offy2,0,x+width,y,Graphics.RIGHT|Graphics.TOP);
		drawRegion(g,buf,bufDrawX+offx2,0,buf.getWidth()-bufDrawX-offx2,height-(buf.getHeight()-bufDrawY-offy2),0,x,y+height,Graphics.BOTTOM|Graphics.LEFT);
		drawRegion(g,buf,0,0,width-(buf.getWidth()-bufDrawX-offx2), height-(buf.getHeight()-bufDrawY-offy2),0,x+width,y+height,Graphics.RIGHT|Graphics.BOTTOM);
	}
	
	private void drawRegion(Graphics g,Image img,int x_src,int y_src,int width,int height,int transform,int x_dest,int y_dest,int anchor)
	{
		if(width<=0 || height<=0) return;
		if(width>this.width)
			width=this.width;
		if(height>this.height)
			height=this.height;
		g.drawRegion(img,x_src,y_src,width,height,transform,x_dest,y_dest,anchor);
	}
	/**
	 * 绘制活动对象层和建筑层一起参与排序
	 * @param g
	 * @param x
	 * @param y
	 */
	public final void drawUp(Graphics g,int x,int y)
	{
		Res res = null;
	}
	
	
	
	public static Map initMap(short id)
	{
		Map map = Script.readMap(id);
		return map;
	}
	/**
	 * 绘制地表层
	 * @param drawId
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public void drawUp(Graphics g,int drawId,int x,int y,int w,int h)
	{
		if(info.actsRes[drawId]==null)
		{
			info.actsRes[drawId]=ResManager.getRes(info.actsResId[drawId], true);
		}
		long nowTime = System.currentTimeMillis();
		Res res=info.actsRes[drawId];
		int nx=x/mapInfo_W;
		int ny=y/mapInfo_H;
		x=x%mapInfo_W;
		y=y%mapInfo_H;
		int numX=(x+w)/mapInfo_W+((x+w)%mapInfo_W==0?0:1);
		int numY=(y+h)/mapInfo_H+((y+h)%mapInfo_H==0?0:1);
		for(int i=0;i<numX;i++)
		{
			for(int j=0;j<numY;j++)
			{
				 
					if(res!=null)
					{
						if(SystemAPI.inField(info.actsX[drawId]+i*mapInfo_W, info.actsY[drawId]+j*mapInfo_H, 5, 5, x, y, w, h)!=SystemAPI.FIELD_OUT)//在屏幕内
						{
							if(res instanceof SpriteInfo)
							{
								int nextFrame = Res.autoRunSprite(res, info.serieShow[drawId],info.frameId[drawId], info.lastChangeTimeUpFace[drawId], nowTime);
								if(nextFrame >= 0)
								{
									info.frameId[drawId] = (byte)nextFrame;
									info.lastChangeTimeUpFace[drawId] = nowTime;
								}
							}
							res.draw(g, info.actsX[drawId]+(nx+i)*mapInfo_W - GameData.mapX, info.actsY[drawId]+(ny+j)*mapInfo_H - GameData.mapY, info.colorIndex[drawId], info.serieShow[drawId], info.frameId[drawId], info.flips[drawId],0);
						}
					}
					 
				 
				 
			}
		}
	}



	public void setBufReset(boolean isBufReset) {
		this.isBufReset = isBufReset;
	}
}
