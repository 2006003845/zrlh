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

public class Department_strategyActivity  extends GameActivity implements DataChangeListener{
	public static Department_strategyActivity mContext = null; 
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
		title.setText("策划部");
		returnback.setOnClickListener(new OnClickListener()
		{

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (mContext != null) {
					mContext = null;
					}
				Department_strategyActivity.this.finish();
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
				Department_strategyActivity.this.finish();
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
			if(department[i].type == Department.PLANNING ){

				Log.i("Log", "策划部=============================！！！");
				final Department depart_planning = GameData.corporation.department[i];
				RelativeLayout planning = (RelativeLayout)this.findViewById(R.id.department_personal);
				TextView planning_name = (TextView)planning.findViewById(R.id.company_dep_name);
				
				planning_name.setText("策划部"); //未改
				Button planning_button = (Button) planning.findViewById(R.id.company_dep_button);
				planning_button.setVisibility(View.GONE);
				RelativeLayout planning_level = (RelativeLayout)this.findViewById(R.id.department_personal_level);
				TextView planning_level_name = (TextView)planning_level.findViewById(R.id.company_dep_name);
				Button planning_level_button = (Button)planning_level.findViewById(R.id.company_dep_button);
				planning_level_name.setText(MainActivity.resources.getString(R.string.characterinfo_level)+department[i].level);//未改
				//finance_level_button.setText("升级");
				planning_level_button.setBackgroundResource(R.drawable.button_levelup);
				
				planning_level_button.setOnClickListener(new OnClickListener()
				{
					public void onClick(View arg0) 
					{
						Connection.sendMessage(GameProtocol.CONNECTION_REQ_LEVELUP_VIEW,ConstructData.getLevelUPInfo((byte)1, depart_planning.id));//未改
					}
				}
				
				);
				
				
				RelativeLayout planning_num = (RelativeLayout)this.findViewById(R.id.department_personal_num);
				TextView planning_num_name = (TextView)planning_num.findViewById(R.id.company_dep_name);
				Button planning_num_button = (Button)planning_num.findViewById(R.id.company_dep_button);
				planning_num_name.setText(MainActivity.resources.getString(R.string.department_peoplenum)+department[i].employees.length);//未改
				planning_num_button.setVisibility(View.GONE);
				
				RelativeLayout planning_ceo = (RelativeLayout)this.findViewById(R.id.department_personal_ceo);
				TextView planning_ceo_name = (TextView)planning_ceo.findViewById(R.id.company_dep_name);
				Button planning_ceo_button = (Button)planning_ceo.findViewById(R.id.company_dep_button);
				name =  GameData.getEmployeeNameByDuty(5, depart_planning.id, MainActivity.resources.getString(R.string.department_vacancy));
				planning_ceo_name.setText(MainActivity.resources.getString(R.string.department_finance_dept_ceo)+name);
				 
				planning_ceo_button.setBackgroundResource(name.equals(MainActivity.resources.getString(R.string.department_vacancy))?R.drawable.button_appointed:R.drawable.outgoing);
				
				planning_ceo_button.setOnClickListener(new OnClickListener()
				{

					public void onClick(View arg0) 
					{
						 String name =  GameData.getEmployeeNameByDuty(5, depart_planning.id, MainActivity.resources.getString(R.string.department_vacancy));
						
						 if(name.equals(MainActivity.resources.getString(R.string.department_vacancy)))//任命
						 {
							 String ename[] = new String[depart_planning.employees.length];
							 if(depart_planning.employees.length==0){
									display(MainActivity.resources.getString(R.string.ttoast1));									 
								 }else{
							 for(int i = 0 ; i < ename.length; i++)
							 {
								 ename[i] = depart_planning.employees[i].name;
							 }
								
								Builder builder = new AlertDialog.Builder(Department_strategyActivity.this);
								
								builder.setItems(ename, new DialogInterface.OnClickListener()
								{

									public void onClick(DialogInterface dialog,
											int index)
									{
										 Connection.sendMessage(GameProtocol.CONNECTION_SEND_Staff_DUTY_CHANGE_REQ,
												 ConstructData.getStaffDutyChange(depart_planning.employees[index].id, (byte)5));
										//0普通职员或店员;1主管或店长;2助理;3经理;4总监;5首席
									}
								}
								).create().show(); 
						 }}
						 else//卸任
						 {
							 Connection.sendMessage(GameProtocol.CONNECTION_SEND_Staff_DUTY_CHANGE_REQ,
									 ConstructData.getStaffDutyChange(GameData.getEmployeeByDuty(5, depart_planning.id).id, (byte)0));
/*							 Connection.sendMessage(GameProtocol.CONNECTION_SEND_Staff_DUTY_CHANGE_REQ,
									 ConstructData.getStaffDutyChange(GameData.getEmployeeByDuty(1, depart_planning.id).id, (byte)0));
*/						 }
					}
				}
				);
				 
				
				RelativeLayout planning_director = (RelativeLayout)this.findViewById(R.id.department_personal_director);
				TextView planning_director_name = (TextView)planning_director.findViewById(R.id.company_dep_name);
				Button planning_director_button = (Button)planning_director.findViewById(R.id.company_dep_button);
				name =  GameData.getEmployeeNameByDuty(4, depart_planning.id, MainActivity.resources.getString(R.string.department_vacancy));
				planning_director_name.setText(MainActivity.resources.getString(R.string.department_director)+name);
				 
				planning_director_button.setBackgroundResource(name.equals(MainActivity.resources.getString(R.string.department_vacancy))?R.drawable.button_appointed:R.drawable.outgoing);
				planning_director_button.setOnClickListener(new OnClickListener()
				{

					public void onClick(View arg0) 
					{
						 String name =  GameData.getEmployeeNameByDuty(4, depart_planning.id, MainActivity.resources.getString(R.string.department_vacancy));
						
						 if(name.equals(MainActivity.resources.getString(R.string.department_vacancy)))//任命
						 {
							 String ename[] = new String[depart_planning.employees.length];
							 if(depart_planning.employees.length==0){
									display(MainActivity.resources.getString(R.string.ttoast1));									 
								 }else{
							 for(int i = 0 ; i < ename.length; i++)
							 {
								 ename[i] = depart_planning.employees[i].name;
							 }
								
								Builder builder = new AlertDialog.Builder(Department_strategyActivity.this);
								
								builder.setItems(ename, new DialogInterface.OnClickListener()
								{

									public void onClick(DialogInterface dialog,
											int index)
									{
										 Connection.sendMessage(GameProtocol.CONNECTION_SEND_Staff_DUTY_CHANGE_REQ,
												 ConstructData.getStaffDutyChange(depart_planning.employees[index].id, (byte)4));
										//0普通职员或店员;1主管或店长;2助理;3经理;4总监;5首席
									}
								}
								).create().show(); 
						 }}
						 else//卸任
						 {
							 Connection.sendMessage(GameProtocol.CONNECTION_SEND_Staff_DUTY_CHANGE_REQ,
									 ConstructData.getStaffDutyChange(GameData.getEmployeeByDuty(4, depart_planning.id).id, (byte)0));
/*							 Connection.sendMessage(GameProtocol.CONNECTION_SEND_Staff_DUTY_CHANGE_REQ,
									 ConstructData.getStaffDutyChange(GameData.getEmployeeByDuty(1, depart_planning.id).id, (byte)0));
*/						 }
					}
				}
				);
				 
				
				RelativeLayout planning_manager = (RelativeLayout)this.findViewById(R.id.department_personal_manager);
				TextView planning_manager_name = (TextView)planning_manager.findViewById(R.id.company_dep_name);
				Button planning_manager_button = (Button)planning_manager.findViewById(R.id.company_dep_button);
				name =  GameData.getEmployeeNameByDuty(3, depart_planning.id, MainActivity.resources.getString(R.string.department_vacancy));
				planning_manager_name.setText(MainActivity.resources.getString(R.string.department_manager)+name);
				 
				planning_manager_button.setBackgroundResource(name.equals(MainActivity.resources.getString(R.string.department_vacancy))?R.drawable.button_appointed:R.drawable.outgoing);
				planning_manager_button.setOnClickListener(new OnClickListener()
				{

					public void onClick(View arg0) 
					{
						 String name =  GameData.getEmployeeNameByDuty(3, depart_planning.id, MainActivity.resources.getString(R.string.department_vacancy));
						
						 if(name.equals(MainActivity.resources.getString(R.string.department_vacancy)))//任命
						 {
							 String ename[] = new String[depart_planning.employees.length];
							 if(depart_planning.employees.length==0){
									display(MainActivity.resources.getString(R.string.ttoast1));									 
								 }else{
							 for(int i = 0 ; i < ename.length; i++)
							 {
								 ename[i] = depart_planning.employees[i].name;
							 }
								
								Builder builder = new AlertDialog.Builder(Department_strategyActivity.this);
								
								builder.setItems(ename, new DialogInterface.OnClickListener()
								{

									public void onClick(DialogInterface dialog,
											int index)
									{
										 Connection.sendMessage(GameProtocol.CONNECTION_SEND_Staff_DUTY_CHANGE_REQ,
												 ConstructData.getStaffDutyChange(depart_planning.employees[index].id, (byte)3));
										//0普通职员或店员;1主管或店长;2助理;3经理;4总监;5首席
									}
								}
								).create().show(); 
						 }}
						 else//卸任
						 {
							 Connection.sendMessage(GameProtocol.CONNECTION_SEND_Staff_DUTY_CHANGE_REQ,
									 ConstructData.getStaffDutyChange(GameData.getEmployeeByDuty(3, depart_planning.id).id, (byte)0));
/*							 Connection.sendMessage(GameProtocol.CONNECTION_SEND_Staff_DUTY_CHANGE_REQ,
									 ConstructData.getStaffDutyChange(GameData.getEmployeeByDuty(1, depart_planning.id).id, (byte)0));
*/						 }
					}
				}
				);
				 
				
				RelativeLayout planning_assistant = (RelativeLayout)this.findViewById(R.id.department_personal_assistant);
				TextView finance_assistant_name = (TextView)planning_assistant.findViewById(R.id.company_dep_name);
				Button planning_assistant_button = (Button)planning_assistant.findViewById(R.id.company_dep_button);
				name =  GameData.getEmployeeNameByDuty(2, depart_planning.id, MainActivity.resources.getString(R.string.department_vacancy));
				finance_assistant_name.setText(MainActivity.resources.getString(R.string.department_assistant)+name);
				 
				planning_assistant_button.setBackgroundResource(name.equals(MainActivity.resources.getString(R.string.department_vacancy))?R.drawable.button_appointed:R.drawable.outgoing);
				planning_assistant_button.setOnClickListener(new OnClickListener()
				{

					public void onClick(View arg0) 
					{
						 String name =  GameData.getEmployeeNameByDuty(2, depart_planning.id, MainActivity.resources.getString(R.string.department_vacancy));
						
						 if(name.equals(MainActivity.resources.getString(R.string.department_vacancy)))//任命
						 {
							 String ename[] = new String[depart_planning.employees.length];
							 if(depart_planning.employees.length==0){
									display(MainActivity.resources.getString(R.string.ttoast1));									 
								 }else{
							 for(int i = 0 ; i < ename.length; i++)
							 {
								 ename[i] = depart_planning.employees[i].name;
							 }
								
								Builder builder = new AlertDialog.Builder(Department_strategyActivity.this);
								
								builder.setItems(ename, new DialogInterface.OnClickListener()
								{

									public void onClick(DialogInterface dialog,
											int index)
									{
										 Connection.sendMessage(GameProtocol.CONNECTION_SEND_Staff_DUTY_CHANGE_REQ,
												 ConstructData.getStaffDutyChange(depart_planning.employees[index].id, (byte)2));
										//0普通职员或店员;1主管或店长;2助理;3经理;4总监;5首席
									}
								}
								).create().show(); 
						 }}
						 else//卸任
						 {
							 Connection.sendMessage(GameProtocol.CONNECTION_SEND_Staff_DUTY_CHANGE_REQ,
									 ConstructData.getStaffDutyChange(GameData.getEmployeeByDuty(2, depart_planning.id).id, (byte)0));
/*							 Connection.sendMessage(GameProtocol.CONNECTION_SEND_Staff_DUTY_CHANGE_REQ,
									 ConstructData.getStaffDutyChange(GameData.getEmployeeByDuty(1, depart_planning.id).id, (byte)0));
*/						 }
					}
				}
				);
				 
				
				RelativeLayout planning_competent = (RelativeLayout)this.findViewById(R.id.department_personal_competent);
				TextView finance_competent_name = (TextView)planning_competent.findViewById(R.id.company_dep_name);
				Button planning_competent_button = (Button)planning_competent.findViewById(R.id.company_dep_button);
				name =  GameData.getEmployeeNameByDuty(1, depart_planning.id, MainActivity.resources.getString(R.string.department_vacancy));
				finance_competent_name.setText(MainActivity.resources.getString(R.string.department_competent)+name);
				 
				planning_competent_button.setBackgroundResource(name.equals(MainActivity.resources.getString(R.string.department_vacancy))?R.drawable.button_appointed:R.drawable.outgoing);
				planning_competent_button.setOnClickListener(new OnClickListener()
				{

					public void onClick(View arg0) 
					{
						 String name =  GameData.getEmployeeNameByDuty(1, depart_planning.id, MainActivity.resources.getString(R.string.department_vacancy));
						
						 if(name.equals(MainActivity.resources.getString(R.string.department_vacancy)))//任命
						 {
							 String ename[] = new String[depart_planning.employees.length];
							 if(depart_planning.employees.length==0){
									display(MainActivity.resources.getString(R.string.ttoast1));									 
								 }else{
							 for(int i = 0 ; i < ename.length; i++)
							 {
								 ename[i] = depart_planning.employees[i].name;
							 }
								
								Builder builder = new AlertDialog.Builder(Department_strategyActivity.this);
								
								builder.setItems(ename, new DialogInterface.OnClickListener()
								{

									public void onClick(DialogInterface dialog,
											int index)
									{
										 Connection.sendMessage(GameProtocol.CONNECTION_SEND_Staff_DUTY_CHANGE_REQ,
												 ConstructData.getStaffDutyChange(depart_planning.employees[index].id, (byte)1));
										//0普通职员或店员;1主管或店长;2助理;3经理;4总监;5首席
									}
								}
								).create().show(); 
						 }}
						 else//卸任
						 {
							 Connection.sendMessage(GameProtocol.CONNECTION_SEND_Staff_DUTY_CHANGE_REQ,
									 ConstructData.getStaffDutyChange(GameData.getEmployeeByDuty(1, depart_planning.id).id, (byte)0));
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
		 intent.setClass(Department_strategyActivity.this,activityClass);
		 intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		 
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
