package cn.zrong.activity;

import json.JSONException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.zrong.ApplicationData;
import cn.zrong.activity.base.BaseActivity;
import cn.zrong.connection.Community;
import cn.zrong.entity.City;
import cn.zrong.entity.Friend;
import cn.zrong.entity.RoleInfo;
import cn.zrong.entity.RoleInfo.Gender;
import cn.zrong.loader.AsyncDataLoader;
import cn.zrong.loader.AsyncDataLoader.Callback;
import cn.zrong.loader.AsyncImageLoader;
import cn.zrong.loader.ImageCache;

public class CardActivity extends BaseActivity {
	private Context context;
	public static CardActivity mIntance;

	public static void launch(Context c, Intent intent) {
		if (intent == null) {
			intent = new Intent();
		}
		intent.setClass(c, CardActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		c.startActivity(intent);
	}

	public static final String WHERE_COME_FROM = "card_wherecomefrom";
	public static final int COMEFROM_SELF = 1;
	public static final int COMEFROM_FRIEND = 2;
	public static final int COMEFROM_OTHER = 4;
	private int whereComeFrom = 0;

	private String weiboId;
	private Friend friend;
	private RoleInfo roleInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;
		mIntance = this;
		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			whereComeFrom = bundle.getInt(WHERE_COME_FROM);
			weiboId = bundle.getString("weiboId");
			friend = (Friend) bundle.getSerializable("friend");
		}
		setContentView(R.layout.card);
		initView();
	}

	private ImageView userHeadImgV, genderImgV;
	private TextView nickNameTV, locationTV, userIntroTV;
	private Button excuseBtn, friendsBtn, msgBtn;
	private LinearLayout btnLayout;

	private void initView() {
		this.findViewById(R.id.back_btn).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						getGameContext().finish();
					}
				});
		userHeadImgV = (ImageView) this.findViewById(R.id.card_headportrait);
		genderImgV = (ImageView) this.findViewById(R.id.card_seximgv);
		nickNameTV = (TextView) this.findViewById(R.id.card_name_tv);
		locationTV = (TextView) this.findViewById(R.id.card_location_tv);
		userIntroTV = (TextView) this.findViewById(R.id.card_intro_tv);
		excuseBtn = (Button) this.findViewById(R.id.card_btn_addasfriend);
		excuseBtn.setOnClickListener(onClickListener);

		btnLayout = (LinearLayout) this.findViewById(R.id.card_btn_layout);
		friendsBtn = (Button) this.findViewById(R.id.card_btn_friends);
		friendsBtn.setOnClickListener(onClickListener);
		msgBtn = (Button) this.findViewById(R.id.card_btn_msg);
		msgBtn.setOnClickListener(onClickListener);
		if (whereComeFrom == COMEFROM_SELF) {
			btnLayout.setVisibility(View.VISIBLE);
			excuseBtn.setText(R.string.edit);
			// TODO
		} else if (whereComeFrom == COMEFROM_FRIEND) {
			btnLayout.setVisibility(View.GONE);
			excuseBtn.setVisibility(View.INVISIBLE);

		} else {
			btnLayout.setVisibility(View.GONE);
			// TODO 检测 该好友是否已经申请添加
			excuseBtn.setText(R.string.add_as_friend);
			// TODO
		}
		update();

	}

	private OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.card_btn_addasfriend:
				// TODO
				if (whereComeFrom == COMEFROM_SELF) {
					// TODO
					Intent intent = new Intent();
					Bundle b = new Bundle();
					b.putSerializable("roleInfo", roleInfo);
					intent.putExtras(b);
					CardEditActivity.launch(context, intent);
				} else if (whereComeFrom == COMEFROM_FRIEND) {
					return;
				} else {

				}
				break;
			case R.id.card_btn_friends:
				// TODO
				FriendsActivity.launch(context, null);
				break;
			case R.id.card_btn_msg:
				// TODO
				MessageListActivity.launch(context);
				break;

			}
		}
	};

	public void update() {
		if (friend != null) {
			initFriend();
		} else {
			new AsyncDataLoader(getWeiboUserCallback).execute();
		}
	}

	private Callback getWeiboUserCallback = new Callback() {

		@Override
		public void onStart() {
			try {
				Community comm = Community.getInstance(context);
				if (comm != null) {
					roleInfo = comm.getRoleInfo(ApplicationData.currentUser
							.getUserInfo().index);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void onPrepare() {
		}

		@Override
		public void onFinish() {
			if (roleInfo != null) {
				nickNameTV.setText(roleInfo.nickName);
				String headUrl = roleInfo.headerUrl;
				if (headUrl != null && !headUrl.equals("")) {
					// AsyncImageLoader.getInstance().loadPortrait(headUrl,
					// headUrl, userHeadImgV);
					ImageCache.getInstance().loadImg(context, headUrl,
							userHeadImgV);
				}
				if (roleInfo.gender != null && roleInfo.gender.equals(Gender.男)) {
					genderImgV.setImageResource(R.drawable.img_man);
				} else {
					genderImgV.setImageResource(R.drawable.img_woman);
				}
				if (roleInfo.city.name == null) {
					City c = getCity(roleInfo.city.index);
					if (c != null) {
						roleInfo.city.name = c.name;
					} else {
						roleInfo.city.name = "";
					}
				}
				locationTV.setText(roleInfo.city.name);
				userIntroTV.setText(roleInfo.intro);

			}
		}
	};

	private void initFriend() {
		nickNameTV.setText(friend.nickName);
		String headUrl = friend.headerUrl;
		if (headUrl != null && !headUrl.equals("")) {
			// AsyncImageLoader.getInstance().loadPortrait(friend.index,
			// headUrl,
			// userHeadImgV);
			ImageCache.getInstance().loadImg(context, headUrl, userHeadImgV);
		}
		if (friend.gender != null && friend.gender.equals(Gender.男)) {
			genderImgV.setImageResource(R.drawable.img_man);
		} else {
			genderImgV.setImageResource(R.drawable.img_woman);
		}
		if (friend.city != null && friend.city.name == null) {
			City c = getCity(friend.city.index);
			if (c != null) {
				friend.city.name = c.name;
			} else {
				friend.city.name = "";
			}
			locationTV.setText(friend.city.name);
		}

		userIntroTV.setText(friend.intro);
	}

	private City getCity(String index) {
		for (City c : ApplicationData.cityList) {
			if (c.index.equals(index)) {
				return c;
			}
		}
		return null;
	}

	@Override
	public BaseActivity getGameContext() {
		return this;
	}

}
