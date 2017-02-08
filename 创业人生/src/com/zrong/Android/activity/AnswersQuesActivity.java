package com.zrong.Android.activity;

import com.zrong.Android.game.Connection;

import com.zrong.Android.game.ConstructData;
import com.zrong.Android.online.network.GameProtocol;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import android.widget.TextView;
import com.zrong.Android.activity.R;

public class AnswersQuesActivity extends GameActivity {
	public static AnswersQuesActivity mContext;

	private Button title, select;
	private TextView totalTV, questionTV, answerItem1TV, answerItem2TV,answerItem3TV;
	private RadioGroup rg;
	private RadioButton rbt1, rbt2,rbt3;

	private int taskId;
	private byte pass, totalSize, size, right;
	private String titleText, question;
	private String[] options;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		mContext = this;

		setContentView(R.layout.answerquestion);
		initView();

	}

	/**
	 * 初始化界面
	 */
	private void initView() {
		Button returnback = (Button) this
				.findViewById(R.id.answerquestion_button_returnback);
		Button cancel = (Button) this
				.findViewById(R.id.answerquestion_button_cancel);
		returnback.setOnClickListener(new OnClickListener() {
 
			public void onClick(View v) {
				if(mContext==null){
					mContext=AnswersQuesActivity.this;
					mContext.finish();
				}
				mContext.finish();
				mContext = null;
			}
		});
		cancel.setOnClickListener(new OnClickListener() {
 
			public void onClick(View v) {
				if(mContext==null){
					mContext=AnswersQuesActivity.this;
					mContext.finish();
				}
				mContext.finish();
				mContext = null;
			}
		});

		title = (Button) this.findViewById(R.id.answerquestion_button_title);
		select = (Button) this.findViewById(R.id.answerquestion_confirmed);
		select.setOnClickListener(new OnClickListener() {
 
			public void onClick(View v) {
				// 发送回答问题包
				Connection.sendMessage(
						GameProtocol.CONNECTION_SEND_QuestionAnswer,
						ConstructData.getAnswerQuesOpreqData(taskId,
								(byte) (rbt1.isChecked() ? 0 : (rbt2.isChecked() ? 1:2))));
				AnswersQuesActivity.this.finish();
				mContext = null;
				if (pass == totalSize - 1) {
					AnswersQuesActivity.this.finish();
					mContext = null;
				}

			}
		});
		totalTV = (TextView) this.findViewById(R.id.answerquestion_total_tv);
		questionTV = (TextView) this
				.findViewById(R.id.answerquestion_question_tv);
		answerItem1TV = (TextView) this
				.findViewById(R.id.answerquestion_scroll_checkitem1);
		answerItem2TV = (TextView) this
				.findViewById(R.id.answerquestion_scroll_checkitem2);
		answerItem3TV = (TextView) this
		.findViewById(R.id.answerquestion_scroll_checkitem3);
		rg = (RadioGroup) this.findViewById(R.id.answerques_radiogp);
		rbt1 = (RadioButton) this.findViewById(R.id.answerquestion_scroll_rbt1);
		rbt2 = (RadioButton) this.findViewById(R.id.answerquestion_scroll_rbt2);
		rbt3 = (RadioButton) this.findViewById(R.id.answerquestion_scroll_rbt3);
		Bundle bundle = mContext.getIntent().getExtras();
		updateView(bundle);
	}

	/**
	 * 更新界面
	 * 
	 * @param bundle
	 */
	public void updateView(Bundle bundle) {

		taskId = bundle.getInt("taskId");// 任务Id
		titleText = bundle.getString("title");// 标题
		pass = bundle.getByte("pass");// 已答题数
		right = bundle.getByte("right");// 答对题数
		totalSize = bundle.getByte("totalSize");// 总题数
		question = bundle.getString("question");// 问题
		size = bundle.getByte("size");// 选项个数
		options = bundle.getStringArray("options");// 选项内容

		title.setText(titleText);
		totalTV.setText("已经回答" + pass + "道题");
		questionTV.setText(question);
		if (size == 2 && options != null) {
			rbt3.setVisibility(View.GONE);
			answerItem1TV.setText(options[0]);
			answerItem2TV.setText(options[1]);
			Log.i("juj", "题目一："+options[0]+"\n"+"题目二："+options[1]);
		}else if(size == 3 && options != null){
			
			answerItem1TV.setText(options[0]);
			answerItem2TV.setText(options[1]);
			answerItem3TV.setText(options[2]);
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
