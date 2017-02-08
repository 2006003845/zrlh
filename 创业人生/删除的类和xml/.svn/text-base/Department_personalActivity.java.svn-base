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

public class Department_personalActivity extends GameActivity implements DataChangeListener {
	public static Department_personalActivity mContext = null; 
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
		title.setText("人事部");
		returnback.setOnClickListener(new OnClickListener()
		{

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (mContext != null) {
					mContext = null;
					}
				Department_personalActivity.this.finish();
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
				Department_personalActivity.this.finish();
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
			if(department[i].type == Department.PERSONNAL)//人事部
			{
				
				final Department depart_personal = GameData.corporation.department[i];
				
				RelativeLayout personnal = (RelativeLayout)this.findViewById(R.id.department_personal);
				TextView personnal_name = (TextView)personnal.findViewById(R.id.company_dep_name);
				Button personnal_button = (Button)personnal.findViewById(R.id.company_dep_button);
				personnal_name.setText(MainActivity.resources.getString(R.string.department_personnel_dept));
				personnal_button.setBackgroundResource(R.drawable.button_standing);
				personnal_button.setVisibility(View.GONE);
				 
				RelativeLayout personnal_level = (RelativeLayout)this.findViewById(R.id.department_personal_level);
				TextView personnal_level_name = (TextView)personnal_level.findViewById(R.id.company_dep_name);
				Button personnal_level_button = (Button)personnal_level.findViewById(R.id.company_dep_button);
				personnal_level_name.setText(MainActivity.resources.getString(R.string.characterinfo_level)+department[i].level);
				 
				personnal_level_button.setBackgroundResource(R.drawable.button_levelup);
				personnal_level_button.setOnClickListener(new OnClickListener()
				{

					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						Connection.sendMessage(GameProtocol.CONNECTION_REQ_LEVELUP_VIEW,ConstructData.getLevelUPInfo((byte)1, depart_personal.id));
					}
					
				}
				);
				
				RelativeLayout personnal_num = (RelativeLayout)this.findViewById(R.id.department_personal_num);
				TextView personnal_num_name = (TextView)personnal_num.findViewById(R.id.company_dep_name);
				Button personnal_num_button = (Button)personnal_num.findViewById(R.id.company_dep_button);
				personnal_num_name.setText(MainActivity.resources.getString(R.string.department_peoplenum)+department[i].employees.length);
				 
				personnal_num_button.setVisibility(View.GONE);
				
				RelativeLayout personnal_ceo = (RelativeLayout)this.findViewById(R.id.department_personal_ceo);
				TextView personnal_ceo_name = (TextView)personnal_ceo.findViewById(R.id.company_dep_name);
				Button personnal_ceo_button = (Button)personnal_ceo.findViewById(R.id.company_dep_button);
				name =  GameData.getEmployeeNameByDuty(5, depart_personal.id, MainActivity.resources.getString(R.string.department_vacancy));
				personnal_ceo_name.setText(MainActivity.resources.getString(R.string.department_ceo)+name);
				
				personnal_ceo_button.setBackgroundResource(name.equals(MainActivity.resources.getString(R.string.department_vacancy))?R.drawable.button_appointed:R.drawable.outgoing);
				personnal_ceo_button.setOnClickListener(new OnClickListener()
				{
					public void onClick(View arg0)
					{
						String name =  GameData.getEmployeeNameByDuty(5, depart_personal.id, MainActivity.resources.getString(R.string.department_vacancy)); 
						 if(name.equals(MainActivity.resources.getString(R.string.department_vacancy)))//任命
						 {
							 String ename[] = new String[depart_personal.employees.length];
							 if(depart_personal.employees.length==0){
								display(MainActivity.resources.getString(R.string.ttoast1));
								 Log.i("ppq", "depart_personal.employees.length___>"+depart_personal.employees.length);
							 }else{
							 Log.i("ppq", "depart_personal.employees.length___"+depart_personal.employees.length);
							 for(int i = 0 ; i < ename.length; i++)
							 {
								 ename[i] = depart_personal.employees[i].name;
							 }
								
								Builder builder = new AlertDialog.Builder(Department_personalActivity.this);
								
								builder.setItems(ename, new DialogInterface.OnClickListener()
								{

									public void onClick(DialogInterface dialog,
											int index)
									{
										 Connection.sendMessage(GameProtocol.CONNECTION_SEND_Staff_DUTY_CHANGE_REQ,
												 ConstructData.getStaffDutyChange(depart_personal.employees[index].id, (byte)5));
										//0普通职员或店员;1主管或店长;2助理;3经理;4总监;5首席
									}
								}
								).create().show(); 
						 }
							 }
						 else//卸任
						 {
							 Connection.sendMessage(GameProtocol.CONNECTION_SEND_Staff_DUTY_CHANGE_REQ,
									 ConstructData.getStaffDutyChange(GameData.getEmployeeByDuty(5, depart_personal.id).id, (byte)5));
							 /*Connection.sendMessage(GameProtocol.CONNECTION_SEND_Staff_DUTY_CHANGE_REQ,
									 ConstructData.getStaffDutyChange(GameData.getEmployeeByDuty(1, depart_personal.id).id, (byte)0));*/
					 }
					}
					
				}
				);
				 
				
				
				RelativeLayout personnal_director = (RelativeLayout)this.findViewById(R.id.department_personal_director);
				TextView personnal_director_name = (TextView)personnal_director.findViewById(R.id.company_dep_name);
				Button personnal_director_button = (Button)personnal_director.findViewById(R.id.company_dep_button);
				name =  GameData.getEmployeeNameByDuty(4, depart_personal.id, MainActivity.resources.getString(R.string.department_vacancy)); 
				personnal_director_name.setText(MainActivity.resources.getString(R.string.department_director)+name); 
				personnal_director_button.setBackgroundResource(name.equals(MainActivity.resources.getString(R.string.department_vacancy))?R.drawable.button_appointed:R.drawable.outgoing);
				personnal_director_button.setOnClickListener(new OnClickListener()
				{

					public void onClick(View arg0)
					{
						String name =  GameData.getEmployeeNameByDuty(4, depart_personal.id, MainActivity.resources.getString(R.string.department_vacancy)); 
						 if(name.equals(MainActivity.resources.getString(R.string.department_vacancy)))//任命
						 {
							 String ename[] = new String[depart_personal.employees.length];
							 if(depart_personal.employees.length==0){
									display(MainActivity.resources.getString(R.string.ttoast1));
									 Log.i("ppq", "depart_personal.employees.length___>"+depart_personal.employees.length);
								 }else{
							 for(int i = 0 ; i < ename.length; i++)
							 {
								 ename[i] = depart_personal.employees[i].name;
							 }
								
								Builder builder = new AlertDialog.Builder(Department_personalActivity.this);
								
								builder.setItems(ename, new DialogInterface.OnClickListener()
								{

									public void onClick(DialogInterface dialog,
											int index)
									{
										 Connection.sendMessage(GameProtocol.CONNECTION_SEND_Staff_DUTY_CHANGE_REQ,
												 ConstructData.getStaffDutyChange(depart_personal.employees[index].id, (byte)4));
										//0普通职员或店员;1主管或店长;2助理;3经理;4总监;5首席
									}
								}
								).create().show(); 
						 }}
						 else//卸任
						 {
							 Connection.sendMessage(GameProtocol.CONNECTION_SEND_Staff_DUTY_CHANGE_REQ,
									 ConstructData.getStaffDutyChange(GameData.getEmployeeByDuty(4, depart_personal.id).id, (byte)4));
/*							 Connection.sendMessage(GameProtocol.CONNECTION_SEND_Staff_DUTY_CHANGE_REQ,
									 ConstructData.getStaffDutyChange(GameData.getEmployeeByDuty(1, depart_personal.id).id, (byte)0));
*/						 }
					}
					
				}
				);
				
				
				RelativeLayout personnal_manager = (RelativeLayout)this.findViewById(R.id.department_personal_manager);
				TextView personnal_mannager_name = (TextView)personnal_manager.findViewById(R.id.company_dep_name);
				Button personnal_mannager_button = (Button)personnal_manager.findViewById(R.id.company_dep_button);
				name =  GameData.getEmployeeNameByDuty(3, depart_personal.id, MainActivity.resources.getString(R.string.department_vacancy)); 
				personnal_mannager_name.setText(MainActivity.resources.getString(R.string.department_manager)+name); 
				personnal_mannager_button.setBackgroundResource(name.equals(MainActivity.resources.getString(R.string.department_vacancy))?R.drawable.button_appointed:R.drawable.outgoing);
				personnal_mannager_button.setOnClickListener(new OnClickListener()
				{

					public void onClick(View arg0)
					{
						String name =  GameData.getEmployeeNameByDuty(3, depart_personal.id, MainActivity.resources.getString(R.string.department_vacancy)); 
						 if(name.equals(MainActivity.resources.getString(R.string.department_vacancy)))//任命
						 {
							 String ename[] = new String[depart_personal.employees.length];
							 if(depart_personal.employees.length==0){
									display(MainActivity.resources.getString(R.string.ttoast1));
									 Log.i("ppq", "depart_personal.employees.length___>"+depart_personal.employees.length);
								 }else{
							 for(int i = 0 ; i < ename.length; i++)
							 {
								 ename[i] = depart_personal.employees[i].name;
							 }
								
								Builder builder = new AlertDialog.Builder(Department_personalActivity.this);
								
								builder.setItems(ename, new DialogInterface.OnClickListener()
								{

									public void onClick(DialogInterface dialog,
											int index)
									{
										 Connection.sendMessage(GameProtocol.CONNECTION_SEND_Staff_DUTY_CHANGE_REQ,
												 ConstructData.getStaffDutyChange(depart_personal.employees[index].id, (byte)3));
										//0普通职员或店员;1主管或店长;2助理;3经理;4总监;5首席
									}
								}
								).create().show(); 
						 }}
						 else//卸任
						 {
							 Connection.sendMessage(GameProtocol.CONNECTION_SEND_Staff_DUTY_CHANGE_REQ,
									 ConstructData.getStaffDutyChange(GameData.getEmployeeByDuty(3, depart_personal.id).id, (byte)3));
/*							 Connection.sendMessage(GameProtocol.CONNECTION_SEND_Staff_DUTY_CHANGE_REQ,
									 ConstructData.getStaffDutyChange(GameData.getEmployeeByDuty(1, depart_personal.id).id, (byte)0));
*/						 }
					}
					
				}
				);
				
				
				RelativeLayout personnal_assistant = (RelativeLayout)this.findViewById(R.id.department_personal_assistant);
				TextView personnal_assistant_name = (TextView)personnal_assistant.findViewById(R.id.company_dep_name);
				Button personnal_assistant_button = (Button)personnal_assistant.findViewById(R.id.company_dep_button);
			    name =  GameData.getEmployeeNameByDuty(2, depart_personal.id, MainActivity.resources.getString(R.string.department_vacancy)); 
				personnal_assistant_name.setText(MainActivity.resources.getString(R.string.department_assistant)+name);
				personnal_assistant_button.setBackgroundResource(name.equals(MainActivity.resources.getString(R.string.department_vacancy))?R.drawable.button_appointed:R.drawable.outgoing);
				
				personnal_assistant_button.setOnClickListener(new OnClickListener()
				{

					public void onClick(View arg0)
					{
						String name =  GameData.getEmployeeNameByDuty(2, depart_personal.id, MainActivity.resources.getString(R.string.department_vacancy)); 
						 if(name.equals(MainActivity.resources.getString(R.string.department_vacancy)))//任命
						 {
							 String ename[] = new String[depart_personal.employees.length];
							 if(depart_personal.employees.length==0){
									display(MainActivity.resources.getString(R.string.ttoast1));
									 Log.i("ppq", "depart_personal.employees.length___>"+depart_personal.employees.length);
								 }else{
							 for(int i = 0 ; i < ename.length; i++)
							 {
								 ename[i] = depart_personal.employees[i].name;
							 }
								
								Builder builder = new AlertDialog.Builder(Department_personalActivity.this);
								
								builder.setItems(ename, new DialogInterface.OnClickListener()
								{

									public void onClick(DialogInterface dialog,
											int index)
									{
										 Connection.sendMessage(GameProtocol.CONNECTION_SEND_Staff_DUTY_CHANGE_REQ,
												 ConstructData.getStaffDutyChange(depart_personal.employees[index].id, (byte)2));
										//0普通职员或店员;1主管或店长;2助理;3经理;4总监;5首席
									}
								}
								).create().show(); 
						 }}
						 else//卸任
						 {
							 Connection.sendMessage(GameProtocol.CONNECTION_SEND_Staff_DUTY_CHANGE_REQ,
									 ConstructData.getStaffDutyChange(GameData.getEmployeeByDuty(2, depart_personal.id).id, (byte)0));
/*							 Connection.sendMessage(GameProtocol.CONNECTION_SEND_Staff_DUTY_CHANGE_REQ,
									 ConstructData.getStaffDutyChange(GameData.getEmployeeByDuty(1, depart_personal.id).id, (byte)0));
*/						 }
					}
					
				}
				);
				
				RelativeLayout personnal_competent = (RelativeLayout)this.findViewById(R.id.department_personal_competent);
				TextView personnal_competent_name = (TextView)personnal_competent.findViewById(R.id.company_dep_name);
				Button personnal_competent_button = (Button)personnal_competent.findViewById(R.id.company_dep_button);
			    //name = GameData.getEmployeeNameById(department[i].manager_oneId,"空缺");
			    name = GameData.getEmployeeNameByDuty(1, depart_personal.id, MainActivity.resources.getString(R.string.department_vacancy));
				personnal_competent_name.setText(MainActivity.resources.getString(R.string.department_competent)+name);
				personnal_competent_button.setBackgroundResource(name.equals(MainActivity.resources.getString(R.string.department_vacancy))?R.drawable.button_appointed:R.drawable.outgoing);
				
				personnal_competent_button.setOnClickListener(new OnClickListener()
				{

					public void onClick(View arg0) 
					{
						 String name =  GameData.getEmployeeNameByDuty(1, depart_personal.id, MainActivity.resources.getString(R.string.department_vacancy));
						
						 if(name.equals(MainActivity.resources.getString(R.string.department_vacancy)))//任命
						 {
							 String ename[] = new String[depart_personal.employees.length];
							 if(depart_personal.employees.length==0){
									display(MainActivity.resources.getString(R.string.ttoast1));
									 Log.i("ppq", "depart_personal.employees.length___>"+depart_personal.employees.length);
								 }else{
							 for(int i = 0 ; i < ename.length; i++)
							 {
								 ename[i] = depart_personal.employees[i].name;
							 }
								
								Builder builder = new AlertDialog.Builder(Department_personalActivity.this);
								
								builder.setItems(ename, new DialogInterface.OnClickListener()
								{

									public void onClick(DialogInterface dialog,
											int index)
									{
										 Connection.sendMessage(GameProtocol.CONNECTION_SEND_Staff_DUTY_CHANGE_REQ,
												 ConstructData.getStaffDutyChange(depart_personal.employees[index].id, (byte)1));
										//0普通职员或店员;1主管或店长;2助理;3经理;4总监;5首席
									}
								}
								).create().show(); 
						 }}
						 else//卸任
						 {
							 Connection.sendMessage(GameProtocol.CONNECTION_SEND_Staff_DUTY_CHANGE_REQ,
									 ConstructData.getStaffDutyChange(GameData.getEmployeeByDuty(1, depart_personal.id).id, (byte)0));
/*							 Connection.sendMessage(GameProtocol.CONNECTION_SEND_Staff_DUTY_CHANGE_REQ,
									 ConstructData.getStaffDutyChange(GameData.getEmployeeByDuty(1, depart_personal.id).id, (byte)0));
*/						 }
						
						
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
		
		 intent.setClass(Department_personalActivity.this,activityClass);
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
