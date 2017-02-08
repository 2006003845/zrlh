package com.zrong.Android.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ShareCircleActivity extends GameActivity {
	private TextView tv1,tv2,tv3,tv4,tv5;
	private Button returnBack,cancel;
	//链接
	private String link1 = "http://g.10086.cn/gamecms/wap/gd/fenxiangquan?tn=19&type=1";
	private String link2 = "http://gamepie.g188.net/gamecms/wap/game/gameinfo/110228156000";
	private String link3 = "http://gamepie.g188.net/gamecms/wap/game/gameinfo/110225498000";
	private String link4 = "http://gamepie.g188.net/gamecms/wap/game/gameinfo/110225498000";
	private String link5 = "http://gamepie.g188.net:80/gamecms/wap/gd/gdfxqyx?t=84731";
	
     
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.sharedcircle);
        
        returnBack = (Button) this.findViewById(R.id.sharedcircle_button_returnback);
        cancel = (Button) this.findViewById(R.id.sharedcircle_button_cancel);
        
        returnBack.setOnClickListener(new OnClickListener() {			
			 
			public void onClick(View v) {
				ShareCircleActivity.this.finish();
			}
		});
        
        cancel.setOnClickListener(new OnClickListener() {			
			 
			public void onClick(View v) {
				ShareCircleActivity.this.finish();
			}
		});
        tv1 = (TextView) this.findViewById(R.id.sharedcircle_game_tele);
        tv2 = (TextView) this.findViewById(R.id.sharedcircle_game_newest);
        tv3 = (TextView) this.findViewById(R.id.sharedcircle_game_hot);
        tv4 = (TextView) this.findViewById(R.id.sharedcircle_game_classics);
        tv5 = (TextView) this.findViewById(R.id.sharedcircle_game_more);
        
        
        Spanned spanned1 = Html.fromHtml("<a href="+"\"" + link1+"\""+" >"+"【中国移动手机游戏分享圈】</a>");
        Spanned spanned2 = Html.fromHtml("<a href='" + link2+ "'>"+"【分享圈最新游戏推荐】"+"</a>");
        Spanned spanned3 = Html.fromHtml("<a href='" + link3+ "'>"+"<u>【分享圈热门游戏推荐】</u>"+"</a>");
        Spanned spanned4 = Html.fromHtml("<a href="+"\"" + link4+"\""+" >"+"【分享圈经典游戏推荐】"+"</a>");
        Spanned spanned5 = Html.fromHtml("<a href='" + link5+ "'>"+"【更多分享圈游戏推荐】"+"</a>");
        
       tv1.setText(spanned1);
        tv1.setMovementMethod(LinkMovementMethod.getInstance());  
        tv2.setText(spanned2);
        tv2.setMovementMethod(LinkMovementMethod.getInstance());
        tv3.setText(spanned3);
        tv3.setMovementMethod(LinkMovementMethod.getInstance());
        tv4.setText(spanned4);
        tv4.setMovementMethod(LinkMovementMethod.getInstance());
        tv5.setText(spanned5);     
        tv5.setMovementMethod(LinkMovementMethod.getInstance());
       
    }

	 
	public GameActivity getGameContext() {
		// TODO Auto-generated method stub
		return this;
	}
	
	
}