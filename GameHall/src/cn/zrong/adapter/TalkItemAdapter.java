package cn.zrong.adapter;

import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import cn.zrong.activity.HomeActivity;
import cn.zrong.activity.R;
import cn.zrong.entity.Status;
import cn.zrong.loader.AsyncImageLoader;
import cn.zrong.utils.TextUtil;
import cn.zrong.utils.TimeUtil;
import cn.zrong.widget.ImageZoomView;
import cn.zrong.widget.SimpleZoomListener;
import cn.zrong.widget.ZoomState;

public class TalkItemAdapter extends BaseAdapter {
	private Context mContext;
	private List<Status> mStatusList;

	private AsyncImageLoader asyncImageLoader = AsyncImageLoader.getInstance();

	public TalkItemAdapter(Context context, List<Status> status) {
		mContext = context;
		mStatusList = status;

	}

	@Override
	public int getCount() {
		return mStatusList.size();
	}

	@Override
	public Object getItem(int position) {
		return mStatusList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		WeiboItem item = null;
		if (convertView == null) {
			item = new WeiboItem();
			convertView = View.inflate(mContext, R.layout.group_talklistv_item,
					null);
			//
			item.icon = (ImageView) convertView
					.findViewById(R.id.weibo_item_icon);
			item.name = (TextView) convertView
					.findViewById(R.id.group_talklistv_item_name);
			item.pic = (ImageView) convertView
					.findViewById(R.id.group_talklistv_item_pic);
			item.createTime = (TextView) convertView
					.findViewById(R.id.group_talklistv_item_createTime);
			item.content = (TextView) convertView
					.findViewById(R.id.group_talklistv_item_content);
			item.content_pic = (ImageView) convertView
					.findViewById(R.id.group_talklistv_item_content_pic);
			convertView.setTag(item);
		}
		item = (WeiboItem) convertView.getTag();
		final Status status = mStatusList.get(position);
		// asyncImageLoader.loadPortrait((status.getUser().info.,
		// status.getPicImgUrl(), item.icon);
		String headUrl = status.getWbHead();
		if (headUrl != null && !headUrl.equals("")) {
			asyncImageLoader.loadPortrait(status.getWbID(), headUrl, item.icon);
		}
		final String picUrl = status.getPicImgUrl();
		if (picUrl != null && !picUrl.equals("")) {
			item.pic.setVisibility(View.VISIBLE);
			item.content_pic.setVisibility(View.VISIBLE);
			asyncImageLoader.loadPre(status.getIndex(), picUrl,
					item.content_pic);
		} else {
			item.pic.setVisibility(View.GONE);
			item.content_pic.setVisibility(View.GONE);
		}
		item.content_pic.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showPicDialog(picUrl);
			}
		});

		item.name.setText(status.getWbName());
		item.createTime.setText(TimeUtil.getTimeStr(status.getCreatedAt()));
		item.content.setText(TextUtil.formatContent(status.getContent(),
				mContext));
		return convertView;
	}

	private void showPicDialog(String imgUrl) {
		LayoutInflater inflater = (LayoutInflater) mContext
				.getSystemService(android.content.Context.LAYOUT_INFLATER_SERVICE);
		final View layout = inflater.inflate(R.layout.dialog_pic_preview, null);
		final AlertDialog dialog = new AlertDialog.Builder(mContext).create();
		dialog.show();
		dialog.getWindow().setContentView(layout);
		dialog.getWindow().setLayout(
				HomeActivity.mInstance.getWindowManager().getDefaultDisplay()
						.getWidth(),
				HomeActivity.mInstance.getWindowManager().getDefaultDisplay()
						.getHeight());
		dialog.findViewById(R.id.dialog_pic_prev_return).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO
						dialog.dismiss();
					}
				});
		// 删除
		dialog.findViewById(R.id.dialog_pic_prev_save).setVisibility(View.GONE);
		ImageZoomView previewView = (ImageZoomView) dialog
				.findViewById(R.id.pic_preview);
		// previewView.setImage(contentPicBm);
		AsyncImageLoader.getInstance().loadPre(imgUrl, imgUrl, previewView);
		ZoomState mZoomState = new ZoomState();
		previewView.setZoomState(mZoomState);
		SimpleZoomListener mZoomListener = new SimpleZoomListener();
		mZoomListener.setZoomState(mZoomState);
		previewView.setOnTouchListener(mZoomListener);
		mZoomState.setPanX(0.5f);
		mZoomState.setPanY(0.5f);
		mZoomState.setZoom(1f);
		mZoomState.notifyObservers();
	}

}
