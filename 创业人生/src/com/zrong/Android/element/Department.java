package com.zrong.Android.element;
/**
 * ����
 * @author Administrator
 *
 */
public class Department extends Branch{
	
	//0��ְͨԱ���Ա;1���ܻ�곤;2����;3����;4�ܼ�;5��ϯ
	/**�ȼ�*/
	public byte level;
//	/**��������*/
//	public short staffNum;
	/**ְλ */
	public byte duty;
	/**��Ա������Ҫ��*/
	public short  staff_level;
	/**��5��id���Ƽ�ʹ�� */
	/**��������id*/
	public long manager_oneId;
	/**��������id*/
	public long manager_twoId;
	/**���ž���id*/
	public long manager_threeId;
	/**�����ܼ�id*/
	public long manager_fourId;
	/**������ϯid*/
	public long manager_five;
	/** ================/
	 
	/**ʿ�� */
	public short morale;
	/**���ʿ�� */
	public short maxMorale;
	/**���� */
	public short ability;
	/**������� */
	public short maxAbility;
	/**�ҳ� */
	public short faith;
	/**����ҳ� */
	public short maxFaith;
	/**���� */
	public short exp;
	/**����� */
	public short maxExp;
	/**����id*/
	public byte qualityId;
	/***/
	public byte qualityLv;
	
	public Strategy[] strategties; 
	
	
	
	
	
	
	
	
}
