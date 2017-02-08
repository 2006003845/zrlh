package com.zrong.Android.activity;


import com.zrong.Android.Util.BitmapUtil;
import com.zrong.Android.Util.ImageAdapter;
import com.zrong.Android.Util.Music;
import com.zrong.Android.Util.SystemAPI;
import com.zrong.Android.game.Connection;
import com.zrong.Android.game.ConstructData;
import com.zrong.Android.online.network.GameProtocol;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import android.widget.Toast;

public class CreateCommerceActivity extends GameActivity {
	public static CreateCommerceActivity mContext = null;
	public static int rolepicId;
	private int[]	mImageIds = new int[]{
			1379,1380,1381,1382,1383,1384,1385,1386 };
	 int h;
	 int w;
	 
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.createcommerce);
		Button turnback=(Button)findViewById(R.id.createcommerce_return);
		Button cancel = (Button)findViewById(R.id.createcommerce_cancel);
		Button confirm=(Button)findViewById(R.id.button_confirm);
		Gallery mGallery = (Gallery)findViewById(R.id.logo_gallery);
		mGallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

			 
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				rolepicId=position;
			}

			 
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}});
		
		
		
		
		Bundle bundle = this.getIntent().getExtras();
		
		String[] msg = bundle.getStringArray("msg");
		
		String s = SystemAPI.getText(msg[0])+"\n"+SystemAPI.getText(msg[1])+"\n"+SystemAPI.getText(msg[2])+"\n"+SystemAPI.getText(msg[3]);
		
		TextView tv = (TextView)findViewById(R.id.create_condition);
		
		tv.setText(s);
		ImageView imv =(ImageView)findViewById(R.id.logo_frame);
		/*imv.setMinimumWidth(10);
		imv.setMinimumHeight(10);
		imv.setMaxWidth(20);
		imv.setMaxHeight(20);
		Toast.makeText(this, "宽度"+w+"高度"+h, Toast.LENGTH_LONG).show();
		w = imv.getWidth();
		h = imv.getHeight();*/
		mGallery.setAdapter(new ImageAdapter(this));
		
		
		turnback.setOnClickListener(new Button.OnClickListener(){

			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				CreateCommerceActivity.this.finish();
			}});
		cancel.setOnClickListener(new Button.OnClickListener(){
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				CreateCommerceActivity.this.finish();
			}});
		
		confirm.setOnClickListener(new Button.OnClickListener(){
			
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				EditText et = (EditText)findViewById(R.id.commerce_name_edittext);
				String name = "";
				name = et.getText().toString();
		Connection.sendMessage(GameProtocol.CONNECTION_SEND_COfC_Create_Do_Req,ConstructData.getCOfC_Create_Do_ReqData(name,rolepicId));

				
			}});
	}
	 
	public class ImageAdapter extends BaseAdapter
	{
		private Context mycontext;  
		         
		        public ImageAdapter(Context context)
		        {  
		            this.mycontext = context; 
		        }

				 
				public int getCount() {
					// TODO Auto-generated method stub
					return mImageIds.length;
				}

				 
				public Object getItem(int position) {
					// TODO Auto-generated method stub
					return position;
				}

				 
				public long getItemId(int position) {
					// TODO Auto-generated method stub
					return position;
				}

				 
				
				 public View getView(int position, View convertView, ViewGroup parent) { 
					    
					    ImageView imageView ;
					    if (convertView == null)
						{
							// 给ImageView设置资源
							imageView = new ImageView(mycontext);
						}
						else
						{
							imageView = (ImageView) convertView;
						}
				     
				        Bitmap bitmap = BitmapUtil.getBitmap(mImageIds[position],0,(float)1);
				        imageView.setImageBitmap(bitmap);
				       //  h= imageView.getHeight();
				       //  w = imageView.getWidth();
				      
				        imageView.setPadding(50, 10, 50, 5);  				   				      
				        return imageView;   
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
