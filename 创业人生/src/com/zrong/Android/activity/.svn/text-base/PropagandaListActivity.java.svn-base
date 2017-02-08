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

import android.R.string;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class PropagandaListActivity extends GameActivity{
	public static PropagandaListActivity mContext;
	private int[]	mImageIds = new int[]{
			1379,1380,1381,1382,1383,1384,1385,1386 };
	 
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		mContext = this;
		setContentView(R.layout.propagandalist);
		//String [] menu = new String[GameData.pro.size];
		ListView lv = (ListView)findViewById(R.id.propagandalist_listview);
		Button returnback = (Button)findViewById(R.id.propagandalist_button_returnback);
		Button cancel = (Button)findViewById(R.id.propagandalist_button_cancel);
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> map;
		if (GameData.pro!=null&&GameData.pro.length>0){
		 for (int i = 0; i <GameData.pro.length; i++) 
		 {
			 map = new HashMap<String, Object>();
			 
		     map.put("image",String.valueOf(GameData.pro[i].resid) );
			 map.put("desc", GameData.pro[i].desc);
			 list.add(map);
		 }

		 ImageAdapter listAdapter = new ImageAdapter(this,list,   
	                R.layout.propagandalist_item, new String[] {"image","desc"},   
	                new int[] {R.id.propagandalist_logo,R.id.propagandalist_detail});   
	  
	        lv.setAdapter(listAdapter);
		}else{
			Toast.makeText(this, MainActivity.resources.getString(R.string.propagandalist_toast),Toast.LENGTH_SHORT ).show();
			}
	    returnback.setOnClickListener(new Button.OnClickListener(){

			 
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				PropagandaListActivity.mContext.finish();
				
			}});
	    cancel.setOnClickListener(new Button.OnClickListener(){
	    	
	    	 
	    	public void onClick(View arg0) {
	    		// TODO Auto-generated method stub
	    		PropagandaListActivity.mContext.finish();
	    		
	    	}});
	    lv.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView<?> arg0, View arg1, int index,
					long arg3) {
				// TODO Auto-generated method stub
				 final int idex = index;
				 final long position = arg3;
				 final AlertDialog dlg2 = new AlertDialog.Builder(PropagandaListActivity.this).create();
					dlg2.show();					
					dlg2.getWindow().setContentView(R.layout.dialog_new);
					Button confirm =(Button)dlg2.findViewById(R.id.button_confirm);
					Button cancel =(Button)dlg2.findViewById(R.id.button_cancel);
					TextView tv = (TextView)dlg2.findViewById(R.id.notice_message);
					tv.setTextSize(16);
					tv.setText(MainActivity.resources.getString(R.string.propagandalist_toast1)+GameData.pro[index].money+MainActivity.resources.getString(R.string.propagandalist_toast2));
				    confirm.setOnClickListener(new OnClickListener() {
						
						 
						public void onClick(View v) {
							// TODO Auto-generated method stub
							Connection.sendMessage(GameProtocol.
									CONNECTION_SEND_JointAdvocacy_Req,
									ConstructData.Join_JointAdvocacy_Req(GameData.pro[idex].id,0));//8宣传列表
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
				AlertDialog.Builder builder = new AlertDialog.Builder(PropagandaListActivity.mContext);
				builder.setMessage(MainActivity.resources.getString(R.string.propagandalist_toast1)+GameData.pro[index].money+MainActivity.resources.getString(R.string.propagandalist_toast2))
				       .setPositiveButton(MainActivity.resources.getString(R.string.dialog_ok), new DialogInterface.OnClickListener(){				    	   
						
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							Connection.sendMessage(GameProtocol.
									CONNECTION_SEND_JointAdvocacy_Req,
									ConstructData.Join_JointAdvocacy_Req(GameData.pro[idex].id));//8宣传列表
							
							dialog.dismiss();
						}})
				       .setNegativeButton(MainActivity.resources.getString(R.string.dialog_return), new DialogInterface.OnClickListener(){

						 
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
						holder.image =(ImageView)convertView.findViewById(R.id.propagandalist_logo);
						holder.info =(TextView)convertView.findViewById(R.id.propagandalist_detail);
						
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
