package com.zrong.Android.activity;

import java.util.Vector;

import com.zrong.Android.Util.ImageAdapter;
import com.zrong.Android.Util.Music;
import com.zrong.Android.element.Employee;
import com.zrong.Android.game.Connection;
import com.zrong.Android.game.ConstructData;
import com.zrong.Android.game.GameData;
import com.zrong.Android.online.network.GameProtocol;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class CreateBuildingActivity extends GameActivity {

	public static CreateBuildingActivity mContext = null;

	private Gallery gallery;

	private int cityX;

	private int cityY;
	
	private int [] canBuildIndex;

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		mContext = this;
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		this.setContentView(R.layout.createbuilding);

		Bundle bundle = new Bundle();

		cityX = bundle.getInt("cityX");

		cityY = bundle.getInt("cityY");

		gallery = (Gallery) findViewById(R.id.createbuilding_gallery01);

		ImageAdapter adapter = new ImageAdapter(this,
				ImageAdapter.SPRITEGALLERY);
		
		canBuildIndex = GameData.getIndex_accessBuilding();
		

		int imageId[] = new int[canBuildIndex.length];

		for (int i = 0; i < canBuildIndex.length; i++) {
			imageId[i] = GameData.getUIResId(GameData.buildingId[canBuildIndex[i]]);
		}

		adapter.setImageIdArray(imageId);

		gallery.setAdapter(adapter);

		gallery.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) 
			{

				Toast.makeText(
						CreateBuildingActivity.mContext,

						MainActivity.resources.getString(R.string.createbuilding_toast_choose) + GameData.buildingName[position]
								+ MainActivity.resources.getString(R.string.createbuilding_toast_employ), Toast.LENGTH_SHORT).show();

				final Vector vector = new Vector();

				for (int i = 0; i < GameData.corporation.employee.length; i++) {
					if (GameData.corporation.employee[i].department <= 0) {
						vector.addElement(i);
					}
				}

				if (vector.isEmpty()) {
					Toast.makeText(CreateBuildingActivity.mContext,
							MainActivity.resources.getString(R.string.createbuilding_toast_message), Toast.LENGTH_SHORT).show();
				}

				String[] name = new String[vector.size()];
				if(name.length ==0){
					return;
				}else{
				for (int i = 0; i < name.length; i++) {
					name[i] = GameData.corporation.employee[Integer
							.parseInt(String.valueOf(vector.elementAt(i)))].name;
				}

				Builder builder = new AlertDialog.Builder(
						CreateBuildingActivity.this);
				final int p = position;
				builder.setItems(name, new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int index) {
						int idx = Integer.parseInt(String.valueOf(vector
								.elementAt(index)));
						Employee e = (Employee) GameData.corporation.employee[index];
						Connection
								.sendMessage(
										GameProtocol.CONNECTION_SEND_CREATE_PUBLICBUIDING,
										ConstructData
												.getCreatePublicBuilding(
														GameData.buildingName[canBuildIndex[p]],
														GameData.buildingId[canBuildIndex[p]],
														GameData.mapIds[GameData.mapIdIndex],
														(short) cityX,
														(short) cityY,
														(byte) 0, e.id));
						CreateBuildingActivity.this.finish();

					}

				}

				).create().show();

				Gallery gallery = (Gallery) parent;

				ImageAdapter adappert = (ImageAdapter) gallery.getAdapter();

				adappert.notifyDataSetChanged(position);
				}
				}
		});

		Button returnback = (Button) this
				.findViewById(R.id.createbuilding_button_returnback);

		Button cancle = (Button) this
				.findViewById(R.id.createbuilding_button_cancel);

		returnback.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				CreateBuildingActivity.this.finish();
			}

		});

		cancle.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {

				CreateBuildingActivity.this.finish();
			}

		});
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
