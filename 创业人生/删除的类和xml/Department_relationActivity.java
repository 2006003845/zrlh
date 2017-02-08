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

public class Department_relationActivity extends GameActivity implements DataChangeListener{
	public static Department_relationActivity mContext = null; 
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
		title.setText("公关部");
		returnback.setOnClickListener(new OnClickListener()
		{

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (mContext != null) {
					mContext = null;
					}
				Department_relationActivity.this.finish();
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
				Department_relationActivity.this.finish();
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
			 if(department[i].type == Department.MARKET)//公关部
			 {

					final Department depart_relation = GameData.corporation.department[i];
					RelativeLayout relation = (RelativeLayout)this.findViewById(R.id.department_personal);
					TextView relation_name = (TextView)relation.findViewById(R.id.company_dep_name);
					Button relation_button = (Button)relation.findViewById(R.id.company_dep_button);
					relation_name.setText(MainActivity.resources.getString(R.string.department_marcom));
					//relation_button.setText("常务");
					relation_button.setBackgroundResource(R.drawable.button_standing);
					relation_button.setVisibility(View.GONE);
					
					RelativeLayout relation_level = (RelativeLayout)this.findViewById(R.id.department_personal_level);
					TextView relation_level_name = (TextView)relation_level.findViewById(R.id.company_dep_name);
					Button relation_level_button = (Button)relation_level.findViewById(R.id.company_dep_button);
					relation_level_name.setText(MainActivity.resources.getString(R.string.characterinfo_level)+department[i].level);
					//relation_level_button.setText("升级");
					relation_level_button.setBackgroundResource(R.drawable.button_levelup);
					relation_level_button.setOnClickListener(new OnClickListener()
					{

						public void onClick(View arg0) {
							// TODO Auto-generated method stub
							Connection.sendMessage(GameProtocol.CONNECTION_REQ_LEVELUP_VIEW,ConstructData.getLevelUPInfo((byte)1, depart_relation.id));
						}
						
					}
					);
					
					
					RelativeLayout relation_num = (RelativeLayout)this.findViewById(R.id.department_personal_num);
					TextView relation_name_num = (TextView)relation_num.findViewById(R.id.company_dep_name);
					Button relation_button_num = (Button)relation_num.findViewById(R.id.company_dep_button);
					relation_name_num.setText(MainActivity.resources.getString(R.string.department_peoplenum)+department[i].employees.length);
					relation_button_num.setVisibility(View.GONE);
					
					RelativeLayout relation_ceo = (RelativeLayout)this.findViewById(R.id.department_personal_ceo);
					TextView relation_ceo_name = (TextView)relation_ceo.findViewById(R.id.company_dep_name);
					Button relation_ceo_button = (Button)relation_ceo.findViewById(R.id.company_dep_button);
					name =  GameData.getEmployeeNameByDuty(5, depart_relation.id, MainActivity.resources.getString(R.string.department_vacancy));
					relation_ceo_name.setText(MainActivity.resources.getString(R.string.department_marcom_ceo)+name);
					 
					relation_ceo_button.setBackgroundResource(name.equals(MainActivity.resources.getString(R.string.department_vacancy))?R.drawable.button_appointed:R.drawable.outgoing);
					relation_ceo_button.setOnClickListener(new OnClickListener()
					{

						public void onClick(View arg0) 
						{
							 String name =  GameData.getEmployeeNameByDuty(5, depart_relation.id, MainActivity.resources.getString(R.string.department_vacancy));
							
							 if(name.equals(MainActivity.resources.getString(R.string.department_vacancy)))//任命
							 {
								 String ename[] = new String[depart_relation.employees.length];
								 if(depart_relation.employees.length==0){
										display(MainActivity.resources.getString(R.string.ttoast1));									 
									 }else{
								 for(int i = 0 ; i < ename.length; i++)
								 {
									 ename[i] = depart_relation.employees[i].name;
								 }
									
									Builder builder = new AlertDialog.Builder(Department_relationActivity.this);
									
									builder.setItems(ename, new DialogInterface.OnClickListener()
									{

										public void onClick(DialogInterface dialog,
												int index)
										{
											 Connection.sendMessage(GameProtocol.CONNECTION_SEND_Staff_DUTY_CHANGE_REQ,
													 ConstructData.getStaffDutyChange(depart_relation.employees[index].id, (byte)5));
											//0普通职员或店员;1主管或店长;2助理;3经理;4总监;5首席
										}
									}
									).create().show(); 
							 }}
							 else//卸任
							 {
								 Connection.sendMessage(GameProtocol.CONNECTION_SEND_Staff_DUTY_CHANGE_REQ,
										 ConstructData.getStaffDutyChange(GameData.getEmployeeByDuty(5, depart_relation.id).id, (byte)0));
	/*							 Connection.sendMessage(GameProtocol.CONNECTION_SEND_Staff_DUTY_CHANGE_REQ,
										 ConstructData.getStaffDutyChange(GameData.getEmployeeByDuty(1, depart_relation.id).id, (byte)0));
	*/						 }
							
							
						}
						
					}
					
					);
					
					 
					
					
					RelativeLayout relation_director = (RelativeLayout)this.findViewById(R.id.department_personal_director);
					TextView relation_director_name = (TextView)relation_director.findViewById(R.id.company_dep_name);
					Button relation_director_button = (Button)relation_director.findViewById(R.id.company_dep_button);
					name =  GameData.getEmployeeNameByDuty(4, depart_relation.id, MainActivity.resources.getString(R.string.department_vacancy));
					relation_director_name.setText(MainActivity.resources.getString(R.string.department_director)+name);
					 
					relation_director_button.setBackgroundResource(name.equals(MainActivity.resources.getString(R.string.department_vacancy))?R.drawable.button_appointed:R.drawable.outgoing);
					relation_director_button.setOnClickListener(new OnClickListener()
					{

						public void onClick(View arg0) 
						{
							 String name =  GameData.getEmployeeNameByDuty(4, depart_relation.id, MainActivity.resources.getString(R.string.department_vacancy));
							
							 if(name.equals(MainActivity.resources.getString(R.string.department_vacancy)))//任命
							 {
								 String ename[] = new String[depart_relation.employees.length];
								 if(depart_relation.employees.length==0){
										display(MainActivity.resources.getString(R.string.ttoast1));									 
									 }else{
								 for(int i = 0 ; i < ename.length; i++)
								 {
									 ename[i] = depart_relation.employees[i].name;
								 }
									
									Builder builder = new AlertDialog.Builder(Department_relationActivity.this);
									
									builder.setItems(ename, new DialogInterface.OnClickListener()
									{

										public void onClick(DialogInterface dialog,
												int index)
										{
											 Connection.sendMessage(GameProtocol.CONNECTION_SEND_Staff_DUTY_CHANGE_REQ,
													 ConstructData.getStaffDutyChange(depart_relation.employees[index].id, (byte)4));
											//0普通职员或店员;1主管或店长;2助理;3经理;4总监;5首席
										}
									}
									).create().show(); 
							 }}
							 else//卸任
							 {
								 Connection.sendMessage(GameProtocol.CONNECTION_SEND_Staff_DUTY_CHANGE_REQ,
										 ConstructData.getStaffDutyChange(GameData.getEmployeeByDuty(4, depart_relation.id).id, (byte)0));
	/*							 Connection.sendMessage(GameProtocol.CONNECTION_SEND_Staff_DUTY_CHANGE_REQ,
										 ConstructData.getStaffDutyChange(GameData.getEmployeeByDuty(1, depart_relation.id).id, (byte)0));
	*/						 }
							
							
						}
						
					}
					
					);
					 
					
					RelativeLayout relation_manager = (RelativeLayout)this.findViewById(R.id.department_personal_manager);
					TextView relation_manager_name = (TextView)relation_manager.findViewById(R.id.company_dep_name);
					Button relation_manager_button = (Button)relation_manager.findViewById(R.id.company_dep_button);
					name =  GameData.getEmployeeNameByDuty(3, depart_relation.id, MainActivity.resources.getString(R.string.department_vacancy));
					relation_manager_name.setText(MainActivity.resources.getString(R.string.department_manager)+name);
					 
					relation_manager_button.setBackgroundResource(name.equals(MainActivity.resources.getString(R.string.department_vacancy))?R.drawable.button_appointed:R.drawable.outgoing);
					relation_manager_button.setOnClickListener(new OnClickListener()
					{

						public void onClick(View arg0) 
						{
							 String name =  GameData.getEmployeeNameByDuty(3, depart_relation.id, MainActivity.resources.getString(R.string.department_vacancy));
							
							 if(name.equals(MainActivity.resources.getString(R.string.department_vacancy)))//任命
							 {
								 String ename[] = new String[depart_relation.employees.length];
								 if(depart_relation.employees.length==0){
										display(MainActivity.resources.getString(R.string.ttoast1));									 
									 }else{
								 for(int i = 0 ; i < ename.length; i++)
								 {
									 ename[i] = depart_relation.employees[i].name;
								 }
									
									Builder builder = new AlertDialog.Builder(Department_relationActivity.this);
									
									builder.setItems(ename, new DialogInterface.OnClickListener()
									{

										public void onClick(DialogInterface dialog,
												int index)
										{
											 Connection.sendMessage(GameProtocol.CONNECTION_SEND_Staff_DUTY_CHANGE_REQ,
													 ConstructData.getStaffDutyChange(depart_relation.employees[index].id, (byte)3));
											//0普通职员或店员;1主管或店长;2助理;3经理;4总监;5首席
										}
									}
									).create().show(); 
							 }}
							 else//卸任
							 {
								 Connection.sendMessage(GameProtocol.CONNECTION_SEND_Staff_DUTY_CHANGE_REQ,
										 ConstructData.getStaffDutyChange(GameData.getEmployeeByDuty(3, depart_relation.id).id, (byte)0));
	/*							 Connection.sendMessage(GameProtocol.CONNECTION_SEND_Staff_DUTY_CHANGE_REQ,
										 ConstructData.getStaffDutyChange(GameData.getEmployeeByDuty(1, depart_relation.id).id, (byte)0));
	*/						 }
							
							
						}
						
					}
					
					);
				 
					
					RelativeLayout relation_assistant = (RelativeLayout)this.findViewById(R.id.department_personal_assistant);
					TextView relation_assistant_name = (TextView)relation_assistant.findViewById(R.id.company_dep_name);
					Button relation_assistant_button = (Button)relation_assistant.findViewById(R.id.company_dep_button);
					name =  GameData.getEmployeeNameByDuty(2, depart_relation.id, MainActivity.resources.getString(R.string.department_vacancy));
					relation_assistant_name.setText(MainActivity.resources.getString(R.string.department_assistant)+name);
					 
					relation_assistant_button.setBackgroundResource(name.equals(MainActivity.resources.getString(R.string.department_vacancy))?R.drawable.button_appointed:R.drawable.outgoing);
					relation_assistant_button.setOnClickListener(new OnClickListener()
					{

						public void onClick(View arg0) 
						{
							 String name =  GameData.getEmployeeNameByDuty(2, depart_relation.id, MainActivity.resources.getString(R.string.department_vacancy));
							
							 if(name.equals(MainActivity.resources.getString(R.string.department_vacancy)))//任命
							 {
								 String ename[] = new String[depart_relation.employees.length];
								 if(depart_relation.employees.length==0){
										display(MainActivity.resources.getString(R.string.ttoast1));									 
									 }else{
								 for(int i = 0 ; i < ename.length; i++)
								 {
									 ename[i] = depart_relation.employees[i].name;
								 }
									
									Builder builder = new AlertDialog.Builder(Department_relationActivity.this);
									
									builder.setItems(ename, new DialogInterface.OnClickListener()
									{

										public void onClick(DialogInterface dialog,
												int index)
										{
											 Connection.sendMessage(GameProtocol.CONNECTION_SEND_Staff_DUTY_CHANGE_REQ,
													 ConstructData.getStaffDutyChange(depart_relation.employees[index].id, (byte)2));
											//0普通职员或店员;1主管或店长;2助理;3经理;4总监;5首席
										}
									}
									).create().show(); 
							 }}
							 else//卸任
							 {
								 Connection.sendMessage(GameProtocol.CONNECTION_SEND_Staff_DUTY_CHANGE_REQ,
										 ConstructData.getStaffDutyChange(GameData.getEmployeeByDuty(2, depart_relation.id).id, (byte)0));
	/*							 Connection.sendMessage(GameProtocol.CONNECTION_SEND_Staff_DUTY_CHANGE_REQ,
										 ConstructData.getStaffDutyChange(GameData.getEmployeeByDuty(1, depart_relation.id).id, (byte)0));
	*/						 }
							
							
						}
						
					}
					
					);
					 
					
					RelativeLayout relation_competent = (RelativeLayout)this.findViewById(R.id.department_personal_competent);
					TextView relation_competent_name = (TextView)relation_competent.findViewById(R.id.company_dep_name);
					Button relation_competent_button = (Button)relation_competent.findViewById(R.id.company_dep_button);
					name =  GameData.getEmployeeNameByDuty(1, depart_relation.id, MainActivity.resources.getString(R.string.department_vacancy));
					relation_competent_name.setText(MainActivity.resources.getString(R.string.department_competent)+name);
					 
					relation_competent_button.setBackgroundResource(name.equals(MainActivity.resources.getString(R.string.department_vacancy))?R.drawable.button_appointed:R.drawable.outgoing);
					
					relation_competent_button.setOnClickListener(new OnClickListener()
					{

						public void onClick(View arg0) 
						{
							 String name =  GameData.getEmployeeNameByDuty(1, depart_relation.id, MainActivity.resources.getString(R.string.department_vacancy));
							
							 if(name.equals(MainActivity.resources.getString(R.string.department_vacancy)))//任命
							 {
								 String ename[] = new String[depart_relation.employees.length];
								 if(depart_relation.employees.length==0){
										display(MainActivity.resources.getString(R.string.ttoast1));									 
									 }else{
								 for(int i = 0 ; i < ename.length; i++)
								 {
									 ename[i] = depart_relation.employees[i].name;
								 }
									
									Builder builder = new AlertDialog.Builder(Department_relationActivity.this);
									
									builder.setItems(ename, new DialogInterface.OnClickListener()
									{

										public void onClick(DialogInterface dialog,
												int index)
										{
											 Connection.sendMessage(GameProtocol.CONNECTION_SEND_Staff_DUTY_CHANGE_REQ,
													 ConstructData.getStaffDutyChange(depart_relation.employees[index].id, (byte)1));
											//0普通职员或店员;1主管或店长;2助理;3经理;4总监;5首席
										}
									}
									).create().show(); 
							 }}
							 else//卸任
							 {
								 Connection.sendMessage(GameProtocol.CONNECTION_SEND_Staff_DUTY_CHANGE_REQ,
										 ConstructData.getStaffDutyChange(GameData.getEmployeeByDuty(1, depart_relation.id).id, (byte)0));
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
		 intent.setClass(Department_relationActivity.this,activityClass);
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
