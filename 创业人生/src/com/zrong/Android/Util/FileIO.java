package com.zrong.Android.Util;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.zrong.Android.game.GameDefinition;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

//文件IO类
public class FileIO
{
	public static final String tag="FileIO";
	//GBK编码方式
	public static final String GBK_CODE="GBK";
	//UTF-8编码方式
	public static final String UTF8_CODE="UTP-8";
	
	/**
	 * 读取文本文件
	 * @param originalObject 载有资源包的类对象
	 * @param resURL 文件在资源包中的URL
	 * @param textCodeMethod 文字编码方式
	 * @return 编码后的文本
	 */
	public static String readFileText(Object originalObject,String resURL,String textCodeType){
		InputStream is=originalObject.getClass().getResourceAsStream(resURL);
		try{
			DataInputStream dis=new DataInputStream(is);
			ByteArrayOutputStream baos=new ByteArrayOutputStream();
			int ch;

			while ((ch = dis.read()) != -1)
				baos.write(ch);
			
			dis.close();
			
			return new String(baos.toByteArray(),textCodeType);
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * 把assets文件夹下的资源写入到sdcard中
	 */
	public static void getAssets(Context context){
		try {
			/** 
			 * 创建sdcard存储文件的目录
			 */
			File files =Environment.getExternalStorageDirectory();
			String str =files.toString();
			
			String [] filenames = context.getAssets().list("video");
			
			if(filenames != null)
			{
				for(int i = 0; i < filenames.length; i++)
				{
					InputStream is =context.getAssets().open("video/"+filenames[i]);
					
					FileOperation.writeToSDCardFromInputStream("/video",filenames[i],is);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	 
}

