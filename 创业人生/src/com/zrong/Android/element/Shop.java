package com.zrong.Android.element;

 

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Vector;

import android.util.Log;

import com.zrong.Android.game.GameData;

/**
 * ����
 * @author rain
 *
 */
public class Shop extends Building
{

	 
	/**���̵ȼ�*/
	public byte		level;
	/**С�͡����͡�����*/
	public byte		scale;
	/**���̼ӳ�*/
	public int gain;
 
	/**�ɳ���*/
	public int		increasingRate;
	/**�곤id */
	public long managerId;
	 
	/** ��������*/
	public int flowGain;
	
	public int flowGain_canvass;//��������
	public int flowGain_canvassed;//������������
	public byte competitor;//��ޱ�־ 0��ʾ���Ǿ������֣�1��ʾ�Ǿ�������
	public int competitorNum;//��޴�����������Ǿ������֣��������û������
	/**����ȼ� */
	public byte goodsLv;
	/**�������*/
	public int goodsNum;
	
	/**���̻������ֵ */
	public int maxGoodsNum;
	/** ����ʣ��ʱ��*/
	public short stockTime;
	/**����ʿ�� */
	public int morale;
	/**�������ʿ�� */
	public int maxMorale;
	/**����Ʒ�� */
	public int quality;
	/**����Ʒ������ */
	public int maxQuality;
	/**����֪���� */
	public int popularity;
	/**������֪���� */
	public int maxPopularity;
	/**���̳ɳ� */
	public int grown;
	/** */
	public long incomeBase;
	
	public long incomeToday;
	
	public long incomeYesterday;
	
	public short map_id;

	public byte address;
	
	public long buildingId;
	
	public String shopKeeper;
	
	/**
	 * ����������ʶ 1����״̬ 2�������� 3���������� 4�� �������У�Ҳ������
	 */
	public byte recruitStat;//
	
	public long curTime;//�յ�ʣ��ʱ��ʱ��curTime
	
	public int lastTime;//ʣ���ʱ��
	
	/**
	 * ��������
	 * @return
	 */
	public boolean isRecruiting()
	{
		if(this.recruitStat == 2 || this.recruitStat ==4)
		{
			return true;
		}
		return false;
	}
	 
	/**
	 * ������
	 * @return
	 */
	public boolean isRecruited()
	{
		if(this.recruitStat == 3 || this.recruitStat ==4)
		{
			return true;
		}
		return false;
	}
	public static Shop readShop(DataInputStream dis)
	{
		Shop shop=new Shop();
		try {
			shop.id=dis.readLong();
			shop.simpleName=dis.readUTF();
			shop.level=dis.readByte();
			shop.managerId=dis.readLong();
			shop.staffNum=dis.readShort();
			shop.maxStaffNum=dis.readShort();
			shop.flowGain=dis.readInt();
			shop.scale=dis.readByte();
			shop.goodsLv=dis.readByte();
			shop.goodsNum=dis.readInt();
			shop.maxGoodsNum=dis.readInt();

			shop.stockTime=dis.readShort();
			shop.gain=dis.readInt();
			shop.morale=dis.readInt();
			shop.maxMorale=dis.readInt();
			shop.quality=dis.readInt();
			shop.maxQuality=dis.readInt();
			shop.popularity=dis.readInt();
			shop.maxPopularity=dis.readInt();
			shop.grown=dis.readInt();
			shop.incomeBase=dis.readLong();
			shop.incomeToday=dis.readLong();
			shop.incomeYesterday=dis.readLong();
			shop.map_id=dis.readShort();
			shop.cityX=dis.readShort();
			shop.cityY=dis.readShort();
			shop.address=dis.readByte();
			shop.buildingId = dis.readLong();
			shop.trade_id = dis.readByte();
			shop.recruitStat = dis.readByte();
			shop.lastTime = dis.readInt();
			shop.curTime = System.currentTimeMillis();
//			System.out.println("shop.recruitStat ��ĸ== " + shop.recruitStat); 
			shop.name=GameData.getProfessionName(shop.simpleName,shop.trade_id, shop.scale);
			shop.flowGain_canvass = dis.readInt();
			//#ifdef Debug
//			System.out.println("shop.flowGain_canvass ����������== " + shop.flowGain_canvass);
			//#endif
			shop.flowGain_canvassed = dis.readInt();
			//#ifdef Debug
//			System.out.println("shop.flowGain_canvassed ����������== " + shop.flowGain_canvassed);
			//#endif
			//��ӵ��̹�����ҵ����� �ֶ�
			shop.usename=dis.readUTF();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return shop;
	}
	/*
	 * ����汾
	 * public static Shop readShop(DataInputStream dis)
	{
		Shop shop=new Shop();
		Log.i("Log2", "Into ---readShop");
		try {
			Log.i("Log2", "StartToReadData========");
			shop.type = 4;
			Log.i("Log2", "1"+shop.type);
			shop.id=dis.readLong();
			Log.i("Log2", "2"+shop.id);
			shop.simpleName=dis.readUTF();
			Log.i("Log2", "3"+shop.simpleName);
			shop.level=dis.readByte();
			Log.i("Log2", "4"+shop.level);
			shop.managerId=dis.readLong();
			Log.i("Log2", "5"+shop.managerId);
			shop.staffNum=dis.readShort();
			Log.i("Log2", "6"+shop.staffNum);
			shop.maxStaffNum=dis.readShort();
			Log.i("Log2", "7"+shop.maxStaffNum);
			shop.flowGain=dis.readInt();
			Log.i("Log2", "8"+shop.flowGain);
			shop.scale=dis.readByte();
			Log.i("Log2", "9"+shop.scale);
			shop.goodsLv=dis.readByte();
			Log.i("Log2", "10"+shop.goodsLv);
			shop.goodsNum=dis.readInt();
			Log.i("Log2", "11"+shop.goodsNum);
			shop.maxGoodsNum=dis.readInt();
			Log.i("Log2", "12"+shop.maxGoodsNum);

			shop.stockTime=dis.readShort();
			Log.i("Log2", "13"+shop.stockTime);
			shop.gain=dis.readInt();
			Log.i("Log2", "14"+shop.gain);
			shop.morale=dis.readInt();
			Log.i("Log2", "15"+shop.morale);
			shop.maxMorale=dis.readInt();
			Log.i("Log2", "16"+shop.maxMorale);
			shop.quality=dis.readInt();
			Log.i("Log2", "17"+shop.quality);
			shop.maxQuality=dis.readInt();
			Log.i("Log2", "18"+shop.maxQuality);
			shop.popularity=dis.readInt();
			Log.i("Log2", "19"+shop.popularity);
			shop.maxPopularity=dis.readInt();
			Log.i("Log2", "20"+shop.maxPopularity);
			shop.grown=dis.readInt();
			Log.i("Log2", "21"+shop.grown);
			shop.incomeBase=dis.readLong();
			Log.i("Log2", "22"+shop.incomeBase);
			shop.incomeToday=dis.readLong();
			Log.i("Log2", "23"+shop.incomeToday);
			shop.incomeYesterday=dis.readLong();
			Log.i("Log2", "24"+shop.incomeYesterday);
			shop.map_id=dis.readShort();
			Log.i("Log2", "25"+shop.map_id);
			shop.cityX=dis.readShort();
			Log.i("Log2", "26"+shop.cityX);
			shop.cityY=dis.readShort();
			Log.i("Log2", "27"+shop.cityY);
			shop.address=dis.readByte();
			Log.i("Log2", "28"+shop.address);
			shop.buildingId = dis.readLong();
			Log.i("Log2", "29"+shop.buildingId);
			shop.trade_id = dis.readByte();
			Log.i("Log2", "30"+shop.trade_id);
			shop.recruitStat = dis.readByte();
			Log.i("Log2", "31"+shop.recruitStat);
			shop.lastTime = dis.readInt();
			Log.i("Log2", "32"+shop.lastTime);
			shop.curTime = System.currentTimeMillis();
			Log.i("Log2", "33"+shop.curTime);
			//#ifdef Debug
			System.out.println("shop.recruitStat ��ĸ== " + shop.recruitStat);
			//#endif
			shop.name=GameData.getProfessionName(shop.simpleName,shop.trade_id, shop.scale);
			shop.flowGain_canvass = dis.readInt();
			Log.i("Log2", "34"+shop.flowGain_canvass);
			shop.flowGain_canvassed = dis.readInt();
			Log.i("Log2", "35"+shop.flowGain_canvassed);
			Log.i("Log2", "ReadShopData---Over");
		} catch (IOException e) {
			e.printStackTrace();
			Log.i("Log2", "readShop====�����쳣");
		}
		Log.i("Log2", "Shop.BuildingId"+shop.buildingId);
		return shop;
	}*/
	
	public Employee[] getEmployees()
	{
		Vector v = new Vector();
		
		
		return null;
	}
}
