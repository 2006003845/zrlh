package cn.zrong.activity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import json.JSONException;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import cn.zrong.ApplicationData;
import cn.zrong.GameAPI;
import cn.zrong.activity.base.BaseActivity;
import cn.zrong.connection.Community;
import cn.zrong.connection.Connection;
import cn.zrong.connection.IOWriter;
import cn.zrong.connection.Protocol;
import cn.zrong.connection.Utility;
import cn.zrong.entity.Friend;
import cn.zrong.entity.GameUser;
import cn.zrong.loader.AsyncDataLoader;
import cn.zrong.loader.AsyncDataLoader.Callback;

public class GroupCreateActivity extends BaseActivity {

	public static void launch(Context c, Intent intent) {
		if (intent == null) {
			intent = new Intent();
		}
		intent.setClass(c, GroupCreateActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		c.startActivity(intent);
	}

	private Context context;

	public static GroupCreateActivity mInstance;

	private boolean isNameRepeat = false;

	public ProgressDialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;
		mInstance = this;
		setContentView(R.layout.group_create);
		initView();
	}

	private ImageView groupFormImgV, nameMarkImgV;
	private TextView nameTestTV;
	private EditText groupNameET, friendsET, introET, noticeET;
	private ListView friendListV;
	private ImageView addMembImgV;

	private List<Friend> selectedFriendsStrs = new ArrayList<Friend>();
	private StringBuilder userIds = new StringBuilder();

	private CommitGroupThread thread;

	private void initView() {
		this.findViewById(R.id.back_btn).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						getGameContext().finish();
					}
				});
		this.findViewById(R.id.group_create_createbtn).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO
						if (isNameRepeat) {
							Toast.makeText(context, "您的圈名已经有人使用，请更换一个", 1000)
									.show();
							return;
						}
						if (thread == null) {
							dialog = new ProgressDialog(context,
									android.R.style.Widget_ProgressBar_Small);
							// dialog.
							dialog.setCancelable(true);
							dialog.setMessage("发送创建申请...");
							dialog.show();
							thread = new CommitGroupThread();
							thread.start();
						} else {
							Toast.makeText(context, "已经发送申请",
									Toast.LENGTH_SHORT).show();
						}

					}
				});

		friendsET = (EditText) this.findViewById(R.id.group_create_memb_et);

		introET = (EditText) this.findViewById(R.id.group_create_intro_et);
		noticeET = (EditText) this.findViewById(R.id.group_create_notice_et);
		groupFormImgV = (ImageView) this
				.findViewById(R.id.group_create_form_imgv);
		nameMarkImgV = (ImageView) this
				.findViewById(R.id.group_create_name_mark);
		nameTestTV = (TextView) this.findViewById(R.id.group_create_name_test);
		groupFormImgV.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showPic();
			}
		});
		groupNameET = (EditText) this.findViewById(R.id.group_create_name_et);
		groupNameET.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				String name = groupNameET.getText().toString().trim();
				if (isNameRepeat(name)) {
					nameTestTV.setVisibility(View.VISIBLE);
					isNameRepeat = true;
					nameMarkImgV.setImageResource(R.drawable.img_wrong);
				} else {
					isNameRepeat = false;
					nameMarkImgV.setImageResource(R.drawable.img_right);
					nameTestTV.setVisibility(View.INVISIBLE);
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});
		addMembImgV = (ImageView) this
				.findViewById(R.id.group_create_addmemb_btn);
		addMembImgV.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (friendListV.getVisibility() == View.VISIBLE) {
					friendListV.setVisibility(View.INVISIBLE);
				} else {
					friendListV.setVisibility(View.VISIBLE);
				}
			}
		});
		friendListV = (ListView) this
				.findViewById(R.id.group_create_friends_lv);
		friendListV.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO
				Friend userInfo = ApplicationData.friendsList.get(position);
			}
		});
		if (ApplicationData.friendsList.size() > 0) {
			FriendListAdapter2 adapter_friends = new FriendListAdapter2(
					context, ApplicationData.friendsList,
					R.layout.group_friendlist_item);
			friendListV.setAdapter(adapter_friends);
		} else {
			new AsyncDataLoader(loadFriendsCallback).execute();
		}
	}

	private Callback loadFriendsCallback = new Callback() {
		ProgressDialog dialog;

		@Override
		public void onStart() {
			try {
				Community comm = Community.getInstance(context);
				if (comm != null) {
					List<Friend> list = comm.getFriendList("", 1, 20);
					if (list != null) {
						ApplicationData.friendsList.clear();
						ApplicationData.friendsList.addAll(list);
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void onPrepare() {
			dialog = new ProgressDialog(context,
					android.R.style.Widget_ProgressBar_Small);
			dialog.setCancelable(false);
			dialog.setMessage("加载...");
			dialog.show();
		}

		@Override
		public void onFinish() {
			setProgressBarIndeterminateVisibility(false);
			dialog.dismiss();
			FriendListAdapter2 adapter_friends = new FriendListAdapter2(
					context, ApplicationData.friendsList,
					R.layout.group_friendlist_item);
			friendListV.setAdapter(adapter_friends);
		}
	};
	private static final int DIALOG_PIC_SOURCE = 2; // 询问图片来源

	/**
	 * 插入图片（未完成Camera）
	 */
	private void showPic() {
		showDialog(DIALOG_PIC_SOURCE);
	}

	protected Dialog onCreateDialog(int id, Bundle args) {
		switch (id) {
		case DIALOG_PIC_SOURCE:
			return new AlertDialog.Builder(this).setItems(R.array.picFrom,
					picListener).create();
		}
		return super.onCreateDialog(id);
	}

	private String SD_CARD_TEMP_DIR = Environment.getExternalStorageDirectory()
			+ File.separator + "tmpPhoto1.jpg";
	private DialogInterface.OnClickListener picListener = new DialogInterface.OnClickListener() {

		@Override
		public void onClick(DialogInterface dialog, int which) {
			if (which == 0) {
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				intent.putExtra(MediaStore.EXTRA_OUTPUT,
						Uri.fromFile(new File(SD_CARD_TEMP_DIR)));
				startActivityForResult(intent, 0);
			} else if (which == 1) {
				Intent intent = new Intent();
				// Type为image
				intent.setType("image/*");
				// Action:选择数据然后返回
				intent.setAction(Intent.ACTION_GET_CONTENT);
				startActivityForResult(intent, 1);
			}
		}
	};

	private byte[] contentPicByte;
	private Bitmap contentPicBm;

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		try {
			super.onActivityResult(requestCode, resultCode, data);
			switch (requestCode) {
			case 0:
				BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
				bitmapOptions.inSampleSize = 8;
				File file = new File(SD_CARD_TEMP_DIR);
				if (file.exists()) {
					contentPicBm = BitmapFactory.decodeFile(SD_CARD_TEMP_DIR,
							bitmapOptions);
					if (contentPicBm != null) {
						groupFormImgV.setImageBitmap(contentPicBm);
						new UploadPicThread(contentPicBm).start();
					}
				}
				break;

			case 1:
				if (resultCode == RESULT_OK) {
					Uri uri = data.getData();
					try {
						InputStream is = getContentResolver().openInputStream(
								uri);
						ByteArrayOutputStream outputStream = new ByteArrayOutputStream(
								1024);
						byte[] buffer = new byte[1024];
						int lenth;
						while ((lenth = is.read(buffer)) >= 0) {
							outputStream.write(buffer, 0, lenth);
						}
						contentPicByte = outputStream.toByteArray();
						// Drawable drawable = BitmapDrawable.createFromStream(
						// getContentResolver().openInputStream(uri), "");
						contentPicBm = BitmapFactory.decodeByteArray(
								contentPicByte, 0, contentPicByte.length);
						// contentPic.setImageDrawable(drawable);
						if (contentPicBm != null) {
							groupFormImgV.setImageBitmap(contentPicBm);
							new UploadPicThread(contentPicBm).start();
						}
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				break;
			}
		} catch (Exception e) {
			Log.e("error", e.getMessage());
		}
	}

	private boolean isNameRepeat(String name) {
		// TODO
		boolean isRepeat = false;
		// try {
		// isRepeat = Community.getInstance().isGroupNameRepeat(name);
		// } catch (JSONException e) {
		// e.printStackTrace();
		// }
		return isRepeat;
	}

	@Override
	public BaseActivity getGameContext() {
		// TODO Auto-generated method stub
		return GroupCreateActivity.this;
	}

	class FriendListAdapter2 extends BaseAdapter {

		private List<Friend> friendsList;
		private int layoutResID;
		private LayoutInflater inflater;
		private Context context;

		public FriendListAdapter2(Context context, List<Friend> friendsList,
				int layoutResID) {
			this.context = context;
			this.friendsList = friendsList;
			this.layoutResID = layoutResID;
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
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			View view = convertView;
			if (view == null) {
				view = inflater.inflate(layoutResID, null);
			}
			Friend friend = friendsList.get(position);

			TextView nameTV = (TextView) view
					.findViewById(R.id.group_friendlist_item_name);
			nameTV.setText(friend.nickName);
			CheckBox chB = (CheckBox) view
					.findViewById(R.id.group_friendlist_item_checkbox);
			chB.setOnCheckedChangeListener(new OnCheckedChangeListener() {

				@Override
				public void onCheckedChanged(CompoundButton buttonView,
						boolean isChecked) {
					if (isChecked) {
						selectedFriendsStrs.add(friendsList.get(position));
					} else {
						selectedFriendsStrs.remove(friendsList.get(position));
					}
					StringBuilder strB = new StringBuilder();
					for (int i = 0; i < selectedFriendsStrs.size(); i++) {
						Friend u = selectedFriendsStrs.get(i);
						if (i > 0) {
							strB.append(";");
							userIds.append(";");
						}
						strB.append(u.nickName);
						userIds.append(u.wbId);
					}

					friendsET.setText(strB);
				}
			});
			return view;
		}

	}

	private String headPicName = null;

	private String uploadPic(Bitmap bm) {
		bm = Bitmap.createScaledBitmap(bm, 150, 150, false);
		String result = "";
		// 重新读入图片，注意这次要把options.inJustDecodeBounds 设为 false哦
		String imgUrl = context.getResources().getString(R.string.imgUrl);
		try {
			result = Utility.openUrl(this, imgUrl + "?keyID="
					+ ApplicationData.currentUser.getKeyID() + "&type=3",
					"POST", bm);
			Log.i("Loh", "result:" + result);
		} catch (Exception e) {
			Log.e("Loh", e.getMessage());
		}
		return result;
	}

	class UploadPicThread extends Thread {
		Bitmap bm;

		public UploadPicThread(Bitmap bm) {
			this.bm = bm;
		}

		@Override
		public void run() {
			headPicName = uploadPic(bm);
			Log.i("Loh", "head:" + headPicName);
			super.run();
		}
	}

	class CommitGroupThread extends Thread {
		@Override
		public void run() {
			while (headPicName == "") {
			}

			Connection.sendMessage(
					Protocol.msgType_createGroups,
					IOWriter.getCreateGroup(Protocol.msgType_createGroups,
							ApplicationData.currentUser.getKeyID(), "",
							groupNameET.getText().toString().trim(),
							introET.getText().toString().trim(),
							noticeET.getText().toString().trim(), "1", "0",
							userIds.toString().trim(),
							headPicName == null ? "" : headPicName).getBytes(),
					GameAPI.Port_Weibo);

			super.run();
		}
	}

	@Override
	protected void onDestroy() {
		if (contentPicBm != null && !contentPicBm.isRecycled()) {
			contentPicBm.recycle();
			contentPicBm = null;
		}
		super.onDestroy();
	}

}
