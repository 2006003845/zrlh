package cn.zrong.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MessageManager implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8680228344631242699L;
	/**
	 * mail消息类型:1
	 */
	public static final int TYPE_MESSAGE_MAIL = 1;
	/**
	 * request 请求类型:2
	 */
	public static final int TYPE_MESSAGE_REQUEST = 2;

	private String msgTypeName = "";

	public String getMsgTypeName(int messageType) {
		switch (messageType) {
		case TYPE_MESSAGE_MAIL:
			return "私信";

		case TYPE_MESSAGE_REQUEST:

			return "请求";
		}
		return "";
	}

	private int messageType;

	public int getMessageType() {
		return messageType;
	}

	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}

	public void setMessageList(List<Msg> messageList) {
		if (messageList != null) {
			this.messageList.clear();
			this.messageList.addAll(messageList);
		}
	}

	public String getMsgTypeName() {
		return msgTypeName;
	}

	public void setMsgTypeName(String msgTypeName) {
		this.msgTypeName = msgTypeName;
	}

	public List<Msg> getMessageList() {
		return messageList;
	}

	private List<Msg> messageList = new ArrayList<Msg>();

	public void addMessage(Msg msg) {
		messageList.add(msg);
	}

	public void setMessage(int index, Msg msg) {
		messageList.set(index, msg);
	}

	public void removeMessage(int index) {
		messageList.remove(index);
	}

	public void removeMessage(Msg msg) {
		messageList.remove(msg);
	}

}
