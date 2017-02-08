package com.zrong.Android.activity;

import com.zrong.Android.game.Connection;
import com.zrong.Android.game.ConstructData;
import com.zrong.Android.online.network.GameProtocol;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class TaskAnswerResultActivity extends GameActivity {
	public static TaskAnswerResultActivity mContext = null;
	private TextView answernum;
	private TextView questiondesc;
	private TextView correctanswer;
	private Button next,title;
	private int taskId;
	private byte pass, hasNext;
	private String titleText,question,answer;
	private String[] options;
	
	 
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		mContext = this;
		this.setContentView(R.layout.answerresult);
		
		initView();
	}
	private void initView() {
		Button returnback = (Button) this
				.findViewById(R.id.answerresult_button_returnback);
		Button cancel = (Button) this
				.findViewById(R.id.answerresult_button_cancel);
		returnback.setOnClickListener(new OnClickListener() {

			 
			public void onClick(View v) {
				if(mContext == null){
					mContext =TaskAnswerResultActivity.this;
					mContext.finish();
				}
				mContext.finish();
			}
		});
		cancel.setOnClickListener(new OnClickListener() {

			 
			public void onClick(View v) {
				if(mContext == null){
					mContext =TaskAnswerResultActivity.this;
					mContext.finish();
				}
				mContext.finish();
			}
		});
		next = (Button) this.findViewById(R.id.answerresult_next);
		title = (Button)this.findViewById(R.id.answerresult_button_title);
		next.setOnClickListener(new Button.OnClickListener() {

			 
			public void onClick(View v) {
				Log.i("Log", "next...");
				// 发送显示下一题的包
				if(hasNext ==0){
					TaskAnswerResultActivity.this.finish();
					mContext = null;
				}else{
					Log.i("juj", "______zhixing");
				Connection.sendMessage(
						GameProtocol.CONNECTION_SEND_NEXTQUESTION,
						ConstructData.getShowNextQuesOpreqData(taskId));
				       TaskAnswerResultActivity.this.finish();
				       mContext = null;
				        }
					
						
					

			}
		});
		answernum = (TextView) this
		        .findViewById(R.id.answerresult_num_tv);
		questiondesc = (TextView) this
				.findViewById(R.id.answerresult_desc_tv);
		correctanswer = (TextView)this
		      .findViewById(R.id.answerresult_correct_tv);
	
		Bundle bundle = mContext.getIntent().getExtras();
		updateView(bundle);
	}
	
	/**
	 * 更新界面
	 * @param bundle
	 */
	public  void updateView(Bundle bundle){

		taskId = bundle.getInt("taskId");// 任务Id
		titleText = bundle.getString("title");// 标题
		pass = bundle.getByte("pass");// 已答题数
		question = bundle.getString("question");// 问题
		answer = bundle.getString("answer");
		hasNext = bundle.getByte("hasNext");
		
		title.setText(titleText);
		answernum.setText("已经回答" + pass + "道题");
		questiondesc.setText(question);
		correctanswer.setText(answer);
		
	/*	if (size == 2 && options != null) {
			answerItem1TV.setText(options[0]);
			answerItem2TV.setText(options[1]);
		}*/
		
		
	}
	
	
	
	 
	public GameActivity getGameContext() {
		// TODO Auto-generated method stub
		return this;
	}
	
	 
	public void finish() 
	{
		mContext = null;
		super.finish();
	}


}
