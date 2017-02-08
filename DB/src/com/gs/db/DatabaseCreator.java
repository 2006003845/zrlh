package com.gs.db;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;


import android.provider.BaseColumns;

public abstract class DatabaseCreator implements BaseColumns {
	/**
	 * The database's name
	 */
	public static final String DATABASE_NAME = "gs.db";
	/**
	 * The version of current database
	 */
	public static final int DATABASE_VERSION = 1;
	/**
	 * Classes's name extends from this class.
	 */
	public static final String[] SUBCLASSES = new String[] {
			"com.gs.table.Video"};

	public static ArrayList<Table> createTables(){
		ArrayList<Table> createTableSqls=new ArrayList<Table>();
		for(String clssName: SUBCLASSES){
			createTableSqls.add(getTableCreator(clssName));
		}
		return createTableSqls;
	}
	
	/**
	 * Get sub-classes of this class.
	 * @return Array of sub-classes.
	 */
	@SuppressWarnings("unchecked")
	public static final Class<TableLable> getSubClasse(String className) {
		Class<TableLable> subClass = null;
			try {
				subClass = (Class<TableLable>) Class.forName(className);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		return subClass;
	}

	/**
	 * Create a sentence to create a table by using a hash-map.
	 * 
	 * @param tableName
	 *            The table's name to create.
	 * @param map
	 *            A map to store table columns info.
	 * @return
	 */
	private static final Table getTableCreator(String className) {
		Map<String, String> map=new LinkedHashMap<String, String>();
		
		//Ö÷¼ü
		map.put(_ID, "integer primary key autoincrement");
		Class<TableLable> clz=getSubClasse(className);
		String tableName="";
		DBAnotation classAnotation=clz.getAnnotation(DBAnotation.class);
		if(classAnotation!=null){
			tableName=classAnotation.tableName();
		}else{
			tableName=className.toLowerCase().substring(className.lastIndexOf(".")+1);
		}
		getFields(map, clz);
		try {
			Class<TableLable> clz2=(Class<TableLable>) clz.getSuperclass();
			getFields(map, clz2);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		
		String[] keys = map.keySet().toArray(new String[0]);
		String value = null;
		StringBuilder creator = new StringBuilder();
		creator.append("CREATE TABLE ").append(tableName).append("( ");
		int length = keys.length;
		for (int i = 0; i < length; i++) {
			value = map.get(keys[i]);
			creator.append(keys[i]).append(" ");
			creator.append(value);
			if (i < length - 1) {
				creator.append(",");
			}
		}
		creator.append(")");
		Table table=new Table(tableName, creator.toString());
		return table;
	}

	private static void getFields(Map<String, String> map, Class<TableLable> clz) {
		Field[] fields = clz.getDeclaredFields();
		if(fields!=null&&fields.length>0){
			for(Field field:fields){
				if(field.getName().equals("_id"))continue;
				DBAnotation anotation=field.getAnnotation(DBAnotation.class);
				if(anotation!=null){
					map.put(field.getName(), anotation.type());
				}
			}
		}
		
	}
}
