package com.zrong.Android.activity;



import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;



import com.zrong.Android.Util.BitmapUtil;
import com.zrong.Android.Util.Music; 
import com.zrong.Android.game.Connection;
import com.zrong.Android.game.ConstructData;
import com.zrong.Android.game.GameData;
import com.zrong.Android.online.network.GameProtocol;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class PropagandaActivity extends GameActivity {
	public static PropagandaActivity mContext = null;
	private int[] image = new int[]{834,833,832};
	private String[] str = new String[]{GameData.joinDesc[0],GameData.joinDesc[1],GameData.joinDesc[2]};
	 
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		mContext = this;
		setContentView(R.layout.propaganda);
		Button returnback = (Button)findViewById(R.id.propaganda_returnback);
		Button cancel = (Button)findViewById(R.id.propaganda_cancel);
		
		returnback.setOnClickListener(new Button.OnClickListener(){

			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				PropagandaActivity.this.finish();
				
			}});
		cancel.setOnClickListener(new Button.OnClickListener(){
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				PropagandaActivity.this.finish();
				
			}});
	/*	ImageView v= new ImageView(mContext);
		ImageView[] iv = v.setImageBitmap(bitmap);;
		 Bitmap bitmap = BitmapUtil.getBitmap(image[0],0,(float)1);*/
	     
		ListView lv = (ListView)findViewById(R.id.commercepropagandalistview);
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		HashMap<String, Object> map ;
		for(int i=0;i<image.length;i++)
		{     map = new HashMap<String, Object>();
			//map.put("logo", );
			map.put("imageId", String.valueOf(image[i]));
			map.put("detail", str[i]);
			
			list.add(map);
		}
		ImageAdapter listAdapter = new ImageAdapter(this, list,
				                   R.layout.propaganda_item,
				                   new String[]{"imageId","detail"},
				                   new int[]{R.id.propaganda_logo,R.id.propaganda_detail});
		lv.setAdapter(listAdapter);
		lv.setOnItemClickListener(new OnItemClickListener(){

			 
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				 final int idex = arg2;
				 final AlertDialog dlg2 = new AlertDialog.Builder(PropagandaActivity.this).create();
					dlg2.show();					
					dlg2.getWindow().setContentView(R.layout.dialog_new);
					Button confirm =(Button)dlg2.findViewById(R.id.button_confirm);
					Button cancel =(Button)dlg2.findViewById(R.id.button_cancel);
					TextView tv = (TextView)dlg2.findViewById(R.id.notice_message);
					tv.setTextSize(16);
					tv.setText(MainActivity.resources.getString(R.string.propagandalist_toast1)+GameData.joinMoney[arg2]+MainActivity.resources.getString(R.string.propagandalist_toast2));
					confirm.setOnClickListener(new OnClickListener() {
						
						 
						public void onClick(View v) {
							// TODO Auto-generated method stub
							Connection.sendMessage(GameProtocol.
									CONNECTION_SEND_Initiated_JointAdvocacy_Req,
									ConstructData.Initiated_JointAdvocacy_Req((byte)idex));//8宣传列表							
							dlg2.dismiss();
						}
					});
					cancel.setOnClickListener(new OnClickListener() {
						
						 
						public void onClick(View v) {
							// TODO Auto-generated method stub
							dlg2.dismiss();
						}
					});
				/*	
					AlertDialog.Builder builder = new AlertDialog.Builder(PropagandaActivity.mContext);
				builder.setMessage("是否花费"+GameData.joinMoney[arg2]+"钱进行宣传!")
				       .setPositiveButton("确定", new DialogInterface.OnClickListener(){				    	   
						 
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							Connection.sendMessage(GameProtocol.
									CONNECTION_SEND_Initiated_JointAdvocacy_Req,
									ConstructData.Initiated_JointAdvocacy_Req((byte)idex));//8宣传列表
							
							dialog.dismiss();
						}})
				       .setNegativeButton("返回", new DialogInterface.OnClickListener(){

						 
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							dialog.dismiss();
						}} );
				builder.create().show();*/
						  				
			}});
		
	}
	public class ImageAdapter extends BaseAdapter
	{
		private Context mContext;
		private ArrayList appList;
		private int layout;
		private String keyString[];
		private int[] valueViewID;
		private List<Boolean> chkb =  new ArrayList<Boolean>();
		private Holder holder;
		private AdapterView.OnItemClickListener listener;
		private LayoutInflater mInflater;		        
	    public ImageAdapter(Context context,ArrayList<HashMap<String, Object>> appList,int resource,String[] from, int[] to)
	    {  	        	        
	        mContext= context;
			this.appList = appList;
			this.layout = resource;
			keyString = new String[from.length];
			valueViewID = new int[to.length];
			System.arraycopy(from, 0, keyString, 0, from.length);
			System.arraycopy(to, 0, valueViewID, 0, to.length);
			mInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			chkb =  new ArrayList<Boolean>();
			chkb.clear();
			
			for(int i =0; i < getCount();i++)
			{
				chkb.add(false);
			}
	    }

	    public int getCount() {
			// TODO Auto-generated method stub
			return appList.size();
		}

		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return appList.get(position);
		}

		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}
				
				 
				 
		 public View getView(int position, View convertView, ViewGroup parent) { 
			   
					if(convertView != null)
					{
						holder = (Holder)convertView.getTag();
					}
					else
					{
						holder = new Holder();
						convertView = mInflater.inflate(layout, null);
						holder.image =(ImageView)convertView.findViewById(R.id.propaganda_logo);
						holder.info =(TextView)convertView.findViewById(R.id.propaganda_detail);
						
					}
					HashMap map = (HashMap)appList.get(position);
					
					Bitmap bitmap = BitmapUtil.getBitmap(Integer.parseInt(String.valueOf(map.get(keyString[0]))),0,(float)1);
					holder.image.setImageBitmap(bitmap);
					holder.info.setText((String)map.get(keyString[1]));
					convertView.setTag(holder);
					return convertView;
		 }
				
				private class Holder
				{   
					ImageView image;
					TextView info;
					 
					 
				}
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
