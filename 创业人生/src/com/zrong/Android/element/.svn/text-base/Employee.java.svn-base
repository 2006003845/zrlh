package com.zrong.Android.element;

import java.io.DataInputStream;
import java.io.IOException;

 
import android.util.Log;

import com.zrong.Android.Listener.DataChangeListener;
import com.zrong.Android.activity.MainActivity;
import com.zrong.Android.activity.R;

/**
 * 员工
 * @author rain
 *
 */
public class Employee
{
	public DataChangeListener listener;
	public static final String employee_dutyname = MainActivity.resources.getString(R.string.employee_dutyname);
	public static final String[] dutyName = employee_dutyname.split(",");
	//public static final String[] dutyName = new String[]{"职员/店员","主管/店长","助理","经理","总监","首席"}; 
	/**整个游戏世界中唯一的id*/
	public long 		id;
	 
	/** :0人事部;1市场部;2策划部;3财务部;4店铺;5建筑;6空闲*/
	public int type;
	/**头像索引*/
	public int		imageId;
	/**姓名*/
	public String 		name;
	
	public String companyName;
	/**性别*/
	public byte 	sex;
	/**职位 */
	public short duty;//0普通职员或店员;1主管或店长;2助理;3经理;4总监;5首席 
	/**年龄*/
	public byte 	age;
	/**薪水*/
	public int 		salary;
	/**合同剩余时间*/
	public long		compact;
	/**空闲，工作等等*/
	public byte		status;
	/**工种，所在部门*/
	public long 	department;
	/**部门名字 */
	public String departName;
	/**爱好 */
	public short hobby;
	/**人品*/
	public int		makeUp;
	/**等级*/
	public short		level;
	/**经验*/
	public int		experence;
	
	/**经验上线 */
	public int maxExperence;
	/**能力*/
	public int		ability;
	/**能力最大值 */
	public int maxAbility;
	/**忠诚*/
	public int		loyalty;
	
	/** 忠诚上线*/
	public int maxLoyalty;

	/**体力*/
	public int strength;
	/***/
	public int maxstrength;
	/**士气 */
	public int morale;
	/**技能 */
	public Skill[] skill;
	/**素质 */
	public Quality[] quality;
	/**雇主名字 */
	public String employerName;
	/**员工价格 */
	public int price;
	/**员工所在公司 */
	public String empCompany;	
	/**超级员工描述*/
	public String superInfo = "";
	/**愿望描述*/
	public String wishDesc = "";
	
	
	
	public void copy(Employee e)
	{
		if(e == null) 
			return;
		this.id = e.id;
		this.imageId = e.imageId;
		this.sex = e.sex;
		this.type = e.type;
		this.name = e.name;
		this.companyName=e.companyName;
		this.duty = e.duty;
		this.level = e.level;
		this.department = e.department;
		this.departName = e.departName;
		this.hobby = e.hobby ;
		this.salary = e.salary;
		this.compact = e.compact;
		this.ability = e.ability;
		this.maxAbility = e.maxAbility;
		this.loyalty = e.loyalty;
		this.maxLoyalty = e.maxLoyalty;
		this.experence = e.experence;
		this.maxExperence = e.maxExperence;
		this.morale = e.morale;
		this.makeUp = e.makeUp;
		this.strength = e.strength;
		this.maxstrength = e.maxstrength;
		this.employerName = e.employerName;
		this.price = e.price;
		this.empCompany = e.empCompany;
		if(e.skill != null)
		{
			this.skill=new Skill[e.skill.length];
			for(int i=0;i<e.skill.length;i++)
			{
				Skill sk=new Skill();
				sk.id = e.skill[i].id;
				sk.level = e.skill[i].level;
				sk.name = e.skill[i].name;
				this.skill[i]=sk;
			}
		}
		
		if(e.quality != null)
		{
			this.quality=new Quality[e.quality.length];
			for(int i=0;i<e.quality.length;i++)
			{
				Quality q = new Quality();
				q.id = e.quality[i].id;
				q.level = e.quality[i].level;
				q.name = e.quality[i].name;
				this.quality[i]=q;
			}
		}
		 
	}
	
	public static Employee readEmployee(DataInputStream dis) throws IOException
	{
		Employee emp=new Employee();
		emp.id=dis.readLong();
		emp.imageId=dis.readInt();
		emp.sex=dis.readByte();
		emp.type=dis.readByte();
		emp.name=dis.readUTF();
		emp.departName = dis.readUTF();
		emp.duty=dis.readShort();
		emp.level=dis.readShort();
		emp.department=dis.readLong();
		emp.departName=dis.readUTF();
		emp.hobby=dis.readShort();
		emp.salary=dis.readInt();
		emp.compact=dis.readLong();
		emp.ability=dis.readInt();
		emp.maxAbility=dis.readInt();
		emp.loyalty=dis.readInt();
		emp.maxLoyalty=dis.readInt();
		emp.experence=dis.readInt();
		emp.maxExperence=dis.readInt();
		emp.morale=dis.readInt();
		emp.makeUp=dis.readInt();
		emp.strength=dis.readInt();
		emp.maxstrength=dis.readInt();
		
		byte skillNum=dis.readByte();

		if(skillNum>=0)
		{
			emp.skill=new Skill[skillNum];
			for(int i=0;i<skillNum;i++)
			{
				Skill sk=new Skill();
				sk.id=dis.readShort();
				sk.level=dis.readByte();
				sk.name=dis.readUTF();
				emp.skill[i]=sk;
			}
		}
		
		byte qualityNum=dis.readByte();
		if(qualityNum>=0)
		{
			emp.quality=new Quality[qualityNum];
			
			for(int i=0;i<qualityNum;i++)
			{
				Quality q=new Quality();
				q.id=dis.readShort();
				q.level=dis.readByte();
				q.name=dis.readUTF();
				emp.quality[i]=q; 
				q = null;
			}
		}
		if(emp.sex == 2||emp.sex==3)
		{
			emp.superInfo = dis.readUTF();
			emp.wishDesc = dis.readUTF();
		}
		
		return emp;
	}
	
	
	public String getSkillName(int index,String defaultname)
	{
		String name =defaultname;
		
		if(skill != null)
		{
			if(index <skill.length&& index >= 0)
			{
				name = skill[index].name;
			}
		}
		
		return name;
	}
	
	public int getQualityLevel(long id,int defaultlevel)
	{
		int level = defaultlevel;
		
		if(quality != null)
		{
			for(int i = 0 ; i < quality.length; i++)
			{
				if(quality[i].id == id)
				{
					level = quality[i].level;
				}
			}
		}
		
		return level;
		
	}
	
	public void addDataChangeListener(DataChangeListener listener)
	{
		this.listener = listener;
	}
	
}
