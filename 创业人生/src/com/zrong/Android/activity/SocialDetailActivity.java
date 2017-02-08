package com.zrong.Android.activity;

import com.zrong.Android.Util.Music;
import com.zrong.Android.View.SpriteView;
import com.zrong.Android.element.Social;
import com.zrong.Android.game.Connection;
import com.zrong.Android.game.ConstructData;
import com.zrong.Android.game.GameData;
import com.zrong.Android.online.network.GameProtocol;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SocialDetailActivity extends GameActivity{

	public static SocialDetailActivity mContext = null;
	
	private Social social;
	
	private SpriteView sv;
	
	private TextView name;
	private TextView level;
	private TextView sex;
	private TextView companyName;
	private TextView shopNum;
	private TextView companyLevel;
	private TextView employeeNum;
	private TextView companyAssert;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		mContext = this;
		
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		Bundle bundle = this.getIntent().getExtras();
		byte type = bundle.getByte("type");
		
		int index = bundle.getInt("index");
		
		if(type == 1)//好友
		{
			social = (Social)GameData.friend.elementAt(index);
		}
		else if(type == 2)//黑名单
		{
			social = (Social)GameData.blacklist.elementAt(index);
		}
		else if(type == 3)//陌生者
		{
			social = (Social)GameData.stranger.elementAt(index);
		}
		else if(type == 4)//竞争者
		{
			social = (Social)GameData.competitor.elementAt(index);
		}
		else if(type == 5)//搜索好友
		{
			social = GameData.seachPlayer[index];
		}
		this.setContentView(R.layout.social_detail);
		Button returnback = (Button)this.findViewById(R.id.social_detail_button_returnback);
		Button cancel = (Button)this.findViewById(R.id.social_detail_button_cancel);
		
		returnback.setOnClickListener(new OnClickListener()
		{

			public void onClick(View arg0) 
			{
				 
				SocialDetailActivity.this.finish();
			}
			
		}
		);
		cancel.setOnClickListener(new OnClickListener()
		{
			
			public void onClick(View arg0) 
			{
				
				SocialDetailActivity.this.finish();
			}
			
		}
		);
		
		Button seach = (Button)this.findViewById(R.id.social_detail_dig);
		
		seach.setOnClickListener(new OnClickListener()
		{
			public void onClick(View arg0) 
			{
				// TODO Auto-generated method stub

				Intent intent = new Intent();
				
				Bundle bundle = new Bundle();
				
				bundle.putByte("type", (byte)3);
				
				bundle.putString("staffname", social.name);
				
				bundle.putString("mastername", "");
				
				bundle.putByte("size", (byte)1);
				
				bundle.putByteArray("office", new byte[]{0});
				
				bundle.putByteArray("tType", new byte[]{0});
				
				bundle.putByteArray("level", new byte[]{0});
				
				bundle.putInt("begin", 0);
				
				bundle.putInt("count", 50);
				
				intent.putExtras(bundle);
				
				intent.setClass(SocialDetailActivity.this, DiglistActivity.class);
				
				SocialDetailActivity.this.startActivity(intent);
				
				Connection.sendMessage(GameProtocol.REQSearchStaff_Req, ConstructData.SearchStaff_Req((byte)3, social.name, "", (byte)1, new byte[]{0}, new byte[]{0}, new byte[]{0}, 0, 50));
			}
			
		}
		);
		
		
		sv = (SpriteView)this.findViewById(R.id.social_detail_head);
		
		sv.setSeries(social.headId);
		
		
		name = (TextView)this.findViewById(R.id.social_detail_name);
		
		level = (TextView)this.findViewById(R.id.social_detail_level);
		
		sex = (TextView)this.findViewById(R.id.social_detail_sex);
		
		name.setText(MainActivity.resources.getString(R.string.socialdetail_name)+social.name);
		
		level.setText(MainActivity.resources.getString(R.string.socialdetail_level)+social.level);
		
		sex.setText(MainActivity.resources.getString(R.string.socialdetail_sex)+(social.sex==0?MainActivity.resources.getString(R.string.socialdetail_man):MainActivity.resources.getString(R.string.socialdetail_woman)));
		
		companyName = (TextView)this.findViewById(R.id.social_detail_company_name);
		
		shopNum = (TextView)this.findViewById(R.id.social_detail_shopnum);
		
		companyLevel =(TextView)this.findViewById(R.id.social_detail_company_level);
		
		employeeNum = (TextView)this.findViewById(R.id.social_detail_employeenum);
		
		companyAssert = (TextView)this.findViewById(R.id.social_detail_assert);
		
		companyName.setText(MainActivity.resources.getString(R.string.socialdetail_companyname)+social.companyName);
		
		shopNum.setText( MainActivity.resources.getString(R.string.socialdetail_shopnum)+social.currShopCount+"/"+social.maxShopCount);
		
		companyLevel.setText(MainActivity.resources.getString(R.string.socialdetail_companylevel)+social.companyLevel);
		
		employeeNum.setText( MainActivity.resources.getString(R.string.socialdetail_employeenum)+social.currEmployeeCount+"/"+social.maxEmployeeCount);
		
		companyAssert.setText(MainActivity.resources.getString(R.string.socialdetail_companyasset)+social.companyAsset);
		
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
