package cn.zrong.activity;

import java.util.ArrayList;
import java.util.List;

import json.JSONException;
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
import cn.zrong.entity.Mail;
import cn.zrong.loader.AsyncImageLoader;
import cn.zrong.utils.Tools;

public class SendMessageActivity extends BaseActivity {

	public static void launch(Context c, Intent intent) {
		if (intent == null) {
			intent = new Intent();
		}
		intent.setClass(c, SendMessageActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		c.startActivity(intent);
	}

	private Context context;

	private Mail msg;
	private Friend friend;

	private List<Mail> msgList = new ArrayList<Mail>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;

		Bundle b = getIntent().getExtras();
		if (b != null) {
			msg = (Mail) b.getSerializable("msg");
			friend = (Friend) b.getSerializable("friend");

		}
		setContentView(R.layout.msg_send);
		initView();
	}

	private TextView titleTV;
	private EditText contentET;
	private Button sendBtn;
	private ListView msgListV;
	private MSGListVAdapter adapter;

	private void initView() {

		this.findViewById(R.id.back_btn).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						getGameContext().finish();
					}
				});
		titleTV = (TextView) this.findViewById(R.id.msg_send_title);
		contentET = (EditText) this.findViewById(R.id.msg_send_et);
		sendBtn = (Button) this.findViewById(R.id.msg_send_btn_send);
		if (msg != null) {
			msgList.add(msg);
			sendBtn.setText(R.string.reply);
			titleTV.setText("与" + msg.wbName + "私信");
		} else if (friend != null) {
			sendBtn.setText(R.string.send);
			titleTV.setText("与" + friend.nickName + "私信");
		}

		sendBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String content = contentET.getText().toString().trim();
				if (content.equals("")) {
					Toast.makeText(context, "请输入发送内容", Toast.LENGTH_SHORT)
							.show();
					return;
				}
				Mail m = new Mail();
				if (friend != null) {
					if (friend.wbId == null) {
						return;
					}
					m.id = friend.wbId;
				} else if (msg != null) {
					m.id = msg.id;
				}
				m.headUrl = ApplicationData.currentUser.getUserInfo().headerUrl;
				m.cont = content;
				m.state = Mail.STATE_SENDING;
				m.isSelf = true;
				msgList.add(m);
				adapter.notifyDataSetChanged();
				// TODO send msg
				new SendMsgTask(m).execute((Void) null);
			}
		});

		msgListV = (ListView) this.findViewById(R.id.msg_send_msglistv);
		msgListV.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

			}
		});
		adapter = new MSGListVAdapter();
		msgListV.setAdapter(adapter);
	}

	class SendMsgTask extends AsyncTask<Object, Integer, Boolean> {
		Mail msg;

		public SendMsgTask(Mail msg) {
			this.msg = msg;
		}

		@Override
		protected Boolean doInBackground(Object... params) {
			try {
				Community comm = Community.getInstance(context);
				if (comm != null) {
					return comm.sendMessage(msg.id, msg.cont);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return false;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			if (result) {
				msg.state = Mail.STATE_SEND_SUCCESS;
			} else {
				msg.state = Mail.STATE_SEND_FAILED;
			}
			adapter.notifyDataSetChanged();
			super.onPostExecute(result);
		}

	}

	@Override
	public BaseActivity getGameContext() {
		return this;
	}

	class MSGListVAdapter extends BaseAdapter {
		private LayoutInflater inflater;

		public MSGListVAdapter() {
			inflater = (LayoutInflater) context
					.getSystemService(android.content.Context.LAYOUT_INFLATER_SERVICE);
		}

		@Override
		public int getCount() {
			return msgList.size();
		}

		@Override
		public Object getItem(int position) {
			return msgList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = convertView;
			Mail msg = msgList.get(position);
			boolean isSelf = false;
			if (view == null) {
				if (msg.isSelf) {
					isSelf = true;
					view = inflater.inflate(R.layout.msg_send_childv2, null);
				} else {
					isSelf = false;
					view = inflater.inflate(R.layout.msg_send_childv1, null);
				}
			}

			ImageView imgv = (ImageView) view
					.findViewById(R.id.msg_send_childv_head);
			if (msg.headUrl != null && !msg.headUrl.equals("")) {
				AsyncImageLoader.getInstance().loadPortrait(msg.headUrl,
						msg.headUrl, imgv);
			}
			TextView nameTV = (TextView) view
					.findViewById(R.id.msg_send_childv_name);
			if (isSelf) {
				nameTV.setText("我");
			} else {
				nameTV.setText(msg.wbName);
			}
			TextView contentTV = (TextView) view
					.findViewById(R.id.msg_send_childv_content);
			contentTV.setText(msg.cont);
			if (isSelf) {
				ProgressBar pb = (ProgressBar) view
						.findViewById(R.id.msg_send_childv_progress);
				if (msg.state == Mail.STATE_SENDING) {
					pb.setVisibility(View.VISIBLE);
				} else if (msg.state == Mail.STATE_SEND_SUCCESS) {
					Tools.showToast(context, "发送成功");
					pb.setVisibility(View.GONE);
				} else {
					Tools.showToast(context, "发送失败");
					pb.setVisibility(View.GONE);
				}
			}
			return view;
		}
	}

}
