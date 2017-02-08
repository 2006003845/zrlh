package cn.zrong.activity;

import java.util.ArrayList;
import java.util.List;

import json.JSONException;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import cn.zrong.ApplicationData;
import cn.zrong.activity.base.BaseActivity;
import cn.zrong.connection.Community;
import cn.zrong.entity.Friend;
import cn.zrong.entity.Group;
import cn.zrong.entity.User;
import cn.zrong.loader.AsyncDataLoader;
import cn.zrong.loader.AsyncDataLoader.Callback;
import cn.zrong.loader.AsyncImageLoader;
import cn.zrong.utils.Tools;

public class FriendsSearchActivity extends BaseActivity {
	private Context context;

	public static void launch(Context c, Intent intent) {
		if (intent == null) {
			intent = new Intent();
		}
		intent.setClass(c, FriendsSearchActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		c.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;
		setContentView(R.layout.friends_search);
		initView();
	}

	private ImageView searchBtn;
	private EditText searchET;
	private ProgressBar searchPB;
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
		searchET = (EditText) this.findViewById(R.id.friends_search_et);
		searchBtn = (ImageView) this
				.findViewById(R.id.friends_search_btn_search);
		searchBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new AsyncDataLoader(loadSearchFriendsCallback).execute();
			}
		});
		searchPB = (ProgressBar) this
				.findViewById(R.id.friends_search_progress);
		friendsListV = (ListView) this.findViewById(R.id.friends_search_listv);
		friendsListV.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Friend user = friendsList.get(position);
				Intent intent = new Intent();
				Bundle b = new Bundle();
				b.putSerializable("friend", user);
				if (isFriend(user.index)) {
					b.putInt(CardActivity.WHERE_COME_FROM,
							CardActivity.COMEFROM_FRIEND);
				} else {
					b.putInt(CardActivity.WHERE_COME_FROM,
							CardActivity.COMEFROM_OTHER);
				}
				intent.putExtras(b);
				CardActivity.launch(context, intent);
			}
		});

		adapter = new FriendsSearchListVAdapter();
		friendsListV.setAdapter(adapter);
	}

	private FriendsSearchListVAdapter adapter;

	private Callback loadSearchFriendsCallback = new Callback() {
		@Override
		public void onStart() {
			try {

				User u = ApplicationData.currentUser;
				Log.i("Log", u.getUserInfo().nickName);
				List<Friend> list = null;
				if (searchET != null && searchET.getText() != null) {
					Community comm = Community.getInstance(context);
					if (comm != null) {
						list = comm.searchFriendList(searchET.getText()
								.toString().trim(), 1, 20);
					}
				} else {
					Toast.makeText(context, "输入栏不能为空", Toast.LENGTH_SHORT)
							.show();
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
			searchBtn.setVisibility(View.GONE);
			searchPB.setVisibility(View.VISIBLE);
		}

		@Override
		public void onFinish() {
			searchPB.setVisibility(View.GONE);
			searchBtn.setVisibility(View.VISIBLE);
			if (friendsList.size() == 0) {
				Toast.makeText(getGameContext(), "没有搜索到相关结果",
						Toast.LENGTH_SHORT).show();
			}
			adapter.notifyDataSetChanged();

		}
	};

	@Override
	public BaseActivity getGameContext() {
		return this;
	}

	class FriendsSearchListVAdapter extends BaseAdapter {
		private LayoutInflater inflater;

		public FriendsSearchListVAdapter() {
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
				view = inflater.inflate(R.layout.friends_search_listv_item,
						null);
			}
			final Friend user = friendsList.get(position);
			ImageView imgv = (ImageView) view
					.findViewById(R.id.friends_search_listv_item_imgv);
			AsyncImageLoader.getInstance().loadPortrait(user.headerUrl,
					user.headerUrl, imgv);
			TextView nameTV = (TextView) view
					.findViewById(R.id.friends_search_listv_item_name);
			nameTV.setText(user.nickName);
			Button addBtn = (Button) view
					.findViewById(R.id.friends_search_listv_item_btn_add);
			final boolean isFriend = isFriend(user.index);
			if (isFriend) {
				addBtn.setText("已为好友");
				user.isFriend = Friend.STATE_FRIEND_IS;
			} else {
				addBtn.setText(R.string.add_as_friend);
			}
			switch (user.isFriend) {
			case Friend.STATE_FRIEND_IS:
				addBtn.setText("已为好友");
				break;
			case Friend.STATE_FRIEND_Sending:
				addBtn.setText("审核中...");
				break;
			case Friend.STATE_FRIEND_VERIFY:
				addBtn.setText("审核中...");
				break;
			}
			addBtn.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if (user.isFriend == Friend.STATE_FRIEND_IS) {
						Tools.showToast(context, "已是您的好友");
						return;
					} else if (user.index.equals(ApplicationData.currentUser
							.getUserInfo().index)) {
						Tools.showToast(context, "您不能加自己");
						return;
					} else {
						// TODO
						new AddFriendTask(user).execute((Void) null);
					}
				}
			});
			return view;
		}
	}

	class AddFriendTask extends AsyncTask<Object, Integer, Integer> {
		Friend friend;

		public AddFriendTask(Friend friend) {
			this.friend = friend;
		}

		@Override
		protected Integer doInBackground(Object... params) {
			try {
				Community comm = Community.getInstance(context);
				if (comm != null) {
					return comm.addAsFriend(friend.index);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return -1;
		}

		@Override
		protected void onPostExecute(Integer result) {
			friend.isFriend = result;
			adapter.notifyDataSetChanged();
			super.onPostExecute(result);
		}

	}

	private boolean isFriend(String roleId) {
		for (Friend friend : ApplicationData.friendsList) {
			if (friend.index.endsWith(roleId)) {
				return true;
			}
		}
		return false;

	}

}
