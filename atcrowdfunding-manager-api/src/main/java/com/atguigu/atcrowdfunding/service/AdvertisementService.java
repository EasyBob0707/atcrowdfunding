package com.atguigu.atcrowdfunding.service;

import java.util.Map;

import com.atguigu.atcrowdfunding.bean.Advertisement;
import com.atguigu.atcrowdfunding.utils.Datas;
import com.atguigu.atcrowdfunding.utils.Page;

public interface AdvertisementService {
	
	public Advertisement queryAdvertisement(Map<String, Object> AdvertisementMap);

	public Page<Advertisement> pageQuery(Map<String, Object> AdvertisementMap);

	public int queryCount(Map<String, Object> AdvertisementMap);

	public int insertAdvertisement(Advertisement Advertisement);

	public Advertisement queryById(Integer id);

	public int updateAdvertisement(Advertisement Advertisement);

	public int deleteAdvertisement(Integer id);

	public int deleteAdvertisements(Datas ds);
}
