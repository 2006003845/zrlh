package com.example.llkc_sdk_sampleflash;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.llkc_sdk_flash.api.IPort;
import com.example.llkc_sdk_flash.api.PortImpl;
import com.example.llkc_sdk_flash.beans.RankItem;
import com.example.llkc_sdk_flash.beans.UserInfo;
import com.example.llkc_sdk_flash.net.NoPlatformUserExpection;
import com.example.llkc_sdk_flash.task.RankListCallBack;
import com.example.llkc_sdk_flash.task.Result;
import com.example.llkc_sdk_flash.task.ResultCallBack;
import com.example.llkc_sdk_flash.ui.ItemViewInterface;
import com.example.llkc_sdk_flash.ui.RankDialog;
import com.zzl.zl_app.cache.ImageCache;
import com.zzl.zl_app.utils.Tools;

public class MainActivity extends Activity {

	IPort iport;
	TextView tv_user, tv_ranks, tv_score, tv_integral;
	UserInfo user = null;
	ImageCache imgCache;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		iport = PortImpl.getPort();
		imgCache = ImageCache.getInstance();
		imgCache.setHandler(new Handler());
		setContentView(R.layout.activity_main);
		tv_user = (TextView) this.findViewById(R.id.tv_user);
		tv_ranks = (TextView) this.findViewById(R.id.tv_rank);
		tv_score = (TextView) this.findViewById(R.id.tv_commitscore);
		tv_integral = (TextView) this.findViewById(R.id.tv_commitintegral);

		this.findViewById(R.id.btn_user).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						user = iport.getUserInfo(MainActivity.this);
						tv_user.setText("account: " + user.getAccount()
								+ "------------pwd:" + user.getPwd());
					}
				});
		this.findViewById(R.id.btn_rank).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						if (user == null)
							return;
						try {
							iport.getGameRankList(MainActivity.this,
									user.getAccount(), user.getPwd(), "1",
									new RankListCallBack() {

										@Override
										public void onResult(Result result,
												List<RankItem> list) {
											if (list != null) {
												Tools.log("LLKC_SDK", list
														.toArray().toString());

												String str = "";
												for (RankItem rankItem : list) {
													str += rankItem.toString();
												}
												tv_ranks.setText(str);
												RankDialog d = new MyRankDialog(
														MainActivity.this);
												// d.showRankDialog(list, "米");
												// d.showRankDialog(list, "分",
												// "好友排名",
												// R.drawable.ic_launcher,
												// 0, 0, 0);
												d.showRankDialog(list, "秒",
														"好友排名",
														R.drawable.ic_launcher,
														viewInter);
											} else {
												tv_ranks.setText("\n result------stat:"
														+ result.stat
														+ "\n msg:"
														+ result.msg);
											}
										}
									});
						} catch (NoPlatformUserExpection e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				});
		this.findViewById(R.id.btn_commitscore).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						if (user == null)
							return;
						try {

							iport.postGameScoreData(MainActivity.this,
									user.getAccount(), user.getPwd(), "1", 100,
									new ResultCallBack() {

										@Override
										public void onResult(Result result) {
											tv_score.setText("\n result------stat:"
													+ result.stat
													+ "\n msg:"
													+ result.msg);
										}
									});

						} catch (NoPlatformUserExpection e) {
							Tools.log("LLKC_SDK", "no user");
						}
					}
				});
		this.findViewById(R.id.btn_commitintegral).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						if (user == null)
							return;
						try {

							iport.postUsedIntegralData(MainActivity.this,
									user.getAccount(), user.getPwd(), "1", 50,
									new ResultCallBack() {

										@Override
										public void onResult(Result result) {
											tv_integral
													.setText("\n result------stat:"
															+ result.stat
															+ "\n msg:"
															+ result.msg);
										}
									});
						} catch (NoPlatformUserExpection e) {
							Tools.log("LLKC_SDK", "no user");
						}
					}
				});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	ItemViewInterface viewInter = new ItemViewInterface() {

		@Override
		public View getItemView(LayoutInflater inflater, int position,
				View convertView, ViewGroup parent, List<RankItem> itemList,
				String util) {
			ViewHolder holder = null;
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.dialog_rank_item, null);
				holder = new ViewHolder();
				holder.num = (TextView) convertView
						.findViewById(R.id.item_rank_num);
				holder.head = (ImageView) convertView
						.findViewById(R.id.item_rank_head);
				holder.name = (TextView) convertView
						.findViewById(R.id.item_rank_name);
				holder.score = (TextView) convertView
						.findViewById(R.id.item_rank_score);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			RankItem item = itemList.get(position);
			holder.num.setText(item.getRanking() + "");
			holder.name.setText(item.getUname());
			holder.head.setTag(item.getHead());
			// item.setHead("http://d1.51gugu.com/12414012/4c75c497bf285e2333839d23156e14f9/835183.jpg");
			imgCache.loadImg(item.getHead(), item.getHead(), parent,
					R.drawable.ic_launcher);
			holder.score.setText(item.getScore() + util);
			return convertView;
		}

		class ViewHolder {
			TextView num;
			ImageView head;
			TextView name;
			TextView score;
		}
	};

}
