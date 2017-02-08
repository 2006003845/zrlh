package com.zrong.Android.logic;

import java.util.Hashtable;
import java.util.Vector;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.zrong.Android.Util.Preferences;
import com.zrong.Android.View.Event;
import com.zrong.Android.View.FreshManLead;
import com.zrong.Android.View.SpriteView;
import com.zrong.Android.activity.R;
import com.zrong.Android.game.Connection;
import com.zrong.Android.game.ConstructData;
import com.zrong.Android.game.EventManager;
import com.zrong.Android.game.GameData;
import com.zrong.Android.game.GameDef;
import com.zrong.Android.game.GameDefinition;
import com.zrong.Android.game.GameGroupControl;
import com.zrong.Android.online.network.GameProtocol;

public class CreateCharactor extends LogicObject {

	public static final String TAG = "CreateCharactor";

	private String playerName = null;

	private String companyName = null;

	private String promotionID = null;

	private EditText pName = null;// 角色名

	private EditText cName = null;// 公司名

	private EditText pID = null;// 推广号

	private TextView proID;

	public CreateCharactor(Context context, GameGroupControl control) {
		super(context, control);
	}

	@Override
	public void init() {
		initView();
	}

	@Override
	public void initView() {
		
		
		
		View v = View.inflate(context, R.layout.createcharactor, null);

		registerView(v);

		v.setId(GameDefinition.CreateCharactorView);

		RadioGroup rg = (RadioGroup) v
				.findViewById(R.id.createcharactor_radiogroup);

		final RadioButton rb1 = (RadioButton) v
				.findViewById(R.id.createcharactor_radiobutton_male);

		final RadioButton rb2 = (RadioButton) v
				.findViewById(R.id.createcharactor_radiobutton_female);
		final SpriteView sv = (SpriteView) v
				.findViewById(R.id.createcharactor_Avatar);

		pName = (EditText) v.findViewById(R.id.createcharactor_pname);
		cName = (EditText) v.findViewById(R.id.createcharactor_cname);
		pID = (EditText) v.findViewById(R.id.createcharactor_pcid);

		Button confirm = (Button) v.findViewById(R.id.createcharacter_confirm);
		Button returns = (Button) v.findViewById(R.id.createcharacter_return);

		proID = (TextView) v.findViewById(R.id.pcidtext);

//		if (GameDef.channelId.equals(GameDef.dangle)) {
			 
//			proID.setVisibility(View.GONE);
//			 
//			pID.setVisibility(View.GONE);
//		}
		sv.setSeries(0);
		rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup group, int checkId) {
				// TODO Auto-generated method stub
				if (checkId == rb1.getId())// 选择了男性
				{
				//	Log.v(TAG, "选择了男性");
					sv.setSeries(0);
				} else if (checkId == rb2.getId())// 选择了女性
				{
				//	Log.v(TAG, "选择了女性");
					sv.setSeries(1);
				}
			}
		});

		confirm.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {

				long pid = 0;
				if (!("".equals(pID.getText()))) {
					try {
						pid = Long.parseLong(pID.getText().toString());
						
					} catch (NumberFormatException e) {
					}

				} 
				// 登录

				if (GameData.usersStatus == 0)// 注册公司
				{
					Connection
							.sendMessage(
									(short) GameProtocol.CONNECTION_SEND_CPINFOR,
									ConstructData.getCpName(cName.getText()
											.toString().trim()));

				} else {
					Connection.sendMessage(
							GameProtocol.CONNECTION_SEND_CREATE_REG,
							ConstructData.getCreatRoleTest(pName.getText()
									.toString().trim(), "",
									(byte) (sv.getSeries() % 2),
									sv.getSeries(), "", pid));
				}
				Vector v = new Vector();
				v.addElement(GameProtocol.CONNECTION_SEND_CREATE_REG);
				control.setGameStatus(GameDefinition.Game_Loading, v);

			}

		});
		returns.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				Vector v = new Vector();
				String uuname = control.preferences
						.getString(Preferences.USERNAME);
				String uupassword = control.preferences
						.getString(Preferences.PASSWORD);
				v.addElement(uuname);
				v.addElement(uupassword);
				control.setGameStatus(GameDefinition.Game_Login, v);
			}
		});

	}

	public void synchviewdata() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {

	}

	@Override
	public void loadProperties(Vector v) {
		if (v != null && v.size() > 0) {
			playerName = companyName = String.valueOf(v.elementAt(0));
			pName.setText(playerName);
			cName.setText(companyName);

		}
	}

	@Override
	protected void reCycle() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void refurbish() {

	}

}
