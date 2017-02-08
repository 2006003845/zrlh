package com.gs.db;

import java.lang.reflect.Field;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.text.GetChars;
import android.util.Log;


public class DataBaseUtil {
	private static final String Tag = "SQL";

	public static ArrayList<String> generateCols(Class claz) {
		ArrayList<String> cols = getFields(claz);
		ArrayList<String> superCols = getFields(claz.getSuperclass());
		if(superCols!=null&&superCols.size()>0){
			cols.addAll(superCols);
		}
		return cols;
	}


	private static ArrayList<String> getFields(Class claz) {
		ArrayList<String> cols = new ArrayList<String>();
		Field[] fields = claz.getDeclaredFields();
		for (short i = 0; i < fields.length; i++) {
			Field field = fields[i];
			String fieldName = field.getName();
			cols.add(fieldName);
		}
		return cols;
	}

	
//	===============================================================================
	
	public static long inser(Context context, ArrayList<TableLable> list) {
		DBHelper dbHelper = DBHelper.getInstance(context);
		long row = 0;
		Class<TableLable> cls=(Class<TableLable>) list.get(0).getClass();
		String tableName="";
		DBAnotation classAnotation=cls.getAnnotation(DBAnotation.class);
		if(classAnotation!=null){
			tableName=classAnotation.tableName();
		}else{
			tableName=cls.getName().toLowerCase().substring(cls.getName().lastIndexOf(".")+1);
		}
		ArrayList<String> cols = generateCols(cls);
		try {
			Log.i(Tag, "方法名：inser(Context context, ArrayList<TableLable> list)");
			for (int j = 0; j < list.size(); j++) {
				TableLable entity = list.get(j);
				ContentValues values = new ContentValues();
				for (int i = 0; i < cols.size(); i++) {
					Class tCls = entity.getClass();
					Field field = null;
					try {
						field = tCls.getField(cols.get(i));
					} catch (Exception e) {
						continue;
					}
					Class clz = field.getType();
					if(field.getName().equals("_id"))continue;
					if (clz.getName().equals("long")) {
						long k = (Long) field.get(entity);
						values.put(cols.get(i), k);
					} else if (clz == String.class) {
						String k = (String) field.get(entity);
						values.put(cols.get(i), k);
					}else if (clz.getName().equals("int")) {
						int k = (Integer) field.get(entity);
						values.put(cols.get(i), k );
					} 
				}
				Log.i(Tag, values.toString());
				dbHelper.insert(tableName, values);
				row++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.i(Tag, e.getMessage());
		}
		Log.i(Tag, "插入"+row+"条记录");
		return row;
	}
	public static long insert(Context context, TableLable entity) {
		DBHelper dbHelper = DBHelper.getInstance(context);
		long row = -1;
		ArrayList<String> cols = generateCols(entity.getClass());
		try {
			Log.i(Tag, "方法名：insert(Context context, TableLable entity)");
			ContentValues values = new ContentValues();
			for (int i = 0; i < cols.size(); i++) {
				Class tCls = entity.getClass();
				Field field = tCls.getField(cols.get(i));
				Class clz = field.getType();
				if(field.getName().equals("_id"))continue;
				if (clz == String.class) {
					String k = (String) field.get(entity);
					values.put(cols.get(i), k + "");
				} else if (clz.getName().equals("long")) {
					long k = (Long) field.get(entity);
					values.put(cols.get(i), k);
				}else if (clz.getName().equals("int")) {
					int k = (Integer) field.get(entity);
					values.put(cols.get(i), k);
				}
			}
			String tableName = getTableName(entity);
			dbHelper.insert(tableName, values);
			row=1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		Log.i(Tag, "插入"+row+"条记录");
		return row;
	}


	private static String getTableName(TableLable entity) {
		String tableName="";
		DBAnotation classAnotation=entity.getClass().getAnnotation(DBAnotation.class);
		if(classAnotation!=null){
			tableName=classAnotation.tableName();
		}else{
			tableName=entity.getClass().getName().toLowerCase().substring(entity.getClass().getName().lastIndexOf(".")+1);
		}
		return tableName;
	}
	/*public static ArrayList<Video> queryVideo(Context context,String url) {
		ArrayList<Video> list = null;
		DBHelper dbHelper = DBHelper.getInstance(context);
		Cursor cursor = null;
		try {
			cursor = dbHelper.rawQuery("select * from "
					+ VideoColumn.TABLE_NAME+" where "+VideoColumn.URL+"='"+url+"'", null);
			list = cursorToListVideo(list, cursor);
		} catch (Exception e) {
			e.printStackTrace();
			Log.i(Tag, e.getMessage());
		} finally {
			if (cursor != null) {
				cursor.close();
			}
		}
		System.out.println(list);
		return list;
	}*/
	public static ArrayList<TableLable> query(Context context,TableLable entity,Field... fields) {
		String tableName="";
		DBAnotation classAnotation=entity.getClass().getAnnotation(DBAnotation.class);
		if(classAnotation!=null){
			tableName=classAnotation.tableName();
		}else{
			tableName=entity.getClass().getName().toLowerCase().substring(entity.getClass().getName().lastIndexOf(".")+1);
		}
		ArrayList<TableLable> list = null;
		DBHelper dbHelper = DBHelper.getInstance(context);
		Cursor cursor = null;
		try {
			Log.i(Tag, "方法名：query(Context context,TableLable entity,Field... fields)");
			String value="";
			if(fields!=null&&fields.length>0){
				int i=-1;
				for(Field field:fields){
					i++;
					Class clz=field.getType();
					if(i==0){
						value+=field.getName()+"=";
					}else{
						value+=" and " +field.getName()+"=";
						
					}
					if (clz == String.class) {
						value+="'"+field.get(entity)+"'";
					} else if (clz.getName().equals("int")||clz.getName().equals("long")) {
						value+=""+field.get(entity)+"";
					}
				}
			}
			String sql="select * from "
					+ tableName+" where "+value;
			Log.i(Tag, sql);
			cursor = dbHelper.rawQuery(sql, null);
			list = cursorToList(list, cursor,(Class<TableLable>) entity.getClass());
		} catch (Exception e) {
			e.printStackTrace();
			Log.i(Tag, e.getMessage());
		} finally {
			if (cursor != null) {
				cursor.close();
			}
		}
		if(list!=null){
			Log.i(Tag,"查询到"+list.size()+"条结果\n"+ list.toString());
		}else{
			Log.i(Tag, "查询到0条结果");
			
		}
		return list;
	}
	
	private static ArrayList<TableLable> cursorToList(ArrayList<TableLable> list,
			Cursor cursor,Class<TableLable> cls) throws Exception {
		ArrayList<String> cols = generateCols(cls);
		if (cursor != null && cursor.getCount() > 0) {
			list = new ArrayList<TableLable>();
			int i = cursor.getColumnCount();
			while (cursor.moveToNext()) {
				TableLable entity = (TableLable) cls.newInstance();
				for (int k = 0; k < i; k++) {
					String colName = cursor.getColumnName(k);
					if (cols.contains(colName)) {
						Class tCls = entity.getClass();
						Field field = tCls.getField(colName);
						Class clz = field.getType();
						if (clz == String.class) {
							field.set(entity, cursor.getString(k));

						} else if (clz.getName().equals("int")) {
							field.set(entity, cursor.getInt(k));
						}else if(clz.getName().equals("long")){
							field.set(entity, cursor.getLong(k));
						}

					}
				}
				list.add(entity);
			}
		}
		return list;
	}
	public static void delete(Context context,TableLable entity) {
		String tableName=getTableName(entity);
		DBHelper dbHelper = DBHelper.getInstance(context);
		String sql = "delete from " + tableName;
		
		
		Log.i(Tag, "方法名：delete(Context context,TableLable entity)");
		Log.i(Tag, sql);
		try {
			dbHelper.ExecSQL(sql);
		} catch (Exception e) {
			e.printStackTrace();
			Log.i(Tag, e.getMessage());
		} finally {
		}
	}
	public static void  delete(Context context,TableLable entity,Field... fields) {
		String tableName=getTableName(entity);
		DBHelper dbHelper = DBHelper.getInstance(context);
		try {
			Log.i(Tag, "方法名：delete(Context context,TableLable entity,Field... fields)");
			String value="";
			if(fields!=null&&fields.length>0){
				int i=-1;
				for(Field field:fields){
					i++;
					Class clz=field.getType();
					if(i==0){
						value+=field.getName()+"=";
					}else{
						value+=" and " +field.getName()+"=";
						
					}
					if (clz == String.class) {
						value+="'"+field.get(entity)+"'";
					} else if (clz.getName().equals("int")||clz.getName().equals("long")) {
						value+=""+field.get(entity)+"";
					}
				}
			}
			String sql = "delete from " + tableName+" where "+value;
			Log.i(Tag, sql);
			dbHelper.ExecSQL(sql);
		} catch (Exception e) {
			e.printStackTrace();
			Log.i(Tag, e.getMessage());
		} finally {
		}
	}
	public static int update(Context context,TableLable entity,Field...fields){
		String tableName=getTableName(entity);
		DBHelper dbHelper = DBHelper.getInstance(context);
		int row=0;
		try {
			Log.i(Tag, "方法名：update(Context context,TableLable entity,Field...fields)");
			ArrayList<String> cols = generateCols(entity.getClass());
				ContentValues values = new ContentValues();
				for (int i = 0; i < cols.size(); i++) {
					Class tCls = entity.getClass();
					Field field = tCls.getField(cols.get(i));
					Class clz = field.getType();
					if(field.getName().equals("_id"))continue;
					if (clz == String.class) {
						String k = (String) field.get(entity);
						values.put(cols.get(i), k + "");
					} else if (clz.getName().equals("long")) {
						long k = (Long) field.get(entity);
						values.put(cols.get(i), k);
					}else if (clz.getName().equals("int")) {
						int k = (Integer) field.get(entity);
						values.put(cols.get(i), k);
					}
				}
			String[] whereArgs=null;
			String WhereClause=null;
			if(fields!=null&&fields.length>0){
				int i=-1;
				WhereClause="";
				whereArgs=new String[fields.length];
				for(Field field:fields){
					i++;
					Class clz=field.getType();
					if(i==0){
						
						WhereClause+=field.getName()+"=?";
					}else{
						WhereClause+=", and " +field.getName()+"=?";
						
					}
					if (clz == String.class) {
						whereArgs[i]=(String) field.get(entity);
					} else if (clz.getName().equals("int")||clz.getName().equals("long")) {
						whereArgs[i]=String.valueOf(field.get(entity));
					}
				}
			}
//			String sql = "update "+tableName+" set " +" where "+value;
//			Log.i(Tag, sql);
//			dbHelper.ExecSQL(sql);
			row=dbHelper.update(tableName, values, WhereClause, whereArgs);
			Log.i(Tag, "更新"+row+"条结果");
		} catch (Exception e) {
			e.printStackTrace();
			Log.i(Tag, e.getMessage());
		} finally {
		}
		return row;
	}
}
