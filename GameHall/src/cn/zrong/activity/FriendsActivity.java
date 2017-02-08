package cn.zrong.activity;

import java.util.ArrayList;
import java.util.List;

import json.JSONException;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import cn.zrong.activity.base.BaseActivity;
import cn.zrong.connection.Community;
import cn.zrong.entity.Friend;
import cn.zrong.entity.GameUser;
import cn.zrong.loader.AsyncDataLoader;
import cn.zrong.loader.AsyncDataLoader.Callback;
import cn.zrong.loader.AsyncImageLoader;

public class FriendsActivity extends BaseActivity {
	private Context context;

	public static void launch(Context c, Intent intent) {
		if (intent == null) {
			intent = new Intent();
		}
		intent.setClass(c, FriendsActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		c.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;
		setContentView(R.layout.friends);
		initView();
	}

	private ListView friendsListV;
	private List<Friend> friendsList = new ArrayList<Friend>();

	private void initView() {
		this.findViewById(R.id.back_btn).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						getGameContext().finish();
					}
				});
		this.findViewById(R.id.friends_btn_search).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						FriendsSearchActivity.launch(context, null);
					}
				});
		friendsListV = (ListView) this.findViewById(R.id.friends_listv);
		friendsListV.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Friend user = friendsList.get(position);
				Intent intent = new Intent();
				Bundle b = new Bundle();
				b.putSerializable("friend", user);
				b.putInt(CardActivity.WHERE_COME_FROM,
						CardActivity.COMEFROM_FRIEND);
				intent.putExtras(b);
				CardActivity.launch(context, intent);
			}
		});
		adapter = new FriendsListVAdapter();
		friendsListV.setAdapter(adapter);
		new AsyncDataLoader(loadFriendsCallback).execute();
	}

	private FriendsListVAdapter adapter;

	private Callback loadFriendsCallback = new Callback() {
		ProgressDialog dialog;

		@Override
		public void onStart() {
			try {
				List<Friend> list = null;
				Community comm = Community.getInstance(context);
				if (comm != null) {
					list = comm.getFriendList("", 1, 20);
				}
				if (list != null) {
					friendsList.clear();
					friendsList.addAll(list);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void onPrepare() {
			dialog = new ProgressDialog(context,
					android.R.style.Widget_ProgressBar_Small);
			dialog.setCancelable(true);
			dialog.setMessage("加载...");
			dialog.show();
		}

		@Override
		public void onFinish() {
			setProgressBarIndeterminateVisibility(false);
			dialog.dismiss();
			adapter.notifyDataSetChanged();
		}
	};

	@Override
	public BaseActivity getGameContext() {
		return this;
	}

	class FriendsListVAdapter extends BaseAdapter {
		private LayoutInflater inflater;

		public FriendsListVAdapter() {
			inflater = (LayoutInflater) context
					.getSystemService(android.content.Context.LAYOUT_INFLATER_SERVICE);
		}

		@Override
		public int getCount() {
			return friendsList.size();
		}

		@Override
		public Object getItem(int position) {
			return friendsList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = convertView;
			if (view == null) {
				view = inflater.inflate(R.layout.friends_listv_item, null);
			}
			final Friend user = friendsList.get(position);
			ImageView imgv = (ImageView) view
					.findViewById(R.id.friends_listv_item_imgv);
			if (user.headerUrl != null && !user.headerUrl.equals("")) {
				AsyncImageLoader.getInstance().loadPortrait(user.headerUrl,
						user.headerUrl, imgv);
			}
			TextView nameTV = (TextView) view
					.findViewById(R.id.friends_listv_item_name);
			nameTV.setText(user.nickName);
			view.findViewById(R.id.friends_listv_item_btn_sendmsg)
					.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							// TODO sendMSG
							Intent intent = new Intent();
							Bundle b = new Bundle();
							b.putSerializable("friend", user);
							intent.putExtras(b);
							SendMessageActivity.launch(context, intent);
						}
					});
			return view;
		}
	}

}
