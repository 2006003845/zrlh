package com.zrong.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.zrong.data.DoctorJumpDB;
import com.zrong.data.GameData;
import com.zrong.entity.Goods;
import com.zrong.ui.HomeActivity;
import com.zrong.ui.R;
import com.zrong.ui.StoreActivity;
import com.zrong.utils.BitmapUtils;
import com.zrong.view.NumView;

public class ImageAdapter extends BaseAdapter {

	private Context mContext;
	private int mGalleryItemBackground;
	private ArrayList<Goods> goodss;
	private int layoutResID;
	private LayoutInflater inflater;
	private ViewFlipper mViewFlipper;

	private DoctorJumpDB doctorDB;

	public ImageAdapter(Context context, ArrayList<Goods> goodss,
			int layoutResID) {
		doctorDB = new DoctorJumpDB(context);
		this.mContext = context;
		this.goodss = goodss;
		this.layoutResID = layoutResID;
		inflater = (LayoutInflater) mContext
				.getSystemService(android.content.Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return Integer.MAX_VALUE;
	}

	@Override
	public Object getItem(int position) {
		return goodss.get(position % goodss.size());
	}

	@Override
	public long getItemId(int position) {
		return position % goodss.size();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (position < 0) {
			position = -position;
		}
		final int index = position % goodss.size();
		View view = convertView;
		if (view == null) {
			view = inflater.inflate(layoutResID, null);
		}
		mViewFlipper = (ViewFlipper) view.findViewById(R.id.ViewFlipper);

		final Goods g = goodss.get(index);

		ImageView imgV = (ImageView) view
				.findViewById(R.id.gallery_item_goods_img);
		imgV.setImageBitmap(BitmapUtils.getBitmap(mContext, g.g_imgName));
		// // 设置ImageView的伸缩规格，用了自带的属性值
		// imgV.setAdjustViewBounds(true);
		imgV.setScaleType(ImageView.ScaleType.FIT_CENTER);

		// view.setLayoutParams(new Gallery.LayoutParams(
		// LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		view.setLayoutParams(new Gallery.LayoutParams(162, 273));
		final ImageView operateBtn = (ImageView) view
				.findViewById(R.id.gallery_item_btn_operate);
		final NumView priceV = (NumView) view
				.findViewById(R.id.gallery_item_goods_priceview);
		priceV.setImgId(R.drawable.img_pricenum);
		priceV.setFrameCount(10);
		priceV.setAddMarkImg(false);
		if (g.g_id >= 100 && g.isOwning == Goods.UNOWNING) {
			mViewFlipper.setDisplayedChild(1);
			ImageView detail2 = (ImageView) view
					.findViewById(R.id.gallery_item_goods_detail2);
			detail2.setVisibility(View.VISIBLE);
			switch (g.g_id) {
			case GameData.rainbowBed_ID:
				detail2.setImageResource(R.drawable.tv_achievement2);
				break;
			case GameData.blackJumper_ID:
				detail2.setImageResource(R.drawable.tv_achievement1);
				break;
			case GameData.sceneValley_ID:
				detail2.setImageResource(R.drawable.tv_achievement3);
				break;
			case GameData.skillbifrost_ID:
				detail2.setImageResource(R.drawable.tv_achievement4);
				break;
			}
		} else {
			mViewFlipper.setDisplayedChild(0);
			if (g.isOwning == Goods.OWNING) {
				// operateBtn
				// .setBackgroundResource(R.drawable.bg_btn_other2_selector);
				if (g.isEquipment == Goods.HAVEEQUIP) {
					operateBtn
							.setImageResource(R.drawable.tv_haveequip_selector);
				} else {
					operateBtn
							.setImageResource(R.drawable.tv_equipment_selector);
				}
				priceV.setVisibility(View.INVISIBLE);
			} else {
				// operateBtn
				// .setBackgroundResource(R.drawable.bg_btn_buy_selector);
				operateBtn.setImageResource(R.drawable.tv_buy_selector);
				priceV.setVisibility(View.VISIBLE);
				priceV.setNum("" + g.g_price);
			}

			operateBtn.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if (goodss.get(index).isOwning == Goods.OWNING) {
						// 装备
						if (Goods.GOODS_TYPE_JUMPER == g.g_type) {
							// 更新数据库
							changeGoodsState(g);
						} else if (Goods.GOODS_TYPE_BED == g.g_type) {
							// 更新数据库
							changeGoodsStateOnly(g);
						} else if (Goods.GOODS_TYPE_EFFECT == g.g_type) {
							changeGoodsState(g);
						} else if (Goods.GOODS_TYPE_SENCE == g.g_type) {
							changeGoodsStateOnly(g);
						} else if (Goods.GOODS_TYPE_SKILL == g.g_type) {
							changeGoodsState(g);
						}
					} else {
						// 购买
						int wealth = GameData.player.getWealth();
						if (g.g_id >= 100 && g.isOwning == Goods.UNOWNING) {
							Toast.makeText(mContext, "该商品未开启(完成对应成就方可开启)", 1000)
									.show();
						}
						if (wealth < g.g_price) {
							// 提示：您的金币不够
							Toast.makeText(mContext,
									"您的金币不够...无法购买(请努力游戏赚取金币)", 1000).show();
						} else {
							GameData.player.setWealth(wealth - g.g_price);
							SharedPreferences prefs = PreferenceManager
									.getDefaultSharedPreferences(mContext);
							prefs.edit()
									.putInt(HomeActivity.PLAYER_WEALTH,
											GameData.player.getWealth())
									.commit();
							doctorDB.updataGoods(g.g_id, Goods.OWNING,
									Goods.UNEQUIP);
							StoreActivity.mContext.updateWealth();
						}
					}
					// 尝试开启彩虹桥
					tryTOOpenRainBowBed();
					StoreActivity.mContext.initGoodssData();
					if (g.g_type == Goods.GOODS_TYPE_JUMPER
							|| g.g_type == Goods.GOODS_TYPE_SENCE) {
						goodss.clear();
						goodss.addAll(GameData.goodsOfStore
								.get(GameData.PERSONANDSENCE));
					} else {
						goodss.clear();
						goodss.addAll(GameData.goodsOfStore.get(GameData.PROP));
					}
				}
			});
		}

		// view.setBackgroundResource(mGalleryItemBackground);
		return view;
	}

	public int getmGalleryItemBackground() {
		return mGalleryItemBackground;
	}

	public void setmGalleryItemBackground(int mGalleryItemBackground) {
		this.mGalleryItemBackground = mGalleryItemBackground;
	}

	private void changeGoodsState(Goods g) {
		if (Goods.HAVEEQUIP == g.isEquipment) {
			doctorDB.updataGoods(g.g_id, -1, Goods.UNEQUIP);
		} else {
			doctorDB.updataGoods(g.g_id, -1, Goods.HAVEEQUIP);
		}
	}

	private void changeGoodsStateOnly(Goods g) {
		ArrayList<Goods> list = getSomeGoodsByType(goodss, g.g_type);
		for (Goods goods : list) {
			if (goods.g_id == g.g_id && Goods.UNEQUIP == g.isEquipment) {
				doctorDB.updataGoods(g.g_id, -1, Goods.HAVEEQUIP);
			} else {
				doctorDB.updataGoods(g.g_id, -1, Goods.UNEQUIP);
			}
		}
	}

	private ArrayList<Goods> someGoods = new ArrayList<Goods>();

	private ArrayList<Goods> getSomeGoodsByType(ArrayList<Goods> goodss,
			int type) {
		someGoods.clear();
		for (Goods goods : goodss) {
			if (goods.g_type == type) {
				someGoods.add(goods);
			}
		}
		return someGoods;
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

}
