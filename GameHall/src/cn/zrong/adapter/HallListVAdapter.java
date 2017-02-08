package cn.zrong.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import cn.zrong.activity.R;
import cn.zrong.apk.GameApk;
import cn.zrong.entity.GameItem;
import cn.zrong.loader.AsyncImageLoader;
import cn.zrong.widget.GameItemOnClickListener;
import cn.zrong.widget.GameItemOnLongClickListener;

public class HallListVAdapter extends BaseAdapter {

	private List<GameItem> gameList;
	private Context mContext;
	private LayoutInflater inflater;
	private List<List<GameItem>> data = new ArrayList<List<GameItem>>();;

	public HallListVAdapter(List<GameItem> gameList, Context mContext) {
		super();
		this.gameList = gameList;
		this.mContext = mContext;
		inflater = (LayoutInflater) mContext
				.getSystemService(android.content.Context.LAYOUT_INFLATER_SERVICE);
		initData();
	}

	private void initData() {
		data.clear();
		List<GameItem> list = null;
		int size = gameList.size();

		for (int i = 0; i < size; i++) {
			if (i == 0) {
				list = new ArrayList<GameItem>();
			}
			if (i % 2 == 0 && i != 0) {
				data.add(list);
				list = new ArrayList<GameItem>();
			}
			list.add(gameList.get(i));
			if (i == gameList.size() - 1) {
				data.add(list);
			}
		}
	}

	public void setData(List<GameItem> gameList) {
		this.gameList = gameList;
		notifyDataSetChanged();
	}

	@Override
	public void notifyDataSetChanged() {
		initData();
		super.notifyDataSetChanged();

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View layout = inflater.inflate(R.layout.home_hall_listv_item, null);

		ImageView gameIcon01, gameIcon02;
		gameIcon01 = (ImageView) layout.findViewById(R.id.hall_listv_item01);
		gameIcon02 = (ImageView) layout.findViewById(R.id.hall_listv_item02);

		final LinearLayout layout1 = (LinearLayout) layout
				.findViewById(R.id.hall_listv_1_layout);
		ProgressBar pb1 = (ProgressBar) layout
				.findViewById(R.id.hall_listv_1_pb);
		TextView tv1 = (TextView) layout.findViewById(R.id.hall_listv_1_tv);

		final LinearLayout layout2 = (LinearLayout) layout
				.findViewById(R.id.hall_listv_2_layout);
		ProgressBar pb2 = (ProgressBar) layout
				.findViewById(R.id.hall_listv_2_pb);
		TextView tv2 = (TextView) layout.findViewById(R.id.hall_listv_2_tv);

		List<GameItem> list = null;
		if (position < data.size()) {
			list = data.get(position);
		}

		GameItem item1 = null;
		GameItem item2 = null;

		if (list == null || list.size() == 0 || list.get(0) == null) {
			gameIcon01.setVisibility(View.INVISIBLE);
		} else {
			item1 = data.get(position).get(0);
			if (item1.gameDrawable != null) {
				gameIcon01.setImageDrawable(item1.gameDrawable);
			} else if (item1.gameImgUrl != null && !item1.gameImgUrl.equals("")) {
				AsyncImageLoader.getInstance().loadGamePre(item1.gameImgUrl,
						item1.gameImgUrl, gameIcon01, item1);
				// ImageCache.getInstance().getImg(mContext, item1.gameImgUrl,
				// gameIcon01);
			}
			Log.i("Load", "progress:" + item1.progress);
			Log.i("Load", "fileSize:" + item1.fileSize);

			tv1.setText(item1.progress * 100 / item1.fileSize + "%");
		}
		if (list == null || list.size() < 2 || list.get(1) == null) {
			gameIcon02.setVisibility(View.INVISIBLE);
		} else {
			item2 = data.get(position).get(1);
			if (item2.gameDrawable != null) {
				gameIcon02.setImageDrawable(item2.gameDrawable);
			} else if (item2.gameImgUrl != null && !item2.gameImgUrl.equals("")) {
				AsyncImageLoader.getInstance().loadGamePre(item2.gameImgUrl,
						item2.gameImgUrl, gameIcon02, item2);
				// ImageCache.getInstance().getImg(mContext, item2.gameImgUrl,
				// gameIcon02);
			}
			Log.i("Load", "progress:" + item2.progress);
			Log.i("Load", "fileSize:" + item2.fileSize);
			tv2.setText(item2.progress * 100 / item2.fileSize + "%");
		}

		if (item1.isLoading) {
			layout1.setVisibility(View.VISIBLE);
		} else {
			layout1.setVisibility(View.INVISIBLE);
		}

		if (item2.isLoading) {
			layout2.setVisibility(View.VISIBLE);
		} else {
			layout2.setVisibility(View.INVISIBLE);
		}

		gameIcon01.setOnClickListener(new GameItemOnClickListener(item1) {

			@Override
			public void onGameItemClick(GameItem gameItem) {
				Toast.makeText(mContext, gameItem.gameName, Toast.LENGTH_SHORT)
						.show();
				GameApk apk = new GameApk(gameItem.gameDown, gameItem.gamePack,
						gameItem);
				int action = apk.loadOrLauchApk(mContext, handler);
				if (action == GameApk.DOWNLOADGAME
						|| action == GameApk.UPDATEGAME) {
					layout1.setVisibility(View.VISIBLE);
					gameItem.isLoading = true;
				}
			}
		});
		gameIcon01
				.setOnLongClickListener(new GameItemOnLongClickListener(item1) {

					@Override
					public void onGameItemLongClick(GameItem gameItem) {

					}
				});
		gameIcon02.setOnClickListener(new GameItemOnClickListener(item2) {

			@Override
			public void onGameItemClick(GameItem gameItem) {
				Toast.makeText(mContext, gameItem.gameName, Toast.LENGTH_SHORT)
						.show();
				GameApk apk = new GameApk(gameItem.gameDown, gameItem.gamePack,
						gameItem);
				int action = apk.loadOrLauchApk(mContext, handler);
				if (action == GameApk.DOWNLOADGAME
						|| action == GameApk.UPDATEGAME) {
					layout2.setVisibility(View.VISIBLE);
					gameItem.isLoading = true;
				}
			}
		});
		gameIcon02
				.setOnLongClickListener(new GameItemOnLongClickListener(item2) {

					@Override
					public void onGameItemLongClick(GameItem gameItem) {

					}
				});
		return layout;
	}

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			int what = msg.what;
			switch (what) {
			case 0:
				HallListVAdapter.this.notifyDataSetChanged();
				break;
			case 1:
				HallListVAdapter.this.notifyDataSetChanged();
				Toast.makeText(mContext, "完成", Toast.LENGTH_SHORT).show();
				break;

			}
		};
	};

}
