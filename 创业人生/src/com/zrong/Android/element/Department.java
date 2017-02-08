package com.zrong.Android.element;
/**
 * 部门
 * @author Administrator
 *
 */
public class Department extends Branch{
	
	//0普通职员或店员;1主管或店长;2助理;3经理;4总监;5首席
	/**等级*/
	public byte level;
//	/**部门人数*/
//	public short staffNum;
	/**职位 */
	public byte duty;
	/**对员工级别要求*/
	public short  staff_level;
	/**这5个id不推荐使用 */
	/**部门主管id*/
	public long manager_oneId;
	/**部门助理id*/
	public long manager_twoId;
	/**部门经理id*/
	public long manager_threeId;
	/**部门总监id*/
	public long manager_fourId;
	/**部门首席id*/
	public long manager_five;
	/** ================/
	 
	/**士气 */
	public short morale;
	/**最大士气 */
	public short maxMorale;
	/**能力 */
	public short ability;
	/**最大能力 */
	public short maxAbility;
	/**忠诚 */
	public short faith;
	/**最大忠诚 */
	public short maxFaith;
	/**经验 */
	public short exp;
	/**最大经验 */
	public short maxExp;
	/**素质id*/
	public byte qualityId;
	/***/
	public byte qualityLv;
	
	public Strategy[] strategties; 
	
	
	
	
	
	
	
	
}
