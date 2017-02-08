package com.example.databasedemo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OrgSite implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 694753641010093051L;

	public OrgSite() {
		super();
	}

	public OrgSite(String name, String addr, double latitudeD,
			double longitudeD, String phone) {
		super();
		this.name = name;
		this.addr = addr;
		this.latitudeD = latitudeD;
		this.longitudeD = longitudeD;
		this.phone = phone;
	}

	public OrgSite(JSONObject jsonO) throws JSONException {
		if (!jsonO.isNull(Protocol.ProtocolKey.KEY_Name))
			name = jsonO.getString(Protocol.ProtocolKey.KEY_Name);
		if (!jsonO.isNull(Protocol.ProtocolKey.KEY_Addr))
			addr = jsonO.getString(Protocol.ProtocolKey.KEY_Addr);
		if (!jsonO.isNull(Protocol.ProtocolKey.KEY_X)){
			String ss = jsonO.getString(Protocol.ProtocolKey.KEY_X);
			if(ss!=null && !ss.equals("")){
				longitudeD = Double.parseDouble(jsonO
					.getString(Protocol.ProtocolKey.KEY_X));
			}
		}
		if (!jsonO.isNull(Protocol.ProtocolKey.KEY_Y)){
			String ss = jsonO.getString(Protocol.ProtocolKey.KEY_Y);
			if(ss!=null && !ss.equals("")){
				latitudeD = Double.parseDouble(jsonO
					.getString(Protocol.ProtocolKey.KEY_Y));
			}
		}
		if (!jsonO.isNull(Protocol.ProtocolKey.KEY_Phone))
			phone = jsonO.getString(Protocol.ProtocolKey.KEY_Phone);
	}

	public String name;
	public String addr;
	public double latitudeD;
	public double longitudeD;
	public String phone;

	public static List<OrgSite> getOrgSiteList(JSONArray array)
			throws JSONException {
		int size = array.length();
		List<OrgSite> os = new ArrayList<OrgSite>(size);
		for (int i = 0; i < size; i++) {
			JSONObject jsonO = array.getJSONObject(i);
			OrgSite u = new OrgSite(jsonO);
			os.add(u);
		}
		return os;
	}

}
