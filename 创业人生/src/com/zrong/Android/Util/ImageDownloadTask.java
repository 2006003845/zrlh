package com.zrong.Android.Util;

import java.io.IOException;

import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

	public class ImageDownloadTask extends AsyncTask<Object, Object, Bitmap>{    
				
				private ImageView imageView = null;    
		        
				protected void onPreExecute() 
		        {
					super.onPreExecute();
				}

				 
				protected void onProgressUpdate(Object... values) 
				{
					 imageView.setImageBitmap(BitmapUtil.getBitmap(Integer.parseInt(String.valueOf(values[0])),0,(float)1));
					 super.onProgressUpdate(values);
				}

				protected Bitmap doInBackground(Object... params) 
		         {       
		             Bitmap bmp = null;    
		             
		             imageView = (ImageView) params[0];
		             
		             Log.v("yz", "params[1] ="+String.valueOf(params[1]));
		             bmp = BitmapUtil.getBitmap(Integer.parseInt(String.valueOf(params[1])),0,(float)1);
		             
		             if(bmp == null)
		             {
		            	 publishProgress(params[2]);
		             }
		             while(bmp ==null)
		             {
		            	 bmp = BitmapUtil.getBitmap(Integer.parseInt(String.valueOf(params[1])),0,(float)1);
		             } 
		             return bmp;    
		         }    
		             
		         protected void onPostExecute(Bitmap result){    
		        	
		             imageView.setImageBitmap(result);    
		         }    

	
}
