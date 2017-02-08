package com.zrong.Android.activity;

import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.zrong.Android.Util.Music;
import com.zrong.Android.element.Branch;
import com.zrong.Android.game.Connection;
import com.zrong.Android.game.ConstructData;
import com.zrong.Android.game.GameData;
import com.zrong.Android.online.network.GameProtocol;

public class SearchconditionActivity extends GameActivity {

	public static SearchconditionActivity mContext;
	private Button returnback, cancel, search, title, conditionPlayerName,
			conditionStaffName;
	private Spinner conditionDepartmentPost, conditionShopType,
			conditionPublicBuilder;
	private ImageView scrollviewback;
	private ScrollView scrollView;
	private LinearLayout linearLayout;
    private String Posts = MainActivity.resources.getString(R.string.searchcondition_departmentPosts);
	private String[] departmentPosts = Posts.split(",");
    /*private String[] departmentPosts = new String[] { "    部门职位",
			"    人事部首席执行官", "    人事部总监", "    人事部经理", "    人事部助理", "    人事部主管",
			"    公关部首席执行官", "    公关部总监", "    公关部经理", "    公关部助理", "    公关部主管",
			"    财务部首席执行官", "    财务部总监", "    财务部经理", "    财务部助理", "    财务部主管" };*/
	private String shopTypes[] = new String[GameData.professionId.length + 1];

	LayoutInflater factory = null;

	private byte[] office = new byte[] { 0, 0, 0 };// 0 部门1店铺2建筑
	//
	private byte[] type = new byte[3];
	//
	private byte[] level = new byte[3];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mContext = this;

		getWindow().requestFeature(Window.FEATURE_NO_TITLE);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		this.setContentView(R.layout.searchcondition);

		factory = LayoutInflater.from(this);

		for (int i = 0; i < type.length; i++) {
			type[i] = 0;
		}
		for (int i = 0; i < level.length; i++) {
			level[i] = 0;
		}

		initView();

		search.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				Intent intent = new Intent();

				Bundle bundle = new Bundle();

				bundle.putByte("type", (byte) 2);
				if (!conditionPlayerName.getText().toString().trim()
						.equals(MainActivity.resources.getString(R.string.searchcondition_playername))) {
					bundle.putString("mastername", conditionPlayerName
							.getText().toString().trim());
					Log.i("SearchCondition", bundle.getString("mastername"));
				} else {
					bundle.putString("mastername", "");
					Log.i("SearchCondition", MainActivity.resources.getString(R.string.searchcondition_null));
				}

				if (!conditionStaffName.getText().toString().trim()
						.equals(MainActivity.resources.getString(R.string.searchcondition_staffname))) {
					bundle.putString("staffname", conditionStaffName.getText()
							.toString().trim());
					Log.i("SearchCondition", bundle.getString("staffname"));
				} else {
					bundle.putString("staffname", "");
					Log.i("SearchCondition", MainActivity.resources.getString(R.string.searchcondition_null));
				}

				bundle.putByte("size", (byte) 3);

				bundle.putByteArray("office", office);

				bundle.putByteArray("tType", type);

				bundle.putByteArray("level", level);

				bundle.putInt("begin", 0);

				bundle.putInt("count", 50);

				intent.putExtras(bundle);

				intent.setClass(SearchconditionActivity.this,
						DiglistActivity.class);
				
				SearchconditionActivity.this.startActivity(intent);
				Connection
						.sendMessage(
								GameProtocol.REQSearchStaff_Req,
								ConstructData
										.SearchStaff_Req(
												(byte) 2,
												!conditionPlayerName.getText()
														.toString().trim()
														.equals(MainActivity.resources.getString(R.string.searchcondition_playername)) ? conditionPlayerName
														.getText().toString()
														.trim()
														: "",
												!conditionStaffName.getText()
														.toString().trim()
														.equals(MainActivity.resources.getString(R.string.searchcondition_staffname)) ? conditionStaffName
														.getText().toString()
														.trim()
														: "", (byte) 3, office,
												type, level, 0, 50));

			}
		});

		returnback.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				SearchconditionActivity.this.finish();

			}
		});
		cancel.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {

				SearchconditionActivity.this.finish();
			}
		});

		// 玩家姓名
		conditionPlayerName.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Builder builder = new AlertDialog.Builder(
						SearchconditionActivity.this);

				View playerEnterView = factory.inflate(
						R.layout.searchcondition_player_dailog, null);
				builder.setView(playerEnterView);

				TextView tv = (TextView) playerEnterView
						.findViewById(R.id.searchcondition_player_dialog_text);
				final EditText et = (EditText) playerEnterView
						.findViewById(R.id.searchcondition_player_dialog_edit);

				tv.setText(MainActivity.resources.getString(R.string.searchcondition_playername));

				builder.setPositiveButton(MainActivity.resources.getString(R.string.dialog_ok),
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface arg0, int arg1) {
								if ("".equals(et.getText().toString().trim())) {
									conditionPlayerName.setText("   玩家姓名");
								} else {
									conditionPlayerName.setText("    "
											+ et.getText().toString());
								}
							}

						});

				builder.setNegativeButton(MainActivity.resources.getString(R.string.dialog_cancel),
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface arg0, int arg1) {
							}
						}).create().show();
			}
		});

		conditionStaffName.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Builder builder = new AlertDialog.Builder(
						SearchconditionActivity.this);
				View playerEnterView = factory.inflate(
						R.layout.searchcondition_player_dailog, null);
				builder.setView(playerEnterView);

				TextView tv = (TextView) playerEnterView
						.findViewById(R.id.searchcondition_player_dialog_text);
				final EditText et = (EditText) playerEnterView
						.findViewById(R.id.searchcondition_player_dialog_edit);
				tv.setText(MainActivity.resources.getString(R.string.searchcondition_staffname));

				builder.setPositiveButton(MainActivity.resources.getString(R.string.dialog_ok),
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface arg0, int arg1) {
								if ("".equals(et.getText().toString().trim())) {
									conditionStaffName.setText("   员工姓名");
								} else {
									conditionStaffName.setText("    "
											+ et.getText().toString());
								}
							}
						});

				builder.setNegativeButton(MainActivity.resources.getString(R.string.dialog_cancel),
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface arg0, int arg1) {
							}
						}).create().show();
			}
		});

		// 部门岗位
		conditionDepartmentPost
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long id) {
						if (position > 0) {
							office[0] = 2;
							if (position >= 1 && position <= 5)// 人事部
							{
								type[0] = Branch.PERSONNAL;
							} else if (position >= 6 && position <= 10)// 公关部
							{
								type[0] = Branch.MARKET;
							} else if (position >= 11 && position <= 15)// 财务部
							{
								type[0] = Branch.FINANCE;
							}else if(position>=16 &&position<=20){
								type[0] = Branch.PLANNING;
							}
							Log.i("SearchCondition", "" + type[0]);
							int d = (position - 1) % 5;
							level[0] = (byte) (5 - d);
						}
					}

					public void onNothingSelected(AdapterView<?> parent) {
					}
				});

		// 店铺类型
		conditionShopType
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long id) {

						if (position > 0) {
							office[1] = 1;
							type[1] = GameData.professionId[position - 1];
							level[1] = 0;
						}
						Log.i("SearchCondition", "" + type[1]);

					}

					public void onNothingSelected(AdapterView<?> parent) {
					}
				});

		// 公共建筑
		conditionPublicBuilder
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long id) {
						if (position > 0) {
							office[2] = 3;
							type[2] = GameData.buildingId[position - 1];
							level[2] = 0;
						}
						Log.i("SearchCondition", "" + type[2]);

					}

					public void onNothingSelected(AdapterView<?> parent) {
					}
				});
	}

	// view 初始化
	private void initView() {
		// 加载所有店铺到shopTypes中
		shopTypes[0] = MainActivity.resources.getString(R.string.searchcondition_shopstyle);
		for (int i = 1; i < shopTypes.length; i++) {
			shopTypes[i] = "    " + GameData.shopName[(i - 1) * 3].substring(2);
		}

		String[] builderNames = new String[GameData.buildingName.length + 1];
		builderNames[0] = MainActivity.resources.getString(R.string.searchcondition_building);
		for (int i = 1; i < builderNames.length; i++) {
			builderNames[i] = "    " + GameData.buildingName[i - 1];
		}

		returnback = (Button) this
				.findViewById(R.id.searchcondition_button_returnback);
		cancel = (Button) this.findViewById(R.id.searchcondition_button_cancel);
		search = (Button) this.findViewById(R.id.searchcondition_search);
		title = (Button) this.findViewById(R.id.searchcondition_button_title);

		scrollviewback = (ImageView) this
				.findViewById(R.id.searchcondition_scrollviewback);

		scrollView = (ScrollView) this
				.findViewById(R.id.searchcondition_scorll);
		LinearLayout linearLayout = (LinearLayout) this
				.findViewById(R.id.linearLayout);

		conditionPlayerName = (Button) this.findViewById(R.id.b_playerName);
		conditionStaffName = (Button) this.findViewById(R.id.b_staffName);
		conditionDepartmentPost = (Spinner) this
				.findViewById(R.id.s_departmentPost);
		conditionShopType = (Spinner) this.findViewById(R.id.s_shopType);
		conditionPublicBuilder = (Spinner) this
				.findViewById(R.id.s_publicBuilder);
		// 部门职位
		List<String> departList = (List<String>) Arrays.asList(departmentPosts);
		ArrayAdapter<String> departAdapter = createAdapter(departList);
		departAdapter
				.setDropDownViewResource(R.layout.simple_spinner_item_customer);
		conditionDepartmentPost.setAdapter(departAdapter);
		// 店铺类型
		List<String> shopList = (List<String>) Arrays.asList(shopTypes);
		ArrayAdapter<String> shopAdapter = createAdapter(shopList);
		shopAdapter
				.setDropDownViewResource(R.layout.simple_spinner_item_customer);
		conditionShopType.setAdapter(shopAdapter);
		// 公共建筑
		List<String> builderList = (List<String>) Arrays.asList(builderNames);
		ArrayAdapter<String> builderAdapter = createAdapter(builderList);
		builderAdapter
				.setDropDownViewResource(R.layout.simple_spinner_item_customer);
		conditionPublicBuilder.setAdapter(builderAdapter);
	}

	/**
	 * 创建适配器
	 * 
	 * @param list
	 * @return
	 */
	private ArrayAdapter<String> createAdapter(List<String> list) {
		return new ArrayAdapter<String>(this,
			android.R.layout.simple_spinner_item, list);
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
