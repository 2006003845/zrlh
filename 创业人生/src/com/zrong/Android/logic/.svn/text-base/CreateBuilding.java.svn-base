package com.zrong.Android.logic;

import java.util.Random;
import java.util.Vector;


import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import com.zrong.Android.Util.ImageAdapter;
import com.zrong.Android.activity.MainActivity;
import com.zrong.Android.activity.R;
import com.zrong.Android.game.Connection;
import com.zrong.Android.game.ConstructData;
 
import com.zrong.Android.game.GameData;
import com.zrong.Android.game.GameDefinition;
import com.zrong.Android.game.GameGroupControl;
 
import com.zrong.Android.online.network.GameProtocol;

public class CreateBuilding extends LogicObject implements OnTouchListener
{
	/**
	 * 九宫格中每个格子的状态,0 代表可以建造，1代表不可以建造
	 */
	public byte[] mark = null;
	
	public CreateBuilding(Context context, GameGroupControl control) {
		super(context, control);
		
	}

	
	public void init() 
	{
		initView();
	}
	
	private RadioGroup rg;
	private RadioButton smallRadioButton;
	private RadioButton midRadioButton;
	private RadioButton bigRadioButton;
	
	private int radioSelect;
	
	private EditText shopName;
	
	private Gallery gallery;
	/**
	 * 建造地址
	 */
	private int cityX;
	
	private int cityY;
	/**
	 * 建造小地址
	 */
	private int startGrid;
	
	
	public void initView(){
		View v = getView(); 
		if(v == null)
		{
			v = View.inflate(context, R.layout.createshop, null);
			
			this.registerView(v);
			 
			v.setId(GameDefinition.CreateBuildingView);
			
			rg = (RadioGroup)v.findViewById(R.id.createshop_radiogroup);
			
			smallRadioButton = (RadioButton)v.findViewById(R.id.createshop_radiobutton_small);
			
			midRadioButton = (RadioButton)v.findViewById(R.id.createshop_radiobutton_mid);
			
			bigRadioButton = (RadioButton)v.findViewById(R.id.createshop_radiobutton_big);
			
			rg.check(smallRadioButton.getId());
			
			radioSelect = 0;
			
			shopName = (EditText)v.findViewById(R.id.createshop_EditText_name);
			
			gallery =(Gallery)v.findViewById(R.id.createshop_gallery01);
			
		    ImageAdapter adapter = new ImageAdapter(super.context,ImageAdapter.GALLERY);
		    
		    int imageId[] = new int[GameData.professionId.length];
		    
		    for(int i = 0; i < GameData.professionId.length; i++)
		    {
		    	imageId[i] = GameData.getUIResId(GameData.professionId[i]);
		    }
		    
		    adapter.setImageIdArray(imageId);
		    
		    gallery.setAdapter(adapter);
			
			rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
			{

				public void onCheckedChanged(RadioGroup group, int checkId) {
					// TODO Auto-generated method stub
					if(checkId == smallRadioButton.getId())
					{
						radioSelect = 0;
					}
					else if(checkId == midRadioButton.getId())
					{
						radioSelect = 1;
					}
					else if(checkId == bigRadioButton.getId())
					{
						radioSelect = 2;
					}
				}
			}
			
			);
			
			
			gallery.setOnItemClickListener(new OnItemClickListener(){
				public void onItemClick(AdapterView<?> parent, View v, int position, long id)
				{
					String name = GameData.shopName[position*GameData.shop_scale_size+radioSelect];
					
			        Toast.makeText(MainActivity.mContext,MainActivity.resources.getString(R.string.createbuilding_toast)+name+"id ="+rg.getCheckedRadioButtonId(), 
			            Toast.LENGTH_SHORT).show();
			        
			        Gallery  gallery = (Gallery)parent;
			        
			        ImageAdapter adappert = (ImageAdapter)gallery.getAdapter();
			        
			        adappert.notifyDataSetChanged(position);
				}
			});
			
			Random r = new Random();
			
			r.setSeed(100);
			
			
			
			int random =Math.abs(r.nextInt());
			
			int select = random%imageId.length;
			
			if(select == 0 || select == imageId.length-1)
			{
				select = imageId.length/2;
			}
			
			adapter.notifyDataSetChanged(select);
			
			gallery.setSelection(select);
			
		}
		
		
		Button cancel = (Button)v.findViewById(R.id.createshop_button_cancel);
		
		Button returnBack =(Button)v.findViewById(R.id.createshop_button_returnback);
		
		Button create = (Button)v.findViewById(R.id.createshop_button_create);
		
		cancel.setOnClickListener(new OnClickListener()
		{
			public void onClick(View arg0) {
				
				control.logic.remove("CreateBuilding"); 
			}
		}
		);
		
		returnBack.setOnClickListener(new OnClickListener()
		{

			public void onClick(View arg0) {
				control.logic.remove("CreateBuilding"); 
			}
		}
		);
		
		
		create.setOnClickListener(new OnClickListener()
		{
			public void onClick(View arg0){ 
				control.logic.remove("CreateBuilding"); 
				GameData.buildCityX = (short)cityX;
				GameData.buildCityY = (short)cityY;
				
				Connection.sendMessage(GameProtocol.CONNECTION_SEND_SHOP_CREATE,ConstructData.getCreateShop(shopName.getText().toString(),(byte)(radioSelect+1),GameData.professionId[gallery.getSelectedItemPosition()],GameData.mapIds[GameData.mapIdIndex], (short)cityX, (short)cityY,(byte)startGrid));
				
				Vector v = new Vector();
				v.addElement(String.valueOf(GameProtocol.CONNECTION_SEND_SHOP_CREATE));
				control.setGameStatus(GameDefinition.Game_Loading, v);
			}
		}
		);
		 
		
	}

	@Override
	public void synchviewdata() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadProperties(Vector v) {
		if(v != null && v.size() >= 3)
		{
			cityX = Integer.parseInt(String.valueOf(v.elementAt(0)));
			cityY = Integer.parseInt(String.valueOf(v.elementAt(1)));
			startGrid = Integer.parseInt(String.valueOf(v.elementAt(2)));
		}
	}

	@Override
	protected void reCycle() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void refurbish() {
		// TODO Auto-generated method stub
		
	}

	public boolean onTouch(View arg0, MotionEvent arg1) {
		// TODO Auto-generated method stub
		return false;
	}

}
