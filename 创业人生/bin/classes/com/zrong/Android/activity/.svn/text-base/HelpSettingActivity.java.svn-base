package com.zrong.Android.activity;

 

import java.util.ArrayList;

import java.util.HashMap;

import org.w3c.dom.Text;

 

import com.zrong.Android.Listener.ActivityTransform;
import com.zrong.Android.Util.Music;
import com.zrong.Android.Util.xmlPhaser.XElem;
import com.zrong.Android.game.Connection;
import com.zrong.Android.game.ConstructData;
import com.zrong.Android.game.GameDef;
import com.zrong.Android.online.network.GameProtocol;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class HelpSettingActivity extends GameActivity{

	
	/*public static final String  helpsetting_message = */
		
		                                /*{"建造店铺操作：",
                                         "地图——区域——新手区——选择空地——创建店铺——输入店铺名称——选择店铺规模——选择店铺种类",
                                         "店铺升级操作：",
                                         "店铺——列表——店铺详细信息界面——升级",
                                         "员工招聘操作：",
                                         "菜单——员工——招聘",
                                         "员工升级操作：",
                                         "菜单——员工——列表——选择员工——菜单——查看属性——菜单——培训——进修——确定",
                                         "员工技能培训操作：",
                                         "菜单——员工——列表——选择员工——菜单——查看属性——菜单——培训——技能培训",
                                         "员工素质培训操作：",
                                         "菜单——员工——列表——选择员工——菜单——查看属性——菜单——培训——素质培训",
                                         "聊天操作：",
                                         "打开聊天界面——选择信息输入栏——输入聊天内容——发送",
                                         "发邮件操作：",
                                         "打开邮件界面——选择写邮件——输入收件人名称——输入邮件标题——输入邮件内容——添加物品附件（可选）",
                                         "创建商会操作：",
                                         "菜单——商会——商会列表——菜单——创建商会——选择商会图标——输入商会名称"
       
		                                 };*/
 
	 
 
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
 
		
 
		this.setContentView(R.layout.about);
		TextView tv = (TextView)findViewById(R.id.about_textview);
		tv.setText(MainActivity.resources.getString(R.string.helpsetting_message));
		tv.setMovementMethod(ScrollingMovementMethod.getInstance());
	        Button title=(Button)findViewById(R.id.about_button_title);
	        title.setText(MainActivity.resources.getString(R.string.help));
	        Button turnback=(Button)findViewById(R.id.about_button_returnback);
	       
	        turnback.setOnClickListener(new Button.OnClickListener()
	        {

				 
				public void onClick(View v)
				{
					// TODO Auto-generated method stub
					HelpSettingActivity.this.finish();
				}
			});
	       
	        Button cancel=(Button)findViewById(R.id.about_button_cancel);

	        cancel.setOnClickListener(new Button.OnClickListener()
	        {
	        	public void onClick(View v)
	        	{
	        		HelpSettingActivity.this.finish();
	        	}
	        });
				
	}
	
	
	@Override
	public GameActivity getGameContext() {
		// TODO Auto-generated method stub
		return this;
	}
	
	

}

