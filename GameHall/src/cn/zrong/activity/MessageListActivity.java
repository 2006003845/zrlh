package cn.zrong.activity;

import java.util.ArrayList;
import java.util.List;

import json.JSONException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import cn.zrong.ApplicationData;
import cn.zrong.activity.base.BaseActivity;
import cn.zrong.adapter.MSGTwoLevelAdapter;
import cn.zrong.connection.Community;
import cn.zrong.entity.Mail;
import cn.zrong.entity.MessageManager;
import cn.zrong.entity.Request;
import cn.zrong.loader.AsyncDataLoader;
import cn.zrong.loader.AsyncDataLoader.Callback;

public class MessageListActivity extends BaseActivity {

	private Context context;
	private ExpandableListView messageTwoLeveListV;
	private MSGTwoLevelAdapter msgAdapter;
	private List<MessageManager> msgMList = new ArrayList<MessageManager>();

	public static void launch(Context c) {
		Intent intent = new Intent(c, MessageListActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		c.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;

		setContentView(R.layout.msglist);

		this.findViewById(R.id.back_btn).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						MessageListActivity.this.finish();
					}
				});
		this.findViewById(R.id.refresh_btn).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						updateMsgList();
					}
				});
		messageTwoLeveListV = (ExpandableListView) this
				.findViewById(R.id.msglist_expandablelistv);
		messageTwoLeveListV.setGroupIndicator(null);
		msgAdapter = new MSGTwoLevelAdapter(this, context, msgMList,
				R.layout.msglist_item, R.layout.msglist_child_item,
				R.layout.msglist_child2_item);
		messageTwoLeveListV.setAdapter(msgAdapter);
		messageTwoLeveListV.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				// TODO

				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				MessageManager mm = msgMList.get(groupPosition);
				if (mm.getMessageType() == MessageManager.TYPE_MESSAGE_MAIL) {
					bundle.putSerializable("msg",
							mm.getMessageList().get(childPosition));
					intent.putExtras(bundle);
					SendMessageActivity.launch(context, intent);

				} else if (mm.getMessageType() == MessageManager.TYPE_MESSAGE_REQUEST) {

				}

				return false;
			}
		});
		updateMsgList();
	}

	public void updateMsgList() {
		new AsyncDataLoader(getMsgCallback).execute();

	}

	private Callback getMsgCallback = new Callback() {

		@Override
		public void onStart() {
			Community comm = Community.getInstance(context);
			if (comm != null) {
				try {
					ApplicationData.mailList.clear();
					List<Mail> list2 = comm.getMailList();
					if (list2 != null) {

						for (Mail mail : list2) {
							if (mail.type == MessageManager.TYPE_MESSAGE_MAIL) {
								ApplicationData.mailList.add(mail);
							}
						}
					}
					ApplicationData.requestList.clear();
					List<Request> list3 = comm.getRequestListOfFriend();
					if (list3 != null) {

						for (Request req : list3) {
							if (req.type == MessageManager.TYPE_MESSAGE_REQUEST) {
								ApplicationData.requestList.add(req);
							}
						}
					}

					List<Request> list4 = comm.getRequestListOfGroup();
					if (list4 != null) {

						for (Request req : list4) {
							if (req.type == MessageManager.TYPE_MESSAGE_REQUEST) {
								ApplicationData.requestList.add(req);
							}
						}
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}

		@Override
		public void onPrepare() {
		}

		@Override
		public void onFinish() {
			initData();
			msgAdapter.notifyDataSetChanged();
		}
	};

	private void initData() {
		msgMList.clear();
		MessageManager msgManager = new MessageManager();
		msgManager.setMessageType(MessageManager.TYPE_MESSAGE_MAIL);
		for (int i = 0; i < ApplicationData.mailList.size(); i++) {
			msgManager.addMessage(ApplicationData.mailList.get(i));
		}
		msgMList.add(msgManager);
		MessageManager msgManager2 = new MessageManager();
		msgManager2.setMessageType(MessageManager.TYPE_MESSAGE_REQUEST);
		for (int i = 0; i < ApplicationData.requestList.size(); i++) {
			msgManager2.addMessage(ApplicationData.requestList.get(i));
		}
		msgMList.add(msgManager2);
	}

	@Override
	public BaseActivity getGameContext() {
		// TODO Auto-generated method stub
		return MessageListActivity.this;
	}

}
