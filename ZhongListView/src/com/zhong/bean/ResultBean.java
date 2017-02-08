package com.zhong.bean;

import java.io.Serializable;
import java.util.List;

public class ResultBean {

	private String flag;
	private List<City> citylist;

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public List<City> getCitylist() {
		return citylist;
	}

	public void setCitylist(List<City> citylist) {
		this.citylist = citylist;
	}

	@Override
	public String toString() {
		return "ResultBean [flag=" + flag + ", citylist=" + citylist + "]";
	}

}
