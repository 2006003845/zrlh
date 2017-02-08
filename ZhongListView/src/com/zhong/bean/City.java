package com.zhong.bean;

import java.io.Serializable;

public class City {

	/** 城市中文名：na */
	private String na;
	/** 编号: id */
	private String id;
	/** 城市全拼：as */
	private String as;
	/** 城市简拼：ss */
	private String ss;
	/** 经度：lon */
	private String lon;
	/** 纬度：lat */
	private String lat;
	public City(){
		
	}
	public City(String na, String id, String as, String ss, String lon, String lat) {
		super();
		this.na = na;
		this.id = id;
		this.as = as;
		this.ss = ss;
		this.lon = lon;
		this.lat = lat;
	}

	public String getNa() {
		return na;
	}

	public void setNa(String na) {
		this.na = na;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstPY() {
		return "" + ss.charAt(0);
		// return firstPY;
	}

	public String getAs() {
		return as;
	}

	public void setAs(String as) {
		this.as = as;
	}

	public String getSs() {
		return ss;
	}

	public void setSs(String ss) {
		this.ss = ss;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

}
