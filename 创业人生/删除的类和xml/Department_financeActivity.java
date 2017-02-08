package com.zrong.Android.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zrong.Android.Listener.ActivityTransform;
import com.zrong.Android.Listener.DataChangeListener;
import com.zrong.Android.Util.Music;
import com.zrong.Android.element.Department;
import com.zrong.Android.game.Connection;
import com.zrong.Android.game.ConstructData;
import com.zrong.Android.game.GameData;
import com.zrong.Android.online.network.GameProtocol;

public class Department_financeActivity  extends GameActivity implements DataChangeListener {
	public static Department_financeActivity mContext = null; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		

		mContext = this;
		
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		this.setContentView(R.layout.department_1);
		
		Button returnback = (Button)this.findViewById(R.id.department_button_returnback);
		
		Button cancle = (Button)this.findViewById(R.id.department_button_cancel);
		Button title = (Button)this.findViewById(R.id.department_button_title);
		title.setText("财务部");
		returnback.setOnClickListener(new OnClickListener()
		{

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (mContext != null) {
					mContext = null;
					}
				Department_financeActivity.this.finish();
			}
			
		}
		);
		
		cancle.setOnClickListener(new OnClickListener()
		{

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (mContext != null) {
					mContext = null;
					}
				Department_financeActivity.this.finish();
			}
			
		}
		);
		initDepartment();
	}
	public void display(String str){
		Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
	}
	public void initDepartment()
	{
final Department[] department = GameData.corporation.department;
		
		String name = "";
		
		GameData.corporation.addDataChangeListener(this);
		
		for(int i = 0; i < department.length;i++)
		{
			 if(department[i].type == Department.FINANCE)//财务部
			 {

					final Department depart_finance = GameData.corporation.department[i];
					RelativeLayout finance = (RelativeLayout)this.findViewById(R.id.department_personal);
					TextView finance_name = (TextView)finance.findViewById(R.id.company_dep_name);
					Button finance_button = (Button)finance.findViewById(R.id.company_dep_button);
					finance_name.setText(MainActivity.resources.getString(R.string.department_finance_dept)); 
					finance_button.setBackgroundResource(R.drawable.button_standing);
					finance_button.setOnClickListener(new OnClickListener()
					{
						public void onClick(View v) 
						{
							String s[] = new String[]{MainActivity.resources.getString(R.string.department_concerning),MainActivity.resources.getString(R.string.department_tax),MainActivity.resources.getString(R.string.department_paid)};
							
							Builder builder = new AlertDialog.Builder(Department_financeActivity.this);
							
							builder.setItems(s, new DialogInterface.OnClickListener()
							{

								public void onClick(DialogInterface dialog,
										int index)
								 {
									Connection.sendMessage(GameProtocol.CONNECTION_SEND_FINANCE,new byte[]{(byte)(index+1)});
									
									//Connection.sendMessage(GameProtocol.CONNECTION_SEND_STAFF_OP_REQ, ConstructData.Staff_Appoint_Req((byte)0, shopId ,shopType , new long[]{GameData.corporation.employee[idx].id} ));
								}
								
							}
							
							).create().show();
						}	
					}
					
					);
					
					RelativeLayout finance_level = (RelativeLayout)this.findViewById(R.id.department_personal_level);
					TextView finance_level_name = (TextView)finance_level.findViewById(R.id.company_dep_name);
					Button finance_level_button = (Button)finance_level.findViewById(R.id.company_dep_button);
					finance_level_name.setText(MainActivity.resources.getString(R.string.characterinfo_level)+department[i].level);
					//finance_level_button.setText("升级");
					finance_level_button.setBackgroundResource(R.drawable.button_levelup);
					
					finance_level_button.setOnClickListener(new OnClickListener()
					{
						public void onClick(View arg0) 
						{
							Connection.sendMessage(GameProtocol.CONNECTION_REQ_LEVELUP_VIEW,ConstructData.getLevelUPInfo((byte)1, depart_finance.id));
						}
					}
					
					);
					
					
					RelativeLayout finance_num = (RelativeLayout)this.findViewById(R.id.department_personal_num);
					TextView finance_num_name = (TextView)finance_num.findViewById(R.id.company_dep_name);
					Button finance_num_button = (Button)finance_num.findViewById(R.id.company_dep_button);
					finance_num_name.setText(MainActivity.resources.getString(R.string.department_peoplenum)+department[i].employees.length);
					finance_num_button.setVisibility(View.GONE);
					
					RelativeLayout finance_ceo = (RelativeLayout)this.findViewById(R.id.department_personal_ceo);
					TextView finance_ceo_name = (TextView)finance_ceo.findViewById(R.id.company_dep_name);
					Button finance_ceo_button = (Button)finance_ceo.findViewById(R.id.company_dep_button);
					name =  GameData.getEmployeeNameByDuty(5, depart_finance.id, MainActivity.resources.getString(R.string.department_vacancy));
					finance_ceo_name.setText(MainActivity.resources.getString(R.string.department_finance_dept_ceo_1)+name);
					 
					finance_ceo_button.setBackgroundResource(name.equals(MainActivity.resources.getString(R.string.department_vacancy))?R.drawable.button_appointed:R.drawable.outgoing);
					
					finance_ceo_button.setOnClickListener(new OnClickListener()
					{

						public void onClick(View arg0) 
						{
							 String name =  GameData.getEmployeeNameByDuty(5, depart_finance.id, MainActivity.resources.getString(R.string.department_vacancy));
							
							 if(name.equals(MainActivity.resources.getString(R.string.department_vacancy)))//任命
							 {
								 String ename[] = new String[depart_finance.employees.length];
								 if(depart_finance.employees.length==0){
										display(MainActivity.resources.getString(R.string.ttoast1));									 
									 }else{
								 for(int i = 0 ; i < ename.length; i++)
								 {
									 ename[i] = depart_finance.employees[i].name;
								 }
									
									Builder builder = new AlertDialog.Builder(Department_financeActivity.this);
									
									builder.setItems(ename, new DialogInterface.OnClickListener()
									{

										public void onClick(DialogInterface dialog,
												int index)
										{
											 Connection.sendMessage(GameProtocol.CONNECTION_SEND_Staff_DUTY_CHANGE_REQ,
													 ConstructData.getStaffDutyChange(depart_finance.employees[index].id, (byte)5));
											//0普通职员或店员;1主管或店长;2助理;3经理;4总监;5首席
										}
									}
									).create().show(); 
							 }}
							 else//卸任
							 {
								 Connection.sendMessage(GameProtocol.CONNECTION_SEND_Staff_DUTY_CHANGE_REQ,
										 ConstructData.getStaffDutyChange(GameData.getEmployeeByDuty(5, depart_finance.id).id, (byte)0));
	/*							 Connection.sendMessage(GameProtocol.CONNECTION_SEND_Staff_DUTY_CHANGE_REQ,
										 ConstructData.getStaffDutyChange(GameData.getEmployeeByDuty(1, depart_finance.id).id, (byte)0));
	*/						 }
						}
					}
					);
					 
					
					RelativeLayout finance_director = (RelativeLayout)this.findViewById(R.id.department_personal_director);
					TextView finance_director_name = (TextView)finance_director.findViewById(R.id.company_dep_name);
					Button finance_director_button = (Button)finance_director.findViewById(R.id.company_dep_button);
					name =  GameData.getEmployeeNameByDuty(4, depart_finance.id, MainActivity.resources.getString(R.string.department_vacancy));
					finance_director_name.setText(MainActivity.resources.getString(R.string.department_director)+name);
					 
					finance_director_button.setBackgroundResource(name.equals(MainActivity.resources.getString(R.string.department_vacancy))?R.drawable.button_appointed:R.drawable.outgoing);
					finance_director_button.setOnClickListener(new OnClickListener()
					{

						public void onClick(View arg0) 
						{
							 String name =  GameData.getEmployeeNameByDuty(4, depart_finance.id, MainActivity.resources.getString(R.string.department_vacancy));
							
							 if(name.equals(MainActivity.resources.getString(R.string.department_vacancy)))//任命
							 {
								 String ename[] = new String[depart_finance.employees.length];
								 if(depart_finance.employees.length==0){
										display(MainActivity.resources.getString(R.string.ttoast1));									 
									 }else{
								 for(int i = 0 ; i < ename.length; i++)
								 {
									 ename[i] = depart_finance.employees[i].name;
								 }
									
									Builder builder = new AlertDialog.Builder(Department_financeActivity.this);
									
									builder.setItems(ename, new DialogInterface.OnClickListener()
									{

										public void onClick(DialogInterface dialog,
												int index)
										{
											 Connection.sendMessage(GameProtocol.CONNECTION_SEND_Staff_DUTY_CHANGE_REQ,
													 ConstructData.getStaffDutyChange(depart_finance.employees[index].id, (byte)4));
											//0普通职员或店员;1主管或店长;2助理;3经理;4总监;5首席
										}
									}
									).create().show(); 
							 }}
							 else//卸任
							 {
								 Connection.sendMessage(GameProtocol.CONNECTION_SEND_Staff_DUTY_CHANGE_REQ,
										 ConstructData.getStaffDutyChange(GameData.getEmployeeByDuty(4, depart_finance.id).id, (byte)0));
	/*							 Connection.sendMessage(GameProtocol.CONNECTION_SEND_Staff_DUTY_CHANGE_REQ,
										 ConstructData.getStaffDutyChange(GameData.getEmployeeByDuty(1, depart_finance.id).id, (byte)0));
	*/						 }
						}
					}
					);
					 
					
					RelativeLayout finance_manager = (RelativeLayout)this.findViewById(R.id.department_personal_manager);
					TextView finance_manager_name = (TextView)finance_manager.findViewById(R.id.company_dep_name);
					Button finance_manager_button = (Button)finance_manager.findViewById(R.id.company_dep_button);
					name =  GameData.getEmployeeNameByDuty(3, depart_finance.id, MainActivity.resources.getString(R.string.department_vacancy));
					finance_manager_name.setText(MainActivity.resources.getString(R.string.department_manager)+name);
					 
					finance_manager_button.setBackgroundResource(name.equals(MainActivity.resources.getString(R.string.department_vacancy))?R.drawable.button_appointed:R.drawable.outgoing);
					finance_manager_button.setOnClickListener(new OnClickListener()
					{

						public void onClick(View arg0) 
						{
							 String name =  GameData.getEmployeeNameByDuty(3, depart_finance.id, MainActivity.resources.getString(R.string.department_vacancy));
							
							 if(name.equals(MainActivity.resources.getString(R.string.department_vacancy)))//任命
							 {
								 String ename[] = new String[depart_finance.employees.length];
								 if(depart_finance.employees.length==0){
										display(MainActivity.resources.getString(R.string.ttoast1));									 
									 }else{
								 for(int i = 0 ; i < ename.length; i++)
								 {
									 ename[i] = depart_finance.employees[i].name;
								 }
									
									Builder builder = new AlertDialog.Builder(Department_financeActivity.this);
									
									builder.setItems(ename, new DialogInterface.OnClickListener()
									{

										public void onClick(DialogInterface dialog,
												int index)
										{
											 Connection.sendMessage(GameProtocol.CONNECTION_SEND_Staff_DUTY_CHANGE_REQ,
													 ConstructData.getStaffDutyChange(depart_finance.employees[index].id, (byte)3));
											//0普通职员或店员;1主管或店长;2助理;3经理;4总监;5首席
										}
									}
									).create().show(); 
							 }}
							 else//卸任
							 {
								 Connection.sendMessage(GameProtocol.CONNECTION_SEND_Staff_DUTY_CHANGE_REQ,
										 ConstructData.getStaffDutyChange(GameData.getEmployeeByDuty(3, depart_finance.id).id, (byte)0));
	/*							 Connection.sendMessage(GameProtocol.CONNECTION_SEND_Staff_DUTY_CHANGE_REQ,
										 ConstructData.getStaffDutyChange(GameData.getEmployeeByDuty(1, depart_finance.id).id, (byte)0));
	*/						 }
						}
					}
					);
					 
					
					RelativeLayout finance_assistant = (RelativeLayout)this.findViewById(R.id.department_personal_assistant);
					TextView finance_assistant_name = (TextView)finance_assistant.findViewById(R.id.company_dep_name);
					Button finance_assistant_button = (Button)finance_assistant.findViewById(R.id.company_dep_button);
					name =  GameData.getEmployeeNameByDuty(2, depart_finance.id, MainActivity.resources.getString(R.string.department_vacancy));
					finance_assistant_name.setText(MainActivity.resources.getString(R.string.department_assistant)+name);
					 
					finance_assistant_button.setBackgroundResource(name.equals(MainActivity.resources.getString(R.string.department_vacancy))?R.drawable.button_appointed:R.drawable.outgoing);
					finance_assistant_button.setOnClickListener(new OnClickListener()
					{

						public void onClick(View arg0) 
						{
							 String name =  GameData.getEmployeeNameByDuty(2, depart_finance.id, MainActivity.resources.getString(R.string.department_vacancy));
							
							 if(name.equals(MainActivity.resources.getString(R.string.department_vacancy)))//任命
							 {
								 String ename[] = new String[depart_finance.employees.length];
								 if(depart_finance.employees.length==0){
										display(MainActivity.resources.getString(R.string.ttoast1));									 
									 }else{
								 for(int i = 0 ; i < ename.length; i++)
								 {
									 ename[i] = depart_finance.employees[i].name;
								 }
									
									Builder builder = new AlertDialog.Builder(Department_financeActivity.this);
									
									builder.setItems(ename, new DialogInterface.OnClickListener()
									{

										public void onClick(DialogInterface dialog,
												int index)
										{
											 Connection.sendMessage(GameProtocol.CONNECTION_SEND_Staff_DUTY_CHANGE_REQ,
													 ConstructData.getStaffDutyChange(depart_finance.employees[index].id, (byte)2));
											//0普通职员或店员;1主管或店长;2助理;3经理;4总监;5首席
										}
									}
									).create().show(); 
							 }}
							 else//卸任
							 {
								 Connection.sendMessage(GameProtocol.CONNECTION_SEND_Staff_DUTY_CHANGE_REQ,
										 ConstructData.getStaffDutyChange(GameData.getEmployeeByDuty(2, depart_finance.id).id, (byte)0));
	/*							 Connection.sendMessage(GameProtocol.CONNECTION_SEND_Staff_DUTY_CHANGE_REQ,
										 ConstructData.getStaffDutyChange(GameData.getEmployeeByDuty(1, depart_finance.id).id, (byte)0));
	*/						 }
						}
					}
					);
					 
					
					RelativeLayout finance_competent = (RelativeLayout)this.findViewById(R.id.department_personal_competent);
					TextView finance_competent_name = (TextView)finance_competent.findViewById(R.id.company_dep_name);
					Button finance_competent_button = (Button)finance_competent.findViewById(R.id.company_dep_button);
					name =  GameData.getEmployeeNameByDuty(1, depart_finance.id, MainActivity.resources.getString(R.string.department_vacancy));
					finance_competent_name.setText(MainActivity.resources.getString(R.string.department_competent)+name);
					 
					finance_competent_button.setBackgroundResource(name.equals(MainActivity.resources.getString(R.string.department_vacancy))?R.drawable.button_appointed:R.drawable.outgoing);
					finance_competent_button.setOnClickListener(new OnClickListener()
					{

						public void onClick(View arg0) 
						{
							 String name =  GameData.getEmployeeNameByDuty(1, depart_finance.id, MainActivity.resources.getString(R.string.department_vacancy));
							
							 if(name.equals(MainActivity.resources.getString(R.string.department_vacancy)))//任命
							 {
								 String ename[] = new String[depart_finance.employees.length];
								 if(depart_finance.employees.length==0){
										display(MainActivity.resources.getString(R.string.ttoast1));									 
									 }else{
								 for(int i = 0 ; i < ename.length; i++)
								 {
									 ename[i] = depart_finance.employees[i].name;
								 }
									
									Builder builder = new AlertDialog.Builder(Department_financeActivity.this);
									
									builder.setItems(ename, new DialogInterface.OnClickListener()
									{

										public void onClick(DialogInterface dialog,
												int index)
										{
											 Connection.sendMessage(GameProtocol.CONNECTION_SEND_Staff_DUTY_CHANGE_REQ,
													 ConstructData.getStaffDutyChange(depart_finance.employees[index].id, (byte)1));
											//0普通职员或店员;1主管或店长;2助理;3经理;4总监;5首席
										}
									}
									).create().show(); 
							 }}
							 else//卸任
							 {
								 Connection.sendMessage(GameProtocol.CONNECTION_SEND_Staff_DUTY_CHANGE_REQ,
										 ConstructData.getStaffDutyChange(GameData.getEmployeeByDuty(1, depart_finance.id).id, (byte)0));
							 }
						}
					}
					);
				
			 }
		}
		}
	public void Activitychange(Class activityClass, Intent intent) {
		 if(intent == null)
		 {
			 intent = new Intent();
		 }
		Log.i("Log", activityClass.getName());
		 intent.setClass(Department_financeActivity.this,activityClass);
//		 intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		
		 this.startActivity(intent);
		
	}


	public void OnDataChange(Bundle bundle) 
	{
		initDepartment();
	}
	
	@Override
	public GameActivity getGameContext() {
		// TODO Auto-generated method stub
		return this;
	}
	
	@Override
	public void finish() 
	{
		mContext = null;
		super.finish();
	}

}
