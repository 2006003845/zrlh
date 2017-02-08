package com.zrong.Android.activity;

import java.util.ArrayList;
import java.util.HashMap;

import com.zrong.Android.Util.Music;
import com.zrong.Android.element.Employee;
import com.zrong.Android.element.Social;
import com.zrong.Android.game.AnalysisData;
import com.zrong.Android.game.Connection;
import com.zrong.Android.game.ConstructData;
import com.zrong.Android.game.GameData;
import com.zrong.Android.online.network.GameProtocol;

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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;

public class DiglistActivity extends GameActivity{

	
	public static DiglistActivity mContext = null; 
	public static Employee e = null;
	
	private ListView list;
	/**
	 * 1人才市场2猎头中心
	 */
	private byte type ;
	
	private String staffname;
	
	private String mastername;
	
	private byte size;
	
	private byte[] office;
	
	private byte[] tType;
	
	private byte[] level;
	
	private int begin;
	
	private int count;
	private boolean isfromSocial =false;
	private boolean isfromPlanningdept = false;
	
	
	
	 
	protected void onCreate(Bundle savedInstanceState) 
	{
		 
		super.onCreate(savedInstanceState);
		
		mContext = this;
		
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		this.setContentView(R.layout.diglist);
		
		Bundle bundle = this.getIntent().getExtras();
		
		type = bundle.getByte("type");
		
		staffname = bundle.getString("staffname");
		
		mastername = bundle.getString("mastername");
		
		size = bundle.getByte("size");
		
		office = bundle.getByteArray("office");
		
		tType = bundle.getByteArray("tType");
		
		level = bundle.getByteArray("level");
		
		begin = bundle.getInt("begin");
		
		count = bundle.getInt("count");
		isfromSocial = bundle.get("fromsocial") !=null;
		isfromPlanningdept = bundle.get("fromPlanningDept") !=null;
		
		Button title = (Button)this.findViewById(R.id.diglist_button_title);
		Button refresh = (Button)this.findViewById(R.id.diglist_refresh);
		if( !isfromSocial&&!isfromPlanningdept){
		if(type ==1)
		{
			title.setText(MainActivity.resources.getString(R.string.diglist_talentmarket));
		}
		else if(type ==2)
		{
			title.setText(MainActivity.resources.getString(R.string.diglist_huntingcenter));
			//refresh.setVisibility(View.INVISIBLE);
		}
		else if(type == 3)
		{
			title.setText(MainActivity.resources.getString(R.string.diglist_huntingcenter));
			//refresh.setVisibility(View.INVISIBLE);
		}
		}else if(isfromSocial ||isfromPlanningdept)
		{
			if(type == 3){
				title.setText(MainActivity.resources.getString(R.string.staff_list));	
				refresh.setVisibility(View.GONE);
			}
		}	
		 
		
		Button returnback = (Button)this.findViewById(R.id.diglist_button_returnback);
		Button cancel = (Button)this.findViewById(R.id.diglist_button_cancel);
		
	
		
		returnback.setOnClickListener(new OnClickListener()
		{

			public void onClick(View arg0)
			{
				DiglistActivity.this.finish();
				
				
			}
		}
		);
		cancel.setOnClickListener(new OnClickListener()
		{
			
			public void onClick(View arg0)
			{
				
				DiglistActivity.this.finish();
				
			}
		}
		);
		
		refresh.setOnClickListener(new OnClickListener()
		{
			public void onClick(View arg0)
			{
				begin = begin+count;
				Connection.sendMessage(GameProtocol.REQSearchStaff_Req, ConstructData.SearchStaff_Req(type, staffname, mastername, size, office, tType, level, begin, count));
			}
		}
		);
		
		list = (ListView)this.findViewById(R.id.diglist_listview);
		
		initList();
		
		list.setOnItemClickListener(new AdapterView.OnItemClickListener()
		{

			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) 
			{
				 if(! isfromSocial&&!isfromPlanningdept){
				String [] item = new String[]{MainActivity.resources.getString(R.string.diglist_menudetail),MainActivity.resources.getString(R.string.diglist_menuemployee)};
				
				if(type ==2||type ==3)
				{
					item[1] = MainActivity.resources.getString(R.string.diglist_menuemployee_dig);
				}
				
				final int p = position;
				
				Log.i("Diglist", ""+p);
				
				final AlertDialog dlg = new AlertDialog.Builder(DiglistActivity.this).create();
				dlg.show();					
				dlg.getWindow().setContentView(R.layout.pm_emoloyeelist);
				
				
				ListView lv = (ListView)dlg.findViewById(R.id.pm_emoloyeelist_listview);
				
				ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
										
										for(int i =0; i < item.length; i++)
										{
											HashMap<String, Object> map = new HashMap<String, Object>();
											map.put("name", item[i]);
											list.add(map);
										}
										
										SimpleAdapter adpter = new SimpleAdapter(DiglistActivity.this,
												list, R.layout.pm_employeelist_item, new String[] { "name" }, new int[] {
												R.id.pm_employeelist_item_name });
										lv.setAdapter(adpter);
										
										 
										
										lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
										{
											 
											public void onItemClick(AdapterView<?> parent,
													View view, int position, long id) 
											{
												

												 if(position ==0)
												 {
													 Intent intent = new Intent();
													 
													 Bundle bundle = new Bundle();
													 
													 bundle.putByte("type", (byte)2);
													 
													 bundle.putByte("index", (byte)p);
													 
													 intent.putExtras(bundle);
													 
													 intent.setClass(DiglistActivity.this, EmployeeInfoActivity.class);
													 
													
													 
													 DiglistActivity.this.startActivity(intent);
												 }
												 else if(position == 1)
												 {
													 
													 final Employee e = (Employee)GameData.diglist.elementAt(p);
													 if(type ==1)
													 {
															Connection.sendMessage(GameProtocol.REQ_TradeStaff_Req, ConstructData.REQ_TradeStaff((byte)3,e.id,e.price)); 
													 }
													 else
													 {
													 String[] item = new String[8];
													 
													 for(int i =0; i < item.length; i++)
													 {
														 item[i] = MainActivity.resources.getString(R.string.diglist_menuemployee_dig_cost)+getPay(e,i+1)+MainActivity.resources.getString(R.string.diglist_menuemployee_dig_successrate)+((i+1)*10)+"%";
													 }
													 
													 
													 //创建dlg
													 final AlertDialog dlg = new AlertDialog.Builder(DiglistActivity.this).create();
													 dlg.show();					
													 dlg.getWindow().setContentView(R.layout.pm_emoloyeelist);

													 //向listview中添加数据
													 ListView lv = (ListView)dlg.findViewById(R.id.pm_emoloyeelist_listview);
													 
													 lv.setLayoutParams(new RelativeLayout.LayoutParams(480,RelativeLayout.LayoutParams.WRAP_CONTENT));
													 						
													 ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
													 						
							 						for(int i =0; i < item.length; i++)
							 						{
							 							HashMap<String, Object> map = new HashMap<String, Object>();
							 							map.put("name", item[i]);
							 							list.add(map);
							 						}
													 						
						 						SimpleAdapter adpter = new SimpleAdapter(DiglistActivity.this,
						 								list, R.layout.pm_employeelist_item, new String[] { "name" }, new int[] {
						 								R.id.pm_employeelist_item_name });
						 						lv.setAdapter(adpter);

						 						//listview响应
						 						lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
						 						{
						 							 
						 							public void onItemClick(AdapterView<?> parent,
						 									View view, int position, long id) 
						 							{
						 								Connection.sendMessage(GameProtocol.REQ_FINDEMPLOY,ConstructData.get_earchPeople(e.id, getPay(e,position+1)));
						 								dlg.dismiss();
						 								
						 							}
						 							
						 						}
						 						
						 						);
													 
//													 Builder builder = new AlertDialog.Builder(DiglistActivity.this);
//													 
//													 builder.setItems(item, new DialogInterface.OnClickListener()
//													 {
//
//														public void onClick(DialogInterface arg0,
//																int index) 
//														{
//															Connection.sendMessage(GameProtocol.REQ_FINDEMPLOY,ConstructData.get_earchPeople(e.id, getPay(e,index+1)));
//														}
//														 
//													 }
//													 
//													 ).create().show();
//													 }
													// Connection.sendMessage(GameProtocol.REQ_FINDEMPLOY,ConstructData.get_earchPeople(employee.id, empWeekly.salary));
												 }
													dlg.dismiss();
												
											}
											
											}
										}
										);
				
//				Builder builder = new AlertDialog.Builder(DiglistActivity.this);
//				
//				builder.setItems(item, new DialogInterface.OnClickListener()
//				{
//
//					public void onClick(DialogInterface dialog, int index)
//					{
//						 if(index ==0)
//						 {
//							 Intent intent = new Intent();
//							 
//							 Bundle bundle = new Bundle();
//							 
//							 bundle.putByte("type", (byte)2);
//							 
//							 bundle.putByte("index", (byte)p);
//							 
//							 intent.putExtras(bundle);
//							 
//							 intent.setClass(DiglistActivity.this, EmployeeInfoActivity.class);
//							 
//							
//							 
//							 DiglistActivity.this.startActivity(intent);
//						 }
//						 else if(index == 1)
//						 {
//							 
//							 final Employee e = (Employee)GameData.diglist.elementAt(p);
//							 if(type ==1)
//							 {
//									Connection.sendMessage(GameProtocol.REQ_TradeStaff_Req, ConstructData.REQ_TradeStaff((byte)3,e.id,e.price)); 
//							 }
//							 else
//							 {
//							 String[] item = new String[8];
//							 
//							 for(int i =0; i < item.length; i++)
//							 {
//								 item[i] = MainActivity.resources.getString(R.string.diglist_menuemployee_dig_cost)+getPay(e,i+1)+MainActivity.resources.getString(R.string.diglist_menuemployee_dig_successrate)+((i+1)*10)+"%";
//							 }
//							 Builder builder = new AlertDialog.Builder(DiglistActivity.this);
//							 
//							 builder.setItems(item, new DialogInterface.OnClickListener()
//							 {
//
//								public void onClick(DialogInterface arg0,
//										int index) 
//								{
//									Connection.sendMessage(GameProtocol.REQ_FINDEMPLOY,ConstructData.get_earchPeople(e.id, getPay(e,index+1)));
//								}
//								 
//							 }
//							 
//							 ).create().show();
//							 }
//							// Connection.sendMessage(GameProtocol.REQ_FINDEMPLOY,ConstructData.get_earchPeople(employee.id, empWeekly.salary));
//						 }
//						
//					}
//					
//				}
//				).create().show();
				
			}else if(isfromSocial||isfromPlanningdept)
			{    
				Log.i("juj","Vector的长度是："+GameData.friend.size());
				Social s = (Social) GameData.friend.elementAt(SocialActivity.friend_ID);
				e = (Employee)GameData.diglist.elementAt(position);
				Connection.sendMessage(
						GameProtocol.PLANNINGACTION_REQ,
						ConstructData.getPlanningAction((byte)PlanningDeptActivity.pd_index,(long)s.id,(byte)1,new long[]{e.id}));
				
			}
		}
		}
		);
		 
	}
	 public static int getPay(Employee e,int changeMarginChan){
	    	
	    	//需要薪水=原始薪水*（2*目标成功率-主角策略值/10000+目标忠诚度*2）^5
		 // 成功率=((目标薪水/员工薪水)^(1/5)-(员工忠诚/员工忠诚上限*2)+(主角策略值/10000))/2
	    	double dpay = 0d;
//	    	double drate = ((Integer)rate).doubleValue();//成功比率
//	    	double dweekly = ((Integer)staff).doubleValue();//薪水
//	    	double dfaith = ((Integer)staff.getFaith()).doubleValue();//忠诚度
//	    	double dmaxfaith = ((Integer)staff.getMaxFaith()).doubleValue();//忠诚度最大值
//	    	double dstrategy = ((Integer)Game.i.getStrategy()).doubleValue();//主角策略之
	    	
	    	double dsalary = (double)e.salary ;//薪水
	    	double drate = (double)changeMarginChan*10;//成功比率
	    	double dstrategy = GameData.player.strategy;//主角策略值
	    	double  dfaith= e.loyalty ;//目标忠诚度
	    	double dMaxfaith = e.maxLoyalty ;//最大忠诚度
	    	
//	    	dpay = dsalary * Math.pow(2*drate/100-dstrategy*2/10000+dfaith*2/dMaxfaith,5);
	    	double dd = 1d;
	    	for(int i=0;i<5;i++)
	    	{
	    		dd *= (2*drate/100-dstrategy*2/10000+dfaith*2/dMaxfaith);
	    	}
	    	dpay = dsalary *dd ;
	    	if(dpay < 2*e.salary)
	    		dpay = 2*e.salary;
	    	return (int)dpay/2;
	    }
	public void initList()
	{
		ArrayList<HashMap<String, Object>> arraylist = new ArrayList<HashMap<String,Object>>();
		
		HashMap map = null;
  //    if(SocialActivity.mContext == null)
      {
		String nametag = MainActivity.resources.getString(R.string.diglist_name);
		String leveltag = MainActivity.resources.getString(R.string.diglist_rate);
		String loyaltytag = MainActivity.resources.getString(R.string.diglist_loyalty);
		String companytag =MainActivity.resources.getString(R.string.diglist_companyname);

		//Employee e = null;
		for(int i = 0; i < GameData.diglist.size(); i++)
		{
			e = (Employee)GameData.diglist.elementAt(i);
			map = new HashMap<String,Object>();
			

			map.put(MainActivity.resources.getString(R.string.diglist_name), nametag+e.name);
			map.put(MainActivity.resources.getString(R.string.diglist_rate), leveltag+e.level);
			map.put(MainActivity.resources.getString(R.string.diglist_loyalty), loyaltytag+e.loyalty);
			map.put(MainActivity.resources.getString(R.string.diglist_company), companytag+e.empCompany);

			arraylist.add(map);
		}
		
		SimpleAdapter listAdapter = new SimpleAdapter(DiglistActivity.this,arraylist,   

                R.layout.diglist_item, new String[] {MainActivity.resources.getString(R.string.diglist_name),MainActivity.resources.getString(R.string.diglist_rate),MainActivity.resources.getString(R.string.diglist_loyalty),MainActivity.resources.getString(R.string.diglist_company)},   
                new int[] {R.id.diglist_item_name,R.id.diglist_item_level,R.id.diglist_item_loyalty,R.id.diglist_item_company});

      
		list.setAdapter(listAdapter);
      }/*else if(SocialActivity.mContext != null)
      {

  		String nametag = MainActivity.resources.getString(R.string.diglist_name);
  		String leveltag = MainActivity.resources.getString(R.string.diglist_rate);
  		String loyaltytag = MainActivity.resources.getString(R.string.diglist_loyalty);
  		String companytag =MainActivity.resources.getString(R.string.diglist_companyname);

  		Employee e = null;
  		for(int i = 0; i < AnalysisData.staff_NUM; i++)
  		{
  			e = (Employee)GameData.diglist.elementAt(i);
  			map = new HashMap<String,Object>();
  			

  			map.put(MainActivity.resources.getString(R.string.diglist_name), nametag+e.name);
  			map.put(MainActivity.resources.getString(R.string.diglist_rate), leveltag+e.level);
  			map.put(MainActivity.resources.getString(R.string.diglist_loyalty), loyaltytag+e.loyalty);
  			map.put(MainActivity.resources.getString(R.string.diglist_company), companytag+e.empCompany);

  			arraylist.add(map);
  			Log.i("name", e.name);
  		}
  		
  		SimpleAdapter listAdapter = new SimpleAdapter(DiglistActivity.this,arraylist,   

                  R.layout.diglist_item, new String[] {MainActivity.resources.getString(R.string.diglist_name),MainActivity.resources.getString(R.string.diglist_rate),MainActivity.resources.getString(R.string.diglist_loyalty),MainActivity.resources.getString(R.string.diglist_company)},   
                  new int[] {R.id.diglist_item_name,R.id.diglist_item_level,R.id.diglist_item_loyalty,R.id.diglist_item_company});

        
  		list.setAdapter(listAdapter);  
      }*/
	}
	
	
	
	 
	public GameActivity getGameContext() {
		// TODO Auto-generated method stub
		return this;
	}
	
	 
	public void finish() 
	{
		mContext = null;
		super.finish();
	}
	
}
