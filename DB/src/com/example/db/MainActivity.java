package com.example.db;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.gs.db.DataBaseUtil;
import com.gs.db.TableLable;
import com.gs.table.Video;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ArrayList<TableLable> a=new ArrayList<TableLable>();
		for(int i=0;i<2;i++){
			Video entity=new Video();
			entity.length=3434;
			entity.path="path";
			entity.url="url";
			entity.size=3434;
			entity.createTime="1111111111111111";
			a.add(entity);
		}
		DataBaseUtil.inser(this, a);
		Video entity=new Video();
		entity.length=3434;
		entity.path="path";
		entity.url="url";
		entity.size=3434;
		entity.createTime="sdfsdf";
//		LocalMessageUtil.insertMsg(this, entity);
		DataBaseUtil.insert(this, entity);
		ArrayList<TableLable> list;
		try {
			list = DataBaseUtil.query(this, entity,entity.getClass().getDeclaredField("url"),entity.getClass().getDeclaredField("size"));
			if(list!=null&&list.size()>0){
				for(TableLable i:list){
					System.out.println(i);
				}
			}
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		DataBaseUtil.delete(this, entity);
		try {
			list = DataBaseUtil.query(this, entity,entity.getClass().getDeclaredField("url"));
			if(list!=null&&list.size()>0){
				for(TableLable i:list){
					System.out.println(i);
				}
			}
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		try {
			entity.path="dddddddddddddddd";
			DataBaseUtil.update(this, entity, entity.getClass().getDeclaredField("url"));
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			list = DataBaseUtil.query(this, entity,entity.getClass().getDeclaredField("url"));
			if(list!=null&&list.size()>0){
				for(TableLable i:list){
					System.out.println(i);
				}
			}
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
