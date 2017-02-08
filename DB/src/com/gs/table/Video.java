package com.gs.table;


import com.gs.db.DBAnotation;
import com.gs.db.TableLable;

@DBAnotation(tableName="video")
public class Video extends TableLable{
	@DBAnotation(_id="")
	public int _id;
	@DBAnotation(type="text")
	public String url;
	@DBAnotation(type="text")
	public String path;
	@DBAnotation
	public long length;
	@DBAnotation(type="integer")
	public int size;
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return _id+"  "+url+"    "+path+"    "+length+"  "+createTime;
	}
}
