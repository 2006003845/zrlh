package com.zzl.zl_app.db;

import java.util.List;

import com.zzl.zl_app.entity.Obj;

public interface IDB {

	public boolean insertObj(Obj obj);

	public boolean updateObj(Obj obj);

	public List<Obj> queryObj(String tableName, String[] columns, String where,
			String[] whereArgs, String groupBy, String having, String orderBy);

	public boolean deleteObj(Obj obj);
}
