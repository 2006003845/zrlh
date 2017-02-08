package com.example.databasedemo;

import net.tsz.afinal.FinalDb;
import android.content.Context;

public class DBUtils {
	private static DBUtils dbUtils;
	
	private DBUtils(){}
	
	public static DBUtils getDBUtil(){
		if(dbUtils==null){
			dbUtils = new DBUtils();
		}
		return dbUtils;
	}
	
	FinalDb db;
	public void init(Context context) {
		db = FinalDb.create(context, "db");
		UserInfo user = new UserInfo();
		
		
		db.save(user);
		db.update(user);
		db.delete(user);
		db.findAll(UserInfo.class);
	}
	
	public void insert(Object object){
		db.save(object);
	}
	
	public void update(Object object){
		db.update(object);
	}
	
	public void update(Object object,String conditions){
		db.update(object, conditions);
	}
	
	public void delete(Object object){
		db.delete(object);
	}
	
	
}
