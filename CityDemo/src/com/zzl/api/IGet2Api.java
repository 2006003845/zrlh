package com.zzl.api;

import java.util.List;

import com.zzl.city.City;

public interface IGet2Api {

	public static int From_Assert = 1;
	public static int From_Net = 2;
	public static int From_SDCard = 3;

	List<City> getCityList(int from, String fileName) throws WSError;

}
