package cn.zrong.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import cn.zrong.ApplicationData;
import cn.zrong.GameAPI;
import cn.zrong.activity.MessageListActivity;
import cn.zrong.activity.R;
import cn.zrong.connection.Connection;
import cn.zrong.connection.IOWriter;
import cn.zrong.connection.Protocol;
import cn.zrong.entity.Mail;
import cn.zrong.entity.MessageManager;
import cn.zrong.entity.Request;
import cn.zrong.loader.AsyncImageLoader;

public class MSGTwoLevelAdapter extends BaseExpandableListAdapter {
	private Context mContext;

	private List<MessageManager> data;

	private int titleLayout;
	private int childLayout;
	private int childLayout2;
	
	private MessageListActivity act;

	private LayoutInflater mInflater;

	public MSGTwoLevelAdapter(MessageListActivity act, Context context,
			List<MessageManager> msgManagerList, int titleLayout,
			int childLayout, int childLayout2) {
		mContext = context;
		this.titleLayout = titleLayout;
		this.childLayout = childLayout;
		this.childLayout2 = childLayout2;
		this.act = act;
		data = msgManagerList;
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getGroupCount() {
		return data.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return data.get(groupPosition).getMessageList().size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return data.get(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return data.get(groupPosition).getMessageList().get(childPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		View v;
		if (convertView == null) {
			v = newGroupView(isExpanded, parent);
		} else {
			v = convertView;
		}
		bindGroupView(v, data.get(groupPosition), groupPosition, isExpanded);
		return v;
	}

	private void bindGroupView(View view, MessageManager msgM,
			int groupPosition, boolean isExpanded) {
		// TODO
		TextView titleTV = (TextView) view
				.findViewById(R.id.msglist_item_msgtype_tv);
		titleTV.setText(msgM.getMsgTypeName(msgM.getMessageType()));
		TextView remindTV = (TextView) view
				.findViewById(R.id.msglist_item_msgremind_tv);
		switch (msgM.getMessageType()) {
		case MessageManager.TYPE_MESSAGE_MAIL:
			remindTV.setText(mContext.getResources().getString(
					R.string.message_item_ninyou)
					+ msgM.getMessageList().size()
					+ mContext.getResources().getString(
							R.string.message_item_emailNum));
			break;
		case MessageManager.TYPE_MESSAGE_REQUEST:
			remindTV.setText(mContext.getResources().getString(
					R.string.message_item_ninyou)
					+ msgM.getMessageList().size()
					+ mContext.getResources().getString(
							R.string.message_item_requestNum));
			break;
		}
		ImageView expandImgV = (ImageView) view
				.findViewById(R.id.msglist_item_expand_imgv);
		if (isExpanded) {
			expandImgV.setImageResource(R.drawable.btn_reduce_selector);
		} else {
			expandImgV.setImageResource(R.drawable.btn_add_selector);
		}
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		View v;
		int type = data.get(groupPosition).getMessageType();
		if (convertView == null) {
			v = newChildView(isLastChild, parent, type);
		} else {
			v = convertView;
		}
		bindChildView(v, data.get(groupPosition), type, childPosition,
				groupPosition);
		return v;
	}

	private void bindChildView(View view, final MessageManager mm, int type,
			final int childPosition, int groupPosition) {
		// TODO
		if (type == MessageManager.TYPE_MESSAGE_REQUEST) {
			ImageView imgV = (ImageView) view
					.findViewById(R.id.msglist_child_item_imgv);

			TextView nameTV = (TextView) view
					.findViewById(R.id.msglist_child_item_msg_who);
			TextView whatTV = (TextView) view
					.findViewById(R.id.msglist_child_item_msg_what);
			final Request sm = (Request) mm.getMessageList().get(childPosition);
			if (sm.head != null && !sm.head.equals("")) {
				AsyncImageLoader.getInstance().loadPortrait(sm.head, sm.head,
						imgV);
			}
			nameTV.setText(sm.name);
			whatTV.setText(sm.msg);
			view.findViewById(R.id.msglist_child_item_btn_agree)
					.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							if (sm.requsest_type == Request.REQUEST_TYPE_FRIEND) {
								Connection
										.sendMessage(
												Protocol.msgType_agreeAddFriendRequest,
												IOWriter.getAuthFriend(
														Protocol.msgType_agreeAddFriendRequest,
														ApplicationData.currentUser
																.getKeyID(),
														sm.index, 1).getBytes(),
												GameAPI.Port_Role);
							} else if (sm.requsest_type == Request.REQUEST_TYPE_GROUP) {
								Connection
										.sendMessage(
												Protocol.msgType_agreeAddGroupRequest,
												IOWriter.getAuthGroup(
														Protocol.msgType_agreeAddGroupRequest,
														ApplicationData.currentUser
																.getKeyID(),
														"", "", sm.index, 1)
														.getBytes(),
												GameAPI.Port_Weibo);
							}
							mm.getMessageList().remove(childPosition);
							MSGTwoLevelAdapter.this.notifyDataSetChanged();
						}
					});
			view.findViewById(R.id.msglist_child_item_btn_refuse)
					.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							if (sm.requsest_type == Request.REQUEST_TYPE_FRIEND) {
								Connection
										.sendMessage(
												Protocol.msgType_agreeAddFriendRequest,
												IOWriter.getAuthFriend(
														Protocol.msgType_agreeAddFriendRequest,
														ApplicationData.currentUser
																.getKeyID(),
														sm.index, 0).getBytes(),
												GameAPI.Port_Role);
							} else if (sm.requsest_type == Request.REQUEST_TYPE_GROUP) {
								Connection
										.sendMessage(
												Protocol.msgType_agreeAddGroupRequest,
												IOWriter.getAuthGroup(
														Protocol.msgType_agreeAddGroupRequest,
														ApplicationData.currentUser
																.getKeyID(),
														"", "", sm.index, 0)
														.getBytes(),
												GameAPI.Port_Weibo);
							}
							mm.getMessageList().remove(childPosition);
							MSGTwoLevelAdapter.this.notifyDataSetChanged();
						}
					});

		} else if (type == MessageManager.TYPE_MESSAGE_MAIL) {
			ImageView imgV = (ImageView) view
					.findViewById(R.id.msglist_child_item_imgv);
			TextView nameTV = (TextView) view
					.findViewById(R.id.msglist_child_item_msg_who);
			TextView whatTV = (TextView) view
					.findViewById(R.id.msglist_child_item_msg_what);
			TextView whenTV = (TextView) view
					.findViewById(R.id.msglist_child_item_msg_when);
			Mail sm = (Mail) mm.getMessageList().get(childPosition);
			if (sm.headUrl != null && !sm.headUrl.equals("")) {
				AsyncImageLoader.getInstance().loadPortrait(sm.headUrl,
						sm.headUrl, imgV);
			}
			nameTV.setText(sm.wbName);
			whatTV.setText(sm.cont);
			whenTV.setText(sm.time);
		}
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * Instantiates a new View for a group.
	 * 
	 * @param isExpanded
	 *            Whether the group is currently expanded.
	 * @param parent
	 *            The eventual parent of this new View.
	 * @return A new group View
	 */
	public View newGroupView(boolean isExpanded, ViewGroup parent) {
		return mInflater.inflate(titleLayout, null);
	}

	/**
	 * Instantiates a new View for a child.
	 * 
	 * @param isLastChild
	 *            Whether the child is the last child within its group.
	 * @param parent
	 *            The eventual parent of this new View.
	 * @return A new child View
	 */
	public View newChildView(boolean isLastChild, ViewGroup parent, int type) {
		if (type == MessageManager.TYPE_MESSAGE_REQUEST) {
			return mInflater.inflate(childLayout2, null);
		} else {
			return mInflater.inflate(childLayout, null);

		}
	}

}
