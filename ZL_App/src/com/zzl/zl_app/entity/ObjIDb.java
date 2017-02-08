package com.zzl.zl_app.entity;

import java.util.List;

import com.zzl.zl_app.db.IDB;

public abstract class ObjIDb implements IDB {

	@Override
	public boolean insertObj(Obj obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateObj(Obj obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Obj> queryObj(String tableName, String[] columns, String where,
			String[] whereArgs, String groupBy, String having, String orderBy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteObj(Obj obj) {
		// TODO Auto-generated method stub
		return false;
	}

}
