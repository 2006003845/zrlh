package cn.zrong.widget;

import java.io.File;

import json.JSONException;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.zrong.ApplicationData;
import cn.zrong.GameAPI;
import cn.zrong.activity.GroupActivity;
import cn.zrong.activity.GroupInfoActivity;
import cn.zrong.activity.HomeActivity;
import cn.zrong.activity.R;
import cn.zrong.adapter.FaceAdapter;
import cn.zrong.connection.Community;
import cn.zrong.connection.Connection;
import cn.zrong.connection.IOWriter;
import cn.zrong.connection.Protocol;
import cn.zrong.entity.Face;
import cn.zrong.entity.Group;
import cn.zrong.loader.AsyncImageLoader;
import cn.zrong.utils.TextUtil;
import cn.zrong.weibobinding.BroadcastSender;

public class HeadListView extends ListView {
	private Context context;

	public HeadListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;
		// init();
	}

	public HeadListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// init();
	}

	public HeadListView(Context context) {
		super(context);
		// init();
	}

	private TextView groupNameTV, groupMembNumTV, groupMasterTV;
	private ImageView groupImageV, talkContentImgV;
	private TextView groupNotice;
	private RelativeLayout talkLayout;
	private EditText talkContentET, talkBtn;
	private TextView leftTextNumTV;

	private Group mGroup;
	private TextView exitTV;

	public void init(final Context context, Group group) {
		this.context = context;
		this.mGroup = group;
		LinearLayout view = (LinearLayout) getView(context,
				R.layout.group_listv_headerview);
		addHeaderView(view);
		measureView(view);

		express = (GridView) view
				.findViewById(R.id.group_talk_express_gridview);
		initexpress();
		view.findViewById(R.id.group_headview_tap).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO 跳转到GroupInfoActivity
						Intent intent = new Intent();
						Bundle b = new Bundle();
						b.putSerializable("group", mGroup);
						intent.putExtras(b);
						GroupInfoActivity.launch(context, intent);
					}
				});
		exitTV = (TextView) view.findViewById(R.id.group_exit_btn);
		if (mGroup.isAdmin() || mGroup.isSys()) {
			exitTV.setVisibility(View.GONE);
		}

		exitTV.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showExitGroupDiag();
			}
		});
		// titleTV = (TextView) view.findViewById(R.id.group_title_name);
		groupNameTV = (TextView) view.findViewById(R.id.group_name_tv);
		groupMembNumTV = (TextView) view.findViewById(R.id.group_membernum_tv);
		groupMasterTV = (TextView) view.findViewById(R.id.group_master_tv);
		groupImageV = (ImageView) view.findViewById(R.id.group_imgv);
		String headUrl = group.getgImgUrl();
		if (headUrl != null && !headUrl.equals("")) {
			AsyncImageLoader.getInstance().loadPortrait(headUrl, headUrl,
					groupImageV);
		}

		groupNotice = (TextView) view.findViewById(R.id.group_notice_content);
		talkBtn = (EditText) view.findViewById(R.id.group_talk_btn);
		talkContentImgV = (ImageView) view
				.findViewById(R.id.group_talk_content_imgv);
		talkContentET = (EditText) view
				.findViewById(R.id.group_talk_content_et);
		talkContentET.addTextChangedListener(new TextWatcher() {
			String text = "";

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				int size = talkContentET.getText().length();
				if (size == 140) {
					text = talkContentET.getText().toString().trim();
				}
				if (size > 140) {
					talkContentET.setText(text);
				}
				leftTextNumTV.setText("" + (140 - size));

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		leftTextNumTV = (TextView) view
				.findViewById(R.id.group_talk_send_left_tv);
		talkLayout = (RelativeLayout) view.findViewById(R.id.group_talk_layout);
		talkBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (talkLayout.getVisibility() == View.GONE) {
					talkLayout.setVisibility(View.VISIBLE);
					talkBtn.setVisibility(View.GONE);
				}
			}
		});
		view.findViewById(R.id.group_talk_cancel).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO
						talkLayout.setVisibility(View.GONE);
						talkBtn.setVisibility(View.VISIBLE);
					}
				});
		view.findViewById(R.id.group_talk_clear).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						talkContentET.setText("");
					}
				});
		view.findViewById(R.id.group_talk_photo).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO
						showPicDialog();
					}
				});
		view.findViewById(R.id.group_talk_face).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO
						showFace();
						InputMethodManager m = (InputMethodManager) context
								.getSystemService(Context.INPUT_METHOD_SERVICE);
						m.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
					}
				});

		view.findViewById(R.id.group_talk_send).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO发送
						if (mGroup.haveJoin != 2) {
							Toast.makeText(context, "您不是已该圈子成员,不能发表言论",
									Toast.LENGTH_SHORT).show();
							return;
						}
						new SendMsgThread().start();
						Toast.makeText(context, "发送中...", Toast.LENGTH_LONG)
								.show();
						talkBtn.setVisibility(View.VISIBLE);
						talkLayout.setVisibility(View.GONE);
						talkContentET.setText("");
					}
				});
		if (group != null) {
			// titleTV.setText(GroupActivity.group.getgName());
			groupNameTV.setText(group.getgName());
			groupMembNumTV.setText(group.getgSize());
			groupMasterTV.setText(group.getgAdName());
			groupNotice.setText(group.getgNot());
		}
	}

	public void updateGroupInfo(Group group) {
		mGroup = group;
		if (group != null) {
			// titleTV.setText(GroupActivity.group.getgName());
			groupNameTV.setText(group.getgName());
			groupMembNumTV.setText(group.getgSize());
			groupMasterTV.setText(group.getgAdName());
			groupNotice.setText(group.getgNot());
		}

	}

	public void setTalkPic(Bitmap bm) {
		talkContentImgV.setVisibility(View.VISIBLE);
		talkContentImgV.setImageBitmap(bm);
	}

	private GridView express;

	public void initexpress() {

		express.setAdapter(new FaceAdapter(context, Face.faceNames));
		express.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				if (position < Face.faceNames.length) {
					talkContentET.append(TextUtil.formatImage("["
							+ Face.faceNames[position] + "]", context));
				}
			}
		});
	}

	/**
	 * 显示表情
	 */
	private void showFace() {
		if (express.getVisibility() == View.GONE) {
			express.setVisibility(View.VISIBLE);
		} else {
			express.setVisibility(View.GONE);
		}
	}

	class SendMsgThread extends Thread {
		@Override
		public void run() {
			while (GroupActivity.PicName == "") {
			}
			Connection.sendMessage(
					Protocol.msgType_sendWeibo,
					IOWriter.getSendWeibo(Protocol.msgType_sendWeibo,
							ApplicationData.currentUser.getKeyID(), "",
							talkContentET.getText().toString(),
							Protocol.ProtocolWeibo.TYPE_WeiboSend_GROUP,
							mGroup.getgNum(), "", GroupActivity.PicName)
							.getBytes(), GameAPI.Port_Weibo);

			// 微博绑定分享
			File file = new File(GroupActivity.SD_CARD_TEMP_DIR);
			if (file.exists()) {
				BroadcastSender.createInstance(GroupActivity.mInstance)
						.sendBroadcast(talkContentET.getText().toString(),
								GroupActivity.SD_CARD_TEMP_DIR);
			} else {
				BroadcastSender.createInstance(GroupActivity.mInstance)
						.sendBroadcast(talkContentET.getText().toString());
			}
			super.run();
		}
	}

	private void showPicDialog() {
		AlertDialog dialog = new AlertDialog.Builder(context).setItems(
				R.array.picFrom, picListener).create();
		dialog.show();
	}

	private DialogInterface.OnClickListener picListener = new DialogInterface.OnClickListener() {

		@Override
		public void onClick(DialogInterface dialog, int which) {
			if (which == 0) {
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				intent.putExtra(MediaStore.EXTRA_OUTPUT,
						Uri.fromFile(new File(GroupActivity.SD_CARD_TEMP_DIR)));
				GroupActivity.mInstance.startActivityForResult(intent, 0);
			} else if (which == 1) {
				if (!isSdCardExist()) {
					Toast.makeText(context, "您的SDCard不存在", Toast.LENGTH_SHORT)
							.show();
					return;
				}
				Intent intent = new Intent();
				// Type为image
				intent.setType("image/*");
				// Action:选择数据然后返回
				intent.setAction(Intent.ACTION_GET_CONTENT);
				GroupActivity.mInstance.startActivityForResult(intent, 1);
			}
		}
	};

	private void showExitGroupDiag() {

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(android.content.Context.LAYOUT_INFLATER_SERVICE);
		final View layout = inflater.inflate(R.layout.dialog_twice_true, null);
		final AlertDialog dialog = new AlertDialog.Builder(context).create();
		dialog.show();
		dialog.getWindow().setContentView(layout);
		TextView conTV = (TextView) dialog
				.findViewById(R.id.dialog_twice_content);
		conTV.setText("您确认要退出" + mGroup.getgName() + "吗?");
		dialog.findViewById(R.id.dialog_twice_ok).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						Community comm = Community.getInstance(context);
						if (comm == null) {
							return;
						}
						try {
							if (exitTV.getText().equals("退出圈子")) {
								boolean b = comm.exitGroup(mGroup.getgNum());
								if (b) {
									exitTV.setText("加入圈子");
									mGroup.haveJoin = -1;
									// 更新我的圈子
									if (HomeActivity.mInstance != null) {
										HomeActivity.mInstance.updateMyGroups();
									}
								} else {
									exitTV.setText("退出圈子");
									Toast.makeText(context, "退出失败",
											Toast.LENGTH_SHORT).show();
								}
							} else {
								if (mGroup.haveJoin == -1) {
									int b = comm.joinGroup(mGroup.getgNum());
									mGroup.haveJoin = b;
									if (b == 0) {
										exitTV.setText("审核中...");
									}
								}
							}
						} catch (JSONException e) {
							// Log.e("error", e.getMessage());
						}
						dialog.dismiss();
					}
				});
		dialog.findViewById(R.id.dialog_twice_cancel).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						dialog.dismiss();
					}
				});

	}

	private boolean isSdCardExist() {
		return Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED);
	}

	private void measureView(View child) {
		ViewGroup.LayoutParams p = child.getLayoutParams();
		if (p == null) {
			p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
					ViewGroup.LayoutParams.WRAP_CONTENT);
		}

		int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0 + 0, p.width);
		int lpHeight = p.height;
		int childHeightSpec;
		if (lpHeight > 0) {
			childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight,
					MeasureSpec.EXACTLY);
		} else {
			childHeightSpec = MeasureSpec.makeMeasureSpec(0,
					MeasureSpec.UNSPECIFIED);
		}
		child.measure(childWidthSpec, childHeightSpec);
	}

	private View getView(Context context, int layout) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(layout, this, false);
		return view;
	}
}
