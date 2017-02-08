package com.zrong.Android.Util;

import com.zrong.Android.activity.R;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

public class SelectImageDownLoadTask extends AsyncTask<Object, Object, Bitmap>{

	private ImageView imageView = null;
	
	private Bitmap SelectBitmap = null;
	
    
	protected void onPreExecute() 
    {
		super.onPreExecute();
	}

	 
	protected void onProgressUpdate(Object... values) 
	{	 
		 Bitmap bitmap = BitmapUtil.getBitmap(Integer.parseInt(String.valueOf(values[0])),0,(float)1);
		 imageView.setImageBitmap(BitmapUtil.createSelectImage(bitmap, SelectBitmap));
		 super.onProgressUpdate(values);
	}

	 protected Bitmap doInBackground(Object... params) 
     {       
         Bitmap bmp = null;    
         
         imageView = (ImageView) params[0];
         
         SelectBitmap = (Bitmap)params[1];
         bmp = BitmapUtil.getBitmap(Integer.parseInt(String.valueOf(params[2])),0,(float)1);
         
         if(bmp == null)
         {
        	 publishProgress(params[3]);
         }
         while(bmp ==null)
         {
        	 bmp = BitmapUtil.getBitmap(Integer.parseInt(String.valueOf(params[2])),0,(float)1);
         } 
         return bmp;    
     }    
         
     protected void onPostExecute(Bitmap result)
     {
         imageView.setImageBitmap(BitmapUtil.createSelectImage(result, SelectBitmap));    
     }    

}
