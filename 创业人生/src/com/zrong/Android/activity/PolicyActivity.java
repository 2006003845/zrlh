package com.zrong.Android.activity;

import com.zrong.Android.Util.ImageAdapter;
import com.zrong.Android.game.GameData;

import android.os.Bundle;
import android.widget.Gallery;

public class PolicyActivity extends GameActivity
{
	public PolicyActivity mContext;
	public Gallery gallery;
	
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		mContext = this;
		setContentView(R.layout.policy_dep);
		
		gallery = (Gallery) findViewById(R.id.gallery);

		ImageAdapter adapter = new ImageAdapter(this, ImageAdapter.GALLERY);
	 
		int imageId[] = new int[GameData.professionId.length];

		for (int i = 0; i < GameData.professionId.length; i++) {
			imageId[i] = GameData.getUIResId(GameData.professionId[i]);
		}

		adapter.setImageIdArray(imageId);

		gallery.setAdapter(adapter);
	}






	public GameActivity getGameContext()
	{
		// TODO Auto-generated method stub
		return mContext;
	}

}
