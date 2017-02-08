package com.example.databasedemo;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

public class Profession extends Obj implements Serializable {

	public Profession(String id, String name) {
		super(id, name);
	}

	public ArrayList<Obj> certs = new ArrayList<Obj>();

	public Profession(JSONObject json) throws JSONException {
		super(json);
		if (!json.isNull(Protocol.ProtocolKey.KEY_Id))
			id = json.getString(Protocol.ProtocolKey.KEY_Id);
		if (!json.isNull(Protocol.ProtocolKey.KEY_Name)) {
			name = json.getString(Protocol.ProtocolKey.KEY_Name);
		}
		if (!json.isNull(Protocol.ProtocolKey.KEY_Cert)) {
			String cs = json.getString(Protocol.ProtocolKey.KEY_Cert);
			String[] css = cs.split("|");
			certs.clear();
			for (int i = 0; i < css.length; i++) {
				certs.add(new Cert(css[i]));
			}
		}
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 5358770531181148860L;

}
