package cn.zrong.activity;

import java.util.ArrayList;
import java.util.List;

import json.JSONException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import cn.zrong.ApplicationData;
import cn.zrong.activity.base.BaseActivity;
import cn.zrong.adapter.GroupListVAdapter;
import cn.zrong.connection.Community;
import cn.zrong.entity.Group;
import cn.zrong.entity.User;
import cn.zrong.loader.AsyncDataLoader;
import cn.zrong.loader.AsyncDataLoader.Callback;

public class GroupSearchActivity extends BaseActivity {
	private Context context;
	public static GroupSearchActivity mInstance;

	public static void launch(Context c, Intent intent) {
		if (intent == null) {
			intent = new Intent();
		}
		intent.setClass(c, GroupSearchActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		c.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;
		mInstance = this;
		setContentView(R.layout.group_search);
		initView();
	}

	private ImageView searchBtn;
	private EditText searchET;
	private ProgressBar searchPB;
	private ListView groupListV;
	private GroupListVAdapter groupListVAdapter;
	private List<Group> groupList = new ArrayList<Group>();

	private void initView() {
		this.findViewById(R.id.back_btn).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						getGameContext().finish();
					}
				});
		searchET = (EditText) this.findViewById(R.id.group_search_et);
		searchBtn = (ImageView) this.findViewById(R.id.group_search_btn_search);
		searchPB = (ProgressBar) this.findViewById(R.id.group_search_progress);
		searchBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new AsyncDataLoader(loadSearchGroupCallback).execute();
			}
		});
		groupListV = (ListView) this.findViewById(R.id.group_search_listv);
		groupListV.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				//
				Group g = groupList.get(position);
				if (g.isSys() || isOwningGroup(g) || g.haveJoin == 2) {
					Intent intent = new Intent();
					Bundle b = new Bundle();
					b.putSerializable("group", g);
					intent.putExtras(b);
					GroupActivity.launch(context, intent);
				} else if (g.haveJoin != 2) {
					Intent intent = new Intent();
					Bundle b = new Bundle();
					b.putSerializable("group", g);
					intent.putExtras(b);
					GroupInfoActivity.launch(context, intent);
				}
			}
		});

		groupListVAdapter = new GroupListVAdapter(context, groupList);
		groupListV.setAdapter(groupListVAdapter);
	}

	private Callback loadSearchGroupCallback = new Callback() {
		@Override
		public void onStart() {
			try {
				Community comm = Community.getInstance(context);
				if (comm != null) {
					User u = ApplicationData.currentUser;
					Log.i("Log", u.getUserInfo().nickName);
					List<Group> list;
					if (searchET != null && searchET.getText() != null) {

						list = comm.getGroupList(searchET.getText().toString()
								.trim(), Community.TYPE_GROUP_KEY_ALL);

					} else {
						list = comm.getGroupList("",
								Community.TYPE_GROUP_KEY_ALL);
					}
					if (list != null) {
						groupList.clear();
						groupList.addAll(list);
					}
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
			if (groupList.size() == 0) {
				Toast.makeText(getGameContext(), "没有搜索到相关结果",
						Toast.LENGTH_SHORT).show();
			}
			groupListVAdapter.notifyDataSetChanged();

		}
	};

	@Override
	public BaseActivity getGameContext() {
		return this;
	}

	private boolean isOwningGroup(Group g) {
		if (g == null) {
			return false;
		}
		for (Group group : HomeActivity.mInstance.groupList) {
			if (group.getgNum().equals(g.getgNum())) {
				return true;
			}
		}
		return false;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mInstance = null;
	}

}
