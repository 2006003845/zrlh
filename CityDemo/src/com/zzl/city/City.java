package com.zzl.city;

public class City {
	private String id;
	private String city;
	private String countyStr;
	private String[] countys;
	private String firstPY;
	private String allPY;
	private String allFristPY;

	public City() {
	}

	public City(String id, String city, String[] countys, String firstPY,
			String allPY, String allFristPY) {
		super();
		this.id = id;
		this.city = city;
		this.countys = countys;
		this.firstPY = firstPY;
		this.allPY = allPY;
		this.allFristPY = allFristPY;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCountyStr() {
		return countyStr;
	}

	public void setCountyStr(String countyStr) {
		this.countyStr = countyStr;
	}

	public String[] getCountys() {
		return countys;
	}

	public void setCountys(String[] countys) {
		this.countys = countys;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getFirstPY() {
		return firstPY;
	}

	public void setFirstPY(String firstPY) {
		this.firstPY = firstPY;
	}

	public String getAllPY() {
		return allPY;
	}

	public void setAllPY(String allPY) {
		this.allPY = allPY;
	}

	public String getAllFristPY() {
		return allFristPY;
	}

	public void setAllFristPY(String allFristPY) {
		this.allFristPY = allFristPY;
	}

	@Override
	public String toString() {
		return "City [id=" + id + ", city=" + city + ", countrys=" + countyStr
				+ ", firstPY=" + firstPY + ", allPY=" + allPY + ", allFristPY="
				+ allFristPY + "]";
	}

}
