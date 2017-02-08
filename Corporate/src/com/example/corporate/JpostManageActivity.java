package com.example.corporate;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.corporate.Jobs.JobDetail;
import com.example.corporate.base.BaseActivity;
import com.example.corporate.base.MyToast;
import com.zzl.zl_app.connection.Community;
import com.zzl.zl_app.util.TimeUtil;

public class JpostManageActivity extends BaseActivity {

	public static final String Tag = "jpost_manage";

	public static void launch(Context c, Intent intent) {
		if (intent == null) {
			intent = new Intent();
		}
		intent.setClass(c, JpostManageActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		c.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addActMap(Tag, getContext());
		setContentView(R.layout.jpost_manage);
		initView();
		new GetJPostListTask().execute();
	}

	private TextView titleTV;

	private ListView jpostListV;
	private ImageButton publishBtn;
	Jpostdapter adapter;

	private List<Jobs.JobDetail> jpostList = new ArrayList<Jobs.JobDetail>();

	private void initView() {
		this.findViewById(R.id.back).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				closeOneAct(Tag);
			}
		});
		titleTV = (TextView) this.findViewById(R.id.title_tv);
		titleTV.setText(R.string.jpost_manage);

		publishBtn = (ImageButton) this.findViewById(R.id.btn);
		publishBtn.setImageResource(R.drawable.btn_publish);
		publishBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO
				JpostEditActivity.launch(getContext(), getIntent());
			}
		});
		jpostListV = (ListView) this.findViewById(R.id.jpost_listv);
		adapter = new Jpostdapter();
		jpostListV.setAdapter(adapter);
		jpostListV.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO
			}
		});
		jpostListV.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO
				return false;
			}
		});
	}

	@Override
	public BaseActivity getContext() {
		return this;
	}

	@Override
	public void setDialogContent(AlertDialog dialog, int layoutId, String msg) {
	}

	@Override
	public void setDialogTitle(AlertDialog dialog, int layoutId, String title) {
	}

	@Override
	public OnClickListener setPositiveClickListener(AlertDialog dialog,
			int layoutId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OnClickListener setNegativeClickListener(AlertDialog dialog,
			int layoutId) {
		// TODO Auto-generated method stub
		return null;
	}

	class Jpostdapter extends BaseAdapter {
		private LayoutInflater inflater;

		public Jpostdapter() {
			inflater = (LayoutInflater) getContext().getSystemService(
					android.content.Context.LAYOUT_INFLATER_SERVICE);
		}

		@Override
		public int getCount() {
			return jpostList.size();
		}

		@Override
		public Object getItem(int position) {
			return jpostList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.item_listv_jpost, null);
				holder = new ViewHolder();

				holder.name = (TextView) convertView
						.findViewById(R.id.item_listv_jpost_tv_name);
				holder.time = (TextView) convertView
						.findViewById(R.id.item_listv_jpost_tv_time);
				holder.stat = (TextView) convertView
						.findViewById(R.id.item_listv_jpost_tv_state);
				holder.edit = (Button) convertView
						.findViewById(R.id.item_listv_jpost_btn_edit);
				holder.delete = (Button) convertView
						.findViewById(R.id.item_listv_jpost_btn_delete);
				holder.oper = (LinearLayout) convertView
						.findViewById(R.id.item_listv_jpost_layout_oper);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			final Jobs.JobDetail jpost = jpostList.get(position);
			if (jpost != null) {
				holder.name.setText(jpost.name);
				holder.time.setText(jpost.fabu_date);
				holder.oper.setVisibility(View.VISIBLE);
				if (TimeUtil.isOverDue(jpost.deadline)) {
					holder.stat.setText(R.string.overdue);
				} else {
					holder.stat.setText(R.string.published);
				}
				holder.edit.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO
						Intent intent = new Intent();
						Bundle b = new Bundle();
						b.putSerializable("jpost", jpost);

						JpostEditActivity.launch(getContext(), intent);
					}
				});
				holder.delete.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO
						new DeleteJPostTask(jpost.rid, jpost.area, position)
								.execute();
					}
				});

			}
			return convertView;
		}

		class ViewHolder {
			TextView name;
			TextView time;
			TextView stat;
			Button edit;
			Button delete;
			LinearLayout oper;
		}
	}

	class DeleteJPostTask extends AsyncTask<Object, Integer, Boolean> {

		String rid;
		String city;
		int position;

		public DeleteJPostTask(String rid, String city, int position) {
			super();
			this.rid = rid;
			this.city = city;
			this.position = position;

		}

		ProgressDialog dialog = null;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = new ProgressDialog(getContext());
			dialog.setCancelable(false);
			dialog.setMessage("正在删除,请稍后");
			dialog.show();
		}

		@Override
		protected Boolean doInBackground(Object... params) {
			try {
				return Community.getInstance(getContext()).recruitDelete(
						PlatformAPI.Name, PlatformAPI.Pwd, city, rid);

			} catch (JSONException e) {
				e.printStackTrace();
			}
			return false;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			setProgressBarIndeterminateVisibility(false);
			dialog.dismiss();
			if (result) {
				jpostList.remove(position);
				adapter.notifyDataSetChanged();
			} else {
				MyToast.getToast().showToast(getContext(), "删除失败,请重试");
			}
			super.onPostExecute(result);
		}
	}

	class GetJPostListTask extends AsyncTask<Object, Integer, List<JobDetail>> {

		public GetJPostListTask() {
			super();

		}

		ProgressDialog dialog = null;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = new ProgressDialog(getContext());
			dialog.setCancelable(false);
			dialog.setMessage("正在加载,请稍后");
			dialog.show();
		}

		@Override
		protected List<JobDetail> doInBackground(Object... params) {
			try {
				return Community.getInstance(getContext()).recruitInfoList(
						PlatformAPI.Name, PlatformAPI.Pwd);

			} catch (JSONException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(List<JobDetail> result) {
			setProgressBarIndeterminateVisibility(false);
			dialog.dismiss();
			if (result != null) {
				if (result.size() > 0) {
					jpostList.clear();
					jpostList.addAll(result);
				} else {
					MyToast.getToast().showToast(getContext(),
							"您暂时没有岗位数据,可以点击右上角发布岗位");
				}
				adapter.notifyDataSetChanged();
			} else {
				MyToast.getToast().showToast(getContext(), "请检查您的网络是否正常");
			}
			super.onPostExecute(result);
		}
	}

}
