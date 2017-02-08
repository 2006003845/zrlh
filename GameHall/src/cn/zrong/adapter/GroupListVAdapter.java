package cn.zrong.adapter;

import java.util.List;

import json.JSONException;
import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import cn.zrong.activity.HomeActivity;
import cn.zrong.activity.R;
import cn.zrong.connection.Community;
import cn.zrong.entity.Group;
import cn.zrong.loader.ImageCache;

public class GroupListVAdapter extends BaseAdapter {

	private List<Group> groupList;
	private Context context;
	private LayoutInflater inflater;
	private GroupListVAdapter mInstance;

	public GroupListVAdapter(Context context, List<Group> groupList) {
		this.context = context;
		this.groupList = groupList;
		mInstance = this;
		inflater = (LayoutInflater) context
				.getSystemService(android.content.Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return groupList.size();
	}

	@Override
	public Object getItem(int position) {
		return groupList.get(position);
	}

	@Override
	public long getItemId(int position) {

		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if (view == null) {
			view = inflater.inflate(R.layout.group_listv_item, null);
		}

		final Group group = groupList.get(position);
		final boolean isOwning = isOwningGroup(group);
		ImageView groupImgV = (ImageView) view
				.findViewById(R.id.group_listv_item_imgv);
		String imgUrl = group.getgImgUrl();
		if (imgUrl != null && !imgUrl.equals("")) {
			ImageCache.getInstance().loadImg(context, imgUrl, groupImgV);
		}

		TextView nameTV = (TextView) view
				.findViewById(R.id.group_listv_item_name);
		TextView numTV = (TextView) view
				.findViewById(R.id.group_listv_item_num);
		TextView introTV = (TextView) view
				.findViewById(R.id.group_listv_item_intro);
		if (group.getgName() != null) {
			nameTV.setText(group.getgName());
		}
		if (group.getgSize() != null) {
			numTV.setText(group.getgSize());
		}
		if (group.getgExp() != null) {
			introTV.setText(group.getgExp());
		}
		Button addBtn = (Button) view.findViewById(R.id.group_listv_item_btn);
		if (isOwning) {
			group.haveJoin = 0;
			addBtn.setText("已加入");
		} else {
			group.haveJoin = -1;
		}
		switch (group.haveJoin) {
		case 0:
			addBtn.setText("正在审核");
			break;
		case 1:
			addBtn.setText("正在审核");
			break;
		case 2:
			addBtn.setText("已加入");
			break;
		}
		addBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO 加入圈子
				if (group.haveJoin == 2) {
					return;
				} else {
					new JoinGroupTask(group).execute((Void) null);
				}
			}
		});
		return view;
	}

	class JoinGroupTask extends AsyncTask<Object, Integer, Integer> {
		Group group;

		public JoinGroupTask(Group group) {
			this.group = group;
		}

		@Override
		protected Integer doInBackground(Object... params) {
			try {
				Community comm = Community.getInstance(context);
				if (comm != null) {
					return comm.joinGroup(group.getgNum());
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return -1;
		}

		@Override
		protected void onPostExecute(Integer result) {
			group.haveJoin = result;
			mInstance.notifyDataSetChanged();
			super.onPostExecute(result);
		}

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

}
