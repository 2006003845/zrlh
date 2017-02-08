package res.mapLayer;

import java.util.*;

import javax.microedition.lcdui.*;

import com.zrong.Android.Util.SystemAPI;

import res.*;


import java.io.*;

public class TileLayer extends Res
{
	public static final byte TYPE_MATRIX = 0; //默认矩阵类型
	public static final byte TYPE_RANDOM = 1; //忽略矩阵设置，图块随机排列

	/** 图层数据类型 */
	public byte dataType = TYPE_MATRIX;

	/** 图元图片 */
	public Png basePic;
	public short basePicID;
	public int indexColor;

	/** 图块大小 */
	int tileWidth = 16;
	int tileHeight = 16;
	/**地图的宽和高 */
	public int mapWidth;
	public int mapHeight;

	/** 引用矩阵 */
	public byte[][] tiles;

	public TileLayer()
	{
	}

	public int getTitleWidth ()
	{
		return this.tileWidth;
	}

	public int getTitleHeight ()
	{
		return this.tileHeight;
	}

	/** 返回块行数 */
	public int getRows ()
	{
		return tiles.length;
	}

	/** 返回块列数 */
	public int getCols ()
	{
		return tiles[0].length;
	}

	public void load (DataInputStream dis) throws IOException
	{
		basePicID = dis.readShort();//图片id
		//变色索引(常规图片为-1)
		int index = Res.colorResIndex(basePicID);
		if(index != -1)
		{
			indexColor = Res.colorIndex[index];
		}
		else			
		{
			indexColor = -1;
		}
		if(basePicID >= 0)
		{
			basePic = Res.getPng(basePicID);
		}
		else
		{
			basePic = null;
		}
		int rows = dis.readShort();
		int cols = dis.readShort();
		tileWidth = dis.readShort();
		tileHeight = dis.readShort();
		dataType = dis.readByte();
		if(dataType == TYPE_RANDOM)
			return;

		tiles = new byte[rows][cols];
		for(int i = 0;i < rows;i++)
		{
			for(int j = 0;j < cols;j++)
			{
				tiles[i][j] = (byte)dis.read();
			}
		}
		mapWidth=tiles[0].length*tileWidth;
		mapHeight=tiles.length*tileHeight;
	}

	
	public void destroy ()
	{
	}

	public void draw (Graphics g, int x, int y, int flip, Vector params)
	{

	}

	public void paint (Graphics g, int drawX, int drawY, int offX, int offY, int w, int h)
	{
		if(basePic == null)
		{
			basePic = ResManager.getPng(basePicID, false);
			if(basePic == null)
				return;
		}
		int picTileWidth = basePic.getWidth() / tileWidth + ((basePic.getWidth() % tileWidth) == 0 ? 0 : 1);
		int tileStartX = offX / tileWidth;
		int tileStartY = offY / tileHeight;
		int tileEndX = tileStartX + SystemAPI.divEnter(w, tileWidth);
		int tileEndY = tileStartY + SystemAPI.divEnter(h, tileHeight);
//		if(tileStartX < 0)
//			tileStartX = 0;
//		if(tileStartY < 0)
//			tileStartY = 0;
//		if(tileEndX >= tiles[0].length)
//			tileEndX = tiles[0].length - 1;
//		if(tileEndY >= tiles.length)
//			tileEndY = tiles.length - 1;
		for(int i = tileStartY;i <= tileEndY;i++)
		{
			for(int j = tileStartX;j <=tileEndX;j++)
			{
				int tileDrawX = (j - tileStartX) * tileWidth + drawX - (offX % tileWidth);
				int tileDrawY = (i - tileStartY) * tileHeight + drawY - (offY % tileHeight);
				int clipX = (tileDrawX > drawX) ? tileDrawX : drawX;
				int clipY = (tileDrawY > drawY) ? tileDrawY : drawY;
				int clipW = (tileDrawX + tileWidth < drawX + w) ? tileWidth - (clipX - tileDrawX) : drawX + w - clipX;
				int clipH = (tileDrawY + tileHeight < drawY + h) ? tileHeight - (clipY - tileDrawY) : drawY + h - clipY;
				g.setClip(clipX, clipY, clipW, clipH);
				int off_x = (tiles[i%tiles.length][j%tiles[0].length] % picTileWidth) * tileWidth;
				int off_y = (tiles[i%tiles.length][j%tiles[0].length] / picTileWidth) * tileHeight;
				basePic.draw(g, tileDrawX - off_x, tileDrawY - off_y, indexColor, -1, 0, 0, 0);
			}
		}
		g.setClip(0, 0, Short.MAX_VALUE, Short.MAX_VALUE);
	}

	
	public void draw (Graphics g, int x, int y, int colorIndex, int index, int frameId, int flip, int anchor)
	{
		if(basePic == null)
			return;
		int maxIdx = basePic.getWidth() / tileWidth + ((basePic.getHeight() % tileHeight) == 0 ? 0 : 1);
		for(int i = 0;i < tiles.length;i++)
		{
			for(int j = 0;j < tiles[i].length;j++)
			{
				if(!Res.inField(x, y, j * tileWidth, i * tileHeight, tileWidth, tileHeight))
					continue;
				g.setClip(j * tileWidth - x, i * tileHeight - y, tileWidth, tileHeight);
				int off_x = (tiles[i][j] % maxIdx) * tileWidth;
				int off_y = (tiles[i][j] / maxIdx) * tileHeight;
				basePic.draw(g, j * tileWidth - off_x - x, i * tileHeight - off_y - y, 0, -1, 0, 0, anchor);
			}
		}
	}
}
