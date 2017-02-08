package com.don.fragment;

import com.aphidmobile.flip.FlipViewController;
import com.krislq.sliding.MainActivity;
import com.krislq.sliding.R;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class FlipViewFragment extends Fragment{
	protected FlipViewController flipView;
    private MainActivity mMain=null;
    private View ContextView=null;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mMain=(MainActivity) getActivity();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		   ContextView=inflater.inflate(R.layout.flipview, container,false);
		   flipView=(FlipViewController)ContextView.findViewById(R.id.FlipViewController);
		   flipView.setAdapter(new BaseAdapter() {
			@Override
			public int getCount() {
				return 10;
			}

			@Override
			public Object getItem(int position) {
				return position;
			}

			@Override
			public long getItemId(int position) {
				return position;
			}

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				NumberTextView view;
				if (convertView == null) {
					final Context context = parent.getContext();
					view = new NumberTextView(context, position);
					view.setTextSize(35);
				}
				else {
					view = (NumberTextView) convertView;
					view.setNumber(position);
				}
				
				return view;
			}
		});
		return ContextView;
	}
	
	
}
