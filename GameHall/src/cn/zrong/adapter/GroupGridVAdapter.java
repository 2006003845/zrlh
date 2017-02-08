package cn.zrong.adapter;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import json.JSONException;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import cn.zrong.activity.R;
import cn.zrong.connection.Community;
import cn.zrong.entity.GameItem;
import cn.zrong.entity.Group;
import cn.zrong.loader.AsyncImageLoader;
import cn.zrong.loader.ImageCache;
import cn.zrong.utils.Tools;

public class GroupGridVAdapter extends BaseAdapter {
	private List<Group> groupList;
	private Context context;
	private LayoutInflater inflater;

	private boolean isEditState = false;

	public boolean isEditState() {
		return isEditState;
	}

	public void setEditState(boolean isEditState) {
		this.isEditState = isEditState;
	}

	public GroupGridVAdapter(Context context, List<Group> groupList) {
		this.context = context;
		this.groupList = groupList;
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
			view = inflater.inflate(R.layout.home_group_gridv_item, null);
		}
		ImageView deleteImgV = (ImageView) view
				.findViewById(R.id.group_gridv_item_delete);

		final Group group = groupList.get(position);
		ImageView groupImgV = (ImageView) view
				.findViewById(R.id.group_gridv_item_imgv);
		ProgressBar imgPB = (ProgressBar) view
				.findViewById(R.id.group_gridv_item_imgv_pb);
		imgPB.setVisibility(View.GONE);
		String imgUrl = group.getgImgUrl();
		if (imgUrl != null && !imgUrl.equals("")) {
			Bitmap bm = ImageCache.getInstance().getImgCach2(imgUrl);
			if (bm != null) {
				imgPB.setVisibility(View.GONE);
				groupImgV.setImageBitmap(bm);

			} else {
				imgPB.setVisibility(View.VISIBLE);
				new LoadImgTask(imgUrl, groupImgV, imgPB).execute();
			}
		}
		// if (imgUrl != null && !imgUrl.equals("")) {
		// ImageCache.getInstance().loadImg(context, imgUrl, groupImgV, this);
		// }
		if (isEditState) {
			imgPB.setVisibility(View.GONE);
			deleteImgV.setVisibility(View.VISIBLE);

		} else {
			deleteImgV.setVisibility(View.INVISIBLE);

		}
		TextView nameTV = (TextView) view
				.findViewById(R.id.group_gridv_item_name);
		if (group.getgName() != null) {
			nameTV.setText(group.getgName());
		}

		deleteImgV.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (group.isAdmin()) {
					Tools.showToast(context, "无法删除圈子，可以在圈子资料中解散圈子");
					return;
				} else if (group.isSys()) {
					Tools.showToast(context, "无法系统删除圈子");
					return;
				} else {
					showExitGroupDiag(group);
				}

			}
		});
		return view;
	}

	class LoadImgTask extends AsyncTask<Void, Integer, Bitmap> {
		ImageView imgv;
		String url;

		ProgressBar pb;

		public LoadImgTask(String url, ImageView imgv, ProgressBar pb) {
			this.imgv = imgv;
			this.url = url;
			this.pb = pb;
		}

		@Override
		protected Bitmap doInBackground(Void... params) {
			Bitmap bm = null;
			try {
				bm = ImageCache.getInstance().getImgCach(url);
			} catch (MalformedURLException e) {

			} catch (IOException e) {

			} catch (Exception e) {

			}
			return bm;
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			pb.setVisibility(View.GONE);
			if (result != null && imgv != null) {
				imgv.setImageBitmap(result);
				imgv.postInvalidate();
			}
			super.onPostExecute(result);
		}

	}

	private void showExitGroupDiag(final Group group) {

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(android.content.Context.LAYOUT_INFLATER_SERVICE);
		final View layout = inflater.inflate(R.layout.dialog_twice_true, null);
		final AlertDialog dialog = new AlertDialog.Builder(context).create();
		dialog.show();
		dialog.getWindow().setContentView(layout);
		TextView conTV = (TextView) dialog
				.findViewById(R.id.dialog_twice_content);
		// conTV.setText(R.string.twice_sure);
		conTV.setText("您确认要退出" + group.getgName() + "吗?");
		dialog.findViewById(R.id.dialog_twice_ok).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {

						try {
							Community comm = Community.getInstance(context);
							if (comm != null) {
								boolean b = comm.exitGroup(group.getgNum());
								if (b) {
									groupList.remove(group);
									notifyDataSetChanged();
								}
							}
						} catch (JSONException e) {
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

}
