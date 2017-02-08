package com.zrong.ui;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.AlertDialog;
import android.content.res.TypedArray;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.zrong.adapter.ImageAdapter;
import com.zrong.data.DoctorJumpDB;
import com.zrong.data.GameData;
import com.zrong.entity.Goods;
import com.zrong.entity.Music;
import com.zrong.ui.base.Base2Activity;
import com.zrong.utils.InitData;
import com.zrong.view.MyGallery;
import com.zrong.view.NumView;

public class StoreActivity extends Base2Activity implements InitData {

	public static StoreActivity mContext;
	private MyGallery myGallery;
	private ArrayList<Goods> goodss = new ArrayList<Goods>();
	private ImageAdapter adapter;
	private ImageView personsenceBtn, propBtn;
	private DoctorJumpDB doctorDB;
	private NumView wealthV;

	public static final String SCENCE_SELECTAED = "selected_sence";
	public static final String NPC_SELECTAED = "selected_npc";
	public static final String BED_SELECTAED = "selected_bed";
	public static final String Prop_SELECTAED = "selected_prop";

	@Override
	public void init() {
		super.init();
		mContext = this;
		Music.getInstance(this).start(R.raw.other, true);
		// doctorDB = DoctorJumpDB.newInstanceOfDB(this);
		doctorDB = new DoctorJumpDB(this);
		tryTOOpenRainBowBed();
		GameData.goodsOfStore = new HashMap<String, ArrayList<Goods>>();
		initGoodssData();
		goodss.addAll(GameData.goodsOfStore.get(GameData.PERSONANDSENCE));
		// 尝试开启彩虹桥

		initView();
	}

	private void initView() {
		setContentView(R.layout.store);
		wealthV = (NumView) this.findViewById(R.id.store_wealthview);
		wealthV.setImgId(R.drawable.img_goldnum);
		wealthV.setFrameCount(10);
		wealthV.setAddMarkImg(false);
		updateWealth();
		this.findViewById(R.id.store_btn_backhome).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						activitySwitch();
					}
				});
		personsenceBtn = (ImageView) this
				.findViewById(R.id.store_btn_personsence);
		personsenceBtn.setClickable(false);
		personsenceBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				goodss.clear();
				propBtn.setClickable(true);
				personsenceBtn.setClickable(false);
				goodss.addAll(GameData.goodsOfStore
						.get(GameData.PERSONANDSENCE));
				adapter.notifyDataSetChanged();
			}
		});
		propBtn = (ImageView) this.findViewById(R.id.store_prop_btn);
		propBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				goodss.clear();
				personsenceBtn.setClickable(true);
				propBtn.setClickable(false);
				goodss.addAll(GameData.goodsOfStore.get(GameData.PROP));
				adapter.notifyDataSetChanged();
			}
		});
		myGallery = (MyGallery) findViewById(R.id.store_gallery);
		myGallery.setSelected(true);
		myGallery.setSelection(200);

		// myGallery.setBackgroundResource(R.drawable.bg0);
		adapter = new ImageAdapter(this, goodss, R.layout.store_gallery_item);
		// 设置背景风格 Gallery背景风格定义在attrs.xml中
		TypedArray typedArray = obtainStyledAttributes(R.styleable.Gallery);
		adapter.setmGalleryItemBackground(typedArray.getResourceId(
				R.styleable.Gallery_android_galleryItemBackground, 0));
		myGallery.setAdapter(adapter);
		myGallery.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					final int position, long id) {
				Goods g = goodss.get(position % goodss.size());
				showDetailDialog(g);
			}
		});
	}

	private void tryTOOpenRainBowBed() {
		ArrayList<Goods> list = Goods.getGoodsList(doctorDB.query(
				Goods.TAB_NAME, null, Goods.G_TYPE + "=? and "
						+ Goods.G_ISOWNING + "=?", new String[] {
						Goods.GOODS_TYPE_BED + "", "" + Goods.OWNING }, null,
				null, null));
		if (list.size() < 4) {
			return;
		}

		// 更新彩虹桥商品的状态
		if (list.get(0).isOwning == Goods.UNOWNING) {
			doctorDB.updataGoods(GameData.rainbowBed_ID, Goods.OWNING,
					Goods.UNEQUIP);
		}
	}

	@Override
	protected void onDestroy() {
		doctorDB.closeDB();
		doctorDB.close();
		super.onDestroy();
	}

	private void showDetailDialog(Goods g) {
		LayoutInflater inflater = getLayoutInflater();
		View layout = inflater.inflate(R.layout.store_detail_diag, null);
		final AlertDialog dialog = new AlertDialog.Builder(this).create();
		dialog.show();
		dialog.getWindow().setContentView(layout);
		TextView titlT = (TextView) dialog.findViewById(R.id.diag_title_tv);
		titlT.setText(g.g_name);
		TextView contentT = (TextView) dialog
				.findViewById(R.id.diag_content_tv);
		contentT.setText(g.g_info);

		dialog.findViewById(R.id.diag_close_btn).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						dialog.cancel();
					}
				});
	}

	@Override
	public void initGoodssData() {
		GameData.goodsOfStore.clear();
		GameData.goodsOfStore.put(GameData.PERSONANDSENCE, Goods
				.getGoodsList(doctorDB.query(Goods.TAB_NAME, null, Goods.G_TYPE
						+ " in(?,?)", new String[] {
						Goods.GOODS_TYPE_JUMPER + "",
						Goods.GOODS_TYPE_SENCE + "" }, null, null, null)));

		GameData.goodsOfStore
				.put(GameData.PROP,
						Goods.getGoodsList(doctorDB.query(Goods.TAB_NAME, null,
								Goods.G_TYPE + " in(?,?,?)", new String[] {
										Goods.GOODS_TYPE_BED + "",
										Goods.GOODS_TYPE_EFFECT + "",
										Goods.GOODS_TYPE_SKILL + "" }, null,
								null, null)));

		doctorDB.closeDB();
		if (adapter != null) {
			adapter.notifyDataSetChanged();
		}
	}

	@Override
	protected void onResume() {
		mContext = this;
		super.onResume();
	}

	@Override
	protected void onStop() {
		if (mContext != null) {
			mContext = null;
		}
		super.onStop();
	}

	@Override
	public void updateWealth() {
		wealthV.setNum(GameData.player.getWealth() + "");
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			activitySwitch();
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}

	int version = Integer.valueOf(android.os.Build.VERSION.SDK);

	// 界面切换效果
	private void activitySwitch() {

		if (version >= 5) {
			overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
		}
		this.finish();
	}

}
