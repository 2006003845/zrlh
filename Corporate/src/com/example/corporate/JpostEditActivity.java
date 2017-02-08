package com.example.corporate;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONException;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.corporate.base.BaseActivity;
import com.example.corporate.base.MyToast;
import com.zzl.zl_app.connection.Community;
import com.zzl.zl_app.net_port.Get2ApiImpl;
import com.zzl.zl_app.net_port.WSError;

public class JpostEditActivity extends BaseActivity {

	public static final String Tag = "jpost_edit";

	public static void launch(Context c, Intent intent) {
		if (intent == null) {
			intent = new Intent();
		}
		intent.setClass(c, JpostEditActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		c.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addActMap(Tag, getContext());
		setContentView(R.layout.jpost_edit);
		new initDataTask().execute();
		initView();
	}

	Jobs.JobDetail jPost;
	private int state = State_Publish;
	private static final int State_Publish = 1;
	private static final int State_Edit = 2;

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		Bundle b = intent.getExtras();
		if (b != null) {
			jPost = (Jobs.JobDetail) b.getSerializable("jpost");
			// init data
			state = State_Edit;
			updateView();

		}
	}

	public void updateView() {
		if (state == State_Edit) {
			nameET.setText(jPost.name);
			numET.setText(jPost.peonumber);
			addrET.setText(jPost.address);
			desribET.setText(jPost.description);
			demandET.setText(jPost.demand);
			linkmanET.setText(jPost.contacts_name);
			phoneET.setText(jPost.tel);
			salaryTV.setText(jPost.salary_min + "-" + jPost.salary_max);
			typeTV.setText(jPost.classify);
			deadlineTV.setText(jPost.deadline);
			tagTV.setText(jPost.tags);
			areaTV.setText(jPost.area);
		}
	}

	private TextView titleTV;

	private EditText nameET, numET, addrET, desribET, demandET, linkmanET,
			phoneET;
	private TextView salaryTV, typeTV, deadlineTV, tagTV, areaTV;
	private TextView leftTV;

	private ImageView licenseImgV;

	private ArrayList<Obj> salaryList = new ArrayList<Obj>();
	private ArrayList<Obj> tagList = new ArrayList<Obj>();
	private ArrayList<Obj> deadlineList = new ArrayList<Obj>();

	ArrayList<HashMap<Obj, ArrayList<Obj>>> typeLists = new ArrayList<HashMap<Obj, ArrayList<Obj>>>();

	private void initData() {
		salaryList.clear();
		salaryList.add(new Salary("1", "0~2000"));
		salaryList.add(new Salary("2", "2000~4000"));
		salaryList.add(new Salary("3", "4000~6000"));
		salaryList.add(new Salary("4", "6000以上"));

		tagList.clear();
		tagList.add(new Tag("1", "五险一金"));
		tagList.add(new Tag("2", "提供食宿"));
		tagList.add(new Tag("3", "年底双薪"));
		tagList.add(new Tag("4", "交通补助"));
		tagList.add(new Tag("5", "通讯补助"));
		tagList.add(new Tag("6", "周末双休"));
		tagList.add(new Tag("7", "应届生"));

		Get2ApiImpl get = new Get2ApiImpl(getContext());
		try {
			ArrayList<HashMap<Obj, ArrayList<Obj>>> lists = get.getTypeMapList(
					Get2ApiImpl.From_Assert, "classify.json");
			if (lists != null && lists.size() > 0) {
				typeLists.clear();
				typeLists.addAll(lists);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WSError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		deadlineList.clear();
		Deadline d = new Deadline("1", "半个月");
		d.months = 0.5 + "";
		deadlineList.add(d);
		Deadline d1 = new Deadline("1", "一个月");
		d.months = 1 + "";
		deadlineList.add(d1);
		Deadline d2 = new Deadline("2", "二个月");
		d.months = 2 + "";
		deadlineList.add(d2);
		Deadline d3 = new Deadline("3", "三个月");
		d.months = 3 + "";
		deadlineList.add(d3);
	}

	private String tag = null;
	private Deadline dl;

	private void initView() {
		this.findViewById(R.id.back).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				closeOneAct(Tag);
			}
		});
		titleTV = (TextView) this.findViewById(R.id.title_tv);
		titleTV.setText(R.string.fillin_auth_info);
		this.findViewById(R.id.btn).setVisibility(View.GONE);
		nameET = (EditText) this.findViewById(R.id.jpost_et_name);
		numET = (EditText) this.findViewById(R.id.jpost_et_num);
		addrET = (EditText) this.findViewById(R.id.jpost_et_addr);
		desribET = (EditText) this.findViewById(R.id.jpost_et_describ);
		demandET = (EditText) this.findViewById(R.id.jpost_et_demand);
		linkmanET = (EditText) this.findViewById(R.id.jpost_et_linkman);
		phoneET = (EditText) this.findViewById(R.id.jpost_et_phone);
		salaryTV = (TextView) this.findViewById(R.id.jpost_tv_salary);
		typeTV = (TextView) this.findViewById(R.id.jpost_tv_classify);
		deadlineTV = (TextView) this.findViewById(R.id.jpost_tv_days);
		tagTV = (TextView) this.findViewById(R.id.jpost_tv_tag);
		areaTV = (TextView) this.findViewById(R.id.jpost_tv_area);
		salaryTV.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				PullDownList pullDownList = new PullDownList(getContext(),
						getContext().findViewById(R.id.jpost_layout_salary),
						salaryTV, salaryList);
				pullDownList
						.setPullOnItemClickListener(new PullOnItemClickListener() {

							@Override
							public void onPullItemClick(int position) {
								Salary c = (Salary) salaryList.get(position);
								salaryTV.setText(c.name);
							}
						});
				pullDownList.popupWindwShowing();
			}
		});
		typeTV.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getContext(), Level1Activity.class);
				Bundle b = new Bundle();
				b.putSerializable("list", typeLists);
				intent.putExtras(b);
				startActivityForResult(intent, 100);
			}
		});
		areaTV.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getContext(),
						CityLevel1Activity.class);
				startActivityForResult(intent, 101);
			}
		});
		deadlineTV.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				PullDownList pullDownList2 = new PullDownList(getContext(),
						getContext().findViewById(R.id.jpost_layout_days),
						deadlineTV, deadlineList);
				pullDownList2
						.setPullOnItemClickListener(new PullOnItemClickListener() {

							@Override
							public void onPullItemClick(int position) {
								Deadline c = (Deadline) deadlineList
										.get(position);
								dl = c;
								deadlineTV.setText(c.name);
							}
						});
				pullDownList2.popupWindwShowing();
			}
		});
		tagTV.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				PullDownList pullDownList3 = new PullDownList(getContext(),
						getContext().findViewById(R.id.jpost_layout_tag),
						tagTV, tagList);
				pullDownList3
						.setPullOnItemClickListener(new PullOnItemClickListener() {

							@Override
							public void onPullItemClick(int position) {
								Tag c = (Tag) tagList.get(position);
								String t = tagTV.getText().toString();
								if (tag == null) {
									tagTV.setText(c.name);
									tag = tagTV.getText().toString();
								} else
									tagTV.setText(t + "," + c.name);
							}
						});
				pullDownList3.popupWindwShowing();
			}
		});
		this.findViewById(R.id.jpost_btn_commit).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO
						// TODO
						String name = nameET.getText().toString();
						String peoNum = numET.getText().toString();
						String addr = addrET.getText().toString();
						String description = desribET.getText().toString();
						String demand = demandET.getText().toString();
						String linkman = linkmanET.getText().toString();
						String phone = phoneET.getText().toString();
						String salary = salaryTV.getText().toString();
						// String type = typeTV.getText().toString();
						String deadline = deadlineTV.getText().toString();
						String tag = tagTV.getText().toString();
						String area = areaTV.getText().toString();

						if (name == null || name.equals("")) {
							MyToast.getToast().showToast(getContext(),
									"岗位名称不能为空");
							return;
						}
						if (peoNum == null || peoNum.equals("")) {
							MyToast.getToast().showToast(getContext(),
									"招聘人数不能为空");
							return;
						}
						if (addr == null || addr.equals("")) {
							MyToast.getToast().showToast(getContext(),
									"工作地址不能为空");
							return;
						}
						if (description == null || description.equals("")) {
							MyToast.getToast().showToast(getContext(),
									"岗位介绍不能为空");
							return;
						}
						if (linkman == null || linkman.equals("")) {
							MyToast.getToast().showToast(getContext(),
									"联系人不能为空");
						}
						if (demand == null || demand.equals("")) {
							MyToast.getToast().showToast(getContext(),
									"岗位要求不能为空");
							return;
						}
						if (phone == null || phone.equals("")) {
							MyToast.getToast().showToast(getContext(),
									"联系电话不能为空");
							return;
						}
						if (salary == null || salary.equals("")) {
							MyToast.getToast().showToast(getContext(),
									"薪水范围不能为空");
							return;
						}
						if (type == null) {
							MyToast.getToast().showToast(getContext(),
									"岗位类型不能为空");
							return;
						}
						if (dl == null) {
							MyToast.getToast().showToast(getContext(),
									"有效期不能为空");
							return;
						}
						if (area == null || area.equals("")) {
							MyToast.getToast().showToast(getContext(),
									"工作所在城市不能为空");
							return;
						}
						new CommitJPostTask(name, peoNum, addr, description,
								demand, linkman, phone, salary, key.id + "-"
										+ type.id, dl.months, tag, area)
								.execute();
					}
				});

	}

	Type key;
	Type type;

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 100) {
			if (data != null) {
				Bundle b = data.getExtras();
				if (b != null) {
					key = (Type) (Obj) b.getSerializable("key");
					type = (Type) (Obj) b.getSerializable("obj");
					if (type != null)
						typeTV.setText(key.name + "-" + type.name);
				}
			}
		} else if (requestCode == 101) {
			if (data != null) {
				Bundle b = data.getExtras();
				if (b != null) {
					Obj key = (Obj) b.getSerializable("key");
					Obj city = (Obj) b.getSerializable("obj");
					if (city != null)
						areaTV.setText(key.name + "-" + city.name);
				}
			}
		}
	}

	class CommitJPostTask extends AsyncTask<Object, Integer, Boolean> {

		String name;
		String peoNum;
		String addr;
		String description;
		String demand;
		String linkman;
		String phone;
		String salary;
		String type;
		String deadline;
		String tag;
		String area;

		public CommitJPostTask(String name, String peoNum, String addr,
				String description, String demand, String linkman,
				String phone, String salary, String type, String deadline,
				String tag, String area) {
			super();
			this.name = name;
			this.peoNum = peoNum;
			this.addr = addr;
			this.description = description;
			this.demand = demand;
			this.linkman = linkman;
			this.phone = phone;
			this.salary = salary;
			this.type = type;
			this.deadline = deadline;
			this.tag = tag;
			this.area = area;
		}

		ProgressDialog dialog = null;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = new ProgressDialog(getContext());
			dialog.setCancelable(false);
			dialog.setMessage("提交中,请稍后");
			dialog.show();
		}

		@Override
		protected Boolean doInBackground(Object... params) {
			try {
				return Community.getInstance(getContext()).recruitOp(
						PlatformAPI.Name, PlatformAPI.Pwd,
						state == State_Edit ? "2" : "1", area, "", name,
						peoNum, "", "", linkman, phone, type, description,
						demand, deadline, tag, addr);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return false;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			setProgressBarIndeterminateVisibility(false);
			dialog.dismiss();
			if (result) {
				JpostManageActivity.launch(getContext(), getIntent());
			} else {
				MyToast.getToast().showToast(getContext(), "提交失败,请重试");
			}
			super.onPostExecute(result);
		}
	}

	@Override
	public BaseActivity getContext() {
		return this;
	}

	@Override
	public void setDialogContent(AlertDialog dialog, int layoutId, String msg) {
	}

	@Override
	public void setDialogTitle(AlertDialog dialog, int layoutId, String title) {
	}

	@Override
	public OnClickListener setPositiveClickListener(AlertDialog dialog,
			int layoutId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OnClickListener setNegativeClickListener(AlertDialog dialog,
			int layoutId) {
		// TODO Auto-generated method stub
		return null;
	}

	class initDataTask extends AsyncTask<Object, Integer, Boolean> {

		public initDataTask() {
			super();

		}

		ProgressDialog dialog = null;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = new ProgressDialog(getContext());
			dialog.setCancelable(false);
			dialog.setMessage("数据初始化");
			dialog.show();
		}

		@Override
		protected Boolean doInBackground(Object... params) {
			initData();
			return false;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			setProgressBarIndeterminateVisibility(false);
			dialog.dismiss();
			super.onPostExecute(result);
		}
	}

}
