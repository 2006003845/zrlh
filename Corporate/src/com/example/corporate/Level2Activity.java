package com.example.corporate;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.corporate.Level1Activity.Objdapter;
import com.example.corporate.Level1Activity.Objdapter.ViewHolder;
import com.example.corporate.base.BaseActivity;

public class Level2Activity extends BaseActivity {
	public static final String Tag = "level2";

	public static void launch(Context c, Intent intent) {
		if (intent == null) {
			intent = new Intent();
		}
		intent.setClass(c, Level2Activity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		c.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.level1);
		initView();
		Bundle b = getIntent().getExtras();
		if (b != null) {
			@SuppressWarnings("unchecked")
			ArrayList<Obj> list = (ArrayList<Obj>) b.getSerializable("list");
			if (list != null && list.size() > 0) {
				objList.clear();
				objList.addAll(list);
				if (adapter != null)
					adapter.notifyDataSetChanged();
			}
		}
	}

	ListView objListV;
	ArrayList<Obj> objList = new ArrayList<Obj>();

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);

	}

	Objdapter adapter;

	private void initView() {
		objListV = (ListView) this.findViewById(R.id.level1_listv);
		adapter = new Objdapter();
		objListV.setAdapter(adapter);
		objListV.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Obj obj = objList.get(arg2);
				Intent intent = getIntent();
				Bundle b = new Bundle();
				b.putSerializable("obj", obj);
				intent.putExtras(b);
				getContext().setResult(RESULT_OK, intent);
				getContext().finish();
				// finishActivity(100);
			}
		});
	}

	@Override
	public BaseActivity getContext() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public void setDialogContent(AlertDialog dialog, int layoutId, String msg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setDialogTitle(AlertDialog dialog, int layoutId, String title) {
		// TODO Auto-generated method stub

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

	class Objdapter extends BaseAdapter {
		private LayoutInflater inflater;

		public Objdapter() {
			inflater = (LayoutInflater) getContext().getSystemService(
					android.content.Context.LAYOUT_INFLATER_SERVICE);
		}

		@Override
		public int getCount() {
			return objList.size();
		}

		@Override
		public Object getItem(int position) {
			return objList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.item_tv, null);
				holder = new ViewHolder();

				holder.name = (TextView) convertView.findViewById(R.id.item_tv);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			final Obj obj = objList.get(position);
			if (obj != null) {
				holder.name.setText(obj.name);
			}
			return convertView;
		}

		class ViewHolder {
			TextView name;

		}
	}

}
