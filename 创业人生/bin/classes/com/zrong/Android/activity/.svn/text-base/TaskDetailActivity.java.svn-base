package com.zrong.Android.activity;

import com.zrong.Android.Util.Music;
import com.zrong.Android.Util.SystemAPI;
import com.zrong.Android.View.FreshManLead;
import com.zrong.Android.View.SpriteView;
import com.zrong.Android.game.Connection;
import com.zrong.Android.game.ConstructData;
import com.zrong.Android.game.GameData;
import com.zrong.Android.online.network.GameProtocol;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class TaskDetailActivity extends GameActivity {
	public static TaskDetailActivity mContext = null;

	private int id;
	private byte state;
	private byte group;
	private String title;
	private String target;
	private String guide;
	private String award;

	private TextView titleView;
	private TextView targetView;
	private TextView guideView;
	private TextView awardView;
	private SpriteView taskDetialArrows;

	private Button button;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;

		getWindow().requestFeature(Window.FEATURE_NO_TITLE);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		Bundle bundle = this.getIntent().getExtras();

		id = bundle.getInt("id", 0);
		state = bundle.getByte("state", (byte) 0);
		group = bundle.getByte("group", (byte) 0);
		title = bundle.getString("title");
		target = bundle.getString("target");
		guide = bundle.getString("guide");
		award = bundle.getString("award");

		this.setContentView(R.layout.task_details);

		Button returnback = (Button) this
				.findViewById(R.id.task_details_button_returnback);
		Button cancel = (Button) this
				.findViewById(R.id.task_details_button_cancel);
		returnback.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				TaskDetailActivity.this.finish();
			}
		});

		cancel.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				TaskDetailActivity.this.finish();
			}
		});
		

		titleView = (TextView) this.findViewById(R.id.task_details_title);

		targetView = (TextView) this
				.findViewById(R.id.task_details_target_content);

		guideView = (TextView) this
				.findViewById(R.id.task_details_guideline_content);

		awardView = (TextView) this
				.findViewById(R.id.task_details_reward_content);

		titleView.setText(SystemAPI.getText(title));

		targetView.setText(SystemAPI.getText(target));

		guideView.setText(SystemAPI.getText(guide));

		awardView.setText(SystemAPI.getText(award));

		button = (Button) this.findViewById(R.id.task_details_button);

		if (state == 0)// 任务未接收
		{
			button.setBackgroundResource(R.drawable.button_accept);
		}
		// zhouzhilong amend
		else if (state == 1 || state == 7)// 任务已完成，可领取
		{
			button.setBackgroundResource(R.drawable.button_get);
		}//zhouzhilong add
		else if(state == 6){
			button.setBackgroundResource(R.drawable.answer_question);
		} else {
			button.setBackgroundResource(R.drawable.button_giveup);
		}

		button.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				//zhouzhilong add 新手引导添加
				if (GameData.isFreshMan) {
					if (FreshManLead.caseId == 5 || FreshManLead.caseId == 18) {
						taskDetialArrows.setVisibility(View.GONE);
						FreshManLead.caseId++;
						if(FreshManLead.caseId == 5){
								TaskActivity.mContext.finish();
						}
					}
				}
				if (state == 0) {
					Connection.sendMessage(
							GameProtocol.CONNECTION_SEND_TASK_OPREQ,
							ConstructData.getTaskOpreqData(id, (byte) 1));
					TaskDetailActivity.this.finish();
				}
				// zhouzhilong amend
				else if (state == 1 || state == 7) {
					Connection.sendMessage(
							GameProtocol.CONNECTION_SEND_TASK_OPREQ,
							ConstructData.getTaskOpreqData(id, (byte) 2));
					TaskDetailActivity.this.finish();
				} else if(state == 6){//开始答题
					Log.i("Log", "TaskDetail...开始答题---");
					Connection.sendMessage(
							GameProtocol.CONNECTION_SEND_QUESTIONSTART,
							ConstructData.getShowNextQuesOpreqData(id));
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					TaskDetailActivity.this.finish();
					
				}else {
					Connection.sendMessage(
							GameProtocol.CONNECTION_SEND_TASK_OPREQ,
							ConstructData.getTaskOpreqData(id, (byte) 3));
					TaskDetailActivity.this.finish();
				}
			}

		}

		);
		
		// zhouzhilong add---新手引导部分
		if (GameData.isFreshMan) {
			if (FreshManLead.caseId == 5 || FreshManLead.caseId == 18) {
				Log.i("Log", "FreshManLead--TaskDetail--caseId :"+FreshManLead.caseId);
				taskDetialArrows = (SpriteView) this
						.findViewById(R.id.taskdetial_button_arrows);
				taskDetialArrows.setSeries(0);
				taskDetialArrows.setVisibility(View.VISIBLE);
			}
		}

	}

	

	@Override
	public GameActivity getGameContext() {
		// TODO Auto-generated method stub
		return this;
	}
	
	@Override
	public void finish() 
	{
		mContext = null;
		super.finish();
	}

}
