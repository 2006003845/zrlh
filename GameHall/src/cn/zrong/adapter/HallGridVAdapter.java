package cn.zrong.adapter;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.zrong.activity.R;
import cn.zrong.apk.GameApk;
import cn.zrong.entity.GameItem;
import cn.zrong.loader.AsyncImageLoader;
import cn.zrong.loader.ImageCache;
import cn.zrong.utils.Tools;
import cn.zrong.widget.GameItemOnClickListener;

public class HallGridVAdapter extends BaseAdapter {
	private List<GameItem> gameList;
	private Context mContext;
	private LayoutInflater inflater;

	public HallGridVAdapter(List<GameItem> gameList, Context mContext) {
		super();
		this.gameList = gameList;
		this.mContext = mContext;
		inflater = (LayoutInflater) mContext
				.getSystemService(android.content.Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return gameList.size();
	}

	@Override
	public Object getItem(int position) {
		return gameList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if (view == null) {
			view = inflater.inflate(R.layout.home_hall_gridv_item, null);
		}
		ImageView gameIcon = (ImageView) view
				.findViewById(R.id.hall_gridv_imgv);
		ProgressBar iconPB = (ProgressBar) view
				.findViewById(R.id.hall_gridv_imgv_pb);
		iconPB.setVisibility(View.VISIBLE);
		TextView nameTV = (TextView) view.findViewById(R.id.hall_gridv_name);
		final RelativeLayout layout = (RelativeLayout) view
				.findViewById(R.id.hall_gridv_layout);
		ProgressBar pb = (ProgressBar) view.findViewById(R.id.hall_gridv_pb);
		TextView progress = (TextView) view
				.findViewById(R.id.hall_gridv_progress);
		if (position < getCount() - 1) {
			GameItem item = gameList.get(position);
			// if (item.gameDrawable != null) {
			// gameIcon.setImageDrawable(item.gameDrawable);
			// iconPB.setVisibility(View.GONE);
			// } else if (item.gameImgUrl != null &&
			// !item.gameImgUrl.equals("")) {
			// AsyncImageLoader.getInstance().loadGamePre(item.gameImgUrl,
			// item.gameImgUrl, gameIcon, item);
			// }

			if (item.gameIconBm != null) {
				gameIcon.setImageBitmap(item.gameIconBm);
				iconPB.setVisibility(View.GONE);
			} else if (item.gameImgUrl != null && !item.gameImgUrl.equals("")) {
				new LoadImgTask(item.gameImgUrl, gameIcon, item, iconPB)
						.execute();
			}
			nameTV.setText(item.gameName);
			if (item.isLoading) {
				layout.setVisibility(View.VISIBLE);
				progress.setText(item.progress * 100 / item.fileSize + "%");
			} else {
				layout.setVisibility(View.INVISIBLE);
			}

			gameIcon.setOnClickListener(new GameItemOnClickListener(item) {
				@Override
				public void onGameItemClick(GameItem gameItem) {
					Toast.makeText(mContext, gameItem.gameName,
							Toast.LENGTH_SHORT).show();
					GameApk apk = new GameApk(gameItem.gameDown,
							gameItem.gamePack, gameItem);
					int action = apk.loadOrLauchApk(mContext, handler);
					if (action == GameApk.DOWNLOADGAME
							|| action == GameApk.UPDATEGAME) {
						layout.setVisibility(View.VISIBLE);
						gameItem.isLoading = true;
					}
				}
			});
		} else if (position == getCount() - 1) {
			iconPB.setVisibility(View.GONE);
			gameIcon.setImageResource(R.drawable.game_more);
			gameIcon.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Tools.showToast(mContext, "敬请期待");
				}
			});
			nameTV.setText("敬请期待");
		}

		return view;
	}

	class LoadImgTask extends AsyncTask<Void, Integer, Bitmap> {
		ImageView imgv;
		String url;
		GameItem item;
		ProgressBar pb;

		public LoadImgTask(String url, ImageView imgv, GameItem item,
				ProgressBar pb) {
			this.imgv = imgv;
			this.url = url;
			this.item = item;
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
				item.gameIconBm = result;
				imgv.setImageBitmap(result);
				imgv.postInvalidate();
			}
			super.onPostExecute(result);
		}

	}

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			int what = msg.what;
			switch (what) {
			case 0:
				HallGridVAdapter.this.notifyDataSetChanged();
				break;
			case 1:
				HallGridVAdapter.this.notifyDataSetChanged();
				Toast.makeText(mContext, "完成", Toast.LENGTH_SHORT).show();
				break;
			}
		};
	};

}
