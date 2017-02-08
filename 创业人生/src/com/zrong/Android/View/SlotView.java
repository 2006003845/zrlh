package com.zrong.Android.View;

import java.io.IOException;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

 

import com.zrong.Android.Util.SystemAPI;
import com.zrong.Android.activity.MainActivity;
import com.zrong.Android.activity.R;
import com.zrong.Android.game.Connection;
import com.zrong.Android.game.GameDefinition;
import com.zrong.Android.game.GameGroupControl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.View;

public class SlotView extends View implements Runnable
{

	public SlotView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		 
	}
	/*源心*/
	private float originalcaleX = 0;
	
	private float originalcaleY =0;

	public SlotView(Context context, AttributeSet attrs) {
		super(context, attrs);
		//bg = new Image(((BitmapDrawable)context.getResources().getDrawable(R.drawable.bg)).getBitmap());
		 try {
			bg = Image.createImage("bg.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ScreenWidth = bg.getWidth();
		ScreenHeight = bg.getHeight();
		mBLoop = true;
        viewBitmap = Bitmap.createBitmap(ScreenWidth, ScreenHeight, Bitmap.Config.ARGB_8888);
        
        
        originalcaleX = (float) (GameDefinition.screenWidth/(float)ScreenWidth);
		originalcaleY = (float) (GameDefinition.screenHeight/(float)ScreenHeight);
		matrix = new Matrix();
		matrix.postScale(originalcaleX, originalcaleY, 0, 0);
		
		buffer = new Canvas(viewBitmap);

		
		logo = new Image[6];
		try {
			logo[0] =Image.createImage("logo0.png");
			logo[1] =Image.createImage("logo1.png");
			logo[2] =Image.createImage("logo2.png");
			logo[3] =Image.createImage("logo3.png");
			logo[4] =Image.createImage("logo4.png");
			logo[5] =Image.createImage("logo5.png");
			transImg1 = Image.createImage("logo51.png");
			transImg2 = Image.createImage("logo52.png");
			 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
			 
		 
		int Wid = /*ScreenWidth == 240 ? 73 : */52;
		widoff[0] = ScreenWidth / 2 - (Wid);
		widoff[1] = ScreenWidth / 2;
		widoff[2] = ScreenWidth / 2 + Wid;
		hei5 = ScreenHeight / 2 - 80;
		
		Thread t = new Thread(this);
		t.start();
	}

	private Graphics g;
	
	private Bitmap viewBitmap;
	
	private Canvas buffer;
	
	Matrix matrix = null;
	
	private Image bg = null;
	
	private Image[] logo;
	
	Image transImg1 = null;
	Image transImg2 = null;
	
	 
	
	
	int[] widoff =
	{
			48, 120, 190
	};
	int[] heioff0 =
	{
			16, 14, 5, 1, -3, -2, 2
	};
	int[] heioff2 =
	{
			16, 12, 2, -8, -12
	};
	int[] heioff1 =
	{
			18, 15, -2, 0
	};
	int hei5 = 72;
	public static int sleeptime = 40;
	int[][] logoInfo =
	{
			{
					2, heioff0[6], 0, heioff0[6], 1, heioff0[6], 500
			},
			{ // 完全显示第1幕
					2, heioff0[2], 0, heioff0[2], 1, heioff0[2], sleeptime
			},
			{ // 第1幕开始淡出
					2, heioff0[1], 0, heioff0[1], 1, heioff0[1], sleeptime
			},
			{
					2, heioff0[0], 0, heioff0[0], 1, heioff0[0], sleeptime, 2
			},

			{
					-1, heioff1[0], -1, heioff1[0], -1, heioff1[0], sleeptime
			},
			{ // 第2幕开始出现
					-1, heioff1[0], 5, heioff1[0], -1, heioff1[0], sleeptime
			},
			{
					-1, heioff1[0], 5, heioff1[1], -1, heioff1[0], sleeptime
			},
			{
					-1, heioff1[0], 5, heioff1[2], -1, heioff1[0], sleeptime
			},
			{
					-1, heioff1[0], 5, heioff1[3], -1, heioff1[0], sleeptime
			},
			{
					-1, heioff1[0], 5, heioff1[0], -1, heioff1[0], sleeptime
			},
			{
					-1, heioff1[0], 5, heioff1[1], 5, heioff1[0], sleeptime, 1
			},
			{
					-1, heioff1[0], 5, heioff1[2], 5, heioff1[1], sleeptime
			},
			{
					5, heioff1[0], 5, heioff1[3], 5, heioff1[2], sleeptime
			},
			{
					5, heioff1[1], 5, heioff1[0], 5, heioff1[3], sleeptime
			}
			// , { //repeat
			// 5, heioff1[2], 5, heioff1[1], 5, heioff1[0], sleeptime}
			// , {
			// 5, heioff1[3], 5, heioff1[2], 5, heioff1[1], sleeptime}
			// , {
			// 5, heioff1[0], 5, heioff1[3], 5, heioff1[2], sleeptime}
			// ,
			//
			// {
			// 5, heioff1[1], 5, heioff1[0], 5, heioff1[3], sleeptime}

			// , { //repeat
			// 5, heioff1[2], 5, heioff1[1], 5, heioff1[0], sleeptime}
			// , {
			// 5, heioff1[3], 5, heioff1[2], 5, heioff1[1], sleeptime}
			// , {
			// 5, heioff1[0], 5, heioff1[3], 5, heioff1[2], sleeptime}
			// , {
			// 5, heioff1[1], 5, heioff1[0], 5, heioff1[3], sleeptime}
			,
			{ // repeat
					5, heioff1[2], 5, heioff1[1], 5, heioff1[0], sleeptime
			},
			{
					5, heioff1[3], 5, heioff1[2], 5, heioff1[1], sleeptime
			},
			{
					5, heioff1[0], 5, heioff1[3], 5, heioff1[2], sleeptime
			},
			{
					5, heioff1[1], 5, heioff1[0], 5, heioff1[3], sleeptime
			}

			// show cmcc
			,
			{
					5, heioff1[2], 2, heioff0[0], 5, heioff1[0], sleeptime
			},
			{
					5, heioff1[3], 2, heioff0[1], 5, heioff1[1], sleeptime
			},
			{
					5, heioff1[0], 2, heioff0[2], 5, heioff1[2], sleeptime
			},
			{
					5, heioff1[1], 2, heioff0[3], 5, heioff1[3], sleeptime
			},
			{
					5, heioff1[2], 2, heioff0[4], 5, heioff1[0], sleeptime
			},
			{
					5, heioff1[3], 2, heioff0[5], 5, heioff1[1], sleeptime
			}
			// , {
			// 5, heioff1[0], 2, heioff0[6], 5, heioff1[2], sleeptime}
			// , {
			// 5, heioff1[1], 2, heioff0[6], 5, heioff1[3], sleeptime}
			// , {
			// 5, heioff1[2], 2, heioff0[6], 5, heioff1[0], sleeptime}
			// , {
			// 5, heioff1[3], 2, heioff0[6], 5, heioff1[1], sleeptime}

			// , {
			// 5, heioff1[0], 2, heioff0[6], 5, heioff1[2], sleeptime}
			// , {
			// 5, heioff1[1], 2, heioff0[6], 5, heioff1[3], sleeptime}

			// show cp
			,
			{
					3, heioff0[0], 2, heioff0[6], 5, heioff1[2], sleeptime
			},
			{
					3, heioff0[1], 2, heioff0[6], 5, heioff1[3], sleeptime
			},
			{
					3, heioff0[2], 2, heioff0[6], 5, heioff1[0], sleeptime
			},
			{
					3, heioff0[3], 2, heioff0[6], 5, heioff1[1], sleeptime
			},
			{
					3, heioff0[4], 2, heioff0[6], 5, heioff1[2], sleeptime
			},
			{
					3, heioff0[5], 2, heioff0[6], 5, heioff1[3], sleeptime
			},
			{
					// 3, heioff0[6], 2, heioff0[6], 5, heioff1[0], sleeptime}
					// , {
					// 3, heioff0[6], 2, heioff0[6], 5, heioff1[1], sleeptime}
					// , {
					// 3, heioff0[6], 2, heioff0[6], 5, heioff1[2], sleeptime}
					// , {
					// 3, heioff0[6], 2, heioff0[6], 5, heioff1[3], sleeptime}
					// , {

					// show sp
					4, heioff0[0], 2, heioff0[6], 3, heioff0[6], sleeptime
			},
			{
					4, heioff0[1], 2, heioff0[6], 3, heioff0[6], sleeptime
			},
			{
					4, heioff0[2], 2, heioff0[6], 3, heioff0[6], sleeptime
			}

			,
			{
					4, heioff0[6], 2, heioff0[6], 3, heioff0[6], 1000
			}
	// , {
	// 4, heioff0[3], 2, heioff0[6], 3, heioff0[6], sleeptime}
	// , {
	// 4, heioff0[4], 2, heioff0[6], 3, heioff0[6], sleeptime}
	// , {
	// 4, heioff0[5], 2, heioff0[6], 3, heioff0[6], sleeptime}
	// , {
	// 4, heioff0[6], 2, heioff0[6], 3, heioff0[6], 1000}

	};
	public   int index = 0; 
	public   int flag =0;
	public SlotView(Context context){
		super(context); 
	}

	 
	protected void onDraw(Canvas canvas) 
	{
	 
		super.onDraw(canvas);
		
	 
		//canvas.drawBitmap(bg, 0, 0, paint);
		 
		
		if(g == null)
		{
			g = new Graphics(buffer);
		}
		else
		{
			g.setCanvas(buffer);
		}
		
		 drawLogo(g);
		 showSplashCanvas(g);
		 canvas.setMatrix(matrix);
		 canvas.drawBitmap(viewBitmap, 0, 0, null);
		
	}
	
	private int ScreenWidth = 320;
	private int ScreenHeight = 240;
	
	public void drawLogo (Graphics g)
	{

		g.setClip(0, 0, g.getClipWidth(), g.getClipHeight());
		g.setColor(0x000000);
		g.fillRect(0, 0, g.getClipWidth(), g.getClipHeight());
		g.drawImage(bg, ScreenWidth/2, ScreenHeight/2, 3);
		for(int i = 0;i < 3;i++)
			if(logoInfo[index][i * 2] != -1)
			{
				int www = widoff[i];
				if(logoInfo[index][i * 2] == 3)
					www = widoff[0];
				else if(logoInfo[index][i * 2] == 4)
					www = widoff[2];
				if(logoInfo[index][i * 2] == 5)
				{

					int jiaozhengx[] =
					{
							0, -1, -1
					};
					if(logoInfo[index][i * 2 + 1] == heioff1[0]){
						g.drawImage(transImg1, www + jiaozhengx[i], hei5 + 55, Graphics.HCENTER | Graphics.TOP);
						}
					else if(logoInfo[index][i * 2 + 1] == heioff1[1]){
						g.drawImage(logo[logoInfo[index][i * 2]], www, hei5 + 28 + 55, 3);
						}
					else if(logoInfo[index][i * 2 + 1] == heioff1[2]){
						g.drawImage(transImg2, www + jiaozhengx[i], hei5 + 55, Graphics.HCENTER | Graphics.TOP);
						}
				}
				else{
					g.drawImage(logo[logoInfo[index][i * 2]], www, logoInfo[index][i * 2 + 1] + hei5 + 28 + 55, 3);
					}
			}
	}
	
	public void showSplashCanvas (Graphics g)
	{
//		if(inLogo)
//		{
//			sound_index++;
//			g.setClip(0, 0, ScreenWidth, ScreenHeight);
//			System.out.println("sound_index= " + sound_index);
//			if(sound_index > 70)
//			{
//				gamelogo = null;
//				logo[4] = null;
//				logo[2] = null;
//				changeStat(end);// 此处为自己游戏的状态索引。直接跳转到正常进入游戏即可
//				index = 0;
//			}
//			else if(sound_index > 40)
//			{
//				g.setColor(0xFFFFFF);
//				g.fillRect(0, 0, ScreenWidth, ScreenHeight);
//				g.drawImage(logo[4], ScreenWidth / 2, ScreenHeight / 2 - 10, 3);
//			}
//			else if(sound_index > 20)
//			{
//				g.setColor(0xFFFFFF);
//				g.fillRect(0, 0, ScreenWidth, ScreenHeight);
//				g.drawImage(logo[2], ScreenWidth / 2, ScreenHeight / 2 - 10, 3);
//			}
//			else if(sound_index > 0)
//			{
//				g.setColor(0);
//				g.fillRect(0, 0, ScreenWidth, ScreenHeight);
//				g.drawImage(gamelogo, ScreenWidth / 2, ScreenHeight / 2 - 20, 3);
//			}
//			return;
//		}
		g.setClip(0, 0, ScreenWidth, ScreenHeight);
//		if(showGameLogo)
//		{
//			g.setColor(0);
//			g.fillRect(0, 0, ScreenWidth, ScreenHeight);
//			g.drawImage(gamelogo, ScreenWidth / 2, ScreenHeight / 2 - 20, 3);
//			if(showGameLogo)
//			{
//				sound_index++;
//				if(sound_index > 20)
//				{
//					showGameLogo = false;
//					sound_index = 0;
//				}
//			}
//			return;
//		}
//		else if(showSoundAsk)
//		{
//			g.setColor(0);
//			g.fillRect(0, 0, ScreenWidth, ScreenHeight);
//			g.drawImage(gamelogo, ScreenWidth / 2, ScreenHeight / 2 - 20, 3);
//			sound_index++;
//			g.setColor(0xFF0000);
//			g.drawImage(soundask, 0, ScreenHeight, Graphics.BOTTOM | Graphics.LEFT);
//			byte a = -1;
////			if(UIManager.isKeyPressed(UIManager.KEY_SOFTKEY1)||UIManager.isPointerPressed(0,SysDef.SCREEN_H-UIManager.POINTER_PRESS_H, UIManager.POINTER_PRESS_W, UIManager.POINTER_PRESS_H))
////			{
//////				Game.instance.playSound = true;// 此处为自己声音开启的索引
//////				enableSoundEffect = true;
//////				showLogoEffect = true;
//////				showSoundAsk = false;
//////				Game.sound.playSound = true;
//////				Game.sound.createSound(3, false, 1, 1); 
////				a = 1;
////			}
////			else if(UIManager.isKeyPressed(UIManager.KEY_SOFTKEY2)||UIManager.isPointerPressed(SysDef.SCREEN_W-UIManager.POINTER_PRESS_W,SysDef.SCREEN_H-UIManager.POINTER_PRESS_H, UIManager.POINTER_PRESS_W, UIManager.POINTER_PRESS_H))
////			{
//////				Game.instance.playSound = false;// 此处为自己声音开启的索引
//////				enableSoundEffect = false;
//////				showLogoEffect = true;
//////				showSoundAsk = false;
////				a = 2;
////			}
////			
////			if(a == 1)
////			{
////				Game.instance.playSound = true;// 此处为自己声音开启的索引
////				enableSoundEffect = true;
////				showLogoEffect = true;
////				showSoundAsk = false;
////				Game.sound.playSound = true;
////				//Game.sound.createSound(3, false, 1, 1); 
////				Game.sound.init(0, 1);
////				Game.sound.start(1);
////				a = -1;
////			}
////			else if(a == 2)
////			{
////				Game.instance.playSound = false;// 此处为自己声音开启的索引
////				enableSoundEffect = false;
////				showLogoEffect = true;
////				showSoundAsk = false;
////				a = -1;
////			}
//			
//			
//			return;
//		}

		if(true)
		{
 
			
 
			

			if(index >= logoInfo.length - 1)
			{  
				index = logoInfo.length - 1;
				
				flag++;
				if(flag >50)
				{
					mBLoop = false;
					
					GameGroupControl.gameGroupControl.setGameStatus(GameDefinition.Game_MainMenu,null);
					MainActivity.mContext.setContentView(GameGroupControl.gameGroupControl);
					
					 
				}
				
			}
			else
			{
				index++;
			}
			 
			return;
		}
		else
		{
//			g.setColor(0);
//			g.fillRect(0, 0, g.getClipWidth(), g.getClipHeight());
//			this.drawLogo(g);
//			sound_index++;
//			if(sound_index > 20)
//			{
//				if(enableSoundEffect)
//				{			
//					//#ifdef Android
//					//# if(Game.sound.getPlayerState() == Sound.UNREALIZED)
//					//#else
//						if(Game.sound.getPlayerState() == Sound.PREFETCHED)
//					//#endif
//					{
//						changeStat(end);// 此处为自己游戏的状态索引。直接跳转到正常进入游戏即可																	
//						gamelogo = null;
//						soundask = null;
//						bg = null;
//						logo = null;
//						transImg1 = null;
//						transImg2 = null;
//						index = 0;
//					}
//				}
//				else
//				{
//					changeStat(end);
//					gamelogo = null;
//					soundask = null;
//					bg = null;
//					logo = null;
//					transImg1 = null;
//					transImg2 = null;
//					index = 0;
//
//				}
//			}
		}
	}
	
	/**
	 * 是否开启线程
	 */
	public boolean mBLoop = false;

	public void run() {
		while (mBLoop)
		{
			SystemAPI.sleep(60);
			
			postInvalidate(); //刷新屏幕 
		}
		
	}
	
}
