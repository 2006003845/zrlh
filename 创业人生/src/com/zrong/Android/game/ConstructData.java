package com.zrong.Android.game;





import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;



import android.util.Log;

import com.zrong.Android.Util.SystemAPI;
 

public class ConstructData {
	/**
	 * 获取重连信息
	 * 
	 * @return
	 */
	public static byte[] getReConnection() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = null;
		try {
			dos = new DataOutputStream(baos);
			dos.writeInt(GameData.key1);
			dos.writeInt(GameData.key2);
			return baos.toByteArray();
		} catch (Exception e) {
			return null;
		} finally {
			SystemAPI.closeOutputStream(baos, dos);
		}
	}
	
	public static byte[] getXMLRequest(String[] key, String[] value) {
		try {

			StringBuffer buffer = new StringBuffer();
			buffer
					.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?><request>");
			if (key != null) {
				for (int i = 0; i < key.length; i++) {
					buffer.append("<").append(key[i]).append(">").append(
							value[i]).append("</").append(key[i]).append(">");
				}
			}
			buffer.append("</request>");
			Log.i("Log", buffer+"");
			String s = buffer.toString();
			// #ifdef Debug
			// # System.out.println(s);
			// #endif
			buffer.delete(0, buffer.length());
			byte[] data = s.getBytes("UTF-8");
			return data;

		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 注册192.168.0.75:7011 (str keyid权限) str 用户名 str 密码
	 */
	public static byte[] getAuthLogin(String keyid, String usName,
			String paswd, short version, String userId,String platform) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = null;
		try {
			dos = new DataOutputStream(baos);
			dos.writeUTF(keyid);
			dos.writeUTF(usName);
			dos.writeUTF(paswd);
			dos.writeShort(version);
			dos.writeUTF(userId);
			dos.writeUTF("android");
			//dos.writeUTF(platform);
			dos.writeByte(Byte.parseByte(platform));
			// #ifdef Debug
			// # Debug.println("version="+version);
			// # Debug.println("userId="+userId);
			// #endif
			return baos.toByteArray();
		} catch (Exception e) {
			return null;
		} finally {
			SystemAPI.closeOutputStream(baos, dos);
		}
	}
	
	 
	
	/**
	 * str 公司
	 */
	public static byte[] getCpName(String cpName) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = null;
		try {
			dos = new DataOutputStream(baos);

			dos.writeUTF(cpName);
			
			return baos.toByteArray();
		} catch (Exception e) {
			return null;
		} finally {
			SystemAPI.closeOutputStream(baos, dos);
		}
	}
	
	/**
	 * 获取地图信息
	 * 
	 * @param cityX
	 *            获取的地图的中心点
	 * @param cityY
	 * @param type
	 *            0不需要模板1需要模板
	 * @param scale
	 *            规模 例如，如果是7就是7x7范围内的地图信息
	 * @return
	 */
	public static byte[] getMapAroundShop(short mapId, short cityX,
			short cityY, byte type, byte scale) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = null;
		try {
			dos = new DataOutputStream(baos);
			dos.writeShort(mapId);
			dos.writeShort(cityX);
			dos.writeShort(cityY);
			dos.writeByte(type);
			dos.writeByte(scale);
			return baos.toByteArray();
		} catch (Exception e) {
			return null;
		} finally {
			SystemAPI.closeOutputStream(baos, dos);
		}
	}
	
	/**
	 * 创建店铺
	 * @param name
	 * @param scale
	 * @param trade
	 * @param mapId
	 * @param x
	 * @param y
	 * @param land
	 * @return
	 */
	public static byte[] getCreateShop(String name, byte scale, byte trade,
			short mapId, short x, short y, byte land) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = null;
		try {
			dos = new DataOutputStream(baos);
			dos.writeUTF(name);
			dos.writeByte(scale);
			dos.writeByte(trade);
			dos.writeShort(mapId);
			dos.writeShort(x);
			dos.writeShort(y);
			dos.writeByte(land);
			return baos.toByteArray();
		} catch (Exception e) {
			return null;
		} finally {
			SystemAPI.closeOutputStream(baos, dos);
		}
	}
	
	/**
	 * 二次确认响应包上行
	 */
	public static byte[] getTwoSureMsg(int id, byte result) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = null;
		try {
			dos = new DataOutputStream(baos);
			dos.writeInt(id);
			dos.writeByte(result);
			return baos.toByteArray();
		} catch (Exception e) {
			return null;
		} finally {
			SystemAPI.closeOutputStream(baos, dos);
		}
	}
	
	/**
	 * 加载商场道具列表信息 请求
	 * 
	 */
	public static byte[] getPropsMallListType(byte type) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = null;
		try {
			dos = new DataOutputStream(baos);

			dos.writeByte(type);

			return baos.toByteArray();
		} catch (Exception e) {
			return null;
		} finally {
			SystemAPI.closeOutputStream(baos, dos);
		}
	}
	
	/**
	 * 购买商品
	 */
	public static byte[] getBuyMallInfo(long id, byte type, byte count) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = null;
		try {
			dos = new DataOutputStream(baos);
			dos.writeLong(id);
			dos.writeByte(type);
			dos.writeByte(count);
			//#ifdef Debug
			//System.out.println("购买物品的id ="+id+",type="+type+",count ="+count);
			//#endif
			return baos.toByteArray();
		} catch (Exception e) {
			return null;
		} finally {
			SystemAPI.closeOutputStream(baos, dos);
		}
	}
	
	public static byte[] getStaffSkillLearn(byte type, short skillId,
			long employeeId) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = null;

		try {
			dos = new DataOutputStream(baos);

			dos.writeByte(type);
			dos.writeShort(skillId);
			dos.writeLong(employeeId);

			return baos.toByteArray();
		} catch (Exception e) {
			return null;
		} finally {
			SystemAPI.closeOutputStream(baos, dos);
		}
	}
	
	/**
	 * 员工操作请求
	 * 
	 * @param opType
	 *            操作类型 操作类型:0安排工作;1解除工作;2解聘
	 * @param departId
	 *            部门id 仅当opType==0是才发送
	 * @param departType
	 *            部门类型 仅当opType==0是才发送
	 * @param staffId
	 *            员工的id
	 * @return
	 */
	public static byte[] getStaffOPReq(byte opType, long departId,
			byte departType, long[] staffId) {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = null;
		try {
			dos = new DataOutputStream(baos);
			dos.writeByte(opType);
			if (opType == 0) {
				dos.writeLong(departId);
				dos.writeByte(departType);
			}
			if (staffId != null) {
				dos.writeByte(staffId.length);
				for (int i = 0; i < staffId.length; i++) {
					dos.writeLong(staffId[i]);
				}
			}
			return baos.toByteArray();
		} catch (Exception e) {
			return null;
		} finally {
			SystemAPI.closeOutputStream(baos, dos);
		}
	}

	/**
	 * 
	 * @param scope
	 * @param size
	 * @param staffId
	 *            发送开会信息
	 * @return
	 */
	public static byte[] getMeetMsg(byte scope, long staffId[]) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = null;
		try {
			dos = new DataOutputStream(baos);
			dos.writeByte(scope);
			dos.writeByte(staffId.length);
			for (int i = 0; i < staffId.length; i++)
				dos.writeLong(staffId[i]);
			return baos.toByteArray();
		} catch (Exception e) {
			return null;
		} finally {
			SystemAPI.closeOutputStream(baos, dos);
		}
	}

	/**
	 * @员工续约请求
	 * @param size
	 * @param staffId
	 * @return
	 */
	public static byte[] getContract(long staffId[]) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = null;
		try {
			dos = new DataOutputStream(baos);
			dos.writeByte(staffId.length);
			for (int i = 0; i < staffId.length; i++)
				dos.writeLong(staffId[i]);
			return baos.toByteArray();
		} catch (Exception e) {
			return null;
		} finally {
			SystemAPI.closeOutputStream(baos, dos);
		}
	}

	public static byte[] getPropaganda(byte type, long staffId[]) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = null;
		try {
			dos = new DataOutputStream(baos);

			dos.writeByte(type);
			dos.writeByte(staffId.length);

			for (int i = 0; i < staffId.length; i++) {
				dos.writeLong(staffId[i]);
			}

			return baos.toByteArray();
		} catch (Exception e) {
			return null;
		} finally {
			SystemAPI.closeOutputStream(baos, dos);
		}
	}

	public static byte[] getTraining(byte type, long staffId[]) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = null;

		try {
			dos = new DataOutputStream(baos);

			dos.writeByte(type);
			dos.writeByte(staffId.length);

			for (int i = 0; i < staffId.length; i++) {
				dos.writeLong(staffId[i]);
			}

			return baos.toByteArray();
		} catch (Exception e) {
			return null;
		} finally {
			SystemAPI.closeOutputStream(baos, dos);
		}
	}
	
	/** 沟通 */
	public static byte[] getCommunication(byte type, long employeeIds[]) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = null;
		try {
			dos = new DataOutputStream(baos);

			dos.writeByte(type);
			dos.writeByte(employeeIds.length);

			for (int i = 0; i < employeeIds.length; i++) {
				dos.writeLong(employeeIds[i]);
			}

			return baos.toByteArray();
		} catch (Exception e) {
			return null;
		} finally {
			SystemAPI.closeOutputStream(baos, dos);
		}
	}

	public static byte[] getCommonWell(byte type, long employeeIds[]) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = null;
		try {
			dos = new DataOutputStream(baos);

			dos.writeByte(type);
			dos.writeByte(employeeIds.length);

			for (int i = 0; i < employeeIds.length; i++) {
				dos.writeLong(employeeIds[i]);
			}

			return baos.toByteArray();

		} catch (Exception e) {
			return null;
		} finally {
			SystemAPI.closeOutputStream(baos, dos);
		}
	}

	/** 解雇员工 */
	public static byte[] unEmployStaffBatch(long departmentIds[]) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = null;

		try {
			dos = new DataOutputStream(baos);
			dos.writeByte(2);
			dos.writeByte(departmentIds.length);

			for (int i = 0; i < departmentIds.length; i++) {
				dos.writeLong(departmentIds[i]);
			}

			return baos.toByteArray();
		} catch (Exception e) {
			return null;
		} finally {
			SystemAPI.closeOutputStream(baos, dos);
		}
	}
	
	/**
	 * 批量雇佣员工
	 * 
	 * @param scope
	 *            0全公司1部门2店铺3建筑
	 * @param departmentId
	 * @return
	 */
	public static byte[] getEmployStaffBatch(byte scope, long[] departmentId) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = null;
		try {
			dos = new DataOutputStream(baos);
			dos.writeByte(scope);
			int size = departmentId.length;
			dos.writeByte((byte) size);
			for (int i = 0; i < size; i++) {
				dos.writeLong(departmentId[i]);
			}
			return baos.toByteArray();
		} catch (Exception e) {
			return null;
		} finally {
			SystemAPI.closeOutputStream(baos, dos);
		}
	}
	
	public static byte[] getShopRP(long shopId[], short actionId) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = null;
		try {
			dos = new DataOutputStream(baos);
			dos.writeShort(actionId);
			dos.writeByte(shopId.length);
			for (int i = 0; i < shopId.length; i++) {
				dos.writeLong(shopId[i]);
			}

			return baos.toByteArray();
		} catch (Exception e) {
			return null;
		} finally {
			SystemAPI.closeOutputStream(baos, dos);
		}
	}

	public static byte[] getLevelUpBatch(long[] shopId) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = null;
		try {
			dos = new DataOutputStream(baos);

			dos.writeByte(shopId.length);
			for (int i = 0; i < shopId.length; i++)
				dos.writeLong(shopId[i]);
			return baos.toByteArray();
		} catch (Exception e) {
			return null;
		} finally {
			SystemAPI.closeOutputStream(baos, dos);
		}
	}

	/**
	 * 
	 * @param size
	 *            巡视请求
	 * @param shopId
	 * @param 1店铺2部门
	 * @return
	 */
	public static byte[] getTour_req(long shopId[], byte type) 
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = null;
		try {
			dos = new DataOutputStream(baos);
			dos.writeByte(type);
			dos.writeByte(shopId.length);
			for (int i = 0; i < shopId.length; i++)
				dos.writeLong(shopId[i]);
			return baos.toByteArray();
		} catch (Exception e) {
			return null;
		} finally {
			SystemAPI.closeOutputStream(baos, dos);
		}
	}
	
	public static byte[] getPurchaseGoods(long shopId,byte numType,byte transportType){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = null;
		try {
			dos = new DataOutputStream(baos);
			dos.writeLong(shopId);
			dos.writeByte(numType);
			dos.writeByte(transportType);
			return baos.toByteArray();
		} catch (Exception e) {
			return null;
		} finally {
			SystemAPI.closeOutputStream(baos, dos);
		}
		
	}
	
	/**
	 * 批量采购数据封装
	 * @param numType
	 * @param shopId
	 * @return
	 */
	public static byte[] getPurchaseGoodsBatch(byte numType, long shopId[]) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = null;
		try {
			dos = new DataOutputStream(baos);

			dos.writeByte(numType);
			dos.writeByte(shopId.length);
			for (int i = 0; i < shopId.length; i++) {
				dos.writeLong(shopId[i]);
			}
			return baos.toByteArray();
		} catch (Exception e) {
			return null;
		} finally {
			SystemAPI.closeOutputStream(baos, dos);
		}
	}
	
	public static byte[] Staff_Appoint_Req(byte type, long depId, byte depType,
			long ids[]) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = null;

		try {
			dos = new DataOutputStream(baos);

			dos.writeByte(type);

			if (type == 0) {
				dos.writeLong(depId);
				dos.writeByte(depType);
			}

			dos.writeByte(ids.length);

			for (int i = 0; i < ids.length; i++) {
				dos.writeLong(ids[i]);
			}

			return baos.toByteArray();
		} catch (Exception e) {
			return null;
		} finally {
			SystemAPI.closeOutputStream(baos, dos);
		}
	}
	
	/**
	 * @@param 员工职务任命请求
	 * @param id
	 *            变更职务员工id
	 * @param duty
	 *            任命职务: 0普通职员或店员;1助理或店长;2经理;3总监;4首席
	 * @return
	 */
	public static byte[] getStaffDutyChange(long id, byte duty) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = null;
		try {
			dos = new DataOutputStream(baos);
			dos.writeLong(id);
			dos.writeByte(duty);
			return baos.toByteArray();
		} catch (Exception e) {
			return null;
		} finally {
			SystemAPI.closeOutputStream(baos, dos);
		}
	}
	
	/**
	 * 招揽好友或竞争者店铺列表的请求包
	 * @param trade行业类型
	 * @param mapId地图id
	 * @param begin起始列表
	 * @param count店铺数量
	 * @param playerId好友或竞争者的id
	 * @return
	 */
	public static byte[] getAvaiRecruit(byte type,long playerId,byte trade, short mapId, long begin,
			byte count) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = null;
		try {
			dos = new DataOutputStream(baos);
			dos.writeByte(type);
			dos.writeLong(playerId);
			dos.writeByte(trade);
			dos.writeShort(mapId);
			dos.writeLong(begin);
			dos.writeByte(count);
			
			return baos.toByteArray();
		} catch (Exception e) {
			return null;
		} finally {
			SystemAPI.closeOutputStream(baos, dos);
		}
	}
	
	
	public static byte[] getShop_Recruit_Req(long srcShopId, long desShopId,
			long empId, short mapId) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = null;
		try {
			dos = new DataOutputStream(baos);
			dos.writeLong(srcShopId);
			dos.writeLong(desShopId);
			dos.writeLong(empId);
			dos.writeShort(mapId);
			return baos.toByteArray();
		} catch (Exception e) {
			return null;
		} finally {
			SystemAPI.closeOutputStream(baos, dos);
		}
	}
	
	 
	
	/**
	 * 获取升级信息
	 * 
	 * @param type
	 *            0公司升级 1部门升级 2店铺升级
	 * @param id
	 *            公司 部门 或店铺id
	 * @return
	 */
	public static byte[] getLevelUPInfo(byte type, long id) {
		// type = 2;
		// id = GameData.corporation.shop[0].id;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = null;
		try {
			dos = new DataOutputStream(baos);
			dos.writeByte(type);
			dos.writeLong(id);
			return baos.toByteArray();
		} catch (Exception e) {
			return null;
		} finally {
			SystemAPI.closeOutputStream(baos, dos);
		}
	}
	
	/**
	 *使用道具
	 */
	public static byte[] useProps(byte ontype, long iteml, byte targetTypeId,
			long targetld, short count) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = null;
		try {
			// #ifdef Debug
			// #
			// System.out.println("ontype ="+ontype+" iteml ="+iteml+" count ="+count);
			// #endif
			dos = new DataOutputStream(baos);
			switch (ontype) {
			case 1:
				dos.writeByte(ontype);
				dos.writeLong(iteml);
				dos.writeByte(targetTypeId);
				dos.writeShort(1);
				dos.writeLong(targetld);
				Log.v("yz", "1="+ontype+",2="+iteml+",3="+targetTypeId+",4=1"+",5="+targetld);
				break;
			case 2:
				dos.writeByte(ontype);
				dos.writeLong(iteml);
				dos.writeShort(count);
				break;
			case 3:
				dos.writeByte(ontype);
				break;
			}

			return baos.toByteArray();
		} catch (Exception e) {
			return null;
		} finally {
			SystemAPI.closeOutputStream(baos, dos);
		}
	}
	
	/**
	 * @任务详情获取请求
	 * @param id
	 * @return
	 */
	public static byte[] getTaskData(int id) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = null;

		try {
			dos = new DataOutputStream(baos);
			dos.writeInt(id);

			return baos.toByteArray();

		} catch (Exception e) {
			return null;
		} finally {
			SystemAPI.closeOutputStream(baos, dos);
		}
	}

	/**
	 * @任务操作请求
	 * @param id
	 * @return
	 */
	public static byte[] getTaskOpreqData(int id, byte opCode) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = null;

		try {
			dos = new DataOutputStream(baos);
			dos.writeInt(id);
			dos.writeByte(opCode);
			return baos.toByteArray();

		} catch (Exception e) {
			return null;
		} finally {
			SystemAPI.closeOutputStream(baos, dos);
		}
	}
	
	/**
	 * 回答问题--答案
	 * @param id
	 * @param opCode
	 * @return
	 */
	public static byte[] getAnswerQuesOpreqData(int id, byte opCode) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = null;

		try {
			dos = new DataOutputStream(baos);
			dos.writeInt(id);
			dos.writeByte(opCode);
			return baos.toByteArray();

		} catch (Exception e) {
			return null;
		} finally {
			SystemAPI.closeOutputStream(baos, dos);
		}
	}
	
	/**
	 * @显示下一题
	 * @param id
	 * @return
	 */
	public static byte[] getShowNextQuesOpreqData(int id) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = null;

		try {
			dos = new DataOutputStream(baos);
			dos.writeInt(id);
			return baos.toByteArray();

		} catch (Exception e) {
			return null;
		} finally {
			SystemAPI.closeOutputStream(baos, dos);
		}
	}
	
	public static byte[] get_Relationship_operation(long id, byte type) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = null;

		try {
			dos = new DataOutputStream(baos);
			dos.writeLong(id);
			dos.writeByte(type);
			return baos.toByteArray();

		} catch (Exception e) {
			return null;
		} finally {
			SystemAPI.closeOutputStream(baos, dos);
		}
	}
	
	public static byte[] getShowMailListData(byte type) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = null;

		try {
			dos = new DataOutputStream(baos);
			dos.writeByte(type);

			return baos.toByteArray();

		} catch (Exception e) {
			return null;
		} finally {
			SystemAPI.closeOutputStream(baos, dos);
		}
	}
	
	public static byte[] getMail(long id, String name, String title, String content,
			long money, byte itemSize) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = null;

		try {
			dos = new DataOutputStream(baos);
			dos.writeLong(id);//ft_7.05发邮件添加一个参数
			dos.writeUTF(name);
			dos.writeUTF(title);
			dos.writeUTF(content);
			dos.writeLong(money);
			dos.writeByte(itemSize);

			return baos.toByteArray();

		} catch (Exception e) {
			return null;
		} finally {
			SystemAPI.closeOutputStream(baos, dos);
		}
	}
	
	public static byte[] getChatData(byte type, String content, long id) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = null;

		try {
			dos = new DataOutputStream(baos);
			dos.writeByte(type);
			dos.writeUTF(content);

			if (type == 1)// 私聊
			{
				dos.writeLong(id);
			}

			return baos.toByteArray();

		} catch (Exception e) {
			return null;
		} finally {
			SystemAPI.closeOutputStream(baos, dos);
		}
	}
	
	public static byte[] getMailOptionData(long id, byte mailType, byte optype)// zhangxiaoqing
	// (long
	// id,
	// byte
	// type)
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = null;
		
		try {
		dos = new DataOutputStream(baos);
		dos.writeLong(id);
		dos.write(mailType);// zhangxiaoqing add
		dos.writeByte(optype);// zhangxiaoqing type
		
		return baos.toByteArray();
		
		} catch (Exception e) {
		return null;
		} finally {
		SystemAPI.closeOutputStream(baos, dos);
		}
	}
	
	/**
	 * 
	 * @param 公益建筑创建
	 * @return
	 */
	public static byte[] getCreatePublicBuilding(String build_name,
			byte trade_id, short map_id, short map_x, short map_y, byte land_i,
			long staff_id) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = null;
		try {
			dos = new DataOutputStream(baos);
			dos.writeUTF(build_name);// 公益建筑名字
			dos.writeByte(trade_id);// 公益建筑行业id
			dos.writeShort(map_id);// 地图id
			dos.writeShort(map_x);// 地图坐标x
			dos.writeShort(map_y);// 地图坐标y
			dos.writeByte(land_i);// 地图基础地块
			dos.writeLong(staff_id);// 负责基础地块的员工id
			return baos.toByteArray();
		} catch (Exception e) {
			return null;
		} finally {
			SystemAPI.closeOutputStream(baos, dos);
		}
	}
	
	
	public static byte[] getSystemInfo(int info[]) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = null;
		try {
			dos = new DataOutputStream(baos);
			dos.writeByte(4);
			for (byte i = 0; i < 4; i++) {
				dos.writeByte(i);
				dos.writeByte((byte) info[i]);
				 
			}
			return baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			SystemAPI.closeOutputStream(baos, dos);
		}
	}
	
	/**
	 * opType 正常搜索 0正常，1被禁言人员
	 * type 搜索类型 1等级相近 2 名称
	 * name 名称
	 * */
	public static byte[] get_Search_User_Req(byte opType,byte type, String name) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = null;

		try {
			dos = new DataOutputStream(baos);
			dos.writeByte(opType);
			dos.writeByte(type);

			if (type == 2) {
				dos.writeUTF(name);
			}

			return baos.toByteArray();

		} catch (Exception e) {
			return null;
		} finally {
			SystemAPI.closeOutputStream(baos, dos);
		}
	}

	
	/**推广好友请求包*/
	public static byte[] getPromoterData(short id, byte opType) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = null;

		try {
			dos = new DataOutputStream(baos);
			dos.writeInt(id);
			dos.writeByte(opType);
			return baos.toByteArray();

		} catch (Exception e) {
			return null;
		} finally {
			SystemAPI.closeOutputStream(baos, dos);
		}
	}
	
	public static byte[] getCreatRoleTest(String usName, String address,
			byte sex, int resId, String tel,long recommendCode) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = null;
		try {
			dos = new DataOutputStream(baos);

			dos.writeUTF(usName);
			dos.writeUTF(address);
			dos.writeByte(sex);
			dos.writeInt(resId);
			dos.writeUTF(tel);
			dos.writeLong(recommendCode);
			return baos.toByteArray();
		} catch (Exception e) {
			return null;
		} finally {
			SystemAPI.closeOutputStream(baos, dos);
		}
	}

	//1 被招揽玩家驱逐招揽; 2 发起招揽玩家取消招揽; 3 被招揽玩家的好友帮忙驱逐
	public static byte[] getShop_UnRecruit_Req(byte type,long destShopId,
			long destShopBuildId) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = null;
		try {
			dos = new DataOutputStream(baos);
			dos.writeByte(type);
			dos.writeLong(destShopId);
			dos.writeLong(destShopBuildId);
			return baos.toByteArray();
		} catch (Exception e) {
			return null;
		} finally {
			SystemAPI.closeOutputStream(baos, dos);
		}
	}
	
	
	/**
	 * 查询员工请求
	 * @param opType 操作类型：1:人才市场 2全部
	 * @param staffname 员工名
	 * @param mastername 员工雇主名
	 * @param office 1：店铺  2：部门  3：公共建筑
	 * @param type 举例：咖啡店、财务部、水立方
	 * @param Level 级别：举例：主管（1）、总监（5）等
	 * @param size 发几条信息
	 * @param start 从第几条开始发消息
	 * @return
	 */
	public static byte[] SearchStaff_Req(byte opType,String staffname,String mastername,byte size,byte office[],byte type[],byte level[],int start,int num)
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = null;

		try {
			dos = new DataOutputStream(baos);
			dos.writeByte(opType);
			dos.writeUTF(staffname);
			dos.writeUTF(mastername);
			dos.writeByte(size);
			for(int i=0;i<size;i++)
			{
				dos.writeByte(office[i]);
				dos.writeByte(type[i]);
				dos.writeByte(level[i]);
			}
			dos.writeInt(start);
			dos.writeInt(num);
			return baos.toByteArray();

		} catch (Exception e) {
			return null;
		} finally {
			SystemAPI.closeOutputStream(baos, dos);
		}	
	}
	
	/**挖人请求包*/
	public static byte[] get_earchPeople(long id,int price)
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = null;

		try {
			dos = new DataOutputStream(baos);
			dos.writeLong(id);
			dos.writeInt(price);
			return baos.toByteArray();

		} catch (Exception e) {
			return null;
		} finally {
			SystemAPI.closeOutputStream(baos, dos);
		}
	}
	
	/**人才市场出售、取消出售、购买员工请求
	 * optype 操作类型：1:出售 2取消出售  3：购买
	 * id 操作的员工ID
	 * price 购买员工出价，仅在opType=3有效
	 */
	public static byte[] REQ_TradeStaff(byte optype,long id,int price)
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = null;

		try {
			dos = new DataOutputStream(baos);
			dos.writeByte(optype);
			dos.writeLong(id);
			dos.writeInt(price);
			return baos.toByteArray();

		} catch (Exception e) {
			return null;
		} finally {
			SystemAPI.closeOutputStream(baos, dos);
		}		
	}
	
	/**
	 * 
	 * @param uid
	 * @return
	 */
	public static byte[] getLogout(long uid) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = null;
		try {
			dos = new DataOutputStream(baos);
			dos.writeLong(uid);
			return baos.toByteArray();
		} catch (Exception e) {
			return null;
		} finally {
			SystemAPI.closeOutputStream(baos, dos);
		}
	}
	
	/**店铺修改卡上行包*/   //jiangxujin add
	public static byte[] getTwoSureInputMsg(int id, byte result,String[] key,String[] value) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = null;
		try {
			dos = new DataOutputStream(baos);
			dos.writeInt(id);
			dos.writeByte(result);
			for(int i=0;i<result;i++)
			{
				dos.writeUTF(key[i]);
				dos.writeUTF(value[i]);
			}
			return baos.toByteArray();
		} catch (Exception e) {
			return null;
		} finally {
			SystemAPI.closeOutputStream(baos, dos);
		}
	}
	/**商会创建条件信息*/   //jiangxujin add
	public static byte[] getCOfC_Create_Info_ReqData(byte value) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = null;

		try {
			dos = new DataOutputStream(baos);
			dos.writeByte(value);
			return baos.toByteArray();

		} catch (Exception e) {
			return null;
		} finally {
			SystemAPI.closeOutputStream(baos, dos);
		}
	}
	
	/**
	 * @author 商会创建条件请求
	 * @param name
	 *            商会姓名
	 * @param id
	 *            商会图标
	 * @return
	 */
	//jiangxujin add
	public static byte[] getCOfC_Create_Do_ReqData(String name, int id) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = null;

		try {
			dos = new DataOutputStream(baos);
			dos.writeUTF(name);
			dos.writeInt(id);
			return baos.toByteArray();

		} catch (Exception e) {
			return null;
		} finally {
			SystemAPI.closeOutputStream(baos, dos);
		}
	}
	
	/**
	 * @author 商会修改公告请求
	 * @param affiche
	 * @return
	 */
	//jiangxujin add
	public static byte[] getCOfC_Change_Affiche_ReqData(String affiche) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = null;

		try {
			
			dos = new DataOutputStream(baos);
			dos.writeUTF(affiche);

			return baos.toByteArray();

		} catch (Exception e) {
			return null;
		} finally {
			SystemAPI.closeOutputStream(baos, dos);
		}
	}
	
	/**
	 * @author 商会列表信息请求（商会搜索请求）
	 * @param name
	 *            商会名字
	 * @param minLv
	 *            商会最小级别：不限制则为0
	 * @param maxLv
	 *            商会最大级别：不限制则为0
	 * @param minNum
	 *            商会最小人数：不限制则为0
	 * @param maxNum
	 *            商会最大人数：不限制则为0
	 * @return
	 */
	//jiangxujin add
	public static byte[] getCOfC_List_Info_ReqData(String name, short minLv,
			short maxLv, short minNum, short maxNum) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = null;

		try {
			dos = new DataOutputStream(baos);
			dos.writeUTF(name);
			dos.writeShort(minLv);
			dos.writeShort(maxLv);
			dos.writeShort(minNum);
			dos.writeShort(maxNum);
			return baos.toByteArray();

		} catch (Exception e) {
			return null;
		} finally {
			SystemAPI.closeOutputStream(baos, dos);
		}
	}
	//jiangxujin add
	public static byte[] getMemberListRequestData(String name, short minLv,
			short maxLv, short minNum, short maxNum) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = null;

		try {
			dos = new DataOutputStream(baos);
			dos.writeUTF(name);
			dos.writeShort(minLv);
			dos.writeShort(maxLv);
			dos.writeShort(minNum);
			dos.writeShort(maxNum);

			return baos.toByteArray();

		} catch (Exception e) {
			return null;
		} finally {
			SystemAPI.closeOutputStream(baos, dos);
		}
	}

	/**
	 * @author 申请或邀请加入商会
	 * @param type
	 *            请求类型：0邀请加入；1申请加入
	 * @param userId
	 *            （被邀请/申请）人物id
	 * @param userName
	 *            （被邀请/申请）人物名字
	 * @param cofcId
	 *            商会id
	 * @return
	 */
	//jiangxujin add (0x107b)
	public static byte[] getCOfC_Join_Req(byte type, long userId,
			String userName, long cofcId) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = null;

		try {
			dos = new DataOutputStream(baos);
			dos.writeByte(type);
			dos.writeLong(userId);
			dos.writeUTF(userName);
			dos.writeLong(cofcId);
			return baos.toByteArray();

		} catch (Exception e) {
			return null;
		} finally {
			SystemAPI.closeOutputStream(baos, dos);
		}
	}

	/**
	 * @author 商会申请或邀请的确
	 * @param id
	 *            请求的id
	 * @param cofc_id
	 *            商会id
	 * @return 确认结果：0同意，1拒绝
	 */
	//jiangxujin add (0x107c)
	public static byte[] getCofC_Request_Resp(long id, long cofcId, byte result) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = null;

		try {
			dos = new DataOutputStream(baos);
			dos.writeLong(id);
			dos.writeLong(cofcId);
			dos.writeByte(result);
			return baos.toByteArray();

		} catch (Exception e) {
			return null;
		} finally {
			SystemAPI.closeOutputStream(baos, dos);
		}
	}
	

	/**
	 * @author 商会成员职务变
	 * @param memerId
	 *            变更职务的会员id
	 * @param duty
	 *            要变更的职务：-1离开商会；0会员；98副会长；99会长
	 * @return
	 */
	//jiangxujin add (0x107d)
	public static byte[] getCOfC_Change_Duty_Req(long memerId, byte duty) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = null;

		try {
			dos = new DataOutputStream(baos);
			dos.writeLong(memerId);
			dos.writeByte(duty);
			return baos.toByteArray();

		} catch (Exception e) {
			return null;
		} finally {
			SystemAPI.closeOutputStream(baos, dos);
		}
	}
	
	/**
	 * @author 商会捐款请求
	 * @param optype
	 *            操作类别:1为设定捐款2捐款
	 * @param fees
	 *            捐款数
	 * @return
	 */
	//jiangxujin add (0x107e)
	public static byte[] getCOfC_Fees_Req(byte optype, long fees) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = null;

		try {
			dos = new DataOutputStream(baos);
			dos.writeByte(optype);
			dos.writeLong(fees);
			return baos.toByteArray();

		} catch (Exception e) {
			return null;
		} finally {
			SystemAPI.closeOutputStream(baos, dos);
		}
	}
	
	/**
	 * @author 客户端数据请求
	 * @param req_type
	 *            请求数据类型（0-地图信息;1角色信息;2公司信息;
	 *            3部门信息;4店铺信息;5员工信息;6店铺模板信息,7商会联合宣传模板, 8商会联合宣传信息列表）
	 * @return
	 */
	//jiangxujin add(0x101d)
	public static byte[] ClientDatas_Req(byte req_type) {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = null;
		try {
			dos = new DataOutputStream(baos);
			dos.writeByte(req_type);
			return baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		} finally {
			SystemAPI.closeOutputStream(baos, dos);
		}

	}
	
	/**
	 * @author 发起商会联合宣传请求
	 * @param 联合宣传类型
	 *            ：1（街道宣传）; 2(报纸宣传); 3(电视宣传);
	 * @return
	 */
	//jiangxujin add (0x1088)
	public static byte[] Initiated_JointAdvocacy_Req(byte type) {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = null;
		try {
			dos = new DataOutputStream(baos);
			dos.writeByte(type);
			return baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		} finally {
			SystemAPI.closeOutputStream(baos, dos);
		}

	}
	
	
	/**
	 * @author 参与商会联合宣传
	 * @param 联合宣传的id
	 * @return
	 */
	public static byte[] Join_JointAdvocacy_Req(long advocacyId,int type)
	{

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = null;
		try
		{
			dos = new DataOutputStream(baos);
			dos.writeByte(type);
			if(type == 0)
			dos.writeLong(advocacyId);
			return baos.toByteArray();
		}catch(Exception e)
		{
			e.printStackTrace();
			return null;
			
		}
		finally
		{
			SystemAPI.closeOutputStream(baos, dos);
		}
	
	}


	/**
	 * 彩票购买
	 */

	public static byte[] getLotteryTicketBuy(String num, short count) {
		// #ifdef Debug
		// #
		// System.out.println("-----------------------------购买的期号是 ="+num+" 数量是="+count);
		// #endif
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = null;
		try {
			dos = new DataOutputStream(baos);

			dos.writeUTF(num);
			dos.writeShort(count);

			return baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			SystemAPI.closeOutputStream(baos, dos);
		}
	}
	
	/**
	 * 彩票兑换
	 */

	public static byte[] getLotteryTicketMoney(byte count) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = null;
		try {
			dos = new DataOutputStream(baos);

			// dos.writeUTF(num);
			dos.writeShort(count);

			return baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			SystemAPI.closeOutputStream(baos, dos);
		}
	}
	/**
	 * 彩票列表请求
	 */
	public static byte[] getLotteryTicketAskList(byte count) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = null;
		try {
			dos = new DataOutputStream(baos);
			
			dos.writeShort(count);

			return baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			SystemAPI.closeOutputStream(baos, dos);
		}
	}
	/**
	 * (0x1101)开奖结果请求
	 *
	 * */
/*	public static void exec_LotteryTicket_info(DataInputStream dis) {
		try {
				String name = dis.readUTF();
				if (!Tutorial.isInTutorial()) {
					final ConformDialogEx cd = new ConformDialogEx("彩票中奖信息", name);
					cd.addActionListener(new ActionListener() {
						public void actionPerformed(Object sender) {
							if (sender == cd.buttonLeft) {
								Connection.sendMessage(GameProtocol.LOTTERY_TACKET_ASK_LIST, Connection.getLotteryTicketAskList((byte)2));
								cd.deactive();
							} else if (sender == cd.buttonRight) {
								cd.deactive();
							}
						}
					});
					cd.setBounds(25, (SysDef.SCREEN_H - 180) >> 1,
							SysDef.SCREEN_W - 50, 180);
					cd.cover();
					GameRun.addWidget(cd, true);
				}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("错误-------------加载彩票中奖信息");
		}
	}*/
	
	
	
	/**
	 * 封装创业学堂操作请求的数据
	 * @param type操作请求码
	 * @return byte[]
	 */
	public static byte[] getVentureSchoolInfo(byte type) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = null;
		try {
			dos = new DataOutputStream(baos);
			dos.writeByte(type);
			return baos.toByteArray();
		} catch (Exception e) {
			return null;
		} finally {
			SystemAPI.closeOutputStream(baos, dos);
		}
	}
	


	/**策划部店铺列表请求*/
	public static byte[]getShopAskList(long userid)
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = null;
		try {
			dos = new DataOutputStream(baos);
			
			dos.writeLong(userid);

			return baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			SystemAPI.closeOutputStream(baos, dos);
		}
		}
		/**策划部行为请求包*/ 
		public static byte[]getPlanningAction(byte optype,long targetUid,byte size,long[] targetid)
		{
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			DataOutputStream dos = null;
			try {
				dos = new DataOutputStream(baos);
				
				dos.writeByte(optype);
				dos.writeLong(targetUid);
				dos.writeByte(size);
				
				for(int i=0;i<size;i++)
				{
					dos.writeLong(targetid[i]);
				}

				return baos.toByteArray();
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			} finally {
				SystemAPI.closeOutputStream(baos, dos);
			}		
	}
		/**
		 * @策划部操作请求
		 * @param id
		 * @return 
		 */
		public static byte[] PlanningActionData(long id)
		{
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			DataOutputStream dos = null;
			
			try
			{
				dos=new DataOutputStream(baos);
				 
				dos.writeLong(id);   
				
				return baos.toByteArray();
				
			}catch(Exception e)
			{
				return null;
			}
			finally
			{
				SystemAPI.closeOutputStream(baos, dos);
			}
		}
		/**
		 * @博士托管获取请求
		 * @param id
		 * @return 
		 */
		public static byte[] getDoctorShop(long id)
		{
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			DataOutputStream dos = null;
			
			try
			{
				dos=new DataOutputStream(baos);
				 
				dos.writeLong(id); 
				
				return baos.toByteArray();
				
			}catch(Exception e)
			{
				return null;
			}
			finally
			{
				SystemAPI.closeOutputStream(baos, dos);
			}
		}
		/**
		 * @博士托管获取请求
		 * @param id
		 * @return 
		 */
		public static byte[] getDoctorTrustData(byte type,short id,short skillId,byte level,
				byte targetType,byte size,long targetID[])
		{
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			DataOutputStream dos = null;
			
			try
			{
				dos=new DataOutputStream(baos);
				Log.v("yz", "doctor type ="+type);
				dos.writeByte(type);
				dos.writeShort(id);
				dos.writeShort(skillId);
				dos.writeByte(level);
				dos.writeByte(targetType);
				dos.writeByte(size);
				for(int i=0;i<size;i++)
				{ 
					dos.writeLong(targetID[i]);
				}
				
				return baos.toByteArray();
				
			}catch(Exception e)
			{
				return null;
			}
			finally
			{
				SystemAPI.closeOutputStream(baos, dos);
			}
		}
		/**
		 * @博士任务详情获取请求
		 * @param id
		 * @return 
		 */
		public static byte[] getDoctorTaskData(byte id)
		{
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			DataOutputStream dos = null;
			
			try
			{
				dos=new DataOutputStream(baos);
				dos.writeByte(id);
				
				return baos.toByteArray();
				
			}catch(Exception e)
			{
				return null;
			}
			finally
			{
				SystemAPI.closeOutputStream(baos, dos);
			}
		}
		
		public static byte[] get_User_Detail_Req(long id)
		{
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			DataOutputStream dos = null;
			
			try
			{
				dos=new DataOutputStream(baos);
				dos.writeLong(id);
				
				return baos.toByteArray();
				
			}catch(Exception e)
			{
				return null;
			}
			finally
			{
				SystemAPI.closeOutputStream(baos, dos);
			}
		}
		
		
}
