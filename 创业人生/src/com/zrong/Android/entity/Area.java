package com.zrong.Android.entity;

import java.util.ArrayList;
import java.util.List;

public class Area {
	private String areaName;
	private List<Province> prvinces;
	
	
	public Area(){
		
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	
	
	public List<Province> getPrvinces() {
		return prvinces;
	}

	public void setPrvinces(List<Province> prvinces) {
		this.prvinces = prvinces;
	}

	@Override
	public String toString() {
		return this.areaName;
	}
	
	

}
